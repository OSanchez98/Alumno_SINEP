package com.example.ofelia.app_alumno;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class DatoEmp extends AppCompatActivity {
    //Variables del Recycle
    private AdapterEmpresas AdaptEmpre;
    private RecyclerView recyclerViewEmpresas;
    private RecyclerView.LayoutManager lman;
    private List<empresas> list;

    private List<empresas> getList(){return this.list;}

    private String jsonResult;
    public String url ="http://192.168.1.2/WebService/DatosAliados.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dato_emp);
        emp();

        //Se llama al layout
        recyclerViewEmpresas = (RecyclerView) findViewById(R.id.recyclerIdEmpresas);
        //Se determina la forma de la lista
        recyclerViewEmpresas.setHasFixedSize(true);
        recyclerViewEmpresas.setLayoutManager(new LinearLayoutManager(this));

        lman = new LinearLayoutManager(this);
        recyclerViewEmpresas.setLayoutManager(lman);
        //Se envia toda la informacion de la lista al adaptador
        //Se asigna la informacion al recycleview
       emp();
    }
    //Obtiene los datos que se van a usar y se retorna el array
    private void emp(){
      /*  fechas.add(new DatesModelo("27/05/2018", "Carta de Liberacion", "Ing. Software", R.drawable.ic_event_available_black_24dp));
        fechas.add(new DatesModelo("28/05/2018", "Carta de Aceptacion", "Ing. Biomedica", R.drawable.ic_event_available_black_24dp));
        fechas.add(new DatesModelo("29/05/2018", "Carta de Declaracion", "Lic. Terapia Fisica", R.drawable.ic_event_available_black_24dp));
        fechas.add(new DatesModelo("27/05/2018", "Carta de Liberacion", "Ing. Software", R.drawable.ic_event_available_black_24dp));
*/
      GetEmpresa wsClient = new GetEmpresa(AdaptEmpre,recyclerViewEmpresas,list,DatoEmp.this);
        wsClient.execute();
    }

}
