package es.interviews.euris.invoiceapp.domain;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public enum PaymentMode {
	DF, DFFM, DF60;

	public Date dueDateFor(Date date) {
		switch (this) {
		case DF:
			return date;
		case DFFM:
			GregorianCalendar dueDateDFFM = new GregorianCalendar();
			dueDateDFFM.setTime(date);
			
			// add one day till we get the first day of the next month
			do { dueDateDFFM.add(Calendar.DAY_OF_MONTH, 1); } 
				while(dueDateDFFM.get(Calendar.DAY_OF_MONTH) != 1);
			
			// then return the day before
			dueDateDFFM.add(Calendar.DAY_OF_MONTH, -1);
			return dueDateDFFM.getTime();
		case DF60:
			GregorianCalendar dueDateDF60 = new GregorianCalendar();
			dueDateDF60.setTime(date);
			
			// add 2 solar months
			dueDateDF60.add(Calendar.MONTH, 2);
			
			return dueDateDF60.getTime();
		default: throw new IllegalStateException(); 		
		}		
	}
}
