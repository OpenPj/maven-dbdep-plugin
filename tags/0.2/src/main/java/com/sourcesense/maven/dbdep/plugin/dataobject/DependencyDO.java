package com.sourcesense.maven.dbdep.plugin.dataobject;

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

/**
 * Data Object for dependency
 * @author Piergiorgio Lucidi
 * 
 */
public class DependencyDO {

	private String project = "";
	private String environment = "";
	private String name = "";
	private String version = "";

	public String toString() {
		return "DependencyDO | Project: " + this.project + " | Environment: "
				+ this.environment + " | Name: " + this.name + " | Version: "
				+ this.version;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getEnvironment() {
		return environment;
	}

	public void setEnvironment(String environment) {
		this.environment = environment;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

}
