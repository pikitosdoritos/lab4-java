import java.util.HashMap;
import java.util.Map;

// Flyweight Factory
public class QuestionFactory {

    private static final Map<String, QuestionType> cache = new HashMap<>();

    public static final String SINGLE = "single";
    public static final String MULTIPLE = "multiple";
    public static final String OPEN = "open";

    public static QuestionType getType(String type) {

        if (cache.containsKey(type)) {
            System.out.println("  [CACHE HIT] Object already exists: " + type
                    + " | hashCode=" + System.identityHashCode(cache.get(type)));
            return cache.get(type);
        }

        QuestionType questionType;

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
            default:
                throw new IllegalArgumentException("Unknown type: " + type);
        }

        cache.put(type, questionType);

        System.out.println("  [NEW OBJECT] Created new object: " + type
                + " | hashCode=" + System.identityHashCode(questionType));

        return questionType;
    }

    public static int getCacheSize() {
        return cache.size();
    }

    public static void printCacheInfo() {
        System.out.println("\n====== QuestionFactory Cache ======");
        System.out.println("Objects in memory: " + cache.size());

        for (Map.Entry<String, QuestionType> entry : cache.entrySet()) {
            System.out.println("  key='" + entry.getKey()
                    + "' | class=" + entry.getValue().getClass().getSimpleName()
                    + " | hashCode=" + System.identityHashCode(entry.getValue()));
        }

        System.out.println("=================================");
    }
}