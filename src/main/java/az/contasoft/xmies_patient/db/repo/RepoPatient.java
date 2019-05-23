package az.contasoft.xmies_patient.db.repo;

import az.contasoft.xmies_patient.db.entity.Patient;
import org.springframework.data.repository.CrudRepository;


import java.util.List;

/**
 * repo of Patient
 *
 * return @repoPatient
 */

public interface RepoPatient extends CrudRepository<Patient, Long> {
/*
 private long idPatient;
    private String patientNo;
    private String patientName;
    private String patientSurname;
    private String patientFatherName;
    private Date patientBirthDate;   //tevelludu
    private int patientGenderProperty;    //cinsiyyeti
    private int patientTypeProperty;   //xeste tipi
    private int patientCitizenshipProperty;   //vetendashligi
    private int patientIdTypeProperty;      //kimlik tipi
    private String patientPinCode;   //fin
    private long idPatientBirthPlace;  //doguldugu yer  //TODO idbirthPlace etmeliyik?//id adress for flanshey
    private String patientMotherName;
    private String patientMotherSurname; //TODO ana soyad neye lazim?
    private long idPatientCurrentAddress;  //faktiki adresi
    private long idPatientRegistrationAddress;  //qeydiyyatd adresi
    private String barcode;

    private String patientHomePhoneNumber; //ev teli
    private String patientMobilePhoneNumber; //mobil teli
    private String patientEmail;
    private int isDelete;
    private long idPersonalDeletedBy;
 */
    //todo qalsin axira bu

       List<Patient> findAll();

    Patient findByIdPatient(long idPatient);

    List<Patient> findByPatientNameAndPatientSurnameAndPatientFatherNameAndPatientPinCode(String patientName,String patientSurname,String patientFatherName,String patientPinCode);

    List<Patient> findAllByOrderByIdPatientDesc();

    Patient findByIdPatientAndAndIsDelete(long idPatient,int isDelete);

    Patient findByPatientPinCode(String patientPinCode);



}
