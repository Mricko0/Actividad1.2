package com.example.mrybakinmysqlm8act12;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

//El adaptador proporciona acceso a los elementos de datos. El adaptador también es responsable de crear una vista para cada elemento del conjunto de datos.
public class Adaptador extends RecyclerView.Adapter<Adaptador.ViewHolder>{

    private Context context;
    private Activity activity;
    private ArrayList IncidenciasID, IncidenciasTitulo, IncidenciasUsuario, IncidenciasElemento, IncidenciasTipo, IncidenciasUbicacion, IncidenciasDesc, IncidenciasFecha;
    //Declaramos el constructor del adaptador
    Adaptador(Activity activity, Context context, ArrayList IncidenciasID, ArrayList IncidenciasTitulo, ArrayList IncidenciasUsuario,
                  ArrayList IncidenciasElemento, ArrayList IncidenciasTipo, ArrayList IncidenciasUbicacion, ArrayList IncidenciasDesc, ArrayList IncidenciasFecha){
        this.activity = activity;
        this.context = context;
        this.IncidenciasID = IncidenciasID;
        this.IncidenciasTitulo = IncidenciasTitulo;
        this.IncidenciasUsuario = IncidenciasUsuario;
        this.IncidenciasElemento = IncidenciasElemento;
        this.IncidenciasTipo = IncidenciasTipo;
        this.IncidenciasUbicacion = IncidenciasUbicacion;
        this.IncidenciasDesc = IncidenciasDesc;
        this.IncidenciasFecha = IncidenciasFecha;
    }

    //Crea el viewholder en el layout columna donde ahi estara la estructura de cada incidencia.
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.columna, parent, false);
        return new ViewHolder(view);
    }
    //En resumen une el texto que escribimos en añadir incidencia y crea la lista para que la podamos ver dentro de la aplicacion
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        //Aqui recoje el texto que hemos puesto en añadir una actividad y su posicion o la ID.
        holder.incidencia_id_txt.setText(String.valueOf(IncidenciasID.get(position)));
        holder.incidencias_titulo_txt.setText(String.valueOf(IncidenciasTitulo.get(position)));
        holder.incidencias_usuario_txt.setText(String.valueOf(IncidenciasUsuario.get(position)));
        holder.incidencias_elemento_txt.setText(String.valueOf(IncidenciasElemento.get(position)));
        holder.incidencias_tipo_txt.setText(String.valueOf(IncidenciasTipo.get(position)));
        holder.incidencias_ubicacion_txt.setText(String.valueOf(IncidenciasUbicacion.get(position)));
        holder.incidencias_desc_txt.setText(String.valueOf(IncidenciasDesc.get(position)));
        holder.incidencias_fecha_txt.setText(String.valueOf(IncidenciasFecha.get(position)));
        //Recyclerview onClickListener
        holder.LayoutMain.setOnClickListener(new View.OnClickListener() {
            //Aqui lo crea en su respectiva posicion.
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ActualizarActividad.class);
                intent.putExtra("id", String.valueOf(IncidenciasID.get(position)));
                intent.putExtra("Titulo", String.valueOf(IncidenciasTitulo.get(position)));
                intent.putExtra("Usuario", String.valueOf(IncidenciasUsuario.get(position)));
                intent.putExtra("Elemento", String.valueOf(IncidenciasElemento.get(position)));
                intent.putExtra("Tipo elemento", String.valueOf(IncidenciasTipo.get(position)));
                intent.putExtra("Ubicacion", String.valueOf(IncidenciasUbicacion.get(position)));
                intent.putExtra("Descripcion", String.valueOf(IncidenciasDesc.get(position)));
                intent.putExtra("Fecha", String.valueOf(IncidenciasFecha.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });
    }
    //Cuentas todas las ID restantes.
    @Override
    public int getItemCount() {
        return IncidenciasID.size();
    }
    //Aqui se hace cargo que se muestre el viewholder, la lista de las incidencias.
    class ViewHolder extends RecyclerView.ViewHolder {
    
        TextView incidencia_id_txt, incidencias_titulo_txt, incidencias_usuario_txt, incidencias_elemento_txt, incidencias_tipo_txt, incidencias_ubicacion_txt, incidencias_desc_txt, incidencias_fecha_txt;
        LinearLayout LayoutMain;
        //Crea la visualizacion de las listas con los correspondientes datos y tambien crea una animacion simple.
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            incidencia_id_txt = itemView.findViewById(R.id.incidencia_id_txt);
            incidencias_titulo_txt = itemView.findViewById(R.id.incidencia_titulo_txt);
            incidencias_elemento_txt = itemView.findViewById(R.id.incidencia_usuario_txt);
            incidencias_tipo_txt = itemView.findViewById(R.id.incidencia_tipo_txt);
            incidencias_ubicacion_txt = itemView.findViewById(R.id.incidencia_ubicacion_txt);
            incidencias_desc_txt = itemView.findViewById(R.id.incidencia_descripcion_txt);
            incidencias_fecha_txt = itemView.findViewById(R.id.incidencia_fecha_txt);
            LayoutMain = itemView.findViewById(R.id.MainLayout);

            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.anim);
            LayoutMain.setAnimation(translate_anim);
        }

    }
}
