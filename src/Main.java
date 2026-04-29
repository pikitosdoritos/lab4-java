import java.util.Arrays;

// Main class — entry point of the program
// Demonstrates Flyweight + Strategy patterns
public class Main {

        public static void main(String[] args) {

                System.out.println("=== Testing System - Flyweight Pattern ===\n");

                // ---------- Create answers for first test ----------

                Answer a1 = new Answer("3", false);
                Answer a2 = new Answer("4", true);
                Answer a3 = new Answer("5", false);

                Answer b1 = new Answer("Flyweight", true);
                Answer b2 = new Answer("Singleton", false);
                Answer b3 = new Answer("Adapter", true);
                Answer b4 = new Answer("Decorator", true);

                Answer c1 = new Answer("Java", true);
                Answer c2 = new Answer("Python", false);

                // ---------- Create questions (first test) ----------

                System.out.println("--- Creating questions (first test) ---");

                Question q1 = new Question(
                                "2 + 2 = ?",
                                Arrays.asList(a1, a2, a3),
                                QuestionFactory.SINGLE);

                Question q2 = new Question(
                                "Which are structural patterns?",
                                Arrays.asList(b1, b2, b3, b4),
                                QuestionFactory.MULTIPLE);

                Question q3 = new Question(
                                "Which language is used in this lab?",
                                Arrays.asList(c1, c2),
                                QuestionFactory.OPEN);

                // ---------- Second test ----------

                System.out.println("\n--- Creating questions (second test) ---");

                Answer d1 = new Answer("HashMap", true);
                Answer d2 = new Answer("ArrayList", false);

                Answer e1 = new Answer("Flyweight Factory", true);
                Answer e2 = new Answer("Abstract Factory", false);

                Answer f1 = new Answer("2", false);
                Answer f2 = new Answer("3", true);
                Answer f3 = new Answer("4", false);

                Question q4 = new Question(
                                "Which collection is used in Flyweight cache?",
                                Arrays.asList(d1, d2),
                                QuestionFactory.OPEN);

                Question q5 = new Question(
                                "What is the name of factory in Flyweight?",
                                Arrays.asList(e1, e2),
                                QuestionFactory.OPEN);

                Question q6 = new Question(
                                "1 + 2 = ?",
                                Arrays.asList(f1, f2, f3),
                                QuestionFactory.SINGLE);

                // ---------- Create tests ----------

                Test test1 = new Test("Math Test", Arrays.asList(q1, q2, q3));
                Test test2 = new Test("OOP Test", Arrays.asList(q4, q5, q6));

                // Strategy pattern
                test1.setStrategy(new SimpleEvaluationStrategy());
                test2.setStrategy(new PassFailEvaluationStrategy());

                // ---------- Students ----------

                Student student1 = new Student("Nikita", 19);
                Student student2 = new Student("Mariya", 20);

                // ---------- Run tests ----------

                System.out.println("\n--- Student 1 takes Math Test ---");

                test1.conductTest(student1, Arrays.asList(
                                "4",
                                "Flyweight, Adapter, Decorator",
                                "Java"));

                System.out.println("\n--- Student 2 takes Math Test ---");

                test1.conductTest(student2, Arrays.asList(
                                "3",
                                "Flyweight",
                                "Python"));

                System.out.println("\n--- Student 1 takes OOP Test ---");

                test2.conductTest(student1, Arrays.asList(
                                "HashMap",
                                "Flyweight Factory",
                                "3"));

                // ---------- Flyweight demo ----------

                QuestionFactory.printCacheInfo();

                System.out.println("\n--- Flyweight Effect ---");

                System.out.println("Questions used: 9");
                System.out.println("Objects in memory: " + QuestionFactory.getCacheSize());
                System.out.println("Result: instead of 9 objects - only 3!");

                // ---------- Results ----------

                System.out.println(student1);
                student1.printResults();

                System.out.println(student2);
                student2.printResults();

                System.out.println("\n" + test1);
        }
}