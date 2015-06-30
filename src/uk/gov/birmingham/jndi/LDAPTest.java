package uk.gov.birmingham.jndi;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;



public class LDAPTest {
	public static void main(String [] args) {
		
		
		final String groupDN = "CN=GG_CYPF_Users,OU=Application_Groups,OU=Applications,DC=addm,DC=ads,DC=brm,DC=pri";	
		final String userDN = "CN=Ammar Khalid,OU=Applications,OU=Service Birmingham,DC=addm,DC=ads,DC=brm,DC=pri";
		final String ldapAdServer = "ldap://addm.ads.brm.pri:389";
		final String ldapSearchBase = "DC=addm,DC=ads,DC=brm,DC=pri";
		final String ldapUserName = "CN=Service Account CYPF eRecords,OU=Application_Service_Accounts,OU=Applications,DC=addm,DC=ads,DC=brm,DC=pri";
		final String ldapPassword = "#3R3c0Rd5";
		//final String ldapPassword = "#3R3c0Rd53";
		
		Hashtable<String, String> env = new Hashtable<String, String>();
		
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.SECURITY_AUTHENTICATION, "simple");
		env.put(Context.SECURITY_PRINCIPAL, ldapUserName);
		env.put(Context.SECURITY_CREDENTIALS, ldapPassword);		
		env.put(Context.PROVIDER_URL, ldapAdServer);
		
		DirContext ctx;
		
		try {
		    //Authenticate the logon user
		    ctx = new InitialDirContext(env);
		    System.out.println(ctx.getNameInNamespace());
		    
			//lctx = new InitialLdapContext(env, null);
		    //boolean isMember = LDAPUtils.isMemberOfADGroup(ctx, groupDN, userDN);
		    //System.out.println(isMember);
		    //String searchBase = "DC=addm,DC=ads,DC=brm,DC=pri";
		    
		    // Perform an exact group match with the "memberOf" attribute.
		    StringBuilder searchFilter = new StringBuilder("(&");
		    searchFilter.append("(objectClass=person)");
		    searchFilter.append("(userPrincipalName=" + ldapUserName + ")");
		    searchFilter.append("(memberOf=" + groupDN + ")");
		    searchFilter.append(")");
		 
		    SearchControls sCtrl = new SearchControls();
		    sCtrl.setSearchScope(SearchControls.SUBTREE_SCOPE);
		 
		    NamingEnumeration answer = ctx.search(ldapSearchBase, searchFilter.toString(), sCtrl);
		    boolean pass = false;
		 
		    if (answer.hasMoreElements()) {
		        pass = true;
		    }
		 
		    if (pass) {
		        // The user belongs to the group. Do something...
		    	System.out.println("The user belongs to the group. Do something...");
		    } else {
		        // The user doesn't belong to the group.
		    	System.out.println("The user doesn't belong to the group");
		    }
		    
		} catch (NamingException ex) {
		    //Do something with the exception...
		}

	}

}
