package az.contasoft.xmies_patient.api.searchServices.internal;

import az.contasoft.xmies_patient.db.entity.Patient;
@Deprecated
public class ResponseSearchPatient {
    private  int serverCode;
    private  String serverMessage;
    private Patient patient;

    public ResponseSearchPatient() {
    }

    @Override
    public String toString() {
        return "ResponseSearchPatient{" +
                "serverCode=" + serverCode +
                ", serverMessage='" + serverMessage + '\'' +
                ", patient=" + patient +
                '}';
    }


    public int getServerCode() {
        return serverCode;
    }

    public void setServerCode(int serverCode) {
        this.serverCode = serverCode;
    }

    public String getServerMessage() {
        return serverMessage;
    }

    public void setServerMessage(String serverMessage) {
        this.serverMessage = serverMessage;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public ResponseSearchPatient(int serverCode, String serverMessage, Patient patient) {
        this.serverCode = serverCode;
        this.serverMessage = serverMessage;
        this.patient = patient;
    }
}
