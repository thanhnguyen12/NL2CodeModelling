package config;

public class Config {

	public static final String mainPath =  "/remote/rs/tien/anhnt/TextToCode/";
	public static final String mainDataPath = mainPath+ "data/";
	public static final String threadInfoPath = mainDataPath + "ThreadInfo.dat";

	public static final String mainDatabasePath = mainPath + "database/";
	public static final String mappingFilePath = mainDatabasePath + "clt.table";
	public static final String docsFilePath = mainDatabasePath + "docs.table";
	public static final String modMappingFilePath = mainDatabasePath + "clt_mod.table";
	public static final String mappingLineFilePath = Config.mainDatabasePath + "MappingLines.dat";
	public static final String globalInfoFilePath = Config.mainDatabasePath + "GlobalInfo.dat";
	

	public static final int topDiscardCombinedAPI = 1000;
	public static final int topCombinedAPI = 1000;
	

	public static String alignDirPath = "/remote/rs/tien/anhnt/TextToCode/data/berkeleyaligner/";
	public static String alignTrainDirPath = alignDirPath + "train/";
	public static String alignTestDirPath = alignDirPath + "test/";
	public static String alignOutputDirPath = alignDirPath + "output/";
	public static String myConfFilePath = alignDirPath + "align.conf";
	
	public static String nlpPostfix = "l";
	public static String javaPostfix = "j";
}