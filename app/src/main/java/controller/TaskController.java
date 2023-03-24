package controller;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Task;
import util.ConnectionFactory;

public class TaskController {

    public void save(Task task) {

        String sql = "INSERT INTO tasks (idProject, name, description, notes, completed, deadline, createdDate, updatedDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        Connection connection = null;
        PreparedStatement statement = null;

        try {

            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);

            statement.setInt(1, task.getIdProject());
            statement.setString(2, task.getName());
            statement.setString(3, task.getDescription());
            statement.setBoolean(4, task.getCompleted());
            statement.setString(5, task.getNotes());
            // Aten√ß√£o, o Date abaixo √© do pacote de SQL e n√£o do java util!
            statement.setDate(6, task.getDeadline() != null ? new Date(task.getDeadline().getTime()) : null);
            statement.setDate(7, new Date(task.getCreatedDate().getTime()));
            statement.setDate(8, new Date(task.getUpdatedDate().getTime()));
            statement.execute();

        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao salvar a tarefa :(" + ex.getMessage(), ex);
            // O bloco finally sempre ser√° executado! N√£o se esque√ßa de fechar a connection
            // e o statement quando forem abertos!
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }

    }

    public void update(Task task) {

        String sql = "UPDATE tasks SET idProject = ?, name = ?, description = ?, notes = ?, completed = ?,deadline = ?, createdDate = ?, updatedDate = ? WHERE id = ?";

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            // Estabelecendo a conex√£o com o banco de dados:
            connection = ConnectionFactory.getConnection();
            // Preparando Query:
            statement = connection.prepareStatement(sql);

            // Setando os valores do statement:
            statement.setInt(1, task.getIdProject());
            statement.setString(2, task.getName());
            statement.setString(3, task.getDescription());
            statement.setString(4, task.getNotes());
            statement.setBoolean(5, task.getCompleted());
            statement.setDate(6, new Date(task.getDeadline().getTime()));
            statement.setDate(7, new Date(task.getCreatedDate().getTime()));
            statement.setDate(8, new Date(task.getUpdatedDate().getTime()));
            statement.setInt(9, task.getId());
            // executando a query:
            statement.execute();

        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao atualizar a tarefa :(" + ex.getMessage(), ex);
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
    }

    public List<Task> getAll(int idProject) {

        String sql = "SELECT * FROM tasks";
        // Lista de tarefas que ser„o desenvolvidas quando a chamada do mÈtodo acontecer
        List<Task> tasks = new ArrayList<>();
        // CriaÁ„o da conex„o:
        try (Connection connection = ConnectionFactory.getConnection();  PreparedStatement statement = connection.prepareStatement(sql);  ResultSet resultSet = statement.executeQuery()) {
            
            while (resultSet.next()) {
                Task task = new Task();
                task.setId(resultSet.getInt("id"));
                task.setIdProject(resultSet.getInt("idProject"));
                task.setName(resultSet.getString("name"));
                task.setDescription(resultSet.getString("description"));
                task.setNotes(resultSet.getString("notes"));
                task.setCompleted(resultSet.getBoolean("completed"));
                task.setDeadline(resultSet.getDate("deadline"));
                task.setCreatedDate(resultSet.getDate("createdDate"));
                task.setUpdatedDate(resultSet.getDate("updatedDate"));
                task.add(task);
            }

        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao buscar tarefas :(" + ex.getMessage(), ex);
        }
        // Lista de tarefas criada e carregada no banco de dados!
        return tasks;
    }
    
    public void removeById(int taskId) {

        String sql = "DELETE FROM tasks WHERE id = ?";

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            // id ser· alterado pelo usu·rio a partir do statement abaixo;
            // Estabelecendo a conex„o com o banco de dados:
            connection = ConnectionFactory.getConnection();
            // Preparando Query:
            statement = connection.prepareStatement(sql);
            // Setando valores:
            statement.setInt(1, taskId);
            // Executando Query:
            statement.execute();

        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao deletar a tarefa :(");
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
    }
}
