package src.com.core;

import java.util.HashMap;

public class Instance {
	
	
	private double latitude;
	private double longitude;
	private String[] institutionClasses;
	private Cluster cluster;
	private String name;
	
	public Instance() {
		// TODO Auto-generated constructor stub
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String[] getInstitutionClasses() {
		return institutionClasses;
	}

	public void setInstitutionClasses(String[] institutionClasses) {
		this.institutionClasses = institutionClasses;
	}

	public Cluster getCluster() {
		return cluster;
	}

	public void setCluster(Cluster cluster) {
		this.cluster = cluster;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public double[] oneHotEncode(HashMap<String, Integer> allInstitutionClasses) {
		double[] onehots = new double[allInstitutionClasses.size()];
		for (int i = 0; i < institutionClasses.length; i++) {
			onehots[allInstitutionClasses.get(institutionClasses[i])] = 1.0d;
		}
		return onehots;
	}
	
	@Override
	public String toString() {
		return "" + getName() + " (" + getLatitude() + ", " + getLongitude() + "); " + getCluster().toString() + ";";
	}
	
}
