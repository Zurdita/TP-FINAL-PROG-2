package com.ifes.tpfinal.controlador;

import com.ifes.tpfinal.dom.*;
import com.ifes.tpfinal.servicio.ServicioConcesionaria;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/concesionarias")
public class ConcesionariaControllerMvc {

  private final ServicioConcesionaria servicio;

  public ConcesionariaControllerMvc(ServicioConcesionaria servicio) {
    this.servicio = servicio;
  }

  @GetMapping("/consultar")
  public String consultar(Model model) {
    model.addAttribute("concesionarias", servicio.todos());
    return "concesionarias/consultar";
  }

  @GetMapping("/nuevo")
  public String nuevo(Model model) {
    model.addAttribute("concesionaria", new Concesionaria());
    model.addAttribute("contacto", new Contacto());
    return "concesionarias/nuevo";
  }

  @PostMapping
  public String crear(@RequestParam String nombre,
                      @RequestParam String domicilio,
                      @RequestParam(required = false) String telefono,
                      @RequestParam(required = false) String email) {
    var c = new Concesionaria(nombre, domicilio, new Contacto(telefono, email));
    servicio.crear(c);
    return "redirect:/concesionarias/consultar";
  }

  @GetMapping("/{id}")
  public String detalle(@PathVariable Long id, Model model) {
    var conc = servicio.porId(id);
    model.addAttribute("concesionaria", conc);
    model.addAttribute("rodado", new Auto());
    return "concesionarias/detalle";
  }

  @GetMapping("/{id}/rodados/nuevo")
  public String nuevoRodado(@PathVariable Long id, Model model) {
    model.addAttribute("concesionaria", servicio.porId(id));
    return "rodados/nuevo";
  }

  @PostMapping("/{id}/rodados")
  public String agregarRodado(@PathVariable Long id,
                              @RequestParam String tipo,
                              @RequestParam String marca,
                              @RequestParam String modelo,
                              @RequestParam int anio,
                              @RequestParam(required = false, defaultValue = "4") Integer puertas,
                              @RequestParam(required = false, defaultValue = "false") boolean traccion4x4) {
    var conc = servicio.porId(id);
    Rodado r;
    if ("AUTO".equalsIgnoreCase(tipo)) {
      r = new Auto(marca, modelo, anio, puertas != null ? puertas : 4);
    } else {
      r = new Camioneta(marca, modelo, anio, traccion4x4);
    }
    conc.agregarRodado(r);
    servicio.actualizar(conc);
    return "redirect:/concesionarias/" + id;
  }

  @GetMapping("/buscar")
  public String buscar(@RequestParam(required = false) String domicilio, Model model) {
    if (domicilio != null && !domicilio.isBlank()) {
      model.addAttribute("concesionarias", servicio.porDomicilio(domicilio));
      model.addAttribute("domicilio", domicilio);
      return "concesionarias/consultar";
    }
    return "redirect:/concesionarias/consultar";
  }

  @GetMapping("/informe")
  public String informe(Model model) {
    model.addAttribute("informe", servicio.informe());
    return "concesionarias/informe";
  }
}
