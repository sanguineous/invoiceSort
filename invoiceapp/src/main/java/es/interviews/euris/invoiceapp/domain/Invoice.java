package es.interviews.euris.invoiceapp.domain;

import java.math.BigInteger;
import java.util.Date;

public class Invoice {

	private BigInteger number;
	private Date date;
	private PaymentMode paymentMode;
	private Date dueDate;

	public Invoice(BigInteger number, Date date, PaymentMode paymentMode) {
		this.number = number;
		this.date = date;
		this.paymentMode = paymentMode;
	}

	public Date paymentDueDate() {
		if(dueDate == null){
			dueDate = paymentMode.dueDateFor(date);
		}
		return dueDate;
	}

	public BigInteger getNumber() {
		return number;
	}

	public Date getDate() {
		return date;
	}

}
