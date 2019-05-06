package az.contasoft.xmies_patient.api.searchServices;

import az.contasoft.xmies_patient.api.searchServices.internal.ResponseSearchListPatient;
import az.contasoft.xmies_patient.api.searchServices.internal.ResponseSearchPatient;
import az.contasoft.xmies_patient.api.searchServices.internalService.PatientSearchInternalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/searchServices")
public class PatientSearchServicesController {

private Logger logger = LoggerFactory.getLogger(this.getClass());
@Autowired
PatientSearchInternalService patientSearchInternalService;
@GetMapping
    public ResponseSearchListPatient getAll() {

        logger.info("search -> controller -> All request : {}");
        return patientSearchInternalService.getAll();


    }
    @GetMapping("/getByIdPatient/{idPatient}")
    public ResponseSearchPatient getByIdPatient(@PathVariable("idPatient") long idPatient){
        logger.info("search -> controller -> request : {}", idPatient);
        return patientSearchInternalService.getByIdPatient(idPatient);

    }

@GetMapping("/getFullName/{patientName}/{patientSurname}/{patientFatherName}/{patientPinCode}")
    public ResponseSearchListPatient getFullName(@PathVariable("patientName") String patientName,@PathVariable("patientSurname") String patientSurname,
                                                 @PathVariable("patientFatherName")String patientFatherName, @PathVariable("patientPinCode")String patientPinCode) {

    logger.info("search -> controller -> All request");


    return patientSearchInternalService.getFullName(patientName,patientSurname,patientFatherName,patientPinCode);


}
@GetMapping("/getAllByIdPatientOrderByIdPatientDesc/{idPatient}")
    public ResponseSearchListPatient getAllByIdPatientOrderByIdPatientDesc(@PathVariable("idPatient") long idPatient){
    logger.info("search -> controller -> All getAllByIdPatientOrderByIdPatientDesc");

        return patientSearchInternalService.getAllByIdPatientOrderByIdPatientDesc(idPatient);

    }





}
