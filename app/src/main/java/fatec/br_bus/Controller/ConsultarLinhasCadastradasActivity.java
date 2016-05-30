package fatec.br_bus.Controller;

import android.app.Activity;
import android.app.Application;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import fatec.br_bus.Model.DAO.DAO_Linha;
import fatec.br_bus.Model.Object.Agenda;
import fatec.br_bus.Model.Object.Linha;
import fatec.br_bus.Model.Object.Ponto;
import fatec.br_bus.Model.Object.Tarefa;
import fatec.br_bus.R;


public class ConsultarLinhasCadastradasActivity extends Activity implements TarefaInterface {

    SectionsPagerAdapter mSectionsPagerAdapter;
    ViewPager mViewPager;

    int count;
    public static int page;
    public static int contador;
    public static ArrayList<Linha> arraylinha = null;
    public static ArrayList<ArrayList<String>> arrayDearrayHoras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = 0;
        contador = 0;
        setContentView(R.layout.activity_consultar_linhas_cadastradas);
        DAO_Linha dao_linha = new DAO_Linha(getBaseContext());
        arraylinha = dao_linha.buscarall();
        count = arraylinha.size();
        if (count == 0) {
            count = 1;
        }
        mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        DAO_Linha dao_linha = new DAO_Linha(getBaseContext());
        arraylinha = dao_linha.buscarall();
        count = arraylinha.size();
        if (count == 0) {
            count = 1;
        }

        mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        if (arraylinha.size() > 0) {
               Tarefa tarefa = new Tarefa(this, this);
              tarefa.execute(arraylinha);
        }
    }


    @Override
    public void depoisDownload(ArrayList<ArrayList<String>> horas) {

        TextView linha = (TextView) findViewById(R.id.lb_linha);
        TextView linha_info = (TextView) findViewById(R.id.lb_linhaInfo);
        TextView linha_status = (TextView) findViewById(R.id.lb_linhaStatus);
        if (arraylinha.size() == 0) {
            linha.setText("Não existe linha cadastrada");
            linha_info.setText("--");
            linha_status.setText("--STATUS--");
        } else {
            if ((horas == null) || (horas.get(0).size() == 0)) {
                linha.setText(arraylinha.get(0).getCodigo() + " - " + arraylinha.get(0).getNome());
                linha_info.setText("Nenhum ônibus no trajeto");
                linha_status.setText("--STATUS--");
            } else {
                arrayDearrayHoras = horas;
                linha.setText(arraylinha.get(0).getCodigo() + " - " + arraylinha.get(0).getNome());
                linha_info.setText("Previsão de chegada: " + horas.get(0).get(0).toString());
                linha_status.setText("No Horário");
            }
        }
    }


    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return Page0.newInstance(position + 1);
                case 1:
                    return Page1.newInstance(position + 1);
                case 2:
                    return Page2.newInstance(position + 1);
                case 3:
                    return Page3.newInstance(position + 1);
                case 4:
                    return Page4.newInstance(position + 1);
                case 5:
                    return Page5.newInstance(position + 1);
                case 6:
                    return Page6.newInstance(position + 1);
                case 7:
                    return Page7.newInstance(position + 1);
                case 8:
                    return Page8.newInstance(position + 1);
                case 9:
                    return Page9.newInstance(position + 1);

            }
            return null;

        }

        @Override
        public int getCount() {
            contador = contador + 1;
            if (contador > 80) {
                if (page > 7) {
                    page = 9;
                } else {
                    page = 0;
                }
            }
            return count;
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class Page0 extends Fragment {
        private static final String ARG_SECTION_NUMBER = "section_number";

        public static Page0 newInstance(int sectionNumber) {
            Page0 fragment = new Page0();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_consultar_linhas_cadastradas, container, false);
            TextView linha = (TextView) rootView.findViewById(R.id.lb_linha);
            TextView linha_info = (TextView) rootView.findViewById(R.id.lb_linhaInfo);
            TextView linha_status = (TextView) rootView.findViewById(R.id.lb_linhaStatus);
            if (arraylinha.size() == 0) {
                linha.setText("sem linha cadastrada");
            } else {
                carregaTela(rootView, 0);
            }
            return rootView;
        }

        @Override
        public void onStart() {
            if (page > 0) {
                page = 1;
            } else {
                page = 0;
            }
            contador = 0;
            super.onResume();
        }

    }

    public static class Page1 extends Fragment {
        private static final String ARG_SECTION_NUMBER = "section_number";

        public static Page1 newInstance(int sectionNumber) {
            Page1 fragment = new Page1();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_consultar_linhas_cadastradas, container, false);
            carregaTela(rootView, 1);
            return rootView;
        }

        @Override
        public void onStart() {
            if (page > 1) {
                page = 2;
            } else {
                page = 0;
            }
            contador = 0;
            super.onResume();
        }
    }

    public static class Page2 extends Fragment {
        private static final String ARG_SECTION_NUMBER = "section_number";

        public static Page2 newInstance(int sectionNumber) {
            Page2 fragment = new Page2();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_consultar_linhas_cadastradas, container, false);
            carregaTela(rootView, 2);
            return rootView;
        }

        @Override
        public void onStart() {
            if (page > 2) {
                page = 3;
            } else {
                page = 1;
            }
            contador = 0;
            super.onResume();
        }
    }

    public static class Page3 extends Fragment {
        private static final String ARG_SECTION_NUMBER = "section_number";

        public static Page3 newInstance(int sectionNumber) {
            Page3 fragment = new Page3();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_consultar_linhas_cadastradas, container, false);
            carregaTela(rootView, 3);
            return rootView;
        }

        @Override
        public void onStart() {
            if (page > 3) {
                page = 4;
            } else {
                page = 2;
            }
            contador = 0;
            super.onResume();
        }
    }

    public static class Page4 extends Fragment {
        private static final String ARG_SECTION_NUMBER = "section_number";

        public static Page4 newInstance(int sectionNumber) {
            Page4 fragment = new Page4();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_consultar_linhas_cadastradas, container, false);
            carregaTela(rootView, 4);
            return rootView;
        }

        @Override
        public void onStart() {
            if (page > 4) {
                page = 5;
            } else {
                page = 3;
            }
            contador = 0;
            super.onResume();
        }
    }

    public static class Page5 extends Fragment {
        private static final String ARG_SECTION_NUMBER = "section_number";

        public static Page5 newInstance(int sectionNumber) {
            Page5 fragment = new Page5();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_consultar_linhas_cadastradas, container, false);
            carregaTela(rootView, 5);
            return rootView;
        }

        @Override
        public void onResume() {
            if (page > 5) {
                page = 4;
            } else {
                page = 6;
            }
            contador = 0;
            super.onResume();
        }
    }

    public static class Page6 extends Fragment {
        private static final String ARG_SECTION_NUMBER = "section_number";

        public static Page6 newInstance(int sectionNumber) {
            Page6 fragment = new Page6();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_consultar_linhas_cadastradas, container, false);
            carregaTela(rootView, 6);
            return rootView;
        }

        @Override
        public void onResume() {
            if (page > 6) {
                page = 7;
            } else {
                page = 5;
            }
            contador = 0;
            super.onResume();
        }
    }

    public static class Page7 extends Fragment {
        private static final String ARG_SECTION_NUMBER = "section_number";

        public static Page7 newInstance(int sectionNumber) {
            Page7 fragment = new Page7();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_consultar_linhas_cadastradas, container, false);
            carregaTela(rootView, 7);
            return rootView;
        }

        @Override
        public void onResume() {
            if (page > 7) {
                page = 8;
            } else {
                page = 6;
            }
            contador = 0;
            super.onResume();
        }
    }

    public static class Page8 extends Fragment {
        private static final String ARG_SECTION_NUMBER = "section_number";

        public static Page8 newInstance(int sectionNumber) {
            Page8 fragment = new Page8();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_consultar_linhas_cadastradas, container, false);
            carregaTela(rootView, 8);
            return rootView;
        }

        @Override
        public void onResume() {
            if (page > 8) {
                page = 9;
            } else {
                page = 7;
            }
            contador = 0;
            super.onResume();
        }
    }

    public static class Page9 extends Fragment {
        private static final String ARG_SECTION_NUMBER = "section_number";

        public static Page9 newInstance(int sectionNumber) {
            Page9 fragment = new Page9();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_consultar_linhas_cadastradas, container, false);
            carregaTela(rootView, 9);

            return rootView;
        }

        @Override
        public void onResume() {
            if (page > 9) {
                page = 9;
            } else {
                page = 8;
            }
            contador = 0;
            super.onResume();
        }
    }

    private static void carregaTela(View rootView, Integer pos) {
        TextView linha = (TextView) rootView.findViewById(R.id.lb_linha);
        TextView linha_info = (TextView) rootView.findViewById(R.id.lb_linhaInfo);
        TextView linha_status = (TextView) rootView.findViewById(R.id.lb_linhaStatus);
        linha.setText(arraylinha.get(pos).getCodigo() + " - " + arraylinha.get(pos).getNome() + "\n " + String.valueOf(pos));
        if (arrayDearrayHoras != null && arrayDearrayHoras.get(pos).get(0).toString() != "") {
            linha_info.setText("Previsão de chegada: " + arrayDearrayHoras.get(pos).get(0).toString());
            linha_status.setText("No Horário");
        } else {
            linha_info.setText("Nenhum ônibus no trajeto");
            linha_status.setText("--STATUS--");
        }
    }

    public void ClickbtnCadastro(View view) {
        VarApp.inhaPadrao = null;
        Intent novaChamada = new Intent(ConsultarLinhasCadastradasActivity.this, CadastroActivity.class);
        startActivity(novaChamada);
    }

    public void ClickbtnViewDetalhes(View view) {

        VarApp.inhaPadrao = arraylinha.get(page);
        if (arrayDearrayHoras != null) {
            for (int i = 0; i < arrayDearrayHoras.get(page).size(); i++) {
                VarApp.arrayHorasPadrao.add(i, arrayDearrayHoras.get(page).get(i).toString());
            }
        }
          Intent novaChamada = new Intent(ConsultarLinhasCadastradasActivity.this, DetalhesActivity.class);
          startActivity(novaChamada);
    }


    public static class VarApp extends Application {
        public static Agenda agenda;
        public static Ponto ponto;
        public static ArrayList<String> pontosNome = new ArrayList<String>();
        public static ArrayList<String> pontosCod = new ArrayList<String>();
        public static Linha inhaPadrao = null;
        public static ArrayList<String> arrayHorasPadrao = new ArrayList<String>();;
    }


}


