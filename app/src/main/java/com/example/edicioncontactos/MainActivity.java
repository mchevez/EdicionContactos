package com.example.edicioncontactos;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;
import java.util.Locale;

import static java.lang.Integer.parseInt;

public class MainActivity extends AppCompatActivity {

    private  TextInputEditText tietNombre;
    private  TextInputEditText tietTelefono;
    private  TextInputEditText tietEmail;
    private  TextInputEditText tietDescripcion;
    private TextView tvFecha;
    private Button btnFecha;
    private String Nombre;
    private String FechaNacimiento;
    private String Telefono;
    private String Email;
    private String Descripcion;

    // Guardar el último año, mes y día del mes
    private int ultimoAnio, ultimoMes, ultimoDiaDelMes;

    private DatePickerDialog.OnDateSetListener listenerDeDatePicker = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            // Esto se llama cuando seleccionan una fecha. Nos pasa la vista, pero más importante, nos pasa:
            // El año, el mes y el día del mes. Es lo que necesitamos para saber la fecha completa

            // Refrescamos las globales
            ultimoAnio = year;
            ultimoMes = month;
            ultimoDiaDelMes = dayOfMonth;
            refrescarFechaEnEditText();
        }
    };

    public void refrescarFechaEnEditText() {
        // Formateamos la fecha pero podríamos hacer cualquier otra cosa ;)
        String fecha = String.format(Locale.getDefault(), "%02d-%02d-%02d", ultimoAnio, ultimoMes+1, ultimoDiaDelMes);

        // La ponemos en el editText
        tvFecha.setText(fecha);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle xnuevodatos = getIntent().getExtras();

        // Poner último año, mes y día a la fecha de hoy

        init_field();



        if(xnuevodatos != null)
        {
            Log.i("Parametros ","Sin datos");

            Nombre =  xnuevodatos.getString("Nombre");
            Telefono = xnuevodatos.getString("Telefono");
            FechaNacimiento = xnuevodatos.getString("FechaNacimiento");
            Email = xnuevodatos.getString("Email");
            Descripcion = xnuevodatos.getString("Descripcion");

            tietNombre.setText(Nombre);
            tietTelefono.setText(Telefono);
            tietEmail.setText(Email);
            tietDescripcion.setText(Descripcion);

            String[] mifecha = FechaNacimiento.split("-");

            int anio = Integer.parseInt(mifecha[0]);
            int mes = Integer.parseInt(mifecha[1])-1;
            int dia = Integer.parseInt(mifecha[2]);
            Init_Fechas(anio,mes,dia);
        }
        else{
            Init_Fechas(0,0,0);
        }

        refrescarFechaEnEditText();
        btnFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialogFecha = new DatePickerDialog(MainActivity.this, listenerDeDatePicker, ultimoAnio,ultimoMes, ultimoDiaDelMes);
                dialogFecha.show();
            }
        });
    }

    public void enviarDatos(View v){
        Intent i = new Intent(MainActivity.this, EditarContacto.class);

        Nombre = tietNombre.getText().toString();
        FechaNacimiento = tvFecha.getText().toString();
        Telefono = tietTelefono.getText().toString();
        Email = tietEmail.getText().toString();
        Descripcion = tietDescripcion.getText().toString();

        if(!Nombre.equals("") && !Telefono.equals("") && !Email.equals("") && !Descripcion.equals("")){
            i.putExtra("Nombre",Nombre);
            i.putExtra("FechaNacimiento",FechaNacimiento);
            i.putExtra("Telefono",Telefono);
            i.putExtra("Email",Email);
            i.putExtra("Descripcion",Descripcion);
            startActivity(i);
        }
        else{
            Toast.makeText(this,"Debe completar todos los campos",Toast.LENGTH_LONG).show();
        }
    }
    public void init_field(){
        tietNombre = (TextInputEditText) findViewById(R.id.tietNombre);
        tietTelefono = (TextInputEditText) findViewById(R.id.tietTelefono);
        tietEmail = (TextInputEditText) findViewById(R.id.tietEmail);
        tietDescripcion = (TextInputEditText) findViewById(R.id.tietDescripcion);
        tvFecha = (TextView) findViewById(R.id.tvFecha);
        btnFecha = (Button) findViewById(R.id.btnFecha);
    }

    public void Init_Fechas(int anio, int mes, int dia)
    {
        final Calendar calendario = Calendar.getInstance();

        if(anio != 0) {
            calendario.set(Calendar.YEAR,anio);
            ultimoAnio = calendario.get(Calendar.YEAR);
            calendario.set(Calendar.MONTH,mes);
            ultimoMes = calendario.get(Calendar.MONTH);
            calendario.set(Calendar.DAY_OF_MONTH,dia);
            ultimoDiaDelMes = calendario.get(Calendar.DAY_OF_MONTH);
        }
        else {
            ultimoAnio = calendario.get(Calendar.YEAR);
            ultimoMes = calendario.get(Calendar.MONTH);
            ultimoDiaDelMes = calendario.get(Calendar.DAY_OF_MONTH);
        }
    }
}