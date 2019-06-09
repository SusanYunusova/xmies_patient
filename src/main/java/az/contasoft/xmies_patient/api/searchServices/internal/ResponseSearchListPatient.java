package az.contasoft.xmies_patient.api.searchServices.internal;

import az.contasoft.xmies_patient.db.entity.Patient;

import java.util.List;
@Deprecated
public class ResponseSearchListPatient {
    private  int serverCode;
    private  String serverMessage;
    private List<Patient> listOfPatient;

    public ResponseSearchListPatient(int serverCode, String serverMessage, List<Patient> listOfPatient) {
        this.serverCode = serverCode;
        this.serverMessage = serverMessage;
        this.listOfPatient = listOfPatient;
    }

    public ResponseSearchListPatient() {

    }

    @Override
    public String toString() {
        return "ResponseSearchListPatient{" +
                "serverCode=" + serverCode +
                ", serverMessage='" + serverMessage + '\'' +
                ", listOfPatient=" + listOfPatient +
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

    public List<Patient> getListOfPatient() {
        return listOfPatient;
    }

    public void setListOfPatient(List<Patient> listOfPatient) {
        this.listOfPatient = listOfPatient;
    }
}
