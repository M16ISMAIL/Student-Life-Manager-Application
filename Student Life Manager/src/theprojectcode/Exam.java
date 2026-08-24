/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package theprojectcode;

public class Exam {

    private String subject;
    private String date;

    // Constructor
    public Exam(String subject, String date) {
        this.subject = subject;
        this.date = date;
    }

    // Getters
    public String getSubject() {
        return subject;
    }

    public String getDate() {
        return date;
    }

    // Setters
    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
