/**
 * 
 */
package databaseprocess;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import utils.FileUtils;
import utils.Logger;
import config.Config;

/**
 * @author anhnt
 *
 */
public class TableFileReader {
	static int lineCount = 0; 
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TableFileReader cltReader = new TableFileReader();
		TableFileReader docsReader = new TableFileReader();

		
		try {
			GlobalInfo globalInfo = new GlobalInfo();
			
			ArrayList<int[]> allLines = cltReader.readAllLinesIdx(Config.mappingFilePath, globalInfo);
			globalInfo.buildIdxTermDict();
			
			ArrayList<String[]> allDocsLines = docsReader.readAllLinesStr(Config.docsFilePath, globalInfo);
			for(String[] aDocLine:allDocsLines){
				String pqn = aDocLine[0];
				String name = aDocLine[1];
				globalInfo.nameSet.add(name);
				if (globalInfo.pqnNameMap.containsKey(pqn)){
					globalInfo.pqnNameMap.get(pqn).add(name);
				}
				else {
					HashSet<String> names =new HashSet<String>();
					names.add(name);
					globalInfo.pqnNameMap.put(pqn, names);
				}
			}
			
			FileUtils.writeObjectFile(allLines, Config.mappingLineFilePath);
			FileUtils.writeObjectFile(globalInfo, Config.globalInfoFilePath);

			Logger.log(System.lineSeparator() + "lineCount: " + lineCount);
			Logger.log( "dict size: " + globalInfo.termIdxDict.size());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public ArrayList<String[]> readAllLinesStr(String filePath, GlobalInfo globalInfo) throws IOException {
		ArrayList<String[]> allLines = new ArrayList<String[]>();
		InputStreamReader isr = new InputStreamReader(new FileInputStream(filePath));
		BufferedReader br = new BufferedReader(isr);
		
		String curLine;
		while((curLine=br.readLine()) != null){
			String [] split = curLine.split("&");
			allLines.add(split);
		}
		br.close();
		return allLines;
		
	}
	
	public ArrayList<int[]> readAllLinesIdx(String filePath, GlobalInfo globalInfo) throws IOException{
		ArrayList<int[]> allLines = new ArrayList<int[]>(121283484);
		InputStreamReader isr = new InputStreamReader(new FileInputStream(filePath));
		BufferedReader br = new BufferedReader(isr);
		
		String curLine;
	
		while((curLine=br.readLine()) != null){
			lineCount++;
			if(lineCount%1000==0){
				System.out.print(lineCount + "  ");
				if(lineCount%100000==0){
					System.out.println();
				}
				
			}
			String [] split = curLine.split("&");
			int[] intSplit = getIntSplit(split, globalInfo.termIdxDict);
			allLines.add(intSplit);
		}
		br.close();
		return allLines;
	}
	
	public int[] getIntSplit(String[] split, HashMap<String, Integer>  termIdxDict){
		int[] intSplit = new int[split.length];
		for (int i=0; i<split.length; i++){
			String tmp = split[i];
			int idx = termIdxDict.size();
			if(termIdxDict.containsKey(tmp)){
				idx = termIdxDict.get(tmp);
			}
			else {
				termIdxDict.put(tmp, idx);
			}
			intSplit[i] = idx;
		}
		
		return intSplit;
	}
}