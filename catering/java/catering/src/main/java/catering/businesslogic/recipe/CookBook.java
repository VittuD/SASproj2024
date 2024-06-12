package catering.businesslogic.recipe;

import catering.businesslogic.turn.Kitchen;
import catering.businesslogic.user.User;

import java.util.List;

public class CookBook {
    private User owner;
    private List<KitchenDuty> cookBook;
    public CookBook() {
    }

    public CookBook(User owner, List<KitchenDuty> cookBook) {
        this.owner = owner;
        this.cookBook = cookBook;
    }

    // Getters and Setters
    public User getOwner() {
        return owner;
    }
    public void setOwner(User owner) {
        this.owner = owner;
    }
    public List<KitchenDuty> getCookBook() {
        return cookBook;
    }
    public void setCookBook(List<KitchenDuty> cookBook) {
        this.cookBook = cookBook;
    }
}
