package ObjectActivity;

import java.util.ArrayList;
import java.util.Scanner;

import SchoolSystemObjects.*;

public class Menus {
    private ArrayList<Student> students;
    private ArrayList<University> universities;
    private ArrayList<Application> applications;
    private ArrayList<Admin> admins;
    private ArrayList<Department> departments;
    private ArrayList<UniversityStudent> uniStudents;
    private ArrayList<Teacher> teachers;

    public Menus(ArrayList<Student> students, ArrayList<University> universities, ArrayList<Application> applications,
    ArrayList<Admin> admins, ArrayList<Department> departments, ArrayList<UniversityStudent> uniStudents, ArrayList<Teacher> teachers){
        this.students = students;
        this.universities = universities;
        this.applications = applications;
        this.admins = admins;
        this.departments = departments;
        this.uniStudents = uniStudents;
        this.teachers = teachers;
    }

    //MAIN MENU
    public void mainMenu(){
        Scanner scanner = new Scanner(System.in);
        boolean activeLoop = true;
        String choice;
        while (activeLoop){
            System.out.println(" ----- Welcome to UniFuture, please choose one of the following to continue -----\n1) Student account\n2) Teachers\n3) Universities\n4) Admins\n5) Exit");
            choice = scanner.nextLine();
            while (!choice.equals("1") && !choice.equals("2") && !choice.equals("3") && !choice.equals("4")
            && !choice.equals("5")){
                System.out.println("Please choose one of the options above!");
                choice = scanner.nextLine();
            }
            int realChoice = Integer.valueOf(choice);
            switch (realChoice){
                case 1:
                    userMenu();
                    break;
                case 2:
                    TeacherActivity teachAct = new TeacherActivity(this.teachers);
                    teachAct.teacherLogin();
                    break;
                case 3:
                    UniversityActivity uniAct = new UniversityActivity(this.universities);
                    uniAct.addUni(this.admins, this.departments, this.teachers);
                    break;
                case 4:
                    ArrayList<Student> studs = new ArrayList<>();
                    AdminActivity adminAct = new AdminActivity(this.admins);
                    adminAct.adminLogIn(this.applications, studs, this.universities);
                    break;
                case 5:
                    activeLoop = false;
                    break;
            }
        }
    }

    //Ask user to register/login/return back to main menu/
    void userMenu(){
        System.out.println("\n-------------\n");
        boolean activeLoop = true;
        String choice;
        Scanner scanner = new Scanner(System.in);
   
        while (activeLoop){ 
            StudentActivity stAct = new StudentActivity(this.students, this.uniStudents);
            System.out.println("Choose one of the following:\n1) Login\n2) Register new account\n3) Return to main menu");           
            choice = scanner.nextLine();
            while (!choice.equals("1") && !choice.equals("2") && !choice.equals("3")){
                System.out.println("Please choose one of the options above!");
                choice = scanner.nextLine();
            }
            int realChoice = Integer.valueOf(choice);
            switch (realChoice){
                case 1:
                    stAct.loginToAccount(this.universities, this.applications, this.departments);
                    break;    
                case 2:
                    stAct.createAccount();
                    break;
                case 3:
                    activeLoop = false;
                    System.out.println("\n-------------\n");
                    break;
            }
        }
    }

}
