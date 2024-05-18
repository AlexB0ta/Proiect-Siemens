package com.example.proiectsiemens.rowMapper;

import com.example.proiectsiemens.model.Hotel;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HotelRowMapper implements RowMapper<Hotel> {
    @Override
    public Hotel mapRow(ResultSet rs, int rowNum) throws SQLException {
        Hotel hotel = new Hotel();
        hotel.setId(rs.getInt("id"));
        hotel.setName(rs.getString("name"));
        hotel.setLatitude(rs.getDouble("latitude"));
        hotel.setLongitude(rs.getDouble("longitude"));
        return hotel;
    }

}
