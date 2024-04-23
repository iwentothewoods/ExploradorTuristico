package com.lunasoft.examenturismo.ui.configuracion;

import android.content.res.Configuration;
import android.content.res.Resources;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.lunasoft.examenturismo.LugarTuristico;
import com.lunasoft.examenturismo.R;

import java.util.List;
import java.util.Locale;

public class ConfiguracionViewModel extends ViewModel {

    private Resources resources;
    private String codigoIdiomaSeleccionado = "es";

    public void setResources(Resources resources) {
        this.resources = resources;
    }
    
    public void cambiarIdioma(String idiomaElegido) {
        codigoIdiomaSeleccionado = obtenerCodigoIdioma(idiomaElegido);
        establecerIdioma(codigoIdiomaSeleccionado);
    }

    public String getCodigoIdiomaSeleccionado() {
        return codigoIdiomaSeleccionado;
    }

    private String obtenerCodigoIdioma(String idioma) {
        if (idioma.equals("English")) {
            return "en";
        } else if (idioma.equals("Español")) {
            return "es";
        } else {
            return "es";
        }
    }

    private void establecerIdioma(String codigoIdioma) {
        Locale locale = new Locale(codigoIdioma);
        locale.setDefault(locale);
        Configuration config = resources.getConfiguration();
        config.setLocale(locale);
        resources.updateConfiguration(config, resources.getDisplayMetrics());
    }

    /* TESTING CAMBIO DE MAPA */
    private MutableLiveData<String> tipoMapa = new MutableLiveData<>();

    public LiveData<String> getTipoMapa() {
        return tipoMapa;
    }

    public void setTipoMapa(String tipo) {
        tipoMapa.setValue(tipo);
    }

    public int obtenerTipoMapa() {
        String selectedMapType = tipoMapa.getValue();

        if (selectedMapType == null) {
            return GoogleMap.MAP_TYPE_NORMAL; // valor por defecto
        }

        switch (selectedMapType) {
            case "Satelital":
            case "Satellite":
                return GoogleMap.MAP_TYPE_SATELLITE;
            case "Terreno":
            case "Terrain":
                return GoogleMap.MAP_TYPE_TERRAIN;
            case "Híbrido":
            case "Hybrid":
                return GoogleMap.MAP_TYPE_HYBRID;
            default:
                return GoogleMap.MAP_TYPE_NORMAL;
        }
    }

    /* TESTING PONER MARCADORES */

    public void colocarMarcadoresEnMapa(List<LugarTuristico> lugares, GoogleMap googleMap) {
        if (lugares != null && googleMap != null) {
            for (LugarTuristico lugar : lugares) {
                LatLng lugarLatLng = new LatLng(lugar.getLat(), lugar.getLon());
                MarkerOptions markerLugar = new MarkerOptions()
                        .position(lugarLatLng)
                        .title(lugar.getNombre());

                // Establecer icono según el rubro
                switch (lugar.getRubro()) {
                    case COMIDA:
                        markerLugar.icon(BitmapDescriptorFactory.fromResource(R.drawable.comida));
                        break;
                    case ALOJAMIENTO:
                        markerLugar.icon(BitmapDescriptorFactory.fromResource(R.drawable.alojamiento));
                        break;
                    case ENTRETENIMIENTO:
                        markerLugar.icon(BitmapDescriptorFactory.fromResource(R.drawable.entretenimiento));
                        break;
                    default:
                        markerLugar.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
                        break;
                }
                googleMap.addMarker(markerLugar);
            }
        }
    }

}
