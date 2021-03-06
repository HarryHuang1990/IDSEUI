package cn.iscas.idse.config;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Platform;

import net.sf.json.JSONObject;
import cn.iscas.idse.index.segmentation.StopWordFilter;
import cn.iscas.idse.index.segmentation.TermLemmatizer;
import cn.iscas.idse.index.segmentation.WordSegmentation;
import cn.iscas.idse.storage.DBManager;
import cn.iscas.idse.storage.entity.Category;
import cn.iscas.idse.storage.entity.FileType;
import cn.iscas.idse.storage.entity.TargetDirectory;
import cn.iscas.idse.storage.entity.accessor.AccessorFactory;
import cn.iscas.idse.storage.entity.accessor.CategoryAccessor;
import cn.iscas.idse.storage.entity.accessor.FileTypeAccessor;
import cn.iscas.idse.storage.entity.accessor.TargetDirectoryAccessor;
import cn.iscas.idse.utilities.Converter;

/**
 * 管理系统的配置信息管理：
 * 解析不同文件格式的插件信息，以<后缀名，完整类名>形式存在
 * @author HarryHuang
 *
 */
public class SystemConfiguration {
	
	/**
	 * 文件格式-插件（处理类）映射表
	 */
	public static Map<String, String> formatPluginMap = new HashMap<String, String>();
	/**
	 * 停用词表-插件（处理类）映射表
	 */
	public static Map<String, String> stopWordPluginMap = new HashMap<String, String>();
	/**
	 * Berkeley DB manager
	 */
	public static DBManager database = null;
	/**
	 * 文件类型索引缓存
	 */
	public static Map<String, FileType> fileTypeBuff = new HashMap<String, FileType>();
	/**
	 * 分隔符列表
	 */
	public static String seperatorRegx = "[-_:]"; 
	
	/**
	 * 允许分析的PDF文本大小上限（K）
	 */
	public static short maxSizeAllowed_PDF = 5000;
	
	/**
	 * 允许分析的text文本大小上限（K）
	 */
	public static short maxSizeAllowed_TXT = 500;
	
	/**
	 * 如果文件夹中的文件数超过150个，则不对这个文件夹下的这些文件进行分析。如果有子文件夹，需要继续判断。
	 */
	public static int maxFileCountPreDirectory = 150;
	
	/**
	 * 有效KL值上限，凡是kl值大于上限的两个document,不建立topic语义关联
	 */
	public static double klUpbound = 0.3;
	
	/**
	 * 文件之间的切换距离上限，凡是切换距离D(a,b)大于上限的两个document，不建立location语义关联
	 */
	public static int dMAX_GAMA = 1;
	
	/**
	 * 各部分得分对总关系得分的贡献比例
	 */
	public static double topicFactor = 0.4;
	public static double taskFactor = 0.4;
	public static double locationFactor = 0.2;
	
	/**
	 * 返回给用户的结果数
	 */
	public static int topN = 20;
	/**
	 * 相关文档的推荐步长
	 */
	public static int step = 2;
	/**
	 * 只对相邻节点数小于该阈值的节点采计算2 step推荐
	 */
	public static int neightborThreshold = 100;
	/**
	 * 推荐的文档数
	 */
	public static int recommendedDocNumber = 5;
	
	/**
	 * 待索引的目标目录列表
	 */
	public static List<String> targetDirectories = new ArrayList<String>();
	public static String targetDirectoryValues = null;
	/**
	 * 应用程序根目录路径。路径由反斜杠/间隔，路径末尾以反斜杠/结束
	 */
	public static String rootPath = "";
	
	/**
	 * 用户行为日志文件
	 */
	public static String userActivityLogFile = "F:/user_activity_log/log.csv";
	/**
	 * LDA目录
	 */
	public static String LDAPath = "";
	/**
	 * 用于LDA建模的docID列表文件 (固定名称)
	 */
	public static String LDAdocIDListFileName = "00000000.map";
	/**
	 * 用于LDA建模的doc数据文件 (固定名称)
	 */
	public static String LDADataFileName = "00000000";
	
