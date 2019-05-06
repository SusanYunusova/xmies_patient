package az.contasoft.xmies_patient.api.crudServices.internal;

public class RequestForFullName {
    private  String patientName;
    private String patientSurname;
    private  String patientFatherName;
    private  String patientPinCode;

    public RequestForFullName() {

    }

    @Override
    public String toString() {
        return "RequestForFullName{" +
                "patientName='" + patientName + '\'' +
                ", patientSurname='" + patientSurname + '\'' +
                ", patientFatherName='" + patientFatherName + '\'' +
                ", patientPinCode='" + patientPinCode + '\'' +
                '}';
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

    public String getPatientPinCode() {
        return patientPinCode;
    }

    public void setPatientPinCode(String patientPinCode) {
        this.patientPinCode = patientPinCode;
    }

    public RequestForFullName(String patientName, String patientSurname, String patientFatherName, String patientPinCode) {
        this.patientName = patientName;
        this.patientSurname = patientSurname;
        this.patientFatherName = patientFatherName;
        this.patientPinCode = patientPinCode;
    }
}
