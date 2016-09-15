package com.infosys.eventtracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.query.LdapQuery;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.infosys.eventtracker.object.LDAPUser;
import static org.springframework.ldap.query.LdapQueryBuilder.query;

import javax.annotation.Resource;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;

/**
 * 
 * @author Clement_Soullard
 *
 */
@Component
public class LDAPService {

	
	@Autowired
	private MyLDAPTemplate ldapTemplate;

	public void findEmail() {
		AttributesMapper<String> attm = new AttributesMapper<String>() {
			@Override
			public String mapFromAttributes(Attributes arg0) throws NamingException {
				return null;
			}
		};
		ldapTemplate.search(query().where("objectclass").is("person"), attm);
	}

}
