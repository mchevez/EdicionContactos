package com.example.edicioncontactos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class EditarContacto extends AppCompatActivity {
    private TextView tvNombre;
    private TextView tvTelefono;
    private TextView tvEmail;
    private TextView tvDescripcion;
    private TextView tvFechaNacimiento;

    private String Nombre;
    private String Telefono;
    private String Email;
    private String Descripcion;
    private String FechaNacimiento;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_contacto);

        tvNombre = (TextView) findViewById(R.id.tvDatoNombre);
        tvTelefono = (TextView) findViewById(R.id.tvDatoTelefono);
        tvEmail = (TextView) findViewById(R.id.tvDatoEmail);
        tvDescripcion = (TextView) findViewById(R.id.tvDatoDescripcion);
        tvFechaNacimiento = (TextView) findViewById(R.id.tvDatoFecha);

        Bundle lstparametros = getIntent().getExtras();

        Nombre =  lstparametros.getString("Nombre");
        Telefono = lstparametros.getString("Telefono");
        Email = lstparametros.getString("Email");
        Descripcion = lstparametros.getString("Descripcion");
        FechaNacimiento = lstparametros.getString("FechaNacimiento");


        tvNombre.setText(Nombre);
        tvTelefono.setText(Telefono);
        tvEmail.setText(Email);
        tvDescripcion.setText(Descripcion);
        tvFechaNacimiento.setText(FechaNacimiento);
    }

    public void editarDatos(View v){
        Intent i = new Intent(EditarContacto.this, MainActivity.class);

        i.putExtra("Nombre",Nombre);
        i.putExtra("Telefono",Telefono);
        i.putExtra("FechaNacimiento",FechaNacimiento);
        i.putExtra("Email",Email);
        i.putExtra("Descripcion",Descripcion);

        startActivity(i);
    }
}