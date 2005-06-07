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
package org.eclipse.jst.j2ee.application.internal.operations;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.common.componentcore.util.ComponentUtilities;
import org.eclipse.jst.j2ee.datamodel.properties.IJ2EEComponentExportDataModelProperties;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.frameworks.datamodel.AbstractDataModelProvider;
import org.eclipse.wst.common.frameworks.datamodel.DataModelPropertyDescriptor;
import org.eclipse.wst.common.frameworks.internal.plugin.WTPCommonMessages;
import org.eclipse.wst.common.frameworks.internal.plugin.WTPCommonPlugin;

public abstract class J2EEArtifactExportDataModelProvider extends AbstractDataModelProvider implements IJ2EEComponentExportDataModelProperties {

    public J2EEArtifactExportDataModelProvider() {
        super();
    }

    public String[] getPropertyNames() {
        return new String[]{COMPONENT_NAME, ARCHIVE_DESTINATION, EXPORT_SOURCE_FILES, OVERWRITE_EXISTING, RUN_BUILD};
    }
    protected abstract String getComponentID();

    protected abstract String getWrongProjectTypeString(String projectName);

    protected abstract String getModuleExtension();
    
    public Object getDefaultProperty(String propertyName) {
        if (propertyName.equals(ARCHIVE_DESTINATION)) {
            return ""; //$NON-NLS-1$
        } else if (propertyName.equals(EXPORT_SOURCE_FILES)) {
            return Boolean.FALSE;
        } else if (propertyName.equals(OVERWRITE_EXISTING)) {
            return Boolean.FALSE;
        } else if (propertyName.equals(RUN_BUILD)) {
            return Boolean.TRUE;
        }
        return super.getDefaultProperty(propertyName);
    }

    /**
     * Populate the resource name combo with connector projects that are not encrypted.
     */
    public DataModelPropertyDescriptor[] getValidPropertyDescriptors(String propertyName) {
        //TODO: populate valid components
        if (propertyName.equals(COMPONENT_NAME)) {
            List componentNames = new ArrayList();
            IVirtualComponent[] wbComps = ComponentUtilities.getAllWorkbenchComponents();
            
            List relevantComponents = new ArrayList();
            for (int i = 0; i < wbComps.length; i++) {
                if(wbComps[i].getComponentTypeId().equals(getComponentID()))
                    relevantComponents.add(wbComps[i]);
            }
            
            if(relevantComponents == null || relevantComponents.size() == 0) return null;
            
            for (int j = 0; j < relevantComponents.size(); j++) {
                componentNames.add(((IVirtualComponent)relevantComponents.get(j)).getName());
            }
            Object[] components = relevantComponents.toArray(new Object[relevantComponents.size()]);
            String[] names = (String[])componentNames.toArray(new String[componentNames.size()]);
            
            return DataModelPropertyDescriptor.createDescriptors(components, names);
        }
        return super.getValidPropertyDescriptors(propertyName);
        //(ProjectUtilities.getProjectNamesWithoutForwardSlash((String[]) projectsWithNature.toArray(new String[projectsWithNature.size()])));
    }
    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperationDataModel#doValidateProperty(java.lang.String)
     */
    public IStatus validate(String propertyName) {
//        if (PROJECT_NAME.equals(propertyName)) {
//            String projectName = (String) model.getProperty(PROJECT_NAME);
            //TODO: add manual project name validation
//          IStatus status = ProjectCreationDataModel.validateProjectName(projectName);
//          if (!status.isOK()) {
//              return status;
//          }
//            if(projectName == null) return OK_STATUS;
//            IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
//            if (!project.exists()) {
//                return WTPCommonPlugin.createErrorStatus(WTPCommonPlugin.getResourceString(WTPCommonMessages.PROJECT_NOT_EXISTS_ERROR, new Object[]{projectName}));
//            }
//            try {
//                if (!project.hasNature(getNatureID())) {
//                    return WTPCommonPlugin.createErrorStatus(getWrongProjectTypeString(project.getName()));
//                }
//            } catch (CoreException e) {
//                Logger.getLogger().logError(e);
//            }
//        } 
    if (ARCHIVE_DESTINATION.equals(propertyName)) {
            String archiveLocation = (String) model.getProperty(ARCHIVE_DESTINATION);
            if (!model.isPropertySet(ARCHIVE_DESTINATION) || archiveLocation.equals("")) { //$NON-NLS-1$
                return WTPCommonPlugin.createErrorStatus(WTPCommonPlugin.getResourceString(WTPCommonMessages.DESTINATION_INVALID)); //$NON-NLS-1$);
            } else if (model.isPropertySet(ARCHIVE_DESTINATION) && !validateModuleType(archiveLocation)) {
                return WTPCommonPlugin.createErrorStatus(WTPCommonPlugin.getResourceString(WTPCommonMessages.DESTINATION_ARCHIVE_SHOULD_END_WITH, new Object[]{getModuleExtension()})); //$NON-NLS-1$);
            } else if (model.isPropertySet(ARCHIVE_DESTINATION)) {
                IStatus tempStatus = validateLocation(archiveLocation);
                if (tempStatus != OK_STATUS)
                    return tempStatus;
            }
        }
        if (ARCHIVE_DESTINATION.equals(propertyName) || OVERWRITE_EXISTING.equals(propertyName)) {
            String location = (String) getProperty(ARCHIVE_DESTINATION);
            if (checkForExistingFileResource(location)) {
                return WTPCommonPlugin.createErrorStatus(WTPCommonPlugin.getResourceString(WTPCommonMessages.RESOURCE_EXISTS_ERROR, new Object[]{location}));
            }
        }
        return OK_STATUS;
    }
    

