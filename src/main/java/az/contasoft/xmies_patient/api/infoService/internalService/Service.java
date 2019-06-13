package az.contasoft.xmies_patient.api.infoService.internalService;


import az.contasoft.xmies_patient.api.infoService.internal.PatientInfo;
import az.contasoft.xmies_patient.db.entity.Patient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Service {

    @Autowired
    CashService cashService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    public ResponseEntity<PatientInfo> getPatientInfoByidPatient(long idPatient){
        return cashService.getPatientInfoByidPatient(idPatient);
    }

    public List<PatientInfo> getPatientList(){
        logger.info("{}","Service -> getPatientList -> trying to get map as List of Patient...");
        return cashService.getMapAsList();
    }

    public ResponseEntity<PatientInfo> addingNewPatient(Patient patient){
        return cashService.addNewPatientToMap(patient);
    }

}
