package grades.app;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CreationWindow {
    private Driver driver;

    private JFrame window = new JFrame();

    private JTextField nameBox = new JTextField(10);
    private JTextField numberBox = new JTextField(5);

    private JPanel rootPanel = new JPanel();
    private JButton submitButton = new JButton("Create");
    private JLabel text = new JLabel("Enter the name of the course and the number of criterion");

    private final Dimension DIMENSION = new Dimension(450, 100);

    public CreationWindow(Driver d) {
        driver = d;
        initWindow();
    }

    public void initWindow() {
        submitButton.addActionListener(CREATE_COURSE_LISTENER);

        rootPanel.add(text);
        rootPanel.add(nameBox);
        rootPanel.add(numberBox);
        rootPanel.add(submitButton);

        window.setTitle("Create Course");
        window.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        window.setSize(DIMENSION);   
        window.setResizable(false);
        window.add(rootPanel);
    }

    public void showWindow() {
        nameBox.setText("");
        numberBox.setText("");
        window.setVisible(true);

    }

    public void hideWindow() {
        window.setVisible(false);
    }

    private final ActionListener CREATE_COURSE_LISTENER = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent agr0) {
            driver.createCourse(nameBox.getText(), numberBox.getText());
        }
    };
    

}
