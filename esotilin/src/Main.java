package dao;
//Mira meco, me sale esto, comentame las lineas que necesites que cambie o que quieras otra cosa
import model.Estudiante;
//SIGUE HACIENDO
//pero que parte ctm
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EstudianteDAOImpl implements EstudianteDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/tu_base_de_datos";
    private static final String USER = "tu_usuario";
    private static final String PASSWORD = "tu_contraseña";

    public void crearEstudiante(Estudiante estudiante) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO estudiantes (nombre, apellido) VALUES (?, ?)")) {
            preparedStatement.setString(1, estudiante.getNombre());
            preparedStatement.setString(2, estudiante.getApellido());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Estudiante obtenerEstudiante(int id) {
        // ... (Implementación similar para obtener un estudiante por ID)
    }

    public List<Estudiante> obtenerTodosLosEstudiantes() {
        List<Estudiante> estudiantes = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM estudiantes")) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Estudiante estudiante = new Estudiante();
                estudiante.setId(resultSet.getInt("id"));
                estudiante.setNombre(resultSet.getString("nombre"));
                estudiante.setApellido(resultSet.getString("apellido"));
                estudiantes.add(estudiante);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return estudiantes;
    }

    public void actualizarEstudiante(Estudiante estudiante) {
        // ... (Implementación similar para actualizar un estudiante)
    }

    public void eliminarEstudiante(int id) {
        // ... (Implementación similar para eliminar un estudiante)
    }
}
