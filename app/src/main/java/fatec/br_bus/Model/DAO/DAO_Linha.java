package fatec.br_bus.Model.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import fatec.br_bus.Model.Object.Linha;

public class DAO_Linha {
    private SQLiteDatabase db;
    private DAO_BD banco;

    public DAO_Linha(Context context) {
        banco = new DAO_BD(context);
    }

    public ArrayList<Linha> buscarall() {

        Cursor cursor = banco.getWritableDatabase().rawQuery(
                "select bli_nome,lin_codigo, lin_terminal,lin_pon_id,lin_age_id,pon_codigo " +
                        "from linha " +
                        "inner join ponto on pon_id = lin_pon_id "+
                        "inner join base_linhas on bli_codigo = lin_codigo and bli_terminal = lin_terminal  ", new String[]{});
        ArrayList<Linha> retorno = new ArrayList<Linha>();
        Linha linha = null;
        if (cursor.moveToFirst()) {
            for (int i = 0; i < cursor.getCount(); i++) {
                linha = new Linha(cursor.getString(0),//nome
                        cursor.getString(1),//codigo
                        cursor.getString(2),//terminal
                        cursor.getInt(3),//id ponto
                        cursor.getInt(4),
                        cursor.getString(5));//id agenda
                retorno.add(i,linha);
                cursor.moveToNext();
            }

        }
        return retorno;
    }

    public Linha buscarCodigo(String codigo) {
        Cursor cursor = banco.getWritableDatabase().rawQuery(
                "select bli_nome,lin_codigo, lin_terminal,lin_pon_id,lin_age_id " +
                        "from linha " +
                        "inner join base_linhas on bli_codigo = lin_codigo and bli_terminal = lin_terminal  " +
                        "where lin_codigo = ?  ", new String[]{codigo});

        Linha retorno = null;
        if (cursor.moveToFirst()) {
            retorno = new Linha(cursor.getString(0),//nome
                    cursor.getString(1),//codigo
                    cursor.getString(2),//terminal
                    cursor.getInt(3),//id ponto
                    cursor.getInt(4),
                    "");//id agenda
        }
        return retorno;
    }

    public Linha buscarBaseCod(String codigo) {
        Cursor cursor = banco.getWritableDatabase().query("base_linhas",
                new String[]{"bli_nome", "bli_codigo", "bli_terminal"},
                "bli_codigo = ? ",
                new String[]{codigo},
                null,
                null,
                null);

        Linha retorno = null;
        if (cursor.moveToFirst()) {
            retorno = new Linha(cursor.getString(0),//nome
                    cursor.getString(1),//codigo
                    cursor.getString(2),//terminal
                    0,//id ponto
                    0,
                    "");//id agenda
        }
        return retorno;
    }


    public Linha buscarCodTerm(String codigo, String term) {
        Cursor cursor = banco.getWritableDatabase().rawQuery(
                "select bli_nome,lin_codigo, lin_terminal,lin_pon_id,lin_age_id " +
                        "from linha " +
                        "inner join base_linhas on bli_codigo = lin_codigo and bli_terminal = lin_terminal  " +
                        "where lin_codigo = ? and lin_terminal = ? ", new String[]{codigo, term});

        Linha retorno = null;
        if (cursor.moveToFirst()) {
            retorno = new Linha(cursor.getString(0),//nome
                    cursor.getString(1),//codigo
                    cursor.getString(2),//terminal
                    cursor.getInt(3),//id ponto
                    cursor.getInt(4),
                    "");//id agenda
        }
        return retorno;
    }

    public Linha buscarbasecodterm(String codigo, String term) {
        Cursor cursor = banco.getWritableDatabase().query("base_linhas",
                new String[]{"bli_nome", "bli_codigo", "bli_terminal"},
                " bli_codigo = ? and bli_terminal = ? ",
                new String[]{codigo, term},
                null,
                null,
                null);

        Linha retorno = null;
        if (cursor.moveToFirst()) {
            retorno = new Linha(cursor.getString(0),//nome
                    cursor.getString(1),//codigo
                    cursor.getString(2),//terminal
                    0,//id ponto
                    0,
                    "");//id agenda
        }
        return retorno;
    }

    public int excluir(Linha linha) {
        int total = banco.getWritableDatabase().delete("linha",
                "lin_codigo = ? and lin_terminal = ?", new String[]{linha.getCodigo(), linha.getTerminal()});

        return total;
    }

    public long editar(Linha linha) {
        ContentValues valores = new ContentValues();

        valores.put("lin_codigo", linha.getCodigo());
        valores.put("lin_terminal", linha.getTerminal());
        valores.put("lin_age_id", linha.getId_agenda());
        valores.put("lin_pon_id", linha.getId_ponto());


        int total = banco.getWritableDatabase().update("linha", valores,
                "lin_codigo = ? and lin_terminal = ? ", new String[]{linha.getCodigo(), linha.getTerminal()});

        return total;
    }

    public long incluir(Linha linha) {
        ContentValues valores = new ContentValues();

        valores.put("lin_codigo", linha.getCodigo());
        valores.put("lin_terminal", linha.getTerminal());
        valores.put("lin_age_id", linha.getId_agenda());
        valores.put("lin_pon_id", linha.getId_ponto());
        long id = banco.getWritableDatabase().insert("linha", null, valores);

        return id;
    }
}