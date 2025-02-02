package com.oasis.poc1.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oasis.poc1.entity.OasisPoc1;
import com.oasis.poc1.repository.OasisPoc1Repo;

/**************
 * Class: Poc1DatabaseService 
 * 
 * Purpose: This class will have functions that communicates with Database.
 * 
 */
@Service
public class Poc1DatabaseService {
	
	Logger logger = LoggerFactory.getLogger(Poc1DatabaseService.class);
	
	@Autowired
	DataSource datasource;
	
	@Autowired
	OasisPoc1Repo repository;
	
	/**************
	 * Method: checkDatabaseConnection 
	 * Purpose: This method will test the Database Connection status
	 * Input parameters: None
	 * @return Success/Failure String as response
	 */
	public String checkDatabaseConnection() throws SQLException {	
		logger.info("**** inside checkDatabaseConnection() begins ****");
		String response ="";
		if(datasource.getConnection().isValid(1000)) {
			logger.info("Database connection valid = {}"+ datasource.getConnection().isValid(1000));
		    response = "Database is connected successfully";
		    /*
		    DatabaseMetaData md = datasource.getConnection().getMetaData();
		    ResultSet rs = md.getTables(null, null, "%", null);
		    while (rs.next()) {
		      System.out.println(rs.getString(3));
		    }
		    */
		}else {
			response = "Error in connecting database. Please check the connection.";
		}
		logger.info("**** inside checkDatabaseConnection() ends ****");
		return response;
	}
	
	/**************
	 * Method: getAllEntitiesfromDatabase 
	 * Purpose: This method will return all values store in Oasis_Poc1 table
	 * Input parameters: None
	 * @return List <Oasis_Poc1>
	 */	
	public List<OasisPoc1> getAllEntitiesfromDatabase(){
		logger.info("*** inside getAllEntitiesfromDb() begins ****");
		List<OasisPoc1> entityList = new ArrayList<OasisPoc1>();
		entityList=repository.findAll();
		if(Objects.nonNull(entityList)&& entityList.size()!=0) {
			logger.info("**** Respose : "+ entityList.toString()+" ****");
		}else {
			logger.error("Entity List is empty is Database");
		}
		logger.info("**** inside getAllEntitiesfromDb() ends ****");
		return entityList;
	}
	
	
}
