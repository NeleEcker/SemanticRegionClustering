package src.com.core;

import java.util.ArrayList;

public class SimpleSquareClusteringAlgorithm implements IClusteringAlgorithm {

	private double clustersize = 0.0005d;
	
	public ArrayList<Instance> buildCluster(ArrayList<Instance> instances) {
		
		Instance referencePoint = instances.get(0);
		
		ArrayList<Cluster> clusters = new ArrayList<Cluster>();
		Cluster c = new Cluster();
		c.setEdge1(new double[] {referencePoint.getLatitude() - (clustersize/2.0d), referencePoint.getLongitude() - (clustersize/2.0d)});
		c.setEdge2(new double[] {referencePoint.getLatitude() - (clustersize/2.0d), referencePoint.getLongitude() + (clustersize/2.0d)});
		c.setEdge3(new double[] {referencePoint.getLatitude() + (clustersize/2.0d), referencePoint.getLongitude() - (clustersize/2.0d)});
		c.setEdge4(new double[] {referencePoint.getLatitude() + (clustersize/2.0d), referencePoint.getLongitude() + (clustersize/2.0d)});
		clusters.add(c);
		
		for (Instance instance : instances) {
			double latDist = instance.getLatitude() - referencePoint.getLatitude();
			double longDist = instance.getLongitude() - referencePoint.getLongitude();
			
			double tmp = latDist - (clusters.get(0).getEdge1()[0] - referencePoint.getLatitude()) ;
			//round to smaller number
			double lowerLat = ((int) (tmp / clustersize)) * clustersize + 0.0d + clusters.get(0).getEdge1()[0];
			tmp = longDist - (clusters.get(0).getEdge1()[1] - referencePoint.getLongitude()) ;
			//round to smaller number
			double lowerLong = (int) (tmp / clustersize) * clustersize + 0.0d + clusters.get(0).getEdge1()[1];;
			
			c = new Cluster();
			c.setEdge1(new double[] {lowerLat, lowerLong});
			c.setEdge2(new double[] {lowerLat, lowerLong + clustersize});
			c.setEdge3(new double[] {lowerLat + clustersize, lowerLong});
			c.setEdge4(new double[] {lowerLat + clustersize, lowerLong + clustersize});
			boolean found = false;
			for (Cluster cluster : clusters) {
				if (cluster.equals(c)) {
					found = true;
				}
			}
			if (!found)
				clusters.add(c);
			
			instance.setCluster(c);
			
		}
		
		return instances;
	}

	public double getClustersize() {
		return clustersize;
	}

	public void setClustersize(double clustersize) {
		this.clustersize = clustersize;
	}

}
