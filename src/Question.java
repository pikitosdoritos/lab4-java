import java.util.List;

// Клас Question описує одне питання тесту
// Тут зберігається текст питання, варіанти відповідей
// і тип питання (через Flyweight)
public class Question {

    // Текст питання (наприклад: "2 + 2 = ?")
    private String text;

    // Список відповідей до цього питання
    // Кожен елемент — об'єкт класу Answer
    private List<Answer> answers;

    // Тип питання (Flyweight)
    // Це не просто поле — це об'єкт, який перевикористовується
    // для всіх питань одного типу (single, multiple, open)
    private QuestionType questionType;

    // Порожній конструктор
    // Потрібен, щоб можна було створити об'єкт без значень
    public Question() {
    }

    // Старий конструктор з лаби 2
    // Залишений для сумісності
    public Question(String text, List<Answer> answers) {
        this.text = text;
        this.answers = answers;
    }

    // Новий конструктор
    // Додає можливість задати тип питання через ключ
    // typeKey — це рядок ("single", "multiple", "open")
    public Question(String text, List<Answer> answers, String typeKey) {

        this.text = text;
        this.answers = answers;

        // Отримуємо об'єкт типу через фабрику
        // Тут або створиться новий об'єкт, або візьметься з кешу
        this.questionType = QuestionFactory.getType(typeKey);
    }

    // Метод для виводу питання на екран
    // Виводить:
    // - текст питання
    // - інструкцію (через Flyweight)
    // - варіанти відповідей
    public void display() {

        System.out.println("  Питання: " + text);

        // Якщо тип питання заданий — виводимо інструкцію
        if (questionType != null) {
            questionType.printInstruction();
        }

        // Виводимо всі варіанти відповідей
        System.out.println("  Варіанти:");
        for (int i = 0; i < answers.size(); i++) {
            System.out.println("    " + (i + 1) + ") " + answers.get(i).getText());
        }
    }

    // Метод перевірки відповіді студента
    // Тут ми НЕ перевіряємо самі, а передаємо це Flyweight-об'єкту
    public boolean checkAnswer(String studentAnswer) {

        // Якщо тип не заданий — повертаємо false
        if (questionType == null)
            return false;

        // Делегуємо перевірку відповідному типу питання
        return questionType.checkAnswer(this, studentAnswer);
    }

    // ---- Getters і setters ----
    // Використовуються для доступу до приватних полів

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }

    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }

    // Перевизначення toString()
    // Додаємо ще тип питання у вивід
    @Override
    public String toString() {

        // Якщо тип є — додаємо його до рядка
        String type = (questionType != null)
                ? " [" + questionType.getTypeName() + "]"
                : "";

        return "Question: " + text + type + "\nAnswers: " + answers;
    }
}