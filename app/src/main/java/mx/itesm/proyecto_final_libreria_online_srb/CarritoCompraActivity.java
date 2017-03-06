package mx.itesm.proyecto_final_libreria_online_srb;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class CarritoCompraActivity extends ListActivity {

    LibroCarritoAdapter libroCarritoAdapter;
    TextView tvTotalVenta;
    private Venta ventaUsuario;
    private static final String DOUBLE_FORMAT_PRECIO = "$0.00";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito_compra);
        Intent intent = getIntent();
        ventaUsuario = (Venta) intent.getExtras().getSerializable("ventaUsuario");
        libroCarritoAdapter = new LibroCarritoAdapter(this, ventaUsuario.getLibroCarritos());
        setListAdapter(libroCarritoAdapter);
        tvTotalVenta = (TextView) findViewById(R.id.tvTotalCompraCarrito);
        DecimalFormat formmater = new DecimalFormat(DOUBLE_FORMAT_PRECIO);
        tvTotalVenta.setText(formmater.format(getTotalCarrito(ventaUsuario.getLibroCarritos())));
    }

    private double getTotalCarrito(ArrayList<LibroCarrito> libroCarritos){
           double total = 0;
        for(LibroCarrito libroCarrito:libroCarritos){
            total += libroCarrito.getTotalVenta();
        }
        return total;
    }
}
