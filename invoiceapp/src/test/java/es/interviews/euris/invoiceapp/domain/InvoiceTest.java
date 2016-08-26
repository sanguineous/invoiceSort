package es.interviews.euris.invoiceapp.domain;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import junit.framework.TestCase;

public class InvoiceTest extends TestCase {

	public void testPaymentDueDate() throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date invoiceDate = dateFormat.parse("23/03/2013");
		Invoice aDFInvoice = new Invoice(
				BigInteger.valueOf(1L), 
				invoiceDate, 
				PaymentMode.DF);
		Invoice aDF60Invoice = new Invoice(
				BigInteger.valueOf(1L), 
				invoiceDate, 
				PaymentMode.DF60);
		Invoice aDFFMInvoice = new Invoice(
				BigInteger.valueOf(1L), 
				invoiceDate, 
				PaymentMode.DFFM);
		
		assertEquals(aDFInvoice.paymentDueDate().getTime(), invoiceDate.getTime());

		assertEquals(aDFFMInvoice.paymentDueDate().getTime(), 
				dateFormat.parse("31/03/2013").getTime());

		assertEquals(aDF60Invoice.paymentDueDate().getTime(), 
				dateFormat.parse("23/05/2013").getTime());
		
	}

}
