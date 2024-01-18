import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ElectionDbOperations {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/election_db";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Hrishi@2048";

    public static void addClient(ElectionParticipant election) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String query = "INSERT INTO participants (name, contact, age, isVoter, isCandidate) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, election.name);
                preparedStatement.setString(2, election.contact);
                preparedStatement.setInt(3, election.age);
                preparedStatement.setBoolean(4, election.isVoter);
                preparedStatement.setBoolean(5, election.isCandidate);

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Participant  added to the database successfully.");
                } else {
                    System.out.println("Failed to add Participant to the database.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // public static List<ElectionParticipant> getAllClients() {
    //     List<ElectionParticipant> participants = new ArrayList<>();
    //     try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
    //         String query = "SELECT * FROM participants";
    //         try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
    //             ResultSet resultSet = preparedStatement.executeQuery();
    //             while (resultSet.next()) {
    //                 String name = resultSet.getString("name");
    //                 String contact = resultSet.getString("contact");
    //                 int age = resultSet.getInt("age");
    //                 boolean isVoter = resultSet.getBoolean("isVoter");
    //                 boolean isCandidate = resultSet.getBoolean("isVoter");
    //                 ElectionParticipant ppnt = new ElectionParticipant(name,contact, age, isVoter,isCandidate);
    //                 participants.add(ppnt);
    //             }
    //         }
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //     }
    //     return participants;
    // }    
    
    // public static void deleteAllClients() {
    //     try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
    //         String query = "DELETE FROM participants";
    //         try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
    //             int rowsAffected = preparedStatement.executeUpdate();
    //             System.out.println("Deleted successfully: " + rowsAffected + " row(s)");
    //         }
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //     }
    // }

    
    // public static void updateClientContact(String clientName, String newContact) {
    //     try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
    //         String query = "UPDATE participants SET contact = ? WHERE name = ?";
    //         try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
    //             preparedStatement.setString(1, newContact);
    //             preparedStatement.setString(2, clientName);

    //             int rowsAffected = preparedStatement.executeUpdate();

    //             if (rowsAffected > 0) {
    //                 System.out.println("Client contact updated in the database successfully.");
    //             } else {
    //                 System.out.println("Failed to update client contact in the database.");
    //             }
    //         }
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //     }
    // }

 
}
