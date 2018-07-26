package com.example.ofelia.app_alumno;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

public class principal extends AppCompatActivity {

    //Variables del Recycle
    private AdapterEmpresas AdaptEmp;
    private RecyclerView recyclerViewEmpresas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
    }
}
