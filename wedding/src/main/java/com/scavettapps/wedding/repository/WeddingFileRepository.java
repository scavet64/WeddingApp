package com.scavettapps.wedding.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.scavettapps.wedding.domain.WeddingFile;

public interface WeddingFileRepository extends JpaRepository<WeddingFile, Long>{
	List<WeddingFile> findAllByOrderByCreatedOnDesc();
	
	Optional<WeddingFile> findBySha256(String hash);
}
