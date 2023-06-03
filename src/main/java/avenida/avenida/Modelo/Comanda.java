package avenida.avenida.Modelo;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import org.springframework.context.annotation.ComponentScan;
//events

@ComponentScan
@Entity
@Table(name = "comanda")
public class Comanda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "IdMesa")
    private Mesa mesa;

   
    @ManyToOne
    @JoinColumn(name = "IdCamarero")
    private User idCamarero;


    @Column(name = "Date")
    private java.util.Date date;

    @Column(name = "Hour")
    private LocalTime hour;

    @Column(name = "RegistryDate")
    private java.util.Date registryDate;

    @Column(name = "ImporteComanda")
    private Double importeComanda;

    @Column(name = "NumComensales")
    private int numComensales;

    //La anotación @OneToMany significa que una Comanda puede tener muchas LineaComandas. La anotación @JoinColumn especifica la columna en la tabla linea_comanda que se usa para la relación.
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "comanda_id")
    private List<LineaComanda> lineaComandas = new ArrayList<>();

// Constructor vacío
    public Comanda() {
    }

// Constructor con parámetros
    public Comanda(int id, Mesa mesa, User idCamarero, Date date, LocalTime hour, Date registryDate,
            Double importeComanda, int numComensales, List<LineaComanda> lineaComandas) {
        this.id = id;
        this.mesa = mesa;
        this.idCamarero = idCamarero;
        this.date = date;
        this.hour = hour;
        this.registryDate = registryDate;
        this.importeComanda = importeComanda;
        this.numComensales = numComensales;
        this.lineaComandas = lineaComandas;
    }

// Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    public User getIdCamarero() {
        return idCamarero;
    }

    public void setIdCamarero(User idCamarero) {
        this.idCamarero = idCamarero;
    }

    public java.util.Date getDate() {
        return date;
    }

    public void setDate(java.util.Date date) {
        this.date = date;
    }

    public LocalTime getHour() {
        return hour;
    }

    public void setHour(LocalTime hour) {
        this.hour = hour;
    }

    public java.util.Date getRegistryDate() {
        return registryDate;
    }

    public void setRegistryDate(java.util.Date registryDate) {
        this.registryDate = registryDate;
    }

    public Double getImporteComanda() {
        return importeComanda;
    }

    public void setImporteComanda(Double importeComanda) {
        this.importeComanda = importeComanda;
    }

    public int getNumComensales() {
        return numComensales;
    }

    public void setNumComensales(int numComensales) {
        this.numComensales = numComensales;
    }

    public List<LineaComanda> getLineaComandas() {
        return lineaComandas;
    }

    public void setLineaComandas(List<LineaComanda> lineaComandas) {
        this.lineaComandas = lineaComandas;
    }
//ToString
    @Override
    public String toString() {
        return "Comanda [id=" + id + ", mesa=" + mesa + ", idCamarero=" + idCamarero + ", date=" + date + ", hour="
                + hour + ", registryDate=" + registryDate + ", importeComanda=" + importeComanda + ", numComensales="
                + numComensales + ", lineaComandas=" + lineaComandas + "]";
    }

}