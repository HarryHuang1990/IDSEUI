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
import org.eclipse.swt.custom.CCombo;
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
import cn.iscas.idse.index.Index;
import cn.iscas.idse.rank.MatrixWriter;
import cn.iscas.idse.rank.PersonalRank;
import cn.iscas.idse.ui.action.OpenTargetSelectionAction;
import cn.iscas.idse.ui.bean.IconSize;
import cn.iscas.idse.ui.bean.FileType;
import utitilies.IconSelector;

public class DialogSettingIndex extends TitleAreaDialog {
	public static final String ID = Consts.ID_PREFIX + "DialogSettingIndex";
	
	private Image image = IconSelector.getImage(IconSize.SIZE80, FileType.DOC);
	private Text directories2indexText;
	private Combo maxPDFSizeText;
	private Combo maxTXTSizeText;
	private Combo maxDirectorySizeText;
	
	public static int GROUP_PADDING = 5;
	public static int INDENT_SPACING = 170;
	public static int ADJACENT_SPACING = 12;
	public static int BUTTON_ID_RUNNING = 0x00001;
	
	public DialogSettingIndex(Shell parentShell) {
		super(parentShell);
	}
	
	public boolean close(){
		if(image!=null) image.dispose();
		return super.close();
	}

	@Override
	protected Control createContents(Composite parent) {
		Control contents = super.createContents(parent);
		
		super.setTitle("创建，管理，执行索引配置");
		
		super.setMessage("创建索引配置索引本地文件");
		
		super.setTitleImage(this.image);
				
		return contents;
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite composite = (Composite)super.createDialogArea(parent);
		
		//  directories to index
		Composite directories2indexComposite = new Composite(composite, SWT.NONE);
		directories2indexComposite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		directories2indexComposite.setLayout(new FormLayout());	
		
		Label directories2indexLabel = new Label(directories2indexComposite, SWT.NONE);
		directories2indexLabel.setText("索引目录：");
		FormData directories2indexLabelData = new FormData();
		directories2indexLabelData.top = new FormAttachment(0, DialogSettingIndex.GROUP_PADDING);
		directories2indexLabelData.left = new FormAttachment(0, DialogSettingIndex.GROUP_PADDING	);
		directories2indexLabel.setLayoutData(directories2indexLabelData);
		
		Button browserButton = new Button(directories2indexComposite, SWT.NONE);
		browserButton.setText("添加目录");
		FormData browserButtonData = new FormData();
		browserButtonData.top = new FormAttachment(0, DialogSettingIndex.GROUP_PADDING);
		browserButtonData.right = new FormAttachment(100, -DialogSettingIndex.GROUP_PADDING);
		browserButtonData.width = 100;
		browserButtonData.height = 27;
		browserButton.setLayoutData(browserButtonData);
		
		
		directories2indexText = new Text(directories2indexComposite, SWT.NONE | SWT.BORDER);
		FormData directories2indexTextData = new FormData();
		directories2indexTextData.top = new FormAttachment(0, DialogSettingIndex.GROUP_PADDING);
		directories2indexTextData.left = new FormAttachment(0, DialogSettingIndex.INDENT_SPACING);
		directories2indexTextData.right = new FormAttachment(browserButton, -DialogSettingIndex.GROUP_PADDING);
		directories2indexText.setLayoutData(directories2indexTextData);
		directories2indexText.setEnabled(false);
		directories2indexText.setText(SystemConfiguration.targetDirectoryValues);
		
		browserButton.addSelectionListener(new OpenTargetSelectionAction(directories2indexText));
		
		// max size of PDF to index (KB)
		Composite maxPDFSizeComposite = new Composite(composite, SWT.NONE );
		maxPDFSizeComposite.setLayout(new FormLayout());
		GridData comboCompositeData = new GridData(GridData.FILL_HORIZONTAL);
		comboCompositeData.heightHint = 43;
		maxPDFSizeComposite.setLayoutData(comboCompositeData);
		
		Label maxPDFSizeLabel = new Label(maxPDFSizeComposite, SWT.NONE);
		maxPDFSizeLabel.setText("PDF大小上限(KB)：");
		FormData maxPDFSizeLabelData = new FormData();
		maxPDFSizeLabelData.top = new FormAttachment(0, DialogSettingIndex.ADJACENT_SPACING);
		maxPDFSizeLabelData.left = new FormAttachment(0, DialogSettingIndex.GROUP_PADDING	);
		maxPDFSizeLabel.setLayoutData(maxPDFSizeLabelData);
		
		maxPDFSizeText = new Combo(maxPDFSizeComposite, SWT.NONE);
		FormData maxPDFSizeTextData = new FormData();
		maxPDFSizeTextData.top = new FormAttachment(0, DialogSettingIndex.ADJACENT_SPACING);
		maxPDFSizeTextData.left = new FormAttachment(0, DialogSettingIndex.INDENT_SPACING);
		maxPDFSizeTextData.right = new FormAttachment(100, -DialogSettingIndex.GROUP_PADDING);
		maxPDFSizeText.setLayoutData(maxPDFSizeTextData);
		int pdfSize = 0;
		for(int i=0; i<15; i++, pdfSize += 500){
			maxPDFSizeText.add(pdfSize + "");
		}
		maxPDFSizeText.setText(SystemConfiguration.maxSizeAllowed_PDF + "");
		
		// max size of Txt to index (KB)
		Composite maxTXTSizeComposite = new Composite(composite, SWT.NONE);
		maxTXTSizeComposite.setLayout(new FormLayout());
		maxTXTSizeComposite.setLayoutData(comboCompositeData);
		
		Label maxTXTSizeLabel = new Label(maxTXTSizeComposite, SWT.NONE);
		maxTXTSizeLabel.setText("TXT大小上限(KB)：");
		FormData maxTXTSizeLabelData = new FormData();
		maxTXTSizeLabelData.top = new FormAttachment(0, DialogSettingIndex.ADJACENT_SPACING);
		maxTXTSizeLabelData.left = new FormAttachment(0, DialogSettingIndex.GROUP_PADDING	);
		maxTXTSizeLabel.setLayoutData(maxTXTSizeLabelData);
		
		maxTXTSizeText = new Combo(maxTXTSizeComposite, SWT.NONE | SWT.BORDER);
		FormData maxTXTSizeTextData = new FormData();
		maxTXTSizeTextData.top = new FormAttachment(0, DialogSettingIndex.ADJACENT_SPACING);
		maxTXTSizeTextData.left = new FormAttachment(0, DialogSettingIndex.INDENT_SPACING);
		maxTXTSizeTextData.right = new FormAttachment(100, -DialogSettingIndex.GROUP_PADDING);
		maxTXTSizeText.setLayoutData(maxTXTSizeTextData);
		int txtSize = 0;
		for(int i=0; i<20; i++, txtSize += 50){
			maxTXTSizeText.add(txtSize + "");
		}
		maxTXTSizeText.setText(SystemConfiguration.maxSizeAllowed_TXT + "");
		
		// max size of directories to index (files)
		Composite maxDirectorySizeComposite = new Composite(composite, SWT.NONE);
		maxDirectorySizeComposite.setLayout(new FormLayout());
		maxDirectorySizeComposite.setLayoutData(comboCompositeData);
		
		Label maxDirectorySizeLabel = new Label(maxDirectorySizeComposite, SWT.NONE);
		maxDirectorySizeLabel.setText("文件夹文件数上限：");
		FormData maxDirectorySizeLabelData = new FormData();
		maxDirectorySizeLabelData.top = new FormAttachment(0, DialogSettingIndex.ADJACENT_SPACING);
		maxDirectorySizeLabelData.left = new FormAttachment(0, DialogSettingIndex.GROUP_PADDING	);
		maxDirectorySizeLabel.setLayoutData(maxDirectorySizeLabelData);
		
		maxDirectorySizeText = new Combo(maxDirectorySizeComposite, SWT.NONE | SWT.BORDER);
		FormData maxDirectorySizeTextData = new FormData();
		maxDirectorySizeTextData.top = new FormAttachment(0, DialogSettingIndex.ADJACENT_SPACING);
		maxDirectorySizeTextData.left = new FormAttachment(0, DialogSettingIndex.INDENT_SPACING);
		maxDirectorySizeTextData.right = new FormAttachment(100, -DialogSettingIndex.GROUP_PADDING);
		maxDirectorySizeText.setLayoutData(maxDirectorySizeTextData);
		int fileNum = 30;
		for(int i=0; i<10; i++, fileNum += 20){
			maxDirectorySizeText.add(fileNum + "");
		}
		maxDirectorySizeText.setText(SystemConfiguration.maxFileCountPreDirectory + "");
		
		// 注册dialog
		ViewManager.register(DialogSettingIndex.ID, this);
		
		return composite;
	}
	
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		super.createButton(parent, DialogSettingIndex.BUTTON_ID_RUNNING, "Run", true);
		super.createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
		super.createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, true);
	}
	
	@Override
	protected void okPressed() {
		if(this.getDirectories2index().trim().isEmpty()){
			MessageDialog.openWarning(null, "警告", "请选择待索引的目录");
		}
		else if(this.getMaxDirectorySize().trim().isEmpty()){
			MessageDialog.openWarning(null, "警告", "请设置文件夹文件数上限");
		}
		else if(this.getMaxPDFSize().trim().isEmpty()){
			MessageDialog.openWarning(null, "警告", "请设置PDF大小上限");
		}
		else if(this.getMaxTXTSize().trim().isEmpty()){
			MessageDialog.openWarning(null, "警告", "请设置TXT大小上限");
		}
		else{
			SystemConfiguration.setTargetDirectories2Index(this.getDirectories2index());
			SystemConfiguration.setPDFSizeUpbound(this.getMaxPDFSize());
			SystemConfiguration.setTXTSizeUpbound(this.getMaxTXTSize());
			SystemConfiguration.setDirectorySizeUpbound(this.getMaxDirectorySize());
			super.okPressed();
		}
	}
	
	

	@Override
	protected void buttonPressed(int buttonId) {
		if(buttonId == DialogSettingIndex.BUTTON_ID_RUNNING){
			if(this.getDirectories2index().trim().isEmpty()){
				MessageDialog.openWarning(null, "警告", "请选择待索引的目录");
			}
			else if(this.getMaxDirectorySize().trim().isEmpty()){
				MessageDialog.openWarning(null, "警告", "请设置文件夹文件数上限");
			}
			else if(this.getMaxPDFSize().trim().isEmpty()){
				MessageDialog.openWarning(null, "警告", "请设置PDF大小上限");
			}
			else if(this.getMaxTXTSize().trim().isEmpty()){
				MessageDialog.openWarning(null, "警告", "请设置TXT大小上限");
			}
			else{
				SystemConfiguration.setTargetDirectories2Index(this.getDirectories2index());
				SystemConfiguration.setPDFSizeUpbound(this.getMaxPDFSize());
				SystemConfiguration.setTXTSizeUpbound(this.getMaxTXTSize());
				SystemConfiguration.setDirectorySizeUpbound(this.getMaxDirectorySize());
				super.buttonPressed(buttonId);
				
				Job indexJob = new Job("正在索引......"){

					@Override
					protected IStatus run(IProgressMonitor monitor) {
						monitor.beginTask("正在索引......", IProgressMonitor.UNKNOWN);
						boolean toUpdate = SystemConfiguration.getIndexStatus();
						SystemConfiguration.setIndexStatusSuccess(0);
						if(toUpdate){
							Index indexer = new Index();
							indexer.updateIndex();
						}
						else{
							SystemConfiguration.resetIndexDatabaseEnvironment();
							SystemConfiguration.setIndexStatusBuilt(0);
							Index indexer = new Index();
							indexer.createIndex();
						}
						
						MatrixWriter graphBuilder = new MatrixWriter();
						graphBuilder.run();
						PersonalRank pageRankRunner = new PersonalRank();
						pageRankRunner.run();
						return Status.OK_STATUS;
					}
					
				};
//				indexJob.setUser(true);
				indexJob.schedule();
				
			}
		}
	}

	public String getDirectories2index(){
		return this.directories2indexText.getText();
	}
	public String getMaxPDFSize(){
		return this.maxPDFSizeText.getText();
	}
	public String getMaxTXTSize(){
		return this.maxTXTSizeText.getText();
	}
	public String getMaxDirectorySize(){
		return this.maxDirectorySizeText.getText();
	}
	
	public void setDirectories2index(String content){
		this.directories2indexText.setText(content);
	}
	public void setMaxPDFSize(String content){
		this.maxPDFSizeText.setText(content);
	}
	public void setMaxTXTSize(String content){
		this.maxTXTSizeText.setText(content);
	}
	public void setMaxDirectorySize(String content){
		this.maxDirectorySizeText.setText(content);
	}
}
