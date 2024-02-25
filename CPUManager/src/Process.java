import java.util.List;

public  class Process {
    private int processId;
    private int arrivalTime;
    private int burstTime;

    private String status; // Status can be "Waiting", "Running", "Completed", etc.
    private int startTime; // Time at which the process starts execution
    private int completionTime; // Time at which the process completes execution

    // Constructor
    public Process(int processId, int arrivalTime, int burstTime) {
        this.processId = processId;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;

        this.status = "Waiting"; // Initialize status as waiting by default
        this.startTime = -1; // Initialize start time as -1 (not started)
        this.completionTime = -1; // Initialize completion time as -1 (not completed)
    }

    // Getter methods
    public int getProcessId() {
        return processId;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getBurstTime() {
        return burstTime;
    }


    public String getStatus() {
        return status;
    }

    public int getStartTime() {
        return startTime;
    }

    public int getCompletionTime() {
        return completionTime;
    }

    // Setter method to update process status
    public void setStatus(String status) {
        this.status = status;
    }

    // Method to update start time when process begins execution
    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    // Method to update completion time when process completes execution
    public void setCompletionTime(int completionTime) {
        this.completionTime = completionTime;
    }

    // Method to calculate remaining burst time
    public int calculateRemainingBurstTime(int currentTime) {
        if (status.equals("Completed")) {
            return 0; // If process is completed, remaining burst time is 0
        } else {
            return burstTime - (currentTime - startTime); // Remaining burst time is the difference between total burst time and time already executed
        }
    }

    // Method to check if the process has completed execution
    public boolean isCompleted() {
        return status.equals("Completed");
    }

    // Method to calculate response time (time taken from arrival to start)
    public int calculateResponseTime() {
        if (startTime == -1) {
            return -1; // If process has not started, response time is undefined
        } else {
            return startTime - arrivalTime; // Response time is the time taken from arrival to start
        }
    }
    public int calculateTurnAroundTime() {
        if (completionTime == -1) {
            return -1; // If process has not completed, turnaround time is undefined
        } else {
            return completionTime - arrivalTime; // Turnaround time is the time taken from arrival to completion
        }
    }
    public int calculateWaitingTime(int startTime) {
        if (this.startTime == -1) {
            return -1; // If process has not started, waiting time is undefined
        } else {
            return this.startTime - arrivalTime; // Waiting time is the time taken from arrival to start
        }
    }
    public int calculateCompletionTime() {
        return completionTime; // Completion time is the time at which the process completes execution
    }
    public int calculateResponseRatio() {
        return calculateResponseTime() / burstTime; // Response ratio is the response time divided by total burst time
    }
    public int calculateTurnAroundRatio() {
        return calculateTurnAroundTime() / burstTime; // Turnaround ratio is the turnaround time divided by total burst time
    }


    public int calculateTurnaroundTime(int completionTime) {
        return completionTime - arrivalTime; // Turnaround time is the time taken from arrival to completion
    }
    public static int  calculateTotalTime(List<Process> processList) {
        int totalTime = 0;
        for (Process process : processList) {
            int completionTime = process.getArrivalTime() + process.getBurstTime();
            totalTime = Math.max(totalTime, completionTime);
        }
        return totalTime;
    }


    public String getName() {
        return "P" + processId; // Process name is P<processId>
    }

    public void execute(int executionTime) {
        // Update the start time if the process is starting execution now
        if (startTime == -1) {
            startTime = Math.max(arrivalTime, 0); // Ensure start time is not before arrival time or negative
        }

        // Simulate execution by decrementing burst time
        burstTime -= executionTime;

        // If burst time becomes zero, mark the process as completed
        if (burstTime == 0) {
            status = "Completed";
            completionTime = startTime + executionTime; // Update completion time
        }
    }


    public void setBurstTime(int i) {
        this.burstTime = i;


    }
}
