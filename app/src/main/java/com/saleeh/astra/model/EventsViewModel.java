package com.saleeh.astra.model;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import com.saleeh.astra.BR;
import com.saleeh.astra.R;
import com.saleeh.astra.api.models.Event;
import com.saleeh.astra.api.models.Group;

import me.tatarka.bindingcollectionadapter.ItemView;

/**
 * Created by saleeh on 10/8/15.
 */
public class EventsViewModel {
    public final ObservableList<Event> items = new ObservableArrayList<>();
    public final ItemView itemView = ItemView.of(BR.item, R.layout.list_item_event);

}
