package uts.bagas.task;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class TambahActivity extends AppCompatActivity {

    private EditText editTextNamaAgenda;
    private EditText editTextDeskripsi;
    private Spinner spinnerStatus;
    private Button buttonSimpan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);

        // Inisialisasi komponen UI
        editTextNamaAgenda = findViewById(R.id.editTextNamaAgenda);
        editTextDeskripsi = findViewById(R.id.editTextDeskripsi);
        spinnerStatus = findViewById(R.id.spinnerStatus);
        buttonSimpan = findViewById(R.id.buttonSimpan);

        // Setup Spinner untuk Status
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.status_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerStatus.setAdapter(adapter);

        // Set listener untuk tombol Simpan
        buttonSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String namaAgenda = editTextNamaAgenda.getText().toString();
                String deskripsi = editTextDeskripsi.getText().toString();
                String status = spinnerStatus.getSelectedItem().toString();

                // Tampilkan pesan
                Toast.makeText(TambahActivity.this, "Agenda Disimpan:\n" +
                                "Nama: " + namaAgenda + "\nDeskripsi: " + deskripsi + "\nStatus: " + status,
                        Toast.LENGTH_LONG).show();

                // Mengembalikan data ke HomeActivity
                Intent returnIntent = new Intent();
                returnIntent.putExtra("nama", namaAgenda);
                returnIntent.putExtra("deskripsi", deskripsi);
                returnIntent.putExtra("status", status);
                setResult(RESULT_OK, returnIntent);
                finish(); // Tutup TambahActivity
            }
        });
    }
}
