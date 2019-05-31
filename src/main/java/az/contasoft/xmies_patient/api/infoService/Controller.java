package az.contasoft.xmies_patient.api.infoService;

import az.contasoft.xmies_patient.api.infoService.internal.PatientInfo;
import az.contasoft.xmies_patient.api.infoService.internalService.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/patient-info")
public class Controller {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    Service service;


    /**
     *
     *
     *  ██░ ██ ▓█████  ██▓  ██████ ▓█████  ███▄    █  ▄▄▄▄   ▓█████  ██▀███    ▄████
     * ▓██░ ██▒▓█   ▀ ▓██▒▒██    ▒ ▓█   ▀  ██ ▀█   █ ▓█████▄ ▓█   ▀ ▓██ ▒ ██▒ ██▒ ▀█▒
     * ▒██▀▀██░▒███   ▒██▒░ ▓██▄   ▒███   ▓██  ▀█ ██▒▒██▒ ▄██▒███   ▓██ ░▄█ ▒▒██░▄▄▄░
     * ░▓█ ░██ ▒▓█  ▄ ░██░  ▒   ██▒▒▓█  ▄ ▓██▒  ▐▌██▒▒██░█▀  ▒▓█  ▄ ▒██▀▀█▄  ░▓█  ██▓
     * ░▓█▒░██▓░▒████▒░██░▒██████▒▒░▒████▒▒██░   ▓██░░▓█  ▀█▓░▒████▒░██▓ ▒██▒░▒▓███▀▒
     *  ▒ ░░▒░▒░░ ▒░ ░░▓  ▒ ▒▓▒ ▒ ░░░ ▒░ ░░ ▒░   ▒ ▒ ░▒▓███▀▒░░ ▒░ ░░ ▒▓ ░▒▓░ ░▒   ▒
     *  ▒ ░▒░ ░ ░ ░  ░ ▒ ░░ ░▒  ░ ░ ░ ░  ░░ ░░   ░ ▒░▒░▒   ░  ░ ░  ░  ░▒ ░ ▒░  ░   ░
     *  ░  ░░ ░   ░    ▒ ░░  ░  ░     ░      ░   ░ ░  ░    ░    ░     ░░   ░ ░ ░   ░
     *  ░  ░  ░   ░  ░ ░        ░     ░  ░         ░  ░         ░  ░   ░           ░
     *                                                     ░
     *   ██████  ▄▄▄      ▓██   ██▓    ███▄ ▄███▓ ▄▄▄      ▓██   ██▓    ███▄    █  ▄▄▄       ███▄ ▄███▓▓█████
     * ▒██    ▒ ▒████▄     ▒██  ██▒   ▓██▒▀█▀ ██▒▒████▄     ▒██  ██▒    ██ ▀█   █ ▒████▄    ▓██▒▀█▀ ██▒▓█   ▀
     * ░ ▓██▄   ▒██  ▀█▄    ▒██ ██░   ▓██    ▓██░▒██  ▀█▄    ▒██ ██░   ▓██  ▀█ ██▒▒██  ▀█▄  ▓██    ▓██░▒███
     *   ▒   ██▒░██▄▄▄▄██   ░ ▐██▓░   ▒██    ▒██ ░██▄▄▄▄██   ░ ▐██▓░   ▓██▒  ▐▌██▒░██▄▄▄▄██ ▒██    ▒██ ▒▓█  ▄
     * ▒██████▒▒ ▓█   ▓██▒  ░ ██▒▓░   ▒██▒   ░██▒ ▓█   ▓██▒  ░ ██▒▓░   ▒██░   ▓██░ ▓█   ▓██▒▒██▒   ░██▒░▒████▒
     * ▒ ▒▓▒ ▒ ░ ▒▒   ▓▒█░   ██▒▒▒    ░ ▒░   ░  ░ ▒▒   ▓▒█░   ██▒▒▒    ░ ▒░   ▒ ▒  ▒▒   ▓▒█░░ ▒░   ░  ░░░ ▒░ ░
     * ░ ░▒  ░ ░  ▒   ▒▒ ░ ▓██ ░▒░    ░  ░      ░  ▒   ▒▒ ░ ▓██ ░▒░    ░ ░░   ░ ▒░  ▒   ▒▒ ░░  ░      ░ ░ ░  ░
     * ░  ░  ░    ░   ▒    ▒ ▒ ░░     ░      ░     ░   ▒    ▒ ▒ ░░        ░   ░ ░   ░   ▒   ░      ░      ░
     *       ░        ░  ░ ░ ░               ░         ░  ░ ░ ░                 ░       ░  ░       ░      ░  ░
     *                     ░ ░                              ░ ░
     * @param idPatient
     * @return
     */


    @GetMapping("/patient/{id}")
    public ResponseEntity<PatientInfo> getPatientInfoByid(@PathVariable("id") long idPatient){
        logger.info("getting info by idPatient : {}",idPatient);
        return service.getPatientInfoByidPatient(idPatient);
    }
//Myellimin keyfi??

}
