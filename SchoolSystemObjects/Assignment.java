package SchoolSystemObjects;

import java.io.Serializable;

public class Assignment implements Serializable{
    private static final long serialVersionUID = 1L;
    private String title;
    private String question;
    private double grade;
    private Teacher teacher;
    private String answear;
    private UniversityStudent uniStudent;

    public Assignment(String title, String question, Teacher teacher, UniversityStudent uniStudent, String answear, double grade){
        this.title = title;
        this.question = question;
        this.grade = grade;
        this.teacher = teacher;
        this.answear = answear;
        this.uniStudent = uniStudent;
    }

    public Assignment(){
        
    }

    //Getters
    String getTitle(){
        return this.title;
    }
    String getQuestion(){
        return this.question;
    }
    String getAnswear(){
        return this.answear;
    }
    double getGrade(){
        return this.grade;
    }
    Teacher getTeacher(){
        return this.teacher;
    }
    UniversityStudent getUniStudent(){
        return this.uniStudent;
    }

    //Setters
    void setGrade(double grade){
        this.grade = grade;
    }
    void setAnswear(String answear){
        this.answear = answear;
    }

    public String toString(){
        if (this.grade == -1){
            return "| Assignment from "+this.teacher+" : "+this.title+", Grade: Not Graded |";
        }
        else{
            return "| Assignment from "+this.teacher+" : "+this.title+", Grade: "+this.grade+" |";
        }
    }
}
