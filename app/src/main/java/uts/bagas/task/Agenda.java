package uts.bagas.task;

public class Agenda {
    private String nama;
    private String deskripsi;
    private String status;

    public Agenda(String nama, String deskripsi, String status) {
        this.nama = nama;
        this.deskripsi = deskripsi;
        this.status = status;
    }

    public String getNama() {
        return nama;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public String getStatus() {
        return status;
    }
}

