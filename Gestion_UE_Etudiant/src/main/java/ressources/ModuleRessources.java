package ressources;

import entities.Module;
import entities.UniteEnseignement;
import metiers.ModuleBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("modules")
public class ModuleRessources {
    public static ModuleBusiness moduleMetier = new ModuleBusiness();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addModule(Module module) {
        if (moduleMetier.addModule(module)) {
            return Response.status(Response.Status.CREATED).entity(moduleMetier.getAllModules()).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
    @GET
    @Path("/getAll")
    @Produces(MediaType.APPLICATION_JSON)

    public Response getAllModule() {
      if (moduleMetier.getAllModules().size() != 0) {
            return Response.status(Response.Status.OK).entity(moduleMetier.getAllModules()).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
    @GET
    @Path("/{matricule}")
    @Produces(MediaType.APPLICATION_JSON)

    public Response getModuleById(@PathParam("matricule") String matricule) {
        Module module = moduleMetier.getModuleByMatricule(matricule);
        if (module != null) {
            return Response.status(Response.Status.OK).entity(module).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)

    public Response getAllModules(@QueryParam("type") String typeStr) {
        List<Module> modules;

        if (typeStr != null) {
            try {
                Module.TypeModule type = Module.TypeModule.valueOf(typeStr);
                modules = moduleMetier.getModulesByType(type);
            } catch (IllegalArgumentException e) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("Type de module invalide").build();
            }
        } else {
            modules = moduleMetier.getAllModules();
        }

        if (modules.isEmpty()) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        return Response.status(Response.Status.OK).entity(modules).build();
    }

    @PUT
    @Path("/{matricule}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateModule(@PathParam("matricule") String matricule, Module updatedModule) {
        if (moduleMetier.updateModule(matricule, updatedModule)) {
            return Response.status(Response.Status.OK).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("/{matricule}")

    public Response deleteModule(@PathParam("matricule") String matricule) {
        if (moduleMetier.deleteModule(matricule)) {
            return Response.status(Response.Status.OK).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Path("/UE")
    @Produces(MediaType.APPLICATION_JSON)

    public Response getModulesByUE(@QueryParam("codeUE") int codeUE) {
        UniteEnseignement ue = new UniteEnseignement();
        ue.setCode(codeUE);
        List<Module> modules = moduleMetier.getModulesByUE(ue);

        if (modules.isEmpty()) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        return Response.status(Response.Status.OK).entity(modules).build();
    }
}
