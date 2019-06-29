package dairy.project.production.controller;

import dairy.project.production.Service.BreedingService;
import dairy.project.production.entity.Breeding;
import dairy.project.production.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class BreedingController {
//    @Autowired
//    private CowService cowService;

    @Autowired
    private BreedingService breedingService;

    //Save employee
    @PostMapping("/breedings")
    public Breeding saveBreeding(@Valid @RequestBody Breeding breeding) throws ResourceNotFoundException {
        System.out.println("null"+breeding.getCow());
        return breedingService.save(breeding);
    }


    @GetMapping("/breedings")
    public List<Breeding> findAll(){
        return breedingService.findAll();
    }


}
