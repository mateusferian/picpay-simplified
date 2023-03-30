package br.com.geradordedevs.picpaysimplified.dtos.requests;

import br.com.geradordedevs.picpaysimplified.enums.TypeOfUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO {

    private String name;

    private TypeOfUser typeOfUser;

    private Integer documentNumber;

    private String email;

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
