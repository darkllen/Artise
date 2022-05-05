package backend.artise.repos;

import backend.artise.dto.*;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserServiceRepo extends JpaRepository<UserService, Integer> {

//    @Query("SELECT c FROM Connection c WHERE c.user_to_connect = ?1 and c.status = 0")
//    public List<Connection> getUnapprovedConnections(User user);
    @Query("SELECT us FROM UserService us WHERE us.user = ?1 and us.status = 0")
    List<UserService> getUnconfiremedOrders(User user, Sort by);

    @Query("SELECT us FROM UserService us WHERE us.service.userCategory.user = ?1 and us.status = 0")
    List<UserService> getUnconfiremedServices(User user, Sort by);

    @Query("SELECT us FROM UserService us WHERE us.user = ?1 and us.status = 1")
    List<UserService> getApprovedOrders(User user, Sort by);

    @Query("SELECT us FROM UserService us WHERE us.service.userCategory.user = ?1 and us.status = 1")
    List<UserService> getApprovedServices(User user, Sort by);

    @Query("SELECT us FROM UserService us WHERE us.user = ?1 and us.status = 2")
    List<UserService> getCancelledOrders(User user, Sort by);

    @Query("SELECT us FROM UserService us WHERE us.service.userCategory.user = ?1 and us.status = 2")
    List<UserService> getCancelledServices(User user, Sort by);

}
