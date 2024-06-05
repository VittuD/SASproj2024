package catering.businesslogic.recipe;

public abstract class KitchenDuty {
    protected String name;
    protected String instructions;
    protected String description;

    public KitchenDuty(){
    }

    public KitchenDuty(String name, String instructions, String description) {
        this.name = name;
        this.instructions = instructions;
        this.description = description;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getInstructions() {
        return instructions;
    }
    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}

