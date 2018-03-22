package mobile.divulga.editais.ifsuldeminas.edu.br.model;

public class UsersRole {

    private UsersRolePK id;

    private Role role;

    private User user;

    public UsersRole() {
    }

    public UsersRolePK getId() {
        return this.id;
    }

    public void setId(UsersRolePK id) {
        this.id = id;
    }

    public Role getRole() {
        return this.role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}