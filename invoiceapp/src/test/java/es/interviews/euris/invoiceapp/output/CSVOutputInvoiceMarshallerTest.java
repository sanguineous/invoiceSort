package es.interviews.euris.invoiceapp.output;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import junit.framework.TestCase;

public class CSVOutputInvoiceMarshallerTest extends TestCase {

	public void testMarshall() throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

		OutputInvoice invoice1 = new OutputInvoice(
				BigInteger.valueOf(1L), 
				df.parse("23/03/2013"), 
				df.parse("31/03/2013"));
		
		assertEquals(new CSVOutputInvoiceMarshaller().marshall(invoice1), 
				"1;23/03/2013;31/03/2013");
	}

}
