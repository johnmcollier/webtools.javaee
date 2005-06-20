/*
 * Created on Mar 14, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.eclipse.jst.j2ee.internal.wizard;

import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jst.j2ee.internal.actions.IJ2EEUIContextIds;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIMessages;
import org.eclipse.jst.j2ee.project.datamodel.properties.IFlexibleJavaProjectCreationDataModelProperties;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wst.common.frameworks.datamodel.DataModelPropertyDescriptor;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.internal.datamodel.ui.DataModelWizardPage;
import org.eclipse.wst.common.frameworks.internal.operations.IProjectCreationProperties;
import org.eclipse.wst.server.ui.ServerUIUtil;

public class FlexibleProjectCreationWizardPage extends DataModelWizardPage implements IFlexibleJavaProjectCreationDataModelProperties {
	private static final boolean isWindows = SWT.getPlatform().toLowerCase().startsWith("win"); //$NON-NLS-1$

	protected NewFlexibleProjectGroup projectNameGroup;
	protected Composite advancedComposite;
	protected Button advancedButton;
	protected boolean showAdvanced = false;
	protected AdvancedSizeController advancedController;
	protected boolean advancedControlsBuilt = false;
	protected Combo serverTargetCombo;

	private class AdvancedSizeController implements ControlListener {
		private int advancedHeight = -1;
		private Point originalSize;
		private boolean ignoreShellResize = false;

		private AdvancedSizeController(Shell aShell) {
			originalSize = aShell.getSize();
			aShell.addControlListener(this);
		}

		public void controlMoved(ControlEvent e) {
			//do nothing
		}

		public void controlResized(ControlEvent e) {
			if (!ignoreShellResize) {
				Control control = (Control) e.getSource();
				if (control.isVisible()) {
					originalSize = control.getSize();
					if (advancedHeight == -1)
						setShellSizeForAdvanced();
				}
			}
		}

		protected void resetOriginalShellSize() {
			setShellSize(originalSize.x, originalSize.y);
		}

		private void setShellSize(int x, int y) {
			ignoreShellResize = true;
			try {
				getShell().setSize(x, y);
			} finally {
				ignoreShellResize = false;
			}
		}

		protected void setShellSizeForAdvanced() {
			int height = calculateAdvancedShellHeight();
			if (height != -1)
				setShellSize(getShell().getSize().x, height);
		}

		private int calculateAdvancedShellHeight() {
			Point advancedCompSize = advancedComposite.getSize();
			if (advancedCompSize.x == 0)
				return -1;
			int height = computeAdvancedHeight();
			if (!showAdvanced && height != -1)
				height = height - advancedComposite.getSize().y;
			return height;
		}

		/*
		 * Compute the height with the advanced section showing. @return
		 */
		private int computeAdvancedHeight() {
			if (advancedHeight == -1) {
				Point controlSize = getControl().getSize();
				if (controlSize.x != 0) {
					int minHeight = originalSize.y - controlSize.y;
					Point pageSize = getControl().computeSize(SWT.DEFAULT, SWT.DEFAULT);
					advancedHeight = pageSize.y + minHeight;
				}
			}
			return advancedHeight;
		}
	}

	/**
	 * @param model
	 * @param pageName
	 */
	public FlexibleProjectCreationWizardPage(IDataModel model, String pageName) {
		super(model, pageName);
		setTitle(J2EEUIMessages.getResourceString(J2EEUIMessages.FLEXIBLE_PROJECT_MAIN_PG_TITLE));
		setDescription(J2EEUIMessages.getResourceString(J2EEUIMessages.FLEXIBLE_PROJECT_MAIN_PG_DESC));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.internal.ui.wizard.J2EEProjectCreationPage#createTopLevelComposite(org.eclipse.swt.widgets.Composite)
	 */
	protected Composite createTopLevelComposite(Composite parent) {
		setInfopopID(IJ2EEUIContextIds.NEW_EAR_WIZARD_P1);
		Composite top = new Composite(parent, SWT.NONE);
		top.setLayout(new GridLayout());
		top.setData(new GridData(GridData.FILL_BOTH));
		Composite composite = new Composite(top, SWT.NONE);
		GridLayout layout = new GridLayout(3, false);
		composite.setLayout(layout);
		createProjectNameGroup(composite);
		Composite detail = new Composite(top, SWT.NONE);
		detail.setLayout(new GridLayout());
		detail.setLayoutData(new GridData(GridData.FILL_BOTH));

		createAdvancedComposite(detail);
		return top;
	}

	protected void createProjectNameGroup(Composite parent) {
		projectNameGroup = new NewFlexibleProjectGroup(parent, SWT.NULL, model, synchHelper);
	}

	/**
	 * @param parent
	 */
	protected Composite createAdvancedComposite(Composite parent) {
		advancedControlsBuilt = true;
		advancedButton = new Button(parent, SWT.TOGGLE);
		setAdvancedLabelText();
		final Cursor hand = new Cursor(advancedButton.getDisplay(), SWT.CURSOR_HAND);
		advancedButton.addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				hand.dispose();
			}
		});
		advancedButton.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				toggleAdvanced(true);
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				//do nothing
			}
		});
		advancedButton.addListener(SWT.MouseHover, new Listener() {
			public void handleEvent(Event event) {
				if (event.type == SWT.MouseHover)
					advancedButton.setCursor(hand);
			}
		});
		advancedComposite = new Composite(parent, SWT.NONE);
		//toggleAdvanced(false);
		GridLayout layout = new GridLayout(3, false);
		GridData data = new GridData(GridData.FILL_HORIZONTAL);
		advancedComposite.setLayoutData(data);
		advancedComposite.setLayout(layout);
		addToAdvancedComposite(advancedComposite);
		return advancedComposite;
	}

	/**
	 * @param advancedLabel
	 */
	private void setAdvancedLabelText() {
		if (advancedControlsBuilt) {
			if (showAdvanced)
				advancedButton.setText(J2EEUIMessages.getResourceString("J2EEProjectCreationPage_UI_0")); //$NON-NLS-1$
			else
				advancedButton.setText(J2EEUIMessages.getResourceString("J2EEProjectCreationPage_UI_1")); //$NON-NLS-1$
		}
	}

	/**
	 * @param advancedLabel
	 */
	protected void toggleAdvanced(boolean setSize) {
		if (advancedControlsBuilt) {
			showAdvanced = !showAdvanced;
			advancedComposite.setVisible(showAdvanced);
			setAdvancedLabelText();
			if (setSize && isWindows)
				advancedController.setShellSizeForAdvanced();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.ui.wizard.WTPWizardPage#enter()
	 */
	protected void enter() {
		if (advancedControlsBuilt) {
			if (isFirstTimeToPage)
				initializeAdvancedController();
			if (isWindows) {
				advancedController.setShellSizeForAdvanced();
			}
		}
		super.enter();
	}

	private void initializeAdvancedController() {
		advancedController = new AdvancedSizeController(getShell());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.ui.wizard.WTPWizardPage#exit()
	 */
	protected void exit() {
		if (advancedControlsBuilt && isWindows) {
			advancedController.resetOriginalShellSize();
		}
		super.exit();
	}

	protected void createServerTargetComposite(Composite parent) {
		Label label = new Label(parent, SWT.NONE);
		label.setText(J2EEUIMessages.getResourceString(J2EEUIMessages.TARGET_SERVER_LBL));
		serverTargetCombo = new Combo(parent, SWT.BORDER | SWT.READ_ONLY);
		serverTargetCombo.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		Button newServerTargetButton = new Button(parent, SWT.NONE);
		newServerTargetButton.setText(J2EEUIMessages.getResourceString(J2EEUIMessages.NEW_THREE_DOTS_E));
		newServerTargetButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				FlexibleProjectCreationWizardPage.launchNewRuntimeWizard(getShell(), model.getNestedModel(NESTED_MODEL_SERVER_TARGET));
			}
		});
		Control[] deps = new Control[]{label, newServerTargetButton};
		synchHelper.synchCombo(serverTargetCombo, RUNTIME_TARGET_ID, deps);
        if(serverTargetCombo.getVisibleItemCount() != 0)
            serverTargetCombo.select(0);		
	}

	protected void addToAdvancedComposite(Composite parent) {
		createServerTargetComposite(parent);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.ui.wizard.J2EEWizardPage#getValidationPropertyNames()
	 */
	protected String[] getValidationPropertyNames() {
		return new String[]{IProjectCreationProperties.PROJECT_NAME, PROJECT_LOCATION, RUNTIME_TARGET_ID};
	}

	public static boolean launchNewRuntimeWizard(Shell shell, IDataModel model) {
		DataModelPropertyDescriptor[] preAdditionDescriptors = model.getValidPropertyDescriptors(RUNTIME_TARGET_ID);
		boolean isOK = ServerUIUtil.showNewRuntimeWizard(shell, "", "");  //$NON-NLS-1$  //$NON-NLS-2$
		if (isOK && model != null) {
			model.notifyPropertyChange(RUNTIME_TARGET_ID, IDataModel.VALID_VALUES_CHG);
			DataModelPropertyDescriptor[] postAdditionDescriptors = model.getValidPropertyDescriptors(RUNTIME_TARGET_ID);
			Object[] preAddition = new Object[preAdditionDescriptors.length];
			for (int i = 0; i < preAddition.length; i++) {
				preAddition[i] = preAdditionDescriptors[i].getPropertyValue();
			}
			Object[] postAddition = new Object[postAdditionDescriptors.length];
			for (int i = 0; i < postAddition.length; i++) {
				postAddition[i] = postAdditionDescriptors[i].getPropertyValue();
			}
			Object newAddition = ProjectUtilities.getNewObject(preAddition, postAddition);

            model.notifyPropertyChange(RUNTIME_TARGET_ID, IDataModel.VALID_VALUES_CHG);
			if (newAddition != null)
				model.setProperty(RUNTIME_TARGET_ID, newAddition);
		}
		return isOK;
	}
}