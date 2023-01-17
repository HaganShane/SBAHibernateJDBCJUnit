/**
 * @author Shane Hagan
 * Date: 01-13-2023
 * Project: SBA - Core Java/Hibernate/JUnit
 * Class: SMSUtil - contains our optional methods for session and connection to our database
 * Not required, just makes our code cleaner in other classes to call these methods, as opposed to rewriting the code each time
 */

package com.jpa.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SMSUtil {
	
	/**
	 * getSession method will return a session for us to use relating to the database
	 * This method makes it easier so we do not retype this code in every method
	 * @return
	 */
	public static Session getSession() {
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();
		return session;
	}
	
	/**
	 * setConnection method will return a connection for us to use with the database
	 * This method makes it easier so we do not retype this code in every method
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static Connection setConnection() throws ClassNotFoundException, SQLException {
        String url = "jdbc:mysql://localhost:3306/smsdb";
        String user = "root";
        String password = "password";
        
        Class.forName("com.mysql.cj.jdbc.Driver");
        
        Connection connection = DriverManager.getConnection(url, user, password);
		
        return connection;
	}
}
