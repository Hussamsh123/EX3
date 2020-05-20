package com.hussam.ex2postpc;

import android.content.Context;
import android.os.Build;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Random;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private ArrayList<Item> todoBoomList;
    private Context context;

    RecyclerViewAdapter(ArrayList<Item> toodoBoomList, Context context) {
        this.todoBoomList = toodoBoomList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.todoboomm_layout, parent,
                false);
        return new ViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.text.setText(todoBoomList.get(position).getItemText());
        if (todoBoomList.get(position).isClicked()) {
            holder.text.setAlpha(0.3f);
        }
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.text.setAlpha(0.3f);
                if (!todoBoomList.get(position).isClicked()) {
                    Toast toast = Toast.makeText(context, "TODO \"" +
                            todoBoomList.get(position).getItemText() + "\" is now DONE. BOOM!", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
                todoBoomList.get(position).setClicked();


            }
        });
    }

    void updateData(ArrayList<Item> viewModels) {
        todoBoomList.clear();
        todoBoomList.addAll(viewModels);
        System.out.println(todoBoomList);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return todoBoomList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView text;
        private ConstraintLayout layout;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.TextID);
            layout = itemView.findViewById(R.id.layout);
        }
    }
}
