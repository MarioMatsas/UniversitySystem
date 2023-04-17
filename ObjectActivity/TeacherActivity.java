package ObjectActivity;

import java.util.ArrayList;
import java.util.Scanner;

import SchoolSystemObjects.Teacher;

public class TeacherActivity {
    private ArrayList<Teacher> teachers;

    public TeacherActivity(ArrayList<Teacher> teachers){
        this.teachers = teachers;
    }

    void teacherLogin(){
        System.out.println("\n-------------\n");
        Scanner scanner = new Scanner(System.in);

        //Request login credentials
        System.err.println("Enter Name: ");
        String name = scanner.nextLine();
        System.out.println("Enter Password: ");
        String password = scanner.nextLine();
        //Check if the password is a valid input (only contains numbers)
        boolean valid = password.matches("[0-9]+");
        while (valid == false){
            System.out.println("Your password only contains digits...Please re-enter it");
            password = scanner.nextLine();
            valid = password.matches("[0-9]+");
        }
        int realPassword = Integer.valueOf(password);


        //Check if name and password match with any admins
        boolean matchFound = false;
        for (Teacher teacher: this.teachers){
            if (teacher.getName().equals(name) && teacher.getPassword() == realPassword){

                //Log teacher into his personal account
                teacherAccount(teacher);
                matchFound = true;
            }
        }
        if (!matchFound){
            System.out.println("No teachers with such credentials found.");
        }
        System.out.println("\n-------------\n");
    }

    void teacherAccount(Teacher teacher){
        System.out.println("\n-------------\n");
        Scanner scanner = new Scanner(System.in);
        //Welcome back message
        System.out.println("Welcome back Mr/Mrs "+teacher.getName()+"\n");

        boolean activeLoop = true;
        String choice;
        while (activeLoop){
            System.out.println("What would you like to do?\n1) Send new assignment\n2) Grade assignments\n3) Go back to main menu");
            choice = scanner.nextLine();
            while (!choice.equals("1") && !choice.equals("2") && !choice.equals("3")){
                System.out.println("Please choose one of the options above!");
                choice = scanner.nextLine();
            }
            int realChoice = Integer.valueOf(choice);
            switch (realChoice){
                case 1:
                    teacher.sendAssignments();
                    break;
                case 2:
                    teacher.gradeAssignment();
                    break;
                case 3:
                    activeLoop = false;
                    break;
            }
        }
    }
}
