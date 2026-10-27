package graphql;

import business.ModuleBusiness;
import business.UniteEnseignementBusiness;
import com.coxautodev.graphql.tools.GraphQLRootResolver;
import entities.Module;
import entities.UniteEnseignement;

/**
 * Root Mutation Resolver for GraphQL operations.
 * Exposes mutations (create, update, delete) for Module and UniteEnseignement.
 */
public class MutationResolver implements GraphQLRootResolver {

    private UniteEnseignementBusiness helper;
    private ModuleBusiness moduleHelper;

    public MutationResolver() {
        helper = new UniteEnseignementBusiness();
        moduleHelper = new ModuleBusiness();
    }

    // ==========================================
    // Operations Mutation pour UniteEnseignement
    // ==========================================

    /**
     * Creation d'une nouvelle unite enseignement
     */
    public boolean addUniteEnseignement(int code, String domaine, String responsable, int credits, int semestre) {
        UniteEnseignement ue = new UniteEnseignement(code, domaine, responsable, credits, semestre);
        return helper.addUniteEnseignement(ue);
    }

    public boolean createUniteEnseignement(int code, String domaine, String responsable, int credits, int semestre) {
        return addUniteEnseignement(code, domaine, responsable, credits, semestre);
    }

    /**
     * Mettre a jour une Unite d'Enseignement existante (identifiee par son code)
     */
    public boolean updateUniteEnseignement(int code, String domaine, String responsable, int credits, int semestre) {
        UniteEnseignement updatedUE = new UniteEnseignement(code, domaine, responsable, credits, semestre);
        return helper.updateUniteEnseignement(code, updatedUE);
    }

    /**
     * Supprime une Unite d'Enseignement en fonction de son code
     */
    public boolean deleteUniteEnseignement(int code) {
        return helper.deleteUniteEnseignement(code);
    }

    // ==========================================
    // Operations Mutation pour Module
    // ==========================================

    /**
     * Creation d'un nouveau module avec un type de retour Boolean
     */
    public boolean createModule(String matricule, String nom, int coefficient, int volumeHoraire, Module.TypeModule type, Integer codeUE) {
        UniteEnseignement ue = null;
        if (codeUE != null) {
            ue = helper.getUEByCode(codeUE);
        }
        Module module = new Module(matricule, nom, coefficient, volumeHoraire, type, ue);
        return moduleHelper.addModule(module);
    }

    public boolean addModule(String matricule, String nom, int coefficient, int volumeHoraire, Module.TypeModule type, Integer codeUE) {
        return createModule(matricule, nom, coefficient, volumeHoraire, type, codeUE);
    }

    /**
     * Modification d'un module ayant un identifiant specifique
     */
    public boolean updateModule(String matricule, String nom, int coefficient, int volumeHoraire, Module.TypeModule type, Integer codeUE) {
        UniteEnseignement ue = null;
        if (codeUE != null) {
            ue = helper.getUEByCode(codeUE);
        }
        Module updatedModule = new Module(matricule, nom, coefficient, volumeHoraire, type, ue);
        return moduleHelper.updateModule(matricule, updatedModule);
    }

    /**
     * Suppression d'un module ayant une matricule specifique
     */
    public boolean deleteModule(String matricule) {
        return moduleHelper.deleteModule(matricule);
    }
}