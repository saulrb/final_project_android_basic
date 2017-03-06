package mx.itesm.proyecto_final_libreria_online_srb;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by saul on 25/2/2017.
 */

public class AlmacenLibrosServicio implements Serializable{

    private ArrayList<Libro> libros;

    public AlmacenLibrosServicio(ArrayList<Libro> libros) {
        this.libros = libros;
    }

    public boolean agregarLibro(Libro libro){
        boolean success = false;
        if(encuentraLibro(libro.getISBN()) == null) {
            libros.add(libro);
            success = true;
        }
        return success;
    }

    public Libro encuentraLibro(String isbn){
        Libro encontrado = null;
        if(isbn != null) {
            for(Libro libro : libros){
                if(libro.getISBN().equals(isbn)){
                    encontrado = libro;
                }
            }
        }
        return  encontrado;
    }
    public boolean venderLibro(String isbn,int cantidad){
        boolean success = false;
        Libro avender = encuentraLibro(isbn);
        if (validarVenta(avender,cantidad)){
            int nuevaExistencia = avender.getExistencia() - cantidad;
            avender.setExistencia(nuevaExistencia);
            success = true;
        }
        return success;
    }

    public boolean validarVenta(Libro libro, int cantidad){
        boolean success = false;
        int nuevaExistencia = libro.getExistencia() > 0 && (libro.getExistencia() - cantidad) > 0 ? libro.getExistencia() - cantidad : libro.getExistencia();
        if (nuevaExistencia < libro.getExistencia() ){
            success = true;
        }
        return success;
    }

    public ArrayList<Libro> getLibros() {
        return libros;
    }
}
