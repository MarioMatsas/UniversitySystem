package SchoolSystemObjects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Student implements Serializable{
    private static final long serialVersionUID = 1L;
    protected String name;
    protected int age;
    protected double grade;
    private String password;
    protected ArrayList<Message> messages;
    protected Department department;

    public Student(String name, int age, double grade, String password, ArrayList<Message> messages, Department department){
        this.name = name;
        this.age = age;
        this.grade = grade;
        this.password = password;
        this.messages = messages;
        this.department = department;
    }

    public Student(){
        
    }

    //Getters
    public String getName(){
        return this.name;
    }
    int getAge(){
        return this.age;
    }
    double getGrade(){
        return this.grade;
    }
    public String getPassword(){
        return this.password;
    }
    ArrayList<Message> getMessages(){
        return this.messages;
    }
    Department getDepartment(){
        return this.department;
    }

    //Setters
    void setMessages(ArrayList<Message> messages){
        this.messages = messages;
    }
    void setDepartment(Department department){
        this.department = department;
    }


    public String toString(){
        return "| Name: "+this.name+", Age: "+this.age+", Grade: "+this.grade+" |";
    }

    //Prints all the available universities, regardless of whether the student can apply or not
    public void viewUnis(ArrayList<University> list){
        System.out.println("\n-------------\n");
        System.out.println("----- Here is a list of all the universities -----:");

        //Keep track of the numbers displayed
        int count = 1;

        for (University uni: list){  
            //Print all the available universities      
            System.out.println(count+") " + uni.getName() + " -----> grade required: " + uni.getRequiredGrade());
            count++;       
        }
    }

    //Takes all the departments as an input and return a list of Departments the student can apply to
    public ArrayList<Department> findDepartments(ArrayList<Department> departments){
        //List that houses the universities a student can apply to
        ArrayList<Department> applyDepartments = new ArrayList<>();

        //Find univerisities the student can apply to
        for (Department department: departments){
            if ( this.grade >= department.getRequiredGrade()){
                applyDepartments.add(department);
            }
        }
        return applyDepartments;
    }

    //Takes list of departments the student can apply to, gives him the choice to choose where to apply
    //and adds their application to a larger application list
    public void applyToUni(ArrayList<Department> list, ArrayList<Application> applicationList){
        boolean activeLoop = true;
        boolean forcedExit = false;

        //Allow student to choose the universities they want to apply to
        Scanner scanner = new Scanner(System.in);
        ArrayList<Department> desiredDepartments = new ArrayList<>();

        while (activeLoop){  
            System.out.println("\n-------------\n");
            //Keep track of unirsity list number
            int count = 1;    
            //Choose an assignment to grade or return to menu
            System.out.println("\nSelect the schools you would like to apply to or return back to the menu ('-1')");
            for (Department department: list){
                System.out.println(count+") " +department.getUniversity().getName() + " : Department of " + department.getTitle() + " -----> grade required: " + department.getRequiredGrade());
                count++;           
            }
            
            String tempChoice = scanner.nextLine();
            if (tempChoice.equals("-1")){
                activeLoop = false;
            }
            else{
                //Check the validity of the assignment choice
                boolean valid = tempChoice.matches("[0-9]+");
                while (valid == false || Integer.valueOf(tempChoice)>=count || Integer.valueOf(tempChoice)<=0){
                    System.out.println("Please pick one of the options above");
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

                    //Find the indexes of the accepted students, on the student list and add them to the acceptedStudents list           
                    desiredDepartments.add(list.get(choice - 1));
                    //Remove the chosen department from the list of options
                    list.remove(list.get(choice - 1));
                }
            }
        }

        //Add the application into the application list
        Application application = new Application(this, desiredDepartments);
        applicationList.add(application);
        
    }

    public boolean checkMessages(ArrayList<Student> students, ArrayList<UniversityStudent> uniStudents, boolean studentRemoved){
        System.out.println("\n-------------\n");
        boolean forcedExit = false;
        ArrayList<Message> msgsTemp = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        //Print all the acceptance/rejection letters the student has received
        int count = 1; //Keep track of the printed numbers
        for (Message msg: this.messages){
            System.out.println(count+") "+msg);
            
            count++;
            
            msgsTemp.add(msg);
        }
        //Let student choose if / which department he wants to join
        System.out.println("Choose the department you would like to join or choose none ('-1')");
        
        //Check the department choice for validity
        String tempDepChoice = scanner.nextLine();
        if (!tempDepChoice.equals("-1")){
            boolean valid = tempDepChoice.matches("[0-9]+");
            while (valid == false || Integer.valueOf(tempDepChoice)>messages.size() || Integer.valueOf(tempDepChoice)<=0 ||
            messages.get(Integer.valueOf(tempDepChoice)-1).getAcceptanceOrNot().equals("Rejected")){
                System.out.println("Please pick one of the options above (make sure you are accepted by the department) or choose none ('-1')");
                tempDepChoice = scanner.nextLine();
                valid = tempDepChoice.matches("[0-9]+");
                if (tempDepChoice.equals("-1")){
                    forcedExit = true;
                    break;
                }
            }
            if (forcedExit){
                ArrayList<Message> studentMessages = new ArrayList<>();
                this.setMessages(studentMessages);
                return false;
            }
            else{
                int depChoice = Integer.valueOf(tempDepChoice);
    
                Department chosenDepartment = messages.get(depChoice-1).getDepartment();
                System.out.println(chosenDepartment);
        
                //Get all the data from the Student that chose the department and use it to create a new UniversityStudent with the same info
                ArrayList<Message> uniStudentMessages = new ArrayList<>(); //Empty list of messages since they will all be delted after his choice
                ArrayList<Assignment> uniAssignments = new ArrayList<>();
                ArrayList<Assignment> uniGradedAssignments = new ArrayList<>();
        
                UniversityStudent uniStudent = new UniversityStudent(this.name, this.age, this.grade,
                this.password, uniStudentMessages, chosenDepartment, uniAssignments, uniGradedAssignments);
                System.out.println(uniStudent);
                uniStudents.add(uniStudent);
        
                //Add student to department
                ArrayList<UniversityStudent> unStdns = chosenDepartment.getUniStudents();
                unStdns.add(uniStudent);
                chosenDepartment.setUniStudents(unStdns);
                System.out.println(chosenDepartment);
        
                //Since the student is now a university student, we can delete the Student object
                students.remove(this);
                System.out.println("\n-------------\n");
                return true;
            }
        }
        else{
            ArrayList<Message> studentMessages = new ArrayList<>();
            this.setMessages(studentMessages);
            return false;
        }
 
    }
}
