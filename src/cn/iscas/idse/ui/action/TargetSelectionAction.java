package cn.iscas.idse.ui.action;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;

/**
 * Ìí¼Ó´ýË÷ÒýÄ¿Â¼
 * @author harry
 *
 */
public class TargetSelectionAction implements SelectionListener {
	
	private List targetList = null;
	
	public TargetSelectionAction(List targetList){
		this.targetList = targetList;
	}
	
	@Override
	public void widgetSelected(SelectionEvent e) {
		// TODO Auto-generated method stub
		Button button = (Button) e.widget;
		if("Ìí¼Ó".equalsIgnoreCase(button.getText())){
			DirectoryDialog dirDialog = new DirectoryDialog(new Shell(), SWT.MULTI);
			dirDialog.setFilterPath("/home/");
			String selectedTarget = dirDialog.open();
			if(this.targetList.indexOf(selectedTarget)==-1){
				this.targetList.add(selectedTarget);
			}
		}
		else if("É¾³ý".equalsIgnoreCase(button.getText())){
			String[] selectedItems = targetList.getSelection();
			for(String item : selectedItems){
				this.targetList.remove(item);
			}
		}
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
