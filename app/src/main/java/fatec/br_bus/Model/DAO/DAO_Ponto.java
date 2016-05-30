package fatec.br_bus.Model.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import fatec.br_bus.Controller.ConsultarLinhasCadastradasActivity;
import fatec.br_bus.Model.Object.Ponto;

public class DAO_Ponto {
    private SQLiteDatabase db;
    private DAO_BD banco;

    public DAO_Ponto(Context context) {
        banco = new DAO_BD(context);
    }

    public Ponto buscarId(int id) {
        Cursor cursor = banco.getWritableDatabase().rawQuery(
                "select pon_id,bpo_nome,pon_codigo " +
                        "from ponto " +
                        "inner join base_pontos on pon_codigo = bpo_codigo  " +
                        "where pon_id = ? ", new String[]{String.valueOf(id)});

        Ponto retorno = null;
        if (cursor.moveToFirst()) {
            retorno = new Ponto(cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2));
        }
        return retorno;
    }

    public void CarregaPontos(String codigo, String terminal) {
        Cursor cursor = banco.getWritableDatabase().rawQuery(
                "select distinct bpo_codigo,bpo_nome " +
                        "from base_paradas " +
                        "inner join base_pontos on bpa_codigo = bpo_codigo  " +
                        "where bpa_codLinha = ? and bpa_terLinha = ?", new String[]{codigo, terminal});
        ConsultarLinhasCadastradasActivity.VarApp.pontosNome.clear();
        ConsultarLinhasCadastradasActivity.VarApp.pontosCod.clear();
        if (cursor.moveToFirst()) {
            for (int i = 0; i < cursor.getCount(); i++) {
                ConsultarLinhasCadastradasActivity.VarApp.pontosCod.add(i, cursor.getString(0));
                ConsultarLinhasCadastradasActivity.VarApp.pontosNome.add(i, cursor.getString(1));
                cursor.moveToNext();
            }
        }
    }

    public int excluir(long id) {
        int total = banco.getWritableDatabase().delete("ponto",
                "pon_id = ?", new String[]{String.valueOf(id)});

        return total;
    }

    public long editar(Ponto ponto) {
        ContentValues valores = new ContentValues();
        valores.put("pon_codigo", ponto.getCodigo());

        int id = banco.getWritableDatabase().update("ponto", valores,
                "pon_id = ?", new String[]{String.valueOf(ponto.getId())});
        return id;
    }

    public long incluir(Ponto ponto) {
        ContentValues valores = new ContentValues();

        valores.put("pon_codigo", ponto.getCodigo());

        long id = banco.getWritableDatabase().insert("ponto", null, valores);

        return id;
    }
}
