
package services.divulga.editais.ifsuldeminas.edu.br;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import beans.divulga.editais.ifsuldeminas.edu.br.Country;

/* 
 * author: Arpit Mandliya
 * */
@Path("/countries")
public class CountryRestService {
	
	private List<Country> listOfCountries = createCountryList();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Country> getCountries() {
		return listOfCountries;
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Country getCountryById(@PathParam("id") int id) {
		for (Country country : listOfCountries) {
			if (country.getId() == id)
				return country;
		}

		return null;
	}
	
	@POST
	@Path("{id}/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Country> addCountry(@PathParam("id")int id, @PathParam("name")String name) {
		listOfCountries.add(new Country(id, name));
		return listOfCountries;
	}
	
	@PUT
	@Path("{id}/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public Country editCountry(@PathParam("id")int id, @PathParam("name")String name) {
		for (Country country : listOfCountries) {
			if (country.getId() == id) {
				country.setCountryName(name);
				return country;
			}				
		}
		return null;
	}

	// Utiliy method to create country list.
	public List<Country> createCountryList() {
		Country indiaCountry = new Country(1, "India");
		Country chinaCountry = new Country(4, "China");
		Country nepalCountry = new Country(3, "Nepal");
		Country bhutanCountry = new Country(2, "Bhutan");
		
		listOfCountries = new ArrayList<Country>();
		listOfCountries.add(indiaCountry);
		listOfCountries.add(chinaCountry);
		listOfCountries.add(nepalCountry);
		listOfCountries.add(bhutanCountry);
		return listOfCountries;
	}
}
