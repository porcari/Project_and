package fatec.br_bus.Controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import fatec.br_bus.Model.DAO.DAO_Agenda;
import fatec.br_bus.Model.DAO.DAO_Linha;
import fatec.br_bus.Model.DAO.DAO_Ponto;
import fatec.br_bus.R;


public class DetalhesActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);
        if (!(ConsultarLinhasCadastradasActivity.VarApp.inhaPadrao == null)) {
            TextView textlinha = (TextView) findViewById(R.id.textView2);
            textlinha.setText(ConsultarLinhasCadastradasActivity.VarApp.inhaPadrao.getCodigo() + " - " +
                    ConsultarLinhasCadastradasActivity.VarApp.inhaPadrao.getNome());
        }

          ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ConsultarLinhasCadastradasActivity.VarApp.arrayHorasPadrao);

         ListView listahoras = (ListView) findViewById(R.id.listView2);
         listahoras.setAdapter(adapter);
         listahoras.setOnItemClickListener(chamaHoras(this));
    }

    public AdapterView.OnItemClickListener chamaHoras(final Context context) {
        return (new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

    }

    public void ClickbtnExcluir(View view) {
        DAO_Linha dao_linha = new DAO_Linha(getBaseContext());
        DAO_Agenda dao_agenda = new DAO_Agenda(getBaseContext());
        DAO_Ponto dao_ponto = new DAO_Ponto(getBaseContext());

        dao_ponto.excluir(ConsultarLinhasCadastradasActivity.VarApp.inhaPadrao.getId_ponto());
        dao_agenda.excluir(ConsultarLinhasCadastradasActivity.VarApp.inhaPadrao.getId_agenda());
        dao_linha.excluir(ConsultarLinhasCadastradasActivity.VarApp.inhaPadrao);

        alerta("Excluido com Sucesso");
        super.finish();
    }

    public void ClickbtnAlterar(View view) {
        Intent novaChamada = new Intent(DetalhesActivity.this, CadastroActivity.class);
        startActivity(novaChamada);
    }

    public void ClickbtnVoltar(View view) {
        ConsultarLinhasCadastradasActivity.VarApp.arrayHorasPadrao = null;
        ConsultarLinhasCadastradasActivity.VarApp.arrayHorasPadrao = new ArrayList<String>();
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