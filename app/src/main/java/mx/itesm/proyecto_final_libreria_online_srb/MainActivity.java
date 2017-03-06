package mx.itesm.proyecto_final_libreria_online_srb;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends ListActivity implements AdapterView.OnItemClickListener, View.OnClickListener{

    private AlmacenLibrosServicio almacenLibrosServicio  = new AlmacenLibrosServicio(this.createBooksInStock());
    private VentaServicio ventaServicio = new VentaServicio(new ArrayList<Venta>());
    private String useremail;
    private static final int REQUEST_FOR_SELL = 1;
    private static final int REQUEST_FOR_CHECKOUT = 2;
    private Venta ventaUsuario;
    Button bVerCarrito ;
    LibroAdapter adapterLibro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adapterLibro = new LibroAdapter(this,almacenLibrosServicio.getLibros());
        setListAdapter(adapterLibro);
        getListView().setOnItemClickListener(this);
        Intent intent = getIntent();
        if(intent.getExtras() != null){
            useremail = intent.getExtras().getString("useremail");
        }
        ventaUsuario = ventaServicio.encuentraVenta(0,useremail);
        if ( ventaUsuario == null ) {
            ventaUsuario = ventaServicio.crearVenta(useremail);
            ventaServicio.getVentas().add(ventaUsuario);
        }
        bVerCarrito = (Button) findViewById(R.id.bttnVerCarrito);
        bVerCarrito.setOnClickListener(this);
    }

    private ArrayList<Libro> createBooksInStock(){
        ArrayList<Libro> libros = new ArrayList<Libro>();
        Libro libro = new Libro(10,R.drawable.android_programming_guide,"9781329747517",Double.parseDouble("33.49"),
                "Android:App Development Programming Guide:Learn In A Day");
        libros.add(libro);
        libro = new Libro(3,R.drawable.libro_java,"1430264543",Double.parseDouble("56"),
                "Learn Java for Android Development");
        libros.add(libro);
        libro = new Libro(6,R.drawable.libro_beginnig,"9781785889035",Double.parseDouble("48"),
                "Android Programming for Beginners");
        libros.add(libro);
        libro = new Libro(8,R.drawable.libro_cookbook,"1785886193",Double.parseDouble("44.99"),
                "Android Application Development Cookbook Second Edition");
        libros.add(libro);
        libro = new Libro(5,R.drawable.int_to_android_application_development_,"013438945X",Double.parseDouble("39.99"),
                "Android Application Development Cookbook Second Edition");
        libros.add(libro);
        Collections.sort(libros);
        return libros;
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.bttnVerCarrito){
            if (ventaUsuario.getLibroCarritos().isEmpty()){
                Toast.makeText(this,"No tiene compras registradas ",Toast.LENGTH_LONG).show();
            }else {
                Intent intent = new Intent(MainActivity.this, CarritoCompraActivity.class);
                intent.putExtra("ventaUsuario", ventaUsuario);
                startActivity(intent);
            }
        }
    }

    /**
     * Callback method to be invoked when an item in this AdapterView has
     * been clicked.
     * <p>
     * Implementers can call getItemAtPosition(position) if they need
     * to access the data associated with the selected item.
     *
     * @param parent   The AdapterView where the click happened.
     * @param view     The view within the AdapterView that was clicked (this
     *                 will be a view provided by the adapter)
     * @param position The position of the view in the adapter.
     * @param id       The row id of the item that was clicked.
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Libro libro = (Libro) parent.getItemAtPosition(position);
        Intent intent = new Intent(MainActivity.this,DetalleLibroActivity.class);
        intent.putExtra("libro",libro);
        startActivityForResult(intent,REQUEST_FOR_SELL);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if (resultCode == RESULT_OK ){
            if (requestCode == REQUEST_FOR_SELL ){
                Bundle datos = data.getExtras();
                Libro libro = (Libro) datos.getSerializable("libro");
                int cantidad = datos.getInt("cantidad");
                ventaUsuario.agregarLibro(libro,cantidad);
                almacenLibrosServicio.venderLibro(libro.getISBN(),cantidad);
                adapterLibro.notifyDataSetChanged();
                Toast.makeText(this,"Compraste "+cantidad+" del ".concat(libro.getTitulo()),Toast.LENGTH_LONG).show();
            }
            if (requestCode == REQUEST_FOR_CHECKOUT) {

            }
        }
    }

}
