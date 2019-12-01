import java.util.*;
import java.sql.*;
import java.io.*;
class jdbc_practice{
	public static void main(String[] args) throws Exception{
		Class.forName("com.mysql.jdbc.Driver"); // registering and loading the driver
		String url = "jdbc:mysql:/localhost:3306/trial"; // local host is the name of the server containing the db and trial is the name of the database under usage;
		String user = "root";
		String password = "root";
		Connection con = DriverManager.getConnection(url,user,password); // getting connected to database
		Statement s = con.createStatement();
		String sql = "insert into test(id,name) values(3,'prameela')"; 
		s.executeUpdate(sql);
		
		}
}
