package com.example.mrybakinmysqlm8act12;

import android.util.Log;
import android.widget.Toast;
import androidx.annotation.Nullable;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

//Clase de Base de datos para la aplicacion donde se crea un SQLite.
public class BaseDeDatos extends SQLiteOpenHelper {

    //Strings y variables para la base de datos
    private Context context;
    private static final String NOMBRE_BD = "SQLMrybakin.db";
    private static final int DB_VERSION = 1;
    private static final String NOMBRE_TABLA = "IncidenciasTabla";
    private static final String COLUMNA_ID = "_ID";
    private static final String COLUMNA_TITULO = "TituloIncidencia";
    private static final String COLUMNA_USUARIO = "NombreUsuarioIncidencia";
    private static final String COLUMNA_DESCRIPCION = "DescripcionIncidencia";
    private static final String COLUMNA_ELEMENTO = "ElementoAfectadoIncidencia";
    private static final String COLUMNA_TIPOELEM = "TipoDeElementoIncidencia";
    private static final String COLUMNA_UBICACION = "UbicacionIncidencia";
    private static final String COLUMNA_FECHA = "FechaIncidencia";

    
    //Crea el context
    BaseDeDatos(@Nullable Context context) {
        super(context, NOMBRE_BD, null, DB_VERSION);
        this.context = context;
    }

    //Constructor de base de datos
    public BaseDeDatos(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    //Crea la base de datos localmente y la tabla.
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + NOMBRE_TABLA +
                " (" + COLUMNA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMNA_TITULO + " TEXT, " +
                COLUMNA_USUARIO + " TEXT, " +
                COLUMNA_ELEMENTO + " TEXT, " +
                COLUMNA_TIPOELEM + " TEXT, " +
                COLUMNA_UBICACION + " TEXT, " +
                COLUMNA_DESCRIPCION + " TEXT, "+
                COLUMNA_FECHA + " TEXT);";
        sqLiteDatabase.execSQL(query);
    }
    
    //Se llama cuando la versi??n de su base de datos cambi??, lo que significa que la estructura de la tabla subyacente cambi??, etc.
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + NOMBRE_TABLA);
        onCreate(sqLiteDatabase);
    }
    
    //Metodo para a??adir incidencias a la base de datos. Si ahi un error lo comenta con un toast.
    void A??adirIncidencia(String titulo, String usuario, String elemento, String Tipo, String Ubicacion, String Desc, String Fecha){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues ContentValues = new ContentValues();
        ContentValues.put(COLUMNA_TITULO, titulo);
        ContentValues.put(COLUMNA_USUARIO, usuario);
        ContentValues.put(COLUMNA_ELEMENTO, elemento);
        ContentValues.put(COLUMNA_TIPOELEM, Tipo);
        ContentValues.put(COLUMNA_UBICACION, Ubicacion);
        ContentValues.put(COLUMNA_DESCRIPCION, Desc);
        ContentValues.put(COLUMNA_FECHA, Fecha);

        long result = db.insert(NOMBRE_TABLA,null, ContentValues);

        if(result == -1){
            Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "A??adido satisfactoriamente!", Toast.LENGTH_SHORT).show();
        }

    }
    //Metodo para actualizar una incidencia, gracias a la ID de la columna edita la incidencia necesaria.
    void ActualizarIncidencia(String row_id, String titulo, String usuario, String elemento, String Tipo, String Ubicacion, String Desc, String Fecha){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues ContentValues = new ContentValues();
        ContentValues.put(COLUMNA_TITULO, titulo);
        ContentValues.put(COLUMNA_USUARIO, usuario);
        ContentValues.put(COLUMNA_ELEMENTO, elemento);
        ContentValues.put(COLUMNA_TIPOELEM, Tipo);
        ContentValues.put(COLUMNA_UBICACION, Ubicacion);
        ContentValues.put(COLUMNA_DESCRIPCION, Desc);
        ContentValues.put(COLUMNA_FECHA, Fecha);

        long result = db.update(NOMBRE_TABLA, ContentValues, "_ID=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Actualizado correctamente!", Toast.LENGTH_SHORT).show();
        }

    }
    //Eliminar incidendias en especifico con una ID
    void EliminarIncidencia(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(NOMBRE_TABLA, "_ID=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Error al eliminar.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Eliminado correctamente.", Toast.LENGTH_SHORT).show();
        }
    }
    //Lee todas las incidencias
    Cursor LeerTodo(){
        String query = "SELECT * FROM " + NOMBRE_TABLA;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
    //Borra todas las incidencias
    void BorrarTodo(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + NOMBRE_TABLA);
    }
}
