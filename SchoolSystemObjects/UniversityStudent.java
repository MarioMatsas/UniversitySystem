package SchoolSystemObjects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class UniversityStudent extends Student{
    private ArrayList<Assignment> assignments;
    private ArrayList<Assignment> gradedAssignments;


    public UniversityStudent(String name, int age, double grade, String password, ArrayList<Message> messages, Department department,
    ArrayList<Assignment> assignments, ArrayList<Assignment> gradedAssignments) {
        super(name, age, grade, password, messages, department);
        this.assignments = assignments;
        this.gradedAssignments = gradedAssignments;
    }

    public UniversityStudent(){
        
    }

    public String toString(){
        return "| Name: "+super.name+", Age: "+this.age+", Grade: "+this.grade+", Department: "+this.department.getTitle()+" of "+this.department.getUniversity()+" |";
    }

    //Setters
    void setMessages(ArrayList<Message> messages){
        this.messages = messages;
    }
    void setDepartment(Department department){
        this.department = department;
    }
    void setAssignmentsList(ArrayList<Assignment> assignments){
        this.assignments = assignments;
    }
    void setGradedAssignmentsList(ArrayList<Assignment> gradedAssignments){
        this.gradedAssignments = gradedAssignments;
    }
    void setAssignment(Assignment assignment){
        this.assignments.add(assignment);
    }
    void setGradedAssignment(Assignment assignment){
        this.gradedAssignments.add(assignment);
    }

    //Check messages
    public void checkMessages(){
        System.out.println("\n-------------\n");
        boolean forcedExit = false;
        Scanner scanner = new Scanner(System.in);
        ArrayList<Message> msgsTemp = new ArrayList<>();
        

        //Print all the acceptance/rejection letters the university student has received
        int count = 1; //Keep track of the printed numbers
        for (Message msg: this.getMessages()){
            System.out.println(count+") "+msg);
            
            count++;
            
            msgsTemp.add(msg);
        }
        //Let student choose if / which department he wants to join
        System.out.println("Choose the department you would like to join or choose none ('-1')");

        String tempChoice = scanner.nextLine();
        if (!tempChoice.equals("-1")){
            //Check the validity of the assignment choice
            boolean valid = tempChoice.matches("[0-9]+");
            while (valid == false || Integer.valueOf(tempChoice)>count || Integer.valueOf(tempChoice)<=0||
            messages.get(Integer.valueOf(tempChoice)-1).getAcceptanceOrNot().equals("Rejected")){
                System.out.println("Please pick one of the options above (make sure you are accepted by the department) or choose none ('-1')");
                tempChoice = scanner.nextLine();
                valid = tempChoice.matches("[0-9]+");
                if (tempChoice.equals("-1")){
                    forcedExit = true;
                    break;
                }
            }
            if (forcedExit){
                //If the university student doesnt choose any of the departments that have accepted them, then just delete all the messages
                ArrayList<Message> uniStudentMessages = new ArrayList<>();
                this.setMessages(uniStudentMessages);
            }
            else{
                int depChoice = Integer.valueOf(tempChoice);

                Department chosenDeartment = this.getMessages().get(depChoice-1).getDepartment();
                System.out.println(chosenDeartment);
    
                //Change the data of the university student so that it reflects the change of departments
                ArrayList<Message> uniStudentMessages = new ArrayList<>(); //Empty list of messages since they will all be delted after his choice
                Department uniStuDepartment = chosenDeartment;
                ArrayList<Assignment> uniAssignments = new ArrayList<>();
                ArrayList<Assignment> uniGradedAssignments = new ArrayList<>();
    
                this.setMessages(uniStudentMessages);
                this.setDepartment(uniStuDepartment);
                this.setAssignmentsList(uniAssignments);
                this.setGradedAssignmentsList(uniGradedAssignments);
    
                System.out.println(this);
    
                //Add university student to department
                ArrayList<UniversityStudent> unStdns = uniStuDepartment.getUniStudents();
                unStdns.add(this);
                chosenDeartment.setUniStudents(unStdns);
            }
           
        }
        else{
            //If the university student doesnt chose any of the departments that have accepted them, then just delete all the messages
            ArrayList<Message> uniStudentMessages = new ArrayList<>();
            this.setMessages(uniStudentMessages);
        }
        
    }

    //Complete assigmnments
    public void checkAssignments(){
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

            //Choose an assignment to answear or return to menu
            System.out.println("Choose the number of an assignment to answear it or return back to the menu ('-1')");

            String tempChoice = scanner.nextLine();
            if (tempChoice.equals("-1")){
                activeLoop = false;
            }
            else{
                //Check the validity of the assignment choice
                boolean valid = tempChoice.matches("[0-9]+");
                while (valid == false || Integer.valueOf(tempChoice)>=count || Integer.valueOf(tempChoice)<=0){
                    System.out.println("Please pick one of the options above or choose none ('-1')");
                    tempChoice = scanner.nextLine();
                    valid = tempChoice.matches("[0-9]+");
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

                    //Give your asnwear
                    System.out.println("Give your answear to the following problem:\n"+this.assignments.get(choice-1).getQuestion());
                    String answear = scanner.nextLine();
    
                    //Place the anwear in the assignment
                    this.assignments.get(choice-1).setAnswear(answear);
    
                    //Send the answeared Assignmnet back to the teacher
                    this.assignments.get(choice-1).getTeacher().setAssignments(this.assignments.get(choice-1));
    
                    //Remove the chosen Assignmnet from the list of assignments
                    assignments.remove(this.assignments.get(choice-1));
                }                
            }        
        }
    }

    //Check grades
    public void checkGrades(){
        //Go trough the list of assignments and prith each one of them
        for (Assignment asign: gradedAssignments){
            System.out.println(asign);
        }
    }
}
