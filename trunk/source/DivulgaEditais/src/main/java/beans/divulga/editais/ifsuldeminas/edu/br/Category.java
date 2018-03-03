package beans.divulga.editais.ifsuldeminas.edu.br;

import java.io.Serializable;
import javax.persistence.*;

import org.codehaus.jackson.annotate.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the categories database table.
 * 
 */
@Entity
@Table(name="categories")
@NamedQuery(name="Category.findAll", query="SELECT c FROM Category c")
public class Category implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="category_id")
	private int categoryId;

	private String description;

	//bi-directional many-to-one association to NoticesCategory
	@OneToMany(mappedBy="category")
	@JsonIgnore
	private List<NoticesCategory> noticesCategories;

	//bi-directional many-to-one association to UsersCategory
	@OneToMany(mappedBy="category")
	@JsonIgnore
	private List<UsersCategory> usersCategories;

	public Category() {
	}

	public int getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<NoticesCategory> getNoticesCategories() {
		return this.noticesCategories;
	}

	public void setNoticesCategories(List<NoticesCategory> noticesCategories) {
		this.noticesCategories = noticesCategories;
	}

	public NoticesCategory addNoticesCategory(NoticesCategory noticesCategory) {
		getNoticesCategories().add(noticesCategory);
		noticesCategory.setCategory(this);

		return noticesCategory;
	}

	public NoticesCategory removeNoticesCategory(NoticesCategory noticesCategory) {
		getNoticesCategories().remove(noticesCategory);
		noticesCategory.setCategory(null);

		return noticesCategory;
	}

	public List<UsersCategory> getUsersCategories() {
		return this.usersCategories;
	}

	public void setUsersCategories(List<UsersCategory> usersCategories) {
		this.usersCategories = usersCategories;
	}

	public UsersCategory addUsersCategory(UsersCategory usersCategory) {
		getUsersCategories().add(usersCategory);
		usersCategory.setCategory(this);

		return usersCategory;
	}

	public UsersCategory removeUsersCategory(UsersCategory usersCategory) {
		getUsersCategories().remove(usersCategory);
		usersCategory.setCategory(null);

		return usersCategory;
	}

}