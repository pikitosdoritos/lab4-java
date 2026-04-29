import java.util.HashSet;
import java.util.Set;

// Конкретна реалізація Flyweight — тип питання з кількома правильними відповідями
// Один об'єкт цього класу буде використовуватись для всіх таких питань
// Студент вводить відповіді через кому (наприклад: "Java, Python")
public class MultipleAnswerType implements QuestionType {

    // Внутрішній стан Flyweight
    // Назва типу питання — однакова для всіх об'єктів цього типу
    private final String typeName = "Кілька правильних відповідей";

    // Повертає назву типу питання
    @Override
    public String getTypeName() {
        return typeName;
    }

    // Виводить інструкцію для студента
    // Пояснює як правильно вводити відповідь
    @Override
    public void printInstruction() {
        System.out.println("  [Тип] Введіть всі правильні відповіді через кому");
    }

    // Основний метод перевірки відповіді
    // question — містить правильні варіанти (зовнішній стан)
    // studentAnswer — рядок, який ввів студент
    @Override
    public boolean checkAnswer(Question question, String studentAnswer) {

        // Множина правильних відповідей
        // Set використовується, щоб:
        // - не було дублікатів
        // - порядок не мав значення
        Set<String> correctSet = new HashSet<>();

        // Заповнюємо множину правильних відповідей
        for (Answer answer : question.getAnswers()) {
            if (answer.isCorrect()) {

                // trim() — прибирає пробіли
                // toLowerCase() — щоб не залежати від регістру
                correctSet.add(answer.getText().trim().toLowerCase());
            }
        }

        // Множина відповідей студента
        Set<String> studentSet = new HashSet<>();

        // Розбиваємо рядок по комі
        // Наприклад: "Java, Python" → ["Java", " Python"]
        for (String part : studentAnswer.split(",")) {

            // Так само чистимо і переводимо в нижній регістр
            studentSet.add(part.trim().toLowerCase());
        }

        // Порівнюємо множини
        // Вони мають бути повністю однакові:
        // - ті самі елементи
        // - без зайвих і без пропущених
        return studentSet.equals(correctSet);
    }
}