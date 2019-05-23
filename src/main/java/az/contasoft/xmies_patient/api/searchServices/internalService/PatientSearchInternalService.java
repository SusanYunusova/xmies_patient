package az.contasoft.xmies_patient.api.searchServices.internalService;

import az.contasoft.xmies_patient.api.searchServices.internal.PatientData;
import az.contasoft.xmies_patient.api.searchServices.internal.ResponsePatientSearch;
import az.contasoft.xmies_patient.api.searchServices.internal.ResponseSearchListPatient;
import az.contasoft.xmies_patient.api.searchServices.internal.ResponseSearchPatient;
import az.contasoft.xmies_patient.db.entity.Patient;
import az.contasoft.xmies_patient.db.repo.RepoPatient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PatientSearchInternalService {


    public static List<PatientData> listOfPatients = new ArrayList<>();


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

    ResponseSearchListPatient response = new ResponseSearchListPatient();
    try {
        List<Patient> listOfPatient = repoPatient.findByPatientNameAndPatientSurnameAndPatientFatherNameAndPatientPinCode(patientName, patientSurname, patientFatherName, patientPinCode);

    if (listOfPatient == null) {

        response.setListOfPatient(null);
        response.setServerCode(100);
        response.setServerMessage("patientName,patientSurname,patientFatherName,patientPinCode");
        logger.info(" response : {}", response.toString());
    } else {
        response.setListOfPatient(listOfPatient);
        response.setServerCode(200);
        response.setServerMessage("fullName");

        logger.info("Error response : {}", response.toString());


    }
    return response;
}catch (Exception e){
        response.setServerCode(100);
        response.setServerMessage(e + "");
        logger.info("error getting all patient: {}", e, e);
        return response;
    }

}

    /*



    List<Patient> findAllByOrderByIdPatientDesc(long idPatient);
     */

    /**
     *
     *
     * @return
     */

    public ResponseSearchListPatient getAllByOrderByIdPatientDesc( ) {
        ResponseSearchListPatient response = new ResponseSearchListPatient();
        try {
            List<Patient> listOfPatient = repoPatient.findAllByOrderByIdPatientDesc();


            if (listOfPatient == null||listOfPatient.isEmpty()) {

                response.setListOfPatient(null);
                response.setServerCode(100);
                response.setServerMessage("AllByOrderByIdPatientDesc");
                logger.info(" response : {}", response.toString());
            } else {
                response.setListOfPatient(listOfPatient);
                response.setServerCode(200);
                response.setServerMessage("AllByIdPatientOrderByIdPatientDesc");

                logger.info("Error response : {}", response.toString());


            }
            return response;


        } catch (Exception e) {
            response.setServerCode(100);
            response.setServerMessage(e + "");
            logger.info("error getting all patient: {}", e, e);
            return response;
        }


//todo try catch


    }

    public ResponsePatientSearch getPatientFullNames(String enteredText) {
        initList();
        String[] enteredTextMas = enteredText.split(" ");
//        System.out.println("mas : "+enteredTextMas[0]+" "+enteredTextMas[1]);
        List<PatientData> list = new ArrayList<>();
        ResponsePatientSearch response = new ResponsePatientSearch();
        try {
            list.addAll(listOfPatients);

            for (int j = 0; j < enteredTextMas.length; j++) {
                List<PatientData> newList = new ArrayList<>();
                for (PatientData patientData : list) {
                    if (patientData.getFullData().toLowerCase().contains(enteredTextMas[j].toLowerCase())) {
                        newList.add(patientData);
                    }
                }

                list = newList;

            }

            response = new ResponsePatientSearch(200, "List of patients", new ArrayList<>());
            logger.info("response : {}", response.toString());
            for (PatientData patientData : list) {
                response.getList().add(patientData);
                if (response.getList().size() == 10) {
                    return response;
                }
            }
            logger.info("response : {}", response.toString());
            return response;
        } catch (Exception e) {
            response.setServerCode(100);
            response.setServerMessage(e + "");
            logger.info("error getting fullname: {}", e, e);
            return response;

        }


    }

    private void initList(){
        if(listOfPatients==null || listOfPatients.size()==0){
            List<Patient> list = repoPatient.findAll();
            System.out.println("list size : "+list.size());
            list.forEach(patient -> {
                String data = patient.getBarcode()
                        .concat(" ")
                        .concat(patient.getPatientName())
                        .concat(" ")
                        .concat(patient.getPatientSurname())
                        .concat(" ")
                        .concat(patient.getPatientFatherName())
                        .concat(" ")
                        .concat(patient.getPatientMobilePhoneNumber());
                listOfPatients.add(new PatientData(patient.getIdPatient(),data));
                System.out.println("general list size : "+listOfPatients.size());
            });
        }
    }
}
