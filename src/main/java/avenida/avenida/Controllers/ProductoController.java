package avenida.avenida.Controllers;

import avenida.avenida.Modelo.Producto;
import avenida.avenida.Services.ProductoService;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

 //ProductoController  
    @Controller
    @RequestMapping("/producto")
    public class ProductoController {
    
        @Autowired
        private ProductoService productoService;

        //ir a ver todos los productos
        @GetMapping("/listado-producto")
        public String listarProductos(Model model) {
            List<Producto> productos = productoService.findAll();
            model.addAttribute("productos", productos);
            model.addAttribute("producto", new Producto()); // Añade esta línea
            return "/views/Producto/listado-producto";
        }

    //ir a la ventana de Guardar producto
     @GetMapping("/agregarProducto")
    public String agregarProducto(Model model) {
        // Guardar un nuevo producto
        model.addAttribute("newProducto", new Producto());
        // Redirigir a la lista de productos
        return "/views/Producto/agregarProducto";
    }

   @PostMapping("/agregaProducto")
   public String saveProducto(@ModelAttribute("newProducto") Producto newProducto) {
       // Guardar un nuevo producto
       productoService.save(newProducto);
       // Redirigir a la lista de mesas
       return "redirect:/producto/listado-producto";
   }

    // Obtener todos los productos (GET)
        @GetMapping
        public ResponseEntity<List<Producto>> getAllProductos() {
            List<Producto> productos = productoService.findAll();
            return new ResponseEntity<>(productos, HttpStatus.OK);
        }
    
    // Obtener un producto por ID (GET)
        @GetMapping("/{id}")
        public ResponseEntity<Producto> getProductoById(@PathVariable int id) {
            Producto producto = productoService.findById(id);
            return new ResponseEntity<>(producto, HttpStatus.OK);
        }
    
    //Obtener producto para editar en html
        @GetMapping("/edit-producto/{id}")
        public String verproductoDetalle(@PathVariable int id, Model model) {
            Producto producto = productoService.findById(id);
            model.addAttribute("producto", producto);
            return "/views/Producto/edit-producto";
        }

    @PostMapping("/edit-producto/{id}")
    public String updateProducto(@PathVariable int id, @ModelAttribute("producto") Producto producto) {
        producto.setId(id); // Establece el ID del producto
        productoService.save(producto); // Guarda los cambios en el producto
        return "redirect:/producto/listado-producto";
    }
    
    // Eliminar una producto por ID (DELETE)
        @PostMapping("/delete/{id}")
        public String deleteProducto(@PathVariable int id) {
            productoService.delete(id);
            return "redirect:/producto/listado-producto";
        }
    
    // Creando la interfaz web
        public ProductoController(ProductoService productoService) {
            this.productoService = productoService;
        }

 }