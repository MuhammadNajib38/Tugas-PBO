package frame;

import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import helpers.koneksi;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SepedaInputFrame extends JFrame{
    private JPanel mainPanel;
    private JTextField idTextField;
    private JTextField namaTextField;
    private JButton batalButton;
    private JButton simpanButton;

    int id;

    public void setId(int id) {
        this.id = id;
    }

    public SepedaInputFrame(){
        simpanButton.addActionListener(e -> {
            String nama = namaTextField.getText();
            String keyword = namaTextField.getText();
            if(nama.equals("")){
                JOptionPane.showMessageDialog( null,
                        "Isi nama sepeda",""+
                                "validasi data kosong",
                        JOptionPane.WARNING_MESSAGE);
                namaTextField.requestFocus();
                return;
            }
            Connection c = koneksi.getConnection();
            PreparedStatement ps;
            try {
                if(id == 0){
                String  cekSQL = "SELECT + FROM sepeda WHERE nama = ?";
                ps = c.prepareStatement(cekSQL);
                ps.setString( 1, nama);
                ResultSet rs = ps.executeQuery();
                if(rs.next()){
                    JOptionPane.showMessageDialog( null,
                            "Data nama sepeda sama sudah ada",
                            "validasi data sama",
                            JOptionPane.WARNING_MESSAGE);
                } else {
                    String insertSQL = "INSERT INTO sepeda SET nama = ?";
                    ps = c.prepareStatement(insertSQL);
                    ps.setString(1, nama);
                    ps.executeUpdate();
                    dispose();
                }
                
            } else {
                String updateSQL = "UPDATE sepeda SET nama = ? WHERE id=?";
                    ps = c.prepareStatement(updateSQL);
                    ps.setString(1, nama);
                    ps.setInt(2, id);
                    ps.executeUpdate();
                    dispose();

                }
            } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            });
        batalButton.addActionListener(e -> {
            dispose();
        });
    }
    public void init(){
        setContentPane(mainPanel);
        setTitle("Data Sepeda");
        pack();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public void isiKomponen(){
        Connection c = koneksi.getConnection();
        String findSQL = "SELECT * FROM sepeda WHERE id = ?";
        PreparedStatement ps;

        try {
            ps = c.prepareStatement(findSQL);
            ps.setInt( 1,id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                idTextField.setText(String.valueOf(rs.getInt("id")));
                namaTextField.setText(rs.getString("nama"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

