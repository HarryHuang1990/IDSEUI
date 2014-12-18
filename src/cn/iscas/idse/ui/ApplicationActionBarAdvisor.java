package cn.iscas.idse.ui;

import org.eclipse.jface.action.ControlContribution;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import cn.iscas.idse.ui.action.InputBoxListener;
import utitilies.IconSelector;

public class ApplicationActionBarAdvisor extends ActionBarAdvisor {

    public ApplicationActionBarAdvisor(IActionBarConfigurer configurer) {
        super(configurer);
    }

    protected void makeActions(IWorkbenchWindow window) {
    }

    protected void fillMenuBar(IMenuManager menuBar) {
    }

	@Override
	protected void fillCoolBar(ICoolBarManager coolBar) {
		IToolBarManager toolbar = new ToolBarManager(coolBar.getStyle());
		coolBar.add(toolbar);
		
		IContributionItem searchIcon = new ControlContribution(null){
			@Override
			protected Control createControl(Composite parent) {
				Label icon = new Label(parent, SWT.NONE );
				icon.setImage(IconSelector.getImage("/icons/search16.png"));
				return icon;
			}
		};
		
		IContributionItem searchInputText = new ControlContribution(null){
			@Override
			protected Control createControl(Composite parent) {
				Text searchInput = new Text(parent, SWT.NONE | SWT.BORDER);
				searchInput.setMessage("搜索你的本地资源                                                                                                        ");
				searchInput.addModifyListener(new InputBoxListener(searchInput));
				return searchInput;
			}
		};
		
		toolbar.add(searchIcon);
		toolbar.add(searchInputText);
		
	}
    
    
}
