/**
 * 
 */
package es.upm.dit.isst.dao;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

import es.upm.dit.isst.model.Subasta;

/**
 * @author alvaro
 */
public class SubastaDAOImplTest {
	
	private final LocalServiceTestHelper helper = new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		helper.setUp();
		
		SubastaDAO dao = SubastaDAOImpl.getInstance();
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		ArrayList<String> customers = new ArrayList<String>();
		ArrayList<String> customers2 = new ArrayList<String>();
		dao.add(false, 20, 11, "Subasta test", "Subasta utilizada en las pruebas", user, customers);
		dao.add(false, 10, 0, "Subasta test2", "Subasta utilizada en las pruebas", user, customers2);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		helper.tearDown();
	}

	/**
	 * Test method for {@link es.upm.dit.isst.dao.SubastaDAOImpl#readSubastas()}.
	 */
	@Test
	public void testReadSubastas() {
		SubastaDAO dao = SubastaDAOImpl.getInstance();
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		
		List<Subasta> subastas = dao.readSubastas();
		
		assertEquals(subastas.get(0).getState(), false);
		assertEquals(subastas.get(1).getState(), false);
		assertEquals(subastas.get(0).getuserApuntados(), 11);
		assertEquals(subastas.get(1).getuserApuntados(), 0);
		assertEquals(subastas.get(0).getuserMax(), 20);
		assertEquals(subastas.get(1).getuserMax(), 10);
		assertEquals(subastas.get(0).getTitle(), "Subasta test");
		assertEquals(subastas.get(1).getTitle(), "Subasta test2");
		assertEquals(subastas.get(0).getDescription(), "Subasta utilizada en las pruebas");
		assertEquals(subastas.get(1).getDescription(), "Subasta utilizada en las pruebas");
	}

	/**
	 * Test method for {@link es.upm.dit.isst.dao.SubastaDAOImpl#listSubastasByUser(com.google.appengine.api.users.User)}.
	 */
	@Test
	public void testListSubastasByUser() {
		SubastaDAO dao = SubastaDAOImpl.getInstance();
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		
		List<Subasta> subastas = dao.listSubastasByUser(user);
		
		assertEquals(subastas.get(0).getState(), false);
		assertEquals(subastas.get(1).getState(), false);
		assertEquals(subastas.get(0).getuserApuntados(), 11);
		assertEquals(subastas.get(1).getuserApuntados(), 0);
		assertEquals(subastas.get(0).getuserMax(), 20);
		assertEquals(subastas.get(1).getuserMax(), 10);
		assertEquals(subastas.get(0).getTitle(), "Subasta test");
		assertEquals(subastas.get(1).getTitle(), "Subasta test2");
		assertEquals(subastas.get(0).getDescription(), "Subasta utilizada en las pruebas");
		assertEquals(subastas.get(1).getDescription(), "Subasta utilizada en las pruebas");
	}

	/**
	 * Test method for {@link es.upm.dit.isst.dao.SubastaDAOImpl#add(boolean, int, int, java.lang.String, java.lang.String, com.google.appengine.api.users.User, java.util.ArrayList)}.
	 */
	@Test
	public void testAdd() {
		SubastaDAO dao = SubastaDAOImpl.getInstance();
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		ArrayList<String> customers = new ArrayList<String>();
		ArrayList<String> customers2 = new ArrayList<String>();
		
		Subasta subasta = dao.add(false, 20, 11, "Subasta test", "Subasta utilizada en las pruebas", user, customers);
		assertEquals(subasta.getState(), false);
		assertEquals(subasta.getuserApuntados(), 11);
		assertEquals(subasta.getuserMax(), 20);
		assertEquals(subasta.getTitle(), "Subasta test");
		assertEquals(subasta.getDescription(), "Subasta utilizada en las pruebas");
		assertEquals(subasta.getUsuariosApuntados(), customers);
		
		Subasta subasta2 = dao.add(false, 10, 0, "Subasta test2", "Subasta utilizada en las pruebas", user, customers2);
		assertEquals(subasta2.getState(), false);
		assertEquals(subasta2.getuserApuntados(), 0);
		assertEquals(subasta2.getuserMax(), 10);
		assertEquals(subasta2.getTitle(), "Subasta test2");
		assertEquals(subasta2.getDescription(), "Subasta utilizada en las pruebas");
		assertEquals(subasta2.getUsuariosApuntados(), customers2);
	}

	/**
	 * Test method for {@link es.upm.dit.isst.dao.SubastaDAOImpl#remove(long)}.
	 */
	@Test
	public void testRemove() {
		SubastaDAO dao = SubastaDAOImpl.getInstance();
		List<Subasta> subastas = dao.readSubastas();
		
		Subasta subasta = subastas.get(0);
		Long id = subasta.getId();
		
		dao.remove(id);
		
		assertEquals(dao.readSubastas().get(0).getuserMax(), 10);
	}

	/**
	 * Test method for {@link es.upm.dit.isst.dao.SubastaDAOImpl#getSubasta(long)}.
	 */
	@Test
	public void testGetSubasta() {
		SubastaDAO dao = SubastaDAOImpl.getInstance();
		List<Subasta> subastas = dao.readSubastas();
		
		Subasta subasta = subastas.get(0);
		Long id = subasta.getId();
		
		Subasta subasta2 = dao.getSubasta(id);
		
		assertEquals(subasta2.getuserMax(), subasta.getuserMax());
	}

	/**
	 * Test method for {@link es.upm.dit.isst.dao.SubastaDAOImpl#getCustomersOfSubasta(long)}.
	 */
	@Test
	public void testGetCustomersOfSubasta() {
		SubastaDAO dao = SubastaDAOImpl.getInstance();
		List<Subasta> subastas = dao.readSubastas();
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		
		Subasta subasta = subastas.get(0);
		Long id = subasta.getId();
		ArrayList<String> customers = subasta.getUsuariosApuntados();
		
		ArrayList<String> customers2 = dao.getCustomersOfSubasta(id);
		
		assertEquals(customers2, customers);
	}

	/**
	 * Test method for {@link es.upm.dit.isst.dao.SubastaDAOImpl#updateUsersApuntados(long, int)}.
	 */
	@Test
	public void testUpdateUsersApuntados() {
		SubastaDAO dao = SubastaDAOImpl.getInstance();
		List<Subasta> subastas = dao.readSubastas();
		Subasta subasta = subastas.get(0);
		Long id = subasta.getId();
		int usersApunt = subasta.getuserApuntados();
		
		dao.updateUsersApuntados(id, usersApunt+1);
		
		assertEquals(dao.readSubastas().get(0).getuserApuntados(), 12);
	}

	/**
	 * Test method for {@link es.upm.dit.isst.dao.SubastaDAOImpl#updateState(long, boolean)}.
	 */
	@Test
	public void testUpdateState() {
		SubastaDAO dao = SubastaDAOImpl.getInstance();
		List<Subasta> subastas = dao.readSubastas();
		Subasta subasta = subastas.get(0);
		Long id = subasta.getId();
		
		dao.updateState(id, true);
		
		assertEquals(dao.readSubastas().get(0).getState(), true);
	}

	/**
	 * Test method for {@link es.upm.dit.isst.dao.SubastaDAOImpl#updateCustomers(long, java.util.ArrayList)}.
	 */
	@Test
	public void testUpdateCustomers() {
		SubastaDAO dao = SubastaDAOImpl.getInstance();
		List<Subasta> subastas = dao.readSubastas();
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		
		Subasta subasta = subastas.get(0);
		Long id = subasta.getId();
		ArrayList<String> customers = subasta.getUsuariosApuntados();
		
		customers.add("example@example.es");
		
		dao.updateCustomers(id, customers);
		
		assertEquals(subasta.getUsuariosApuntados(), customers);
	}

}
