//----------------------------------------------------------------------------79
//
//
// Langage     : Java
//
// Description : 
//
// Auteur        : Mathieu Gandin - Octo
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
