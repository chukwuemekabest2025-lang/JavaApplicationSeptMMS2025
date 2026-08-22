import java.util.*;

public class HealthyRecipeConverter {
    private static final Map<String, String> substitutions = new HashMap<>();

    static {
        substitutions.put("1 cup sour cream", "1 cup yogurt");
        substitutions.put("1 cup butter", "1 cup margarine or yogurt");
        substitutions.put("1 egg", "2 egg whites");
        substitutions.put("1 cup milk", "1 cup soy milk");
        substitutions.put("1 cup sugar", "1/2 cup honey");
    }

    public static void main(String[] args) {
        System.out.println("WARNING: Always consult your physician before making significant changes to your diet.\n");

        String recipe = "Ingredients: 1 cup butter, 1 cup sugar, 1 egg, 1 cup milk";
        System.out.println("Original Recipe:\n" + recipe + "\n");

        String healthyRecipe = recipe;
        for (Map.Entry<String, String> entry : substitutions.entrySet()) {
            healthyRecipe = healthyRecipe.replace(entry.getKey(), entry.getValue());
        }

        System.out.println("Healthier Substitution Suggestion:\n" + healthyRecipe);
    }
}