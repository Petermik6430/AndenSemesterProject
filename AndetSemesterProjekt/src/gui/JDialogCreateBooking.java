/*
package gui;

import java.awt.BorderLayout;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import com.github.lgooddatepicker.components.CalendarPanel;
import com.github.lgooddatepicker.optionalusertools.CalendarListener;
import com.github.lgooddatepicker.zinternaltools.CalendarSelectionEvent;
import com.github.lgooddatepicker.zinternaltools.YearMonthChangeEvent;
import controller.EmployeeController;
import controller.BookingController;
import db.DataAccessException;
import model.BookingType;
import model.Employee;
import model.TimeSlot;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class JDialogCreateBooking extends JDialog {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private JTable table;
    private DefaultTableModel tableModel;
    private EmployeeController employeeController;
    private BookingController bookingController;

    /**
     * Launch the application.
     */
/*
    public static void main(String[] args) {
        try {
            JDialogCreateBooking dialog = new JDialogCreateBooking();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the dialog.
     */
    /*
    public JDialogCreateBooking() throws DataAccessException {
        // Initialiser instansvariabler
        employeeController = new EmployeeController();
        bookingController = new BookingController();

        List<Employee> employees = employeeController.getEmployees(); 
        
        setBounds(100, 100, 450, 634);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        CalendarPanel calendarPanel = new CalendarPanel();
        calendarPanel.addCalendarListener(new CalendarListener() {
            @Override
            public void selectedDateChanged(CalendarSelectionEvent arg0) {
                try {
                    dateSelected(arg0);
                } catch (DataAccessException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void yearMonthChanged(YearMonthChangeEvent arg0) {
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
                    if (value.equals(BookingType.available)) {
                        LocalTime time = (LocalTime) table.getValueAt(row, 0);
                        Employee employee = employees.get(col - 1);
                        openBookingDialog(employee, time, LocalDate.now());
                    }
                }
            }

            private void openBookingDialog(Employee employee, LocalTime time, LocalDate date) {
                try {
                    BookingDialog bookingDialog = new BookingDialog(employee, time, date); // Kald den korrekte konstruktør
                    bookingDialog.setVisible(true);
                } catch (DataAccessException e) {
                    e.printStackTrace();
                }
            }
        });

        table.setBounds(10, 341, 269, 243);
        getContentPane().add(new JScrollPane(table), BorderLayout.SOUTH);

        try {
            LocalDate today = LocalDate.now();
            fillTableWithAvailableTimes(tableModel, employees, today);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

        setVisible(true);
        
    }
    
    private void fillTableWithAvailableTimes(DefaultTableModel tableModel, List<Employee> employees, LocalDate date) throws DataAccessException {
        tableModel.setRowCount(0); // Ryd eksisterende rækker

        LocalTime startTime = LocalTime.of(9, 0);
        LocalTime endTime = LocalTime.of(18, 0);

        List<LocalTime> timeSlots = new ArrayList<>();
        for (LocalTime time = startTime; time.isBefore(endTime); time = time.plusMinutes(30)) {
            timeSlots.add(time);
        }

        for (LocalTime time : timeSlots) {
            Object[] rowData = new Object[employees.size() + 1];
            rowData[0] = time;

            for (int i = 0; i < employees.size(); i++) {
                Employee employee = employees.get(i);
                List<TimeSlot> employeeTimeSlots = bookingController.findAvailableTimes(employee, date);

                BookingType status = BookingType.available;
                for (TimeSlot slot : employeeTimeSlots) {
                    if (slot.getTime().equals(time)) {
                        status = slot.getStatus();
                        break;
                    }
                }

                rowData[i + 1] = status;
            }

            tableModel.addRow(rowData);
        }

        // Sørg for at opdatere tabellen
        updateTable();
    }

    private void updateTable() {
        ((DefaultTableModel) table.getModel()).fireTableDataChanged();
        table.repaint();
    }

    private void dateSelected(CalendarSelectionEvent arg0) throws DataAccessException {
        LocalDate selectedDate = arg0.getNewDate();
        List<Employee> employees = employeeController.getEmployees();

        try {
            fillTableWithAvailableTimes(tableModel, employees, selectedDate);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }
}

*/
package gui;

