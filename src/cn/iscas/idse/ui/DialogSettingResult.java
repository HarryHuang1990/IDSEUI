package cn.iscas.idse.ui;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import cn.iscas.idse.config.SystemConfiguration;
import cn.iscas.idse.index.Index;
import cn.iscas.idse.rank.MatrixWriter;
import cn.iscas.idse.rank.PersonalRank;
import cn.iscas.idse.ui.bean.IconSize;
import cn.iscas.idse.ui.bean.FileType;
import utitilies.IconSelector;

public class DialogSettingResult extends TitleAreaDialog {
	public static final String ID = Consts.ID_PREFIX + "DialogSettingResult";
	
	private Image image = IconSelector.getImage(IconSize.SIZE80, FileType.DOC);
	private Combo resultNumberText;
	private Combo recommendStepText;
	private Combo RecommendNumberText;
	
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
		GridData comboCompositeData = new GridData(GridData.FILL_HORIZONTAL);
		comboCompositeData.heightHint = 43;
		resultNumberComposite.setLayoutData(comboCompositeData);
		resultNumberComposite.setLayout(new FormLayout());	
		
		Label resultNumberLabel = new Label(resultNumberComposite, SWT.NONE);
		resultNumberLabel.setText("返回搜索结果数：");
		FormData resultNumberLabelData = new FormData();
		resultNumberLabelData.top = new FormAttachment(0, DialogSettingResult.GROUP_PADDING);
		resultNumberLabelData.left = new FormAttachment(0, DialogSettingResult.GROUP_PADDING	);
		resultNumberLabel.setLayoutData(resultNumberLabelData);
		
		resultNumberText = new Combo(resultNumberComposite, SWT.NONE | SWT.BORDER);
		FormData resultNumberTextData = new FormData();
		resultNumberTextData.top = new FormAttachment(0, DialogSettingResult.GROUP_PADDING);
		resultNumberTextData.left = new FormAttachment(0, DialogSettingResult.INDENT_SPACING);
		resultNumberTextData.right = new FormAttachment(100, -DialogSettingResult.GROUP_PADDING);
		resultNumberText.setLayoutData(resultNumberTextData);
		resultNumberText.add("10");
		resultNumberText.add("20");
		resultNumberText.add("30");
		resultNumberText.setText(SystemConfiguration.topN + "");
		
		// Recommendation step
		Composite recommendStepComposite = new Composite(composite, SWT.NONE);
		recommendStepComposite.setLayoutData(comboCompositeData);
		recommendStepComposite.setLayout(new FormLayout());
		
		Label recommendStepLabel = new Label(recommendStepComposite, SWT.NONE);
		recommendStepLabel.setText("推荐步长：");
		FormData recommendStepLabelData = new FormData();
		recommendStepLabelData.top = new FormAttachment(0, DialogSettingResult.ADJACENT_SPACING);
		recommendStepLabelData.left = new FormAttachment(0, DialogSettingResult.GROUP_PADDING	);
		recommendStepLabel.setLayoutData(recommendStepLabelData);
		
		recommendStepText = new Combo(recommendStepComposite, SWT.NONE | SWT.BORDER);
		FormData recommendStepTextData = new FormData();
		recommendStepTextData.top = new FormAttachment(0, DialogSettingResult.ADJACENT_SPACING);
		recommendStepTextData.left = new FormAttachment(0, DialogSettingResult.INDENT_SPACING);
		recommendStepTextData.right = new FormAttachment(100, -DialogSettingResult.GROUP_PADDING);
		recommendStepText.setLayoutData(recommendStepTextData);
		recommendStepText.add("2");
		recommendStepText.add("3");
		recommendStepText.add("4");
		recommendStepText.setText(SystemConfiguration.step + "");
		
		// Number of Recommendation
		Composite RecommendNumberComposite = new Composite(composite, SWT.NONE);
		RecommendNumberComposite.setLayoutData(comboCompositeData);
		RecommendNumberComposite.setLayout(new FormLayout());
		
