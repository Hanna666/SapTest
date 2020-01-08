package framework.driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBValidation
{
	public Connection con = null;
	static DBValidation db = new DBValidation();
	
	
	
	public static DBValidation getInstance()
	{
		return db;
	}

	

	public void connectSqlServerDB()
	{
		
	}
	
	public void connectMySqlDB(String host,String port,String dbName, String userName, String password)
	{
		
		try
		{
			//itappsbrnotest.honeywell.com
			//3306
			//vacmancustomertest
			//root
			//tjjMfBSTWQdtuc7L
			
			Class.forName("com.mysql.jdbc.Driver"); 
			con=DriverManager.getConnection("jdbc:mysql://"+ host.trim() + ":"+ port.trim() + "/"+ dbName.trim(), userName.trim(), password.trim());
			 
		}
		catch( Exception e)
		{
			e.printStackTrace();
		}
		
		
	}
	
	public Statement connectOracleDB(String host,String port,String dbName, String userName, String password) throws ClassNotFoundException
	{
		Statement stmt = null;
		
		
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");  
			  
			Connection con=DriverManager.getConnection(  
					"jdbc:oracle:thin:@//"+host+":"+port+"/"+dbName+"\",\""+userName+"\",\""+password+"\""); 
			 stmt=con.createStatement(); 
		}
		catch( Exception e)
		{
			e.printStackTrace();
		}
		return stmt;
	}
	//--------------------------------------------------------------------------------------------------
	
	public static void MySqlSelectQuery(Statement stmt,String Query) throws SQLException
	{
		
		ResultSet rs=stmt.executeQuery(Query);
		int rowNum=rs.getRow();
		System.out.println("Row num is "+rowNum);
	
		while(rs.next())
		{
			for(int i=0;i< rowNum;i++)
			{
			 System.out.println(rs.getString(i));
			 
			}
		}
		
				
	}
	
	
}
