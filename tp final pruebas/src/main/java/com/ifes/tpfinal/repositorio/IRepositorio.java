package com.ifes.tpfinal.repositorio;

import java.util.List;

public interface IRepositorio<T, ID> {
  T create(T obj);
  T update(T obj);
  void delete(ID id);
  T findById(ID id);
  List<T> findAll();
}
