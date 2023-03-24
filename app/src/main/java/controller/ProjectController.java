package controller;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Project;
import util.ConnectionFactory;

public class ProjectController {

    public void save(Project project) {

        String sql = "INSERT INTO projects (name, description, createdDate, updatedDate) VALUES (?, ?, ?, ?)";

        Connection connection = null;
        PreparedStatement statement = null;

        try {

            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);

            statement.setString(1, project.getName());
            statement.setString(2, project.getDescription());
            statement.setDate(3, new Date(project.getCreatedDate().getTime()));
            statement.setDate(4, new Date(project.getUpdatedDate().getTime()));
            statement.execute();

        } catch (SQLException ex) {

            throw new RuntimeException("Erro ao salvar o projeto :(" + ex.getMessage(), ex);

        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
    }

    public void update(Project project) {

        String sql = "UPDATE projects SET name = ?, description = ?, createdDate = ?, updatedDate = ? WHERE id = ?";

        Connection connection = null;
        PreparedStatement statement = null;

        try {

            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);

            statement.setString(1, project.getName());
            statement.setString(2, project.getIdDescription());
            statement.setDate(3, new Date(project.getCreatedDate().getTime()));
            statement.setDate(4, new Date(project.getUpdatedDate().getTime()));
            statement.setInt(5, project.getId());
            statement.execute();

        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao atualizar o projeto :(" + ex.getMessage(), ex);
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
    }

    public List<Project> getAll() {

        String sql = "SELECT * FROM projects";
        List<Project> projects = new ArrayList<>();

        try (Connection connection = ConnectionFactory.getConnection();  PreparedStatement statement = connection.prepareStatement(sql);  ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Project project = new Project();
                project.setId(resultSet.getInt("id"));
                project.setName(resultSet.getString("name"));
                project.setDescription(resultSet.getString("description"));
                project.setCreatedDate(resultSet.getDate("createdDate"));
                project.setUpdatedDate(resultSet.getDate("updatedDate"));
                projects.add(project);
            }

        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao buscar projetos :(" + ex.getMessage(), ex);
        }
        return projects;
    }

    public void removeById(int idProject) {

        String sql = "DELETE FROM projects WHERE id = ?";

        Connection connection = null;
        PreparedStatement statement = null;

        try {

            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);

            statement.setInt(1, idProject);
            statement.execute();

        } catch (SQLException ex) {

            throw new RuntimeException("Erro ao deletar o projeto :(" + ex.getMessage(), ex);

        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
    }

}
