package az.contasoft.xmies_patient.api.crudServices.internal;

import az.contasoft.xmies_patient.db.entity.Patient;
@Deprecated
public class PatientResponse {
    private int serverCode;
    private String serverMessage;
    private Patient patient;

    public PatientResponse(int serverCode, String serverMessage, Patient patient) {
        this.serverCode = serverCode;
        this.serverMessage = serverMessage;
        this.patient = patient;
    }

    public PatientResponse() {
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
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

    @Override
    public String toString() {
        return "PatientResponse{" +
                "serverCode=" + serverCode +
                ", serverMessage='" + serverMessage + '\'' +
                ", patient=" + patient +
                '}';
    }
}


