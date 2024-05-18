package com.example.proiectsiemens.bll;

import com.example.proiectsiemens.dao.HotelDAO;
import com.example.proiectsiemens.dao.ReservationDAO;
import com.example.proiectsiemens.dao.ReviewDAO;
import com.example.proiectsiemens.dao.RoomDAO;
import com.example.proiectsiemens.model.Hotel;
import com.example.proiectsiemens.model.Room;
import org.json.simple.parser.ParseException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.List;
@Component
public class LoadData implements CommandLineRunner {

    private final HotelDAO hotelDAO;

    private final RoomDAO roomDAO;
    private final ReservationDAO reservationDAO;
    private final ReviewDAO reviewDAO;

    public LoadData(HotelDAO hotelDAO, RoomDAO roomDAO, ReservationDAO reservationDAO, ReviewDAO reviewDAO) {
        this.hotelDAO = hotelDAO;
        this.roomDAO = roomDAO;
        this.reservationDAO = reservationDAO;
        this.reviewDAO = reviewDAO;
    }

    @Override
    public void run(String... args) throws Exception {
        List<Hotel> hotelsToDel=hotelDAO.findAll();
        for(Hotel hotel:hotelsToDel){
            hotelDAO.deleteById(hotel.getId());
        }
        List<Room> roomsToDel=roomDAO.findAll();
        for(Room room:roomsToDel){
            roomDAO.deleteById(room.getRoomNumber());
        }


        List<Hotel> hotelList= null;
        try {
            hotelList = FileRead.readJsonFile("C:\\Users\\alexb\\OneDrive\\Desktop\\ProiectSiemens\\src\\main\\resources\\hotels.json");
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }

        for(Hotel hotel:hotelList){
            hotelDAO.insert(hotel);
            for(Room room:hotel.getRooms()){
                roomDAO.insert(room);
            }
        }
    }
}
