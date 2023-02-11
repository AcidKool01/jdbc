import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class student {

//    private static final String SERVER_URL = "jdbc:mysql://localhost:3306/students_details";
//    private static final String USER_NAME = "root";
//    private static final String PASSWORD = "Ventotdi1.6";

    public static void displayData (ResultSet resultSet) throws SQLException {
        while(resultSet.next()) {
            int roll = resultSet.getInt("Roll_No");
            String name = resultSet.getString("Name");
            String city = resultSet.getString("City");
            String dept = resultSet.getString("Department");
            System.out.println(roll + "   " + name + "   " + city + "   " + dept);
        }
    }

    public static void main(String[] args) {
        Connection conn = null;
        ResultSet resultSet = null;
        Statement statement = null;
        try {
            InputStream inputStream = new FileInputStream("db.properties");
            Properties p = new Properties();
            p.load(inputStream);

            String user = p.getProperty("user");
            String password = p.getProperty("password");
            String url = p.getProperty("url");

            conn = DriverManager.getConnection(url, user, password);
            statement = conn.createStatement();

            System.out.println("The Student Details are:");
            resultSet = statement.executeQuery("select * from students");
            displayData(resultSet);

            System.out.println("\nThe Students in CSE Branch are:");
            resultSet = statement.executeQuery("select * from students where Department = 'CSE'");
            displayData(resultSet);
        } catch (SQLException | FileNotFoundException throwables) {
            System.out.println(throwables.getMessage());
            throwables.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

