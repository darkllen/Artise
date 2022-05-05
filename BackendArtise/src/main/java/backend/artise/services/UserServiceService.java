package backend.artise.services;

import backend.artise.dto.*;
import backend.artise.repos.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceService {
    private final UserServiceRepo repo;

    public Optional<backend.artise.dto.UserService> getById(int id){
        return repo.findById(id);
    }
    public backend.artise.dto.UserService createUserService(backend.artise.dto.UserService userService) {
        backend.artise.dto.UserService uS = repo.saveAndFlush(userService);
        return uS;
    }

    public List<backend.artise.dto.UserService> getUnconfiremedOrders(User user, Integer order, String sort_by) {
        if (sort_by.equals("")){sort_by="id";}
        Sort.Direction direction = Sort.Direction.ASC;
        if (order==1){direction = Sort.Direction.DESC;}
        return repo.getUnconfiremedOrders(user, Sort.by(direction, sort_by));
    }

    public List<backend.artise.dto.UserService> getUnconfiremedServices(User user, Integer order, String sort_by) {
        if (sort_by.equals("")){sort_by="id";}
        Sort.Direction direction = Sort.Direction.ASC;
        if (order==1){direction = Sort.Direction.DESC;}
        return repo.getUnconfiremedServices(user, Sort.by(direction, sort_by));
    }

    public List<backend.artise.dto.UserService> getApprovedOrders(User user, Integer order, String sort_by) {
        if (sort_by.equals("")){sort_by="id";}
        Sort.Direction direction = Sort.Direction.ASC;
        if (order==1){direction = Sort.Direction.DESC;}
        return repo.getApprovedOrders(user, Sort.by(direction, sort_by));
    }

    public List<backend.artise.dto.UserService> getApprovedServices(User user, Integer order, String sort_by) {
        if (sort_by.equals("")){sort_by="id";}
        Sort.Direction direction = Sort.Direction.ASC;
        if (order==1){direction = Sort.Direction.DESC;}
        return repo.getApprovedServices(user, Sort.by(direction, sort_by));
    }

    public List<backend.artise.dto.UserService> getCancelledOrders(User user, Integer order, String sort_by) {
        if (sort_by.equals("")){sort_by="id";}
        Sort.Direction direction = Sort.Direction.ASC;
        if (order==1){direction = Sort.Direction.DESC;}
        return repo.getCancelledOrders(user, Sort.by(direction, sort_by));
    }

    public List<backend.artise.dto.UserService> getCancelledServices(User user, Integer order, String sort_by) {
        if (sort_by.equals("")){sort_by="id";}
        Sort.Direction direction = Sort.Direction.ASC;
        if (order==1){direction = Sort.Direction.DESC;}
        return repo.getCancelledServices(user, Sort.by(direction, sort_by));
    }

    public void cancell(backend.artise.dto.UserService userService) {
        userService.setStatus(2);
        repo.saveAndFlush(userService);
    }

    public void approve(backend.artise.dto.UserService userService) {
        userService.setStatus(1);
        repo.saveAndFlush(userService);
    }


}
