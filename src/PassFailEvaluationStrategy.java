public class PassFailEvaluationStrategy implements EvaluationStrategy{

    @Override
    public int calculateScore(int correct, int total) {
        // Якщо питань немає, поветраємо 0, щоб не було ділення на нуль
        if (total == 0) {
            return 0;
        }

        if (correct >= total / 2) {
            return 100;
        } else {
            return 0; 
        }
    }
    
}
