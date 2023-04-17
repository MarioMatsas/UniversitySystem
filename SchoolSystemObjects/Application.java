package SchoolSystemObjects;

import java.io.Serializable;
import java.util.ArrayList;

public class Application implements Serializable{
    private static final long serialVersionUID = 1L;
    private Student student;
    private ArrayList<Department> departmentList;

    public Application(Student student, ArrayList<Department> departmentList){
        this.student = student;
        this.departmentList = departmentList;
    }

    public Application(){
        
    }

    //Getters
    Student getStudent(){
        return this.student;
    }
    ArrayList<Department> getDepartmentList(){
        return this.departmentList;
    }

    public String toString(){
        return this.student.toString() + " " + this.departmentList;
    }

}
