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

import java.util.List;

import org.apache.maven.project.MavenProject;

/**
 * 
 * @author Piergiorgio Lucidi
 *
 */
public interface IDbDependenciesMojoBl {

	public static final String BEAN_NAME = "dbDepMojoBl";
	public void write(MavenProject project, String projectName, String environment);
	public String getEnvironment(List activeProfiles);
	
}
