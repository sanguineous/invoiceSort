package es.interviews.euris.invoiceapp.output;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.Collection;

public class InvoiceSetOutputFileChannel implements InvoiceSetOutputChannel {

	private static final String CHARSET = "UTF-8";
	private BufferedWriter writer;
	private OutputInvoiceMarshaller marshaller;
	
	public InvoiceSetOutputFileChannel(File outputFile, OutputInvoiceMarshaller marshaller) throws FileNotFoundException, UnsupportedEncodingException {
		this.marshaller = marshaller;
		writer = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(outputFile), CHARSET));
	}

	public void write(Collection<OutputInvoice> invoices) throws IOException {
		for(OutputInvoice invoice : invoices){
			writer.write(marshaller.marshall(invoice) + "\n");
		}
	}

	public void close() throws IOException {
		writer.flush();
		writer.close();		
	}

}
