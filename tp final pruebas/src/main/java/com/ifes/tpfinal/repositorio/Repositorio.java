package com.ifes.tpfinal.repositorio;

import javax.jdo.*;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public abstract class Repositorio<T, ID> implements IRepositorio<T, ID> {

  protected final PersistenceManagerFactory pmf;
  private final Class<T> entityClass;

  protected Repositorio(PersistenceManagerFactory pmf, Class<T> entityClass) {
    this.pmf = pmf;
    this.entityClass = entityClass;
  }

  @Override
  public T create(T obj) {
    PersistenceManager pm = pmf.getPersistenceManager();
    Transaction tx = pm.currentTransaction();
    try {
      tx.begin();
      T persisted = pm.makePersistent(obj);
      tx.commit();
      return pm.detachCopy(persisted);
    } finally {
      if (tx.isActive()) tx.rollback();
      pm.close();
    }
  }

  @Override
  public T update(T obj) {
    PersistenceManager pm = pmf.getPersistenceManager();
    Transaction tx = pm.currentTransaction();
    try {
      tx.begin();
      T merged = pm.makePersistent(obj);
      tx.commit();
      return pm.detachCopy(merged);
    } finally {
      if (tx.isActive()) tx.rollback();
      pm.close();
    }
  }

  @Override
  public void delete(ID id) {
    PersistenceManager pm = pmf.getPersistenceManager();
    Transaction tx = pm.currentTransaction();
    try {
      tx.begin();
      T obj = pm.getObjectById(entityClass, id);
      pm.deletePersistent(obj);
      tx.commit();
    } finally {
      if (tx.isActive()) tx.rollback();
      pm.close();
    }
  }

  @Override
  public T findById(ID id) {
    PersistenceManager pm = pmf.getPersistenceManager();
    try {
      T obj = pm.getObjectById(entityClass, id);
      return pm.detachCopy(obj);
    } finally {
      pm.close();
    }
  }

@Override
public java.util.List<T> findAll() {
    PersistenceManager pm = pmf.getPersistenceManager();
    try {
        Query<T> q = pm.newQuery(entityClass);
        @SuppressWarnings("unchecked")
        java.util.List<T> res = (java.util.List<T>) q.executeList();
        return (java.util.List<T>) pm.detachCopyAll(res);
    } finally {
        pm.close();
    }
}

}
