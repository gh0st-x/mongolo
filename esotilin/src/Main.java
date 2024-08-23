package dao;
//Mira meco, me sale esto, comentame las lineas que necesites que cambie o que quieras otra cosa
import model.Estudiante;
//SIGUE HACIENDO
//pero que parte ctm
//dime hasta donde tengo bien 
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

//Funcion para obtener estudiantes
public Estudiante obtenerEstudiante(int id) {
    Estudiante estudiante = null;
    try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
         PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM estudiantes WHERE id = ?")) {
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            estudiante = new Estudiante();
            estudiante.setId(resultSet.getInt("id"));
            estudiante.setNombre(resultSet.getString("nombre"));
            estudiante.setApellido(resultSet.getString("apellido"));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return estudiante;
}

//Mas funciones
public void actualizarEstudiante(Estudiante estudiante) {
    try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
         PreparedStatement preparedStatement = connection.prepareStatement("UPDATE estudiantes SET nombre = ?, apellido = ? WHERE id = ?")) {
        preparedStatement.setString(1, estudiante.getNombre());
        preparedStatement.setString(2, estudiante.getApellido());
        preparedStatement.setInt(3, estudiante.getId());
        preparedStatement.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

public void eliminarEstudiante(int id) {
    try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
         PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM estudiantes WHERE id = ?")) {
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaPrincipal extends JFrame {
    private JTextField txtNombre;
    private JButton btnAgregar;

    public VentanaPrincipal() {
        setTitle("Gestión de Estudiantes");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel principal
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));

        // Panel superior con el campo de texto
        JPanel panelSuperior = new JPanel();
        panelSuperior.add(new JLabel("Nombre:"));
        txtNombre = new JTextField(20);
        panelSuperior.add(txtNombre);
        panel.add(panelSuperior);

        // Panel inferior con el botón
        JPanel panelInferior = new JPanel();
        btnAgregar = new JButton("Agregar Estudiante");
        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para agregar un nuevo estudiante
                String nombre = txtNombre.getText();
                // ... (llamar al método de la clase DAO para crear un nuevo estudiante)
            }
        });
        panelInferior.add(btnAgregar);
        panel.add(panelInferior);

        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VentanaPrincipal ventana = new VentanaPrincipal();
            ventana.setVisible(true);
        });
    }
}
