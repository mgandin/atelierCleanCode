package com.sgcib.training.object;

import java.sql.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;



public class CVFactory {

	private static Log logger = LogFactory.getLog(CVFactory.class);


	public static void insertFormation(CV cv) throws SQLException {
        logger.warn("inserting CV :" + cv.toString());
	}

    public static CV getTableauBord(Long intId) {
        return new CV();
    }

    
}