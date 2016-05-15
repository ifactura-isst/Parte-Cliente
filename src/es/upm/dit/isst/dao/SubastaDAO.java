package es.upm.dit.isst.dao;


import java.util.ArrayList;
import java.util.List;

import com.google.appengine.api.users.User;

import es.upm.dit.isst.model.Subasta;

public interface SubastaDAO {

	public void remove(long id);
	
	List<Subasta> readSubastas();

	List<Subasta> listSubastasByUser(User user);
		
	Subasta getSubasta(long subastaId);
	
	ArrayList<String> getCustomersOfSubasta(long subastaId);

	public Subasta add(boolean state, int userMax, int userApuntados, String title, String description, User user, ArrayList<String> customers);

	public void update(long id, boolean state, int userMax, int userApuntados, String title, String description, User user, ArrayList<String> customers);

	void updateState(long subastaId, boolean state);

	void updateUsersApuntados(long subastaId, int userApuntados);

	void updateCustomers(long subastaId, ArrayList<String> customers);


	
}
