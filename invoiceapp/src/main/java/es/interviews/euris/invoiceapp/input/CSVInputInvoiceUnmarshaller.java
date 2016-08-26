package es.interviews.euris.invoiceapp.input;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CSVInputInvoiceUnmarshaller implements InputInvoiceUnmarshaller {

	private static final String CSV_SEPARATOR = ";";
	private SimpleDateFormat invoiceDateFormat = new SimpleDateFormat("dd/MM/yyyy");

	public InputInvoice unmarshall(String line) throws UnmarshallingException {
		
		String[] values = line.split(CSV_SEPARATOR);
		
		BigInteger invoiceNumber = null;
		Date invoiceDate = null;
		InputInvoice.PaymentMode paymentMode = null;
		
		try {
			try {
				invoiceNumber = BigInteger.valueOf(Long.parseLong(values[0]));
			} catch (NumberFormatException e){
				throw new UnmarshallingException("Unable to unmarshall Invoice number value: " + values[0]);
			}

			try {
				invoiceDate = invoiceDateFormat.parse(values[1]);
			} catch (java.text.ParseException e) {
				throw new UnmarshallingException("Unable to unmarshall Invoice date value: " + values[1]);
			}
			
			try {
				paymentMode = InputInvoice.PaymentMode.valueOf(values[2]);
			} catch (IllegalArgumentException e){
				throw new UnmarshallingException("Unable to unmarshall Invoice payment mode value: " + values[2]);
			}
			
			return new InputInvoice(invoiceNumber, invoiceDate, paymentMode);
		} catch (ArrayIndexOutOfBoundsException e){
			throw new UnmarshallingException("Unable to unmarshall Invoice from line: " + line);
		}

	}

}
