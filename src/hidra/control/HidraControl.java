package hidra.control;

import hidra.model.Hidra;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.ConcurrentRefUpdateException;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRefNameException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.NoFilepatternException;
import org.eclipse.jgit.api.errors.NoHeadException;
import org.eclipse.jgit.api.errors.NoMessageException;
import org.eclipse.jgit.api.errors.RefAlreadyExistsException;
import org.eclipse.jgit.api.errors.RefNotFoundException;
import org.eclipse.jgit.api.errors.TransportException;
import org.eclipse.jgit.api.errors.UnmergedPathsException;
import org.eclipse.jgit.api.errors.WrongRepositoryStateException;
import org.eclipse.jgit.errors.NoWorkTreeException;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;

@WebService(serviceName = "HidraControl/hidraControl")
public class HidraControl implements IHidra {

	private Hidra hidra;

	public Hidra getHidra() {
		return hidra;
	}

	public void setHidra(Hidra hidra) {
		this.hidra = hidra;
	}

	@Override
	@WebMethod
	public void inicializar(@WebParam(name = "localPath") String localPath) {
		File dir = new File(localPath);
		Git git;

		try {
			git = Git.init().setDirectory(dir).call();
			hidra = new Hidra(git);

		} catch (GitAPIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		@SuppressWarnings("unused")
		Repository repository;

		try {
			repository = FileRepositoryBuilder.create(new File(dir
					.getAbsolutePath(), ".git"));
			hidra.setLocalPath(dir.getAbsolutePath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	@WebMethod
	public boolean adicionar(@WebParam(name = "fileName") String fileName) {
		String tmp;

		if (hidra == null) {
			System.err.println("Repositorio não inicializado");
		} else {

			tmp = hidra.getLocalPath() + "/" + fileName;

			if (!new File(tmp).exists()) {
				System.err.println("Arquivo inexistente");
			} else {
				try {
					hidra.getGit().add().addFilepattern(fileName).call();
					hidra.setAdded(hidra.getGit().status().call().getAdded());
				} catch (NoFilepatternException e) {
					e.printStackTrace();
				} catch (GitAPIException e) {
					e.printStackTrace();
				}

			}
			return true;
		}
		return false;
	}

	@Override
	@WebMethod
	public boolean remove(@WebParam(name = "filename") String filename) {
		String tmp;
		if (hidra.equals(null))
			System.err.println("Repositorio nao inicializado");
		else {
			tmp = hidra.getLocalPath() + "/" + filename;

			if (!new File(tmp).exists()) {
				System.err.println("Arquivo inexistente");
			} else {
				try {
					hidra.getGit().rm().addFilepattern(filename).call();
					hidra.setRemoved(this.hidra.getGit().status().call()
							.getUntracked());
				} catch (NoFilepatternException e) {
					e.printStackTrace();
				} catch (GitAPIException e) {
					e.printStackTrace();
				}

				if (this.hidra.getRemoved() != null)
					System.out.println("Arquivo excluido com sucesso");

			}
			return true;
		}

		return false;
	}

	@Override
	@WebMethod
	public boolean commit(@WebParam(name = "message") String message) {

		if (hidra.equals(null))
			System.err.println("Repositorio nao inicializado");
		else {
			try {
				RevCommit commit = hidra.getGit().commit().setMessage(message)
						.call();
				System.out.println(commit.getId().getName());
			} catch (NoHeadException e) {
				e.printStackTrace();
			} catch (NoMessageException e) {
				e.printStackTrace();
			} catch (UnmergedPathsException e) {
				e.printStackTrace();
			} catch (ConcurrentRefUpdateException e) {
				e.printStackTrace();
			} catch (WrongRepositoryStateException e) {
				e.printStackTrace();
			} catch (GitAPIException e) {
				e.printStackTrace();
			}

			return true;
		}

		return false;
	}

	@WebMethod
	@Override
	public void clone(@WebParam(name = "remotePath") String remotePath,
			@WebParam(name = "localPath") String localPath) {
		File file = new File(localPath);
		try {
			Git.cloneRepository().setURI(remotePath).setDirectory(file).call();
			hidra = new Hidra();
			hidra.setRemotePath(remotePath);
			hidra.setLocalPath(localPath);

		} catch (InvalidRemoteException e) {
			System.err.println("URL invalida " + e);
			e.printStackTrace();
		} catch (TransportException e) {
			e.printStackTrace();
		} catch (GitAPIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		FileRepositoryBuilder builder = new FileRepositoryBuilder();
		try {
			builder.setGitDir(file).readEnvironment().findGitDir().build();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@WebMethod
	@Override
	public String status() {
		if (hidra.equals(null))
			System.err.println("Repositorio nao inicializado");
		else {
			try {
				hidra.setStatus(hidra.getGit().status().call());
				String showStatus = "Added: "
						+ this.hidra.getStatus().getAdded() + "\nChanged"
						+ this.hidra.getStatus().getChanged()
						+ "\nConflicting: "
						+ this.hidra.getStatus().getConflicting()
						+ "\nConflictingStageState: "
						+ this.hidra.getStatus().getConflictingStageState()
						+ "\nIgnoredNotInIndex: "
						+ this.hidra.getStatus().getIgnoredNotInIndex()
						+ "\nMissing: " + this.hidra.getStatus().getMissing()
						+ "\nModified: " + this.hidra.getStatus().getModified()
						+ "\nRemoved: " + this.hidra.getStatus().getRemoved()
						+ "\nUntracked: "
						+ this.hidra.getStatus().getUntracked()
						+ "\nUntrackedFolders: "
						+ this.hidra.getStatus().getUntrackedFolders()
						+ "\nUncommitted Changes"
						+ this.hidra.getStatus().getUncommittedChanges();

				return showStatus;
			} catch (NoWorkTreeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (GitAPIException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public String getLogs() {
		String logs = null;
		if (hidra.equals(null)) {
			System.err.println("Repositorio nao inicializado");
		} else {

			// Repository repository1 = git1.getRepository();
			//ObjectId head = repository1.resolve("HEAD"); //$NON-NLS-1$
			Iterable<RevCommit> log = null;
			try {
				log = hidra.getGit().log().call();
			} catch (NoHeadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (GitAPIException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			@SuppressWarnings("rawtypes")
			Iterator itr = log.iterator();

			while (itr.hasNext()) {
				// Object element = itr.next();
				RevCommit rev = (RevCommit) itr.next();
				// System.out.println(element);
				logs = "Author: " + rev.getAuthorIdent().getName()
						+ "\nMessage: " + rev.getFullMessage();
				/*
				 * System.out.println("Author: " +
				 * rev.getAuthorIdent().getName()); //$NON-NLS-1$
				 * System.out.println("Message: " + rev.getFullMessage());
				 * //$NON-NLS-1$ System.out.println();
				 */
				return logs;
			}

		}
		return logs;
	}

	@WebMethod
	public String showBranch() {
		String branches = null;
		if (hidra.equals(null)) {
			System.err.println("Repositorio nao inicializado");
		} else {
			List<org.eclipse.jgit.lib.Ref> call = null;
			try {
				call = new Git(hidra.getGit().getRepository()).branchList().call();
			} catch (GitAPIException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// repensar mostrar ou não o id do branch
			for (org.eclipse.jgit.lib.Ref ref : call) {
				branches = "Branch: "  + ref.getName(); //$NON-NLS-1$ //$NON-NLS-2$
			}
		}
		hidra.getGit().getRepository().close();
		return branches;
	}
	
	@WebMethod
	public String createBranch(@WebParam(name = "nameBranch")String nameBranch){
		String branch = null;
		if (hidra.equals(null)) {
			System.err.println("Repositorio nao inicializado");
		} else {
			try {
				hidra.getGit().branchCreate().setName(nameBranch).call();

			} catch (RefAlreadyExistsException e1) {

				e1.printStackTrace();
			} catch (RefNotFoundException e1) {

				e1.printStackTrace();
			} catch (InvalidRefNameException e1) {

				e1.printStackTrace();
			} catch (GitAPIException e1) {

				e1.printStackTrace();
			}

			List<org.eclipse.jgit.lib.Ref> call = null;
			try {
				call = new Git(hidra.getGit().getRepository()).branchList().call();
			} catch (GitAPIException e) {

				e.printStackTrace();
			}

			for (org.eclipse.jgit.lib.Ref ref : call) {
				branch = "Branch Created: " + " " + ref.getName(); //$NON-NLS-1$ //$NON-NLS-2$

			}
			hidra.getGit().getRepository().close();
			
			return branch;
		}
		return branch;
	}
}
