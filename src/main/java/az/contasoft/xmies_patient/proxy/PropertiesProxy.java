package az.contasoft.xmies_patient.proxy;


import az.contasoft.xmies_patient.api.infoService.internal.Properties;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "netflix-zuul-api-gateway-server")
@RibbonClient(name = "xmies_properties")
public interface PropertiesProxy {

    @GetMapping("/xmies_properties/searchServices/getByIdProperties/{idProperties}")
    ResponseEntity<Properties> getByIdProperties(@PathVariable("idProperties") long idProperties);



}
