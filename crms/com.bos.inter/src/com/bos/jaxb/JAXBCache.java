package com.bos.jaxb;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

public final class JAXBCache {

	private static final JAXBCache instance = new JAXBCache();

	private final ConcurrentMap<String, JAXBContext> contextCache = new ConcurrentHashMap<String, JAXBContext>();

	private JAXBCache() {
	}

	public static JAXBCache instance() {
		return instance;
	}

	JAXBContext getJAXBContext(Class<?> clazz) throws JAXBException {
		JAXBContext context = contextCache.get(clazz.getName());
		if (context == null) {
			context = JAXBContext.newInstance(clazz);
			contextCache.putIfAbsent(clazz.getName(), context);
		}
		return context;
	}

}
