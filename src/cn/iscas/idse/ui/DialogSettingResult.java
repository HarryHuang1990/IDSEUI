package cn.iscas.idse.ui;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import cn.iscas.idse.ui.bean.IconSize;
import cn.iscas.idse.ui.bean.IconType;
import utitilies.IconSelector;

public class DialogSettingResult extends TitleAreaDialog {
	
	private Image image = IconSelector.getImage(IconSize.SIZE80, IconType.DOC);
	public static int GROUP_PADDING = 5;
	public static int INDENT_SPACING = 170;
	public static int ADJACENT_SPACING = 12;
	public static int BUTTON_ID_RUNNING = 0x00001;
	
	public DialogSettingResult(Shell parentShell) {
		super(parentShell);
	}
	
	public boolean close(){
		if(image!=null) image.dispose();
		return super.close();
	}

	@Override
	protected Control createContents(Composite parent) {
		Control contents = super.createContents(parent);
		
		super.setTitle("创建，管理，执行搜索结果配置");
		
		super.setMessage("创建搜索结果相关参数的配置");
		
		super.setTitleImage(this.image);
				
		return contents;
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite composite = (Composite)super.createDialogArea(parent);
		
		// Number of Result returned
		Composite resultNumberComposite = new Composite(composite, SWT.NONE);
		resultNumberComposite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		resultNumberComposite.setLayout(new FormLayout());	
		
		Label resultNumberLabel = new Label(resultNumberComposite, SWT.NONE);
		resultNumberLabel.setText("返回搜索结果数：");
		FormData resultNumberLabelData = new FormData();
		resultNumberLabelData.top = new FormAttachment(0, DialogSettingResult.GROUP_PADDING);
		resultNumberLabelData.left = new FormAttachment(0, DialogSettingResult.GROUP_PADDING	);
		resultNumberLabel.setLayoutData(resultNumberLabelData);
		
		Text resultNumberText = new Text(resultNumberComposite, SWT.NONE | SWT.BORDER);
		FormData resultNumberTextData = new FormData();
		resultNumberTextData.top = new FormAttachment(0, DialogSettingResult.GROUP_PADDING);
		resultNumberTextData.left = new FormAttachment(0, DialogSettingResult.INDENT_SPACING);
		resultNumberTextData.right = new FormAttachment(100, -DialogSettingResult.GROUP_PADDING);
		resultNumberText.setLayoutData(resultNumberTextData);
		
		// Recommendation step
		Composite recommendStepComposite = new Composite(composite, SWT.NONE);
		recommendStepComposite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		recommendStepComposite.setLayout(new FormLayout());
		
		Label recommendStepLabel = new Label(recommendStepComposite, SWT.NONE);
		recommendStepLabel.setText("推荐步长：");
		FormData recommendStepLabelData = new FormData();
		recommendStepLabelData.top = new FormAttachment(0, DialogSettingResult.ADJACENT_SPACING);
		recommendStepLabelData.left = new FormAttachment(0, DialogSettingResult.GROUP_PADDING	);
		recommendStepLabel.setLayoutData(recommendStepLabelData);
		
		Text recommendStepText = new Text(recommendStepComposite, SWT.NONE | SWT.BORDER);
		FormData recommendStepTextData = new FormData();
		recommendStepTextData.top = new FormAttachment(0, DialogSettingResult.ADJACENT_SPACING);
		recommendStepTextData.left = new FormAttachment(0, DialogSettingResult.INDENT_SPACING);
		recommendStepTextData.right = new FormAttachment(100, -DialogSettingResult.GROUP_PADDING);
		recommendStepText.setLayoutData(recommendStepTextData);
		
		// Number of Recommendation
		Composite RecommendNumberComposite = new Composite(composite, SWT.NONE);
		RecommendNumberComposite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		RecommendNumberComposite.setLayout(new FormLayout());
		
		Label RecommendNumberLabel = new Label(RecommendNumberComposite, SWT.NONE);
		RecommendNumberLabel.setText("推荐结果数：");
		FormData RecommendNumberLabelData = new FormData();
		RecommendNumberLabelData.top = new FormAttachment(0, DialogSettingResult.ADJACENT_SPACING);
		RecommendNumberLabelData.left = new FormAttachment(0, DialogSettingResult.GROUP_PADDING	);
		RecommendNumberLabel.setLayoutData(RecommendNumberLabelData);
		
		Text RecommendNumberText = new Text(RecommendNumberComposite, SWT.NONE | SWT.BORDER);
		FormData RecommendNumberTextData = new FormData();
		RecommendNumberTextData.top = new FormAttachment(0, DialogSettingResult.ADJACENT_SPACING);
		RecommendNumberTextData.left = new FormAttachment(0, DialogSettingResult.INDENT_SPACING);
		RecommendNumberTextData.right = new FormAttachment(100, -DialogSettingResult.GROUP_PADDING);
		RecommendNumberText.setLayoutData(RecommendNumberTextData);
		
		
		return composite;
	}

	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		super.createButton(parent, DialogSettingResult.BUTTON_ID_RUNNING, "Run", true);
		super.createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
		super.createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, true);
	}
	
	
}
