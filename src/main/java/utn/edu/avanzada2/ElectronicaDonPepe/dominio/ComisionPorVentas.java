package utn.edu.avanzada2.ElectronicaDonPepe.dominio;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class ComisionPorVentas implements Serializable {
	

	private static final long serialVersionUID = -226772040173581699L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private Integer cantidadMinima;
	private Integer cantidadMaxima;
	private Integer valorComision;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCantidadMinima() {
		return cantidadMinima;
	}
	public void setCantidadMinima(Integer cantidadMinima) {
		this.cantidadMinima = cantidadMinima;
	}
	public Integer getCantidadMaxima() {
		return cantidadMaxima;
	}
	public void setCantidadMaxima(Integer cantidadMaxima) {
		this.cantidadMaxima = cantidadMaxima;
	}
	public Integer getValorComision() {
		return valorComision;
	}
	public void setValorComision(Integer valorComision) {
		this.valorComision = valorComision;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cantidadMaxima == null) ? 0 : cantidadMaxima.hashCode());
		result = prime * result + ((cantidadMinima == null) ? 0 : cantidadMinima.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((valorComision == null) ? 0 : valorComision.hashCode());
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
		ComisionPorVentas other = (ComisionPorVentas) obj;
		if (cantidadMaxima == null) {
			if (other.cantidadMaxima != null)
				return false;
		} else if (!cantidadMaxima.equals(other.cantidadMaxima))
			return false;
		if (cantidadMinima == null) {
			if (other.cantidadMinima != null)
				return false;
		} else if (!cantidadMinima.equals(other.cantidadMinima))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (valorComision == null) {
			if (other.valorComision != null)
				return false;
		} else if (!valorComision.equals(other.valorComision))
			return false;
		return true;
	}
	
	
	
	
}
