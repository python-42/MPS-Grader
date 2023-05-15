package grades.app;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import grader.lib.Constants.GradeScore;

public class AddWindow {
    private Driver driver;

    private JFrame window = new JFrame();

    private final GradeScore[] OPTIONS = {GradeScore.AD, GradeScore.PR, GradeScore.BA, GradeScore.MI, GradeScore.ZERO};

    private JTextField nameBox = new JTextField(10);
    private JTextField criterionBox = new JTextField(5);
    private JComboBox<GradeScore> scoreBox = new JComboBox<GradeScore>(OPTIONS);

    private JPanel rootPanel = new JPanel();
    private JButton submitButton = new JButton("Add Evidence");
    private JLabel text = new JLabel("Enter the name of the evidence and the recieved score.");
    private JLabel text2 = new JLabel("Enter the character(s) which represent the desired criterion.");

    private final Dimension DIMENSION = new Dimension(450, 100);

    public AddWindow(Driver d) {
        driver = d;
        initWindow();
    }

    private void initWindow() {
        submitButton.addActionListener(ADD_LISTENER);

        rootPanel.add(text);
        rootPanel.add(text2);
        rootPanel.add(nameBox);
        rootPanel.add(scoreBox);
        rootPanel.add(criterionBox);
        rootPanel.add(submitButton);

        window.setTitle("Add Evidence");
        window.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        window.setSize(DIMENSION);   
        window.setResizable(false);
        window.add(rootPanel);
    }

    public void showWindow() {
        nameBox.setText("");
        scoreBox.setSelectedIndex(0);
        window.setVisible(true);

    }

    public void hideWindow() {
        window.setVisible(false);
    }

    private final ActionListener ADD_LISTENER = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent agr0) {
            String criteria = criterionBox.getText();
            for (char c : criteria.toCharArray()) {
                driver.addAssignment(nameBox.getText(), (GradeScore) scoreBox.getSelectedItem(), c);
            }
        }
    };
    

}
