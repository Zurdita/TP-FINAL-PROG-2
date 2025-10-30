package com.ifes.tpfinal.dom;

import javax.jdo.annotations.*;

@PersistenceCapable(detachable = "true")
public class Auto extends Rodado {

  @Column(allowsNull = "false")
  private int puertas;

  public Auto() {}

  public Auto(String marca, String modelo, int anio, int puertas) {
    super(marca, modelo, anio);
    this.puertas = puertas;
  }

  public int getPuertas() { return puertas; }
  public void setPuertas(int puertas) { this.puertas = puertas; }

@Override
public String toString() {
    return String.format(
        "Auto{id=%d, marca='%s', modelo='%s', anio=%d, puertas=%d}",
        getId(), getMarca(), getModelo(), getAnio(), puertas
    );
}

}
