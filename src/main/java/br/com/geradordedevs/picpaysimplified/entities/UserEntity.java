package br.com.geradordedevs.picpaysimplified.entities;

import br.com.geradordedevs.picpaysimplified.enums.TypeOfUser;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

    private Integer documentNumber;

    private String email;

    private String password;

    private BigDecimal value;

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", typeOfUser=" + typeOfUser +
                ", email='" + email + '\'' +
                '}';
    }
}
