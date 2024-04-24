package com.lunasoft.examenturismo.ui.lugares_turisticos;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lunasoft.examenturismo.LugarTuristico;
import com.lunasoft.examenturismo.R;
import com.lunasoft.examenturismo.databinding.FragmentLugaresBinding;

import java.util.ArrayList;
import java.util.List;

public class LugaresFragment extends Fragment {

    private FragmentLugaresBinding binding;
    private LugarAdapter adapter;

    private LugarSeleccionadoViewModel mViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        LugaresViewModel viewModel =
                new ViewModelProvider(this).get(LugaresViewModel.class);

        binding = FragmentLugaresBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        RecyclerView recyclerView = binding.listaLugares;

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new LugarAdapter(new ArrayList<>(), getContext(), getLayoutInflater());
        recyclerView.setAdapter(adapter);

        viewModel.getListaLugares().observe(getViewLifecycleOwner(), new Observer<List<LugarTuristico>>() {
            @Override
            public void onChanged(List<LugarTuristico> lugares) {
                adapter.setLugares(lugares);
            }
        });

        mViewModel = new ViewModelProvider(this).get(LugarSeleccionadoViewModel.class);
        mViewModel.getLugarM().observe(getViewLifecycleOwner(), new Observer<LugarTuristico>() {
            @Override
            public void onChanged(LugarTuristico lugar) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("lugar", lugar);
                Navigation.findNavController(getActivity(), R.id.nav_host_fragment_content_main).navigate(R.id.lugarSeleccionado, bundle);
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