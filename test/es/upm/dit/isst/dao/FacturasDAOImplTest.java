/**
 * 
 */
package es.upm.dit.isst.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.appengine.api.users.*;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

import es.upm.dit.isst.dao.FacturasDAO;
import es.upm.dit.isst.dao.FacturasDAOImpl;
import es.upm.dit.isst.model.Factura;

/**
 * 
 * @author alvaro
 */
public class FacturasDAOImplTest {
	
	private final LocalServiceTestHelper helper = new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		helper.setUp();
		
		FacturasDAO dao = FacturasDAOImpl.getInstance();
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		dao.add("Movistar", "21/04/2016", "21/05/2016", 19.50, 0.50, 20.0, 24.40, 1.5, 150.0, "Seseña", "Toledo", user);
		dao.add("Orange", "21/08/2016", "21/09/2016", 18.40, 1.50, 19.90, 24.20, 1.5, 150.0, "Seseña", "Toledo", user);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		helper.tearDown();
	}

	/**
	 * Test method for {@link es.upm.dit.isst.dao.FacturasDAOImpl#add(java.lang.String, java.lang.String, java.lang.String, java.lang.Double, java.lang.Double, java.lang.Double, java.lang.Double, java.lang.Double, java.lang.Double, java.lang.String, java.lang.String, com.google.appengine.api.users.User)}.
	 */
	@Test
	public void testAdd() {
		FacturasDAO dao = FacturasDAOImpl.getInstance();
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		
		Factura factura = dao.add("Yoigo", "21/04/2016", "21/05/2016", 9.50, 0.50, 10.0, 14.40, 1.5, 150.0, "Illescas", "Toledo", user);
		assertEquals(factura.getEmpresa(), "Yoigo");
		assertEquals(factura.getEndDate(), "21/05/2016");
		assertEquals(factura.getConsumos(), 0.50, 0.05);
		assertEquals(factura.getSinImpuestos(), 10.0, 0.05);
		
		Factura factura2 = dao.add("Yoigo", "21/08/2016", "21/09/2016", 8.40, 1.50, 9.90, 14.20, 1.5, 150.0, "Esquivias", "Toledo", user);
		assertEquals(factura2.getMunicipio(), "Esquivias");
		assertEquals(factura2.getDatosContratados(), 1.5, 0.05);
	}

	/**
	 * Test method for {@link es.upm.dit.isst.dao.FacturasDAOImpl#getFacturas(com.google.appengine.api.users.User)}.
	 */
	@Test
	public void testGetFacturas() {
		FacturasDAO dao = FacturasDAOImpl.getInstance();
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		
		List<Factura> facturas = dao.getFacturas(user);

		assertEquals(facturas.get(0).getEmpresa(), "Movistar");
		assertEquals(facturas.get(1).getEmpresa(), "Orange");
		assertEquals(facturas.get(0).getCuotas(), 19.50, 0.05);
		assertEquals(facturas.get(1).getCuotas(), 18.40, 0.05);
		assertEquals(facturas.get(0).getDatosContratados(), 1.5, 0.05);
		assertEquals(facturas.get(1).getDatosContratados(), 1.5, 0.05);
		assertEquals(facturas.get(0).getImporteTotal(), 24.40, 0.05);
		assertEquals(facturas.get(1).getImporteTotal(), 24.20, 0.05);
		assertEquals(facturas.get(0).getProvincia(), "Toledo");
		assertEquals(facturas.get(1).getProvincia(), "Toledo");
	}

	/**
	 * Test method for {@link es.upm.dit.isst.dao.FacturasDAOImpl#delete(java.lang.Long)}.
	 */
	@Test
	public void testDelete() {
		FacturasDAO dao = FacturasDAOImpl.getInstance();
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		List<Factura> facturas = dao.getFacturas(user);
		Long id = facturas.get(0).getId();
		
		dao.delete(id);
		assertNotEquals(dao.getFacturas(user).get(0).getCuotas(), 19.50, 0.05);
	}

}
