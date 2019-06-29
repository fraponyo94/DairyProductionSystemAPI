package dairy.project.production.controller;


import dairy.project.production.Service.CalvingService;
import dairy.project.production.entity.Calving;
import dairy.project.production.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api")
public class CalvingController {
    @Autowired
    private CalvingService calvingService;

    //Save calf
    @PostMapping("/calfs")
    public ResponseEntity<Calving> save(@Valid @RequestBody Calving calving) throws ResourceNotFoundException {
        return ResponseEntity.ok(calvingService.save(calving));
    }

    //Find calf by parent Id
    @PutMapping("/calfs/cow/{id}")
    public List<Calving> findByParentId(@PathVariable(value = "id") String cowId                                                  ) {
     return calvingService.findCalfByParent(cowId);
    }


    @GetMapping("/calfs")
    public List<Calving> findAll(){
        return calvingService.findAll();
    }




}
