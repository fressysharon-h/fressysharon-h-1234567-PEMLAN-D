import java.awt.*;
import java.awt.event.*;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.swing.*;

public class NilaiMahasiswaGUI extends JFrame {

    // Radio Buttons
    private JRadioButton rbASD, rbPemlan, rbMatkomlan, rbProbstat;
    private ButtonGroup  groupMatkul;

    // Input fields
    private JTextField tfTugas, tfKuis, tfUTS, tfUAS;
    private JTextField tfHasil;

    // Buttons
    private JButton btnHitung;
    private JButton btnTampilSemua;

    // TextArea rekap
    private JTextArea taHasil;

    // Objek mata kuliah
    private ASD       asd       = new ASD();
    private Pemlan    pemlan    = new Pemlan();
    private Matkomlan matkomlan = new Matkomlan();
    private Probstat  probstat  = new Probstat();

    // Nilai akhir 
    private Map<String, Double> nilaiMap = new LinkedHashMap<>();

    public NilaiMahasiswaGUI() {
        initComponents();
        buildLayout();
        attachEvents();

        setTitle("Hitung Nilai Akhir dengan GUI...");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initComponents() {
        // Radio buttons – urutan seperti screenshot: ASD | Pemlan | Matkomlan | Probstat
        rbASD       = new JRadioButton("ASD");
        rbPemlan    = new JRadioButton("Pemlan", true);   // default terpilih
        rbMatkomlan = new JRadioButton("Matkomlan");
        rbProbstat  = new JRadioButton("Probstat");

        groupMatkul = new ButtonGroup();
        groupMatkul.add(rbASD);
        groupMatkul.add(rbPemlan);
        groupMatkul.add(rbMatkomlan);
        groupMatkul.add(rbProbstat);

        // Input fields
        tfTugas = new JTextField(10);
        tfKuis  = new JTextField(10);
        tfUTS   = new JTextField(10);
        tfUAS   = new JTextField(10);

        // Hasil 
        tfHasil = new JTextField(10);
        tfHasil.setEditable(false);

        // Tombol Hitung
        btnHitung = new JButton("Hitung");

        // TextArea rekap
        taHasil = new JTextArea(6, 28);
        taHasil.setEditable(false);
        taHasil.setFont(new Font("Monospaced", Font.PLAIN, 12));

        // Tombol Tampilkan
        btnTampilSemua = new JButton("Tampilkan nilai semua matkul");
    }

    private void buildLayout() {
        // Panel utama dengan padding
        JPanel main = new JPanel();
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
        main.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        // Judul 
        JLabel lblJudul = new JLabel("Hitung Nilai Akhir");
        lblJudul.setFont(new Font("SansSerif", Font.BOLD, 14));
        lblJudul.setAlignmentX(Component.CENTER_ALIGNMENT);
        main.add(lblJudul);
        main.add(Box.createVerticalStrut(8));

        // Panel Radio Buttons
        JPanel pRadio = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        pRadio.add(rbASD);
        pRadio.add(rbPemlan);
        pRadio.add(rbMatkomlan);
        pRadio.add(rbProbstat);
        main.add(pRadio);
        main.add(Box.createVerticalStrut(8));

        // Panel Form (GridLayout label + field)
        JPanel pForm = new JPanel(new GridLayout(5, 2, 5, 5));
        pForm.add(new JLabel("Tugas :"));  pForm.add(tfTugas);
        pForm.add(new JLabel("Kuis :"));   pForm.add(tfKuis);
        pForm.add(new JLabel("UTS :"));    pForm.add(tfUTS);
        pForm.add(new JLabel("UAS :"));    pForm.add(tfUAS);
        pForm.add(new JLabel("Hasil :"));  pForm.add(tfHasil);
        main.add(pForm);
        main.add(Box.createVerticalStrut(8));

        // Tombol Hitung (center) 
        JPanel pHitung = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pHitung.add(btnHitung);
        main.add(pHitung);
        main.add(Box.createVerticalStrut(6));

        // TextArea dalam border 
        JScrollPane scroll = new JScrollPane(taHasil);
        scroll.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createEtchedBorder(),
            "HASIL NILAI SEMUA MATA KULIAH"
        ));
        scroll.setAlignmentX(Component.CENTER_ALIGNMENT);
        main.add(scroll);
        main.add(Box.createVerticalStrut(8));

        // Tombol Tampilkan
        JPanel pTampil = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pTampil.add(btnTampilSemua);
        main.add(pTampil);

        add(main);
    }

    private void attachEvents() {
        ActionListener radioListener = e -> clearFields();
        rbASD.addActionListener(radioListener);
        rbPemlan.addActionListener(radioListener);
        rbMatkomlan.addActionListener(radioListener);
        rbProbstat.addActionListener(radioListener);

        btnHitung.addActionListener(e -> hitung());

        btnTampilSemua.addActionListener(e -> tampilSemua());
    }

    private void clearFields() {
        tfTugas.setText("");
        tfKuis.setText("");
        tfUTS.setText("");
        tfUAS.setText("");
        tfHasil.setText("");
    }

    private MataKuliah getMKDipilih() {
        if (rbASD.isSelected())       return asd;
        if (rbPemlan.isSelected())    return pemlan;
        if (rbMatkomlan.isSelected()) return matkomlan;
        if (rbProbstat.isSelected())  return probstat;
        return pemlan;
    }

    private void hitung() {
        try {
            double tugas = Double.parseDouble(tfTugas.getText().trim());
            double kuis  = Double.parseDouble(tfKuis.getText().trim());
            double uts   = Double.parseDouble(tfUTS.getText().trim());
            double uas   = Double.parseDouble(tfUAS.getText().trim());

            if (tugas < 0 || tugas > 100 || kuis < 0 || kuis > 100 ||
                uts   < 0 || uts   > 100 || uas  < 0 || uas  > 100) {
                JOptionPane.showMessageDialog(this,
                    "Nilai harus antara 0 – 100!", "Input Tidak Valid",
                    JOptionPane.WARNING_MESSAGE);
                return;
            }

            MataKuliah mk = getMKDipilih();
            mk.setNilai(tugas, kuis, uts, uas);
            double hasil = mk.hitungNilaiAkhir();

            tfHasil.setText(String.valueOf(Math.round(hasil * 10.0) / 10.0));

            nilaiMap.put(mk.getNama(), hasil);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                "Isi semua field dengan angka!", "Input Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private void tampilSemua() {
        if (nilaiMap.isEmpty()) {
            taHasil.setText("Belum ada nilai yang dihitung.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        String[] urutan = {"Pemlan", "ASD", "Matkomlan", "Probstat"};
        for (String nama : urutan) {
            if (nilaiMap.containsKey(nama)) {
                double v = nilaiMap.get(nama);
                // Format: nama + spasi + ":" + nilai (1 desimal), sama seperti screenshot
                sb.append(String.format("%-10s : %.1f%n", nama, v));
            }
        }
        taHasil.setText(sb.toString());
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}

        SwingUtilities.invokeLater(NilaiMahasiswaGUI::new);
    }
}