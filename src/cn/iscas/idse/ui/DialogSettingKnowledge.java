package cn.iscas.idse.ui;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import cn.iscas.idse.config.SystemConfiguration;
import cn.iscas.idse.ui.action.UserLogDirectoryAction;
import cn.iscas.idse.ui.bean.IconSize;
import cn.iscas.idse.ui.bean.FileType;
import utitilies.IconSelector;

public class DialogSettingKnowledge extends TitleAreaDialog {
	public static final String ID = Consts.ID_PREFIX + "DialogSettingKnowledge";
	
	private Image image = IconSelector.getImage(IconSize.SIZE80, FileType.DOC);
	private Text userActivityText;
	private Combo minDurationText;
	private Combo minIntervalText;
	private Text similarThresholdText;
	private Text KLThresholdText;
	private Text TransferLengthText;
	private Text topicFactorText;
	private Text TaskFactorText;
	private Text LocationFactorText;
	
	public static int GROUP_PADDING = 5;
	public static int INDENT_SPACING = 170;
	public static int ADJACENT_SPACING = 12;
	public static int BUTTON_ID_RUNNING = 0x00001;
	
	public DialogSettingKnowledge(Shell parentShell) {
		super(parentShell);
	}
	
	public boolean close(){
		if(image!=null) image.dispose();
		return super.close();
	}

