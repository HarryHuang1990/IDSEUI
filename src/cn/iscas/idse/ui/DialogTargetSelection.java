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
		
		this.getShell().setText("ѡ�������Ŀ¼");
		this.getShell().setSize(430, 350);
		
		
		Label tipLabel = new Label(parent, SWT.NONE);
		GridData tipData = new GridData();
		tipData.horizontalSpan = 2;
		tipLabel.setLayoutData(tipData);
		tipLabel.setText("������Ҫ����������Ŀ¼");
		// Ŀ��Ŀ¼
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

		
		// ��Ӱ�ť
		Button addButton = new Button(buttonComposite, SWT.NONE);
		addButton.setText("���");
		GridData addButtonData = new GridData();
		addButtonData.widthHint = 100;
		addButton.setLayoutData(addButtonData);
		addButton.addSelectionListener(new TargetSelectionAction(targetList));
		
		// ɾ����ť
		Button delButton = new Button(buttonComposite, SWT.NONE);
		delButton.setText("ɾ��");
		GridData delButtonData = new GridData();
		delButtonData.widthHint = 100;
		delButton.setLayoutData(delButtonData);
		delButton.addSelectionListener(new TargetSelectionAction(targetList));
	    
		// ע��dialog
		ViewManager.register(DialogTargetSelection.ID, this);
		
	    return parent;
	  }
	
	@Override
	protected void okPressed() {
		// TODO Auto-generated method stub
		String[] items = this.targetList.getItems();
		if(items == null || items.length == 0){
			MessageDialog.openWarning(null, "����", "��ѡ���������Ŀ¼");
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
