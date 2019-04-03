package com.scavettapps.wedding.core.weddingfile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.scavettapps.wedding.core.event.EventService;
import com.scavettapps.wedding.core.response.DataResponse;
import com.scavettapps.wedding.core.response.ErrorResponse;
import com.scavettapps.wedding.core.response.Response;
import com.scavettapps.wedding.domain.WeddingFile;
import com.scavettapps.wedding.domain.Event;
import com.scavettapps.wedding.domain.WeddingUser;
import com.scavettapps.wedding.repository.WeddingFileRepository;
import com.scavettapps.wedding.security.services.UserDetailsMapperService;

@RestController
@RequestMapping("/files")
public class WeddingFileController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserDetailsMapperService mapper;

	@Autowired
	private EventService eventService;

	@Autowired
	private WeddingFileService fileService;

	@GetMapping
	public ResponseEntity<Response> getAllFilesOrderdByDate() {
		
		return ResponseEntity.ok(new DataResponse(fileService.getAllFilesOrderdByUploaded()));
	}

	/**
	 * 
	 * @param files         multipart files to upload
	 * @param newDocRequest object containing info about document
	 * @param userDetails   the user making the change
	 * @return file upload response containing all successful files, failed files
	 *         and the created document object
	 * @throws DomainObjectNotFoundException if persisting to the DB fails
	 * @throws NotFoundException             if file creating fails
	 */
	@PostMapping("/uploadFiles")
	public ResponseEntity<Response> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files,
			WeddingFileUploadRequest uploadContentReq, @AuthenticationPrincipal UserDetails userDetails) {

		// Get the user and event from the request
		WeddingUser weddingUser = mapper.getLeashUserFromUserDetails(userDetails);
		Event event = eventService.getEventById(Long.valueOf(uploadContentReq.getEventId()));

		for (int x = 0; x < files.length; x++) {
			try {
				WeddingFile fileEntry = fileService.saveAndStoreFile(files[x], weddingUser, event, uploadContentReq);
				// uploadedContent.add(fileEntry);
			} catch (WeddingFileAlreadyExistsException ex) {
				throw ex;
				//return ResponseEntity.badRequest().body(new ErrorResponse("File Alredy Exists"));
			} catch (WeddingFileCreationException ex) {
				throw ex;
				//return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse(ex.getLocalizedMessage()));
			}
		}
		return ResponseEntity.ok(new DataResponse("Successfully Uploaded"));
	}

	/**
	 * The download File endpoint
	 *
	 * @param fileID  takes the ID of the file
	 * @param request HttpServletRequest
	 * @return returns the file as a byte stream
	 * @throws FileNotFoundException if not file is found
	 */
	@GetMapping("/downloadFile/{filehash:.+}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String fileID, HttpServletRequest request)
			throws FileNotFoundException {

		File filePath = fileService.getDownloadPath(fileID);
		// Load file as Resource
		Resource resource = fileService.loadFileAsResource(filePath);

		// Try to determine file's content type
		String contentType = null;
		try {
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		} catch (IOException ex) {
			logger.info("Could not determine file type.");
		}

		// Fallback to the default content type if type could not be determined
		if (contentType == null) {
			contentType = "application/octet-stream";
		}

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filePath.getName() + "\"")
				.body(resource);
	}

}
