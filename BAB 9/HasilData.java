import javax.swing.*;
import java.awt.*;

public class HasilData extends JFrame {

    public HasilData(String nama, String tanggal, String noPendaftaran,
                     String noTelp, String alamat, String email) {

        setTitle("Data Mahasiswa");
        setSize(420, 370);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        getContentPane().setBackground(new Color(210, 215, 220));

        // luar
        JPanel outerPanel = new JPanel(new BorderLayout());
        outerPanel.setBackground(new Color(210, 215, 220));
        outerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Judul
        JLabel titleLabel = new JLabel("Data Mahasiswa", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 10, 0));
        outerPanel.add(titleLabel, BorderLayout.NORTH);

        // dalam 
        JPanel innerPanel = new JPanel(new GridLayout(8, 1, 0, 0));
        innerPanel.setBackground(Color.WHITE);
        innerPanel.setBorder(BorderFactory.createLineBorder(new Color(100, 150, 220), 2));

        innerPanel.add(buatBaris("Nama",             nama));
        innerPanel.add(buatBaris("Tanggal Lahir",    tanggal));
        innerPanel.add(buatBaris("No.Pendaftaran",   noPendaftaran));
        innerPanel.add(buatBaris("No.Telp",          noTelp));
        innerPanel.add(buatBaris("Alamat",           alamat));
        innerPanel.add(buatBaris("E-mail",           email));

        innerPanel.add(new JPanel());
        innerPanel.add(new JPanel());

        outerPanel.add(innerPanel, BorderLayout.CENTER);
        add(outerPanel);
        setVisible(true);
    }

    private JPanel buatBaris(String field, String nilai) {
        JPanel baris = new JPanel(null);
        baris.setBackground(Color.WHITE);

        JLabel lblField = new JLabel(field);
        lblField.setBounds(10, 0, 120, 30);
        lblField.setFont(new Font("Arial", Font.PLAIN, 12));

        JLabel lblTitikDua = new JLabel(":");
        lblTitikDua.setBounds(130, 0, 15, 30);
        lblTitikDua.setFont(new Font("Arial", Font.PLAIN, 12));

        JLabel lblNilai = new JLabel(nilai);
        lblNilai.setBounds(145, 0, 240, 30);
        lblNilai.setFont(new Font("Arial", Font.PLAIN, 12));

        baris.add(lblField);
        baris.add(lblTitikDua);
        baris.add(lblNilai);

        return baris;
    }
}
