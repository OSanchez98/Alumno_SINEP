package com.example.ofelia.app_alumno;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class fechas extends AppCompatActivity {

    //Variables del Recycle
    private AdapterDatosFecha adaptadorFechas;
    private RecyclerView recyclerViewFechas;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fechas);

        //Se llama al layout
        recyclerViewFechas = (RecyclerView) findViewById(R.id.recyclerIdFechas);
        //Se determina la forma de la lista
        recyclerViewFechas.setLayoutManager(new LinearLayoutManager(this));

        //Se envia toda la informacion de la lista al adaptador
            //Se asigna la informacion al recycleview
        adaptadorFechas = new AdapterDatosFecha(obtieneFechas());
        recyclerViewFechas.setAdapter(adaptadorFechas);
    }

    //Obtiene los datos que se van a usar y se retorna el array
    public List<DatesModelo> obtieneFechas(){
        List<DatesModelo> fechas = new ArrayList<>();
        fechas.add(new DatesModelo("27/05/2018", "Carta de Liberacion", "Ing. Software", R.drawable.ic_event_available_black_24dp));
        fechas.add(new DatesModelo("28/05/2018", "Carta de Aceptacion", "Ing. Biomedica", R.drawable.ic_event_available_black_24dp));
        fechas.add(new DatesModelo("29/05/2018", "Carta de Declaracion", "Lic. Terapia Fisica", R.drawable.ic_event_available_black_24dp));
        fechas.add(new DatesModelo("27/05/2018", "Carta de Liberacion", "Ing. Software", R.drawable.ic_event_available_black_24dp));

        return fechas;
    }


}
