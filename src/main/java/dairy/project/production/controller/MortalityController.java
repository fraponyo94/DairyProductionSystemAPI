package dairy.project.production.controller;


import dairy.project.production.Service.MortalityService;
import dairy.project.production.entity.Mortality;
import dairy.project.production.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MortalityController {

    @Autowired
    private MortalityService mortalityService;

    //Save Mortality Record
    @PostMapping("/mortality")
    public Mortality saveMortalityRecord(@Valid @RequestBody Mortality mortality) throws ResourceNotFoundException {
        return mortalityService.save(mortality);
    }

    @GetMapping("/mortality")
    public List<Mortality>  mortalityrecords(){
        return mortalityService.findAll();
    }


}
