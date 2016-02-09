package com.saleeh.astra.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.saleeh.astra.databinding.FragmentHomeBinding;
import com.saleeh.astra.model.GroupViewModel;
import com.saleeh.astra.api.models.Group;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }

    FragmentHomeBinding binding;
    GroupViewModel viewModel = new GroupViewModel();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        viewModel.items.add(new Group("CSE", "#636161", "100"));
        viewModel.items.add(new Group("ME", "#e51c23", "200"));
        viewModel.items.add(new Group("AE", "#e91e63", "150"));
        viewModel.items.add(new Group("AU", "#03a9f4", "300"));
        viewModel.items.add(new Group("CE", "#ff5722", "400"));
        viewModel.items.add(new Group("EC", "#259b24", "200"));
        binding.setViewModel(viewModel);
        return binding.getRoot();
    }

}
