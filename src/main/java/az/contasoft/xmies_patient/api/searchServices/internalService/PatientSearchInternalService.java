package az.contasoft.xmies_patient.api.searchServices.internalService;

import az.contasoft.xmies_patient.api.searchServices.internal.ResponseSearchListPatient;
import az.contasoft.xmies_patient.api.searchServices.internal.ResponseSearchPatient;
import az.contasoft.xmies_patient.db.entity.Patient;
import az.contasoft.xmies_patient.db.repo.RepoPatient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PatientSearchInternalService {
    @Autowired
    RepoPatient repoPatient;
    private Logger logger = LoggerFactory.getLogger(this.getClass());




    public ResponseSearchListPatient getAll() {
        ResponseSearchListPatient responseSearchListPatient = new ResponseSearchListPatient();
        logger.info(("search all patient:{})"), responseSearchListPatient.toString());
        try {
            List<Patient> patientList = repoPatient.findAll();
            if (patientList == null || patientList.isEmpty()) {
                responseSearchListPatient.setListOfPatient(null);
                responseSearchListPatient.setServerCode(210);
                responseSearchListPatient.setServerMessage("Patient not found");
                logger.info("search Patient: {}", responseSearchListPatient.toString());

            } else {
                responseSearchListPatient.setListOfPatient(patientList);
                responseSearchListPatient.setServerCode(200);
                responseSearchListPatient.setServerMessage("OK");
                logger.info("search Patient: {}", responseSearchListPatient.toString());
            }
            return responseSearchListPatient;

        } catch (Exception e) {
            responseSearchListPatient.setServerCode(100);
            responseSearchListPatient.setServerMessage(e + "");
            logger.info("error getting all patient: {}", e, e);
            return responseSearchListPatient;
        }

    }
//     Patient findByIdPatient(long idPatient);
    public ResponseSearchPatient getByIdPatient(long idPatient) {
        Patient findByIdPatient = repoPatient.findByIdPatient(idPatient);

        ResponseSearchPatient response = new ResponseSearchPatient();

        if (findByIdPatient == null) {
            response.setPatient(null);
            response.setServerCode(100);
            response.setServerMessage("IdPatient patient search");

            logger.info("searchByIdPatient response : {}", response.toString());

        } else {
            response.setPatient(findByIdPatient);
            response.setServerCode(200);
            response.setServerMessage(" Document found");
            logger.info("Error searchDocument response : {}", response.toString());
        }
        return response;
    }
//    List<Patient> findByPatientNameAndPatientSurnameAndPatientFatherNameAndPatientPinCode(String patientName,String patientSurname,String patientFatherName,String patientPinCode);
public ResponseSearchListPatient getFullName(String patientName, String patientSurname,
                                             String patientFatherName, String patientPinCode) {

    List<Patient> listOfPatient = repoPatient.findByPatientNameAndPatientSurnameAndPatientFatherNameAndPatientPinCode(patientName, patientSurname, patientFatherName, patientPinCode);
    ResponseSearchListPatient response = new ResponseSearchListPatient();

    if (listOfPatient == null) {

        response.setListOfPatient(null);
        response.setServerCode(100);
        response.setServerMessage("patientName,patientSurname,patientFatherName,patientPinCode");
        logger.info(" response : {}", response.toString());
    } else {
        response.setListOfPatient(listOfPatient);
        response.setServerCode(200);
        response.setServerMessage("fullname");

        logger.info("Error response : {}", response.toString());


    }
    return response;


}

    /*



    List<Patient> findAllByIdPatientOrderByIdPatientDesc(long idPatient);
     */

    public ResponseSearchListPatient getAllByIdPatientOrderByIdPatientDesc(long idPatient){
        List<Patient> listOfPatient = repoPatient.findAllByIdPatientOrderByIdPatientDesc(idPatient);
        ResponseSearchListPatient response = new ResponseSearchListPatient();

        if (listOfPatient == null) {

            response.setListOfPatient(null);
            response.setServerCode(100);
            response.setServerMessage("AllByIdPatientOrderByIdPatientDesc");
            logger.info(" response : {}", response.toString());
        } else {
            response.setListOfPatient(listOfPatient);
            response.setServerCode(200);
            response.setServerMessage("AllByIdPatientOrderByIdPatientDesc");

            logger.info("Error response : {}", response.toString());


        }
        return response;


    }








}
