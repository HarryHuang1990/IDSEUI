package cn.iscas.idse.storage.entity.accessor;

import cn.iscas.idse.storage.entity.PageRankGraph;

import com.sleepycat.persist.EntityStore;
import com.sleepycat.persist.PrimaryIndex;

/**
 * <p>
 * Berkeley DB DPL API organize primary and secondary indexes using a specialize data accessor class.
 * the main reason for the accessor class to exist is to provide convenient access to all the indexes
 * in use for Entity class.
 * </p>
 * <p>
 * this is an Accessor class for Entity location relation class.
 * </p>  
 * @author Harry Huang
 *
 */
public class PageRankGraphAccessor {
	
	/**
	 * primary key accessor
	 */
	private PrimaryIndex<Integer, PageRankGraph> primaryDocumentID;
	
	public PageRankGraphAccessor(EntityStore store){
		/*
		 * create a primary accessor.
		 */
		this.primaryDocumentID = store.getPrimaryIndex(Integer.class, PageRankGraph.class);
	}

	public PrimaryIndex<Integer, PageRankGraph> getPrimaryDocumentID() {
		return primaryDocumentID;
	}
	
}