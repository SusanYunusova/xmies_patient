package az.contasoft.xmies_patient.api.crudServices.internalService;

import az.contasoft.xmies_patient.api.crudServices.internal.PatientResponse;
import az.contasoft.xmies_patient.api.crudServices.internal.SavePatientRequest;
import az.contasoft.xmies_patient.api.crudServices.internal.UpdatePatientRequest;
import az.contasoft.xmies_patient.db.entity.Patient;
import az.contasoft.xmies_patient.db.repo.RepoPatient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PatientCrudInternalService {

    @Autowired
    RepoPatient repoPatient;

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    /**
     * SavePatient entties
     *
     * return @patientResponse
     */

    public PatientResponse savePatient(SavePatientRequest savePatientRequest) {
        PatientResponse patientResponse = new PatientResponse();
        try {
            Patient patient = new Patient();



            // patient.setIdPatient(savePatientRequest.getIdPatient());

            patient.setPatientName(savePatientRequest.getPatientName());
            patient.setPatientSurname(savePatientRequest.getPatientSurname());
            patient.setBarcode(savePatientRequest.getBarcode());
            patient.setPatientFatherName(savePatientRequest.getPatientFatherName());
            patient.setPatientIdTypeProperty(savePatientRequest.getPatientIdTypeProperty());
            patient.setPatientBirthDate(savePatientRequest.getPatientBirthDate());
            patient.setPatientNo(savePatientRequest.getPatientNo());
            patient.setIdPatientCurrentAddress(savePatientRequest.getIdPatientCurrentAddress());
            patient.setPatientEmail(savePatientRequest.getPatientEmail());
            patient.setPatientCitizenshipProperty(savePatientRequest.getPatientCitizenshipProperty());
            patient.setIdPatientRegistrationAddress(savePatientRequest.getIdPatientRegistrationAddress());
            patient.setPatientGenderProperty(savePatientRequest.getPatientGenderProperty());

            patient.setPatientBirthDate(savePatientRequest.getPatientBirthDate());
            patient.setIdPatientBirthPlace(savePatientRequest.getIdPatientBirthPlace());
            patient.setPatientHomePhoneNumber(savePatientRequest.getPatientHomePhoneNumber());
            patient.setPatientMobilePhoneNumber(savePatientRequest.getPatientMobilePhoneNumber());
            patient.setPatientMotherName(savePatientRequest.getPatientMotherName());
            patient.setPatientMotherSurname(savePatientRequest.getPatientMotherSurname());
            patient.setPatientTypeProperty(savePatientRequest.getPatientTypeProperty());
            patient.setPatientPinCode(savePatientRequest.getPatientPinCode());


            patient = repoPatient.save(patient);
            patientResponse.setServerCode(200);
            patientResponse.setServerMessage("OK");
            patientResponse.setPatient(patient);
            logger.info("savePatient response : {}", savePatientRequest.toString());


        } catch (Exception e) {
            patientResponse.setServerCode(100);
            patientResponse.setServerMessage("error");
            logger.error("Error save savePatient : {}", e,e);
        }
        return patientResponse;
    }


    /**
     * UpdatePatient entties
     *
     * return @patientResponse
     */

    public PatientResponse updatePatient(UpdatePatientRequest updatePatientRequest) {
        PatientResponse patientResponse = new PatientResponse();
        Patient pat = repoPatient.findByIdPatient(updatePatientRequest.getIdPatient());

        try {
            if (pat != null) {
                //todo idPatient olmalidirmi?
                pat.setIdPatient(updatePatientRequest.getIdPatient());
                pat.setPatientNo(updatePatientRequest.getPatientNo());
                pat.setPatientName(updatePatientRequest.getPatientName());
                pat.setPatientSurname(updatePatientRequest.getPatientSurname());
                pat.setBarcode(updatePatientRequest.getBarcode());
                pat.setPatientFatherName(updatePatientRequest.getPatientFatherName());
                pat.setPatientIdTypeProperty(updatePatientRequest.getPatientIdTypeProperty());
                pat.setPatientGenderProperty(updatePatientRequest.getPatientGenderProperty());
                pat.setPatientBirthDate(updatePatientRequest.getPatientBirthDate());
                pat.setIdPatientCurrentAddress(updatePatientRequest.getIdPatientCurrentAddress());
                pat.setPatientEmail(updatePatientRequest.getPatientEmail());
                pat.setPatientCitizenshipProperty(updatePatientRequest.getPatientCitizenshipProperty());
                pat.setIdPatientRegistrationAddress(updatePatientRequest.getIdPatientRegistrationAddress());
                pat.setPatientBirthDate(updatePatientRequest.getPatientBirthDate());
                pat.setIdPatientBirthPlace(updatePatientRequest.getIdPatientBirthPlace());
                pat.setPatientHomePhoneNumber(updatePatientRequest.getPatientHomePhoneNumber());
                pat.setPatientMobilePhoneNumber(updatePatientRequest.getPatientMobilePhoneNumber());
                pat.setPatientMotherName(updatePatientRequest.getPatientMotherName());
                pat.setPatientMotherSurname(updatePatientRequest.getPatientMotherSurname());
                pat.setPatientPinCode(updatePatientRequest.getPatientPinCode());
                pat.setPatientTypeProperty(updatePatientRequest.getPatientTypeProperty());

                pat = repoPatient.save(pat);

                patientResponse.setServerCode(200);
                patientResponse.setServerMessage("OK");
                patientResponse.setPatient(pat);
                logger.info("updatePatient response : {}", updatePatientRequest.toString());
            } else {
                patientResponse.setServerCode(210);
                patientResponse.setServerMessage("OK");
                logger.info("updatePatient response : {}", patientResponse.toString());
            }
        } catch (Exception e) {
            patientResponse.setServerCode(100);
            patientResponse.setServerMessage("error");
            logger.error("Error update file text : {}", e,e);
        }
        return patientResponse;
    }

    /**
     * Deleting idPatient
     *
     * return @patientResponse
     */
    public PatientResponse deleteIdPatient(long idPatient) {
        PatientResponse patientResponse = new PatientResponse();
        try {
            Patient pat = repoPatient.findByIdPatientAndAndIsDelete(idPatient,1);

            if (pat == null) {
                patientResponse.setServerMessage("Patient not found");
                patientResponse.setServerCode(230);
            } else {
                pat.setIsDelete(0);
                repoPatient.save(pat);
                patientResponse.setServerCode(200);
                patientResponse.setServerMessage("OK patient is deleted");
                patientResponse.setPatient(pat);


            }
        } catch (Exception e) {
            patientResponse.setServerCode(100);
            patientResponse.setServerMessage("Error patient deleting");
            logger.error("Error delete : {}", e, e);
        }
        return patientResponse;
    }


}
