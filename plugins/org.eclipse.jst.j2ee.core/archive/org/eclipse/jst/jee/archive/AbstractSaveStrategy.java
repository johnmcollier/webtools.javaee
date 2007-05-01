package org.eclipse.jst.jee.archive;

import java.io.IOException;
import java.util.List;

public abstract class AbstractSaveStrategy extends AbstractArchiveStrategy implements IArchiveSaveStrategy {

	public void close() throws IOException {
	}

	public void finish() throws IOException {
	}

	public void save() throws ArchiveSaveFailureException {
		try {
			List files = getArchive().getArchiveResources();
			IArchiveResource file = null;
			for (int i = 0; i < files.size(); i++) {
				file = (IArchiveResource) files.get(i);
				if (shouldSave(file)) {
					save(file);
				}
			}
		} finally {
			try {
				finish();
			} catch (IOException e) {
				throw new ArchiveSaveFailureException(e);
			}
		}
	}

	protected abstract void save(IArchiveResource file) throws ArchiveSaveFailureException;

	protected boolean shouldSave(IArchiveResource file) {
		return true;
	}

}
