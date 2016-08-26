package es.interviews.euris.invoiceapp.input;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class InvoiceInputFileChannel implements InvoiceInputChannel {

	private static final String CHARSET = "UTF-8";
	private BufferedReader reader;
	private InputInvoiceUnmarshaller lineUnmarshaller;

	public InvoiceInputFileChannel(File inputFile, InputInvoiceUnmarshaller lineParser) throws FileNotFoundException, UnsupportedEncodingException {
		this.lineUnmarshaller = lineParser;
		reader = new BufferedReader(new InputStreamReader(
				new FileInputStream(inputFile), CHARSET));
	}

	public InputInvoice readNext() throws IOException, UnmarshallingException {
		String line = reader.readLine().trim();
		if(line == null || "".equals(line)){
			return null;
		}
		return lineUnmarshaller.unmarshall(line);
	}

	public void close() throws IOException {
		reader.close();
	}

}
