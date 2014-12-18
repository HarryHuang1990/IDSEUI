package cn.iscas.idse.console;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

import cn.iscas.idse.search.QueryResult;
import cn.iscas.idse.search.Search;

/**
 * This Handler is responsible for executing query and 
 * return the top 20 results and their relevent documents. 
 * @author Harry Huang
 *
 */
public class QueryHandler extends AbstractHandler {
	 @Override
	 public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		 String keywords = request.getParameter("keyWords");
		 System.out.println(keywords);
		 Search search = new Search();
		 QueryResult queryResult = search.executeSearch(keywords);
		 JSONObject resultInJSON = queryResult.getResultsInJSON();
//		 System.out.println(resultInJSON.toString());
		 
		 response.setContentType("text/html;charset=utf-8");
		 response.setStatus(HttpServletResponse.SC_OK);
		 baseRequest.setHandled(true);
		 response.getWriter().println(resultInJSON.toString());
		 
	 }
}
