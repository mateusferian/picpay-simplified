package br.com.geradordedevs.picpaysimplified.dtos.responses;

import br.com.geradordedevs.picpaysimplified.enums.TypeOfUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {

    private Long id;

    private String name;

    private TypeOfUser typeOfUser;

    private String email;

    private BigDecimal value;
}
