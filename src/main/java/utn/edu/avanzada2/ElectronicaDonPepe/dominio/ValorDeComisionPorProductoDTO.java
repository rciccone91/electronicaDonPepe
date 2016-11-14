package utn.edu.avanzada2.ElectronicaDonPepe.dominio;

public class ValorDeComisionPorProductoDTO {
	
	private Producto producto;
	private Integer valorComision;
	
	public ValorDeComisionPorProductoDTO(Producto p,Integer c){
		super();
		this.producto = p;
		this.valorComision = c;
	}
	
	public ValorDeComisionPorProductoDTO(){
		super();
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
	
	

}
