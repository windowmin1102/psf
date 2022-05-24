package com.cm.psf.pubspofac.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cm.psf.pubspofac.PubSpoFac;
import com.cm.psf.pubspofac.PubSpoFacSelect;
import com.cm.psf.pubspofac.dao.PubSpoFacDao;

@Service	//���� ���̾�, ���ο��� �ڹ� ������ ó��
public class PubSpoFacService implements IPubSpoFacService{

	@Autowired
	PubSpoFacDao pubSpoFacDao;
	
	@Override
	public List<PubSpoFac> pubSpoFacSearch(PubSpoFacSelect pubSpoFacS) {
		
		List<PubSpoFac> psfs = pubSpoFacDao.pubSpoFacSelect(pubSpoFacS);
		
		if(psfs != null) {
			System.out.println("getPsf Success!");

		} else {
			System.out.println("getPsf Fail!");
		}
		
		return psfs;
	}
}
