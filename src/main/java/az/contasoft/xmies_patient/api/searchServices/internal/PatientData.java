package az.contasoft.xmies_patient.api.searchServices.internal;

public class PatientData {
    private long idPatient;
    private String fullData;

    public PatientData(long idPatient, String fullData) {
        this.idPatient = idPatient;
        this.fullData = fullData;
    }

    public PatientData() {
    }


    @Override
    public String toString() {
        return "PatientData{" +
                "idPatient=" + idPatient +
                ", fullData='" + fullData + '\'' +
                '}';
    }

    public long getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(long idPatient) {
        this.idPatient = idPatient;
    }

    public String getFullData() {
        return fullData;
    }

    public void setFullData(String fullData) {
        this.fullData = fullData;
    }
}
