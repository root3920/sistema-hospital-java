import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;

public class Sql {

    String id, cedula, nombre, apellido, direccion, telefono, provincia, edad, sexo, codigo, pacientes_anual,
            pacientes_mes, especialidad;
    String sql;
    String encontrado = "false";
    String tabla;
    String listado = "false";
    String status = "false";

    Sql() {
        id = "";
        cedula = "";
        nombre = "";
        apellido = "";
        direccion = "";
        telefono = "";
        provincia = "";
        edad = "";
        sexo = "";
        codigo = "";
        pacientes_anual = "";
        pacientes_mes = "";
        especialidad = "";
        sql = "";
        tabla = "";
    }

    public void listar(DefaultTableModel dtm) {
        Bdd bdd = new Bdd();
        String sql = "";
        status = "Status: Usuarios Listados";

        if (tabla == "paciente") {
            listado = "true";
            dtm.addColumn("id");
            dtm.addColumn("Cedula");
            dtm.addColumn("Nombre");
            dtm.addColumn("Apellido");
            dtm.addColumn("Direccion");
            dtm.addColumn("Telefono");
            dtm.addColumn("Provincia");
            dtm.addColumn("Edad");
            dtm.addColumn("Sexo");

            Object[] fila = new Object[10];

            try {
                sql = "select * from " + tabla;
                ResultSet rs = bdd.stmt.executeQuery(sql);
                while (rs.next()) {
                    fila[0] = rs.getString("id");
                    fila[1] = rs.getString("cedula");
                    fila[2] = rs.getString("nombre");
                    fila[3] = rs.getString("apellido");
                    fila[4] = rs.getString("direccion");
                    fila[5] = rs.getString("telefono");
                    fila[6] = rs.getString("provincia");
                    fila[7] = rs.getString("edad");
                    fila[8] = rs.getString("sexo");

                    dtm.addRow(fila);
                }
                cerrar();
            } catch (Exception e) {
                System.out.println("error cargarTabla " + e.toString());
            }
        } else {
            listado = "true";

            dtm.addColumn("id");
            dtm.addColumn("codigo");
            dtm.addColumn("Cedula");
            dtm.addColumn("Nombre");
            dtm.addColumn("Apellido");
            dtm.addColumn("Direccion");
            dtm.addColumn("Telefono");
            dtm.addColumn("Especialidad");
            dtm.addColumn("P. Mensual");
            dtm.addColumn("P. Anual");

            Object[] fila = new Object[10];

            try {
                sql = "select * from " + tabla;
                ResultSet rs = bdd.stmt.executeQuery(sql);
                while (rs.next()) {
                    fila[0] = rs.getString("id");
                    fila[1] = rs.getString("codigo");
                    fila[2] = rs.getString("cedula");
                    fila[3] = rs.getString("nombre");
                    fila[4] = rs.getString("apellido");
                    fila[5] = rs.getString("direccion");
                    fila[6] = rs.getString("telefono");
                    fila[7] = rs.getString("especialidad");
                    fila[8] = rs.getString("pacientes_mes");
                    fila[9] = rs.getString("pacientes_anual");

                    dtm.addRow(fila);
                }
                cerrar();
            } catch (Exception e) {
                status = e.toString();
                System.out.println("error cargarTabla " + e.toString());
            }
        }

    }

    public void buscar(String ced) {
        Bdd bdd = new Bdd();
        id = "";
        cedula = "";
        nombre = "";
        apellido = "";
        direccion = "";
        telefono = "";
        provincia = "";
        edad = "";
        sexo = "";
        codigo = "";
        pacientes_anual = "";
        pacientes_mes = "";
        especialidad = "";
        sql = "";

        try {
            sql = "select * from " + tabla + " where cedula = '" + ced + "'";
            bdd.rs = bdd.stmt.executeQuery(sql);

            if (bdd.rs.next()) {
                if (tabla == "paciente") {
                    status = "Status: Usuario Encontrado";
                    id = bdd.rs.getString("id");
                    cedula = bdd.rs.getString("cedula");
                    nombre = bdd.rs.getString("nombre");
                    apellido = bdd.rs.getString("apellido");
                    direccion = bdd.rs.getString("direccion");
                    telefono = bdd.rs.getString("telefono");
                    provincia = bdd.rs.getString("provincia");
                    edad = bdd.rs.getString("edad");
                    sexo = bdd.rs.getString("sexo");
                    encontrado = "true";
                } else {
                    status = "Status: Usuario Encontrado";
                    id = bdd.rs.getString("id");
                    codigo = bdd.rs.getString("codigo");
                    cedula = bdd.rs.getString("cedula");
                    nombre = bdd.rs.getString("nombre");
                    apellido = bdd.rs.getString("apellido");
                    direccion = bdd.rs.getString("direccion");
                    telefono = bdd.rs.getString("telefono");
                    especialidad = bdd.rs.getString("especialidad");
                    pacientes_mes = bdd.rs.getString("pacientes_mes");
                    pacientes_anual = bdd.rs.getString("pacientes_anual");
                    encontrado = "true";
                }
            } else {
                status = "Status: Usuario No Encontrado";
                id = "";
                cedula = "";
                nombre = "";
                apellido = "";
                direccion = "";
                telefono = "";
                provincia = "";
                edad = "";
                sexo = "";
                codigo = "";
                pacientes_anual = "";
                pacientes_mes = "";
                especialidad = "";
                sql = "";
                encontrado = "noencontrado";
            }

            cerrar();
        } catch (Exception e) {
            status = e.toString();
            System.out.println("error buscar " + e.toString());
        }

    }

