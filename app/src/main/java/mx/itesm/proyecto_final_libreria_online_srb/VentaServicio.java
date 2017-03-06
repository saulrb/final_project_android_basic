package mx.itesm.proyecto_final_libreria_online_srb;

import java.util.ArrayList;

/**
 * Created by saul on 25/2/2017.
 */

public class VentaServicio {

    private ArrayList<Venta> ventas;

    public VentaServicio(ArrayList<Venta> ventas) {
        this.ventas = ventas;
    }

    public ArrayList<Venta> getVentas() {
        return ventas;
    }

    public void setVentas(ArrayList<Venta> ventas) {
        this.ventas = ventas;
    }

    public Venta encuentraVenta(int ventaId, String usuario){
        Venta venta = null;
        if(ventaId > 0 || usuario != null){
            for(Venta venta1 : ventas){
                if(venta.getUsuario().equals(usuario) && !venta.isCerrada()){
                    venta = venta1;
                }
                if(venta.getIdVenta() == ventaId ){
                    venta = venta1;
                }
            }
        }
        return venta;
    }

    public Venta crearVenta(String usuario){
        ArrayList<LibroCarrito> libroCarritos = new ArrayList<LibroCarrito>();
        Venta venta = new Venta(libroCarritos,usuario,0,this.ventas.size()+1,false);
        return venta;
    }

}
