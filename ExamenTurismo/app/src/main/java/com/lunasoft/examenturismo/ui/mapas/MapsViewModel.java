package com.lunasoft.examenturismo.ui.mapas;

import android.Manifest;
import android.app.Application;
import android.content.pm.PackageManager;
import android.location.Location;
//import android.location.LocationRequest;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.location.*;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.lunasoft.examenturismo.LugarTuristico;
import com.lunasoft.examenturismo.ui.lugares_turisticos.LugaresViewModel;

import java.util.List;

public class MapsViewModel extends AndroidViewModel {

    private MutableLiveData<Location> mLocation;
    private FusedLocationProviderClient fused;
    LocationCallback callback;
    private MutableLiveData<List<LugarTuristico>> mLugaresTuristicos;

    private LugaresViewModel lugaresViewModel;

    public MapsViewModel(@NonNull Application application) {
        super(application);
        fused = LocationServices.getFusedLocationProviderClient(getApplication());
        lugaresViewModel = new ViewModelProvider.AndroidViewModelFactory(application).create(LugaresViewModel.class);

    }

    public LiveData<Location> getMLocation() {
        if (mLocation == null) {
            this.mLocation = new MutableLiveData<>();
        }
        return mLocation;
    }

    public void obtenerUltimaUbicacion() {
        if (ActivityCompat.checkSelfPermission(getApplication(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplication(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.d("salida", "Sin permisos");
            return;
        }

        Task<Location> task = fused.getLastLocation();

        task.addOnSuccessListener(getApplication().getMainExecutor(), new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    mLocation.postValue(location);
                }
            }
        });
    }

    public void lecturaPermanente() {
        LocationRequest request = new LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 5000).build();
        callback = new LocationCallback() {
            @Override
            public void onLocationResult(@NonNull LocationResult locationResult) {
                // super.onLocationResult(locationResult);
                if (locationResult == null) {
                    return;
                }
                Location location = locationResult.getLastLocation();
                mLocation.postValue(location);
            }
        };

        if (ActivityCompat.checkSelfPermission(getApplication(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplication(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        fused.requestLocationUpdates(request, callback, null);
    }

    public void paraLecturaPermanente(){
        fused.removeLocationUpdates(callback);
    }

    public LiveData<List<LugarTuristico>> getListaLugares() {
        return lugaresViewModel.getListaLugares();
    }
}
