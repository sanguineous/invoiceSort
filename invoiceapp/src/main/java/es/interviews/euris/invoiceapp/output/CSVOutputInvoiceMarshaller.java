package es.interviews.euris.invoiceapp.output;

import java.text.SimpleDateFormat;

public class CSVOutputInvoiceMarshaller implements OutputInvoiceMarshaller {

	private static final String CSV_SEPARATOR = ";";
	private SimpleDateFormat invoiceDateFormat = new SimpleDateFormat("dd/MM/yyyy");
	
	public String marshall(OutputInvoice invoice) {
		
		return invoice.getNumber() + CSV_SEPARATOR
			 + invoiceDateFormat.format(invoice.getDate()) + CSV_SEPARATOR
			 + invoiceDateFormat.format(invoice.getPaymentDueDate());
	}

}
