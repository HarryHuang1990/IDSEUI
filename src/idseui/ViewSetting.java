package idseui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
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
		parent.setLayout(new FormLayout());
		
		ScrolledComposite scrolledComposite = new ScrolledComposite(parent, SWT.V_SCROLL | SWT.BORDER);
		FormData scrolledCompositeData = new FormData();
		scrolledCompositeData.left = new FormAttachment(0,0);
		scrolledCompositeData.top = new FormAttachment(0,0);
		scrolledCompositeData.right = new FormAttachment(100,0);
		scrolledCompositeData.bottom = new FormAttachment(100,0);
		scrolledComposite.setLayoutData(scrolledCompositeData);
		
		Composite composite = new Composite(scrolledComposite, SWT.NONE);
		scrolledComposite.setContent(composite);
		composite.setLayout(new FormLayout());
		composite.setBounds(0, 0, 500, 1000);
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
		// directories to index
		
		
		// max size of pdf to index (KB)
		
		// max size of txt to index (KB)
		
		// max size of directory to index (files)
		
		
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
		
		// user activity log file
		
		// min duration to divide user log(s)
		
		// min interval to divide user log(s)
		
		// similarity threshold to merge raw log cluster after dividing
		
		// KL threshold for topic pruning
		
		// Transfer lenth threshold between two files
		
		// Contributions factor of Topic Relation
		
		// Contribution factor of Task Relation
		
		// Contribution factor of Location Relation
		
		
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
		
		// Number of Result returned
		
		// Recommendation step
		
		// Number of Recommendation
		
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

}
