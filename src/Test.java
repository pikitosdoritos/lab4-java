import java.util.List;

// Клас Test описує тест (тобто набір питань)
// Тут зберігається назва тесту і список питань,
// а також є логіка проходження тесту студентом
public class Test {

    // Назва тесту (наприклад: "Math Test")
    private String title;

    // Список питань у тесті
    // Кожне питання — це об'єкт класу Question
    private List<Question> questions;

    // Стратегія оцінювання (патерн Strategy)
    // Вона визначає, як саме рахується результат
    private EvaluationStrategy strategy;

    // Порожній конструктор
    public Test() {
    }

    // Конструктор з параметрами
    public Test(String title, List<Question> questions) {
        this.title = title;
        this.questions = questions;
    }

    // Встановлюємо спосіб оцінювання
    public void setStrategy(EvaluationStrategy strategy) {
        this.strategy = strategy;
    }

    // Метод conductTest — основна логіка проходження тесту
    public int conductTest(Student student, List<String> answers) {

        System.out.println("+------------------------------------------+");
        System.out.println("  Тест: " + title);
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

            System.out.println("  Result: " + (isCorrect ? "✓ Correct" : "✗ Wrong"));

            if (isCorrect)
                correct++;
        }

        // Використовуємо Strategy для підрахунку результату
        int percent = strategy.calculateScore(correct, questions.size());

        // Зберігаємо результат у студента
        student.addResult(title, percent);

        System.out.println("\n  Result: " + correct + " out of "
                + questions.size() + " correct (" + percent + "%)");
                
        System.out.println("+------------------------------------------+");

        return correct;
    }

    // ---- Getters і setters ----

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