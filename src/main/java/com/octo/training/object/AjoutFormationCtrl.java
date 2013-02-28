package com.octo.training.object;

import java.sql.SQLException;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;


public class AjoutFormationCtrl  {

	public void doEnregistrer(HttpServletRequest req, SessionContainer scc,
			ControllerBaseAttributes attributes) throws SQLException {
		CtrlAttributesEnregistrer attEnregistrer = (CtrlAttributesEnregistrer) attributes;
		attributes.enableRedirectParam = true;
		Internaute internaute = scc.getInternaute();
		CV cv = new CV();
		// On r�cup�re le Pourcentage d'origine
		cv = CVFactory.getTableauBord(internaute.getIntId());
		// On cr� un nouveau pour la mise � jour
		cv = new CV();
		cv.setIntId(internaute.getIntId());
		// poucentage pour ajout
		Formation formation1 = new Formation();
		formation1.setIntId(internaute.getIntId());
		int param_renseign = 0;
		/* Formation 1 */
		if (hasStartingMonthAndYear(attEnregistrer)) {
            param_renseign = formatAndSetFormationDate(attEnregistrer, formation1, param_renseign);
		}
		if (attEnregistrer.param_f_formation1_date_fin_mois != null
				&& attEnregistrer.param_f_formation1_date_fin_annee != null) {
			String chaine = attEnregistrer.param_f_formation1_date_fin_annee + "-"
					+ attEnregistrer.param_f_formation1_date_fin_mois + "-" + "01" + " 00:00:00.000000000 ";
			Timestamp myTime = Timestamp.valueOf(chaine);
			formation1.setFrmDateFin(myTime);
			param_renseign++;
		}
		if (attEnregistrer.param_f_formation1_etablissement != null) {
			formation1.setFrmEtablisssement(attEnregistrer.param_f_formation1_etablissement);
			param_renseign++;
		}
		if (attEnregistrer.param_f_formation1_lieu != null) {
			formation1.setFrmLieu(attEnregistrer.param_f_formation1_lieu);
			param_renseign++;
		}
		if (attEnregistrer.param_f_formation1_diplome != null) {
			formation1.setFrmDiplome(attEnregistrer.param_f_formation1_diplome);
			param_renseign++;
		}
		if (attEnregistrer.param_f_formation1_obtenu != null) {
			formation1.setFrmObtenu(attEnregistrer.param_f_formation1_obtenu);
			param_renseign++;
		}
		if (attEnregistrer.param_f_formation1_specialisation != null) {
			formation1.setFrmSpecialite(attEnregistrer.param_f_formation1_specialisation);
			param_renseign++;
		}
		if (attEnregistrer.param_f_formation1_tfo_code != null) {
			formation1.setTfoCode(attEnregistrer.param_f_formation1_tfo_code);
			param_renseign++;
		}
		if (param_renseign > 0) {
			if (attEnregistrer.param_f_formation1_frmid.intValue() == 0
					|| attEnregistrer.param_f_formation1_frmid == null) {
				FormationFactory.insert(formation1);
			}
			else {
				formation1.setFrmId(attEnregistrer.param_f_formation1_frmid);
				FormationFactory.update(formation1);
			}
		}
		Integer pourcent = FormationFactory.getPourcent(internaute.getIntId());
		if (attEnregistrer.param_f_niveau_formation != null) {
			cv.setNivCode(attEnregistrer.param_f_niveau_formation);
			pourcent = new Integer(pourcent.intValue() + 5);
		}
		cv.setCuvPourcentFormation(pourcent);
		CVFactory.insertFormation(cv);
	}

    private int formatAndSetFormationDate(CtrlAttributesEnregistrer attEnregistrer, Formation formation1, int param_renseign) {
        String chaine = attEnregistrer.param_f_formation1_date_debut_annee + "-"
                + attEnregistrer.param_f_formation1_date_debut_mois + "-" + "01" + " 00:00:00.000000000 ";
        Timestamp myTime = Timestamp.valueOf(chaine);
        formation1.setFrmDateDebut(myTime);
        param_renseign++;
        return param_renseign;
    }

    private boolean hasStartingMonthAndYear(CtrlAttributesEnregistrer attEnregistrer) {
        return attEnregistrer.param_f_formation1_date_debut_mois != null
                && attEnregistrer.param_f_formation1_date_debut_annee != null;
    }

}
