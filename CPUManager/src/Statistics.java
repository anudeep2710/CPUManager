import java.util.List;

public class Statistics {

    // Method to calculate average waiting time
    public static double calculateAverageWaitingTime(List<Process> processList) {
        int totalWaitingTime = 0;
        for (Process process : processList) {
            totalWaitingTime += process.calculateWaitingTime(process.getStartTime());
        }
        return (double) totalWaitingTime / processList.size();
    }

    // Method to calculate average turnaround time
    public static double calculateAverageTurnaroundTime(List<Process> processList) {
        int totalTurnaroundTime = 0;
        for (Process process : processList) {
            totalTurnaroundTime += process.calculateTurnaroundTime(process.getCompletionTime());
        }
        return (double) totalTurnaroundTime / processList.size();
    }

    // Method to calculate maximum waiting time
    public static int calculateMaxWaitingTime(List<Process> processList) {
        int maxWaitingTime = Integer.MIN_VALUE;
        for (Process process : processList) {
            int waitingTime = process.calculateWaitingTime(process.getStartTime());
            if (waitingTime > maxWaitingTime) {
                maxWaitingTime = waitingTime;
            }
        }
        return maxWaitingTime;
    }

    // Method to calculate minimum turnaround time
    public static int calculateMinTurnaroundTime(List<Process> processList) {
        int minTurnaroundTime = Integer.MAX_VALUE;
        for (Process process : processList) {
            int turnaroundTime = process.calculateTurnaroundTime(process.getCompletionTime());
            if (turnaroundTime < minTurnaroundTime) {
                minTurnaroundTime = turnaroundTime;
            }
        }
        return minTurnaroundTime;
    }

    public static void calculateStatistics(List<Process> processList) {
        double averageWaitingTime = calculateAverageWaitingTime(processList);
        double averageTurnaroundTime = calculateAverageTurnaroundTime(processList);
        int maxWaitingTime = calculateMaxWaitingTime(processList);
        int minTurnaroundTime = calculateMinTurnaroundTime(processList);

        System.out.println("Average Waiting Time: " + averageWaitingTime);
        System.out.println("Average Turnaround Time: " + averageTurnaroundTime);
        System.out.println("Maximum Waiting Time: " + maxWaitingTime);
        System.out.println("Minimum Turnaround Time: " + minTurnaroundTime);
        System.out.println();
        System.out.println("Process\t\tWaiting Time\tTurnaround Time");
        for (Process process : processList) {
            System.out.println(process.getName() + "\t\t" + process.calculateWaitingTime(process.getStartTime()) + "\t\t" + process.calculateTurnaroundTime(process.getCompletionTime()));
        }
        System.out.println();
    }
}
