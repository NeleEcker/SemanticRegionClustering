package src.com.clustering;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.UUID;

import src.com.core.Helper;
import src.com.core.Instance;
import weka.core.Instances;

public class WekaPreprocessor {

	private File tmpFile;
	public WekaPreprocessor() {
		tmpFile = null;
	}
	
	public void postprocess() {
		this.tmpFile.delete();
	}

	public Instances preprocess(ArrayList<Instance> instances) {
		
		
		Helper.normalizeGeoCoordinates(instances);
		
		File tmp = saveToArff(instances);
		
		
	    Instances data = MyUtilsForWekaInstanceHelper.getInstanceFromFile(tmp.getAbsolutePath());
	    tmpFile = tmp;
	    
		return data;

	    
	    
	}
	

@SuppressWarnings("resource")
private File saveToArff(ArrayList<Instance> instances) {
		String tmpFileName = "src/main/java/tmpFiles/"+UUID.randomUUID()+".arff";
		
		HashMap<String, Integer> allInstitutionClasses = Helper.getAllInstitutionClasses(instances);
		
		String arff = "\n" + 
				"@RELATION iris\n" + 
				"\n" + 
				"@ATTRIBUTE lat	REAL\n" + 
				"@ATTRIBUTE long 	REAL\n";
		for (String institution : allInstitutionClasses.keySet()) {
			arff += "@ATTRIBUTE "+institution+" 	REAL\n";
		}
		
		arff += "\n" + 
				"@DATA\n";

		for (Instance instance : instances) {
			arff += instance.getLatitude() + "," + instance.getLongitude();
			for (double d : instance.oneHotEncodeWeighted(allInstitutionClasses)) {
				arff += "," + d;
			}
			arff += "\n";
		}
		File tmp = null;
		try {
			tmp = new File(tmpFileName);
			tmp.createNewFile();
			
			PrintWriter pw = new PrintWriter(tmp);
			pw.println(arff);
			pw.flush();
			pw.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return tmp;
	}


public static class MyUtilsForWekaInstanceHelper {

public static Instances getInstanceFromFile(String pFileName)
{
    Instances data = null;
    try {
        BufferedReader reader = new BufferedReader(new FileReader(pFileName));
        data = new Instances(reader);
        reader.close();
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    } 
    return data;

}
  }
	
	
}
