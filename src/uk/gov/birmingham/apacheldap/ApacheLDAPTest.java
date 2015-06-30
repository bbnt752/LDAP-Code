package uk.gov.birmingham.apacheldap;

import org.apache.directory.api.ldap.model.exception.LdapException;
import org.apache.directory.ldap.client.api.*;

public class ApacheLDAPTest {
	
	public static void main(String args[]) {
		final String ldapUserName = "CN=Service Account CYPF eRecords,OU=Application_Service_Accounts,OU=Applications,DC=addm,DC=ads,DC=brm,DC=pri";
		final String ldapPassword = "#3R3c0Rd5";
		
		
		LdapConnection connection = new LdapNetworkConnection("addm.ads.brm.pri", 389);
		//The following statement keeps the connection opened forever
		connection.setTimeOut(0);
		try {
			connection.bind(ldapUserName, ldapPassword);
		} catch (LdapException ldapE) {
			ldapE.printStackTrace();
			
		}
		System.out.println("Connection is connected: " + connection.isConnected());
		System.out.println("");
	}

}
