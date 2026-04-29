// Оголошення публічного класу Answer
// Даний клас використовується для представлення варіанту відповіді у тестовій системі
public class Answer {

    // Приватне поле text — зберігає текст відповіді
    private String text;

    // Приватне поле isCorrect — логічне значення, яке визначає,
    // чи є дана відповідь правильною (true — правильна, false — неправильна)
    private boolean isCorrect;

    // Порожній конструктор
    // Використовується для створення об'єкта без початкової ініціалізації
    public Answer() {
    }

    // Конструктор з параметрами
    // Дозволяє створити об'єкт із заданим текстом відповіді та її правильністю
    public Answer(String text, boolean isCorrect) {
        this.text = text;
        this.isCorrect = isCorrect;
    }

    // Getter для отримання тексту відповіді
    public String getText() {
        return text;
    }

    // Getter для перевірки правильності відповіді
    // Особливість: для типу boolean використовується префікс "is"
    public boolean isCorrect() {
        return isCorrect;
    }

    // Setter для встановлення тексту відповіді
    public void setText(String text) {
        this.text = text;
    }

    // Setter для встановлення правильності відповіді
    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }

    // Перевизначення методу toString()
    // Використовується для зручного текстового представлення об'єкта
    // Виводить текст відповіді та її статус (correct / wrong)
    @Override
    public String toString() {
        return text + " (" + (isCorrect ? "correct" : "wrong") + ")";
    }
}