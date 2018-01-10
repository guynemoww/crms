package com.bos.pub.socket.service.response.base;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

import com.bos.pub.socket.service.mortgage.response.EsbBodyMtmqRs02002000004A02;
import com.bos.pub.socket.service.response.EsbBodyEcifRs12002000013A01;
import com.bos.pub.socket.service.response.EsbBodyEcifRs12002000013A02;
import com.bos.pub.socket.service.response.EsbBodyEcifRs12002000013A04;
import com.bos.pub.socket.service.response.EsbBodyGjRs07003000001A01;
import com.bos.pub.socket.service.response.EsbBodyHxRs01001000002A02;
import com.bos.pub.socket.service.response.EsbBodyHxRs01001000002A03;
import com.bos.pub.socket.service.response.EsbBodyHxRs01001000002A05;
import com.bos.pub.socket.service.response.EsbBodyHxRs12003000004A01;
import com.bos.pub.socket.service.response.EsbBodyHxRs12003000004A10;
import com.bos.pub.socket.service.response.EsbBodyHxRs12005000001A01;
import com.bos.pub.socket.service.response.EsbBodyHxRs12005000002A04;
import com.bos.pub.socket.service.response.EsbBodyHxRs12005000003A01;
import com.bos.pub.socket.service.response.EsbBodyMtmqRs02002000003A01;
import com.bos.pub.socket.service.response.EsbBodyMtmqRs02002000003A02;
import com.bos.pub.socket.service.response.EsbBodyMtmqRs02002000003A03;
import com.bos.pub.socket.service.response.EsbBodyMtmqRs02002000004A01;
import com.bos.pub.socket.service.response.EsbBodyMtmqRs02003000003A01;
import com.bos.pub.socket.service.response.EsbBodyMtmqRs02003000004A01;
import com.bos.pub.socket.service.response.EsbBodyMtmqRs02003000004A02;
import com.bos.pub.socket.service.response.EsbBodyMtmqRs02003000004A03;
import com.bos.pub.socket.service.response.EsbBodyMtmqRs02003000004A04;
import com.bos.pub.socket.service.response.EsbBodyMtmqRs02003000004A06;
import com.bos.pub.socket.service.response.EsbBodyMtmqRs02003000004A07;
import com.bos.pub.socket.service.response.EsbBodyMtmqRs02003000004A05;
import com.bos.pub.socket.service.response.EsbBodyMtmqRs12002000013A09;
import com.bos.pub.socket.service.response.EsbBodyMtmqRs12002000013A10;
import com.bos.pub.socket.service.response.EsbBodyMtmqRs12002000013A13;
import com.bos.pub.socket.service.response.EsbBodyRs;
import com.bos.pub.socket.service.response.EsbBodyRzRs12002000012A01;
import com.bos.pub.socket.service.response.EsbBodyRzRs12003000005A02;
import com.bos.pub.socket.util.EsbSocketConstant;

@XmlRootElement(name = "service")
public class EsbServiceRs implements Serializable {
	private static final long serialVersionUID = 1L;

	private EsbSysHeadRs esbSysHead;
	private EsbAppHeadRs esbAppHead;
	private Object esbBody;

	public EsbServiceRs(){
	}

	public EsbSysHeadRs getEsbSysHead() {
		return esbSysHead;
	}

	@XmlElement(name = "SYS_HEAD")
	public void setEsbSysHead(EsbSysHeadRs esbSysHead) {
		this.esbSysHead = esbSysHead;
	}

	public EsbAppHeadRs getEsbAppHead() {
		return esbAppHead;
	}

	@XmlElement(name = "APP_HEAD")
	public void setEsbAppHead(EsbAppHeadRs esbAppHead) {
		this.esbAppHead = esbAppHead;
	}


	public Object getEsbBody() {
		return esbBody;
	}
	
