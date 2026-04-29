import java.util.List;

// Class Test describes a test (set of questions)
// Contains logic for running the test for a student
public class Test {

    private String title;
    private List<Question> questions;

    // Strategy pattern: defines how score is calculated
    private EvaluationStrategy strategy;

    public Test() {
    }

    public Test(String title, List<Question> questions) {
        this.title = title;
        this.questions = questions;
    }

    // Set evaluation strategy
    public void setStrategy(EvaluationStrategy strategy) {
        this.strategy = strategy;
    }

    // Main method to run the test
    public int conductTest(Student student, List<String> answers) {

        System.out.println("+------------------------------------------+");
        System.out.println("  Test: " + title);
        System.out.println("  Student: " + student.getName()
                + ", age: " + student.getAge());
        System.out.println("+------------------------------------------+");

        int correct = 0;

        for (int i = 0; i < questions.size(); i++) {

            Question q = questions.get(i);

            System.out.println("\nQuestion " + (i + 1) + ":");

            q.display();

            String answer = (i < answers.size()) ? answers.get(i) : "";

            System.out.println("  Answer: " + answer);

            boolean isCorrect = q.checkAnswer(answer);

            System.out.println("  Result: " + (isCorrect ? "Correct" : "Wrong"));

            if (isCorrect) {
                correct++;
            }
        }

        // Use Strategy to calculate score
        int percent = strategy.calculateScore(correct, questions.size());

        student.addResult(title, percent);

        System.out.println("\n  Final result: " + correct + " out of "
                + questions.size() + " correct (" + percent + "%)");

        System.out.println("+------------------------------------------+");

        return correct;
    }

    // Getters and setters

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    @Override
    public String toString() {
        return "Test: " + title + "\nQuestions: " + questions;
    }
}