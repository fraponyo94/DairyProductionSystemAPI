package dairy.project.production.controller;

import dairy.project.production.Service.HealthService;
import dairy.project.production.entity.Health;
import dairy.project.production.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class HealthController {

    @Autowired
    private HealthService healthService;


    //Save
    @PostMapping("/health")
    public Health saveHealthRecords(@Valid @RequestBody Health health) throws ResourceNotFoundException {
        return healthService.save(health);
    }


    // Find all
    @GetMapping("/health")
    public List<Health> findAll(){
        return  healthService.findAll();
    }
}
