package es.interviews.euris.invoiceapp;

public class InvoiceReorderingJob {

	private InvoideDataReader invoiceDataReader;
	private InvoiceSetRepository invoiceSetRepository;

	public InvoiceReorderingJob(InvoideDataReader invoiceDataReader, InvoiceSetRepository repository) {
		this.invoiceDataReader = invoiceDataReader;
		this.invoiceSetRepository = repository;
	}

	public void run() {
		InvoiceDataDTO invoiceDTO = invoiceDataReader.readNext();
		do {
			new Invoice(invoiceDTO.getNumber(), invoiceDTO.getDate(), invoiceDTO.getPaymentRule());
			
			invoiceDTO = invoiceDataReader.readNext();
		} while (invoiceDTO != null);
	}

}
