package com.example.demo.Service;

import com.example.demo.Exception.ResourceNotFoundException;
import com.example.demo.Model.Supervisor;
import com.example.demo.Repository.SupervisorRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SupervisorService {

    private SupervisorRepository supervisorRepository;

    public SupervisorService(SupervisorRepository supervisorRepository) {
        this.supervisorRepository = supervisorRepository;
    }

    public List<Supervisor> findAll() {
        return supervisorRepository.findAll();
    }

    public Supervisor findById(Long id) {
        return supervisorRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Supervisor not found"));
    }

    public Supervisor save(Supervisor supervisor) {
        return supervisorRepository.save(supervisor);
    }

    public void delete(Supervisor supervisor) {
        supervisorRepository.delete(supervisor);
    }
}

