package org.library.Model;

public class ScheduledTask implements Runnable {
    @Override
    public void run() {
        for(Student s: Database.getListOfStudent()){
            int initialDueAmount=s.getDue_amount();
            s.updateDueAmount();
            int DueAmount=s.getDue_amount();
            if(DueAmount!=initialDueAmount){
                Database.updateStudentDue(s);
            }
        }
    }
}
