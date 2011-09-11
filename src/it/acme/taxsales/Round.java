package it.acme.taxsales;

import java.math.BigDecimal;

public class Round {

	public BigDecimal doIt(BigDecimal bigDecimal) {
		return bigDecimal.setScale(2, bigDecimal.ROUND_HALF_DOWN);  
	}
}
