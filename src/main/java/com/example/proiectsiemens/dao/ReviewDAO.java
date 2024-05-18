package com.example.proiectsiemens.dao;

import com.example.proiectsiemens.model.Review;
import com.example.proiectsiemens.rowMapper.ReviewRowMapper;
import com.example.proiectsiemens.rowMapper.RoomRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ReviewDAO implements InterfaceDAO<Review>{

    private final JdbcTemplate jdbcTemplate;

    public ReviewDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Review> findAll() {
        String query = "SELECT * FROM review";
        return jdbcTemplate.query(query, new ReviewRowMapper());
    }

    @Override
    public Review findById(int id) {
        String query = "SELECT * FROM review WHERE reviewId = ?";
        return jdbcTemplate.queryForObject(query, new Object[]{id}, new ReviewRowMapper());
    }

    public List<Review> getReviewsForRooms(int roomId) {
        String sql = "SELECT r.reviewId, r.reservationId, r.rating, r.comment " +
                "FROM review r " +
                "JOIN reservation res ON r.reservationId = res.id " +
                "WHERE res.roomId = ?";

        return jdbcTemplate.query(sql, new Object[]{roomId}, new ReviewRowMapper());
    }

    @Override
    public int insert(Review review) {
        String sql = "INSERT IGNORE INTO review (reservationId, rating, comment) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql, review.getReservationId(), review.getRating(), review.getComment());
    }

    @Override
    public int update(Review review) {
        String sql = "UPDATE review SET rating = ?, comment = ? WHERE reviewId = ?";
        return jdbcTemplate.update(sql, review.getRating(), review.getComment(), review.getReviewId());
    }

    @Override
    public int deleteById(int id) {
        String sql = "DELETE FROM review WHERE reviewId = ?";
        return jdbcTemplate.update(sql, id);
    }
}
