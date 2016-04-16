package es.upm.dit.isst.facturas.dao;

import java.util.List;
import com.google.appengine.api.users.User;
import es.upm.dit.isst.facturas.model.Factura;

public interface FacturasDAO {
	
	public Factura add (String nombre, String apellidos, String tipo, String empresa, String startDate, String endDate,
			Double importe, String municipio, String provincia, User user);
	
	public List<Factura> getFacturas (User user);
	
	//public void delete (Long id);

}
