package com.lunasoft.examenturismo;

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

import com.google.android.gms.location.*;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

public class MainActivityViewModel extends AndroidViewModel {

    private MutableLiveData<Location> mLocation;
    private FusedLocationProviderClient fused;
    LocationCallback callback;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        fused = LocationServices.getFusedLocationProviderClient(getApplication());
    }

}
