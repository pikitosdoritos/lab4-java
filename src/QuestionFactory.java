import java.util.HashMap;
import java.util.Map;

// Клас QuestionFactory — це реалізація патерну Flyweight (фабрика з кешем)
// Його задача — створювати об'єкти типів питань і ПЕРЕВИКОРИСТОВУВАТИ їх
// Тобто якщо тип вже створений — ми не створюємо новий, а беремо з кешу
public class QuestionFactory {

    // Кеш для збереження вже створених об'єктів
    // ключ — назва типу (наприклад "single")
    // значення — об'єкт типу QuestionType
    // static означає, що цей кеш один на всю програму
    private static final Map<String, QuestionType> cache = new HashMap<>();

    // Константи для типів питань
    // Це зроблено щоб не писати рядки вручну і не зробити помилку
    public static final String SINGLE = "single";
    public static final String MULTIPLE = "multiple";
    public static final String OPEN = "open";

    // Основний метод фабрики
    // Приймає тип питання і повертає відповідний об'єкт
    public static QuestionType getType(String type) {

        // Спочатку перевіряємо чи є об'єкт у кеші
        // Якщо є — просто повертаємо його
        if (cache.containsKey(type)) {

            // Виводимо повідомлення, що об'єкт взято з кешу
            System.out.println("  [CACHE HIT] Об'єкт вже є у кеші: " + type
                    + " | hashCode=" + System.identityHashCode(cache.get(type)));

            return cache.get(type);
        }

        // Якщо об'єкта ще немає — створюємо новий
        QuestionType questionType;

        // В залежності від типу створюємо потрібний клас
        switch (type) {
            case SINGLE:
                questionType = new SingleAnswerType();
                break;

            case MULTIPLE:
                questionType = new MultipleAnswerType();
                break;

            case OPEN:
                questionType = new OpenAnswerType();
                break;

            // Якщо тип невідомий — помилка
            default:
                throw new IllegalArgumentException("Невідомий тип: " + type);
        }

        // Додаємо створений об'єкт у кеш
        cache.put(type, questionType);

        // Виводимо, що створено новий об'єкт
        System.out.println("  [NEW OBJECT] Створено новий об'єкт: " + type
                + " | hashCode=" + System.identityHashCode(questionType));

        // Повертаємо об'єкт
        return questionType;
    }

    // Метод повертає кількість унікальних об'єктів у кеші
    // Тобто скільки реально створено об'єктів (це і є ефект Flyweight)
    public static int getCacheSize() {
        return cache.size();
    }

    // Метод для виводу інформації про кеш
    // Використовується для демонстрації, що об'єкти не дублюються
    public static void printCacheInfo() {

        System.out.println("\n====== Кеш QuestionFactory ======");

        // Виводимо кількість об'єктів
        System.out.println("Унікальних об'єктів у пам'яті: " + cache.size());

        // Проходимо по всіх елементах кешу
        for (Map.Entry<String, QuestionType> entry : cache.entrySet()) {

            // Виводимо:
            // ключ (тип)
            // клас об'єкта
            // hashCode (щоб показати, що об'єкт один і той самий)
            System.out.println("  ключ='" + entry.getKey()
                    + "' | клас=" + entry.getValue().getClass().getSimpleName()
                    + " | hashCode=" + System.identityHashCode(entry.getValue()));
        }

        System.out.println("=================================");
    }
}