package cn.iscas.idse.ui.action;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Text;

import cn.iscas.idse.ui.DialogTargetSelection;

public class OpenTargetSelectionAction implements SelectionListener {

	private Text targetDirectories = null;
	
	public OpenTargetSelectionAction(Text targetDirectories){
		this.targetDirectories = targetDirectories;
	}
	
	@Override
	public void widgetSelected(SelectionEvent e) {
		// TODO Auto-generated method stub
		DialogTargetSelection dialog = new DialogTargetSelection(null);
		dialog.open();
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
		// TODO Auto-generated method stub

	}

}