	/**
	 * pageRank分批写入数据库，每增加5000个节点的时候，写入数据库
	 */
	public static int pageRankWriteCountThreshold = 5000;
	/**
	 * dictionary分批写入数据库，每增加300000个posting写一个数据库
	 */
	public static int dictionaryWriteCountThreshold = 300000;
	
	public static int LDAIteration = 100;
	
	/**
	 * 过滤时间间隔-用于界定有效打开文件，对于那些打开之后再很短的时间内就关闭的文件，
	 * 我们不认为他们具有很强的目的性。比如说我们在浏览网页的时候，对于我们感兴趣的，
	 * 或者说重要的页面我们会关注很长的时间，但是对于一些无用的页面的俩说我们可能扫
	 * 一眼就关闭了，前后打开的时间可能就几秒种。这个有效的文件打开时间下限用该参数来
	 * 定义（单位为s）
	 */
	public static int validViewPeriod = 10; 
	/**
	 * task时间间隔-用于度量task之间的时间跨度，如果相邻两个文件之间间隔时间超过10分钟，
	 * 则认为启动了一个新的task。
	 */
	public static int intervalTaskPeriod = 600;
	
	/**
	 * 用于task合并的相似度阈值
	 */
	public static double taskSimilarityThreshold = 0.5f;
	
	
	/**
	 * 服务器端口
	 */
	public static int ServerPort = 8081;
	
	/**
	 * 索引是否创建， 0-未创建，1-未创建
	 */
	public static int IndexStatusBuilt=0;
	/**
	 * 索引是否创建成功，0-未成功创建，1-成功创建
	 */
	public static int IndexStatusSuccess=0;
	
	public static TermLemmatizer lemmatizer = null;
	public static StopWordFilter stopWordFilter = null;
	public static WordSegmentation wordSegmentor = null;
	
