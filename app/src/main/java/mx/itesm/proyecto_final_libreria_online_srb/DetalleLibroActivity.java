package mx.itesm.proyecto_final_libreria_online_srb;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class DetalleLibroActivity extends AppCompatActivity implements View.OnClickListener , AdapterView.OnItemSelectedListener{

    private Spinner spValor;
    private TextView tvISBN;
    private TextView tvTitulo;
    private TextView tvExistencia;
    private TextView tvPrecio;
    private TextView tvTotal;
    private ImageView ivLibro;
    private Button btnGuardar;
    int positionSel;
    private Libro libro;
    int[] selected = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_libro);

        tvISBN       = (TextView) findViewById(R.id.isbn);
        tvTitulo     = (TextView) findViewById(R.id.titulo);
        tvExistencia = (TextView) findViewById(R.id.existencia);
        tvPrecio     = (TextView) findViewById(R.id.precio);
        tvTotal      = (TextView) findViewById(R.id.tvTotalCompra);
        btnGuardar   = (Button) findViewById(R.id.bttnGuardarCompra);
        spValor      = (Spinner) findViewById(R.id.spCantidad);
        ivLibro      = (ImageView) findViewById(R.id.iVDetalle);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.
                createFromResource(this,R.array.valores_enteros,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spValor.setAdapter(adapter);
        spValor.setOnItemSelectedListener(this);
        Intent intent = getIntent();
        if(intent.getExtras() != null){
            libro = (Libro) intent.getExtras().getSerializable("libro");
            tvISBN.setText(libro.getISBN());
            tvTotal.setText("0");
            tvTitulo.setText(libro.getTitulo());
            tvPrecio.setText(libro.getPrecioFormatted());
            tvExistencia.setText(libro.getExistenciaFormatted());
            if (libro.getImagen() > 0)
                ivLibro.setImageResource(libro.getImagen());
        }
        btnGuardar.setOnClickListener(this);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        if(v.getId() == btnGuardar.getId()){
            if (selected[positionSel] == 0) {
                Toast.makeText(this,"La compra no sera agregada, no selecciono alguna cantidad valida",Toast.LENGTH_LONG).show();
                Intent intent = new Intent();
                setResult(RESULT_CANCELED, intent);
                finish();
            }else {
                Intent intent = new Intent();
                intent.putExtra("cantidad", selected[positionSel]);
                intent.putExtra("libro", libro);
                setResult(RESULT_OK, intent);
                finish();
            }
        }
    }

    /**
     * <p>Callback method to be invoked when an item in this view has been
     * selected. This callback is invoked only when the newly selected
     * position is different from the previously selected position or if
     * there was no selected item.</p>
     * <p>
     * Impelmenters can call getItemAtPosition(position) if they need to access the
     * data associated with the selected item.
     *
     * @param parent   The AdapterView where the selection happened
     * @param view     The view within the AdapterView that was clicked
     * @param position The position of the view in the adapter
     * @param id       The row id of the item that is selected
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        positionSel = position;
        if(selected[position] > libro.getExistencia()) {
            Toast.makeText(this,"No puedes seleccionar mas unidades que las existentes",Toast.LENGTH_LONG).show();
        }else {
            double costo = selected[position] * libro.getPrecio();
            tvTotal.setText(String.valueOf(costo));
        }
    }

    /**
     * Callback method to be invoked when the selection disappears from this
     * view. The selection can disappear for instance when touch is activated
     * or when the adapter becomes empty.
     *
     * @param parent The AdapterView that now contains no selected item.
     */
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        positionSel = -1;
    }
}
