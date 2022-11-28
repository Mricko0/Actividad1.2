package com.example.mrybakinmysqlm8act12;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

//Clase para añadir las actividades
public class AñadirActividad extends AppCompatActivity {

    EditText Titulo_Input, Usuario_Input, Elemento_Input, Tipo_Input, Ubicacion_Input, Desc_Input, Fecha_Input;
    Button AñadirBOTON;
    
    //Recoje los strings de antes y busca ID de los layouts y lo introduce en la base de datos.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_anadir);
        //Encontrar las ID dentro de los layouts.
        Titulo_Input = findViewById(R.id.titulo_input);
        Usuario_Input = findViewById(R.id.usuario_input);
        Elemento_Input = findViewById(R.id.elemento_input);
        Tipo_Input = findViewById(R.id.tipo_input);
        Ubicacion_Input = findViewById(R.id.ubicacion_input);
        Desc_Input = findViewById(R.id.desc_input);
        Fecha_Input = findViewById(R.id.fecha_input);
        //boton que se usa para añadir nuevas incidencias
        AñadirBOTON = findViewById(R.id.AñadirBOTON);
        AñadirBOTON.setOnClickListener(new View.OnClickListener() {
            //Al darle click se creara un nuevo registro en la base de datos
            @Override
            public void onClick(View view) {
                BaseDeDatos myDB = new BaseDeDatos(AñadirActividad.this);
                myDB.AñadirIncidencia(Titulo_Input.getText().toString().trim(),
                        Usuario_Input.getText().toString().trim(),
                        Elemento_Input.getText().toString().trim(),
                        Tipo_Input.getText().toString().trim(),
                        Ubicacion_Input.getText().toString().trim(),
                        Desc_Input.getText().toString().trim(),
                        Fecha_Input.getText().toString().trim());
            }
        });
    }

}
