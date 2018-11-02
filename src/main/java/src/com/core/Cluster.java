package src.com.core;

public class Cluster {

	private double[] edge1 = {0,0};
	private double[] edge2 = {0,0};
	private double[] edge3 = {0,0};
	private double[] edge4 = {0,0};
	private String name = "";
	
	public Cluster() {
		// TODO Auto-generated constructor stub
	}

	public double[] getEdge1() {
		return edge1;
	}

	public void setEdge1(double[] edge1) {
		this.edge1 = edge1;
	}

	public double[] getEdge2() {
		return edge2;
	}

	public void setEdge2(double[] edge2) {
		this.edge2 = edge2;
	}

	public double[] getEdge3() {
		return edge3;
	}

	public void setEdge3(double[] edge3) {
		this.edge3 = edge3;
	}

	public double[] getEdge4() {
		return edge4;
	}

	public void setEdge4(double[] edge4) {
		this.edge4 = edge4;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		//Get cluster center
		return "(" + getCenterLat() + "-" + getCenterLong() +")";
	}
	
	public double getCenterLat() {
		double halfClusterSize = (getEdge1()[0] - getEdge2()[0]) / 2.0d;
		double centerLat = (getEdge1()[0] + halfClusterSize);
		double centerLong = (getEdge1()[1] + halfClusterSize);
		return centerLat;
	}
	public double getCenterLong() {
		double halfClusterSize = (getEdge1()[0] - getEdge2()[0]) / 2.0d;
		double centerLat = (getEdge1()[0] + halfClusterSize);
		double centerLong = (getEdge1()[1] + halfClusterSize);
		return centerLong;
	}
	
	@Override
	public boolean equals(Object obj) {
		
		
		if (!obj.getClass().toString().equals("class src.com.core.Cluster")) {
			return super.equals(obj);
		}
		
		Cluster cObj = (Cluster) obj; 
		double halfClusterSize = Math.abs((getEdge1()[0] - getEdge4()[0]) / 2.0d);
		double tmp = (getCenterLat() - cObj.getCenterLat()) ;
		if ( (getCenterLat() - cObj.getCenterLat()) < halfClusterSize) {
			if ( (getCenterLong() - cObj.getCenterLong()) < halfClusterSize) {
				return true;
			}
		}
		return false;
	}
	
}
