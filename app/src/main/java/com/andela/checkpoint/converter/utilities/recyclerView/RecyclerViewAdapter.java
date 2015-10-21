package com.andela.checkpoint.converter.utilities.recyclerView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.andela.checkpoint.converter.R;
import com.andela.checkpoint.converter.model.Currency;

import java.util.ArrayList;

/**
 * Created by andela on 10/18/15.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RowViewHolder>{
    Context context;
    ArrayList<Currency> itemsList;

    public RecyclerViewAdapter(Context context, ArrayList<Currency> itemsList) {
        this.context = context;
        this.itemsList = itemsList;
    }

    @Override
    public int getItemCount() {
        if (itemsList == null) {
            return 0;
        } else {
            return itemsList.size();
        }
    }

    @Override
    public RowViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.single_row, null);
        RowViewHolder viewHolder = new RowViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RowViewHolder rowViewHolder, int position) {
        Currency items = itemsList.get(position);
        rowViewHolder.textView.setText(String.valueOf(items.getSymbol()));
        rowViewHolder.textViewCurrency.setText(String.valueOf(items.getRate()));
    }

}
