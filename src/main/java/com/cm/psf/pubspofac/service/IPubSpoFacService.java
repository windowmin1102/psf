package com.cm.psf.pubspofac.service;

import java.util.List;

import com.cm.psf.pubspofac.PubSpoFac;
import com.cm.psf.pubspofac.PubSpoFacSelect;

public interface IPubSpoFacService {
	List<PubSpoFac> pubSpoFacSearch(PubSpoFacSelect pubSpoFacS);
}
