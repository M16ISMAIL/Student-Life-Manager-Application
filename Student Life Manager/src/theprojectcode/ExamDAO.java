package theprojectcode;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import java.sql.*;

public class ExamDAO {

    // Method to get all exams from database
    public ObservableList<Exam> getAllExams() {
        String sql = "SELECT * FROM exams";
        ObservableList<Exam> examsList = FXCollections.observableArrayList();

        try (Connection conn = DBConnection.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String subject = rs.getString("subject");
                String date = rs.getString("date");
                examsList.add(new Exam(subject, date));
            }
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alert.show();
        }
        return examsList;
    }

    // Method to add a new exam to database
    public boolean addExam(String subject, String date) {
        String sql = "INSERT INTO exams (subject, date) VALUES (?, ?)";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, subject);
            pstmt.setString(2, date);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alert.show();
            return false;
        }
    }

    // Method to delete an exam from database
    public boolean deleteExam(String subject, String date) {
        String sql = "DELETE FROM exams WHERE subject = ? AND date = ?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, subject);
            pstmt.setString(2, date);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alert.show();
            return false;
        }
    }
}
