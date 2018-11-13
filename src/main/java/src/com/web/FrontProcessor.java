package src.com.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.UUID;

import src.com.clustering.ClassOverlap;
import src.com.clustering.DBScanClusteringAlgorithm;
import src.com.clustering.SimpleSquareClusteringAlgorithm;
import src.com.core.Category;
import src.com.core.Cluster;
import src.com.core.Instance;
import src.com.data.Dataset;

public class FrontProcessor {

	ArrayList<Instance> instances = null;
	ArrayList<Cluster> clusters = null;
	
	public FrontProcessor() {
		
	}
	
	public void process(String latitude, String longitude, String algorithm) {

		System.out.println("-> Creating dummy instances...");
		ArrayList<Instance> instances = new Dataset("8.476682", "49.483752").getDataset(); //new Dataset(latitude, longitude).getDataset(); //createDummyInstances();
		
		
		SimpleSquareClusteringAlgorithm clusterAlg = new SimpleSquareClusteringAlgorithm();
		clusterAlg.setClustersize(1.0);
/*
		DBScanClusteringAlgorithm clusterAlg = new DBScanClusteringAlgorithm();
		clusterAlg.setEpsilon(0.01);
		clusterAlg.setMinPoints(1);
	*/	
		System.out.println("-> Building clusters...");
		clusterAlg.buildCluster(instances);
		instances = clusterAlg.getInstances();

		System.out.println("-> Computing cluster category...");
		ArrayList<Cluster> cluster = clusterAlg.getClusters();
		ClassOverlap co = new ClassOverlap();
		co.setClusterClasses(cluster);

		this.instances = instances;
		this.clusters = cluster;
		
	}
	
	
	public ArrayList<Instance> getInstances() {
		return instances;
	}


	public ArrayList<Cluster> getClusters() {
		return clusters;
	}

	private ArrayList<Instance> createDummyInstances() {
		ArrayList<Instance> instances = new ArrayList<Instance>(); 

		Instance i = new Instance();
		i.setInstitutionClasses(new ArrayList<Category>(Arrays.asList(new Category[]{new Category("Thing", 1.0), new Category("bar", 2.0)})));
		i.setName("a");
		i.setLatitude(50);
		i.setLongitude(50);
		instances.add(i);
		
		for (int j = 0; j < 10; j++) {
			i = new Instance();
			i.setInstitutionClasses(new ArrayList<Category>(Arrays.asList(new Category[]{new Category("Thing", 1.0), new Category("museum", 2.0)})));
			i.setName("a");
			i.setLatitude(new Random().nextDouble());
			i.setLongitude(new Random().nextDouble());
			instances.add(i);
		}
		
		return instances;
	}
	
	
}
