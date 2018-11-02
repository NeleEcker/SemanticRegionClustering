package src.com.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ClassOverlap {

	public ClassOverlap() {
		// TODO Auto-generated constructor stub
	}

	public void setClusterClasses(ArrayList<Cluster> clusters) {
		for (Cluster cluster : clusters) {

			HashMap<String, Double> allInstitutionClasses = new HashMap<String, Double>();
			for (Instance instance : cluster.getInstances()) {
				for (Category institutionClass : instance.getInstitutionClasses()) {
					if (allInstitutionClasses.containsKey(institutionClass.name)) {
						allInstitutionClasses.put(institutionClass.name, allInstitutionClasses.get(institutionClass.name)+institutionClass.weight);
					} else {
						allInstitutionClasses.put(institutionClass.name, 0.0);
					}
				}
			}
			
			double max = -1.0;
			@SuppressWarnings("unused")
			String maxKey = "";
			Iterator iterator = allInstitutionClasses.entrySet().iterator();
			while (iterator.hasNext()) {
			    Map.Entry entry = (Map.Entry) iterator.next();
			    String key = (String) entry.getKey();
			    double value = (Double) entry.getValue();
			    if (value > max) {
			    	max = value;
			    	maxKey = key;
			    }
			}
			cluster.setName(maxKey);
			
		}
		
		
	}
	
}
