package com.cm.psf.pubspofac.dao;

import java.util.List;

import com.cm.psf.pubspofac.PubSpoFac;
import com.cm.psf.pubspofac.PubSpoFacSelect;

/* 서비스 기능 인터페이스 정리 */
public interface IPubSpoFacDao {
	List<PubSpoFac> pubSpoFacSelect(PubSpoFacSelect pubSpoFacS);	//검색
}
