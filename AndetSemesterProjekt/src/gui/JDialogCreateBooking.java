
package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import com.github.lgooddatepicker.components.CalendarPanel;
import com.github.lgooddatepicker.optionalusertools.CalendarListener;
import com.github.lgooddatepicker.zinternaltools.CalendarSelectionEvent;
import com.github.lgooddatepicker.zinternaltools.YearMonthChangeEvent;

import controller.BookingController;
import controller.EmployeeController;
import db.DataAccessException;
import model.Employee;

public class JDialogCreateBooking extends JDialog {
    private BookingController bookingController;
    private EmployeeController employeeController;
    private JTable table;
    private DefaultTableModel tableModel;
    private JPanel contentPanel = new JPanel();

    public JDialogCreateBooking() throws DataAccessException {
        setTitle("Book Tid");
        setBounds(100, 100, 450, 634);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        bookingController = new BookingController(); // Initialize bookingController
        employeeController = new EmployeeController(); // Initialize employeeController

        List<Employee> employees = employeeController.getEmployees();

        CalendarPanel calendarPanel = new CalendarPanel();
        calendarPanel.addCalendarListener(new CalendarListener() {
            @Override
            public void selectedDateChanged(CalendarSelectionEvent event) {
                try {
                    LocalDate selectedDate = event.getNewDate();
                    updateTable(selectedDate);
                } catch (DataAccessException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void yearMonthChanged(YearMonthChangeEvent event) {
                // TODO Auto-generated method stub
            }
        });
        calendarPanel.setBounds(0, 0, 279, 318);
        contentPanel.add(calendarPanel);

        Object[] columnNames = new Object[employees.size() + 1];
        columnNames[0] = "Tid";
        for (int i = 0; i < employees.size(); i++) {
            columnNames[i + 1] = employees.get(i).getFirstName();
        }

        tableModel = new DefaultTableModel(columnNames, 0) {
            private static final long serialVersionUID = 1L;

            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(tableModel);
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());
                int col = table.columnAtPoint(e.getPoint());
                if (col > 0) { // Undgå første kolonne
                    Object value = table.getValueAt(row, col);
                    if (bookingController.isBookingAvailable(value)) {
                        LocalTime time = (LocalTime) table.getValueAt(row, 0);
                        Employee employee = employees.get(col - 1);
                        LocalDate selectedDate = calendarPanel.getSelectedDate();
                        openBookingDialog(employee, time, selectedDate);
                    }
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void updateTable(LocalDate date) throws DataAccessException {
        List<Employee> employees = employeeController.getEmployees();
        List<Object[]> tableData = bookingController.updateTableData(date);

        tableModel.setRowCount(0);
        for (Object[] rowData : tableData) {
            tableModel.addRow(rowData);
        }
    }

    private void openBookingDialog(Employee employee, LocalTime time, LocalDate date) {
        try {
            bookingController.createBooking(); // Opret en ny booking
            bookingController.setEmployee(employee); // Sæt medarbejder
            bookingController.setStaringTime(time); // Sæt starttidspunkt
            BookingDialog bookingDialog = new BookingDialog(bookingController, employee, time, date);
            bookingDialog.setVisible(true);

            updateTable(date);
        } catch (DataAccessException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Fejl ved åbning af booking dialog.");
        }
    }

    @Override
    public void setVisible(boolean b) {
        super.setVisible(b);
        try {
            updateTable(LocalDate.now());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                JDialogCreateBooking dialog = new JDialogCreateBooking();
                dialog.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}

