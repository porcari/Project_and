package fatec.br_bus.Controller;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import fatec.br_bus.Model.Object.Ponto;
import fatec.br_bus.R;


public class CadastroPontoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_ponto);

        if (!(ConsultarLinhasCadastradasActivity.VarApp.ponto == null)) {
            TextView textPonto = (TextView) findViewById(R.id.lbmPonto);
            textPonto.setText(ConsultarLinhasCadastradasActivity.VarApp.ponto.getCodigo() + " - " +
                    ConsultarLinhasCadastradasActivity.VarApp.ponto.getNome());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ConsultarLinhasCadastradasActivity.VarApp.pontosNome);

        ListView listaPontos = (ListView) findViewById(R.id.listView);
        listaPontos.setAdapter(adapter);
        listaPontos.setOnItemClickListener(chamaPontos(this));
    }

    public AdapterView.OnItemClickListener chamaPontos(final Context context) {
        return (new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                TextView textPonto = (TextView) findViewById(R.id.lbmPonto);
                textPonto.setText(ConsultarLinhasCadastradasActivity.VarApp.pontosCod.get(position)+" - "+ConsultarLinhasCadastradasActivity.VarApp.pontosNome.get(position));
                if (ConsultarLinhasCadastradasActivity.VarApp.ponto == null) {
                    Ponto pontoAux = new Ponto(0,ConsultarLinhasCadastradasActivity.VarApp.pontosNome.get(position),ConsultarLinhasCadastradasActivity.VarApp.pontosCod.get(position));
                    ConsultarLinhasCadastradasActivity.VarApp.ponto = pontoAux;
                }else{
                    ConsultarLinhasCadastradasActivity.VarApp.ponto.setNome(ConsultarLinhasCadastradasActivity.VarApp.pontosNome.get(position));
                    ConsultarLinhasCadastradasActivity.VarApp.ponto.setCodigo(ConsultarLinhasCadastradasActivity.VarApp.pontosCod.get(position));
                }


            }
        });

    }

    public void ClickbtnSalvarPonto(View view) {
        alerta("Salvo com Sucesso");
        super.finish();
    }
    public void alerta(String msg) {
        LayoutInflater layoutInflater = getLayoutInflater();
        int layout = R.layout.alerta;
        ViewGroup viewGroup = (ViewGroup) findViewById(R.id.toast_layout_root);
        View view2 = layoutInflater.inflate(layout, viewGroup);
        TextView tv_texto = (TextView) view2.findViewById(R.id.texto);
        tv_texto.setText(msg);
        Toast toast = new Toast(this);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(view2);
        toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL, 0, 0);
        toast.show();
    }
}
