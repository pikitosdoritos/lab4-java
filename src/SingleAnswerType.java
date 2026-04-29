// Конкретна реалізація Flyweight — тип питання з ОДНІЄЮ правильною відповіддю
// Цей об'єкт створюється ОДИН РАЗ і зберігається у кеші QuestionFactory
// Внутрішній стан: назва типу + логіка перевірки (однакові для всіх питань цього типу)
// Зовнішній стан: саме питання і відповідь студента — приходять як параметри
public class SingleAnswerType implements QuestionType {

    // Внутрішній стан — назва типу, не змінюється
    private final String typeName = "Single answer";

    @Override
    public String getTypeName() {
        return typeName;
    }

    // Інструкція для студента
    @Override
    public void printInstruction() {
        System.out.println("  [Type] Choose ONE correct answer");
    }

    // Перевірка: шукаємо відповідь з isCorrect == true
    // і порівнюємо її текст з тим що ввів студент
    @Override
    public boolean checkAnswer(Question question, String studentAnswer) {
        for (Answer answer : question.getAnswers()) {
            if (answer.isCorrect()) {
                return answer.getText().equalsIgnoreCase(studentAnswer.trim());
            }
        }
        return false;
    }
}