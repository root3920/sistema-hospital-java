import javax.swing.*;
import java.awt.event.*;

public class Grafico implements ActionListener {
    JFrame ventana;
    JPanel home;
    JLabel lbl_nombre, lbl_nombreEncontrado;
    JMenuBar bar;
    JMenu inicio, mantenimiento, reporte;
    JMenuItem presentacion, salir, paciente, medico, reporte_pacientes, reporte_medicos;
    String tabla;

    public void setMenuBar() {

        // Crear MenuBar
        bar = new JMenuBar();
        ventana.setJMenuBar(bar);

        // Crear Menus
        inicio = new JMenu("Inicio");
        mantenimiento = new JMenu("Mantenimiento");
        reporte = new JMenu("Reporte");
        bar.add(inicio);
        bar.add(mantenimiento);
        bar.add(reporte);

        // Crear Items
        presentacion = new JMenuItem("Presentacion");
        inicio.add(presentacion);
        presentacion.addActionListener(this);

        salir = new JMenuItem("Salir");
        inicio.add(salir);
        salir.addActionListener(this);

        paciente = new JMenuItem("Paciente");
        mantenimiento.add(paciente);
        paciente.addActionListener(this);

        medico = new JMenuItem("Medico");
        mantenimiento.add(medico);
        medico.addActionListener(this);

        reporte_pacientes = new JMenuItem("Reporte de Pacientes");
        reporte.add(reporte_pacientes);
        reporte_pacientes.addActionListener(this);

        reporte_medicos = new JMenuItem("Reporte de Medicos");
        reporte.add(reporte_medicos);
        reporte_medicos.addActionListener(this);
    }

    Grafico() {
        ventana = new JFrame("Ventana");
        ventana.setBounds(000, 000, 1200, 500);
        ventana.setLayout(null);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Presentacion p = new Presentacion(ventana);
        // Crear MenuBar
        setMenuBar();

        ventana.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == presentacion)
            new Presentacion(ventana);
        if (e.getSource() == salir)
            System.exit(0);
        if (e.getSource() == paciente)
            new Paciente(ventana);
        if (e.getSource() == medico)
            new Medico(ventana);
        if (e.getSource() == reporte_pacientes) {
            tabla = "paciente";
            new App(tabla, ventana);
        }
        if (e.getSource() == reporte_medicos) {
            tabla = "medico";
            new App(tabla, ventana);
        }

    }
}
