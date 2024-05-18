package com.example.proiectsiemens.controller;

import com.example.proiectsiemens.dao.ReservationDAO;
import com.example.proiectsiemens.dao.ReviewDAO;
import com.example.proiectsiemens.dao.RoomDAO;
import com.example.proiectsiemens.model.Reservation;
import com.example.proiectsiemens.model.Review;
import com.example.proiectsiemens.model.Room;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
public class BookingController {

    private final RoomDAO roomDAO;
    private final ReservationDAO reservationDAO;

    private final ReviewDAO reviewDAO;

    public BookingController(RoomDAO roomDAO, ReservationDAO reservationDAO, ReviewDAO reviewDAO) {
        this.roomDAO = roomDAO;
        this.reservationDAO = reservationDAO;
        this.reviewDAO = reviewDAO;
    }

    @GetMapping("/reserveRoom/{roomId}")
    public String getHotelDetails(@PathVariable int roomId ,Model model) {
        Room room=roomDAO.findById(roomId);

        Reservation reservation=new Reservation();
        reservation.setRoomId(roomId);
        model.addAttribute("room",room);
        model.addAttribute("reservation", reservation);
        return "bookingPage";
    }

    @PostMapping("/reserveRoom")
    public String processReservation(@ModelAttribute Reservation reservationForm, Model model) {
        Room room = roomDAO.findById(reservationForm.getRoomId());

        room.setAvailable(false);
        roomDAO.update(room);

        reservationDAO.insert(reservationForm);

        model.addAttribute("reservation", reservationForm);
        return "reservationSuccess";
    }

    @GetMapping("/reservations")
    public String viewReservations(Model model) {
        List<Reservation> reservations = reservationDAO.findAll();
        model.addAttribute("reservations", reservations);
        return "reservationsPage";
    }

    @GetMapping("/reservationDetails/{id}")
    public String reservationDetails(@PathVariable int id, Model model) {
        Optional<Reservation> reservationOpt = Optional.ofNullable(reservationDAO.findById(id));
        if (reservationOpt.isPresent()) {
            Reservation reservation = reservationOpt.get();
            LocalDateTime endDate = reservation.getStartDate().plusDays(reservation.getNumNights());
            LocalDateTime now = LocalDateTime.now();

            boolean canLeaveReview = now.isAfter(endDate);
            System.out.println(now);
            boolean canCancel = reservation.getStartDate().isAfter(now.plusHours(2));
            int bookedRoomID = reservation.getRoomId();
            Room room=roomDAO.findById(bookedRoomID);
            double totalPrice = room.getPrice()*reservation.getNumNights();
            Duration duration=Duration.between(reservation.getStartDate(),now);

            Review review=new Review();
            review.setReservationId(reservation.getId());

            model.addAttribute("reservation", reservation);
            model.addAttribute("canLeaveReview", canLeaveReview);
            model.addAttribute("canCancel", canCancel);
            model.addAttribute("review", new Review());
            model.addAttribute("totalPrice",totalPrice);
            model.addAttribute("roomType",room.getType());
            model.addAttribute("review",review);
            model.addAttribute("timeLeft",duration.toHours());
            return "reservationDetails";
        }
        return "redirect:/reservations";
    }

    @PostMapping("/leaveReview")
    public String leaveReview(@ModelAttribute Review review,Model model) {
        System.out.println(review);
        reviewDAO.insert(review);

        model.addAttribute("review",review);
        return "redirect:/reservations";
    }

    @PostMapping("/cancelReservation")
    public String cancelReservation(@RequestParam int reservationId) {
        reservationDAO.deleteById(reservationId);
        return "redirect:/reservations";
    }
}
