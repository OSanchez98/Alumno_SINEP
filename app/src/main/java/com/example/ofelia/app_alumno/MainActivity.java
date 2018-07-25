package com.example.ofelia.app_alumno;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;

import java.io.DataOutput;
import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    EditText txtus,txtpas;
    Button btnEntrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtus=(EditText)findViewById(R.id.txtMat);

        btnEntrar = (Button)findViewById(R.id.btLogin);

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Thread tr = new Thread()
                {
                    @Override
                    public void run() { //se van a enviar y recibir los valores del ws
                        final String res = enviarPost(txtus.getText().toString());
                        //metodo que permite trabajar con la interfaz grafica desde el hilo
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                int r= objtJSON(res);
                                if(r>0) //validamos si existe o no
                                {
                                    Intent i = new Intent(getApplicationContext(),principal.class);
                                    startActivity(i);
                                }
                                else
                                {
                                    Toast.makeText(getApplicationContext(),"Ususario no encontrado",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                };
              tr.start();
            }
        });

    }
    public String enviarPost(String idalumnos_practicantes)
    {
        String parametros="idalumnos_practicantes="+idalumnos_practicantes;
        //variable para conectar con el webservice
        HttpURLConnection connection = null;
        String respuesta="";
        try {
            //variable que almacena URL
            URL url = new URL("http://192.168.109.42/WebService/ServicioVal.php");
            connection=(HttpURLConnection)url.openConnection();
            //metodo de envio de datos
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Length",""+Integer.toString(parametros.getBytes().length));

            connection.setDoOutput(true); //salida de datos
            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            wr.writeBytes(parametros);
            wr.close();

            Scanner inStream = new Scanner(connection.getInputStream());

            while (inStream.hasNextLine()) //recorre todas las lineas y lo concatena
            {
                respuesta+=(inStream.nextLine()); //almacena toda la cadena
            }
        }catch (Exception e){}
            return  respuesta.toString(); //retorna la respuesta
    }
    public int objtJSON(String res)
    {
        int registros=0;
        try {
            JSONArray json = new JSONArray(res); //se convierte en json para poder contar
            if(json.length()>0)
            {
                registros=1;
            }

        }catch (Exception e){}
        return registros;
    }
}
