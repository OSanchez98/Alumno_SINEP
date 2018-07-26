package com.example.ofelia.app_alumno;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.List;

public class GetEmpresa extends AsyncTask<Void,Void,String> {
    private AdapterEmpresas AdaptEmp;
    private RecyclerView recyclerViewEmpresas;
    private List<empresas> ListEmp;

    private Context httpCont;
    ProgressDialog prodial;

    public GetEmpresa(AdapterEmpresas adaptEmp, RecyclerView recyclerViewEmpresas, List<empresas> listEmp, Context httpCont) {
        AdaptEmp = adaptEmp;
        this.recyclerViewEmpresas = recyclerViewEmpresas;
        ListEmp = listEmp;
        this.httpCont = httpCont;
    }

    @Override
    protected void onPreExecute()
    {
        super.onPreExecute();
        prodial= ProgressDialog.show(httpCont,"Espere un momento","Porfavor");
    }
    @Override
    protected String doInBackground(Void... voids) {
        String result = null;
        try {
            String wsURL ="http://192.168.1.2/WebService/DatosAliados.php";
            URL url = new URL(wsURL);
            HttpURLConnection urlcon = (HttpURLConnection)url.openConnection();
            InputStream in = new BufferedInputStream(urlcon.getInputStream());
            result = inputStreamToString(in);

        }catch (Exception e){e.printStackTrace();}
        return result;
    }

    @Override
    protected void onPostExecute(String s)
    {
        super.onPostExecute(s);
        prodial.dismiss();
        try {
            JSONObject jsonObject = new JSONObject(URLDecoder.decode(s,"UTF-8"));
            JSONArray jsonArray = jsonObject.getJSONArray("aliados_estrategicos");

            for(int i=0;i<jsonArray.length();i++)
            {
                String name = jsonArray.getJSONObject(i).getString("nombre_empresa");
                String web = jsonArray.getJSONObject(i).getString("pagoficial_empresa");
                this.ListEmp.add(new empresas(name,web));
            }
            AdaptEmp = new AdapterEmpresas(this.ListEmp);
            recyclerViewEmpresas.setAdapter(this.AdaptEmp);
        }catch (Exception e){}
    }

    private String inputStreamToString(InputStream is)
    {
        String rline="";
        StringBuilder ans = new StringBuilder();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader rd = new BufferedReader(isr);
        try {
            while ((rline = rd.readLine())!=null)
            {
                ans.append(rline);
            }
        }catch (Exception e){e.printStackTrace();}
        return ans.toString();
    }
}
