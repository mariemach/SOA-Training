package ressources;

import entities.UniteEnseignement;
import metiers.UniteEnseignementBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("UE")
public class GestionUeRessources {

    public static UniteEnseignementBusiness uniteEnseignementMetier = new UniteEnseignementBusiness();

    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response addUniteEnseignement(UniteEnseignement ue) {
        if (ue != null && uniteEnseignementMetier.addUniteEnseignement(ue)) {
            return Response.status(Response.Status.CREATED).entity(ue).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUnitesEnseignement(@QueryParam("domaine") String domaine, @QueryParam("semestre") Integer semestre) {
        List<UniteEnseignement> liste;

        if (semestre != null) {
            liste = uniteEnseignementMetier.getUEBySemestre(semestre);
        } else if (domaine != null) {
            liste = uniteEnseignementMetier.getUEByDomaine(domaine);
        } else {
            liste = uniteEnseignementMetier.getListeUE();
        }

        if (liste == null || liste.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.OK).entity(liste).build();
    }

    @PUT
    @Path("/{code}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response updateUE(UniteEnseignement updatedUE, @PathParam("code") int code) {
        if (updatedUE != null && uniteEnseignementMetier.updateUniteEnseignement(code, updatedUE)) {
            return Response.status(Response.Status.OK).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("/{code}")
    public Response deleteUniteEnseignement(@PathParam("code") int code) {
        if (uniteEnseignementMetier.deleteUniteEnseignement(code)) {
            return Response.status(Response.Status.OK).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Path("/{code}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUniteEnseignementById(@PathParam("code") int code) {
        UniteEnseignement ue = uniteEnseignementMetier.getUEByCode(code);
        if (ue != null) {
            return Response.status(Response.Status.OK).entity(ue).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
