package dairy.project.production.controller;

import dairy.project.production.Service.MilkingService;
import dairy.project.production.entity.Milk;
import dairy.project.production.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/api")
public class MilkingController {

    @Autowired
    private MilkingService milkingService;

    //Save
    @PostMapping("/milking")
    public Milk saveBreeding(@Valid @RequestBody Milk milking) throws ResourceNotFoundException {

        return milkingService.save(milking);
    }

    @GetMapping("/milking")
    public List<Milk> findAll(){
        return  milkingService.findAll();

    }

    // Find Records given cowTag and date
    @GetMapping("/milking/{tag}/{date}")
   public Milk getRecordByCowTagAndDate(@PathVariable String tag, @PathVariable Date date) throws ResourceNotFoundException {

        return milkingService.findByCowTagAndDate(tag,date);

    }
}
