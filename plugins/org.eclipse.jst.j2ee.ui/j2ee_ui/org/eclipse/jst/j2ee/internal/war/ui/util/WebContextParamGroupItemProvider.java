/*
 * Created on Mar 29, 2004
 */
package org.eclipse.jst.j2ee.internal.war.ui.util;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.jst.j2ee.internal.web.plugin.WebPlugin;
import org.eclipse.jst.j2ee.internal.web.providers.WebAppEditResourceHandler;
import org.eclipse.jst.j2ee.webapplication.WebApp;


/**
 * @author jlanuti
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public class WebContextParamGroupItemProvider extends WebGroupItemProvider {

	/**
	 * @param adapterFactory
	 */
	public WebContextParamGroupItemProvider(AdapterFactory adapterFactory, WeakReference weakWebApp) {
		super(adapterFactory, weakWebApp);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.emf.edit.provider.ITreeItemContentProvider#getChildren(java.lang.Object)
	 */
	public Collection getChildren(Object object) {
		List result = new ArrayList();
		if (weakWebApp!=null) {
			Object webApp = weakWebApp.get();
			if(null != webApp){
				result.addAll(((WebApp)webApp).getContextParams());
			}
		}
		return result;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.emf.edit.provider.ItemProvider#getImage()
	 */
	public Object getImage(Object object) {
		return WebPlugin.getDefault().getImage("initializ_parameter_context"); //$NON-NLS-1$
	}
	/* (non-Javadoc)
	 * @see org.eclipse.emf.edit.provider.ItemProvider#getText()
	 */
	public String getText(Object object) {
		return WebAppEditResourceHandler.getString("Context_Parameters_2"); //$NON-NLS-1$ 
	}
	/* (non-Javadoc)
	 * @see org.eclipse.emf.edit.provider.ITreeItemContentProvider#hasChildren(java.lang.Object)
	 */
	public boolean hasChildren(Object object) {
		return !getChildren(object).isEmpty();
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.emf.edit.provider.ITreeItemContentProvider#getParent(java.lang.Object)
	 */
	public Object getParent(Object object) {
		return weakWebApp.get();
	}
}