import java.awt.BorderLayout;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import com.github.lgooddatepicker.components.CalendarPanel;
import com.github.lgooddatepicker.optionalusertools.CalendarListener;
import com.github.lgooddatepicker.zinternaltools.CalendarSelectionEvent;
import com.github.lgooddatepicker.zinternaltools.YearMonthChangeEvent;
import controller.EmployeeController;
import controller.BookingController;
import db.DataAccessException;
import model.Booking;
import model.BookingType;
import model.Employee;
import model.TimeSlot;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class JDialogCreateBooking extends JDialog {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private JTable table;
    private DefaultTableModel tableModel;
    private EmployeeController employeeController;
    private BookingController bookingController;

    /**
     * Launch the application.
     */

    public static void main(String[] args) {
        try {
            JDialogCreateBooking dialog = new JDialogCreateBooking();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the dialog.
     */

    public JDialogCreateBooking() throws DataAccessException {
        // Initialiser instansvariabler
        employeeController = new EmployeeController();
        bookingController = new BookingController();

        List<Employee> employees = employeeController.getEmployees(); 
        
        setBounds(100, 100, 450, 634);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        CalendarPanel calendarPanel = new CalendarPanel();
        calendarPanel.addCalendarListener(new CalendarListener() {
            @Override
            public void selectedDateChanged(CalendarSelectionEvent arg0) {
                try {
                    dateSelected(arg0);
                } catch (DataAccessException e) {
                    e.printStackTrace();
                }
            }


			@Override
            public void yearMonthChanged(YearMonthChangeEvent arg0) {
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
            /**
			 * 
			 */
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
                    if (value.equals(BookingType.available)) {
                        LocalTime time = (LocalTime) table.getValueAt(row, 0);
                        Employee employee = employees.get(col - 1);
                        LocalDate selectedDate = calendarPanel.getSelectedDate();
                        openBookingDialog(employee, time, selectedDate);
                    }
                }
            }
            
            private void openBookingDialog(Employee employee, LocalTime time, LocalDate date) {
                try {
                    System.out.println("Opening booking dialog for date: " + date);
                    BookingDialog bookingDialog = new BookingDialog(employee, time, date); // Kald den korrekte konstruktør
                    bookingDialog.setVisible(true);
                    
                    // Opdater tabellen efter booking
                    fillTableWithAvailableTimes(tableModel, employeeController.getEmployees(), date);
                } catch (DataAccessException e) {
                    e.printStackTrace();
                }
            }

        });
    
        

        table.setBounds(10, 341, 269, 243);
        getContentPane().add(new JScrollPane(table), BorderLayout.SOUTH);

        try {
            LocalDate today = LocalDate.now();
            fillTableWithAvailableTimes(tableModel, employees, today);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

        setVisible(true);
        
    }
    
    private void fillTableWithAvailableTimes(DefaultTableModel tableModel, List<Employee> employees, LocalDate date) throws DataAccessException {
        if (date == null) {
            throw new DataAccessException("Date cannot be null", null);
        }
        System.out.println("Updating table for date: " + date);
        tableModel.setRowCount(0); // Ryd eksisterende rækker

        LocalTime startTime = LocalTime.of(9, 0);
        LocalTime endTime = LocalTime.of(18, 0);

        List<LocalTime> timeSlots = new ArrayList<>();
        for (LocalTime time = startTime; time.isBefore(endTime); time = time.plusMinutes(30)) {
            timeSlots.add(time);
        }

        for (LocalTime time : timeSlots) {
            Object[] rowData = new Object[employees.size() + 1];
            rowData[0] = time;

            for (int i = 0; i < employees.size(); i++) {
                Employee employee = employees.get(i);
                if (employee == null) {
                    throw new DataAccessException("Employee cannot be null", null);
                }

                List<TimeSlot> employeeTimeSlots = bookingController.findAvailableTimes(employee, date);

                BookingType status = BookingType.available;
                for (TimeSlot slot : employeeTimeSlots) {
                    if (slot.getTime().equals(time)) {
                        status = slot.getStatus();
                        break;
                    }
                }

                System.out.println("Time: " + time + ", Employee: " + employee.getFirstName() + ", Status: " + status);

                rowData[i + 1] = status;
            }

            tableModel.addRow(rowData);
        }

        System.out.println("Table updated for date: " + date);
        ((DefaultTableModel) table.getModel()).fireTableDataChanged();
        table.repaint();
    }


    private void dateSelected(CalendarSelectionEvent arg0) throws DataAccessException {
        LocalDate selectedDate = arg0.getNewDate();
        System.out.println("dateSelected: " + selectedDate);
        List<Employee> employees = employeeController.getEmployees();
        fillTableWithAvailableTimes(tableModel, employees, selectedDate);
    }
    




}

