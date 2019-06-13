package az.contasoft.xmies_patient.util;

import az.contasoft.xmies_patient.api.infoService.internal.Address;
import az.contasoft.xmies_patient.api.infoService.internal.PatientInfo;
import az.contasoft.xmies_patient.api.infoService.internal.Properties;
import az.contasoft.xmies_patient.db.entity.Patient;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HazelCastUtility {
    private static HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance();
    private static Logger logger = LoggerFactory.getLogger("HazelCastUtility");

    public static HazelcastInstance getHazelcastInstance(){
        if(hazelcastInstance == null){
            hazelcastInstance = Hazelcast.newHazelcastInstance();
        }
        return hazelcastInstance;
    }

    public static void addOrUpdatePatientToHazelCast(Patient patient) {
        logger.info("{}", "Trying to add or update patient to hazelcast.. " + patient.toString());
        IMap<Long, Patient> mapOfPatient = getHazelcastInstance().getMap("patient");
        mapOfPatient.put(patient.getIdPatient(), patient);
    }

    public static void addOrUpdatePatientInfoToHazelCast(PatientInfo patientInfo) {
        logger.info("{}", "Trying to add or update personal to hazelcast.. " + patientInfo.toString());
        IMap<Long, PatientInfo> mapOfPatient = getHazelcastInstance().getMap("patientInfo");
        mapOfPatient.put(patientInfo.getIdPatient(), patientInfo);
    }



    public static void deletePatientFromHazelCast(Long id) {
        logger.info("{}", "Trying to remove patient from hazelCast");
        IMap<Long, Patient> mapOfPatient = getHazelcastInstance().getMap("patient");
        mapOfPatient.remove(id);
    }

    public static IMap<Long, Patient> getMapOfPatient(){
        IMap<Long, Patient> mapOfPatient = getHazelcastInstance().getMap("patient");
        return mapOfPatient;
    }

    public static IMap<Long, PatientInfo> getMapOfPatientInfo(){
        IMap<Long, PatientInfo> mapOfPatient = getHazelcastInstance().getMap("patientInfo");
        return mapOfPatient;
    }


    public static Properties getPropertyFromHazelCast(int idProperty){
        IMap<Long, Properties> mapOfProperties = getHazelcastInstance().getMap("properties");
        return mapOfProperties.get(idProperty);
    }

    public static Address getAddressFromHazelCast(long idAddress){
        IMap<Long, Address> mapOfProperties = getHazelcastInstance().getMap("address");
        return mapOfProperties.get(idAddress);
    }

}
