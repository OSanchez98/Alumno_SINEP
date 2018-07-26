package com.example.ofelia.app_alumno;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;

import java.io.DataOutput;
import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
/*      FUENTES DEL MAIN PRINCIPAL (LOGIN)      */
    EditText txtus,txtpas;
    TextView txvTitle, txvLeyenda, txvQueOnda, txvSINEP;
    Button btnEntrar;

    //Variables de la fuente
    private Typeface Ral_Medium, Ral_Light, Ral_Bold, Ral_Regular;
    private Typeface Mons_Medium, Mons_Regular;

    /*      FUENTES DEL APARTADO DE FECHAS       */
    TextView txvFecha, txvDocto, txvCarrera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        load_fonts();

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
                                    //Invocamos a la nueva ventana
                                        //Primer parametro es donde me encuenro, segundo es a donde quiero ir
                                    Intent f = new Intent(MainActivity.this, fechas.class);
                                    //Inicia el activity
                                    startActivity(f);
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
            URL url = new URL("http://localhost/WebService/ServicioVal.php");
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



    public void load_fonts(){
    /*          CARGA FUENTES DESDE EL ASSETS          */
        //Raleway ttf
        String sRal_Light= "fonts/Raleway-Light.ttf";
        this.Ral_Light = Typeface.createFromAsset(getAssets(),sRal_Light); //1

        String sRal_Regular= "fonts/Raleway-Regular.ttf";
        this.Ral_Regular = Typeface.createFromAsset(getAssets(),sRal_Regular); //2

        String sRal_Medium = "fonts/Raleway-Medium.ttf";
        this.Ral_Medium = Typeface.createFromAsset(getAssets(),sRal_Medium); //3

        String sRal_Bold= "fonts/Raleway-Bold.ttf";
        this.Ral_Bold = Typeface.createFromAsset(getAssets(),sRal_Bold); //4

        //Montserrat otf
        String sMons_Regular= "fonts/Montserrat-Regular.otf";
        this.Mons_Regular = Typeface.createFromAsset(getAssets(),sMons_Regular); //1

        String sMons_Medum= "fonts/Montserrat-Medium.otf";
        this.Mons_Medium = Typeface.createFromAsset(getAssets(),sMons_Medum); //2


    /*      ASIGNA FUENTES A LOS ELEMENTOS      */
        //Text field matrícula
        txtus = (EditText) findViewById(R.id.txtMat);
        txtus.setTypeface(Mons_Regular);
        //Titulo
        txvTitle = (TextView) findViewById(R.id.titleSINEP);
        txvTitle.setTypeface(Ral_Bold);
        //Definicion del SINEP
        txvSINEP = (TextView) findViewById(R.id.titleSINEP2);
        txvSINEP.setTypeface(Ral_Regular);
        //Leyenda
        txvLeyenda = (TextView) findViewById(R.id.txtLeyenda);
        txvLeyenda.setTypeface(Ral_Light);
        //Que onda
        txvQueOnda = (TextView) findViewById(R.id.txtQueOnda);
        txvQueOnda.setTypeface(Ral_Light);

    /*Fuentes de las fechas
        //TextView de Fecha
        txvFecha = (TextView) findViewById(R.id.txvFechaId);
        txvFecha.setTypeface(Mons_Regular);
        //Txv de Documento
        txvDocto = (TextView) findViewById(R.id.txvDoctoId);
        txvDocto.setTypeface(Ral_Regular);
        //Txv de Carrera
        txvCarrera = (TextView) findViewById(R.id.txvCarreraId);
        txvCarrera.setTypeface(Ral_Regular);

*/
    }

}
