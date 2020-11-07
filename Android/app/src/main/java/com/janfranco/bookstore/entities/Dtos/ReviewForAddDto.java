package com.janfranco.bookstore.entities.Dtos;

public class ReviewForAddDto {

    private String bookId;
    private String review;
    private float star;

    public ReviewForAddDto(String bookId, String review) {
        this.bookId = bookId;
        this.review = review;
    }

    public ReviewForAddDto(String bookId, float star) {
        this.bookId = bookId;
        this.star = star;
    }

    public ReviewForAddDto(String bookId, String review, float star) {
        this.bookId = bookId;
        this.review = review;
        this.star = star;
    }

    public String getBookId() {
        return bookId;
    }

    public String getReview() {
        return review;
    }

    public float getStar() {
        return star;
    }

}
