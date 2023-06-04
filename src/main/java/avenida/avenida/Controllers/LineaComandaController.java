package avenida.avenida.Controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import avenida.avenida.Modelo.LineaComanda;
import avenida.avenida.Modelo.Comanda;
import avenida.avenida.Modelo.Producto;
import avenida.avenida.Modelo.User;
import avenida.avenida.Repositorios.LineaComandaRepository;
import avenida.avenida.Services.ComandaService;
import avenida.avenida.Services.LineaComandaService;
import avenida.avenida.Services.ProductoService;
import avenida.avenida.Services.UserService;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
//reparaciones
import javax.validation.Valid;

@Controller
@RequestMapping("/lineaComanda")
public class LineaComandaController {
  
    // Inyecciones de dependencias
        @Autowired
        private LineaComandaService lineaComandaService;
    
        @Autowired
        private ComandaService comandaService;  

        @Autowired
        private UserService userService;
    
        @Autowired
        private LineaComandaRepository lineaComandaRepository;
    
        @Autowired
        private ProductoService productoService;
    
//añadir lineaComanda
    @PostMapping("/add")
    public String addLineaComanda(@ModelAttribute LineaComanda lineaComanda, Model model) {
        if(lineaComanda.getComanda() == null) {
            // Añadimos un mensaje de error al modelo o realiza alguna acción para manejar este caso
            model.addAttribute("error", "La comanda es nula");
            return "nombre-de-tu-vista-de-error";
        }

        Comanda comanda = comandaService.findById(lineaComanda.getComanda().getId());

        // Configuramos la comanda en la LineaComanda
        lineaComanda.setComanda(comanda);
        lineaComandaService.save(lineaComanda);

        // Añadimos la nueva LineaComanda al modelo para que se pueda acceder a ella en la vista
        model.addAttribute("lineaComanda", lineaComanda);
        
        // Añadimos una nueva LineaComanda al modelo para el formulario de la próxima entrada
        model.addAttribute("nuevaLineaComanda", new LineaComanda());

        // Devolvemos la vista comanda-view-edit, que se actualizará con la nueva LineaComanda
        return "views/Comanda/comanda-view-edit";
    }


// cargar editar lineaComanda
        @GetMapping("/edit/{id}")
        public String showEditLineaComandaForm(@PathVariable("id") int id, Model model) {
            try {
                LineaComanda lineaComanda = lineaComandaService.findById(id);
                List<Producto> productos = productoService.findAll();
                List<User> users = userService.findAll();
    
                model.addAttribute("lineaComanda", lineaComanda);
                model.addAttribute("productos", productos);
                model.addAttribute("users", users);
    
                return "views/LineaComanda/lineaComanda-edit";
             } catch (RuntimeException e) {
                return "erorr"; // Mostrar una página de error personalizada si la reparación no se encuentra
            } 
        }
    
    
// editar lineaComanda
        @PostMapping("/update-post")
        public String updateLineaComanda(@ModelAttribute("lineaComanda") LineaComanda lineaComanda, BindingResult result, Model model) {
            if (result.hasErrors()) {
                // Manejar errores de validación aquí
                return "views/LineaComanda/lineaComanda-edit";
            }
            lineaComandaService.save(lineaComanda);
    
            return "redirect:/LineaComanda/listado-lineaComanda";
            // Redirige al usuario a la lista de lineaComanda después de guardar los cambios
        }
    
        @GetMapping
        public String showLineaComanda(Model model) {
            List<LineaComanda> lineaComanda = lineaComandaService.findAll();
            model.addAttribute("lineaComanda", lineaComanda);
            return "lineaComanda";
        }
    
        @GetMapping("/listado-lineaComanda")
        public String listadoLineaComanda(Model model) {
            List<LineaComanda> lineaComanda = lineaComandaService.findAll();
            List<User> users = userService.findAll();
            List<Producto> productos = productoService.findAll();
            model.addAttribute("lineaComanda", lineaComanda);
            model.addAttribute("users", users);
            model.addAttribute("productos", productos);
            return "views/LineaComanda/listado-lineaComanda";
        }
        @GetMapping("/lineaComanda-add")
        public String addLineaComanda(Model model) {
            LineaComanda lineaComanda = new LineaComanda();
            List<Producto> productos = productoService.findAll();
            List<User> users = userService.findAll();
    
            model.addAttribute("lineaComanda", lineaComanda);
            model.addAttribute("productos", productos);
            model.addAttribute("users", users);
    
            return "views/lineaComanda/LineaComanda/lineaComanda-add";
        }
        @PutMapping("/{id}")
    public ResponseEntity<LineaComanda> updateLineaComanda(@PathVariable int id, @Valid @RequestBody LineaComanda lineaComanda) {
        LineaComanda updatedLineaComanda = lineaComandaService.update(id, lineaComanda);
        return new ResponseEntity<>(updatedLineaComanda, HttpStatus.OK);
    }

    @PostMapping("/delete/{id}")
    public String deleteLineaComanda(@PathVariable int id) {
        lineaComandaService.deleteById(id);
        return "redirect:/LineaComanda/listado-lineaComanda";
    }
}