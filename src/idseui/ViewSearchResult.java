package idseui;

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

import utitilies.Utilities;

public class ViewSearchResult extends ViewPart {

	public static final String ID = Consts.ID_PREFIX + "ViewSearchResult";
	
	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(new FillLayout());
		ScrolledComposite scrolledComposite = new ScrolledComposite(parent, SWT.H_SCROLL | SWT.V_SCROLL);
		Composite composite = new Composite(scrolledComposite, SWT.NONE);
		scrolledComposite.setContent(composite);
		
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 1;
		composite.setLayout(gridLayout);
		
		
		for(int i=0; i<20; i++){
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
			icon.setImage(Utilities.getImage("/icons/docx_mac64.png"));
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
				itemIcon.setImage(Utilities.getImage("/icons/pdf32.png"));
				
				Label itemName = new Label(recommendList, SWT.NONE);
				itemName.setText("���ڹ���Ա��������÷ѵ����˵��.pdf");
			}
		}
		
		composite.setSize(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

}
