package az.contasoft.xmies_patient.api.infoService.internalService;

import az.contasoft.xmies_patient.api.infoService.internal.Address;
import az.contasoft.xmies_patient.api.infoService.internal.PatientInfo;
import az.contasoft.xmies_patient.api.infoService.internal.Properties;
import az.contasoft.xmies_patient.api.proxy.AddressProxy;
import az.contasoft.xmies_patient.api.proxy.PropertiesProxy;
import az.contasoft.xmies_patient.api.util.HazelCastUtility;
import az.contasoft.xmies_patient.db.entity.Patient;
import az.contasoft.xmies_patient.db.repo.RepoPatient;
import com.hazelcast.core.IMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CashService {


    private Logger logger = LoggerFactory.getLogger(this.getClass());
    public static IMap<Long, PatientInfo> mapOfPatientInfo;
    public static IMap<Long, Patient> mapOfPatient;


    private final RepoPatient repoPatient;
    private final AddressProxy addressProxy;
    private final PropertiesProxy propertiesProxy;


    public CashService(AddressProxy addressProxy, PropertiesProxy propertiesProxy, RepoPatient repoPatient) {
        this.addressProxy = addressProxy;
        this.propertiesProxy = propertiesProxy;
        this.repoPatient = repoPatient;
    }

    public ResponseEntity<PatientInfo> getPatientInfoByidPatient(long idPatient) {
        PatientInfo patientInfo = getPatientInfo(idPatient);
        if (patientInfo == null) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(patientInfo, HttpStatus.OK);
        }
    }

    /**
     * adding new data to map
     */
    public ResponseEntity<PatientInfo> addNewPatientToMap(Patient patient) {
        PatientInfo info = getPatientInfo(patient);
        HazelCastUtility.addOrUpdatePatientInfoToHazelCast(info);
//        mapOfPatientInfo.put(info.getIdPatient(),info);
        return new ResponseEntity<>(info, HttpStatus.OK);
    }

//Susan Yekkkkasdfghjkl  tebiiki

    /**
     * Patient obyektine esasen onun butun melumatlarinin alinmasi.
     *
     * @param patient
     * @return
     */
    private PatientInfo getPatientInfo(Patient patient) {

        PatientInfo info = new PatientInfo();

        info.setIdPatient(patient.getIdPatient());
        info.setBarcode(patient.getBarcode());
        info.setPatientName(patient.getPatientName());
        info.setPatientSurname(patient.getPatientSurname());
        info.setPatientFatherName(patient.getPatientFatherName());
        info.setPatientBirthDate(patient.getPatientBirthDate());
        info.setPatientPinCode(patient.getPatientPinCode());
        info.setPatientMotherName(patient.getPatientMotherName());
        info.setPatientMotherSurname(patient.getPatientMotherSurname());
        info.setPatientHomePhoneNumber(patient.getPatientHomePhoneNumber());
        info.setPatientMobilePhoneNumber(patient.getPatientMobilePhoneNumber());
        info.setPatientEmail(patient.getPatientEmail());


        ResponseEntity<Address> responseAddress = null;
        /**
         * xestenin addresse gedecek 3addresini adres servisine gonderirem.
         * Dogum yeri
         * qeydiyyatda oldugu yer
         * yashadigi yer
         */
        if (patient.getIdPatientCurrentAddress() > 0) {
            Address address = HazelCastUtility.getAddressFromHazelCast(patient.getIdPatientCurrentAddress());
            if (address == null) {
                responseAddress = addressProxy.getAddressIdAddress(patient.getIdPatientCurrentAddress());
                if (responseAddress.getStatusCodeValue() == 200) {
                    logger.info("response for current address : {} ", responseAddress.toString());
                    info.setIdPatientCurrentAddress(responseAddress.getBody());
                } else {
                    logger.info("current not found for patient : {}", patient.getIdPatient());
                }
            } else {
                info.setIdPatientCurrentAddress(address);
            }
        }

        //todo
        if (patient.getIdPatientRegistrationAddress() > 0) {
            Address address = HazelCastUtility.getAddressFromHazelCast(patient.getIdPatientRegistrationAddress());
            if (address == null) {
                responseAddress = addressProxy.getAddressIdAddress(patient.getIdPatientRegistrationAddress());
                if (responseAddress.getStatusCodeValue() == 200) {
                    logger.info("response for regist address : {} ", responseAddress.toString());
                    info.setIdPatientRegistrationAddress(responseAddress.getBody());
                } else {
                    logger.info("registAddress not found for patient : {}", patient.getIdPatient());
                }
            } else {
                info.setIdPatientRegistrationAddress(address);
            }

        }


        if (patient.getIdPatientBirthPlace() > 0) {
            Address address = HazelCastUtility.getAddressFromHazelCast(patient.getIdPatientBirthPlace());
            if (address == null) {
                responseAddress = addressProxy.getAddressIdAddress(patient.getIdPatientBirthPlace());
                if (responseAddress.getStatusCodeValue() == 200) {
                    logger.info("response for birthPlace address : {} ", responseAddress.toString());
                    info.setIdPatientBirthPlace(responseAddress.getBody());
                } else {
                    logger.info("birthPlace not found for patient : {}", patient.getIdPatient());

                }
            } else {
                info.setIdPatientBirthPlace(address);
            }
        }
/**
 * propertiden sechilecek melumatlari
 * burdan cekib goturur
 */
        ResponseEntity<Properties> responseEntity;

        if (patient.getPatientGenderProperty() > 0) {
            Properties properties = HazelCastUtility.getPropertyFromHazelCast(patient.getPatientGenderProperty());
            if (properties == null) {
                responseEntity = propertiesProxy.getByIdProperties(patient.getPatientGenderProperty());
                if (responseEntity.getStatusCodeValue() == 200) {
                    logger.info("response for gender property : {} ", responseEntity.toString());
                    info.setPatientGenderProperty(responseEntity.getBody());

                } else {
                    logger.info("gender not found for patient : {}", patient.getIdPatient());
                }

            } else {
                info.setPatientGenderProperty(properties);
            }

        }

        if (patient.getPatientCitizenshipProperty() > 0) {
            Properties properties = HazelCastUtility.getPropertyFromHazelCast(patient.getPatientCitizenshipProperty());
            if (properties == null) {
                responseEntity = propertiesProxy.getByIdProperties(patient.getPatientCitizenshipProperty());
                if (responseEntity.getStatusCodeValue() == 200) {
                    logger.info("response for cizitizenship property : {} ", responseEntity.toString());
                    info.setPatientCitizenshipProperty(responseEntity.getBody());
                } else {
                    logger.info("citizienship not found for patient : {}", patient.getIdPatient());
                }
            } else {
                info.setPatientCitizenshipProperty(properties);
            }
        }
        if (patient.getPatientIdTypeProperty() > 0) {
            Properties properties = HazelCastUtility.getPropertyFromHazelCast(patient.getPatientIdTypeProperty());
            if (properties == null) {
                responseEntity = propertiesProxy.getByIdProperties(patient.getPatientIdTypeProperty());
                if (responseEntity.getStatusCodeValue() == 200) {
                    logger.info("response for idType property : {} ", responseEntity.toString());
                    info.setPatientIdTypeProperty(responseEntity.getBody());
                } else {
                    logger.info("idType not found for patient : {}", patient.getIdPatient());
                }
            } else {
                info.setPatientIdTypeProperty(properties);
            }
        }

        if (patient.getPatientTypeProperty() > 0) {
            Properties properties = HazelCastUtility.getPropertyFromHazelCast(patient.getPatientTypeProperty());
            if (properties == null) {
                responseEntity = propertiesProxy.getByIdProperties(patient.getPatientTypeProperty());
                if (responseEntity.getStatusCodeValue() == 200) {
                    logger.info("response for patientType property : {} ", responseEntity.toString());
                    info.setPatientTypeProperty(responseEntity.getBody());
                } else {
                    logger.info("patientType not found for patient : {}", patient.getIdPatient());

                }
            } else {
                info.setPatientTypeProperty(properties);
            }
        }

        info.setDetails(info.getPatientDetail());

        return info;

    }


    /**
     * istenilen vaxt map in yeni Patient siyahisniin cashden silinib tekrar yenilenmesi uchun
     */
