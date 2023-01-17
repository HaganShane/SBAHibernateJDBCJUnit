package com.jpa.jpa;

import java.sql.SQLException;

import com.jpa.util.SMSMenu;

public class SMSRunner 
{
    public static void main( String[] args ) throws ClassNotFoundException, SQLException
    {
    	SMSMenu menuObj = new SMSMenu();
    	
    	menuObj.determineStatus();
    }
}
