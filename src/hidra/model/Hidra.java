package hidra.model;

/**
 * @author Urbieta Souza
 *
 */

import java.io.File;
import java.util.Set;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.InitCommand;
import org.eclipse.jgit.api.Status;
import org.eclipse.jgit.api.errors.GitAPIException;
//import org.eclipse.jgit.lib.Constants;
//import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.Repository;

//import org.junit.Test;

/**
 * @author Danielli Urbieta e Pedro Souza Junior
 * 
 * 
 *         class Depot
 */
public class Hidra {

	private Git git;

	private String localPath;

	private String remotePath;

	private Repository localRepository;

	private String diretoryToCopy;

	private Repository repository;

	@SuppressWarnings("rawtypes")
	private Set added;

	@SuppressWarnings("rawtypes")
	private Set removed;

	private Status status;

	
	public Hidra(Git git){
		this.git = git;
	}
	
	
	/**
	 * construtor da classe
	 * 
	 * @param localPath
	 * @param remotePath
	 */
	public Hidra(String localPath, String remotePath) {

		this.localPath = localPath;
		this.remotePath = remotePath;
	}

	/**
	 * @param localPath
	 */
	public Hidra(String localPath) {
		super();
		this.localPath = localPath;
	}

	/**
	 *
	 */
	public Hidra() {
		super();
	}

	/* ================ metodos geters e seters ===================== */

	/**
	 * @return the localPath
	 */
	public String getLocalPath() {
		return this.localPath;
	}

	/**
	 * @param localPath
	 *            the localPath to set
	 */
	public void setLocalPath(String localPath) {
		this.localPath = localPath;
	}

	/**
	 * @return the remotePath
	 */
	public String getRemotePath() {
		return this.remotePath;
	}

	/**
	 * @param remotePath
	 *            the remotePath to set
	 */
	public void setRemotePath(String remotePath) {
		this.remotePath = remotePath;
	}

	/**
	 * @return the localRepository
	 */
	public Repository getLocalRepository() {
		return this.localRepository;
	}

	/**
	 * @param localRepository
	 *            the localRepository to set
	 */
	public void setLocalRepository(Repository localRepository) {
		this.localRepository = localRepository;
	}

	/**
	 * @return the git
	 */
	public Git getGit() {
		return this.git;
	}

	/**
	 * @param git
	 *            the git to set
	 */
	public void setGit(Git git) {
		this.git = git;
	}

	/**
	 * @return the repository
	 */
	public Repository getrepository() {
		return this.repository;
	}

	/**
	 * @param repository
	 *            the repository to set
	 */
	public void setrepository(Repository repository) {
		this.repository = repository;
	}

	/**
	 * @return Set
	 */
	@SuppressWarnings("rawtypes")
	public Set getAdded() {
		return this.added;
	}

	/**
	 * @param added
	 */
	@SuppressWarnings("rawtypes")
	public void setAdded(Set added) {
		this.added = added;
	}

	/**
	 * @return removido
	 */
	@SuppressWarnings("rawtypes")
	public Set getRemoved() {
		return removed;
	}

	/**
	 * @param removed
	 */
	@SuppressWarnings("rawtypes")
	public void setRemoved(Set removed) {
		this.removed = removed;
	}

	/**
	 * @return String
	 */
	public String getDiretoryToCopy() {
		return diretoryToCopy;
	}

	/**
	 * @param diretoryToCopy
	 */
	public void setDiretoryToCopy(String diretoryToCopy) {
		this.diretoryToCopy = diretoryToCopy;
	}

	/**
	 * @return Repository
	 */
	public Repository getRepository() {
		return repository;
	}

	/**
	 * @param repository
	 */
	public void setRepository(Repository repository) {
		this.repository = repository;
	}

	/**
	 * @return Status
	 */
	public Status getStatus() {
		return status;
	}

	/**
	 * @param status
	 */
	public void setStatus(Status status) {
		this.status = status;
	}

}