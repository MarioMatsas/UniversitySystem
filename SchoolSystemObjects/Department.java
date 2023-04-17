package SchoolSystemObjects;

import java.io.Serializable;
import java.util.ArrayList;

public class Department implements Serializable{
    private static final long serialVersionUID = 1L;
    private String title;
    private University university;
    private double grade;
    private ArrayList<UniversityStudent> uniStudents;

    public Department(String title, University university, double grade, ArrayList<UniversityStudent> uniStudents){ 
        this.title = title;                                                                                                      
        this.university = university;
        this.grade = grade;
        this.uniStudents = uniStudents;
    }

    public Department(){

    }

    //Getters
    public String getTitle(){
        return this.title;
    }
    University getUniversity(){
        return this.university;
    }
    double getRequiredGrade(){
        return this.grade;
    }
    ArrayList<UniversityStudent> getUniStudents(){
        return this.uniStudents;
    }

    //Setters
    void setUniStudents(ArrayList<UniversityStudent> uniStudents){
        this.uniStudents = uniStudents;
    } 

    public String toString(){
        return "-- "+this.title+" -- "+" | "+this.grade;
    }
}
