package az.contasoft.xmies_patient.api.proxy;


import az.contasoft.xmies_patient.api.infoService.internal.Address;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "netflix-zuul-api-gateway-server")
@RibbonClient(name = "xmies_address")
public interface AddressProxy {

    @GetMapping("/xmies_address/getAddressByIDAddress/{idAddress}")
    ResponseEntity<Address> getAddressIdAddress(@PathVariable("idAddress") long idAddress);

}