	@XmlElements(value = {
			@XmlElement(name = EsbSocketConstant.EcifRq12002000013BODY01, type = EsbBodyEcifRs12002000013A01.class),
			@XmlElement(name = EsbSocketConstant.EcifRq12002000013BODY02, type = EsbBodyEcifRs12002000013A02.class),
			@XmlElement(name = EsbSocketConstant.EcifRq12002000013BODY04, type = EsbBodyEcifRs12002000013A04.class),
			//-----------------------------------------------------------------------------------------------
			@XmlElement(name = EsbSocketConstant.ESBBODYRS, type = EsbBodyRs.class),
			@XmlElement(name = EsbSocketConstant.GjRq07003000001BODY01, type = EsbBodyGjRs07003000001A01.class),
			//-----------------------------------------------------------------------------------------------
			@XmlElement(name = EsbSocketConstant.HxRq01001000002BODY02, type = EsbBodyHxRs01001000002A02.class),
			@XmlElement(name = EsbSocketConstant.HxRq01001000002BODY03, type = EsbBodyHxRs01001000002A03.class),
			@XmlElement(name = EsbSocketConstant.HxRq01001000002BODY05, type = EsbBodyHxRs01001000002A05.class),
			@XmlElement(name = EsbSocketConstant.HxRq12003000004BODY01, type = EsbBodyHxRs12003000004A01.class),
			@XmlElement(name = EsbSocketConstant.HxRq12005000001BODY01, type = EsbBodyHxRs12005000001A01.class),
			@XmlElement(name = EsbSocketConstant.HxRq12005000003BODY01, type = EsbBodyHxRs12005000003A01.class),
			@XmlElement(name = EsbSocketConstant.HxRq12005000002BODY04, type = EsbBodyHxRs12005000002A04.class),
			//-----------------------------------------------------------------------------------------------
			@XmlElement(name = EsbSocketConstant.RzRq12002000012BODY01, type = EsbBodyRzRs12002000012A01.class),
			@XmlElement(name = EsbSocketConstant.RzRq12003000005BODY02, type = EsbBodyRzRs12003000005A02.class),
			//---------------------------------核心账户查询新交易----------------------------------------------------
			@XmlElement(name = EsbSocketConstant.HxRq12003000004BODY10, type = EsbBodyHxRs12003000004A10.class),
			//-------------------------------面谈面签------------------------------------------------------------
			@XmlElement(name = EsbSocketConstant.MtmqRq02003000003BODY01, type = EsbBodyMtmqRs02003000003A01.class),
			@XmlElement(name = EsbSocketConstant.MtmqRq12002000013BODY09, type = EsbBodyMtmqRs12002000013A09.class),
			@XmlElement(name = EsbSocketConstant.MtmqRq12002000013BODY10, type = EsbBodyMtmqRs12002000013A10.class),
			@XmlElement(name = EsbSocketConstant.MtmqRq12002000013BODY13, type = EsbBodyMtmqRs12002000013A13.class),
			@XmlElement(name = EsbSocketConstant.MtmqRq02003000004BODY01, type = EsbBodyMtmqRs02003000004A01.class),
			@XmlElement(name = EsbSocketConstant.MtmqRq02003000004BODY02, type = EsbBodyMtmqRs02003000004A02.class),
			@XmlElement(name = EsbSocketConstant.MtmqRq02003000004BODY03, type = EsbBodyMtmqRs02003000004A03.class),
			@XmlElement(name = EsbSocketConstant.MtmqRq02002000004BODY01, type = EsbBodyMtmqRs02002000004A01.class),
			@XmlElement(name = EsbSocketConstant.MtmqRq02002000004BODY02, type = EsbBodyMtmqRs02002000004A02.class),
			@XmlElement(name = EsbSocketConstant.MtmqRq02002000003BODY01, type = EsbBodyMtmqRs02002000003A01.class),
			@XmlElement(name = EsbSocketConstant.MtmqRq02002000003BODY02, type = EsbBodyMtmqRs02002000003A02.class),
			@XmlElement(name = EsbSocketConstant.MtmqRq02002000003BODY03, type = EsbBodyMtmqRs02002000003A03.class),
			@XmlElement(name = EsbSocketConstant.MtmqRq02003000004BODY06, type = EsbBodyMtmqRs02003000004A06.class),
			@XmlElement(name = EsbSocketConstant.MtmqRq02003000004BODY07, type = EsbBodyMtmqRs02003000004A07.class),
			@XmlElement(name = EsbSocketConstant.MtmqRq02003000004BODY05, type = EsbBodyMtmqRs02003000004A05.class),
			@XmlElement(name = EsbSocketConstant.MtmqRq02003000004BODY04, type = EsbBodyMtmqRs02003000004A04.class),

		})
	public void setEsbBody(Object esbBody) {
		this.esbBody = esbBody;
	}
}
