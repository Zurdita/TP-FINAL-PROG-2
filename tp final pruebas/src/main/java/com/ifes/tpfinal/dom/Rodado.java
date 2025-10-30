package com.ifes.tpfinal.dom;

import javax.jdo.annotations.*;

@PersistenceCapable(detachable = "true")
@Inheritance(strategy = InheritanceStrategy.NEW_TABLE)
public abstract class Rodado {

  @PrimaryKey
  @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
  private Long id;

  @Column(allowsNull = "false")
  private String marca;

  @Column(allowsNull = "false")
  private String modelo;

  @Column(allowsNull = "false")
  private int anio;

  @Persistent(defaultFetchGroup = "true")
  private Concesionaria concesionaria;

  public Rodado() {}

  public Rodado(String marca, String modelo, int anio) {
    this.marca = marca;
    this.modelo = modelo;
    this.anio = anio;
  }

  public Long getId() { return id; }
  public String getMarca() { return marca; }
  public void setMarca(String marca) { this.marca = marca; }

  public String getModelo() { return modelo; }
  public void setModelo(String modelo) { this.modelo = modelo; }

  public int getAnio() { return anio; }
  public void setAnio(int anio) { this.anio = anio; }

  public Concesionaria getConcesionaria() { return concesionaria; }
  public void setConcesionaria(Concesionaria concesionaria) { this.concesionaria = concesionaria; }

@Override
public String toString() {
    return String.format(
        "Rodado{id=%d, marca='%s', modelo='%s', anio=%d}",
        id, marca, modelo, anio
    );
}

}
