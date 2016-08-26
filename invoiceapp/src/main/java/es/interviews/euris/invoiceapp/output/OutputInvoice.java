package es.interviews.euris.invoiceapp.output;

import java.math.BigInteger;
import java.util.Date;

public class OutputInvoice {

	private BigInteger number;
	private Date date;
	private Date paymentDueDate;

	public OutputInvoice(BigInteger number, Date date, Date paymentDueDate) {
		super();
		this.number = number;
		this.date = date;
		this.paymentDueDate = paymentDueDate;
	}

	public BigInteger getNumber() {
		return number;
	}

	public Date getDate() {
		return date;
	}

	public Date getPaymentDueDate() {
		return paymentDueDate;
	}

}
