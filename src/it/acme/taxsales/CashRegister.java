package it.acme.taxsales;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class CashRegister {
	
	private Map map;
	private Map key;
	private BigDecimal total;
	private BigDecimal salesTaxes;
	private BigDecimal actualPrice;
	private BasicIncrement tax;
	private ImportIncrement taxImport;
	private Ticket ticket;
	private Round round;
	private Counter counter;
	

	public CashRegister(BasicIncrement tax, ImportIncrement taxImport, Ticket ticket, Round round, Counter counter) {
		map = new HashMap < HashMap<String, Boolean>, BigDecimal >();
		this.total = new BigDecimal(0.00);
		this.salesTaxes = new BigDecimal(0.00);
		this.actualPrice = new BigDecimal(0.00);
		this.tax = tax;
		this.taxImport = taxImport;
		this.ticket = ticket;
		this.round = round;
		this.counter = counter;
	}

	public void accumulate(String item, BigDecimal bigDecimal, boolean imported) {
		count(item, imported);
		incresePrice(item, bigDecimal, imported);
		increseSalesTaxes(item, bigDecimal, imported);
		increaseTotal();
	}
	
	public Ticket enter() {
		counter.countByKeys();
		return ticket.write(map, counter,  total, salesTaxes);
	}
	
	private void count(String item, boolean imported) {
		initKey(item, imported);
		counter.accumulate(key);
	}

	private void incresePrice(String item, BigDecimal price, boolean imported) {
		actualPrice = price.add(tax.calculate(item, price));
		actualPrice = actualPrice.add(taxImport.calculate(imported, price));
		initKey(item, imported);
		map.put(key, actualPrice);
	}

	private void increseSalesTaxes(String item, BigDecimal price, boolean imported) {
		salesTaxes = salesTaxes.add(tax.calculate(item, price));
		salesTaxes = salesTaxes.add(taxImport.calculate(imported, price));
		salesTaxes = round.doIt(salesTaxes);
		
	}
	
	private void increaseTotal() {
		total = total.add(actualPrice);
		total = round.doIt(total);
		
	}
	
	private void initKey(String item, boolean imported) {
		key = new HashMap<String, Boolean>();
		key.put(item, imported);
	}

}
