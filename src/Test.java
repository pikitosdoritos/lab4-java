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

    // Порожній конструктор
    // Потрібен для створення об'єкта без початкових значень
    public Test() {
    }

    // Конструктор з параметрами
    // Одразу задаємо назву тесту і список питань
    public Test(String title, List<Question> questions) {
        this.title = title;
        this.questions = questions;
    }

    // Метод conductTest — основна логіка проходження тесту
    // student — студент, який проходить тест
    // answers — список відповідей студента (у тому ж порядку, що і питання)
    // Повертає кількість правильних відповідей
    public int conductTest(Student student, List<String> answers) {

        // Виводимо заголовок тесту
        System.out.println("+------------------------------------------+");
        System.out.println("  Тест: " + title);
        System.out.println("  Студент: " + student.getName()
                + ", вік: " + student.getAge());
        System.out.println("+------------------------------------------+");

        // Лічильник правильних відповідей
        int correct = 0;

        // Проходимо по всіх питаннях
        for (int i = 0; i < questions.size(); i++) {

            // Беремо поточне питання
            Question q = questions.get(i);

            System.out.println("\nПитання " + (i + 1) + ":");

            // Виводимо саме питання і варіанти відповідей
            q.display();

            // Отримуємо відповідь студента
            // Якщо студент дав менше відповідей, ніж питань — беремо пустий рядок
            String answer = (i < answers.size()) ? answers.get(i) : "";

            System.out.println("  Відповідь: " + answer);

            // Перевіряємо правильність відповіді
            // Тут використовується логіка з класу Question (включаючи Flyweight)
            boolean isCorrect = q.checkAnswer(answer);

            // Виводимо результат по цьому питанню
            System.out.println("  Результат: " + (isCorrect ? "✓ Правильно" : "✗ Неправильно"));

            // Якщо відповідь правильна — збільшуємо лічильник
            if (isCorrect)
                correct++;
        }

        // Рахуємо відсоток правильних відповідей
        int percent = (int) Math.round((double) correct / questions.size() * 100);

        // Зберігаємо результат у студента
        student.addResult(title, percent);

        // Виводимо підсумок тесту
        System.out.println("\n  Підсумок: " + correct + " з "
                + questions.size() + " правильно (" + percent + "%)");
        System.out.println("+------------------------------------------+");

        // Повертаємо кількість правильних відповідей
        return correct;
    }

    // ---- Геттери і сеттери ----
    // Дають доступ до приватних полів класу

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

    // Перевизначення toString()
    // Використовується для зручного виводу тесту
    @Override
    public String toString() {
        return "Test: " + title + "\nQuestions: " + questions;
    }
}