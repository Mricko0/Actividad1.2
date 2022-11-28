package com.example.mrybakinmysqlm8act12;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ActualizarActividad extends AppCompatActivity {

    EditText Titulo_Input, Usuario_Input, Elemento_Input, Tipo_Input, Ubicacion_Input, Desc_Input, Fecha_Input;
    Button ActualizarBOTON, EliminarBOTON;
    String id, Titulo, Usuario, Elemento, Tipo, Ubicacion, Desc, Fecha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actualizar_actividad);

        Titulo_Input = findViewById(R.id.titulo_input2);
        Usuario_Input = findViewById(R.id.usuario_input2);
        Elemento_Input = findViewById(R.id.elemento_input2);
        Tipo_Input = findViewById(R.id.tipo_input2);
        Ubicacion_Input = findViewById(R.id.ubicacion_input2);
        Desc_Input = findViewById(R.id.desc_input2);
        Fecha_Input = findViewById(R.id.fecha_input2);
        ActualizarBOTON = findViewById(R.id.actualizar_boton);
        EliminarBOTON = findViewById(R.id.eliminar_boton);

        getAndSetIntentData();

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(Titulo);
        }

        ActualizarBOTON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BaseDeDatos myDB = new BaseDeDatos(ActualizarActividad.this);
                Titulo = Titulo_Input.getText().toString().trim();
                Usuario = Usuario_Input.getText().toString().trim();
                Elemento = Elemento_Input.getText().toString().trim();
                Tipo = Tipo_Input.getText().toString().trim();
                Ubicacion = Ubicacion_Input.getText().toString().trim();
                Desc = Desc_Input.getText().toString().trim();
                Fecha = Fecha_Input.getText().toString().trim();
                myDB.ActualizarIncidencia(id, Titulo, Usuario, Elemento,Tipo,Ubicacion,Desc,Fecha);
            }
        });
        EliminarBOTON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });

    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("Titulo") &&
                getIntent().hasExtra("Autor") && getIntent().hasExtra("pages")){
            id = getIntent().getStringExtra("id");
            Titulo = getIntent().getStringExtra("Titulo");
            Usuario = getIntent().getStringExtra("Usuario");
            Elemento = getIntent().getStringExtra("Elemento");
            Tipo = getIntent().getStringExtra("Tipo");
            Ubicacion = getIntent().getStringExtra("Ubicacion");
            Desc = getIntent().getStringExtra("Desc");
            Fecha = getIntent().getStringExtra("Fecha");

            Titulo_Input.setText(Titulo);
            Usuario_Input.setText(Usuario);
            Elemento_Input.setText(Elemento);
            Tipo_Input.setText(Tipo);
            Ubicacion_Input.setText(Ubicacion);
            Desc_Input.setText(Desc);
            Fecha_Input.setText(Fecha);

            Log.d("stev", Titulo +" "+ Usuario +" "+Elemento+" "+Tipo+" "+Ubicacion+" "+Desc+" "+Fecha);
        }else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Eliminar " + Titulo + " ?");
        builder.setMessage("Estas seguro que quieres eliminarlo? " + Titulo + " ?");
        builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                BaseDeDatos myDB = new BaseDeDatos(ActualizarActividad.this);
                myDB.EliminarIncidencia(id);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }

}
