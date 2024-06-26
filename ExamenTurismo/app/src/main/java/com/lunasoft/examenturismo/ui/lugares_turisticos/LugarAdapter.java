package com.lunasoft.examenturismo.ui.lugares_turisticos;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lunasoft.examenturismo.LugarTuristico;
import com.lunasoft.examenturismo.R;
import androidx.navigation.Navigation;

import java.util.List;

public class LugarAdapter extends RecyclerView.Adapter<LugarAdapter.ViewHolderPepe> {
    private List<LugarTuristico> listaDeLugares;
    private Context context;
    private LayoutInflater li;
    public LugarAdapter(List<LugarTuristico> listaDeLugares, Context context, LayoutInflater li) {
        this.listaDeLugares = listaDeLugares;
        this.context = context;
        this.li = li;
    }

    public void setLugares(List<LugarTuristico> lugares) {
        listaDeLugares.clear();
        listaDeLugares.addAll(lugares);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolderPepe onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = li.inflate(R.layout.item, parent, false);
        return new ViewHolderPepe(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderPepe holder, int position) {
        LugarTuristico lugar = listaDeLugares.get(position);

        holder.nombre.setText(lugar.getNombre());
        holder.descripcion.setText(lugar.getDescripcion());
        holder.foto.setImageResource(lugar.getFoto());

        holder.boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Bundle bundle = new Bundle();
               bundle.putSerializable("lugar", lugar);
               Navigation.findNavController(v).navigate(R.id.lugarSeleccionado, bundle);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listaDeLugares.size();
    }

    public class ViewHolderPepe extends RecyclerView.ViewHolder {
        TextView nombre, descripcion;
        ImageView foto;
        Button boton;

        public ViewHolderPepe(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.tvTitulo);
            descripcion = itemView.findViewById(R.id.tvDescripcion);
            boton = itemView.findViewById(R.id.btVerMas);
            foto = itemView.findViewById(R.id.ivFoto);
        }
    }
}
