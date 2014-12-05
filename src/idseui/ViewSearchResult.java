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
			//top-left: 图片
			Label icon = new Label(block, SWT.NONE);
			icon.setImage(Utilities.getImage("/icons/docx_mac64.png"));
			//top-right: 文件名 + 按钮
			Composite info = new Composite(block, SWT.NONE);
			GridLayout infoLayout = new GridLayout();
			infoLayout.numColumns = 1;
			info.setLayout(infoLayout);
			
			Label filename = new Label(info, SWT.NONE);
			filename.setText("总体部关于春节期间员工放假及集体购买火车票的通知.doc");
			filename.setFont(new Font(null, "黑体", 12, SWT.NORMAL));
			
			Composite buttonBar = new Composite(info, SWT.NONE);
			GridLayout buttonBarLayout = new GridLayout();
			buttonBarLayout.numColumns = 2;
			buttonBar.setLayout(buttonBarLayout);
			
			GridData buttonGridData = new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1);
			buttonGridData.widthHint = 150;
			Button openFileButton = new Button(buttonBar, SWT.BORDER);
			openFileButton.setText("打开文件");
			openFileButton.setLayoutData(buttonGridData);
			Button openInDirectoryButton = new Button(buttonBar, SWT.BORDER);
			openInDirectoryButton.setText("在文件夹中显示");
			openInDirectoryButton.setLayoutData(buttonGridData);
			
			//bottom-left: 空白填充
			Label label = new Label(block, SWT.NONE);
			
			//bottom-right: 推荐列表
			Composite recommendList = new Composite(block, SWT.NONE);
			GridLayout recommendListLayout = new GridLayout();
			recommendListLayout.numColumns = 2;
			recommendList.setLayout(recommendListLayout);
			
			for(int k=0; k<5; k++){
				Label itemIcon = new Label(recommendList, SWT.NONE);
				itemIcon.setImage(Utilities.getImage("/icons/pdf32.png"));
				
				Label itemName = new Label(recommendList, SWT.NONE);
				itemName.setText("关于公务员出差报销差旅费的相关说明.pdf");
			}
		}
		
		
		
		
		composite.setSize(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

}
