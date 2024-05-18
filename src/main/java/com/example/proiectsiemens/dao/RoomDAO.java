package com.example.proiectsiemens.dao;

import com.example.proiectsiemens.model.Room;
import com.example.proiectsiemens.rowMapper.RoomRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
@Service
public class RoomDAO implements InterfaceDAO<Room> {

    private final JdbcTemplate jdbcTemplate;

    public RoomDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Room> findAll() {
        String query = "SELECT * FROM room";
        return jdbcTemplate.query(query, new RoomRowMapper());
    }
    public List<Room> findAllByHotelId(int id) {
        String query = "SELECT * FROM room WHERE hotelId = ?";
        return jdbcTemplate.query(query, new Object[]{id}, new RoomRowMapper());
    }

    @Override
    public Room findById(int id) {
        String query = "SELECT * FROM room WHERE roomNumber = ?";
        return jdbcTemplate.queryForObject(query, new Object[]{id}, new RoomRowMapper());
    }

    @Override
    public int insert(Room room) {
        String query = "INSERT IGNORE INTO room (roomNumber, hotelId, type, price, isAvailable) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(query, room.getRoomNumber(), room.getHotelId(), room.getType(), room.getPrice(), room.isAvailable());
        return 0;
    }

    @Override
    public int update(Room room) {
        String query = "UPDATE room SET type = ?, price = ?, isAvailable = ? WHERE roomNumber = ? AND hotelId = ?";
        jdbcTemplate.update(query, room.getType(), room.getPrice(), room.isAvailable(), room.getRoomNumber(), room.getHotelId());
        return 0;
    }

    @Override
    public int deleteById(int id) {
        String query = "DELETE FROM room WHERE roomNumber = ?";
        jdbcTemplate.update(query, id);
        return id;
    }
}
