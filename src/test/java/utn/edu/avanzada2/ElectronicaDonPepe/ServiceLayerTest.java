package utn.edu.avanzada2.ElectronicaDonPepe;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import utn.edu.avanzada2.ElectronicaDonPepe.config.AppConfig;
import utn.edu.avanzada2.ElectronicaDonPepe.dominio.Campania;
import utn.edu.avanzada2.ElectronicaDonPepe.dominio.ComisionPorProducto;
import utn.edu.avanzada2.ElectronicaDonPepe.dominio.ComisionPorVentas;
import utn.edu.avanzada2.ElectronicaDonPepe.dominio.ItemVentas;
import utn.edu.avanzada2.ElectronicaDonPepe.dominio.Producto;
import utn.edu.avanzada2.ElectronicaDonPepe.dominio.Rol;
import utn.edu.avanzada2.ElectronicaDonPepe.dominio.Usuario;
import utn.edu.avanzada2.ElectronicaDonPepe.dominio.ValorDeComisionPorProductoDTO;
import utn.edu.avanzada2.ElectronicaDonPepe.dominio.Vendedor;
import utn.edu.avanzada2.ElectronicaDonPepe.dominio.Ventas;
import utn.edu.avanzada2.ElectronicaDonPepe.servicelayer.ServiceLayer;

@RunWith(JUnit4.class)
public class ServiceLayerTest {
	

