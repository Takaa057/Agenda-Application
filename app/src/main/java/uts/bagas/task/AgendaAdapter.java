package uts.bagas.task;

import android.content.Context;
import android.graphics.Color; // Pastikan untuk mengimpor Color
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AgendaAdapter extends ArrayAdapter<Agenda> {

    public AgendaAdapter(Context context, ArrayList<Agenda> agendas) {
        super(context, 0, agendas);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Mendapatkan data item untuk posisi tertentu
        Agenda agenda = getItem(position);

        // Menggunakan view recycle
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_agenda, parent, false);
        }

        // Mengambil referensi ke komponen UI
        TextView textViewNama = convertView.findViewById(R.id.textViewNama);
        TextView textViewDeskripsi = convertView.findViewById(R.id.textViewDeskripsi);
        TextView textViewStatus = convertView.findViewById(R.id.textViewStatus);

        // Mengatur nilai untuk setiap TextView
        textViewNama.setText(agenda.getNama());
        textViewDeskripsi.setText(agenda.getDeskripsi());
        textViewStatus.setText(agenda.getStatus());

        // Mengatur warna teks berdasarkan status
        if ("Selesai".equals(agenda.getStatus())) {
            textViewStatus.setTextColor(Color.GREEN); // Warna hijau untuk Selesai
        } else if ("Belum Selesai".equals(agenda.getStatus())) {
            textViewStatus.setTextColor(Color.RED); // Warna merah untuk Belum Selesai
        } else {
            textViewStatus.setTextColor(Color.BLACK); // Warna hitam untuk status lainnya (opsional)
        }

        return convertView;
    }
}
