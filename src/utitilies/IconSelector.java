package utitilies;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import cn.iscas.idse.ui.bean.IconSize;
import cn.iscas.idse.ui.bean.IconType;

public class IconSelector {
	
	public static Map<IconType, Map<IconSize, String> > icon_map = null;
	
	static{
		IconSelector.icon_map = new HashMap<IconType, Map<IconSize, String> >();
		Map<IconSize, String> doc_map = new HashMap<IconSize, String>();
		doc_map.put(IconSize.SIZE32, "docx_mac32.png");
		doc_map.put(IconSize.SIZE64, "docx_mac64.png");
		doc_map.put(IconSize.SIZE80, "docx_mac80.png");
		doc_map.put(IconSize.SIZE160, "docx_mac160.png");
		IconSelector.icon_map.put(IconType.DOC, doc_map);
		
		Map<IconSize, String> jpeg_map = new HashMap<IconSize, String>();
		jpeg_map.put(IconSize.SIZE32, "jpeg32.png");
		jpeg_map.put(IconSize.SIZE64, "jpeg64.png");
		jpeg_map.put(IconSize.SIZE80, "jpeg80.png");
		jpeg_map.put(IconSize.SIZE160, "jpeg160.png");
		IconSelector.icon_map.put(IconType.JPEG, jpeg_map);
		
		Map<IconSize, String> pdf_map = new HashMap<IconSize, String>();
		pdf_map.put(IconSize.SIZE32, "pdf32.png");
		pdf_map.put(IconSize.SIZE64, "pdf64.png");
		pdf_map.put(IconSize.SIZE80, "pdf80.png");
		pdf_map.put(IconSize.SIZE160, "pdf160.png");
		IconSelector.icon_map.put(IconType.PDF, pdf_map);
		
		Map<IconSize, String> png_map = new HashMap<IconSize, String>();
		png_map.put(IconSize.SIZE32, "png32.png");
		png_map.put(IconSize.SIZE64, "png64.png");
		png_map.put(IconSize.SIZE80, "png80.png");
		png_map.put(IconSize.SIZE160, "png160.png");
		IconSelector.icon_map.put(IconType.PNG, png_map);
		
		Map<IconSize, String> ppt_map = new HashMap<IconSize, String>();
		ppt_map.put(IconSize.SIZE32, "pptx_mac32.png");
		ppt_map.put(IconSize.SIZE64, "pptx_mac64.png");
		ppt_map.put(IconSize.SIZE80, "pptx_mac80.png");
		ppt_map.put(IconSize.SIZE160, "pptx_mac160.png");
		IconSelector.icon_map.put(IconType.PPT, ppt_map);
		
		Map<IconSize, String> rar_map = new HashMap<IconSize, String>();
		rar_map.put(IconSize.SIZE32, "rar32.png");
		rar_map.put(IconSize.SIZE64, "rar64.png");
		rar_map.put(IconSize.SIZE80, "rar80.png");
		rar_map.put(IconSize.SIZE160, "rar160.png");
		IconSelector.icon_map.put(IconType.RAR, rar_map);
		
		Map<IconSize, String> txt_map = new HashMap<IconSize, String>();
		txt_map.put(IconSize.SIZE32, "text32.png");
		txt_map.put(IconSize.SIZE64, "text64.png");
		txt_map.put(IconSize.SIZE80, "text80.png");
		txt_map.put(IconSize.SIZE160, "text160.png");
		IconSelector.icon_map.put(IconType.TEXT, txt_map);
		
		Map<IconSize, String> xls_map = new HashMap<IconSize, String>();
		xls_map.put(IconSize.SIZE32, "xlsx_mac32.png");
		xls_map.put(IconSize.SIZE64, "xlsx_mac64.png");
		xls_map.put(IconSize.SIZE80, "xlsx_mac80.png");
		xls_map.put(IconSize.SIZE160, "xlsx_mac160.png");
		IconSelector.icon_map.put(IconType.XLS, xls_map);
		
		Map<IconSize, String> zip_map = new HashMap<IconSize, String>();
		zip_map.put(IconSize.SIZE32, "zip32.png");
		zip_map.put(IconSize.SIZE64, "zip64.png");
		zip_map.put(IconSize.SIZE80, "zip80.png");
		zip_map.put(IconSize.SIZE160, "zip160.png");
		IconSelector.icon_map.put(IconType.ZIP, zip_map);
	}
	
	public static Image getImage(String fileName, IconSize iconSize){
		Image image = null;
		int lastDot = fileName.lastIndexOf(".");
		String suffix = null;
		if(lastDot != -1){
			suffix = fileName.substring(lastDot + 1).toLowerCase();
		}
		
		if(suffix == null){}
		else if("docx".equals(suffix) || "doc".equals(suffix)){
			image = IconSelector.getImage(iconSize, IconType.DOC);
		}
		else if("jpeg".equals(suffix)){
			image = IconSelector.getImage(iconSize, IconType.JPEG);
		}
		else if("pdf".equals(suffix)){
			image = IconSelector.getImage(iconSize, IconType.PDF);
		}
		else if("png".equals(suffix)){
			image = IconSelector.getImage(iconSize, IconType.PNG);
		}
		else if("pptx".equals(suffix) || "ppt".equals(suffix)){
			image = IconSelector.getImage(iconSize, IconType.PPT);
		}
		else if("rar".equals(suffix)){
			image = IconSelector.getImage(iconSize, IconType.RAR);
		}
		else if("xlsx".equals(suffix) || "xls".equals(suffix)){
			image = IconSelector.getImage(iconSize, IconType.XLS);
		}
		else if("zip".equals(suffix)){
			image = IconSelector.getImage(iconSize, IconType.ZIP);
		}
		else if("text".equals(suffix)){
			image = IconSelector.getImage(iconSize, IconType.TEXT);
		}
		else{
			image = IconSelector.getImage(iconSize, IconType.TEXT);
		}
		
		return image;
	}
	
	public static Image getImage(IconSize iconSize, IconType iconType){
		String relativePath = "/icons/"+IconSelector.icon_map.get(iconType).get(iconSize);
		return IconSelector.getImage(relativePath);
	}
	
	public static Image getImage(String relativePath){
		ImageDescriptor imageDes = AbstractUIPlugin.imageDescriptorFromPlugin("IDSEUI", relativePath);
		Image image = imageDes.createImage();
		return image;
	}
}
