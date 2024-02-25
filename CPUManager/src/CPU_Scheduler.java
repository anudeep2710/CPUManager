import java.util.Collections;
import java.util.List;

public abstract class CPU_Scheduler {
    protected List<Process> processList;

    // Constructor
    public CPU_Scheduler(List<Process> processList) {
        this.processList = processList;
    }

    // Abstract method to implement scheduling algorithm
    public abstract void schedule();

    // Method to add a process to the scheduler
    public void addProcess(Process process) {
        processList.add(process);
    }

    // Method to remove a process from the scheduler
    public void removeProcess(Process process) {
        processList.remove(process);
    }

    // Method to get the list of processes in the scheduler
    public List<Process> getProcessList() {
        return processList;
    }
    public void sortProcessListByArrivalTime() {
        processList.sort((p1, p2) -> p1.getArrivalTime() - p2.getArrivalTime());
    }

    // Method to check if the process list is empty
    public boolean isProcessListEmpty() {
        return processList.isEmpty();
    }

    // Method to get the number of processes in the scheduler
    public int getNumProcesses() {
        return processList.size();
    }

    // Method to clear all processes from the scheduler
    public void clearProcessList() {
        processList.clear();
    }

    // Method to display the details of all processes in the scheduler
    public void displayProcessDetails() {
        System.out.println("Process Details:");
        for (Process process : processList) {
            System.out.println("Process ID: " + process.getProcessId());
            System.out.println("Arrival Time: " + process.getArrivalTime());
            System.out.println("Burst Time: " + process.getBurstTime());
            System.out.println("Status: " + process.getStatus());
            System.out.println();
        }
    }
    public void displayProcessDetails(Process process) {
        System.out.println("Process Details:");
        System.out.println("Process ID: " + process.getProcessId());
        System.out.println("Arrival Time: " + process.getArrivalTime());
        System.out.println("Burst Time: " + process.getBurstTime());
        System.out.println("Status: " + process.getStatus());
        System.out.println();



    }




}
