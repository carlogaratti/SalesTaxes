package it.acme.taxsales;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class TaxSales {
	
	private CashRegister cashRegister;
	private BasicIncrement tax;
	private ImportIncrement taxImport;
	private Ticket ticket;
	private Round round;
	private Counter counter;
	
	@Before
	public void init(){
		round = new Round();
		tax = new BasicIncrement(round);
		taxImport = new ImportIncrement(round);
		ticket = new Ticket();
		counter = new Counter();
		cashRegister = new CashRegister(tax, taxImport, ticket, round, counter);
	}
		
	@Test
	public void oneBook(){
		cashRegister.accumulate("book", new BigDecimal(12.49), false);
		ticket = cashRegister.enter();
		assertThat(ticket.out, is("1 book price : 12.49 salesTaxes 0.00 total 12.49"));
	}
	
	@Test
	public void twoBooks(){
		cashRegister.accumulate("book", new BigDecimal(12.49), false);
		cashRegister.accumulate("book", new BigDecimal(12.49), false);
		ticket = cashRegister.enter();
		assertThat(ticket.out, is("2 book price : 12.49 salesTaxes 0.00 total 24.98"));
	}
	

	@Test
	public void oneBookoneChocolate(){
		cashRegister.accumulate("book", new BigDecimal(12.49), false);
		cashRegister.accumulate("chocolate", new BigDecimal(0.85), false);
		ticket = cashRegister.enter();
		assertThat(ticket.out, is("1 book price : 12.49 1 chocolate price : 0.85 salesTaxes 0.00 total 13.34"));
	}
	
	@Test
	public void oneBookoneChocolateoneCD(){
		cashRegister.accumulate("book", new BigDecimal(12.49), false);
		cashRegister.accumulate("chocolate", new BigDecimal(0.85), false);
		cashRegister.accumulate("CD", new BigDecimal(14.99), false);
		ticket = cashRegister.enter();
		assertThat(ticket.out,is("1 book price : 12.49 1 CD price : 16.49 1 chocolate price : 0.85 salesTaxes 1.50 total 29.83"));
	}
	
	@Test
	public void oneboxChocolatesImportedOnebottlePerfumeImported(){
		cashRegister.accumulate("boxChocolates", new BigDecimal(10.00), true);
		cashRegister.accumulate("bottlePerfume", new BigDecimal(47.50), true);
		ticket = cashRegister.enter();
		assertThat(ticket.out, is("1 Imported boxChocolates price : 10.50 1 Imported bottlePerfume price : 54.63 salesTaxes 7.63 total 65.13"));
	}
	
	@Test
	public void onebottlePerfumeImportedonebottlePerfumeoneboxChocolatesImportedoneheadachePills(){
		cashRegister.accumulate("bottlePerfume", new BigDecimal(27.99), true);
		cashRegister.accumulate("bottlePerfume", new BigDecimal(18.99), false);
		cashRegister.accumulate("boxChocolates", new BigDecimal(11.25), true);
		cashRegister.accumulate("headachePills", new BigDecimal(9.75), false);
		ticket = cashRegister.enter();
		assertThat(ticket.out, is("1 bottlePerfume price : 20.89 1 Imported boxChocolates price : 11.81 1 headachePills price : 9.75 1 Imported bottlePerfume price : 32.19 salesTaxes 6.66 total 74.64"));
	}
}
