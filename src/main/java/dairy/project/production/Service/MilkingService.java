package dairy.project.production.Service;

import dairy.project.production.Repository.CowRepository;
import dairy.project.production.Repository.MilkRepository;
import dairy.project.production.entity.Cow;
import dairy.project.production.entity.Milk;
import dairy.project.production.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MilkingService {
    @Autowired
    private CowRepository cowRepository;
    @Autowired
    private MilkRepository milkRepository;


    //Save
    public Milk save(Milk milk) throws ResourceNotFoundException {
        String cowTag = milk.getCow().getCowTag();
        Optional<Cow> selectedCow = cowRepository.findById(cowTag);
        if(selectedCow.isPresent()){
            Optional<Milk> milkingRecords = milkRepository.findByDateAndCow_CowTag(milk.getDate(),cowTag);
            if (milkingRecords.isPresent()) {
               //Incase records exist
                Milk milk1 = milkingRecords.get();
                    milk1.setFirstMilking(milk.getFirstMilking());
                    milk1.setSecondMilking(milk.getSecondMilking());
                    milk1.setOtherMilking(milk.getOtherMilking());
                    milk1.setRemarks(milk.getRemarks());

                return milkRepository.save(milk1);
            } else {
                milk.setCow(selectedCow.get());
                return milkRepository.save(milk);
            }
        }else{
            throw new ResourceNotFoundException("Selected Cow with cowTag:"+ cowTag+ " not found");
        }


    }


    // Find by Cow and date
    public Milk findByCowTagAndDate(String cowTag, Date date)throws ResourceNotFoundException{
        return milkRepository.findByDateAndCow_CowTag(date,cowTag)
                .orElseThrow(()->new ResourceNotFoundException("No records found"));
    }


    // Find all
    public List<Milk> findAll() {
        //
        return milkRepository.findAll();
    }
}
