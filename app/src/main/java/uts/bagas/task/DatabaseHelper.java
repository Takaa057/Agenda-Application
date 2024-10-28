package uts.bagas.task;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Nama database dan versi
    private static final String DATABASE_NAME = "agenda.db";
    private static final int DATABASE_VERSION = 1;

    // Nama tabel
    private static final String TABLE_AGENDA = "agenda";

    // Kolom tabel
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAMA = "nama";
    private static final String COLUMN_DESKRIPSI = "deskripsi";
    private static final String COLUMN_STATUS = "status";

    // Query untuk membuat tabel
    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_AGENDA + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NAMA + " TEXT, " +
                    COLUMN_DESKRIPSI + " TEXT, " +
                    COLUMN_STATUS + " TEXT" +
                    ");";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_AGENDA);
        onCreate(db);
    }

    // Metode untuk menambahkan agenda
    public void addAgenda(Agenda agenda) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAMA, agenda.getNama());
        values.put(COLUMN_DESKRIPSI, agenda.getDeskripsi());
        values.put(COLUMN_STATUS, agenda.getStatus());

        db.insert(TABLE_AGENDA, null, values);
        db.close();
    }

    // Metode untuk mendapatkan semua agenda
    public ArrayList<Agenda> getAllAgendas() {
        ArrayList<Agenda> agendaList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_AGENDA,
                new String[]{COLUMN_ID, COLUMN_NAMA, COLUMN_DESKRIPSI, COLUMN_STATUS},
                null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                Agenda agenda = new Agenda(cursor.getString(1), cursor.getString(2), cursor.getString(3));
                agendaList.add(agenda);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return agendaList;
    }
}

