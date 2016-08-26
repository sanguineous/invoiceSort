package es.interviews.euris.invoiceapp.job;

import java.io.IOException;
import java.util.Date;
import java.util.TreeMap;

import es.interviews.euris.invoiceapp.domain.Invoice;
import es.interviews.euris.invoiceapp.domain.PaymentMode;
import es.interviews.euris.invoiceapp.input.InputInvoice;
import es.interviews.euris.invoiceapp.input.InvoiceInputChannel;
import es.interviews.euris.invoiceapp.input.UnmarshallingException;
import es.interviews.euris.invoiceapp.output.InvoiceSetOutputChannel;
import es.interviews.euris.invoiceapp.output.OutputInvoice;

public class InvoiceSortingJob {

	private InvoiceInputChannel invoiceInputChannel;
	private InvoiceSetOutputChannel invoiceSetOutputChannel;

	public InvoiceSortingJob(InvoiceInputChannel invoiceInputChannel, InvoiceSetOutputChannel invoiceSetOutputChannel) {
		this.invoiceInputChannel = invoiceInputChannel;
		this.invoiceSetOutputChannel = invoiceSetOutputChannel;
	}

	public void run() throws IOException, UnmarshallingException {
		
		TreeMap<Date, OutputInvoice> sortedInvoices = new TreeMap<Date, OutputInvoice>();
		
		InputInvoice inputInvoice = null;
		while((inputInvoice = invoiceInputChannel.readNext()) != null) {
			
			Invoice invoice = new Invoice(
					inputInvoice.getNumber(),
					inputInvoice.getDate(),
					PaymentMode.valueOf(
							inputInvoice.getPaymentMode().toString()));
			
			sortedInvoices.put(invoice.paymentDueDate(),
					new OutputInvoice(
							invoice.getNumber(), 
							invoice.getDate(), 
							invoice.paymentDueDate()));
		}
		
		invoiceSetOutputChannel.write(sortedInvoices.values());
	}

}
