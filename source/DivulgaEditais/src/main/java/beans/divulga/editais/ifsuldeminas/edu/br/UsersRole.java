package beans.divulga.editais.ifsuldeminas.edu.br;

import java.io.Serializable;
import javax.persistence.*;

import org.codehaus.jackson.annotate.JsonBackReference;


/**
 * The persistent class for the users_roles database table.
 * 
 */
@Entity
@Table(name="users_roles")
@NamedQuery(name="UsersRole.findAll", query="SELECT u FROM UsersRole u")
public class UsersRole implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private UsersRolePK id;

	//bi-directional many-to-one association to Role
	@ManyToOne
	@JoinColumn(name="role_id", insertable = false, updatable = false)
	@JsonBackReference
	private Role role;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="user_id", insertable = false, updatable = false)
	@JsonBackReference
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