package com.sourcesense.maven.dbdep.plugin.bl;


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
 * @author piergiorgiolucidi
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
		log.info("DbDep writing starting...: "+project.getName());
		List dependencies = pomParser.getDependenciesFromPOM(project,projectName,environment);
		dbDependenciesDao.insertDependencies(dependencies);
		log.info("DbDep writing has finished correctly for this project: "+project.getName());
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
    		log.warn("No environment has found in settings.xml Maven file");
    	return environment;
    	
	}
	
}
