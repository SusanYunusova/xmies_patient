package az.contasoft.xmies_patient.api.infoService.internal;


public class Properties {


    private long idProperties;
    private  String value;
    private  long parentId;
    private  int isDelete;

    public Properties() {
    }

    public Properties(long idProperties, String value, long parentId, int isDelete) {
        this.idProperties = idProperties;
        this.value = value;
        this.parentId = parentId;
        this.isDelete = isDelete;
    }

    public long getIdProperties() {
        return idProperties;
    }

    public void setIdProperties(long idProperties) {
        this.idProperties = idProperties;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }

    @Override
    public String toString() {
        return "Properties{" +
                "idProperties=" + idProperties +
                ", value='" + value + '\'' +
                ", parentId=" + parentId +
                ", isDelete=" + isDelete +
                '}';

    }}
