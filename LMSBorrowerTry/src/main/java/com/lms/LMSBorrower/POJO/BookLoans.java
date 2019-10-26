package com.lms.LMSBorrower.POJO;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_book_loans")
public class BookLoans implements Serializable{


	private static final long serialVersionUID = 9187897266122342615L;

	@EmbeddedId
	private BookLoansCompositeKey blCompKey;
	
	@Column(name = "dateOut")
	private Date dateOut;
	
	@Column(name = "dueDate")
	private Date dueDate;
	
	public BookLoans() {}
	
	public BookLoans(BookLoansCompositeKey blCompKey, Date dateOut, Date dueDate) {
		super();
		this.blCompKey = blCompKey;
		this.dateOut = dateOut;
		this.dueDate = dueDate;
	}

	public BookLoansCompositeKey getBlCompKey() {
		return blCompKey;
	}

	public void setBlCompKey(BookLoansCompositeKey blCompKey) {
		this.blCompKey = blCompKey;
	}

	public Date getDateOut() {
		return dateOut;
	}

	public void setDateOut(Date dateOut) {
		this.dateOut = dateOut;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((blCompKey == null) ? 0 : blCompKey.hashCode());
		result = prime * result + ((dateOut == null) ? 0 : dateOut.hashCode());
		result = prime * result + ((dueDate == null) ? 0 : dueDate.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BookLoans other = (BookLoans) obj;
		if (blCompKey == null) {
			if (other.blCompKey != null)
				return false;
		} else if (!blCompKey.equals(other.blCompKey))
			return false;
		if (dateOut == null) {
			if (other.dateOut != null)
				return false;
		} else if (!dateOut.equals(other.dateOut))
			return false;
		if (dueDate == null) {
			if (other.dueDate != null)
				return false;
		} else if (!dueDate.equals(other.dueDate))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BookLoans [blCompKey=" + blCompKey + ", dateOut=" + dateOut + ", dueDate=" + dueDate + "]";
	}

}