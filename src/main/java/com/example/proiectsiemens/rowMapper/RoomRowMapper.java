package com.example.proiectsiemens.rowMapper;

import com.example.proiectsiemens.model.Room;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoomRowMapper implements RowMapper<Room> {

    @Override
    public Room mapRow(ResultSet rs, int rowNum) throws SQLException {
        Room room = new Room();
        room.setRoomNumber(rs.getInt("roomNumber"));
        room.setHotelId(rs.getInt("hotelId"));
        room.setType(rs.getInt("type"));
        room.setPrice(rs.getDouble("price"));
        room.setAvailable(rs.getBoolean("isAvailable"));
        return room;
    }
}
