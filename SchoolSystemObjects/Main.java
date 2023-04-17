package SchoolSystemObjects;

import java.util.ArrayList;

import ObjectActivity.Menus;
import ObjectActivity.SaveAndLoad;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException{ 
        //Array lists that contain all the 'special' type of objects we will use
        ArrayList<ArrayList> contents = new ArrayList<>();
        ArrayList<Student> students = new ArrayList<>();
        ArrayList<University> universities = new ArrayList<>();
        ArrayList<Application> applications = new  ArrayList<>();
        ArrayList<Admin> admins = new ArrayList<>();
        ArrayList<Department> departments = new ArrayList<>();
        ArrayList<UniversityStudent> uniStudents = new ArrayList<>();
        ArrayList<Teacher> teachers = new ArrayList<>();
         
        //Load the file data/ Create new data files 
        SaveAndLoad saveLoad = new SaveAndLoad();

        contents = saveLoad.getFileInfo("ObjectData/contents.ser", contents);
        if (!contents.isEmpty()){
            students = contents.get(0);
            universities = contents.get(1);
            applications = contents.get(2);
            admins = contents.get(3);
            departments = contents.get(4);
            uniStudents = contents.get(5);
            teachers = contents.get(6);
        }
            
        //Main Programme
        Menus menu = new Menus(students, universities, applications, admins, departments, uniStudents, teachers);
        menu.mainMenu();

        //Save all the data
        contents.add(0, students);
        contents.add(1, universities);
        contents.add(2, applications);
        contents.add(3, admins);
        contents.add(4, departments);
        contents.add(5, uniStudents);
        contents.add(6, teachers);
        saveLoad.saveFileInfo("ObjectData/contents.ser", contents);

    }
}
