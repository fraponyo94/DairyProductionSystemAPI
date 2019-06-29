package dairy.project.production.controller;


import dairy.project.production.Service.ExpensesService;
import dairy.project.production.entity.Expenses;
import dairy.project.production.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class ExpensesController {
    @Autowired
    private ExpensesService expensesService;


    //Save
    @PostMapping("/expenses")
    public Expenses saveBreeding(@Valid @RequestBody Expenses expenses) {

        return expensesService.save(expenses);
    }
}
