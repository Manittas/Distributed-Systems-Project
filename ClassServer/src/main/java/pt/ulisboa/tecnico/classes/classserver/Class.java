package pt.ulisboa.tecnico.classes.classserver;

import pt.ulisboa.tecnico.classes.Stringify;
import pt.ulisboa.tecnico.classes.contract.ClassesDefinitions;

import java.util.Comparator;
import java.util.HashMap;
import java.util.stream.Collectors;

public class Class {

    private int capacity;
    private boolean enrollmentStatus; // true if open, false if not
    private HashMap<String, String> enrolledStudents;
    private HashMap<String, String> notEnrolledStudents;

    public Class () {
        enrollmentStatus = false;
        enrolledStudents = new HashMap<>();
        notEnrolledStudents = new HashMap<>();
    }

    public int getCapacity() {
        return capacity;
    }

    public boolean getEnrollmentStatus() {
        return enrollmentStatus;
    }

    public HashMap<String, String> getEnrolledStudents() {
        return enrolledStudents;
    }

    public synchronized void setEnrolledStudents(HashMap<String, String> enrolledStudents) {
        this.enrolledStudents = enrolledStudents;
    }

    public HashMap<String, String> getNotEnrolledStudents() {
        return notEnrolledStudents;
    }

    public synchronized void setNotEnrolledStudents(HashMap<String, String> notEnrolledStudents) {
        this.notEnrolledStudents = notEnrolledStudents;
    }

    public synchronized void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public synchronized void setEnrollmentStatus(boolean enrollmentStatus) {
        this.enrollmentStatus = enrollmentStatus;
    }

    public synchronized void addEnrolledStudent(String number, String name) {
        enrolledStudents.put(number, name);
        notEnrolledStudents.remove(number);
    }

    public synchronized void removeEnrolledStudent(String number) {
        String name = enrolledStudents.remove(number);
        notEnrolledStudents.put(number, name);
    }

    public String toString() {
        return "Class:\n" +
                "\tcapacity: " + capacity + '\n' +
                "\tenrollment status: " + enrollmentStatus + '\n' +
                "\tenrolled: " + enrolledStudents + '\n' +
                "\tdiscarded: " + notEnrolledStudents + '\n';
    }
}
