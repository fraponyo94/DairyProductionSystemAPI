package dairy.project.production.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "milk")
@Data
@NoArgsConstructor
public class Milk {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Temporal(TemporalType.DATE)
    @Column(name = "milkingDate")
    @JsonFormat
            (shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date date;

    @Column(name = "firstMilking")
    private double firstMilking;

    @Column(name = "secondMilking")
    private double secondMilking;

    @Column(name = "otherMilking")
    private double otherMilking;

    @Column(name="remarks",nullable = true)
    private String remarks;


    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "cowId")
    private Cow cow;

}
