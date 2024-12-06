package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.github.lgooddatepicker.components.DateTimePicker;
import com.github.lgooddatepicker.optionalusertools.CalendarListener;
import com.github.lgooddatepicker.zinternaltools.CalendarSelectionEvent;
import com.github.lgooddatepicker.zinternaltools.YearMonthChangeEvent;
import com.github.lgooddatepicker.components.CalendarPanel;

public class JDialogCreateBooking extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

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
	public JDialogCreateBooking() {
		setBounds(100, 100, 450, 414);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		DateTimePicker dateTimePicker = new DateTimePicker();
		dateTimePicker.setBounds(10, 11, 216, 23);
		contentPanel.add(dateTimePicker);
		
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
		calendarPanel.setBounds(0, 42, 279, 318);
		contentPanel.add(calendarPanel);
	}
	
	private void dateSelected(CalendarSelectionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
