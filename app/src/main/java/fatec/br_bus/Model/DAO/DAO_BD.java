package fatec.br_bus.Model.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DAO_BD extends SQLiteOpenHelper {
    private static final String NOME_BANCO = "banco_br-bus.db";
    private static final int VERSAO = 14;

    public DAO_BD(Context context) {
        super(context, NOME_BANCO, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql;
        sql = "CREATE TABLE linha ("
                + "	lin_id	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,"
                + "	lin_codigo		TEXT,"
                + "	lin_terminal	TEXT,"
                + "	lin_pon_id      INTEGER,"
                + "	lin_age_id      INTEGER"
                + ")";
        db.execSQL(sql);

        sql = "CREATE TABLE ponto ("
                + "	pon_id	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,"
                + "	pon_codigo		TEXT"
                + ")";
        db.execSQL(sql);

        sql = "CREATE TABLE agenda ("
                + "	age_id	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,"
                + "	age_horIni        TEXT,"
                + "	age_horFim        TEXT,"
                + "	age_minIni        TEXT,"
                + "	age_minFim        TEXT,"
                + "	age_segunda	      TEXT,"
                + "	age_terca         TEXT,"
                + "	age_quarta        TEXT,"
                + "	age_quinta        TEXT,"
                + "	age_sexta         TEXT,"
                + "	age_sabado        TEXT,"
                + "	age_domingo       TEXT"
                + ")";
        db.execSQL(sql);

        sql = "CREATE TABLE base_linhas ("
                + "	bli_nome		TEXT,"
                + "	bli_codigo		TEXT,"
                + "	bli_terminal	TEXT,"
                + "	bli_segunda	    TEXT,"
                + "	bli_terca       TEXT,"
                + "	bli_quarta      TEXT,"
                + "	bli_quinta      TEXT,"
                + "	bli_sexta       TEXT,"
                + "	bli_sabado      TEXT,"
                + "	bli_domingo     TEXT"
                + ")";
        db.execSQL(sql);

        sql = "CREATE TABLE base_paradas ("
                + "	bpa_codLinha    TEXT,"
                + "	bpa_terLinha	TEXT,"
                + "	bpa_codigo		TEXT"
                + ")";
        db.execSQL(sql);

        sql = "CREATE TABLE base_pontos ("
                + "	bpo_nome		TEXT,"
                + "	bpo_codigo		TEXT"
                + ")";
        db.execSQL(sql);

        DAO_Script_linhas script_linha = new DAO_Script_linhas();
        DAO_Script_paradas script_parada = new DAO_Script_paradas();
        DAO_Script_pontos script_ponto = new DAO_Script_pontos();

        script_linha.script_base_linhas(db);

        script_ponto.script_base_ponto(db);
        script_parada.script_base_parada(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS linha");
        db.execSQL("DROP TABLE IF EXISTS agenda");
        db.execSQL("DROP TABLE IF EXISTS ponto");
        db.execSQL("DROP TABLE IF EXISTS base_linhas");
        db.execSQL("DROP TABLE IF EXISTS base_paradas");
        db.execSQL("DROP TABLE IF EXISTS base_pontos");
        onCreate(db);


    }
}