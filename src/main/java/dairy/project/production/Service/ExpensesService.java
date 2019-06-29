package dairy.project.production.Service;


import dairy.project.production.Repository.ExpenseRepository;
import dairy.project.production.entity.Expenses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExpensesService {

    @Autowired
    private ExpenseRepository expenseRepository;

    //Save
    public Expenses save(Expenses expenses) {
        return expenseRepository.save(expenses);
    }
}
