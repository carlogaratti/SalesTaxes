package it.acme.taxsales;

import java.math.BigDecimal;

public class ImportIncrement {

	private Round round;
	private BigDecimal rate;
	
	public ImportIncrement(Round round){
		this.round = round;
		rate = new BigDecimal(0.05);
	}

	public BigDecimal calculate(boolean imported, BigDecimal price) {
		if (imported){
			return round.doIt(price.multiply(rate));
		}
		else 
			return new BigDecimal(0.00);
	}

}
