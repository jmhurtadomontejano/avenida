package avenida.avenida.Services;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import avenida.avenida.Modelo.Comanda;
import avenida.avenida.Modelo.LineaComanda;
import avenida.avenida.Repositorios.ComandaRepository;

//importar exceptions
import avenida.avenida.Exceptions.ResourceNotFoundException;

@Service
public class ComandaService {

    @Autowired
    private ComandaRepository comandaRepository;

    // guardar comandas
    public Comanda save(Comanda comanda) {
        for (LineaComanda lineaComanda : comanda.getLineaComandas()) {
            // Aquí puedes realizar cualquier validación o configuración adicional en cada LineaComanda
        }
        return comandaRepository.save(comanda);
    }

    // encontrar todas las comandas
    public List<Comanda> findAll() {
        return comandaRepository.findAll();
    }

    // encontrar una comanda por ID
    public Comanda findById(int id) {
        Optional<Comanda> comanda = comandaRepository.findById(id);
        if (comanda.isPresent()) {
            return comanda.get();
        } else {
            throw new RuntimeException("Comanda no encontrada con el ID: " + id);
        }
    }
}
