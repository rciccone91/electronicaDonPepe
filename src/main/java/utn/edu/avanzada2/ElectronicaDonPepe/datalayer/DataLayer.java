package utn.edu.avanzada2.ElectronicaDonPepe.datalayer;

public interface DataLayer<T> {
	
	public void guardar(T t);
	
	public void modificar(T t);
	
	public void eliminar(T t);
}
