package com.ifes.tpfinal.servicio;

import com.ifes.tpfinal.repositorio.IRepositorio;
import java.util.List;

public abstract class Servicio<T, ID> implements IServicio<T, ID> {

  protected final IRepositorio<T, ID> repo;

  protected Servicio(IRepositorio<T, ID> repo) {
    this.repo = repo;
  }

  public T crear(T t) { return repo.create(t); }
  public T actualizar(T t) { return repo.update(t); }
  public void eliminar(ID id) { repo.delete(id); }
  public T porId(ID id) { return repo.findById(id); }
  public java.util.List<T> todos() { return repo.findAll(); }
}
