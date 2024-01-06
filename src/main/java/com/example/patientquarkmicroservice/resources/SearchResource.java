package com.example.patientquarkmicroservice.resources;

import com.example.patientquarkmicroservice.dtos.PatientDTO;
import com.example.patientquarkmicroservice.services.SearchService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;

import java.util.List;


@Path("/search")
public class SearchResource {

    @Inject
    SearchService searchService;

    @GET
    @Path("/patients")
    public List<PatientDTO> searchPatients(
            @QueryParam("name") String name,
            @QueryParam("condition") String condition,
            @QueryParam("encounterDate") String encounterDate) {
        return searchService.searchPatients(name, condition, encounterDate);
    }

    @GET
    @Path("/all")
    public Response searchAll() {
        return Response.ok(searchService.findAll()).build();
    }
/*
    @GET
    @Path("/doctors")
    public Response searchDoctors(
            @QueryParam("name") String name,
            @QueryParam("encounterDate") String encounterDate) {
        return Response.ok(searchService.searchDoctors(name, encounterDate)).build();
    }

 */
}
