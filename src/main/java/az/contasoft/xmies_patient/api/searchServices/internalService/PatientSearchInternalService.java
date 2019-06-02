package az.contasoft.xmies_patient.api.searchServices.internalService;

import az.contasoft.xmies_patient.api.infoService.internal.PatientInfo;
import az.contasoft.xmies_patient.api.infoService.internalService.Service;
import az.contasoft.xmies_patient.api.searchServices.internal.PatientData;
import az.contasoft.xmies_patient.api.searchServices.internal.ResponsePatientSearch;
import az.contasoft.xmies_patient.api.searchServices.internal.ResponseSearchListPatient;
import az.contasoft.xmies_patient.api.searchServices.internal.ResponseSearchPatient;
import az.contasoft.xmies_patient.db.entity.Patient;
import az.contasoft.xmies_patient.db.repo.RepoPatient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PatientSearchInternalService {


    public List<PatientData> listOfPatients = new ArrayList<>();


    @Autowired
    RepoPatient repoPatient;

    @Autowired
    Service service;

    private Logger logger = LoggerFactory.getLogger(this.getClass());




    public ResponseEntity<List<PatientInfo>> getAll() {
        List<PatientInfo> list = service.getPatientList();
        if(list == null || list.size()==0){
            return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(list,HttpStatus.OK);
        }
    }
//     Patient findByIdPatient(long idPatient);
    public ResponseSearchPatient getByIdPatient(long idPatient) {
        Patient findByIdPatient = repoPatient.findByIdPatient(idPatient);

        ResponseSearchPatient response = new ResponseSearchPatient();

        if (findByIdPatient == null) {
            response.setPatient(null);
            response.setServerCode(400);
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
        response.setServerCode(404);
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
        response.setServerCode(400);
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
                response.setServerCode(404);
                response.setServerMessage("AllByOrderByIdPatientDesc not found");
                logger.info(" response : {}", response.toString());
            } else {
                response.setListOfPatient(listOfPatient);
                response.setServerCode(200);
                response.setServerMessage("AllByIdPatientOrderByIdPatientDesc found");

                logger.info("Error response : {}", response.toString());


            }
            return response;


        } catch (Exception e) {
            response.setServerCode(400);
            response.setServerMessage(e + "");
            logger.info("error getting all patient: {}", e, e);
            return response;
        }


//todo try catch


    }

    public ResponseEntity<List<PatientData>> getPatientFullNames(String enteredText) {
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
            List<PatientData>  dataList=new ArrayList<>();
            for (PatientData patientData : list) {
               dataList.add(patientData);
                if( dataList.size() == 10) {
                    return new ResponseEntity<>(dataList, HttpStatus.OK);
                }
            }
            logger.info("response : {}", response.toString());
            return new ResponseEntity<>(dataList, HttpStatus.OK);
        } catch (Exception e) {
//            response.setServerCode(400);
//            response.setServerMessage(e + "");
            logger.info("error getting fullname: {}", e, e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

        }


    }

    private void initList(){
//        if(listOfPatients==null || listOfPatients.size()==0){
        listOfPatients = new ArrayList<>();
            List<PatientInfo> list = service.getPatientList();
            System.out.println("list size : "+list.size());
            list.forEach(patient -> {
                String data = patient.getPatientDetail();
                listOfPatients.add(new PatientData(patient.getIdPatient(),data));
//                System.out.println("general list size : "+listOfPatients.size());
            });
//        }
    }
}
