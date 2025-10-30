package com.ifes.tpfinal.configuracion;

import javax.jdo.PersistenceManagerFactory;
import javax.jdo.JDOHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Configuracion {
  @Bean
  public PersistenceManagerFactory pmf() {
    return JDOHelper.getPersistenceManagerFactory("mysql");
  }
}
