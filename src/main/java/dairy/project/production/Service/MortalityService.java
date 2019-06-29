package dairy.project.production.Service;

import dairy.project.production.Repository.CalvingRepository;
import dairy.project.production.Repository.CowRepository;
import dairy.project.production.Repository.MortalityRepository;
import dairy.project.production.entity.Calving;
import dairy.project.production.entity.Cow;
import dairy.project.production.entity.Mortality;
import dairy.project.production.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MortalityService {
    @Autowired
    private CowRepository cowRepository;
    @Autowired
    private MortalityRepository mortalityRepository;

    @Autowired
    private CalvingRepository calvingRepository;

    //Save
    public Mortality save(Mortality mortality) throws ResourceNotFoundException {
        String cowTag = mortality.getCow().getCowTag();
        if(cowTag != null) {
            Optional<Cow> selectedCow = cowRepository.findById(cowTag);
            if (selectedCow.isPresent()) {
                Optional<Mortality> mortalityRecords = mortalityRepository.findByCow_Dead(true);
                if (mortalityRecords.isPresent()) {
                    //Incase records exist
                } else {
                    mortality.setCow(selectedCow.get());
                    mortality.getCow().setDead(true);

                }
            } else {
                Optional<Calving> selectedCalf = calvingRepository.findByCalfId(mortality.getCalf().getCalfId());
                if (selectedCalf.isPresent()) {
                    //check whether records exist
                    Optional<Mortality> alreadyExist = mortalityRepository.findByCalf_Dead(true);
                    if (alreadyExist.isPresent()) {
                        //return some error message
                    } else {
                        mortality.setCalf(selectedCalf.get());
                        mortality.getCalf().setDead(true);
                    }
                }
                //throw new ResourceNotFoundException("Selected Cow with cowTag:"+ cowTag+ " not found");
            }
        }

        return mortalityRepository.save(mortality);
    }

    //Find all
    public List<Mortality> findAll() {
        return mortalityRepository.findAll();
    }
}