	@Override
	protected Control createContents(Composite parent) {
		Control contents = super.createContents(parent);
		
		super.setTitle("创建，管理，执行知识挖掘配置");
		
		super.setMessage("配置知识挖掘所需参数");
		
		super.setTitleImage(this.image);
				
		return contents;
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite composite = (Composite)super.createDialogArea(parent);
		
		// user activity log file
		Composite userActivityComposite = new Composite(composite, SWT.NONE);
		userActivityComposite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		userActivityComposite.setLayout(new FormLayout());	
		
		Label userActivityLabel = new Label(userActivityComposite, SWT.NONE);
		userActivityLabel.setText("用户日志目录：");
		FormData userActivityLabelData = new FormData();
		userActivityLabelData.top = new FormAttachment(0, DialogSettingKnowledge.GROUP_PADDING);
		userActivityLabelData.left = new FormAttachment(0, DialogSettingKnowledge.GROUP_PADDING	);
		userActivityLabel.setLayoutData(userActivityLabelData);
		
		Button browserButton = new Button(userActivityComposite, SWT.NONE);
		browserButton.setText("设置目录");
		FormData browserButtonData = new FormData();
		browserButtonData.top = new FormAttachment(0, DialogSettingIndex.GROUP_PADDING);
		browserButtonData.right = new FormAttachment(100, -DialogSettingIndex.GROUP_PADDING);
		browserButtonData.width = 100;
		browserButtonData.height = 27;
		browserButton.setLayoutData(browserButtonData);
		
		Text userActivityText = new Text(userActivityComposite, SWT.NONE | SWT.BORDER);
		FormData userActivityTextData = new FormData();
		userActivityTextData.top = new FormAttachment(0, DialogSettingKnowledge.GROUP_PADDING);
		userActivityTextData.left = new FormAttachment(0, DialogSettingKnowledge.INDENT_SPACING);
		userActivityTextData.right = new FormAttachment(browserButton, -DialogSettingKnowledge.GROUP_PADDING);
		userActivityText.setLayoutData(userActivityTextData);
		userActivityText.setEnabled(false);
		userActivityText.setText(SystemConfiguration.userActivityLogFile);
		
		browserButton.addSelectionListener(new UserLogDirectoryAction(userActivityText));
		
		// min duration to divide user log(s)
		Composite minDurationComposite = new Composite(composite, SWT.NONE);
		GridData comboCompositeData = new GridData(GridData.FILL_HORIZONTAL);
		comboCompositeData.heightHint = 43;
		minDurationComposite.setLayoutData(comboCompositeData);
		minDurationComposite.setLayout(new FormLayout());
		
		Label minDurationLabel = new Label(minDurationComposite, SWT.NONE);
		minDurationLabel.setText("最短有效行为时长：");
		FormData minDurationLabelData = new FormData();
		minDurationLabelData.top = new FormAttachment(0, DialogSettingKnowledge.ADJACENT_SPACING);
		minDurationLabelData.left = new FormAttachment(0, DialogSettingKnowledge.GROUP_PADDING	);
		minDurationLabel.setLayoutData(minDurationLabelData);

		Combo minDurationText = new Combo(minDurationComposite, SWT.NONE | SWT.BORDER);
		FormData minDurationTextData = new FormData();
		minDurationTextData.top = new FormAttachment(0, DialogSettingKnowledge.ADJACENT_SPACING);
		minDurationTextData.left = new FormAttachment(0, DialogSettingKnowledge.INDENT_SPACING);
		minDurationTextData.right = new FormAttachment(100, -DialogSettingKnowledge.GROUP_PADDING);
		minDurationText.setLayoutData(minDurationTextData);
		int validSecs = 1;
		for(int i=0; i<30; i++, validSecs++){
			minDurationText.add(validSecs + "");
		}
		minDurationText.setText(SystemConfiguration.validViewPeriod + "");
		
		// min interval to divide user log(s)
		Composite minIntervalComposite = new Composite(composite, SWT.NONE);
		minIntervalComposite.setLayoutData(comboCompositeData);
		minIntervalComposite.setLayout(new FormLayout());
		
		Label minIntervalLabel = new Label(minIntervalComposite, SWT.NONE);
		minIntervalLabel.setText("新任务启动最小间隔：");
		FormData minIntervalLabelData = new FormData();
		minIntervalLabelData.top = new FormAttachment(0, DialogSettingKnowledge.ADJACENT_SPACING);
		minIntervalLabelData.left = new FormAttachment(0, DialogSettingKnowledge.GROUP_PADDING	);
		minIntervalLabel.setLayoutData(minIntervalLabelData);
		
		Combo minIntervalText = new Combo(minIntervalComposite, SWT.NONE | SWT.BORDER);
		FormData minIntervalTextData = new FormData();
		minIntervalTextData.top = new FormAttachment(0, DialogSettingKnowledge.ADJACENT_SPACING);
		minIntervalTextData.left = new FormAttachment(0, DialogSettingKnowledge.INDENT_SPACING);
		minIntervalTextData.right = new FormAttachment(100, -DialogSettingKnowledge.GROUP_PADDING);
		minIntervalText.setLayoutData(minIntervalTextData);
		int minInterval = 1;
		for(int i=0; i<30; i++, minInterval++){
			minIntervalText.add(minInterval + "");
		}
		minIntervalText.setText(SystemConfiguration.intervalTaskPeriod + "");
		
		// similarity threshold to merge raw log cluster after dividing
		Composite similarThresholdComposite = new Composite(composite, SWT.NONE);
		similarThresholdComposite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		similarThresholdComposite.setLayout(new FormLayout());
		
		Label similarThresholdLabel = new Label(similarThresholdComposite, SWT.NONE);
		similarThresholdLabel.setText("task相似度阈值：");
		FormData similarThresholdLabelData = new FormData();
		similarThresholdLabelData.top = new FormAttachment(0, DialogSettingKnowledge.ADJACENT_SPACING);
		similarThresholdLabelData.left = new FormAttachment(0, DialogSettingKnowledge.GROUP_PADDING	);
		similarThresholdLabel.setLayoutData(similarThresholdLabelData);
		
		Text similarThresholdText = new Text(similarThresholdComposite, SWT.NONE | SWT.BORDER);
		FormData similarThresholdTextData = new FormData();
		similarThresholdTextData.top = new FormAttachment(0, DialogSettingKnowledge.ADJACENT_SPACING);
		similarThresholdTextData.left = new FormAttachment(0, DialogSettingKnowledge.INDENT_SPACING);
		similarThresholdTextData.right = new FormAttachment(100, -DialogSettingKnowledge.GROUP_PADDING);
		similarThresholdText.setLayoutData(similarThresholdTextData);
		similarThresholdText.setText(SystemConfiguration.taskSimilarityThreshold + "");
		
		// KL threshold for topic pruning
		Composite KLThresholdComposite = new Composite(composite, SWT.NONE);
		KLThresholdComposite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		KLThresholdComposite.setLayout(new FormLayout());
		
		Label KLThresholdLabel = new Label(KLThresholdComposite, SWT.NONE);
		KLThresholdLabel.setText("KL散度阈值：");
		FormData KLThresholdLabelData = new FormData();
		KLThresholdLabelData.top = new FormAttachment(0, DialogSettingKnowledge.ADJACENT_SPACING);
		KLThresholdLabelData.left = new FormAttachment(0, DialogSettingKnowledge.GROUP_PADDING	);
		KLThresholdLabel.setLayoutData(KLThresholdLabelData);
		
		Text KLThresholdText = new Text(KLThresholdComposite, SWT.NONE | SWT.BORDER);
		FormData KLThresholdTextData = new FormData();
		KLThresholdTextData.top = new FormAttachment(0, DialogSettingKnowledge.ADJACENT_SPACING);
		KLThresholdTextData.left = new FormAttachment(0, DialogSettingKnowledge.INDENT_SPACING);
		KLThresholdTextData.right = new FormAttachment(100, -DialogSettingKnowledge.GROUP_PADDING);
		KLThresholdText.setLayoutData(KLThresholdTextData);
		KLThresholdText.setText(SystemConfiguration.klUpbound + "");
		
		// Transfer lenth threshold between two files
		Composite TransferLengthComposite = new Composite(composite, SWT.NONE);
		TransferLengthComposite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		TransferLengthComposite.setLayout(new FormLayout());
		
		Label TransferLengthLabel = new Label(TransferLengthComposite, SWT.NONE);
		TransferLengthLabel.setText("转移距离阈值：");
		FormData TransferLengthLabelData = new FormData();
		TransferLengthLabelData.top = new FormAttachment(0, DialogSettingKnowledge.ADJACENT_SPACING);
		TransferLengthLabelData.left = new FormAttachment(0, DialogSettingKnowledge.GROUP_PADDING	);
		TransferLengthLabel.setLayoutData(TransferLengthLabelData);
		
		Text TransferLengthText = new Text(TransferLengthComposite, SWT.NONE | SWT.BORDER);
		FormData TransferLengthTextData = new FormData();
		TransferLengthTextData.top = new FormAttachment(0, DialogSettingKnowledge.ADJACENT_SPACING);
		TransferLengthTextData.left = new FormAttachment(0, DialogSettingKnowledge.INDENT_SPACING);
		TransferLengthTextData.right = new FormAttachment(100, -DialogSettingKnowledge.GROUP_PADDING);
		TransferLengthText.setLayoutData(TransferLengthTextData);
		TransferLengthText.setText(SystemConfiguration.dMAX_GAMA + "");
		
		// Contributions factor of Topic Relation
		Composite topicFactorComposite = new Composite(composite, SWT.NONE);
		topicFactorComposite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		topicFactorComposite.setLayout(new FormLayout());
		
		Label topicFactorLabel = new Label(topicFactorComposite, SWT.NONE);
		topicFactorLabel.setText("Topic关系贡献因子：");
		FormData topicFactorLabelData = new FormData();
		topicFactorLabelData.top = new FormAttachment(0, DialogSettingKnowledge.ADJACENT_SPACING);
		topicFactorLabelData.left = new FormAttachment(0, DialogSettingKnowledge.GROUP_PADDING	);
		topicFactorLabel.setLayoutData(topicFactorLabelData);
		
		Text topicFactorText = new Text(topicFactorComposite, SWT.NONE | SWT.BORDER);
		FormData topicFactorTextData = new FormData();
		topicFactorTextData.top = new FormAttachment(0, DialogSettingKnowledge.ADJACENT_SPACING);
		topicFactorTextData.left = new FormAttachment(0, DialogSettingKnowledge.INDENT_SPACING);
		topicFactorTextData.right = new FormAttachment(100, -DialogSettingKnowledge.GROUP_PADDING);
		topicFactorText.setLayoutData(topicFactorTextData);
		topicFactorText.setText(SystemConfiguration.topicFactor + "");
		
		// Contribution factor of Task Relation
		Composite TaskFactorComposite = new Composite(composite, SWT.NONE);
		TaskFactorComposite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		TaskFactorComposite.setLayout(new FormLayout());
		
		Label TaskFactorLabel = new Label(TaskFactorComposite, SWT.NONE);
		TaskFactorLabel.setText("Task关系贡献因子：");
		FormData TaskFactorLabelData = new FormData();
		TaskFactorLabelData.top = new FormAttachment(0, DialogSettingKnowledge.ADJACENT_SPACING);
		TaskFactorLabelData.left = new FormAttachment(0, DialogSettingKnowledge.GROUP_PADDING	);
		TaskFactorLabel.setLayoutData(TaskFactorLabelData);
		
		Text TaskFactorText = new Text(TaskFactorComposite, SWT.NONE | SWT.BORDER);
		FormData TaskFactorTextData = new FormData();
		TaskFactorTextData.top = new FormAttachment(0, DialogSettingKnowledge.ADJACENT_SPACING);
		TaskFactorTextData.left = new FormAttachment(0, DialogSettingKnowledge.INDENT_SPACING);
		TaskFactorTextData.right = new FormAttachment(100, -DialogSettingKnowledge.GROUP_PADDING);
		TaskFactorText.setLayoutData(TaskFactorTextData);
		TaskFactorText.setText(SystemConfiguration.taskFactor + "");
		
		// Contribution factor of Location Relation		
		Composite LocationFactorComposite = new Composite(composite, SWT.NONE);
		LocationFactorComposite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		LocationFactorComposite.setLayout(new FormLayout());
		
		Label LocationFactorLabel = new Label(LocationFactorComposite, SWT.NONE);
		LocationFactorLabel.setText("Location关系贡献银子：");
		FormData LocationFactorLabelData = new FormData();
		LocationFactorLabelData.top = new FormAttachment(0, DialogSettingKnowledge.ADJACENT_SPACING);
		LocationFactorLabelData.left = new FormAttachment(0, DialogSettingKnowledge.GROUP_PADDING	);
		LocationFactorLabel.setLayoutData(LocationFactorLabelData);
		
		Text LocationFactorText = new Text(LocationFactorComposite, SWT.NONE | SWT.BORDER);
		FormData LocationFactorTextData = new FormData();
		LocationFactorTextData.top = new FormAttachment(0, DialogSettingKnowledge.ADJACENT_SPACING);
		LocationFactorTextData.left = new FormAttachment(0, DialogSettingKnowledge.INDENT_SPACING);
		LocationFactorTextData.right = new FormAttachment(100, -DialogSettingKnowledge.GROUP_PADDING);
		LocationFactorText.setLayoutData(LocationFactorTextData);
		LocationFactorText.setText(SystemConfiguration.locationFactor + "");
		
		// 注册dialog
		ViewManager.register(DialogSettingKnowledge.ID, this);
		
		return composite;
	}

	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		super.createButton(parent, DialogSettingKnowledge.BUTTON_ID_RUNNING, "Run", true);
		super.createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
		super.createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, true);
	}
	
	public String getUserActivity(){
		return this.userActivityText.getText();
	}
	public String getMinDuration(){
		return this.minDurationText.getText();
	}
	public String getMinInterval(){
		return this.minIntervalText.getText();
	}
	public String getSimilarThreshold(){
		return this.similarThresholdText.getText();
	}
	public String getKLThreshold(){
		return this.KLThresholdText.getText();
	}
	public String getTransferLength(){
		return this.TransferLengthText.getText();
	}
	public String getTopicFactor(){
		return this.topicFactorText.getText();
	}
	public String getTaskFactor(){
		return this.TaskFactorText.getText();
	}
	public String getLocationFactor(){
		return this.LocationFactorText.getText();
	}
	
	public void setUserActivity(String content){
		this.userActivityText.setText(content);
	}
	public void setMinDuration(String content){
		this.minDurationText.setText(content);
	}
	public void setMinInterval(String content){
		this.minIntervalText.setText(content);
	}
	public void setSimilarThreshold(String content){
		this.similarThresholdText.setText(content);
	}
	public void setKLThreshold(String content){
		this.KLThresholdText.setText(content);
	}
	public void setTransferLength(String content){
		this.TransferLengthText.setText(content);
	}
	public void setTopicFactor(String content){
		this.topicFactorText.setText(content);
	}
	public void setTaskFactor(String content){
		this.TaskFactorText.setText(content);
	}
	public void setLocationFactor(String content){
		this.LocationFactorText.setText(content);
	}
	
	
}
