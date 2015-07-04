/**
 * 
 */
package align;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import config.Config;
import utils.FileUtils;
import edu.berkeley.nlp.wordAlignment.Main;

/**
 * @author anhnt
 *
 */
public class MainAligner {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MainAligner aligner = new MainAligner();
		try {
			FileUtils.deleteDirectoryContent(new File(Config.alignOutputDirPath));
			FileUtils.deleteRecursive(new File(Config.alignOutputDirPath));
			Thread.sleep(2000);

		} catch (Exception e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
		}
		aligner.setUp(Config.myConfFilePath);
		aligner.doAlignment(Config.myConfFilePath);
	}
	
	
	public void setUp(String confPath ){
		try {
			FileWriter fw = new FileWriter(confPath);
		
			String tmp = 
					"forwardModels	MODEL1 HMM " + System.lineSeparator() 
					+ "reverseModels	MODEL1 HMM"+ System.lineSeparator() 
					+ "mode	JOINT JOINT"+ System.lineSeparator() 
					+ "iters	2 2"+ System.lineSeparator() 
					+ "execDir	" + Config.alignOutputDirPath + System.lineSeparator() 
					+ "create"+ System.lineSeparator() 
					+ "saveParams	true"+ System.lineSeparator() 
					+ "numThreads	1"+ System.lineSeparator() 
					+ "msPerLine	10000"+ System.lineSeparator() 
					+ "alignTraining"+ System.lineSeparator() 
					+ "foreignSuffix	" + Config.nlpPostfix+ System.lineSeparator() 
					+ "englishSuffix	" + Config.javaPostfix + System.lineSeparator() 
				//	+ "lowercase"+ System.lineSeparator() 
					+ "trainSources	"+ Config.alignTrainDirPath + System.lineSeparator() 
					+ "sentences	MAX"+ System.lineSeparator() 
					+ "testSources "+ Config.alignTestDirPath + System.lineSeparator() 
					+ "maxTestSentences	MAX"+ System.lineSeparator() 
					+ "offsetTestSentences	0"+ System.lineSeparator() 
					+ "competitiveThresholding";
			fw.append(tmp);
			fw.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void doAlignment(String confPath){
		String[] args = new String[]{"++"+confPath};
		Main.main(args);
	}
	
}