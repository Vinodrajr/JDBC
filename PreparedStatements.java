package javatosqlusingjdbcapi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class PreparedStatements {
	public static void main(String[] args) {

		String s = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/javabatch5";
		String user_name = "root";
		String password = "root";
		String query = "Insert into employee values(?,?,?)";
		Scanner sc=new Scanner(System.in);
		int id=sc.nextInt();
		try {
			// 1. load and Register Driver
			Class.forName(s);
			// 2. Establish Connection
			Connection connection = DriverManager.getConnection(url, user_name, password);

			// 3.Create Statement
			PreparedStatement preparedStatement = connection.prepareStatement(query);

			// 4.Execute Statement
			preparedStatement.setInt(1,id);
			preparedStatement.setString(2,"asdef");
			preparedStatement.setDouble(3,4556);

			preparedStatement.execute();

			// 5.Close
			connection.close();

			System.out.println("Data Saved");

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
