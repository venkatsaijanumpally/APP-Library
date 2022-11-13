package org.library.Model;

public class ScheduledTask implements Runnable {
    @Override
    public void run() {
        for(Student s: Database.getListOfStudent()){
            s.updateDueAmount();
        }
    }
}
