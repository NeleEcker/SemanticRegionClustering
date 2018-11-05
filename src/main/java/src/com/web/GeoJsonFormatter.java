package src.com.web;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.UUID;

import src.com.core.Instance;

public class GeoJsonFormatter {

	public static File format(ArrayList<Instance> instances) {
		File tmpFile = null;
		System.out.println("-> Preparing results...");
		try {
			tmpFile = new File(""+UUID.randomUUID()+".json");
			tmpFile.createNewFile();
			PrintWriter pw = new PrintWriter(tmpFile);
			for (Instance instance : instances) {
				pw.print(instance.getId() + " (" + instance.getCluster().getId() + ") \n");
			}
			pw.flush();
			pw.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tmpFile;
	}
	
}
