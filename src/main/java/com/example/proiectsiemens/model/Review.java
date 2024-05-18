package com.example.proiectsiemens.model;

public class Review {
    private int reviewId;
    private int reservationId;
    private int rating;
    private String comment;

    public Review() {
    }

    public Review(int reviewId, int reservationId, int rating, String comment) {
        this.reviewId = reviewId;
        this.reservationId = reservationId;
        this.rating = rating;
        this.comment = comment;
    }

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Review{" +
                "reviewId=" + reviewId +
                ", reservationId=" + reservationId +
                ", rating=" + rating +
                ", comment='" + comment + '\'' +
                '}';
    }
}
