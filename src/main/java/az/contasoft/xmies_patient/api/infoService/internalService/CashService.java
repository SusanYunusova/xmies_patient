package az.contasoft.xmies_patient.api.infoService.internalService;

import az.contasoft.xmies_patient.api.infoService.internal.Address;
import az.contasoft.xmies_patient.api.infoService.internal.PatientInfo;
import az.contasoft.xmies_patient.api.infoService.internal.Properties;
import az.contasoft.xmies_patient.api.proxy.AddressProxy;
import az.contasoft.xmies_patient.api.proxy.PropertiesProxy;
import az.contasoft.xmies_patient.db.entity.Patient;
import az.contasoft.xmies_patient.db.repo.RepoPatient;
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
    public static Map<Long, PatientInfo> mapOfPatientInfo;



    private final RepoPatient repoPatient;
    private final AddressProxy addressProxy;
    private final PropertiesProxy propertiesProxy;


    public CashService(AddressProxy addressProxy, PropertiesProxy propertiesProxy, RepoPatient repoPatient) {
        this.addressProxy = addressProxy;
        this.propertiesProxy = propertiesProxy;
        this.repoPatient = repoPatient;
    }

    public ResponseEntity<PatientInfo> getPatientInfoByidPatient(long idPatient){
        PatientInfo patientInfo = getPatientInfo(idPatient);
        if(patientInfo==null){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(patientInfo,HttpStatus.OK);
        }
    }

    /**
     * adding new data to map
     */
    public ResponseEntity<PatientInfo> addNewPatientToMap(Patient patient){
        PatientInfo info = getPatientInfo(patient);
        mapOfPatientInfo.put(info.getIdPatient(),info);
        return new ResponseEntity<>(info,HttpStatus.OK);
    }

//Susan Yekkkkasdfghjkl  tebiiki

    /**
     * Patient obyektine esasen onun butun melumatlarinin alinmasi.
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
            responseAddress = addressProxy.getAddressIdAddress(patient.getIdPatientCurrentAddress());
            if (responseAddress.getStatusCodeValue() == 200) {
                logger.info("response for current address : {} ", responseAddress.toString());
                info.setIdPatientCurrentAddress(responseAddress.getBody());
            } else {
                logger.info("current not found for patient : {}", patient.getIdPatient());
            }
        }

        if (patient.getIdPatientRegistrationAddress() > 0) {
            responseAddress = addressProxy.getAddressIdAddress(patient.getIdPatientRegistrationAddress());
            if (responseAddress.getStatusCodeValue() == 200) {
                info.setIdPatientRegistrationAddress(responseAddress.getBody());
            }
        }

        if (patient.getIdPatientBirthPlace() > 0) {
            responseAddress = addressProxy.getAddressIdAddress(patient.getIdPatientBirthPlace());
            if (responseAddress.getStatusCodeValue() == 200) {
                info.setIdPatientBirthPlace(responseAddress.getBody());
            }
        }



/**
 * propertiden sechilecek melumatlari
 * burdan cekib goturur
 */

        ResponseEntity<Properties> responseEntity;

        if (patient.getPatientGenderProperty() > 0) {

            responseEntity = propertiesProxy.getByIdProperties(patient.getPatientGenderProperty());
            if (responseEntity.getStatusCodeValue() == 200) {
                info.setPatientGenderProperty(responseEntity.getBody());
            }

        }

        if (patient.getPatientCitizenshipProperty() > 0) {

            responseEntity = propertiesProxy.getByIdProperties(patient.getPatientCitizenshipProperty());
            if (responseEntity.getStatusCodeValue() == 200) {
                info.setPatientCitizenshipProperty(responseEntity.getBody());
            }
        }

        if (patient.getPatientIdTypeProperty() > 0) {
            responseEntity = propertiesProxy.getByIdProperties(patient.getPatientIdTypeProperty());
            if (responseEntity.getStatusCodeValue() == 200) {
                info.setPatientIdTypeProperty(responseEntity.getBody());
            }

        }
        if (patient.getPatientTypeProperty() > 0) {
            responseEntity = propertiesProxy.getByIdProperties(patient.getPatientTypeProperty());
            if (responseEntity.getStatusCodeValue() == 200) {
                info.setPatientTypeProperty(responseEntity.getBody());
            }
        }

        info.setDetails(info.getPatientDetail());

        return info;

    }


    /**
     * istenilen vaxt map in yeni Patient siyahisniin cashden silinib tekrar yenilenmesi uchun
     */
    public void refresh(){
        mapOfPatientInfo = null;
        mapOfPatientInfo = new HashMap<>();
        initMap();
    }

    /**
     * idPatient e esasen hazir olan cashda saxlanilan siyahidan yeni map dan PatientInfo nu elde elemek
     * @param idPatient
     * @return
     */
    private PatientInfo getPatientInfo(long idPatient){
        if(mapOfPatientInfo!=null){
            return mapOfPatientInfo.get(idPatient);
        }else{
            refresh();
            return mapOfPatientInfo.get(idPatient);
        }
    }


    /**
     * Map in tekrar yigilmasi
     */
    private void initMap(){
        try{
            logger.info("{}","trying to init map of patients");
            List<Patient> listOfPatients = repoPatient.findAllByOrderByIdPatientDesc();
            for (Patient patient : listOfPatients) {
                PatientInfo patientInfo = getPatientInfo(patient);
                mapOfPatientInfo.put(patientInfo.getIdPatient(),patientInfo);
            }
        }catch (Exception e){
            logger.error("Error {} : {}","init map",e,e);
        }
    }



    public List<PatientInfo> getMapAsList(){
        if(mapOfPatientInfo==null || mapOfPatientInfo.size()==0){
            refresh();
        }
        List<PatientInfo> listOfPatientInfo = new ArrayList<>();
        for (Long idPatient : mapOfPatientInfo.keySet()) {
            listOfPatientInfo.add(mapOfPatientInfo.get(idPatient));
        }
        return listOfPatientInfo;
    }

}
