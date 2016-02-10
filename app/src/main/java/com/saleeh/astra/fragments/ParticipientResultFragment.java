package com.saleeh.astra.fragments;


import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.saleeh.astra.api.ServiceAPI;
import com.saleeh.astra.api.models.Participant;
import com.saleeh.astra.databinding.FragmentParticipantBinding;
import com.saleeh.astra.databinding.FragmentParticipantEventBinding;
import com.saleeh.astra.model.ParticipantViewModel;
import com.saleeh.astra.model.ResultViewModel;

import java.util.List;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 */
public class ParticipientResultFragment extends BaseFragment {


    public String eventId;

    public String eventName;

    public ParticipientResultFragment() {
        // Required empty public constructor
    }

    public static ParticipientResultFragment newInstance(String id, String eventName) {
        ParticipientResultFragment frag = new ParticipientResultFragment();
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

    FragmentParticipantEventBinding binding;
    ResultViewModel viewModel = new ResultViewModel();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(eventName);

        // Inflate the layout for this fragment
        binding = FragmentParticipantEventBinding.inflate(inflater, container, false);
        binding.setViewModel(viewModel);

        ServiceAPI.getInstance().getApiService().results(eventId).enqueue(new Callback<List<Participant>>() {
            @Override
            public void onResponse(Response<List<Participant>> response, Retrofit retrofit) {
                viewModel.items.addAll(response.body());
                binding.progressView.setVisibility(View.GONE);
                if (viewModel.items.size() <= 0)
                    showMessage("Result Not published");

            }

            @Override
            public void onFailure(Throwable t) {

                showMessage("Server Error :" + t.getMessage());
            }
        });
        return binding.getRoot();
    }

}
