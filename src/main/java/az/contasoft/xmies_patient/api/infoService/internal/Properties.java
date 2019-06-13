package az.contasoft.xmies_patient.api.infoService.internal;


import java.io.Serializable;

public class Properties implements Serializable {


    /***
     *
     *
     *       SSSSSSSSSSSSSSS
     *     SS:::::::::::::::S
     *    S:::::SSSSSS::::::S
     *    S:::::S     SSSSSSS
     *    S:::::S            uuuuuu    uuuuuu  zzzzzzzzzzzzzzzzzyyyyyyy           yyyyyyy
     *    S:::::S            u::::u    u::::u  z:::::::::::::::z y:::::y         y:::::y
     *     S::::SSSS         u::::u    u::::u  z::::::::::::::z   y:::::y       y:::::y
     *      SS::::::SSSSS    u::::u    u::::u  zzzzzzzz::::::z     y:::::y     y:::::y
     *        SSS::::::::SS  u::::u    u::::u        z::::::z       y:::::y   y:::::y
     *           SSSSSS::::S u::::u    u::::u       z::::::z         y:::::y y:::::y
     *                S:::::Su::::u    u::::u      z::::::z           y:::::y:::::y
     *                S:::::Su:::::uuuu:::::u     z::::::z             y:::::::::y
     *    SSSSSSS     S:::::Su:::::::::::::::uu  z::::::zzzzzzzz        y:::::::y
     *    S::::::SSSSSS:::::S u:::::::::::::::u z::::::::::::::z         y:::::y
     *    S:::::::::::::::SS   uu::::::::uu:::uz:::::::::::::::z        y:::::y
     *     SSSSSSSSSSSSSSS       uuuuuuuu  uuuuzzzzzzzzzzzzzzzzz       y:::::y
     *                                                                y:::::y
     *                                                               y:::::y
     *                                                              y:::::y
     *                                                             y:::::y
     *                                                            yyyyyyy
     *
     *
     */

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
