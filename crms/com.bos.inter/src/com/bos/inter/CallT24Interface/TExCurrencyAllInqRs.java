package com.bos.inter.CallT24Interface;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import com.bos.jaxb.javabean.SuperBosfxRs;

public class TExCurrencyAllInqRs extends SuperBosfxRs {
	@XmlElement(name = "TExCurrencyAllInqRec")
	public List<TExCurrencyAllInqRec> TExCurrencyAllInqRec;

	public String toString() {
		return "TExCurrencyAllInqRs [TExCurrencyAllInqRec="
				+ TExCurrencyAllInqRec + " ]";
	}

	public void setTExCurrencyAllInqRec(
			List<TExCurrencyAllInqRec> exCurrencyAllInqRec) {
		TExCurrencyAllInqRec = exCurrencyAllInqRec;
	}

}
