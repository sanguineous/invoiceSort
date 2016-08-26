package es.interviews.euris.invoiceapp.job;

import java.io.IOException;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

import es.interviews.euris.invoiceapp.input.InputInvoice;
import es.interviews.euris.invoiceapp.input.InvoiceInputChannel;
import es.interviews.euris.invoiceapp.input.UnmarshallingException;
import es.interviews.euris.invoiceapp.output.InvoiceSetOutputChannel;
import es.interviews.euris.invoiceapp.output.OutputInvoice;
import junit.framework.TestCase;

public class InvoiceSortingJobTest extends TestCase {

	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	
	public void testRun() throws IOException, UnmarshallingException, ParseException {
		
		TestInputChannel testInputChannel = new TestInputChannel();
		TestOutputChannel testOutputChannel = new TestOutputChannel();
		new InvoiceSortingJob(testInputChannel, testOutputChannel).run();
		testOutputChannel.close();
		testInputChannel.close();
	}

	private class TestOutputChannel implements InvoiceSetOutputChannel {

		private boolean writeCalled = false;
		public void close() throws IOException {
			assertTrue(writeCalled);
		}

		public void write(Collection<OutputInvoice> values) throws IOException {
			assertFalse(writeCalled);
			writeCalled = true;
			Iterator<OutputInvoice> it = values.iterator();
			try {
				OutputInvoice invoice1 = it.next();
				assertEquals(invoice1.getNumber(), BigInteger.valueOf(2L));
				assertEquals(invoice1.getDate().getTime(), dateFormat.parse("23/02/2013").getTime() );
				assertEquals(invoice1.getPaymentDueDate().getTime(), dateFormat.parse("28/02/2013").getTime() );

				OutputInvoice invoice2 = it.next();
				assertEquals(invoice2.getNumber(), BigInteger.valueOf(1L));
				assertEquals(invoice2.getDate().getTime(), dateFormat.parse("23/03/2013").getTime() );
				assertEquals(invoice2.getPaymentDueDate().getTime(), dateFormat.parse("23/03/2013").getTime() );

				OutputInvoice invoice3 = it.next();
				assertEquals(invoice3.getNumber(), BigInteger.valueOf(3L));
				assertEquals(invoice3.getDate().getTime(), dateFormat.parse("24/01/2013").getTime() );
				assertEquals(invoice3.getPaymentDueDate().getTime(), dateFormat.parse("24/03/2013").getTime() );

			} catch (ParseException e) {
				e.printStackTrace();
			}

		}

	}
	
	private class TestInputChannel implements InvoiceInputChannel {

		Iterator<InputInvoice> it;
		private int readCallTimes = 0;
		public TestInputChannel() throws ParseException{
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			ArrayList<InputInvoice> inputList = new ArrayList<InputInvoice>();
			inputList.add(
					new InputInvoice(BigInteger.valueOf(1L), 
							dateFormat.parse("23/03/2013"), 
							InputInvoice.PaymentMode.DF));  // 23/03
			inputList.add(
					new InputInvoice(BigInteger.valueOf(2L), 
							dateFormat.parse("23/02/2013"), 
							InputInvoice.PaymentMode.DFFM)); // 28/02
			inputList.add(
					new InputInvoice(BigInteger.valueOf(3L), 
							dateFormat.parse("24/01/2013"), 
							InputInvoice.PaymentMode.DF60)); // 24/03
			
			it = inputList.iterator();
		}
		
		public void close() throws IOException {
			assertEquals(readCallTimes , 4);
		}

		public InputInvoice readNext() throws IOException, UnmarshallingException {
			readCallTimes++;
			try {
				return it.next();
			} catch (NoSuchElementException e){
				return null;
			}

		}

	}
}
