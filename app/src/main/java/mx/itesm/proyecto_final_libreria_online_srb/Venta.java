package mx.itesm.proyecto_final_libreria_online_srb;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by saul on 25/2/2017.
 */

public class Venta implements Serializable{

    private double totalVenta;
    private ArrayList<LibroCarrito> libroCarritos;
    String usuario;
    int idVenta;
    boolean cerrada;

    public Venta(ArrayList<LibroCarrito> libroCarritos, String usuario, double totalVenta, int idVenta, boolean cerrada) {
        this.libroCarritos = libroCarritos;
        this.totalVenta = totalVenta;
        this.usuario = usuario;
        this.idVenta = idVenta;
        this.cerrada = cerrada;
    }

    public ArrayList<LibroCarrito> getLibroCarritos() {
        return libroCarritos;
    }

    public void setLibroCarritos(ArrayList<LibroCarrito> libroCarritos) {
        this.libroCarritos = libroCarritos;
    }

    public double getTotalVenta() {
        return totalVenta;
    }

    public void setTotalVenta(double totalVenta) {
        this.totalVenta = totalVenta;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public boolean isCerrada() {
        return cerrada;
    }

    public void setCerrada(boolean cerrada) {
        this.cerrada = cerrada;
    }

    public void agregarLibro(Libro libro, int cantidad){
        LibroCarrito libroCarrito = encuentraLibro(libro);
        if (encuentraLibro(libro) == null) {
            libroCarrito = new LibroCarrito(0, libro.getImagen(), libro.getISBN(), libro.getPrecio(), libro.getTitulo(), 0);
            libroCarrito.setCantidad(cantidad);
            libroCarrito.setTotalVenta((cantidad * libro.getPrecio()));
            libroCarritos.add(libroCarrito);
        }else {
            libroCarrito.setCantidad(cantidad + libroCarrito.getCantidad());
        }
    }

    public LibroCarrito encuentraLibro(Libro libro){
        LibroCarrito libroCarrito = null;
        for(LibroCarrito libroCarrito1 : libroCarritos){
            if(libro.getISBN().equalsIgnoreCase(libroCarrito1.getISBN())) {
                libroCarrito = libroCarrito1;
            }
        }
        return libroCarrito;
    }
}
