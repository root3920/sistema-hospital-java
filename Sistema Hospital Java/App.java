import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.*;

public class App implements ActionListener {
    ButtonGroup bg_orden;
    JRadioButton rb_cedula, rb_nombre, rb_apellido;
    String orden, tabla;
    JButton btn_reporte;
    JFrame ventana;
    JLabel lbl_orden;

    App(String t, JFrame v) {
        ventana = v;
        tabla = t;

        v.getContentPane().removeAll();
        v.getContentPane().setBackground(Color.WHITE);
        v.revalidate();
        lbl_orden = new JLabel("Ordenado por:");
        lbl_orden.setBounds(400, 100, 150, 50);
        ventana.add(lbl_orden);

        bg_orden = new ButtonGroup();
        rb_apellido = new JRadioButton("Apellido");
        rb_apellido.setBounds(400, 160, 300, 50);

        rb_cedula = new JRadioButton("Cedula");
        rb_cedula.setBounds(400, 220, 300, 50);

        rb_nombre = new JRadioButton("Nombre");
        rb_nombre.setBounds(400, 280, 300, 50);

        bg_orden.add(rb_cedula);
        bg_orden.add(rb_nombre);
        bg_orden.add(rb_apellido);

        btn_reporte = new JButton("Crear Reporte");
        btn_reporte.setBounds(330, 30, 450, 30);
        btn_reporte.addActionListener(this);
        ventana.add(btn_reporte);
        ventana.add(rb_apellido);
        ventana.add(rb_cedula);
        ventana.add(rb_nombre);
        v.repaint();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn_reporte)
            crearReporte();
    }

    public void crearReporte() {
        try {

            Bdd bdd = new Bdd();
            Map<String, Object> parametro = new HashMap<String, Object>();

            if (rb_cedula.isSelected())
                orden = "cedula";
            else if (rb_nombre.isSelected())
                orden = "nombre";
            else if (rb_apellido.isSelected())
                orden = "apellido";

            parametro.put("orden", orden);
            parametro.put("titulo", "ordenado por " + orden);

            if (tabla == "paciente") {
                JasperPrint jasperPrint = JasperFillManager.fillReport("paciente_report.jasper", parametro,
                        bdd.getConnection());
                JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
                jasperViewer.setVisible(true);
            } else {
                JasperPrint jasperPrint = JasperFillManager.fillReport("medico_report.jasper", parametro,
                        bdd.getConnection());
                JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
                jasperViewer.setVisible(true);
            }

        } catch (Exception e) {
            System.out.println("error" + e.toString());
        }
    }

}