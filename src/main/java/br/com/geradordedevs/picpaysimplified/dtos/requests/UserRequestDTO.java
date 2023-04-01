package br.com.geradordedevs.picpaysimplified.dtos.requests;

import br.com.geradordedevs.picpaysimplified.enums.TypeOfUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO {

    @NotBlank(message = "{white.field}")
    @Size(min = 3,max = 50, message = "{size.invalid}")
    private String name;

    private TypeOfUser typeOfUser;

    private Integer documentNumber;

    @NotNull(message = "{null.field}")
    @Email(message = "{invalid.email}")
    private String email;

    @NotBlank(message = "{white.field}")
    @Size(min = 8,max = 20, message = "{size.invalid}")
    private String password;

    @Override
    public String toString() {
        return "UserRequestDTO{" +
                "name='" + name + '\'' +
                ", typeOfUser=" + typeOfUser +
                ", email='" + email + '\'' +
                '}';
    }
}
