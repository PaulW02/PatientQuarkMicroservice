package com.example.patientquarkmicroservice.services;

import com.example.patientquarkmicroservice.dtos.PatientDTO;
import com.example.patientquarkmicroservice.entities.Patient;
import com.example.patientquarkmicroservice.repositories.PatientRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class SearchService {
    private static final Logger LOG = LoggerFactory.getLogger(SearchService.class);

    @Inject
    PatientRepository patientRepository;
  /*  @Inject
    UserRepository userRepository;

    @Inject
    Mutiny.SessionFactory sf;

*/
  public List<Patient> findAll() {
      return patientRepository.findAll().list();
  }


    private PatientDTO convertToDTO(Patient p) {
        LOG.info("Converting patient to DTO: " + p.getFirstName() + " " + p.getLastName());
        return new PatientDTO(p.getId(), p.getFirstName(), p.getLastName(), p.getAge());
    }


    public List<PatientDTO> searchPatients(String name, String condition, String encounterDate) {
            try {
                if (!name.equals("") && !condition.equals("") && !encounterDate.equals("")) {
                    return patientRepository.searchPatientsByNameAndConditionAndEncounterDate(name,condition,encounterDate).list().stream()
                            .map(this::convertToDTO)
                            .collect(Collectors.toList());
                }else if (!name.equals("") && condition.equals("") && encounterDate.equals("")) {
                    return patientRepository.searchPatientsByName(name).list().stream()
                            .map(this::convertToDTO)
                            .collect(Collectors.toList());
                } else if (!name.equals("") && !condition.equals("") && encounterDate.equals("")) {
                    return patientRepository.searchPatientsByNameAndCondition(name, condition).list().stream()
                            .map(this::convertToDTO)
                            .collect(Collectors.toList());
                } else if (!name.equals("") && condition.equals("") && !encounterDate.equals("")) {
                    return patientRepository.searchPatientsByNameAndEncounterDate(name, encounterDate).list().stream()
                            .map(this::convertToDTO)
                            .collect(Collectors.toList());
                } else if (name.equals("") && !condition.equals("") && encounterDate.equals("")) {
                    return patientRepository.searchPatientsByCondition(condition).list().stream()
                            .map(this::convertToDTO)
                            .collect(Collectors.toList());
                } else if (name.equals("") && !condition.equals("") && !encounterDate.equals("")) {
                    return patientRepository.searchPatientsByConditionAndEncounterDate(condition, encounterDate).list().stream()
                            .map(this::convertToDTO)
                            .collect(Collectors.toList());
                } else if (name.equals("") && condition.equals("") && !encounterDate.equals("")) {
                    return patientRepository.searchPatientsByEncounterDate(encounterDate).list().stream()
                            .map(this::convertToDTO)
                            .collect(Collectors.toList());
                } else {
                    return null;
                }
            } catch (Exception e) {
                return null;
            }
    }
/*

    public Uni<List<User>> searchDoctors(String name, String encounterDate) {
        if (name != null && encounterDate == null) {
            // Search doctors by name only
            return userRepository.find("LOWER(first_name) LIKE LOWER(?1) OR LOWER(last_name) LIKE LOWER(?1) AND role LIKE ?2", "%" + name + "%", "%Doctor%").list();
        } else if (name == null && encounterDate != null) {
            // Search doctors by encounter date only
            // Add logic to search based on encounter date (e.g., join with encounters table)
            // This is a simplified example, adjust it based on your actual schema
            return userRepository.find("JOIN encounters e ON e.doctor.id = id WHERE e.visitDate = ?1 AND role LIKE ?2", encounterDate, "%Doctor%").list();
        } else {
            // Handle other cases or combinations of parameters as needed
            return null;
        }
    }
    */
}

