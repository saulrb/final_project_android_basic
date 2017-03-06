package mx.itesm.proyecto_final_libreria_online_srb;

import java.text.DecimalFormat;

/**
 * Created by saul on 25/2/2017.
 */

public class LibroCarrito extends Libro {

    int ventaId;
    int cantidad;
    double totalVenta;
    private static final String INT_FORMAT = "0";

    public LibroCarrito(int existencia, int imagen, String ISBN, double precio, String titulo, int ventaId) {
        super(existencia, imagen, ISBN, precio, titulo);
        this.ventaId = ventaId;
    }

    public int getVentaId() {
        return ventaId;
    }

    public void setVentaId(int ventaId) {
        this.ventaId = ventaId;
    }

    public double getTotalVenta() {
        return totalVenta;
    }

    public void setTotalVenta(double totalVenta) {
        this.totalVenta = totalVenta;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getCantidadFormatted() {
        DecimalFormat formmater = new DecimalFormat(INT_FORMAT);
        return formmater.format(this.cantidad);
    }
}
