package com.saleeh.astra;

import android.content.Intent;
import android.content.res.TypedArray;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Toast;

import com.dd.processbutton.iml.ActionProcessButton;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.SaveCallback;
import com.saleeh.astra.api.models.User;
import com.saleeh.astra.databinding.ActivityUserBinding;

import java.util.HashMap;

import bolts.Continuation;
import bolts.Task;
import uz.shift.colorpicker.OnColorChangedListener;

public class UserActivity extends BaseActivity {

    ActivityUserBinding binding;
    //   int colors[];
    ParseInstallation installation;
    String[] sems = new String[]{"Semester", "2", "4", "6", "8"};
    int currentColor;
    String groups[];
    private String currentDept = "CSE";
    private HashMap<Integer, Integer> colors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        installation = ParseInstallation.getCurrentInstallation();

        binding = DataBindingUtil.setContentView(this, R.layout.activity_user);
        TypedArray ta = getResources().obtainTypedArray(R.array.colors);
        groups = getResources().getStringArray(R.array.groups);
        colors = new HashMap<>();
        for (int i = 0; i < ta.length(); i++) {
            colors.put(ta.getColor(i, 0), i);
        }
        ta.recycle();
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

    }

    public String getDept(int color) {
        return groups[colors.get(color)];

    }

}
