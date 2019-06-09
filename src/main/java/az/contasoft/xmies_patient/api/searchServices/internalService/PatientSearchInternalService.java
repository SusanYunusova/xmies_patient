package az.contasoft.xmies_patient.api.searchServices.internalService;

import az.contasoft.xmies_patient.api.infoService.internal.PatientInfo;
import az.contasoft.xmies_patient.api.infoService.internalService.Service;
import az.contasoft.xmies_patient.api.searchServices.internal.PatientData;
import az.contasoft.xmies_patient.api.searchServices.internal.ResponsePatientSearch;
import az.contasoft.xmies_patient.api.searchServices.internal.ResponseSearchListPatient;
import az.contasoft.xmies_patient.api.searchServices.internal.ResponseSearchPatient;
import az.contasoft.xmies_patient.api.util.HazelCastUtility;
import az.contasoft.xmies_patient.db.entity.Patient;
import az.contasoft.xmies_patient.db.repo.RepoPatient;
import com.hazelcast.core.IMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
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

    @PostConstruct
    public void init() {
        startCaching();
    }


    public ResponseEntity<List<PatientInfo>> getAll() {
        List<PatientInfo> list = service.getPatientList();
        if (list == null || list.size() == 0) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
    }

    //     Patient findByIdPatient(long idPatient);
    public ResponseEntity<Patient> getByIdPatient(long idPatient) {
        IMap<Long, Patient> patientIMap = HazelCastUtility.getMapOfPatient();
        Patient patient = patientIMap.get(idPatient);
        if (patient != null) {
            logger.info("searchByIdPatient response : {}", "found");
            return new ResponseEntity<>(patient, HttpStatus.OK);
        } else {
            Patient findByIdPatient = repoPatient.findByIdPatient(idPatient);
            if (findByIdPatient == null) {
                logger.info("searchByIdPatient response : {}", "yoxdudanaa");
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            } else {
                logger.info("Error searchByIdPatient response : {}", "founddd");
                HazelCastUtility.addOrUpdatePersonalToHazelCast(patient);
                return new ResponseEntity<>(patient, HttpStatus.OK);
            }

        }
    }

//    public void getFullName(String patientName, String patientSurname,
//                                                 String patientFatherName, String patientPinCode) {
//
//        ResponseSearchListPatient response = new ResponseSearchListPatient();
//        try {
//            List<Patient> listOfPatient = repoPatient.findByPatientNameAndPatientSurnameAndPatientFatherNameAndPatientPinCode(patientName, patientSurname, patientFatherName, patientPinCode);
//
//            if (listOfPatient == null) {
//
//                response.setListOfPatient(null);
//                response.setServerCode(404);
//                response.setServerMessage("patientName,patientSurname,patientFatherName,patientPinCode");
//                logger.info(" response : {}", response.toString());
//            } else {
//                response.setListOfPatient(listOfPatient);
//                response.setServerCode(200);
//                response.setServerMessage("fullName");
//
//                logger.info("Error response : {}", response.toString());
//
//
//            }
//            return response;
//        } catch (Exception e) {
//            response.setServerCode(400);
//            response.setServerMessage(e + "");
//            logger.info("error getting all patient: {}", e, e);
//            return response;
//        }
//
//    }

    /*



    List<Patient> findAllByOrderByIdPatientDesc(long idPatient);
     */

    /**
     * @return
     */

    public ResponseEntity<List<Patient>> getAllByOrderByIdPatientDesc() {
        try {
            List<Patient> listOfPatient = repoPatient.findAllByOrderByIdPatientDesc();


            if (listOfPatient == null || listOfPatient.isEmpty()) {
                logger.info(" response : {}", "tapilmadidanaaa");
               return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);

            } else {
                logger.info("Error response : {}", "tapildi");
                return new ResponseEntity<>(listOfPatient,HttpStatus.OK);


            }



        } catch (Exception e) {

            logger.info("error getting all patient: {}", e, e);
          return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
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
            List<PatientData> dataList = new ArrayList<>();
            for (PatientData patientData : list) {
                dataList.add(patientData);
                if (dataList.size() == 10) {
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

    private void initList() {
//        if(listOfPatients==null || listOfPatients.size()==0){
        listOfPatients = new ArrayList<>();
        List<PatientInfo> list = service.getPatientList();
        System.out.println("list size : " + list.size());
        list.forEach(patient -> {
            String data = patient.getPatientDetail();
            listOfPatients.add(new PatientData(patient.getIdPatient(), data));
        });
    }

    public IMap<Long, Patient> startCaching() {
        IMap<Long, Patient> mapOfPatient = HazelCastUtility.getMapOfPatient();
        if (mapOfPatient == null || mapOfPatient.isEmpty()) {
            List<Patient> list = repoPatient.findAll();
            for (Patient patient : list) {

                HazelCastUtility.addOrUpdatePersonalToHazelCast(patient);
            }
        }
        return mapOfPatient;
    }
}
