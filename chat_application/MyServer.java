import java.net.*;  
import java.io.*;
import java.sql.*;
import java.util.*;  
class MyServer{  
public static void main(String args[])throws Exception{
	Class.forName("com.mysql.jdbc.Driver");
	String user = "root";
	String pass = "root";
	String url =  "jdbc:mysql:/localhost:3306/chat_application"; 
	Connection con = DriverManager.getConnection(url,user,pass);
	Statement st = con.createStatement();
	ResultSet rs;
	String sql = "Select * from messages";
	rs = st.executeQuery(sql);
	while(rs.next()){
		System.out.println(rs.getString(1));
	}
	rs.close();
	sql = "delete from messages";
	st.executeUpdate(sql);
	ServerSocket ss=new ServerSocket(3333);
	Socket s=ss.accept();  
	DataInputStream din=new DataInputStream(s.getInputStream());  
	DataOutputStream dout=new DataOutputStream(s.getOutputStream());  
	BufferedReader br=new BufferedReader(new InputStreamReader(System.in));    
	String str="",str2="";  
	while(!str.equals("stop")){
		str=din.readUTF(); 
		System.out.println("client says: "+str);  
		str2=br.readLine();  
		dout.writeUTF(str2);  
		dout.flush();  
	}  
	din.close();  
	s.close();  
	ss.close();  
	}
}  
