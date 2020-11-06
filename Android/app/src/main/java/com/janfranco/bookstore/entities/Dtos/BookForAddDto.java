package com.janfranco.bookstore.entities.Dtos;

public class BookForAddDto {

    private String title, author;
    private String cover;
    private int pageCount;

    public BookForAddDto(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public BookForAddDto(String title, String author, String cover) {
        this(title, author);
        this.cover = cover;
    }

    public BookForAddDto(String title, String author, int pageCount) {
        this(title, author);
        this.pageCount = pageCount;
    }

    public BookForAddDto(String title, String author, String cover, int pageCount) {
        this(title, author);
        this.cover = cover;
        this.pageCount = pageCount;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getCover() {
        return cover;
    }

    public int getPageCount() {
        return pageCount;
    }

}
