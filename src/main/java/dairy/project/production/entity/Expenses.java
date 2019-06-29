package dairy.project.production.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "otherExpenses")
@Data
@NoArgsConstructor
public class Expenses {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "expenseId")
    private int id;

    @Column(name = "itemName")
    private String itemName;

    @Column(name = "quantity")
    private double quantity;

    @Column(name = "pricePerUnit")
    private double pricePerUnit;

    @Column(name = "remarks")
    private String remarks;

    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    @JsonFormat
            (shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date date;


}
