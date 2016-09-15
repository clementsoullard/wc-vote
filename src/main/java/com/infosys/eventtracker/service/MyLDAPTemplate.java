package com.infosys.eventtracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.ContextSource;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.stereotype.Component;

@Component
public class MyLDAPTemplate extends LdapTemplate {
	  @Autowired
	    public MyLDAPTemplate(ContextSource contextSource) { super(contextSource); }
}
