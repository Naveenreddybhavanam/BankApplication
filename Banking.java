package com.naveen;
import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.DriverManager;
public class Banking extends HttpServlet
{
	
public void service(HttpServletRequest req,HttpServletResponse res) throws IOException
{
	int number=Integer.parseInt(req.getParameter("num3"));
	int n=Integer.parseInt(req.getParameter("num4"));
	int total=0;
	res.setContentType("text/html");
	PrintWriter out=res.getWriter();
	out.println("<body bgcolor=skyblue>");
	String url="jdbc:mysql://127.0.0.1:3306/mydb";
	String uname="root";
	String pass="Naveenreddy@24";
	String x="Select * from bank where id="+number;
	try {
		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
	} catch (SQLException e2) {
		// TODO Auto-generated catch block
		e2.printStackTrace();
	}
	
	try{
	Class.forName("com.mysql.jdbc.Driver").newInstance();	
	Connection con=DriverManager.getConnection(url,uname,pass);
	Statement stmt=con.createStatement();
	
	ResultSet rs=stmt.executeQuery(x);
	out.println("<h1>Transcations</h1><br>");
	out.println(" <h4>  withdraw "+"  deposit &nbsp"+"  id </h4> <br>");
	while(rs.next())
	{
	out.println("&nbsp&nbsp"+rs.getInt("withdraw")+"&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"+rs.getInt("deposit")+" &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"+rs.getInt("id")+"<br>");
	out.println( );
	}
	rs.close();
	out.println("<h3>Before Fund Transfer</h3><br>");
	String y="select id,withdraw,deposit,sum(deposit)-sum(withdraw) as Balance from bank where id="+number;
	ResultSet rs1=stmt.executeQuery(y);
	while(rs1.next())
	{
	out.println("<h3>Account Number is:-"+rs1.getInt("id")+"&nbsp&nbsp&nbsp Balance Amount is:- "+rs1.getInt("Balance")+"</h3><br>");
	total=(rs1.getInt("Balance"));
	out.println( );
	}
	out.println("<h3>After Fund Transfer Balance is:-</h3>"+(total-n));
	
	stmt.close();
	con.close();
	}catch(Exception e){
		e.printStackTrace();
	}
	out.println("</body>");
}
}