	public static void init(){};
	
	
	static{
		System.out.println("系统正在初始化...");
		String formatSuffixValue = "";
		String formatSuffixSplits[] = null;
		String classValue = "";
		String classSplits[] = null;
		String formatClassValue = "";
		String formatClassSplits[] = null;
		
		String targetDirectorySplits[] = null;
		
		String formatCategory = "";
		String formatCategorySplits[] = null;
		
		
		/*
		 * 加载索引状态参数
		 */
		IndexStatusBuilt = Integer.parseInt(PropertiesManager.getKeyValue("index.status.built"));
		IndexStatusSuccess = Integer.parseInt(PropertiesManager.getKeyValue("index.status.success"));
		
		/*
		 * 加载文件类型后缀和插件（类名）
		 */
		formatSuffixValue = PropertiesManager.getKeyValue("format.suffix");
		formatSuffixSplits = formatSuffixValue.split(",");
		
		classValue = PropertiesManager.getKeyValue("format.classpath");
		classSplits = classValue.split(",");
		
		formatClassValue = PropertiesManager.getKeyValue("format.suffix_classpath_map");
		formatClassSplits = formatClassValue.split(",");
		
		for(int i=0; i<formatSuffixSplits.length; i++)
			formatPluginMap.put(formatSuffixSplits[i], classSplits[Integer.parseInt(formatClassSplits[i]) - 1]);
		
		/*
		 * 加载待索引的目标目录列表 
		 */
		targetDirectoryValues = PropertiesManager.getKeyValue("target.directory");
		targetDirectorySplits = targetDirectoryValues.split(",");
		for(int i=0; i<targetDirectorySplits.length; i++){
			targetDirectories.add(targetDirectorySplits[i]);
			System.out.println(targetDirectorySplits[i]);
		}
		
		/*
		 *获取应用程序根目录地址 
		 */
		rootPath = Converter.convertBackSlashToSlash(SystemConfiguration.getRoot("IDSEUI"));
		rootPath += "/";
		
		/*
		 * 加载LDA目录的
		 */
		LDAPath = rootPath + "LDA/";//PropertiesManager.getKeyValue("LDA.dir");
		
		/*
		 * 判断索引目标是否改变
		 */
		database = new DBManager();
//		TargetDirectoryAccessor accessor = new TargetDirectoryAccessor(database.getIndexStore());
//		for(String targetPath : targetDirectories){
//			if(!accessor.getSecondaryTargetPath().contains(targetPath)){
//				//put the target path into the Berkeley DB
//				TargetDirectory targetAccessor = new TargetDirectory(targetPath);
//				accessor.getPrimaryTargetID().putNoReturn(targetAccessor);
//			}
//		}
		
		/*
		 * 加载文件格式类别和文件格式列表
		 */
		formatCategory = PropertiesManager.getKeyValue("format.category");
		formatCategorySplits = formatCategory.split(",");
		
		String type = "";
		String typeSplits[] = null;
		CategoryAccessor categoryAccessor = AccessorFactory.getCategoryAccessor(database.getIndexStore());
		FileTypeAccessor fileTypeAccessor = AccessorFactory.getFileTypeAccessor(database.getIndexStore());
		
		for(int i=0; i<formatCategorySplits.length; i++){
			if(!categoryAccessor.getPrimaryCategoryID().contains((byte)(i+1))){
				categoryAccessor.getPrimaryCategoryID().putNoReturn(new Category((byte)(i+1), formatCategorySplits[i]));
			}
			type = PropertiesManager.getKeyValue("format.category." + formatCategorySplits[i]);
			typeSplits = type.split(",");
			for(int j=0; j<typeSplits.length; j++){
				fileTypeBuff.put(typeSplits[j], new FileType(typeSplits[j], (byte)(i+1)));
			}
		}
		
		
		// 加载建模参数
		maxSizeAllowed_PDF = Short.parseShort(PropertiesManager.getKeyValue("maxSizeAllowed_PDF"));				
		maxSizeAllowed_TXT = Short.parseShort(PropertiesManager.getKeyValue("maxSizeAllowed_TXT"));
		maxFileCountPreDirectory = Integer.parseInt(PropertiesManager.getKeyValue("maxFileCountPreDirectory"));
		validViewPeriod = Integer.parseInt(PropertiesManager.getKeyValue("validViewPeriod"));
		intervalTaskPeriod = Integer.parseInt(PropertiesManager.getKeyValue("intervalTaskPeriod"));
		taskSimilarityThreshold = Double.parseDouble(PropertiesManager.getKeyValue("taskSimilarityThreshold"));
		klUpbound = Double.parseDouble(PropertiesManager.getKeyValue("klUpbound"));
		dMAX_GAMA = Integer.parseInt(PropertiesManager.getKeyValue("dMAX_GAMA"));
		topicFactor = Double.parseDouble(PropertiesManager.getKeyValue("topicFactor"));
		taskFactor = Double.parseDouble(PropertiesManager.getKeyValue("taskFactor"));
		locationFactor = Double.parseDouble(PropertiesManager.getKeyValue("locationFactor"));
		topN = Integer.parseInt(PropertiesManager.getKeyValue("topN"));
		step = Integer.parseInt(PropertiesManager.getKeyValue("step"));
		recommendedDocNumber = Integer.parseInt(PropertiesManager.getKeyValue("recommendedDocNumber"));
		userActivityLogFile = PropertiesManager.getKeyValue("userActivityLogFile");
		pageRankWriteCountThreshold = Integer.parseInt(PropertiesManager.getKeyValue("pagerank.update.threshold"));
		dictionaryWriteCountThreshold = Integer.parseInt(PropertiesManager.getKeyValue("dictionary.update.threshold"));
		LDAIteration = Integer.parseInt(PropertiesManager.getKeyValue("LDA.iteration"));
		
		
		// 初始化 停用词，分词，词形归并对象
		lemmatizer = new TermLemmatizer(); 
		stopWordFilter = new StopWordFilter();
		wordSegmentor = new WordSegmentation();
	}
	
