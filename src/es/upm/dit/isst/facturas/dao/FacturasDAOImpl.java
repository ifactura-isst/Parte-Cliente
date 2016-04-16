package es.upm.dit.isst.facturas.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import com.google.appengine.api.users.User;
import es.upm.dit.isst.facturas.model.Factura;


public class FacturasDAOImpl implements FacturasDAO {
	
	private static FacturasDAOImpl instance;
	
	private FacturasDAOImpl() {
	}
	
	public static FacturasDAOImpl getInstance() {
		if (instance == null) {
			instance = new FacturasDAOImpl();
		}
		return instance;
	}

	@Override
	public Factura add(String nombre, String apellidos, String tipo, String empresa, String startDate, String endDate,
			Double importe, String municipio, String provincia, User user) {
		EntityManager em = EMFService.get().createEntityManager();
		Factura factura = new Factura(nombre, apellidos, tipo, empresa, startDate, endDate, importe, municipio, provincia, user);
		em.persist(factura);
		em.close();
		return factura;
	}

	@Override
	public List<Factura> getFacturas(User user) {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select t from Factura t where t.user =:user");
		q.setParameter("user", user);
		List<Factura> facturas = q.getResultList();
		em.close();
		return facturas;
	}

}
