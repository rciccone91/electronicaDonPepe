package utn.edu.avanzada2.ElectronicaDonPepe.dominio;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Ventas implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private Date fecha;
	private String factura;
	@ManyToOne
	@JoinColumn(referencedColumnName="id" ,name="vendedor_id")
	private Vendedor vendedor;
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<ItemVentas> itemVentas;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Vendedor getVendedor() {
		return vendedor;
	}
	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}
	public List<ItemVentas> getItemVentas() {
		return itemVentas;
	}
	public void setItemVentas(List<ItemVentas> itemVentas) {
		this.itemVentas = itemVentas;
		for (ItemVentas iv : this.itemVentas) {
			iv.setVentas(this);
		}
	}
	public String getFactura() {
		return factura;
	}
	public void setFactura(String factura) {
		this.factura = factura;
	}
	
}
