package com.gustavoschaedler.api.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name="tblTicket")
public class Ticket implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;

	@NotNull
	private int number;
	
	@NotNull
	private short n1;
	
	@NotNull
	private short n2;
	
	@NotNull
	private short n3;
	
	@NotNull
	private short result = -1;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public short getN1() {
		return n1;
	}

	public void setN1(short n1) {
		this.n1 = n1;
	}

	public short getN2() {
		return n2;
	}

	public void setN2(short n2) {
		this.n2 = n2;
	}

	public short getN3() {
		return n3;
	}

	public void setN3(short n3) {
		this.n3 = n3;
	}
	
	public short getResult() {
		return result;
	}

	public void setResult(short result) {
		this.result = result;
	}

}
