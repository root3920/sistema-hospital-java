import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Paciente implements ActionListener {
    JFrame paciente;
    JPanel panel, panel2;
    JTextField tf_cedula, tf_id, tf_nombre, tf_apellido, tf_direccion, tf_telefono, tf_provincia, tf_edad, tf_sexo;
    JLabel lbl_cedula, lbl_id, lbl_nombre, lbl_apellido, lbl_direccion, lbl_telefono, lbl_provincia, lbl_edad, lbl_sexo;
    JButton btn_buscar, btn_modificar, btn_eliminar, btn_listar, btn_agregar, btn_limpiar, btn_corregirbusqueda;
    Tabla tabla2 = new Tabla();
    Sql sql = new Sql();
    JLabel status;
    Boolean busqueda = false;
    Boolean limpieza = true;

    Paciente(JFrame v) {
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
        lbl_cedula.setBounds(20, 60, 100, 20);
        panel.add(lbl_cedula);

        tf_cedula = new JTextField();
        tf_cedula.setBounds(80, 60, 250, 20);
        panel.add(tf_cedula);

        // id
        lbl_id = new JLabel("ID:");
        lbl_id.setBounds(20, 30, 100, 20);
        panel.add(lbl_id);

        tf_id = new JTextField();
        tf_id.setBounds(80, 30, 250, 20);
        panel.add(tf_id);

        // nombre
        lbl_nombre = new JLabel("Nombre:");
        lbl_nombre.setBounds(20, 90, 100, 20);
        panel.add(lbl_nombre);

        tf_nombre = new JTextField();
        tf_nombre.setBounds(80, 90, 250, 20);
        panel.add(tf_nombre);

        // apellido
        lbl_apellido = new JLabel("Apellido:");
        lbl_apellido.setBounds(20, 120, 100, 20);
        panel.add(lbl_apellido);

        tf_apellido = new JTextField();
        tf_apellido.setBounds(80, 120, 250, 20);
        panel.add(tf_apellido);

        // direccion
        lbl_direccion = new JLabel("Direccion:");
        lbl_direccion.setBounds(20, 150, 100, 20);
        panel.add(lbl_direccion);

        tf_direccion = new JTextField();
        tf_direccion.setBounds(80, 150, 250, 20);
        panel.add(tf_direccion);

        // Telefono
        lbl_telefono = new JLabel("Telefono:");
        lbl_telefono.setBounds(20, 180, 100, 20);
        panel.add(lbl_telefono);

        tf_telefono = new JTextField();
        tf_telefono.setBounds(80, 180, 250, 20);
        panel.add(tf_telefono);

        // Provincia
        lbl_provincia = new JLabel("Provincia:");
        lbl_provincia.setBounds(20, 210, 100, 20);
        panel.add(lbl_provincia);

        tf_provincia = new JTextField();
        tf_provincia.setBounds(80, 210, 250, 20);
        panel.add(tf_provincia);

        // Edad
        lbl_edad = new JLabel("Edad:");
        lbl_edad.setBounds(20, 240, 100, 20);
        panel.add(lbl_edad);

        tf_edad = new JTextField();
        tf_edad.setBounds(80, 240, 250, 20);
        panel.add(tf_edad);

        // Sexo
        lbl_sexo = new JLabel("Sexo:");
        lbl_sexo.setBounds(20, 270, 100, 20);
        panel.add(lbl_sexo);

        tf_sexo = new JTextField();
        tf_sexo.setBounds(80, 270, 250, 20);
        panel.add(tf_sexo);
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
            btn_buscar.setBounds(80, 300, 250, 30);
            panel.add(btn_buscar);
            btn_buscar.addActionListener(this);

            panel.repaint();

        } else if (sql.encontrado == "noencontrado") {
            panel.remove(btn_buscar);

            btn_agregar = new JButton("Agregar");
            btn_agregar.setBounds(80, 300, 250, 30);
            panel.add(btn_agregar);
            btn_agregar.addActionListener(this);

            btn_corregirbusqueda = new JButton("Corregir Busqueda");
            btn_corregirbusqueda.setBounds(80, 340, 250, 30);
            panel.add(btn_corregirbusqueda);
            btn_corregirbusqueda.addActionListener(this);

            panel.repaint();

        } else if (sql.encontrado == "limpiado") {
            panel.remove(btn_modificar);
            panel.remove(btn_eliminar);
            panel.remove(btn_limpiar);

            btn_buscar = new JButton("Buscar");
            btn_buscar.setBounds(80, 300, 250, 30);
            panel.add(btn_buscar);
            btn_buscar.addActionListener(this);

            panel.repaint();
        } else if (sql.encontrado == "busquedacorregida") {
            panel.remove(btn_buscar);
            panel.remove(btn_agregar);
            panel.remove(btn_corregirbusqueda);

            btn_limpiar = new JButton("Limpiar");
            btn_limpiar.setBounds(80, 300, 250, 30);
            panel.add(btn_limpiar);
            btn_limpiar.addActionListener(this);

            btn_modificar = new JButton("Modificar");
            btn_modificar.setBounds(80, 340, 250, 30);
            panel.add(btn_modificar);
            btn_modificar.addActionListener(this);

            btn_eliminar = new JButton("Eliminar");
            btn_eliminar.setBounds(80, 380, 250, 30);
            panel.add(btn_eliminar);
            btn_eliminar.addActionListener(this);

            panel.repaint();
        } else {
            panel.remove(btn_buscar);

            btn_limpiar = new JButton("Limpiar");
            btn_limpiar.setBounds(80, 300, 250, 30);
            panel.add(btn_limpiar);
            btn_limpiar.addActionListener(this);

            btn_modificar = new JButton("Modificar");
            btn_modificar.setBounds(80, 340, 250, 30);
            panel.add(btn_modificar);
            btn_modificar.addActionListener(this);

            btn_eliminar = new JButton("Eliminar");
            btn_eliminar.setBounds(80, 380, 250, 30);
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
            sql.tabla = "paciente";
            sql.buscar(tf_cedula.getText());
            buscar();
            crearBotones();
            actualizarStatus(sql.status);
        }
        if (e.getSource() == btn_corregirbusqueda) {
            sql.tabla = "paciente";
            sql.buscar(tf_cedula.getText());
            buscar();
            corregirbuscar();
            actualizarStatus(sql.status);
            crearBotones();
        }

        if (e.getSource() == btn_eliminar) {
            sql.tabla = "paciente";
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
                sql.tabla = "paciente";
                sql.modificar(tf_id.getText(), tf_nombre.getText(), tf_apellido.getText(), tf_direccion.getText(),
                        tf_telefono.getText(), tf_provincia.getText(), tf_edad.getText(), tf_sexo.getText());
                actualizarStatus(sql.status);
            }

        }
        if (e.getSource() == btn_agregar) {
            sql.tabla = "paciente";
            sql.agregar(tf_id.getText(), tf_cedula.getText(), tf_nombre.getText(), tf_apellido.getText(),
                    tf_direccion.getText(),
                    tf_telefono.getText(), tf_provincia.getText(), tf_edad.getText(), tf_sexo.getText());
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
                tf_edad.setText(sql.edad);
                tf_provincia.setText(sql.provincia);
                tf_telefono.setText(sql.telefono);
                tf_sexo.setText(sql.sexo);
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
        sql.tabla = "paciente";

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
        tf_edad.setText("");
        tf_provincia.setText("");
        tf_telefono.setText("");
        tf_sexo.setText("");
    }
}
