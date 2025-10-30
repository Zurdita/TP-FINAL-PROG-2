package com.ifes.tpfinal.dom;

import javax.jdo.annotations.*;

@PersistenceCapable(detachable = "true")
public class Camioneta extends Rodado {

  @Column(allowsNull = "false")
  private boolean traccion4x4;

  public Camioneta() {}

  public Camioneta(String marca, String modelo, int anio, boolean traccion4x4) {
    super(marca, modelo, anio);
    this.traccion4x4 = traccion4x4;
  }

  public boolean isTraccion4x4() { return traccion4x4; }
  public void setTraccion4x4(boolean traccion4x4) { this.traccion4x4 = traccion4x4; }

@Override
public String toString() {
    return String.format(
        "Camioneta{id=%d, marca='%s', modelo='%s', anio=%d, traccion4x4=%b}",
        getId(), getMarca(), getModelo(), getAnio(), traccion4x4
    );
}

}
