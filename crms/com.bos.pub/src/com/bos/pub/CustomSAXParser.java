package com.bos.pub;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentType;
import org.dom4j.Element;
import org.dom4j.io.SAXContentHandler;

import com.sun.org.apache.xerces.internal.parsers.SAXParser;
import com.sun.org.apache.xerces.internal.xni.XNIException;
import com.sun.org.apache.xerces.internal.xni.parser.XMLInputSource;

public class CustomSAXParser extends SAXParser {
	public void parse(XMLInputSource inputSource) throws XNIException,
			IOException {
		super.parse(inputSource);
		SAXContentHandler cont = (SAXContentHandler) this.getContentHandler();
		Document doc = cont.getDocument();
		DocumentType doctype = doc.getDocType();
		if (null == doctype
				|| !"hibernate-mapping".equals(doctype.getElementName()))
			return;
		Element root = doc.getRootElement();
		Iterator it = root.elementIterator();
		while (it.hasNext()) {
			Element ele = (Element) it.next();
			if (null == ele || "class".equals(ele.getName()) == false)
				continue;
			Iterator eitor = ele.elementIterator();
			Element versionElement = null;
			Element idElement = null;
			while (eitor.hasNext()) {
				Element prop = (Element) eitor.next();
				if (null == prop)
					continue;
				String elename = prop.getName();
				if ("tuplizer".equals(elename) || "id".equals(elename)
						|| "composite-id".equals(elename)
						|| "discriminator".equals(elename)
						|| "natural-id".equals(elename)) {
					idElement = prop;
				}

				String name = prop.attributeValue("name");
				if ("version".equals(name)) {
					// 处理乐观锁
					versionElement = prop;

					break;
				}
			}
			if (versionElement != null && idElement != null) {
				eitor = ele.elementIterator();
				List<Element> list = new ArrayList<Element>();
				while (eitor.hasNext()) {
					Element prop = (Element) eitor.next();
					if (null == prop || versionElement == prop)
						continue;
					if (idElement == prop) {
						list.add(idElement);
						list.add(versionElement);
					} else {
						list.add(prop);
					}
				}
				ele.clearContent();
				ele.addAttribute("optimistic-lock", "version");
				versionElement.setName("version");
				for (int i = 0; i < list.size(); i++) {
					ele.add(list.get(i));
				}
				// System.out.println(ele.asXML());
			}// if
		}
	}
}
