package beans.divulga.editais.ifsuldeminas.edu.br;

import java.io.Serializable;
import javax.persistence.*;

import org.codehaus.jackson.annotate.JsonIgnore;


/**
 * The persistent class for the notices_categories database table.
 * 
 */
@Entity
@Table(name="notices_categories")
@NamedQuery(name="NoticesCategory.findAll", query="SELECT n FROM NoticesCategory n")
public class NoticesCategory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="notice_category_id")
	private int noticeCategoryId;

	//bi-directional many-to-one association to Category
	@ManyToOne
	@JoinColumn(name="category_id")
	private Category category;

	//bi-directional many-to-one association to Notice
	@ManyToOne
	@JoinColumn(name="notice_id")
	@JsonIgnore
	private Notice notice;

	public NoticesCategory() {
	}

	public int getNoticeCategoryId() {
		return this.noticeCategoryId;
	}

	public void setNoticeCategoryId(int noticeCategoryId) {
		this.noticeCategoryId = noticeCategoryId;
	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Notice getNotice() {
		return this.notice;
	}

	public void setNotice(Notice notice) {
		this.notice = notice;
	}

}