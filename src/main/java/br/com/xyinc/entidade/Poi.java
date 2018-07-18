package br.com.xyinc.entidade;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
/**
 * Entidade POI
 * 
 * @author Luciano
 *
 */
@Entity
@Table(name = "POIS")
public class Poi implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "{nome.nao.vazio}")
	private String nome;

	@NotNull(message = "{coordx.nao.vazio}")
	@Min(value = 0, message = "{coordx.inteiro}")
	private Integer coordX;

	@NotNull(message = "{coordy.nao.vazio}")
	@Min(value = 0, message = "{coordy.inteiro}")
	private Integer coordY;

	public Poi() {

	}

	public Poi(@NotBlank(message = "{nome.nao.vazio}") String nome,
			@NotNull(message = "{coordx.nao.vazio}") @Min(value = 0, message = "{coordx.inteiro}") Integer coordX,
			@NotNull(message = "{coordy.nao.vazio}") @Min(value = 0, message = "{coordy.inteiro}") Integer coordY) {
		super();
		this.nome = nome;
		this.coordX = coordX;
		this.coordY = coordY;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getCoordX() {
		return coordX;
	}

	public void setCoordX(Integer coordX) {
		this.coordX = coordX;
	}

	public Integer getCoordY() {
		return coordY;
	}

	public void setCoordY(Integer coordY) {
		this.coordY = coordY;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Poi other = (Poi) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
