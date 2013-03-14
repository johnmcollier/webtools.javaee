/**
 */
package org.eclipse.jst.javaee.core.internal.impl;

import java.math.BigInteger;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.jst.javaee.core.ConnectionFactoryResourceType;
import org.eclipse.jst.javaee.core.Description;
import org.eclipse.jst.javaee.core.PropertyType;
import org.eclipse.jst.javaee.core.TransactionSupport;

import org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Connection Factory Resource Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.ConnectionFactoryResourceTypeImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.ConnectionFactoryResourceTypeImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.ConnectionFactoryResourceTypeImpl#getClassName <em>Class Name</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.ConnectionFactoryResourceTypeImpl#getResourceAdapter <em>Resource Adapter</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.ConnectionFactoryResourceTypeImpl#getMaxPoolSize <em>Max Pool Size</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.ConnectionFactoryResourceTypeImpl#getMinPoolSize <em>Min Pool Size</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.ConnectionFactoryResourceTypeImpl#getTransactionSupport <em>Transaction Support</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.ConnectionFactoryResourceTypeImpl#getProperty <em>Property</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.ConnectionFactoryResourceTypeImpl#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ConnectionFactoryResourceTypeImpl extends EObjectImpl implements ConnectionFactoryResourceType {
	/**
	 * The cached value of the '{@link #getDescription() <em>Description</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected Description description;

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getClassName() <em>Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClassName()
	 * @generated
	 * @ordered
	 */
	protected static final String CLASS_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getClassName() <em>Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClassName()
	 * @generated
	 * @ordered
	 */
	protected String className = CLASS_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getResourceAdapter() <em>Resource Adapter</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResourceAdapter()
	 * @generated
	 * @ordered
	 */
	protected static final String RESOURCE_ADAPTER_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getResourceAdapter() <em>Resource Adapter</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResourceAdapter()
	 * @generated
	 * @ordered
	 */
	protected String resourceAdapter = RESOURCE_ADAPTER_EDEFAULT;

	/**
	 * The default value of the '{@link #getMaxPoolSize() <em>Max Pool Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaxPoolSize()
	 * @generated
	 * @ordered
	 */
	protected static final BigInteger MAX_POOL_SIZE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMaxPoolSize() <em>Max Pool Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaxPoolSize()
	 * @generated
	 * @ordered
	 */
	protected BigInteger maxPoolSize = MAX_POOL_SIZE_EDEFAULT;

	/**
	 * The default value of the '{@link #getMinPoolSize() <em>Min Pool Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMinPoolSize()
	 * @generated
	 * @ordered
	 */
	protected static final BigInteger MIN_POOL_SIZE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMinPoolSize() <em>Min Pool Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMinPoolSize()
	 * @generated
	 * @ordered
	 */
	protected BigInteger minPoolSize = MIN_POOL_SIZE_EDEFAULT;

	/**
	 * The default value of the '{@link #getTransactionSupport() <em>Transaction Support</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTransactionSupport()
	 * @generated
	 * @ordered
	 */
	protected static final TransactionSupport TRANSACTION_SUPPORT_EDEFAULT = TransactionSupport.NO_TRANSACTION;

	/**
	 * The cached value of the '{@link #getTransactionSupport() <em>Transaction Support</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTransactionSupport()
	 * @generated
	 * @ordered
	 */
	protected TransactionSupport transactionSupport = TRANSACTION_SUPPORT_EDEFAULT;

	/**
	 * This is true if the Transaction Support attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean transactionSupportESet;

	/**
	 * The cached value of the '{@link #getProperty() <em>Property</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProperty()
	 * @generated
	 * @ordered
	 */
	protected EList<PropertyType> property;

	/**
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected static final String ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected String id = ID_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ConnectionFactoryResourceTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JavaeePackage.Literals.CONNECTION_FACTORY_RESOURCE_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Description getDescription() {
		return description;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDescription(Description newDescription, NotificationChain msgs) {
		Description oldDescription = description;
		description = newDescription;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, JavaeePackage.CONNECTION_FACTORY_RESOURCE_TYPE__DESCRIPTION, oldDescription, newDescription);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDescription(Description newDescription) {
		if (newDescription != description) {
			NotificationChain msgs = null;
			if (description != null)
				msgs = ((InternalEObject)description).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - JavaeePackage.CONNECTION_FACTORY_RESOURCE_TYPE__DESCRIPTION, null, msgs);
			if (newDescription != null)
				msgs = ((InternalEObject)newDescription).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - JavaeePackage.CONNECTION_FACTORY_RESOURCE_TYPE__DESCRIPTION, null, msgs);
			msgs = basicSetDescription(newDescription, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.CONNECTION_FACTORY_RESOURCE_TYPE__DESCRIPTION, newDescription, newDescription));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.CONNECTION_FACTORY_RESOURCE_TYPE__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getClassName() {
		return className;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setClassName(String newClassName) {
		String oldClassName = className;
		className = newClassName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.CONNECTION_FACTORY_RESOURCE_TYPE__CLASS_NAME, oldClassName, className));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getResourceAdapter() {
		return resourceAdapter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setResourceAdapter(String newResourceAdapter) {
		String oldResourceAdapter = resourceAdapter;
		resourceAdapter = newResourceAdapter;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.CONNECTION_FACTORY_RESOURCE_TYPE__RESOURCE_ADAPTER, oldResourceAdapter, resourceAdapter));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger getMaxPoolSize() {
		return maxPoolSize;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMaxPoolSize(BigInteger newMaxPoolSize) {
		BigInteger oldMaxPoolSize = maxPoolSize;
		maxPoolSize = newMaxPoolSize;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.CONNECTION_FACTORY_RESOURCE_TYPE__MAX_POOL_SIZE, oldMaxPoolSize, maxPoolSize));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger getMinPoolSize() {
		return minPoolSize;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMinPoolSize(BigInteger newMinPoolSize) {
		BigInteger oldMinPoolSize = minPoolSize;
		minPoolSize = newMinPoolSize;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.CONNECTION_FACTORY_RESOURCE_TYPE__MIN_POOL_SIZE, oldMinPoolSize, minPoolSize));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TransactionSupport getTransactionSupport() {
		return transactionSupport;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTransactionSupport(TransactionSupport newTransactionSupport) {
		TransactionSupport oldTransactionSupport = transactionSupport;
		transactionSupport = newTransactionSupport == null ? TRANSACTION_SUPPORT_EDEFAULT : newTransactionSupport;
		boolean oldTransactionSupportESet = transactionSupportESet;
		transactionSupportESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.CONNECTION_FACTORY_RESOURCE_TYPE__TRANSACTION_SUPPORT, oldTransactionSupport, transactionSupport, !oldTransactionSupportESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetTransactionSupport() {
		TransactionSupport oldTransactionSupport = transactionSupport;
		boolean oldTransactionSupportESet = transactionSupportESet;
		transactionSupport = TRANSACTION_SUPPORT_EDEFAULT;
		transactionSupportESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, JavaeePackage.CONNECTION_FACTORY_RESOURCE_TYPE__TRANSACTION_SUPPORT, oldTransactionSupport, TRANSACTION_SUPPORT_EDEFAULT, oldTransactionSupportESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetTransactionSupport() {
		return transactionSupportESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<PropertyType> getProperty() {
		if (property == null) {
			property = new EObjectContainmentEList<PropertyType>(PropertyType.class, this, JavaeePackage.CONNECTION_FACTORY_RESOURCE_TYPE__PROPERTY);
		}
		return property;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getId() {
		return id;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setId(String newId) {
		String oldId = id;
		id = newId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.CONNECTION_FACTORY_RESOURCE_TYPE__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case JavaeePackage.CONNECTION_FACTORY_RESOURCE_TYPE__DESCRIPTION:
				return basicSetDescription(null, msgs);
			case JavaeePackage.CONNECTION_FACTORY_RESOURCE_TYPE__PROPERTY:
				return ((InternalEList<?>)getProperty()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case JavaeePackage.CONNECTION_FACTORY_RESOURCE_TYPE__DESCRIPTION:
				return getDescription();
			case JavaeePackage.CONNECTION_FACTORY_RESOURCE_TYPE__NAME:
				return getName();
			case JavaeePackage.CONNECTION_FACTORY_RESOURCE_TYPE__CLASS_NAME:
				return getClassName();
			case JavaeePackage.CONNECTION_FACTORY_RESOURCE_TYPE__RESOURCE_ADAPTER:
				return getResourceAdapter();
			case JavaeePackage.CONNECTION_FACTORY_RESOURCE_TYPE__MAX_POOL_SIZE:
				return getMaxPoolSize();
			case JavaeePackage.CONNECTION_FACTORY_RESOURCE_TYPE__MIN_POOL_SIZE:
				return getMinPoolSize();
			case JavaeePackage.CONNECTION_FACTORY_RESOURCE_TYPE__TRANSACTION_SUPPORT:
				return getTransactionSupport();
			case JavaeePackage.CONNECTION_FACTORY_RESOURCE_TYPE__PROPERTY:
				return getProperty();
			case JavaeePackage.CONNECTION_FACTORY_RESOURCE_TYPE__ID:
				return getId();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case JavaeePackage.CONNECTION_FACTORY_RESOURCE_TYPE__DESCRIPTION:
				setDescription((Description)newValue);
				return;
			case JavaeePackage.CONNECTION_FACTORY_RESOURCE_TYPE__NAME:
				setName((String)newValue);
				return;
			case JavaeePackage.CONNECTION_FACTORY_RESOURCE_TYPE__CLASS_NAME:
				setClassName((String)newValue);
				return;
			case JavaeePackage.CONNECTION_FACTORY_RESOURCE_TYPE__RESOURCE_ADAPTER:
				setResourceAdapter((String)newValue);
				return;
			case JavaeePackage.CONNECTION_FACTORY_RESOURCE_TYPE__MAX_POOL_SIZE:
				setMaxPoolSize((BigInteger)newValue);
				return;
			case JavaeePackage.CONNECTION_FACTORY_RESOURCE_TYPE__MIN_POOL_SIZE:
				setMinPoolSize((BigInteger)newValue);
				return;
			case JavaeePackage.CONNECTION_FACTORY_RESOURCE_TYPE__TRANSACTION_SUPPORT:
				setTransactionSupport((TransactionSupport)newValue);
				return;
			case JavaeePackage.CONNECTION_FACTORY_RESOURCE_TYPE__PROPERTY:
				getProperty().clear();
				getProperty().addAll((Collection<? extends PropertyType>)newValue);
				return;
			case JavaeePackage.CONNECTION_FACTORY_RESOURCE_TYPE__ID:
				setId((String)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case JavaeePackage.CONNECTION_FACTORY_RESOURCE_TYPE__DESCRIPTION:
				setDescription((Description)null);
				return;
			case JavaeePackage.CONNECTION_FACTORY_RESOURCE_TYPE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case JavaeePackage.CONNECTION_FACTORY_RESOURCE_TYPE__CLASS_NAME:
				setClassName(CLASS_NAME_EDEFAULT);
				return;
			case JavaeePackage.CONNECTION_FACTORY_RESOURCE_TYPE__RESOURCE_ADAPTER:
				setResourceAdapter(RESOURCE_ADAPTER_EDEFAULT);
				return;
			case JavaeePackage.CONNECTION_FACTORY_RESOURCE_TYPE__MAX_POOL_SIZE:
				setMaxPoolSize(MAX_POOL_SIZE_EDEFAULT);
				return;
			case JavaeePackage.CONNECTION_FACTORY_RESOURCE_TYPE__MIN_POOL_SIZE:
				setMinPoolSize(MIN_POOL_SIZE_EDEFAULT);
				return;
			case JavaeePackage.CONNECTION_FACTORY_RESOURCE_TYPE__TRANSACTION_SUPPORT:
				unsetTransactionSupport();
				return;
			case JavaeePackage.CONNECTION_FACTORY_RESOURCE_TYPE__PROPERTY:
				getProperty().clear();
				return;
			case JavaeePackage.CONNECTION_FACTORY_RESOURCE_TYPE__ID:
				setId(ID_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case JavaeePackage.CONNECTION_FACTORY_RESOURCE_TYPE__DESCRIPTION:
				return description != null;
			case JavaeePackage.CONNECTION_FACTORY_RESOURCE_TYPE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case JavaeePackage.CONNECTION_FACTORY_RESOURCE_TYPE__CLASS_NAME:
				return CLASS_NAME_EDEFAULT == null ? className != null : !CLASS_NAME_EDEFAULT.equals(className);
			case JavaeePackage.CONNECTION_FACTORY_RESOURCE_TYPE__RESOURCE_ADAPTER:
				return RESOURCE_ADAPTER_EDEFAULT == null ? resourceAdapter != null : !RESOURCE_ADAPTER_EDEFAULT.equals(resourceAdapter);
			case JavaeePackage.CONNECTION_FACTORY_RESOURCE_TYPE__MAX_POOL_SIZE:
				return MAX_POOL_SIZE_EDEFAULT == null ? maxPoolSize != null : !MAX_POOL_SIZE_EDEFAULT.equals(maxPoolSize);
			case JavaeePackage.CONNECTION_FACTORY_RESOURCE_TYPE__MIN_POOL_SIZE:
				return MIN_POOL_SIZE_EDEFAULT == null ? minPoolSize != null : !MIN_POOL_SIZE_EDEFAULT.equals(minPoolSize);
			case JavaeePackage.CONNECTION_FACTORY_RESOURCE_TYPE__TRANSACTION_SUPPORT:
				return isSetTransactionSupport();
			case JavaeePackage.CONNECTION_FACTORY_RESOURCE_TYPE__PROPERTY:
				return property != null && !property.isEmpty();
			case JavaeePackage.CONNECTION_FACTORY_RESOURCE_TYPE__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (name: "); //$NON-NLS-1$
		result.append(name);
		result.append(", className: "); //$NON-NLS-1$
		result.append(className);
		result.append(", resourceAdapter: "); //$NON-NLS-1$
		result.append(resourceAdapter);
		result.append(", maxPoolSize: "); //$NON-NLS-1$
		result.append(maxPoolSize);
		result.append(", minPoolSize: "); //$NON-NLS-1$
		result.append(minPoolSize);
		result.append(", transactionSupport: "); //$NON-NLS-1$
		if (transactionSupportESet) result.append(transactionSupport); else result.append("<unset>"); //$NON-NLS-1$
		result.append(", id: "); //$NON-NLS-1$
		result.append(id);
		result.append(')');
		return result.toString();
	}

} //ConnectionFactoryResourceTypeImpl
