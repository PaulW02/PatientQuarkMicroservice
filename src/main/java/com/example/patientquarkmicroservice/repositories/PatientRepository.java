package com.example.patientquarkmicroservice.repositories;

import com.example.patientquarkmicroservice.entities.Patient;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PatientRepository implements PanacheRepository<Patient> {
    public PanacheQuery<Patient> searchPatientsByNameAndConditionAndEncounterDate(String name, String condition, String encounterDate) {
        return find("SELECT DISTINCT p FROM Patient p " +
                        "LEFT JOIN FETCH p.conditions c " +
                        "LEFT JOIN FETCH p.encounters e " +
                        "WHERE (LOWER(p.firstName) || ' ' || LOWER(p.lastName)) LIKE ?1 " +
                        "AND c.id IN (SELECT c1.id FROM Condition c1 WHERE c1.conditionName = ?2) " +
                        "AND ?3 MEMBER OF e.visitDate",
                "%" + name.toLowerCase() + "%", condition.toLowerCase(), encounterDate);
    }

    public PanacheQuery<Patient> searchPatientsByNameAndCondition(String name, String condition) {
        return find("SELECT DISTINCT p FROM Patient p " +
                        "LEFT JOIN FETCH p.conditions c " +
                        "WHERE (LOWER(p.firstName) || ' ' || LOWER(p.lastName)) LIKE ?1 " +
                        "AND c.id IN (SELECT c1.id FROM Condition c1 WHERE c1.conditionName = ?2) ",
                "%" + name.toLowerCase() + "%", condition.toLowerCase());
    }


    public PanacheQuery<Patient> searchPatientsByNameAndEncounterDate(String name, String encounterDate) {
        return find("SELECT DISTINCT p FROM Patient p " +
                        "LEFT JOIN FETCH p.encounters e " +
                        "WHERE (LOWER(p.firstName) || ' ' || LOWER(p.lastName)) LIKE ?1 " +
                        "AND ?2 MEMBER OF e.visitDate ",
                "%" + name.toLowerCase() + "%", encounterDate);
    }

    public PanacheQuery<Patient> searchPatientsByConditionAndEncounterDate(String condition, String encounterDate) {
        return find("SELECT DISTINCT p FROM Patient p " +
                        "LEFT JOIN FETCH p.conditions c " +
                        "LEFT JOIN FETCH p.encounters e " +
                        "WHERE c.id IN (SELECT c1.id FROM Condition c1 WHERE c1.conditionName = ?1) " +
                        "AND ?2 MEMBER OF e.visitDate ",
                "%" + condition.toLowerCase() + "%", encounterDate);
    }

    public PanacheQuery<Patient> searchPatientsByCondition(String condition) {
        return find("SELECT DISTINCT p FROM Patient p LEFT JOIN conditions c ON p = c.patient WHERE c.id IN (SELECT c1.id FROM Condition c1 WHERE LOWER(c1.condition_name) = LOWER(?1))",
                "%" + condition.toLowerCase() + "%");
    }


    public PanacheQuery<Patient> searchPatientsByEncounterDate(String encounterDate) {
        return find("SELECT DISTINCT p FROM Patient p " +
                        "LEFT JOIN FETCH p.encounters e " +
                        "WHERE ?1 MEMBER OF e.visitDate ",
                encounterDate);
    }

    public PanacheQuery<Patient> searchPatientsByName(String name) {
        return find("SELECT DISTINCT p FROM Patient p " +
                        "WHERE (LOWER(p.firstName) || ' ' || LOWER(p.lastName)) LIKE ?1 ",
                "%" + name.toLowerCase() + "%");
    }

}
