package com.example.ofelia.app_alumno;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


public class AdapterDatosFecha extends RecyclerView.Adapter<AdapterDatosFecha.ViewHolder>{

    //Clase del viewHolder
    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txvDate, txvCarrera, txvDocto;
        private ImageView iconDate;

        //Constructor de la clase
        public ViewHolder(View itemView) {
            super(itemView);
            txvDate = (TextView) itemView.findViewById(R.id.txvFechaId);
            txvCarrera = (TextView) itemView.findViewById(R.id.txvCarreraId);
            txvDocto = (TextView) itemView.findViewById(R.id.txvDoctoId);
            iconDate = (ImageView) itemView.findViewById(R.id.iconDate);

        }
    }

    //Almacena los datos de cada item en manera de lista
    public List<DatesModelo> fechasLista;

    //Mete la lista
    public AdapterDatosFecha(List<DatesModelo> fechasLista) {
        this.fechasLista = fechasLista;
    }

    //Infla el contenido del item a la lista
        //Inflar: es cuando se usa un layout dentro de otro
            //El items_date tiene que ser parte del activity_fechas
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        //debe traer el layout que tiene los items, como que el hijo al que esta como que 'heredando'
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_dates,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    //este metodo es para ir cambiando el contenido de cada item
        //i es igual a la psicion
    @Override
    public void onBindViewHolder(ViewHolder holder, int i) {
        holder.txvDate.setText(fechasLista.get(i).getDate());
        holder.txvDocto.setText(fechasLista.get(i).getTypeDoc());
        holder.txvCarrera.setText(fechasLista.get(i).getCarrer());
        holder.iconDate.setImageResource(fechasLista.get(i).getIconImg());
    }

    //Determina la cantidad de elementos a procesar
    @Override
    public int getItemCount() {
        return fechasLista.size();
    }
}
