package es.interviews.euris.invoiceapp.input;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import junit.framework.TestCase;

public class CSVInputInvoiceUnmarshallerTest extends TestCase {

	public void testUnmarshall() throws UnmarshallingException, ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		InputInvoice invoice1 = new CSVInputInvoiceUnmarshaller().unmarshall("1;23/03/2013;DF");
		assertEquals(invoice1.getNumber(), BigInteger.valueOf(1L));
		assertEquals(invoice1.getDate().getTime(), dateFormat.parse("23/03/2013").getTime() );
		assertEquals(invoice1.getPaymentMode(), InputInvoice.PaymentMode.DF);
	}

}
