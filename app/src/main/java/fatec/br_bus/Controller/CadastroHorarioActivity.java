package fatec.br_bus.Controller;

import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import fatec.br_bus.R;


public class CadastroHorarioActivity extends Activity {
    /**
     * Private members of the class
     */
    private TextView displayTime;
    private TextView displayTime2;
    private Button pickTime;
    private Button pickTime2;
    private Button botao_voltar;

    private int pHour;
    private int pMinute;

    private int pHour2;
    private int pMinute2;

    private int botao_clicado = 0;

    /**
     * This integer will uniquely define the dialog to be used for displaying time picker.
     */
    static final int TIME_DIALOG_ID = 0;
    static final int TIME_DIALOG_ID2 = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_horario);
        /** Capture our View elements */
        displayTime = (TextView) findViewById(R.id.timeDisplay);
        displayTime2 = (TextView) findViewById(R.id.timeDisplay_2);
        pickTime = (Button) findViewById(R.id.btn_horario_inicio);
        pickTime2 = (Button) findViewById(R.id.btn_horario_fim);
        botao_voltar = (Button) findViewById(R.id.btn_voltar);


        /** Listener for click event of the button */
        pickTime.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                botao_clicado = 1;
                showDialog(TIME_DIALOG_ID);
            }
        });

        /** Listener for click event of the button */
        pickTime2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                botao_clicado = 2;
                showDialog(TIME_DIALOG_ID2);
            }
        });

        CheckBox check_seg = (CheckBox) findViewById(R.id.chk_seg);
        CheckBox check_ter = (CheckBox) findViewById(R.id.chk_ter);
        CheckBox check_qua = (CheckBox) findViewById(R.id.chk_qua);
        CheckBox check_qui = (CheckBox) findViewById(R.id.chk_qui);
        CheckBox check_sex = (CheckBox) findViewById(R.id.chk_sex);
        CheckBox check_sab = (CheckBox) findViewById(R.id.chk_sab);
        CheckBox check_dom = (CheckBox) findViewById(R.id.chk_dom);


        /** Get the current time */
        pHour = Integer.parseInt(ConsultarLinhasCadastradasActivity.VarApp.agenda.getHora_Ini());
        pHour2 = Integer.parseInt(ConsultarLinhasCadastradasActivity.VarApp.agenda.getHora_Fim());
        pMinute = Integer.parseInt(ConsultarLinhasCadastradasActivity.VarApp.agenda.getMin_Ini());
        pMinute2 = Integer.parseInt(ConsultarLinhasCadastradasActivity.VarApp.agenda.getMin_Fim());

        check_seg.setChecked((ConsultarLinhasCadastradasActivity.VarApp.agenda.getSegunda().equals("1")));
        check_seg.setEnabled((!ConsultarLinhasCadastradasActivity.VarApp.agenda.getSegunda().equals("0")));

        check_ter.setChecked((ConsultarLinhasCadastradasActivity.VarApp.agenda.getTerca().equals("1")));
        check_ter.setEnabled((!ConsultarLinhasCadastradasActivity.VarApp.agenda.getTerca().equals("0")));

        check_qua.setChecked((ConsultarLinhasCadastradasActivity.VarApp.agenda.getQuarta().equals("1")));
        check_qua.setEnabled((!ConsultarLinhasCadastradasActivity.VarApp.agenda.getQuarta().equals("0")));

        check_qui.setChecked((ConsultarLinhasCadastradasActivity.VarApp.agenda.getQuinta().equals("1")));
        check_qui.setEnabled((!ConsultarLinhasCadastradasActivity.VarApp.agenda.getQuinta().equals("0")));

        check_sex.setChecked((ConsultarLinhasCadastradasActivity.VarApp.agenda.getSexta().equals("1")));
        check_sex.setEnabled((!ConsultarLinhasCadastradasActivity.VarApp.agenda.getSexta().equals("0")));

        check_sab.setChecked((ConsultarLinhasCadastradasActivity.VarApp.agenda.getSabado().equals("1")));
        check_sab.setEnabled((!ConsultarLinhasCadastradasActivity.VarApp.agenda.getSabado().equals("0")));

        check_dom.setChecked((ConsultarLinhasCadastradasActivity.VarApp.agenda.getDomingo().equals("1")));
        check_dom.setEnabled((!ConsultarLinhasCadastradasActivity.VarApp.agenda.getDomingo().equals("0")));
        /** Display the current time in the TextView */
        updateDisplay();
        updateDisplay2();

    }

    /**
     * Add padding to numbers less than ten
     */
    private static String pad(int c) {
        if (c >= 10)
            return String.valueOf(c);
        else
            return "0" + String.valueOf(c);
    }


    /**
     * Callback received when the user "picks" a time in the dialog
     */
    TimePickerDialog.OnTimeSetListener mTimeSetListener =
            new TimePickerDialog.OnTimeSetListener() {
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    String tipo = "";
                    if (botao_clicado == 1) {
                        pHour = hourOfDay;
                        pMinute = minute;
                        if (pHour > pHour2) {
                            pHour2 = pHour;
                            pMinute2 = pMinute;
                        } else {
                            if (pHour == pHour2) {
                                if (pMinute > pMinute2) {
                                    pMinute2 = pMinute;
                                }
                            }

                        }
                        ConsultarLinhasCadastradasActivity.VarApp.agenda.setHora_Ini(String.valueOf(pHour));
                        ConsultarLinhasCadastradasActivity.VarApp.agenda.setMin_Ini(String.valueOf(pMinute));
                        tipo = "Inicio:";
                        updateDisplay();

                        ConsultarLinhasCadastradasActivity.VarApp.agenda.setHora_Fim(String.valueOf(pHour2));
                        ConsultarLinhasCadastradasActivity.VarApp.agenda.setMin_Fim(String.valueOf(pMinute2));
                        tipo = "Fim:";
                        updateDisplay2();
                    }
                    if (botao_clicado == 2) {
                        pHour2 = hourOfDay;
                        pMinute2 = minute;
                        if (pHour > pHour2) {
                            pHour2 = pHour;
                            pMinute2 = pMinute;
                            alerta("Horário final não pode ser menor");
                        } else {
                            if (pHour == pHour2) {
                                if (pMinute > pMinute2) {
                                    pMinute2 = pMinute;
                                    alerta("Horário final não pode ser menor");
                                }
                            }

                        }


                        ConsultarLinhasCadastradasActivity.VarApp.agenda.setHora_Fim(String.valueOf(pHour2));
                        ConsultarLinhasCadastradasActivity.VarApp.agenda.setMin_Fim(String.valueOf(pMinute2));
                        tipo = "Fim:";
                        updateDisplay2();

                        ConsultarLinhasCadastradasActivity.VarApp.agenda.setHora_Ini(String.valueOf(pHour));
                        ConsultarLinhasCadastradasActivity.VarApp.agenda.setMin_Ini(String.valueOf(pMinute));
                        tipo = "Inicio:";
                        updateDisplay();
                    }
                    displayToast(tipo);
                }
            };

    /**
     * Updates the time in the TextView
     */
    private void updateDisplay() {
        displayTime.setText(
                new StringBuilder()
                        .append(pad(pHour)).append("h")
                        .append(pad(pMinute)));
    }

    private void updateDisplay2() {
        displayTime2.setText(
                new StringBuilder()
                        .append(pad(pHour2)).append("h")
                        .append(pad(pMinute2)));
    }


    private void displayToast(String tipo) {
        Toast.makeText(this, new StringBuilder().append(tipo).
                append("Alterado para: ").
                append(displayTime.getText()), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case TIME_DIALOG_ID:
                return new TimePickerDialog(this, mTimeSetListener, pHour, pMinute, true);
            case TIME_DIALOG_ID2:
                return new TimePickerDialog(this, mTimeSetListener, pHour2, pMinute2, true);
        }
        return null;
    }

    public void ClickbtnSalvar(View view) {
        alerta("Salvo com Sucesso");
        super.finish();
    }

    public void ClickChkSeg(View view) {
        CheckBox check_seg = (CheckBox) findViewById(R.id.chk_seg);
        if (!validaCheck()) {
            alerta("Não é permitido inataivar todos! ");
            check_seg.setChecked(true);
        } else {
            if (check_seg.isChecked()) {
                ConsultarLinhasCadastradasActivity.VarApp.agenda.setSegunda("1");
            } else {
                ConsultarLinhasCadastradasActivity.VarApp.agenda.setSegunda("2");
            }
        }
    }

    public void ClickChkTer(View view) {
        CheckBox check_ter = (CheckBox) findViewById(R.id.chk_ter);
        if (!validaCheck()) {
            alerta("Não é permitido inataivar todos! ");
            check_ter.setChecked(true);
        } else {
            if (check_ter.isChecked()) {
                ConsultarLinhasCadastradasActivity.VarApp.agenda.setTerca("1");
            } else {
                ConsultarLinhasCadastradasActivity.VarApp.agenda.setTerca("2");
            }
        }
    }

    public void ClickChkQua(View view) {
        CheckBox check_qua = (CheckBox) findViewById(R.id.chk_qua);
        if (!validaCheck()) {
            alerta("Não é permitido inataivar todos! ");
            check_qua.setChecked(true);
        } else {
            if (check_qua.isChecked()) {
                ConsultarLinhasCadastradasActivity.VarApp.agenda.setQuarta("1");
            } else {
                ConsultarLinhasCadastradasActivity.VarApp.agenda.setQuarta("2");
            }
        }
    }

    public void ClickChkQui(View view) {
        CheckBox check_qui = (CheckBox) findViewById(R.id.chk_qui);
        if (!validaCheck()) {
            alerta("Não é permitido inataivar todos! ");
            check_qui.setChecked(true);
        } else {
            if (check_qui.isChecked()) {
                ConsultarLinhasCadastradasActivity.VarApp.agenda.setQuinta("1");
            } else {
                ConsultarLinhasCadastradasActivity.VarApp.agenda.setQuinta("2");
            }
        }
    }

    public void ClickChkSex(View view) {
        CheckBox check_sex = (CheckBox) findViewById(R.id.chk_sex);
        if (!validaCheck()) {
            alerta("Não é permitido inataivar todos! ");
            check_sex.setChecked(true);
        } else {
            if (check_sex.isChecked()) {
                ConsultarLinhasCadastradasActivity.VarApp.agenda.setSexta("1");
            } else {
                ConsultarLinhasCadastradasActivity.VarApp.agenda.setSexta("2");
            }
        }
    }

    public void ClickChkSab(View view) {
        CheckBox check_sab = (CheckBox) findViewById(R.id.chk_sab);
        if (!validaCheck()) {
            alerta("Não é permitido inataivar todos! ");
            check_sab.setChecked(true);
        } else {
            if (check_sab.isChecked()) {
                ConsultarLinhasCadastradasActivity.VarApp.agenda.setSabado("1");
            } else {
                ConsultarLinhasCadastradasActivity.VarApp.agenda.setSabado("2");
            }
        }
    }

    public void ClickChkDom(View view) {
        CheckBox check_dom = (CheckBox) findViewById(R.id.chk_dom);
        if (!validaCheck()) {
            alerta("Não é permitido inataivar todos! ");
            check_dom.setChecked(true);
        } else {
            if (check_dom.isChecked()) {
                ConsultarLinhasCadastradasActivity.VarApp.agenda.setDomingo("1");
            } else {
                ConsultarLinhasCadastradasActivity.VarApp.agenda.setDomingo("2");
            }
        }
    }

    public boolean validaCheck() {
        boolean param;
        CheckBox check_seg = (CheckBox) findViewById(R.id.chk_seg);
        CheckBox check_ter = (CheckBox) findViewById(R.id.chk_ter);
        CheckBox check_qua = (CheckBox) findViewById(R.id.chk_qua);
        CheckBox check_qui = (CheckBox) findViewById(R.id.chk_qui);
        CheckBox check_sex = (CheckBox) findViewById(R.id.chk_sex);
        CheckBox check_sab = (CheckBox) findViewById(R.id.chk_sab);
        CheckBox check_dom = (CheckBox) findViewById(R.id.chk_dom);
        param = false;
        if (!param) {
            param = check_seg.isChecked();
        }
        if (!param) {
            param = check_ter.isChecked();
        }
        if (!param) {
            param = check_qua.isChecked();
        }
        if (!param) {
            param = check_qui.isChecked();
        }
        if (!param) {
            param = check_sex.isChecked();
        }
        if (!param) {
            param = check_sab.isChecked();
        }
        if (!param) {
            param = check_dom.isChecked();
        }
        return param;
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