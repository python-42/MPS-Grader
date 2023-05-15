package grades.app;

import grader.lib.Constants.GradeScore;
import grader.lib.Assignment;
import grader.lib.Course;

public class Driver {
    private MainWindow window;
    private CreationWindow cWindow;
    private AddWindow mWindow;
    private Service service;

    public Driver() {
        this.window = new MainWindow(this);
        this.cWindow = new CreationWindow(this);
        this.mWindow = new AddWindow(this);
        this.service = new Service();

    }

    public void createCourse(String name, String number) {
        int num = 0;
            try{
                num = Integer.parseInt(number);
            }catch (NumberFormatException e){
                window.showWarning("Course creation inputted incorrectly formed.\nPlease try again.");
                return;
            }
            if(num != 4 && num != 5) {
                window.showWarning("Course creation inputted incorrectly formmatted:\nIncorrect number of criterion. Please try again.");
            }else {
                service.addCourse(name, num);
                window.setDisplayedCourse(service.getCourse(name));
                window.registerNewCourse(name);
                cWindow.hideWindow();
            }
    }

    public void dropCourse(String name) {
        if(service.courseExists(name)) {
            service.dropCourse(name);
        }
    }

    public void switchDisplayedCourse(String name) {
        if(service.courseExists(name)) {
            window.setDisplayedCourse(service.getCourse(name));
        }
    }

    public void addButton() {
        cWindow.showWindow();
    }

    public void showModWindow() {
        mWindow.showWindow();
    }

    public void addAssignment(String name, GradeScore score, char criterion) {
        if(service.courseExists(window.getSelectedCourse())) {
            Course c = service.getCourse(window.getSelectedCourse());
            if(c.criteriaExists(criterion)) {
                c.getCriterion(criterion).addAssignment(new Assignment(score, name));
            }else {
                window.showWarning("Criterion '" + criterion + "'' does not exist.");
            }
        }
        mWindow.hideWindow();
        window.refreshDisplayedCourse();
    }

}
