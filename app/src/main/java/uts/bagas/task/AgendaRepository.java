package uts.bagas.task;

import android.content.Context;
import java.util.ArrayList;

public class AgendaRepository {

    private DatabaseHelper databaseHelper;

    public AgendaRepository(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }

    // Menambahkan agenda ke database
    public void addAgenda(Agenda agenda) {
        databaseHelper.addAgenda(agenda);
    }

    // Mengambil semua agenda dari database
    public ArrayList<Agenda> getAllAgendas() {
        return databaseHelper.getAllAgendas();
    }
}

