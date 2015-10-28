package com.andela.checkpoint.converter.ui_helpers.recyclerView;

import android.view.View;
import android.widget.TextView;
import android.support.v7.widget.RecyclerView;

import com.andela.checkpoint.converter.R;

/**
 * Created by andela on 10/18/15.
 */

public class RowViewHolder extends RecyclerView.ViewHolder {

    TextView textViewCurrency;
    TextView textView;

    public RowViewHolder(View view) {
        super(view);
        this.textView = (TextView) view.findViewById(R.id.title);
        this.textViewCurrency = (TextView) view.findViewById(R.id.currency_value);
    }
}

