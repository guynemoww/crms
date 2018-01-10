package com.bos.pub.socket.service.request.base;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

import com.bos.pub.socket.service.mortgage.request.EsbBodyMtmqRq02002000004A02;
import com.bos.pub.socket.service.request.EsbBodyEcifRq12002000013A01;
import com.bos.pub.socket.service.request.EsbBodyEcifRq12002000013A02;
import com.bos.pub.socket.service.request.EsbBodyEcifRq12002000013A03;
import com.bos.pub.socket.service.request.EsbBodyEcifRq12002000013A04;
import com.bos.pub.socket.service.request.EsbBodyEcifRq12002000013A05;
import com.bos.pub.socket.service.request.EsbBodyEcifRq12002000013A06;
import com.bos.pub.socket.service.request.EsbBodyGjRq02001000001A01;
import com.bos.pub.socket.service.request.EsbBodyGjRq02001000003A01;
import com.bos.pub.socket.service.request.EsbBodyGjRq02002000001A01;
import com.bos.pub.socket.service.request.EsbBodyGjRq05001000001A01;
import com.bos.pub.socket.service.request.EsbBodyGjRq05001000001A02;
import com.bos.pub.socket.service.request.EsbBodyGjRq05001000002A03;
import com.bos.pub.socket.service.request.EsbBodyGjRq05001000002A04;
import com.bos.pub.socket.service.request.EsbBodyGjRq07003000001A01;
import com.bos.pub.socket.service.request.EsbBodyHxRq01001000002A02;
import com.bos.pub.socket.service.request.EsbBodyHxRq01001000002A03;
import com.bos.pub.socket.service.request.EsbBodyHxRq01001000002A05;
import com.bos.pub.socket.service.request.EsbBodyHxRq02002000002A01;
import com.bos.pub.socket.service.request.EsbBodyHxRq02002000002A02;
import com.bos.pub.socket.service.request.EsbBodyHxRq02002000002A03;
import com.bos.pub.socket.service.request.EsbBodyHxRq12003000004A01;
import com.bos.pub.socket.service.request.EsbBodyHxRq12003000004A10;
import com.bos.pub.socket.service.request.EsbBodyHxRq12005000001A01;
import com.bos.pub.socket.service.request.EsbBodyHxRq12005000002A01;
import com.bos.pub.socket.service.request.EsbBodyHxRq12005000002A02;
import com.bos.pub.socket.service.request.EsbBodyHxRq12005000002A03;
import com.bos.pub.socket.service.request.EsbBodyHxRq12005000002A04;
import com.bos.pub.socket.service.request.EsbBodyHxRq12005000003A01;
import com.bos.pub.socket.service.request.EsbBodyMtmqRq02002000003A01;
import com.bos.pub.socket.service.request.EsbBodyMtmqRq02002000003A02;
import com.bos.pub.socket.service.request.EsbBodyMtmqRq02002000003A03;
import com.bos.pub.socket.service.request.EsbBodyMtmqRq02002000004A01;
import com.bos.pub.socket.service.request.EsbBodyMtmqRq02003000004A01;
import com.bos.pub.socket.service.request.EsbBodyMtmqRq02003000004A02;
import com.bos.pub.socket.service.request.EsbBodyMtmqRq02003000004A03;
import com.bos.pub.socket.service.request.EsbBodyMtmqRq02003000004A04;
import com.bos.pub.socket.service.request.EsbBodyMtmqRq02003000004A06;
import com.bos.pub.socket.service.request.EsbBodyMtmqRq02003000004A07;
import com.bos.pub.socket.service.request.EsbBodyMtmqRq02003000004A05;
import com.bos.pub.socket.service.request.EsbBodyMtmqRq12002000013A09;
import com.bos.pub.socket.service.request.EsbBodyMtmqRq12002000013A10;
import com.bos.pub.socket.service.request.EsbBodyMtmqRq12002000013A13;
import com.bos.pub.socket.service.request.EsbBodyRzRq12002000012A01;
import com.bos.pub.socket.service.request.EsbBodyRzRq12003000005A02;
import com.bos.pub.socket.service.request.EsbBodyWmaRq03002000011A01;
import com.bos.pub.socket.service.request.EsbBodyWmaRq03002000011A02;
import com.bos.pub.socket.service.request.EsbBodyXdRq02001000002A01;
import com.bos.pub.socket.service.request.EsbBodyXdRq02001000003A02;
import com.bos.pub.socket.service.request.EsbBodyXdRq05002000001A01;
import com.bos.pub.socket.service.request.EsbBodyMtmqRq02003000003A01;
import com.bos.pub.socket.util.EsbSocketConstant;

