package it.acme.taxsales;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Counter {

	public Map  occurrencebyKey;
	public List<Map> allKeyes;
	
	public Counter() {
		allKeyes = new ArrayList();
		occurrencebyKey = new HashMap<Map, Integer>();
		
	}

	public void accumulate(Map key) {
		allKeyes.add(key);
	}
	
	public void countByKeys(){
		Integer times;
		for (Map key : allKeyes) {
			times = (Integer)occurrencebyKey.get(key);
			if (times == null)
				times = 0;
			occurrencebyKey.put(key, times + 1);
			
		}
	}
	
	public int times(Map key){
		return (Integer)occurrencebyKey.get(key);
	}
			
	}