	public static JSONObject getParamsInJSON(){
		JSONObject obj = new JSONObject();
		obj.accumulate("target_directory", targetDirectoryValues);
		obj.accumulate("pdf_size", maxSizeAllowed_PDF);
		obj.accumulate("txt_size", maxSizeAllowed_TXT);
		obj.accumulate("directory_size", maxFileCountPreDirectory);
		obj.accumulate("duration", validViewPeriod);
		obj.accumulate("interval", intervalTaskPeriod);
		obj.accumulate("task_similarity", taskSimilarityThreshold);
		obj.accumulate("kl", klUpbound);
		obj.accumulate("transfer_length", dMAX_GAMA);
		obj.accumulate("topic_factor", topicFactor);
		obj.accumulate("task_factor", taskFactor);
		obj.accumulate("location_factor", locationFactor);
		obj.accumulate("result_number", topN);
		obj.accumulate("recommend_step", step);
		obj.accumulate("recommend_size", recommendedDocNumber);
		obj.accumulate("log_file", userActivityLogFile);
		return obj;
	}
	
	public static void updateParams(
			String targetDirectoryValues,
			short maxSizeAllowed_PDF,
			short maxSizeAllowed_TXT,
			int maxFileCountPreDirectory,
			int validViewPeriod,
			int intervalTaskPeriod,
			Double taskSimilarityThreshold,
			double klUpbound,
			int dMAX_GAMA,
			double topicFactor,
			double taskFactor,
			double locationFactor,
			int topN,
			int step,
			int recommendedDocNumber,
			String userActivityLogFile
			){
		SystemConfiguration.targetDirectoryValues = targetDirectoryValues;
		String[]targetDirectorySplits = SystemConfiguration.targetDirectoryValues.split(",");
		SystemConfiguration.targetDirectories.clear();
		for(int i=0; i<targetDirectorySplits.length; i++){
			SystemConfiguration.targetDirectories.add(targetDirectorySplits[i]);
			System.out.println(targetDirectorySplits[i]);
		}
		
		SystemConfiguration.maxSizeAllowed_PDF = maxSizeAllowed_PDF;
		SystemConfiguration.maxSizeAllowed_TXT = maxSizeAllowed_TXT;
		SystemConfiguration.maxFileCountPreDirectory = maxFileCountPreDirectory;
		SystemConfiguration.validViewPeriod = validViewPeriod;
		SystemConfiguration.intervalTaskPeriod = intervalTaskPeriod;
		SystemConfiguration.taskSimilarityThreshold = taskSimilarityThreshold;
		SystemConfiguration.klUpbound = klUpbound;
		SystemConfiguration.dMAX_GAMA = dMAX_GAMA;
		SystemConfiguration.topicFactor = topicFactor;
		SystemConfiguration.taskFactor = taskFactor;
		SystemConfiguration.locationFactor = locationFactor;
		SystemConfiguration.topN = topN;
		SystemConfiguration.step = step;
		SystemConfiguration.recommendedDocNumber = recommendedDocNumber;
		SystemConfiguration.userActivityLogFile = userActivityLogFile;
	}
	
	public static String getRoot(String pluginID){
		String path = null;
		try{
//			path = System.getProperty("user.dir") + "/";
			path = FileLocator.toFileURL(Platform.getBundle(pluginID).getEntry("")).getPath();
			System.out.println(path);
//			path = path.substring(path.indexOf("/")+1, path.length());
		}catch(Exception e){
			e.printStackTrace();
		}
		return path;
	}
	
	public static void setTargetDirectories2Index(String targetDirectories){
		if(targetDirectories == null || targetDirectories.isEmpty())
			return;
		SystemConfiguration.targetDirectoryValues = null;
		SystemConfiguration.targetDirectories.clear();
		SystemConfiguration.targetDirectoryValues = targetDirectories;
		SystemConfiguration.targetDirectories.clear();
		String[] directories = targetDirectories.split(",");
		for(String directory : directories){
			SystemConfiguration.targetDirectories.add(directory);
		}
		
		PropertiesManager.updateProperties("target.directory", SystemConfiguration.targetDirectoryValues);
	}
	
