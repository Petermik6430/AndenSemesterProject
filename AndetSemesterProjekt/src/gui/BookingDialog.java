package gui;

import javax.swing.*;
import controller.BookingController;
import controller.ServiceController;
import db.DataAccessException;
import model.Booking;
import model.Employee;
import model.Service;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class BookingDialog extends JDialog {
	private JTextField txtPhone;
    private JComboBox<Service> comboBoxService;
    private JTextField txtNote;  // Tilføj tekstfelt til noter
    private BookingController bookingController;
    private ServiceController serviceController;
    private LocalTime startTime;
    private LocalDate selectedDate;
    private Employee employee;

    public BookingDialog(BookingController bookingController, Employee employee, LocalTime startTime, LocalDate date) throws DataAccessException {
        this.bookingController = bookingController;
        this.employee = employee;
        this.startTime = startTime;
        this.selectedDate = date;

        setTitle("Opret Booking");
        setBounds(100, 100, 450, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        serviceController = new ServiceController();

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(5, 2)); // Opdater layout til 5 rækker

        // Kunde telefonnummer
        JLabel lblPhone = new JLabel("Telefonnummer:");
        contentPanel.add(lblPhone);
        txtPhone = new JTextField();
        contentPanel.add(txtPhone);

        // Service
        JLabel lblService = new JLabel("Service:");
        contentPanel.add(lblService);
        comboBoxService = new JComboBox<>();
        List<Service> services = serviceController.findAllService();
        for (Service service : services) {
            comboBoxService.addItem(service);
        }
        contentPanel.add(comboBoxService);

        // Note
        JLabel lblNote = new JLabel("Note:");
        contentPanel.add(lblNote);
        txtNote = new JTextField();
        contentPanel.add(txtNote);

        getContentPane().add(contentPanel, BorderLayout.CENTER);

        // Knapper
        JPanel buttonPane = new JPanel();
        JButton btnOpret = new JButton("Opret");
        btnOpret.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    createBooking();
                } catch (DataAccessException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(BookingDialog.this, "Fejl ved oprettelse af booking.");
                }
            }
        });
        buttonPane.add(btnOpret);
        getContentPane().add(buttonPane, BorderLayout.SOUTH);
    }

    private void createBooking() throws DataAccessException {
        String phoneNo = txtPhone.getText();
        Service selectedService = (Service) comboBoxService.getSelectedItem();
        String note = txtNote.getText(); // Hent teksten fra note feltet

        // Sæt kunde, service, medarbejder, dato og tidspunkt i bookingController
        bookingController.createBooking();
        bookingController.selectCustomerByPhoneNo(phoneNo);
        bookingController.setService(selectedService);
        bookingController.setEmployee(employee);
        bookingController.setStaringTime(startTime);
        bookingController.setDate(selectedDate);
        bookingController.setNote(note); // Sæt noten

        // Gem booking i databasen
        Booking booking = bookingController.completeBooking();

        JOptionPane.showMessageDialog(this, "Booking oprettet med ID: " + booking.getBookingId());
    }
}
