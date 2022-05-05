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
public class ServiceService {
    private final ServiceRepo repo;

    public Optional<backend.artise.dto.Service> getById(int id){
        return repo.findById(id);
    }

    public backend.artise.dto.Service createService(backend.artise.dto.Service service) {
        backend.artise.dto.Service s = repo.saveAndFlush(service);
        return s;
    }

    public List<backend.artise.dto.Service> getServicesForCategory(Category category, Integer order, String sort_by) {
        if (sort_by.equals("")){sort_by="id";}
        Sort.Direction direction = Sort.Direction.ASC;
        if (order==1){direction = Sort.Direction.DESC;}
        return repo.findAllByCategory(category, Sort.by(direction, sort_by));
    }

    public List<backend.artise.dto.Service> getServicesForProfession(Profession profession, Integer order, String sort_by) {
        if (sort_by.equals("")){sort_by="id";}
        Sort.Direction direction = Sort.Direction.ASC;
        if (order==1){direction = Sort.Direction.DESC;}
        return repo.findAllByProfession(profession, Sort.by(direction, sort_by));
    }

    public List<backend.artise.dto.Service> getServicesForUser(User user, Integer order, String sort_by) {
        if (sort_by.equals("")){sort_by="id";}
        Sort.Direction direction = Sort.Direction.ASC;
        if (order==1){direction = Sort.Direction.DESC;}
        return repo.findAllByUser(user, Sort.by(direction, sort_by));
    }

    public List<backend.artise.dto.Service> getAllServices(Integer order, String sort_by) {
        if (sort_by.equals("")){sort_by="id";}
        Sort.Direction direction = Sort.Direction.ASC;
        if (order==1){direction = Sort.Direction.DESC;}
        return repo.findAll(Sort.by(direction, sort_by));
    }

}
