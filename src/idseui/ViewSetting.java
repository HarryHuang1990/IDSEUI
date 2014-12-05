package idseui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.part.ViewPart;

public class ViewSetting extends ViewPart {

	public static final String ID = Consts.ID_PREFIX + "ViewSetting";
	
	public static int VIEW_PADDING = 5;
	public static int GROUP_PADDING = 5;
	public static int INDENT_SPACING = 150;
	public static int ADJACENT_SPACING = 12;
	
	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(new FillLayout());
		
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new FormLayout());
		/*
		 *  index group
		 */
		Group indexGroup = new Group(composite, SWT.BORDER);
		indexGroup.setText("索引配置");
		indexGroup.setLayout(new FormLayout());
		FormData indexGroupFormData = new FormData();
		indexGroupFormData.top = new FormAttachment(0, ViewSetting.VIEW_PADDING);
		indexGroupFormData.left = new FormAttachment(0, ViewSetting.VIEW_PADDING);
		indexGroupFormData.right = new FormAttachment(100, -ViewSetting.VIEW_PADDING); 
		indexGroup.setLayoutData(indexGroupFormData);
		
		/*
		 * knowledge mining
		 */
		Group KMGroup = new Group(composite, SWT.BORDER);
		KMGroup.setText("知识挖掘参数");
		KMGroup.setLayout(new FormLayout());
		FormData KMGroupFormData = new FormData();
		KMGroupFormData.top = new FormAttachment(indexGroup, ViewSetting.VIEW_PADDING);
		KMGroupFormData.left = new FormAttachment(0, ViewSetting.VIEW_PADDING);
		KMGroupFormData.right = new FormAttachment(100, -ViewSetting.VIEW_PADDING); 
		KMGroup.setLayoutData(KMGroupFormData);
		
		/*
		 * search result;
		 */
		Group resultGroup = new Group(composite, SWT.BORDER);
		resultGroup.setText("搜索结果配置");
		resultGroup.setLayout(new FormLayout());
		FormData resultGroupFormData = new FormData();
		resultGroupFormData.top = new FormAttachment(KMGroup, ViewSetting.VIEW_PADDING);
		resultGroupFormData.left = new FormAttachment(0, ViewSetting.VIEW_PADDING);
		resultGroupFormData.right = new FormAttachment(100, -ViewSetting.VIEW_PADDING); 
		resultGroup.setLayoutData(resultGroupFormData);
		
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

}
