package fatec.br_bus.Model.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import fatec.br_bus.Model.Object.Agenda;

public class DAO_Agenda {
    private SQLiteDatabase db;
    private DAO_BD banco;

    public DAO_Agenda(Context context) {
        banco = new DAO_BD(context);
    }


    public Agenda buscarId(int id) {
        Cursor cursor = banco.getWritableDatabase().query("agenda",
                new String[]{"age_horIni", "age_horFim", "age_minIni", "age_minFim", "age_segunda", "age_terca", "age_quarta", "age_quinta", "age_sexta", "age_sabado", "age_domingo"},
                "age_id = ?",
                new String[]{String.valueOf(id)},
                null,
                null,
                null);

        Agenda retorno = null;
        if (cursor.moveToFirst()) {
            retorno = new Agenda(cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6),
                    cursor.getString(7),
                    cursor.getString(8),
                    cursor.getString(9),
                    cursor.getString(10));
        }
        return retorno;
    }

    public Agenda buscarBaseCodTerm(String codigo, String term) {
        Cursor cursor = banco.getWritableDatabase().query("base_linhas",
                new String[]{"bli_segunda", "bli_terca", "bli_quarta", "bli_quinta", "bli_sexta", "bli_sabado", "bli_domingo"},
                " bli_codigo = ? and bli_terminal = ? ",
                new String[]{codigo, term},
                null,
                null,
                null);

        Agenda retorno = null;
        if (cursor.moveToFirst()) {
            retorno = new Agenda("00",
                    "00",
                    "00",
                    "00",
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6));
        }
        return retorno;
    }



    public int excluir(int id) {
        int total = banco.getWritableDatabase().delete("agenda",
                "age_id = ?", new String[]{Integer.toString(id)});

        return total;
    }

    public long editar(Agenda agenda, int id) {
        ContentValues valores = new ContentValues();

        valores.put("age_horIni", agenda.getHora_Ini());
        valores.put("age_horFim", agenda.getHora_Fim());
        valores.put("age_minIni", agenda.getMin_Ini());
        valores.put("age_minFim", agenda.getMin_Fim());
        valores.put("age_segunda", agenda.getSegunda());
        valores.put("age_terca", agenda.getTerca());
        valores.put("age_quarta", agenda.getQuarta());
        valores.put("age_quinta", agenda.getQuinta());
        valores.put("age_sexta", agenda.getSexta());
        valores.put("age_sabado", agenda.getSabado());
        valores.put("age_domingo", agenda.getDomingo());
        valores.put("age_id", id);

        int total = banco.getWritableDatabase().update("agenda", valores,
                "age_id = ?", new String[]{Integer.toString(id)});

        return total;
    }

    public long incluir(Agenda agenda) {
        ContentValues valores = new ContentValues();

        valores.put("age_horIni", agenda.getHora_Ini());
        valores.put("age_horFim", agenda.getHora_Fim());
        valores.put("age_minIni", agenda.getMin_Ini());
        valores.put("age_minFim", agenda.getMin_Fim());
        valores.put("age_segunda", agenda.getSegunda());
        valores.put("age_terca", agenda.getTerca());
        valores.put("age_quarta", agenda.getQuarta());
        valores.put("age_quinta", agenda.getQuinta());
        valores.put("age_sexta", agenda.getSexta());
        valores.put("age_sabado", agenda.getSabado());
        valores.put("age_domingo", agenda.getDomingo());

        long id = banco.getWritableDatabase().insert("agenda", null, valores);

        return id;
    }
}