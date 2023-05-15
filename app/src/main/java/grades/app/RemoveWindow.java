package grades.app;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import grader.lib.Assignment;
import grader.lib.Course;
import grader.lib.Criterion;

public class RemoveWindow {
    private Course course;

    private JFrame window = new JFrame();

    private JComboBox<Character> criterionBox = new JComboBox<Character>();
    private JComboBox<Assignment> assignmentBox = new JComboBox<Assignment>();

    private JPanel rootPanel = new JPanel();
    private JButton submitButton = new JButton("Remove Evidence");
    private JLabel text = new JLabel("Select the criterion then select the assignment to remove.");

    private final Dimension DIMENSION = new Dimension(450, 100);

    public RemoveWindow(Course c) {
        course = c;
        initCriterionBox();
        initAssignmentBox();
        initWindow();
    }

    private void initCriterionBox() {
        criterionBox.removeAllItems();
        for (int i = 65; i < 65+course.NUMBER_OF_CRITERION; i++) {//65 is ascii value for 'A'
            criterionBox.addItem((char)i);
        }
    }

    private void initWindow() {
        submitButton.addActionListener(REMOVE_LISTENER);
        criterionBox.addActionListener(CRITERION_BOX_LISTENER);

        rootPanel.add(text);
        rootPanel.add(criterionBox);
        rootPanel.add(assignmentBox);
        rootPanel.add(submitButton);

        window.setTitle("Add Evidence");
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setSize(DIMENSION);   
        window.setResizable(false);
        window.add(rootPanel);
        window.setVisible(true);
    }

    private void initAssignmentBox() {
        Criterion c = course.getCriterion((char) criterionBox.getSelectedItem());
        assignmentBox.removeAllItems();
        for (Assignment a : c.viewAssignments()) {
            assignmentBox.addItem(a);
        }
        //assignmentBox.setSelectedIndex(-1);
    }

    private final ActionListener REMOVE_LISTENER = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent agr0) {
            course.getCriterion((char) criterionBox.getSelectedItem()).removeAssignment((Assignment) assignmentBox.getSelectedItem());
            initAssignmentBox();
        }
    };

    private final ActionListener CRITERION_BOX_LISTENER = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            initAssignmentBox();
        }  
    };

}