	public static void setTargetDirectories2Index(List<String> targetDirectories){
		if(targetDirectories == null || targetDirectories.isEmpty())
			return;
		
		String directories = targetDirectories.get(0);
		for(int i=1; i<targetDirectories.size(); i++){
			directories += "," + targetDirectories.get(i);
		}
		SystemConfiguration.targetDirectoryValues = directories;
		SystemConfiguration.targetDirectories = targetDirectories;
		
		PropertiesManager.updateProperties("target.directory", SystemConfiguration.targetDirectoryValues);
	}
	
	public static void setTargetDirectories2Index(String[] targetDirectories){
		if(targetDirectories == null || targetDirectories.length == 0)
			return;
		
		SystemConfiguration.targetDirectories.clear();
		SystemConfiguration.targetDirectories.add(targetDirectories[0]);
		String directories = targetDirectories[0];
		for(int i=1; i<targetDirectories.length; i++){
			directories += "," + targetDirectories[i];
			SystemConfiguration.targetDirectories.add(targetDirectories[i]);
		}
		SystemConfiguration.targetDirectoryValues = directories;
		
		PropertiesManager.updateProperties("target.directory", SystemConfiguration.targetDirectoryValues);
	}

	public static void setPDFSizeUpbound(String PDFUpbound){
		SystemConfiguration.maxSizeAllowed_PDF = Short.parseShort(PDFUpbound);
		PropertiesManager.updateProperties("maxSizeAllowed_PDF", PDFUpbound);
	}
	
	public static void setTXTSizeUpbound(String TXTUpbound){
		SystemConfiguration.maxSizeAllowed_TXT = Short.parseShort(TXTUpbound);
		PropertiesManager.updateProperties("maxSizeAllowed_TXT", TXTUpbound);
	}
	
	public static void setDirectorySizeUpbound(String DirectoryUpbound){
		SystemConfiguration.maxFileCountPreDirectory = Short.parseShort(DirectoryUpbound);
		PropertiesManager.updateProperties("maxFileCountPreDirectory", DirectoryUpbound);
	}
	
	public static void setUserActivityLog(String userLog){
		SystemConfiguration.userActivityLogFile = userLog;
		PropertiesManager.updateProperties("userActivityLogFile", userLog);
	}
	
	public static void setMinValidViewPeriod(String minValidViewPeriod){
		SystemConfiguration.validViewPeriod = Integer.parseInt(minValidViewPeriod);
		PropertiesManager.updateProperties("validViewPeriod", minValidViewPeriod);
	}
	
	public static void setMinTaskInterval(String minTaskInterval){
		SystemConfiguration.intervalTaskPeriod = Integer.parseInt(minTaskInterval);
		PropertiesManager.updateProperties("intervalTaskPeriod", minTaskInterval);
	}
	
	public static void setTaskSimilarity(String similarity){
		SystemConfiguration.taskSimilarityThreshold = Double.parseDouble(similarity);
		PropertiesManager.updateProperties("taskSimilarityThreshold", similarity);
	}
	
	public static void setKLThreshold(String KLThreshold){
		SystemConfiguration.klUpbound = Double.parseDouble(KLThreshold);
		PropertiesManager.updateProperties("klUpbound", KLThreshold);
	}
	
	public static void setThransferLengthThreshold(String transferLengthThreshold){
		SystemConfiguration.dMAX_GAMA = Integer.parseInt(transferLengthThreshold);
		PropertiesManager.updateProperties("dMAX_GAMA", transferLengthThreshold);
	}
	
	public static void setTopicFactor(String topicFactor){
		SystemConfiguration.topicFactor = Double.parseDouble(topicFactor);
		PropertiesManager.updateProperties("topicFactor", topicFactor);
	}
	
