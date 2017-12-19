package services.divulga.editais.ifsuldeminas.edu.br;

import javax.persistence.EntityManager;

import utils.divulga.editais.ifsuldeminas.edu.br.EMFactory;

public class BaseService {
	
	protected EntityManager getEM() {
		return EMFactory.getInstance().getEntityManager();
	}
	
	protected EntityManager closetEM() {
		return EMFactory.getInstance().getEntityManager();
	}
	
	protected void beginTransaction() {
		if(!getEM().getTransaction().isActive()) {
			getEM().getTransaction().begin();
		}
	}
	
	protected void commitTransaction() {
		getEM().getTransaction().commit();
	}
}
