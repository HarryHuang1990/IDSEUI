package cn.iscas.idse.ui.action;

import java.io.File;
import java.io.IOException;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;

import cn.iscas.idse.search.ResultBean;
import cn.iscas.idse.ui.bean.OpenMode;

public class OpenSelectionListener implements SelectionListener {
	
	private ResultBean resultBean;
	private OpenMode mode;
	
	public OpenSelectionListener(ResultBean resultBean, OpenMode mode){
		this.resultBean = resultBean;
		this.mode = mode;
	}
	
	@Override
	public void widgetSelected(SelectionEvent e) {
		File target=null;
		if(this.mode == OpenMode.FILE){
			target = new File(this.resultBean.getDirectory(), this.resultBean.getFile());
		}
		else if(this.mode == OpenMode.DIRECTORY){
			target = new File(this.resultBean.getDirectory());
		}
		
		try {
			java.awt.Desktop.getDesktop().open(target);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
		// TODO Auto-generated method stub

	}

}
