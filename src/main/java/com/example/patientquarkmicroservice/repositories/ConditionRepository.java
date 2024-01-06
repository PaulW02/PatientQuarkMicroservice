package com.example.patientquarkmicroservice.repositories;

import com.example.patientquarkmicroservice.entities.Condition;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ConditionRepository implements PanacheRepository<Condition> {
}

