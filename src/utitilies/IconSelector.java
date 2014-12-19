package utitilies;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import cn.iscas.idse.format.FileExtractor;
import cn.iscas.idse.format.FileExtractorFactory;
import cn.iscas.idse.ui.bean.IconSize;
import cn.iscas.idse.ui.bean.FileType;

public class IconSelector {
	
	public static Map<FileType, Map<IconSize, String> > icon_map = null;
	
	static{
		IconSelector.icon_map = new HashMap<FileType, Map<IconSize, String> >();
		Map<IconSize, String> doc_map = new HashMap<IconSize, String>();
		doc_map.put(IconSize.SIZE32, "docx_mac32.png");
		doc_map.put(IconSize.SIZE64, "docx_mac64.png");
		doc_map.put(IconSize.SIZE80, "docx_mac80.png");
		doc_map.put(IconSize.SIZE160, "docx_mac160.png");
		IconSelector.icon_map.put(FileType.DOC, doc_map);
		
		Map<IconSize, String> jpeg_map = new HashMap<IconSize, String>();
		jpeg_map.put(IconSize.SIZE32, "jpeg32.png");
		jpeg_map.put(IconSize.SIZE64, "jpeg64.png");
		jpeg_map.put(IconSize.SIZE80, "jpeg80.png");
		jpeg_map.put(IconSize.SIZE160, "jpeg160.png");
		IconSelector.icon_map.put(FileType.JPEG, jpeg_map);
		
		Map<IconSize, String> pdf_map = new HashMap<IconSize, String>();
		pdf_map.put(IconSize.SIZE32, "pdf32.png");
		pdf_map.put(IconSize.SIZE64, "pdf64.png");
		pdf_map.put(IconSize.SIZE80, "pdf80.png");
		pdf_map.put(IconSize.SIZE160, "pdf160.png");
		IconSelector.icon_map.put(FileType.PDF, pdf_map);
		
		Map<IconSize, String> png_map = new HashMap<IconSize, String>();
		png_map.put(IconSize.SIZE32, "png32.png");
		png_map.put(IconSize.SIZE64, "png64.png");
		png_map.put(IconSize.SIZE80, "png80.png");
		png_map.put(IconSize.SIZE160, "png160.png");
		IconSelector.icon_map.put(FileType.PNG, png_map);
		
		Map<IconSize, String> ppt_map = new HashMap<IconSize, String>();
		ppt_map.put(IconSize.SIZE32, "pptx_mac32.png");
		ppt_map.put(IconSize.SIZE64, "pptx_mac64.png");
		ppt_map.put(IconSize.SIZE80, "pptx_mac80.png");
		ppt_map.put(IconSize.SIZE160, "pptx_mac160.png");
		IconSelector.icon_map.put(FileType.PPT, ppt_map);
		
		Map<IconSize, String> rar_map = new HashMap<IconSize, String>();
		rar_map.put(IconSize.SIZE32, "rar32.png");
		rar_map.put(IconSize.SIZE64, "rar64.png");
		rar_map.put(IconSize.SIZE80, "rar80.png");
		rar_map.put(IconSize.SIZE160, "rar160.png");
		IconSelector.icon_map.put(FileType.RAR, rar_map);
		
		Map<IconSize, String> txt_map = new HashMap<IconSize, String>();
		txt_map.put(IconSize.SIZE32, "text32.png");
		txt_map.put(IconSize.SIZE64, "text64.png");
		txt_map.put(IconSize.SIZE80, "text80.png");
		txt_map.put(IconSize.SIZE160, "text160.png");
		IconSelector.icon_map.put(FileType.TEXT, txt_map);
		
		Map<IconSize, String> xls_map = new HashMap<IconSize, String>();
		xls_map.put(IconSize.SIZE32, "xlsx_mac32.png");
		xls_map.put(IconSize.SIZE64, "xlsx_mac64.png");
		xls_map.put(IconSize.SIZE80, "xlsx_mac80.png");
		xls_map.put(IconSize.SIZE160, "xlsx_mac160.png");
		IconSelector.icon_map.put(FileType.XLS, xls_map);
		
		Map<IconSize, String> zip_map = new HashMap<IconSize, String>();
		zip_map.put(IconSize.SIZE32, "zip32.png");
		zip_map.put(IconSize.SIZE64, "zip64.png");
		zip_map.put(IconSize.SIZE80, "zip80.png");
		zip_map.put(IconSize.SIZE160, "zip160.png");
		IconSelector.icon_map.put(FileType.ZIP, zip_map);
	}
	
	public static Image getImage(String fileName, IconSize iconSize){
		Image image = IconSelector.getImage(iconSize, IconSelector.getFileType(fileName));
		return image;
	}
	
	public static Image getImage(IconSize iconSize, FileType iconType){
		String relativePath = "/icons/"+IconSelector.icon_map.get(iconType).get(iconSize);
		return IconSelector.getImage(relativePath);
	}
	
	public static Image getImage(String relativePath){
		ImageDescriptor imageDes = AbstractUIPlugin.imageDescriptorFromPlugin("IDSEUI", relativePath);
		Image image = imageDes.createImage();
		return image;
	}
	
	/**
	 * 获取枚举类型的文件类型
	 * @param fileName
	 * @return	enum类型的文件类型
	 */
	public static FileType getFileType(String fileName){
		String suffix = IconSelector.getFileTypeInString(fileName);
		FileType type = FileType.OTHER;
		
		if(suffix == null) type = FileType.OTHER;
		else if("docx".equals(suffix) || "doc".equals(suffix))  type = FileType.DOC;
		else if("jpeg".equals(suffix)) type = FileType.JPEG;
		else if("pdf".equals(suffix)) type = FileType.PDF;
		else if("png".equals(suffix)) type =  FileType.PNG;
		else if("pptx".equals(suffix) || "ppt".equals(suffix)) type = FileType.PPT;
		else if("rar".equals(suffix)) type = FileType.RAR;
		else if("xlsx".equals(suffix) || "xls".equals(suffix)) type = FileType.XLS;
		else if("zip".equals(suffix)) type = FileType.ZIP;
		else if("text".equals(suffix)) type = FileType.TEXT;
		else type = FileType.OTHER;
		
		return type;
	}
	/**
	 * 获取字符串类型的文件类型
	 * @param fileName
	 * @return 字符串类型的文件类型
	 */
	public static String getFileTypeInString(String fileName){
		int lastDot = fileName.lastIndexOf(".");
		String suffix = null;
		if(lastDot != -1){
			suffix = fileName.substring(lastDot + 1).toLowerCase();
		}
		return suffix;
	}
	
	/**
	 * 获得文件摘要，前2000个字符
	 * @param directory
	 * @param fileName
	 * @return
	 */
	public static String getFileAbstract(String directory, String fileName){
		String content = "";
		String suffix = IconSelector.getFileTypeInString(fileName);
		if(suffix == null) suffix = ".";
		else suffix = "."+suffix;
		
		FileExtractor extractor = FileExtractorFactory.getInstance().getFileExtractor(suffix);
		if(extractor != null){
			extractor.setFilePath(directory + File.separator + fileName);
			content = extractor.getContent();
			if(content == null)content = "";
			if(content.length()>2000){
				content = content.substring(0, 2000);
			}
		}
		return content;
	}
}
