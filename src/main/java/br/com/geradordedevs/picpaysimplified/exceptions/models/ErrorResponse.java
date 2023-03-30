package br.com.geradordedevs.picpaysimplified.exceptions.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
    private  Long timestamp;
    private Integer status;
    private String code;
    private String message;
    private List<ErrorObject> error;

}
