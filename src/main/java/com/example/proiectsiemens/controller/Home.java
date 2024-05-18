package com.example.proiectsiemens.controller;

import com.example.proiectsiemens.bll.DistanceCalculator;
import com.example.proiectsiemens.bll.FileRead;
import com.example.proiectsiemens.dao.HotelDAO;
import com.example.proiectsiemens.dao.ReviewDAO;
import com.example.proiectsiemens.dao.RoomDAO;
import com.example.proiectsiemens.model.Hotel;
import com.example.proiectsiemens.model.LocationData;
import com.example.proiectsiemens.model.Review;
import com.example.proiectsiemens.model.Room;
import org.json.simple.parser.ParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class Home {
    private  final HotelDAO hotelDAO;

    private final RoomDAO roomDAO;

    private final ReviewDAO reviewDAO;

    private List<Hotel> hotelsInRange=new ArrayList<>();

    public Home(HotelDAO hotelDAO, RoomDAO roomDAO, ReviewDAO reviewDAO) {
        this.hotelDAO = hotelDAO;
        this.roomDAO = roomDAO;
        this.reviewDAO = reviewDAO;
    }

    @GetMapping("/home")
    public String home() {

        return "HomePage";
    }


    @PostMapping("/searchHotels")
    public ResponseEntity<String>  searchHotels(@RequestBody(required = false) LocationData locationData) {
        hotelsInRange.clear();


        double latitude = locationData.getLatitude();
        double longitude = locationData.getLongitude();
        int radius = locationData.getRaza();
        List<Hotel> hotelList=hotelDAO.findAll();


        for (Hotel hotel : hotelList) {
            double hotelLat = hotel.getLatitude();
            double hotelLong = hotel.getLongitude();

            double distance = DistanceCalculator.calculateDistance(latitude, longitude, hotelLat, hotelLong)/1000;

            System.out.println(hotel);

            if (distance <= radius) {
                    hotelsInRange.add(hotel);
            }
        }

        return ResponseEntity.ok("Succes");
    }

    @GetMapping("/displayHotels")
    public String displayValues(Model model) {

        model.addAttribute("hotelsInRange", hotelsInRange);

        return "showHotels";
    }

    @GetMapping("/hotelDetails/{hotelId}")
    public String getHotelDetails(@PathVariable int hotelId, Model model) {
        Hotel hotel = hotelDAO.findById(hotelId);

        List<Room> rooms=roomDAO.findAllByHotelId(hotelId);
        List<Review> reviewList=new ArrayList<>();
        double hotelRating = 0;
        for(Room room:rooms){
            List<Review> reviewPerRoom=reviewDAO.getReviewsForRooms(room.getRoomNumber());
            for(Review review:reviewPerRoom){
                reviewList.add(review);
                hotelRating+=review.getRating();
            }
        }
        hotelRating/=reviewList.size();

        model.addAttribute("hotelRating",hotelRating);
        model.addAttribute("hotel", hotel);
        model.addAttribute("rooms",rooms);
        model.addAttribute("reviews",reviewList);
        return "hotelDetails";
    }


}
