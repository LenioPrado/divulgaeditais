package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the modalities database table.
 * 
 */
@Entity
@Table(name="modalities")
@NamedQuery(name="Modality.findAll", query="SELECT m FROM Modality m")
public class Modality implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="modality_id")
	private int modalityId;

	private String acronyms;

	private String description;

	//bi-directional many-to-one association to Notice
	@OneToMany(mappedBy="modality")
	private Set<Notice> notices;

	public Modality() {
	}

	public int getModalityId() {
		return this.modalityId;
	}

	public void setModalityId(int modalityId) {
		this.modalityId = modalityId;
	}

	public String getAcronyms() {
		return this.acronyms;
	}

	public void setAcronyms(String acronyms) {
		this.acronyms = acronyms;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Notice> getNotices() {
		return this.notices;
	}

	public void setNotices(Set<Notice> notices) {
		this.notices = notices;
	}

	public Notice addNotice(Notice notice) {
		getNotices().add(notice);
		notice.setModality(this);

		return notice;
	}

	public Notice removeNotice(Notice notice) {
		getNotices().remove(notice);
		notice.setModality(null);

		return notice;
	}

}