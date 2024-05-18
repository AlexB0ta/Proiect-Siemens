package com.example.proiectsiemens.dao;

import com.example.proiectsiemens.model.Hotel;
import com.example.proiectsiemens.rowMapper.HotelRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class HotelDAO implements InterfaceDAO<Hotel> {

    private final JdbcTemplate jdbcTemplate;

    public HotelDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Hotel> findAll() {
        String sql="select * from hotel";
        return jdbcTemplate.query(sql,new HotelRowMapper());
    }

    @Override
    public Hotel findById(int id) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM hotel WHERE id = ?",
                new Object[]{id},
                new HotelRowMapper()
        );
    }

    @Override
    public int insert(Hotel hotel) {
        return jdbcTemplate.update(
                "INSERT IGNORE INTO hotel (id, name, latitude, longitude) VALUES (?, ?, ?, ?)",
                hotel.getId(), hotel.getName(), hotel.getLatitude(), hotel.getLongitude()
        );
    }

    @Override
    public int update(Hotel hotel) {
        return jdbcTemplate.update(
                "UPDATE hotel SET name = ?, latitude = ?, longitude = ? WHERE id = ?",
                hotel.getName(), hotel.getLatitude(), hotel.getLongitude(), hotel.getId()
        );
    }

    @Override
    public int deleteById(int id) {
        return jdbcTemplate.update(
                "DELETE FROM hotel WHERE id = ?",
                id
        );
    }

}
