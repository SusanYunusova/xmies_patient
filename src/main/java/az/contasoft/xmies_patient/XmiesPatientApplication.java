package az.contasoft.xmies_patient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients("az.contasoft.xmies_patient")
public class XmiesPatientApplication {

    public static void main(String[] args) {
        SpringApplication.run(XmiesPatientApplication.class, args);
    }

}
