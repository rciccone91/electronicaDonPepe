package utn.edu.avanzada2.ElectronicaDonPepe.servicelayer;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;

import utn.edu.avanzada2.ElectronicaDonPepe.datalayer.DataLayerImple;
import utn.edu.avanzada2.ElectronicaDonPepe.dominio.Campania;
import utn.edu.avanzada2.ElectronicaDonPepe.dominio.ComisionPorProducto;
import utn.edu.avanzada2.ElectronicaDonPepe.dominio.ComisionPorVentas;
import utn.edu.avanzada2.ElectronicaDonPepe.dominio.ItemVentas;
import utn.edu.avanzada2.ElectronicaDonPepe.dominio.Producto;
import utn.edu.avanzada2.ElectronicaDonPepe.dominio.Rol;
import utn.edu.avanzada2.ElectronicaDonPepe.dominio.UnidadesYComisionPorProductoDTO;
import utn.edu.avanzada2.ElectronicaDonPepe.dominio.Usuario;
import utn.edu.avanzada2.ElectronicaDonPepe.dominio.ValorDeComisionPorProductoDTO;
import utn.edu.avanzada2.ElectronicaDonPepe.dominio.Vendedor;
import utn.edu.avanzada2.ElectronicaDonPepe.dominio.Ventas;

public class ServiceLayer {

	private DataLayerImple<Vendedor> vendedorDataLayer;
	private DataLayerImple<Campania> campaniaDataLayer;
	private DataLayerImple<ComisionPorProducto> comisionPorProductoDataLayer;
	private DataLayerImple<ComisionPorVentas> comisionPorVentasDataLayer;
	private DataLayerImple<ItemVentas> itemVentasDataLayer;
	private DataLayerImple<Producto> productoDataLayer;
	private DataLayerImple<Rol> rolDataLayer;
	private DataLayerImple<Usuario> usuarioDataLayer;
	private DataLayerImple<Ventas> ventasDataLayer;

	public DataLayerImple<Vendedor> getVendedorDataLayer() {
		return vendedorDataLayer;
	}

	@Autowired
	public void setVendedorDataLayer(DataLayerImple<Vendedor> vendedorDataLayer) {
		this.vendedorDataLayer = vendedorDataLayer;
	}

	public DataLayerImple<Campania> getCampaniaDataLayer() {
		return campaniaDataLayer;
	}

	public DataLayerImple<ComisionPorProducto> getComisionPorProductoDataLayer() {
		return comisionPorProductoDataLayer;
	}

	@Autowired
	public void setComisionPorProductoDataLayer(DataLayerImple<ComisionPorProducto> comisionPorProductoDataLayer) {
		this.comisionPorProductoDataLayer = comisionPorProductoDataLayer;
	}

	@Autowired
	public void setCampaniaDataLayer(DataLayerImple<Campania> campaniaDataLayer) {
		this.campaniaDataLayer = campaniaDataLayer;
	}

	public DataLayerImple<ComisionPorVentas> getComisionPorVentasDataLayer() {
		return comisionPorVentasDataLayer;
	}

	@Autowired
	public void setComisionPorVentasDataLayer(DataLayerImple<ComisionPorVentas> comisionPorVentasDataLayer) {
		this.comisionPorVentasDataLayer = comisionPorVentasDataLayer;
	}

	public DataLayerImple<ItemVentas> getItemVentasDataLayer() {
		return itemVentasDataLayer;
	}

	@Autowired
	public void setItemVentasDataLayer(DataLayerImple<ItemVentas> itemVentasDataLayer) {
		this.itemVentasDataLayer = itemVentasDataLayer;
	}

	public DataLayerImple<Producto> getProductoDataLayer() {
		return productoDataLayer;
	}

	@Autowired
	public void setProductoDataLayer(DataLayerImple<Producto> productoDataLayer) {
		this.productoDataLayer = productoDataLayer;
	}

	public DataLayerImple<Rol> getRolDataLayer() {
		return rolDataLayer;
	}

	@Autowired
	public void setRolDataLayer(DataLayerImple<Rol> rolDataLayer) {
		this.rolDataLayer = rolDataLayer;
	}

	public DataLayerImple<Usuario> getUsuarioDataLayer() {
		return usuarioDataLayer;
	}

	@Autowired
	public void setUsuarioDataLayer(DataLayerImple<Usuario> usuarioDataLayer) {
		this.usuarioDataLayer = usuarioDataLayer;
	}

	public DataLayerImple<Ventas> getVentasDataLayer() {
		return ventasDataLayer;
	}

	@Autowired
	public void setVentasDataLayer(DataLayerImple<Ventas> ventasDataLayer) {
		this.ventasDataLayer = ventasDataLayer;
	}

	public void guardarVendedor(Vendedor vendedor){
		vendedorDataLayer.guardar(vendedor);
	}

	public void guardarVenta(Ventas venta){
		ventasDataLayer.guardar(venta);
	}

	public void guardarProducto(Producto producto){
		productoDataLayer.guardar(producto);
	}

	//	public void guardarRol(Rol rol) {
	//		rolDataLayer.guardar(rol);
	//	}

	public void guardarUsuario(Usuario usuario) {
		usuarioDataLayer.guardar(usuario);
	}

	public List<Producto> listarProductos(){
		return productoDataLayer.list(Producto.class);
	}

	public void guardarComisionPorProd(ComisionPorProducto comisionPorProducto) {
		comisionPorProductoDataLayer.guardar(comisionPorProducto);
	}

	public void guardarComisionPorVenta(ComisionPorVentas comisionPorVentas) {
		comisionPorVentasDataLayer.guardar(comisionPorVentas);
	}

	public List<Rol> listarRoles(){
		return rolDataLayer.list(Rol.class);
	}

	public Integer getComisionPorVentasByVendedor(Vendedor vendedor, Integer mes){
		Number ventas = vendedorDataLayer.getVentasPorVendedor(vendedor,mes);
		return ventasDataLayer.getComisionPorVentas(ventas);
	}

	public List<ValorDeComisionPorProductoDTO> getComisionPorVentaDeProducto(Vendedor vendedor, Integer mes){

		List<UnidadesYComisionPorProductoDTO> vendidos = 
				comisionPorProductoDataLayer.getProductosVendidosPorVendedorAlMes(vendedor.getId(), mes);
		List<ValorDeComisionPorProductoDTO> comisiones = new ArrayList<ValorDeComisionPorProductoDTO>();
		vendidos.forEach(ucxp -> comisiones.add(
				new ValorDeComisionPorProductoDTO(productoDataLayer.getProductoById(ucxp.getProducto()),
						ucxp.getUnidades()*ucxp.getValorComision())));
		return comisiones;
	} 

	public Vendedor getMejorVendedorDelMes(Integer mes){
		return vendedorDataLayer.getVendedorDelMes(mes);
	}
}
