package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import controller.BookingController;
import controller.ServiceController;
import db.DataAccessException;
import model.Booking;
import model.Customer;
import model.Employee;
import model.Service;

public class BookingDialog extends JDialog {
    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private JTextField txtPhone;
    private JComboBox<Service> comboBoxService;
    private Employee employee;
    private LocalTime time;
    private LocalDate date;
    private BookingController bookingController;
    private ServiceController serviceController;
    private JTable table;
    private DefaultTableModel tableModel;

    // Korrekt defineret konstruktør
    public BookingDialog(Employee employee, LocalTime time, LocalDate date) throws DataAccessException {
        this.employee = employee;
        this.time = time;
        this.date = date;
        this.bookingController = new BookingController(); // Initialiser bookingController
        this.serviceController = new ServiceController(); // Initialiser serviceController

        setTitle("Opret Booking");
        setBounds(100, 100, 450, 300);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        JLabel lblFindCustomerByPhoneNo = new JLabel("Find kunde");
        lblFindCustomerByPhoneNo.setBounds(108, 56, 84, 14);
        contentPanel.add(lblFindCustomerByPhoneNo);

        txtPhone = new JTextField();
        txtPhone.setBounds(181, 53, 150, 20);
        contentPanel.add(txtPhone);
        txtPhone.setColumns(10);

        JLabel lblNewLabel = new JLabel("Vælg service");
        lblNewLabel.setBounds(108, 87, 63, 14);
        contentPanel.add(lblNewLabel);

        comboBoxService = new JComboBox<>();
        comboBoxService.setBounds(181, 84, 150, 20);
        contentPanel.add(comboBoxService);
        
        // Hent og tilføj services til comboBox
        List<Service> services = serviceController.findAllService();
        for (Service service : services) {
            comboBoxService.addItem(service);
        }

        JPanel buttonPane = new JPanel();
        buttonPane.setBounds(0, 228, 434, 33);
        contentPanel.add(buttonPane);
        buttonPane.setLayout(null);

        JButton okButton = new JButton("Opret");
        okButton.setBounds(298, 5, 61, 23);
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String phoneNo = txtPhone.getText();
                    Service selectedService = (Service) comboBoxService.getSelectedItem();

                    // Find kunde via telefonnummer
                   Customer customer = bookingController.selectCustomer(phoneNo);
                    if (customer == null) {
                        JOptionPane.showMessageDialog(BookingDialog.this, "Kunde ikke fundet.");
                        return;
                    }

                    if (selectedService == null) {
                        JOptionPane.showMessageDialog(BookingDialog.this, "Vælg venligst en service.");
                        return;
                    }

                    // Opret booking
                    Booking booking = new Booking();
                    booking.setEmployee(employee);
                    booking.setCustomer(customer);
                    booking.setService(selectedService); // Brug setService til at sætte servicen
                    booking.setBookingDate(LocalDateTime.of(date, time));

                    // Gem booking i databasen
                   bookingController.createBooking(booking);

                    JOptionPane.showMessageDialog(BookingDialog.this, "Booking oprettet!");
                  //  updateTableAfterBooking();
                    dispose(); // Luk dialogen
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(BookingDialog.this, "Fejl ved oprettelse af booking.");
                }
            }
            
            
        });
        okButton.setActionCommand("OK");
        buttonPane.add(okButton);
        getRootPane().setDefaultButton(okButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBounds(364, 5, 65, 23);
        cancelButton.setActionCommand("Cancel");
        buttonPane.add(cancelButton);

        JButton btnTilbage = new JButton("Tilbage");
        btnTilbage.setBounds(10, 5, 75, 23);
        buttonPane.add(btnTilbage);
        btnTilbage.setActionCommand("OK");
    }
    /*
    private void updateTableAfterBooking() {
        try {
            LocalDate date = LocalDate.now(); // eller den specifikke dato du ønsker at opdatere
            List<Booking> bookings = bookingController.findBookingsByDate(date);
            tableModel.setRowCount(0); // Ryd tabel
            for (Booking booking : bookings) {
                tableModel.addRow(new Object[] {
                    booking.getBookingDate(),
                    booking.getEmployee().getFirstName(),
                    booking.getService().getName(),
                    booking.getCustomer().getFirstName(),
                    booking.getType().name()
                });
            }
            tableModel.fireTableDataChanged();
        } catch (DataAccessException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Fejl ved opdatering af tabel efter booking: " + e.getMessage());
        }
    }
*/
}


