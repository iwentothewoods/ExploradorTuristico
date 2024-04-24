package com.lunasoft.examenturismo.ui.mapas;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.lunasoft.examenturismo.LugarTuristico;
import com.lunasoft.examenturismo.R;
import com.lunasoft.examenturismo.databinding.FragmentMapsBinding;
import com.lunasoft.examenturismo.ui.configuracion.ConfiguracionViewModel;
import com.lunasoft.examenturismo.ui.lugares_turisticos.LugarAdapter;

import java.util.List;

public class MapsFragment extends Fragment implements OnMapReadyCallback {

    private FragmentMapsBinding binding;
    private MapsViewModel viewModel;
    private GoogleMap googleMap;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentMapsBinding.inflate(inflater, container, false);
        //view model de Maps
        viewModel = new ViewModelProvider(this).get(MapsViewModel.class);
        viewModel.getMLocation().observe(getViewLifecycleOwner(), new Observer<Location>() {
            @Override
            public void onChanged(Location location) {
                if (googleMap != null) {
                    LatLng miUbicacion = new LatLng(location.getLatitude(), location.getLongitude());
                    googleMap.addMarker(new MarkerOptions().position(miUbicacion).title("Mi Ubicación"));
                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(miUbicacion, 15));
                }
            }
        });
        viewModel.obtenerUltimaUbicacion();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap map) {
        googleMap = map;
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        // Necesitamos el vm de config para manejar los tipos de mapa
        ConfiguracionViewModel configuracionViewModel = new ViewModelProvider(requireActivity()).get(ConfiguracionViewModel.class);

        viewModel.getListaLugares().observe(getViewLifecycleOwner(), new Observer<List<LugarTuristico>>() {
            @Override
            public void onChanged(List<LugarTuristico> lugares) {
                // Coloca marcadores en el mapa utilizando el ViewModel de Configuración
                configuracionViewModel.colocarMarcadoresEnMapa(lugares, googleMap);
            }
        });

        configuracionViewModel.getTipoMapa().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String tipoMapa) {
                googleMap.setMapType(configuracionViewModel.obtenerTipoMapa());
            }
        });


    }

}

