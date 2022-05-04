package backend.artise.services;

import backend.artise.dto.Connection;
import backend.artise.dto.User;
import backend.artise.repos.CategoryRepo;
import backend.artise.repos.ConnectionRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.relational.core.sql.FalseCondition;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ConnectionService {
    private final ConnectionRepo repo;

    public Connection createConnection(User userInit, User userConnect){
        Connection connection = new Connection();
        connection.setUser_init(userInit);
        connection.setUser_to_connect(userConnect);
        connection.setStatus(0);
        return repo.save(connection);
    }

    public Connection getByIds(User userInit, User userConnect){
        return repo.getByIds(userInit, userConnect);
    }

    public Connection getById(Integer connectionId) {
        return repo.getById(connectionId);
    }

    public void approve(Connection connection) {
        connection.setStatus(1);
        repo.saveAndFlush(connection);
    }

    public void decline(Connection connection) {
        repo.delete(connection);
    }

    public List<Connection> getApprovedConnections(User user) {
        return repo.getApprovedConnections(user);
    }

    public List<Connection> getUnapprovedConnections(User user) {
        return repo.getUnapprovedConnections(user);
    }

    public List<Connection> getSentConnections(User user) {
        return repo.getSentConnections(user);
    }

    public boolean isConnected(User user, User user1) {
        try{
            return getByIds(user, user1).getStatus().equals(1);
        } catch (Exception e){
            try {
                return getByIds(user1, user).getStatus().equals(1);
            } catch (Exception e1){
                return false;
            }
        }
    }
}
