package com.saleeh.astra.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.saleeh.astra.api.models.Event;
import com.saleeh.astra.api.models.Participant;
import com.saleeh.astra.databinding.FragmentEventBinding;
import com.saleeh.astra.databinding.FragmentParticipantBinding;
import com.saleeh.astra.model.EventsViewModel;
import com.saleeh.astra.model.ParticipantViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class ParticipientFragment extends Fragment {


    public ParticipientFragment() {
        // Required empty public constructor
    }

    FragmentParticipantBinding binding;
    ParticipantViewModel viewModel = new ParticipantViewModel();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentParticipantBinding.inflate(inflater, container, false);
        viewModel.items.add(new Participant("Saleeh", "CS", "8"));
        viewModel.items.add(new Participant("Rahana", "CS", "2"));
        viewModel.items.add(new Participant("Aith", "CE", "4"));
        viewModel.items.add(new Participant("Aswin", "AE", "6"));
        viewModel.items.add(new Participant("Lijo", "IT", "8"));
        viewModel.items.add(new Participant("sasasa", "EX", "3"));
        binding.setViewModel(viewModel);
        return binding.getRoot();
    }

}
