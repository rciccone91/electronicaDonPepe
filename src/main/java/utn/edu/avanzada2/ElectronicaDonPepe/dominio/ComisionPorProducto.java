package utn.edu.avanzada2.ElectronicaDonPepe.dominio;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class ComisionPorProducto implements Serializable{
	

	private static final long serialVersionUID = -5207494426182056773L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	@OneToOne(fetch=FetchType.LAZY, cascade=CascadeType.MERGE)
	private Producto producto;
	private Integer valorComision;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
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
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((producto == null) ? 0 : producto.hashCode());
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
		ComisionPorProducto other = (ComisionPorProducto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (producto == null) {
			if (other.producto != null)
				return false;
		} else if (!producto.equals(other.producto))
			return false;
		if (valorComision == null) {
			if (other.valorComision != null)
				return false;
		} else if (!valorComision.equals(other.valorComision))
			return false;
		return true;
	}
	
	
	
}
