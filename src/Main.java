import java.util.Arrays;

// Головний клас програми
// Тут ми запускаємо всю систему і демонструємо роботу патерну Flyweight
public class Main {

    public static void main(String[] args) {

        // Виводимо заголовок
        System.out.println("=== Система тестування — патерн Flyweight ===\n");

        // -----------------------------
        // Створюємо відповіді для першого тесту
        // -----------------------------

        // Питання 1 — одна правильна відповідь
        Answer a1 = new Answer("3", false);
        Answer a2 = new Answer("4", true); // правильна
        Answer a3 = new Answer("5", false);

        // Питання 2 — кілька правильних відповідей
        Answer b1 = new Answer("Flyweight", true); // правильна
        Answer b2 = new Answer("Singleton", false);
        Answer b3 = new Answer("Adapter", true); // правильна
        Answer b4 = new Answer("Decorator", true); // правильна

        // Питання 3 — відкрита відповідь
        Answer c1 = new Answer("Java", true); // правильна
        Answer c2 = new Answer("Python", false);

        // -----------------------------
        // Створюємо питання (перший тест)
        // Тут фабрика створює НОВІ об'єкти (Flyweight)
        // -----------------------------
        System.out.println("--- Створення питань (перший тест) ---");

        Question q1 = new Question(
                "2 + 2 = ?",
                Arrays.asList(a1, a2, a3),
                QuestionFactory.SINGLE);

        Question q2 = new Question(
                "Які є структурні патерни?",
                Arrays.asList(b1, b2, b3, b4),
                QuestionFactory.MULTIPLE);

        Question q3 = new Question(
                "Яка мова використовується у цій лабораторній?",
                Arrays.asList(c1, c2),
                QuestionFactory.OPEN);

        // -----------------------------
        // Другий тест — використовує ті самі типи питань
        // Тут фабрика НЕ створює нові об'єкти, а бере з кешу (Flyweight)
        // -----------------------------
        System.out.println("\n--- Створення питань (другий тест) ---");

        Answer d1 = new Answer("HashMap", true);
        Answer d2 = new Answer("ArrayList", false);

        Answer e1 = new Answer("Flyweight Factory", true);
        Answer e2 = new Answer("Abstract Factory", false);

        Answer f1 = new Answer("2", false);
        Answer f2 = new Answer("3", true);
        Answer f3 = new Answer("4", false);

        Question q4 = new Question(
                "Яка колекція використовується у кеші Flyweight?",
                Arrays.asList(d1, d2),
                QuestionFactory.OPEN // вже є у кеші
        );

        Question q5 = new Question(
                "Як називається фабрика у патерні Flyweight?",
                Arrays.asList(e1, e2),
                QuestionFactory.OPEN // кеш
        );

        Question q6 = new Question(
                "1 + 2 = ?",
                Arrays.asList(f1, f2, f3),
                QuestionFactory.SINGLE // кеш
        );

        // -----------------------------
        // Створюємо тести 
        // -----------------------------
        Test test1 = new Test("Math Test", Arrays.asList(q1, q2, q3));
        Test test2 = new Test("OOP Test", Arrays.asList(q4, q5, q6));

        // -----------------------------
        // Створюємо студентів
        // -----------------------------
        Student student1 = new Student("Nikita", 19);
        Student student2 = new Student("Mariya", 20);

        // -----------------------------
        // Проходження тестів
        // -----------------------------
        System.out.println("\n--- Студент 1 проходить Math Test ---");

        test1.conductTest(student1, Arrays.asList(
                "4", // правильно
                "Flyweight, Adapter, Decorator", // правильно
                "Java" // правильно
        ));

        System.out.println("\n--- Студент 2 проходить Math Test ---");

        test1.conductTest(student2, Arrays.asList(
                "3", // неправильно
                "Flyweight", // не всі правильні
                "Python" // неправильно
        ));

        System.out.println("\n--- Студент 1 проходить OOP Test ---");

        test2.conductTest(student1, Arrays.asList(
                "HashMap", // правильно
                "Flyweight Factory", // правильно
                "3" // правильно
        ));

        // -----------------------------
        // Виводимо кеш — демонстрація Flyweight
        // -----------------------------
        QuestionFactory.printCacheInfo();

        System.out.println("\n--- Ефект Flyweight ---");

        // Пояснення ефекту
        System.out.println("Питань використано: 9 (у трьох сесіях)");
        System.out.println("Об'єктів у пам'яті: " + QuestionFactory.getCacheSize());
        System.out.println("Висновок: замість 9 об'єктів — лише 3!\n");

        // -----------------------------
        // Виводимо результати студентів
        // -----------------------------
        System.out.println(student1);
        student1.printResults();

        System.out.println(student2);
        student2.printResults();

        // Перевірка toString
        System.out.println("\n" + test1);
    }
}