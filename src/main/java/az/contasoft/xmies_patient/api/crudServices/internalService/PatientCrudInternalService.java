package az.contasoft.xmies_patient.api.crudServices.internalService;

import az.contasoft.xmies_patient.api.crudServices.internal.PatientResponse;
import az.contasoft.xmies_patient.api.crudServices.internal.SavePatientRequest;
import az.contasoft.xmies_patient.api.crudServices.internal.UpdatePatientRequest;
import az.contasoft.xmies_patient.api.infoService.internalService.CashService;
import az.contasoft.xmies_patient.api.infoService.internalService.Service;
import az.contasoft.xmies_patient.db.entity.Patient;
import az.contasoft.xmies_patient.db.repo.RepoPatient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class PatientCrudInternalService {

    @Autowired
    RepoPatient repoPatient;
    @Autowired
    CashService cashService;
    private Logger logger = LoggerFactory.getLogger(this.getClass());


    /**
     * SavePatient entties
     * <p>
     * return @patientResponse
     */

    public ResponseEntity<Patient> savePatient(SavePatientRequest savePatientRequest) {
        //  PatientResponse patientResponse = new PatientResponse();
        try {
            Patient result = repoPatient.findByPatientPinCode(savePatientRequest.getPatientPinCode());
            if (result == null) {
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
                logger.info("{}", "saving patient");
                cashService.refresh();
                return new ResponseEntity<>(patient, HttpStatus.OK);
            } else {
                logger.info("patient has already saved!!! response : {}", savePatientRequest.toString());
                return new ResponseEntity<>(result, HttpStatus.valueOf("This patient already saved"));
            }

        } catch (Exception e) {

            logger.error("Error save savePatient : {}", e, e);

            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * UpdatePatient entties
     * <p>
     * return @patientResponse
     */

    public ResponseEntity<Patient> updatePatient(UpdatePatientRequest updatePatientRequest) {
        // PatientResponse patientResponse = new PatientResponse();
        Patient pat = repoPatient.findByIdPatient(updatePatientRequest.getIdPatient());

        try {
            if (pat != null) {


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


                logger.info("updatePatient response : {}", updatePatientRequest.toString());
                cashService.refresh();
                return new ResponseEntity<>(pat, HttpStatus.OK);
            } else {

                logger.info("updatePatient response : {}", "Not found");
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {

            logger.error("Error update file text : {}", e, e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * Deleting idPatient
     * <p>
     * return @patientResponse
     */
    public ResponseEntity<Patient> deleteIdPatient(long idPatient) {
        //  PatientResponse patientResponse = new PatientResponse();
        try {
            Patient pat = repoPatient.findByIdPatientAndAndIsDelete(idPatient, 1);

            if (pat == null) {
                logger.info("deletePatient response : {}", "Not found");
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            } else {
                pat.setIsDelete(0);
                repoPatient.save(pat);
                cashService.refresh();
                return new ResponseEntity<>(pat, HttpStatus.OK);


            }
        } catch (Exception e) {

            logger.error("Error delete : {}", e, e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


}
