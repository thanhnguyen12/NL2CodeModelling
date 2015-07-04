/**
 * 
 */
package mapprocess;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Scanner;

import process.TagsProcessing;
import process.TagsProcessingSQL;
import utils.FileUtils;
import utils.Logger;
import config.Config;
import data.ThreadData;
import database.SQLiteInterface;

/**
 * @author anhnt_000
 *
 */
public class MappingProcessorSQL {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//		String key = "d*e3BW8LD1uD7ZxzWeY39g(("; 
//		String site = "stackoverflow";
		
		TagsProcessingSQL tagProc = new TagsProcessingSQL(); 
//		tagProc.init(key, site);
				
		doMain(Config.mappingFilePath, Config.modMappingFilePath, tagProc, Config.threadInfoPath);
		
		HashMap<Long, ThreadData> threadIDDataMap = (HashMap<Long, ThreadData>) FileUtils.readObjectFile(Config.threadInfoPath);
		


	}
	
	public static void doMain(String origMapping, String modMapping, TagsProcessingSQL tagProc, String threadInfoPath){
		ArrayList<String> origLines = readOrigLines(origMapping);
		Logger.log(origLines.size());
		modLines(origLines, modMapping);
		
		SQLiteInterface sqLiteInterface = new SQLiteInterface(Config.pathToDBLibs, Config.pathToDatabase);
		HashMap<Long, ThreadData> threadIDDataMap = new HashMap<Long, ThreadData>();
		processLines(origLines, threadIDDataMap, tagProc, sqLiteInterface);
		
		Logger.log("Writing data:");
		FileUtils.writeObjectFile(threadIDDataMap, threadInfoPath);
		Logger.log("Finish writing");
		sqLiteInterface.dispose();

	}
	
	
	public static void processLines(ArrayList<String> origLines, HashMap<Long, ThreadData> threadIDDataMap, TagsProcessingSQL tagProc,
			SQLiteInterface sqLiteInterface ){
		LinkedHashSet<Long> allIDs = new LinkedHashSet<Long>();  
		for (int i=1; i<origLines.size();i++){
			String line = origLines.get(i);
			String[] split = line.split(",");
			String tmp = split[0].trim();
			Long id = Long.parseLong(tmp);
			allIDs.add(id);
		}
		
		for (Long id:allIDs){
			if (id == 10370731l) 
			{
				ThreadData thread = tagProc.processThread(id, sqLiteInterface);
				threadIDDataMap.put(id, thread);
			}
		}
	}
	
	
	public static void modLines(ArrayList<String> origLines, String modMapping){
		try{
			FileWriter fw = new FileWriter(modMapping);
			
			String firstLine = origLines.get(0);	
			fw.append(firstLine + ",thread link" + System.lineSeparator());
			
			for (int i=1; i<origLines.size();i++){
				String line = origLines.get(i);
				String[] split = line.split(",");
				String tmp = split[0].trim();
				String link = "http://stackoverflow.com/questions/"+tmp;
				fw.append(line + "," + link + System.lineSeparator());
			}
			
			fw.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	
	public static ArrayList<String> readOrigLines(String origMapping){
		ArrayList<String> origLines = new ArrayList<String>();
		try{
			Scanner sc = new Scanner(new File(origMapping) );
			while (sc.hasNextLine()){
				String tmp = sc.nextLine();
				origLines.add(tmp);
			}
			sc.close();
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return origLines;
	}
	
	
	
}
