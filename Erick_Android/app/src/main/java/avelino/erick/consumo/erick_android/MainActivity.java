package avelino.erick.consumo.erick_android;


import android.os.AsyncTask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;


import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.http.HttpResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;




public class MainActivity extends AppCompatActivity {

    EditText cedula;
    RadioButton r1,r2;
    TextView nombre;
    TextView apellido;
    Button btn;
    Loginservice log;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //inicio de variables
        cedula = (EditText) findViewById(R.id.ced);
        r1=(RadioButton) findViewById(R.id.radioButton1);
        r2=(RadioButton) findViewById(R.id.radioButton2);
        nombre=(TextView)findViewById(R.id.re);
        apellido=(TextView)findViewById(R.id.re1);
        btn=(Button) findViewById(R.id.button);



        btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v ) {

                log=new Loginservice();
                log.execute("0930722475","Erick");


                /*obetener ob= new obetener();
                ob.execute(toString());
                String ced= cedula.getText().toString();
                String lec= "15120001";
                String lec1="15120002";
                if (r1.isChecked()==true)
                {


                }
                else
                    if (r2.isChecked()==true)
                {

                }*/







                /*String ape_estudiante;
                String cod_carrera;
                String cod_estudiante;
                String cod_lectivo;
                String nom_carrera;
                String nom_estudiante;
                String nom_lectivo;
                String nom_materia;
                double primer_academica;
                double primer_evaluacion;
                double primer_promedio;
                double promedio;
                double segundo_academica;
                double segundo_evaluacion;
                double segundo_promedio;
                String ced = cedula.getText().toString();
                int nro1 = Integer.parseInt(ced);
                if (r1.isChecked() == true) {
                    String lectivo = "2013 - 2014 CI";
                    String lec = "15120001";

                    HttpClient httpClient = new DefaultHttpClient();

                    String id = nombre.getText().toString();
                    HttpGet del = new HttpGet("http://lisrestful.azurewebsites.net/api/notas?cod_estudiante=" + ced + "&cod_lectivo=" + lec + "");
                    try {

                        HttpResponse resp = httpClient.execute(del);
                        String respStr = EntityUtils.toString(resp.getEntity());

                        JSONObject respJSON = new JSONObject(respStr);

                        nom_estudiante = respJSON.getString("nom_estudiante");
                        ape_estudiante = respJSON.getString("ape_estudiante");

                        nombre.setText(nom_estudiante);
                        apellido.setText(ape_estudiante);

                    } catch (Exception ex) {
                        Log.e("ServicioRest", "Error!", ex);
                    }

                } else if (r2.isChecked() == true) {

                        nombre.setText("copia");



                }*/
            }


        });


}
    private class Loginservice extends AsyncTask<String,String,Boolean>
    {
     JSONObject responseJSON;
        @Override
        protected  Boolean doInBackground(String... params)
        {
            Boolean result=true;
            HttpClient httpClient=new DefaultHttpClient();
            HttpPost post=new HttpPost("http://lisrestful.azurewebsites.net/api/notas?cod_estudiante=0930722475&cod_lectivo=15120002");
            post.setHeader("content-type","application/json");

            //construir objeto
            JSONObject dato=new JSONObject();
            try{
                dato.put("nom_estudiante", params[0]);
                dato.put("ape_estudiante", params[1]);
                dato.put("token", "11f4aebd66702cd867dc87d6f5071609f49a18603469b09a6fc7d5843b5701");
                StringEntity entity=new StringEntity(dato.toString());
                post.setEntity(entity);
                HttpResponse resp=httpClient.execute(post);
                String respStr= EntityUtils.toString(resp.getEntity());
                JSONObject respJSON= new  JSONObject(respStr);
                if (respJSON.getBoolean("Exito"))
                {
                    result=false;
                }
                else{
                    responseJSON=respJSON;
                }



            }catch (JSONException e){
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


            return result;
        }
        @Override
        protected void onPostExecute(Boolean result)
        {
            if (result)
            {
                try {
                    JSONObject datos=responseJSON.getJSONObject("Datos");
                    nombre.setText(datos.getString("nom_estudiante"));
                    apellido.setText(datos.getString("ape_estudiante"));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }

    }



   /* private class obetener extends AsyncTask<String,Void,Void>
    {
        String aape_estudiante;
        String acod_carrera;
        String acod_estudiante;
        String acod_lectivo;
        String anom_carrera;
        String anom_estudiante;
        String anom_lectivo;
        String anom_materia;
        double aprimer_academica;
        double aprimer_evaluacion;
        double aprimer_promedio;
        double apromedio;
        double asegundo_academica;
        double asegundo_evaluacion;
        double asegundo_promedio;
        @Override
        protected Void doInBackground(String... params)
        {
            String lectivo = "2013 - 2014 CI";
            String lec = "15120001";

            Log.i("consultar","doInBackground");
            HttpClient httpClient = new DefaultHttpClient();
            String sid = params[0];
            HttpGet del = new HttpGet("http://lisrestful.azurewebsites.net/api/notas?cod_estudiante=0930722475&cod_lectivo=15120002"+sid);
            del.setHeader("content-type","application/json");
            try {

                HttpResponse resp = httpClient.execute(del);
                String respStr = EntityUtils.toString(resp.getEntity());
                JSONObject respJSON = new JSONObject(respStr);
                aape_estudiante=respJSON.getString("ape_estudiante");
                anom_estudiante=respJSON.getString("nom_estudiante");


            } catch (Exception ex) {
                Log.e("ServicioRest", "Error!", ex);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void Result)
        {
        Log.i("ServicioRest","onpostExecute");
        nombre.setText("Nombre " + anom_estudiante);
        apellido.setText("Apellido " + aape_estudiante);
        }
        @Override
        protected void onPreExecute()
        {
            Log.i("ServicioRest", "onPreExecute");
            nombre.setText("se esta obteniendo la informacion");


        }

    }*/

}
