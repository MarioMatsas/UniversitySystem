package SchoolSystemObjects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;


public class Teacher implements Serializable{
    private static final long serialVersionUID = 1L;
    private String name;
    private Department department;
    private ArrayList<Assignment> assignments;
    private int password;

    public Teacher(String name, int password, Department department, ArrayList<Assignment> assignments){
        this.name = name;
        this.password = password;
        this.department = department;
        this.assignments = assignments;
    }

    public Teacher(){
        
    }

    //Getters
    public String getName(){
        return this.name;
    }
    public int getPassword(){
        return this.password;
    }
    Department getDepartment(){
        return this.department;
    }
    ArrayList<Assignment> getAssignment(){
        return this.assignments;
    }

    //Setters 
    void setAssignments(Assignment assignment){
        this.assignments.add(assignment);
    }

    public String toString(){
        return "Mr/Mrs "+this.name+"| Password: "+this.password;
    }

    //Send assignments
    public void sendAssignments(){
        System.out.println("\n-------------\n");
        Scanner scanner = new Scanner(System.in);

        //Get assignment title and question
        System.out.println("Enter assignment title");
        String title = scanner.nextLine();
        System.out.println("Enter assignment question");
        String question = scanner.nextLine();

        //Go through all the UninersityStudents in this department and send them the assignment
        for (UniversityStudent uniS: this.department.getUniStudents()){
            Assignment assignment = new Assignment(title, question, this, uniS, "", -1);
            uniS.setAssignment(assignment);
        }
        System.out.println("\n-------------\n");
    }

    //Grade assignments
    public void gradeAssignment(){
        Scanner scanner = new Scanner(System.in);
        boolean activeLoop = true;
        boolean forcedExit = false;

        while (activeLoop){
            System.out.println("\n-------------\n");
            //Display all the assignmens
            int count = 1;
            for (Assignment assign: this.assignments){
                System.out.println(count+") "+assign.getTitle());
                count++;
            }
            
            //Choose an assignment to grade or return to menu
            System.out.println("Choose the number of an assignment to grade it or return back to the menu ('-1')");
            
            String tempChoice = scanner.nextLine();
            if (tempChoice.equals("-1")){
                activeLoop = false;
            }
            else{
                //Check the validity of the assignment choice
                boolean valid = tempChoice.matches("[0-9]+");
                while (valid == false || Integer.valueOf(tempChoice)>=count){
                    System.out.println("Please pick one of the options above or choose none ('-1')");
                    tempChoice = scanner.nextLine();
                    valid = tempChoice.matches("[0-9]+");
                    if (tempChoice.equals("-1")){
                        forcedExit = true;
                        break;
                    }
                }
                if (forcedExit){
                    activeLoop = false;
                }
                else{
                    int choice = Integer.valueOf(tempChoice);

                    //Enter the grade
                    System.out.println("Q: "+this.assignments.get(choice-1).getQuestion()+"\nA: "+this.assignments.get(choice-1).getAnswear()+"\nGrade:");
                    double grade = scanner.nextDouble();
    
                    //Place the anwear in the assignment
                    this.assignments.get(choice-1).setGrade(grade);
    
                    //Send the gradeed Assignmnet back to the university student
                    this.assignments.get(choice-1).getUniStudent().setGradedAssignment(this.assignments.get(choice-1));
    
                    //Remove the chosen Assignmnet from the list of assignments
                    assignments.remove(this.assignments.get(choice-1));
                }               
            }
            System.out.println("\n-------------\n");
        }
    }
    
}
