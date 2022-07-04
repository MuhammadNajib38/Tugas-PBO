import frame.SepedaViewFrame;
import helpers.koneksi;

public class main {
    public static void main(String[] args) {
        koneksi.getConnection();
        SepedaViewFrame viewFrame = new SepedaViewFrame();
        viewFrame.setVisible(true);
    }
}
