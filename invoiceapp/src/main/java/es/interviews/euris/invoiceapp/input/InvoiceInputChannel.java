package es.interviews.euris.invoiceapp.input;

import java.io.Closeable;
import java.io.IOException;

public interface InvoiceInputChannel extends Closeable {

	public InputInvoice readNext() throws IOException, UnmarshallingException;

}