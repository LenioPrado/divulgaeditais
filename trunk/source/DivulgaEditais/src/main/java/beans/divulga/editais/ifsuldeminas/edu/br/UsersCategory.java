package beans.divulga.editais.ifsuldeminas.edu.br;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the users_categories database table.
 * 
 */
@Entity
@Table(name="users_categories")
@NamedQuery(name="UsersCategory.findAll", query="SELECT u FROM UsersCategory u")
public class UsersCategory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="user_category_id")
	private int userCategoryId;

	@Column(name="user_id")
	private int userId;

	//bi-directional many-to-one association to Category
	@ManyToOne
	@JoinColumn(name="category_id")
	private Category category;

	public UsersCategory() {
	}

	public int getUserCategoryId() {
		return this.userCategoryId;
	}

	public void setUserCategoryId(int userCategoryId) {
		this.userCategoryId = userCategoryId;
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}