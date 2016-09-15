package com.infosys.eventtracker.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.ldap.core.ContextSource;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.stereotype.Component;

@Component
public class MyLDAPContextSource extends LdapContextSource implements ContextSource {

    @Value("${ldap.url}")
    @Override
    public void setUrl(String url) { super.setUrl(url);  }

    @Value("${ldap.base}")
    @Override
    public void setBase(String base) {super.setBase(base); }

    @Value("${ldap.username}")
    @Override
    public void setUserDn(String userDn) {super.setUserDn(userDn); }

    @Value("${ldap.password}")
    @Override
    public void setPassword(String password) { super.setPassword(password); }
}
