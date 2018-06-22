package com.recruitment.bookcase.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name="BOOK")
public class Book implements Serializable{

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;

	@NotEmpty
	@Column(name="TITLE", nullable=false)
	private String title;

	@Column(name="DATE_OF_PURCHASE", nullable=false)
	private String dateOfPurchase;

	@Column(name="READ", nullable=false)
	private String isRead;

}
