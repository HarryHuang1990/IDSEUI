package utitilies;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;

public class Utilities {
	public static Image getImage(String relativePath){
		ImageDescriptor imageDes = AbstractUIPlugin.imageDescriptorFromPlugin("IDSEUI", relativePath);
		Image image = imageDes.createImage();
		return image;
	}
}
