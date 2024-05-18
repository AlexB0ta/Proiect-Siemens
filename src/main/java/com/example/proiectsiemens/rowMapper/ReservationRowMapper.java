package com.example.proiectsiemens.rowMapper;

import com.example.proiectsiemens.model.Reservation;
import com.mysql.cj.result.Row;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReservationRowMapper implements RowMapper<Reservation> {
    @Override
    public Reservation mapRow(ResultSet rs, int rowNum) throws SQLException {
        Reservation reservation = new Reservation();
        reservation.setId(rs.getInt("id"));
        reservation.setName(rs.getString("name"));
        reservation.setCnp(rs.getString("cnp"));
        reservation.setStartDate(rs.getTimestamp("startDate").toLocalDateTime());
        reservation.setNumNights(rs.getInt("numNights"));
        reservation.setPayMethod(rs.getString("payMethod"));
        reservation.setRoomId(rs.getInt("roomId"));
        return reservation;
    }
}
