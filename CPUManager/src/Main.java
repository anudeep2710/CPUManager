import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class Main extends JFrame {

    private DefaultTableModel tableModel;
    private JTable table;
    private JButton addButton;
    private JButton submitButton;
    private JRadioButton sjfRadioButton;
    private JRadioButton fcfsRadioButton;
    private JRadioButton roundRobinRadioButton;

    private ButtonGroup policyGroup;
    private List<Process> processList;
    private JTextArea textArea;

    public Main() {
        setTitle("Process Input");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initComponents();
    }

    private void initComponents() {
        processList = new ArrayList<>();

        // Initialize table with columns
        String[] columnNames = {"Process ID", "Arrival Time", "Burst Time"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);

        // Add table to scroll pane
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Add button to add a new row to the table
        addButton = new JButton("Add Process");
        addButton.addActionListener(e -> addRow());

        // Create radio buttons for selecting scheduling policy
        sjfRadioButton = new JRadioButton("Shortest Job First (SJF)");
        fcfsRadioButton = new JRadioButton("First-Come, First-Served (FCFS)");
        roundRobinRadioButton = new JRadioButton("Round Robin");
        policyGroup = new ButtonGroup();
        policyGroup.add(sjfRadioButton);
        policyGroup.add(fcfsRadioButton);
        policyGroup.add(roundRobinRadioButton);

        // Add radio buttons to a panel
        JPanel radioButtonPanel = new JPanel();
        radioButtonPanel.add(sjfRadioButton);
        radioButtonPanel.add(fcfsRadioButton);
        radioButtonPanel.add(roundRobinRadioButton);
        add(radioButtonPanel, BorderLayout.NORTH);

        // Add button to submit process list
        submitButton = new JButton("Submit");
        submitButton.addActionListener(e -> submit());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(submitButton);
        add(buttonPanel, BorderLayout.SOUTH);


    }

    private void addRow() {
        tableModel.addRow(new Object[]{"", "", ""});
    }

    private void submit() {
        processList = getProcessList(); // Initialize processList before calling getProcessList()
        if (processList != null) { // Check if processList is not null
            // Ask the user for the number of simulations and seed value through dialog boxes
            int numberOfRuns = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter the number of simulations:"));
            long seedValue = Long.parseLong(JOptionPane.showInputDialog(this, "Enter the seed value for random number generation:"));

            // Determine the selected scheduling policy
            SchedulingPolicy scheduler = null;
            int timeQuantum;
            if (sjfRadioButton.isSelected()) {
                scheduler = new SJF_Policy();
            } else if (fcfsRadioButton.isSelected()) {
                scheduler = new FCFS_Policy();
            } else if (roundRobinRadioButton.isSelected()) {
                // Get time quantum input if Round Robin is selected
                try {
                    timeQuantum = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter the time quantum:"));
                    // Save the process list for Round Robin policy
                    RoundRobin_Policy roundRobinPolicy = new RoundRobin_Policy(timeQuantum);
                    roundRobinPolicy.setProcessList(processList);
                    scheduler = roundRobinPolicy;
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Invalid time quantum input.", "Error", JOptionPane.ERROR_MESSAGE);
                    return; // Return without proceeding if time quantum input is invalid
                }
            }

            if (scheduler == null) {
                JOptionPane.showMessageDialog(this, "Please select a scheduling policy.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Create a visualizer
            UI visualizer = new DesktopUI("Scheduler Visualization", 800, 600); // Use DesktopUI for visualization

            int totaltime = Process.calculateTotalTime(processList);

            // Create a simulator
            Simulator simulator = new Simulator(processList, scheduler, totaltime, visualizer);

            // Run the simulation
            simulator.runSimulation(numberOfRuns, seedValue);

            // Calculate statistics
            Statistics.calculateStatistics(processList);

            // Print turnaround time and waiting time for each process

            // Display scheduling results using the GUI visualizer
            GUIVisualizer guiVisualizer = new GUIVisualizer();
            guiVisualizer.visualize(processList);
        }
    }


    // Method to retrieve the process list
    private List<Process> getProcessList() {
        List<Process> processList = new ArrayList<>();
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            String processIdStr = tableModel.getValueAt(i, 0).toString();
            String arrivalTimeStr = tableModel.getValueAt(i, 1).toString();
            String burstTimeStr = tableModel.getValueAt(i, 2).toString();

            // Check if any of the values are empty
            if (processIdStr.isEmpty() || arrivalTimeStr.isEmpty() || burstTimeStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                return null; // Return null indicating an error
            }

            try {
                // Parse values into integers
                int processId = Integer.parseInt(processIdStr);
                int arrivalTime = Integer.parseInt(arrivalTimeStr);
                int burstTime = Integer.parseInt(burstTimeStr);

                // Create new process and add to the list
                processList.add(new Process(processId, arrivalTime, burstTime));
            } catch (NumberFormatException e) {
                // Handle invalid number format
                JOptionPane.showMessageDialog(this, "Invalid input format.", "Error", JOptionPane.ERROR_MESSAGE);
                return null; // Return null indicating an error
            }
        }
        return processList;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main main = new Main();
            main.setVisible(true);
        });
    }
}
