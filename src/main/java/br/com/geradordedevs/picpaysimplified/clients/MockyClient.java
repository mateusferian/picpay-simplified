package br.com.geradordedevs.picpaysimplified.clients;

import br.com.geradordedevs.picpaysimplified.dtos.responses.TransferResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "mocky.io", url = "https://run.mocky.io/v3")
public interface MockyClient {

    @GetMapping("/8fafdd68-a090-496f-8c9a-3442cf30dae6")
    TransferResponseDTO getMessage();
}
