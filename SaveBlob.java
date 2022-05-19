package jdbc_fileoutputstream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.jdbc.Driver;

public class SaveBlob {
//	int id;
//	 String name;
//	 FileInputStream stream;
//	public int getId() {
//		return id;
//	}
//	public void setId(int id) {
//		this.id = id;
//	}
//	public String getName() {
//		return name;
//	}
//	public void setName(String name) {
//		this.name = name;
//	}
//	public FileInputStream getStream() {
//		return stream;
//	}
//	public void setStream(FileInputStream stream) {
//		this.stream = stream;
//	}

	private Connection getConnection() throws SQLException {
		Driver driver = new Driver();
		DriverManager.registerDriver(driver);

		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javabatch", "root", "root");

		return connection;

	}

	public void saveimage(FileInputstreamIntoDatabase database) {

		String query = "Insert into savefile values(?,?,?)";

		try {
			PreparedStatement preparedStatement = getConnection().prepareStatement(query);
			preparedStatement.setInt(1, database.getId());
			preparedStatement.setString(2, database.getName());
			preparedStatement.setBlob(3, database.getStream());
			preparedStatement.execute();
			System.out.println("Done");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void getImage() throws FileNotFoundException {
		String query = "Select * from savefile where id=?";

		try {
			PreparedStatement preparedStatement = getConnection().prepareStatement(query);
			preparedStatement.setInt(1, 1);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int a = resultSet.getInt(1);
				Blob blob = resultSet.getBlob(3);
				byte[] b = blob.getBytes(1, (int) blob.length());

				File file = new File("C:/temp/trees.jpg");
				FileOutputStream stream = new FileOutputStream(file);
				stream.write(b);
			}

			System.out.println("Done");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) throws FileNotFoundException {
		SaveBlob blob = new SaveBlob();

		FileInputstreamIntoDatabase database = new FileInputstreamIntoDatabase();

		database.setId(1);
		database.setName("Tree");
		File file = new File("C:/temp/tree.jpg");
		FileInputStream fileInputStream = new FileInputStream(file);
		database.setStream(fileInputStream);
		blob.saveimage(database);

	}

}
