package gui;

import java.awt.BorderLayout;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.swing.JComboBox;
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
import javax.swing.JTable;

public class JDialogCreateBooking extends JDialog {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private JTable table;
    private DefaultTableModel tableModel;
    private EmployeeController employeeController;
    private BookingController bookingController;
    private JComboBox<Employee> employeeComboBox;

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
        bookingController.setDateTime(LocalDate.of(2024, 12, 02)); // Sæt datoen her

        List<Employee> employees = employeeController.getEmployees(); 
        employeeComboBox = new JComboBox<>(employees.toArray(new Employee[0]));
        employeeComboBox.setBounds(10, 10, 200, 25);
        contentPanel.add(employeeComboBox);
        
        setBounds(100, 100, 450, 634);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        CalendarPanel calendarPanel = new CalendarPanel();
        calendarPanel.addCalendarListener(new CalendarListener() {
            @Override
            public void selectedDateChanged(CalendarSelectionEvent arg0) {
                dateSelected(arg0);
            } 

            @Override
            public void yearMonthChanged(YearMonthChangeEvent arg0) {
                // TODO Auto-generated method stub
            }
        });
        calendarPanel.setBounds(0, 0, 279, 318);
        contentPanel.add(calendarPanel);

   //     List<Employee> employees = employeeController.getEmployees();

        Object[] columnNames = new Object[employees.size() + 1];
        columnNames[0] = "Tid";
        for (int i = 0; i < employees.size(); i++) {
            columnNames[i + 1] = employees.get(i).getFirstName();
        }

        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        table.setBounds(10, 341, 269, 243);
        add(new JScrollPane(table), BorderLayout.SOUTH);

        try {
        	LocalDate tody = LocalDate.now();
        	LocalDate.now(); Employee initialEmployee = (Employee) employeeComboBox.getSelectedItem();
            fillTableWithAvailableTimes(tableModel, initialEmployee, tody);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

        setVisible(true);
    }
    
    private void fillTableWithAvailableTimes(DefaultTableModel tableModel, Employee selectedEmployee, LocalDate date) throws DataAccessException {
        selectedEmployee = (Employee) employeeComboBox.getSelectedItem();
        if (selectedEmployee != null) {
            List<LocalTime> availableTimes = bookingController.findAvailableTimes(selectedEmployee, date); // Brug den valgte medarbejder og den valgte dato
            tableModel.setRowCount(0); // Ryd eksisterende rækker

            for (LocalTime time : availableTimes) {
                BookingType type = bookingController.getBookingTypeForTime(selectedEmployee, date, time);
                tableModel.addRow(new Object[]{time, type});
            }
        }
    }

