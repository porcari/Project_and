package fatec.br_bus.Model.DAO;

import android.database.sqlite.SQLiteDatabase;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DAO_Script_paradas {
    public void script_base_parada(SQLiteDatabase db) {
        try {
            FileReader arq = new FileReader("/sdcard/Download/paradas.txt");
            BufferedReader lerArq = new BufferedReader(arq);
            String linha = lerArq.readLine();
            while (linha != null) {
                try {
                    db.execSQL(linha);
                }finally {
                    linha=linha;
                }

                linha = lerArq.readLine();
            }
            arq.close();
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
        }
    }

}
