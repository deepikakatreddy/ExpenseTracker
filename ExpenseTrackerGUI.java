import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

public class ExpenseTrackerGUI extends JFrame {
    private ArrayList<Expense> expenses;
    private JTextArea expenseTextArea;

    public ExpenseTrackerGUI() {
        expenses = new ArrayList<>();
        expenseTextArea = new JTextArea();
        createUI();
    }

    private void createUI() {
        setTitle("Expense Tracker");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton addButton = new JButton("Add Expense");
        JButton viewButton = new JButton("View Expenses");
        JButton totalButton = new JButton("Total Expenses");

        buttonPanel.add(addButton);
        buttonPanel.add(viewButton);
        buttonPanel.add(totalButton);

        mainPanel.add(buttonPanel, BorderLayout.NORTH);
        mainPanel.add(new JScrollPane(expenseTextArea), BorderLayout.CENTER);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addExpense();
            }
        });

        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewExpenses();
            }
        });

        totalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateTotalExpenses();
            }
        });

        add(mainPanel);
    }

    private void addExpense() {
        String category = JOptionPane.showInputDialog("Enter expense category:");
        double amount = Double.parseDouble(JOptionPane.showInputDialog("Enter expense amount:"));
        String date = new SimpleDateFormat("MM/dd/yyyy").format(new Date());

        Expense expense = new Expense(category, amount, date);
        expenses.add(expense);

        expenseTextArea.append("Category: " + expense.category +
                ", Amount: $" + expense.amount +
                ", Date: " + expense.date + "\n");
    }

    private void viewExpenses() {
        if (expenses.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No expenses recorded yet.");
        } else {
            StringBuilder sb = new StringBuilder("Expense History:\n");
            for (Expense expense : expenses) {
                sb.append("Category: ").append(expense.category)
                        .append(", Amount: $").append(expense.amount)
                        .append(", Date: ").append(expense.date).append("\n");
            }
            expenseTextArea.setText(sb.toString());
        }
    }

    private void calculateTotalExpenses() {
        double total = 0;
        for (Expense expense : expenses) {
            total += expense.amount;
        }
        JOptionPane.showMessageDialog(this, "Total Expenses: $" + total);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ExpenseTrackerGUI().setVisible(true);
            }
        });
    }
}
