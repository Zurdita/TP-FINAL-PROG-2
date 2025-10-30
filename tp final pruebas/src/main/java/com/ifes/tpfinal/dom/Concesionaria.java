package com.ifes.tpfinal.dom;

import javax.jdo.annotations.*;
import java.util.ArrayList;
import java.util.List;

@PersistenceCapable(detachable = "true")
@Queries({
  @Query(
    name = "Concesionaria.findByDomicilio",
    language = "JDOQL",
    value = "SELECT FROM com.ifes.tpfinal.dom.Concesionaria WHERE domicilio == :domicilio"
  )
})
public class Concesionaria {

  @PrimaryKey
  @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
  private Long id;

  @Column(allowsNull = "false")
  private String nombre;

  @Column(allowsNull = "false")
  private String domicilio;

  @Embedded
  private Contacto contacto;

  @Persistent(defaultFetchGroup = "true", mappedBy = "concesionaria")
  @Join
  private List<Rodado> stock = new ArrayList<>();

  public Concesionaria() {}

  public Concesionaria(String nombre, String domicilio, Contacto contacto) {
    this.nombre = nombre;
    this.domicilio = domicilio;
    this.contacto = contacto;
  }

  public Long getId() { return id; }

  public String getNombre() { return nombre; }
  public void setNombre(String nombre) { this.nombre = nombre; }

  public String getDomicilio() { return domicilio; }
  public void setDomicilio(String domicilio) { this.domicilio = domicilio; }

  public Contacto getContacto() { return contacto; }
  public void setContacto(Contacto contacto) { this.contacto = contacto; }

  public List<Rodado> getStock() { return stock; }

  public void agregarRodado(Rodado r) {
    if (r != null) {
      r.setConcesionaria(this);
      stock.add(r);
    }
  }

@Override
public String toString() {
    return String.format(
        "Concesionaria{id=%d, nombre='%s', domicilio='%s', contacto=%s, stock_size=%d}",
        id, nombre, domicilio, contacto, (stock != null ? stock.size() : 0)
    );
}

}
