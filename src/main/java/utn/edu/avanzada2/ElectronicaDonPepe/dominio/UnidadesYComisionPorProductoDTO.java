package utn.edu.avanzada2.ElectronicaDonPepe.dominio;

public class UnidadesYComisionPorProductoDTO {
	
	private Integer producto;
	private Integer unidades;
	private Integer valorComision;
	
	public UnidadesYComisionPorProductoDTO(Integer producto_id, Integer cantidad, Integer comision) {
		super();
		this.producto = producto_id;
		this.unidades = cantidad;
		this.valorComision = comision;
	}
	public Integer getProducto() {
		return producto;
	}
	public void setProducto(Integer producto) {
		this.producto = producto;
	}
	public Integer getUnidades() {
		return unidades;
	}
	public void setUnidades(Integer unidades) {
		this.unidades = unidades;
	}
	public Integer getValorComision() {
		return valorComision;
	}
	public void setValorComision(Integer valorComision) {
		this.valorComision = valorComision;
	}
	
}
