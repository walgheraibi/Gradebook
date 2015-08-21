import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class Studentform
 */
@WebServlet("/Studentform")
public class Studentform extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Studentform() {
      //  super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		 
	    //  getServletContext().getRequestDispatcher("/output.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ResultSet result =null;
		Connection conn = null;
		 try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
		
		 String url = "jdbc:oracle:thin:testuser/password@localhost"; 
	      
	        //properties for creating connection to Oracle database
	        Properties props = new Properties();
	        props.setProperty("user", "testdb");
	        props.setProperty("password", "password");
	      
	        //creating connection to Oracle database using JDBC
	        
	    	conn = DriverManager.getConnection(url,props);
		 }
	    	 catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			  
		String sql =""; 

	        if(request.getParameter("action").equals("add"))
	        {
	        String studentid=request.getParameter("studentid");
	   		 String grade=request.getParameter("grade");
	   		 String assignmentname=request.getParameter("assignmentname");
	   		 String type=request.getParameter("type");
	   		 String date=request.getParameter("date");
	   		 String classname= request.getParameter("classname");
	   	 		 sql ="insert into student (studentid,assignmentname,homeworktype, assignmentdate,grade,CLASSNAME) values("+
						studentid +",'"+assignmentname+"','"+type+"', TO_DATE('"+date+"','MM-DD-YYYY'),"+Double.parseDouble(grade)+",'"+classname+"')";
	   		getResult(sql, conn);
	        }
	        else if(request.getParameter("action").equals("byStudent"))
	        {
	        	int count =0;
	        	int avg =0;
	        	classitem classlist = new classitem();
	        	HashMap <String, classitem> classavg = new HashMap <String, classitem>();
	        	HashMap <String, Double> weights = new HashMap <String, Double>();
	        	String assignmentbyid ="<table class=\"table\"><thead><tr><th>CLASSNAME</th><th>ASSIGNMENTNAME</th><th>HOMEWORKTYPE</th><th>ASSIGNMENTDATE</th><th>GRADE</th></tr></thrad><tbody>";
	        	String studentid=request.getParameter("getstudentid");
	        	 sql ="select * from student where studentid="+Integer.parseInt(studentid);
	        	 result = getResult(sql, conn);
	        	
	        	 try {

					while(result.next())
					 {	
						assignmentbyid+="<tr><td>"+result.getString("CLASSNAME")+"</td><td>"+result.getString("ASSIGNMENTNAME")+"</td><td>"+result.getString("HOMEWORKTYPE")+"</td><td>"+result.getDate("ASSIGNMENTDATE")+"</td><td>"+result.getDouble("GRADE")+"</td></tr>"; 
						if(classavg.containsKey(result.getString("CLASSNAME")))
							{
							classlist=classavg.get(result.getString("CLASSNAME"));
							classlist.setType(result.getString("HOMEWORKTYPE"),result.getDouble("GRADE"));
							classavg.replace(result.getString("CLASSNAME"), classlist);
							}
						else
							{
							classlist = new classitem(result.getString("CLASSNAME"), result.getString("HOMEWORKTYPE"),result.getDouble("GRADE"));
							classavg.put(result.getString("CLASSNAME"), classlist);
							}
						avg+=result.getDouble("GRADE");
						count++;
					 }
					avg = (avg/count);
					assignmentbyid+="</tbody></table><br><h2>Student Over All Average is: " + avg+"</h2><table class=\"table\"><thead><tr><th>CLASS NAME</th><th>HOMEWORK TYPE</th><th>AVERAGE</th><th>WEIGHTS</th></tr></thrad><tbody>";
					
					sql ="select * from weights";
		        	 result = getResult(sql, conn);
				      
		        	
		        	 while(result.next())
					 {	
		        		 weights.put(result.getString("CLASSNAME"),(result.getDouble("weights")/100));
		        	 }
					 for(String key : classavg.keySet())
		        	 {
						 System.out.println(classavg.get(key).getClassName());
						 System.out.println(classavg.get(key).getType());
						 System.out.println(classavg.get(key).getassiavg("homework"));
		        		 classlist= classavg.get(key);
		        	 for(String keyw: weights.keySet())
		        	 { 
		        	if(classlist.typeexist(keyw))
	        		 {
	           			System.out.println("result exect");
	        			 Double typeavg = weights.get(keyw) * classlist.getassiavg(keyw);
	        			 System.out.println(""+ weights.get(keyw) );
	        			 assignmentbyid+="<tr><td>"+classlist.getClassName() +"</td><td>"+keyw +"</td><td>" + typeavg+"</td><td>" + weights.get(keyw)*100+"</td></tr>";
	        		 }
		        	 }
		        	 }
					 assignmentbyid+="</tbody></table>";
				      response.setContentType("text/html");
					  request.setAttribute("assignmentbyid",assignmentbyid);
				   
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        	 
	        }
			
	        else if(request.getParameter("action").equals("byType"))
	        {
	        	double lowest =0;
	        	double highest =0;
	        	String assignmentbytype ="<table class=\"table\"><thead><tr><th>ASSIGNMENTNAME</th><th>HOMEWORKTYPE</th><th>ASSIGNMENTDATE</th><th>GRADE</th></tr></thrad><tbody>";
	        	String assigntype=request.getParameter("assigntype");
	        	 sql ="select * from student where HOMEWORKTYPE='"+assigntype+"'";
	        	 result = getResult(sql, conn);
	        	
	        	 try {

					while(result.next())
					 {	
						assignmentbytype+="<tr><td>"+result.getString("ASSIGNMENTNAME")+"</td><td>"+result.getString("HOMEWORKTYPE")+"</td><td>"+result.getDate("ASSIGNMENTDATE")+"</td><td>"+result.getDouble("GRADE")+"</td></tr>"; 
						if(lowest==0)
							lowest =result.getDouble("GRADE");
						else if(lowest>result.getDouble("GRADE"))
							lowest = result.getDouble("GRADE");
						if(highest==0)
							highest =result.getDouble("GRADE");
						else if(highest<result.getDouble("GRADE"))
							highest = result.getDouble("GRADE");
					 }
					assignmentbytype+="</tbody></table><br><h2>Highest Grade is: " + highest+"</h2><br><h2>Lowest Grade is: " + lowest+"</h2>";
				      response.setContentType("text/html");
					  request.setAttribute("assignmentbytype",assignmentbytype);
				   
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        	 
	        }
	        else if(request.getParameter("action").equals("change"))
	        {
	        	 String homework=request.getParameter("homework");
		   		 String quiz=request.getParameter("quiz");
		   		 String test=request.getParameter("test");
		   		 String project=request.getParameter("project");
		   	
		   		 sql ="update WEIGHTS set weights= case CLASSNAME when 'homework' then "+homework +" when 'project' then "+project +" when 'quiz' then "+quiz+" when 'test' then "+test +" end";
				  getResult(sql, conn);
		   	
	        }
	        else if(request.getParameter("action").equals("bySandT"))
	        {

	        	int count =0;
	        	double avg = 0;
	        	String assignmentbyst ="<table class=\"table\"><thead><tr><th>ASSIGNMENTNAME</th><th>HOMEWORKTYPE</th><th>ASSIGNMENTDATE</th><th>GRADE</th></tr></thrad><tbody>";
	        	String getsid=request.getParameter("getsid");
	        	String assignmtype=request.getParameter("assignmtype");
	        	 sql ="select * from student where studentid="+Integer.parseInt(getsid)+" and HOMEWORKTYPE='"+assignmtype+"'";
	        	 result = getResult(sql, conn);
	        	
	        	 try {

					while(result.next())
					 {	
						assignmentbyst+="<tr><td>"+result.getString("ASSIGNMENTNAME")+"</td><td>"+result.getString("HOMEWORKTYPE")+"</td><td>"+result.getDate("ASSIGNMENTDATE")+"</td><td>"+result.getDouble("GRADE")+"</td></tr>"; 
						avg+= result.getDouble("GRADE");
						count++;
					 }
					avg = (avg/count);
					assignmentbyst+="</tbody></table><br><h2>Student Average is: " + avg+"</h2>";
				      response.setContentType("text/html");
					  request.setAttribute("assignmentbyst",assignmentbyst);
				   
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        	 
	        }
	      getServletContext().getRequestDispatcher("/input.jsp").forward(request, response);
	}
	
	private ResultSet getResult(String sql, Connection conn )
	{
		ResultSet result =null;

	    try{  
		//creating PreparedStatement object to execute query

		 PreparedStatement preStatement = conn.prepareStatement(sql);

		 result = preStatement.executeQuery();
		 System.out.println("after execution "+result);
			}
		 catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		return result;
	}

}
