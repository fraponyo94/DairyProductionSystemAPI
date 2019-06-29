package dairy.project.production.controller;

import dairy.project.production.Service.CowService;
import dairy.project.production.entity.Cow;
import dairy.project.production.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class CowController implements Serializable {

    @Autowired
    private CowService cowService;


    //Save cow
    @PostMapping("/cows")
    public Cow saveCow(@Valid @RequestBody Cow cow) {
         System.out.println(cow);
        return cowService.save(cow);
    }

    //Update cow
    @PutMapping("/cows/{id}")
    public ResponseEntity<Cow> update(@PathVariable(value = "id") String cowId,
                                                   @Valid @RequestBody Cow cowDetails) throws ResourceNotFoundException {


        final Cow updatedCow = cowService.update(cowId,cowDetails);
        return ResponseEntity.ok(updatedCow);
    }


  //Find All
    @GetMapping("/cows")
    public List<Cow> findAll() {
        return cowService.findAll();
    }



    @GetMapping("/cows/{id}")
    public ResponseEntity<Cow> findById(@PathVariable(value = "id") String cowId) throws ResourceNotFoundException
             {
       Cow cow = cowService.findById(cowId);
        return ResponseEntity.ok().body(cow);
    }



    //delete cow
    @DeleteMapping("/cows/{id}")
    public Map<String, Boolean> remove(@PathVariable(value = "id") String cowId)
            throws ResourceNotFoundException {
        Cow cow = cowService.findById(cowId);

        cowService.removeRecord(cow);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        return response;
    }

}
