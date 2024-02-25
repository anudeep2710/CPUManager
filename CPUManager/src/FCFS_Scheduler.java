import java.util.List;

public class FCFS_Scheduler extends CPU_Scheduler {

    // Constructor
    public FCFS_Scheduler(List<Process> processList) {
        super(processList);
    }

    // Implementation of FCFS scheduling algorithm
    @Override
    public void schedule() {
        // Sort processes based on arrival time
        sortProcessListByArrivalTime();

        // Initialize current time
        int currentTime = 0;

        // Process each job in the order they arrive
        for (Process process : processList) {
            // Update process status to Running
            process.setStatus("Running");

            // Set start time
            process.setStartTime(currentTime);

            // Execute the process
            executeProcess(process);

            // Update current time to account for process execution time
            currentTime += process.getBurstTime();

            // Set completion time
            process.setCompletionTime(currentTime);

            // Update process status to Completed
            process.setStatus("Completed");
        }
    }

    // Method to execute a process
    private void executeProcess(Process process) {
        // Simulate execution by printing the process ID and execution time
        System.out.println("Executing Process " + process.getProcessId() + " for " + process.getBurstTime() + " units of time.");
        // In a real system, this method would perform the actual execution of the process.
    }

    // Method to print a summary of the scheduling results
    public void printSchedulingSummary() {
        System.out.println("FCFS Scheduling Summary:");
        for (Process process : processList) {
            System.out.println("Process " + process.getProcessId() + ":");
            System.out.println("- Arrival Time: " + process.getArrivalTime());
            System.out.println("- Burst Time: " + process.getBurstTime());
            System.out.println("- Start Time: " + process.getStartTime());
            System.out.println("- Completion Time: " + process.getCompletionTime());
            System.out.println("- Turnaround Time: " + process.calculateTurnaroundTime(process.getCompletionTime()));
            System.out.println("- Waiting Time: " + process.calculateWaitingTime(process.getStartTime()));
            System.out.println();
        }
    }
}
