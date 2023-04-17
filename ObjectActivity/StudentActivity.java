package ObjectActivity;

import java.util.ArrayList;
import java.util.Scanner;

import SchoolSystemObjects.*;

public class StudentActivity {
    private ArrayList<Student> students;
    private ArrayList<UniversityStudent> uniStudents;

    public StudentActivity(ArrayList<Student> students, ArrayList<UniversityStudent> uniStudents){
        this.students = students;
        this.uniStudents = uniStudents;
    }
    
    public void createAccount(){    
        System.out.println("\n-------------\n");
        Scanner scanner = new Scanner(System.in);

        //Request student, presonal info
        System.out.println("Enter Full Name: ");
        String name = scanner.nextLine();
        System.out.println("Enter Password: ");
        String password = scanner.nextLine();
        System.out.println("Enter Age: ");

        //Check if age is a valid number
        String tempAge = scanner.nextLine();
        boolean valid = tempAge.matches("[0-9]+");
        while (valid == false || Integer.valueOf(tempAge)<17){
            System.out.println("Please enter a valid age");
            tempAge = scanner.nextLine();
            valid = tempAge.matches("[0-9]+");
        }
        int age = Integer.valueOf(tempAge);

        System.out.println("Enter Grade: ");

        //Check if the grade given is valid
        String tempGrade = scanner.nextLine();
        
        while (true){
            try{
                Double.parseDouble(tempGrade);
                if (Double.valueOf(tempGrade)<=10 && Double.valueOf(tempGrade)>=0){
                    break;
                }
                System.out.println("Please enetr a valid grade (number)");
                tempGrade = scanner.nextLine();
            }
            catch(NumberFormatException e){
                System.out.println("Please enetr a valid grade (number)");
                tempGrade = scanner.nextLine();
            }
        }
        double grade = Double.valueOf(tempGrade);
        ArrayList<Message> messages = new ArrayList<>();       

        //Create the new student account
        Student student = new Student(name, age, grade, password, messages, null);
        System.out.println("Account Registered!\n"+student.toString());

        //Add new student to the student list
        this.students.add(student);
        System.out.println("\n-------------\n");
    }

    public void loginToAccount(ArrayList<University> universities, ArrayList<Application> applications, ArrayList<Department> departments){
        System.out.println("\n-------------\n");
        Scanner scanner = new Scanner(System.in);

        //Request login credentials
        System.err.println("Enter Full Name: ");
        String name = scanner.nextLine();
        System.out.println("Enter Password: ");
        String password = scanner.nextLine();

        //Check if name and password match with any students
        boolean matchFound = false;
        for (Student student: this.students){
            if (student.getName().equals(name) && student.getPassword().equals(password)){

                //Log student into their personal account
                matchFound = true;
                studentAccount(student, universities, applications, departments, uniStudents, students); 
                break;
                
            }
        }
        //Check if name and password match with any university students
        for (UniversityStudent uniStudent: this.uniStudents){
            if (uniStudent.getName().equals(name) && uniStudent.getPassword().equals(password)){

                //Log university student into their personal account
                matchFound = true;
                uniStudentAccount(uniStudent, universities, applications, departments, uniStudents);
                break;
            }
        }
        if (!matchFound){
            System.out.println("No students with such credentials found.");
        }
        System.out.println("\n-------------\n");
    }

    void uniStudentAccount(UniversityStudent uniStudent, ArrayList<University> universities, ArrayList<Application> applications,
    ArrayList<Department> departments, ArrayList<UniversityStudent> uniStudents){
        System.out.println("\n-------------\n");
        Scanner scanner = new Scanner(System.in);

        boolean activeLoop = true;
        String choice;

        //Welcome message
        System.out.println("Welcome back "+uniStudent.getName()+" !");
        
        //Ask student what they would like to do in their account
        while (activeLoop){
            System.out.println("\n-------------\n");
            //Print option menu
            System.out.println("What would you like to do?\n1) View all Universities\n2) Apply to available Departments\n3) Check Messages"+
            "\n4) Check Assignments\n5) Check Grades\n6) Go back to Student menu");

            choice = scanner.nextLine();
            while (!choice.equals("1") && !choice.equals("2") && !choice.equals("3") && !choice.equals("4")
            && !choice.equals("5") && !choice.equals("6")){
                System.out.println("Please choose one of the options above!");
                choice = scanner.nextLine();
            }
            int realChoice = Integer.valueOf(choice);
            switch (realChoice){
                case 1:
                    //View a list of all the universities
                    uniStudent.viewUnis(universities);
                    break;
                case 2:
                    //Get list of available universities
                    ArrayList<Department> availableDepartments = uniStudent.findDepartments(departments);

                    uniStudent.applyToUni(availableDepartments, applications);
                    break;
                case 3:
                    uniStudent.checkMessages();
                    break;
                case 4:
                    uniStudent.checkAssignments();
                    break;
                case 5:
                    uniStudent.checkGrades();
                    break;
                case 6:
                    activeLoop = false;
                    break;
            }
        }
    }

    void studentAccount(Student student, ArrayList<University> universities, ArrayList<Application> applications,
    ArrayList<Department> departments, ArrayList<UniversityStudent> uniStudents, ArrayList<Student> students){
        System.out.println("\n-------------\n");
        Scanner scanner = new Scanner(System.in);

        boolean activeLoop = true;
        boolean studentRemoved = false;
        String choice;
        //Welcome message
        System.out.println("Welcome back "+student.getName()+" !");
        
        //Ask student what they would like to do in their account
        while (activeLoop){
            System.out.println("\n-------------\n");
            //If the Student object gets removed after becoming a UniversityStudent object, then we break because it cant choose any
            //of the options below
            if (studentRemoved){
               break;
            }

            //Print option menu
            System.out.println("What would you like to do?\n1) View all Universities\n2) Apply to available Departments\n3) Check Messages\n4) Go back to Student menu");
            choice = scanner.nextLine();
            while (!choice.equals("1") && !choice.equals("2") && !choice.equals("3") && !choice.equals("4")){
                System.out.println("Please choose one of the options above!");
                choice = scanner.nextLine();
            }
            int realChoice = Integer.valueOf(choice);
            switch (realChoice){
                case 1:
                    //View a list of all the universities
                    student.viewUnis(universities);
                    break;
                case 2:
                    //Get list of available universities
                    ArrayList<Department> availableDepartments = student.findDepartments(departments);

                    student.applyToUni(availableDepartments, applications);
                    break;
                case 3:
                    studentRemoved = student.checkMessages(students, uniStudents, studentRemoved);
                    break;
                case 4:
                    activeLoop = false;
                    break;
            }
        }
    }
}
