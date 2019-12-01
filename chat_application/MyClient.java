import java.net.*;  
import java.io.*;
import java.sql.*;
import java.util.*;  
class MyClient{  
public static void main(String args[])throws Exception{
	try{  
		Socket s=new Socket("localhost",3333);
		DataInputStream din=new DataInputStream(s.getInputStream());  
		DataOutputStream dout=new DataOutputStream(s.getOutputStream());  
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));    
		String str="",str2="";  
		while(!str.equals("stop")){  
			str=br.readLine();  
			dout.writeUTF(str);  
			dout.flush();  
			str2=din.readUTF();  
			System.out.println("Server says: "+str2);  
		}    
		dout.close();  
		s.close();
	}catch(ConnectException  | EOFException e){
		Class.forName("com.mysql.jdbc.Driver"); // registering and loading the driver
                String url = "jdbc:mysql:/localhost:3306/chat_application"; // local host is the name of the server containing the db and trial is the name of the database under usage;
                String user = "root";
                String password = "root";
                Connection con = DriverManager.getConnection(url,user,password); // getting connected to database
                Statement s = con.createStatement();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String msg = "";
		int i = 1;
		while(!msg.equals("stop")){
			System.out.println("Enter the message");
			msg = br.readLine();
			String sql = "insert into messages(msg) values('"+msg+"')";
               		 s.executeUpdate(sql);
			 i ++;


	}
	}
}
}

