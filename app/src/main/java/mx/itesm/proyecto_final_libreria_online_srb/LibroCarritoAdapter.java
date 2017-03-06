package mx.itesm.proyecto_final_libreria_online_srb;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by saul on 1/3/2017.
 */

public class LibroCarritoAdapter  extends ArrayAdapter<LibroCarrito> {
    /**
     * Constructor
     *
     * @param context  The current context.
     *
     * @param objects  The objects to represent in the ListView.
     */
    public LibroCarritoAdapter(Context context, List<LibroCarrito> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LibroCarrito libro = getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row2,parent,false);
        }
        ImageView ivLibro = (ImageView) convertView.findViewById(R.id.ivLibro);
        TextView tvISBN = (TextView) convertView.findViewById(R.id.isbn);
        TextView tvTitulo = (TextView) convertView.findViewById(R.id.titulo);
        TextView tvCompra = (TextView) convertView.findViewById(R.id.compra);
        TextView tvPrecio = (TextView) convertView.findViewById(R.id.precio);

        ivLibro.setImageResource(libro.getImagen());
        tvISBN.setText(libro.getISBN());
        tvTitulo.setText(libro.getTitulo());
        tvCompra.setText(libro.getCantidadFormatted());
        tvPrecio.setText(libro.getPrecioFormatted());

        return convertView;
    }
}
