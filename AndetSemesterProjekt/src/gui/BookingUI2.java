package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BookingUI2 {
    private BookingController bc;
    private JFrame frame;
    private JButton createBookingButton;
    private JButton seeCalendarButton;

    public BookingUI2() {
        bc = new BookingController();
        frame = new JFrame("Booking UI");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        createBookingButton = new JButton("Create Booking");
        createBookingButton.setBounds(50, 50, 200, 30);
        createBookingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createBooking();
            }
        });

        seeCalendarButton = new JButton("See Calendar");
        seeCalendarButton.setBounds(50, 100, 200, 30);
        seeCalendarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seeCalendar();
            }
        });

        frame.add(createBookingButton);
        frame.add(seeCalendarButton);
        frame.setVisible(true);
    }

    public void createBooking() {
        // Implementation for creating a booking
    }

    public void seeCalendar() {
        // Implementation for seeing the calendar
    }

    public static void main(String[] args) {
        new BookingUI2();
    }
}

class BookingController {
    // Implementation of BookingController
}

