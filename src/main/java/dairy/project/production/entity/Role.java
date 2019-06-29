package dairy.project.production.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dairy.project.production.Enumerated.RoleName;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Entity
@Table(name = "roles")
@Data
@NoArgsConstructor
public class Role {
	
	@Id
	@Column(name = "roleId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private Long id;

	@Enumerated(EnumType.STRING)
	@Column(name="role")
	private RoleName name;

	public Role(RoleName name) {
		this.name = name;
	}
}
