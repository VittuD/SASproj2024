package catering.businesslogic.user;

public class UserManager {
    private User currentUser;

    public void login(String username) //TODO: bisogna implementare il login vero!
    {
        this.currentUser = User.loadUser(username);
    }

    public User getCurrentUser() {
        return this.currentUser;
    }
}
