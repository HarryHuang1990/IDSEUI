package cn.iscas.idse.ui.action;


import java.util.ArrayList;

import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Text;

import cn.iscas.idse.search.QueryResult;
import cn.iscas.idse.search.Search;
import cn.iscas.idse.ui.ViewManager;
import cn.iscas.idse.ui.ViewSearchResult;

public class InputBoxListener implements ModifyListener {
	private Text inputBox = null;
	private Search search = null;
	
	public InputBoxListener(Text inputBox){
		this.inputBox = inputBox;
		this.search = new Search();
	}

	@Override
	public void modifyText(ModifyEvent e) {
		// TODO Auto-generated method stub
		System.out.println(this.inputBox.getText());
		QueryResult queryResult = this.search.executeSearch(this.inputBox.getText());
		ViewSearchResult viewSearchResult = (ViewSearchResult)ViewManager.getView(ViewSearchResult.ID);
		viewSearchResult.clear();
		if(queryResult != null){
			viewSearchResult.addResultList(queryResult.getResultInBeans());
		}
	}
	
	
	
}
