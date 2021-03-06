package cn.iscas.idse.ui;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

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

import cn.iscas.idse.search.ResultBean;
import cn.iscas.idse.ui.action.OpenSelectionListener;
import cn.iscas.idse.ui.bean.IconSize;
import cn.iscas.idse.ui.bean.FileType;
import cn.iscas.idse.ui.bean.OpenMode;
import utitilies.IconSelector;

public class ViewFileReader extends ViewPart {
	
	public static final String ID = Consts.ID_PREFIX + "ViewFileReader";
	
	private ScrolledComposite scrolledComposite = null;
	private Composite composite = null;
	
	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(new FillLayout());
		scrolledComposite = new ScrolledComposite(parent, SWT.V_SCROLL | SWT.H_SCROLL);
		scrolledComposite.setLayout(new FillLayout());
		composite = new Composite(scrolledComposite, SWT.NONE);
		scrolledComposite.setContent(composite);
		
		composite.setLayout(new FormLayout());
		
		// 图标
		Label icon = new Label(composite, SWT.NONE);
		icon.setImage(IconSelector.getImage(IconSize.SIZE160, FileType.DOC));
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
		
		// 时间
		Label filedate = new Label(composite, SWT.NONE);
		filedate.setText("2014年09月04日, 19时28分23秒");
		filedate.setFont(new Font(null, "黑体", 12, SWT.NORMAL));
		FormData filedateFormData = new FormData();
		filedateFormData.top = new FormAttachment(filename, 5);
		filedateFormData.left = new FormAttachment(icon, 5);
		filedate.setLayoutData(filedateFormData);
		
		//格式
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
		openInDirectoryButton.setText("在文件夹中打开");
		FormData openInDirectoryButtonFormData = new FormData();
		openInDirectoryButtonFormData.top = new FormAttachment(filesizeLabel, 20);
		openInDirectoryButtonFormData.left = new FormAttachment(openFileButton, 5);
		openInDirectoryButtonFormData.width = 150;
		openInDirectoryButton.setLayoutData(openInDirectoryButtonFormData);
		
		// 摘要
		Label abstractBox = new Label(composite, SWT.BORDER);
		FormData abstractBoxFormData = new FormData();
		abstractBoxFormData.top = new FormAttachment(icon, 10);
		abstractBoxFormData.left = new FormAttachment(0, 0);
		abstractBoxFormData.width = 5000;
		abstractBoxFormData.height = 2000;
		abstractBox.setLayoutData(abstractBoxFormData);
		abstractBox.setText("Experiments as Research Validation - Have We Gone too Far?\nJeffrey D. Ullman, July 9, 2013\nI recently submitted a paper to VLDB, and when I got the reviews back, I noticed that\nthe review form now has a question referees are required to answer, about whether the\nexperiments were well carried out, with choices like “believable” and “not believable.”\nThe reviewers had a bit of trouble with that question, because my paper had no\nexperiments; it was a paper about computational complexity of MapReduce algorithms.\nTwo of the reviewers said the nonexistent experiments were not believable, which is\nwrong – you have to see something to disbelieve it.\n");
		abstractBox.setBackground(new Color(null, 255, 255, 255));
		
		composite.setSize(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
		// 注册view
		ViewManager.register(ViewFileReader.ID, this);
	}
	/**
	 * 清空视图
	 */
	public void clear(){
		this.composite.dispose();
	}
	
