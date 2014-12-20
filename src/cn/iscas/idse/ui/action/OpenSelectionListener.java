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
		String target = this.resultBean.getDirectory();
		if(this.mode == OpenMode.FILE){
			target = target + File.separator + this.resultBean.getFile();
		}
		
		try {
			/*
			 *  1. 处理文件名中有类似空格等特殊字符的情况，直接构造命令字符串会无法执行，因此使用命令字符串数组来解决这个问题，通过将命令和值分开，可以有效执行命令
			 *  2. 中文乱码的问题，Runtime中输入的字符串编码必须和项目环境编码相同，因此需要将编码转为GBK，命令才能正常执行。
			 */
			String[]cmds = new String[]{"xdg-open", new String(target.getBytes("UTF-8"), "GBK")};
			Runtime.getRuntime().exec(cmds);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
		// TODO Auto-generated method stub

	}

}
