package grades.app;

import java.util.HashMap;

import grader.lib.Course;

public class Service {
    private HashMap<String, Course> courses = new HashMap<String, Course>();
    
    public void addCourse(String name, int criterion) {
        courses.put(name, new Course(name, criterion));
        
    }

    public void dropCourse(String name) {
        if (courses.containsKey(name)) {
            courses.remove(name);
        }
    }

    /**
     * Will return null if the specified course doesn't exist.
     * @param name The name of the course
     * @return the specified course, or {@code null}
     */
    public Course getCourse(String name) {
        return courses.get(name);
    }

    public boolean courseExists(String name) {
        return courses.containsKey(name);
    }

}
