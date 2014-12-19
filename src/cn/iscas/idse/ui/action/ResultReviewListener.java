package cn.iscas.idse.ui.action;

import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;

import cn.iscas.idse.search.ResultBean;
import cn.iscas.idse.ui.ViewFileReader;
import cn.iscas.idse.ui.ViewManager;

public class ResultReviewListener implements MouseListener {
	
	private ResultBean resultBean;
	
	public ResultReviewListener(ResultBean resultBean){
		this.resultBean = resultBean;
	}
	
	@Override
	public void mouseDoubleClick(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDown(MouseEvent e) {
		// TODO Auto-generated method stub
		ViewFileReader viewFileReader = (ViewFileReader)ViewManager.getView(ViewFileReader.ID);
		viewFileReader.loadAbstract(resultBean);
	}

	@Override
	public void mouseUp(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
