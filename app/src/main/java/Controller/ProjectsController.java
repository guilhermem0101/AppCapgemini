/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;


import Model.Projects;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import util.ConnectionFactory;


/**
 *
 * @author Dead
 */
public class ProjectsController {
    
    public void save (Projects projects){
        
        String sql = "INSERT INTO projects (name, description, createdAt, updatedAt) VALUES (?, ?, ?, ?)";
        Connection connection =  null;
        PreparedStatement statement = null; 
        
        try {
            try { 
                
                
            }catch (Exception e) {
                throw new RuntimeException("Erro ao conectar");
            }
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1,projects.getName());
            statement.setString(2, projects.getDescription());
            statement.setDate(3,new Date(projects.getCreatedAt().getTime()));
            statement.setDate(4,new Date(projects.getUpdatedAt().getTime()));
            
            statement.execute();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar " + e);
        } finally{
            
            ConnectionFactory.closeConnection(connection, statement);
        }
        
    }
    
    public void update(Projects projects){
        
        String sql = "UPDATE tasks SET name = ?, description = ?, createdAt = ?, updatedAt = ? WHERE id = ?";
        Connection connection =  null;
        PreparedStatement statement = null; 
        
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            
            statement.setString(1,projects.getName());
            statement.setString(2, projects.getDescription());
            statement.setDate(3,new Date(projects.getCreatedAt().getTime()));
            statement.setDate(4,new Date(projects.getUpdatedAt().getTime()));
            statement.setInt(5, projects.getId());
            statement.execute();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao alterar");
        } finally{
            
            ConnectionFactory.closeConnection(connection, statement);
        }
    }
    
    public void deleteById(int projectId) {
        String sql = "DELETE FROM projects WHERE id = ?";
        
        Connection con =null;
        PreparedStatement statement = null;     
        
        try {
            con = ConnectionFactory.getConnection();
            statement = con.prepareStatement(sql);
            statement.setInt(1, projectId);
            statement.execute();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar");
        } finally{
            
            ConnectionFactory.closeConnection(con, statement);
        }
        
    }
    
    public List<Projects> getAll(){
        String sql = "SELECT * FROM projects";
        Connection connection =  null;
        PreparedStatement statement = null; 
        ResultSet resultSet = null;
        
        List<Projects> projects = new ArrayList<>();
        
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            
            while(resultSet.next()){
                Projects project = new Projects();
                
                project.setId(resultSet.getInt("id"));
                project.setName(resultSet.getString("name"));
                project.setDescription(resultSet.getString("description"));
                project.setCreatedAt(resultSet.getDate("createdAt"));
                project.setUpdatedAt(resultSet.getDate("updatedAt"));
                
                projects.add(project);
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao chamar os dados");
        } finally{
            
            ConnectionFactory.closeConnection(connection, statement, resultSet);
        }
    
        
        return projects;
    }
    
}
