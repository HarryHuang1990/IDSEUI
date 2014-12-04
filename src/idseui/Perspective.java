package idseui;

import javax.swing.text.View;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import org.eclipse.ui.console.IConsoleConstants;

public class Perspective implements IPerspectiveFactory {

	public void createInitialLayout(IPageLayout layout) {
		layout.setEditorAreaVisible(false);
		layout.addView(ViewSearchResult.ID, IPageLayout.LEFT, 0.6f, layout.getEditorArea());
		IFolderLayout rightFolder = layout.createFolder("assist.right.folder", IPageLayout.RIGHT, 0.4f, layout.getEditorArea());
		rightFolder.addView(ViewFileReader.ID);
		rightFolder.addView(ViewSetting.ID);
		
		// console
		System.out.println("dfsdfsdfsdf"+rightFolder.getProperty("folderId"));
		layout.addView(IConsoleConstants.ID_CONSOLE_VIEW, IPageLayout.BOTTOM, 0.3f, "assist.right.folder");
		ViewConsole console = new ViewConsole();
		console.openConsole();
		
		layout.getViewLayout(ViewSearchResult.ID).setCloseable(false);
		layout.getViewLayout(ViewFileReader.ID).setCloseable(false);
		layout.getViewLayout(ViewSetting.ID).setCloseable(false);
	}
}
