package br.com.geradordedevs.picpaysimplified.entities;

import br.com.geradordedevs.picpaysimplified.enums.TypeOfUser;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private TypeOfUser typeOfUser;

    private String documentNumber;

    private String email;

    private String password;

    private BigDecimal value;

    @Override
    public String toString() {
        return "UserRequestDTO{" +
                "name='" + name + '\'' +
                ", typeOfUser=" + typeOfUser +
                ", email='" + email + '\'' +
                '}';
    }
}
