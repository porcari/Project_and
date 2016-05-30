package fatec.br_bus.Controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import fatec.br_bus.Model.DAO.DAO_Agenda;
import fatec.br_bus.Model.DAO.DAO_Linha;
import fatec.br_bus.Model.DAO.DAO_Ponto;
import fatec.br_bus.Model.Object.Agenda;
import fatec.br_bus.Model.Object.Linha;
import fatec.br_bus.Model.Object.Ponto;
import fatec.br_bus.R;


public class CadastroActivity extends Activity {
    Linha linha = new Linha("", "", "", 0, 0, "");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        linha = null;
        ConsultarLinhasCadastradasActivity.VarApp.agenda = null;
        ConsultarLinhasCadastradasActivity.VarApp.ponto = null;
        if (ConsultarLinhasCadastradasActivity.VarApp.inhaPadrao != null) {
            TextView input_linhaBase = (TextView) findViewById(R.id.input_linha);
            input_linhaBase.setText(ConsultarLinhasCadastradasActivity.VarApp.inhaPadrao.getCodigo());
            Button btnPesq = (Button) findViewById(R.id.btn_pesquisar);
            btnPesq.callOnClick();
        }
        chekboxs();
    }

    @Override
    public void onResume() {
        super.onResume();
        chekboxs();
    }

    public void ClickTextViewPesq(View view) {
        TextView lb_linha = (TextView) findViewById(R.id.lb_LinhaPesq);
        CheckBox checkBoxBairro = (CheckBox) findViewById(R.id.checkBoxBairro);
        CheckBox checkBoxTerminal = (CheckBox) findViewById(R.id.checkBoxTerm);
        Button btnConfigHora = (Button) findViewById(R.id.btn_configuracaoHorario);
        Button btnConfigPonto = (Button) findViewById(R.id.btn_pontoOnibus);
        Button btnSalvar = (Button) findViewById(R.id.btn_salvarCadastro);
        lb_linha.setText("");
        checkBoxTerminal.setChecked(false);
        checkBoxBairro.setChecked(false);
        checkBoxTerminal.setEnabled(false);
        checkBoxBairro.setEnabled(false);
        btnConfigHora.setEnabled(false);
        btnConfigPonto.setEnabled(false);
        btnSalvar.setEnabled(false);
        ConsultarLinhasCadastradasActivity.VarApp.agenda = null;
        ConsultarLinhasCadastradasActivity.VarApp.ponto = null;
        linha = null;
        chekboxs();
    }

    public void ClickbtPesquisar(View view) {
        //crio objeto DAO_LINHA e DAO_AGENDA
        DAO_Linha dao_linha = new DAO_Linha(getBaseContext());
        DAO_Agenda dao_agenda = new DAO_Agenda(getBaseContext());
        DAO_Ponto dao_ponto = new DAO_Ponto(getBaseContext());
        //Carrego
        ConsultarLinhasCadastradasActivity.VarApp.agenda = null;
        ConsultarLinhasCadastradasActivity.VarApp.ponto = null;
        TextView lb_linhaInput = (TextView) findViewById(R.id.input_linha);
        //busco a linha pelo codigo
        if (ConsultarLinhasCadastradasActivity.VarApp.inhaPadrao != null) {
            linha = ConsultarLinhasCadastradasActivity.VarApp.inhaPadrao;
        } else {
            linha = dao_linha.buscarCodigo(lb_linhaInput.getText().toString());
        }

        //verifico se alinha ja esta cadastrada
        if (linha == null) {
            //busco a linha e agenda nos dados base
            linha = dao_linha.buscarBaseCod(lb_linhaInput.getText().toString());
            if (!(linha == null)) {
                ConsultarLinhasCadastradasActivity.VarApp.agenda = dao_agenda.buscarBaseCodTerm(linha.getCodigo(), linha.getTerminal());
            }
        } else {
            //busco a agenda pelo id
            ConsultarLinhasCadastradasActivity.VarApp.agenda = dao_agenda.buscarId(linha.getId_agenda());
            //busco o ponto pelo id
            ConsultarLinhasCadastradasActivity.VarApp.ponto = dao_ponto.buscarId(linha.getId_ponto());
        }
        //carrego dados da tela
        parametrostela(linha);
        //fecha teclado
        ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE))
                .hideSoftInputFromWindow(lb_linhaInput.getWindowToken(), 0);
    }

    public void ClickbtCkeqBairro(View view) {
        //crio objeto DAO_LINHA e DAO_AGENDA
        DAO_Linha dao_linha = new DAO_Linha(getBaseContext());
        DAO_Agenda dao_agenda = new DAO_Agenda(getBaseContext());
        DAO_Ponto dao_ponto = new DAO_Ponto(getBaseContext());
        //Carrego
        ConsultarLinhasCadastradasActivity.VarApp.agenda = null;
        ConsultarLinhasCadastradasActivity.VarApp.ponto = null;
        String codigo = linha.getCodigo();
        TextView lb_linhaInput = (TextView) findViewById(R.id.input_linha);
        //busco a linha pelo codigo e bairro
        linha = dao_linha.buscarCodTerm(codigo, "1");
        if (linha == null) {
            //busco a linha e agenda nos dados base
            linha = dao_linha.buscarbasecodterm(codigo, "1");
            if (!(linha == null)) {
                ConsultarLinhasCadastradasActivity.VarApp.agenda = dao_agenda.buscarBaseCodTerm(linha.getCodigo(), linha.getTerminal());
            }
        } else {
            //busco a agenda pelo id
            ConsultarLinhasCadastradasActivity.VarApp.agenda = dao_agenda.buscarId(linha.getId_agenda());
            //busco o ponto pelo id
            ConsultarLinhasCadastradasActivity.VarApp.ponto = dao_ponto.buscarId(linha.getId_ponto());
        }
        //carrego dados da tela
        parametrostela(linha);
    }

    public void ClickbtCkeqTerm(View view) {
        //crio objeto DAO_LINHA e DAO_AGENDA
        DAO_Linha dao_linha = new DAO_Linha(getBaseContext());
        DAO_Agenda dao_agenda = new DAO_Agenda(getBaseContext());
        DAO_Ponto dao_ponto = new DAO_Ponto(getBaseContext());
        //Carrego
        ConsultarLinhasCadastradasActivity.VarApp.agenda = null;
        ConsultarLinhasCadastradasActivity.VarApp.ponto = null;
        String codigo = linha.getCodigo();
        TextView lb_linhaInput = (TextView) findViewById(R.id.input_linha);
        //busco a linha pelo codigo e terminal
        linha = dao_linha.buscarCodTerm(codigo, "0");
        if (linha == null) {
            //busco a linha e agenda nos dados base
            linha = dao_linha.buscarbasecodterm(codigo, "0");
            if (!(linha == null)) {
                ConsultarLinhasCadastradasActivity.VarApp.agenda = dao_agenda.buscarBaseCodTerm(linha.getCodigo(), linha.getTerminal());
            }
        } else {
            //busco a agenda pelo id
            ConsultarLinhasCadastradasActivity.VarApp.agenda = dao_agenda.buscarId(linha.getId_agenda());
            //busco o ponto pelo id
            ConsultarLinhasCadastradasActivity.VarApp.ponto = dao_ponto.buscarId(linha.getId_ponto());
        }
        //carrego dados da tela
        parametrostela(linha);
    }


    public void parametrostela(Linha linha) {
        TextView lb_linha = (TextView) findViewById(R.id.lb_LinhaPesq);
        CheckBox checkBoxBairro = (CheckBox) findViewById(R.id.checkBoxBairro);
        CheckBox checkBoxTerminal = (CheckBox) findViewById(R.id.checkBoxTerm);
        Button btnConfigHora = (Button) findViewById(R.id.btn_configuracaoHorario);
        Button btnConfigPonto = (Button) findViewById(R.id.btn_pontoOnibus);
        Button btnSalvar = (Button) findViewById(R.id.btn_salvarCadastro);
        if (linha == null) {
            alerta("Linha não encontrada");
            lb_linha.setText("");
            checkBoxTerminal.setChecked(false);
            checkBoxBairro.setChecked(false);
            checkBoxTerminal.setEnabled(false);
            checkBoxBairro.setEnabled(false);
            btnConfigHora.setEnabled(false);
            btnConfigPonto.setEnabled(false);
            btnSalvar.setEnabled(false);
            ConsultarLinhasCadastradasActivity.VarApp.agenda = null;
            ConsultarLinhasCadastradasActivity.VarApp.ponto = null;
            chekboxs();
        } else {
            lb_linha.setText(linha.getNome());
            btnSalvar.setEnabled(false);
            chekboxs();
            if (linha.getTerminal().equals("1")) {
                checkBoxTerminal.setChecked(false);
                checkBoxBairro.setChecked(true);
                checkBoxTerminal.setEnabled(true);
                checkBoxBairro.setEnabled(true);
                btnConfigHora.setEnabled(true);
                btnConfigPonto.setEnabled(true);
            } else {
                checkBoxTerminal.setChecked(true);
                checkBoxBairro.setChecked(false);
                checkBoxTerminal.setEnabled(true);
                checkBoxBairro.setEnabled(true);
                btnConfigHora.setEnabled(true);
                btnConfigPonto.setEnabled(true);
            }
        }
    }

    public void ClickbtnConfiguracaoHorario(View view) {
        Intent novaChamada = new Intent(CadastroActivity.this, CadastroHorarioActivity.class);
        startActivity(novaChamada);
    }

    public void ClickbtnConfiguracaoPonto(View view) {
        DAO_Ponto dao_ponto = new DAO_Ponto(getBaseContext());
        dao_ponto.CarregaPontos(linha.getCodigo(), linha.getTerminal());
        Intent telaPonto = new Intent(CadastroActivity.this, CadastroPontoActivity.class);
        startActivity(telaPonto);

    }

    public void ClickbtnSalvar(View view) {
        long id_incluirLin;
        long id_incluirAg;
        long id_incluirPon;
        Linha linhaAux = null;
        Agenda agendaAux = null;
        Ponto pontoAux = null;

        DAO_Linha dao_linha = new DAO_Linha(getBaseContext());
        DAO_Agenda dao_agenda = new DAO_Agenda(getBaseContext());
        DAO_Ponto dao_ponto = new DAO_Ponto(getBaseContext());

        agendaAux = dao_agenda.buscarId(linha.getId_agenda());
        if (agendaAux == null) {
            id_incluirAg = dao_agenda.incluir(ConsultarLinhasCadastradasActivity.VarApp.agenda);
            linha.setId_agenda(Integer.valueOf(String.valueOf((id_incluirAg))));
        } else {
            id_incluirAg = dao_agenda.editar(ConsultarLinhasCadastradasActivity.VarApp.agenda, linha.getId_agenda());
        }


        pontoAux = dao_ponto.buscarId(linha.getId_ponto());
        if (pontoAux == null) {
            id_incluirPon = dao_ponto.incluir(ConsultarLinhasCadastradasActivity.VarApp.ponto);
            linha.setId_ponto(Integer.valueOf(String.valueOf((id_incluirPon))));
        } else {
            id_incluirPon = dao_ponto.editar(ConsultarLinhasCadastradasActivity.VarApp.ponto);
        }

        linhaAux = dao_linha.buscarCodTerm(linha.getCodigo(), linha.getTerminal());
        if (linhaAux == null) {
            id_incluirLin = dao_linha.incluir(linha);
            alerta("Salvo com Sucesso");
        } else {
            id_incluirLin = dao_linha.editar(linha);
            alerta("Atualizado com Sucesso");
        }
        linha = null;
        ConsultarLinhasCadastradasActivity.VarApp.agenda = null;
        ConsultarLinhasCadastradasActivity.VarApp.ponto = null;
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

    private static String pad(int c) {
        if (c >= 10)
            return String.valueOf(c);
        else
            return "0" + String.valueOf(c);
    }

    public void chekboxs() {
        TextView textHoraIni = (TextView) findViewById(R.id.textHoraIni);
        TextView textHoraFim = (TextView) findViewById(R.id.textHoraFim);
        TextView lbmHoraini = (TextView) findViewById(R.id.lbmHoraini);
        TextView lbmHorafim = (TextView) findViewById(R.id.lbmHorafim);
        TextView lbmSemana = (TextView) findViewById(R.id.lbmSemana);
        TextView lbmPonto = (TextView) findViewById(R.id.lbmPonto);
        TextView lbmDescPonto = (TextView) findViewById(R.id.lbmDescPonto);
        LinearLayout linear = (LinearLayout) findViewById(R.id.linearLayout2);
        CheckBox check_seg = (CheckBox) findViewById(R.id.chekSeg);
        CheckBox check_ter = (CheckBox) findViewById(R.id.chekter);
        CheckBox check_qua = (CheckBox) findViewById(R.id.chekqua);
        CheckBox check_qui = (CheckBox) findViewById(R.id.chekqui);
        CheckBox check_sex = (CheckBox) findViewById(R.id.cheksex);
        CheckBox check_sab = (CheckBox) findViewById(R.id.cheksab);
        CheckBox check_dom = (CheckBox) findViewById(R.id.chekdom);
        if (ConsultarLinhasCadastradasActivity.VarApp.agenda == null) {
            check_seg.setVisibility(View.INVISIBLE);
            check_ter.setVisibility(View.INVISIBLE);
            check_qua.setVisibility(View.INVISIBLE);
            check_qui.setVisibility(View.INVISIBLE);
            check_sex.setVisibility(View.INVISIBLE);
            check_sab.setVisibility(View.INVISIBLE);
            check_dom.setVisibility(View.INVISIBLE);
            textHoraIni.setVisibility(View.INVISIBLE);
            textHoraFim.setVisibility(View.INVISIBLE);
            linear.setVisibility(View.INVISIBLE);
            lbmHoraini.setVisibility(View.INVISIBLE);
            lbmHorafim.setVisibility(View.INVISIBLE);
            lbmSemana.setVisibility(View.INVISIBLE);
            lbmPonto.setVisibility(View.INVISIBLE);
            lbmDescPonto.setVisibility(View.INVISIBLE);
        } else {
            linear.setVisibility(View.VISIBLE);
            check_seg.setVisibility(View.VISIBLE);
            check_ter.setVisibility(View.VISIBLE);
            check_qua.setVisibility(View.VISIBLE);
            check_qui.setVisibility(View.VISIBLE);
            check_sex.setVisibility(View.VISIBLE);
            check_sab.setVisibility(View.VISIBLE);
            check_dom.setVisibility(View.VISIBLE);
            textHoraIni.setVisibility(View.VISIBLE);
            textHoraFim.setVisibility(View.VISIBLE);
            lbmHoraini.setVisibility(View.VISIBLE);
            lbmHorafim.setVisibility(View.VISIBLE);
            lbmSemana.setVisibility(View.VISIBLE);
            check_seg.setChecked((ConsultarLinhasCadastradasActivity.VarApp.agenda.getSegunda().equals("1")));
            check_ter.setChecked((ConsultarLinhasCadastradasActivity.VarApp.agenda.getTerca().equals("1")));
            check_qua.setChecked((ConsultarLinhasCadastradasActivity.VarApp.agenda.getQuarta().equals("1")));
            check_qui.setChecked((ConsultarLinhasCadastradasActivity.VarApp.agenda.getQuinta().equals("1")));
            check_sex.setChecked((ConsultarLinhasCadastradasActivity.VarApp.agenda.getSexta().equals("1")));
            check_sab.setChecked((ConsultarLinhasCadastradasActivity.VarApp.agenda.getSabado().equals("1")));
            check_dom.setChecked((ConsultarLinhasCadastradasActivity.VarApp.agenda.getDomingo().equals("1")));
            textHoraIni.setText(
                    new StringBuilder()
                            .append(pad(Integer.valueOf(ConsultarLinhasCadastradasActivity.VarApp.agenda.getHora_Ini()))).append(":")
                            .append(pad(Integer.valueOf(ConsultarLinhasCadastradasActivity.VarApp.agenda.getMin_Ini()))));
            textHoraFim.setText(
                    new StringBuilder()
                            .append(pad(Integer.valueOf(ConsultarLinhasCadastradasActivity.VarApp.agenda.getHora_Fim()))).append(":")
                            .append(pad(Integer.valueOf(ConsultarLinhasCadastradasActivity.VarApp.agenda.getMin_Fim()))));

            if (!(ConsultarLinhasCadastradasActivity.VarApp.ponto == null)) {
                Button btnSalvar = (Button) findViewById(R.id.btn_salvarCadastro);
                btnSalvar.setEnabled(true);
                lbmPonto.setVisibility(View.VISIBLE);
                lbmDescPonto.setVisibility(View.VISIBLE);
                lbmDescPonto.setText(ConsultarLinhasCadastradasActivity.VarApp.ponto.getCodigo() + " - " +
                        ConsultarLinhasCadastradasActivity.VarApp.ponto.getNome());
            } else {
                Button btnSalvar = (Button) findViewById(R.id.btn_salvarCadastro);
                btnSalvar.setEnabled(false);
                lbmPonto.setVisibility(View.INVISIBLE);
                lbmDescPonto.setVisibility(View.INVISIBLE);
                lbmDescPonto.setText("");
            }
        }


    }
}
