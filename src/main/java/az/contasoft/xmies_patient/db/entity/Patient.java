package az.contasoft.xmies_patient.db.entity;

import javax.persistence.*;
import java.util.Date;
/*      idXeste
        no
        ad
        soyad
        təvəllüd
        idProperties(cinsiyet)
        idProperties(xeste_tipi)
        idProperties(vetendasligi)
        idProperties(Kimlik_tipi)
        fin
        doğulduğu yer
        ana adı
        ana soyadı
        idFaktikiAdress
        idAddress
        barkod
        ev telefonu
        mobil
        email
*/

/**
 * entites of Patient
 *
 * return @patientEntities
 */


@Entity
@Table(name = "patient")

public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPatient",nullable = false, unique = true)
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

    public Patient() {
    }

    @Override
    public String toString() {
        return "Patient{" +
                "idPatient=" + idPatient +
                ", patientNo='" + patientNo + '\'' +
                ", patientName='" + patientName + '\'' +
                ", patientSurname='" + patientSurname + '\'' +
                ", patientFatherName='" + patientFatherName + '\'' +
                ", patientBirthDate=" + patientBirthDate +
                ", patientGenderProperty=" + patientGenderProperty +
                ", patientTypeProperty=" + patientTypeProperty +
                ", patientCitizenshipProperty=" + patientCitizenshipProperty +
                ", patientIdTypeProperty=" + patientIdTypeProperty +
                ", patientPinCode='" + patientPinCode + '\'' +
                ", idPatientBirthPlace=" + idPatientBirthPlace +
                ", patientMotherName='" + patientMotherName + '\'' +
                ", patientMotherSurname='" + patientMotherSurname + '\'' +
                ", idPatientCurrentAddress=" + idPatientCurrentAddress +
                ", idPatientRegistrationAddress=" + idPatientRegistrationAddress +
                ", barcode='" + barcode + '\'' +
                ", patientHomePhoneNumber='" + patientHomePhoneNumber + '\'' +
                ", patientMobilePhoneNumber='" + patientMobilePhoneNumber + '\'' +
                ", patientEmail='" + patientEmail + '\'' +
                ", isDelete=" + isDelete +
                ", idPersonalDeletedBy=" + idPersonalDeletedBy +
                '}';
    }

    public long getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(long idPatient) {
        this.idPatient = idPatient;
    }

    public String getPatientNo() {
        return patientNo;
    }

    public void setPatientNo(String patientNo) {
        this.patientNo = patientNo;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientSurname() {
        return patientSurname;
    }

    public void setPatientSurname(String patientSurname) {
        this.patientSurname = patientSurname;
    }

    public String getPatientFatherName() {
        return patientFatherName;
    }

    public void setPatientFatherName(String patientFatherName) {
        this.patientFatherName = patientFatherName;
    }

    public Date getPatientBirthDate() {
        return patientBirthDate;
    }

    public void setPatientBirthDate(Date patientBirthDate) {
        this.patientBirthDate = patientBirthDate;
    }

    public int getPatientGenderProperty() {
        return patientGenderProperty;
    }

    public void setPatientGenderProperty(int patientGenderProperty) {
        this.patientGenderProperty = patientGenderProperty;
    }

    public int getPatientTypeProperty() {
        return patientTypeProperty;
    }

    public void setPatientTypeProperty(int patientTypeProperty) {
        this.patientTypeProperty = patientTypeProperty;
    }

    public int getPatientCitizenshipProperty() {
        return patientCitizenshipProperty;
    }

    public void setPatientCitizenshipProperty(int patientCitizenshipProperty) {
        this.patientCitizenshipProperty = patientCitizenshipProperty;
    }

    public int getPatientIdTypeProperty() {
        return patientIdTypeProperty;
    }

    public void setPatientIdTypeProperty(int patientIdTypeProperty) {
        this.patientIdTypeProperty = patientIdTypeProperty;
    }

    public String getPatientPinCode() {
        return patientPinCode;
    }

    public void setPatientPinCode(String patientPinCode) {
        this.patientPinCode = patientPinCode;
    }

    public long getIdPatientBirthPlace() {
        return idPatientBirthPlace;
    }

    public void setIdPatientBirthPlace(long idPatientBirthPlace) {
        this.idPatientBirthPlace = idPatientBirthPlace;
    }

    public String getPatientMotherName() {
        return patientMotherName;
    }

    public void setPatientMotherName(String patientMotherName) {
        this.patientMotherName = patientMotherName;
    }

    public String getPatientMotherSurname() {
        return patientMotherSurname;
    }

    public void setPatientMotherSurname(String patientMotherSurname) {
        this.patientMotherSurname = patientMotherSurname;
    }

    public long getIdPatientCurrentAddress() {
        return idPatientCurrentAddress;
    }

    public void setIdPatientCurrentAddress(long idPatientCurrentAddress) {
        this.idPatientCurrentAddress = idPatientCurrentAddress;
    }

    public long getIdPatientRegistrationAddress() {
        return idPatientRegistrationAddress;
    }

    public void setIdPatientRegistrationAddress(long idPatientRegistrationAddress) {
        this.idPatientRegistrationAddress = idPatientRegistrationAddress;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getPatientHomePhoneNumber() {
        return patientHomePhoneNumber;
    }

    public void setPatientHomePhoneNumber(String patientHomePhoneNumber) {
        this.patientHomePhoneNumber = patientHomePhoneNumber;
    }

    public String getPatientMobilePhoneNumber() {
        return patientMobilePhoneNumber;
    }

    public void setPatientMobilePhoneNumber(String patientMobilePhoneNumber) {
        this.patientMobilePhoneNumber = patientMobilePhoneNumber;
    }

    public String getPatientEmail() {
        return patientEmail;
    }

    public void setPatientEmail(String patientEmail) {
        this.patientEmail = patientEmail;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }

    public long getIdPersonalDeletedBy() {
        return idPersonalDeletedBy;
    }

    public void setIdPersonalDeletedBy(long idPersonalDeletedBy) {
        this.idPersonalDeletedBy = idPersonalDeletedBy;
    }

    public Patient(String patientNo, String patientName, String patientSurname, String patientFatherName, Date patientBirthDate, int patientGenderProperty, int patientTypeProperty, int patientCitizenshipProperty, int patientIdTypeProperty, String patientPinCode, long idPatientBirthPlace, String patientMotherName, String patientMotherSurname, long idPatientCurrentAddress, long idPatientRegistrationAddress, String barcode, String patientHomePhoneNumber, String patientMobilePhoneNumber, String patientEmail, int isDelete, long idPersonalDeletedBy) {
        this.patientNo = patientNo;
        this.patientName = patientName;
        this.patientSurname = patientSurname;
        this.patientFatherName = patientFatherName;
        this.patientBirthDate = patientBirthDate;
        this.patientGenderProperty = patientGenderProperty;
        this.patientTypeProperty = patientTypeProperty;
        this.patientCitizenshipProperty = patientCitizenshipProperty;
        this.patientIdTypeProperty = patientIdTypeProperty;
        this.patientPinCode = patientPinCode;
        this.idPatientBirthPlace = idPatientBirthPlace;
        this.patientMotherName = patientMotherName;
        this.patientMotherSurname = patientMotherSurname;
        this.idPatientCurrentAddress = idPatientCurrentAddress;
        this.idPatientRegistrationAddress = idPatientRegistrationAddress;
        this.barcode = barcode;
        this.patientHomePhoneNumber = patientHomePhoneNumber;
        this.patientMobilePhoneNumber = patientMobilePhoneNumber;
        this.patientEmail = patientEmail;
        this.isDelete = isDelete;
        this.idPersonalDeletedBy = idPersonalDeletedBy;
    }
}
