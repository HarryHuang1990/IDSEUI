package cn.iscas.idse.ui;

import org.eclipse.jface.dialogs.IconAndMessageDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;

import cn.iscas.idse.config.SystemConfiguration;
import cn.iscas.idse.ui.action.TargetSelectionAction;

public class DialogTargetSelection extends IconAndMessageDialog {
	public static final String ID = Consts.ID_PREFIX + "DialogTargetSelection";
	
	private List targetList;
	
	public DialogTargetSelection(Shell parentShell) {
		super(parentShell);
		// TODO Auto-generated constructor stub
	}
	
	protected Control createDialogArea(Composite parent) { 
		createMessageArea(parent);
		
		this.getShell().setText("选择待索引目录");
		this.getShell().setSize(430, 350);
		
		
		Label tipLabel = new Label(parent, SWT.NONE);
		GridData tipData = new GridData();
		tipData.horizontalSpan = 2;
		tipLabel.setLayoutData(tipData);
		tipLabel.setText("管理需要建立索引的目录");
		// 目标目录
		targetList = new List(parent, SWT.V_SCROLL  | SWT.H_SCROLL | SWT.MULTI | SWT.BORDER);
		GridData targetListData = new GridData();
		targetList.setLayoutData(targetListData);
		targetListData.heightHint = 200;
		targetListData.widthHint = 250;
		for(String targetDir : SystemConfiguration.targetDirectories){
			targetList.add(targetDir);
		}
		
		Composite buttonComposite = new Composite(parent, SWT.NONE );
		GridLayout buttonCompositeLayout = new GridLayout();
		buttonCompositeLayout.numColumns = 1;
		buttonComposite.setLayout(buttonCompositeLayout);
		
		GridData buttonCompositeData = new GridData();
		buttonCompositeData.heightHint = 220;
		buttonComposite.setLayoutData(buttonCompositeData);

		
		// 添加按钮
		Button addButton = new Button(buttonComposite, SWT.NONE);
		addButton.setText("添加");
		GridData addButtonData = new GridData();
		addButtonData.widthHint = 100;
		addButton.setLayoutData(addButtonData);
		addButton.addSelectionListener(new TargetSelectionAction(targetList));
		
		// 删除按钮
		Button delButton = new Button(buttonComposite, SWT.NONE);
		delButton.setText("删除");
		GridData delButtonData = new GridData();
		delButtonData.widthHint = 100;
		delButton.setLayoutData(delButtonData);
		delButton.addSelectionListener(new TargetSelectionAction(targetList));
	    
		// 注册dialog
		ViewManager.register(DialogTargetSelection.ID, this);
		
	    return parent;
	  }
	
	@Override
	protected void okPressed() {
		// TODO Auto-generated method stub
		String[] items = this.targetList.getItems();
		if(items == null || items.length == 0){
			MessageDialog.openWarning(null, "警告", "请选择待索引的目录");
		}
		else{
			SystemConfiguration.setTargetDirectories2Index(items);
			((DialogSettingIndex)ViewManager.getView(DialogSettingIndex.ID)).setDirectories2index(SystemConfiguration.targetDirectoryValues);
			super.okPressed();
		}
	}

	@Override
	protected Image getImage() {
		// TODO Auto-generated method stub
		return null;
	}

}
