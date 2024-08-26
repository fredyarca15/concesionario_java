package dao;

import com.mysql.cj.util.StringUtils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Vehiculo;

public class VehiculoDao {

    //Libreria MySQL
    public String driver = "com.mysql.cj.jdbc.Driver";
    //Nombre de la base de datos
    public String database = "concesionario_test";
    //Host
    public String hostname = "localhost";
    //Puerto
    public String port = "3306";
    //Ruta de nuestra base de datos (desactivamos el uso de SSl con ?useSSL=false
    public String url = "jdbc:mysql://" + hostname + ":" + port + "/" + database + "?useSSL=false";
    //Usuario
    public String username = "root";
    //Password
    public String password = "root1234";

    public Connection conectarMySQL() {
        Connection conn = null;

        try {
            Class.forName(driver);
            conn = (Connection) DriverManager.getConnection(url, username, password);
            System.out.println("Conectado a la base de datos");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public int probarConexion() {
        int cantidadVehiculos = 0;
        String sql = "SELECT COUNT(*) AS total FROM vehiculos;";

        try (Connection conexion = conectarMySQL();
                Statement statement = conexion.createStatement();
                ResultSet resultado = statement.executeQuery(sql)) {

            if (resultado.next()) {
                cantidadVehiculos = resultado.getInt("total");
            }
        } catch (SQLException e) {
            Logger.getLogger(VehiculoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return cantidadVehiculos;
    }

    public void agregar(Vehiculo v) {
        String sql = "INSERT INTO `vehiculos` (`matricula`, `marca`, `modelo`, "
                + "`year_fab`, `precio`, `color`, `tipo_motor`, `transmision`, `km`, `ubicacion`, `estado`, `descripcion`)"
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

        try (Connection conexion = conectarMySQL();
                PreparedStatement statement = conexion.prepareStatement(sql)) {

            statement.setString(1, v.getMatricula());
            statement.setString(2, v.getMarca());
            statement.setString(3, v.getModelo());
            statement.setInt(4, Integer.parseInt(v.getYear_fab()));
            statement.setDouble(5, Double.parseDouble(v.getPrecio()));
            statement.setString(6, v.getColor());
            statement.setString(7, v.getTipo_motor());
            statement.setString(8, v.getTransmision());
            statement.setInt(9, Integer.parseInt(v.getKm()));
            statement.setString(10, v.getUbicacion());
            statement.setString(11, v.getEstado());
            statement.setString(12, v.getDescripcion());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(null, "Se ha grabado con éxito");
            }
        } catch (SQLException ex) {
            Logger.getLogger(VehiculoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void actualizar(Vehiculo v) {
        String sql = "UPDATE `concesionario_test`.`vehiculos` "
                + "SET `matricula` = ?,`marca` = ?, `modelo` = ?, `year_fab` = ?, `precio` = ?, "
                + "`color` = ?, `tipo_motor` = ?, `transmision` = ?, `km` = ?, "
                + "`ubicacion` = ?, `estado` = ?, `descripcion` = ? "
                + "WHERE `id_vehiculo` = ?;";

        try (Connection conexion = conectarMySQL();
                PreparedStatement statement = conexion.prepareStatement(sql)) {

            // Establecer los valores en la consulta
            statement.setString(1, v.getMatricula());
            statement.setString(2, v.getMarca());
            statement.setString(3, v.getModelo());
            statement.setInt(4, Integer.parseInt(v.getYear_fab())); // Convertir a int si es necesario
            statement.setDouble(5, Double.parseDouble(v.getPrecio())); // Convertir a double si es necesario
            statement.setString(6, v.getColor());
            statement.setString(7, v.getTipo_motor());
            statement.setString(8, v.getTransmision());
            statement.setInt(9, Integer.parseInt(v.getKm())); // Convertir a int si es necesario
            statement.setString(10, v.getUbicacion());
            statement.setString(11, v.getEstado());
            statement.setString(12, v.getDescripcion());
            statement.setInt(13, Integer.parseInt(v.getId_vehiculo())); // Convertir a int si es necesario

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(null, "Actualizado con éxito");
            }
        } catch (SQLException ex) {
            Logger.getLogger(VehiculoDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NumberFormatException ex) {
            // Maneja el caso en que la conversión de String a int o double falla
            Logger.getLogger(VehiculoDao.class.getName()).log(Level.SEVERE, "Error en la conversión de tipo de dato", ex);
        }
    }

    public void eliminarVehiculo(String id) {
        try {
            Statement statement = null;
            Connection conexion = conectarMySQL();
            //DELETE FROM `concesionario_test`.`vehiculos` WHERE (`id_vehiculo` = '1');
            String sql = "DELETE FROM vehiculos WHERE id_vehiculo = " + id;
            statement = conexion.createStatement();
            statement.execute(sql);
        } catch (SQLException ex) {
            Logger.getLogger(VehiculoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Vehiculo> listarVehiculos() {
        List<Vehiculo> listado = new ArrayList<>();
        String sql = "SELECT * FROM vehiculos;";

        try (Connection conexion = conectarMySQL();
                Statement statement = conexion.createStatement();
                ResultSet resultado = statement.executeQuery(sql)) {

            while (resultado.next()) {
                Vehiculo vehiculo = new Vehiculo();
                vehiculo.setId_vehiculo(resultado.getString("id_vehiculo"));
                vehiculo.setMatricula(resultado.getString("matricula"));
                vehiculo.setMarca(resultado.getString("marca"));
                vehiculo.setModelo(resultado.getString("modelo"));
                vehiculo.setYear_fab(resultado.getString("year_fab"));
                vehiculo.setPrecio(resultado.getString("precio"));
                vehiculo.setColor(resultado.getString("color"));
                vehiculo.setTipo_motor(resultado.getString("tipo_motor"));
                vehiculo.setTransmision(resultado.getString("transmision"));
                vehiculo.setKm(resultado.getString("km"));
                vehiculo.setUbicacion(resultado.getString("ubicacion"));
                vehiculo.setEstado(resultado.getString("estado"));
                vehiculo.setDescripcion(resultado.getString("descripcion"));
                listado.add(vehiculo);
            }
        } catch (SQLException e) {
            Logger.getLogger(VehiculoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return listado;
    }

    public void guardar(Vehiculo v) {
        if (StringUtils.isEmptyOrWhitespaceOnly(v.getId_vehiculo())) {
            agregar(v);
        } else {
            actualizar(v);
        }
    }
    
    

//    public static void main(String[] args) {
//        VehiculoDao dao = new VehiculoDao();
//        int cantidad = dao.probarConexion();
//        System.out.println("Cantidad de vehículos en la base de datos: " + cantidad);
//    }    
}
