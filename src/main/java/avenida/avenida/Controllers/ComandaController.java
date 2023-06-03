package avenida.avenida.Controllers;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import avenida.avenida.Modelo.Comanda;
import avenida.avenida.Modelo.LineaComanda;
import avenida.avenida.Modelo.Mesa;
import avenida.avenida.Modelo.Producto;
import avenida.avenida.Modelo.User;
import avenida.avenida.Services.ComandaService;
import avenida.avenida.Services.MesaService;
import avenida.avenida.Services.ProductoService;
import avenida.avenida.Services.UserService;
@Controller
@RequestMapping("/comanda")
public class ComandaController {

    @Autowired
    private ComandaService comandaService;

    @Autowired
    private MesaService mesaService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductoService productoService;

    @GetMapping("/view-add")
    public String addComanda(Model model) {
        List<Mesa> mesas = mesaService.findAll();
        List<User> users = userService.findAll();
        List<Producto> productos = productoService.findAll();
        Comanda comanda = new Comanda();
        LineaComanda lineaComanda = new LineaComanda();
        lineaComanda.setComanda(comanda);
        comanda.getLineaComandas().add(lineaComanda);
        model.addAttribute("mesas", mesas);
        model.addAttribute("users", users);
        model.addAttribute("productos", productos);
        model.addAttribute("comanda", comanda);
        model.addAttribute("lineaComanda", lineaComanda);
    
        // Obtén la mesa seleccionada si existe
        Mesa selectedMesa = null;
        if (comanda.getMesa() != null) {
            selectedMesa = mesaService.findById(comanda.getMesa().getId());
        }
        model.addAttribute("selectedMesa", selectedMesa);
    
        return "views/Comanda/comanda-view-add";
    }
    

    @PostMapping("/add")
    public String saveComanda(@ModelAttribute("comanda") Comanda comanda) {
        comandaService.save(comanda);
        return "redirect:/comanda/listado";
    }

    @GetMapping("/listado")
    public String getComandas(Model model) {
        List<Comanda> comandas = comandaService.findAll();
        model.addAttribute("comandas", comandas);
        return "views/Comanda/comanda-listado";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        Comanda comanda = comandaService.findById(id);
        model.addAttribute("comanda", comanda);
        return "views/Comanda/comanda-view-edit";
    }

    @PostMapping("/edit/{id}")
    public String updateComanda(@PathVariable int id, @ModelAttribute("comanda") Comanda comanda) {
        comandaService.save(comanda); // Asegúrate de que tu método save también puede actualizar
        return "redirect:/comanda/listado";
    }
}
