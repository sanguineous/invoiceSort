package es.interviews.euris.invoiceapp.input;

public interface InputInvoiceUnmarshaller {

	InputInvoice unmarshall(String line) throws UnmarshallingException;

}
