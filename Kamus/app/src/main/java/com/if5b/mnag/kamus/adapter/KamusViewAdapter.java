package com.if5b.mnag.kamus.adapter;

import android.content.Context;
import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.if5b.mnag.kamus.models.Kamus;

import java.util.ArrayList;

public class KamusViewAdapter extends RecyclerView.Adapter<KamusViewAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Kamus> data = new ArrayList<>();

    public KamusViewAdapter(Context context) {
        this.context = context;
    }

    public void addItem(ArrayList<Kamus> data){
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public KamusViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull KamusViewAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
