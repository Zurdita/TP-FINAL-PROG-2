package com.ifes.tpfinal.servicio;

import com.ifes.tpfinal.dom.Auto;
import com.ifes.tpfinal.dom.Camioneta;
import com.ifes.tpfinal.dom.Concesionaria;
import com.ifes.tpfinal.repositorio.IRepositorio;
import com.ifes.tpfinal.repositorio.RepositorioConcesionaria;
import org.springframework.stereotype.Service;

import javax.jdo.*;
import java.util.HashMap;
import java.util.Map;

@Service
public class ServicioConcesionaria extends Servicio<Concesionaria, Long> {

  private final PersistenceManagerFactory pmf;
  private final RepositorioConcesionaria repoConc;

  public ServicioConcesionaria(IRepositorio<Concesionaria, Long> repo, PersistenceManagerFactory pmf) {
    super(repo);
    this.pmf = pmf;
    this.repoConc = (RepositorioConcesionaria) repo;
  }

  public Map<String, Long> informe() {
    PersistenceManager pm = pmf.getPersistenceManager();
    try {
      long autos = (Long) pm.newQuery("SELECT count(this) FROM " + Auto.class.getName()).executeUnique();
      long camionetas = (Long) pm.newQuery("SELECT count(this) FROM " + Camioneta.class.getName()).executeUnique();
      Map<String, Long> m = new HashMap<>();
      m.put("autos", autos);
      m.put("camionetas", camionetas);
      return m;
    } finally {
      pm.close();
    }
  }

  public java.util.List<Concesionaria> porDomicilio(String domicilio) {
    return repoConc.findByDomicilio(domicilio);
  }
}
