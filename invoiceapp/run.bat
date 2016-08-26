call mvn package
call mvn exec:java -e -Dexec.classpathScope=runtime -Dexec.mainClass="es.interviews.euris.invoiceapp.main.InvoiceSortMain" -Dexec.args="%1 %2"