	/**
	 *  加载文件内容
	 * @param bean 存储文件信息block
	 */
	public void loadAbstract(ResultBean bean){
		composite = new Composite(scrolledComposite, SWT.NONE);
		scrolledComposite.setContent(composite);
		
		composite.setLayout(new FormLayout());
		// 加载图标
		Label icon = new Label(composite, SWT.NONE);
		icon.setImage(IconSelector.getImage(bean.getFile(), IconSize.SIZE160));
		FormData iconFormData = new FormData();
		iconFormData.top = new FormAttachment(0, 5);
		iconFormData.left = new FormAttachment(0, 5);
		icon.setLayoutData(iconFormData);
		// 加载文件名
		Label filename = new Label(composite, SWT.NONE);
		filename.setText(bean.getFile());
		filename.setFont(new Font(null, "黑体", 14, SWT.BOLD));
		FormData filenameFormData = new FormData();
		filenameFormData.top = new FormAttachment(0, 5);
		filenameFormData.left = new FormAttachment(icon, 5);
		filename.setLayoutData(filenameFormData);
		
		// 加载日期
		File file = new File(bean.getDirectory(), bean.getFile());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日， HH时mm分ss秒");
		
		Label filedate = new Label(composite, SWT.NONE);
		filedate.setText(sdf.format(new Date(file.lastModified())));
		filedate.setFont(new Font(null, "黑体", 12, SWT.NORMAL));
		FormData filedateFormData = new FormData();
		filedateFormData.top = new FormAttachment(filename, 5);
		filedateFormData.left = new FormAttachment(icon, 5);
		filedate.setLayoutData(filedateFormData);
		
		// 加载文件类型
		Label fileformatLabel = new Label(composite, SWT.NONE);
		fileformatLabel.setText("格式：");
		fileformatLabel.setFont(new Font(null, "黑体", 10, SWT.BOLD));
		FormData fileformatLabelFormData = new FormData();
		fileformatLabelFormData.top = new FormAttachment(filedate, 10);
		fileformatLabelFormData.left = new FormAttachment(icon, 40);
		fileformatLabel.setLayoutData(fileformatLabelFormData);
		
		Label fileformatText = new Label(composite, SWT.NONE);
		fileformatText.setText(IconSelector.getFileTypeInString(bean.getFile()) + "文件");
		fileformatText.setFont(new Font(null, "黑体", 10, SWT.NORMAL));
		FormData fileformatTextFormData = new FormData();
		fileformatTextFormData.top = new FormAttachment(filedate, 10);
		fileformatTextFormData.left = new FormAttachment(fileformatLabel, 5);
		fileformatText.setLayoutData(fileformatTextFormData);
		
		// 加载文件大小
		Label filesizeLabel = new Label(composite, SWT.NONE);
		filesizeLabel.setText("大小：");
		filesizeLabel.setFont(new Font(null, "黑体", 10, SWT.BOLD));
		FormData filesizeLabelFormData = new FormData();
		filesizeLabelFormData.top = new FormAttachment(fileformatLabel, 5);
		filesizeLabelFormData.left = new FormAttachment(icon, 40);
		filesizeLabel.setLayoutData(filesizeLabelFormData);
		
		Label filesizeText = new Label(composite, SWT.NONE);
		filesizeText.setText(file.length() + "字节");	//byte
		filesizeText.setFont(new Font(null, "黑体", 10, SWT.NORMAL));
		FormData filesizeTextFormData = new FormData();
		filesizeTextFormData.top = new FormAttachment(fileformatLabel, 5);
		filesizeTextFormData.left = new FormAttachment(filesizeLabel, 5);
		filesizeText.setLayoutData(filesizeTextFormData);
		
		// 加载“打开文件”事件
		Button openFileButton = new Button(composite, SWT.BORDER);
		openFileButton.setText("打开文件");
		FormData openFileButtonFormData = new FormData();
		openFileButtonFormData.top = new FormAttachment(filesizeLabel, 20);
		openFileButtonFormData.left = new FormAttachment(icon, 5);
		openFileButtonFormData.width = 150;
		openFileButton.setLayoutData(openFileButtonFormData);
		openFileButton.addSelectionListener(new OpenSelectionListener(bean, OpenMode.FILE));
		
		// 加载“在文件夹中打开”事件
		Button openInDirectoryButton = new Button(composite, SWT.BORDER);
		openInDirectoryButton.setText("在文件夹中打开");
		FormData openInDirectoryButtonFormData = new FormData();
		openInDirectoryButtonFormData.top = new FormAttachment(filesizeLabel, 20);
		openInDirectoryButtonFormData.left = new FormAttachment(openFileButton, 5);
		openInDirectoryButtonFormData.width = 150;
		openInDirectoryButton.setLayoutData(openInDirectoryButtonFormData);
		openInDirectoryButton.addSelectionListener(new OpenSelectionListener(bean, OpenMode.DIRECTORY));
		
		// 加载文件摘要（2000字符）
		Label abstractBox = new Label(composite, SWT.BORDER | SWT.WRAP);
		FormData abstractBoxFormData = new FormData();
		abstractBoxFormData.top = new FormAttachment(icon, 10);
		abstractBoxFormData.left = new FormAttachment(0, 0);
		abstractBoxFormData.width = 3000;
		abstractBoxFormData.height = 2000;
		abstractBox.setLayoutData(abstractBoxFormData);
		abstractBox.setText(IconSelector.getFileAbstract(bean.getDirectory(), bean.getFile()));
		abstractBox.setBackground(new Color(null, 255, 255, 255));
		
		
		this.composite.setSize(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		this.composite.redraw();
		this.composite.update();
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

}