		Label RecommendNumberLabel = new Label(RecommendNumberComposite, SWT.NONE);
		RecommendNumberLabel.setText("推荐结果数：");
		FormData RecommendNumberLabelData = new FormData();
		RecommendNumberLabelData.top = new FormAttachment(0, DialogSettingResult.ADJACENT_SPACING);
		RecommendNumberLabelData.left = new FormAttachment(0, DialogSettingResult.GROUP_PADDING	);
		RecommendNumberLabel.setLayoutData(RecommendNumberLabelData);
		
		RecommendNumberText = new Combo(RecommendNumberComposite, SWT.NONE | SWT.BORDER);
		FormData RecommendNumberTextData = new FormData();
		RecommendNumberTextData.top = new FormAttachment(0, DialogSettingResult.ADJACENT_SPACING);
		RecommendNumberTextData.left = new FormAttachment(0, DialogSettingResult.INDENT_SPACING);
		RecommendNumberTextData.right = new FormAttachment(100, -DialogSettingResult.GROUP_PADDING);
		RecommendNumberText.setLayoutData(RecommendNumberTextData);
		for(int i=3; i<=10; i++){
			RecommendNumberText.add(""+i);
		}
		RecommendNumberText.setText(SystemConfiguration.recommendedDocNumber + "");
		
		// 注册dialog
		ViewManager.register(DialogSettingResult.ID, this);
		
		return composite;
	}

	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		super.createButton(parent, DialogSettingResult.BUTTON_ID_RUNNING, "Run", true);
		super.createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
		super.createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, true);
	}
	
	@Override
	protected void okPressed() {
		if(this.getResultNumber().trim().isEmpty()){
			MessageDialog.openWarning(null, "警告", "请设置搜索结果数");
		}
		else if(this.getRecommendStep().trim().isEmpty()){
			MessageDialog.openWarning(null, "警告", "请设置推荐步长");
		}
		else if(this.getRecommendNumber().trim().isEmpty()){
			MessageDialog.openWarning(null, "警告", "请设置推荐结果数");
		}
		else{
			SystemConfiguration.setSearchResultNum(this.getResultNumber());;
			SystemConfiguration.setRecommendStep(this.getRecommendStep());
			SystemConfiguration.setRecommendDocNum(this.getRecommendNumber());
			super.okPressed();
		}
	}
	

	@Override
	protected void buttonPressed(int buttonId) {
		if(buttonId == DialogSettingResult.BUTTON_ID_RUNNING){
			if(this.getResultNumber().trim().isEmpty()){
				MessageDialog.openWarning(null, "警告", "请设置搜索结果数");
			}
			else if(this.getRecommendStep().trim().isEmpty()){
				MessageDialog.openWarning(null, "警告", "请设置推荐步长");
			}
			else if(this.getRecommendNumber().trim().isEmpty()){
				MessageDialog.openWarning(null, "警告", "请设置推荐结果数");
			}
			else{
				SystemConfiguration.setSearchResultNum(this.getResultNumber());;
				SystemConfiguration.setRecommendStep(this.getRecommendStep());
				SystemConfiguration.setRecommendDocNum(this.getRecommendNumber());
				super.buttonPressed(buttonId);

				Job indexJob = new Job("正在更新推荐结果......"){

					@Override
					protected IStatus run(IProgressMonitor monitor) {
						monitor.beginTask("正在更新推荐结果......", IProgressMonitor.UNKNOWN);
						MatrixWriter graphBuilder = new MatrixWriter();
						graphBuilder.updateRecommends();
						return Status.OK_STATUS;
					}
					
				};
//				indexJob.setUser(true);
				indexJob.schedule();
				
			}
		}
	}
	
	public String getResultNumber(){
		return this.resultNumberText.getText();
	}
	public String getRecommendStep(){
		return this.recommendStepText.getText();
	}
	public String getRecommendNumber(){
		return this.RecommendNumberText.getText();
	}
	public void setResultNumber(String content){
		this.resultNumberText.setText(content);
	}
	public void setRecommendStep(String content){
		this.recommendStepText.setText(content);
	}
	public void setRecommendNumber(String content){
		this.RecommendNumberText.setText(content);
	}
	
}
