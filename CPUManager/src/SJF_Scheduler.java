import java.util.List;

public class SJF_Scheduler extends CPU_Scheduler {

    public SJF_Scheduler(List<Process> processList) {
        super(processList);
    }

    @Override
    public void schedule() {
        // Sort processes based on burst time (SJF)
        sortProcessListByBurstTime();

        // Initialize the current time
        int currentTime = 0;

        // Iterate through the sorted process list
        for (Process process : processList) {
            // Set the start time of the process
            process.setStartTime(Math.max(currentTime, process.getArrivalTime()));

            // Update the current time to account for the process execution
            currentTime = process.getStartTime() + process.getBurstTime();

            // Set the completion time of the process
            process.setCompletionTime(currentTime);

            // Update the status of the process
            process.setStatus("Completed");
        }
    }

    private void sortProcessListByBurstTime() {
        processList.sort((p1, p2) -> p1.getBurstTime() - p2.getBurstTime()); // Sort the process list by burst time using a lambda expression. The lambda expression compares the burst times of the two processes and returns the difference. The sort method uses the compareTo method of the Process class to compare the burst times. The compareTo method compares the burst times and returns a negative number if p1 has a smaller burst time than p2, a positive number if p1 has a larger burst time than p2, and 0 if the burst times are equal. The sort method sorts the process list in ascending order based on the burst times. The lambda expression is used to provide a custom comparison function for the sort method. The sort method sorts the process list based on the burst times in ascending order.
        System.out.println("Process List sorted by burst time (SJF):");
        printProcessList(); // Print the sorted process list after sorting by burst time.
        System.out.println(); // Print a new line.
        System.out.println("SJF Scheduling Summary:");
        printSchedulingSummary(); // Print the scheduling summary after sorting by burst time.

    }

    private void printSchedulingSummary() {
        int totalTurnaroundTime = 0;
        int totalWaitingTime = 0;
        for (Process process : processList) {
            totalTurnaroundTime += process.calculateTurnaroundTime(process.getCompletionTime());
            totalWaitingTime += process.calculateWaitingTime(process.getStartTime());
        }
        System.out.println("- Average Turnaround Time: " + (double) totalTurnaroundTime / processList.size());
        System.out.println("- Average Waiting Time: " + (double) totalWaitingTime / processList.size());
        System.out.println(); // Print a new line.
        System.out.println("SJF Scheduling Summary:"); // Print the scheduling summary.
        printProcessList(); // Print the sorted process list after sorting by burst time.
        System.out.println(); // Print a new line.
        System.out.println("SJF Scheduling Summary:"); // Print the scheduling summary.
        printProcessList(); // Print the sorted process list after sorting by burst time.
        System.out.println(); // Print a new line.

    }

    private void printProcessList() {
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
        System.out.println(); // Print a new line.
        System.out.println("Total number of processes: " + processList.size()); // Print the total number of processes.
        System.out.println(); // Print a new line.
    }


}
