package com.lunasoft.examenturismo.ui.lugares_turisticos;

import android.app.Application;
import android.content.res.Resources;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.lunasoft.examenturismo.LugarTuristico;
import com.lunasoft.examenturismo.R;

import java.util.ArrayList;
import java.util.List;

public class LugaresViewModel extends AndroidViewModel {

    private MutableLiveData<List<LugarTuristico>> listaLugares;

    public LugaresViewModel(@NonNull Application application) {
        super(application);
        listaLugares = new MutableLiveData<>();
        inicializarLista();
    }

    public LiveData<List<LugarTuristico>> getListaLugares() {
        return listaLugares;
    }

    private void inicializarLista() {
        List<LugarTuristico> lugares = new ArrayList<>();

        lugares.add(new LugarTuristico(
                "La Soñada Cabañas de Tronco y Spa",
                "Ruta 1 Km. 3,8 Nº 2058. Merlo. San Luis",
                -32.37324034475522, -65.01824666748922,
                "La soñada es uno de esos lugares distintos, de características irremplazables. " +
                        "La paz y la tranquilidad de la naturaleza, se adueñan del lugar, magnificando los " +
                        "incomparables sonidos de los pájaros.",
                R.drawable.lasonada,
                "Todos los días de 10hs a 21hs.",
                LugarTuristico.RubroLugar.ALOJAMIENTO
        ));

        lugares.add(new LugarTuristico("Allegra en casa - Cocina de Campo",
                "Ruta 1 cerro de oro, Merlo, San Luis",
                -32.37409140986339, -65.0206245276278,
                "Un lugar diferente, horno barro, Pastas caseras, Pescado.",
                R.drawable.allegra,
                "Todos los días de 10hs a 21hs.",
                LugarTuristico.RubroLugar.COMIDA));

        lugares.add(new LugarTuristico("Yucat Parque Tematico",
                "Calle El Laurel 398, D5881 Merlo, San Luis",
                -32.373141653648226, -65.0119694912067,
                "El Parque Temático Yucat es un emprendimiento turístico y cultural " +
                        "ubicado en Villa de Merlo, San Luis, que recrea la vida de los antiguos " +
                        "habitantes del Valle del Conlara.\n",
                R.drawable.yucat,
                "Jueves a Sábado de 10hs a 17:30hs",
                LugarTuristico.RubroLugar.ENTRETENIMIENTO));

        lugares.add(new LugarTuristico("Finca La Vivi (vivero, confiteria y restó)",
                "RP1 815, D5881 Merlo, San Luis",
                -32.357803571267226, -65.01322121153747,
                "Viví la verdadera experiencia del #TurismoRural! Casa de té y resto, pastelería casera\n" +
                        "Granja de animales, Lactería, Vivero. Entrada libre!",
                R.drawable.finca,
                "Todos los días de 8hs a 22hs.",
                LugarTuristico.RubroLugar.COMIDA));

        lugares.add(new LugarTuristico("Casino Flamingo",
                "Los Olivos, Merlo, San Luis",
                -32.34693966113616, -65.00397026079621,
                "Juegos de mesa como ruleta, blackjack y póquer, máquinas tragamonedas, " +
                        "restaurante y pista de baile en el bar.",
                R.drawable.casino,
                "Todos los días de 10hs a 04hs.",
                LugarTuristico.RubroLugar.ENTRETENIMIENTO));

        lugares.add(new LugarTuristico("Virginia Palace Hotel & Spa",
                "Ruta provincial Nº 1, Carlos Gardel esq, D5881 Merlo, San Luis",
                -32.35397615948374, -65.01524344033237,
                "Los servicios incluyen 2 piscinas al aire libre (1 para niños), " +
                        "una piscina cubierta y un spa. También se ofrece un bar con terraza, " +
                        "además de un área de juegos infantiles y jardines.",
                R.drawable.virginia,
                "Abierto las 24 horas.",
                LugarTuristico.RubroLugar.ALOJAMIENTO));

        listaLugares.setValue(lugares);
    }
}