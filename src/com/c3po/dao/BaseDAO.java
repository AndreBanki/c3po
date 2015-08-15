package com.c3po.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.sql.DataSource;
import javax.swing.JOptionPane;

public class BaseDAO {
	
	private static EntityManager manager;
    
    public static EntityManager getConnection() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("C3PO");
        manager = null;
        manager = emf.createEntityManager();
        if (!manager.isOpen()) {
            JOptionPane.showMessageDialog(null, "Conexão fechada");
        }
        return manager;
    }
    
    
    public static void fechar(){
    	manager.close();
    }

}
