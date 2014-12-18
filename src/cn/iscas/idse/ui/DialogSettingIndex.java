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

public class DialogSettingIndex extends TitleAreaDialog {
	
	private Image image = IconSelector.getImage(IconSize.SIZE80, IconType.DOC);
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
		
		Text directories2indexText = new Text(directories2indexComposite, SWT.NONE | SWT.BORDER);
		FormData directories2indexTextData = new FormData();
		directories2indexTextData.top = new FormAttachment(0, DialogSettingIndex.GROUP_PADDING);
		directories2indexTextData.left = new FormAttachment(0, DialogSettingIndex.INDENT_SPACING);
		directories2indexTextData.right = new FormAttachment(100, -DialogSettingIndex.GROUP_PADDING);
		directories2indexText.setLayoutData(directories2indexTextData);
		
		// max size of PDF to index (KB)
		Composite maxPDFSizeComposite = new Composite(composite, SWT.NONE);
		maxPDFSizeComposite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		maxPDFSizeComposite.setLayout(new FormLayout());
		
		Label maxPDFSizeLabel = new Label(maxPDFSizeComposite, SWT.NONE);
		maxPDFSizeLabel.setText("PDF大小上限(KB)：");
		FormData maxPDFSizeLabelData = new FormData();
		maxPDFSizeLabelData.top = new FormAttachment(0, DialogSettingIndex.ADJACENT_SPACING);
		maxPDFSizeLabelData.left = new FormAttachment(0, DialogSettingIndex.GROUP_PADDING	);
		maxPDFSizeLabel.setLayoutData(maxPDFSizeLabelData);
		
		Text maxPDFSizeText = new Text(maxPDFSizeComposite, SWT.NONE | SWT.BORDER);
		FormData maxPDFSizeTextData = new FormData();
		maxPDFSizeTextData.top = new FormAttachment(0, DialogSettingIndex.ADJACENT_SPACING);
		maxPDFSizeTextData.left = new FormAttachment(0, DialogSettingIndex.INDENT_SPACING);
		maxPDFSizeTextData.right = new FormAttachment(100, -DialogSettingIndex.GROUP_PADDING);
		maxPDFSizeText.setLayoutData(maxPDFSizeTextData);
		
		// max size of Txt to index (KB)
		Composite maxTXTSizeComposite = new Composite(composite, SWT.NONE);
		maxTXTSizeComposite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		maxTXTSizeComposite.setLayout(new FormLayout());
		
		Label maxTXTSizeLabel = new Label(maxTXTSizeComposite, SWT.NONE);
		maxTXTSizeLabel.setText("TXT大小上限(KB)：");
		FormData maxTXTSizeLabelData = new FormData();
		maxTXTSizeLabelData.top = new FormAttachment(0, DialogSettingIndex.ADJACENT_SPACING);
		maxTXTSizeLabelData.left = new FormAttachment(0, DialogSettingIndex.GROUP_PADDING	);
		maxTXTSizeLabel.setLayoutData(maxTXTSizeLabelData);
		
		Text maxTXTSizeText = new Text(maxTXTSizeComposite, SWT.NONE | SWT.BORDER);
		FormData maxTXTSizeTextData = new FormData();
		maxTXTSizeTextData.top = new FormAttachment(0, DialogSettingIndex.ADJACENT_SPACING);
		maxTXTSizeTextData.left = new FormAttachment(0, DialogSettingIndex.INDENT_SPACING);
		maxTXTSizeTextData.right = new FormAttachment(100, -DialogSettingIndex.GROUP_PADDING);
		maxTXTSizeText.setLayoutData(maxTXTSizeTextData);
		
		// max size of directories to index (files)
		Composite maxDirectorySizeComposite = new Composite(composite, SWT.NONE);
		maxDirectorySizeComposite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		maxDirectorySizeComposite.setLayout(new FormLayout());
		
		Label maxDirectorySizeLabel = new Label(maxDirectorySizeComposite, SWT.NONE);
		maxDirectorySizeLabel.setText("文件夹文件数上限：");
		FormData maxDirectorySizeLabelData = new FormData();
		maxDirectorySizeLabelData.top = new FormAttachment(0, DialogSettingIndex.ADJACENT_SPACING);
		maxDirectorySizeLabelData.left = new FormAttachment(0, DialogSettingIndex.GROUP_PADDING	);
		maxDirectorySizeLabel.setLayoutData(maxDirectorySizeLabelData);
		
		Text maxDirectorySizeText = new Text(maxDirectorySizeComposite, SWT.NONE | SWT.BORDER);
		FormData maxDirectorySizeTextData = new FormData();
		maxDirectorySizeTextData.top = new FormAttachment(0, DialogSettingIndex.ADJACENT_SPACING);
		maxDirectorySizeTextData.left = new FormAttachment(0, DialogSettingIndex.INDENT_SPACING);
		maxDirectorySizeTextData.right = new FormAttachment(100, -DialogSettingIndex.GROUP_PADDING);
		maxDirectorySizeText.setLayoutData(maxDirectorySizeTextData);
		
		
		return composite;
	}

	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		super.createButton(parent, DialogSettingIndex.BUTTON_ID_RUNNING, "Run", true);
		super.createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
		super.createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, true);
	}
	
	
}
