// Конкретна реалізація Flyweight — тип "відкрите питання"
// Тут студент вводить відповідь текстом (не вибирає варіант)
// Один об'єкт цього класу буде використовуватись для всіх відкритих питань
public class OpenAnswerType implements QuestionType {

    // Внутрішній стан Flyweight
    // Це назва типу питання, вона однакова для всіх таких об'єктів
    private final String typeName = "Відкрита відповідь";

    // Повертає назву типу питання
    @Override
    public String getTypeName() {
        return typeName;
    }

    // Виводить інструкцію для студента
    // Пояснює, що потрібно ввести відповідь вручну
    @Override
    public void printInstruction() {
        System.out.println("  [Тип] Введіть відповідь у вільній формі");
    }

    // Основний метод перевірки відповіді
    // question — питання з варіантами відповідей (зовнішній стан)
    // studentAnswer — те, що ввів студент
    @Override
    public boolean checkAnswer(Question question, String studentAnswer) {

        // Проходимо по всіх відповідях у питанні
        for (Answer answer : question.getAnswers()) {

            // Знаходимо правильну відповідь
            if (answer.isCorrect()) {

                // Порівнюємо:
                // trim() — прибирає пробіли
                // equalsIgnoreCase() — ігнорує регістр (велика/мала буква)
                return answer.getText().trim()
                        .equalsIgnoreCase(studentAnswer.trim());
            }
        }

        // Якщо правильну відповідь не знайдено — повертаємо false
        return false;
    }
}