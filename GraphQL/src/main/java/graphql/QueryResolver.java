package graphql;

import business.ModuleBusiness;
import business.UniteEnseignementBusiness;
import com.coxautodev.graphql.tools.GraphQLRootResolver;
import entities.Module;
import entities.UniteEnseignement;

import java.util.List;

/**
 * Root Query Resolver for GraphQL operations.
 * Exposes queries for Module and UniteEnseignement.
 */
public class QueryResolver implements GraphQLRootResolver {

    private UniteEnseignementBusiness helper;
    private ModuleBusiness moduleHelper;

    public QueryResolver() {
        helper = new UniteEnseignementBusiness();
        moduleHelper = new ModuleBusiness();
    }

    // ==========================================
    // Operations Query pour UniteEnseignement
    // ==========================================

    /**
     * Recuperation de toutes les unites d'enseignement
     */
    public List<UniteEnseignement> allUEs() {
        return helper.getListeUE();
    }

    /**
     * Recuperation d'une Unite d'Enseignement a partir de son code unique
     */
    public UniteEnseignement getUEByCode(int code) {
        return helper.getUEByCode(code);
    }

    /**
     * Recuperation de toutes les Unites d'Enseignement appartenant a un domaine donne
     */
    public List<UniteEnseignement> getUEByDomaine(String domaine) {
        return helper.getUEByDomaine(domaine);
    }

    /**
     * Recuperation de toutes les Unites d'Enseignement d'un semestre donne
     */
    public List<UniteEnseignement> getUEBySemestre(int semestre) {
        return helper.getUEBySemestre(semestre);
    }

    // ==========================================
    // Operations Query pour Module
    // ==========================================

    /**
     * Recuperation de la liste de tous les modules
     */
    public List<Module> allModules() {
        return moduleHelper.getAllModules();
    }

    /**
     * Recuperation de la liste des modules ayant un type specifique
     */
    public List<Module> getModulesByType(Module.TypeModule type) {
        return moduleHelper.getModulesByType(type);
    }

    /**
     * Recuperation d'un module ayant une matricule specifique
     */
    public Module getModuleByMatricule(String matricule) {
        return moduleHelper.getModuleByMatricule(matricule);
    }
}