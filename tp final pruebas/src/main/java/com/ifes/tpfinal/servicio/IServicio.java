package com.ifes.tpfinal.servicio;

import java.util.List;

public interface IServicio<T, ID> {
  T crear(T t);
  T actualizar(T t);
  void eliminar(ID id);
  T porId(ID id);
  List<T> todos();
}
