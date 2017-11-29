package beans.divulga.editais.ifsuldeminas.edu.br;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the notices database table.
 * 
 */
@Entity
@Table(name="notices")
@NamedQuery(name="Notice.findAll", query="SELECT n FROM Notice n")
public class Notice implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="notice_id")
	private int noticeId;

	private String number;

	private String object;

	@Temporal(TemporalType.DATE)
	@Column(name="trading_date")
	private Date tradingDate;

	private String url;

	//bi-directional many-to-one association to Modality
	@ManyToOne
	@JoinColumn(name="modality_id")
	private Modality modality;

	public Notice() {
	}

	public int getNoticeId() {
		return this.noticeId;
	}

	public void setNoticeId(int noticeId) {
		this.noticeId = noticeId;
	}

	public String getNumber() {
		return this.number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getObject() {
		return this.object;
	}

	public void setObject(String object) {
		this.object = object;
	}

	public Date getTradingDate() {
		return this.tradingDate;
	}

	public void setTradingDate(Date tradingDate) {
		this.tradingDate = tradingDate;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Modality getModality() {
		return this.modality;
	}

	public void setModality(Modality modality) {
		this.modality = modality;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + noticeId;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Notice other = (Notice) obj;
		if (noticeId != other.noticeId)
			return false;
		return true;
	}	

}