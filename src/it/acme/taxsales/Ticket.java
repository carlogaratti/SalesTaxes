package it.acme.taxsales;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Ticket {

	public String out = "";
	DecimalFormat formatter = new DecimalFormat("0.00");
	
	
	public Ticket write(Map map, Counter counter, BigDecimal total, BigDecimal salesTaxes) {
		Set<Map> set = map.keySet();
		 for (Iterator iterator = set.iterator(); iterator.hasNext();) {
			 Map itemsMap = (Map)iterator.next();
			 Set itemSet = itemsMap.keySet();
				 for (Iterator internalIterator = itemSet.iterator(); internalIterator.hasNext();){
					 Object item = (Object) internalIterator.next();
					 String imported = ((Boolean)itemsMap.get(item))?" Imported":""; 
					 out = counter.times(itemsMap) + imported +" "+item.toString()+" price : "+formatter.format(map.get(itemsMap)) +" "+ out;
				 }
			 
		 }
		out = out+"salesTaxes "+salesTaxes+" total "+total;
		return this;
	}



}
