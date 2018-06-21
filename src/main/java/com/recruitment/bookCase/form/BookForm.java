package com.recruitment.bookCase.form;

import java.util.Date;

public class BookForm {


    private String Title;
    private String Author;
    private String Read;
    private Date boughtDate;

    public BookForm() {
    }

    public BookForm(String title, String author, String read, Date boughtDate) {
        Title = title;
        Author = author;
        Read = read;
        this.boughtDate = boughtDate;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public String getRead() {
        return Read;
    }

    public void setRead(String read) {
        Read = read;
    }

    public Date getBoughtDate() {
        return boughtDate;
    }

    public void setBoughtDate(Date boughtDate) {
        this.boughtDate = boughtDate;
    }
}


