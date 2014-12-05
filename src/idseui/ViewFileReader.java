package idseui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.part.ViewPart;

import utitilies.Utilities;

public class ViewFileReader extends ViewPart {
	
	public static final String ID = Consts.ID_PREFIX + "ViewFileReader";
	
	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(new FillLayout());
		ScrolledComposite scrolledComposite = new ScrolledComposite(parent, SWT.V_SCROLL | SWT.H_SCROLL);
		scrolledComposite.setLayout(new FillLayout());
		Composite composite = new Composite(scrolledComposite, SWT.NONE);
		scrolledComposite.setContent(composite);
		
		composite.setLayout(new FormLayout());
		
		// 图标
		Label icon = new Label(composite, SWT.NONE);
		icon.setImage(Utilities.getImage("/icons/text160.png"));
		FormData iconFormData = new FormData();
		iconFormData.top = new FormAttachment(0, 5);
		iconFormData.left = new FormAttachment(0, 5);
		icon.setLayoutData(iconFormData);
		// 文件名
		Label filename = new Label(composite, SWT.NONE);
		filename.setText("关于协助做好推荐应届优秀高校毕业生到北京市基层培养锻炼工作的通知.txt");
		filename.setFont(new Font(null, "黑体", 14, SWT.BOLD));
		FormData filenameFormData = new FormData();
		filenameFormData.top = new FormAttachment(0, 5);
		filenameFormData.left = new FormAttachment(icon, 5);
		filename.setLayoutData(filenameFormData);
		
		// 日期
		Label filedate = new Label(composite, SWT.NONE);
		filedate.setText("2014年09月04日, 19时28分23秒");
		filedate.setFont(new Font(null, "黑体", 12, SWT.NORMAL));
		FormData filedateFormData = new FormData();
		filedateFormData.top = new FormAttachment(filename, 5);
		filedateFormData.left = new FormAttachment(icon, 5);
		filedate.setLayoutData(filedateFormData);
		
		// 格式
		Label fileformatLabel = new Label(composite, SWT.NONE);
		fileformatLabel.setText("格式：");
		fileformatLabel.setFont(new Font(null, "黑体", 10, SWT.BOLD));
		FormData fileformatLabelFormData = new FormData();
		fileformatLabelFormData.top = new FormAttachment(filedate, 10);
		fileformatLabelFormData.left = new FormAttachment(icon, 40);
		fileformatLabel.setLayoutData(fileformatLabelFormData);
		
		Label fileformatText = new Label(composite, SWT.NONE);
		fileformatText.setText("txt文件");
		fileformatText.setFont(new Font(null, "黑体", 10, SWT.NORMAL));
		FormData fileformatTextFormData = new FormData();
		fileformatTextFormData.top = new FormAttachment(filedate, 10);
		fileformatTextFormData.left = new FormAttachment(fileformatLabel, 5);
		fileformatText.setLayoutData(fileformatTextFormData);
		
		
		// 大小
		Label filesizeLabel = new Label(composite, SWT.NONE);
		filesizeLabel.setText("大小：");
		filesizeLabel.setFont(new Font(null, "黑体", 10, SWT.BOLD));
		FormData filesizeLabelFormData = new FormData();
		filesizeLabelFormData.top = new FormAttachment(fileformatLabel, 5);
		filesizeLabelFormData.left = new FormAttachment(icon, 40);
		filesizeLabel.setLayoutData(filesizeLabelFormData);
		
		Label filesizeText = new Label(composite, SWT.NONE);
		filesizeText.setText("123字节");
		filesizeText.setFont(new Font(null, "黑体", 10, SWT.NORMAL));
		FormData filesizeTextFormData = new FormData();
		filesizeTextFormData.top = new FormAttachment(fileformatLabel, 5);
		filesizeTextFormData.left = new FormAttachment(filesizeLabel, 5);
		filesizeText.setLayoutData(filesizeTextFormData);
		
		
		// 按钮
		Button openFileButton = new Button(composite, SWT.BORDER);
		openFileButton.setText("打开文件");
		FormData openFileButtonFormData = new FormData();
		openFileButtonFormData.top = new FormAttachment(filesizeLabel, 20);
		openFileButtonFormData.left = new FormAttachment(icon, 5);
		openFileButtonFormData.width = 150;
		openFileButton.setLayoutData(openFileButtonFormData);
		
		
		Button openInDirectoryButton = new Button(composite, SWT.BORDER);
		openInDirectoryButton.setText("打开文件");
		FormData openInDirectoryButtonFormData = new FormData();
		openInDirectoryButtonFormData.top = new FormAttachment(filesizeLabel, 20);
		openInDirectoryButtonFormData.left = new FormAttachment(openFileButton, 5);
		openInDirectoryButtonFormData.width = 150;
		openInDirectoryButton.setLayoutData(openInDirectoryButtonFormData);
		
		// 文件内容预览
		Label abstractBox = new Label(composite, SWT.BORDER);
		FormData abstractBoxFormData = new FormData();
		abstractBoxFormData.top = new FormAttachment(icon, 10);
		abstractBoxFormData.left = new FormAttachment(0, 0);
		abstractBoxFormData.width = 5000;
		abstractBoxFormData.height = 2000;
		abstractBox.setLayoutData(abstractBoxFormData);
		abstractBox.setText("XXXXXXXXXXXXXXXXXXXXXXXX\nXXXXXXXXXXXXXXXXXXXX\nXXXX\n");
		abstractBox.setBackground(new Color(null, 255, 255, 255));
		
		
		
		composite.setSize(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

}
