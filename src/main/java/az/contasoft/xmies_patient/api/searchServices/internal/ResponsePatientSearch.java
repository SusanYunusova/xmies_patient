package az.contasoft.xmies_patient.api.searchServices.internal;

import java.util.List;

/**
 * Created by heisenberg
 */
public class ResponsePatientSearch {
    private int serverCode;
    private String serverMessage;
    private List<PatientData> list;

    public ResponsePatientSearch(int serverCode, String serverMessage, List<PatientData> list) {
        this.serverCode = serverCode;
        this.serverMessage = serverMessage;
        this.list = list;
    }

    public ResponsePatientSearch() {
    }

    @Override
    public String toString() {
        return "ResponsePatientSearch{" +
                "serverCode=" + serverCode +
                ", serverMessage='" + serverMessage + '\'' +
                ", list=" + list +
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

    public List<PatientData> getList() {
        return list;
    }

    public void setList(List<PatientData> list) {
        this.list = list;
    }
}