	public static void setTaskFactor(String taskFactor){
		SystemConfiguration.taskFactor = Double.parseDouble(taskFactor);
		PropertiesManager.updateProperties("taskFactor", taskFactor);
	}
	
	public static void setLocationFactor(String locationFactor){
		SystemConfiguration.locationFactor = Double.parseDouble(locationFactor);
		PropertiesManager.updateProperties("locationFactor", locationFactor);
	}
	
	public static void setSearchResultNum(String resultNum){
		SystemConfiguration.topN = Integer.parseInt(resultNum);
		PropertiesManager.updateProperties("topN", resultNum);
	}
	
	public static void setRecommendStep(String recommendStep){
		SystemConfiguration.step = Integer.parseInt(recommendStep);
		PropertiesManager.updateProperties("step", recommendStep);
	}
	
	public static void setRecommendDocNum(String recDocNum){
		SystemConfiguration.recommendedDocNumber = Integer.parseInt(recDocNum);
		PropertiesManager.updateProperties("recommendedDocNumber", recDocNum);
	}
	
	public static void setIndexStatusBuilt(int indexStatusBuilt){
		SystemConfiguration.IndexStatusBuilt = indexStatusBuilt;
		PropertiesManager.updateProperties("index.status.built", indexStatusBuilt + "");
	}
	
	public static void setIndexStatusSuccess(int indexStatusSuccess){
		SystemConfiguration.IndexStatusSuccess = indexStatusSuccess;
		PropertiesManager.updateProperties("index.status.success", indexStatusSuccess + "");
	}
	
	public static void resetIndexDatabaseEnvironment(){
		//删除索引目录
		System.out.println(SystemConfiguration.rootPath+"database");
		try {
			String target = SystemConfiguration.rootPath + "database";
			String[]cmds = new String[]{"rm", "-rf",  new String(target.getBytes("UTF-8"), "GBK")};
			Runtime.getRuntime().exec(cmds).waitFor();
		} catch (IOException | InterruptedException e1) {
			e1.printStackTrace();
		}
		//重建索引目录
		File databaseDirectory = new File(SystemConfiguration.rootPath, "database");
		System.out.println("rebuild " + databaseDirectory.mkdir());
		
		
		SystemConfiguration.database = new DBManager();
		/*
		 * 加载文件格式类别和文件格式列表
		 */
		String formatCategory = PropertiesManager.getKeyValue("format.category");
		String[] formatCategorySplits = formatCategory.split(",");
		
		String type = "";
		String typeSplits[] = null;
		CategoryAccessor categoryAccessor = AccessorFactory.getCategoryAccessor(SystemConfiguration.database.getIndexStore());
		FileTypeAccessor fileTypeAccessor = AccessorFactory.getFileTypeAccessor(SystemConfiguration.database.getIndexStore());
		SystemConfiguration.fileTypeBuff.clear();
		for(int i=0; i<formatCategorySplits.length; i++){
			if(!categoryAccessor.getPrimaryCategoryID().contains((byte)(i+1))){
				categoryAccessor.getPrimaryCategoryID().putNoReturn(new Category((byte)(i+1), formatCategorySplits[i]));
			}
			type = PropertiesManager.getKeyValue("format.category." + formatCategorySplits[i]);
			typeSplits = type.split(",");
			for(int j=0; j<typeSplits.length; j++){
				SystemConfiguration.fileTypeBuff.put(typeSplits[j], new FileType(typeSplits[j], (byte)(i+1)));
			}
		}
	}
	
	/**
	 * 返回索引创建状态
	 * true - 可更新，false - 不可更新
	 */
	public static boolean getIndexStatus(){
		
		if(SystemConfiguration.IndexStatusBuilt == 1 && SystemConfiguration.IndexStatusSuccess == 1)
			return true;
		
		return false;
		
	}
	
	public static void main(String args[]){
		String s = "dfsf\\dfs\\sdfs\\";
		
		System.out.println(s.replaceAll("\\\\", "/"));
	}
}
