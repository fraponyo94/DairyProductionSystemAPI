package dairy.project.production.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "cow")
@Data
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Cow {
    @Id
    @Column(name = "cowTag")
    private String cowTag;

    @Column(name = "cowName",nullable = false)
    private String name;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "breed")
    private Breed breed;

    @Temporal(TemporalType.DATE)
    @Column(name = "birthdate")
    @JsonFormat
            (shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date dateAcquired;


    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy = "cowhealth")
    private List<Health> healthList;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn( name = "mortality")
    private Mortality mortality;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy = "cow")
    private List<Milk> milk;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy = "cow")
    private List<Breeding> breedings;


    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy = "cow")
    private List<Calving> calf;

    @Column(name ="dead")
    private boolean dead;


    @Override
    public String toString() {
        return "Cow{" +
                "cowTag='" + cowTag + '\'' +
                ", name='" + name + '\'' +
                ", breed=" + breed +
                ", dateAcquired=" + dateAcquired +
                '}';
    }
}
