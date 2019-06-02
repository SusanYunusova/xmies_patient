package az.contasoft.xmies_patient.api.crudServices;
import az.contasoft.xmies_patient.api.crudServices.internal.SavePatientRequest;
import az.contasoft.xmies_patient.api.crudServices.internal.UpdatePatientRequest;
import az.contasoft.xmies_patient.api.crudServices.internalService.PatientCrudInternalService;
import az.contasoft.xmies_patient.api.infoService.internal.PatientInfo;
import az.contasoft.xmies_patient.db.entity.Patient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
/**
 * CrudServiceController methods
 *
 */
@RestController
@RequestMapping("/crudServices")
public class PatientCrudServiceController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PatientCrudInternalService patientCrudInternalService;
    /**
     * Save
     *
     * @return

     */
    @PostMapping("/add")
    public ResponseEntity<PatientInfo> savePatient(@RequestBody SavePatientRequest savePatientRequest) {
        logger.info("xmies_patient->add->request : {}" + savePatientRequest.toString());
        return patientCrudInternalService.savePatient(savePatientRequest);

    }
    /**
     * update
     *
     * @return

     */
    @PostMapping("/update")
    public ResponseEntity<Patient> updateDocument(@RequestBody UpdatePatientRequest updatePatientRequest){
        logger.info("xmies_patient->update->request : {}" + updatePatientRequest.toString());
        return patientCrudInternalService.updatePatient(updatePatientRequest);
    }

    /**
     * deleting by id
     *
     * @return

     */
//Myellimin keyfi??
    @GetMapping("/delete/{idPatient}")
    public ResponseEntity<Patient> deletePatient(@PathVariable("idPatient") long idPatient){
        return patientCrudInternalService.deleteIdPatient(idPatient);
    }
}
