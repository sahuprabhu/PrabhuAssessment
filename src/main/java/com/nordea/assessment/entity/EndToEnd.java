package com.nordea.assessment.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Setter
@Getter
@Data
@Entity
@Table(name = "ENDTOEND")
@ToString
public class EndToEnd {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(length = 35)
	private String EndToEndId;
	
	@Column
	private Date modifiedTimeStamp = new Date();
	
}
