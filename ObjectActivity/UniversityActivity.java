package ObjectActivity;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import SchoolSystemObjects.*;

public class UniversityActivity {
    private ArrayList<University> universities;

    public UniversityActivity(ArrayList<University> universities){
        this.universities = universities;
    }

    public void addUni(ArrayList<Admin> admins, ArrayList<Department> departments, ArrayList<Teacher> teachers){
        System.out.println("\n-------------\n");
        Scanner scanner = new Scanner(System.in);

        //Get university info
        System.out.println("Enter the University's name");
        String name = scanner.nextLine();
        System.out.println("Enter the University's required grade");
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

        //Create new University object
        University uni = new University(name, grade);

        //Add the new university to the list of universities
        this.universities.add(uni);
        System.out.println("\n"+uni);

        //Create the Admin for this university
        Random random = new Random();
        int password = random.nextInt(899999)+100000; //Generate random 6-digit password
        Admin admin = new Admin(uni, password);

        //Add the new admin to the list of admins
        admins.add(admin);
        System.out.println("\n"+admin);

        //Create the CS, Mathematics and Physics departments of the university
        ArrayList<UniversityStudent> uniStudents1 = new ArrayList<>();
        ArrayList<UniversityStudent> uniStudents2 = new ArrayList<>();
        ArrayList<UniversityStudent> uniStudents3 = new ArrayList<>();

        Department csDepartment = new Department("ComputerScience", uni, grade, uniStudents1);
        Department mathDepartment = new Department("Marhematics", uni, grade, uniStudents2);
        Department physicsDepartment = new Department("Physics", uni, grade, uniStudents3);

        //Add the new departmnts to this temporary list
        ArrayList<Department> tempDepList = new ArrayList<>();
        tempDepList.add(csDepartment);
        tempDepList.add(mathDepartment);
        tempDepList.add(physicsDepartment);

        //Add 2 teachers in all 3 departments
        Teacher teacher1;
        Teacher teacher2;

        for (int i=0; i<3; i++){
            //Empty individual assignment list for the teachers 
            ArrayList<Assignment> ass1 = new ArrayList<>();
            ArrayList<Assignment> ass2 = new ArrayList<>();

            //Generate random ID names and passwords
            String name1 = String.valueOf(random.nextInt(899999)+100000);
            int pass1 = random.nextInt(899999)+100000;
            String name2 = String.valueOf(random.nextInt(899999)+100000);
            int pass2 = random.nextInt(899999)+100000;
            
            //Add them to the cs department
            teacher1 = new Teacher(name1, pass1, tempDepList.get(i), ass1);
            teacher2 = new Teacher(name2, pass2, tempDepList.get(i), ass2);
            System.out.println(tempDepList.get(i).getTitle()+" Department Teachers:");
            System.out.println(teacher1);
            System.out.println(teacher2+"\n");

            //Add the teachers to the list of teachers
            teachers.add(teacher1);
            teachers.add(teacher2);
        }

        //Add the departments to the list of departments
        departments.add(csDepartment);
        departments.add(mathDepartment);
        departments.add(physicsDepartment);
        System.out.println("-------------\n");

    }
}
