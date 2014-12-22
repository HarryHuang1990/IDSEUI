package cn.iscas.idse.ui.action;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class UserLogDirectoryAction implements SelectionListener {

	private Text userLogDirectory = null;
	
	public UserLogDirectoryAction(Text userLogDirectory){
		this.userLogDirectory = userLogDirectory;
	}
	
	@Override
	public void widgetSelected(SelectionEvent e) {
		// TODO Auto-generated method stub
		DirectoryDialog dirDialog = new DirectoryDialog(new Shell(), SWT.MULTI);
		dirDialog.setFilterPath("/home/");
		String userLog = dirDialog.open();
		if(userLog != null){
			this.userLogDirectory.setText(userLog);
		}
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
		// TODO Auto-generated method stub

	}

}
