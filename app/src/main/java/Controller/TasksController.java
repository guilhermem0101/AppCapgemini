/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Tasks;
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
public class TasksController {
    
    public void save (Tasks task){
        
        String sql = "INSERT INTO tasks (idProject, name, descripition, completed, notes, deadline, createdAt, updatedAt) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        Connection connection =  null;
        PreparedStatement statement = null; 
        
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, task.getIdProject());
            statement.setString(2,task.getName());
            statement.setString(3, task.getDescripition());
            statement.setBoolean(4, task.isCompleted());
            statement.setString(5,task.getNotes());
            statement.setDate(6,new Date(task.getDeadline().getTime()));
            statement.setDate(7,new Date(task.getCreatedAt().getTime()));
            statement.setDate(8,new Date(task.getUpdatedAt().getTime()));
            statement.execute();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar");
        } finally{
            
            ConnectionFactory.closeConnection(connection, statement);
        }
        
    }
    
    public void update(Tasks task){
        
        String sql = "UPDATE tasks SET idProject = ?, name = ?, description = ?, completed = ?, notes = ?, deadline = ?, createdAt = ?, updatedAt = ? WHERE id = ?";
        Connection connection =  null;
        PreparedStatement statement = null; 
        
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, task.getIdProject());
            statement.setString(2,task.getName());
            statement.setString(3, task.getDescripition());
            statement.setBoolean(4, task.isCompleted());
            statement.setString(5,task.getNotes());
            statement.setDate(6,new Date(task.getDeadline().getTime()));
            statement.setDate(7,new Date(task.getCreatedAt().getTime()));
            statement.setDate(8,new Date(task.getUpdatedAt().getTime()));
            statement.setInt(9, task.getId());
            statement.execute();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao alterar");
        } finally{
            
            ConnectionFactory.closeConnection(connection, statement);
        }
    }
    
    public void deleteById(int taskId) {
        String sql = "DELETE FROM tasks WHERE id = ?";
        
        Connection con =null;
        PreparedStatement statement = null;     
        
        try {
            con = ConnectionFactory.getConnection();
            statement = con.prepareStatement(sql);
            statement.setInt(1, taskId);
            statement.execute();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar");
        } finally{
            
            ConnectionFactory.closeConnection(con, statement);
        }
        
    }
    
    public List<Tasks> getAll(int IdProject){
        String sql = "SELECT * FROM tasks WHERE idProject = ?";
        Connection connection =  null;
        PreparedStatement statement = null; 
        ResultSet resultSet = null;
        
        List<Tasks> tasks = new ArrayList<Tasks>();
        
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, IdProject);
            resultSet = statement.executeQuery();
            
            while(resultSet.next()){
                Tasks task = new Tasks();
                task.setId(resultSet.getInt("id"));
                task.setIdProject(resultSet.getInt("idProject"));
                task.setName(resultSet.getString("name"));
                task.setDescripition(resultSet.getString("descripition"));
                task.setNotes(resultSet.getString("notes"));
                task.setCompleted(resultSet.getBoolean("completed"));
                task.setCreatedAt(resultSet.getDate("createdAt"));
                task.setUpdatedAt(resultSet.getDate("updatedAt"));
                
                tasks.add(task);
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao chamar os dados");
        } finally{
            
            ConnectionFactory.closeConnection(connection, statement, resultSet);
        }
    
        
        return tasks;
    }
}
