//----------------------------------------------------------------------------79
// SMILE
//
// @(#) $Header: /home/cvs/RefonteEspacePro/src/fr/smile/fwk/exceptions/IncorrectParameterException.java,v 1.2 2005/11/30 16:09:28 dahug Exp $
//
// Langage     : Java
//
// Description : 
//
// Auteur        : Arnaud Mary - SMILE
// Date creation : 12 nov. 2003
//
// Historique    :
//
//----------------------------------------------------------------------------79

package com.octo.training.coment;

/**
 * @author
 * 
 * Exception lancée lorsqu'un paramètre est manquant ou incorrect
 * 
 */
public class IncorrectParameterException extends Exception {

	/**
	 * 
	 */
	public IncorrectParameterException() {
		super();
	}

	/**
	 * @param msg
	 */
	public IncorrectParameterException(String msg) {
		super(msg);
	}

}
