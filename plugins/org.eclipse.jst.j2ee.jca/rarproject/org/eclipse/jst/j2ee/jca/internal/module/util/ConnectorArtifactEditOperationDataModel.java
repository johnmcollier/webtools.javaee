/*******************************************************************************
 * Copyright (c) 2003, 2004, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.jca.internal.module.util;

import org.eclipse.wst.common.frameworks.operations.WTPOperation;
import org.eclipse.wst.common.modulecore.ArtifactEdit;
import org.eclipse.wst.common.modulecore.WorkbenchComponent;
import org.eclipse.wst.common.modulecore.internal.operation.ArtifactEditOperationDataModel;

public class ConnectorArtifactEditOperationDataModel extends ArtifactEditOperationDataModel {

    public WTPOperation getDefaultOperation() {
        return new ConnectorArtifactEditOperation(this);
    }
    
    private ArtifactEdit getArtifactEditForRead(WorkbenchComponent module) {
        return ConnectorArtifactEdit.getConnectorArtifactEditForRead(module);
    }

}
