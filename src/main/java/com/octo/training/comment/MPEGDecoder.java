package com.octo.training.comment;

import java.io.IOException;
import java.io.InputStream;

/**
 * Lecture d'un fichier encodé en binaire MPEG.
 * Pour l'instant, n'implémente que la lecture de la version.
 */
public class MPEGDecoder implements IMPEGDecoder {


    private static final int HEADER_SIZE = 10;

    public void decode(InputStream is, MPEGDecoderCallback callback) throws IOException, MPEGDecoderException {
        byte[] buffer = new byte[HEADER_SIZE];
        int nbBytesRead = is.read(buffer);
        if( nbBytesRead != HEADER_SIZE ) {
            // Le fichier fait moins de 10 octets.
            throw new MPEGDecoderException("file is too small");
        }
        // Le 6ème octet du fichier contient le numéro de version.
        // Ce numero de version est sur 5 bits.
        // Il est précédé de 2 bit à 1 et suivi d'un bit à 1.
        // Pour extraire le numéro de version, on doit donc décaler
        // d'un bit vers la droite pour enlever le dernier bit, puis
        // enlever les 2 bits qui le précèdent, au moyen du bon masque
        // (AND binaire) :
        byte byteThatContainsVersionNumber =  buffer[5];
        int versionNumber = extractVersionNumber(byteThatContainsVersionNumber);
        callback.setVersionNumber( versionNumber );
    }

    private int extractVersionNumber( byte byteThatContainsVersionNumber ) {
        return ( ( byteThatContainsVersionNumber >> 1 ) & 31 );
    }
}
