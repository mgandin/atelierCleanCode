
package com.sgcib.training.object;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;


public class Formation {
	
	private Long intId = null;

	private Long frmId = null;

	private Timestamp frmDateDebut = null;

	private Timestamp frmDateFin = null;

	private String frmEcole = null;

	private String frmLieu = null;

	private String frmSpecialite = null;

	private String frmDiplome = null;

	private Byte frmObtenu = null;

	private Long tfoCode = null;

	private String tfoLibelle = null;

	public void populateFromRs(ResultSet res) throws SQLException {
		intId = FactoryUtils.getLongValueFromRs(res, "INT_ID");
		frmId = FactoryUtils.getLongValueFromRs(res, "FRM_ID");
		frmDateDebut = (Timestamp) FactoryUtils.getValueFromRs(res, "FRM_DATE_DEBUT");
		frmDateFin = (Timestamp) FactoryUtils.getValueFromRs(res, "FRM_DATE_FIN");
		frmEcole = (String) FactoryUtils.getValueFromRs(res, "FRM_ECOLE");
		frmLieu = (String) FactoryUtils.getValueFromRs(res, "FRM_LIEU");
		frmSpecialite = (String) FactoryUtils.getValueFromRs(res, "FRM_SPECIALITE");
		frmDiplome = (String) FactoryUtils.getValueFromRs(res, "FRM_DIPLOME");
		frmObtenu = FactoryUtils.getByteValueFromRs(res, "FRM_OBTENU");
		tfoCode = FactoryUtils.getLongValueFromRs(res, "TFO_CODE");
		tfoLibelle = (String) FactoryUtils.getValueFromRs(res, "TFO_LIBELLE");
	}

	/**
	 * @return
	 */
	public Timestamp getFrmDateDebut() {
		return frmDateDebut;
	}

	/**
	 * @return
	 */
	public Timestamp getFrmDateFin() {
		return frmDateFin;
	}

	/**
	 * @return
	 */
	public String getFrmDiplome() {
		return frmDiplome;
	}

	/**
	 * @return
	 */
	public String getFrmEtablisssement() {
		return frmEcole;
	}

	/**
	 * @return
	 */
	public Long getFrmId() {
		return frmId;
	}

	/**
	 * @return
	 */
	public String getFrmLieu() {
		return frmLieu;
	}

	/**
	 * @return
	 */
	public Byte getFrmObtenu() {
		return frmObtenu;
	}

	/**
	 * @return
	 */
	public String getFrmSpecialite() {
		return frmSpecialite;
	}

	/**
	 * @return
	 */
	public Long getIntId() {
		return intId;
	}

	/**
	 * @return
	 */
	public Long getTfoCode() {
		return tfoCode;
	}

	/**
	 * @param timestamp
	 */
	public void setFrmDateDebut(Timestamp timestamp) {
		frmDateDebut = timestamp;
	}

	/**
	 * @param timestamp
	 */
	public void setFrmDateFin(Timestamp timestamp) {
		frmDateFin = timestamp;
	}

	/**
	 * @param string
	 */
	public void setFrmDiplome(String string) {
		frmDiplome = string;
	}

	/**
	 * @param string
	 */
	public void setFrmEtablisssement(String string) {
		frmEcole = string;
	}

	/**
	 * @param decimal
	 */
	public void setFrmId(Long decimal) {
		frmId = decimal;
	}

	/**
	 * @param string
	 */
	public void setFrmLieu(String string) {
		frmLieu = string;
	}

	/**
	 * @param decimal
	 */
	public void setFrmObtenu(Byte decimal) {
		frmObtenu = decimal;
	}

	/**
	 * @param string
	 */
	public void setFrmSpecialite(String string) {
		frmSpecialite = string;
	}

	/**
	 * @param decimal
	 */
	public void setIntId(Long decimal) {
		intId = decimal;
	}

	/**
	 * @param decimal
	 */
	public void setTfoCode(Long decimal) {
		tfoCode = decimal;
	}
}
