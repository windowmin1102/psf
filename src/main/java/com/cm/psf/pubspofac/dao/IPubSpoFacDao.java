package com.cm.psf.pubspofac.dao;

import java.util.List;

import com.cm.psf.pubspofac.PubSpoFac;
import com.cm.psf.pubspofac.PubSpoFacSelect;

/* ���� ��� �������̽� ���� */
public interface IPubSpoFacDao {
	List<PubSpoFac> pubSpoFacSelect(PubSpoFacSelect pubSpoFacS);	//�˻�
}
