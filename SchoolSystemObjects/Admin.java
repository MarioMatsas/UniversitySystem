package SchoolSystemObjects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Admin implements Serializable{
    private static final long serialVersionUID = 1L;
    private University university;
    private long password;

    public Admin(University university, long password){
        this.university = university;
        this.password = password;
    }

    public Admin(){
        
    }

    //Getters
    public University getUni(){
        return this.university;
    }
    public long getPassword(){
        return this.password;
    }

    public String toString(){
        return "| Admin of: "+this.university.getName()+",   Password: "+this.password+" |";
    }

    void getApplictions(ArrayList<Application> applications, ArrayList<Object[]> studentDepartementList){
        //Go through all the applications
        for (Application app: applications){
            ArrayList<Department> departmentList = app.getDepartmentList(); //Get all the departments that a student applied to
            for (Department department: departmentList){

                //Check if the student has applied to this admins university
                if (department.getUniversity().getName().equals(this.university.getName())){

                    //Add them to the studentDepartementList                   
                    Object[] stDepPair = {app.getStudent(), department};
                    studentDepartementList.add(stDepPair);
                } 
            }
        }
    }

    public void handleApplications(ArrayList<Application> applications, ArrayList<Object[]> studentDepartementList){
        Scanner scanner = new Scanner(System.in);
        boolean activeLoop = true;
        boolean forcedExit = false;
        ArrayList<Object[]> acceptedApplications = new ArrayList<>();

        //Get list of students and their applications
        getApplictions(applications, studentDepartementList);



        while (activeLoop){
            System.out.println("\n-------------\n");
            int count = 1;
            //Display all the applicants and create a list of applicants
            for (Object[] pair: studentDepartementList){
                System.out.println(count+") Student info: "+pair[0]+"   --->   Department of: "+((Department)pair[1]).getTitle());
                count++;
            }      

            //Choose an assignment to grade or return to menu
            System.out.println("Select the numbers of the applicant you want to accept or return back to the menu ('-1')");
                
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

                    //Find the index of the accepted student, on the student list and add them to the acceptedStudents list           
                    acceptedApplications.add(studentDepartementList.get(choice - 1));

                    //Remove the accepted applicant from the list of applicants
                    for (Application app: applications){
                        if (app.getStudent() == (Student)studentDepartementList.get(choice - 1)[0]){
                            ArrayList<Department> departmentList = app.getDepartmentList(); //Get all the departments that a student applied to
                            for (Department department: departmentList){
                                if (department == (Department)studentDepartementList.get(choice - 1)[1]){
                                    app.getDepartmentList().remove(department);
                                    break;
                                }
                            }
                        }
                    }
                }                             
            }
        }

        System.out.println(applications);
        for (Application app: applications){

            //For each application go through the list of universities the applicant has applied to
            ArrayList<Department> departmentList = app.getDepartmentList();
            for (int i=0; i<departmentList.size(); i++){

                //If one of the universities applied to matches the Admin's then that university is removed from the list
                if (departmentList.get(i).getUniversity().getName().equals(this.university.getName())){
                    app.getDepartmentList().remove(departmentList.get(i));
                }
            }
                
        }

        //Send each student a message stating whether they were accepted or not
        for (Object[] stDepPair: studentDepartementList){
            ArrayList<Message> messages = ((Student) stDepPair[0]).getMessages();
            Department dep = (Department) stDepPair[1];

            //Check if the student got accepted or not
            if (acceptedApplications.contains(stDepPair)){ 
                Message msg = new Message(dep, "Accepted");
                messages.add(msg);
            }
            else{
                Message msg = new Message(dep, "Rejected");
                messages.add(msg);
            }

            //Update the messages of each student that applied to this university
            Student student = (Student) stDepPair[0];
            student.setMessages(messages);

        }
        System.out.println("\n-------------\n");
    }
}