//    public void refresh(){
//        mapOfPatientInfo = null;
//        mapOfPatientInfo = new HashMap<>();
//        initMap();
//    }

    /**
     * idPatient e esasen hazir olan cashda saxlanilan siyahidan yeni map dan PatientInfo nu elde elemek
     *
     * @param idPatient
     * @return
     */
    private PatientInfo getPatientInfo(long idPatient) {
        mapOfPatientInfo = HazelCastUtility.getMapOfPatientInfo();
        if (mapOfPatientInfo != null) {
            return mapOfPatientInfo.get(idPatient);
        } else {
//            refresh();
            startCaching();
            return mapOfPatientInfo.get(idPatient);
        }
    }


    /**
     * Map in tekrar yigilmasi
     */
//    private void initMap(){
//        try{
//            logger.info("{}","trying to init map of patients");
//            List<Patient> listOfPatients = repoPatient.findAllByOrderByIdPatientDesc();
//            for (Patient patient : listOfPatients) {
//                PatientInfo patientInfo = getPatientInfo(patient);
//                mapOfPatientInfo.put(patientInfo.getIdPatient(),patientInfo);
//            }
//        }catch (Exception e){
//            logger.error("Error {} : {}","init map",e,e);
//        }
//    }
    public IMap<Long, Patient> startCaching() {
        IMap<Long, Patient> mapOfPatient = HazelCastUtility.getMapOfPatient();
        if (mapOfPatient == null || mapOfPatient.isEmpty()) {
            List<Patient> list = repoPatient.findAllByOrderByIdPatientDesc();
            for (Patient patient : list) {
                HazelCastUtility.addOrUpdatePersonalToHazelCast(patient);
                HazelCastUtility.addOrUpdatePatientInfoToHazelCast(getPatientInfo(patient));
            }
        }
        return mapOfPatient;
    }


    public List<PatientInfo> getMapAsList() {
        mapOfPatientInfo = HazelCastUtility.getMapOfPatientInfo();
        if (mapOfPatientInfo == null || mapOfPatientInfo.isEmpty()) {
//            refresh();
            startCaching();
        }
        List<PatientInfo> listOfPatientInfo = new ArrayList<>();
        for (Long idPatient : mapOfPatientInfo.keySet()) {
            listOfPatientInfo.add(mapOfPatientInfo.get(idPatient));
        }
        return listOfPatientInfo;
    }

}
