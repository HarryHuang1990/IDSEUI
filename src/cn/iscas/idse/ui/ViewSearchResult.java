package cn.iscas.idse.ui;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.part.ViewPart;

import cn.iscas.idse.search.ResultBean;
import cn.iscas.idse.ui.action.OpenSelectionListener;
import cn.iscas.idse.ui.action.ResultReviewListener;
import cn.iscas.idse.ui.bean.IconSize;
import cn.iscas.idse.ui.bean.FileType;
import cn.iscas.idse.ui.bean.OpenMode;
import utitilies.IconSelector;

public class ViewSearchResult extends ViewPart {

	public static final String ID = Consts.ID_PREFIX + "ViewSearchResult";
	private Composite composite = null;
	private ScrolledComposite scrolledComposite = null;
	
	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(new FillLayout());
		this.scrolledComposite = new ScrolledComposite(parent, SWT.H_SCROLL | SWT.V_SCROLL);
		this.composite = new Composite(scrolledComposite, SWT.NONE);
		scrolledComposite.setContent(composite);
		
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 1;
		composite.setLayout(gridLayout);
		
		for(int i=0; i<2; i++){
			Label separator = new Label(composite, SWT.SEPARATOR | SWT.HORIZONTAL);
			GridData separatorGridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
			separatorGridData.horizontalSpan = 6;
			separator.setLayoutData(separatorGridData);
			
			Composite block = new Composite(composite, SWT.NONE);
			GridLayout blockLayout = new GridLayout();
			blockLayout.numColumns = 2;
			block.setLayout(blockLayout);
			//top-left: ͼƬ
			Label icon = new Label(block, SWT.NONE);
			icon.setImage(IconSelector.getImage(IconSize.SIZE64, FileType.DOC));
//			icon.addMouseListener();
			
			//top-right: �ļ��� + ��ť
			Composite info = new Composite(block, SWT.NONE);
			GridLayout infoLayout = new GridLayout();
			infoLayout.numColumns = 1;
			info.setLayout(infoLayout);
			
			Label filename = new Label(info, SWT.NONE);
			filename.setText("���岿���ڴ����ڼ�Ա���żټ����幺���Ʊ��֪ͨ.doc");
			filename.setFont(new Font(null, "����", 12, SWT.NORMAL));
			
			Composite buttonBar = new Composite(info, SWT.NONE);
			GridLayout buttonBarLayout = new GridLayout();
			buttonBarLayout.numColumns = 2;
			buttonBar.setLayout(buttonBarLayout);
			
			GridData buttonGridData = new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1);
			buttonGridData.widthHint = 150;
			Button openFileButton = new Button(buttonBar, SWT.BORDER);
			openFileButton.setText("���ļ�");
			openFileButton.setLayoutData(buttonGridData);
			Button openInDirectoryButton = new Button(buttonBar, SWT.BORDER);
			openInDirectoryButton.setText("���ļ�������ʾ");
			openInDirectoryButton.setLayoutData(buttonGridData);
			
			//bottom-left: �հ����
			Label label = new Label(block, SWT.NONE);
			
			//bottom-right: �Ƽ��б�
			Composite recommendList = new Composite(block, SWT.NONE);
			GridLayout recommendListLayout = new GridLayout();
			recommendListLayout.numColumns = 2;
			recommendList.setLayout(recommendListLayout);
			
			for(int k=0; k<5; k++){
				Label itemIcon = new Label(recommendList, SWT.NONE);
				itemIcon.setImage(IconSelector.getImage(IconSize.SIZE32, FileType.DOC));
				
				Label itemName = new Label(recommendList, SWT.NONE);
				itemName.setText("���ڹ���Ա��������÷ѵ����˵��.pdf");
			}
		}
		
		composite.setSize(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
		// ע��view
		ViewManager.register(ViewSearchResult.ID, this);
	}
	
	/**
	 * �������������
	 */
	public void clear(){
		this.composite.dispose();
		System.out.println(this.composite);
	}
	
	/**
	 * ������������б�����������
	 * @param resultList	��������б�
	 */
	public void addResultList(ArrayList<ResultBean> resultList){
		this.composite = new Composite(this.scrolledComposite, SWT.NONE);
		this.scrolledComposite.setContent(this.composite);
		
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 1;
		this.composite.setLayout(gridLayout);
		
		for(ResultBean item : resultList){
			this.addResultItem(item);
		}
		
		this.composite.setSize(this.composite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
		this.composite.redraw();
		this.composite.update();
	}
	/**
	 * ���ص������������
	 * @param bean �����������
	 */
	public void addResultItem(ResultBean bean){
		
		Label separator = new Label(this.composite, SWT.SEPARATOR | SWT.HORIZONTAL);
		GridData separatorGridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		separatorGridData.horizontalSpan = 6;
		separator.setLayoutData(separatorGridData);
		
		Composite block = new Composite(this.composite, SWT.NONE);
		GridLayout blockLayout = new GridLayout();
		blockLayout.numColumns = 2;
		block.setLayout(blockLayout);
		//top-left: ͼƬ
		Label icon = new Label(block, SWT.NONE);
		icon.setImage(IconSelector.getImage(bean.getFile(), IconSize.SIZE64));
		icon.addMouseListener(new ResultReviewListener(bean));
		//top-right: �ļ��� + ��ť
		Composite info = new Composite(block, SWT.NONE);
		GridLayout infoLayout = new GridLayout();
		infoLayout.numColumns = 1;
		info.setLayout(infoLayout);
		
		Label filename = new Label(info, SWT.NONE);
		filename.setText(bean.getFile());
		filename.setFont(new Font(null, "����", 12, SWT.NORMAL));
		filename.addMouseListener(new ResultReviewListener(bean));
		
		Composite buttonBar = new Composite(info, SWT.NONE);
		GridLayout buttonBarLayout = new GridLayout();
		buttonBarLayout.numColumns = 2;
		buttonBar.setLayout(buttonBarLayout);
		
		GridData buttonGridData = new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1);
		buttonGridData.widthHint = 150;
		Button openFileButton = new Button(buttonBar, SWT.BORDER);
		openFileButton.setText("���ļ�");
		openFileButton.setLayoutData(buttonGridData);
		openFileButton.addSelectionListener(new OpenSelectionListener(bean, OpenMode.FILE));
		
		
		Button openInDirectoryButton = new Button(buttonBar, SWT.BORDER);
		openInDirectoryButton.setText("���ļ�������ʾ");
		openInDirectoryButton.setLayoutData(buttonGridData);
		openInDirectoryButton.addSelectionListener(new OpenSelectionListener(bean, OpenMode.DIRECTORY));
		
		//bottom-left: �հ����
		Label label = new Label(block, SWT.NONE);
		
		//bottom-right: �Ƽ��б�
		Composite recommendList = new Composite(block, SWT.NONE);
		GridLayout recommendListLayout = new GridLayout();
		recommendListLayout.numColumns = 2;
		recommendList.setLayout(recommendListLayout);
		
		for(ResultBean recBean : bean.getRecommends()){
			Label itemIcon = new Label(recommendList, SWT.NONE);
			itemIcon.setImage(IconSelector.getImage(recBean.getFile(), IconSize.SIZE32));
			itemIcon.addMouseListener(new ResultReviewListener(recBean));
			
			Label itemName = new Label(recommendList, SWT.NONE);
			itemName.setText(recBean.getFile());
			itemName.addMouseListener(new ResultReviewListener(recBean));
		}
		
	}
	

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

}
