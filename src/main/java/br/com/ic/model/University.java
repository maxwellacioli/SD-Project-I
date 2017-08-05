package br.com.ic.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="APP_UNIVERSITY")
public class University implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;

	@NotEmpty
	@Column(name="NAME", nullable=false)
	private String name;



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		University user = (University) o;

	
		if (id != null ? !id.equals(user.id) : user.id != null) return false;
		if (name != null ? !name.equals(user.name) : user.name != null) return false;
		return false;
	}

	@Override
	public int hashCode() {
		int result;
		long temp;
		result = id != null ? id.hashCode() : 0;
		result = 31 * result + (name != null ? name.hashCode() : 0);

		return result;
	}

	@Override
	public String toString() {
		return "University [id=" + id + ", name=" + name  + "]";
	}


}
