import java.util.LinkedHashMap;
import java.util.Map;

// Клас Student — описує студента в системі тестування
// Тут зберігається базова інформація (ім'я, вік)
// і результати проходження тестів
public class Student {

    // Ім'я студента
    private String name;

    // Вік студента
    private int age;

    // Колекція для збереження результатів тестів
    // ключ — назва тесту
    // значення — відсоток правильних відповідей
    // LinkedHashMap використовується, щоб зберігався порядок додавання
    private Map<String, Integer> results = new LinkedHashMap<>();

    // Порожній конструктор
    // Потрібен, якщо створюємо об'єкт без початкових значень
    public Student() {
    }

    // Конструктор з параметрами
    // Одразу задаємо ім'я і вік студента
    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Метод для додавання результату тесту
    // testTitle — назва тесту
    // percent — результат у відсотках
    public void addResult(String testTitle, int percent) {
        results.put(testTitle, percent);
    }

    // Метод для виводу всіх результатів студента
    public void printResults() {

        // Виводимо заголовок
        System.out.println("=== Результати: " + name + " ===");

        // Якщо студент ще не проходив тести
        if (results.isEmpty()) {
            System.out.println("  Тестів ще не пройдено.");
            return;
        }

        int sum = 0;

        // Проходимо по всіх результатах
        for (Map.Entry<String, Integer> entry : results.entrySet()) {

            // Виводимо назву тесту і результат
            System.out.printf("  %-30s %3d%%%n",
                    entry.getKey() + ":", entry.getValue());

            // Додаємо до суми для підрахунку середнього
            sum += entry.getValue();
        }

        // Виводимо середній бал
        System.out.printf("  %-30s %3d%%%n",
                "Середній бал:", sum / results.size());

        System.out.println();
    }

    // ---- Геттери і сеттери ----
    // Використовуються для доступу до приватних полів (інкапсуляція)

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    // Перевизначення toString()
    // Потрібно, щоб об'єкт гарно виводився в консоль
    @Override
    public String toString() {
        return "Student{name='" + name + "', age=" + age + "}";
    }
}