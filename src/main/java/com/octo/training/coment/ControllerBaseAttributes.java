// ----------------------------------------------------------------------------79
//
// @(#) $Header:
// 1.8 2006/05/04 07:53:06 lapag Exp $
//
// Langage : Java
//
// Description :
//
// Date creation : 14 nov. 2003
//
// Historique :
// 06/08/2005 VDD x7786 - analyse log
// 3.4 UPLOAD INTERROMPU PAR UTILISATEUR
// 3.5 ACTION NULL
//
// ----------------------------------------------------------------------------79
package com.octo.training.cmoment;


import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.DefaultFileItem;
import org.apache.commons.fileupload.DefaultFileItemFactory;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * Description : Attribut de servlet (durée de vie = 1 requête) pour les
 * controleurs
 */
public class ControllerBaseAttributes {

	private static Log logger = LogFactory.getLog(ControllerBaseAttributes.class);


	/**
	 * paramètre action : ce paramètre définit les méthodes qui seront exécutées
	 * par l'application, et aussi la classe CtrlAttributes qui sera utilisée.
	 */
	protected String action = null;

	/**
	 * Paramètre de redirection, facultatif, géré par le framework
	 */
	public boolean enableRedirectParam = false;

	/**
	 * Constructeur de <code>ControllerBaseAttributes</code>
	 */
	public ControllerBaseAttributes() {
		enableRedirectParam = false;
	}

	/**
	 * Renvoie une instance de la classe attributes correspondant au paramêtre
	 * ACTION trouvé dans la requête. Si le formulaire est encodé en multiform,
	 * alors la requête est parsée avec la librairie common-fileupload de
	 * Jakarta.
	 *
	 * @return Si aucun paramêtre ACTION n'est trouvé, la méthode renvoie une
	 * nouvelle instance de la classe <code>ControllerBaseAttributes</code>,
	 * et si une action est trouvée, alors la méthode essaie de renvoyer une
	 * nouvelle instance de la classe CtrlAttributesxxx (ou xxx est le nom de
	 * l'action), située dans le même package que le controleur en cours.
	 */
	public static ControllerBaseAttributes getInstance(HttpServletRequest req, SessionContainer sc,
			Class controllerClass) throws IncorrectParameterException {
		ControllerBaseAttributes attributes = null;
		boolean isMultipartContent = FileUploadBase.isMultipartContent(req);
		List l = null;
		String action = null;
		Hashtable h = null;
		if (isMultipartContent) {
			// Retrieve multipart content
			DefaultFileItemFactory dfif = new DefaultFileItemFactory();
			FileUpload upload = new FileUpload(dfif);
			String s = SConfig.getInstance().getProperty("Config.upload.maxSize");
			Long maxUploadSize = new Long(s);
			upload.setSizeMax(maxUploadSize.longValue());
			try {
				l = upload.parseRequest(req);
				h = new Hashtable(l.size());
				Iterator it = l.iterator();
				DefaultFileItem item = null;
				while (it.hasNext()) {
					item = (DefaultFileItem) it.next();
					h.put(item.getFieldName(), item);
				}
				// récupêre le paramêtre ACTION
				FileItem fi = (FileItem) h.get(ControllerBase.ACTION_PARAMETER);
				if (fi != null) {
					action = fi.getString();
				}
			}
			catch (FileUploadBase.SizeLimitExceededException slee) {
				// Le fichier uploadé dépasse la taille maximale
				sc.setMessageErreurKey("error.parameter.file_size");
				if (logger.isDebugEnabled()) {
					logger.debug("ControllerBaseAttributes", slee);
				}
				throw new IncorrectParameterException();
			}
			catch (FileUploadException e) {
				if (logger.isErrorEnabled()) {
					logger.error("Impossible de parser la requête multiform", e);
				}
				l = null;
				// x7786 3.4 UPLOAD INTERROMPU PAR UTILISATEUR
				// envoi exception pour stopper exécution servlet
				throw new IncorrectParameterException();
			}
		}
		else {
			action = req.getParameter(ControllerBase.ACTION_PARAMETER);
		}
		String className = controllerClass.getName();
		// x7786 3.5 ACTION NULL ou vide
		// envoi exception pour stopper execution servlet
		if (action == null || "".equals(action)) {
			if (logger.isWarnEnabled()) {
				logger.warn("Le parametre action 'mth' n'est pas defini " + "pour le controleur " + className);
			}
			sc.setMessageErreurKey("error.action.null");
			throw new IncorrectParameterException();
		}
		else {
			int i = className.lastIndexOf(".");
			className = className.substring(0, i + 1) + ControllerBase.CTRL_ATTRIBUTES_PREFIX + action;
			try {
				Class attributesClass = Class.forName(className);
				attributes = (ControllerBaseAttributes) attributesClass.newInstance();
			}
			catch (ClassNotFoundException cnfe) {
				if (logger.isWarnEnabled()) {
					logger.warn("la classe " + className + " n'a pas été trouvée " + "pour gérer l'action " + action
							+ " dans le controleur " + controllerClass.getName());
				}
				// x7786 3.X ACTION mal renseignée
				// envoi exception pour stopper execution servlet
				sc.setMessageErreurKey("error.action.wrong");
				throw new IncorrectParameterException();
			}
			catch (IllegalAccessException iae) {
				if (logger.isWarnEnabled()) {
					logger.warn("", iae);
				}
			}
			catch (InstantiationException ie) {
				if (logger.isWarnEnabled()) {
					logger.warn("", ie);
				}
			}
		}
		if (attributes == null) {
			attributes = new ControllerBaseAttributes();
		}
		if (isMultipartContent) {
			attributes.setFileItems(h);
		}
		attributes.setAction(action);
		return attributes;
	}

    private void setFileItems(Hashtable h) {
    }



    /**
	 * Sets the action.
	 * 
	 * @param action The action to set
	 */
	public void setAction(String action) {
		this.action = action;
	}
}