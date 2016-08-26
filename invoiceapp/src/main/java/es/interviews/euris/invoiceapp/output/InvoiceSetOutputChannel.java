package es.interviews.euris.invoiceapp.output;

import java.io.Closeable;
import java.io.IOException;
import java.util.Collection;

public interface InvoiceSetOutputChannel extends Closeable {

	void write(Collection<OutputInvoice> values) throws IOException;
}