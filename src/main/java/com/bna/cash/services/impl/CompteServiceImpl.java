package com.bna.cash.services.impl;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bna.cash.commons.RibGenerator;
import com.bna.cash.entities.Compte;
import com.bna.cash.entities.ImageModel;
import com.bna.cash.entities.User;
import com.bna.cash.enums.Status;
import com.bna.cash.enums.TypeCompte;
import com.bna.cash.enums.TypeUsers;
import com.bna.cash.repositories.CompteRepository;
import com.bna.cash.repositories.ImageRepository;
import com.bna.cash.rest.dto.CompteDto;
import com.bna.cash.rest.dto.DocumentDto;
import com.bna.cash.rest.dto.UserDto;
import com.bna.cash.services.CompteService;
import com.bna.cash.services.UserService;



@Service
public class CompteServiceImpl implements CompteService {

	@Autowired
	private CompteService compteService;
	
	@Autowired
	private CompteRepository compteRepository ;
	
	@Autowired
	private ImageRepository imgImageRepository ;

	@Autowired
	private SecurityService securityService; 
	
	@Autowired
	private UserService userService ; 
	
	@Autowired
	private ImageRepository imageRepository;
	
	@Override
	public void addAccount(CompteDto compteDto) {
		Compte cpt = new Compte();
		//String Uploadimg = imgImageRepository.get ; 
//User user = securityService.getTheCurrentUser();
		BeanUtils.copyProperties(compteDto , cpt);
		cpt.setStatus(com.bna.cash.enums.Status.WAITING);
		Set<ImageModel> documents = new HashSet<>();
		if(compteDto.getDocuments() != null && !compteDto.getDocuments().isEmpty()) {
			for(DocumentDto doc : compteDto.getDocuments()) {
				ImageModel img = new ImageModel();
				img.setId(doc.getId());
				img.setName(doc.getName());
				img.setType(doc.getType());
				String pathToSave = "C:" + File.separator + "Partition D" + File.separator + "Documents";
				File dossier = new File(pathToSave);
				if(!dossier.exists()) {
					dossier.mkdir();
				}
				String docPath = pathToSave + File.separator + doc.getName();
				try {
					FileUtils.writeByteArrayToFile(new File(docPath), doc.getContent());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				img.setPath(docPath);
				img.setAccount(cpt);
				documents.add(img);
			}
		}
		cpt.setFiles(documents);
		compteRepository.save(cpt) ; 
	}
	
	
	
	@Override
	public List<CompteDto> getAllAccounts() {
		List<CompteDto> compteDtos = new ArrayList<>();
		List<Compte> comptes = compteRepository.findAll();
		for(Compte compte : comptes) {
			CompteDto  compteDto = new CompteDto();
			BeanUtils.copyProperties(compte, compteDto);
			//compteDto.add(compteDto);
		}
		return compteDtos;
	}



	@Override
	public CompteDto getCompteById(Long id) {
		List<DocumentDto> documents = null;
		Compte compte = compteRepository.findById(id).get();
		CompteDto compteDto = new CompteDto();
		BeanUtils.copyProperties(compte, compteDto);
		try {
			if(compte.getFiles() != null) {
				documents = new ArrayList<>();
				for(ImageModel file : compte.getFiles()) {
					DocumentDto doc = new DocumentDto();
					BeanUtils.copyProperties(file, doc);
					File docFile = new File(file.getPath());
					byte[] fileContent = Files.readAllBytes(docFile.toPath());
					doc.setContent(fileContent);
					documents.add(doc);
				}
				compteDto.setDocuments(documents);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return compteDto;
	}



	@Override
	public void approuverCompte(Long compteId) {
		Compte compte = compteRepository.findById(compteId).get();
		compte.setStatus(Status.CONFIRMED);
		compte.setRIB(RibGenerator.generateRib());
		compteRepository.save(compte);
	}



	@Override
	public void refuserComtpe(Long compteId) {
		Compte compte = compteRepository.findById(compteId).get();
		compte.setStatus(Status.CANCELED);
		compteRepository.save(compte);
		
	}
	

}
