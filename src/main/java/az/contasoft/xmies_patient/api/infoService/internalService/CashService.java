package az.contasoft.xmies_patient.api.infoService.internalService;

import az.contasoft.xmies_patient.api.infoService.internal.Address;
import az.contasoft.xmies_patient.api.infoService.internal.PatientInfo;
import az.contasoft.xmies_patient.api.infoService.internal.Properties;
import az.contasoft.xmies_patient.api.proxy.AddressProxy;
import az.contasoft.xmies_patient.api.proxy.PropertiesProxy;
import az.contasoft.xmies_patient.db.entity.Patient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CashService {


    public static Map<Long, PatientInfo> mapOfPatientInfo;


    private final AddressProxy addressProxy;


    private final PropertiesProxy propertiesProxy;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public CashService(AddressProxy addressProxy, PropertiesProxy propertiesProxy) {
        this.addressProxy = addressProxy;
        this.propertiesProxy = propertiesProxy;
    }

    public ResponseEntity<PatientInfo> getPatientInfo(long idPatient){

    }


    public PatientInfo getPatientInfo(Patient patient) {

        PatientInfo info = new PatientInfo();

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

        return info;

    }


    public void refresh(){
        mapOfPatientInfo = null;
        initMap();
    }

    public PatientInfo getPatientInfo(long idPatient){
        if(mapOfPatientInfo!=null){
            return mapOfPatientInfo.get(idPatient);
        }else{
            return null;
        }
    }


}
