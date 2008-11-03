package com.sourcesense.maven.dbdep.plugin.parser;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.maven.model.Dependency;
import org.apache.maven.project.MavenProject;

import com.sourcesense.maven.dbdep.plugin.dataobject.DependencyDO;

/**
 * 
 * @author piergiorgiolucidi
 *
 */
public class POMParser {
	
	public List getDependenciesFromPOM(MavenProject project, String projectName, String environment) {
		List dependencies = new ArrayList();
		Iterator i = project.getDependencies().iterator();
		while(i.hasNext()){
			DependencyDO dependency = new DependencyDO();
			Dependency dep = (Dependency)i.next();
			dependency.setProject(projectName);
			dependency.setEnvironment(environment);
			dependency.setName(dep.getArtifactId());
			dependency.setVersion(dep.getVersion());
			dependencies.add(dependency);
		}
		return dependencies;
	}

}
