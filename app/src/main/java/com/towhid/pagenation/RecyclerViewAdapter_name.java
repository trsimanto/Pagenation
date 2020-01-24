package com.towhid.pagenation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


//contact fragment k control korte abong recycle view  er kj korte creat kora hoese
public class RecyclerViewAdapter_name extends RecyclerView.Adapter<RecyclerViewAdapter_name.MyViewHolder> {

    Context mcontext;
    List<Name> mData; //contact clalss type array list object declare kora hoese just , instantiate kora hoy nai

    public RecyclerViewAdapter_name(Context mcontext, List<Name> mData) {
        this.mcontext = mcontext;
        this.mData = mData;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(mcontext).inflate(R.layout.item, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.text.setText(mData.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView text;


        public MyViewHolder(View itemView) {

            super(itemView);

            text = (TextView) itemView.findViewById(R.id.text);


        }
    }


}
