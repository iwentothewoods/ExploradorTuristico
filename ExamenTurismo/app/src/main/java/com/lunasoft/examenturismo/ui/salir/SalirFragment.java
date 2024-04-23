package com.lunasoft.examenturismo.ui.salir;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lunasoft.examenturismo.R;
import com.lunasoft.examenturismo.databinding.FragmentSalirBinding;

public class SalirFragment extends Fragment {

    private SalirViewModel mViewModel;

    private FragmentSalirBinding binding;

    public static SalirFragment newInstance() {
        return new SalirFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SalirViewModel salirViewModel =
                new ViewModelProvider(this).get(SalirViewModel.class);

        binding = FragmentSalirBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.btSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }




}