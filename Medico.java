import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Medico implements ActionListener {
    JFrame paciente;
    JPanel panel, panel2;
    JTextField tf_cedula, tf_id, tf_nombre, tf_apellido, tf_direccion, tf_telefono, tf_especialidad, tf_pacientes_anual,
            tf_pacientes_mes, tf_codigo;
    JLabel lbl_cedula, lbl_id, lbl_nombre, lbl_apellido, lbl_direccion, lbl_telefono, lbl_especialidad,
            lbl_pacientes_anual, lbl_pacientes_mes, lbl_codigo;
    JButton btn_buscar, btn_modificar, btn_eliminar, btn_listar, btn_agregar, btn_limpiar, btn_corregirbusqueda;
    Tabla tabla2 = new Tabla();
    Sql sql = new Sql();
    Boolean busqueda = false;
    Boolean limpieza = true;
    JLabel status;

    Medico(JFrame v) {
        paciente = v;

        v.getContentPane().removeAll();
        v.getContentPane().setBackground(Color.WHITE);
        v.revalidate();

        panel = new JPanel();
        panel.setBounds(0, 0, 400, 500);
        v.add(panel);

        panel2 = new JPanel();
        panel2.setBounds(400, 0, 800, 500);
        panel2.setBackground(Color.WHITE);
        v.add(panel2);

        // Cedula
        lbl_cedula = new JLabel("Cedula:");
        lbl_cedula.setBounds(20, 40, 100, 20);
        panel.add(lbl_cedula);

        tf_cedula = new JTextField();
        tf_cedula.setBounds(120, 40, 250, 20);
        panel.add(tf_cedula);

        // id
        lbl_id = new JLabel("ID:");
        lbl_id.setBounds(20, 10, 100, 20);
        panel.add(lbl_id);

        tf_id = new JTextField();
        tf_id.setBounds(120, 10, 250, 20);
        panel.add(tf_id);

        // nombre
        lbl_nombre = new JLabel("Nombre:");
        lbl_nombre.setBounds(20, 70, 100, 20);
        panel.add(lbl_nombre);

        tf_nombre = new JTextField();
        tf_nombre.setBounds(120, 70, 250, 20);
        panel.add(tf_nombre);

        // apellido
        lbl_apellido = new JLabel("Apellido:");
        lbl_apellido.setBounds(20, 100, 100, 20);
        panel.add(lbl_apellido);

        tf_apellido = new JTextField();
        tf_apellido.setBounds(120, 100, 250, 20);
        panel.add(tf_apellido);

        // direccion
        lbl_direccion = new JLabel("Direccion:");
        lbl_direccion.setBounds(20, 130, 100, 20);
        panel.add(lbl_direccion);

        tf_direccion = new JTextField();
        tf_direccion.setBounds(120, 130, 250, 20);
        panel.add(tf_direccion);

        // Telefono
        lbl_telefono = new JLabel("Telefono:");
        lbl_telefono.setBounds(20, 160, 100, 20);
        panel.add(lbl_telefono);

        tf_telefono = new JTextField();
        tf_telefono.setBounds(120, 160, 250, 20);
        panel.add(tf_telefono);

        // Especialidad
        lbl_especialidad = new JLabel("Especialidad:");
        lbl_especialidad.setBounds(20, 190, 100, 20);
        panel.add(lbl_especialidad);

        tf_especialidad = new JTextField();
        tf_especialidad.setBounds(120, 190, 250, 20);
        panel.add(tf_especialidad);

        // Edad
        lbl_pacientes_anual = new JLabel("Pacientes Anual:");
        lbl_pacientes_anual.setBounds(20, 220, 100, 20);
        panel.add(lbl_pacientes_anual);

        tf_pacientes_anual = new JTextField();
        tf_pacientes_anual.setBounds(120, 220, 250, 20);
        panel.add(tf_pacientes_anual);

        // Sexo
        lbl_pacientes_mes = new JLabel("Pacientes Mes:");
        lbl_pacientes_mes.setBounds(20, 250, 100, 20);
        panel.add(lbl_pacientes_mes);

        tf_pacientes_mes = new JTextField();
        tf_pacientes_mes.setBounds(120, 250, 250, 20);
        panel.add(tf_pacientes_mes);

        // Sexo
        lbl_codigo = new JLabel("Codigo:");
        lbl_codigo.setBounds(20, 280, 100, 20);
        panel.add(lbl_codigo);

        tf_codigo = new JTextField();
        tf_codigo.setBounds(120, 280, 250, 20);
        panel.add(tf_codigo);
        status = new JLabel("");
        status.setBounds(250, 400, 300, 30);
        panel2.add(status);

        crearBotones();
        v.repaint();
    }

    public void actualizarStatus(String s) {
        status.setText(s);
        paciente.repaint();
    }

    public void crearBotones() {
        // Añadir Tabla
        panel2.add(tabla2.sp_lista);

        if (sql.encontrado == "false") {
            btn_listar = new JButton("Listar");
            btn_listar.setBounds(250, 300, 300, 30);
            panel2.add(btn_listar);
            btn_listar.addActionListener(this);

            btn_buscar = new JButton("Buscar");
            btn_buscar.setBounds(120, 310, 250, 30);
            panel.add(btn_buscar);
            btn_buscar.addActionListener(this);

            panel.repaint();

        } else if (sql.encontrado == "noencontrado") {
            panel.remove(btn_buscar);

            btn_agregar = new JButton("Agregar");
            btn_agregar.setBounds(120, 310, 250, 30);
            panel.add(btn_agregar);
            btn_agregar.addActionListener(this);

            btn_corregirbusqueda = new JButton("Corregir Busqueda");
            btn_corregirbusqueda.setBounds(120, 350, 250, 30);
            panel.add(btn_corregirbusqueda);
            btn_corregirbusqueda.addActionListener(this);

            panel.repaint();

        } else if (sql.encontrado == "limpiado") {
            panel.remove(btn_modificar);
            panel.remove(btn_eliminar);
            panel.remove(btn_limpiar);

            btn_buscar = new JButton("Buscar");
            btn_buscar.setBounds(120, 310, 250, 30);
            panel.add(btn_buscar);
            btn_buscar.addActionListener(this);

            panel.repaint();
        } else if (sql.encontrado == "busquedacorregida") {
            panel.remove(btn_buscar);
            panel.remove(btn_agregar);
            panel.remove(btn_corregirbusqueda);

            btn_limpiar = new JButton("Limpiar");
            btn_limpiar.setBounds(120, 310, 250, 30);
            panel.add(btn_limpiar);
            btn_limpiar.addActionListener(this);

            btn_modificar = new JButton("Modificar");
            btn_modificar.setBounds(120, 350, 250, 30);
            panel.add(btn_modificar);
            btn_modificar.addActionListener(this);

            btn_eliminar = new JButton("Eliminar");
            btn_eliminar.setBounds(120, 390, 250, 30);
            panel.add(btn_eliminar);
            btn_eliminar.addActionListener(this);

            panel.repaint();
        } else {
            panel.remove(btn_buscar);

            btn_limpiar = new JButton("Limpiar");
            btn_limpiar.setBounds(120, 300, 250, 30);
            panel.add(btn_limpiar);
            btn_limpiar.addActionListener(this);

            btn_modificar = new JButton("Modificar");
            btn_modificar.setBounds(120, 340, 250, 30);
            panel.add(btn_modificar);
            btn_modificar.addActionListener(this);

            btn_eliminar = new JButton("Eliminar");
            btn_eliminar.setBounds(120, 380, 250, 30);
            panel.add(btn_eliminar);
            btn_eliminar.addActionListener(this);

            panel.repaint();
        }
        panel.setLayout(null);
        panel2.setLayout(null);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn_listar) {
            listar();
            actualizarStatus(sql.status);
        }

        if (e.getSource() == btn_buscar) {
            sql.tabla = "medico";
            sql.buscar(tf_cedula.getText());
            buscar();
            crearBotones();
            actualizarStatus(sql.status);
        }
        if (e.getSource() == btn_corregirbusqueda) {
            sql.tabla = "medico";
            sql.buscar(tf_cedula.getText());
            buscar();
            corregirbuscar();
            actualizarStatus(sql.status);
            crearBotones();
        }

        if (e.getSource() == btn_eliminar) {
            sql.tabla = "medico";
            sql.borrar();
            actualizarStatus(sql.status);
        }

        if (e.getSource() == btn_limpiar) {
            limpiar();
            crearBotones();
            actualizarStatus(sql.status);
        }

        if (e.getSource() == btn_modificar) {
            if (busqueda) {
                sql.tabla = "medico";
                sql.modificar(tf_id.getText(), tf_codigo.getText(), tf_nombre.getText(), tf_apellido.getText(),
                        tf_direccion.getText(),
                        tf_telefono.getText(), tf_especialidad.getText(), tf_pacientes_anual.getText(),
                        tf_pacientes_mes.getText());
                actualizarStatus(sql.status);
            }

        }
        if (e.getSource() == btn_agregar) {
            sql.tabla = "medico";
            sql.agregar(tf_id.getText(), tf_codigo.getText(), tf_cedula.getText(), tf_nombre.getText(),
                    tf_apellido.getText(),
                    tf_direccion.getText(),
                    tf_telefono.getText(), tf_especialidad.getText(), tf_pacientes_mes.getText(),
                    tf_pacientes_anual.getText());
            actualizarStatus(sql.status);

        }

    }

    public void buscar() {

        try {
            if (sql.encontrado == "true") {
                busqueda = true;
                tf_nombre.setText(sql.nombre);
                tf_apellido.setText(sql.apellido);
                tf_id.setText(sql.id);
                tf_direccion.setText(sql.direccion);
                tf_cedula.setText(sql.cedula);
                tf_pacientes_anual.setText(sql.pacientes_anual);
                tf_especialidad.setText(sql.especialidad);
                tf_telefono.setText(sql.telefono);
                tf_pacientes_mes.setText(sql.pacientes_mes);
                tf_codigo.setText(sql.codigo);
            }

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void corregirbuscar() {

        if (sql.encontrado == "true") {
            sql.encontrado = "busquedacorregida";
        } else {
            sql.encontrado = "noencontrado";
        }

    }

    public void listar() {
        sql.tabla = "medico";
        if (sql.listado == "false")
            sql.listar(tabla2.dtm);

    }

    public void limpiar() {
        sql.encontrado = "limpiado";
        tf_nombre.setText("");
        tf_apellido.setText("");
        tf_id.setText("");
        tf_direccion.setText("");
        tf_cedula.setText("");
        tf_pacientes_anual.setText("");
        tf_especialidad.setText("");
        tf_telefono.setText("");
        tf_pacientes_mes.setText("");
        tf_codigo.setText("");
    }
}