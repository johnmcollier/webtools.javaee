package org.eclipse.jst.j2ee.application.internal.operations;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jst.j2ee.componentcore.util.EARArtifactEdit;
import org.eclipse.jst.j2ee.datamodel.properties.IEarComponentCreationDataModelProperties;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.J2EEVersionUtil;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.internal.WorkbenchComponent;
import org.eclipse.wst.common.componentcore.internal.operation.ComponentCreationOperation;
import org.eclipse.wst.common.componentcore.internal.util.IModuleConstants;
import org.eclipse.wst.common.componentcore.resources.ComponentHandle;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualFolder;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

public class EARComponentCreationOperation extends ComponentCreationOperation implements IEarComponentCreationDataModelProperties{

	public EARComponentCreationOperation(IDataModel model) {
		super(model); 
	}
    /* (non-Javadoc)
     * @see org.eclipse.jst.j2ee.application.operations.J2EEComponentCreationOperation#createAndLinkJ2EEComponents()
     */
    protected void createAndLinkJ2EEComponentsForMultipleComponents() throws CoreException {
		IVirtualComponent component = ComponentCore.createComponent(getProject(), getModuleDeployName());
        component.create(0, null);
		//create and link META-INF folder
		IVirtualFolder root = component.getFolder(new Path("/")); //$NON-NLS-1$		
		root.createLink(new Path("/" + getModuleName()), 0, null); //$NON-NLS-1$
		
    	IVirtualFolder metaInfFolder = root.getFolder(J2EEConstants.META_INF);
    	metaInfFolder.create(IResource.FORCE, null);
    }
    
    protected void createAndLinkJ2EEComponentsForSingleComponent() throws CoreException {
        IVirtualComponent component = ComponentCore.createComponent(getProject(), getModuleDeployName());
        component.create(0, null);
        //create and link META-INF folder
        IVirtualFolder root = component.getFolder(new Path("/")); //$NON-NLS-1$     
        root.createLink(new Path("/" + getModuleName()), 0, null); //$NON-NLS-1$
        
        IVirtualFolder metaInfFolder = root.getFolder(J2EEConstants.META_INF);
        metaInfFolder.create(IResource.FORCE, null);
    }
    
	public IProject getProject() {
		String projName = model.getStringProperty(PROJECT_NAME);
		return ProjectUtilities.getProject( projName );
	}

	protected void createDeploymentDescriptor(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
        EARArtifactEdit earEdit = null;
        try {
            ComponentHandle handle = ComponentHandle.create(getProject(),getDataModel().getStringProperty(COMPONENT_DEPLOY_NAME));
            earEdit = EARArtifactEdit.getEARArtifactEditForWrite(handle);
            Integer version = (Integer)getDataModel().getProperty(COMPONENT_VERSION);
       	 	earEdit.createModelRoot(version.intValue());
            earEdit.save(monitor);
        } finally {
       		if (earEdit != null)
       		    earEdit.dispose();
        }		
	}

	public IStatus execute(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		try {
			super.execute(IModuleConstants.JST_EAR_MODULE, monitor, info);
			if (getDataModel().getBooleanProperty(CREATE_DEFAULT_FILES)) {
				createDeploymentDescriptor(monitor);
			}
			addModulesToEAR(monitor);
		} catch (CoreException e) {
			Logger.getLogger().log(e.getMessage());
		} catch (InvocationTargetException e) {
            Logger.getLogger().log(e.getMessage());
		} catch (InterruptedException e) {
            Logger.getLogger().log(e.getMessage());
		}
		return OK_STATUS;
	}
	
	private void addModulesToEAR(IProgressMonitor monitor) {
		try{
			AddComponentToEnterpriseApplicationDataModel dm = new AddComponentToEnterpriseApplicationDataModel();
			dm.setProperty(AddComponentToEnterpriseApplicationDataModel.PROJECT_NAME, model.getProperty(PROJECT_NAME));
			dm.setProperty(AddComponentToEnterpriseApplicationDataModel.EAR_PROJECT_NAME, model.getProperty(PROJECT_NAME));
			dm.setProperty(AddComponentToEnterpriseApplicationDataModel.EAR_MODULE_NAME, model.getProperty(COMPONENT_NAME));
			List modulesList = (List)model.getProperty(J2EE_COMPONENT_LIST);
			if (modulesList != null && !modulesList.isEmpty()) {
				dm.setProperty(AddComponentToEnterpriseApplicationDataModel.MODULE_LIST,modulesList);
				AddComponentToEnterpriseApplicationOperation addModuleOp = (AddComponentToEnterpriseApplicationOperation)dm.getDefaultOperation();
				addModuleOp.execute(monitor);
		   }
		 } catch(Exception e) {
			 Logger.getLogger().log(e);
		 }
	}
    
	protected  void addResources(WorkbenchComponent component ){
		//Default
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.application.operations.J2EEComponentCreationOperation#getVersion()
	 */
	protected String getVersion() {
		int version = getDataModel().getIntProperty(COMPONENT_VERSION);
		return J2EEVersionUtil.getJ2EETextVersion(version);
	}

	public IStatus redo(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		return null;
	}
	public IStatus undo(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		return null;
	}	

/*    protected void setupComponentType(String typeID) {
		IVirtualComponent component = ComponentCore.createComponent(getProject(), getModuleDeployName());
        ComponentType componentType = ComponentcoreFactory.eINSTANCE.createComponentType();
        componentType.setComponentTypeId(typeID);
        componentType.setVersion(getVersion());
        StructureEdit.setComponentType(component, componentType);
    }*/
	
	public String getModuleName() {
		return getDataModel().getStringProperty(COMPONENT_NAME);
	}
	
	public String getModuleDeployName() {
		return getDataModel().getStringProperty(COMPONENT_DEPLOY_NAME);
	}
    protected List getProperties() {
        // TODO Auto-generated method stub
        return null;
    }
}