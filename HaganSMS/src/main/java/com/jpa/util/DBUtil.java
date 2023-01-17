package com.jpa.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.hibernate.Session;

public class DBUtil {
	public void initializeDB() throws ClassNotFoundException, SQLException {
		Session session = SMSUtil.getSession();
		Connection connection = SMSUtil.setConnection();	
	}
}
