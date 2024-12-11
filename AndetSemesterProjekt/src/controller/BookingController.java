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
import model.TimeSlot;

public class BookingController {
    private CustomerController cc;
    private EmployeeController ec;
    private ServiceController sc;
    private BookingDB bookingDB;
    private Map<Employee, List<Booking>> employeeBookings;
    private Booking booking;

    public BookingController() throws DataAccessException {
        cc = new CustomerController();
        ec = new EmployeeController();
        sc = new ServiceController(); // Initialisering af serviceController
        bookingDB = new BookingDB();
        employeeBookings = new HashMap<>();
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
        Customer customer = cc.findCustomerByPhoneNo(phoneNo);
        booking.setCustomer(customer);
        return customer;
    }

    public void setDate(LocalDate date) {
        booking.setBookingDate(LocalDateTime.of(date, booking.getBookingDate().toLocalTime()));
    }

    public void setStaringTime(LocalTime time) {
        if (booking.getBookingDate() == null) {
            booking.setBookingDate(LocalDateTime.of(LocalDate.now(), time)); // Hvis bookingDate er null, initialiser med dato og tid
        } else {
            LocalDate date = booking.getBookingDate().toLocalDate();
            booking.setBookingDate(LocalDateTime.of(date, time));
        }
    }

    public Booking completeBooking() throws DataAccessException {
        if (booking.getEmployee() == null) {
            throw new IllegalStateException("Employee is not set for the booking");
        }
        bookingDB.createBooking(booking);
        return booking;
    }

    public List<TimeSlot> findAvailableTimes(Employee employee, LocalDate date) throws DataAccessException {
        int employeeId = employee.getEmployeeId();
        List<TimeSlot> timeSlots = new ArrayList<>();
        LocalTime startTime = LocalTime.of(9, 0);
        LocalTime endTime = LocalTime.of(18, 0);

        List<Booking> bookings = bookingDB.findBookingByDate(date); // Hent bookinger fra databasen

        for (LocalTime time = startTime; time.isBefore(endTime); time = time.plusMinutes(30)) {
            BookingType status = BookingType.available;
            for (Booking booking : bookings) {
                if (booking.getEmployee() != null && booking.getEmployee().getEmployeeId() == employeeId) {
                    LocalTime bookingStart = booking.getBookingDate().toLocalTime();
                    LocalTime bookingEnd = bookingStart.plusMinutes(30);
                    if (!time.isBefore(bookingStart) && time.isBefore(bookingEnd)) {
                        status = BookingType.booked;
                        break;
                    }
                }
            }
            timeSlots.add(new TimeSlot(time, status));
        }

        // Sorter timeSlots i stigende rækkefølge
        Collections.sort(timeSlots, Comparator.comparing(TimeSlot::getTime));

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

        // Sorter tableData i stigende rækkefølge af tid
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
                List<TimeSlot> employeeTimeSlots = findAvailableTimes(employee, date);

                BookingType status = BookingType.available;
                for (TimeSlot slot : employeeTimeSlots) {
                    if (slot.getTime().equals(time)) {
                        status = slot.getStatus();
                        break;
                    }
                }
                statuses.add(status);
            }
            availableTimes.put(time, statuses);
        }

        return availableTimes;
    }

    public List<Employee> getAllEmployees() throws DataAccessException {
        return ec.getEmployees();
    }

    public List<Service> getAllServices() throws DataAccessException {
        return sc.findAllService();
    }

    public boolean isBookingAvailable(Object value) {
        return BookingType.available.equals(value);
    }
    
    public List<Object[]> updateTableData(LocalDate date) throws DataAccessException {
        List<Employee> employees = getAllEmployees();
        return getAvailableTimesTableData(employees, date);
    }
}


