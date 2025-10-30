package com.ifes.tpfinal.repositorio;

import com.ifes.tpfinal.dom.Concesionaria;
import javax.jdo.*;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class RepositorioConcesionaria extends Repositorio<Concesionaria, Long> {

  public RepositorioConcesionaria(PersistenceManagerFactory pmf) {
    super(pmf, Concesionaria.class);
  }

  public List<Concesionaria> findByDomicilio(String domicilio) {
    PersistenceManager pm = pmf.getPersistenceManager();
    try {
      Query<Concesionaria> q = pm.newNamedQuery(Concesionaria.class, "Concesionaria.findByDomicilio");
      @SuppressWarnings("unchecked")
      List<Concesionaria> res = (List<Concesionaria>) q.execute(domicilio);
      return (List<Concesionaria>) pm.detachCopyAll(res);
    } finally {
      pm.close();
    }
  }
}
