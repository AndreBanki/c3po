package com.c3po.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

public class BaseDAO {
	
	private static EntityManager manager;
    
    public static EntityManager getConnection() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("C3PO");
        manager = null;
        manager = emf.createEntityManager();
        if (!manager.isOpen()) {
            JOptionPane.showMessageDialog(null, "Conex�o fechada");
        }
        return manager;
    }
    
    
    public static void fechar(){
    	manager.close();
    }

}
