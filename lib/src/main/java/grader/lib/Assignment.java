package grader.lib;

import grader.lib.Constants.GradeScore;

public class Assignment {
    public final GradeScore SCORE;
    public final String NAME;

    public Assignment(GradeScore score, String name) {
        this.SCORE = score;
        this.NAME = name;
    }

    public String toString() {
        return NAME;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Assignment)) {
            return false;
        }
        Assignment assignment = (Assignment) o;
        return SCORE.equals(assignment.SCORE) && NAME.equals(assignment.NAME);
    }
}
