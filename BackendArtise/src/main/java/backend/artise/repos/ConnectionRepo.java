package backend.artise.repos;

import backend.artise.dto.Category;
import backend.artise.dto.Connection;
import backend.artise.dto.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ConnectionRepo extends JpaRepository<Connection, Integer> {

    @Query("SELECT c FROM Connection c WHERE c.user_init = ?1 and c.user_to_connect = ?2")
    public Connection getByIds(User userInit, User userConnect);

    @Query("SELECT c FROM Connection c WHERE (c.user_init = ?1 or c.user_to_connect = ?1) and c.status = 1")
    public List<Connection> getApprovedConnections(User user);

    @Query("SELECT c FROM Connection c WHERE c.user_to_connect = ?1 and c.status = 0")
    public List<Connection> getUnapprovedConnections(User user);

    @Query("SELECT c FROM Connection c WHERE c.user_init = ?1 and c.status = 0")
    public List<Connection> getSentConnections(User user);
}
