package catering.businesslogic.recipe;

import java.util.List;

public class Preparation extends KitchenDuty{
    private List<Recipe> referenceRecipe;

    public Preparation(String name, String instructions, String description, List<Recipe> referenceRecipe) {
        super(name, instructions, description);
        this.referenceRecipe = referenceRecipe;
    }

    // Getters and Setters
    public List<Recipe> getReferenceRecipe() {
        return referenceRecipe;
    }
    public void setReferenceRecipe(List<Recipe> referenceRecipe) {
        this.referenceRecipe = referenceRecipe;
    }
}
