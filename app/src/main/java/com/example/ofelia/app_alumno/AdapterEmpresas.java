package com.example.ofelia.app_alumno;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class AdapterEmpresas extends RecyclerView.Adapter<AdapterEmpresas.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txvname, txvWeb;
        private ImageView anuncio;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txvname = (TextView) itemView.findViewById(R.id.txtname);
            txvWeb = (TextView) itemView.findViewById(R.id.txtweb);
            anuncio = (ImageView) itemView.findViewById(R.id.imagenEmp);
        }

    }
    public List<empresas> ListEmp;
    public AdapterEmpresas(List<empresas>ListEmp){this.ListEmp = ListEmp;};

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //debe traer el layout que tiene los items, como que el hijo al que esta como que 'heredando'
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_principal,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.txvname.setText(ListEmp.get(i).getNombre_empresa());
        viewHolder.txvWeb.setText(ListEmp.get(i).getPagoficial_empresa());
       // viewHolder.iconDate.setImageResource(fechasLista.get(i).getIconImg());
    }

    @Override
    public int getItemCount() {
        return 0;
    }






}
