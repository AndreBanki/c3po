package com.c3po.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

public class BaseDAO {
	
	private static EntityManager manager;
    

    public static EntityManager getConnection() {
        EntityManagerFactory emf = null;
        manager = null;
        emf = Persistence.createEntityManagerFactory("C3PO");
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
