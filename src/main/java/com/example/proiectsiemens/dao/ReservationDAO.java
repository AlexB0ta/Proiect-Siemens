package com.example.proiectsiemens.dao;

import com.example.proiectsiemens.model.Reservation;
import com.example.proiectsiemens.rowMapper.ReservationRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ReservationDAO implements InterfaceDAO<Reservation> {

    private final JdbcTemplate jdbcTemplate;

    public ReservationDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Reservation> findAll() {
        String sql = "SELECT * FROM reservation";
        return jdbcTemplate.query(sql, new ReservationRowMapper());
    }

    @Override
    public Reservation findById(int id) {
        String sql = "SELECT * FROM reservation WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new ReservationRowMapper());
    }

    @Override
    public int insert(Reservation reservation) {
        String sql = "INSERT INTO reservation (name, cnp, startDate, numNights, payMethod, roomId) VALUES (?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, reservation.getName(), reservation.getCnp(), reservation.getStartDate(),
                reservation.getNumNights(), reservation.getPayMethod(), reservation.getRoomId());
    }

    @Override
    public int update(Reservation reservation) {
        String sql = "UPDATE reservation SET name = ?, cnp = ?, startDate = ?, numNights = ?, payMethod = ?, roomId = ? WHERE id = ?";
        return jdbcTemplate.update(sql, reservation.getName(), reservation.getCnp(), reservation.getStartDate(),
                reservation.getNumNights(), reservation.getPayMethod(), reservation.getRoomId(), reservation.getId());
    }

    @Override
    public int deleteById(int id) {
        String sql = "DELETE FROM reservation WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }
}
