package com.example.proiectsiemens.bll;

import com.example.proiectsiemens.model.Hotel;
import com.example.proiectsiemens.model.Room;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileRead {
    public static List<Hotel> readJsonFile(String filePath) throws IOException, ParseException {
        List<Hotel> hotels = new ArrayList<>();
        JSONParser parser = new JSONParser();
        try (FileReader reader = new FileReader(filePath)) {
            Object obj = parser.parse(reader);
            JSONArray hotelArray = (JSONArray) obj;
            for (Object hotelObj : hotelArray) {
                JSONObject hotelJson = (JSONObject) hotelObj;
                Hotel hotel = new Hotel();
                hotel.setId((int) (long) hotelJson.get("id"));
                hotel.setName((String) hotelJson.get("name"));
                hotel.setLatitude((double) hotelJson.get("latitude"));
                hotel.setLongitude((double) hotelJson.get("longitude"));
                JSONArray roomsArray = (JSONArray) hotelJson.get("rooms");
                List<Room> rooms = new ArrayList<>();
                for (Object roomObj : roomsArray) {
                    JSONObject roomJson = (JSONObject) roomObj;
                    Room room = new Room();
                    room.setRoomNumber((int) (long) roomJson.get("roomNumber"));
                    room.setType((int) (long) roomJson.get("type"));
                    room.setPrice((double) (long)roomJson.get("price"));
                    room.setAvailable((boolean) roomJson.get("isAvailable"));
                    room.setHotelId((int) (long) hotelJson.get("id"));
                    rooms.add(room);
                }
                hotel.setRooms(rooms);
                hotels.add(hotel);
            }
        }
        return hotels;
    }

}
