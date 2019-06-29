package dairy.project.production.Service;

import dairy.project.production.Repository.BreedingRepository;
import dairy.project.production.Repository.CowRepository;
import dairy.project.production.entity.Breed;
import dairy.project.production.entity.Breeding;
import dairy.project.production.entity.Cow;
import dairy.project.production.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BreedingService {
    @Autowired
    private BreedingRepository breedingRepository;

    @Autowired
    private CowRepository cowRepository;


    //Save
    public Breeding save(Breeding breeding) throws ResourceNotFoundException {
       String cowTag = breeding.getCow().getCowTag();
        Optional<Cow>  selectedCow = cowRepository.findById(cowTag);
        if(selectedCow.isPresent()){
            Optional<Breeding> breedingHistory = breedingRepository.findByDateAndCow_CowTag(breeding.getDate(),cowTag);
            if (breedingHistory.isPresent()) {
                //error
               // return breedingHistory.get();
            } else {
                breeding.setCow(selectedCow.get());
                breeding.setStatus(true);

            }
         }else{
            throw new ResourceNotFoundException("Selected Cow with cowTag:"+ breeding.getCow().getCowTag()+ " not found");
        }

        return breedingRepository.save(breeding);
    }

    public List<Breeding> findAll() {
        return breedingRepository.findAll();
    }
}
