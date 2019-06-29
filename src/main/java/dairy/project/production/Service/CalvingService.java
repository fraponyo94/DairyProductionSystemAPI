package dairy.project.production.Service;


import dairy.project.production.Repository.BreedRepository;
import dairy.project.production.Repository.BreedingRepository;
import dairy.project.production.Repository.CalvingRepository;
import dairy.project.production.Repository.CowRepository;
import dairy.project.production.entity.Breed;
import dairy.project.production.entity.Breeding;
import dairy.project.production.entity.Calving;
import dairy.project.production.entity.Cow;
import dairy.project.production.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CalvingService {

    @Autowired
    private CalvingRepository calvingRepository;

    @Autowired
    private CowRepository cowRepository;
    @Autowired
    private BreedingRepository breedingRepository;

    @Autowired
    private BreedRepository breedRepository;


    // Find by cow Tag
    public List<Calving> findCalfByParent(String parentId){
        return calvingRepository.findByCow_CowTag(parentId);
    }




    //Save calf
    public Calving save(Calving calving) throws ResourceNotFoundException {
        Optional<Calving> calf = calvingRepository.findByCalfId(calving.getCalfId());
        if(!calf.isPresent()){
            //Find by corresponding cow
            Optional<Cow> cow = cowRepository.findById(calving.getCow().getCowTag());
            if (cow.isPresent()){
                //check corresponding breeding history
                Optional<Breeding> breeding = breedingRepository.findByCow_CowTagAndStatus(cow.get().getCowTag(),true);
                if(breeding.isPresent()){
                    breeding.get().setStatus(false);
                    calving.setBreeding(breeding.get());
                    calving.setCow(cow.get());

                    //Breed
                    Optional<Breed> breedExist  = breedRepository.findByName(calving.getBreed().getName());
                    if(breedExist.isPresent()){
                        calving.setBreed(breedExist.get());
                    }


                    return calvingRepository.save(calving);
                }else{
                    throw new ResourceNotFoundException("Breeding history corresponding to cow"+cow.get().getCowTag()+"was not found");
                }

            }else {
                throw new ResourceNotFoundException("");
            }


        }else{
            throw new ResourceNotFoundException("Calf with provided cow tag already exist in the database");
        }


    }


    // Find all
    public List<Calving> findAll(){
        return calvingRepository.findByDead(false);
    }
}
