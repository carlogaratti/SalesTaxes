package it.acme.taxsales;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;



public class BasicIncrement {

	private List taxableItem;
	private Round round;
	private BigDecimal rate; 
	
	public BasicIncrement(Round round){
		taxableItem = new ArrayList<String>();
		taxableItem.add("CD");
		taxableItem.add("bottlePerfume");
		this.round = round;
		rate = new BigDecimal(0.10);
		
	}
	public BigDecimal calculate(String item, BigDecimal price) {
		if (taxableItem.contains(item)){
			return round.doIt(price.multiply(rate));
		}
		else	
			return new BigDecimal(0.00);
	}

}
