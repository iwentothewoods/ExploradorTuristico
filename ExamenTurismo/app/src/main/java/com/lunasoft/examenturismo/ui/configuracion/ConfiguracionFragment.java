package com.lunasoft.examenturismo.ui.configuracion;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.lunasoft.examenturismo.R;

public class ConfiguracionFragment extends Fragment {

    private ConfiguracionViewModel viewModel;

    Spinner tipoMapaSpinner;
    Spinner idiomasSpinner;
    public static final String[] idiomas = {"Seleccionar Idioma", "English", "Español"};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_configuracion, container, false);

        viewModel = new ViewModelProvider(requireActivity()).get(ConfiguracionViewModel.class);
        viewModel.setResources(getResources());

        /* CAMBIO MAPA */

        tipoMapaSpinner = view.findViewById(R.id.map_type_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                requireContext(),
                R.array.tipo_mapas,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tipoMapaSpinner.setAdapter(adapter);

        tipoMapaSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String tipoMapaSeleccionado = parent.getItemAtPosition(position).toString();
                viewModel.setTipoMapa(tipoMapaSeleccionado);

                String mensajeToast = getString(R.string.map_change) + tipoMapaSeleccionado;
                Toast.makeText(requireContext(), mensajeToast, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        /* IDIOMAS */

        idiomasSpinner = view.findViewById(R.id.idiomas_spinner);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, idiomas);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        idiomasSpinner.setAdapter(adapter1);
        idiomasSpinner.setSelection(0);
        idiomasSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String idiomaElegido = parent.getItemAtPosition(position).toString();

                if (!idiomaElegido.equals("Seleccionar Idioma")) {
                    viewModel.cambiarIdioma(idiomaElegido);
                    reiniciarActividad();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return view;
    }

    private void reiniciarActividad() {
        Intent intent = requireActivity().getIntent();
        requireActivity().finish();
        startActivity(intent);
    }
}
