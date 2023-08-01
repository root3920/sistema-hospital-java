import javax.swing.*;
import javax.swing.table.*;

public class Tabla {
    JTable tabla_01;
    JScrollPane sp_lista;
    DefaultTableModel dtm;

    Tabla() {
        dtm = new DefaultTableModel();
        tabla_01 = new JTable(dtm);
        sp_lista = new JScrollPane(tabla_01);
        sp_lista.setBounds(50, 75, 700, 200);
    }

}