	@Test
	public void guardarDatosYConsultarComisiones() {
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);		
		ServiceLayer service = context.getBean(ServiceLayer.class);
		try {
			
			System.out.println("Guardando Productos...");
			Producto prod = new Producto();
			prod.setNombre("Lámpara de bajo consumo");
			prod.setPrecio(new Double(85.50));
			service.guardarProducto(prod);
			
			Producto prod2 = new Producto();
			prod2.setNombre("Termica");
			prod2.setPrecio(new Double(500));
			service.guardarProducto(prod2);
			
			
//			System.out.println("Dando de alta vendedores...");
//			Rol rol = new Rol();
//			rol.setDescripcion("Vendedor");
//			service.guardarRol(rol);
//			
//			rol = service.listarRoles().get(0);
			
			Usuario usuario = new Usuario();
			usuario.setUserId("mrosado");
			usuario.setPassword("pass1234");
//			usuario.setRol(rol);
			service.guardarUsuario(usuario);
			
			Usuario usuario2 = new Usuario();
			usuario2.setUserId("cfulanito");
			usuario2.setPassword("pass1234");
//			usuario2.setRol(rol);
			service.guardarUsuario(usuario2);
			
			Usuario usuario3 = new Usuario();
			usuario3.setUserId("bgomez");
			usuario3.setPassword("pass1234");
//			usuario3.setRol(rol);
			service.guardarUsuario(usuario3);
			
			Vendedor vendedor = new Vendedor();
			vendedor.setApellido("Rosado");
			vendedor.setNombre("Modesto");
			vendedor.setLegajo("60");
			vendedor.setUsuario(usuario);
			service.guardarVendedor(vendedor);
			
			Vendedor vendedor2 = new Vendedor();
			vendedor2.setApellido("Fulanito");
			vendedor2.setNombre("Cosme");
			vendedor2.setLegajo("45");
			vendedor2.setUsuario(usuario2);
			service.guardarVendedor(vendedor2);
			
			Vendedor vendedor3 = new Vendedor();
			vendedor3.setApellido("Gomez");
			vendedor3.setNombre("Barnie");
			vendedor3.setLegajo("23");
			vendedor3.setUsuario(usuario2);
			service.guardarVendedor(vendedor2);
			
			
			List<Producto> productos = service.listarProductos();
			Assert.assertTrue("La cantidad de productos no es igual a 2"
					, productos.size() == 2);
			
			
			System.out.println("Agregando Comisiones por producto...");
			ComisionPorProducto comisionPorProducto = new ComisionPorProducto();
			comisionPorProducto.setProducto(productos.get(0));
			comisionPorProducto.setValorComision(productos.get(0).getId()*10);
			service.guardarComisionPorProd(comisionPorProducto);
			
			comisionPorProducto = new ComisionPorProducto();
			comisionPorProducto.setProducto(productos.get(1));
			comisionPorProducto.setValorComision(productos.get(1).getId()*20);
			service.guardarComisionPorProd(comisionPorProducto);

			System.out.println("Agregando Comisiones por venta...");
			ComisionPorVentas comisionPorVentas = new ComisionPorVentas();
			comisionPorVentas.setCantidadMinima(1);
			comisionPorVentas.setCantidadMaxima(3);
			comisionPorVentas.setValorComision(200);
			
			ComisionPorVentas comisionPorVentas2 = new ComisionPorVentas();
			comisionPorVentas2.setCantidadMinima(4);
			comisionPorVentas2.setCantidadMaxima(10);
			comisionPorVentas2.setValorComision(700);
			
			service.guardarComisionPorVenta(comisionPorVentas);
			service.guardarComisionPorVenta(comisionPorVentas2);
			
//			System.out.println("Agregando Campaña por producto...");
//			Campania campania = new Campania();
//			
//			Calendar hoy = Calendar.getInstance();
//			hoy.add(Calendar.DAY_OF_YEAR, -2);
			
			
			System.out.println("Guardar ventas vendedor 1...");
			
			// --- Venta 1 - Vendedor1 ------------------
			Ventas venta = new Ventas();
			venta.setFecha(Calendar.getInstance().getTime());
			
			ItemVentas item = new ItemVentas();
			item.setCantidad(2);
			item.setProducto(productos.get(0));

			List<ItemVentas> items = new ArrayList<ItemVentas>();
			items.add(item);
			
			venta.setItemVentas(items);
			venta.setVendedor(vendedor);
			
			service.guardarVenta(venta);
			
			
			// --- Venta 2 - Vendedor1 ------------------
			venta = new Ventas();
			venta.setFecha(Calendar.getInstance().getTime());

			item = new ItemVentas();
			item.setCantidad(1);
			item.setProducto(productos.get(0));
			
			ItemVentas item2 = new ItemVentas();
			item2.setCantidad(3);
			item2.setProducto(productos.get(1));

			items = new ArrayList<ItemVentas>();
			items.add(item);
			items.add(item2);

			venta.setItemVentas(items);
			venta.setVendedor(vendedor);

			service.guardarVenta(venta);
			
			
			
			System.out.println("Guardar ventas vendedor 2...");
			
			// --- Venta 1 - Vendedor2 ------------------
			venta = new Ventas();
			venta.setFecha(Calendar.getInstance().getTime());
			
			item = new ItemVentas();
			item.setCantidad(9);
			item.setProducto(productos.get(0));

			items = new ArrayList<ItemVentas>();
			items.add(item);
			
			venta.setItemVentas(items);
			venta.setVendedor(vendedor2);
			
			service.guardarVenta(venta);
			
			
			// --- Venta 2 - Vendedor2  ------------------
			venta = new Ventas();
			venta.setFecha(Calendar.getInstance().getTime());

			item = new ItemVentas();
			item.setCantidad(3);
			item.setProducto(productos.get(0));
			
			item2 = new ItemVentas();
			item2.setCantidad(6);
			item2.setProducto(productos.get(1));

			items = new ArrayList<ItemVentas>();
			items.add(item);
			items.add(item2);

			venta.setItemVentas(items);
			venta.setVendedor(vendedor2);

			service.guardarVenta(venta);

			// --- Venta 3 - Vendedor2  ------------------
			venta = new Ventas();
			venta.setFecha(Calendar.getInstance().getTime());

			item2 = new ItemVentas();
			item2.setCantidad(3);
			item2.setProducto(productos.get(1));

			items = new ArrayList<ItemVentas>();
			items.add(item2);

			venta.setItemVentas(items);
			venta.setVendedor(vendedor2);

			service.guardarVenta(venta);
			
			
			System.out.println("Guardar ventas vendedor 3...");
			// --- Venta 1  - Vendedor3------------------
			venta = new Ventas();
			venta.setFecha(Calendar.getInstance().getTime());

			item = new ItemVentas();
			item.setCantidad(15);
			item.setProducto(productos.get(0));

			items = new ArrayList<ItemVentas>();
			items.add(item);

			venta.setItemVentas(items);
			venta.setVendedor(vendedor2);

			service.guardarVenta(venta);
						
			System.out.println("Comision por venta por vendedores...");
			System.out.println("Vendedor 1: "+service.getComisionPorVentasByVendedor(vendedor,Calendar.getInstance().get(Calendar.MONTH)+1));
			System.out.println("Vendedor 2: "+service.getComisionPorVentasByVendedor(vendedor2,Calendar.getInstance().get(Calendar.MONTH)+1));
			
			List<ValorDeComisionPorProductoDTO> comisionesVendedor1 = 
					service.getComisionPorVentaDeProducto(vendedor, Calendar.getInstance().get(Calendar.MONTH)+1);
			List<ValorDeComisionPorProductoDTO> comisionesVendedor2 = 
					service.getComisionPorVentaDeProducto(vendedor2, Calendar.getInstance().get(Calendar.MONTH)+1);
			
			System.out.println("Comision por producto por vendedores...");
			System.out.println("Vendedor 1....");
			
			comisionesVendedor1.forEach(c -> System.out.println("Producto: "+c.getProducto().getNombre()+" - Comisión: "+c.getValorComision()));
			
			System.out.println("Vendedor 2....");
			comisionesVendedor2.forEach(c -> System.out.println("Producto: "+c.getProducto().getNombre()+" - Comisión: "+c.getValorComision()));
			
			System.out.println("Vendedor del Mes: "+service.getMejorVendedorDelMes(Calendar.getInstance().get(Calendar.MONTH)+1).getUsuario().getUserId());

			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}
	
	
	
}
