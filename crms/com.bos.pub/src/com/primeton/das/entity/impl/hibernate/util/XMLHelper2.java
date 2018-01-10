package com.primeton.das.entity.impl.hibernate.util;

import java.util.List;

import org.dom4j.DocumentFactory;
import org.dom4j.Element;
import org.dom4j.io.DOMReader;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import com.primeton.das.entity.impl.hibernate.Logger;
import com.primeton.das.entity.impl.hibernate.LoggerFactory;

public final class XMLHelper2 {
	private static final Logger log = LoggerFactory
			.getTraceLogger(XMLHelper.class);

	public static final EntityResolver DEFAULT_DTD_RESOLVER = new DTDEntityResolver();

	private DOMReader domReader;

	private SAXReader saxReader;

	public SAXReader createSAXReader(String file, List errorsList,
			EntityResolver entityResolver) {
		if (this.saxReader == null)
			this.saxReader = new SAXReader();
		this.saxReader.setEntityResolver(entityResolver);
		this.saxReader.setErrorHandler(new ErrorLogger(file, errorsList));
		this.saxReader.setMergeAdjacentText(true);
		this.saxReader.setValidation(true);
		try {
			log.info("创建自定义SAX加载器");
			this.saxReader.setXMLReaderClassName("com.bos.pub.CustomSAXParser");
		} catch (SAXException e) {
			log.error("创建XMLReader失败！", e);
		}
		return this.saxReader;
	}

	public DOMReader createDOMReader() {
		if (this.domReader == null)
			this.domReader = new DOMReader();
		return this.domReader;
	}

	public static Element generateDom4jElement(String elementName) {
		return DocumentFactory.getInstance().createElement(elementName);
	}

	public static void dump(Element element) {
		OutputFormat outformat;
		try {
			outformat = OutputFormat.createPrettyPrint();
			XMLWriter writer = new XMLWriter(System.out, outformat);
			writer.write(element);
			writer.flush();
			System.out.println("");
		} catch (Throwable t) {
			System.out.println(element.asXML());
		}
	}

	public static class ErrorLogger implements ErrorHandler {
		private String file;

		private List errors;

		ErrorLogger(String file, List errors) {
			this.file = file;
			this.errors = errors;
		}

		public void error(SAXParseException error) {
			log.error("Error parsing XML: " + this.file + '('
					+ error.getLineNumber() + ") " + error.getMessage());
			this.errors.add(error);
		}

		public void fatalError(SAXParseException error) {
			error(error);
		}

		public void warning(SAXParseException warn) {
			log.warn("Warning parsing XML: " + this.file + '('
					+ warn.getLineNumber() + ") " + warn.getMessage());
		}
	}
}