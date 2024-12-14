package controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.BookingDB;
import db.DataAccessException;
import model.Booking;
import model.BookingType;
import model.Customer;
import model.Employee;
import model.Service;
import model.BookingUnit;

public class BookingController {
    private CustomerController customerController;
    private EmployeeController employeeController;
    private ServiceController serviceController;
    private Booking booking;
    private BookingDB bookingDB;


    public BookingController() throws DataAccessException {
        customerController = new CustomerController();
        employeeController = new EmployeeController();
        serviceController = new ServiceController();
        bookingDB = new BookingDB();
    }

    

    public Booking createBooking() {
        booking = new Booking();
        return booking;
    }

    public void setService(Service service) {
        booking.setService(service);
    }

    public void setNote(String note) {
        booking.setNote(note);
    }
    
    public void setEmployee(Employee employee) {
        booking.setEmployee(employee);
    }

    public Customer selectCustomerByPhoneNo(String phoneNo) throws DataAccessException {
        Customer customer = customerController.findCustomerByPhoneNo(phoneNo);
        booking.setCustomer(customer);
        return customer;
    }

    public void setDate(LocalDate date) {
        if (booking.getBookingDate() == null) {
            booking.setBookingDate(LocalDateTime.of(date, LocalTime.of(0, 0)));
        } else {
            booking.setBookingDate(LocalDateTime.of(date, booking.getBookingDate().toLocalTime()));
        }
    }


    public void setStaringTime(LocalTime time) {
         booking.setBookingDate(LocalDateTime.of(LocalDate.now(), time)); 
           LocalDate date = booking.getBookingDate().toLocalDate();
            booking.setBookingDate(LocalDateTime.of(date, time));
    }

    public Booking completeBooking() throws DataAccessException {
        if (booking.getEmployee() == null) {
            throw new IllegalStateException("Employee is not set for the booking");
        }
        if (booking.getService() == null) {
            throw new IllegalStateException("Service is not set for the booking");
        }
        if (booking.getBookingDate() == null) {
            throw new IllegalStateException("Booking date is not set for the booking");
        }
        if (booking.getNote() == null) {
            booking.setNote(""); // Sæt en tom note, hvis ingen note er angivet
        }

        booking.setBookingType(BookingType.booked); // Sæt default bookingtype, hvis ingen type er angivet
        
        bookingDB.createBooking(booking);
        return booking;
    }


    public List<BookingUnit> findAvailableTimes(Employee employee, LocalDate date) throws DataAccessException {
        int employeeId = employee.getEmployeeId();
        List<BookingUnit> timeSlots = new ArrayList<>();
        LocalTime startTime = LocalTime.of(9, 0);
        LocalTime endTime = LocalTime.of(18, 0);

        List<Booking> bookings = bookingDB.findBookingByDate(date);

        for (LocalTime time = startTime; time.isBefore(endTime); time = time.plusMinutes(30)) {
            BookingType status = BookingType.available;
            for (Booking booking : bookings) {
                if (booking.getEmployee() != null && booking.getEmployee().getEmployeeId() == employeeId) {
                    LocalTime bookingStart = booking.getBookingDate().toLocalTime();
                    LocalTime bookingEnd = bookingStart.plusMinutes(30);
                    if (!time.isBefore(bookingStart) && time.isBefore(bookingEnd)) {
                        status = BookingType.booked;
                    }
                }
            }
            timeSlots.add(new BookingUnit(time, status));
        }

      


        return timeSlots;
    }

    public List<Object[]> getAvailableTimesTableData(List<Employee> employees, LocalDate date) throws DataAccessException {
        List<Object[]> tableData = new ArrayList<>();
        Map<LocalTime, List<BookingType>> availableTimes = findAvailableTimesForAllEmployees(employees, date);

        for (Map.Entry<LocalTime, List<BookingType>> entry : availableTimes.entrySet()) {
            Object[] rowData = new Object[entry.getValue().size() + 1];
            rowData[0] = entry.getKey();
            for (int i = 0; i < entry.getValue().size(); i++) {
                rowData[i + 1] = entry.getValue().get(i);
            }
            tableData.add(rowData);
        }

   
        tableData.sort(Comparator.comparing(row -> (LocalTime) row[0]));

        return tableData;
    }

    private Map<LocalTime, List<BookingType>> findAvailableTimesForAllEmployees(List<Employee> employees, LocalDate date) throws DataAccessException {
        Map<LocalTime, List<BookingType>> availableTimes = new HashMap<>();
        LocalTime startTime = LocalTime.of(9, 0);
        LocalTime endTime = LocalTime.of(18, 0);

        List<LocalTime> timeSlots = new ArrayList<>();
        for (LocalTime time = startTime; time.isBefore(endTime); time = time.plusMinutes(30)) {
        	timeSlots.add(time);
        }

        for (LocalTime time : timeSlots) {
            List<BookingType> statuses = new ArrayList<>();
            for (Employee employee : employees) {
                List<BookingUnit> employeeTimeSlots = findAvailableTimes(employee, date);

                BookingType status = BookingType.available;
                for (BookingUnit unit : employeeTimeSlots) {
                    if (unit.getTime().equals(time)) {
                        status = unit.getStatus();     
                    }
                }
                statuses.add(status);
            }
            availableTimes.put(time, statuses);
        }

        return availableTimes;
    }

    public List<Employee> getAllEmployees() throws DataAccessException {
        return employeeController.getEmployees();
    }

    public List<Service> getAllServices() throws DataAccessException {
        return serviceController.findAllService();
    }

    public boolean isBookingAvailable(Object value) {
        return BookingType.available.equals(value);
    }
    
    public List<Object[]> updateTableDataForAllEmployees(LocalDate date) throws DataAccessException {
        List<Booking> bookings = bookingDB.updateBookingCalender(date);
        List<Object[]> tableData = new ArrayList<>();
        List<LocalTime> bookingUnits = createHalfHourBookingUnits();
        List<Employee> employees = getAllEmployees(); 

        for (LocalTime time : bookingUnits) {
            Object[] rowData = new Object[employees.size() + 1];
            rowData[0] = time; 

            for (int i = 0; i < employees.size(); i++) {
                rowData[i + 1] = "Ledig"; 
            }

            for (Booking booking : bookings) {
                if (booking.getBookingDate().toLocalTime().equals(time)) {
                    for (int i = 0; i < employees.size(); i++) {
                        if (employees.get(i).getEmployeeId() == booking.getEmployee().getEmployeeId()) {
                            rowData[i + 1] = "Booket";
                        }
                    }
                }
            }

            tableData.add(rowData);
        }
        return tableData;
    }

    private List<LocalTime> createHalfHourBookingUnits() {
        List<LocalTime> bookingUnits = new ArrayList<>();
        LocalTime startTime = LocalTime.of(9, 0);
        LocalTime endTime = LocalTime.of(18, 0);

        for (LocalTime time = startTime; time.isBefore(endTime); time = time.plusMinutes(30)) {
            bookingUnits.add(time);
        }
        return bookingUnits;
    }


}


