package com.recruitment.bookCase.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Entity
@Data
@Table(name ="Book")
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @NotEmpty
    @Column(name = "Title", nullable = false)
    private String title;

    @Column(name = "Purchase", nullable = false)
    private String dateOfPurchase;

    @Column(name="Read", nullable = false)
    private String read;

//    public Long getId() {
//        return Id;
//    }
//
//    public void setId(Long id) {
//        Id = id;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getDateOfPurchase() {
//        return dateOfPurchase;
//    }
//
//    public void setDateOfPurchase(String dateOfPurchase) {
//        this.dateOfPurchase = dateOfPurchase;
//    }
//
//    public String getRead() {
//        return read;
//    }
//
//    public void setRead(String read) {
//        this.read = read;
//    }
}