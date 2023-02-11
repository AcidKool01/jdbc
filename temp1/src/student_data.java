import java.sql.*;

public class student_data {

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
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/students_details", "root", "Ventotdi1.6");
            Statement statement = conn.createStatement();
            ResultSet resultSet = null;
            
            System.out.println("The Student Details are:");
            resultSet = statement.executeQuery("select * from students");
            displayData(resultSet);

            System.out.println("\nThe Students in CSE Branch are:");
            resultSet = statement.executeQuery("select * from students where Department = 'CSE'");
            displayData(resultSet);

        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
            throwables.printStackTrace();
        }
    }
}
