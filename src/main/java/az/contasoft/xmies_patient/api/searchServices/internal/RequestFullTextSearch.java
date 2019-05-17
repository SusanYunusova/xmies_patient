package az.contasoft.xmies_patient.api.searchServices.internal;

public class RequestFullTextSearch {
    private String enteredData;

    public RequestFullTextSearch(String enteredData) {
        this.enteredData = enteredData;
    }

    public RequestFullTextSearch() {
    }

    public String getEnteredData() {
        return enteredData;
    }

    public void setEnteredData(String enteredData) {
        this.enteredData = enteredData;
    }

    @Override
    public String toString() {
        return "RequestFullTextSearch{" +
                "enteredData='" + enteredData + '\'' +
                '}';
    }
}
