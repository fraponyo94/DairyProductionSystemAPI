package dairy.project.production.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "breeding")
@Data
@NoArgsConstructor
public class Breeding {



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "breedingId")
    private int id;

    @Column(name = "servicingDate")
    @Temporal(TemporalType.DATE)
    @JsonFormat
            (shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date date;

    @Column(name = "dueDate")
    @Temporal(TemporalType.DATE)
    @JsonFormat
            (shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date dueDate;

    @Column(name = "methodofInsemination")
    private String methodOfInsemination;

    @Column(name = "reproductiveCondition")
    private String reproductiveCondition;


    @Column(name = "reproductiveTreatmentOffered")
    private String reproductiveTreatment;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "cowId")
    private Cow cow;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Calving calf;


    @Column(name="active")
    private boolean status;

}
