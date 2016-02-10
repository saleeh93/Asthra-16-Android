package com.saleeh.astra.model;

import android.databinding.BindingAdapter;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.widget.ImageView;

import com.saleeh.astra.BR;
import com.saleeh.astra.R;
import com.saleeh.astra.api.models.Event;
import com.saleeh.astra.api.models.Participant;

import java.lang.reflect.Field;

import me.tatarka.bindingcollectionadapter.ItemView;

/**
 * Created by saleeh on 10/8/15.
 */
public class ParticipantViewModel {
    public final ObservableList<Participant> items = new ObservableArrayList<>();
    public final ItemView itemView = ItemView.of(BR.item, R.layout.list_item_participant);

    @BindingAdapter({"bind:loadImage"})
    public static void loadImage(ImageView view, Event event) {
        //    Picasso.with(view.getContext()).load(url).error(error).into(view);
        int res = getResId(event.icon, R.drawable.class);
        if (res != -1)
            view.setImageResource(res);
    }

    public static int getResId(String resName, Class<?> c) {

        try {
            Field idField = c.getDeclaredField(resName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

}
