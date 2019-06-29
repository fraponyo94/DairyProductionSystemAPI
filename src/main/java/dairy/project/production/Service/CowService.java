package dairy.project.production.Service;


import dairy.project.production.Repository.BreedRepository;
import dairy.project.production.Repository.CowRepository;
import dairy.project.production.entity.Breed;
import dairy.project.production.entity.Cow;
import dairy.project.production.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CowService {

    @Autowired
    private CowRepository cowRepository;

    @Autowired
    private BreedRepository breedRepository;


    //Save
    public Cow save(Cow cow){
        System.out.println(cow.getCowTag());
       Optional<Cow> cowAlreadyExist = cowRepository.findById(cow.getCowTag());
        if(cowAlreadyExist.isPresent()){
            //error
        }else{
            Optional<Breed> breedExist  = breedRepository.findByName(cow.getBreed().getName());
            // cowAlreadyExist = new Cow();
            if(breedExist.isPresent()) {
                //cowAlreadyExist.setBreed(breed);
                cow.setBreed(breedExist.get());
            }
        }



        return  cowRepository.save(cow);

    }



    //Update
    public Cow update(String cowId, Cow cowdetails) throws ResourceNotFoundException {
        Cow cow = cowRepository.findById(cowId).orElseThrow(() -> new ResourceNotFoundException("Cow with id:: " + cowId + " Not FOUND"));


            cow.setBreed(cowdetails.getBreed());

            cow.setName(cowdetails.getName());

           return cowRepository.save(cow);
    }

   //Find given id
    public Cow findById(String cowId) throws ResourceNotFoundException {
      return cowRepository.findById(cowId).orElseThrow(() -> new ResourceNotFoundException("Cow with id:: " + cowId + " Not FOUND"));

    }



    //Remove Cow
    public void removeRecord(Cow cow){
        cowRepository.delete(cow);

    }


    //Find all cow records
    public List<Cow> findAll() {
        return cowRepository.findByDead(false);
    }


}
