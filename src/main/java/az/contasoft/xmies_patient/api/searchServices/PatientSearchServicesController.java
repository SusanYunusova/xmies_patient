package az.contasoft.xmies_patient.api.searchServices;

import az.contasoft.xmies_patient.api.infoService.internal.PatientInfo;
import az.contasoft.xmies_patient.api.searchServices.internal.*;
import az.contasoft.xmies_patient.api.searchServices.internalService.PatientSearchInternalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/searchServices")
public class PatientSearchServicesController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    PatientSearchInternalService patientSearchInternalService;

    @GetMapping("/getAll")
    public ResponseEntity<List<PatientInfo>> getAll() {

        logger.info("search -> controller -> All request : {}");
        return patientSearchInternalService.getAll();


    }

    @GetMapping("/getByIdPatient/{idPatient}")
    public ResponseSearchPatient getByIdPatient(@PathVariable("idPatient") long idPatient) {
        logger.info("search -> controller -> request : {}", idPatient);
        return patientSearchInternalService.getByIdPatient(idPatient);

    }


    @GetMapping("/getAllByIdPatientOrderByIdPatientDesc")
    public ResponseSearchListPatient getAllByOrderByIdPatientDesc() {
        logger.info("search -> controller -> All getAllByOrderByIdPatientDesc");

        return patientSearchInternalService.getAllByOrderByIdPatientDesc();

    }


    /**
     * Ad soyad ata adi fin kod veya telefon nomresi daxil edilib bir text olaraq gonderilecek ve bizde autoComplete uchun sechim 10 neticeni qaytaracayiq
     * @param enteredText
     * @return list of patients
     */

//        @GetMapping("/getFullName/{patientName}/{patientSurname}/{patientFatherName}/{patientPinCode}")
//        public ResponseSearchListPatient getFullName(@PathVariable("patientName") String patientName, @PathVariable("patientSurname") String patientSurname,
//                                                     @PathVariable("patientFatherName") String patientFatherName, @PathVariable("patientPinCode") String patientPinCode) {
//
//            logger.info("search -> controller -> All request");
//            return patientSearchInternalService.getFullName(patientName, patientSurname, patientFatherName, patientPinCode);
//        }
    @PostMapping("/getFullName")
    public ResponseEntity<List<PatientData>> getPatientFullName(@RequestBody RequestFullTextSearch enteredText) {
        logger.info("search -> controller -> All request");
        return patientSearchInternalService.getPatientFullNames(enteredText.getEnteredData());
    }

}
