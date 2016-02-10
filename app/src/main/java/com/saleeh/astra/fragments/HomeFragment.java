package com.saleeh.astra.fragments;


import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.saleeh.astra.api.ServiceAPI;
import com.saleeh.astra.databinding.FragmentHomeBinding;
import com.saleeh.astra.model.GroupViewModel;
import com.saleeh.astra.api.models.Group;

import java.util.List;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment {


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
        binding.progressView.startAnimation();
        binding.setViewModel(viewModel);

        loadData();

        return binding.getRoot();
    }

    public void loadData() {
        ServiceAPI.getInstance().getApiService().groups().enqueue(new Callback<List<Group>>() {
            @Override
            public void onResponse(Response<List<Group>> response, Retrofit retrofit) {
                viewModel.items.clear();
                for (Group group : response.body()) {

                    viewModel.items.add(group);
                }
                binding.progressView.setProgress(0);
                binding.executePendingBindings();
                binding.progressView.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Throwable t) {

                showMessage("Server Error :" + t.getMessage());

            }
        });
    }


}
