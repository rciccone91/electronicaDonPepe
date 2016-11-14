package utn.edu.avanzada2.ElectronicaDonPepe.datalayer;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;

import utn.edu.avanzada2.ElectronicaDonPepe.dominio.ComisionPorVentas;
import utn.edu.avanzada2.ElectronicaDonPepe.dominio.Producto;
import utn.edu.avanzada2.ElectronicaDonPepe.dominio.UnidadesYComisionPorProductoDTO;
import utn.edu.avanzada2.ElectronicaDonPepe.dominio.Vendedor;
import utn.edu.avanzada2.ElectronicaDonPepe.dominio.Ventas;

public class DataLayerImple<T> implements DataLayer<T> {

	private SessionFactory sessionFactory;
	private  Session session;

	public DataLayerImple()
	{
		if(this.sessionFactory == null){
			sessionFactory = new Configuration().configure()
					.buildSessionFactory();
			this.session = sessionFactory.openSession();
		}
	}

	public void guardar(T t) {
		Transaction tx = session.getTransaction();
		try {
			tx.begin();
			session.save(t);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} 
		//		finally {
		//			session.close();
		//		}

	}

	public void modificar(T t) {
		//		SessionFactory sessionFactory = new Configuration().configure()
		//				.buildSessionFactory();
		//		Session session = sessionFactory.openSession();
		Transaction tx = session.getTransaction();
		try {
			tx.begin();
			session.update(t);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} 
		//		finally {
		//			session.close();
		//		}

	}

	public void eliminar(T t) {
		//		SessionFactory sessionFactory = new Configuration().configure()
		//				.buildSessionFactory();
		//		Session session = sessionFactory.openSession();
		Transaction tx = session.getTransaction();
		try {
			tx.begin();
			session.delete(t);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} 
		//		finally {
		//			session.close();
		//		}

	}

	@SuppressWarnings({ "finally", "unchecked" })
	public List<T> list(Class clazz){
		List<T> lista = new ArrayList<T>();
		try {
			lista = session.createQuery("from utn.edu.avanzada2.ElectronicaDonPepe.dominio."+clazz.getSimpleName()+"").list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			return lista;
		}
	}

	@SuppressWarnings({ "unchecked", "finally" })
	public Number getVentasPorVendedor(Vendedor vendedor, Integer mes) {
		Number count = null;
		try {
			String sql = "select count(1) from ventas v where v.vendedor_id = "+vendedor.getId()
			+ " and month(v.fecha) = "+mes;
			Query query = session.createSQLQuery(sql);
			count = ((Number) query.uniqueResult()).intValue();

		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			return count;
		}
	}

	@SuppressWarnings("finally")
	public Integer getComisionPorVentas(Number ventas) {
		ComisionPorVentas comision = null;
		try {
			String hql = "from ComisionPorVentas c"
					+ " where c.cantidadMinima <= "+ventas
					+ " and c.cantidadMaxima >= "+ventas;
			Query query = session.createQuery(hql);
			comision = (ComisionPorVentas) query.uniqueResult();

		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			return comision.getValorComision();
		}
	}	


	@SuppressWarnings({ "unchecked", "finally" })
	public List<UnidadesYComisionPorProductoDTO> getProductosVendidosPorVendedorAlMes(Integer vendedor,Integer mes) {
		List<UnidadesYComisionPorProductoDTO> vendidos = 
				new ArrayList<UnidadesYComisionPorProductoDTO>();
		try {
			String queryString = "select iv.producto_id, sum(iv.cantidad), cxp.valorComision "
					+ "from itemventas as iv "
					+ "inner join ventas as v "
					+ "on v.id = iv.venta_id "
					+ "and v.vendedor_id = " + vendedor 
					+ " and month(v.fecha) = "+ mes
					+ " inner join comisionporproducto as cxp "
					+ "on iv.producto_id = cxp.producto_id "
					+ "group by iv.producto_id"; 
			Query query = session.createSQLQuery(queryString);
			List<Object> lista = query.list();
			Iterator<Object> itr = lista.iterator();
			while(itr.hasNext()){
				Object[] obj = (Object[]) itr.next();
				Integer productoId = Integer.valueOf(String.valueOf(obj[0]));
				Integer cantidad = Integer.parseInt(String.valueOf(obj[1]));
				Integer comision = Integer.parseInt(String.valueOf(obj[2])); 
				vendidos.add(new UnidadesYComisionPorProductoDTO(productoId,cantidad,comision));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			return vendidos;
		}
	}	


	@SuppressWarnings("finally")
	public Producto getProductoById(Integer id) {
		Producto prod = null;
		try {
			String hql = "from Producto p"
					+ " where p.id =:id";
			Query query = session.createQuery(hql);
			query.setInteger("id", id);
			prod =  (Producto) query.uniqueResult();

		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			return prod;
		}
	}

	@SuppressWarnings({ "unchecked", "finally" })
	public Vendedor getVendedorDelMes(Integer mes) {

		Integer vendedorId = null;
		try {
			String queryString = "select count(1) as cant, vendedor_id "+
					"from ventas "+
					"where month(fecha) ="+ mes +
					" group by vendedor_id "+
					"order by cant desc "+
					"limit 1 ";

			Query query = session.createSQLQuery(queryString);
			List<Object> lista = query.list();
			Iterator<Object> itr = lista.iterator();
			while(itr.hasNext()){
				Object[] obj = (Object[]) itr.next();
				vendedorId = Integer.valueOf(String.valueOf(obj[1]));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			return getVendedorById(vendedorId);
		}
	}	
	
	@SuppressWarnings("finally")
	public Vendedor getVendedorById(Integer id) {
		Vendedor vendedor = null;
		try {
			String hql = "from Vendedor v"
					+ " where v.id =:id";
			Query query = session.createQuery(hql);
			query.setInteger("id", id);
			vendedor =  (Vendedor) query.uniqueResult();

		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			return vendedor;
		}
	}
}
