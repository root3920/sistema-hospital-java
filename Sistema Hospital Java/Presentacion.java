import java.awt.*;
import javax.swing.*;

public class Presentacion {
    JFrame presentacion;

    Presentacion(JFrame v) {
        presentacion = v;

        v.getContentPane().removeAll();
        v.getContentPane().setBackground(Color.WHITE);
        v.revalidate();
        
        JLabel universidadUsuario = new JLabel("Universidad Tecnologica de Panamá",SwingConstants.CENTER);
        universidadUsuario.setBounds(0, 20, 800, 20);
        presentacion.add(universidadUsuario);

        JLabel facultadUsuario = new JLabel("Ingeneria de Sistemas",SwingConstants.CENTER);
        facultadUsuario.setBounds(0, 50, 800, 20);
        presentacion.add(facultadUsuario);

        JLabel carreraUsuario = new JLabel("Desarrollo de Software",SwingConstants.CENTER);
        carreraUsuario.setBounds(0, 80, 800, 20);
        presentacion.add(carreraUsuario);

        JLabel materiaUsuario = new JLabel("Desarrollo de Software III",SwingConstants.CENTER);
        materiaUsuario.setBounds(0, 110, 800, 20);
        presentacion.add(materiaUsuario);

        JLabel profesorUsuario = new JLabel("Ricardo Chan",SwingConstants.CENTER);
        profesorUsuario.setBounds(0, 140, 800, 20);
        presentacion.add(profesorUsuario);

        JLabel nombreUsuario = new JLabel("Federico Alarcón",SwingConstants.CENTER);
        nombreUsuario.setBounds(0, 170, 800, 20);
        presentacion.add(nombreUsuario);

        JLabel cedulaUsuario = new JLabel("E-8-183324",SwingConstants.CENTER);
        cedulaUsuario.setBounds(0, 200, 800, 20);
        presentacion.add(cedulaUsuario);

        JLabel salonUsuario = new JLabel("1LS221",SwingConstants.CENTER);
        salonUsuario.setBounds(0, 230, 800, 20);
        presentacion.add(salonUsuario);

        JLabel fechaActual = new JLabel("01/07/2023",SwingConstants.CENTER);
        fechaActual.setBounds(0, 260, 800, 20);
        presentacion.add(fechaActual);

        v.repaint();

    }
}
