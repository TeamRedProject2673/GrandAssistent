package com.example.grandassistent;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MyViewHolder> {
    private int resourse;
    private ArrayList<Model> mList;

    public MainAdapter(ArrayList<Model> mList, int resourse){
        this.mList = mList;
        this.resourse = resourse;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(resourse,parent,false);
        return new MyViewHolder(view);

    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Model model = mList.get(position);
        holder.textView_nombre.setText(model.getM_nombre());
        holder.textView_telefono.setText(model.getM_telfono());
        holder.textView_especializacion.setText(model.getM_especializacion());

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView textView_nombre, textView_telefono, textView_especializacion;
        public View view;

        public MyViewHolder(View view) {
            super(view);
            this.view = view;
            this.textView_nombre = (TextView) view.findViewById(R.id.tv_Lista_Nombre);
            this.textView_telefono = (TextView) view.findViewById(R.id.tv_Lista_Telefono);
            this.textView_especializacion = (TextView) view.findViewById(R.id.tv_Lista_Especializacion);
        }
    }


}
