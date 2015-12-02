package com.sappress.hcpbook.chp03;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ReadingListServlet
 */
@WebServlet("/ReadingListServlet")
public class ReadingListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	/*    public ReadingListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }*/

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	    // Fetch the EJB list manager:
	    ReadingListManagerBeanLocal readingListManager = getListManager(request.getSession());
	    
	    // Retrieve the target action from the request parameters:
	    String action = request.getParameter("action");
	    if ((action != null) && (action.equals("Delete")))
	    {
	      String title = request.getParameter("title");
	      readingListManager.removeTitle(title);
	      
	      response.sendRedirect("index.jsp");
	    }
	    else
	    {
	      RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
	      rd.forward(request, response);
	    }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
/*		List<String> ReadingList = null;
		HttpSession session = request.getSession();
	    Object o = session.getAttribute("ReadingList");
	    if( o == null)
	    {
	    	ReadingList = new ArrayList<String>();
	    	session.setAttribute("ReadingList", ReadingList);
	    	
	    }
	    else
	    {
	    	ReadingList = (List<String>) o;
	    	
	    }
	    
	    String book = request.getParameter("book");
	    if(book != null)
	    {
	    	ReadingList.add(book);
	    	session.setAttribute("itemCount", ReadingList.size());
	    	RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
	    	rd.forward(request, response);
	    }*/
		
	    // Fetch the EJB list manager:
	    ReadingListManagerBeanLocal readingListManager = getListManager(request.getSession());
	    
	    // Add the selected title to the list:
	    String title = request.getParameter("title");
	    if ((title != null) && (! title.equals("")))
	      readingListManager.addTitle(title);
	        
	    // Re-route the user back to the main reading list form:
	    RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
	    rd.forward(request, response);
		
		
	}

	
	
	  /**
	   * Fetch the session EJB from the session context.
	   * @param session
	   * @return
	   */
	  private ReadingListManagerBeanLocal getListManager(HttpSession session)
	  {
	    // Access/create the reading list manager EJB from the session context:   
	    ReadingListManagerBeanLocal readingListManager = null;
	    readingListManager = (ReadingListManagerBeanLocal) session.getAttribute("readingListManager");
	    if (readingListManager == null)
	    {
	      try
	      {
	        // If the EJB hasn't been created yet, go ahead and create it:
	        InitialContext ctx = new InitialContext();
	        readingListManager = (ReadingListManagerBeanLocal) ctx.lookup("java:comp/env/ejb/ReadingListManager");
	        
	        readingListManager.addTitle("OData and SAP NetWeaver Gateway1");
	        readingListManager.addTitle("SAP HANA: An Introduction1");
	        readingListManager.addTitle("SuccessFactors with SAP ERP HCM1");
	        
	        session.setAttribute("readingListManager", readingListManager);
	      }
	      catch (NamingException ne)
	      {
	        ne.printStackTrace();
	      }
	    }
	    
	    return readingListManager;
	  } // -- private ReadingListManagerBeanLocal getListManager() -- //	
}
