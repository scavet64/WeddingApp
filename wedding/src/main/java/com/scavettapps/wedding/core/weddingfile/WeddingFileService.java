package com.scavettapps.wedding.core.weddingfile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

import javax.xml.bind.DatatypeConverter;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.scavettapps.wedding.domain.Event;
import com.scavettapps.wedding.domain.WeddingFile;
import com.scavettapps.wedding.domain.WeddingUser;
import com.scavettapps.wedding.repository.WeddingFileRepository;

@Service
public class WeddingFileService {

	@Autowired
	WeddingFileRepository fileRepo;

	@Autowired
	private WeddingFileRepository contentRepo;

	@Value("${upload.basepath}")
	private String basePath;

	public List<WeddingFile> getAllFilesOrderdByUploaded() {
		return contentRepo.findAllByOrderByCreatedOnDesc();
	}

	public WeddingFile saveAndStoreFile(MultipartFile file, WeddingUser userMakingFile, Event event,
			WeddingFileUploadRequest uploadContentReq) {

		File storedFile = null;
		try {
			// String fileName = filePath.substring(filePath.lastIndexOf('\\') + 1);

			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] sha256 = digest.digest(file.getBytes());
			String sha256Hash = DatatypeConverter.printHexBinary(sha256);

			// Do we already have this file?
			Optional<WeddingFile> existingFile = contentRepo.findBySha256(sha256Hash);
			if(existingFile.isPresent()) {
				throw new WeddingFileAlreadyExistsException();
			}

			// store file based on hash
			storedFile = this.storeFile(file, sha256Hash);

			// Create new content object then save
			WeddingFile content = new WeddingFile(sha256Hash, uploadContentReq.getName(), userMakingFile, event,
					uploadContentReq.getComment(), FilenameUtils.getExtension(file.getOriginalFilename()));
			content = contentRepo.save(content);
			return content;
		} catch (DataAccessException ex) {
			// Could not save the new content. Revert delete the file
			if (storedFile != null) {
				storedFile.delete();
			}
			throw new WeddingFileCreationException("Could not save the new content into the database");
		} catch (IOException ex) {
			throw new WeddingFileCreationException("Could not store file", ex);
		} catch (NoSuchAlgorithmException ex) {
			throw new WeddingFileCreationException("Could not generate checksum");
		}
	}

	/**
	 * Store file to disk
	 *
	 * @param file      file to store
	 * @param newFileId id of fjle
	 * @return path to file
	 */
	private File storeFile(MultipartFile file, String hash) throws IOException {
		// String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		// fileName = Sanitizer.scrubFilename(fileName);
		File targetLocation = getDownloadPath(hash);
		if (targetLocation.getParentFile().exists() || targetLocation.getParentFile().mkdirs()) {
			Files.copy(file.getInputStream(), targetLocation.toPath(), StandardCopyOption.REPLACE_EXISTING);
			return targetLocation;
		} else {
			throw new IOException("Path could not be made");
		}
	}

	public File getDownloadPath(String hash) {

		String hashSubstring = hash.substring(0, 5);

		StringBuilder builder = new StringBuilder();
		String newFilePath = builder.append(basePath).append(File.separator).append(hashSubstring)
				.append(File.separator).append(hash).toString();

		return new File(newFilePath);
	}

	/**
	 * finds the file on the file system and loads it into a resource
	 *
	 * @param fileName the path of the file
	 * @return resource that is the file
	 * @throws FileNotFoundException if the file is not found
	 */
	public Resource loadFileAsResource(File file) throws FileNotFoundException {
		try {
			// LOGGER.info(filePath);
			Resource resource = new UrlResource(file.toPath().toUri());
			if (resource.exists()) {
				return resource;
			} else {
				throw new FileNotFoundException("File not found " + file.getName());
			}
		} catch (MalformedURLException ex) {
			throw new FileNotFoundException("File not found " + file.getName());
		}
	}

}
