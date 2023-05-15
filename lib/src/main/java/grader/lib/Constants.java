package grader.lib;

public class Constants {

    /**
     * The first 5 letters of the alphabet
     */
    public static final char[] ALPHABET = {'A', 'B', 'C', 'D', 'E'};

    public enum GradeScore {
        AD(4, "Advanced"),
        PR(3, "Proficient"),
        BA(2, "Basic"),
        MI(1, "Minimal"),
        ZERO(0, "Zero");

        public final int value;
        private final String name;
        private GradeScore(int value, String name) {
            this.value = value;
            this.name = name;
        }

        public String toString() {
            return name;
        }
    }

    public enum CriterionScore {
        AD(4, "Advanced"),
        PR(3.2, "Proficient"),
        BA(2.1, "Basic"),
        MI(1.1, "Minimal"),
        ZERO(0, "Zero"),
        NO_EVIDENCE(0, "No evidence");

        public final double value;
        private final String name;
        private CriterionScore(double value, String name) {
            this.value = value;
            this.name = name;
        }

        public String toString() {
            return name;
        }
    }

    public enum LetterGrade {
        A('A'),
        B('B'),
        C('C'),
        D('D'),
        U('U'),
        NO_EVIDENCE('-');

        private final char name;
        private LetterGrade(char name) {
            this.name = name;
        }
        public String toString() {
            return name + "";
        }
    }
     /*Roll up value found here:  https://drive.google.com/file/d/11baaWOECCefm279Pe_132b648l2E8UqY/view */
}