    private void dateSelected(CalendarSelectionEvent arg0) {
        LocalDate selectedDate = arg0.getNewDate();
        Employee selectedEmployee = (Employee) employeeComboBox.getSelectedItem(); // Få valgt medarbejder fra JComboBox

        bookingController.setDateTime(selectedDate); // Sæt den valgte dato
        try {
            fillTableWithAvailableTimes(tableModel, selectedEmployee, selectedDate); // Opdater tabellen med ledige tider for den valgte medarbejder
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    
/*
    private void fillTableWithAvailableTimes(DefaultTableModel tableModel, Employee employee, LocalDate date) throws DataAccessException {
        // Brug den korrekt initialiserede instansvariabel
        List<LocalTime> availableTimes = bookingController.findAvailableTimes(employee, date);
        for (LocalTime time : availableTimes) {
            tableModel.addRow(new Object[]{time, "ledig", "ledig", "ledig"});
        }
    }
 */  
  /*  
    private void fillTableWithAvailableTimes(DefaultTableModel tableModel) throws DataAccessException {
        Employee selectedEmployee = (Employee) employeeComboBox.getSelectedItem();
        LocalDate date = LocalDate.now();
        if (selectedEmployee != null) {
            List<LocalTime> availableTimes = bookingController.findAvailableTimes(selectedEmployee, date); // Bruger den valgte medarbejder og dagens dato for eksempel
            tableModel.setRowCount(0); // Ryd eksisterende rækker

            for (LocalTime time : availableTimes) {
            	BookingType type = bookingController.getBookingTypeForTime(selectedEmployee, date, time);
                tableModel.addRow(new Object[]{time, type});
            }
        }
    }


    private void dateSelected(CalendarSelectionEvent arg0) {
        LocalDate selectedDate = arg0.getNewDate();
        Employee selectedEmployee = (Employee) employeeComboBox.getSelectedItem();
        bookingController.setDateTime(selectedDate); // Sæt den valgte dato

        try {
            fillTableWithAvailableTimes(tableModel); // Opdater tabellen med ledige tider for den valgte medarbejder
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }
    */

/*
    private void dateSelected(CalendarSelectionEvent arg0) {
        LocalDate selectedDate = arg0.getNewDate();
        JComboBox selectedEmployee = (Employee) employeeComboBox.getSelectedItem();
        
        bookingController.setDateTime(selectedDate);
        try {
        	fillTableWithAvailableTimes(tableModel, selectedEmployee, selectedDate);
        } catch (DataAccessException e) {
        	e.printStackTrace();
        }
    	// TODO Auto-generated method stub
    }
    */
}



/*
 * package gui;
 * 
 * import java.awt.BorderLayout; import java.awt.FlowLayout; import
 * java.time.LocalDate; import java.time.LocalTime; import java.util.List;
 * 
 * import javax.swing.JButton; import javax.swing.JDialog; import
 * javax.swing.JPanel; import javax.swing.border.EmptyBorder; import
 * javax.swing.table.DefaultTableModel; import javax.swing.JScrollPane;
 * 
 * import com.github.lgooddatepicker.components.DateTimePicker; import
 * com.github.lgooddatepicker.optionalusertools.CalendarListener; import
 * com.github.lgooddatepicker.zinternaltools.CalendarSelectionEvent; import
 * com.github.lgooddatepicker.zinternaltools.YearMonthChangeEvent;
 * 
 * import controller.EmployeeController; import db.DataAccessException; import
 * controller.BookingController; import model.Employee;
 * 
 * import com.github.lgooddatepicker.components.CalendarPanel; import
 * javax.swing.JTable;
 * 
 * public class JDialogCreateBooking extends JDialog {
 * 
 * private static final long serialVersionUID = 1L; private final JPanel
 * contentPanel = new JPanel(); private JTable table; private DefaultTableModel
 * tableModel; EmployeeController employeeController; BookingController
 * bookingController;
 * 
 * 
 *//**
	 * Launch the application.
	 */
/*
 * public static void main(String[] args) { try {
 * 
 * JDialogCreateBooking dialog = new JDialogCreateBooking();
 * dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
 * dialog.setVisible(true); } catch (Exception e) { e.printStackTrace(); } }
 * 
 *//**
	 * Create the dialog.
	 * 
	 * @throws DataAccessException
	 *//*
		 * public JDialogCreateBooking() throws DataAccessException { EmployeeController
		 * employeeController = new EmployeeController(); BookingController
		 * bookingController = new BookingController();
		 * bookingController.setDateTime(LocalDate.now()); setBounds(100, 100, 450,
		 * 634); getContentPane().setLayout(new BorderLayout());
		 * contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		 * getContentPane().add(contentPanel, BorderLayout.CENTER);
		 * contentPanel.setLayout(null);
		 * 
		 * CalendarPanel calendarPanel = new CalendarPanel();
		 * calendarPanel.addCalendarListener(new CalendarListener() {
		 * 
		 * @Override public void selectedDateChanged(CalendarSelectionEvent arg0) {
		 * dateSelected(arg0);
		 * 
		 * }
		 * 
		 * 
		 * 
		 * @Override public void yearMonthChanged(YearMonthChangeEvent arg0) { // TODO
		 * Auto-generated method stub
		 * 
		 * }
		 * 
		 * }); calendarPanel.setBounds(0, 0, 279, 318); contentPanel.add(calendarPanel);
		 * 
		 * //table = new JTable(); // table.setBounds(10, 341, 269, 243); //
		 * contentPanel.add(table); List<Employee> employees =
		 * employeeController.getEmployees();
		 * 
		 * Object[] columnNames = new Object[employees.size() + 1]; columnNames[0] =
		 * "Tid"; for (int i = 0; i < employees.size(); i++) { columnNames[i + 1] =
		 * employees.get(i).getFirstName(); }
		 * 
		 * tableModel = new DefaultTableModel(columnNames, 0); table = new
		 * JTable(tableModel); table.setBounds(10, 341, 269, 243); add(new
		 * JScrollPane(table), BorderLayout.SOUTH); try {
		 * fillTableWithAvailableTimes(tableModel); } catch (DataAccessException e) {
		 * e.printStackTrace(); } setVisible(true);
		 * 
		 * 
		 * 
		 * }
		 * 
		 * private void fillTableWithAvailableTimes(DefaultTableModel tableModel) throws
		 * DataAccessException { List<LocalTime> availableTimes =
		 * bookingController.findAvailableTimes(); for (LocalTime time : availableTimes)
		 * { tableModel.addRow(new Object[] { time, "ledig","ledig", "ledig"}); }
		 * 
		 * }
		 * 
		 * 
		 * 
		 * private void dateSelected(CalendarSelectionEvent arg0) { // TODO
		 * Auto-generated method stub
		 * 
		 * } }
		 */