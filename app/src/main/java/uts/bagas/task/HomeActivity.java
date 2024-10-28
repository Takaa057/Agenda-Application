package uts.bagas.task;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    private ListView lvTransactions;
    private FloatingActionButton fabAddTransaction;
    private ArrayList<Agenda> agendaList; // Daftar untuk menyimpan agenda
    private AgendaAdapter adapter;
    private AgendaRepository agendaRepository; // Tambahkan ini

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        lvTransactions = findViewById(R.id.lvTransactions);
        fabAddTransaction = findViewById(R.id.fabAddTransaction);
        agendaList = new ArrayList<>(); // Inisialisasi daftar agenda

        // Inisialisasi AgendaRepository
        agendaRepository = new AgendaRepository(this);

        // Ambil semua agenda dari repository
        agendaList.addAll(agendaRepository.getAllAgendas());

        // Adapter untuk ListView
        adapter = new AgendaAdapter(this, agendaList);
        lvTransactions.setAdapter(adapter);

        // Set listener untuk FloatingActionButton
        fabAddTransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigasi ke TambahActivity
                Intent intent = new Intent(HomeActivity.this, TambahActivity.class);
                startActivityForResult(intent, 1); // Menggunakan startActivityForResult
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            // Ambil data dari Intent
            String nama = data.getStringExtra("nama");
            String deskripsi = data.getStringExtra("deskripsi");
            String status = data.getStringExtra("status");

            // Buat agenda baru
            Agenda agenda = new Agenda(nama, deskripsi, status);
            agendaList.add(agenda);

            // Simpan agenda ke repository
            agendaRepository.addAgenda(agenda);

            // Update ListView
            adapter.notifyDataSetChanged(); // Mengupdate adapter
        }
    }
}
