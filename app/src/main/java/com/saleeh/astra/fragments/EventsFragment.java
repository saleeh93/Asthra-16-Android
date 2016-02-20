package com.saleeh.astra.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.saleeh.astra.PartcipientActivity;
import com.saleeh.astra.api.ServiceAPI;
import com.saleeh.astra.api.models.Event;
import com.saleeh.astra.api.models.Group;
import com.saleeh.astra.databinding.FragmentEventBinding;
import com.saleeh.astra.databinding.FragmentHomeBinding;
import com.saleeh.astra.model.EventsViewModel;
import com.saleeh.astra.model.GroupViewModel;

import java.util.List;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventsFragment extends BaseFragment {


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
        binding.setViewModel(viewModel);

        ServiceAPI.getInstance().getApiService().events().enqueue(new Callback<List<Event>>() {
            @Override
            public void onResponse(Response<List<Event>> response, Retrofit retrofit) {
                viewModel.items.addAll(response.body());
                binding.progressView.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Throwable t) {

                showMessage("Server Error :" + t.getMessage());
            }
        });

        binding.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Event event = (Event) parent.getItemAtPosition(position);
                Intent intent = new Intent(getActivity(), PartcipientActivity.class);
                intent.putExtra("id", event.id);
                intent.putExtra("name", event.name);
                startActivity(intent);
            }
        });
        binding.listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                return false;
            }
        });
        return binding.getRoot();
    }


}
