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

    Context context;
    ArrayList<Model> mList;

    public MainAdapter(Context context, ArrayList<Model> mList) {
        this.context = context;
        this.mList = mList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent,false);
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
        TextView textView_nombre, textView_telefono, textView_especializacion;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView_nombre = itemView.findViewById(R.id.tv_Lista_Nombre);
            textView_telefono = itemView.findViewById(R.id.tv_Lista_Telefono);
            textView_especializacion = itemView.findViewById(R.id.tv_Lista_Especializacion);

        }
    }


}
