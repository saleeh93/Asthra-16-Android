package com.saleeh.astra;

import android.content.Intent;
import android.content.res.TypedArray;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Toast;

import com.dd.processbutton.iml.ActionProcessButton;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.SaveCallback;
import com.saleeh.astra.api.ServiceAPI;
import com.saleeh.astra.api.models.Group;
import com.saleeh.astra.api.models.User;
import com.saleeh.astra.databinding.ActivityUserBinding;

import java.util.HashMap;
import java.util.List;

import dmax.dialog.SpotsDialog;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;
import uz.shift.colorpicker.OnColorChangedListener;

public class UserActivity extends BaseActivity {

    ActivityUserBinding binding;
    //   int colors[];
    ParseInstallation installation;
    String[] sems = new String[]{"Semester", "2", "4", "6", "8"};
    int currentColor;
    String groups[];
    private String currentDept = "CSE";
    //  private HashMap<Integer, Integer> colors;

    private HashMap<Integer, String> colorsMap = new HashMap<>();
    int[] colors = new int[10];
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        installation = ParseInstallation.getCurrentInstallation();

        binding = DataBindingUtil.setContentView(this, R.layout.activity_user);
        final SpotsDialog dialog = new SpotsDialog(this);
        dialog.show();
        ServiceAPI.getInstance().getApiService().groups().enqueue(new Callback<List<Group>>() {
            @Override
            public void onResponse(Response<List<Group>> response, Retrofit retrofit) {
                int i = 0;
                for (Group group : response.body()) {
                    colors[i] = Color.parseColor(group.color);
                    colorsMap.put(colors[i], group.name);
                    i++;

                }
                binding.picker.setColors(colors);
                binding.picker.setSelectedColorPosition(0);
                //     binding.picker.setLayoutParams(new );
                dialog.hide();
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(UserActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });
        TypedArray ta = getResources().obtainTypedArray(R.array.colors);
        groups = getResources().getStringArray(R.array.groups);
        //      colors = new HashMap<>();
        //    for (int i = 0; i < ta.length(); i++) {
        //      colors.put(ta.getColor(i, 0), i);
        //  }
        //ta.recycle();
        binding.spinner.setItems(sems);
        binding.picker.setOnColorChangedListener(new OnColorChangedListener() {
            @Override
            public void onColorChanged(int c) {
                currentColor = c;
                currentDept = getDept(c);
                binding.deptTextView.setText(currentDept);
            }
        });
        binding.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.spinner.getSelectedIndex() == 0) {
                    Toast.makeText(UserActivity.this, "Please Select Semester", Toast.LENGTH_SHORT).show();
                    return;
                } else if (binding.editText.getText().toString().equals("")) {
                    Toast.makeText(UserActivity.this, "Please Enter Your name", Toast.LENGTH_SHORT).show();
                    return;
                }
                binding.submit.setMode(ActionProcessButton.Mode.ENDLESS);
                binding.submit.setProgress(50);
                Snackbar.make(binding.rootl, "Account is being created..", Snackbar.LENGTH_INDEFINITE);
                //User us = new User();
                ///   us.setUser(binding.editText.getText().toString(), "CSE", sems[binding.spinner.getSelectedIndex()], currentColor);
                installation.put("name", binding.editText.getText().toString());
                installation.put("group", currentDept);
                installation.put("sem", sems[binding.spinner.getSelectedIndex()]);
                installation.put("theme", currentColor);
                installation.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            startActivity(new Intent(UserActivity.this, MainActivity.class));
                            finish();
                        } else {
                            Snackbar.make(binding.rootl, e.getMessage(), Snackbar.LENGTH_SHORT);
                        }
                    }
                });
            }
        });
        if (installation.get("name") != null) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public String getDept(int color) {
        //    return groups[colors.get(color)];
        return colorsMap.get(color);
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "User Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.saleeh.astra/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "User Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.saleeh.astra/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