@XmlRootElement(name = "service")
public class EsbServiceRq implements Serializable {
	private static final long serialVersionUID = 1L;

	private EsbSysHeadRq esbSysHead;
	private EsbAppHeadRq esbAppHead;
	private Object esbBody;
	
	public EsbServiceRq(){
	}

	public EsbSysHeadRq getEsbSysHead() {
		return esbSysHead;
	}

	@XmlElement(name = "SYS_HEAD")
	public void setEsbSysHead(EsbSysHeadRq esbSysHead) {
		this.esbSysHead = esbSysHead;
	}

	public EsbAppHeadRq getEsbAppHead() {
		return esbAppHead;
	}

	@XmlElement(name = "APP_HEAD")
	public void setEsbAppHead(EsbAppHeadRq esbAppHead) {
		this.esbAppHead = esbAppHead;
	}

	public Object getEsbBody() {
		return esbBody;
	}
	
	@XmlElements(value = {
			@XmlElement(name = EsbSocketConstant.EcifRq12002000013BODY01, type = EsbBodyEcifRq12002000013A01.class),
			@XmlElement(name = EsbSocketConstant.EcifRq12002000013BODY02, type = EsbBodyEcifRq12002000013A02.class),
			@XmlElement(name = EsbSocketConstant.EcifRq12002000013BODY03, type = EsbBodyEcifRq12002000013A03.class),
			@XmlElement(name = EsbSocketConstant.EcifRq12002000013BODY04, type = EsbBodyEcifRq12002000013A04.class),
			@XmlElement(name = EsbSocketConstant.EcifRq12002000013BODY05, type = EsbBodyEcifRq12002000013A05.class),
			@XmlElement(name = EsbSocketConstant.EcifRq12002000013BODY06, type = EsbBodyEcifRq12002000013A06.class),
			//-----------------------------------------------------------------------------------------------
			@XmlElement(name = EsbSocketConstant.GjRq02001000001BODY01, type = EsbBodyGjRq02001000001A01.class),
			@XmlElement(name = EsbSocketConstant.GjRq02001000003BODY01, type = EsbBodyGjRq02001000003A01.class),
			@XmlElement(name = EsbSocketConstant.GjRq02002000001BODY01, type = EsbBodyGjRq02002000001A01.class),
			@XmlElement(name = EsbSocketConstant.GjRq05001000001BODY01, type = EsbBodyGjRq05001000001A01.class),
			@XmlElement(name = EsbSocketConstant.GjRq05001000001BODY02, type = EsbBodyGjRq05001000001A02.class),
			@XmlElement(name = EsbSocketConstant.GjRq05001000002BODY03, type = EsbBodyGjRq05001000002A03.class),
			@XmlElement(name = EsbSocketConstant.GjRq05001000002BODY04, type = EsbBodyGjRq05001000002A04.class),
			@XmlElement(name = EsbSocketConstant.GjRq07003000001BODY01, type = EsbBodyGjRq07003000001A01.class),
			//-----------------------------------------------------------------------------------------------
			@XmlElement(name = EsbSocketConstant.HxRq01001000002BODY02, type = EsbBodyHxRq01001000002A02.class),
			@XmlElement(name = EsbSocketConstant.HxRq01001000002BODY03, type = EsbBodyHxRq01001000002A03.class),
			@XmlElement(name = EsbSocketConstant.HxRq12003000004BODY01, type = EsbBodyHxRq12003000004A01.class),
			@XmlElement(name = EsbSocketConstant.HxRq12005000001BODY01, type = EsbBodyHxRq12005000001A01.class),
			@XmlElement(name = EsbSocketConstant.HxRq12005000002BODY01, type = EsbBodyHxRq12005000002A01.class),
			@XmlElement(name = EsbSocketConstant.HxRq12005000002BODY02, type = EsbBodyHxRq12005000002A02.class),
			@XmlElement(name = EsbSocketConstant.HxRq12005000002BODY03, type = EsbBodyHxRq12005000002A03.class),
			@XmlElement(name = EsbSocketConstant.HxRq12005000003BODY01, type = EsbBodyHxRq12005000003A01.class),
			@XmlElement(name = EsbSocketConstant.HxRq12005000002BODY04, type = EsbBodyHxRq12005000002A04.class),
			@XmlElement(name = EsbSocketConstant.HxRq02002000002BODY01, type = EsbBodyHxRq02002000002A01.class),
			@XmlElement(name = EsbSocketConstant.HxRq02002000002BODY02, type = EsbBodyHxRq02002000002A02.class),
			@XmlElement(name = EsbSocketConstant.HxRq02002000002BODY03, type = EsbBodyHxRq02002000002A03.class),
			//-----------------------------------------------------------------------------------------------
			@XmlElement(name = EsbSocketConstant.RzRq12002000012BODY01, type = EsbBodyRzRq12002000012A01.class),
			@XmlElement(name = EsbSocketConstant.RzRq12003000005BODY02, type = EsbBodyRzRq12003000005A02.class),
			//-----------------------------------------------------------------------------------------------
			@XmlElement(name = EsbSocketConstant.XdRq02001000002BODY01, type = EsbBodyXdRq02001000002A01.class),
			@XmlElement(name = EsbSocketConstant.XdRq02001000003BODY02, type = EsbBodyXdRq02001000003A02.class),
			@XmlElement(name = EsbSocketConstant.XdRq05002000001BODY01, type = EsbBodyXdRq05002000001A01.class),
			//-----------------------------------------------------------------------------------------------
			@XmlElement(name = EsbSocketConstant.WmaRq03002000011BODY01, type = EsbBodyWmaRq03002000011A01.class),
			@XmlElement(name = EsbSocketConstant.WmaRq03002000011BODY02, type = EsbBodyWmaRq03002000011A02.class),
			//-------------------------------核心账户查询新交易7190------------------------------------------------------------
			@XmlElement(name = EsbSocketConstant.HxRq12003000004BODY10, type = EsbBodyHxRq12003000004A10.class),
			//-------------------------------核心账户查询新交易7130------------------------------------------------------------
			@XmlElement(name = EsbSocketConstant.HxRq01001000002BODY05, type = EsbBodyHxRq01001000002A05.class),
			
			//-------------------------------面谈面签------------------------------------------------------------
			@XmlElement(name = EsbSocketConstant.MtmqRq02003000003BODY01, type = EsbBodyMtmqRq02003000003A01.class),
			@XmlElement(name = EsbSocketConstant.MtmqRq12002000013BODY09, type = EsbBodyMtmqRq12002000013A09.class),
			@XmlElement(name = EsbSocketConstant.MtmqRq12002000013BODY10, type = EsbBodyMtmqRq12002000013A10.class),
			@XmlElement(name = EsbSocketConstant.MtmqRq12002000013BODY13, type = EsbBodyMtmqRq12002000013A13.class),
			@XmlElement(name = EsbSocketConstant.MtmqRq02003000004BODY01, type = EsbBodyMtmqRq02003000004A01.class),
			@XmlElement(name = EsbSocketConstant.MtmqRq02003000004BODY02, type = EsbBodyMtmqRq02003000004A02.class),
			@XmlElement(name = EsbSocketConstant.MtmqRq02003000004BODY03, type = EsbBodyMtmqRq02003000004A03.class),
			@XmlElement(name = EsbSocketConstant.MtmqRq02002000004BODY01, type = EsbBodyMtmqRq02002000004A01.class),
			@XmlElement(name = EsbSocketConstant.MtmqRq02002000004BODY02, type = EsbBodyMtmqRq02002000004A02.class),
			@XmlElement(name = EsbSocketConstant.MtmqRq02002000003BODY01, type = EsbBodyMtmqRq02002000003A01.class),
			@XmlElement(name = EsbSocketConstant.MtmqRq02002000003BODY02, type = EsbBodyMtmqRq02002000003A02.class),
			@XmlElement(name = EsbSocketConstant.MtmqRq02002000003BODY03, type = EsbBodyMtmqRq02002000003A03.class),
			@XmlElement(name = EsbSocketConstant.MtmqRq02003000004BODY06, type = EsbBodyMtmqRq02003000004A06.class),
			@XmlElement(name = EsbSocketConstant.MtmqRq02003000004BODY07, type = EsbBodyMtmqRq02003000004A07.class),
			@XmlElement(name = EsbSocketConstant.MtmqRq02003000004BODY05, type = EsbBodyMtmqRq02003000004A05.class),
			@XmlElement(name = EsbSocketConstant.MtmqRq02003000004BODY04, type = EsbBodyMtmqRq02003000004A04.class),

		})
	public void setEsbBody(Object esbBody) {
		this.esbBody = esbBody;
	}
	
	@Override
	public String toString() {
		return "EsbServiceRq [esbSysHead=" + esbSysHead + ",esbAppHead=" + esbAppHead + "]";
	}
}
