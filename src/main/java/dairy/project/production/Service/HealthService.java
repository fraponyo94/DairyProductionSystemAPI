package dairy.project.production.Service;

import dairy.project.production.Repository.CalvingRepository;
import dairy.project.production.Repository.CowRepository;
import dairy.project.production.Repository.HealthRepository;
import dairy.project.production.entity.Calving;
import dairy.project.production.entity.Cow;
import dairy.project.production.entity.Health;
import dairy.project.production.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HealthService {

    @Autowired
    private HealthRepository healthRepository;

    @Autowired
    private CalvingRepository calvingRepository;

    @Autowired
    private CowRepository  cowRepository;

    public Health save(Health health)throws ResourceNotFoundException {


        if (health.getCowhealth().getCowTag() != null) {

            Optional<Cow> selectedCow = cowRepository.findById(health.getCowhealth().getCowTag());
            if (selectedCow.isPresent()) {
                health.setCowhealth(selectedCow.get());

            }
        } else if (health.getCalfHealth().getCalfId() != null) {
            Optional<Calving> selectedCalf = calvingRepository.findByCalfId(health.getCalfHealth().getCalfId());
            if (selectedCalf.isPresent()) {
                health.setCalfHealth(selectedCalf.get());

            }
        }
        return healthRepository.save(health);
    }

    public List<Health> findAll() {
        return  healthRepository.findAll();
    }
}
