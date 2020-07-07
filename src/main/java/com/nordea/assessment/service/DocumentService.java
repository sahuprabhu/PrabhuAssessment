/*
 * Main Service class to save / retrive / writing to cache
 * @Auther : Prabhu Sahu
 * */
package com.nordea.assessment.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import com.nordea.assessment.dao.DocumentErrorRepo;
import com.nordea.assessment.dao.EndToEndRepo;
import com.nordea.assessment.entity.DocumentErrorLog;
import com.nordea.assessment.entity.EndToEnd;
import com.nordea.assessment.model.Document;
import com.nordea.assessment.utility.XMLValidator;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class DocumentService {

	private final EndToEndRepo endToEndRepo;
    private final DocumentErrorRepo documentErrorRepo;
    private final XMLValidator xmlValidator;
	/*
	 * SAves EndToEnd in Database
	 * @param Documet Obj
	 * 
	 * */
	public void saveEndToEndId(Document document) {
		log.info("Saving EndToEndId to DB started");
		try {
			document.getCstmrCdtTrfInitn().getPmtInf().forEach(
					pmt-> pmt.getCdtTrfTxInf().forEach(cdtTrf ->
							{
								EndToEnd endToEndEntity = new EndToEnd();
								endToEndEntity = new EndToEnd();
								endToEndEntity.setEndToEndId(cdtTrf.getPmtId().getEndToEndId());
								endToEndRepo.save(endToEndEntity);
							}));
			
			
			log.info("Saving EndToEndId to DB Finished");
		}catch(Exception e) {
			log.error("Erroe while saving saveEndToEndId :"+e.getMessage());
		}
		
	}

	/*
	 * Reads data from database
	 * 
	 * */
	public ItemReader<EndToEnd> databaseItemReader() {
		try {
			log.info("Reading EndToEndId from DB started");
			Map<String, Sort.Direction> sorts = new HashMap<>();
			sorts.put("id", Direction.ASC);
			RepositoryItemReader<EndToEnd> databaseReader = new RepositoryItemReader<>();
			databaseReader.setRepository(endToEndRepo);
			databaseReader.setMethodName("findAll");
			databaseReader.setSort(sorts);
			return databaseReader;	
		}catch(Exception e) {
			log.error("Erroe while reading from DB databaseItemReader :"+e.getMessage());
			return null;
		}
		
	}
	
	/*
	 * Writes data in cache. The return value of this method will be cached
	 * @param EndToEnd Entity
	 * 
	 * */
	@Cacheable(value = "endToEndIdCacheById")
	public EndToEnd writeToCache(EndToEnd endToEnd) {
		log.info("Writing to cache : ");
		
		return endToEnd;
	}

	/*
	 * Fetch single Entity from DB and Writes data in cache. The return value of this method will be cached
	 * @param id of EndToEnd Entity
	 * 
	 * */
	@Cacheable(value = "endToEndIdCacheById")
	public Optional<EndToEnd> findEndToEndById(int id) {
		
		try {
			log.info("Invoking findEndToEndById with Id : "+id);
			Optional<EndToEnd> endToEnd = endToEndRepo.findById(id);
			return endToEnd;	
		}catch(Exception e) {
			log.error("Erroe while fetching record from DB findEndToEndById :"+e.getMessage());
			return null;
		}
		
	}

	
	/*
	 * Fetch all Entity from DB and Writes data in cache. The return value of this method will be cached
	 * 
	 * */
	@Cacheable(value = "endToEndIdCacheAll")
	public List<EndToEnd> findAllEndToEnd() {
		
		try {
			log.info("Invoking findAllEndToEnd");
			return endToEndRepo.findAll();	
		}catch(Exception e) {
			log.error("Erroe while fetching all from DB findAllEndToEnd :"+e.getMessage());
			return null;
		}
	}
	
	/*
	 * validates the xml against xsd.
	 * @param input xml file
	 * @param input xsd file to validate xml
	 * 
	 * */
	
	public boolean validateXml(String xmlFileName, String  xsdFileName) {
		boolean isValid;
		try {
			isValid = xmlValidator.validate(xmlFileName, xsdFileName);
		 } catch (SAXException | IOException e) {
			 isValid=false;
			 logInvalidXml(e.getMessage());
		 }
		return isValid;
	}
	
	/*
	 * if Xml is not valid logs error message in DocumentErrorLog table.
	 * @param String error message from validateXml 
	 * 
	 * */
	public void logInvalidXml(String errorMessage) {
		log.error("Invalid Xml :");
		DocumentErrorLog documentErrorLog=new DocumentErrorLog();
		documentErrorLog.setErrorMessage(errorMessage);
		documentErrorRepo.save(documentErrorLog);
	}
}
