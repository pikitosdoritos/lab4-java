public class SimpleEvaluationStrategy implements EvaluationStrategy {

    @Override
    public int calculateScore(int correct, int total) {

        // Якщо питань немає, повертаємо 0, щоб не було ділення на нуль
        if (total == 0) {
            return 0;
        }

        // Рахуємо відсоток правильних відповідей
        return (int) Math.round((double) correct / total * 100);
    }
}