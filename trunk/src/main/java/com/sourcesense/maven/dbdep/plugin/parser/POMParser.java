package com.sourcesense.maven.dbdep.plugin.parser;

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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.maven.model.Dependency;
import org.apache.maven.project.MavenProject;

import com.sourcesense.maven.dbdep.plugin.dataobject.DependencyDO;

/**
 * 
 * @author Piergiorgio Lucidi
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
