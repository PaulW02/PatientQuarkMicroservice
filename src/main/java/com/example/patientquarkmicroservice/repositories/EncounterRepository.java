package com.example.patientquarkmicroservice.repositories;

import com.example.patientquarkmicroservice.entities.Encounter;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EncounterRepository implements PanacheRepository<Encounter> {
}

