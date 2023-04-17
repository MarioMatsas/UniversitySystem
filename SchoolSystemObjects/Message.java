package SchoolSystemObjects;

import java.io.Serializable;

public class Message implements Serializable{
    private Department department;
    private String acceptanceOrNot;

    public Message(Department department, String acceptanceOrNot){
        this.department = department;
        this.acceptanceOrNot = acceptanceOrNot;       
    }

    //Getters
    Department getDepartment(){
        return this.department;
    }
    String getAcceptanceOrNot(){
        return this.acceptanceOrNot;
    }
    //String getMessages

    public String toString(){
        return this.acceptanceOrNot+" by the department of: "+this.department.getTitle()+" at "+this.department.getUniversity().getName();
    }
}
