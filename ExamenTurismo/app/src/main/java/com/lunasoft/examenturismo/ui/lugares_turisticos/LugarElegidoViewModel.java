package com.lunasoft.examenturismo.ui.lugares_turisticos;

import android.app.Application;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.lunasoft.examenturismo.LugarTuristico;

public class LugarElegidoViewModel extends AndroidViewModel {

    private MutableLiveData<LugarTuristico> LugarTuristicoM;
    public LugarElegidoViewModel(@NonNull Application application) {
        super(application);
    }
    public LiveData<LugarTuristico> getLugarM() {
        if (LugarTuristicoM == null){
            LugarTuristicoM = new MutableLiveData<>();
        }
        return LugarTuristicoM;
    }
    public void recuperarLugar(Intent intent){

        LugarTuristico lugar = (LugarTuristico)intent.getSerializableExtra("lugar");

        if (lugar != null){
            LugarTuristicoM.setValue(lugar);
        }
    }
}