package grades.app;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;

import grader.lib.Course;

public class MainWindow extends JFrame{
    private Driver driver;
    private Course displayedCourse;

    //main window
    private JPanel rootPanel;

    private JPanel buttonPanel;
    private JButton addButton;
    private JButton removeButton;
    private JComboBox<String> courseSelectionBox;

    private JPanel courseInfoPanel;
    private JLabel courseLabel;
    private JTextArea textArea;
    private JButton addEvidenceButton;
    private JButton removeEvidenceButton;
    private JComboBox<String> criterionViewBox;

    private final Dimension WINDOW_DIMENSION = new Dimension(500, 400);
    private final Dimension BUTTON_DIMENSION = new Dimension(150, 30);
    
    private final Border border = BorderFactory.createLineBorder(Color.DARK_GRAY);

    public MainWindow(Driver d) {
        driver = d;
        initWindow();
    }

    private void initWindow() {
        this.setSize(WINDOW_DIMENSION);
        this.setTitle("MPS Grader");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);

        rootPanel = new JPanel();
        rootPanel.setLayout(new BoxLayout(rootPanel, BoxLayout.Y_AXIS));
        this.add(rootPanel);

        buttonPanel = new JPanel();
        buttonPanel.setAlignmentX(CENTER_ALIGNMENT);
        buttonPanel.setBorder(border);
        rootPanel.add(buttonPanel);

        addButton = new JButton("Add Course");
        addButton.setSize(BUTTON_DIMENSION);
        addButton.addActionListener(ADD_BUTTON_LISTENER);
        buttonPanel.add(addButton);

        removeButton = new JButton("Drop Course");
        removeButton.setToolTipText("Delete the currently selected course");
        removeButton.addActionListener(REMOVE_BUTTON_LISTENER);
        removeButton.setSize(BUTTON_DIMENSION);
        buttonPanel.add(removeButton);

        courseSelectionBox = new JComboBox<String>();
        courseSelectionBox.setSize(BUTTON_DIMENSION);
        courseSelectionBox.addActionListener(COURSE_SELECTION_BOX_LISTENER);
        buttonPanel.add(courseSelectionBox);

        courseInfoPanel = new JPanel();
        courseInfoPanel.setAlignmentX(CENTER_ALIGNMENT);
        //courseInfoPanel.setBorder(border);
        rootPanel.add(courseInfoPanel);

        courseLabel = new JLabel("No course selected");
        courseInfoPanel.add(courseLabel);

        textArea = new JTextArea(15, 30);
        textArea.setEditable(false);
        courseInfoPanel.add(textArea);

        addEvidenceButton = new JButton("Add Evidence");
        addEvidenceButton.addActionListener(ADD_EVIDENCE_LISTENER);
        courseInfoPanel.add(addEvidenceButton);

        removeEvidenceButton = new JButton("Remove Evidence");
        removeEvidenceButton.addActionListener(LAUNCH_REMOVE_LISTENER);
        courseInfoPanel.add(removeEvidenceButton);

        criterionViewBox = new JComboBox<String>();
        criterionViewBox.addActionListener(CRITERION_VIEW_LISTENER);
        courseInfoPanel.add(criterionViewBox);

        this.setVisible(true);
    }

    public void setDisplayedCourse(Course c) {
        textArea.setText(c.toString());
        courseLabel.setText(c.NAME + ":");
        displayedCourse = c;
    }

    public void refreshDisplayedCourse() {
        if(displayedCourse != null) {
            this.setDisplayedCourse(displayedCourse);
        }
    }

    private void displayCriterion(char c) {
        if(displayedCourse != null && displayedCourse.criteriaExists(c)) {
            textArea.setText(displayedCourse.getCriterion(c).toString());
        }
    }

    public void registerNewCourse(String courseName) {
        courseSelectionBox.addItem(courseName);
        courseSelectionBox.setSelectedItem(courseName);
    }

    public void showWarning(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public String getSelectedCourse() {
        return (String) courseSelectionBox.getSelectedItem();
    }

    private final ActionListener ADD_BUTTON_LISTENER = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            driver.addButton();
        }
    };

    private final ActionListener LAUNCH_REMOVE_LISTENER = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            new RemoveWindow(displayedCourse);
        }
    };

    private final ActionListener REMOVE_BUTTON_LISTENER = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            displayedCourse = null;
            textArea.setText("");
            courseLabel.setText("No course selected");
            //courseSelectionBox.setSelectedIndex(0);
            courseSelectionBox.removeItem((String) courseSelectionBox.getSelectedItem());
            driver.dropCourse((String) courseSelectionBox.getSelectedItem());
        }
    };

    private final ActionListener COURSE_SELECTION_BOX_LISTENER = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            driver.switchDisplayedCourse((String) courseSelectionBox.getSelectedItem());
            criterionViewBox.removeAllItems();
            if(displayedCourse != null) {
                criterionViewBox.addItem("Course Overview");
                for (int i = 65; i < 65+displayedCourse.NUMBER_OF_CRITERION; i++) {//65 is ascii value for 'A'
                    criterionViewBox.addItem(String.valueOf((char)i));
                }
            }
        }
    };

    private final ActionListener ADD_EVIDENCE_LISTENER = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            driver.showModWindow();
        }
    };

    private final ActionListener CRITERION_VIEW_LISTENER = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            String value = (String) criterionViewBox.getSelectedItem();
            if(value == null) {
                return;
            }
            if(value.equals("Course Overview")){
                refreshDisplayedCourse();
            }else {
                displayCriterion(value.charAt(0));
            }
        }
    };

}
