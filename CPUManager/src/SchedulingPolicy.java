import java.util.List;

public abstract class SchedulingPolicy {

    // Method to implement the scheduling policy
    public abstract void schedule(List<Process> processList, long seedValue);
}
