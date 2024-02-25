import java.util.List;

public class RoundRobin_Scheduler extends CPU_Scheduler {
    private int timeQuantum;

    public RoundRobin_Scheduler(List<Process> processList, int timeQuantum) {
        super(processList);
        this.timeQuantum = timeQuantum;
    }

    @Override
    public void schedule() {
        int currentTime = 0;

        while (!processList.isEmpty()) {
            for (int i = 0; i < processList.size(); i++) {
                Process process = processList.get(i);

                if (process.getArrivalTime() <= currentTime) {
                    int executionTime = Math.min(timeQuantum, process.getBurstTime());
                    process.setStartTime(currentTime);
                    process.setCompletionTime(currentTime + executionTime);
                    process.setStatus("Completed");

                    // Subtract the execution time from the process burst time
                    process.setBurstTime(process.getBurstTime() - executionTime);

                    // If the process is not completed, move it to the end of the list
                    if (process.getBurstTime() > 0) {
                        processList.remove(i);
                        processList.add(process);
                    } else {
                        // Process completed, remove it from the list
                        processList.remove(i);
                    }

                    break; // Move to the next time unit
                }
            }
            currentTime++; // Increment time
        }
    }
}
