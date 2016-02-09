package com.saleeh.astra.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.saleeh.astra.PartcipientActivity;
import com.saleeh.astra.api.models.Event;
import com.saleeh.astra.api.models.Group;
import com.saleeh.astra.databinding.FragmentEventBinding;
import com.saleeh.astra.databinding.FragmentHomeBinding;
import com.saleeh.astra.model.EventsViewModel;
import com.saleeh.astra.model.GroupViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventsFragment extends Fragment {


    public EventsFragment() {
        // Required empty public constructor
    }

    FragmentEventBinding binding;
    EventsViewModel viewModel = new EventsViewModel();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentEventBinding.inflate(inflater, container, false);
        viewModel.items.add(new Event("Cricket", "#636161", "100"));
        viewModel.items.add(new Event("Football", "#e51c23", "200"));
        viewModel.items.add(new Event("VolleyBall", "#e91e63", "150"));
        viewModel.items.add(new Event("100m", "#03a9f4", "300"));
        viewModel.items.add(new Event("200m", "#ff5722", "400"));
        viewModel.items.add(new Event("EC", "#259b24", "200"));
        binding.setViewModel(viewModel);
        binding.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intetnt = new Intent(getActivity(), PartcipientActivity.class);
                startActivity(intetnt);
            }
        });
        return binding.getRoot();
    }

}
