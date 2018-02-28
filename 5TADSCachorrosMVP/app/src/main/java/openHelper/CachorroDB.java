package openHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import controle.Cachorro;

/**
 * Created by rodrigo on 06/11/17.
 */

public class CachorroDB extends SQLiteOpenHelper {
    private static String TAG = "banco_cachorro.db";
    private static final String NOME_BD = "cachorros.db";
    private static final int VERSAO = 1;
    private static  CachorroDB cachorroDB = null;

    public CachorroDB (Context context){
        super(context, NOME_BD,null,VERSAO);

    }
    public static CachorroDB getInstance (Context context){
        if (cachorroDB == null){
            cachorroDB = new CachorroDB(context);
            return cachorroDB;
        }else {return cachorroDB;}
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS cachorro"+"(_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nome TEXT, raca TEXT, sexo TEXT, foto BLOB);";
        Log.d(TAG,"Criando a tabela, aguarde ...");
        db.execSQL(sql);
        Log.d(TAG, "Tabela criada");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        Log.d("Teste","Upgrade da Versão "+i+" para "+i1+", destruindo tudo.");
        db.execSQL("DROP TABLE IF EXISTS cachorro");
        onCreate(db);
        Log.i("Teste","Executou o script de upgrade.");
    }
    //Insere um novo cachorro ou já atualiza
    public long save(Cachorro cachorro) {
        SQLiteDatabase db = getWritableDatabase();
        try {
            ContentValues values = new ContentValues();
            values.put("nome", cachorro.nome);
            values.put("sexo", cachorro.sexo);
            values.put("raca", cachorro.raca);

            if (cachorro._id == null) {
                return db.insert("cachorro", null, values);
            } else {
                values.put("_id", cachorro._id);
                return db.update("cachorro", values, "_id=" + cachorro._id, null);
            }
        } finally {
            db.close();
        }
    }
    public long delete(Cachorro cachorro) {
        SQLiteDatabase db = getWritableDatabase();
        try {
            return db.delete("cachorro", "_id=?", new String[]{String.valueOf(cachorro._id)});
        } finally {db.close();}
    }
    public List<Cachorro> getAll(){
        SQLiteDatabase db = getReadableDatabase();
        try{
            return toList(db.rawQuery("SELECT * FROM cachorro",null));
        }finally {
            db.close();
        }
    }

    private List<Cachorro> toList(Cursor cursor) {
        List<Cachorro> cachorros = new ArrayList<>();
        if (cursor.moveToFirst()){
            do{
                Cachorro cachorro = new Cachorro();
                cachorro._id = cursor.getLong(cursor.getColumnIndex("_id"));
                cachorro.nome = cursor.getString(cursor.getColumnIndex("nome"));
                cachorro.raca = cursor.getString(cursor.getColumnIndex("raca"));
                cachorro.sexo = cursor.getString(cursor.getColumnIndex("sexo"));
                cachorros.add(cachorro);
            }while (cursor.moveToNext());
        }
        return cachorros;
    }
}