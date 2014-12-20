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
			 *  1. �����ļ����������ƿո�������ַ��������ֱ�ӹ��������ַ������޷�ִ�У����ʹ�������ַ������������������⣬ͨ���������ֵ�ֿ���������Чִ������
			 *  2. ������������⣬Runtime��������ַ�������������Ŀ����������ͬ�������Ҫ������תΪGBK�������������ִ�С�
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
