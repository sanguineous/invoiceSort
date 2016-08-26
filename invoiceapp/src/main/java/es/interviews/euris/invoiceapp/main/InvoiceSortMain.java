package es.interviews.euris.invoiceapp.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import es.interviews.euris.invoiceapp.input.CSVInputInvoiceUnmarshaller;
import es.interviews.euris.invoiceapp.input.InvoiceInputChannel;
import es.interviews.euris.invoiceapp.input.InvoiceInputFileChannel;
import es.interviews.euris.invoiceapp.input.UnmarshallingException;
import es.interviews.euris.invoiceapp.job.InvoiceSortingJob;
import es.interviews.euris.invoiceapp.output.CSVOutputInvoiceMarshaller;
import es.interviews.euris.invoiceapp.output.InvoiceSetOutputChannel;
import es.interviews.euris.invoiceapp.output.InvoiceSetOutputFileChannel;

/**
 * Hello world!
 *
 */
public class InvoiceSortMain 
{
    private static final int EC_INVALID_PARAMS = 1;
	private static final int EC_IO_ERROR = 2;
	private static final String USAGE_STRING = "Usage:\ninvoicesort <source file> <dest file>\n";

	public static void main( String[] args )
    {
		// 1. Check arguments		
		if(args.length != 2){
			System.out.println(USAGE_STRING);
    		System.exit(EC_INVALID_PARAMS);
		}
		
		InvoiceInputChannel invoiceInputChannel = null;
		InvoiceSetOutputChannel invoiceSetOutputChannel = null;
		
		try{
			// 2. Create the CSV file input channel
	    	try {
				invoiceInputChannel = new InvoiceInputFileChannel(
												new File(args[0]),
												new CSVInputInvoiceUnmarshaller());
			} catch (FileNotFoundException e) {
				System.out.println("Cannot find input file: " + args[0]);
	    		System.exit(EC_IO_ERROR);
			} catch (UnsupportedEncodingException e) {
				System.out.println("Internal error:");
				e.printStackTrace();
	    		System.exit(EC_IO_ERROR);
			}
	    	
	    	// 3. Create the CSV file output channel
	    	try {
				invoiceSetOutputChannel =
						new InvoiceSetOutputFileChannel(new File(args[1]),
														new CSVOutputInvoiceMarshaller());
			} catch (FileNotFoundException e1) {
				System.out.println("Cannot find output file: " + args[1]);
	    		System.exit(EC_IO_ERROR);
			} catch (UnsupportedEncodingException e1) {
				System.out.println("Internal error:");
				e1.printStackTrace();
	    		System.exit(EC_IO_ERROR);
			} 	
	    	
	    	// 4. Execute the invoice sorting task
	    	try {
	    		new InvoiceSortingJob(invoiceInputChannel, invoiceSetOutputChannel)
	    			.run();
			} catch (IOException e) {
				System.out.println("I/O error while reading or writing to files: " + e.getMessage());
	    		e.printStackTrace();
				System.exit(EC_IO_ERROR);
			} catch (UnmarshallingException e) {
				System.out.println("Wrong input file format: " + e.getLocalizedMessage());
	    		System.exit(EC_IO_ERROR);
			}
		} finally {
			try { invoiceInputChannel.close(); } catch (Exception e) { }
			try { invoiceSetOutputChannel.close(); } catch (Exception e) { }
		}
    	
    	// 5. exit as successful execution
		System.out.println();
		System.out.println("-----------------------------------");
		System.out.println("Invoices successful sorted to file:\n" + args[1]);
		System.out.println("-----------------------------------");
		System.exit(0);

    }
}
