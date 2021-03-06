package com.saleeh.astra.fragments;


import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.saleeh.astra.api.ServiceAPI;
import com.saleeh.astra.api.models.Event;
import com.saleeh.astra.api.models.Participant;
import com.saleeh.astra.databinding.FragmentEventBinding;
import com.saleeh.astra.databinding.FragmentParticipantBinding;
import com.saleeh.astra.model.EventsViewModel;
import com.saleeh.astra.model.ParticipantViewModel;

import java.util.List;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 */
public class ParticipientFragment extends BaseFragment {

    public String eventId;

    public String eventName;

    public ParticipientFragment() {
        // Required empty public constructor
    }

    public static ParticipientFragment newInstance(String id, String eventName) {
        ParticipientFragment frag = new ParticipientFragment();
        Bundle args = new Bundle();
        args.putString("id", id);
        args.putString("name", eventName);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        eventId = getArguments().getString("id");
        eventName = getArguments().getString("name");

    }

    FragmentParticipantBinding binding;
    ParticipantViewModel viewModel = new ParticipantViewModel();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(eventName);
        binding = FragmentParticipantBinding.inflate(inflater, container, false);

        binding.setViewModel(viewModel);
        ServiceAPI.getInstance().getApiService().participant(eventId).enqueue(new Callback<List<Participant>>() {
            @Override
            public void onResponse(Response<List<Participant>> response, Retrofit retrofit) {
                viewModel.items.addAll(response.body());

                binding.progressView.setVisibility(View.GONE);
                if (viewModel.items.size() <= 0)
                    showMessage("No participants");

            }

            @Override
            public void onFailure(Throwable t) {

                showMessage("Server Error :" + t.getMessage());
            }
        });
        return binding.getRoot();
    }



}
