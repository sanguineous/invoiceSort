package es.interviews.euris.invoiceapp.input;

import java.math.BigInteger;
import java.util.Date;

public class InputInvoice {
	private BigInteger number;
	private Date date;
	private PaymentMode paymentMode;
	
	public InputInvoice(BigInteger invoiceNumber, Date invoiceDate, PaymentMode paymentMode) {
		this.number = invoiceNumber;
		this.date = invoiceDate;
		this.paymentMode = paymentMode;
	}

	public BigInteger getNumber() {
		return number;
	}

	public Date getDate() {
		return date;
	}

	public PaymentMode getPaymentMode() {
		return paymentMode;
	}

	public enum PaymentMode {
		DF, DFFM, DF60
	}
}
