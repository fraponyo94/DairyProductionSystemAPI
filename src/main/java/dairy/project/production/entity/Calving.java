package dairy.project.production.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="calf")
@Data
@NoArgsConstructor
public class Calving {

    @Id
    @Column(name = "breedingId")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "calfId")
    private  String  calfId;

    @Column(name = "dateofCalving")
    @Temporal(TemporalType.DATE)
    @JsonFormat
            (shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date dateOfCalving;

    @Column(name = "sex")
    private String sex;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "breed")
    private Breed breed;

    @Column(name="remarks")
    private String remarks;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy = "calfHealth")
    private List<Health> healthList;

    @OneToOne(targetEntity = Breeding.class, fetch = FetchType.LAZY)
    @JoinColumn(nullable = false,name = "breedingId")
    private Breeding breeding;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "cowId")
    private Cow cow;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn( name = "mortality")
    private Mortality mortality;

    @Column(name ="dead")
    private boolean dead;








}
