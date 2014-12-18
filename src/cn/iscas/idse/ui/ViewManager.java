package cn.iscas.idse.ui;

import java.util.HashMap;
import java.util.Map;


public class ViewManager {
	
	private static Map<String, Object> viewMap = new HashMap<String, Object>();
	
	public static void register(String viewID, Object viewInstance){
		ViewManager.viewMap.put(viewID, viewInstance);
	}
	public static Object getView(String viewID){
		return ViewManager.viewMap.get(viewID);
	}
}
