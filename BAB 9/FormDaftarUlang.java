import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FormDaftarUlang extends JFrame implements ActionListener {

    JTextField tfNama, tfTanggal, tfNoPendaftaran, tfNoTelp, tfEmail;
    JTextArea taAlamat;
    JButton btnSubmit;

    public FormDaftarUlang() {
        setTitle("Form Daftar Ulang Mahasiswa Baru");
        setSize(420, 480);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(new Color(200, 230, 255));

        // Judul
        JLabel title = new JLabel("FORM DAFTAR ULANG", SwingConstants.CENTER);
        title.setBounds(50, 10, 300, 30);
        title.setFont(new Font("Arial", Font.BOLD, 16));
        add(title);

        // Nama Lengkap
        JLabel lNama = new JLabel("Nama Lengkap");
        lNama.setBounds(20, 55, 130, 20);
        add(lNama);
        tfNama = new JTextField();
        tfNama.setBounds(160, 55, 210, 25);
        add(tfNama);

        // Tanggal Lahir
        JLabel lTanggal = new JLabel("Tanggal Lahir");
        lTanggal.setBounds(20, 95, 130, 20);
        add(lTanggal);
        tfTanggal = new JTextField();
        tfTanggal.setBounds(160, 95, 210, 25);
        tfTanggal.setToolTipText("Contoh: 3 Juli 1996");
        add(tfTanggal);

        // Nomor Pendaftaran
        JLabel lNoPendaftaran = new JLabel("No. Pendaftaran");
        lNoPendaftaran.setBounds(20, 135, 130, 20);
        add(lNoPendaftaran);
        tfNoPendaftaran = new JTextField();
        tfNoPendaftaran.setBounds(160, 135, 210, 25);
        add(tfNoPendaftaran);

        // No. Telp
        JLabel lNoTelp = new JLabel("No. Telp");
        lNoTelp.setBounds(20, 175, 130, 20);
        add(lNoTelp);
        tfNoTelp = new JTextField();
        tfNoTelp.setBounds(160, 175, 210, 25);
        add(tfNoTelp);

        // Alamat
        JLabel lAlamat = new JLabel("Alamat");
        lAlamat.setBounds(20, 215, 130, 20);
        add(lAlamat);
        taAlamat = new JTextArea();
        JScrollPane sp = new JScrollPane(taAlamat);
        sp.setBounds(160, 215, 210, 70);
        add(sp);

        // Email
        JLabel lEmail = new JLabel("E-mail");
        lEmail.setBounds(20, 300, 130, 20);
        add(lEmail);
        tfEmail = new JTextField();
        tfEmail.setBounds(160, 300, 210, 25);
        add(tfEmail);

        // Tombol Submit
        btnSubmit = new JButton("Submit");
        btnSubmit.setBounds(150, 360, 110, 32);
        btnSubmit.setBackground(new Color(70, 130, 200));
        btnSubmit.setForeground(Color.WHITE);
        btnSubmit.setFont(new Font("Arial", Font.BOLD, 12));
        btnSubmit.addActionListener(this);
        add(btnSubmit);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String nama          = tfNama.getText().trim();
        String tanggal       = tfTanggal.getText().trim();
        String noPendaftaran = tfNoPendaftaran.getText().trim();
        String noTelp        = tfNoTelp.getText().trim();
        String alamat        = taAlamat.getText().trim();
        String email         = tfEmail.getText().trim();

        if (nama.isEmpty() || tanggal.isEmpty() || noPendaftaran.isEmpty()
                || noTelp.isEmpty() || alamat.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Semua kolom harus diisi!",
                    "Peringatan",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!email.contains("@") || !email.contains(".")) {
            JOptionPane.showMessageDialog(this,
                    "Format e-mail tidak valid!\nContoh: nama@email.com",
                    "Peringatan",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Validasi no telp hanya angka
        if (!noTelp.matches("[0-9]+")) {
            JOptionPane.showMessageDialog(this,
                    "No. Telp hanya boleh berisi angka!",
                    "Peringatan",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Konfirmasi
        int konfirmasi = JOptionPane.showConfirmDialog(this,
                "Apakah data yang Anda isi sudah benar?",
                "Konfirmasi",
                JOptionPane.OK_CANCEL_OPTION);

        if (konfirmasi == JOptionPane.OK_OPTION) {
            new HasilData(nama, tanggal, noPendaftaran, noTelp, alamat, email);
        }
    }

    public static void main(String[] args) {
        new FormDaftarUlang().setVisible(true);
    }
}
