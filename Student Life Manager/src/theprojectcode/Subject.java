/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package theprojectcode;

public class Subject {

    private String name;
    private int hours;

    // Constructor
    public Subject(String name, int hours) {
        this.name = name;
        this.hours = hours;
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getHours() {
        return hours;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }
}
