package ObjectActivity;

import java.util.ArrayList;
import java.util.Scanner;

import SchoolSystemObjects.Admin;
import SchoolSystemObjects.Application;
import SchoolSystemObjects.Student;
import SchoolSystemObjects.University;

public class AdminActivity {
    private ArrayList<Admin> admins;
    
    public AdminActivity(ArrayList<Admin> admins){
        this.admins = admins;
    }

    public void adminLogIn(ArrayList<Application> applications, ArrayList<Student> students, ArrayList<University> universities){
        System.out.println("\n-------------\n");
        Scanner scanner = new Scanner(System.in);

        //Request login credentials
        System.err.println("Enter University of operation: ");
        String uniName = scanner.nextLine();
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
        for (Admin admin: this.admins){
            if (admin.getUni().getName().equals(uniName) && admin.getPassword() == realPassword){

                ArrayList<Object[]> studentDepartmentList = new ArrayList<>(); 

                //Log admin into his personal account
                adminAccount(admin, applications, studentDepartmentList, admins, universities);
                matchFound = true;
            }
        }
        if (!matchFound){
            System.out.println("No admins with such credentials found.");
        }
        System.out.println("\n-------------\n");
    }


    void adminAccount(Admin admin, ArrayList<Application> applications,ArrayList<Object[]> studentDepartementList, ArrayList<Admin> admins, ArrayList<University> universities){
        System.out.println("\n-------------\n");
        Scanner scanner = new Scanner(System.in);
        //Welcome back message
        System.out.println("Welcome back Admin\n");

        //Ask the admin for permission to check applications
        System.out.println("Would you like to check the application? [Y (for yes) / Anything (for no)]");
        String choice = scanner.nextLine();

        if (choice.equals("Y")){
            admin.handleApplications(applications, studentDepartementList);
            System.out.println("Nice job!\n");          
        }
    }
}
