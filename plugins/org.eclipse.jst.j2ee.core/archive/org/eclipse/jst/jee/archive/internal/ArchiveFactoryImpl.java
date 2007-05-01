package org.eclipse.jst.jee.archive.internal;

import java.io.IOException;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import org.eclipse.core.runtime.IPath;
import org.eclipse.jst.jee.archive.ArchiveOpenFailureException;
import org.eclipse.jst.jee.archive.ArchiveOptions;
import org.eclipse.jst.jee.archive.ArchiveSaveFailureException;
import org.eclipse.jst.jee.archive.IArchive;
import org.eclipse.jst.jee.archive.IArchiveFactory;
import org.eclipse.jst.jee.archive.IArchiveHandler;
import org.eclipse.jst.jee.archive.IArchiveSaveStrategy;


public class ArchiveFactoryImpl implements IArchiveFactory {

	public IArchive openArchive(IPath archivePath) throws ArchiveOpenFailureException {
		java.io.File file = new java.io.File(archivePath.toOSString());
		ZipFile zipFile;
		try {
			zipFile = new ZipFile(file);
		} catch (ZipException e) {
			ArchiveOpenFailureException openFailureException = new ArchiveOpenFailureException(e);
			throw openFailureException;
		} catch (IOException e) {
			ArchiveOpenFailureException openFailureException = new ArchiveOpenFailureException(e);
			throw openFailureException;
		}
		ZipFileLoadStrategyImpl loadStrategy = new ZipFileLoadStrategyImpl(zipFile);
		ArchiveOptions archiveOptions = new ArchiveOptions();
		archiveOptions.setOption(ArchiveOptions.LOAD_STRATEGY, loadStrategy);
		return openArchive(archiveOptions);
	}

	// TODO add tracing support
	// info in spec page 154
	public IArchive openArchive(ArchiveOptions archiveOptions) throws ArchiveOpenFailureException {
		IArchive archive = new ArchiveImpl(archiveOptions);
		if (archiveOptions.hasOption(ArchiveOptions.ARCHIVE_HANDLER)) {
			IArchiveHandler archiveHandler = (IArchiveHandler) archiveOptions.getOption(ArchiveOptions.ARCHIVE_HANDLER);
			archive = archiveHandler.convertToSpecificArchive(archive);
		} else {
			IArchiveHandler archiveHandler = ArchiveHanderFactory.getInstance().getArchiveHandler(archive);
			if (null != archiveHandler) {
				archive = archiveHandler.convertToSpecificArchive(archive);
			}
		}
		return archive;
	}

	public void closeArchive(IArchive archive) {
		((ArchiveImpl) archive).close();
		// TODO add tracing support
	}

	public void saveArchive(IArchive archive, IPath outputPath) throws ArchiveSaveFailureException {
		String aUri = outputPath.toOSString();
		java.io.File aFile = new java.io.File(aUri);
		ArchiveUtil.checkWriteable(aFile);
		boolean fileExisted = aFile.exists();
		IArchiveSaveStrategy aSaveStrategy = null;
		try {
			try {
				java.io.File destinationFile = fileExisted ? ArchiveUtil.createTempFile(aUri, aFile.getCanonicalFile().getParentFile()) : aFile;
				aSaveStrategy = createSaveStrategyForJar(destinationFile);
				aSaveStrategy.setArchive(archive);
				save(aSaveStrategy);

				aSaveStrategy.close();
				closeArchive(archive);
				if (fileExisted) {
					ArchiveUtil.cleanupAfterTempSave(aUri, aFile, destinationFile);
				}
			} catch (java.io.IOException ex) {
				// TODO throw new
				// SaveFailureException(CommonArchiveResourceHandler.getString(CommonArchiveResourceHandler.error_saving_EXC_,
				// (new Object[]{aUri})), ex); // = "Error saving "
			}
		} catch (ArchiveSaveFailureException failure) {
			try {
				if (aSaveStrategy != null)
					aSaveStrategy.close();
			} catch (IOException weTried) {
				// Ignore
			}
			if (!fileExisted)
				aFile.delete();
			throw failure;
		}
	}

	protected IArchiveSaveStrategy createSaveStrategyForJar(java.io.File aFile) throws java.io.IOException {
		if (aFile.exists() && aFile.isDirectory()) {
			// TODO throw new
			// IOException(CommonArchiveResourceHandler.getString(CommonArchiveResourceHandler.file_exist_as_dir_EXC_,
			// (new Object[]{aFile.getAbsolutePath()})));// = "A file named {0}
			// exists and is a directory"
		}
		java.io.File parent = aFile.getParentFile();
		if (parent != null)
			parent.mkdirs();
		java.io.OutputStream out = new java.io.FileOutputStream(aFile);
		return new ZipStreamSaveStrategyImpl(out);
	}

	public void save(IArchiveSaveStrategy aStrategy) throws ArchiveSaveFailureException {
		try {
			aStrategy.save();
		} finally {
			try {
				aStrategy.close();
			} catch (IOException e) {
				throw new ArchiveSaveFailureException(e);
			}
		}
	}

	public void saveArchive(IArchive archive, ArchiveOptions archiveOptions) throws ArchiveSaveFailureException {
		// TODO Auto-generated method stub
		
	}

}
