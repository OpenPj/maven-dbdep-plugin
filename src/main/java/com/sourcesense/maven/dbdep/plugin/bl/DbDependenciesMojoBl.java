package com.sourcesense.maven.dbdep.plugin.bl;

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

import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.maven.model.Profile;
import org.apache.maven.project.MavenProject;

import com.sourcesense.maven.dbdep.plugin.dao.DbDependenciesDao;
import com.sourcesense.maven.dbdep.plugin.parser.POMParser;

/**
 * 
 * @author Piergiorgio Lucidi
 *
 */
public class DbDependenciesMojoBl implements IDbDependenciesMojoBl{

	private static Log log = LogFactory.getLog(DbDependenciesMojoBl.class);
	private DbDependenciesDao dbDependenciesDao;
	private POMParser pomParser;
	
	public void setDbDependenciesDao(DbDependenciesDao mateDao) {
		this.dbDependenciesDao = mateDao;
	}

	public void setPomParser(POMParser pomParser) {
		this.pomParser = pomParser;
	}
	
	public void write(MavenProject project, String projectName, String environment){
		log.info("DbDep started for project: "+project.getName());
		List dependencies = pomParser.getDependenciesFromPOM(project,projectName,environment);
		dbDependenciesDao.insertDependencies(dependencies);
		log.info("DbDep finished correctly for this project: "+project.getName());
	}
	
	/**
     * 
     * @param activeProfiles
     * @return environment profile associated to Maven settings.xml 
     */
	public String getEnvironment(List activeProfiles) {
		String environment = "";
    	Iterator i = activeProfiles.iterator();
    	while(i.hasNext()){
    		Profile profile = (Profile)i.next();
    		environment = profile.getId();
    	}
    	if("".equals(environment))
    		log.warn("No environment has found in your own settings.xml Maven file");
    	return environment;
    	
	}
	
}