    public void borrar() {
        Bdd bdd = new Bdd();

        try {
            status = "Status: Usuario Borrado";
            sql = "delete from " + tabla + " where cedula = '" + cedula + "'";
            bdd.stmt.executeUpdate(sql);

            cerrar();
        } catch (Exception e) {
            status = e.toString();
            System.out.println("error agregar " + e.toString());
        }

    }

    // Modificar Paciente
    public void modificar(String id, String n, String a, String d, String t, String p, String ed, String s) {
        Bdd bdd = new Bdd();

        try {
            status = "Status: Usuario Modificado";
            sql = "update " + tabla + " set id = '" + id + "', nombre = '" + n + "', apellido = '" + a
                    + "', direccion = '" + d + "', telefono = '" + t + "', provincia = '" + p
                    + "', edad = '" + ed + "', sexo = '" + s +
                    "' where cedula = '"
                    + cedula + "'";

            bdd.stmt.executeUpdate(sql);

            cerrar();
        } catch (Exception e) {
            status = e.toString();
            System.out.println("error agregar " + e.toString());
        }
    }

    // Modificar Medico
    public void modificar(String id, String c, String n, String a, String d, String t, String es, String pm,
            String pa) {
        Bdd bdd = new Bdd();

        try {
            status = "Status: Usuario Modificado";
            sql = "update " + tabla + " set id = '" + id + "', codigo = '" + c + "', nombre = '" + n + "', apellido = '"
                    + a
                    + "', direccion = '" + d + "', telefono = '" + t + "', especialidad = '" + es
                    + "', pacientes_mes = '" + pm + "', pacientes_anual = '" + pa +
                    "' where cedula = '"
                    + cedula + "'";

            bdd.stmt.executeUpdate(sql);

            cerrar();
        } catch (Exception e) {
            status = e.toString();
            System.out.println("error agregar " + e.toString());
        }
    }

    public void agregar(String id, String c, String n, String a, String d, String t, String p, String ed, String s) {
        Bdd bdd = new Bdd();

        String sql = "";

        try {
            status = "Status: Usuario Agragado";
            sql = "insert into " + tabla
                    + " (id,cedula,nombre,apellido,direccion,telefono,provincia,edad,sexo) values('"
                    + id + "','" + c + "','" + n + "','" + a + "','"
                    + d + "','" + t + "','" + p + "','" + ed + "','" + s + "')";

            bdd.stmt.executeUpdate(sql);

            cerrar();
        } catch (Exception e) {
            status = e.toString();
            System.out.println("error agregar " + e.toString());
        }

    }

    // Agregar Medico
    public void agregar(String id, String c, String co, String n, String a, String d, String t, String es, String pm,
            String pa) {
        Bdd bdd = new Bdd();

        String sql = "";

        try {
            status = "Status: Usuario Agregado";
            sql = "insert into " + tabla
                    + " (id,codigo,cedula,nombre,apellido,direccion,telefono,especialidad,pacientes_mes,pacientes_anual) values('"
                    + id + "','" + c + "','" + co + "','" + n + "','" + a + "','"
                    + d + "','" + t + "','" + es + "','" + pm + "','" + pa + "')";

            bdd.stmt.executeUpdate(sql);

            cerrar();
        } catch (Exception e) {
            status = e.toString();
            System.out.println("error agregar " + e.toString());
        }

    }

    public void cerrar() {
        Bdd bdd = new Bdd();
        try {
            if (bdd.rs != null)
                bdd.rs.close();
            bdd.stmt.close();
            bdd.con.close();
        } catch (Exception e) {
            status = e.toString();
            System.out.println("error query " + e.toString());
        }

    }
}