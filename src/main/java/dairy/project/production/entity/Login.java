package dairy.project.production.entity;


import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class Login {
    @NotBlank
    private String username;

    @NotBlank
    private String password;


}