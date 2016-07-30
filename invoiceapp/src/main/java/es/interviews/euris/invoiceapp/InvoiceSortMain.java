package es.interviews.euris.invoiceapp;

/**
 * Hello world!
 *
 */
public class InvoiceSortMain 
{
    private static final int EC_INVALID_PARAMS = 1;
	private static final String USAGE_STRING = "";

	public static void main( String[] args )
    {
    	
		try {
    		Parameters params = new ParametersBuilder().buildFromArgs(args);
        	
        	InvoideDataReader invoiceDataReader =
        			new InvoiceDataFileReader(params.getInputFile());
        	InvoiceSetRepository repository =
        			new InvoiceSetFileRepository(params.getOutputFile());     	
        	InvoiceReorderingJob job =
        			new InvoiceReorderingJob(invoiceDataReader, repository);
        	
        	job.run();       	
        	
        	System.exit(0);
        	
    	} catch (InvalidParameterException e){
    		System.out.println(USAGE_STRING);
    		System.exit(EC_INVALID_PARAMS);
    	}

    }
}
