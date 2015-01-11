package cn.iscas.idse.ui.action;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Shell;

import cn.iscas.idse.config.SystemConfiguration;
import cn.iscas.idse.index.Index;
import cn.iscas.idse.rank.MatrixWriter;
import cn.iscas.idse.rank.PersonalRank;
import cn.iscas.idse.ui.DialogTargetSelection;

public class IndexToolItemHandler implements IHandler {

	@Override
	public void addHandlerListener(IHandlerListener handlerListener) {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		// staring indexing (or re-indexing)
		Job indexJob = new Job("正在索引......"){

			@Override
			protected IStatus run(IProgressMonitor monitor) {
				monitor.beginTask("正在索引......", IProgressMonitor.UNKNOWN);
				boolean toUpdate = SystemConfiguration.getIndexStatus();
				SystemConfiguration.setIndexStatusSuccess(0);
				if(toUpdate){
					Index indexer = new Index();
					indexer.updateIndex();
				}
				else{
					SystemConfiguration.resetIndexDatabaseEnvironment();
					SystemConfiguration.setIndexStatusBuilt(0);
					Index indexer = new Index();
					indexer.createIndex();
				}
				
				MatrixWriter graphBuilder = new MatrixWriter();
				graphBuilder.run();
				PersonalRank pageRankRunner = new PersonalRank();
				pageRankRunner.run();
				return Status.OK_STATUS;
			}
			
		};
//		indexJob.setUser(true);
		indexJob.schedule();
		
		
		return null;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isHandled() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void removeHandlerListener(IHandlerListener handlerListener) {
		// TODO Auto-generated method stub

	}

}
