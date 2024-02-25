import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProcessManager {
    private List<Process> processList;

    // Constructor
    public ProcessManager() {
        this.processList = new ArrayList<>();
    }

    // Method to add a process to the list
    public void addProcess(Process process) {
        processList.add(process);
    }

    // Method to remove a process from the list
    public void removeProcess(Process process) {
        processList.remove(process);
    }

    // Method to sort processes based on burst time
    public void sortByBurstTime() {
        Collections.sort(processList, (p1, p2) -> p1.getBurstTime() - p2.getBurstTime());
    }

    // Method to sort processes based on arrival time
    public void sortByArrivalTime() {
        Collections.sort(processList, (p1, p2) -> p1.getArrivalTime() - p2.getArrivalTime());
    }

    // Method to filter processes by priority


    // Method to get the list of processes
    public List<Process> getProcessList() {
        return processList;
    }
}
