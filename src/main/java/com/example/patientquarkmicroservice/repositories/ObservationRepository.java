package com.example.patientquarkmicroservice.repositories;

import com.example.patientquarkmicroservice.entities.Observation;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import jakarta.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class ObservationRepository implements PanacheRepository<Observation> {
}

