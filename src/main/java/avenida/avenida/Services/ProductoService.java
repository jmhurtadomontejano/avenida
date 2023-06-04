package avenida.avenida.Services;

import avenida.avenida.Modelo.Producto;
import avenida.avenida.Repositorios.ProductoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;


@Service
public class ProductoService {

    @Autowired
    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    // Encontrar todas las productos
    public List<Producto> findAll() {
        List<Producto> productos = productoRepository.findAll();
        System.out.println("Productos recuperados: " + productos);
        productos.sort(Comparator.comparing(Producto::getNombre));
        return productos;
    }    

    // Encontrar una producto por ID
    public Producto findById(int id) {
        Optional<Producto> producto = productoRepository.findById(id);
        if (producto.isPresent()) {
            return producto.get();
        } else {
            throw new RuntimeException("producto no encontrada con el ID: " + id);
        }
    }

    // Encontrar una producto por precio
    public List<Producto> findByPrecio(String precio) {
        return productoRepository.findByPrecio(precio);
    }

    // Guardar una producto
    public Producto save(Producto producto) {
        return productoRepository.save(producto);
    }

    // Actualizar una producto existente
    public Producto update(int id, Producto productoDetails) {
        Producto producto = findById(id);

        producto.setNombre(productoDetails.getNombre());
        producto.setPrecio(productoDetails.getPrecio());
        producto.setId(productoDetails.getId());
        producto.setLineaComanda(productoDetails.getLineaComanda());

        return productoRepository.save(producto);
    }

    // Eliminar una producto por ID
    public void delete(int id) {
        Producto producto = findById(id);
        productoRepository.delete(producto);
    }




}