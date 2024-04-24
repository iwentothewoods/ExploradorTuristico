package com.lunasoft.examenturismo.ui.lugares_turisticos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.lunasoft.examenturismo.LugarTuristico;
import com.lunasoft.examenturismo.R;

public class LugarSeleccionado extends Fragment {

    private LugarSeleccionadoViewModel mv;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lugar_seleccionado, container, false);

        mv = new ViewModelProvider(this).get(LugarSeleccionadoViewModel.class);

        TextView nombre = view.findViewById(R.id.tvNombreLugar);
        TextView descripcion = view.findViewById(R.id.tvDescripcionLugar);
        TextView horarios = view.findViewById(R.id.tvHorariosLugar);
        TextView ubicacion = view.findViewById(R.id.tvUbicacionLugar);
        ImageView foto = view.findViewById(R.id.ivFotoLugar);
        Button boton = view.findViewById(R.id.btVolver);

        Bundle bundle = getArguments();
        LugarTuristico lugar = (LugarTuristico) bundle.getSerializable("lugar");

        nombre.setText(lugar.getNombre());
        descripcion.setText(lugar.getDescripcion());
        horarios.setText("Horarios: " + lugar.getHorarios());
        ubicacion.setText("Ubicación: " + lugar.getDireccion());
        foto.setImageResource(lugar.getFoto());


        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigateUp();
            }
        });

        return view;
    }

}
