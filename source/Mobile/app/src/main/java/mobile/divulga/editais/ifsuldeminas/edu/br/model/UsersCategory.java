package mobile.divulga.editais.ifsuldeminas.edu.br.model;

public class UsersCategory {

    private int userCategoryId;

    private Category category;

    private User user;

    public UsersCategory() {
    }

    public int getUserCategoryId() {
        return this.userCategoryId;
    }

    public void setUserCategoryId(int userCategoryId) {
        this.userCategoryId = userCategoryId;
    }

    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}