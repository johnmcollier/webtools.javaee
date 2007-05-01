/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jee.archive.internal;

import java.util.zip.ZipFile;

import org.eclipse.core.runtime.IPath;
import org.eclipse.jst.jee.archive.ArchiveModelLoadException;
import org.eclipse.jst.jee.archive.IArchive;



public class EMFZipFileLoadStrategyImpl extends ZipFileLoadStrategyImpl {

	private EMFLoadStrategyHelper emfHelper = new EMFLoadStrategyHelper();

	public EMFZipFileLoadStrategyImpl() {
		super();
	}

	public EMFZipFileLoadStrategyImpl(ZipFile zipFile) {
		super(zipFile);
	}

	public void setArchive(IArchive archive) {
		super.setArchive(archive);
		emfHelper.setArchive(archive);
	}

	public boolean containsModelObject(IPath modelObjectPath) {
		return emfHelper.containsModelObject(modelObjectPath);
	}

	public Object getModelObject(IPath modelObjectPath) throws ArchiveModelLoadException {
		return emfHelper.getModelObject(modelObjectPath);
	}
}
