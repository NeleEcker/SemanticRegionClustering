package src.com.web;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.UUID;

import src.com.core.Cluster;
import src.com.core.Instance;

public class GeoJsonFormatter {

	public static File format(ArrayList<Instance> instances, ArrayList<Cluster> clusters) {
		File tmpFile = null;
		System.out.println("-> Preparing results...");
		try {
			tmpFile = new File(""+UUID.randomUUID()+".json");
			tmpFile.createNewFile();
			PrintWriter pw = new PrintWriter(tmpFile);
			pw.print("{\"type\": \"FeatureCollection\",\n" + 
					"		  \"features\": [");
			boolean needComma = false;
			for (Instance instance : instances) {
				if (instance.getCluster()==null)
					continue;
				if (needComma) {
					needComma = false;
					pw.println(",");
				}
				pw.print("{\n" + 
						"    \"type\": \"Feature\",\n" + 
						"    \"geometry\": {\n" + 
						"      \"type\": \"Point\",\n" + 
						"      \"coordinates\": [\n" + 
						"        "+instance.getLatitude()+",\n" + 
						"        "+instance.getLongitude()+"\n" + 
						"      ]\n" + 
						"    },\n" + 
						"    \"properties\": {\n" + 
						"      \"name\": \""+instance.getName()+"\",\n" + 
						"      \"color\": \""+instance.getCluster().getHexColor()+"\"\n" + 
						"    }\n" + 
						"  }");
				needComma = true;
			}
			for (Cluster cluster : clusters) {
				if (cluster.getEdges().size() > 0) {
					if (needComma) {
						needComma = false;
						pw.println(",");
					}
					cluster.orderEdges();
					pw.print("{\n" + 
							"		  \"type\": \"Feature\",\n" + 
							"		  \"properties\": {\n" + 
							"		    \"name\": \""+cluster.getName()+"\"\n" + 
							"		  },\n" + 
							"		  \"geometry\": {\n" + 
							"		    \"type\": \"Polygon\",\n" + 
							"		    \"coordinates\": [\n" + 
							"		      [\n" + 
							"		        ["+cluster.getEdges().get(0).latitude+", "+cluster.getEdges().get(0).longitude+"],\n" + 
							"		        ["+cluster.getEdges().get(1).latitude+", "+cluster.getEdges().get(1).longitude+"],\n" + 
							"		        ["+cluster.getEdges().get(2).latitude+", "+cluster.getEdges().get(2).longitude+"],\n" + 
							"		        ["+cluster.getEdges().get(3).latitude+", "+cluster.getEdges().get(3).longitude+"],\n" + 
							"		        ["+cluster.getEdges().get(0).latitude+", "+cluster.getEdges().get(0).longitude+"]\n" + 
							"		      ]\n" + 
							"		    ]\n" + 
							"		  }\n" + 
							"		}");
					needComma = true;
				}
			}
			
			
			
			pw.println("]\n" + 
					"		}");
			pw.flush();
			pw.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tmpFile;
	}
}
