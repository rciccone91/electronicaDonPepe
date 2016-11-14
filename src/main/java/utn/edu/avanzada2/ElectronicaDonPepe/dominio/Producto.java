package utn.edu.avanzada2.ElectronicaDonPepe.dominio;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Producto implements Serializable {
	
	private static final long serialVersionUID = 6346392829806408992L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private String nombre;
	private Double precio;
	@OneToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private ComisionPorProducto comisionPorProducto;
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<ItemVentas> itemVentas;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Double getPrecio() {
		return precio;
	}
	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	public ComisionPorProducto getComisionPorProducto() {
		return comisionPorProducto;
	}
	public void setComisionPorProducto(ComisionPorProducto comisionPorProducto) {
		this.comisionPorProducto = comisionPorProducto;
	}
	public List<ItemVentas> getItemVentas() {
		return itemVentas;
	}
	public void setItemVentas(List<ItemVentas> itemVentas) {
		this.itemVentas = itemVentas;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((comisionPorProducto == null) ? 0 : comisionPorProducto.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((itemVentas == null) ? 0 : itemVentas.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((precio == null) ? 0 : precio.hashCode());
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
		Producto other = (Producto) obj;
		if (comisionPorProducto == null) {
			if (other.comisionPorProducto != null)
				return false;
		} else if (!comisionPorProducto.equals(other.comisionPorProducto))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (itemVentas == null) {
			if (other.itemVentas != null)
				return false;
		} else if (!itemVentas.equals(other.itemVentas))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (precio == null) {
			if (other.precio != null)
				return false;
		} else if (!precio.equals(other.precio))
			return false;
		return true;
	}
	
	
	
}
