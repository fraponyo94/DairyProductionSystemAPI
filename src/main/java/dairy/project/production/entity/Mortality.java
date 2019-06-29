package dairy.project.production.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "mortality")
@Data
@NoArgsConstructor
public class Mortality {
    @Id
    @Column(name = "cowId",nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "postMortemReport")
    private String postMortemreport;

    @Column(name = "findings")
    private String findings;

    @Temporal(TemporalType.DATE)
    @Column(name = "date")
    @JsonFormat
            (shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date date;


    @OneToOne( fetch = FetchType.LAZY)
    @JoinColumn(name = "cowId")
    private Cow cow;

    @OneToOne( fetch = FetchType.LAZY)
    @JoinColumn(name = "calfId")
    private Calving calf;
}
