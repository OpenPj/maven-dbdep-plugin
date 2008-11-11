package com.sourcesense.maven.dbdep.plugin.dao;

/*
 * Copyright 2008 Sourcesense 
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 * 
 * http://www.apache.org/licenses/LICENSE-2.0 
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under 
 * the License is distributed on an 
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and limitations under the License. 
 */

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.sourcesense.maven.dbdep.plugin.dataobject.DependencyDO;

/**
 * 
 * @author Piergiorgio Lucidi
 *
 */
public class DbDependenciesDao implements IDbDependeciesDao{
	
	private static Log log = LogFactory.getLog(DbDependenciesDao.class);
	private String psInsert;
	private String psSelect;
	
	private JdbcTemplate jdbcTemplate;

	public void setDs(DataSource ds) {
		this.jdbcTemplate = new JdbcTemplate(ds);
	}

	
	public void insertDependencies(List dependencies) {
		int effect = -1;
		Iterator i = dependencies.iterator();
		while(i.hasNext()){
			DependencyDO dependency = (DependencyDO)i.next();
			if(!existDependency(dependency)){
				try {
					effect = this.jdbcTemplate.update(psInsert,
					new Object[] { dependency.getProject(),
							dependency.getEnvironment(),
							dependency.getName(),
							dependency.getVersion()});
				} catch (DataAccessException e) {
					log.error("Error during storing this dependency: " + dependency,e);
					throw new RuntimeException("Error during storing this dependency: " + dependency,e);
				}
				
				if(effect==0){
					log.error("Error during storing this dependency: " + dependency);
					throw new RuntimeException("Error during storing this dependency: " + dependency);
				}
			}
			if(log.isDebugEnabled())
				log.debug("dependency: "+dependency.getName()+" " +
						"version: "+dependency.getVersion()+" is in database");
		}

	}

	/**
	 * 
	 * @param dependency
	 * @return TRUE if dependency exist
	 */
	private boolean existDependency(final DependencyDO dependency){
		Boolean result = new Boolean(false);
		try {
			 result = (Boolean)this.jdbcTemplate.query(psSelect,
					new PreparedStatementSetter(){
						public void setValues(java.sql.PreparedStatement ps) throws java.sql.SQLException {
							ps.setString(1, dependency.getProject());
							ps.setString(2, dependency.getEnvironment());
							ps.setString(3, dependency.getName());
							ps.setString(4, dependency.getVersion());
						};
					}
					, new ResultSetExtractor(){
						public Object extractData(ResultSet rs)
								throws SQLException, DataAccessException {
							if(rs!=null && rs.next())
								return new Boolean(true);
							else
								return new Boolean(false);
						}}
					);	
		} catch (DataAccessException e) {
			log.error("Error during checking this dependency: "+dependency,e);
			throw new RuntimeException("Error during checking this dependency: "+dependency,e);
		}
		
		return result.booleanValue();
	}

	public void setPsInsert(String psInsert) {
		this.psInsert = psInsert;
	}

	public void setPsSelect(String psSelect) {
		this.psSelect = psSelect;
	}
	
}
