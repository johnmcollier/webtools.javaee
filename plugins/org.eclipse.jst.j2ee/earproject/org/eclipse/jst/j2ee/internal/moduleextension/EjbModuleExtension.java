/*******************************************************************************
 * Copyright (c) 2003, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 * Created on Sep 19, 2003
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.eclipse.jst.j2ee.internal.moduleextension;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.j2ee.ejb.EJBJar;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelOperation;



/**
 * @author cbridgha
 * 
 * To change the template for this generated type comment go to Window>Preferences>Java>Code
 * Generation>Code and Comments
 */
public interface EjbModuleExtension extends EarModuleExtension {

	//void initializeEjbReferencesToModule(J2EENature nature, UpdateModuleReferencesInEARProjectCommand cmd);

	//J2EEJavaProjectInfo createProjectInfo();

	//J2EEImportOperationOLD createImportOperation(IProject proj, EJBJarFile ejbJarFile);

	EJBJar getEJBJar(IProject aProject);

	IProject getDefinedEJBClientJARProject(IProject anEJBProject);

	IDataModelOperation createEJBClientJARProject(
			final String clientProjectName,
			final String srcFolderName,
			final String ejbProjectName,
			final String earProjectName,
			final org.eclipse.wst.common.project.facet.core.runtime.IRuntime runtime);
	
	IDataModelOperation createEJBClientJARProject(IProject ejbProject);	
	
}
