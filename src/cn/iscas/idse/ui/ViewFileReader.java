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
		
		// ͼ��
		Label icon = new Label(composite, SWT.NONE);
		icon.setImage(IconSelector.getImage(IconSize.SIZE160, FileType.DOC));
		FormData iconFormData = new FormData();
		iconFormData.top = new FormAttachment(0, 5);
		iconFormData.left = new FormAttachment(0, 5);
		icon.setLayoutData(iconFormData);
		// �ļ���
		Label filename = new Label(composite, SWT.NONE);
		filename.setText("����Э�������Ƽ�Ӧ�������У��ҵ���������л�����������������֪ͨ.txt");
		filename.setFont(new Font(null, "����", 14, SWT.BOLD));
		FormData filenameFormData = new FormData();
		filenameFormData.top = new FormAttachment(0, 5);
		filenameFormData.left = new FormAttachment(icon, 5);
		filename.setLayoutData(filenameFormData);
		
		// ʱ��
		Label filedate = new Label(composite, SWT.NONE);
		filedate.setText("2014��09��04��, 19ʱ28��23��");
		filedate.setFont(new Font(null, "����", 12, SWT.NORMAL));
		FormData filedateFormData = new FormData();
		filedateFormData.top = new FormAttachment(filename, 5);
		filedateFormData.left = new FormAttachment(icon, 5);
		filedate.setLayoutData(filedateFormData);
		
		//��ʽ
		Label fileformatLabel = new Label(composite, SWT.NONE);
		fileformatLabel.setText("��ʽ��");
		fileformatLabel.setFont(new Font(null, "����", 10, SWT.BOLD));
		FormData fileformatLabelFormData = new FormData();
		fileformatLabelFormData.top = new FormAttachment(filedate, 10);
		fileformatLabelFormData.left = new FormAttachment(icon, 40);
		fileformatLabel.setLayoutData(fileformatLabelFormData);
		
		Label fileformatText = new Label(composite, SWT.NONE);
		fileformatText.setText("txt�ļ�");
		fileformatText.setFont(new Font(null, "����", 10, SWT.NORMAL));
		FormData fileformatTextFormData = new FormData();
		fileformatTextFormData.top = new FormAttachment(filedate, 10);
		fileformatTextFormData.left = new FormAttachment(fileformatLabel, 5);
		fileformatText.setLayoutData(fileformatTextFormData);
		
		
		// ��С
		Label filesizeLabel = new Label(composite, SWT.NONE);
		filesizeLabel.setText("��С��");
		filesizeLabel.setFont(new Font(null, "����", 10, SWT.BOLD));
		FormData filesizeLabelFormData = new FormData();
		filesizeLabelFormData.top = new FormAttachment(fileformatLabel, 5);
		filesizeLabelFormData.left = new FormAttachment(icon, 40);
		filesizeLabel.setLayoutData(filesizeLabelFormData);
		
		Label filesizeText = new Label(composite, SWT.NONE);
		filesizeText.setText("123�ֽ�");
		filesizeText.setFont(new Font(null, "����", 10, SWT.NORMAL));
		FormData filesizeTextFormData = new FormData();
		filesizeTextFormData.top = new FormAttachment(fileformatLabel, 5);
		filesizeTextFormData.left = new FormAttachment(filesizeLabel, 5);
		filesizeText.setLayoutData(filesizeTextFormData);
		
		
		// ��ť
		Button openFileButton = new Button(composite, SWT.BORDER);
		openFileButton.setText("���ļ�");
		FormData openFileButtonFormData = new FormData();
		openFileButtonFormData.top = new FormAttachment(filesizeLabel, 20);
		openFileButtonFormData.left = new FormAttachment(icon, 5);
		openFileButtonFormData.width = 150;
		openFileButton.setLayoutData(openFileButtonFormData);
		
		
		Button openInDirectoryButton = new Button(composite, SWT.BORDER);
		openInDirectoryButton.setText("���ļ����д�");
		FormData openInDirectoryButtonFormData = new FormData();
		openInDirectoryButtonFormData.top = new FormAttachment(filesizeLabel, 20);
		openInDirectoryButtonFormData.left = new FormAttachment(openFileButton, 5);
		openInDirectoryButtonFormData.width = 150;
		openInDirectoryButton.setLayoutData(openInDirectoryButtonFormData);
		
		// ժҪ
		Label abstractBox = new Label(composite, SWT.BORDER);
		FormData abstractBoxFormData = new FormData();
		abstractBoxFormData.top = new FormAttachment(icon, 10);
		abstractBoxFormData.left = new FormAttachment(0, 0);
		abstractBoxFormData.width = 5000;
		abstractBoxFormData.height = 2000;
		abstractBox.setLayoutData(abstractBoxFormData);
		abstractBox.setText("Experiments as Research Validation - Have We Gone too Far?\nJeffrey D. Ullman, July 9, 2013\nI recently submitted a paper to VLDB, and when I got the reviews back, I noticed that\nthe review form now has a question referees are required to answer, about whether the\nexperiments were well carried out, with choices like ��believable�� and ��not believable.��\nThe reviewers had a bit of trouble with that question, because my paper had no\nexperiments; it was a paper about computational complexity of MapReduce algorithms.\nTwo of the reviewers said the nonexistent experiments were not believable, which is\nwrong �C you have to see something to disbelieve it.\n");
		abstractBox.setBackground(new Color(null, 255, 255, 255));
		
		composite.setSize(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
		// ע��view
		ViewManager.register(ViewFileReader.ID, this);
	}
	/**
	 * �����ͼ
	 */
	public void clear(){
		this.composite.dispose();
	}
	
	/**
	 *  �����ļ�����
	 * @param bean �洢�ļ���Ϣblock
	 */
	public void loadAbstract(ResultBean bean){
		composite = new Composite(scrolledComposite, SWT.NONE);
		scrolledComposite.setContent(composite);
		
		composite.setLayout(new FormLayout());
		// ����ͼ��
		Label icon = new Label(composite, SWT.NONE);
		icon.setImage(IconSelector.getImage(bean.getFile(), IconSize.SIZE160));
		FormData iconFormData = new FormData();
		iconFormData.top = new FormAttachment(0, 5);
		iconFormData.left = new FormAttachment(0, 5);
		icon.setLayoutData(iconFormData);
		// �����ļ���
		Label filename = new Label(composite, SWT.NONE);
		filename.setText(bean.getFile());
		filename.setFont(new Font(null, "����", 14, SWT.BOLD));
		FormData filenameFormData = new FormData();
		filenameFormData.top = new FormAttachment(0, 5);
		filenameFormData.left = new FormAttachment(icon, 5);
		filename.setLayoutData(filenameFormData);
		
		// ��������
		File file = new File(bean.getDirectory(), bean.getFile());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy��MM��dd�գ� HHʱmm��ss��");
		
		Label filedate = new Label(composite, SWT.NONE);
		filedate.setText(sdf.format(new Date(file.lastModified())));
		filedate.setFont(new Font(null, "����", 12, SWT.NORMAL));
		FormData filedateFormData = new FormData();
		filedateFormData.top = new FormAttachment(filename, 5);
		filedateFormData.left = new FormAttachment(icon, 5);
		filedate.setLayoutData(filedateFormData);
		
		// �����ļ�����
		Label fileformatLabel = new Label(composite, SWT.NONE);
		fileformatLabel.setText("��ʽ��");
		fileformatLabel.setFont(new Font(null, "����", 10, SWT.BOLD));
		FormData fileformatLabelFormData = new FormData();
		fileformatLabelFormData.top = new FormAttachment(filedate, 10);
		fileformatLabelFormData.left = new FormAttachment(icon, 40);
		fileformatLabel.setLayoutData(fileformatLabelFormData);
		
		Label fileformatText = new Label(composite, SWT.NONE);
		fileformatText.setText(IconSelector.getFileTypeInString(bean.getFile()) + "�ļ�");
		fileformatText.setFont(new Font(null, "����", 10, SWT.NORMAL));
		FormData fileformatTextFormData = new FormData();
		fileformatTextFormData.top = new FormAttachment(filedate, 10);
		fileformatTextFormData.left = new FormAttachment(fileformatLabel, 5);
		fileformatText.setLayoutData(fileformatTextFormData);
		
		// �����ļ���С
		Label filesizeLabel = new Label(composite, SWT.NONE);
		filesizeLabel.setText("��С��");
		filesizeLabel.setFont(new Font(null, "����", 10, SWT.BOLD));
		FormData filesizeLabelFormData = new FormData();
		filesizeLabelFormData.top = new FormAttachment(fileformatLabel, 5);
		filesizeLabelFormData.left = new FormAttachment(icon, 40);
		filesizeLabel.setLayoutData(filesizeLabelFormData);
		
		Label filesizeText = new Label(composite, SWT.NONE);
		filesizeText.setText(file.length() + "�ֽ�");	//byte
		filesizeText.setFont(new Font(null, "����", 10, SWT.NORMAL));
		FormData filesizeTextFormData = new FormData();
		filesizeTextFormData.top = new FormAttachment(fileformatLabel, 5);
		filesizeTextFormData.left = new FormAttachment(filesizeLabel, 5);
		filesizeText.setLayoutData(filesizeTextFormData);
		
		// ���ء����ļ����¼�
		Button openFileButton = new Button(composite, SWT.BORDER);
		openFileButton.setText("���ļ�");
		FormData openFileButtonFormData = new FormData();
		openFileButtonFormData.top = new FormAttachment(filesizeLabel, 20);
		openFileButtonFormData.left = new FormAttachment(icon, 5);
		openFileButtonFormData.width = 150;
		openFileButton.setLayoutData(openFileButtonFormData);
		openFileButton.addSelectionListener(new OpenSelectionListener(bean, OpenMode.FILE));
		
		// ���ء����ļ����д򿪡��¼�
		Button openInDirectoryButton = new Button(composite, SWT.BORDER);
		openInDirectoryButton.setText("���ļ����д�");
		FormData openInDirectoryButtonFormData = new FormData();
		openInDirectoryButtonFormData.top = new FormAttachment(filesizeLabel, 20);
		openInDirectoryButtonFormData.left = new FormAttachment(openFileButton, 5);
		openInDirectoryButtonFormData.width = 150;
		openInDirectoryButton.setLayoutData(openInDirectoryButtonFormData);
		openInDirectoryButton.addSelectionListener(new OpenSelectionListener(bean, OpenMode.DIRECTORY));
		
		// �����ļ�ժҪ��2000�ַ���
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