  private IStatus validateLocation(String archiveLocation) {
        IPath path = null;
        try {
            path = new Path(archiveLocation);
        } catch (IllegalArgumentException ex) {
            return WTPCommonPlugin.createErrorStatus(WTPCommonPlugin.getResourceString(WTPCommonMessages.DESTINATION_INVALID));
        }
        IWorkspace workspace = ResourcesPlugin.getWorkspace();
        IStatus status = workspace.validateName(path.lastSegment(), IResource.FILE);
        if (!status.isOK()) {
            return status;
        }
        String device = path.getDevice();
        if (device == null)
            return OK_STATUS;
        if (path == null || device.length() == 1 && device.charAt(0) == Path.DEVICE_SEPARATOR)
            return WTPCommonPlugin.createErrorStatus(WTPCommonPlugin.getResourceString(WTPCommonMessages.DESTINATION_INVALID));

        if (!path.toFile().canWrite()) {
            if (path.toFile().exists()) {
                return WTPCommonPlugin.createErrorStatus(WTPCommonPlugin.getResourceString(WTPCommonMessages.IS_READ_ONLY));
            }
            boolean OK = false;
            path = path.removeLastSegments(1);
            for (int i = 1; !OK && i < 20 && path.segmentCount() > 0; i++) {
                if (path.toFile().exists()) {
                    OK = true;
                }
                status = workspace.validateName(path.lastSegment(), IResource.FOLDER);
                if (!status.isOK()) {
                    return WTPCommonPlugin.createErrorStatus(WTPCommonPlugin.getResourceString(WTPCommonMessages.DESTINATION_INVALID));
                }
                path = path.removeLastSegments(1);
            }
        }

        return OK_STATUS;
    }

    private boolean checkForExistingFileResource(String fileName) {
        if (!model.getBooleanProperty(OVERWRITE_EXISTING)) {
            java.io.File externalFile = new java.io.File(fileName);
            if (externalFile != null && externalFile.exists())
                return true;
        }
        return false;
    }

    private boolean validateModuleType(String archive) {
        if ((archive.length() < 4) || (!(archive.substring(archive.length() - 4, archive.length()).equalsIgnoreCase(getModuleExtension())))) {
            return false;
        }
        return true;
    }

}
