package com.wallet.walletmgt.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "transactions")
public class Transactions {
	
	@Id
    @Column(name = "transactions_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
	@Column(name = "user_id")
	 private int userId;
	
	 @Column(name = "date_of_trans")
	 private Date date;
	 
	 @Column(name = "balence")
	 private double balence;
	 
	 @Column(name = "debit")
	 private double debit;
	 
	 @Column(name = "credit")
	 private double credit;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int i) {
		this.userId = i;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getBalence() {
		return balence;
	}

	public void setBalence(double balence) {
		this.balence = balence;
	}

	public double getDebit() {
		return debit;
	}

	public void setDebit(double debit) {
		this.debit = debit;
	}

	public double getCredit() {
		return credit;
	}

	public void setCredit(double credit) {
		this.credit = credit;
	}

	@Override
	public String toString() {
		return "Transactions [id=" + id + ", userId=" + userId + ", date=" + date + ", balence=" + balence + ", debit="
				+ debit + ", credit=" + credit + "]";
	}

	public Transactions() {
		super();
		// TODO Auto-generated constructor stub
	}

	
}
