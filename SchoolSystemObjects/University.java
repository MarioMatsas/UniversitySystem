package SchoolSystemObjects;

import java.io.Serializable;

public class University implements Serializable{
    private static final long serialVersionUID = 1L;
    private String name;
    private double grade;

    public University(String name, double grade){
        this.name = name;
        this.grade = grade;
    }

    public University(){
        
    }

    //Getters
    public String getName(){
        return this.name;         
    }

    double getRequiredGrade(){
        return this.grade;
    }

    public String toString(){
        return "-- "+this.name+" -- "+" | "+this.grade;
    }
}
