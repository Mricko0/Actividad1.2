package com.example.mrybakinmysqlm8act12;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class ActividadMain extends AppCompatActivity {
    
    RecyclerView recyclerView;
    FloatingActionButton add_button;
    ImageView empty_imageview;
    TextView no_data;
    BaseDeDatos BaseDeDatos;
    ArrayList<String> Incidencias_id, Incidencias_Titulo, Incidencias_Usuario, Incidencias_Elemento, Incidencias_Tipo, Incidencias_Ubicacion, Incidencias_Desc,Incidencias_Fecha;
    Adaptador Adaptador2;
    //Crea layouts y empieza la aplicacion designado con que layout empieza.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_main);

        recyclerView = findViewById(R.id.recyclerView);
        add_button = findViewById(R.id.AñadirBOTON);
        empty_imageview = findViewById(R.id.empty_imageview);
        no_data = findViewById(R.id.no_data);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActividadMain.this, AñadirActividad.class);
                startActivity(intent);
            }
        });
        //Crea las arrays list para la base de datos.
        BaseDeDatos = new BaseDeDatos(ActividadMain.this);
        Incidencias_id = new ArrayList<>();
        Incidencias_Titulo = new ArrayList<>();
        Incidencias_Usuario = new ArrayList<>();
        Incidencias_Elemento = new ArrayList<>();
        Incidencias_Tipo = new ArrayList<>();
        Incidencias_Ubicacion = new ArrayList<>();
        Incidencias_Desc = new ArrayList<>();
        Incidencias_Fecha = new ArrayList<>();
        GuardarListas();
        //Crea los adaptadores para crear una lista.
        Adaptador2 = new Adaptador(ActividadMain.this,this, Incidencias_id, Incidencias_Titulo, Incidencias_Usuario,
                Incidencias_Elemento,Incidencias_Tipo,Incidencias_Ubicacion,Incidencias_Desc,Incidencias_Fecha);
        recyclerView.setAdapter(Adaptador2);
        recyclerView.setLayoutManager(new LinearLayoutManager(ActividadMain.this));
    }
    //Hace que esta actividad se vuelva a crear con una nueva instancia. Esto da como resultado esencialmente el mismo flujo que cuando se crea la actividad debido a un cambio de configuración.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }
    //Guarda las listas en la base de datos.
    void GuardarListas(){
        Cursor cursor = BaseDeDatos.LeerTodo();
        if(cursor.getCount() == 0){
            empty_imageview.setVisibility(View.VISIBLE);
            no_data.setVisibility(View.VISIBLE);
        }else{
            while (cursor.moveToNext()){
                Incidencias_id.add(cursor.getString(0));
                Incidencias_Titulo.add(cursor.getString(1));
                Incidencias_Usuario.add(cursor.getString(2));
                Incidencias_Elemento.add(cursor.getString(3));
                Incidencias_Tipo.add(cursor.getString(4));
                Incidencias_Ubicacion.add(cursor.getString(5));
                Incidencias_Desc.add(cursor.getString(6));
                Incidencias_Fecha.add(cursor.getString(7));
            }
            empty_imageview.setVisibility(View.GONE);
            no_data.setVisibility(View.GONE);
        }
    }
    //Un menu de opciones
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    //Un boton en el menu de opciones en la cual nos deja borrar todas las incidencias
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.borrar_todo){
            confirmar();
        }
        return super.onOptionsItemSelected(item);
    }
    //Una confirmacion que queremos borrar todas las incidencias.
    void confirmar(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Borrar todo?");
        builder.setMessage("Estas seguro que quieres borrar todo?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                BaseDeDatos myDB = new BaseDeDatos(ActividadMain.this);
                myDB.BorrarTodo();
                Intent intent = new Intent(ActividadMain.this, ActividadMain.class);
                startActivity(intent);
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
