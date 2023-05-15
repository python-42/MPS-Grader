package grader.lib;

import java.util.ArrayList;

import grader.lib.Constants.CriterionScore;
import grader.lib.Constants.GradeScore;

public class Criterion {
    
    private ArrayList<Assignment> grades = new ArrayList<Assignment>();
    public final char ID;

    public Criterion(char id) {
        this.ID = id;
    }

    public Criterion(char id, Assignment a) {
        this.ID = id;
        grades.add(a);
    }

    @SuppressWarnings("unchecked")
    public Criterion(Criterion copy) {
        this.ID = copy.ID;
        this.grades =  (ArrayList<Assignment>) copy.grades.clone();
    }

    public Criterion(char id, ArrayList<Assignment> grades) {
        this.ID = id;
        this.grades = grades;
    }

    public void addAssignment(Assignment a) {
        grades.add(a);
    }

    public void removeAssignment(Assignment a) {
        grades.remove(a);
    }

    public void clearAssignments() {
        grades.clear();    
    }

    public Assignment[] viewAssignments() {
        return grades.toArray(new Assignment[grades.size()]);
    }

    public CriterionScore getCriterionScore() {
        if(getAssignmentCount() == 0) {
            return CriterionScore.NO_EVIDENCE;
        }

        double score = getAverage();
        if(score >= 3.5) {
            return CriterionScore.AD;
        }

        if(score >= 2.5) {
            return CriterionScore.PR;
        }

        if(score >= 1.5) {
            return CriterionScore.BA;
        }

        if(score >= 0.1) {
            return CriterionScore.MI;
        }

        return CriterionScore.ZERO;
    }

    private double getAverage() {
        double rtn = 0;
        for (Assignment i : grades) {
            rtn += i.SCORE.value;
        }
        return rtn / grades.size();
    }

    public int getAssignmentCount() {
        return grades.size();
    }

    @Override
    public String toString() {
        String rtn = "Criterion " + ID + "\nScore: " + getCriterionScore() + " based on " + grades.size() + " assignments.\n";
        for (Assignment i : grades) {
            rtn+= "\t*"+ i.NAME + ": " + i.SCORE.toString() + "\n";
        }
        return rtn;
    }


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Criterion)) {
            return false;
        }
        Criterion criterion = (Criterion) o;
        
        return grades.equals(criterion.grades) && ID == criterion.ID;
    }


    /**
     * Find out how many of the given grade type the given criterion can take while preserving the
     * given grade. Returns -1 if the goal grade is lower than the current grade. Returns -2 if the 
     * criterion can the {@code toTake} value infinitely without lowering the criterion score.
     * @param criterion criterion to test
     * @param target criterion score
     * @param toTake grade score to add to criterion
     * @return -1, -2 or the number of the given grade the criterion can take before falling below the {@code target}
     */
    public static int numberOfPerCriterion(Criterion criterion, CriterionScore target, GradeScore toTake) {
        
        Criterion c = new Criterion(criterion); //Creates a copy so we can work with the criterion without damaging the actual record
        CriterionScore cur = c.getCriterionScore();

        if (target.value > cur.value) {
            return -1;
        }

        if(target.value <= toTake.value) {          
            return -2;
        }

        int rtn = 0;
        while (target.value <= cur.value) {
            c.addAssignment(new Assignment(toTake, ""));
            rtn ++;
            cur = c.getCriterionScore();
        }

        return rtn-1;
    }
}
