package theprojectcode;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import java.sql.*;

public class SubjectDAO {

    // Method to get all subjects from database
    public ObservableList<Subject> getAllSubjects() {
        String sql = "SELECT * FROM subjects";
        ObservableList<Subject> subjectsList = FXCollections.observableArrayList();

        try (Connection conn = DBConnection.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String name = rs.getString("name");
                int hours = rs.getInt("hours");
                subjectsList.add(new Subject(name, hours));
            }
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alert.show();
        }
        return subjectsList;
    }

    // Method to add a new subject to database
    public boolean addSubject(String name, int hours) {
        String sql = "INSERT INTO subjects (name, hours) VALUES (?, ?)";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name);
            pstmt.setInt(2, hours);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alert.show();
            return false;
        }
    }

    // Method to update a subject in database
    public boolean updateSubject(String oldName, String newName, int newHours) {
        String sql = "UPDATE subjects SET name = ?, hours = ? WHERE name = ?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, newName);
            pstmt.setInt(2, newHours);
            pstmt.setString(3, oldName);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alert.show();
            return false;
        }
    }

    // Method to delete a subject from database
    public boolean deleteSubject(String name) {
        String sql = "DELETE FROM subjects WHERE name = ?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alert.show();
            return false;
        }
    }
}
