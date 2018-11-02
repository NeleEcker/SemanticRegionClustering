package src.com.core;

import java.util.ArrayList;

public class Processor {

	public Processor() {
		// TODO Auto-generated constructor stub
	}
	
	public void execute() {
		
		ArrayList<Instance> instances = createDummyInstances();
		
		SimpleSquareClusteringAlgorithm clusterAlg = new SimpleSquareClusteringAlgorithm();
		clusterAlg.setClustersize(0.1);
		instances = clusterAlg.buildCluster(instances);
		
		System.out.println("name (location instance); (location cluster center)");
		for (Instance instance : instances) {
			System.out.println(instance.toString());
		}
		
	}
	
	private ArrayList<Instance> createDummyInstances() {
		ArrayList<Instance> instances = new ArrayList<Instance>(); 

		Instance i = new Instance();
		i.setInstitutionClasses(new String[]{"Thing","bar"});
		i.setName("a");
		i.setLatitude(50);
		i.setLongitude(50);
		instances.add(i);
		
		i = new Instance();
		i.setInstitutionClasses(new String[]{"Thing","bar"});
		i.setName("b");
		i.setLatitude(50.001);
		i.setLongitude(50.001);
		instances.add(i);
		
		i = new Instance();
		i.setInstitutionClasses(new String[]{"Thing","museum"});
		i.setName("c");
		i.setLatitude(50.002);
		i.setLongitude(50.002);
		instances.add(i); 
		
		return instances;
	}
	
	public static void main(String[] args) {
		new Processor().execute();
	}
	
	
}
