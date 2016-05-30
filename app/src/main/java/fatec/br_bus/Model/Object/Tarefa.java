package fatec.br_bus.Model.Object;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import fatec.br_bus.Controller.TarefaInterface;

public class Tarefa extends AsyncTask<ArrayList<Linha>, Void, ArrayList<ArrayList<String>>> {
    private Context context;
    private ProgressDialog progress;
    private TarefaInterface ti;

    public Tarefa(Context context, TarefaInterface ti) {
        this.context = context;
        this.ti = ti;
    }

    @Override
    protected void onPreExecute() {
        progress = new ProgressDialog(context);
        progress.setMessage("Carregando");
        progress.show();
    }

    @Override
    protected ArrayList<ArrayList<String>> doInBackground(ArrayList<Linha>... params) {
        ArrayList<ArrayList<String>> arrayDearrayhora = new ArrayList<ArrayList<String>>();
        String jsonLinha;
        String jsonHora;
        String idLinha = null;

        if (params[0].size() > 0) {

            HttpClient httpclient = new DefaultHttpClient();
            HttpPost post = new HttpPost("http://api.olhovivo.sptrans.com.br/v0/Login/Autenticar?token=428bbecb720a8f0a9e7a1232959da9212276d720562ffa810f501a60ab83b238");
            try {
                post.setHeader("Content-type", "application/json");
                HttpResponse response = httpclient.execute(post);

            } catch (Exception e) {
                e.printStackTrace();
            }

            for (int a = 0; a < params[0].size(); a++) {
                HttpGet post1 = new HttpGet("http://api.olhovivo.sptrans.com.br/v0/Linha/Buscar?termosBusca=" + params[0].get(a).getCodigo());
                try {
                    post1.setHeader("Content-type", "application/json");
                    HttpResponse response1 = httpclient.execute(post1);

                    jsonLinha = inputStreamToString(response1.getEntity().getContent()).toString();
                    JSONObject reader_linha = new JSONObject("{\"linhas\":" + jsonLinha + "}");

                    if (reader_linha.getJSONArray("linhas").length() > 0) {
                        for (int i = 0; i < reader_linha.getJSONArray("linhas").length(); i++) {

                            JSONObject arraySentido = reader_linha.getJSONArray("linhas").getJSONObject(i);
                            if (arraySentido.getString("Sentido") == "2") {
                                if (params[0].get(a).getTerminal().equals("1")) {
                                    idLinha = arraySentido.getString("CodigoLinha");
                                }
                            }
                            if (arraySentido.getString("Sentido") == "1") {
                                if (params[0].get(a).getTerminal().equals("0")) {
                                    idLinha = arraySentido.getString("CodigoLinha");
                                }
                            }
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

                HttpGet post2 = new HttpGet("http://api.olhovivo.sptrans.com.br/v0/Previsao?codigoParada=" + params[0].get(a).getCodPonto() + "&codigoLinha=" + idLinha);
                try {
                    post2.setHeader("Content-type", "application/json");
                    HttpResponse response2 = httpclient.execute(post2);
                    post2 = null;

                    jsonHora = inputStreamToString(response2.getEntity().getContent()).toString();
                    JSONObject reader = new JSONObject(jsonHora);

                    if (reader.getString("p") != "null") {
                        for (int i = 0; i < reader.getJSONObject("p").getJSONArray("l").getJSONObject(0).getJSONArray("vs").length(); i++) {
                            JSONObject arrayOb = reader.getJSONObject("p").getJSONArray("l").getJSONObject(0).getJSONArray("vs").getJSONObject(i);
                            ArrayList<String> arrayhora = new ArrayList<String>();
                            arrayhora.add(i, arrayOb.getString("t"));
                            arrayDearrayhora.add(a, arrayhora);
                        }

                    } else {
                        ArrayList<String> arrayhora = new ArrayList<String>();
                        arrayhora.add(0, "Sem Linha");
                        arrayDearrayhora.add(a, arrayhora);

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return (arrayDearrayhora);

    }

    @Override
    protected void onProgressUpdate(Void... params) {
    }

    @Override
    protected void onPostExecute(ArrayList<ArrayList<String>> text) {
        progress.setMessage("Fim");
        ti.depoisDownload(text);
        progress.dismiss();
    }

    public static StringBuilder inputStreamToString(InputStream is) {
        String rLine = "";
        StringBuilder answer = new StringBuilder();
        BufferedReader rd = new BufferedReader(new InputStreamReader(is));

        try {
            while ((rLine = rd.readLine()) != null) {
                answer.append(rLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return answer;
    }
}
