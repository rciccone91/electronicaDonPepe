package utn.edu.avanzada2.ElectronicaDonPepe.dominio;

import java.util.Date;
import java.util.List;

public class Ventas {

	private Integer id;
	private Date fecha;
	private Vendedor vendedor;
	private List<ItemVentas> articulos;
}
