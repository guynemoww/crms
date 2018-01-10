/**
 * S01001010021003ServiceStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.3  Built on : Aug 10, 2007 (04:45:47 LKT)
 */
package com.primeton.ecds;

/*
 *  S01001010021003ServiceStub java implementation
 */

public class S01001010021003ServiceStub extends org.apache.axis2.client.Stub {
	protected org.apache.axis2.description.AxisOperation[] _operations;

	// hashmaps to keep the fault mapping
	private java.util.HashMap faultExceptionNameMap = new java.util.HashMap();
	private java.util.HashMap faultExceptionClassNameMap = new java.util.HashMap();
	private java.util.HashMap faultMessageMap = new java.util.HashMap();

	private void populateAxisService() throws org.apache.axis2.AxisFault {

		// creating the Service with a unique name
		_service = new org.apache.axis2.description.AxisService("S01001010021003Service" + this.hashCode());

		// creating the operations
		org.apache.axis2.description.AxisOperation __operation;

		_operations = new org.apache.axis2.description.AxisOperation[1];

		__operation = new org.apache.axis2.description.OutInAxisOperation();

		__operation.setName(new javax.xml.namespace.QName("http://www.adtec.com.cn", "S01001010021003"));
		_service.addOperation(__operation);

		_operations[0] = __operation;

	}

	// populates the faults
	private void populateFaults() {

	}

	/**
	 * Constructor that takes in a configContext
	 */

	public S01001010021003ServiceStub(org.apache.axis2.context.ConfigurationContext configurationContext, java.lang.String targetEndpoint) throws org.apache.axis2.AxisFault {
		this(configurationContext, targetEndpoint, false);
	}

	/**
	 * Constructor that takes in a configContext and useseperate listner
	 */
	public S01001010021003ServiceStub(org.apache.axis2.context.ConfigurationContext configurationContext, java.lang.String targetEndpoint, boolean useSeparateListener)
			throws org.apache.axis2.AxisFault {
		// To populate AxisService
		populateAxisService();
		populateFaults();

		_serviceClient = new org.apache.axis2.client.ServiceClient(configurationContext, _service);

		configurationContext = _serviceClient.getServiceContext().getConfigurationContext();

		_serviceClient.getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(targetEndpoint));
		_serviceClient.getOptions().setUseSeparateListener(useSeparateListener);

	}

	/**
	 * Default Constructor
	 */
	public S01001010021003ServiceStub(org.apache.axis2.context.ConfigurationContext configurationContext) throws org.apache.axis2.AxisFault {

		this(configurationContext, "http://172.16.221.83:12103/WebService/CRMS_SVR/S01001010021003");

	}

	/**
	 * Default Constructor
	 */
	public S01001010021003ServiceStub() throws org.apache.axis2.AxisFault {

		this("http://172.16.221.83:12103/WebService/CRMS_SVR/S01001010021003");

	}

	/**
	 * Constructor taking the target endpoint
	 */
	public S01001010021003ServiceStub(java.lang.String targetEndpoint) throws org.apache.axis2.AxisFault {
		this(null, targetEndpoint);
	}

	/**
	 * Auto generated method signature
	 * @see com.primeton.ecds.S01001010021003Service#S01001010021003
	 * @param s010010100210030
	 */

	public com.primeton.ecds.S01001010021003ServiceStub.S01001010021003Response S01001010021003(

	com.primeton.ecds.S01001010021003ServiceStub.S01001010021003 s010010100210030)

	throws java.rmi.RemoteException

	{

		try {
			org.apache.axis2.client.OperationClient _operationClient = _serviceClient.createClient(_operations[0].getName());
			_operationClient.getOptions().setAction("http://172.16.221.83:12103/WebService/CRMS_SVR/S01001010021003");
			_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

			addPropertyToOperationClient(_operationClient, org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR, "&");

			// create a message context
			org.apache.axis2.context.MessageContext _messageContext = new org.apache.axis2.context.MessageContext();

			// create SOAP envelope with that payload
			org.apache.axiom.soap.SOAPEnvelope env = null;

			env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()), s010010100210030, optimizeContent(new javax.xml.namespace.QName("http://www.adtec.com.cn",
					"S01001010021003")));

			// adding SOAP soap_headers
			_serviceClient.addHeadersToEnvelope(env);
			// set the message context with that soap envelope
			_messageContext.setEnvelope(env);
			System.out.println("发送报文:" + env);

			// add the message contxt to the operation client
			_operationClient.addMessageContext(_messageContext);
			System.out.println(env);
			// execute the operation client
			_operationClient.execute(true);

			org.apache.axis2.context.MessageContext _returnMessageContext = _operationClient.getMessageContext(org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
			org.apache.axiom.soap.SOAPEnvelope _returnEnv = _returnMessageContext.getEnvelope();
			System.out.println("接收报文:" + _returnEnv);

			java.lang.Object object = fromOM(_returnEnv.getBody().getFirstElement(), com.primeton.ecds.S01001010021003ServiceStub.S01001010021003Response.class, getEnvelopeNamespaces(_returnEnv));
			_messageContext.getTransportOut().getSender().cleanup(_messageContext);

			return (com.primeton.ecds.S01001010021003ServiceStub.S01001010021003Response) object;

		} catch (org.apache.axis2.AxisFault f) {

			org.apache.axiom.om.OMElement faultElt = f.getDetail();
			if (faultElt != null) {
				if (faultExceptionNameMap.containsKey(faultElt.getQName())) {
					// make the fault by reflection
					try {
						java.lang.String exceptionClassName = (java.lang.String) faultExceptionClassNameMap.get(faultElt.getQName());
						java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
						java.lang.Exception ex = (java.lang.Exception) exceptionClass.newInstance();
						// message class
						java.lang.String messageClassName = (java.lang.String) faultMessageMap.get(faultElt.getQName());
						java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
						java.lang.Object messageObject = fromOM(faultElt, messageClass, null);
						java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage", new java.lang.Class[] { messageClass });
						m.invoke(ex, new java.lang.Object[] { messageObject });

						throw new java.rmi.RemoteException(ex.getMessage(), ex);
					} catch (java.lang.ClassCastException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					} catch (java.lang.ClassNotFoundException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					} catch (java.lang.NoSuchMethodException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					} catch (java.lang.reflect.InvocationTargetException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					} catch (java.lang.IllegalAccessException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					} catch (java.lang.InstantiationException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}
				} else {
					throw f;
				}
			} else {
				throw f;
			}
		}
	}

	/**
	 * Auto generated method signature for Asynchronous Invocations
	 * @see com.primeton.ecds.S01001010021003Service#startS01001010021003
	 * @param s010010100210030
	 */
	public void startS01001010021003(

	com.primeton.ecds.S01001010021003ServiceStub.S01001010021003 s010010100210030,

	final com.primeton.ecds.S01001010021003ServiceCallbackHandler callback)

	throws java.rmi.RemoteException {

		org.apache.axis2.client.OperationClient _operationClient = _serviceClient.createClient(_operations[0].getName());
		_operationClient.getOptions().setAction("http://172.16.221.83:12103/WebService/CRMS_SVR/S01001010021003");
		_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

		addPropertyToOperationClient(_operationClient, org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR, "&");

		// create SOAP envelope with that payload
		org.apache.axiom.soap.SOAPEnvelope env = null;
		org.apache.axis2.context.MessageContext _messageContext = new org.apache.axis2.context.MessageContext();

		// Style is Doc.

		env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()), s010010100210030, optimizeContent(new javax.xml.namespace.QName("http://www.adtec.com.cn", "S01001010021003")));

		// adding SOAP soap_headers
		_serviceClient.addHeadersToEnvelope(env);
		// create message context with that soap envelope
		_messageContext.setEnvelope(env);

		// add the message context to the operation client
		_operationClient.addMessageContext(_messageContext);

		_operationClient.setCallback(new org.apache.axis2.client.async.AxisCallback() {
			public void onMessage(org.apache.axis2.context.MessageContext resultContext) {
				try {
					org.apache.axiom.soap.SOAPEnvelope resultEnv = resultContext.getEnvelope();

					java.lang.Object object = fromOM(resultEnv.getBody().getFirstElement(), com.primeton.ecds.S01001010021003ServiceStub.S01001010021003Response.class,
							getEnvelopeNamespaces(resultEnv));
					callback.receiveResultS01001010021003((com.primeton.ecds.S01001010021003ServiceStub.S01001010021003Response) object);

				} catch (org.apache.axis2.AxisFault e) {
					callback.receiveErrorS01001010021003(e);
				}
			}

			public void onError(java.lang.Exception error) {
				if (error instanceof org.apache.axis2.AxisFault) {
					org.apache.axis2.AxisFault f = (org.apache.axis2.AxisFault) error;
					org.apache.axiom.om.OMElement faultElt = f.getDetail();
					if (faultElt != null) {
						if (faultExceptionNameMap.containsKey(faultElt.getQName())) {
							// make the fault by reflection
							try {
								java.lang.String exceptionClassName = (java.lang.String) faultExceptionClassNameMap.get(faultElt.getQName());
								java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
								java.lang.Exception ex = (java.lang.Exception) exceptionClass.newInstance();
								// message class
								java.lang.String messageClassName = (java.lang.String) faultMessageMap.get(faultElt.getQName());
								java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
								java.lang.Object messageObject = fromOM(faultElt, messageClass, null);
								java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage", new java.lang.Class[] { messageClass });
								m.invoke(ex, new java.lang.Object[] { messageObject });

								callback.receiveErrorS01001010021003(new java.rmi.RemoteException(ex.getMessage(), ex));
							} catch (java.lang.ClassCastException e) {
								// we cannot intantiate the class - throw the original Axis fault
								callback.receiveErrorS01001010021003(f);
							} catch (java.lang.ClassNotFoundException e) {
								// we cannot intantiate the class - throw the original Axis fault
								callback.receiveErrorS01001010021003(f);
							} catch (java.lang.NoSuchMethodException e) {
								// we cannot intantiate the class - throw the original Axis fault
								callback.receiveErrorS01001010021003(f);
							} catch (java.lang.reflect.InvocationTargetException e) {
								// we cannot intantiate the class - throw the original Axis fault
								callback.receiveErrorS01001010021003(f);
							} catch (java.lang.IllegalAccessException e) {
								// we cannot intantiate the class - throw the original Axis fault
								callback.receiveErrorS01001010021003(f);
							} catch (java.lang.InstantiationException e) {
								// we cannot intantiate the class - throw the original Axis fault
								callback.receiveErrorS01001010021003(f);
							} catch (org.apache.axis2.AxisFault e) {
								// we cannot intantiate the class - throw the original Axis fault
								callback.receiveErrorS01001010021003(f);
							}
						} else {
							callback.receiveErrorS01001010021003(f);
						}
					} else {
						callback.receiveErrorS01001010021003(f);
					}
				} else {
					callback.receiveErrorS01001010021003(error);
				}
			}

			public void onFault(org.apache.axis2.context.MessageContext faultContext) {
				org.apache.axis2.AxisFault fault = org.apache.axis2.util.Utils.getInboundFaultFromMessageContext(faultContext);
				onError(fault);
			}

			public void onComplete() {
				// Do nothing by default
			}
		});

		org.apache.axis2.util.CallbackReceiver _callbackReceiver = null;
		if (_operations[0].getMessageReceiver() == null && _operationClient.getOptions().isUseSeparateListener()) {
			_callbackReceiver = new org.apache.axis2.util.CallbackReceiver();
			_operations[0].setMessageReceiver(_callbackReceiver);
		}

		// execute the operation client
		_operationClient.execute(false);

	}

	/**
	 * A utility method that copies the namepaces from the SOAPEnvelope
	 */
	private java.util.Map getEnvelopeNamespaces(org.apache.axiom.soap.SOAPEnvelope env) {
		java.util.Map returnMap = new java.util.HashMap();
		java.util.Iterator namespaceIterator = env.getAllDeclaredNamespaces();
		while (namespaceIterator.hasNext()) {
			org.apache.axiom.om.OMNamespace ns = (org.apache.axiom.om.OMNamespace) namespaceIterator.next();
			returnMap.put(ns.getPrefix(), ns.getNamespaceURI());
		}
		return returnMap;
	}

	private javax.xml.namespace.QName[] opNameArray = null;

	private boolean optimizeContent(javax.xml.namespace.QName opName) {

		if (opNameArray == null) {
			return false;
		}
		for (int i = 0; i < opNameArray.length; i++) {
			if (opName.equals(opNameArray[i])) {
				return true;
			}
		}
		return false;
	}

	// http://172.16.221.83:12103/WebService/CRMS_SVR/S01001010021003

	public static class FMT_SOAP_UTF8_ResponseHeader implements org.apache.axis2.databinding.ADBBean {
		/*
		 * This type was generated from the piece of schema that had
		 * name = FMT_SOAP_UTF8_ResponseHeader
		 * Namespace URI = http://www.adtec.com.cn
		 * Namespace Prefix = ns1
		 */

		private static java.lang.String generatePrefix(java.lang.String namespace) {
			if (namespace.equals("http://www.adtec.com.cn")) {
				return "ns1";
			}
			return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
		}

		/**
		 * field for VersionNo
		 */

		protected java.lang.String localVersionNo;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localVersionNoTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getVersionNo() {
			return localVersionNo;
		}

		/**
		 * Auto generated setter method
		 * @param param VersionNo
		 */
		public void setVersionNo(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localVersionNoTracker = true;
			} else {
				localVersionNoTracker = false;

			}

			this.localVersionNo = param;

		}

		/**
		 * field for ReqSysCode
		 */

		protected java.lang.String localReqSysCode;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localReqSysCodeTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getReqSysCode() {
			return localReqSysCode;
		}

		/**
		 * Auto generated setter method
		 * @param param ReqSysCode
		 */
		public void setReqSysCode(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localReqSysCodeTracker = true;
			} else {
				localReqSysCodeTracker = false;

			}

			this.localReqSysCode = param;

		}

		/**
		 * field for ReqSecCode
		 */

		protected java.lang.String localReqSecCode;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localReqSecCodeTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getReqSecCode() {
			return localReqSecCode;
		}

		/**
		 * Auto generated setter method
		 * @param param ReqSecCode
		 */
		public void setReqSecCode(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localReqSecCodeTracker = true;
			} else {
				localReqSecCodeTracker = false;

			}

			this.localReqSecCode = param;

		}

		/**
		 * field for TxType
		 */

		protected java.lang.String localTxType;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localTxTypeTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getTxType() {
			return localTxType;
		}

		/**
		 * Auto generated setter method
		 * @param param TxType
		 */
		public void setTxType(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localTxTypeTracker = true;
			} else {
				localTxTypeTracker = false;

			}

			this.localTxType = param;

		}

		/**
		 * field for TxMode
		 */

		protected java.lang.String localTxMode;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localTxModeTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getTxMode() {
			return localTxMode;
		}

		/**
		 * Auto generated setter method
		 * @param param TxMode
		 */
		public void setTxMode(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localTxModeTracker = true;
			} else {
				localTxModeTracker = false;

			}

			this.localTxMode = param;

		}

		/**
		 * field for TxCode
		 */

		protected java.lang.String localTxCode;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localTxCodeTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getTxCode() {
			return localTxCode;
		}

		/**
		 * Auto generated setter method
		 * @param param TxCode
		 */
		public void setTxCode(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localTxCodeTracker = true;
			} else {
				localTxCodeTracker = false;

			}

			this.localTxCode = param;

		}

		/**
		 * field for ReqDate
		 */

		protected java.lang.String localReqDate;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localReqDateTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getReqDate() {
			return localReqDate;
		}

		/**
		 * Auto generated setter method
		 * @param param ReqDate
		 */
		public void setReqDate(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localReqDateTracker = true;
			} else {
				localReqDateTracker = false;

			}

			this.localReqDate = param;

		}

		/**
		 * field for ReqTime
		 */

		protected java.lang.String localReqTime;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localReqTimeTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getReqTime() {
			return localReqTime;
		}

		/**
		 * Auto generated setter method
		 * @param param ReqTime
		 */
		public void setReqTime(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localReqTimeTracker = true;
			} else {
				localReqTimeTracker = false;

			}

			this.localReqTime = param;

		}

		/**
		 * field for ReqSeqNo
		 */

		protected java.lang.String localReqSeqNo;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localReqSeqNoTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getReqSeqNo() {
			return localReqSeqNo;
		}

		/**
		 * Auto generated setter method
		 * @param param ReqSeqNo
		 */
		public void setReqSeqNo(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localReqSeqNoTracker = true;
			} else {
				localReqSeqNoTracker = false;

			}

			this.localReqSeqNo = param;

		}

		/**
		 * field for SvrDate
		 */

		protected java.lang.String localSvrDate;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localSvrDateTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getSvrDate() {
			return localSvrDate;
		}

		/**
		 * Auto generated setter method
		 * @param param SvrDate
		 */
		public void setSvrDate(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localSvrDateTracker = true;
			} else {
				localSvrDateTracker = false;

			}

			this.localSvrDate = param;

		}

		/**
		 * field for SvrTime
		 */

		protected java.lang.String localSvrTime;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localSvrTimeTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getSvrTime() {
			return localSvrTime;
		}

		/**
		 * Auto generated setter method
		 * @param param SvrTime
		 */
		public void setSvrTime(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localSvrTimeTracker = true;
			} else {
				localSvrTimeTracker = false;

			}

			this.localSvrTime = param;

		}

		/**
		 * field for SvrSeqNo
		 */

		protected java.lang.String localSvrSeqNo;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localSvrSeqNoTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getSvrSeqNo() {
			return localSvrSeqNo;
		}

		/**
		 * Auto generated setter method
		 * @param param SvrSeqNo
		 */
		public void setSvrSeqNo(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localSvrSeqNoTracker = true;
			} else {
				localSvrSeqNoTracker = false;

			}

			this.localSvrSeqNo = param;

		}

		/**
		 * field for RecvFileName
		 */

		protected java.lang.String localRecvFileName;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localRecvFileNameTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getRecvFileName() {
			return localRecvFileName;
		}

		/**
		 * Auto generated setter method
		 * @param param RecvFileName
		 */
		public void setRecvFileName(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localRecvFileNameTracker = true;
			} else {
				localRecvFileNameTracker = false;

			}

			this.localRecvFileName = param;

		}

		/**
		 * field for TotNum
		 */

		protected java.lang.String localTotNum;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localTotNumTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getTotNum() {
			return localTotNum;
		}

		/**
		 * Auto generated setter method
		 * @param param TotNum
		 */
		public void setTotNum(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localTotNumTracker = true;
			} else {
				localTotNumTracker = false;

			}

			this.localTotNum = param;

		}

		/**
		 * field for CurrNum
		 */

		protected java.lang.String localCurrNum;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localCurrNumTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getCurrNum() {
			return localCurrNum;
		}

		/**
		 * Auto generated setter method
		 * @param param CurrNum
		 */
		public void setCurrNum(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localCurrNumTracker = true;
			} else {
				localCurrNumTracker = false;

			}

			this.localCurrNum = param;

		}

		/**
		 * field for FileHMac
		 */

		protected java.lang.String localFileHMac;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localFileHMacTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getFileHMac() {
			return localFileHMac;
		}

		/**
		 * Auto generated setter method
		 * @param param FileHMac
		 */
		public void setFileHMac(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localFileHMacTracker = true;
			} else {
				localFileHMacTracker = false;

			}

			this.localFileHMac = param;

		}

		/**
		 * field for HMac
		 */

		protected java.lang.String localHMac;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localHMacTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getHMac() {
			return localHMac;
		}

		/**
		 * Auto generated setter method
		 * @param param HMac
		 */
		public void setHMac(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localHMacTracker = true;
			} else {
				localHMacTracker = false;

			}

			this.localHMac = param;

		}

		/**
		 * isReaderMTOMAware
		 * @return true if the reader supports MTOM
		 */
		public static boolean isReaderMTOMAware(javax.xml.stream.XMLStreamReader reader) {
			boolean isReaderMTOMAware = false;

			try {
				isReaderMTOMAware = java.lang.Boolean.TRUE.equals(reader.getProperty(org.apache.axiom.om.OMConstants.IS_DATA_HANDLERS_AWARE));
			} catch (java.lang.IllegalArgumentException e) {
				isReaderMTOMAware = false;
			}
			return isReaderMTOMAware;
		}

		/**
		 * @param parentQName
		 * @param factory
		 * @return org.apache.axiom.om.OMElement
		 */
		public org.apache.axiom.om.OMElement getOMElement(final javax.xml.namespace.QName parentQName, final org.apache.axiom.om.OMFactory factory) throws org.apache.axis2.databinding.ADBException {

			org.apache.axiom.om.OMDataSource dataSource = new org.apache.axis2.databinding.ADBDataSource(this, parentQName) {

				public void serialize(org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
					FMT_SOAP_UTF8_ResponseHeader.this.serialize(parentQName, factory, xmlWriter);
				}
			};
			return new org.apache.axiom.om.impl.llom.OMSourcedElementImpl(parentQName, factory, dataSource);

		}

		public void serialize(final javax.xml.namespace.QName parentQName, final org.apache.axiom.om.OMFactory factory, org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {

			java.lang.String prefix = null;
			java.lang.String namespace = null;

			prefix = parentQName.getPrefix();
			namespace = parentQName.getNamespaceURI();

			if (namespace != null) {
				java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
				if (writerPrefix != null) {
					xmlWriter.writeStartElement(namespace, parentQName.getLocalPart());
				} else {
					if (prefix == null) {
						prefix = generatePrefix(namespace);
					}

					xmlWriter.writeStartElement(prefix, parentQName.getLocalPart(), namespace);
					xmlWriter.writeNamespace(prefix, namespace);
					xmlWriter.setPrefix(prefix, namespace);
				}
			} else {
				xmlWriter.writeStartElement(parentQName.getLocalPart());
			}
			if (localVersionNoTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "VersionNo", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "VersionNo");
					}

				} else {
					xmlWriter.writeStartElement("VersionNo");
				}

				if (localVersionNo == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("VersionNo cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localVersionNo);

				}

				xmlWriter.writeEndElement();
			}
			if (localReqSysCodeTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "ReqSysCode", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "ReqSysCode");
					}

				} else {
					xmlWriter.writeStartElement("ReqSysCode");
				}

				if (localReqSysCode == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("ReqSysCode cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localReqSysCode);

				}

				xmlWriter.writeEndElement();
			}
			if (localReqSecCodeTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "ReqSecCode", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "ReqSecCode");
					}

				} else {
					xmlWriter.writeStartElement("ReqSecCode");
				}

				if (localReqSecCode == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("ReqSecCode cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localReqSecCode);

				}

				xmlWriter.writeEndElement();
			}
			if (localTxTypeTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "TxType", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "TxType");
					}

				} else {
					xmlWriter.writeStartElement("TxType");
				}

				if (localTxType == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("TxType cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localTxType);

				}

				xmlWriter.writeEndElement();
			}
			if (localTxModeTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "TxMode", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "TxMode");
					}

				} else {
					xmlWriter.writeStartElement("TxMode");
				}

				if (localTxMode == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("TxMode cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localTxMode);

				}

				xmlWriter.writeEndElement();
			}
			if (localTxCodeTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "TxCode", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "TxCode");
					}

				} else {
					xmlWriter.writeStartElement("TxCode");
				}

				if (localTxCode == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("TxCode cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localTxCode);

				}

				xmlWriter.writeEndElement();
			}
			if (localReqDateTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "ReqDate", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "ReqDate");
					}

				} else {
					xmlWriter.writeStartElement("ReqDate");
				}

				if (localReqDate == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("ReqDate cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localReqDate);

				}

				xmlWriter.writeEndElement();
			}
			if (localReqTimeTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "ReqTime", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "ReqTime");
					}

				} else {
					xmlWriter.writeStartElement("ReqTime");
				}

				if (localReqTime == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("ReqTime cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localReqTime);

				}

				xmlWriter.writeEndElement();
			}
			if (localReqSeqNoTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "ReqSeqNo", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "ReqSeqNo");
					}

				} else {
					xmlWriter.writeStartElement("ReqSeqNo");
				}

				if (localReqSeqNo == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("ReqSeqNo cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localReqSeqNo);

				}

				xmlWriter.writeEndElement();
			}
			if (localSvrDateTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "SvrDate", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "SvrDate");
					}

				} else {
					xmlWriter.writeStartElement("SvrDate");
				}

				if (localSvrDate == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("SvrDate cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localSvrDate);

				}

				xmlWriter.writeEndElement();
			}
			if (localSvrTimeTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "SvrTime", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "SvrTime");
					}

				} else {
					xmlWriter.writeStartElement("SvrTime");
				}

				if (localSvrTime == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("SvrTime cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localSvrTime);

				}

				xmlWriter.writeEndElement();
			}
			if (localSvrSeqNoTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "SvrSeqNo", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "SvrSeqNo");
					}

				} else {
					xmlWriter.writeStartElement("SvrSeqNo");
				}

				if (localSvrSeqNo == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("SvrSeqNo cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localSvrSeqNo);

				}

				xmlWriter.writeEndElement();
			}
			if (localRecvFileNameTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "RecvFileName", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "RecvFileName");
					}

				} else {
					xmlWriter.writeStartElement("RecvFileName");
				}

				if (localRecvFileName == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("RecvFileName cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localRecvFileName);

				}

				xmlWriter.writeEndElement();
			}
			if (localTotNumTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "TotNum", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "TotNum");
					}

				} else {
					xmlWriter.writeStartElement("TotNum");
				}

				if (localTotNum == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("TotNum cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localTotNum);

				}

				xmlWriter.writeEndElement();
			}
			if (localCurrNumTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "CurrNum", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "CurrNum");
					}

				} else {
					xmlWriter.writeStartElement("CurrNum");
				}

				if (localCurrNum == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("CurrNum cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localCurrNum);

				}

				xmlWriter.writeEndElement();
			}
			if (localFileHMacTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "FileHMac", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "FileHMac");
					}

				} else {
					xmlWriter.writeStartElement("FileHMac");
				}

				if (localFileHMac == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("FileHMac cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localFileHMac);

				}

				xmlWriter.writeEndElement();
			}
			if (localHMacTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "HMac", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "HMac");
					}

				} else {
					xmlWriter.writeStartElement("HMac");
				}

				if (localHMac == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("HMac cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localHMac);

				}

				xmlWriter.writeEndElement();
			}
			xmlWriter.writeEndElement();

		}

		/**
		 * Util method to write an attribute with the ns prefix
		 */
		private void writeAttribute(java.lang.String prefix, java.lang.String namespace, java.lang.String attName, java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			if (xmlWriter.getPrefix(namespace) == null) {
				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);

			}

			xmlWriter.writeAttribute(namespace, attName, attValue);

		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeAttribute(java.lang.String namespace, java.lang.String attName, java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			if (namespace.equals("")) {
				xmlWriter.writeAttribute(attName, attValue);
			} else {
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attValue);
			}
		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeQNameAttribute(java.lang.String namespace, java.lang.String attName, javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {

			java.lang.String attributeNamespace = qname.getNamespaceURI();
			java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
			if (attributePrefix == null) {
				attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
			}
			java.lang.String attributeValue;
			if (attributePrefix.trim().length() > 0) {
				attributeValue = attributePrefix + ":" + qname.getLocalPart();
			} else {
				attributeValue = qname.getLocalPart();
			}

			if (namespace.equals("")) {
				xmlWriter.writeAttribute(attName, attributeValue);
			} else {
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attributeValue);
			}
		}

		/**
		 * method to handle Qnames
		 */

		private void writeQName(javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
			java.lang.String namespaceURI = qname.getNamespaceURI();
			if (namespaceURI != null) {
				java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
				if (prefix == null) {
					prefix = generatePrefix(namespaceURI);
					xmlWriter.writeNamespace(prefix, namespaceURI);
					xmlWriter.setPrefix(prefix, namespaceURI);
				}

				if (prefix.trim().length() > 0) {
					xmlWriter.writeCharacters(prefix + ":" + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
				} else {
					// i.e this is the default namespace
					xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
				}

			} else {
				xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
			}
		}

		private void writeQNames(javax.xml.namespace.QName[] qnames, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

			if (qnames != null) {
				// we have to store this data until last moment since it is not possible to write any
				// namespace data after writing the charactor data
				java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
				java.lang.String namespaceURI = null;
				java.lang.String prefix = null;

				for (int i = 0; i < qnames.length; i++) {
					if (i > 0) {
						stringToWrite.append(" ");
					}
					namespaceURI = qnames[i].getNamespaceURI();
					if (namespaceURI != null) {
						prefix = xmlWriter.getPrefix(namespaceURI);
						if ((prefix == null) || (prefix.length() == 0)) {
							prefix = generatePrefix(namespaceURI);
							xmlWriter.writeNamespace(prefix, namespaceURI);
							xmlWriter.setPrefix(prefix, namespaceURI);
						}

						if (prefix.trim().length() > 0) {
							stringToWrite.append(prefix).append(":").append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
						} else {
							stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
						}
					} else {
						stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
					}
				}
				xmlWriter.writeCharacters(stringToWrite.toString());
			}

		}

		/**
		 * Register a namespace prefix
		 */
		private java.lang.String registerPrefix(javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace) throws javax.xml.stream.XMLStreamException {
			java.lang.String prefix = xmlWriter.getPrefix(namespace);

			if (prefix == null) {
				prefix = generatePrefix(namespace);

				while (xmlWriter.getNamespaceContext().getNamespaceURI(prefix) != null) {
					prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
				}

				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);
			}

			return prefix;
		}

		/**
		 * databinding method to get an XML representation of this object
		 */
		public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName) throws org.apache.axis2.databinding.ADBException {

			java.util.ArrayList elementList = new java.util.ArrayList();
			java.util.ArrayList attribList = new java.util.ArrayList();

			if (localVersionNoTracker) {
				elementList.add(new javax.xml.namespace.QName("", "VersionNo"));

				if (localVersionNo != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localVersionNo));
				} else {
					throw new org.apache.axis2.databinding.ADBException("VersionNo cannot be null!!");
				}
			}
			if (localReqSysCodeTracker) {
				elementList.add(new javax.xml.namespace.QName("", "ReqSysCode"));

				if (localReqSysCode != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localReqSysCode));
				} else {
					throw new org.apache.axis2.databinding.ADBException("ReqSysCode cannot be null!!");
				}
			}
			if (localReqSecCodeTracker) {
				elementList.add(new javax.xml.namespace.QName("", "ReqSecCode"));

				if (localReqSecCode != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localReqSecCode));
				} else {
					throw new org.apache.axis2.databinding.ADBException("ReqSecCode cannot be null!!");
				}
			}
			if (localTxTypeTracker) {
				elementList.add(new javax.xml.namespace.QName("", "TxType"));

				if (localTxType != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localTxType));
				} else {
					throw new org.apache.axis2.databinding.ADBException("TxType cannot be null!!");
				}
			}
			if (localTxModeTracker) {
				elementList.add(new javax.xml.namespace.QName("", "TxMode"));

				if (localTxMode != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localTxMode));
				} else {
					throw new org.apache.axis2.databinding.ADBException("TxMode cannot be null!!");
				}
			}
			if (localTxCodeTracker) {
				elementList.add(new javax.xml.namespace.QName("", "TxCode"));

				if (localTxCode != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localTxCode));
				} else {
					throw new org.apache.axis2.databinding.ADBException("TxCode cannot be null!!");
				}
			}
			if (localReqDateTracker) {
				elementList.add(new javax.xml.namespace.QName("", "ReqDate"));

				if (localReqDate != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localReqDate));
				} else {
					throw new org.apache.axis2.databinding.ADBException("ReqDate cannot be null!!");
				}
			}
			if (localReqTimeTracker) {
				elementList.add(new javax.xml.namespace.QName("", "ReqTime"));

				if (localReqTime != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localReqTime));
				} else {
					throw new org.apache.axis2.databinding.ADBException("ReqTime cannot be null!!");
				}
			}
			if (localReqSeqNoTracker) {
				elementList.add(new javax.xml.namespace.QName("", "ReqSeqNo"));

				if (localReqSeqNo != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localReqSeqNo));
				} else {
					throw new org.apache.axis2.databinding.ADBException("ReqSeqNo cannot be null!!");
				}
			}
			if (localSvrDateTracker) {
				elementList.add(new javax.xml.namespace.QName("", "SvrDate"));

				if (localSvrDate != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localSvrDate));
				} else {
					throw new org.apache.axis2.databinding.ADBException("SvrDate cannot be null!!");
				}
			}
			if (localSvrTimeTracker) {
				elementList.add(new javax.xml.namespace.QName("", "SvrTime"));

				if (localSvrTime != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localSvrTime));
				} else {
					throw new org.apache.axis2.databinding.ADBException("SvrTime cannot be null!!");
				}
			}
			if (localSvrSeqNoTracker) {
				elementList.add(new javax.xml.namespace.QName("", "SvrSeqNo"));

				if (localSvrSeqNo != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localSvrSeqNo));
				} else {
					throw new org.apache.axis2.databinding.ADBException("SvrSeqNo cannot be null!!");
				}
			}
			if (localRecvFileNameTracker) {
				elementList.add(new javax.xml.namespace.QName("", "RecvFileName"));

				if (localRecvFileName != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localRecvFileName));
				} else {
					throw new org.apache.axis2.databinding.ADBException("RecvFileName cannot be null!!");
				}
			}
			if (localTotNumTracker) {
				elementList.add(new javax.xml.namespace.QName("", "TotNum"));

				if (localTotNum != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localTotNum));
				} else {
					throw new org.apache.axis2.databinding.ADBException("TotNum cannot be null!!");
				}
			}
			if (localCurrNumTracker) {
				elementList.add(new javax.xml.namespace.QName("", "CurrNum"));

				if (localCurrNum != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCurrNum));
				} else {
					throw new org.apache.axis2.databinding.ADBException("CurrNum cannot be null!!");
				}
			}
			if (localFileHMacTracker) {
				elementList.add(new javax.xml.namespace.QName("", "FileHMac"));

				if (localFileHMac != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localFileHMac));
				} else {
					throw new org.apache.axis2.databinding.ADBException("FileHMac cannot be null!!");
				}
			}
			if (localHMacTracker) {
				elementList.add(new javax.xml.namespace.QName("", "HMac"));

				if (localHMac != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHMac));
				} else {
					throw new org.apache.axis2.databinding.ADBException("HMac cannot be null!!");
				}
			}

			return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName, elementList.toArray(), attribList.toArray());

		}

		/**
		 * Factory class that keeps the parse method
		 */
		public static class Factory {

			/**
			 * static method to create the object
			 * Precondition: If this object is an element, the current or next start element starts this object and any intervening reader events are
			 * ignorable
			 * If this object is not an element, it is a complex type and the reader is at the event just after the outer start element
			 * Postcondition: If this object is an element, the reader is positioned at its end element
			 * If this object is a complex type, the reader is positioned at the end element of its outer element
			 */
			public static FMT_SOAP_UTF8_ResponseHeader parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception {
				FMT_SOAP_UTF8_ResponseHeader object = new FMT_SOAP_UTF8_ResponseHeader();

				int event;
				java.lang.String nillableValue = null;
				java.lang.String prefix = "";
				java.lang.String namespaceuri = "";
				try {

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "type") != null) {
						java.lang.String fullTypeName = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "type");
						if (fullTypeName != null) {
							java.lang.String nsPrefix = null;
							if (fullTypeName.indexOf(":") > -1) {
								nsPrefix = fullTypeName.substring(0, fullTypeName.indexOf(":"));
							}
							nsPrefix = nsPrefix == null ? "" : nsPrefix;

							java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":") + 1);

							if (!"FMT_SOAP_UTF8_ResponseHeader".equals(type)) {
								// find namespace for the prefix
								java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
								return (FMT_SOAP_UTF8_ResponseHeader) ExtensionMapper.getTypeObject(nsUri, type, reader);
							}

						}

					}

					// Note all attributes that were handled. Used to differ normal attributes
					// from anyAttributes.
					java.util.Vector handledAttributes = new java.util.Vector();

					reader.next();

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "VersionNo").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setVersionNo(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "ReqSysCode").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setReqSysCode(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "ReqSecCode").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setReqSecCode(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "TxType").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setTxType(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "TxMode").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setTxMode(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "TxCode").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setTxCode(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "ReqDate").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setReqDate(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "ReqTime").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setReqTime(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "ReqSeqNo").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setReqSeqNo(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "SvrDate").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setSvrDate(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "SvrTime").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setSvrTime(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "SvrSeqNo").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setSvrSeqNo(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "RecvFileName").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setRecvFileName(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "TotNum").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setTotNum(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "CurrNum").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setCurrNum(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "FileHMac").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setFileHMac(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "HMac").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setHMac(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement())
						// A start element we are not expecting indicates a trailing invalid property
						throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());

				} catch (javax.xml.stream.XMLStreamException e) {
					throw new java.lang.Exception(e);
				}

				return object;
			}

		}// end of factory class

	}

	public static class S01001010021003 implements org.apache.axis2.databinding.ADBBean {

		public static final javax.xml.namespace.QName MY_QNAME = new javax.xml.namespace.QName("http://www.adtec.com.cn", "S01001010021003", "ns1");

		private static java.lang.String generatePrefix(java.lang.String namespace) {
			if (namespace.equals("http://www.adtec.com.cn")) {
				return "ns1";
			}
			return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
		}

		/**
		 * field for ReqTranHeader
		 */

		protected FMT_SOAP_UTF8_ReqTranHeader localReqTranHeader;

		/**
		 * Auto generated getter method
		 * @return FMT_SOAP_UTF8_ReqTranHeader
		 */
		public FMT_SOAP_UTF8_ReqTranHeader getReqTranHeader() {
			return localReqTranHeader;
		}

		/**
		 * Auto generated setter method
		 * @param param ReqTranHeader
		 */
		public void setReqTranHeader(FMT_SOAP_UTF8_ReqTranHeader param) {

			this.localReqTranHeader = param;

		}

		/**
		 * field for RequestBody
		 */

		protected FMT_CRMS_SVR_S01001010021003_IN localRequestBody;

		/**
		 * Auto generated getter method
		 * @return FMT_CRMS_SVR_S01001010021003_IN
		 */
		public FMT_CRMS_SVR_S01001010021003_IN getRequestBody() {
			return localRequestBody;
		}

		/**
		 * Auto generated setter method
		 * @param param RequestBody
		 */
		public void setRequestBody(FMT_CRMS_SVR_S01001010021003_IN param) {

			this.localRequestBody = param;

		}

		/**
		 * field for RequestHeader
		 */

		protected FMT_SOAP_UTF8_RequestHeader localRequestHeader;

		/**
		 * Auto generated getter method
		 * @return FMT_SOAP_UTF8_RequestHeader
		 */
		public FMT_SOAP_UTF8_RequestHeader getRequestHeader() {
			return localRequestHeader;
		}

		/**
		 * Auto generated setter method
		 * @param param RequestHeader
		 */
		public void setRequestHeader(FMT_SOAP_UTF8_RequestHeader param) {

			this.localRequestHeader = param;

		}

		/**
		 * isReaderMTOMAware
		 * @return true if the reader supports MTOM
		 */
		public static boolean isReaderMTOMAware(javax.xml.stream.XMLStreamReader reader) {
			boolean isReaderMTOMAware = false;

			try {
				isReaderMTOMAware = java.lang.Boolean.TRUE.equals(reader.getProperty(org.apache.axiom.om.OMConstants.IS_DATA_HANDLERS_AWARE));
			} catch (java.lang.IllegalArgumentException e) {
				isReaderMTOMAware = false;
			}
			return isReaderMTOMAware;
		}

		/**
		 * @param parentQName
		 * @param factory
		 * @return org.apache.axiom.om.OMElement
		 */
		public org.apache.axiom.om.OMElement getOMElement(final javax.xml.namespace.QName parentQName, final org.apache.axiom.om.OMFactory factory) throws org.apache.axis2.databinding.ADBException {

			org.apache.axiom.om.OMDataSource dataSource = new org.apache.axis2.databinding.ADBDataSource(this, MY_QNAME) {

				public void serialize(org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
					S01001010021003.this.serialize(MY_QNAME, factory, xmlWriter);
				}
			};
			return new org.apache.axiom.om.impl.llom.OMSourcedElementImpl(MY_QNAME, factory, dataSource);

		}

		public void serialize(final javax.xml.namespace.QName parentQName, final org.apache.axiom.om.OMFactory factory, org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {

			java.lang.String prefix = null;
			java.lang.String namespace = null;

			prefix = parentQName.getPrefix();
			namespace = parentQName.getNamespaceURI();

			if (namespace != null) {
				java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
				if (writerPrefix != null) {
					xmlWriter.writeStartElement(namespace, parentQName.getLocalPart());
				} else {
					if (prefix == null) {
						prefix = generatePrefix(namespace);
					}

					xmlWriter.writeStartElement(prefix, parentQName.getLocalPart(), namespace);
					xmlWriter.writeNamespace(prefix, namespace);
					xmlWriter.setPrefix(prefix, namespace);
				}
			} else {
				xmlWriter.writeStartElement(parentQName.getLocalPart());
			}

			if (localReqTranHeader == null) {
				throw new org.apache.axis2.databinding.ADBException("ReqTranHeader cannot be null!!");
			}
			localReqTranHeader.serialize(new javax.xml.namespace.QName("", "ReqTranHeader"), factory, xmlWriter);

			if (localRequestBody == null) {
				throw new org.apache.axis2.databinding.ADBException("RequestBody cannot be null!!");
			}
			localRequestBody.serialize(new javax.xml.namespace.QName("", "RequestBody"), factory, xmlWriter);

			if (localRequestHeader == null) {
				throw new org.apache.axis2.databinding.ADBException("RequestHeader cannot be null!!");
			}
			localRequestHeader.serialize(new javax.xml.namespace.QName("", "RequestHeader"), factory, xmlWriter);

			xmlWriter.writeEndElement();

		}

		/**
		 * Util method to write an attribute with the ns prefix
		 */
		private void writeAttribute(java.lang.String prefix, java.lang.String namespace, java.lang.String attName, java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			if (xmlWriter.getPrefix(namespace) == null) {
				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);

			}

			xmlWriter.writeAttribute(namespace, attName, attValue);

		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeAttribute(java.lang.String namespace, java.lang.String attName, java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			if (namespace.equals("")) {
				xmlWriter.writeAttribute(attName, attValue);
			} else {
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attValue);
			}
		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeQNameAttribute(java.lang.String namespace, java.lang.String attName, javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {

			java.lang.String attributeNamespace = qname.getNamespaceURI();
			java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
			if (attributePrefix == null) {
				attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
			}
			java.lang.String attributeValue;
			if (attributePrefix.trim().length() > 0) {
				attributeValue = attributePrefix + ":" + qname.getLocalPart();
			} else {
				attributeValue = qname.getLocalPart();
			}

			if (namespace.equals("")) {
				xmlWriter.writeAttribute(attName, attributeValue);
			} else {
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attributeValue);
			}
		}

		/**
		 * method to handle Qnames
		 */

		private void writeQName(javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
			java.lang.String namespaceURI = qname.getNamespaceURI();
			if (namespaceURI != null) {
				java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
				if (prefix == null) {
					prefix = generatePrefix(namespaceURI);
					xmlWriter.writeNamespace(prefix, namespaceURI);
					xmlWriter.setPrefix(prefix, namespaceURI);
				}

				if (prefix.trim().length() > 0) {
					xmlWriter.writeCharacters(prefix + ":" + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
				} else {
					// i.e this is the default namespace
					xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
				}

			} else {
				xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
			}
		}

		private void writeQNames(javax.xml.namespace.QName[] qnames, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

			if (qnames != null) {
				// we have to store this data until last moment since it is not possible to write any
				// namespace data after writing the charactor data
				java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
				java.lang.String namespaceURI = null;
				java.lang.String prefix = null;

				for (int i = 0; i < qnames.length; i++) {
					if (i > 0) {
						stringToWrite.append(" ");
					}
					namespaceURI = qnames[i].getNamespaceURI();
					if (namespaceURI != null) {
						prefix = xmlWriter.getPrefix(namespaceURI);
						if ((prefix == null) || (prefix.length() == 0)) {
							prefix = generatePrefix(namespaceURI);
							xmlWriter.writeNamespace(prefix, namespaceURI);
							xmlWriter.setPrefix(prefix, namespaceURI);
						}

						if (prefix.trim().length() > 0) {
							stringToWrite.append(prefix).append(":").append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
						} else {
							stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
						}
					} else {
						stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
					}
				}
				xmlWriter.writeCharacters(stringToWrite.toString());
			}

		}

		/**
		 * Register a namespace prefix
		 */
		private java.lang.String registerPrefix(javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace) throws javax.xml.stream.XMLStreamException {
			java.lang.String prefix = xmlWriter.getPrefix(namespace);

			if (prefix == null) {
				prefix = generatePrefix(namespace);

				while (xmlWriter.getNamespaceContext().getNamespaceURI(prefix) != null) {
					prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
				}

				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);
			}

			return prefix;
		}

		/**
		 * databinding method to get an XML representation of this object
		 */
		public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName) throws org.apache.axis2.databinding.ADBException {

			java.util.ArrayList elementList = new java.util.ArrayList();
			java.util.ArrayList attribList = new java.util.ArrayList();

			elementList.add(new javax.xml.namespace.QName("", "ReqTranHeader"));

			if (localReqTranHeader == null) {
				throw new org.apache.axis2.databinding.ADBException("ReqTranHeader cannot be null!!");
			}
			elementList.add(localReqTranHeader);

			elementList.add(new javax.xml.namespace.QName("", "RequestBody"));

			if (localRequestBody == null) {
				throw new org.apache.axis2.databinding.ADBException("RequestBody cannot be null!!");
			}
			elementList.add(localRequestBody);

			elementList.add(new javax.xml.namespace.QName("", "RequestHeader"));

			if (localRequestHeader == null) {
				throw new org.apache.axis2.databinding.ADBException("RequestHeader cannot be null!!");
			}
			elementList.add(localRequestHeader);

			return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName, elementList.toArray(), attribList.toArray());

		}

		/**
		 * Factory class that keeps the parse method
		 */
		public static class Factory {

			/**
			 * static method to create the object
			 * Precondition: If this object is an element, the current or next start element starts this object and any intervening reader events are
			 * ignorable
			 * If this object is not an element, it is a complex type and the reader is at the event just after the outer start element
			 * Postcondition: If this object is an element, the reader is positioned at its end element
			 * If this object is a complex type, the reader is positioned at the end element of its outer element
			 */
			public static S01001010021003 parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception {
				S01001010021003 object = new S01001010021003();

				int event;
				java.lang.String nillableValue = null;
				java.lang.String prefix = "";
				java.lang.String namespaceuri = "";
				try {

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "type") != null) {
						java.lang.String fullTypeName = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "type");
						if (fullTypeName != null) {
							java.lang.String nsPrefix = null;
							if (fullTypeName.indexOf(":") > -1) {
								nsPrefix = fullTypeName.substring(0, fullTypeName.indexOf(":"));
							}
							nsPrefix = nsPrefix == null ? "" : nsPrefix;

							java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":") + 1);

							if (!"S01001010021003".equals(type)) {
								// find namespace for the prefix
								java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
								return (S01001010021003) ExtensionMapper.getTypeObject(nsUri, type, reader);
							}

						}

					}

					// Note all attributes that were handled. Used to differ normal attributes
					// from anyAttributes.
					java.util.Vector handledAttributes = new java.util.Vector();

					reader.next();

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "ReqTranHeader").equals(reader.getName())) {

						object.setReqTranHeader(FMT_SOAP_UTF8_ReqTranHeader.Factory.parse(reader));

						reader.next();

					} // End of if for expected property start element

					else {
						// A start element we are not expecting indicates an invalid parameter was passed
						throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());
					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "RequestBody").equals(reader.getName())) {

						object.setRequestBody(FMT_CRMS_SVR_S01001010021003_IN.Factory.parse(reader));

						reader.next();

					} // End of if for expected property start element

					else {
						// A start element we are not expecting indicates an invalid parameter was passed
						throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());
					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "RequestHeader").equals(reader.getName())) {

						object.setRequestHeader(FMT_SOAP_UTF8_RequestHeader.Factory.parse(reader));

						reader.next();

					} // End of if for expected property start element

					else {
						// A start element we are not expecting indicates an invalid parameter was passed
						throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());
					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement())
						// A start element we are not expecting indicates a trailing invalid property
						throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());

				} catch (javax.xml.stream.XMLStreamException e) {
					throw new java.lang.Exception(e);
				}

				return object;
			}

		}// end of factory class

	}

	public static class FMT_SOAP_UTF8_ResTranHeader implements org.apache.axis2.databinding.ADBBean {
		/*
		 * This type was generated from the piece of schema that had
		 * name = FMT_SOAP_UTF8_ResTranHeader
		 * Namespace URI = http://www.adtec.com.cn
		 * Namespace Prefix = ns1
		 */

		private static java.lang.String generatePrefix(java.lang.String namespace) {
			if (namespace.equals("http://www.adtec.com.cn")) {
				return "ns1";
			}
			return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
		}

		/**
		 * field for HSecFlag
		 */

		protected java.lang.String localHSecFlag;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localHSecFlagTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getHSecFlag() {
			return localHSecFlag;
		}

		/**
		 * Auto generated setter method
		 * @param param HSecFlag
		 */
		public void setHSecFlag(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localHSecFlagTracker = true;
			} else {
				localHSecFlagTracker = false;

			}

			this.localHSecFlag = param;

		}

		/**
		 * field for HCombFlag
		 */

		protected java.lang.String localHCombFlag;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localHCombFlagTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getHCombFlag() {
			return localHCombFlag;
		}

		/**
		 * Auto generated setter method
		 * @param param HCombFlag
		 */
		public void setHCombFlag(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localHCombFlagTracker = true;
			} else {
				localHCombFlagTracker = false;

			}

			this.localHCombFlag = param;

		}

		/**
		 * field for HSvcInfo
		 */

		protected java.lang.String localHSvcInfo;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localHSvcInfoTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getHSvcInfo() {
			return localHSvcInfo;
		}

		/**
		 * Auto generated setter method
		 * @param param HSvcInfo
		 */
		public void setHSvcInfo(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localHSvcInfoTracker = true;
			} else {
				localHSvcInfoTracker = false;

			}

			this.localHSvcInfo = param;

		}

		/**
		 * field for HSecInfoVerNo
		 */

		protected java.lang.String localHSecInfoVerNo;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localHSecInfoVerNoTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getHSecInfoVerNo() {
			return localHSecInfoVerNo;
		}

		/**
		 * Auto generated setter method
		 * @param param HSecInfoVerNo
		 */
		public void setHSecInfoVerNo(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localHSecInfoVerNoTracker = true;
			} else {
				localHSecInfoVerNoTracker = false;

			}

			this.localHSecInfoVerNo = param;

		}

		/**
		 * field for HMsgRefNo
		 */

		protected java.lang.String localHMsgRefNo;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localHMsgRefNoTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getHMsgRefNo() {
			return localHMsgRefNo;
		}

		/**
		 * Auto generated setter method
		 * @param param HMsgRefNo
		 */
		public void setHMsgRefNo(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localHMsgRefNoTracker = true;
			} else {
				localHMsgRefNoTracker = false;

			}

			this.localHMsgRefNo = param;

		}

		/**
		 * field for HIdentFlag
		 */

		protected java.lang.String localHIdentFlag;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localHIdentFlagTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getHIdentFlag() {
			return localHIdentFlag;
		}

		/**
		 * Auto generated setter method
		 * @param param HIdentFlag
		 */
		public void setHIdentFlag(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localHIdentFlagTracker = true;
			} else {
				localHIdentFlagTracker = false;

			}

			this.localHIdentFlag = param;

		}

		/**
		 * field for HSuperFlag
		 */

		protected java.lang.String localHSuperFlag;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localHSuperFlagTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getHSuperFlag() {
			return localHSuperFlag;
		}

		/**
		 * Auto generated setter method
		 * @param param HSuperFlag
		 */
		public void setHSuperFlag(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localHSuperFlagTracker = true;
			} else {
				localHSuperFlagTracker = false;

			}

			this.localHSuperFlag = param;

		}

		/**
		 * field for HChkFlag
		 */

		protected java.lang.String localHChkFlag;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localHChkFlagTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getHChkFlag() {
			return localHChkFlag;
		}

		/**
		 * Auto generated setter method
		 * @param param HChkFlag
		 */
		public void setHChkFlag(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localHChkFlagTracker = true;
			} else {
				localHChkFlagTracker = false;

			}

			this.localHChkFlag = param;

		}

		/**
		 * field for HChkTxnCd
		 */

		protected java.lang.String localHChkTxnCd;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localHChkTxnCdTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getHChkTxnCd() {
			return localHChkTxnCd;
		}

		/**
		 * Auto generated setter method
		 * @param param HChkTxnCd
		 */
		public void setHChkTxnCd(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localHChkTxnCdTracker = true;
			} else {
				localHChkTxnCdTracker = false;

			}

			this.localHChkTxnCd = param;

		}

		/**
		 * field for HVerfCd
		 */

		protected java.lang.String localHVerfCd;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localHVerfCdTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getHVerfCd() {
			return localHVerfCd;
		}

		/**
		 * Auto generated setter method
		 * @param param HVerfCd
		 */
		public void setHVerfCd(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localHVerfCdTracker = true;
			} else {
				localHVerfCdTracker = false;

			}

			this.localHVerfCd = param;

		}

		/**
		 * field for HTranRes
		 */

		protected java.lang.String localHTranRes;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localHTranResTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getHTranRes() {
			return localHTranRes;
		}

		/**
		 * Auto generated setter method
		 * @param param HTranRes
		 */
		public void setHTranRes(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localHTranResTracker = true;
			} else {
				localHTranResTracker = false;

			}

			this.localHTranRes = param;

		}

		/**
		 * field for HRefTxnCd
		 */

		protected java.lang.String localHRefTxnCd;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localHRefTxnCdTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getHRefTxnCd() {
			return localHRefTxnCd;
		}

		/**
		 * Auto generated setter method
		 * @param param HRefTxnCd
		 */
		public void setHRefTxnCd(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localHRefTxnCdTracker = true;
			} else {
				localHRefTxnCdTracker = false;

			}

			this.localHRefTxnCd = param;

		}

		/**
		 * field for HServerDt
		 */

		protected java.lang.String localHServerDt;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localHServerDtTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getHServerDt() {
			return localHServerDt;
		}

		/**
		 * Auto generated setter method
		 * @param param HServerDt
		 */
		public void setHServerDt(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localHServerDtTracker = true;
			} else {
				localHServerDtTracker = false;

			}

			this.localHServerDt = param;

		}

		/**
		 * field for HServerTm
		 */

		protected java.lang.String localHServerTm;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localHServerTmTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getHServerTm() {
			return localHServerTm;
		}

		/**
		 * Auto generated setter method
		 * @param param HServerTm
		 */
		public void setHServerTm(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localHServerTmTracker = true;
			} else {
				localHServerTmTracker = false;

			}

			this.localHServerTm = param;

		}

		/**
		 * field for HServerSeq
		 */

		protected java.lang.String localHServerSeq;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localHServerSeqTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getHServerSeq() {
			return localHServerSeq;
		}

		/**
		 * Auto generated setter method
		 * @param param HServerSeq
		 */
		public void setHServerSeq(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localHServerSeqTracker = true;
			} else {
				localHServerSeqTracker = false;

			}

			this.localHServerSeq = param;

		}

		/**
		 * field for HAcountDt
		 */

		protected java.lang.String localHAcountDt;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localHAcountDtTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getHAcountDt() {
			return localHAcountDt;
		}

		/**
		 * Auto generated setter method
		 * @param param HAcountDt
		 */
		public void setHAcountDt(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localHAcountDtTracker = true;
			} else {
				localHAcountDtTracker = false;

			}

			this.localHAcountDt = param;

		}

		/**
		 * field for HRefSeq
		 */

		protected java.lang.String localHRefSeq;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localHRefSeqTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getHRefSeq() {
			return localHRefSeq;
		}

		/**
		 * Auto generated setter method
		 * @param param HRefSeq
		 */
		public void setHRefSeq(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localHRefSeqTracker = true;
			} else {
				localHRefSeqTracker = false;

			}

			this.localHRefSeq = param;

		}

		/**
		 * field for HRefDt
		 */

		protected java.lang.String localHRefDt;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localHRefDtTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getHRefDt() {
			return localHRefDt;
		}

		/**
		 * Auto generated setter method
		 * @param param HRefDt
		 */
		public void setHRefDt(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localHRefDtTracker = true;
			} else {
				localHRefDtTracker = false;

			}

			this.localHRefDt = param;

		}

		/**
		 * field for HNextStep
		 */

		protected java.lang.String localHNextStep;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localHNextStepTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getHNextStep() {
			return localHNextStep;
		}

		/**
		 * Auto generated setter method
		 * @param param HNextStep
		 */
		public void setHNextStep(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localHNextStepTracker = true;
			} else {
				localHNextStepTracker = false;

			}

			this.localHNextStep = param;

		}

		/**
		 * field for HVchChk
		 */

		protected java.lang.String localHVchChk;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localHVchChkTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getHVchChk() {
			return localHVchChk;
		}

		/**
		 * Auto generated setter method
		 * @param param HVchChk
		 */
		public void setHVchChk(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localHVchChkTracker = true;
			} else {
				localHVchChkTracker = false;

			}

			this.localHVchChk = param;

		}

		/**
		 * field for HRetResInfo
		 */

		protected java.lang.String localHRetResInfo;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localHRetResInfoTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getHRetResInfo() {
			return localHRetResInfo;
		}

		/**
		 * Auto generated setter method
		 * @param param HRetResInfo
		 */
		public void setHRetResInfo(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localHRetResInfoTracker = true;
			} else {
				localHRetResInfoTracker = false;

			}

			this.localHRetResInfo = param;

		}

		/**
		 * field for HErrTranNo
		 */

		protected java.lang.String localHErrTranNo;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localHErrTranNoTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getHErrTranNo() {
			return localHErrTranNo;
		}

		/**
		 * Auto generated setter method
		 * @param param HErrTranNo
		 */
		public void setHErrTranNo(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localHErrTranNoTracker = true;
			} else {
				localHErrTranNoTracker = false;

			}

			this.localHErrTranNo = param;

		}

		/**
		 * field for HAssiInfo
		 */

		protected java.lang.String localHAssiInfo;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localHAssiInfoTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getHAssiInfo() {
			return localHAssiInfo;
		}

		/**
		 * Auto generated setter method
		 * @param param HAssiInfo
		 */
		public void setHAssiInfo(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localHAssiInfoTracker = true;
			} else {
				localHAssiInfoTracker = false;

			}

			this.localHAssiInfo = param;

		}

		/**
		 * field for HRetCode
		 */

		protected java.lang.String localHRetCode;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localHRetCodeTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getHRetCode() {
			return localHRetCode;
		}

		/**
		 * Auto generated setter method
		 * @param param HRetCode
		 */
		public void setHRetCode(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localHRetCodeTracker = true;
			} else {
				localHRetCodeTracker = false;

			}

			this.localHRetCode = param;

		}

		/**
		 * field for HRetNo
		 */

		protected java.lang.String localHRetNo;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localHRetNoTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getHRetNo() {
			return localHRetNo;
		}

		/**
		 * Auto generated setter method
		 * @param param HRetNo
		 */
		public void setHRetNo(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localHRetNoTracker = true;
			} else {
				localHRetNoTracker = false;

			}

			this.localHRetNo = param;

		}

		/**
		 * field for HRetMsg
		 */

		protected java.lang.String localHRetMsg;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localHRetMsgTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getHRetMsg() {
			return localHRetMsg;
		}

		/**
		 * Auto generated setter method
		 * @param param HRetMsg
		 */
		public void setHRetMsg(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localHRetMsgTracker = true;
			} else {
				localHRetMsgTracker = false;

			}

			this.localHRetMsg = param;

		}

		/**
		 * field for HWarnMsg
		 */

		protected java.lang.String localHWarnMsg;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localHWarnMsgTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getHWarnMsg() {
			return localHWarnMsg;
		}

		/**
		 * Auto generated setter method
		 * @param param HWarnMsg
		 */
		public void setHWarnMsg(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localHWarnMsgTracker = true;
			} else {
				localHWarnMsgTracker = false;

			}

			this.localHWarnMsg = param;

		}

		/**
		 * isReaderMTOMAware
		 * @return true if the reader supports MTOM
		 */
		public static boolean isReaderMTOMAware(javax.xml.stream.XMLStreamReader reader) {
			boolean isReaderMTOMAware = false;

			try {
				isReaderMTOMAware = java.lang.Boolean.TRUE.equals(reader.getProperty(org.apache.axiom.om.OMConstants.IS_DATA_HANDLERS_AWARE));
			} catch (java.lang.IllegalArgumentException e) {
				isReaderMTOMAware = false;
			}
			return isReaderMTOMAware;
		}

		/**
		 * @param parentQName
		 * @param factory
		 * @return org.apache.axiom.om.OMElement
		 */
		public org.apache.axiom.om.OMElement getOMElement(final javax.xml.namespace.QName parentQName, final org.apache.axiom.om.OMFactory factory) throws org.apache.axis2.databinding.ADBException {

			org.apache.axiom.om.OMDataSource dataSource = new org.apache.axis2.databinding.ADBDataSource(this, parentQName) {

				public void serialize(org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
					FMT_SOAP_UTF8_ResTranHeader.this.serialize(parentQName, factory, xmlWriter);
				}
			};
			return new org.apache.axiom.om.impl.llom.OMSourcedElementImpl(parentQName, factory, dataSource);

		}

		public void serialize(final javax.xml.namespace.QName parentQName, final org.apache.axiom.om.OMFactory factory, org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {

			java.lang.String prefix = null;
			java.lang.String namespace = null;

			prefix = parentQName.getPrefix();
			namespace = parentQName.getNamespaceURI();

			if (namespace != null) {
				java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
				if (writerPrefix != null) {
					xmlWriter.writeStartElement(namespace, parentQName.getLocalPart());
				} else {
					if (prefix == null) {
						prefix = generatePrefix(namespace);
					}

					xmlWriter.writeStartElement(prefix, parentQName.getLocalPart(), namespace);
					xmlWriter.writeNamespace(prefix, namespace);
					xmlWriter.setPrefix(prefix, namespace);
				}
			} else {
				xmlWriter.writeStartElement(parentQName.getLocalPart());
			}
			if (localHSecFlagTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "HSecFlag", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "HSecFlag");
					}

				} else {
					xmlWriter.writeStartElement("HSecFlag");
				}

				if (localHSecFlag == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("HSecFlag cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localHSecFlag);

				}

				xmlWriter.writeEndElement();
			}
			if (localHCombFlagTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "HCombFlag", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "HCombFlag");
					}

				} else {
					xmlWriter.writeStartElement("HCombFlag");
				}

				if (localHCombFlag == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("HCombFlag cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localHCombFlag);

				}

				xmlWriter.writeEndElement();
			}
			if (localHSvcInfoTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "HSvcInfo", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "HSvcInfo");
					}

				} else {
					xmlWriter.writeStartElement("HSvcInfo");
				}

				if (localHSvcInfo == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("HSvcInfo cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localHSvcInfo);

				}

				xmlWriter.writeEndElement();
			}
			if (localHSecInfoVerNoTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "HSecInfoVerNo", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "HSecInfoVerNo");
					}

				} else {
					xmlWriter.writeStartElement("HSecInfoVerNo");
				}

				if (localHSecInfoVerNo == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("HSecInfoVerNo cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localHSecInfoVerNo);

				}

				xmlWriter.writeEndElement();
			}
			if (localHMsgRefNoTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "HMsgRefNo", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "HMsgRefNo");
					}

				} else {
					xmlWriter.writeStartElement("HMsgRefNo");
				}

				if (localHMsgRefNo == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("HMsgRefNo cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localHMsgRefNo);

				}

				xmlWriter.writeEndElement();
			}
			if (localHIdentFlagTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "HIdentFlag", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "HIdentFlag");
					}

				} else {
					xmlWriter.writeStartElement("HIdentFlag");
				}

				if (localHIdentFlag == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("HIdentFlag cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localHIdentFlag);

				}

				xmlWriter.writeEndElement();
			}
			if (localHSuperFlagTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "HSuperFlag", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "HSuperFlag");
					}

				} else {
					xmlWriter.writeStartElement("HSuperFlag");
				}

				if (localHSuperFlag == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("HSuperFlag cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localHSuperFlag);

				}

				xmlWriter.writeEndElement();
			}
			if (localHChkFlagTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "HChkFlag", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "HChkFlag");
					}

				} else {
					xmlWriter.writeStartElement("HChkFlag");
				}

				if (localHChkFlag == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("HChkFlag cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localHChkFlag);

				}

				xmlWriter.writeEndElement();
			}
			if (localHChkTxnCdTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "HChkTxnCd", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "HChkTxnCd");
					}

				} else {
					xmlWriter.writeStartElement("HChkTxnCd");
				}

				if (localHChkTxnCd == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("HChkTxnCd cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localHChkTxnCd);

				}

				xmlWriter.writeEndElement();
			}
			if (localHVerfCdTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "HVerfCd", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "HVerfCd");
					}

				} else {
					xmlWriter.writeStartElement("HVerfCd");
				}

				if (localHVerfCd == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("HVerfCd cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localHVerfCd);

				}

				xmlWriter.writeEndElement();
			}
			if (localHTranResTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "HTranRes", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "HTranRes");
					}

				} else {
					xmlWriter.writeStartElement("HTranRes");
				}

				if (localHTranRes == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("HTranRes cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localHTranRes);

				}

				xmlWriter.writeEndElement();
			}
			if (localHRefTxnCdTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "HRefTxnCd", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "HRefTxnCd");
					}

				} else {
					xmlWriter.writeStartElement("HRefTxnCd");
				}

				if (localHRefTxnCd == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("HRefTxnCd cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localHRefTxnCd);

				}

				xmlWriter.writeEndElement();
			}
			if (localHServerDtTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "HServerDt", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "HServerDt");
					}

				} else {
					xmlWriter.writeStartElement("HServerDt");
				}

				if (localHServerDt == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("HServerDt cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localHServerDt);

				}

				xmlWriter.writeEndElement();
			}
			if (localHServerTmTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "HServerTm", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "HServerTm");
					}

				} else {
					xmlWriter.writeStartElement("HServerTm");
				}

				if (localHServerTm == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("HServerTm cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localHServerTm);

				}

				xmlWriter.writeEndElement();
			}
			if (localHServerSeqTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "HServerSeq", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "HServerSeq");
					}

				} else {
					xmlWriter.writeStartElement("HServerSeq");
				}

				if (localHServerSeq == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("HServerSeq cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localHServerSeq);

				}

				xmlWriter.writeEndElement();
			}
			if (localHAcountDtTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "HAcountDt", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "HAcountDt");
					}

				} else {
					xmlWriter.writeStartElement("HAcountDt");
				}

				if (localHAcountDt == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("HAcountDt cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localHAcountDt);

				}

				xmlWriter.writeEndElement();
			}
			if (localHRefSeqTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "HRefSeq", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "HRefSeq");
					}

				} else {
					xmlWriter.writeStartElement("HRefSeq");
				}

				if (localHRefSeq == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("HRefSeq cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localHRefSeq);

				}

				xmlWriter.writeEndElement();
			}
			if (localHRefDtTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "HRefDt", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "HRefDt");
					}

				} else {
					xmlWriter.writeStartElement("HRefDt");
				}

				if (localHRefDt == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("HRefDt cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localHRefDt);

				}

				xmlWriter.writeEndElement();
			}
			if (localHNextStepTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "HNextStep", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "HNextStep");
					}

				} else {
					xmlWriter.writeStartElement("HNextStep");
				}

				if (localHNextStep == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("HNextStep cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localHNextStep);

				}

				xmlWriter.writeEndElement();
			}
			if (localHVchChkTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "HVchChk", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "HVchChk");
					}

				} else {
					xmlWriter.writeStartElement("HVchChk");
				}

				if (localHVchChk == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("HVchChk cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localHVchChk);

				}

				xmlWriter.writeEndElement();
			}
			if (localHRetResInfoTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "HRetResInfo", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "HRetResInfo");
					}

				} else {
					xmlWriter.writeStartElement("HRetResInfo");
				}

				if (localHRetResInfo == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("HRetResInfo cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localHRetResInfo);

				}

				xmlWriter.writeEndElement();
			}
			if (localHErrTranNoTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "HErrTranNo", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "HErrTranNo");
					}

				} else {
					xmlWriter.writeStartElement("HErrTranNo");
				}

				if (localHErrTranNo == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("HErrTranNo cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localHErrTranNo);

				}

				xmlWriter.writeEndElement();
			}
			if (localHAssiInfoTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "HAssiInfo", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "HAssiInfo");
					}

				} else {
					xmlWriter.writeStartElement("HAssiInfo");
				}

				if (localHAssiInfo == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("HAssiInfo cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localHAssiInfo);

				}

				xmlWriter.writeEndElement();
			}
			if (localHRetCodeTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "HRetCode", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "HRetCode");
					}

				} else {
					xmlWriter.writeStartElement("HRetCode");
				}

				if (localHRetCode == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("HRetCode cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localHRetCode);

				}

				xmlWriter.writeEndElement();
			}
			if (localHRetNoTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "HRetNo", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "HRetNo");
					}

				} else {
					xmlWriter.writeStartElement("HRetNo");
				}

				if (localHRetNo == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("HRetNo cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localHRetNo);

				}

				xmlWriter.writeEndElement();
			}
			if (localHRetMsgTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "HRetMsg", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "HRetMsg");
					}

				} else {
					xmlWriter.writeStartElement("HRetMsg");
				}

				if (localHRetMsg == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("HRetMsg cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localHRetMsg);

				}

				xmlWriter.writeEndElement();
			}
			if (localHWarnMsgTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "HWarnMsg", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "HWarnMsg");
					}

				} else {
					xmlWriter.writeStartElement("HWarnMsg");
				}

				if (localHWarnMsg == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("HWarnMsg cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localHWarnMsg);

				}

				xmlWriter.writeEndElement();
			}
			xmlWriter.writeEndElement();

		}

		/**
		 * Util method to write an attribute with the ns prefix
		 */
		private void writeAttribute(java.lang.String prefix, java.lang.String namespace, java.lang.String attName, java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			if (xmlWriter.getPrefix(namespace) == null) {
				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);

			}

			xmlWriter.writeAttribute(namespace, attName, attValue);

		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeAttribute(java.lang.String namespace, java.lang.String attName, java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			if (namespace.equals("")) {
				xmlWriter.writeAttribute(attName, attValue);
			} else {
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attValue);
			}
		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeQNameAttribute(java.lang.String namespace, java.lang.String attName, javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {

			java.lang.String attributeNamespace = qname.getNamespaceURI();
			java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
			if (attributePrefix == null) {
				attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
			}
			java.lang.String attributeValue;
			if (attributePrefix.trim().length() > 0) {
				attributeValue = attributePrefix + ":" + qname.getLocalPart();
			} else {
				attributeValue = qname.getLocalPart();
			}

			if (namespace.equals("")) {
				xmlWriter.writeAttribute(attName, attributeValue);
			} else {
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attributeValue);
			}
		}

		/**
		 * method to handle Qnames
		 */

		private void writeQName(javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
			java.lang.String namespaceURI = qname.getNamespaceURI();
			if (namespaceURI != null) {
				java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
				if (prefix == null) {
					prefix = generatePrefix(namespaceURI);
					xmlWriter.writeNamespace(prefix, namespaceURI);
					xmlWriter.setPrefix(prefix, namespaceURI);
				}

				if (prefix.trim().length() > 0) {
					xmlWriter.writeCharacters(prefix + ":" + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
				} else {
					// i.e this is the default namespace
					xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
				}

			} else {
				xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
			}
		}

		private void writeQNames(javax.xml.namespace.QName[] qnames, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

			if (qnames != null) {
				// we have to store this data until last moment since it is not possible to write any
				// namespace data after writing the charactor data
				java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
				java.lang.String namespaceURI = null;
				java.lang.String prefix = null;

				for (int i = 0; i < qnames.length; i++) {
					if (i > 0) {
						stringToWrite.append(" ");
					}
					namespaceURI = qnames[i].getNamespaceURI();
					if (namespaceURI != null) {
						prefix = xmlWriter.getPrefix(namespaceURI);
						if ((prefix == null) || (prefix.length() == 0)) {
							prefix = generatePrefix(namespaceURI);
							xmlWriter.writeNamespace(prefix, namespaceURI);
							xmlWriter.setPrefix(prefix, namespaceURI);
						}

						if (prefix.trim().length() > 0) {
							stringToWrite.append(prefix).append(":").append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
						} else {
							stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
						}
					} else {
						stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
					}
				}
				xmlWriter.writeCharacters(stringToWrite.toString());
			}

		}

		/**
		 * Register a namespace prefix
		 */
		private java.lang.String registerPrefix(javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace) throws javax.xml.stream.XMLStreamException {
			java.lang.String prefix = xmlWriter.getPrefix(namespace);

			if (prefix == null) {
				prefix = generatePrefix(namespace);

				while (xmlWriter.getNamespaceContext().getNamespaceURI(prefix) != null) {
					prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
				}

				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);
			}

			return prefix;
		}

		/**
		 * databinding method to get an XML representation of this object
		 */
		public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName) throws org.apache.axis2.databinding.ADBException {

			java.util.ArrayList elementList = new java.util.ArrayList();
			java.util.ArrayList attribList = new java.util.ArrayList();

			if (localHSecFlagTracker) {
				elementList.add(new javax.xml.namespace.QName("", "HSecFlag"));

				if (localHSecFlag != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHSecFlag));
				} else {
					throw new org.apache.axis2.databinding.ADBException("HSecFlag cannot be null!!");
				}
			}
			if (localHCombFlagTracker) {
				elementList.add(new javax.xml.namespace.QName("", "HCombFlag"));

				if (localHCombFlag != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHCombFlag));
				} else {
					throw new org.apache.axis2.databinding.ADBException("HCombFlag cannot be null!!");
				}
			}
			if (localHSvcInfoTracker) {
				elementList.add(new javax.xml.namespace.QName("", "HSvcInfo"));

				if (localHSvcInfo != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHSvcInfo));
				} else {
					throw new org.apache.axis2.databinding.ADBException("HSvcInfo cannot be null!!");
				}
			}
			if (localHSecInfoVerNoTracker) {
				elementList.add(new javax.xml.namespace.QName("", "HSecInfoVerNo"));

				if (localHSecInfoVerNo != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHSecInfoVerNo));
				} else {
					throw new org.apache.axis2.databinding.ADBException("HSecInfoVerNo cannot be null!!");
				}
			}
			if (localHMsgRefNoTracker) {
				elementList.add(new javax.xml.namespace.QName("", "HMsgRefNo"));

				if (localHMsgRefNo != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHMsgRefNo));
				} else {
					throw new org.apache.axis2.databinding.ADBException("HMsgRefNo cannot be null!!");
				}
			}
			if (localHIdentFlagTracker) {
				elementList.add(new javax.xml.namespace.QName("", "HIdentFlag"));

				if (localHIdentFlag != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHIdentFlag));
				} else {
					throw new org.apache.axis2.databinding.ADBException("HIdentFlag cannot be null!!");
				}
			}
			if (localHSuperFlagTracker) {
				elementList.add(new javax.xml.namespace.QName("", "HSuperFlag"));

				if (localHSuperFlag != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHSuperFlag));
				} else {
					throw new org.apache.axis2.databinding.ADBException("HSuperFlag cannot be null!!");
				}
			}
			if (localHChkFlagTracker) {
				elementList.add(new javax.xml.namespace.QName("", "HChkFlag"));

				if (localHChkFlag != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHChkFlag));
				} else {
					throw new org.apache.axis2.databinding.ADBException("HChkFlag cannot be null!!");
				}
			}
			if (localHChkTxnCdTracker) {
				elementList.add(new javax.xml.namespace.QName("", "HChkTxnCd"));

				if (localHChkTxnCd != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHChkTxnCd));
				} else {
					throw new org.apache.axis2.databinding.ADBException("HChkTxnCd cannot be null!!");
				}
			}
			if (localHVerfCdTracker) {
				elementList.add(new javax.xml.namespace.QName("", "HVerfCd"));

				if (localHVerfCd != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHVerfCd));
				} else {
					throw new org.apache.axis2.databinding.ADBException("HVerfCd cannot be null!!");
				}
			}
			if (localHTranResTracker) {
				elementList.add(new javax.xml.namespace.QName("", "HTranRes"));

				if (localHTranRes != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHTranRes));
				} else {
					throw new org.apache.axis2.databinding.ADBException("HTranRes cannot be null!!");
				}
			}
			if (localHRefTxnCdTracker) {
				elementList.add(new javax.xml.namespace.QName("", "HRefTxnCd"));

				if (localHRefTxnCd != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHRefTxnCd));
				} else {
					throw new org.apache.axis2.databinding.ADBException("HRefTxnCd cannot be null!!");
				}
			}
			if (localHServerDtTracker) {
				elementList.add(new javax.xml.namespace.QName("", "HServerDt"));

				if (localHServerDt != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHServerDt));
				} else {
					throw new org.apache.axis2.databinding.ADBException("HServerDt cannot be null!!");
				}
			}
			if (localHServerTmTracker) {
				elementList.add(new javax.xml.namespace.QName("", "HServerTm"));

				if (localHServerTm != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHServerTm));
				} else {
					throw new org.apache.axis2.databinding.ADBException("HServerTm cannot be null!!");
				}
			}
			if (localHServerSeqTracker) {
				elementList.add(new javax.xml.namespace.QName("", "HServerSeq"));

				if (localHServerSeq != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHServerSeq));
				} else {
					throw new org.apache.axis2.databinding.ADBException("HServerSeq cannot be null!!");
				}
			}
			if (localHAcountDtTracker) {
				elementList.add(new javax.xml.namespace.QName("", "HAcountDt"));

				if (localHAcountDt != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHAcountDt));
				} else {
					throw new org.apache.axis2.databinding.ADBException("HAcountDt cannot be null!!");
				}
			}
			if (localHRefSeqTracker) {
				elementList.add(new javax.xml.namespace.QName("", "HRefSeq"));

				if (localHRefSeq != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHRefSeq));
				} else {
					throw new org.apache.axis2.databinding.ADBException("HRefSeq cannot be null!!");
				}
			}
			if (localHRefDtTracker) {
				elementList.add(new javax.xml.namespace.QName("", "HRefDt"));

				if (localHRefDt != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHRefDt));
				} else {
					throw new org.apache.axis2.databinding.ADBException("HRefDt cannot be null!!");
				}
			}
			if (localHNextStepTracker) {
				elementList.add(new javax.xml.namespace.QName("", "HNextStep"));

				if (localHNextStep != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHNextStep));
				} else {
					throw new org.apache.axis2.databinding.ADBException("HNextStep cannot be null!!");
				}
			}
			if (localHVchChkTracker) {
				elementList.add(new javax.xml.namespace.QName("", "HVchChk"));

				if (localHVchChk != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHVchChk));
				} else {
					throw new org.apache.axis2.databinding.ADBException("HVchChk cannot be null!!");
				}
			}
			if (localHRetResInfoTracker) {
				elementList.add(new javax.xml.namespace.QName("", "HRetResInfo"));

				if (localHRetResInfo != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHRetResInfo));
				} else {
					throw new org.apache.axis2.databinding.ADBException("HRetResInfo cannot be null!!");
				}
			}
			if (localHErrTranNoTracker) {
				elementList.add(new javax.xml.namespace.QName("", "HErrTranNo"));

				if (localHErrTranNo != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHErrTranNo));
				} else {
					throw new org.apache.axis2.databinding.ADBException("HErrTranNo cannot be null!!");
				}
			}
			if (localHAssiInfoTracker) {
				elementList.add(new javax.xml.namespace.QName("", "HAssiInfo"));

				if (localHAssiInfo != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHAssiInfo));
				} else {
					throw new org.apache.axis2.databinding.ADBException("HAssiInfo cannot be null!!");
				}
			}
			if (localHRetCodeTracker) {
				elementList.add(new javax.xml.namespace.QName("", "HRetCode"));

				if (localHRetCode != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHRetCode));
				} else {
					throw new org.apache.axis2.databinding.ADBException("HRetCode cannot be null!!");
				}
			}
			if (localHRetNoTracker) {
				elementList.add(new javax.xml.namespace.QName("", "HRetNo"));

				if (localHRetNo != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHRetNo));
				} else {
					throw new org.apache.axis2.databinding.ADBException("HRetNo cannot be null!!");
				}
			}
			if (localHRetMsgTracker) {
				elementList.add(new javax.xml.namespace.QName("", "HRetMsg"));

				if (localHRetMsg != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHRetMsg));
				} else {
					throw new org.apache.axis2.databinding.ADBException("HRetMsg cannot be null!!");
				}
			}
			if (localHWarnMsgTracker) {
				elementList.add(new javax.xml.namespace.QName("", "HWarnMsg"));

				if (localHWarnMsg != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHWarnMsg));
				} else {
					throw new org.apache.axis2.databinding.ADBException("HWarnMsg cannot be null!!");
				}
			}

			return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName, elementList.toArray(), attribList.toArray());

		}

		/**
		 * Factory class that keeps the parse method
		 */
		public static class Factory {

			/**
			 * static method to create the object
			 * Precondition: If this object is an element, the current or next start element starts this object and any intervening reader events are
			 * ignorable
			 * If this object is not an element, it is a complex type and the reader is at the event just after the outer start element
			 * Postcondition: If this object is an element, the reader is positioned at its end element
			 * If this object is a complex type, the reader is positioned at the end element of its outer element
			 */
			public static FMT_SOAP_UTF8_ResTranHeader parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception {
				FMT_SOAP_UTF8_ResTranHeader object = new FMT_SOAP_UTF8_ResTranHeader();

				int event;
				java.lang.String nillableValue = null;
				java.lang.String prefix = "";
				java.lang.String namespaceuri = "";
				try {

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "type") != null) {
						java.lang.String fullTypeName = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "type");
						if (fullTypeName != null) {
							java.lang.String nsPrefix = null;
							if (fullTypeName.indexOf(":") > -1) {
								nsPrefix = fullTypeName.substring(0, fullTypeName.indexOf(":"));
							}
							nsPrefix = nsPrefix == null ? "" : nsPrefix;

							java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":") + 1);

							if (!"FMT_SOAP_UTF8_ResTranHeader".equals(type)) {
								// find namespace for the prefix
								java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
								return (FMT_SOAP_UTF8_ResTranHeader) ExtensionMapper.getTypeObject(nsUri, type, reader);
							}

						}

					}

					// Note all attributes that were handled. Used to differ normal attributes
					// from anyAttributes.
					java.util.Vector handledAttributes = new java.util.Vector();

					reader.next();

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "HSecFlag").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setHSecFlag(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "HCombFlag").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setHCombFlag(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "HSvcInfo").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setHSvcInfo(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "HSecInfoVerNo").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setHSecInfoVerNo(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "HMsgRefNo").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setHMsgRefNo(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "HIdentFlag").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setHIdentFlag(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "HSuperFlag").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setHSuperFlag(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "HChkFlag").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setHChkFlag(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "HChkTxnCd").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setHChkTxnCd(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "HVerfCd").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setHVerfCd(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "HTranRes").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setHTranRes(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "HRefTxnCd").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setHRefTxnCd(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "HServerDt").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setHServerDt(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "HServerTm").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setHServerTm(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "HServerSeq").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setHServerSeq(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "HAcountDt").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setHAcountDt(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "HRefSeq").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setHRefSeq(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "HRefDt").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setHRefDt(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "HNextStep").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setHNextStep(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "HVchChk").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setHVchChk(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "HRetResInfo").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setHRetResInfo(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "HErrTranNo").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setHErrTranNo(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "HAssiInfo").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setHAssiInfo(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "HRetCode").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setHRetCode(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "HRetNo").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setHRetNo(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "HRetMsg").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setHRetMsg(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "HWarnMsg").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setHWarnMsg(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement())
						// A start element we are not expecting indicates a trailing invalid property
						throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());

				} catch (javax.xml.stream.XMLStreamException e) {
					throw new java.lang.Exception(e);
				}

				return object;
			}

		}// end of factory class

	}

	public static class FMT_CRMS_SVR_S01001010021003_IN_SUB implements org.apache.axis2.databinding.ADBBean {
		/*
		 * This type was generated from the piece of schema that had
		 * name = FMT_CRMS_SVR_S01001010021003_IN_SUB
		 * Namespace URI = http://www.adtec.com.cn
		 * Namespace Prefix = ns1
		 */

		private static java.lang.String generatePrefix(java.lang.String namespace) {
			if (namespace.equals("http://www.adtec.com.cn")) {
				return "ns1";
			}
			return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
		}

		/**
		 * field for PromNoteNo
		 */

		protected java.lang.String localPromNoteNo;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localPromNoteNoTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getPromNoteNo() {
			return localPromNoteNo;
		}

		/**
		 * Auto generated setter method
		 * @param param PromNoteNo
		 */
		public void setPromNoteNo(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localPromNoteNoTracker = true;
			} else {
				localPromNoteNoTracker = false;

			}

			this.localPromNoteNo = param;

		}

		/**
		 * field for BillNo
		 */

		protected java.lang.String localBillNo;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localBillNoTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getBillNo() {
			return localBillNo;
		}

		/**
		 * Auto generated setter method
		 * @param param BillNo
		 */
		public void setBillNo(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localBillNoTracker = true;
			} else {
				localBillNoTracker = false;

			}

			this.localBillNo = param;

		}

		/**
		 * field for BillMoney
		 */

		protected java.lang.String localBillMoney;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localBillMoneyTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getBillMoney() {
			return localBillMoney;
		}

		/**
		 * Auto generated setter method
		 * @param param BillMoney
		 */
		public void setBillMoney(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localBillMoneyTracker = true;
			} else {
				localBillMoneyTracker = false;

			}

			this.localBillMoney = param;

		}

		/**
		 * field for RemitterCustNo
		 */

		protected java.lang.String localRemitterCustNo;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localRemitterCustNoTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getRemitterCustNo() {
			return localRemitterCustNo;
		}

		/**
		 * Auto generated setter method
		 * @param param RemitterCustNo
		 */
		public void setRemitterCustNo(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localRemitterCustNoTracker = true;
			} else {
				localRemitterCustNoTracker = false;

			}

			this.localRemitterCustNo = param;

		}

		/**
		 * field for RemitterAcctNo
		 */

		protected java.lang.String localRemitterAcctNo;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localRemitterAcctNoTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getRemitterAcctNo() {
			return localRemitterAcctNo;
		}

		/**
		 * Auto generated setter method
		 * @param param RemitterAcctNo
		 */
		public void setRemitterAcctNo(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localRemitterAcctNoTracker = true;
			} else {
				localRemitterAcctNoTracker = false;

			}

			this.localRemitterAcctNo = param;

		}

		/**
		 * field for Remitter
		 */

		protected java.lang.String localRemitter;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localRemitterTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getRemitter() {
			return localRemitter;
		}

		/**
		 * Auto generated setter method
		 * @param param Remitter
		 */
		public void setRemitter(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localRemitterTracker = true;
			} else {
				localRemitterTracker = false;

			}

			this.localRemitter = param;

		}

		/**
		 * field for RemitterBankNo
		 */

		protected java.lang.String localRemitterBankNo;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localRemitterBankNoTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getRemitterBankNo() {
			return localRemitterBankNo;
		}

		/**
		 * Auto generated setter method
		 * @param param RemitterBankNo
		 */
		public void setRemitterBankNo(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localRemitterBankNoTracker = true;
			} else {
				localRemitterBankNoTracker = false;

			}

			this.localRemitterBankNo = param;

		}

		/**
		 * field for RemitterBankName
		 */

		protected java.lang.String localRemitterBankName;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localRemitterBankNameTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getRemitterBankName() {
			return localRemitterBankName;
		}

		/**
		 * Auto generated setter method
		 * @param param RemitterBankName
		 */
		public void setRemitterBankName(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localRemitterBankNameTracker = true;
			} else {
				localRemitterBankNameTracker = false;

			}

			this.localRemitterBankName = param;

		}

		/**
		 * field for DraweeBankNo
		 */

		protected java.lang.String localDraweeBankNo;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localDraweeBankNoTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getDraweeBankNo() {
			return localDraweeBankNo;
		}

		/**
		 * Auto generated setter method
		 * @param param DraweeBankNo
		 */
		public void setDraweeBankNo(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localDraweeBankNoTracker = true;
			} else {
				localDraweeBankNoTracker = false;

			}

			this.localDraweeBankNo = param;

		}

		/**
		 * field for DraweeBankName
		 */

		protected java.lang.String localDraweeBankName;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localDraweeBankNameTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getDraweeBankName() {
			return localDraweeBankName;
		}

		/**
		 * Auto generated setter method
		 * @param param DraweeBankName
		 */
		public void setDraweeBankName(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localDraweeBankNameTracker = true;
			} else {
				localDraweeBankNameTracker = false;

			}

			this.localDraweeBankName = param;

		}

		/**
		 * field for DraweeBankAddr
		 */

		protected java.lang.String localDraweeBankAddr;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localDraweeBankAddrTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getDraweeBankAddr() {
			return localDraweeBankAddr;
		}

		/**
		 * Auto generated setter method
		 * @param param DraweeBankAddr
		 */
		public void setDraweeBankAddr(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localDraweeBankAddrTracker = true;
			} else {
				localDraweeBankAddrTracker = false;

			}

			this.localDraweeBankAddr = param;

		}

		/**
		 * field for PayeeAcctNo
		 */

		protected java.lang.String localPayeeAcctNo;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localPayeeAcctNoTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getPayeeAcctNo() {
			return localPayeeAcctNo;
		}

		/**
		 * Auto generated setter method
		 * @param param PayeeAcctNo
		 */
		public void setPayeeAcctNo(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localPayeeAcctNoTracker = true;
			} else {
				localPayeeAcctNoTracker = false;

			}

			this.localPayeeAcctNo = param;

		}

		/**
		 * field for Payee
		 */

		protected java.lang.String localPayee;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localPayeeTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getPayee() {
			return localPayee;
		}

		/**
		 * Auto generated setter method
		 * @param param Payee
		 */
		public void setPayee(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localPayeeTracker = true;
			} else {
				localPayeeTracker = false;

			}

			this.localPayee = param;

		}

		/**
		 * field for PayeeBankName
		 */

		protected java.lang.String localPayeeBankName;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localPayeeBankNameTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getPayeeBankName() {
			return localPayeeBankName;
		}

		/**
		 * Auto generated setter method
		 * @param param PayeeBankName
		 */
		public void setPayeeBankName(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localPayeeBankNameTracker = true;
			} else {
				localPayeeBankNameTracker = false;

			}

			this.localPayeeBankName = param;

		}

		/**
		 * field for PayeeBankNo
		 */

		protected java.lang.String localPayeeBankNo;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localPayeeBankNoTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getPayeeBankNo() {
			return localPayeeBankNo;
		}

		/**
		 * Auto generated setter method
		 * @param param PayeeBankNo
		 */
		public void setPayeeBankNo(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localPayeeBankNoTracker = true;
			} else {
				localPayeeBankNoTracker = false;

			}

			this.localPayeeBankNo = param;

		}

		/**
		 * field for AcptDt
		 */

		protected java.lang.String localAcptDt;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localAcptDtTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getAcptDt() {
			return localAcptDt;
		}

		/**
		 * Auto generated setter method
		 * @param param AcptDt
		 */
		public void setAcptDt(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localAcptDtTracker = true;
			} else {
				localAcptDtTracker = false;

			}

			this.localAcptDt = param;

		}

		/**
		 * field for DueDt
		 */

		protected java.lang.String localDueDt;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localDueDtTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getDueDt() {
			return localDueDt;
		}

		/**
		 * Auto generated setter method
		 * @param param DueDt
		 */
		public void setDueDt(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localDueDtTracker = true;
			} else {
				localDueDtTracker = false;

			}

			this.localDueDt = param;

		}

		/**
		 * field for AcceptorAcctNo
		 */

		protected java.lang.String localAcceptorAcctNo;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localAcceptorAcctNoTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getAcceptorAcctNo() {
			return localAcceptorAcctNo;
		}

		/**
		 * Auto generated setter method
		 * @param param AcceptorAcctNo
		 */
		public void setAcceptorAcctNo(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localAcceptorAcctNoTracker = true;
			} else {
				localAcceptorAcctNoTracker = false;

			}

			this.localAcceptorAcctNo = param;

		}

		/**
		 * field for Acceptor
		 */

		protected java.lang.String localAcceptor;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localAcceptorTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getAcceptor() {
			return localAcceptor;
		}

		/**
		 * Auto generated setter method
		 * @param param Acceptor
		 */
		public void setAcceptor(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localAcceptorTracker = true;
			} else {
				localAcceptorTracker = false;

			}

			this.localAcceptor = param;

		}

		/**
		 * field for AcceptorBankNo
		 */

		protected java.lang.String localAcceptorBankNo;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localAcceptorBankNoTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getAcceptorBankNo() {
			return localAcceptorBankNo;
		}

		/**
		 * Auto generated setter method
		 * @param param AcceptorBankNo
		 */
		public void setAcceptorBankNo(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localAcceptorBankNoTracker = true;
			} else {
				localAcceptorBankNoTracker = false;

			}

			this.localAcceptorBankNo = param;

		}

		/**
		 * field for IsAccp
		 */

		protected java.lang.String localIsAccp;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localIsAccpTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getIsAccp() {
			return localIsAccp;
		}

		/**
		 * Auto generated setter method
		 * @param param IsAccp
		 */
		public void setIsAccp(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localIsAccpTracker = true;
			} else {
				localIsAccpTracker = false;

			}

			this.localIsAccp = param;

		}

		/**
		 * field for IfSameCity
		 */

		protected java.lang.String localIfSameCity;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localIfSameCityTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getIfSameCity() {
			return localIfSameCity;
		}

		/**
		 * Auto generated setter method
		 * @param param IfSameCity
		 */
		public void setIfSameCity(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localIfSameCityTracker = true;
			} else {
				localIfSameCityTracker = false;

			}

			this.localIfSameCity = param;

		}

		/**
		 * field for ForbidFlag
		 */

		protected java.lang.String localForbidFlag;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localForbidFlagTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getForbidFlag() {
			return localForbidFlag;
		}

		/**
		 * Auto generated setter method
		 * @param param ForbidFlag
		 */
		public void setForbidFlag(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localForbidFlagTracker = true;
			} else {
				localForbidFlagTracker = false;

			}

			this.localForbidFlag = param;

		}

		/**
		 * field for DelayDays
		 */

		protected java.lang.String localDelayDays;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localDelayDaysTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getDelayDays() {
			return localDelayDays;
		}

		/**
		 * Auto generated setter method
		 * @param param DelayDays
		 */
		public void setDelayDays(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localDelayDaysTracker = true;
			} else {
				localDelayDaysTracker = false;

			}

			this.localDelayDays = param;

		}

		/**
		 * field for InterestCalDays
		 */

		protected java.lang.String localInterestCalDays;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localInterestCalDaysTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getInterestCalDays() {
			return localInterestCalDays;
		}

		/**
		 * Auto generated setter method
		 * @param param InterestCalDays
		 */
		public void setInterestCalDays(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localInterestCalDaysTracker = true;
			} else {
				localInterestCalDaysTracker = false;

			}

			this.localInterestCalDays = param;

		}

		/**
		 * field for Interest
		 */

		protected java.lang.String localInterest;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localInterestTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getInterest() {
			return localInterest;
		}

		/**
		 * Auto generated setter method
		 * @param param Interest
		 */
		public void setInterest(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localInterestTracker = true;
			} else {
				localInterestTracker = false;

			}

			this.localInterest = param;

		}

		/**
		 * field for SalerInterest
		 */

		protected java.lang.String localSalerInterest;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localSalerInterestTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getSalerInterest() {
			return localSalerInterest;
		}

		/**
		 * Auto generated setter method
		 * @param param SalerInterest
		 */
		public void setSalerInterest(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localSalerInterestTracker = true;
			} else {
				localSalerInterestTracker = false;

			}

			this.localSalerInterest = param;

		}

		/**
		 * field for BuyerInterest
		 */

		protected java.lang.String localBuyerInterest;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localBuyerInterestTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getBuyerInterest() {
			return localBuyerInterest;
		}

		/**
		 * Auto generated setter method
		 * @param param BuyerInterest
		 */
		public void setBuyerInterest(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localBuyerInterestTracker = true;
			} else {
				localBuyerInterestTracker = false;

			}

			this.localBuyerInterest = param;

		}

		/**
		 * field for ThridInterest
		 */

		protected java.lang.String localThridInterest;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localThridInterestTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getThridInterest() {
			return localThridInterest;
		}

		/**
		 * Auto generated setter method
		 * @param param ThridInterest
		 */
		public void setThridInterest(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localThridInterestTracker = true;
			} else {
				localThridInterestTracker = false;

			}

			this.localThridInterest = param;

		}

		/**
		 * field for PayMoney
		 */

		protected java.lang.String localPayMoney;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localPayMoneyTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getPayMoney() {
			return localPayMoney;
		}

		/**
		 * Auto generated setter method
		 * @param param PayMoney
		 */
		public void setPayMoney(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localPayMoneyTracker = true;
			} else {
				localPayMoneyTracker = false;

			}

			this.localPayMoney = param;

		}

		/**
		 * field for Remarks
		 */

		protected java.lang.String localRemarks;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localRemarksTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getRemarks() {
			return localRemarks;
		}

		/**
		 * Auto generated setter method
		 * @param param Remarks
		 */
		public void setRemarks(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localRemarksTracker = true;
			} else {
				localRemarksTracker = false;

			}

			this.localRemarks = param;

		}

		/**
		 * field for InqRepFlag
		 */

		protected java.lang.String localInqRepFlag;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localInqRepFlagTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getInqRepFlag() {
			return localInqRepFlag;
		}

		/**
		 * Auto generated setter method
		 * @param param InqRepFlag
		 */
		public void setInqRepFlag(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localInqRepFlagTracker = true;
			} else {
				localInqRepFlagTracker = false;

			}

			this.localInqRepFlag = param;

		}

		/**
		 * field for InqRepType
		 */

		protected java.lang.String localInqRepType;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localInqRepTypeTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getInqRepType() {
			return localInqRepType;
		}

		/**
		 * Auto generated setter method
		 * @param param InqRepType
		 */
		public void setInqRepType(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localInqRepTypeTracker = true;
			} else {
				localInqRepTypeTracker = false;

			}

			this.localInqRepType = param;

		}

		/**
		 * field for AcceptorBankName
		 */

		protected java.lang.String localAcceptorBankName;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localAcceptorBankNameTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getAcceptorBankName() {
			return localAcceptorBankName;
		}

		/**
		 * Auto generated setter method
		 * @param param AcceptorBankName
		 */
		public void setAcceptorBankName(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localAcceptorBankNameTracker = true;
			} else {
				localAcceptorBankNameTracker = false;

			}

			this.localAcceptorBankName = param;

		}

		/**
		 * field for PutoutNo
		 */

		protected java.lang.String localPutoutNo;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localPutoutNoTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getPutoutNo() {
			return localPutoutNo;
		}

		/**
		 * Auto generated setter method
		 * @param param PutoutNo
		 */
		public void setPutoutNo(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localPutoutNoTracker = true;
			} else {
				localPutoutNoTracker = false;

			}

			this.localPutoutNo = param;

		}

		/**
		 * field for Bailsum
		 */

		protected java.lang.String localBailsum;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localBailsumTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getBailsum() {
			return localBailsum;
		}

		/**
		 * Auto generated setter method
		 * @param param Bailsum
		 */
		public void setBailsum(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localBailsumTracker = true;
			} else {
				localBailsumTracker = false;

			}

			this.localBailsum = param;

		}

		/**
		 * field for Bailacurrency
		 */

		protected java.lang.String localBailacurrency;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localBailacurrencyTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getBailacurrency() {
			return localBailacurrency;
		}

		/**
		 * Auto generated setter method
		 * @param param Bailacurrency
		 */
		public void setBailacurrency(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localBailacurrencyTracker = true;
			} else {
				localBailacurrencyTracker = false;

			}

			this.localBailacurrency = param;

		}

		/**
		 * field for BailaccountId
		 */

		protected java.lang.String localBailaccountId;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localBailaccountIdTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getBailaccountId() {
			return localBailaccountId;
		}

		/**
		 * Auto generated setter method
		 * @param param BailaccountId
		 */
		public void setBailaccountId(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localBailaccountIdTracker = true;
			} else {
				localBailaccountIdTracker = false;

			}

			this.localBailaccountId = param;

		}

		/**
		 * field for BailaccountNo
		 */

		protected java.lang.String localBailaccountNo;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localBailaccountNoTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getBailaccountNo() {
			return localBailaccountNo;
		}

		/**
		 * Auto generated setter method
		 * @param param BailaccountNo
		 */
		public void setBailaccountNo(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localBailaccountNoTracker = true;
			} else {
				localBailaccountNoTracker = false;

			}

			this.localBailaccountNo = param;

		}

		/**
		 * field for Fee
		 */

		protected java.lang.String localFee;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localFeeTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getFee() {
			return localFee;
		}

		/**
		 * Auto generated setter method
		 * @param param Fee
		 */
		public void setFee(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localFeeTracker = true;
			} else {
				localFeeTracker = false;

			}

			this.localFee = param;

		}

		/**
		 * field for CommitFee
		 */

		protected java.lang.String localCommitFee;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localCommitFeeTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getCommitFee() {
			return localCommitFee;
		}

		/**
		 * Auto generated setter method
		 * @param param CommitFee
		 */
		public void setCommitFee(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localCommitFeeTracker = true;
			} else {
				localCommitFeeTracker = false;

			}

			this.localCommitFee = param;

		}

		/**
		 * field for PledgeNo
		 */

		protected java.lang.String localPledgeNo;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localPledgeNoTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getPledgeNo() {
			return localPledgeNo;
		}

		/**
		 * Auto generated setter method
		 * @param param PledgeNo
		 */
		public void setPledgeNo(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localPledgeNoTracker = true;
			} else {
				localPledgeNoTracker = false;

			}

			this.localPledgeNo = param;

		}

		/**
		 * field for Origin
		 */

		protected java.lang.String localOrigin;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localOriginTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getOrigin() {
			return localOrigin;
		}

		/**
		 * Auto generated setter method
		 * @param param Origin
		 */
		public void setOrigin(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localOriginTracker = true;
			} else {
				localOriginTracker = false;

			}

			this.localOrigin = param;

		}

		/**
		 * field for Flag
		 */

		protected java.lang.String localFlag;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localFlagTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getFlag() {
			return localFlag;
		}

		/**
		 * Auto generated setter method
		 * @param param Flag
		 */
		public void setFlag(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localFlagTracker = true;
			} else {
				localFlagTracker = false;

			}

			this.localFlag = param;

		}

		/**
		 * field for InterestFalg
		 */

		protected java.lang.String localInterestFalg;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localInterestFalgTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getInterestFalg() {
			return localInterestFalg;
		}

		/**
		 * Auto generated setter method
		 * @param param InterestFalg
		 */
		public void setInterestFalg(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localInterestFalgTracker = true;
			} else {
				localInterestFalgTracker = false;

			}

			this.localInterestFalg = param;

		}

		/**
		 * field for Reserve1
		 */

		protected java.lang.String localReserve1;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localReserve1Tracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getReserve1() {
			return localReserve1;
		}

		/**
		 * Auto generated setter method
		 * @param param Reserve1
		 */
		public void setReserve1(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localReserve1Tracker = true;
			} else {
				localReserve1Tracker = false;

			}

			this.localReserve1 = param;

		}

		/**
		 * field for Reserve2
		 */

		protected java.lang.String localReserve2;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localReserve2Tracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getReserve2() {
			return localReserve2;
		}

		/**
		 * Auto generated setter method
		 * @param param Reserve2
		 */
		public void setReserve2(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localReserve2Tracker = true;
			} else {
				localReserve2Tracker = false;

			}

			this.localReserve2 = param;

		}

		/**
		 * field for Reserve3
		 */

		protected java.lang.String localReserve3;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localReserve3Tracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getReserve3() {
			return localReserve3;
		}

		/**
		 * Auto generated setter method
		 * @param param Reserve3
		 */
		public void setReserve3(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localReserve3Tracker = true;
			} else {
				localReserve3Tracker = false;

			}

			this.localReserve3 = param;

		}

		/**
		 * field for Reserve4
		 */

		protected java.lang.String localReserve4;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localReserve4Tracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getReserve4() {
			return localReserve4;
		}

		/**
		 * Auto generated setter method
		 * @param param Reserve4
		 */
		public void setReserve4(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localReserve4Tracker = true;
			} else {
				localReserve4Tracker = false;

			}

			this.localReserve4 = param;

		}

		/**
		 * field for Reserve5
		 */

		protected java.lang.String localReserve5;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localReserve5Tracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getReserve5() {
			return localReserve5;
		}

		/**
		 * Auto generated setter method
		 * @param param Reserve5
		 */
		public void setReserve5(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localReserve5Tracker = true;
			} else {
				localReserve5Tracker = false;

			}

			this.localReserve5 = param;

		}

		/**
		 * field for Reserve6
		 */

		protected java.lang.String localReserve6;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localReserve6Tracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getReserve6() {
			return localReserve6;
		}

		/**
		 * Auto generated setter method
		 * @param param Reserve6
		 */
		public void setReserve6(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localReserve6Tracker = true;
			} else {
				localReserve6Tracker = false;

			}

			this.localReserve6 = param;

		}

		/**
		 * field for Reserve7
		 */

		protected java.lang.String localReserve7;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localReserve7Tracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getReserve7() {
			return localReserve7;
		}

		/**
		 * Auto generated setter method
		 * @param param Reserve7
		 */
		public void setReserve7(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localReserve7Tracker = true;
			} else {
				localReserve7Tracker = false;

			}

			this.localReserve7 = param;

		}

		/**
		 * field for Reserve8
		 */

		protected java.lang.String localReserve8;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localReserve8Tracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getReserve8() {
			return localReserve8;
		}

		/**
		 * Auto generated setter method
		 * @param param Reserve8
		 */
		public void setReserve8(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localReserve8Tracker = true;
			} else {
				localReserve8Tracker = false;

			}

			this.localReserve8 = param;

		}

		/**
		 * field for Reserve9
		 */

		protected java.lang.String localReserve9;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localReserve9Tracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getReserve9() {
			return localReserve9;
		}

		/**
		 * Auto generated setter method
		 * @param param Reserve9
		 */
		public void setReserve9(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localReserve9Tracker = true;
			} else {
				localReserve9Tracker = false;

			}

			this.localReserve9 = param;

		}

		/**
		 * field for Reserve10
		 */

		protected java.lang.String localReserve10;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localReserve10Tracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getReserve10() {
			return localReserve10;
		}

		/**
		 * Auto generated setter method
		 * @param param Reserve10
		 */
		public void setReserve10(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localReserve10Tracker = true;
			} else {
				localReserve10Tracker = false;

			}

			this.localReserve10 = param;

		}

		/**
		 * isReaderMTOMAware
		 * @return true if the reader supports MTOM
		 */
		public static boolean isReaderMTOMAware(javax.xml.stream.XMLStreamReader reader) {
			boolean isReaderMTOMAware = false;

			try {
				isReaderMTOMAware = java.lang.Boolean.TRUE.equals(reader.getProperty(org.apache.axiom.om.OMConstants.IS_DATA_HANDLERS_AWARE));
			} catch (java.lang.IllegalArgumentException e) {
				isReaderMTOMAware = false;
			}
			return isReaderMTOMAware;
		}

		/**
		 * @param parentQName
		 * @param factory
		 * @return org.apache.axiom.om.OMElement
		 */
		public org.apache.axiom.om.OMElement getOMElement(final javax.xml.namespace.QName parentQName, final org.apache.axiom.om.OMFactory factory) throws org.apache.axis2.databinding.ADBException {

			org.apache.axiom.om.OMDataSource dataSource = new org.apache.axis2.databinding.ADBDataSource(this, parentQName) {

				public void serialize(org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
					FMT_CRMS_SVR_S01001010021003_IN_SUB.this.serialize(parentQName, factory, xmlWriter);
				}
			};
			return new org.apache.axiom.om.impl.llom.OMSourcedElementImpl(parentQName, factory, dataSource);

		}

		public void serialize(final javax.xml.namespace.QName parentQName, final org.apache.axiom.om.OMFactory factory, org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {

			java.lang.String prefix = null;
			java.lang.String namespace = null;

			prefix = parentQName.getPrefix();
			namespace = parentQName.getNamespaceURI();

			if (namespace != null) {
				java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
				if (writerPrefix != null) {
					xmlWriter.writeStartElement(namespace, parentQName.getLocalPart());
				} else {
					if (prefix == null) {
						prefix = generatePrefix(namespace);
					}

					xmlWriter.writeStartElement(prefix, parentQName.getLocalPart(), namespace);
					xmlWriter.writeNamespace(prefix, namespace);
					xmlWriter.setPrefix(prefix, namespace);
				}
			} else {
				xmlWriter.writeStartElement(parentQName.getLocalPart());
			}
			if (localPromNoteNoTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "promNoteNo", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "promNoteNo");
					}

				} else {
					xmlWriter.writeStartElement("promNoteNo");
				}

				if (localPromNoteNo == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("promNoteNo cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localPromNoteNo);

				}

				xmlWriter.writeEndElement();
			}
			if (localBillNoTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "billNo", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "billNo");
					}

				} else {
					xmlWriter.writeStartElement("billNo");
				}

				if (localBillNo == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("billNo cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localBillNo);

				}

				xmlWriter.writeEndElement();
			}
			if (localBillMoneyTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "billMoney", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "billMoney");
					}

				} else {
					xmlWriter.writeStartElement("billMoney");
				}

				if (localBillMoney == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("billMoney cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localBillMoney);

				}

				xmlWriter.writeEndElement();
			}
			if (localRemitterCustNoTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "remitterCustNo", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "remitterCustNo");
					}

				} else {
					xmlWriter.writeStartElement("remitterCustNo");
				}

				if (localRemitterCustNo == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("remitterCustNo cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localRemitterCustNo);

				}

				xmlWriter.writeEndElement();
			}
			if (localRemitterAcctNoTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "remitterAcctNo", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "remitterAcctNo");
					}

				} else {
					xmlWriter.writeStartElement("remitterAcctNo");
				}

				if (localRemitterAcctNo == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("remitterAcctNo cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localRemitterAcctNo);

				}

				xmlWriter.writeEndElement();
			}
			if (localRemitterTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "remitter", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "remitter");
					}

				} else {
					xmlWriter.writeStartElement("remitter");
				}

				if (localRemitter == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("remitter cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localRemitter);

				}

				xmlWriter.writeEndElement();
			}
			if (localRemitterBankNoTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "remitterBankNo", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "remitterBankNo");
					}

				} else {
					xmlWriter.writeStartElement("remitterBankNo");
				}

				if (localRemitterBankNo == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("remitterBankNo cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localRemitterBankNo);

				}

				xmlWriter.writeEndElement();
			}
			if (localRemitterBankNameTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "remitterBankName", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "remitterBankName");
					}

				} else {
					xmlWriter.writeStartElement("remitterBankName");
				}

				if (localRemitterBankName == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("remitterBankName cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localRemitterBankName);

				}

				xmlWriter.writeEndElement();
			}
			if (localDraweeBankNoTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "draweeBankNo", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "draweeBankNo");
					}

				} else {
					xmlWriter.writeStartElement("draweeBankNo");
				}

				if (localDraweeBankNo == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("draweeBankNo cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localDraweeBankNo);

				}

				xmlWriter.writeEndElement();
			}
			if (localDraweeBankNameTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "draweeBankName", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "draweeBankName");
					}

				} else {
					xmlWriter.writeStartElement("draweeBankName");
				}

				if (localDraweeBankName == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("draweeBankName cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localDraweeBankName);

				}

				xmlWriter.writeEndElement();
			}
			if (localDraweeBankAddrTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "draweeBankAddr", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "draweeBankAddr");
					}

				} else {
					xmlWriter.writeStartElement("draweeBankAddr");
				}

				if (localDraweeBankAddr == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("draweeBankAddr cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localDraweeBankAddr);

				}

				xmlWriter.writeEndElement();
			}
			if (localPayeeAcctNoTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "payeeAcctNo", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "payeeAcctNo");
					}

				} else {
					xmlWriter.writeStartElement("payeeAcctNo");
				}

				if (localPayeeAcctNo == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("payeeAcctNo cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localPayeeAcctNo);

				}

				xmlWriter.writeEndElement();
			}
			if (localPayeeTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "payee", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "payee");
					}

				} else {
					xmlWriter.writeStartElement("payee");
				}

				if (localPayee == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("payee cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localPayee);

				}

				xmlWriter.writeEndElement();
			}
			if (localPayeeBankNameTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "payeeBankName", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "payeeBankName");
					}

				} else {
					xmlWriter.writeStartElement("payeeBankName");
				}

				if (localPayeeBankName == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("payeeBankName cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localPayeeBankName);

				}

				xmlWriter.writeEndElement();
			}
			if (localPayeeBankNoTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "payeeBankNo", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "payeeBankNo");
					}

				} else {
					xmlWriter.writeStartElement("payeeBankNo");
				}

				if (localPayeeBankNo == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("payeeBankNo cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localPayeeBankNo);

				}

				xmlWriter.writeEndElement();
			}
			if (localAcptDtTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "acptDt", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "acptDt");
					}

				} else {
					xmlWriter.writeStartElement("acptDt");
				}

				if (localAcptDt == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("acptDt cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localAcptDt);

				}

				xmlWriter.writeEndElement();
			}
			if (localDueDtTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "dueDt", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "dueDt");
					}

				} else {
					xmlWriter.writeStartElement("dueDt");
				}

				if (localDueDt == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("dueDt cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localDueDt);

				}

				xmlWriter.writeEndElement();
			}
			if (localAcceptorAcctNoTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "acceptorAcctNo", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "acceptorAcctNo");
					}

				} else {
					xmlWriter.writeStartElement("acceptorAcctNo");
				}

				if (localAcceptorAcctNo == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("acceptorAcctNo cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localAcceptorAcctNo);

				}

				xmlWriter.writeEndElement();
			}
			if (localAcceptorTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "acceptor", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "acceptor");
					}

				} else {
					xmlWriter.writeStartElement("acceptor");
				}

				if (localAcceptor == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("acceptor cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localAcceptor);

				}

				xmlWriter.writeEndElement();
			}
			if (localAcceptorBankNoTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "acceptorBankNo", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "acceptorBankNo");
					}

				} else {
					xmlWriter.writeStartElement("acceptorBankNo");
				}

				if (localAcceptorBankNo == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("acceptorBankNo cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localAcceptorBankNo);

				}

				xmlWriter.writeEndElement();
			}
			if (localIsAccpTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "isAccp", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "isAccp");
					}

				} else {
					xmlWriter.writeStartElement("isAccp");
				}

				if (localIsAccp == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("isAccp cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localIsAccp);

				}

				xmlWriter.writeEndElement();
			}
			if (localIfSameCityTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "ifSameCity", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "ifSameCity");
					}

				} else {
					xmlWriter.writeStartElement("ifSameCity");
				}

				if (localIfSameCity == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("ifSameCity cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localIfSameCity);

				}

				xmlWriter.writeEndElement();
			}
			if (localForbidFlagTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "forbidFlag", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "forbidFlag");
					}

				} else {
					xmlWriter.writeStartElement("forbidFlag");
				}

				if (localForbidFlag == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("forbidFlag cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localForbidFlag);

				}

				xmlWriter.writeEndElement();
			}
			if (localDelayDaysTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "delayDays", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "delayDays");
					}

				} else {
					xmlWriter.writeStartElement("delayDays");
				}

				if (localDelayDays == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("delayDays cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localDelayDays);

				}

				xmlWriter.writeEndElement();
			}
			if (localInterestCalDaysTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "interestCalDays", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "interestCalDays");
					}

				} else {
					xmlWriter.writeStartElement("interestCalDays");
				}

				if (localInterestCalDays == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("interestCalDays cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localInterestCalDays);

				}

				xmlWriter.writeEndElement();
			}
			if (localInterestTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "interest", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "interest");
					}

				} else {
					xmlWriter.writeStartElement("interest");
				}

				if (localInterest == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("interest cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localInterest);

				}

				xmlWriter.writeEndElement();
			}
			if (localSalerInterestTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "salerInterest", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "salerInterest");
					}

				} else {
					xmlWriter.writeStartElement("salerInterest");
				}

				if (localSalerInterest == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("salerInterest cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localSalerInterest);

				}

				xmlWriter.writeEndElement();
			}
			if (localBuyerInterestTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "buyerInterest", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "buyerInterest");
					}

				} else {
					xmlWriter.writeStartElement("buyerInterest");
				}

				if (localBuyerInterest == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("buyerInterest cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localBuyerInterest);

				}

				xmlWriter.writeEndElement();
			}
			if (localThridInterestTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "thridInterest", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "thridInterest");
					}

				} else {
					xmlWriter.writeStartElement("thridInterest");
				}

				if (localThridInterest == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("thridInterest cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localThridInterest);

				}

				xmlWriter.writeEndElement();
			}
			if (localPayMoneyTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "payMoney", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "payMoney");
					}

				} else {
					xmlWriter.writeStartElement("payMoney");
				}

				if (localPayMoney == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("payMoney cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localPayMoney);

				}

				xmlWriter.writeEndElement();
			}
			if (localRemarksTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "remarks", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "remarks");
					}

				} else {
					xmlWriter.writeStartElement("remarks");
				}

				if (localRemarks == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("remarks cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localRemarks);

				}

				xmlWriter.writeEndElement();
			}
			if (localInqRepFlagTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "inqRepFlag", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "inqRepFlag");
					}

				} else {
					xmlWriter.writeStartElement("inqRepFlag");
				}

				if (localInqRepFlag == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("inqRepFlag cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localInqRepFlag);

				}

				xmlWriter.writeEndElement();
			}
			if (localInqRepTypeTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "inqRepType", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "inqRepType");
					}

				} else {
					xmlWriter.writeStartElement("inqRepType");
				}

				if (localInqRepType == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("inqRepType cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localInqRepType);

				}

				xmlWriter.writeEndElement();
			}
			if (localAcceptorBankNameTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "acceptorBankName", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "acceptorBankName");
					}

				} else {
					xmlWriter.writeStartElement("acceptorBankName");
				}

				if (localAcceptorBankName == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("acceptorBankName cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localAcceptorBankName);

				}

				xmlWriter.writeEndElement();
			}
			if (localPutoutNoTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "putoutNo", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "putoutNo");
					}

				} else {
					xmlWriter.writeStartElement("putoutNo");
				}

				if (localPutoutNo == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("putoutNo cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localPutoutNo);

				}

				xmlWriter.writeEndElement();
			}
			if (localBailsumTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "bailsum", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "bailsum");
					}

				} else {
					xmlWriter.writeStartElement("bailsum");
				}

				if (localBailsum == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("bailsum cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localBailsum);

				}

				xmlWriter.writeEndElement();
			}
			if (localBailacurrencyTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "bailacurrency", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "bailacurrency");
					}

				} else {
					xmlWriter.writeStartElement("bailacurrency");
				}

				if (localBailacurrency == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("bailacurrency cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localBailacurrency);

				}

				xmlWriter.writeEndElement();
			}
			if (localBailaccountIdTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "bailaccountId", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "bailaccountId");
					}

				} else {
					xmlWriter.writeStartElement("bailaccountId");
				}

				if (localBailaccountId == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("bailaccountId cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localBailaccountId);

				}

				xmlWriter.writeEndElement();
			}
			if (localBailaccountNoTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "bailaccountNo", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "bailaccountNo");
					}

				} else {
					xmlWriter.writeStartElement("bailaccountNo");
				}

				if (localBailaccountNo == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("bailaccountNo cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localBailaccountNo);

				}

				xmlWriter.writeEndElement();
			}
			if (localFeeTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "fee", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "fee");
					}

				} else {
					xmlWriter.writeStartElement("fee");
				}

				if (localFee == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("fee cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localFee);

				}

				xmlWriter.writeEndElement();
			}
			if (localCommitFeeTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "commitFee", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "commitFee");
					}

				} else {
					xmlWriter.writeStartElement("commitFee");
				}

				if (localCommitFee == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("commitFee cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localCommitFee);

				}

				xmlWriter.writeEndElement();
			}
			if (localPledgeNoTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "pledgeNo", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "pledgeNo");
					}

				} else {
					xmlWriter.writeStartElement("pledgeNo");
				}

				if (localPledgeNo == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("pledgeNo cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localPledgeNo);

				}

				xmlWriter.writeEndElement();
			}
			if (localOriginTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "origin", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "origin");
					}

				} else {
					xmlWriter.writeStartElement("origin");
				}

				if (localOrigin == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("origin cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localOrigin);

				}

				xmlWriter.writeEndElement();
			}
			if (localFlagTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "flag", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "flag");
					}

				} else {
					xmlWriter.writeStartElement("flag");
				}

				if (localFlag == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("flag cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localFlag);

				}

				xmlWriter.writeEndElement();
			}
			if (localInterestFalgTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "interestFalg", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "interestFalg");
					}

				} else {
					xmlWriter.writeStartElement("interestFalg");
				}

				if (localInterestFalg == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("interestFalg cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localInterestFalg);

				}

				xmlWriter.writeEndElement();
			}
			if (localReserve1Tracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "reserve1", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "reserve1");
					}

				} else {
					xmlWriter.writeStartElement("reserve1");
				}

				if (localReserve1 == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("reserve1 cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localReserve1);

				}

				xmlWriter.writeEndElement();
			}
			if (localReserve2Tracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "reserve2", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "reserve2");
					}

				} else {
					xmlWriter.writeStartElement("reserve2");
				}

				if (localReserve2 == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("reserve2 cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localReserve2);

				}

				xmlWriter.writeEndElement();
			}
			if (localReserve3Tracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "reserve3", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "reserve3");
					}

				} else {
					xmlWriter.writeStartElement("reserve3");
				}

				if (localReserve3 == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("reserve3 cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localReserve3);

				}

				xmlWriter.writeEndElement();
			}
			if (localReserve4Tracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "reserve4", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "reserve4");
					}

				} else {
					xmlWriter.writeStartElement("reserve4");
				}

				if (localReserve4 == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("reserve4 cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localReserve4);

				}

				xmlWriter.writeEndElement();
			}
			if (localReserve5Tracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "reserve5", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "reserve5");
					}

				} else {
					xmlWriter.writeStartElement("reserve5");
				}

				if (localReserve5 == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("reserve5 cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localReserve5);

				}

				xmlWriter.writeEndElement();
			}
			if (localReserve6Tracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "reserve6", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "reserve6");
					}

				} else {
					xmlWriter.writeStartElement("reserve6");
				}

				if (localReserve6 == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("reserve6 cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localReserve6);

				}

				xmlWriter.writeEndElement();
			}
			if (localReserve7Tracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "reserve7", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "reserve7");
					}

				} else {
					xmlWriter.writeStartElement("reserve7");
				}

				if (localReserve7 == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("reserve7 cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localReserve7);

				}

				xmlWriter.writeEndElement();
			}
			if (localReserve8Tracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "reserve8", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "reserve8");
					}

				} else {
					xmlWriter.writeStartElement("reserve8");
				}

				if (localReserve8 == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("reserve8 cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localReserve8);

				}

				xmlWriter.writeEndElement();
			}
			if (localReserve9Tracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "reserve9", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "reserve9");
					}

				} else {
					xmlWriter.writeStartElement("reserve9");
				}

				if (localReserve9 == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("reserve9 cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localReserve9);

				}

				xmlWriter.writeEndElement();
			}
			if (localReserve10Tracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "reserve10", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "reserve10");
					}

				} else {
					xmlWriter.writeStartElement("reserve10");
				}

				if (localReserve10 == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("reserve10 cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localReserve10);

				}

				xmlWriter.writeEndElement();
			}
			xmlWriter.writeEndElement();

		}

		/**
		 * Util method to write an attribute with the ns prefix
		 */
		private void writeAttribute(java.lang.String prefix, java.lang.String namespace, java.lang.String attName, java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			if (xmlWriter.getPrefix(namespace) == null) {
				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);

			}

			xmlWriter.writeAttribute(namespace, attName, attValue);

		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeAttribute(java.lang.String namespace, java.lang.String attName, java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			if (namespace.equals("")) {
				xmlWriter.writeAttribute(attName, attValue);
			} else {
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attValue);
			}
		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeQNameAttribute(java.lang.String namespace, java.lang.String attName, javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {

			java.lang.String attributeNamespace = qname.getNamespaceURI();
			java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
			if (attributePrefix == null) {
				attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
			}
			java.lang.String attributeValue;
			if (attributePrefix.trim().length() > 0) {
				attributeValue = attributePrefix + ":" + qname.getLocalPart();
			} else {
				attributeValue = qname.getLocalPart();
			}

			if (namespace.equals("")) {
				xmlWriter.writeAttribute(attName, attributeValue);
			} else {
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attributeValue);
			}
		}

		/**
		 * method to handle Qnames
		 */

		private void writeQName(javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
			java.lang.String namespaceURI = qname.getNamespaceURI();
			if (namespaceURI != null) {
				java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
				if (prefix == null) {
					prefix = generatePrefix(namespaceURI);
					xmlWriter.writeNamespace(prefix, namespaceURI);
					xmlWriter.setPrefix(prefix, namespaceURI);
				}

				if (prefix.trim().length() > 0) {
					xmlWriter.writeCharacters(prefix + ":" + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
				} else {
					// i.e this is the default namespace
					xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
				}

			} else {
				xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
			}
		}

		private void writeQNames(javax.xml.namespace.QName[] qnames, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

			if (qnames != null) {
				// we have to store this data until last moment since it is not possible to write any
				// namespace data after writing the charactor data
				java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
				java.lang.String namespaceURI = null;
				java.lang.String prefix = null;

				for (int i = 0; i < qnames.length; i++) {
					if (i > 0) {
						stringToWrite.append(" ");
					}
					namespaceURI = qnames[i].getNamespaceURI();
					if (namespaceURI != null) {
						prefix = xmlWriter.getPrefix(namespaceURI);
						if ((prefix == null) || (prefix.length() == 0)) {
							prefix = generatePrefix(namespaceURI);
							xmlWriter.writeNamespace(prefix, namespaceURI);
							xmlWriter.setPrefix(prefix, namespaceURI);
						}

						if (prefix.trim().length() > 0) {
							stringToWrite.append(prefix).append(":").append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
						} else {
							stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
						}
					} else {
						stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
					}
				}
				xmlWriter.writeCharacters(stringToWrite.toString());
			}

		}

		/**
		 * Register a namespace prefix
		 */
		private java.lang.String registerPrefix(javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace) throws javax.xml.stream.XMLStreamException {
			java.lang.String prefix = xmlWriter.getPrefix(namespace);

			if (prefix == null) {
				prefix = generatePrefix(namespace);

				while (xmlWriter.getNamespaceContext().getNamespaceURI(prefix) != null) {
					prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
				}

				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);
			}

			return prefix;
		}

		/**
		 * databinding method to get an XML representation of this object
		 */
		public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName) throws org.apache.axis2.databinding.ADBException {

			java.util.ArrayList elementList = new java.util.ArrayList();
			java.util.ArrayList attribList = new java.util.ArrayList();

			if (localPromNoteNoTracker) {
				elementList.add(new javax.xml.namespace.QName("", "promNoteNo"));

				if (localPromNoteNo != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localPromNoteNo));
				} else {
					throw new org.apache.axis2.databinding.ADBException("promNoteNo cannot be null!!");
				}
			}
			if (localBillNoTracker) {
				elementList.add(new javax.xml.namespace.QName("", "billNo"));

				if (localBillNo != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localBillNo));
				} else {
					throw new org.apache.axis2.databinding.ADBException("billNo cannot be null!!");
				}
			}
			if (localBillMoneyTracker) {
				elementList.add(new javax.xml.namespace.QName("", "billMoney"));

				if (localBillMoney != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localBillMoney));
				} else {
					throw new org.apache.axis2.databinding.ADBException("billMoney cannot be null!!");
				}
			}
			if (localRemitterCustNoTracker) {
				elementList.add(new javax.xml.namespace.QName("", "remitterCustNo"));

				if (localRemitterCustNo != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localRemitterCustNo));
				} else {
					throw new org.apache.axis2.databinding.ADBException("remitterCustNo cannot be null!!");
				}
			}
			if (localRemitterAcctNoTracker) {
				elementList.add(new javax.xml.namespace.QName("", "remitterAcctNo"));

				if (localRemitterAcctNo != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localRemitterAcctNo));
				} else {
					throw new org.apache.axis2.databinding.ADBException("remitterAcctNo cannot be null!!");
				}
			}
			if (localRemitterTracker) {
				elementList.add(new javax.xml.namespace.QName("", "remitter"));

				if (localRemitter != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localRemitter));
				} else {
					throw new org.apache.axis2.databinding.ADBException("remitter cannot be null!!");
				}
			}
			if (localRemitterBankNoTracker) {
				elementList.add(new javax.xml.namespace.QName("", "remitterBankNo"));

				if (localRemitterBankNo != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localRemitterBankNo));
				} else {
					throw new org.apache.axis2.databinding.ADBException("remitterBankNo cannot be null!!");
				}
			}
			if (localRemitterBankNameTracker) {
				elementList.add(new javax.xml.namespace.QName("", "remitterBankName"));

				if (localRemitterBankName != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localRemitterBankName));
				} else {
					throw new org.apache.axis2.databinding.ADBException("remitterBankName cannot be null!!");
				}
			}
			if (localDraweeBankNoTracker) {
				elementList.add(new javax.xml.namespace.QName("", "draweeBankNo"));

				if (localDraweeBankNo != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localDraweeBankNo));
				} else {
					throw new org.apache.axis2.databinding.ADBException("draweeBankNo cannot be null!!");
				}
			}
			if (localDraweeBankNameTracker) {
				elementList.add(new javax.xml.namespace.QName("", "draweeBankName"));

				if (localDraweeBankName != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localDraweeBankName));
				} else {
					throw new org.apache.axis2.databinding.ADBException("draweeBankName cannot be null!!");
				}
			}
			if (localDraweeBankAddrTracker) {
				elementList.add(new javax.xml.namespace.QName("", "draweeBankAddr"));

				if (localDraweeBankAddr != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localDraweeBankAddr));
				} else {
					throw new org.apache.axis2.databinding.ADBException("draweeBankAddr cannot be null!!");
				}
			}
			if (localPayeeAcctNoTracker) {
				elementList.add(new javax.xml.namespace.QName("", "payeeAcctNo"));

				if (localPayeeAcctNo != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localPayeeAcctNo));
				} else {
					throw new org.apache.axis2.databinding.ADBException("payeeAcctNo cannot be null!!");
				}
			}
			if (localPayeeTracker) {
				elementList.add(new javax.xml.namespace.QName("", "payee"));

				if (localPayee != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localPayee));
				} else {
					throw new org.apache.axis2.databinding.ADBException("payee cannot be null!!");
				}
			}
			if (localPayeeBankNameTracker) {
				elementList.add(new javax.xml.namespace.QName("", "payeeBankName"));

				if (localPayeeBankName != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localPayeeBankName));
				} else {
					throw new org.apache.axis2.databinding.ADBException("payeeBankName cannot be null!!");
				}
			}
			if (localPayeeBankNoTracker) {
				elementList.add(new javax.xml.namespace.QName("", "payeeBankNo"));

				if (localPayeeBankNo != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localPayeeBankNo));
				} else {
					throw new org.apache.axis2.databinding.ADBException("payeeBankNo cannot be null!!");
				}
			}
			if (localAcptDtTracker) {
				elementList.add(new javax.xml.namespace.QName("", "acptDt"));

				if (localAcptDt != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localAcptDt));
				} else {
					throw new org.apache.axis2.databinding.ADBException("acptDt cannot be null!!");
				}
			}
			if (localDueDtTracker) {
				elementList.add(new javax.xml.namespace.QName("", "dueDt"));

				if (localDueDt != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localDueDt));
				} else {
					throw new org.apache.axis2.databinding.ADBException("dueDt cannot be null!!");
				}
			}
			if (localAcceptorAcctNoTracker) {
				elementList.add(new javax.xml.namespace.QName("", "acceptorAcctNo"));

				if (localAcceptorAcctNo != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localAcceptorAcctNo));
				} else {
					throw new org.apache.axis2.databinding.ADBException("acceptorAcctNo cannot be null!!");
				}
			}
			if (localAcceptorTracker) {
				elementList.add(new javax.xml.namespace.QName("", "acceptor"));

				if (localAcceptor != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localAcceptor));
				} else {
					throw new org.apache.axis2.databinding.ADBException("acceptor cannot be null!!");
				}
			}
			if (localAcceptorBankNoTracker) {
				elementList.add(new javax.xml.namespace.QName("", "acceptorBankNo"));

				if (localAcceptorBankNo != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localAcceptorBankNo));
				} else {
					throw new org.apache.axis2.databinding.ADBException("acceptorBankNo cannot be null!!");
				}
			}
			if (localIsAccpTracker) {
				elementList.add(new javax.xml.namespace.QName("", "isAccp"));

				if (localIsAccp != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localIsAccp));
				} else {
					throw new org.apache.axis2.databinding.ADBException("isAccp cannot be null!!");
				}
			}
			if (localIfSameCityTracker) {
				elementList.add(new javax.xml.namespace.QName("", "ifSameCity"));

				if (localIfSameCity != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localIfSameCity));
				} else {
					throw new org.apache.axis2.databinding.ADBException("ifSameCity cannot be null!!");
				}
			}
			if (localForbidFlagTracker) {
				elementList.add(new javax.xml.namespace.QName("", "forbidFlag"));

				if (localForbidFlag != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localForbidFlag));
				} else {
					throw new org.apache.axis2.databinding.ADBException("forbidFlag cannot be null!!");
				}
			}
			if (localDelayDaysTracker) {
				elementList.add(new javax.xml.namespace.QName("", "delayDays"));

				if (localDelayDays != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localDelayDays));
				} else {
					throw new org.apache.axis2.databinding.ADBException("delayDays cannot be null!!");
				}
			}
			if (localInterestCalDaysTracker) {
				elementList.add(new javax.xml.namespace.QName("", "interestCalDays"));

				if (localInterestCalDays != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localInterestCalDays));
				} else {
					throw new org.apache.axis2.databinding.ADBException("interestCalDays cannot be null!!");
				}
			}
			if (localInterestTracker) {
				elementList.add(new javax.xml.namespace.QName("", "interest"));

				if (localInterest != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localInterest));
				} else {
					throw new org.apache.axis2.databinding.ADBException("interest cannot be null!!");
				}
			}
			if (localSalerInterestTracker) {
				elementList.add(new javax.xml.namespace.QName("", "salerInterest"));

				if (localSalerInterest != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localSalerInterest));
				} else {
					throw new org.apache.axis2.databinding.ADBException("salerInterest cannot be null!!");
				}
			}
			if (localBuyerInterestTracker) {
				elementList.add(new javax.xml.namespace.QName("", "buyerInterest"));

				if (localBuyerInterest != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localBuyerInterest));
				} else {
					throw new org.apache.axis2.databinding.ADBException("buyerInterest cannot be null!!");
				}
			}
			if (localThridInterestTracker) {
				elementList.add(new javax.xml.namespace.QName("", "thridInterest"));

				if (localThridInterest != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localThridInterest));
				} else {
					throw new org.apache.axis2.databinding.ADBException("thridInterest cannot be null!!");
				}
			}
			if (localPayMoneyTracker) {
				elementList.add(new javax.xml.namespace.QName("", "payMoney"));

				if (localPayMoney != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localPayMoney));
				} else {
					throw new org.apache.axis2.databinding.ADBException("payMoney cannot be null!!");
				}
			}
			if (localRemarksTracker) {
				elementList.add(new javax.xml.namespace.QName("", "remarks"));

				if (localRemarks != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localRemarks));
				} else {
					throw new org.apache.axis2.databinding.ADBException("remarks cannot be null!!");
				}
			}
			if (localInqRepFlagTracker) {
				elementList.add(new javax.xml.namespace.QName("", "inqRepFlag"));

				if (localInqRepFlag != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localInqRepFlag));
				} else {
					throw new org.apache.axis2.databinding.ADBException("inqRepFlag cannot be null!!");
				}
			}
			if (localInqRepTypeTracker) {
				elementList.add(new javax.xml.namespace.QName("", "inqRepType"));

				if (localInqRepType != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localInqRepType));
				} else {
					throw new org.apache.axis2.databinding.ADBException("inqRepType cannot be null!!");
				}
			}
			if (localAcceptorBankNameTracker) {
				elementList.add(new javax.xml.namespace.QName("", "acceptorBankName"));

				if (localAcceptorBankName != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localAcceptorBankName));
				} else {
					throw new org.apache.axis2.databinding.ADBException("acceptorBankName cannot be null!!");
				}
			}
			if (localPutoutNoTracker) {
				elementList.add(new javax.xml.namespace.QName("", "putoutNo"));

				if (localPutoutNo != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localPutoutNo));
				} else {
					throw new org.apache.axis2.databinding.ADBException("putoutNo cannot be null!!");
				}
			}
			if (localBailsumTracker) {
				elementList.add(new javax.xml.namespace.QName("", "bailsum"));

				if (localBailsum != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localBailsum));
				} else {
					throw new org.apache.axis2.databinding.ADBException("bailsum cannot be null!!");
				}
			}
			if (localBailacurrencyTracker) {
				elementList.add(new javax.xml.namespace.QName("", "bailacurrency"));

				if (localBailacurrency != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localBailacurrency));
				} else {
					throw new org.apache.axis2.databinding.ADBException("bailacurrency cannot be null!!");
				}
			}
			if (localBailaccountIdTracker) {
				elementList.add(new javax.xml.namespace.QName("", "bailaccountId"));

				if (localBailaccountId != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localBailaccountId));
				} else {
					throw new org.apache.axis2.databinding.ADBException("bailaccountId cannot be null!!");
				}
			}
			if (localBailaccountNoTracker) {
				elementList.add(new javax.xml.namespace.QName("", "bailaccountNo"));

				if (localBailaccountNo != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localBailaccountNo));
				} else {
					throw new org.apache.axis2.databinding.ADBException("bailaccountNo cannot be null!!");
				}
			}
			if (localFeeTracker) {
				elementList.add(new javax.xml.namespace.QName("", "fee"));

				if (localFee != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localFee));
				} else {
					throw new org.apache.axis2.databinding.ADBException("fee cannot be null!!");
				}
			}
			if (localCommitFeeTracker) {
				elementList.add(new javax.xml.namespace.QName("", "commitFee"));

				if (localCommitFee != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCommitFee));
				} else {
					throw new org.apache.axis2.databinding.ADBException("commitFee cannot be null!!");
				}
			}
			if (localPledgeNoTracker) {
				elementList.add(new javax.xml.namespace.QName("", "pledgeNo"));

				if (localPledgeNo != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localPledgeNo));
				} else {
					throw new org.apache.axis2.databinding.ADBException("pledgeNo cannot be null!!");
				}
			}
			if (localOriginTracker) {
				elementList.add(new javax.xml.namespace.QName("", "origin"));

				if (localOrigin != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localOrigin));
				} else {
					throw new org.apache.axis2.databinding.ADBException("origin cannot be null!!");
				}
			}
			if (localFlagTracker) {
				elementList.add(new javax.xml.namespace.QName("", "flag"));

				if (localFlag != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localFlag));
				} else {
					throw new org.apache.axis2.databinding.ADBException("flag cannot be null!!");
				}
			}
			if (localInterestFalgTracker) {
				elementList.add(new javax.xml.namespace.QName("", "interestFalg"));

				if (localInterestFalg != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localInterestFalg));
				} else {
					throw new org.apache.axis2.databinding.ADBException("interestFalg cannot be null!!");
				}
			}
			if (localReserve1Tracker) {
				elementList.add(new javax.xml.namespace.QName("", "reserve1"));

				if (localReserve1 != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localReserve1));
				} else {
					throw new org.apache.axis2.databinding.ADBException("reserve1 cannot be null!!");
				}
			}
			if (localReserve2Tracker) {
				elementList.add(new javax.xml.namespace.QName("", "reserve2"));

				if (localReserve2 != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localReserve2));
				} else {
					throw new org.apache.axis2.databinding.ADBException("reserve2 cannot be null!!");
				}
			}
			if (localReserve3Tracker) {
				elementList.add(new javax.xml.namespace.QName("", "reserve3"));

				if (localReserve3 != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localReserve3));
				} else {
					throw new org.apache.axis2.databinding.ADBException("reserve3 cannot be null!!");
				}
			}
			if (localReserve4Tracker) {
				elementList.add(new javax.xml.namespace.QName("", "reserve4"));

				if (localReserve4 != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localReserve4));
				} else {
					throw new org.apache.axis2.databinding.ADBException("reserve4 cannot be null!!");
				}
			}
			if (localReserve5Tracker) {
				elementList.add(new javax.xml.namespace.QName("", "reserve5"));

				if (localReserve5 != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localReserve5));
				} else {
					throw new org.apache.axis2.databinding.ADBException("reserve5 cannot be null!!");
				}
			}
			if (localReserve6Tracker) {
				elementList.add(new javax.xml.namespace.QName("", "reserve6"));

				if (localReserve6 != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localReserve6));
				} else {
					throw new org.apache.axis2.databinding.ADBException("reserve6 cannot be null!!");
				}
			}
			if (localReserve7Tracker) {
				elementList.add(new javax.xml.namespace.QName("", "reserve7"));

				if (localReserve7 != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localReserve7));
				} else {
					throw new org.apache.axis2.databinding.ADBException("reserve7 cannot be null!!");
				}
			}
			if (localReserve8Tracker) {
				elementList.add(new javax.xml.namespace.QName("", "reserve8"));

				if (localReserve8 != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localReserve8));
				} else {
					throw new org.apache.axis2.databinding.ADBException("reserve8 cannot be null!!");
				}
			}
			if (localReserve9Tracker) {
				elementList.add(new javax.xml.namespace.QName("", "reserve9"));

				if (localReserve9 != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localReserve9));
				} else {
					throw new org.apache.axis2.databinding.ADBException("reserve9 cannot be null!!");
				}
			}
			if (localReserve10Tracker) {
				elementList.add(new javax.xml.namespace.QName("", "reserve10"));

				if (localReserve10 != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localReserve10));
				} else {
					throw new org.apache.axis2.databinding.ADBException("reserve10 cannot be null!!");
				}
			}

			return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName, elementList.toArray(), attribList.toArray());

		}

		/**
		 * Factory class that keeps the parse method
		 */
		public static class Factory {

			/**
			 * static method to create the object
			 * Precondition: If this object is an element, the current or next start element starts this object and any intervening reader events are
			 * ignorable
			 * If this object is not an element, it is a complex type and the reader is at the event just after the outer start element
			 * Postcondition: If this object is an element, the reader is positioned at its end element
			 * If this object is a complex type, the reader is positioned at the end element of its outer element
			 */
			public static FMT_CRMS_SVR_S01001010021003_IN_SUB parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception {
				FMT_CRMS_SVR_S01001010021003_IN_SUB object = new FMT_CRMS_SVR_S01001010021003_IN_SUB();

				int event;
				java.lang.String nillableValue = null;
				java.lang.String prefix = "";
				java.lang.String namespaceuri = "";
				try {

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "type") != null) {
						java.lang.String fullTypeName = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "type");
						if (fullTypeName != null) {
							java.lang.String nsPrefix = null;
							if (fullTypeName.indexOf(":") > -1) {
								nsPrefix = fullTypeName.substring(0, fullTypeName.indexOf(":"));
							}
							nsPrefix = nsPrefix == null ? "" : nsPrefix;

							java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":") + 1);

							if (!"FMT_CRMS_SVR_S01001010021003_IN_SUB".equals(type)) {
								// find namespace for the prefix
								java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
								return (FMT_CRMS_SVR_S01001010021003_IN_SUB) ExtensionMapper.getTypeObject(nsUri, type, reader);
							}

						}

					}

					// Note all attributes that were handled. Used to differ normal attributes
					// from anyAttributes.
					java.util.Vector handledAttributes = new java.util.Vector();

					reader.next();

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "promNoteNo").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setPromNoteNo(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "billNo").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setBillNo(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "billMoney").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setBillMoney(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "remitterCustNo").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setRemitterCustNo(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "remitterAcctNo").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setRemitterAcctNo(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "remitter").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setRemitter(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "remitterBankNo").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setRemitterBankNo(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "remitterBankName").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setRemitterBankName(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "draweeBankNo").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setDraweeBankNo(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "draweeBankName").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setDraweeBankName(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "draweeBankAddr").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setDraweeBankAddr(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "payeeAcctNo").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setPayeeAcctNo(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "payee").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setPayee(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "payeeBankName").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setPayeeBankName(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "payeeBankNo").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setPayeeBankNo(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "acptDt").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setAcptDt(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "dueDt").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setDueDt(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "acceptorAcctNo").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setAcceptorAcctNo(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "acceptor").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setAcceptor(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "acceptorBankNo").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setAcceptorBankNo(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "isAccp").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setIsAccp(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "ifSameCity").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setIfSameCity(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "forbidFlag").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setForbidFlag(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "delayDays").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setDelayDays(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "interestCalDays").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setInterestCalDays(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "interest").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setInterest(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "salerInterest").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setSalerInterest(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "buyerInterest").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setBuyerInterest(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "thridInterest").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setThridInterest(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "payMoney").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setPayMoney(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "remarks").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setRemarks(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "inqRepFlag").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setInqRepFlag(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "inqRepType").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setInqRepType(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "acceptorBankName").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setAcceptorBankName(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "putoutNo").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setPutoutNo(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "bailsum").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setBailsum(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "bailacurrency").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setBailacurrency(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "bailaccountId").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setBailaccountId(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "bailaccountNo").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setBailaccountNo(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "fee").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setFee(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "commitFee").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setCommitFee(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "pledgeNo").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setPledgeNo(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "origin").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setOrigin(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "flag").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setFlag(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "interestFalg").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setInterestFalg(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "reserve1").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setReserve1(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "reserve2").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setReserve2(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "reserve3").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setReserve3(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "reserve4").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setReserve4(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "reserve5").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setReserve5(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "reserve6").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setReserve6(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "reserve7").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setReserve7(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "reserve8").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setReserve8(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "reserve9").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setReserve9(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "reserve10").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setReserve10(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement())
						// A start element we are not expecting indicates a trailing invalid property
						throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());

				} catch (javax.xml.stream.XMLStreamException e) {
					throw new java.lang.Exception(e);
				}

				return object;
			}

		}// end of factory class

	}

	public static class FMT_CRMS_SVR_S01001010021003_OUT implements org.apache.axis2.databinding.ADBBean {
		/*
		 * This type was generated from the piece of schema that had
		 * name = FMT_CRMS_SVR_S01001010021003_OUT
		 * Namespace URI = http://www.adtec.com.cn
		 * Namespace Prefix = ns1
		 */

		private static java.lang.String generatePrefix(java.lang.String namespace) {
			if (namespace.equals("http://www.adtec.com.cn")) {
				return "ns1";
			}
			return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
		}

		/**
		 * field for Reserve1
		 */

		protected java.lang.String localReserve1;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localReserve1Tracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getReserve1() {
			return localReserve1;
		}

		/**
		 * Auto generated setter method
		 * @param param Reserve1
		 */
		public void setReserve1(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localReserve1Tracker = true;
			} else {
				localReserve1Tracker = false;

			}

			this.localReserve1 = param;

		}

		/**
		 * field for Reserve2
		 */

		protected java.lang.String localReserve2;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localReserve2Tracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getReserve2() {
			return localReserve2;
		}

		/**
		 * Auto generated setter method
		 * @param param Reserve2
		 */
		public void setReserve2(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localReserve2Tracker = true;
			} else {
				localReserve2Tracker = false;

			}

			this.localReserve2 = param;

		}

		/**
		 * field for Reserve3
		 */

		protected java.lang.String localReserve3;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localReserve3Tracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getReserve3() {
			return localReserve3;
		}

		/**
		 * Auto generated setter method
		 * @param param Reserve3
		 */
		public void setReserve3(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localReserve3Tracker = true;
			} else {
				localReserve3Tracker = false;

			}

			this.localReserve3 = param;

		}

		/**
		 * field for Reserve4
		 */

		protected java.lang.String localReserve4;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localReserve4Tracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getReserve4() {
			return localReserve4;
		}

		/**
		 * Auto generated setter method
		 * @param param Reserve4
		 */
		public void setReserve4(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localReserve4Tracker = true;
			} else {
				localReserve4Tracker = false;

			}

			this.localReserve4 = param;

		}

		/**
		 * field for Reserve5
		 */

		protected java.lang.String localReserve5;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localReserve5Tracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getReserve5() {
			return localReserve5;
		}

		/**
		 * Auto generated setter method
		 * @param param Reserve5
		 */
		public void setReserve5(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localReserve5Tracker = true;
			} else {
				localReserve5Tracker = false;

			}

			this.localReserve5 = param;

		}

		/**
		 * field for Reserve6
		 */

		protected java.lang.String localReserve6;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localReserve6Tracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getReserve6() {
			return localReserve6;
		}

		/**
		 * Auto generated setter method
		 * @param param Reserve6
		 */
		public void setReserve6(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localReserve6Tracker = true;
			} else {
				localReserve6Tracker = false;

			}

			this.localReserve6 = param;

		}

		/**
		 * field for Reserve7
		 */

		protected java.lang.String localReserve7;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localReserve7Tracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getReserve7() {
			return localReserve7;
		}

		/**
		 * Auto generated setter method
		 * @param param Reserve7
		 */
		public void setReserve7(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localReserve7Tracker = true;
			} else {
				localReserve7Tracker = false;

			}

			this.localReserve7 = param;

		}

		/**
		 * field for Reserve8
		 */

		protected java.lang.String localReserve8;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localReserve8Tracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getReserve8() {
			return localReserve8;
		}

		/**
		 * Auto generated setter method
		 * @param param Reserve8
		 */
		public void setReserve8(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localReserve8Tracker = true;
			} else {
				localReserve8Tracker = false;

			}

			this.localReserve8 = param;

		}

		/**
		 * field for Reserve9
		 */

		protected java.lang.String localReserve9;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localReserve9Tracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getReserve9() {
			return localReserve9;
		}

		/**
		 * Auto generated setter method
		 * @param param Reserve9
		 */
		public void setReserve9(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localReserve9Tracker = true;
			} else {
				localReserve9Tracker = false;

			}

			this.localReserve9 = param;

		}

		/**
		 * field for Reserve10
		 */

		protected java.lang.String localReserve10;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localReserve10Tracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getReserve10() {
			return localReserve10;
		}

		/**
		 * Auto generated setter method
		 * @param param Reserve10
		 */
		public void setReserve10(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localReserve10Tracker = true;
			} else {
				localReserve10Tracker = false;

			}

			this.localReserve10 = param;

		}

		/**
		 * isReaderMTOMAware
		 * @return true if the reader supports MTOM
		 */
		public static boolean isReaderMTOMAware(javax.xml.stream.XMLStreamReader reader) {
			boolean isReaderMTOMAware = false;

			try {
				isReaderMTOMAware = java.lang.Boolean.TRUE.equals(reader.getProperty(org.apache.axiom.om.OMConstants.IS_DATA_HANDLERS_AWARE));
			} catch (java.lang.IllegalArgumentException e) {
				isReaderMTOMAware = false;
			}
			return isReaderMTOMAware;
		}

		/**
		 * @param parentQName
		 * @param factory
		 * @return org.apache.axiom.om.OMElement
		 */
		public org.apache.axiom.om.OMElement getOMElement(final javax.xml.namespace.QName parentQName, final org.apache.axiom.om.OMFactory factory) throws org.apache.axis2.databinding.ADBException {

			org.apache.axiom.om.OMDataSource dataSource = new org.apache.axis2.databinding.ADBDataSource(this, parentQName) {

				public void serialize(org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
					FMT_CRMS_SVR_S01001010021003_OUT.this.serialize(parentQName, factory, xmlWriter);
				}
			};
			return new org.apache.axiom.om.impl.llom.OMSourcedElementImpl(parentQName, factory, dataSource);

		}

		public void serialize(final javax.xml.namespace.QName parentQName, final org.apache.axiom.om.OMFactory factory, org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {

			java.lang.String prefix = null;
			java.lang.String namespace = null;

			prefix = parentQName.getPrefix();
			namespace = parentQName.getNamespaceURI();

			if (namespace != null) {
				java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
				if (writerPrefix != null) {
					xmlWriter.writeStartElement(namespace, parentQName.getLocalPart());
				} else {
					if (prefix == null) {
						prefix = generatePrefix(namespace);
					}

					xmlWriter.writeStartElement(prefix, parentQName.getLocalPart(), namespace);
					xmlWriter.writeNamespace(prefix, namespace);
					xmlWriter.setPrefix(prefix, namespace);
				}
			} else {
				xmlWriter.writeStartElement(parentQName.getLocalPart());
			}
			if (localReserve1Tracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "reserve1", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "reserve1");
					}

				} else {
					xmlWriter.writeStartElement("reserve1");
				}

				if (localReserve1 == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("reserve1 cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localReserve1);

				}

				xmlWriter.writeEndElement();
			}
			if (localReserve2Tracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "reserve2", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "reserve2");
					}

				} else {
					xmlWriter.writeStartElement("reserve2");
				}

				if (localReserve2 == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("reserve2 cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localReserve2);

				}

				xmlWriter.writeEndElement();
			}
			if (localReserve3Tracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "reserve3", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "reserve3");
					}

				} else {
					xmlWriter.writeStartElement("reserve3");
				}

				if (localReserve3 == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("reserve3 cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localReserve3);

				}

				xmlWriter.writeEndElement();
			}
			if (localReserve4Tracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "reserve4", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "reserve4");
					}

				} else {
					xmlWriter.writeStartElement("reserve4");
				}

				if (localReserve4 == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("reserve4 cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localReserve4);

				}

				xmlWriter.writeEndElement();
			}
			if (localReserve5Tracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "reserve5", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "reserve5");
					}

				} else {
					xmlWriter.writeStartElement("reserve5");
				}

				if (localReserve5 == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("reserve5 cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localReserve5);

				}

				xmlWriter.writeEndElement();
			}
			if (localReserve6Tracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "reserve6", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "reserve6");
					}

				} else {
					xmlWriter.writeStartElement("reserve6");
				}

				if (localReserve6 == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("reserve6 cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localReserve6);

				}

				xmlWriter.writeEndElement();
			}
			if (localReserve7Tracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "reserve7", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "reserve7");
					}

				} else {
					xmlWriter.writeStartElement("reserve7");
				}

				if (localReserve7 == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("reserve7 cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localReserve7);

				}

				xmlWriter.writeEndElement();
			}
			if (localReserve8Tracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "reserve8", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "reserve8");
					}

				} else {
					xmlWriter.writeStartElement("reserve8");
				}

				if (localReserve8 == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("reserve8 cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localReserve8);

				}

				xmlWriter.writeEndElement();
			}
			if (localReserve9Tracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "reserve9", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "reserve9");
					}

				} else {
					xmlWriter.writeStartElement("reserve9");
				}

				if (localReserve9 == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("reserve9 cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localReserve9);

				}

				xmlWriter.writeEndElement();
			}
			if (localReserve10Tracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "reserve10", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "reserve10");
					}

				} else {
					xmlWriter.writeStartElement("reserve10");
				}

				if (localReserve10 == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("reserve10 cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localReserve10);

				}

				xmlWriter.writeEndElement();
			}
			xmlWriter.writeEndElement();

		}

		/**
		 * Util method to write an attribute with the ns prefix
		 */
		private void writeAttribute(java.lang.String prefix, java.lang.String namespace, java.lang.String attName, java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			if (xmlWriter.getPrefix(namespace) == null) {
				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);

			}

			xmlWriter.writeAttribute(namespace, attName, attValue);

		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeAttribute(java.lang.String namespace, java.lang.String attName, java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			if (namespace.equals("")) {
				xmlWriter.writeAttribute(attName, attValue);
			} else {
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attValue);
			}
		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeQNameAttribute(java.lang.String namespace, java.lang.String attName, javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {

			java.lang.String attributeNamespace = qname.getNamespaceURI();
			java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
			if (attributePrefix == null) {
				attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
			}
			java.lang.String attributeValue;
			if (attributePrefix.trim().length() > 0) {
				attributeValue = attributePrefix + ":" + qname.getLocalPart();
			} else {
				attributeValue = qname.getLocalPart();
			}

			if (namespace.equals("")) {
				xmlWriter.writeAttribute(attName, attributeValue);
			} else {
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attributeValue);
			}
		}

		/**
		 * method to handle Qnames
		 */

		private void writeQName(javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
			java.lang.String namespaceURI = qname.getNamespaceURI();
			if (namespaceURI != null) {
				java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
				if (prefix == null) {
					prefix = generatePrefix(namespaceURI);
					xmlWriter.writeNamespace(prefix, namespaceURI);
					xmlWriter.setPrefix(prefix, namespaceURI);
				}

				if (prefix.trim().length() > 0) {
					xmlWriter.writeCharacters(prefix + ":" + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
				} else {
					// i.e this is the default namespace
					xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
				}

			} else {
				xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
			}
		}

		private void writeQNames(javax.xml.namespace.QName[] qnames, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

			if (qnames != null) {
				// we have to store this data until last moment since it is not possible to write any
				// namespace data after writing the charactor data
				java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
				java.lang.String namespaceURI = null;
				java.lang.String prefix = null;

				for (int i = 0; i < qnames.length; i++) {
					if (i > 0) {
						stringToWrite.append(" ");
					}
					namespaceURI = qnames[i].getNamespaceURI();
					if (namespaceURI != null) {
						prefix = xmlWriter.getPrefix(namespaceURI);
						if ((prefix == null) || (prefix.length() == 0)) {
							prefix = generatePrefix(namespaceURI);
							xmlWriter.writeNamespace(prefix, namespaceURI);
							xmlWriter.setPrefix(prefix, namespaceURI);
						}

						if (prefix.trim().length() > 0) {
							stringToWrite.append(prefix).append(":").append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
						} else {
							stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
						}
					} else {
						stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
					}
				}
				xmlWriter.writeCharacters(stringToWrite.toString());
			}

		}

		/**
		 * Register a namespace prefix
		 */
		private java.lang.String registerPrefix(javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace) throws javax.xml.stream.XMLStreamException {
			java.lang.String prefix = xmlWriter.getPrefix(namespace);

			if (prefix == null) {
				prefix = generatePrefix(namespace);

				while (xmlWriter.getNamespaceContext().getNamespaceURI(prefix) != null) {
					prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
				}

				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);
			}

			return prefix;
		}

		/**
		 * databinding method to get an XML representation of this object
		 */
		public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName) throws org.apache.axis2.databinding.ADBException {

			java.util.ArrayList elementList = new java.util.ArrayList();
			java.util.ArrayList attribList = new java.util.ArrayList();

			if (localReserve1Tracker) {
				elementList.add(new javax.xml.namespace.QName("", "reserve1"));

				if (localReserve1 != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localReserve1));
				} else {
					throw new org.apache.axis2.databinding.ADBException("reserve1 cannot be null!!");
				}
			}
			if (localReserve2Tracker) {
				elementList.add(new javax.xml.namespace.QName("", "reserve2"));

				if (localReserve2 != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localReserve2));
				} else {
					throw new org.apache.axis2.databinding.ADBException("reserve2 cannot be null!!");
				}
			}
			if (localReserve3Tracker) {
				elementList.add(new javax.xml.namespace.QName("", "reserve3"));

				if (localReserve3 != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localReserve3));
				} else {
					throw new org.apache.axis2.databinding.ADBException("reserve3 cannot be null!!");
				}
			}
			if (localReserve4Tracker) {
				elementList.add(new javax.xml.namespace.QName("", "reserve4"));

				if (localReserve4 != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localReserve4));
				} else {
					throw new org.apache.axis2.databinding.ADBException("reserve4 cannot be null!!");
				}
			}
			if (localReserve5Tracker) {
				elementList.add(new javax.xml.namespace.QName("", "reserve5"));

				if (localReserve5 != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localReserve5));
				} else {
					throw new org.apache.axis2.databinding.ADBException("reserve5 cannot be null!!");
				}
			}
			if (localReserve6Tracker) {
				elementList.add(new javax.xml.namespace.QName("", "reserve6"));

				if (localReserve6 != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localReserve6));
				} else {
					throw new org.apache.axis2.databinding.ADBException("reserve6 cannot be null!!");
				}
			}
			if (localReserve7Tracker) {
				elementList.add(new javax.xml.namespace.QName("", "reserve7"));

				if (localReserve7 != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localReserve7));
				} else {
					throw new org.apache.axis2.databinding.ADBException("reserve7 cannot be null!!");
				}
			}
			if (localReserve8Tracker) {
				elementList.add(new javax.xml.namespace.QName("", "reserve8"));

				if (localReserve8 != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localReserve8));
				} else {
					throw new org.apache.axis2.databinding.ADBException("reserve8 cannot be null!!");
				}
			}
			if (localReserve9Tracker) {
				elementList.add(new javax.xml.namespace.QName("", "reserve9"));

				if (localReserve9 != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localReserve9));
				} else {
					throw new org.apache.axis2.databinding.ADBException("reserve9 cannot be null!!");
				}
			}
			if (localReserve10Tracker) {
				elementList.add(new javax.xml.namespace.QName("", "reserve10"));

				if (localReserve10 != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localReserve10));
				} else {
					throw new org.apache.axis2.databinding.ADBException("reserve10 cannot be null!!");
				}
			}

			return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName, elementList.toArray(), attribList.toArray());

		}

		/**
		 * Factory class that keeps the parse method
		 */
		public static class Factory {

			/**
			 * static method to create the object
			 * Precondition: If this object is an element, the current or next start element starts this object and any intervening reader events are
			 * ignorable
			 * If this object is not an element, it is a complex type and the reader is at the event just after the outer start element
			 * Postcondition: If this object is an element, the reader is positioned at its end element
			 * If this object is a complex type, the reader is positioned at the end element of its outer element
			 */
			public static FMT_CRMS_SVR_S01001010021003_OUT parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception {
				FMT_CRMS_SVR_S01001010021003_OUT object = new FMT_CRMS_SVR_S01001010021003_OUT();

				int event;
				java.lang.String nillableValue = null;
				java.lang.String prefix = "";
				java.lang.String namespaceuri = "";
				try {

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "type") != null) {
						java.lang.String fullTypeName = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "type");
						if (fullTypeName != null) {
							java.lang.String nsPrefix = null;
							if (fullTypeName.indexOf(":") > -1) {
								nsPrefix = fullTypeName.substring(0, fullTypeName.indexOf(":"));
							}
							nsPrefix = nsPrefix == null ? "" : nsPrefix;

							java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":") + 1);

							if (!"FMT_CRMS_SVR_S01001010021003_OUT".equals(type)) {
								// find namespace for the prefix
								java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
								return (FMT_CRMS_SVR_S01001010021003_OUT) ExtensionMapper.getTypeObject(nsUri, type, reader);
							}

						}

					}

					// Note all attributes that were handled. Used to differ normal attributes
					// from anyAttributes.
					java.util.Vector handledAttributes = new java.util.Vector();

					reader.next();

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "reserve1").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setReserve1(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "reserve2").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setReserve2(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "reserve3").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setReserve3(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "reserve4").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setReserve4(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "reserve5").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setReserve5(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "reserve6").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setReserve6(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "reserve7").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setReserve7(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "reserve8").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setReserve8(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "reserve9").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setReserve9(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "reserve10").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setReserve10(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement())
						// A start element we are not expecting indicates a trailing invalid property
						throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());

				} catch (javax.xml.stream.XMLStreamException e) {
					throw new java.lang.Exception(e);
				}

				return object;
			}

		}// end of factory class

	}

	public static class FMT_SOAP_UTF8_RequestHeader implements org.apache.axis2.databinding.ADBBean {
		/*
		 * This type was generated from the piece of schema that had
		 * name = FMT_SOAP_UTF8_RequestHeader
		 * Namespace URI = http://www.adtec.com.cn
		 * Namespace Prefix = ns1
		 */

		private static java.lang.String generatePrefix(java.lang.String namespace) {
			if (namespace.equals("http://www.adtec.com.cn")) {
				return "ns1";
			}
			return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
		}

		/**
		 * field for VersionNo
		 */

		protected java.lang.String localVersionNo;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localVersionNoTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getVersionNo() {
			return localVersionNo;
		}

		/**
		 * Auto generated setter method
		 * @param param VersionNo
		 */
		public void setVersionNo(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localVersionNoTracker = true;
			} else {
				localVersionNoTracker = false;

			}

			this.localVersionNo = param;

		}

		/**
		 * field for ReqSysCode
		 */

		protected java.lang.String localReqSysCode;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localReqSysCodeTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getReqSysCode() {
			return localReqSysCode;
		}

		/**
		 * Auto generated setter method
		 * @param param ReqSysCode
		 */
		public void setReqSysCode(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localReqSysCodeTracker = true;
			} else {
				localReqSysCodeTracker = false;

			}

			this.localReqSysCode = param;

		}

		/**
		 * field for ReqSecCode
		 */

		protected java.lang.String localReqSecCode;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localReqSecCodeTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getReqSecCode() {
			return localReqSecCode;
		}

		/**
		 * Auto generated setter method
		 * @param param ReqSecCode
		 */
		public void setReqSecCode(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localReqSecCodeTracker = true;
			} else {
				localReqSecCodeTracker = false;

			}

			this.localReqSecCode = param;

		}

		/**
		 * field for TxType
		 */

		protected java.lang.String localTxType;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localTxTypeTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getTxType() {
			return localTxType;
		}

		/**
		 * Auto generated setter method
		 * @param param TxType
		 */
		public void setTxType(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localTxTypeTracker = true;
			} else {
				localTxTypeTracker = false;

			}

			this.localTxType = param;

		}

		/**
		 * field for TxMode
		 */

		protected java.lang.String localTxMode;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localTxModeTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getTxMode() {
			return localTxMode;
		}

		/**
		 * Auto generated setter method
		 * @param param TxMode
		 */
		public void setTxMode(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localTxModeTracker = true;
			} else {
				localTxModeTracker = false;

			}

			this.localTxMode = param;

		}

		/**
		 * field for TxCode
		 */

		protected java.lang.String localTxCode;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localTxCodeTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getTxCode() {
			return localTxCode;
		}

		/**
		 * Auto generated setter method
		 * @param param TxCode
		 */
		public void setTxCode(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localTxCodeTracker = true;
			} else {
				localTxCodeTracker = false;

			}

			this.localTxCode = param;

		}

		/**
		 * field for ReqDate
		 */

		protected java.lang.String localReqDate;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localReqDateTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getReqDate() {
			return localReqDate;
		}

		/**
		 * Auto generated setter method
		 * @param param ReqDate
		 */
		public void setReqDate(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localReqDateTracker = true;
			} else {
				localReqDateTracker = false;

			}

			this.localReqDate = param;

		}

		/**
		 * field for ReqTime
		 */

		protected java.lang.String localReqTime;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localReqTimeTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getReqTime() {
			return localReqTime;
		}

		/**
		 * Auto generated setter method
		 * @param param ReqTime
		 */
		public void setReqTime(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localReqTimeTracker = true;
			} else {
				localReqTimeTracker = false;

			}

			this.localReqTime = param;

		}

		/**
		 * field for ReqSeqNo
		 */

		protected java.lang.String localReqSeqNo;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localReqSeqNoTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getReqSeqNo() {
			return localReqSeqNo;
		}

		/**
		 * Auto generated setter method
		 * @param param ReqSeqNo
		 */
		public void setReqSeqNo(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localReqSeqNoTracker = true;
			} else {
				localReqSeqNoTracker = false;

			}

			this.localReqSeqNo = param;

		}

		/**
		 * field for ChanlNo
		 */

		protected java.lang.String localChanlNo;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localChanlNoTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getChanlNo() {
			return localChanlNo;
		}

		/**
		 * Auto generated setter method
		 * @param param ChanlNo
		 */
		public void setChanlNo(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localChanlNoTracker = true;
			} else {
				localChanlNoTracker = false;

			}

			this.localChanlNo = param;

		}

		/**
		 * field for Brch
		 */

		protected java.lang.String localBrch;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localBrchTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getBrch() {
			return localBrch;
		}

		/**
		 * Auto generated setter method
		 * @param param Brch
		 */
		public void setBrch(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localBrchTracker = true;
			} else {
				localBrchTracker = false;

			}

			this.localBrch = param;

		}

		/**
		 * field for TermNo
		 */

		protected java.lang.String localTermNo;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localTermNoTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getTermNo() {
			return localTermNo;
		}

		/**
		 * Auto generated setter method
		 * @param param TermNo
		 */
		public void setTermNo(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localTermNoTracker = true;
			} else {
				localTermNoTracker = false;

			}

			this.localTermNo = param;

		}

		/**
		 * field for Oper
		 */

		protected java.lang.String localOper;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localOperTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getOper() {
			return localOper;
		}

		/**
		 * Auto generated setter method
		 * @param param Oper
		 */
		public void setOper(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localOperTracker = true;
			} else {
				localOperTracker = false;

			}

			this.localOper = param;

		}

		/**
		 * field for SendFileName
		 */

		protected java.lang.String localSendFileName;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localSendFileNameTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getSendFileName() {
			return localSendFileName;
		}

		/**
		 * Auto generated setter method
		 * @param param SendFileName
		 */
		public void setSendFileName(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localSendFileNameTracker = true;
			} else {
				localSendFileNameTracker = false;

			}

			this.localSendFileName = param;

		}

		/**
		 * field for BeginRec
		 */

		protected java.lang.String localBeginRec;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localBeginRecTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getBeginRec() {
			return localBeginRec;
		}

		/**
		 * Auto generated setter method
		 * @param param BeginRec
		 */
		public void setBeginRec(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localBeginRecTracker = true;
			} else {
				localBeginRecTracker = false;

			}

			this.localBeginRec = param;

		}

		/**
		 * field for MaxRec
		 */

		protected java.math.BigInteger localMaxRec;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localMaxRecTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.math.BigInteger
		 */
		public java.math.BigInteger getMaxRec() {
			return localMaxRec;
		}

		/**
		 * Auto generated setter method
		 * @param param MaxRec
		 */
		public void setMaxRec(java.math.BigInteger param) {

			if (param != null) {
				// update the setting tracker
				localMaxRecTracker = true;
			} else {
				localMaxRecTracker = false;

			}

			this.localMaxRec = param;

		}

		/**
		 * field for FileHMac
		 */

		protected java.lang.String localFileHMac;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localFileHMacTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getFileHMac() {
			return localFileHMac;
		}

		/**
		 * Auto generated setter method
		 * @param param FileHMac
		 */
		public void setFileHMac(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localFileHMacTracker = true;
			} else {
				localFileHMacTracker = false;

			}

			this.localFileHMac = param;

		}

		/**
		 * field for HMac
		 */

		protected java.lang.String localHMac;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localHMacTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getHMac() {
			return localHMac;
		}

		/**
		 * Auto generated setter method
		 * @param param HMac
		 */
		public void setHMac(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localHMacTracker = true;
			} else {
				localHMacTracker = false;

			}

			this.localHMac = param;

		}

		/**
		 * isReaderMTOMAware
		 * @return true if the reader supports MTOM
		 */
		public static boolean isReaderMTOMAware(javax.xml.stream.XMLStreamReader reader) {
			boolean isReaderMTOMAware = false;

			try {
				isReaderMTOMAware = java.lang.Boolean.TRUE.equals(reader.getProperty(org.apache.axiom.om.OMConstants.IS_DATA_HANDLERS_AWARE));
			} catch (java.lang.IllegalArgumentException e) {
				isReaderMTOMAware = false;
			}
			return isReaderMTOMAware;
		}

		/**
		 * @param parentQName
		 * @param factory
		 * @return org.apache.axiom.om.OMElement
		 */
		public org.apache.axiom.om.OMElement getOMElement(final javax.xml.namespace.QName parentQName, final org.apache.axiom.om.OMFactory factory) throws org.apache.axis2.databinding.ADBException {

			org.apache.axiom.om.OMDataSource dataSource = new org.apache.axis2.databinding.ADBDataSource(this, parentQName) {

				public void serialize(org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
					FMT_SOAP_UTF8_RequestHeader.this.serialize(parentQName, factory, xmlWriter);
				}
			};
			return new org.apache.axiom.om.impl.llom.OMSourcedElementImpl(parentQName, factory, dataSource);

		}

		public void serialize(final javax.xml.namespace.QName parentQName, final org.apache.axiom.om.OMFactory factory, org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {

			java.lang.String prefix = null;
			java.lang.String namespace = null;

			prefix = parentQName.getPrefix();
			namespace = parentQName.getNamespaceURI();

			if (namespace != null) {
				java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
				if (writerPrefix != null) {
					xmlWriter.writeStartElement(namespace, parentQName.getLocalPart());
				} else {
					if (prefix == null) {
						prefix = generatePrefix(namespace);
					}

					xmlWriter.writeStartElement(prefix, parentQName.getLocalPart(), namespace);
					xmlWriter.writeNamespace(prefix, namespace);
					xmlWriter.setPrefix(prefix, namespace);
				}
			} else {
				xmlWriter.writeStartElement(parentQName.getLocalPart());
			}
			if (localVersionNoTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "VersionNo", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "VersionNo");
					}

				} else {
					xmlWriter.writeStartElement("VersionNo");
				}

				if (localVersionNo == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("VersionNo cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localVersionNo);

				}

				xmlWriter.writeEndElement();
			}
			if (localReqSysCodeTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "ReqSysCode", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "ReqSysCode");
					}

				} else {
					xmlWriter.writeStartElement("ReqSysCode");
				}

				if (localReqSysCode == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("ReqSysCode cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localReqSysCode);

				}

				xmlWriter.writeEndElement();
			}
			if (localReqSecCodeTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "ReqSecCode", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "ReqSecCode");
					}

				} else {
					xmlWriter.writeStartElement("ReqSecCode");
				}

				if (localReqSecCode == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("ReqSecCode cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localReqSecCode);

				}

				xmlWriter.writeEndElement();
			}
			if (localTxTypeTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "TxType", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "TxType");
					}

				} else {
					xmlWriter.writeStartElement("TxType");
				}

				if (localTxType == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("TxType cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localTxType);

				}

				xmlWriter.writeEndElement();
			}
			if (localTxModeTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "TxMode", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "TxMode");
					}

				} else {
					xmlWriter.writeStartElement("TxMode");
				}

				if (localTxMode == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("TxMode cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localTxMode);

				}

				xmlWriter.writeEndElement();
			}
			if (localTxCodeTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "TxCode", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "TxCode");
					}

				} else {
					xmlWriter.writeStartElement("TxCode");
				}

				if (localTxCode == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("TxCode cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localTxCode);

				}

				xmlWriter.writeEndElement();
			}
			if (localReqDateTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "ReqDate", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "ReqDate");
					}

				} else {
					xmlWriter.writeStartElement("ReqDate");
				}

				if (localReqDate == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("ReqDate cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localReqDate);

				}

				xmlWriter.writeEndElement();
			}
			if (localReqTimeTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "ReqTime", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "ReqTime");
					}

				} else {
					xmlWriter.writeStartElement("ReqTime");
				}

				if (localReqTime == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("ReqTime cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localReqTime);

				}

				xmlWriter.writeEndElement();
			}
			if (localReqSeqNoTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "ReqSeqNo", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "ReqSeqNo");
					}

				} else {
					xmlWriter.writeStartElement("ReqSeqNo");
				}

				if (localReqSeqNo == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("ReqSeqNo cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localReqSeqNo);

				}

				xmlWriter.writeEndElement();
			}
			if (localChanlNoTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "ChanlNo", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "ChanlNo");
					}

				} else {
					xmlWriter.writeStartElement("ChanlNo");
				}

				if (localChanlNo == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("ChanlNo cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localChanlNo);

				}

				xmlWriter.writeEndElement();
			}
			if (localBrchTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "Brch", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "Brch");
					}

				} else {
					xmlWriter.writeStartElement("Brch");
				}

				if (localBrch == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("Brch cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localBrch);

				}

				xmlWriter.writeEndElement();
			}
			if (localTermNoTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "TermNo", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "TermNo");
					}

				} else {
					xmlWriter.writeStartElement("TermNo");
				}

				if (localTermNo == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("TermNo cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localTermNo);

				}

				xmlWriter.writeEndElement();
			}
			if (localOperTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "Oper", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "Oper");
					}

				} else {
					xmlWriter.writeStartElement("Oper");
				}

				if (localOper == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("Oper cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localOper);

				}

				xmlWriter.writeEndElement();
			}
			if (localSendFileNameTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "SendFileName", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "SendFileName");
					}

				} else {
					xmlWriter.writeStartElement("SendFileName");
				}

				if (localSendFileName == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("SendFileName cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localSendFileName);

				}

				xmlWriter.writeEndElement();
			}
			if (localBeginRecTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "BeginRec", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "BeginRec");
					}

				} else {
					xmlWriter.writeStartElement("BeginRec");
				}

				if (localBeginRec == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("BeginRec cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localBeginRec);

				}

				xmlWriter.writeEndElement();
			}
			if (localMaxRecTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "MaxRec", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "MaxRec");
					}

				} else {
					xmlWriter.writeStartElement("MaxRec");
				}

				if (localMaxRec == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("MaxRec cannot be null!!");

				} else {

					xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localMaxRec));

				}

				xmlWriter.writeEndElement();
			}
			if (localFileHMacTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "FileHMac", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "FileHMac");
					}

				} else {
					xmlWriter.writeStartElement("FileHMac");
				}

				if (localFileHMac == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("FileHMac cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localFileHMac);

				}

				xmlWriter.writeEndElement();
			}
			if (localHMacTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "HMac", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "HMac");
					}

				} else {
					xmlWriter.writeStartElement("HMac");
				}

				if (localHMac == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("HMac cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localHMac);

				}

				xmlWriter.writeEndElement();
			}
			xmlWriter.writeEndElement();

		}

		/**
		 * Util method to write an attribute with the ns prefix
		 */
		private void writeAttribute(java.lang.String prefix, java.lang.String namespace, java.lang.String attName, java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			if (xmlWriter.getPrefix(namespace) == null) {
				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);

			}

			xmlWriter.writeAttribute(namespace, attName, attValue);

		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeAttribute(java.lang.String namespace, java.lang.String attName, java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			if (namespace.equals("")) {
				xmlWriter.writeAttribute(attName, attValue);
			} else {
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attValue);
			}
		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeQNameAttribute(java.lang.String namespace, java.lang.String attName, javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {

			java.lang.String attributeNamespace = qname.getNamespaceURI();
			java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
			if (attributePrefix == null) {
				attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
			}
			java.lang.String attributeValue;
			if (attributePrefix.trim().length() > 0) {
				attributeValue = attributePrefix + ":" + qname.getLocalPart();
			} else {
				attributeValue = qname.getLocalPart();
			}

			if (namespace.equals("")) {
				xmlWriter.writeAttribute(attName, attributeValue);
			} else {
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attributeValue);
			}
		}

		/**
		 * method to handle Qnames
		 */

		private void writeQName(javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
			java.lang.String namespaceURI = qname.getNamespaceURI();
			if (namespaceURI != null) {
				java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
				if (prefix == null) {
					prefix = generatePrefix(namespaceURI);
					xmlWriter.writeNamespace(prefix, namespaceURI);
					xmlWriter.setPrefix(prefix, namespaceURI);
				}

				if (prefix.trim().length() > 0) {
					xmlWriter.writeCharacters(prefix + ":" + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
				} else {
					// i.e this is the default namespace
					xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
				}

			} else {
				xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
			}
		}

		private void writeQNames(javax.xml.namespace.QName[] qnames, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

			if (qnames != null) {
				// we have to store this data until last moment since it is not possible to write any
				// namespace data after writing the charactor data
				java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
				java.lang.String namespaceURI = null;
				java.lang.String prefix = null;

				for (int i = 0; i < qnames.length; i++) {
					if (i > 0) {
						stringToWrite.append(" ");
					}
					namespaceURI = qnames[i].getNamespaceURI();
					if (namespaceURI != null) {
						prefix = xmlWriter.getPrefix(namespaceURI);
						if ((prefix == null) || (prefix.length() == 0)) {
							prefix = generatePrefix(namespaceURI);
							xmlWriter.writeNamespace(prefix, namespaceURI);
							xmlWriter.setPrefix(prefix, namespaceURI);
						}

						if (prefix.trim().length() > 0) {
							stringToWrite.append(prefix).append(":").append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
						} else {
							stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
						}
					} else {
						stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
					}
				}
				xmlWriter.writeCharacters(stringToWrite.toString());
			}

		}

		/**
		 * Register a namespace prefix
		 */
		private java.lang.String registerPrefix(javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace) throws javax.xml.stream.XMLStreamException {
			java.lang.String prefix = xmlWriter.getPrefix(namespace);

			if (prefix == null) {
				prefix = generatePrefix(namespace);

				while (xmlWriter.getNamespaceContext().getNamespaceURI(prefix) != null) {
					prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
				}

				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);
			}

			return prefix;
		}

		/**
		 * databinding method to get an XML representation of this object
		 */
		public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName) throws org.apache.axis2.databinding.ADBException {

			java.util.ArrayList elementList = new java.util.ArrayList();
			java.util.ArrayList attribList = new java.util.ArrayList();

			if (localVersionNoTracker) {
				elementList.add(new javax.xml.namespace.QName("", "VersionNo"));

				if (localVersionNo != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localVersionNo));
				} else {
					throw new org.apache.axis2.databinding.ADBException("VersionNo cannot be null!!");
				}
			}
			if (localReqSysCodeTracker) {
				elementList.add(new javax.xml.namespace.QName("", "ReqSysCode"));

				if (localReqSysCode != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localReqSysCode));
				} else {
					throw new org.apache.axis2.databinding.ADBException("ReqSysCode cannot be null!!");
				}
			}
			if (localReqSecCodeTracker) {
				elementList.add(new javax.xml.namespace.QName("", "ReqSecCode"));

				if (localReqSecCode != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localReqSecCode));
				} else {
					throw new org.apache.axis2.databinding.ADBException("ReqSecCode cannot be null!!");
				}
			}
			if (localTxTypeTracker) {
				elementList.add(new javax.xml.namespace.QName("", "TxType"));

				if (localTxType != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localTxType));
				} else {
					throw new org.apache.axis2.databinding.ADBException("TxType cannot be null!!");
				}
			}
			if (localTxModeTracker) {
				elementList.add(new javax.xml.namespace.QName("", "TxMode"));

				if (localTxMode != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localTxMode));
				} else {
					throw new org.apache.axis2.databinding.ADBException("TxMode cannot be null!!");
				}
			}
			if (localTxCodeTracker) {
				elementList.add(new javax.xml.namespace.QName("", "TxCode"));

				if (localTxCode != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localTxCode));
				} else {
					throw new org.apache.axis2.databinding.ADBException("TxCode cannot be null!!");
				}
			}
			if (localReqDateTracker) {
				elementList.add(new javax.xml.namespace.QName("", "ReqDate"));

				if (localReqDate != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localReqDate));
				} else {
					throw new org.apache.axis2.databinding.ADBException("ReqDate cannot be null!!");
				}
			}
			if (localReqTimeTracker) {
				elementList.add(new javax.xml.namespace.QName("", "ReqTime"));

				if (localReqTime != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localReqTime));
				} else {
					throw new org.apache.axis2.databinding.ADBException("ReqTime cannot be null!!");
				}
			}
			if (localReqSeqNoTracker) {
				elementList.add(new javax.xml.namespace.QName("", "ReqSeqNo"));

				if (localReqSeqNo != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localReqSeqNo));
				} else {
					throw new org.apache.axis2.databinding.ADBException("ReqSeqNo cannot be null!!");
				}
			}
			if (localChanlNoTracker) {
				elementList.add(new javax.xml.namespace.QName("", "ChanlNo"));

				if (localChanlNo != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localChanlNo));
				} else {
					throw new org.apache.axis2.databinding.ADBException("ChanlNo cannot be null!!");
				}
			}
			if (localBrchTracker) {
				elementList.add(new javax.xml.namespace.QName("", "Brch"));

				if (localBrch != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localBrch));
				} else {
					throw new org.apache.axis2.databinding.ADBException("Brch cannot be null!!");
				}
			}
			if (localTermNoTracker) {
				elementList.add(new javax.xml.namespace.QName("", "TermNo"));

				if (localTermNo != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localTermNo));
				} else {
					throw new org.apache.axis2.databinding.ADBException("TermNo cannot be null!!");
				}
			}
			if (localOperTracker) {
				elementList.add(new javax.xml.namespace.QName("", "Oper"));

				if (localOper != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localOper));
				} else {
					throw new org.apache.axis2.databinding.ADBException("Oper cannot be null!!");
				}
			}
			if (localSendFileNameTracker) {
				elementList.add(new javax.xml.namespace.QName("", "SendFileName"));

				if (localSendFileName != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localSendFileName));
				} else {
					throw new org.apache.axis2.databinding.ADBException("SendFileName cannot be null!!");
				}
			}
			if (localBeginRecTracker) {
				elementList.add(new javax.xml.namespace.QName("", "BeginRec"));

				if (localBeginRec != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localBeginRec));
				} else {
					throw new org.apache.axis2.databinding.ADBException("BeginRec cannot be null!!");
				}
			}
			if (localMaxRecTracker) {
				elementList.add(new javax.xml.namespace.QName("", "MaxRec"));

				if (localMaxRec != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localMaxRec));
				} else {
					throw new org.apache.axis2.databinding.ADBException("MaxRec cannot be null!!");
				}
			}
			if (localFileHMacTracker) {
				elementList.add(new javax.xml.namespace.QName("", "FileHMac"));

				if (localFileHMac != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localFileHMac));
				} else {
					throw new org.apache.axis2.databinding.ADBException("FileHMac cannot be null!!");
				}
			}
			if (localHMacTracker) {
				elementList.add(new javax.xml.namespace.QName("", "HMac"));

				if (localHMac != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHMac));
				} else {
					throw new org.apache.axis2.databinding.ADBException("HMac cannot be null!!");
				}
			}

			return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName, elementList.toArray(), attribList.toArray());

		}

		/**
		 * Factory class that keeps the parse method
		 */
		public static class Factory {

			/**
			 * static method to create the object
			 * Precondition: If this object is an element, the current or next start element starts this object and any intervening reader events are
			 * ignorable
			 * If this object is not an element, it is a complex type and the reader is at the event just after the outer start element
			 * Postcondition: If this object is an element, the reader is positioned at its end element
			 * If this object is a complex type, the reader is positioned at the end element of its outer element
			 */
			public static FMT_SOAP_UTF8_RequestHeader parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception {
				FMT_SOAP_UTF8_RequestHeader object = new FMT_SOAP_UTF8_RequestHeader();

				int event;
				java.lang.String nillableValue = null;
				java.lang.String prefix = "";
				java.lang.String namespaceuri = "";
				try {

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "type") != null) {
						java.lang.String fullTypeName = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "type");
						if (fullTypeName != null) {
							java.lang.String nsPrefix = null;
							if (fullTypeName.indexOf(":") > -1) {
								nsPrefix = fullTypeName.substring(0, fullTypeName.indexOf(":"));
							}
							nsPrefix = nsPrefix == null ? "" : nsPrefix;

							java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":") + 1);

							if (!"FMT_SOAP_UTF8_RequestHeader".equals(type)) {
								// find namespace for the prefix
								java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
								return (FMT_SOAP_UTF8_RequestHeader) ExtensionMapper.getTypeObject(nsUri, type, reader);
							}

						}

					}

					// Note all attributes that were handled. Used to differ normal attributes
					// from anyAttributes.
					java.util.Vector handledAttributes = new java.util.Vector();

					reader.next();

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "VersionNo").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setVersionNo(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "ReqSysCode").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setReqSysCode(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "ReqSecCode").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setReqSecCode(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "TxType").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setTxType(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "TxMode").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setTxMode(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "TxCode").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setTxCode(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "ReqDate").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setReqDate(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "ReqTime").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setReqTime(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "ReqSeqNo").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setReqSeqNo(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "ChanlNo").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setChanlNo(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "Brch").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setBrch(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "TermNo").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setTermNo(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "Oper").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setOper(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "SendFileName").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setSendFileName(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "BeginRec").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setBeginRec(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "MaxRec").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setMaxRec(org.apache.axis2.databinding.utils.ConverterUtil.convertToInteger(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "FileHMac").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setFileHMac(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "HMac").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setHMac(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement())
						// A start element we are not expecting indicates a trailing invalid property
						throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());

				} catch (javax.xml.stream.XMLStreamException e) {
					throw new java.lang.Exception(e);
				}

				return object;
			}

		}// end of factory class

	}

	public static class ExtensionMapper {

		public static java.lang.Object getTypeObject(java.lang.String namespaceURI, java.lang.String typeName, javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception {

			if ("http://www.adtec.com.cn".equals(namespaceURI) && "FMT_SOAP_UTF8_ResponseHeader".equals(typeName)) {

				return FMT_SOAP_UTF8_ResponseHeader.Factory.parse(reader);

			}

			if ("http://www.adtec.com.cn".equals(namespaceURI) && "FMT_SOAP_UTF8_ResTranHeader".equals(typeName)) {

				return FMT_SOAP_UTF8_ResTranHeader.Factory.parse(reader);

			}

			if ("http://www.adtec.com.cn".equals(namespaceURI) && "FMT_CRMS_SVR_S01001010021003_IN_SUB".equals(typeName)) {

				return FMT_CRMS_SVR_S01001010021003_IN_SUB.Factory.parse(reader);

			}

			if ("http://www.adtec.com.cn".equals(namespaceURI) && "FMT_CRMS_SVR_S01001010021003_OUT".equals(typeName)) {

				return FMT_CRMS_SVR_S01001010021003_OUT.Factory.parse(reader);

			}

			if ("http://www.adtec.com.cn".equals(namespaceURI) && "FMT_SOAP_UTF8_RequestHeader".equals(typeName)) {

				return FMT_SOAP_UTF8_RequestHeader.Factory.parse(reader);

			}

			if ("http://www.adtec.com.cn".equals(namespaceURI) && "FMT_SOAP_UTF8_ReqTranHeader".equals(typeName)) {

				return FMT_SOAP_UTF8_ReqTranHeader.Factory.parse(reader);

			}

			if ("http://www.adtec.com.cn".equals(namespaceURI) && "FMT_CRMS_SVR_S01001010021003_IN".equals(typeName)) {

				return FMT_CRMS_SVR_S01001010021003_IN.Factory.parse(reader);

			}

			throw new org.apache.axis2.databinding.ADBException("Unsupported type " + namespaceURI + " " + typeName);
		}

	}

	public static class FMT_SOAP_UTF8_ReqTranHeader implements org.apache.axis2.databinding.ADBBean {
		/*
		 * This type was generated from the piece of schema that had
		 * name = FMT_SOAP_UTF8_ReqTranHeader
		 * Namespace URI = http://www.adtec.com.cn
		 * Namespace Prefix = ns1
		 */

		private static java.lang.String generatePrefix(java.lang.String namespace) {
			if (namespace.equals("http://www.adtec.com.cn")) {
				return "ns1";
			}
			return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
		}

		/**
		 * field for HMsgMac
		 */

		protected java.lang.String localHMsgMac;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localHMsgMacTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getHMsgMac() {
			return localHMsgMac;
		}

		/**
		 * Auto generated setter method
		 * @param param HMsgMac
		 */
		public void setHMsgMac(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localHMsgMacTracker = true;
			} else {
				localHMsgMacTracker = false;

			}

			this.localHMsgMac = param;

		}

		/**
		 * field for HMacBrch
		 */

		protected java.lang.String localHMacBrch;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localHMacBrchTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getHMacBrch() {
			return localHMacBrch;
		}

		/**
		 * Auto generated setter method
		 * @param param HMacBrch
		 */
		public void setHMacBrch(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localHMacBrchTracker = true;
			} else {
				localHMacBrchTracker = false;

			}

			this.localHMacBrch = param;

		}

		/**
		 * field for HPinSeed
		 */

		protected java.lang.String localHPinSeed;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localHPinSeedTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getHPinSeed() {
			return localHPinSeed;
		}

		/**
		 * Auto generated setter method
		 * @param param HPinSeed
		 */
		public void setHPinSeed(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localHPinSeedTracker = true;
			} else {
				localHPinSeedTracker = false;

			}

			this.localHPinSeed = param;

		}

		/**
		 * field for HOriChnl
		 */

		protected java.lang.String localHOriChnl;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localHOriChnlTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getHOriChnl() {
			return localHOriChnl;
		}

		/**
		 * Auto generated setter method
		 * @param param HOriChnl
		 */
		public void setHOriChnl(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localHOriChnlTracker = true;
			} else {
				localHOriChnlTracker = false;

			}

			this.localHOriChnl = param;

		}

		/**
		 * field for HAimCd
		 */

		protected java.lang.String localHAimCd;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localHAimCdTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getHAimCd() {
			return localHAimCd;
		}

		/**
		 * Auto generated setter method
		 * @param param HAimCd
		 */
		public void setHAimCd(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localHAimCdTracker = true;
			} else {
				localHAimCdTracker = false;

			}

			this.localHAimCd = param;

		}

		/**
		 * field for HSecFlag
		 */

		protected java.lang.String localHSecFlag;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localHSecFlagTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getHSecFlag() {
			return localHSecFlag;
		}

		/**
		 * Auto generated setter method
		 * @param param HSecFlag
		 */
		public void setHSecFlag(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localHSecFlagTracker = true;
			} else {
				localHSecFlagTracker = false;

			}

			this.localHSecFlag = param;

		}

		/**
		 * field for HPwdFlag
		 */

		protected java.lang.String localHPwdFlag;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localHPwdFlagTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getHPwdFlag() {
			return localHPwdFlag;
		}

		/**
		 * Auto generated setter method
		 * @param param HPwdFlag
		 */
		public void setHPwdFlag(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localHPwdFlagTracker = true;
			} else {
				localHPwdFlagTracker = false;

			}

			this.localHPwdFlag = param;

		}

		/**
		 * field for HCombFlag
		 */

		protected java.lang.String localHCombFlag;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localHCombFlagTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getHCombFlag() {
			return localHCombFlag;
		}

		/**
		 * Auto generated setter method
		 * @param param HCombFlag
		 */
		public void setHCombFlag(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localHCombFlagTracker = true;
			} else {
				localHCombFlagTracker = false;

			}

			this.localHCombFlag = param;

		}

		/**
		 * field for HSvcInfo
		 */

		protected java.lang.String localHSvcInfo;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localHSvcInfoTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getHSvcInfo() {
			return localHSvcInfo;
		}

		/**
		 * Auto generated setter method
		 * @param param HSvcInfo
		 */
		public void setHSvcInfo(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localHSvcInfoTracker = true;
			} else {
				localHSvcInfoTracker = false;

			}

			this.localHSvcInfo = param;

		}

		/**
		 * field for HEndFlag
		 */

		protected java.lang.String localHEndFlag;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localHEndFlagTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getHEndFlag() {
			return localHEndFlag;
		}

		/**
		 * Auto generated setter method
		 * @param param HEndFlag
		 */
		public void setHEndFlag(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localHEndFlagTracker = true;
			} else {
				localHEndFlagTracker = false;

			}

			this.localHEndFlag = param;

		}

		/**
		 * field for HMsgNo
		 */

		protected java.lang.String localHMsgNo;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localHMsgNoTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getHMsgNo() {
			return localHMsgNo;
		}

		/**
		 * Auto generated setter method
		 * @param param HMsgNo
		 */
		public void setHMsgNo(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localHMsgNoTracker = true;
			} else {
				localHMsgNoTracker = false;

			}

			this.localHMsgNo = param;

		}

		/**
		 * field for HVerfFlag
		 */

		protected java.lang.String localHVerfFlag;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localHVerfFlagTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getHVerfFlag() {
			return localHVerfFlag;
		}

		/**
		 * Auto generated setter method
		 * @param param HVerfFlag
		 */
		public void setHVerfFlag(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localHVerfFlagTracker = true;
			} else {
				localHVerfFlagTracker = false;

			}

			this.localHVerfFlag = param;

		}

		/**
		 * field for HSecInfoVerNo
		 */

		protected java.lang.String localHSecInfoVerNo;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localHSecInfoVerNoTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getHSecInfoVerNo() {
			return localHSecInfoVerNo;
		}

		/**
		 * Auto generated setter method
		 * @param param HSecInfoVerNo
		 */
		public void setHSecInfoVerNo(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localHSecInfoVerNoTracker = true;
			} else {
				localHSecInfoVerNoTracker = false;

			}

			this.localHSecInfoVerNo = param;

		}

		/**
		 * field for HSysChnl
		 */

		protected java.lang.String localHSysChnl;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localHSysChnlTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getHSysChnl() {
			return localHSysChnl;
		}

		/**
		 * Auto generated setter method
		 * @param param HSysChnl
		 */
		public void setHSysChnl(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localHSysChnlTracker = true;
			} else {
				localHSysChnlTracker = false;

			}

			this.localHSysChnl = param;

		}

		/**
		 * field for HoffsetInfo
		 */

		protected java.lang.String localHoffsetInfo;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localHoffsetInfoTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getHoffsetInfo() {
			return localHoffsetInfo;
		}

		/**
		 * Auto generated setter method
		 * @param param HoffsetInfo
		 */
		public void setHoffsetInfo(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localHoffsetInfoTracker = true;
			} else {
				localHoffsetInfoTracker = false;

			}

			this.localHoffsetInfo = param;

		}

		/**
		 * field for HLegaObj
		 */

		protected java.lang.String localHLegaObj;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localHLegaObjTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getHLegaObj() {
			return localHLegaObj;
		}

		/**
		 * Auto generated setter method
		 * @param param HLegaObj
		 */
		public void setHLegaObj(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localHLegaObjTracker = true;
			} else {
				localHLegaObjTracker = false;

			}

			this.localHLegaObj = param;

		}

		/**
		 * field for HMsgRefNo
		 */

		protected java.lang.String localHMsgRefNo;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localHMsgRefNoTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getHMsgRefNo() {
			return localHMsgRefNo;
		}

		/**
		 * Auto generated setter method
		 * @param param HMsgRefNo
		 */
		public void setHMsgRefNo(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localHMsgRefNoTracker = true;
			} else {
				localHMsgRefNoTracker = false;

			}

			this.localHMsgRefNo = param;

		}

		/**
		 * field for HCommType
		 */

		protected java.lang.String localHCommType;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localHCommTypeTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getHCommType() {
			return localHCommType;
		}

		/**
		 * Auto generated setter method
		 * @param param HCommType
		 */
		public void setHCommType(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localHCommTypeTracker = true;
			} else {
				localHCommTypeTracker = false;

			}

			this.localHCommType = param;

		}

		/**
		 * field for HDeviceNo
		 */

		protected java.lang.String localHDeviceNo;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localHDeviceNoTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getHDeviceNo() {
			return localHDeviceNo;
		}

		/**
		 * Auto generated setter method
		 * @param param HDeviceNo
		 */
		public void setHDeviceNo(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localHDeviceNoTracker = true;
			} else {
				localHDeviceNoTracker = false;

			}

			this.localHDeviceNo = param;

		}

		/**
		 * field for HSuperFlag
		 */

		protected java.lang.String localHSuperFlag;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localHSuperFlagTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getHSuperFlag() {
			return localHSuperFlag;
		}

		/**
		 * Auto generated setter method
		 * @param param HSuperFlag
		 */
		public void setHSuperFlag(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localHSuperFlagTracker = true;
			} else {
				localHSuperFlagTracker = false;

			}

			this.localHSuperFlag = param;

		}

		/**
		 * field for HChkFlag
		 */

		protected java.lang.String localHChkFlag;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localHChkFlagTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getHChkFlag() {
			return localHChkFlag;
		}

		/**
		 * Auto generated setter method
		 * @param param HChkFlag
		 */
		public void setHChkFlag(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localHChkFlagTracker = true;
			} else {
				localHChkFlagTracker = false;

			}

			this.localHChkFlag = param;

		}

		/**
		 * field for HChkTxnCd
		 */

		protected java.lang.String localHChkTxnCd;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localHChkTxnCdTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getHChkTxnCd() {
			return localHChkTxnCd;
		}

		/**
		 * Auto generated setter method
		 * @param param HChkTxnCd
		 */
		public void setHChkTxnCd(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localHChkTxnCdTracker = true;
			} else {
				localHChkTxnCdTracker = false;

			}

			this.localHChkTxnCd = param;

		}

		/**
		 * field for HVerfCd
		 */

		protected java.lang.String localHVerfCd;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localHVerfCdTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getHVerfCd() {
			return localHVerfCd;
		}

		/**
		 * Auto generated setter method
		 * @param param HVerfCd
		 */
		public void setHVerfCd(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localHVerfCdTracker = true;
			} else {
				localHVerfCdTracker = false;

			}

			this.localHVerfCd = param;

		}

		/**
		 * field for HCommitFlag
		 */

		protected java.lang.String localHCommitFlag;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localHCommitFlagTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getHCommitFlag() {
			return localHCommitFlag;
		}

		/**
		 * Auto generated setter method
		 * @param param HCommitFlag
		 */
		public void setHCommitFlag(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localHCommitFlagTracker = true;
			} else {
				localHCommitFlagTracker = false;

			}

			this.localHCommitFlag = param;

		}

		/**
		 * field for HTranRes
		 */

		protected java.lang.String localHTranRes;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localHTranResTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getHTranRes() {
			return localHTranRes;
		}

		/**
		 * Auto generated setter method
		 * @param param HTranRes
		 */
		public void setHTranRes(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localHTranResTracker = true;
			} else {
				localHTranResTracker = false;

			}

			this.localHTranRes = param;

		}

		/**
		 * field for HTermNo
		 */

		protected java.lang.String localHTermNo;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localHTermNoTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getHTermNo() {
			return localHTermNo;
		}

		/**
		 * Auto generated setter method
		 * @param param HTermNo
		 */
		public void setHTermNo(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localHTermNoTracker = true;
			} else {
				localHTermNoTracker = false;

			}

			this.localHTermNo = param;

		}

		/**
		 * field for HCityCd
		 */

		protected java.lang.String localHCityCd;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localHCityCdTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getHCityCd() {
			return localHCityCd;
		}

		/**
		 * Auto generated setter method
		 * @param param HCityCd
		 */
		public void setHCityCd(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localHCityCdTracker = true;
			} else {
				localHCityCdTracker = false;

			}

			this.localHCityCd = param;

		}

		/**
		 * field for HBrchNo
		 */

		protected java.lang.String localHBrchNo;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localHBrchNoTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getHBrchNo() {
			return localHBrchNo;
		}

		/**
		 * Auto generated setter method
		 * @param param HBrchNo
		 */
		public void setHBrchNo(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localHBrchNoTracker = true;
			} else {
				localHBrchNoTracker = false;

			}

			this.localHBrchNo = param;

		}

		/**
		 * field for HUserID
		 */

		protected java.lang.String localHUserID;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localHUserIDTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getHUserID() {
			return localHUserID;
		}

		/**
		 * Auto generated setter method
		 * @param param HUserID
		 */
		public void setHUserID(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localHUserIDTracker = true;
			} else {
				localHUserIDTracker = false;

			}

			this.localHUserID = param;

		}

		/**
		 * field for HTxnCd
		 */

		protected java.lang.String localHTxnCd;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localHTxnCdTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getHTxnCd() {
			return localHTxnCd;
		}

		/**
		 * Auto generated setter method
		 * @param param HTxnCd
		 */
		public void setHTxnCd(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localHTxnCdTracker = true;
			} else {
				localHTxnCdTracker = false;

			}

			this.localHTxnCd = param;

		}

		/**
		 * field for HSubTxnCd
		 */

		protected java.lang.String localHSubTxnCd;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localHSubTxnCdTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getHSubTxnCd() {
			return localHSubTxnCd;
		}

		/**
		 * Auto generated setter method
		 * @param param HSubTxnCd
		 */
		public void setHSubTxnCd(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localHSubTxnCdTracker = true;
			} else {
				localHSubTxnCdTracker = false;

			}

			this.localHSubTxnCd = param;

		}

		/**
		 * field for HTxnMod
		 */

		protected java.lang.String localHTxnMod;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localHTxnModTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getHTxnMod() {
			return localHTxnMod;
		}

		/**
		 * Auto generated setter method
		 * @param param HTxnMod
		 */
		public void setHTxnMod(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localHTxnModTracker = true;
			} else {
				localHTxnModTracker = false;

			}

			this.localHTxnMod = param;

		}

		/**
		 * field for HTxnSeq
		 */

		protected java.lang.String localHTxnSeq;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localHTxnSeqTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getHTxnSeq() {
			return localHTxnSeq;
		}

		/**
		 * Auto generated setter method
		 * @param param HTxnSeq
		 */
		public void setHTxnSeq(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localHTxnSeqTracker = true;
			} else {
				localHTxnSeqTracker = false;

			}

			this.localHTxnSeq = param;

		}

		/**
		 * field for HReserveLen
		 */

		protected java.lang.String localHReserveLen;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localHReserveLenTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getHReserveLen() {
			return localHReserveLen;
		}

		/**
		 * Auto generated setter method
		 * @param param HReserveLen
		 */
		public void setHReserveLen(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localHReserveLenTracker = true;
			} else {
				localHReserveLenTracker = false;

			}

			this.localHReserveLen = param;

		}

		/**
		 * field for HSenderSvcCd
		 */

		protected java.lang.String localHSenderSvcCd;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localHSenderSvcCdTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getHSenderSvcCd() {
			return localHSenderSvcCd;
		}

		/**
		 * Auto generated setter method
		 * @param param HSenderSvcCd
		 */
		public void setHSenderSvcCd(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localHSenderSvcCdTracker = true;
			} else {
				localHSenderSvcCdTracker = false;

			}

			this.localHSenderSvcCd = param;

		}

		/**
		 * field for HSenderSeq
		 */

		protected java.lang.String localHSenderSeq;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localHSenderSeqTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getHSenderSeq() {
			return localHSenderSeq;
		}

		/**
		 * Auto generated setter method
		 * @param param HSenderSeq
		 */
		public void setHSenderSeq(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localHSenderSeqTracker = true;
			} else {
				localHSenderSeqTracker = false;

			}

			this.localHSenderSeq = param;

		}

		/**
		 * field for HSenderDate
		 */

		protected java.lang.String localHSenderDate;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localHSenderDateTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getHSenderDate() {
			return localHSenderDate;
		}

		/**
		 * Auto generated setter method
		 * @param param HSenderDate
		 */
		public void setHSenderDate(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localHSenderDateTracker = true;
			} else {
				localHSenderDateTracker = false;

			}

			this.localHSenderDate = param;

		}

		/**
		 * field for HAuthUserID
		 */

		protected java.lang.String localHAuthUserID;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localHAuthUserIDTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getHAuthUserID() {
			return localHAuthUserID;
		}

		/**
		 * Auto generated setter method
		 * @param param HAuthUserID
		 */
		public void setHAuthUserID(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localHAuthUserIDTracker = true;
			} else {
				localHAuthUserIDTracker = false;

			}

			this.localHAuthUserID = param;

		}

		/**
		 * field for HAuthVerfInfo
		 */

		protected java.lang.String localHAuthVerfInfo;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localHAuthVerfInfoTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getHAuthVerfInfo() {
			return localHAuthVerfInfo;
		}

		/**
		 * Auto generated setter method
		 * @param param HAuthVerfInfo
		 */
		public void setHAuthVerfInfo(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localHAuthVerfInfoTracker = true;
			} else {
				localHAuthVerfInfoTracker = false;

			}

			this.localHAuthVerfInfo = param;

		}

		/**
		 * field for HAuthFlag
		 */

		protected java.lang.String localHAuthFlag;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localHAuthFlagTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getHAuthFlag() {
			return localHAuthFlag;
		}

		/**
		 * Auto generated setter method
		 * @param param HAuthFlag
		 */
		public void setHAuthFlag(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localHAuthFlagTracker = true;
			} else {
				localHAuthFlagTracker = false;

			}

			this.localHAuthFlag = param;

		}

		/**
		 * field for HRefSeq
		 */

		protected java.lang.String localHRefSeq;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localHRefSeqTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getHRefSeq() {
			return localHRefSeq;
		}

		/**
		 * Auto generated setter method
		 * @param param HRefSeq
		 */
		public void setHRefSeq(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localHRefSeqTracker = true;
			} else {
				localHRefSeqTracker = false;

			}

			this.localHRefSeq = param;

		}

		/**
		 * field for HAuthSeri
		 */

		protected java.lang.String localHAuthSeri;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localHAuthSeriTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getHAuthSeri() {
			return localHAuthSeri;
		}

		/**
		 * Auto generated setter method
		 * @param param HAuthSeri
		 */
		public void setHAuthSeri(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localHAuthSeriTracker = true;
			} else {
				localHAuthSeriTracker = false;

			}

			this.localHAuthSeri = param;

		}

		/**
		 * field for HHostSeq
		 */

		protected java.lang.String localHHostSeq;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localHHostSeqTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getHHostSeq() {
			return localHHostSeq;
		}

		/**
		 * Auto generated setter method
		 * @param param HHostSeq
		 */
		public void setHHostSeq(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localHHostSeqTracker = true;
			} else {
				localHHostSeqTracker = false;

			}

			this.localHHostSeq = param;

		}

		/**
		 * field for HRefDt
		 */

		protected java.lang.String localHRefDt;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localHRefDtTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getHRefDt() {
			return localHRefDt;
		}

		/**
		 * Auto generated setter method
		 * @param param HRefDt
		 */
		public void setHRefDt(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localHRefDtTracker = true;
			} else {
				localHRefDtTracker = false;

			}

			this.localHRefDt = param;

		}

		/**
		 * field for HSvcVer
		 */

		protected java.lang.String localHSvcVer;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localHSvcVerTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getHSvcVer() {
			return localHSvcVer;
		}

		/**
		 * Auto generated setter method
		 * @param param HSvcVer
		 */
		public void setHSvcVer(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localHSvcVerTracker = true;
			} else {
				localHSvcVerTracker = false;

			}

			this.localHSvcVer = param;

		}

		/**
		 * field for HreserveMsg
		 */

		protected java.lang.String localHreserveMsg;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localHreserveMsgTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getHreserveMsg() {
			return localHreserveMsg;
		}

		/**
		 * Auto generated setter method
		 * @param param HreserveMsg
		 */
		public void setHreserveMsg(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localHreserveMsgTracker = true;
			} else {
				localHreserveMsgTracker = false;

			}

			this.localHreserveMsg = param;

		}

		/**
		 * field for DataLength
		 */

		protected java.math.BigInteger localDataLength;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localDataLengthTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.math.BigInteger
		 */
		public java.math.BigInteger getDataLength() {
			return localDataLength;
		}

		/**
		 * Auto generated setter method
		 * @param param DataLength
		 */
		public void setDataLength(java.math.BigInteger param) {

			if (param != null) {
				// update the setting tracker
				localDataLengthTracker = true;
			} else {
				localDataLengthTracker = false;

			}

			this.localDataLength = param;

		}

		/**
		 * field for HintVerNo
		 */

		protected java.math.BigInteger localHintVerNo;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localHintVerNoTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.math.BigInteger
		 */
		public java.math.BigInteger getHintVerNo() {
			return localHintVerNo;
		}

		/**
		 * Auto generated setter method
		 * @param param HintVerNo
		 */
		public void setHintVerNo(java.math.BigInteger param) {

			if (param != null) {
				// update the setting tracker
				localHintVerNoTracker = true;
			} else {
				localHintVerNoTracker = false;

			}

			this.localHintVerNo = param;

		}

		/**
		 * field for HintOrigMark
		 */

		protected java.math.BigInteger localHintOrigMark;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localHintOrigMarkTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.math.BigInteger
		 */
		public java.math.BigInteger getHintOrigMark() {
			return localHintOrigMark;
		}

		/**
		 * Auto generated setter method
		 * @param param HintOrigMark
		 */
		public void setHintOrigMark(java.math.BigInteger param) {

			if (param != null) {
				// update the setting tracker
				localHintOrigMarkTracker = true;
			} else {
				localHintOrigMarkTracker = false;

			}

			this.localHintOrigMark = param;

		}

		/**
		 * field for HintDestMark
		 */

		protected java.math.BigInteger localHintDestMark;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localHintDestMarkTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.math.BigInteger
		 */
		public java.math.BigInteger getHintDestMark() {
			return localHintDestMark;
		}

		/**
		 * Auto generated setter method
		 * @param param HintDestMark
		 */
		public void setHintDestMark(java.math.BigInteger param) {

			if (param != null) {
				// update the setting tracker
				localHintDestMarkTracker = true;
			} else {
				localHintDestMarkTracker = false;

			}

			this.localHintDestMark = param;

		}

		/**
		 * field for HTranVer
		 */

		protected java.lang.String localHTranVer;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localHTranVerTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getHTranVer() {
			return localHTranVer;
		}

		/**
		 * Auto generated setter method
		 * @param param HTranVer
		 */
		public void setHTranVer(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localHTranVerTracker = true;
			} else {
				localHTranVerTracker = false;

			}

			this.localHTranVer = param;

		}

		/**
		 * field for HOrigMark
		 */

		protected java.lang.String localHOrigMark;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localHOrigMarkTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getHOrigMark() {
			return localHOrigMark;
		}

		/**
		 * Auto generated setter method
		 * @param param HOrigMark
		 */
		public void setHOrigMark(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localHOrigMarkTracker = true;
			} else {
				localHOrigMarkTracker = false;

			}

			this.localHOrigMark = param;

		}

		/**
		 * field for HDestMark
		 */

		protected java.lang.String localHDestMark;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localHDestMarkTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getHDestMark() {
			return localHDestMark;
		}

		/**
		 * Auto generated setter method
		 * @param param HDestMark
		 */
		public void setHDestMark(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localHDestMarkTracker = true;
			} else {
				localHDestMarkTracker = false;

			}

			this.localHDestMark = param;

		}

		/**
		 * field for HIdentFlag
		 */

		protected java.lang.String localHIdentFlag;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localHIdentFlagTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getHIdentFlag() {
			return localHIdentFlag;
		}

		/**
		 * Auto generated setter method
		 * @param param HIdentFlag
		 */
		public void setHIdentFlag(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localHIdentFlagTracker = true;
			} else {
				localHIdentFlagTracker = false;

			}

			this.localHIdentFlag = param;

		}

		/**
		 * isReaderMTOMAware
		 * @return true if the reader supports MTOM
		 */
		public static boolean isReaderMTOMAware(javax.xml.stream.XMLStreamReader reader) {
			boolean isReaderMTOMAware = false;

			try {
				isReaderMTOMAware = java.lang.Boolean.TRUE.equals(reader.getProperty(org.apache.axiom.om.OMConstants.IS_DATA_HANDLERS_AWARE));
			} catch (java.lang.IllegalArgumentException e) {
				isReaderMTOMAware = false;
			}
			return isReaderMTOMAware;
		}

		/**
		 * @param parentQName
		 * @param factory
		 * @return org.apache.axiom.om.OMElement
		 */
		public org.apache.axiom.om.OMElement getOMElement(final javax.xml.namespace.QName parentQName, final org.apache.axiom.om.OMFactory factory) throws org.apache.axis2.databinding.ADBException {

			org.apache.axiom.om.OMDataSource dataSource = new org.apache.axis2.databinding.ADBDataSource(this, parentQName) {

				public void serialize(org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
					FMT_SOAP_UTF8_ReqTranHeader.this.serialize(parentQName, factory, xmlWriter);
				}
			};
			return new org.apache.axiom.om.impl.llom.OMSourcedElementImpl(parentQName, factory, dataSource);

		}

		public void serialize(final javax.xml.namespace.QName parentQName, final org.apache.axiom.om.OMFactory factory, org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {

			java.lang.String prefix = null;
			java.lang.String namespace = null;

			prefix = parentQName.getPrefix();
			namespace = parentQName.getNamespaceURI();

			if (namespace != null) {
				java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
				if (writerPrefix != null) {
					xmlWriter.writeStartElement(namespace, parentQName.getLocalPart());
				} else {
					if (prefix == null) {
						prefix = generatePrefix(namespace);
					}

					xmlWriter.writeStartElement(prefix, parentQName.getLocalPart(), namespace);
					xmlWriter.writeNamespace(prefix, namespace);
					xmlWriter.setPrefix(prefix, namespace);
				}
			} else {
				xmlWriter.writeStartElement(parentQName.getLocalPart());
			}
			if (localHMsgMacTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "HMsgMac", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "HMsgMac");
					}

				} else {
					xmlWriter.writeStartElement("HMsgMac");
				}

				if (localHMsgMac == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("HMsgMac cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localHMsgMac);

				}

				xmlWriter.writeEndElement();
			}
			if (localHMacBrchTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "HMacBrch", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "HMacBrch");
					}

				} else {
					xmlWriter.writeStartElement("HMacBrch");
				}

				if (localHMacBrch == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("HMacBrch cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localHMacBrch);

				}

				xmlWriter.writeEndElement();
			}
			if (localHPinSeedTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "HPinSeed", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "HPinSeed");
					}

				} else {
					xmlWriter.writeStartElement("HPinSeed");
				}

				if (localHPinSeed == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("HPinSeed cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localHPinSeed);

				}

				xmlWriter.writeEndElement();
			}
			if (localHOriChnlTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "HOriChnl", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "HOriChnl");
					}

				} else {
					xmlWriter.writeStartElement("HOriChnl");
				}

				if (localHOriChnl == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("HOriChnl cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localHOriChnl);

				}

				xmlWriter.writeEndElement();
			}
			if (localHAimCdTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "HAimCd", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "HAimCd");
					}

				} else {
					xmlWriter.writeStartElement("HAimCd");
				}

				if (localHAimCd == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("HAimCd cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localHAimCd);

				}

				xmlWriter.writeEndElement();
			}
			if (localHSecFlagTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "HSecFlag", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "HSecFlag");
					}

				} else {
					xmlWriter.writeStartElement("HSecFlag");
				}

				if (localHSecFlag == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("HSecFlag cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localHSecFlag);

				}

				xmlWriter.writeEndElement();
			}
			if (localHPwdFlagTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "HPwdFlag", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "HPwdFlag");
					}

				} else {
					xmlWriter.writeStartElement("HPwdFlag");
				}

				if (localHPwdFlag == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("HPwdFlag cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localHPwdFlag);

				}

				xmlWriter.writeEndElement();
			}
			if (localHCombFlagTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "HCombFlag", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "HCombFlag");
					}

				} else {
					xmlWriter.writeStartElement("HCombFlag");
				}

				if (localHCombFlag == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("HCombFlag cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localHCombFlag);

				}

				xmlWriter.writeEndElement();
			}
			if (localHSvcInfoTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "HSvcInfo", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "HSvcInfo");
					}

				} else {
					xmlWriter.writeStartElement("HSvcInfo");
				}

				if (localHSvcInfo == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("HSvcInfo cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localHSvcInfo);

				}

				xmlWriter.writeEndElement();
			}
			if (localHEndFlagTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "HEndFlag", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "HEndFlag");
					}

				} else {
					xmlWriter.writeStartElement("HEndFlag");
				}

				if (localHEndFlag == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("HEndFlag cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localHEndFlag);

				}

				xmlWriter.writeEndElement();
			}
			if (localHMsgNoTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "HMsgNo", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "HMsgNo");
					}

				} else {
					xmlWriter.writeStartElement("HMsgNo");
				}

				if (localHMsgNo == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("HMsgNo cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localHMsgNo);

				}

				xmlWriter.writeEndElement();
			}
			if (localHVerfFlagTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "HVerfFlag", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "HVerfFlag");
					}

				} else {
					xmlWriter.writeStartElement("HVerfFlag");
				}

				if (localHVerfFlag == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("HVerfFlag cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localHVerfFlag);

				}

				xmlWriter.writeEndElement();
			}
			if (localHSecInfoVerNoTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "HSecInfoVerNo", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "HSecInfoVerNo");
					}

				} else {
					xmlWriter.writeStartElement("HSecInfoVerNo");
				}

				if (localHSecInfoVerNo == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("HSecInfoVerNo cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localHSecInfoVerNo);

				}

				xmlWriter.writeEndElement();
			}
			if (localHSysChnlTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "HSysChnl", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "HSysChnl");
					}

				} else {
					xmlWriter.writeStartElement("HSysChnl");
				}

				if (localHSysChnl == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("HSysChnl cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localHSysChnl);

				}

				xmlWriter.writeEndElement();
			}
			if (localHoffsetInfoTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "HoffsetInfo", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "HoffsetInfo");
					}

				} else {
					xmlWriter.writeStartElement("HoffsetInfo");
				}

				if (localHoffsetInfo == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("HoffsetInfo cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localHoffsetInfo);

				}

				xmlWriter.writeEndElement();
			}
			if (localHLegaObjTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "HLegaObj", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "HLegaObj");
					}

				} else {
					xmlWriter.writeStartElement("HLegaObj");
				}

				if (localHLegaObj == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("HLegaObj cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localHLegaObj);

				}

				xmlWriter.writeEndElement();
			}
			if (localHMsgRefNoTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "HMsgRefNo", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "HMsgRefNo");
					}

				} else {
					xmlWriter.writeStartElement("HMsgRefNo");
				}

				if (localHMsgRefNo == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("HMsgRefNo cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localHMsgRefNo);

				}

				xmlWriter.writeEndElement();
			}
			if (localHCommTypeTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "HCommType", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "HCommType");
					}

				} else {
					xmlWriter.writeStartElement("HCommType");
				}

				if (localHCommType == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("HCommType cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localHCommType);

				}

				xmlWriter.writeEndElement();
			}
			if (localHDeviceNoTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "HDeviceNo", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "HDeviceNo");
					}

				} else {
					xmlWriter.writeStartElement("HDeviceNo");
				}

				if (localHDeviceNo == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("HDeviceNo cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localHDeviceNo);

				}

				xmlWriter.writeEndElement();
			}
			if (localHSuperFlagTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "HSuperFlag", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "HSuperFlag");
					}

				} else {
					xmlWriter.writeStartElement("HSuperFlag");
				}

				if (localHSuperFlag == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("HSuperFlag cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localHSuperFlag);

				}

				xmlWriter.writeEndElement();
			}
			if (localHChkFlagTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "HChkFlag", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "HChkFlag");
					}

				} else {
					xmlWriter.writeStartElement("HChkFlag");
				}

				if (localHChkFlag == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("HChkFlag cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localHChkFlag);

				}

				xmlWriter.writeEndElement();
			}
			if (localHChkTxnCdTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "HChkTxnCd", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "HChkTxnCd");
					}

				} else {
					xmlWriter.writeStartElement("HChkTxnCd");
				}

				if (localHChkTxnCd == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("HChkTxnCd cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localHChkTxnCd);

				}

				xmlWriter.writeEndElement();
			}
			if (localHVerfCdTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "HVerfCd", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "HVerfCd");
					}

				} else {
					xmlWriter.writeStartElement("HVerfCd");
				}

				if (localHVerfCd == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("HVerfCd cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localHVerfCd);

				}

				xmlWriter.writeEndElement();
			}
			if (localHCommitFlagTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "HCommitFlag", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "HCommitFlag");
					}

				} else {
					xmlWriter.writeStartElement("HCommitFlag");
				}

				if (localHCommitFlag == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("HCommitFlag cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localHCommitFlag);

				}

				xmlWriter.writeEndElement();
			}
			if (localHTranResTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "HTranRes", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "HTranRes");
					}

				} else {
					xmlWriter.writeStartElement("HTranRes");
				}

				if (localHTranRes == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("HTranRes cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localHTranRes);

				}

				xmlWriter.writeEndElement();
			}
			if (localHTermNoTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "HTermNo", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "HTermNo");
					}

				} else {
					xmlWriter.writeStartElement("HTermNo");
				}

				if (localHTermNo == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("HTermNo cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localHTermNo);

				}

				xmlWriter.writeEndElement();
			}
			if (localHCityCdTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "HCityCd", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "HCityCd");
					}

				} else {
					xmlWriter.writeStartElement("HCityCd");
				}

				if (localHCityCd == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("HCityCd cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localHCityCd);

				}

				xmlWriter.writeEndElement();
			}
			if (localHBrchNoTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "HBrchNo", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "HBrchNo");
					}

				} else {
					xmlWriter.writeStartElement("HBrchNo");
				}

				if (localHBrchNo == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("HBrchNo cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localHBrchNo);

				}

				xmlWriter.writeEndElement();
			}
			if (localHUserIDTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "HUserID", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "HUserID");
					}

				} else {
					xmlWriter.writeStartElement("HUserID");
				}

				if (localHUserID == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("HUserID cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localHUserID);

				}

				xmlWriter.writeEndElement();
			}
			if (localHTxnCdTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "HTxnCd", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "HTxnCd");
					}

				} else {
					xmlWriter.writeStartElement("HTxnCd");
				}

				if (localHTxnCd == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("HTxnCd cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localHTxnCd);

				}

				xmlWriter.writeEndElement();
			}
			if (localHSubTxnCdTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "HSubTxnCd", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "HSubTxnCd");
					}

				} else {
					xmlWriter.writeStartElement("HSubTxnCd");
				}

				if (localHSubTxnCd == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("HSubTxnCd cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localHSubTxnCd);

				}

				xmlWriter.writeEndElement();
			}
			if (localHTxnModTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "HTxnMod", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "HTxnMod");
					}

				} else {
					xmlWriter.writeStartElement("HTxnMod");
				}

				if (localHTxnMod == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("HTxnMod cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localHTxnMod);

				}

				xmlWriter.writeEndElement();
			}
			if (localHTxnSeqTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "HTxnSeq", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "HTxnSeq");
					}

				} else {
					xmlWriter.writeStartElement("HTxnSeq");
				}

				if (localHTxnSeq == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("HTxnSeq cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localHTxnSeq);

				}

				xmlWriter.writeEndElement();
			}
			if (localHReserveLenTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "HReserveLen", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "HReserveLen");
					}

				} else {
					xmlWriter.writeStartElement("HReserveLen");
				}

				if (localHReserveLen == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("HReserveLen cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localHReserveLen);

				}

				xmlWriter.writeEndElement();
			}
			if (localHSenderSvcCdTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "HSenderSvcCd", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "HSenderSvcCd");
					}

				} else {
					xmlWriter.writeStartElement("HSenderSvcCd");
				}

				if (localHSenderSvcCd == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("HSenderSvcCd cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localHSenderSvcCd);

				}

				xmlWriter.writeEndElement();
			}
			if (localHSenderSeqTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "HSenderSeq", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "HSenderSeq");
					}

				} else {
					xmlWriter.writeStartElement("HSenderSeq");
				}

				if (localHSenderSeq == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("HSenderSeq cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localHSenderSeq);

				}

				xmlWriter.writeEndElement();
			}
			if (localHSenderDateTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "HSenderDate", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "HSenderDate");
					}

				} else {
					xmlWriter.writeStartElement("HSenderDate");
				}

				if (localHSenderDate == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("HSenderDate cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localHSenderDate);

				}

				xmlWriter.writeEndElement();
			}
			if (localHAuthUserIDTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "HAuthUserID", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "HAuthUserID");
					}

				} else {
					xmlWriter.writeStartElement("HAuthUserID");
				}

				if (localHAuthUserID == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("HAuthUserID cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localHAuthUserID);

				}

				xmlWriter.writeEndElement();
			}
			if (localHAuthVerfInfoTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "HAuthVerfInfo", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "HAuthVerfInfo");
					}

				} else {
					xmlWriter.writeStartElement("HAuthVerfInfo");
				}

				if (localHAuthVerfInfo == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("HAuthVerfInfo cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localHAuthVerfInfo);

				}

				xmlWriter.writeEndElement();
			}
			if (localHAuthFlagTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "HAuthFlag", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "HAuthFlag");
					}

				} else {
					xmlWriter.writeStartElement("HAuthFlag");
				}

				if (localHAuthFlag == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("HAuthFlag cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localHAuthFlag);

				}

				xmlWriter.writeEndElement();
			}
			if (localHRefSeqTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "HRefSeq", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "HRefSeq");
					}

				} else {
					xmlWriter.writeStartElement("HRefSeq");
				}

				if (localHRefSeq == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("HRefSeq cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localHRefSeq);

				}

				xmlWriter.writeEndElement();
			}
			if (localHAuthSeriTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "HAuthSeri", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "HAuthSeri");
					}

				} else {
					xmlWriter.writeStartElement("HAuthSeri");
				}

				if (localHAuthSeri == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("HAuthSeri cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localHAuthSeri);

				}

				xmlWriter.writeEndElement();
			}
			if (localHHostSeqTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "HHostSeq", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "HHostSeq");
					}

				} else {
					xmlWriter.writeStartElement("HHostSeq");
				}

				if (localHHostSeq == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("HHostSeq cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localHHostSeq);

				}

				xmlWriter.writeEndElement();
			}
			if (localHRefDtTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "HRefDt", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "HRefDt");
					}

				} else {
					xmlWriter.writeStartElement("HRefDt");
				}

				if (localHRefDt == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("HRefDt cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localHRefDt);

				}

				xmlWriter.writeEndElement();
			}
			if (localHSvcVerTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "HSvcVer", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "HSvcVer");
					}

				} else {
					xmlWriter.writeStartElement("HSvcVer");
				}

				if (localHSvcVer == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("HSvcVer cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localHSvcVer);

				}

				xmlWriter.writeEndElement();
			}
			if (localHreserveMsgTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "HreserveMsg", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "HreserveMsg");
					}

				} else {
					xmlWriter.writeStartElement("HreserveMsg");
				}

				if (localHreserveMsg == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("HreserveMsg cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localHreserveMsg);

				}

				xmlWriter.writeEndElement();
			}
			if (localDataLengthTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "DataLength", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "DataLength");
					}

				} else {
					xmlWriter.writeStartElement("DataLength");
				}

				if (localDataLength == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("DataLength cannot be null!!");

				} else {

					xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localDataLength));

				}

				xmlWriter.writeEndElement();
			}
			if (localHintVerNoTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "HintVerNo", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "HintVerNo");
					}

				} else {
					xmlWriter.writeStartElement("HintVerNo");
				}

				if (localHintVerNo == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("HintVerNo cannot be null!!");

				} else {

					xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHintVerNo));

				}

				xmlWriter.writeEndElement();
			}
			if (localHintOrigMarkTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "HintOrigMark", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "HintOrigMark");
					}

				} else {
					xmlWriter.writeStartElement("HintOrigMark");
				}

				if (localHintOrigMark == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("HintOrigMark cannot be null!!");

				} else {

					xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHintOrigMark));

				}

				xmlWriter.writeEndElement();
			}
			if (localHintDestMarkTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "HintDestMark", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "HintDestMark");
					}

				} else {
					xmlWriter.writeStartElement("HintDestMark");
				}

				if (localHintDestMark == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("HintDestMark cannot be null!!");

				} else {

					xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHintDestMark));

				}

				xmlWriter.writeEndElement();
			}
			if (localHTranVerTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "HTranVer", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "HTranVer");
					}

				} else {
					xmlWriter.writeStartElement("HTranVer");
				}

				if (localHTranVer == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("HTranVer cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localHTranVer);

				}

				xmlWriter.writeEndElement();
			}
			if (localHOrigMarkTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "HOrigMark", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "HOrigMark");
					}

				} else {
					xmlWriter.writeStartElement("HOrigMark");
				}

				if (localHOrigMark == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("HOrigMark cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localHOrigMark);

				}

				xmlWriter.writeEndElement();
			}
			if (localHDestMarkTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "HDestMark", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "HDestMark");
					}

				} else {
					xmlWriter.writeStartElement("HDestMark");
				}

				if (localHDestMark == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("HDestMark cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localHDestMark);

				}

				xmlWriter.writeEndElement();
			}
			if (localHIdentFlagTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "HIdentFlag", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "HIdentFlag");
					}

				} else {
					xmlWriter.writeStartElement("HIdentFlag");
				}

				if (localHIdentFlag == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("HIdentFlag cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localHIdentFlag);

				}

				xmlWriter.writeEndElement();
			}
			xmlWriter.writeEndElement();

		}

		/**
		 * Util method to write an attribute with the ns prefix
		 */
		private void writeAttribute(java.lang.String prefix, java.lang.String namespace, java.lang.String attName, java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			if (xmlWriter.getPrefix(namespace) == null) {
				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);

			}

			xmlWriter.writeAttribute(namespace, attName, attValue);

		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeAttribute(java.lang.String namespace, java.lang.String attName, java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			if (namespace.equals("")) {
				xmlWriter.writeAttribute(attName, attValue);
			} else {
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attValue);
			}
		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeQNameAttribute(java.lang.String namespace, java.lang.String attName, javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {

			java.lang.String attributeNamespace = qname.getNamespaceURI();
			java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
			if (attributePrefix == null) {
				attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
			}
			java.lang.String attributeValue;
			if (attributePrefix.trim().length() > 0) {
				attributeValue = attributePrefix + ":" + qname.getLocalPart();
			} else {
				attributeValue = qname.getLocalPart();
			}

			if (namespace.equals("")) {
				xmlWriter.writeAttribute(attName, attributeValue);
			} else {
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attributeValue);
			}
		}

		/**
		 * method to handle Qnames
		 */

		private void writeQName(javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
			java.lang.String namespaceURI = qname.getNamespaceURI();
			if (namespaceURI != null) {
				java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
				if (prefix == null) {
					prefix = generatePrefix(namespaceURI);
					xmlWriter.writeNamespace(prefix, namespaceURI);
					xmlWriter.setPrefix(prefix, namespaceURI);
				}

				if (prefix.trim().length() > 0) {
					xmlWriter.writeCharacters(prefix + ":" + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
				} else {
					// i.e this is the default namespace
					xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
				}

			} else {
				xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
			}
		}

		private void writeQNames(javax.xml.namespace.QName[] qnames, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

			if (qnames != null) {
				// we have to store this data until last moment since it is not possible to write any
				// namespace data after writing the charactor data
				java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
				java.lang.String namespaceURI = null;
				java.lang.String prefix = null;

				for (int i = 0; i < qnames.length; i++) {
					if (i > 0) {
						stringToWrite.append(" ");
					}
					namespaceURI = qnames[i].getNamespaceURI();
					if (namespaceURI != null) {
						prefix = xmlWriter.getPrefix(namespaceURI);
						if ((prefix == null) || (prefix.length() == 0)) {
							prefix = generatePrefix(namespaceURI);
							xmlWriter.writeNamespace(prefix, namespaceURI);
							xmlWriter.setPrefix(prefix, namespaceURI);
						}

						if (prefix.trim().length() > 0) {
							stringToWrite.append(prefix).append(":").append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
						} else {
							stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
						}
					} else {
						stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
					}
				}
				xmlWriter.writeCharacters(stringToWrite.toString());
			}

		}

		/**
		 * Register a namespace prefix
		 */
		private java.lang.String registerPrefix(javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace) throws javax.xml.stream.XMLStreamException {
			java.lang.String prefix = xmlWriter.getPrefix(namespace);

			if (prefix == null) {
				prefix = generatePrefix(namespace);

				while (xmlWriter.getNamespaceContext().getNamespaceURI(prefix) != null) {
					prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
				}

				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);
			}

			return prefix;
		}

		/**
		 * databinding method to get an XML representation of this object
		 */
		public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName) throws org.apache.axis2.databinding.ADBException {

			java.util.ArrayList elementList = new java.util.ArrayList();
			java.util.ArrayList attribList = new java.util.ArrayList();

			if (localHMsgMacTracker) {
				elementList.add(new javax.xml.namespace.QName("", "HMsgMac"));

				if (localHMsgMac != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHMsgMac));
				} else {
					throw new org.apache.axis2.databinding.ADBException("HMsgMac cannot be null!!");
				}
			}
			if (localHMacBrchTracker) {
				elementList.add(new javax.xml.namespace.QName("", "HMacBrch"));

				if (localHMacBrch != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHMacBrch));
				} else {
					throw new org.apache.axis2.databinding.ADBException("HMacBrch cannot be null!!");
				}
			}
			if (localHPinSeedTracker) {
				elementList.add(new javax.xml.namespace.QName("", "HPinSeed"));

				if (localHPinSeed != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHPinSeed));
				} else {
					throw new org.apache.axis2.databinding.ADBException("HPinSeed cannot be null!!");
				}
			}
			if (localHOriChnlTracker) {
				elementList.add(new javax.xml.namespace.QName("", "HOriChnl"));

				if (localHOriChnl != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHOriChnl));
				} else {
					throw new org.apache.axis2.databinding.ADBException("HOriChnl cannot be null!!");
				}
			}
			if (localHAimCdTracker) {
				elementList.add(new javax.xml.namespace.QName("", "HAimCd"));

				if (localHAimCd != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHAimCd));
				} else {
					throw new org.apache.axis2.databinding.ADBException("HAimCd cannot be null!!");
				}
			}
			if (localHSecFlagTracker) {
				elementList.add(new javax.xml.namespace.QName("", "HSecFlag"));

				if (localHSecFlag != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHSecFlag));
				} else {
					throw new org.apache.axis2.databinding.ADBException("HSecFlag cannot be null!!");
				}
			}
			if (localHPwdFlagTracker) {
				elementList.add(new javax.xml.namespace.QName("", "HPwdFlag"));

				if (localHPwdFlag != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHPwdFlag));
				} else {
					throw new org.apache.axis2.databinding.ADBException("HPwdFlag cannot be null!!");
				}
			}
			if (localHCombFlagTracker) {
				elementList.add(new javax.xml.namespace.QName("", "HCombFlag"));

				if (localHCombFlag != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHCombFlag));
				} else {
					throw new org.apache.axis2.databinding.ADBException("HCombFlag cannot be null!!");
				}
			}
			if (localHSvcInfoTracker) {
				elementList.add(new javax.xml.namespace.QName("", "HSvcInfo"));

				if (localHSvcInfo != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHSvcInfo));
				} else {
					throw new org.apache.axis2.databinding.ADBException("HSvcInfo cannot be null!!");
				}
			}
			if (localHEndFlagTracker) {
				elementList.add(new javax.xml.namespace.QName("", "HEndFlag"));

				if (localHEndFlag != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHEndFlag));
				} else {
					throw new org.apache.axis2.databinding.ADBException("HEndFlag cannot be null!!");
				}
			}
			if (localHMsgNoTracker) {
				elementList.add(new javax.xml.namespace.QName("", "HMsgNo"));

				if (localHMsgNo != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHMsgNo));
				} else {
					throw new org.apache.axis2.databinding.ADBException("HMsgNo cannot be null!!");
				}
			}
			if (localHVerfFlagTracker) {
				elementList.add(new javax.xml.namespace.QName("", "HVerfFlag"));

				if (localHVerfFlag != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHVerfFlag));
				} else {
					throw new org.apache.axis2.databinding.ADBException("HVerfFlag cannot be null!!");
				}
			}
			if (localHSecInfoVerNoTracker) {
				elementList.add(new javax.xml.namespace.QName("", "HSecInfoVerNo"));

				if (localHSecInfoVerNo != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHSecInfoVerNo));
				} else {
					throw new org.apache.axis2.databinding.ADBException("HSecInfoVerNo cannot be null!!");
				}
			}
			if (localHSysChnlTracker) {
				elementList.add(new javax.xml.namespace.QName("", "HSysChnl"));

				if (localHSysChnl != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHSysChnl));
				} else {
					throw new org.apache.axis2.databinding.ADBException("HSysChnl cannot be null!!");
				}
			}
			if (localHoffsetInfoTracker) {
				elementList.add(new javax.xml.namespace.QName("", "HoffsetInfo"));

				if (localHoffsetInfo != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHoffsetInfo));
				} else {
					throw new org.apache.axis2.databinding.ADBException("HoffsetInfo cannot be null!!");
				}
			}
			if (localHLegaObjTracker) {
				elementList.add(new javax.xml.namespace.QName("", "HLegaObj"));

				if (localHLegaObj != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHLegaObj));
				} else {
					throw new org.apache.axis2.databinding.ADBException("HLegaObj cannot be null!!");
				}
			}
			if (localHMsgRefNoTracker) {
				elementList.add(new javax.xml.namespace.QName("", "HMsgRefNo"));

				if (localHMsgRefNo != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHMsgRefNo));
				} else {
					throw new org.apache.axis2.databinding.ADBException("HMsgRefNo cannot be null!!");
				}
			}
			if (localHCommTypeTracker) {
				elementList.add(new javax.xml.namespace.QName("", "HCommType"));

				if (localHCommType != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHCommType));
				} else {
					throw new org.apache.axis2.databinding.ADBException("HCommType cannot be null!!");
				}
			}
			if (localHDeviceNoTracker) {
				elementList.add(new javax.xml.namespace.QName("", "HDeviceNo"));

				if (localHDeviceNo != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHDeviceNo));
				} else {
					throw new org.apache.axis2.databinding.ADBException("HDeviceNo cannot be null!!");
				}
			}
			if (localHSuperFlagTracker) {
				elementList.add(new javax.xml.namespace.QName("", "HSuperFlag"));

				if (localHSuperFlag != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHSuperFlag));
				} else {
					throw new org.apache.axis2.databinding.ADBException("HSuperFlag cannot be null!!");
				}
			}
			if (localHChkFlagTracker) {
				elementList.add(new javax.xml.namespace.QName("", "HChkFlag"));

				if (localHChkFlag != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHChkFlag));
				} else {
					throw new org.apache.axis2.databinding.ADBException("HChkFlag cannot be null!!");
				}
			}
			if (localHChkTxnCdTracker) {
				elementList.add(new javax.xml.namespace.QName("", "HChkTxnCd"));

				if (localHChkTxnCd != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHChkTxnCd));
				} else {
					throw new org.apache.axis2.databinding.ADBException("HChkTxnCd cannot be null!!");
				}
			}
			if (localHVerfCdTracker) {
				elementList.add(new javax.xml.namespace.QName("", "HVerfCd"));

				if (localHVerfCd != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHVerfCd));
				} else {
					throw new org.apache.axis2.databinding.ADBException("HVerfCd cannot be null!!");
				}
			}
			if (localHCommitFlagTracker) {
				elementList.add(new javax.xml.namespace.QName("", "HCommitFlag"));

				if (localHCommitFlag != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHCommitFlag));
				} else {
					throw new org.apache.axis2.databinding.ADBException("HCommitFlag cannot be null!!");
				}
			}
			if (localHTranResTracker) {
				elementList.add(new javax.xml.namespace.QName("", "HTranRes"));

				if (localHTranRes != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHTranRes));
				} else {
					throw new org.apache.axis2.databinding.ADBException("HTranRes cannot be null!!");
				}
			}
			if (localHTermNoTracker) {
				elementList.add(new javax.xml.namespace.QName("", "HTermNo"));

				if (localHTermNo != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHTermNo));
				} else {
					throw new org.apache.axis2.databinding.ADBException("HTermNo cannot be null!!");
				}
			}
			if (localHCityCdTracker) {
				elementList.add(new javax.xml.namespace.QName("", "HCityCd"));

				if (localHCityCd != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHCityCd));
				} else {
					throw new org.apache.axis2.databinding.ADBException("HCityCd cannot be null!!");
				}
			}
			if (localHBrchNoTracker) {
				elementList.add(new javax.xml.namespace.QName("", "HBrchNo"));

				if (localHBrchNo != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHBrchNo));
				} else {
					throw new org.apache.axis2.databinding.ADBException("HBrchNo cannot be null!!");
				}
			}
			if (localHUserIDTracker) {
				elementList.add(new javax.xml.namespace.QName("", "HUserID"));

				if (localHUserID != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHUserID));
				} else {
					throw new org.apache.axis2.databinding.ADBException("HUserID cannot be null!!");
				}
			}
			if (localHTxnCdTracker) {
				elementList.add(new javax.xml.namespace.QName("", "HTxnCd"));

				if (localHTxnCd != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHTxnCd));
				} else {
					throw new org.apache.axis2.databinding.ADBException("HTxnCd cannot be null!!");
				}
			}
			if (localHSubTxnCdTracker) {
				elementList.add(new javax.xml.namespace.QName("", "HSubTxnCd"));

				if (localHSubTxnCd != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHSubTxnCd));
				} else {
					throw new org.apache.axis2.databinding.ADBException("HSubTxnCd cannot be null!!");
				}
			}
			if (localHTxnModTracker) {
				elementList.add(new javax.xml.namespace.QName("", "HTxnMod"));

				if (localHTxnMod != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHTxnMod));
				} else {
					throw new org.apache.axis2.databinding.ADBException("HTxnMod cannot be null!!");
				}
			}
			if (localHTxnSeqTracker) {
				elementList.add(new javax.xml.namespace.QName("", "HTxnSeq"));

				if (localHTxnSeq != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHTxnSeq));
				} else {
					throw new org.apache.axis2.databinding.ADBException("HTxnSeq cannot be null!!");
				}
			}
			if (localHReserveLenTracker) {
				elementList.add(new javax.xml.namespace.QName("", "HReserveLen"));

				if (localHReserveLen != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHReserveLen));
				} else {
					throw new org.apache.axis2.databinding.ADBException("HReserveLen cannot be null!!");
				}
			}
			if (localHSenderSvcCdTracker) {
				elementList.add(new javax.xml.namespace.QName("", "HSenderSvcCd"));

				if (localHSenderSvcCd != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHSenderSvcCd));
				} else {
					throw new org.apache.axis2.databinding.ADBException("HSenderSvcCd cannot be null!!");
				}
			}
			if (localHSenderSeqTracker) {
				elementList.add(new javax.xml.namespace.QName("", "HSenderSeq"));

				if (localHSenderSeq != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHSenderSeq));
				} else {
					throw new org.apache.axis2.databinding.ADBException("HSenderSeq cannot be null!!");
				}
			}
			if (localHSenderDateTracker) {
				elementList.add(new javax.xml.namespace.QName("", "HSenderDate"));

				if (localHSenderDate != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHSenderDate));
				} else {
					throw new org.apache.axis2.databinding.ADBException("HSenderDate cannot be null!!");
				}
			}
			if (localHAuthUserIDTracker) {
				elementList.add(new javax.xml.namespace.QName("", "HAuthUserID"));

				if (localHAuthUserID != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHAuthUserID));
				} else {
					throw new org.apache.axis2.databinding.ADBException("HAuthUserID cannot be null!!");
				}
			}
			if (localHAuthVerfInfoTracker) {
				elementList.add(new javax.xml.namespace.QName("", "HAuthVerfInfo"));

				if (localHAuthVerfInfo != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHAuthVerfInfo));
				} else {
					throw new org.apache.axis2.databinding.ADBException("HAuthVerfInfo cannot be null!!");
				}
			}
			if (localHAuthFlagTracker) {
				elementList.add(new javax.xml.namespace.QName("", "HAuthFlag"));

				if (localHAuthFlag != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHAuthFlag));
				} else {
					throw new org.apache.axis2.databinding.ADBException("HAuthFlag cannot be null!!");
				}
			}
			if (localHRefSeqTracker) {
				elementList.add(new javax.xml.namespace.QName("", "HRefSeq"));

				if (localHRefSeq != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHRefSeq));
				} else {
					throw new org.apache.axis2.databinding.ADBException("HRefSeq cannot be null!!");
				}
			}
			if (localHAuthSeriTracker) {
				elementList.add(new javax.xml.namespace.QName("", "HAuthSeri"));

				if (localHAuthSeri != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHAuthSeri));
				} else {
					throw new org.apache.axis2.databinding.ADBException("HAuthSeri cannot be null!!");
				}
			}
			if (localHHostSeqTracker) {
				elementList.add(new javax.xml.namespace.QName("", "HHostSeq"));

				if (localHHostSeq != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHHostSeq));
				} else {
					throw new org.apache.axis2.databinding.ADBException("HHostSeq cannot be null!!");
				}
			}
			if (localHRefDtTracker) {
				elementList.add(new javax.xml.namespace.QName("", "HRefDt"));

				if (localHRefDt != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHRefDt));
				} else {
					throw new org.apache.axis2.databinding.ADBException("HRefDt cannot be null!!");
				}
			}
			if (localHSvcVerTracker) {
				elementList.add(new javax.xml.namespace.QName("", "HSvcVer"));

				if (localHSvcVer != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHSvcVer));
				} else {
					throw new org.apache.axis2.databinding.ADBException("HSvcVer cannot be null!!");
				}
			}
			if (localHreserveMsgTracker) {
				elementList.add(new javax.xml.namespace.QName("", "HreserveMsg"));

				if (localHreserveMsg != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHreserveMsg));
				} else {
					throw new org.apache.axis2.databinding.ADBException("HreserveMsg cannot be null!!");
				}
			}
			if (localDataLengthTracker) {
				elementList.add(new javax.xml.namespace.QName("", "DataLength"));

				if (localDataLength != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localDataLength));
				} else {
					throw new org.apache.axis2.databinding.ADBException("DataLength cannot be null!!");
				}
			}
			if (localHintVerNoTracker) {
				elementList.add(new javax.xml.namespace.QName("", "HintVerNo"));

				if (localHintVerNo != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHintVerNo));
				} else {
					throw new org.apache.axis2.databinding.ADBException("HintVerNo cannot be null!!");
				}
			}
			if (localHintOrigMarkTracker) {
				elementList.add(new javax.xml.namespace.QName("", "HintOrigMark"));

				if (localHintOrigMark != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHintOrigMark));
				} else {
					throw new org.apache.axis2.databinding.ADBException("HintOrigMark cannot be null!!");
				}
			}
			if (localHintDestMarkTracker) {
				elementList.add(new javax.xml.namespace.QName("", "HintDestMark"));

				if (localHintDestMark != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHintDestMark));
				} else {
					throw new org.apache.axis2.databinding.ADBException("HintDestMark cannot be null!!");
				}
			}
			if (localHTranVerTracker) {
				elementList.add(new javax.xml.namespace.QName("", "HTranVer"));

				if (localHTranVer != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHTranVer));
				} else {
					throw new org.apache.axis2.databinding.ADBException("HTranVer cannot be null!!");
				}
			}
			if (localHOrigMarkTracker) {
				elementList.add(new javax.xml.namespace.QName("", "HOrigMark"));

				if (localHOrigMark != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHOrigMark));
				} else {
					throw new org.apache.axis2.databinding.ADBException("HOrigMark cannot be null!!");
				}
			}
			if (localHDestMarkTracker) {
				elementList.add(new javax.xml.namespace.QName("", "HDestMark"));

				if (localHDestMark != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHDestMark));
				} else {
					throw new org.apache.axis2.databinding.ADBException("HDestMark cannot be null!!");
				}
			}
			if (localHIdentFlagTracker) {
				elementList.add(new javax.xml.namespace.QName("", "HIdentFlag"));

				if (localHIdentFlag != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHIdentFlag));
				} else {
					throw new org.apache.axis2.databinding.ADBException("HIdentFlag cannot be null!!");
				}
			}

			return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName, elementList.toArray(), attribList.toArray());

		}

		/**
		 * Factory class that keeps the parse method
		 */
		public static class Factory {

			/**
			 * static method to create the object
			 * Precondition: If this object is an element, the current or next start element starts this object and any intervening reader events are
			 * ignorable
			 * If this object is not an element, it is a complex type and the reader is at the event just after the outer start element
			 * Postcondition: If this object is an element, the reader is positioned at its end element
			 * If this object is a complex type, the reader is positioned at the end element of its outer element
			 */
			public static FMT_SOAP_UTF8_ReqTranHeader parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception {
				FMT_SOAP_UTF8_ReqTranHeader object = new FMT_SOAP_UTF8_ReqTranHeader();

				int event;
				java.lang.String nillableValue = null;
				java.lang.String prefix = "";
				java.lang.String namespaceuri = "";
				try {

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "type") != null) {
						java.lang.String fullTypeName = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "type");
						if (fullTypeName != null) {
							java.lang.String nsPrefix = null;
							if (fullTypeName.indexOf(":") > -1) {
								nsPrefix = fullTypeName.substring(0, fullTypeName.indexOf(":"));
							}
							nsPrefix = nsPrefix == null ? "" : nsPrefix;

							java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":") + 1);

							if (!"FMT_SOAP_UTF8_ReqTranHeader".equals(type)) {
								// find namespace for the prefix
								java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
								return (FMT_SOAP_UTF8_ReqTranHeader) ExtensionMapper.getTypeObject(nsUri, type, reader);
							}

						}

					}

					// Note all attributes that were handled. Used to differ normal attributes
					// from anyAttributes.
					java.util.Vector handledAttributes = new java.util.Vector();

					reader.next();

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "HMsgMac").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setHMsgMac(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "HMacBrch").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setHMacBrch(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "HPinSeed").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setHPinSeed(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "HOriChnl").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setHOriChnl(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "HAimCd").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setHAimCd(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "HSecFlag").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setHSecFlag(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "HPwdFlag").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setHPwdFlag(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "HCombFlag").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setHCombFlag(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "HSvcInfo").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setHSvcInfo(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "HEndFlag").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setHEndFlag(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "HMsgNo").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setHMsgNo(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "HVerfFlag").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setHVerfFlag(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "HSecInfoVerNo").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setHSecInfoVerNo(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "HSysChnl").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setHSysChnl(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "HoffsetInfo").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setHoffsetInfo(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "HLegaObj").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setHLegaObj(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "HMsgRefNo").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setHMsgRefNo(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "HCommType").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setHCommType(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "HDeviceNo").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setHDeviceNo(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "HSuperFlag").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setHSuperFlag(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "HChkFlag").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setHChkFlag(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "HChkTxnCd").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setHChkTxnCd(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "HVerfCd").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setHVerfCd(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "HCommitFlag").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setHCommitFlag(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "HTranRes").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setHTranRes(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "HTermNo").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setHTermNo(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "HCityCd").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setHCityCd(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "HBrchNo").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setHBrchNo(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "HUserID").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setHUserID(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "HTxnCd").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setHTxnCd(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "HSubTxnCd").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setHSubTxnCd(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "HTxnMod").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setHTxnMod(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "HTxnSeq").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setHTxnSeq(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "HReserveLen").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setHReserveLen(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "HSenderSvcCd").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setHSenderSvcCd(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "HSenderSeq").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setHSenderSeq(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "HSenderDate").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setHSenderDate(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "HAuthUserID").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setHAuthUserID(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "HAuthVerfInfo").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setHAuthVerfInfo(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "HAuthFlag").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setHAuthFlag(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "HRefSeq").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setHRefSeq(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "HAuthSeri").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setHAuthSeri(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "HHostSeq").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setHHostSeq(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "HRefDt").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setHRefDt(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "HSvcVer").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setHSvcVer(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "HreserveMsg").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setHreserveMsg(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "DataLength").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setDataLength(org.apache.axis2.databinding.utils.ConverterUtil.convertToInteger(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "HintVerNo").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setHintVerNo(org.apache.axis2.databinding.utils.ConverterUtil.convertToInteger(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "HintOrigMark").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setHintOrigMark(org.apache.axis2.databinding.utils.ConverterUtil.convertToInteger(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "HintDestMark").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setHintDestMark(org.apache.axis2.databinding.utils.ConverterUtil.convertToInteger(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "HTranVer").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setHTranVer(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "HOrigMark").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setHOrigMark(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "HDestMark").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setHDestMark(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "HIdentFlag").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setHIdentFlag(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement())
						// A start element we are not expecting indicates a trailing invalid property
						throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());

				} catch (javax.xml.stream.XMLStreamException e) {
					throw new java.lang.Exception(e);
				}

				return object;
			}

		}// end of factory class

	}

	public static class FMT_CRMS_SVR_S01001010021003_IN implements org.apache.axis2.databinding.ADBBean {
		/*
		 * This type was generated from the piece of schema that had
		 * name = FMT_CRMS_SVR_S01001010021003_IN
		 * Namespace URI = http://www.adtec.com.cn
		 * Namespace Prefix = ns1
		 */

		private static java.lang.String generatePrefix(java.lang.String namespace) {
			if (namespace.equals("http://www.adtec.com.cn")) {
				return "ns1";
			}
			return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
		}

		/**
		 * field for BusiLaunBrch
		 */

		protected java.lang.String localBusiLaunBrch;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localBusiLaunBrchTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getBusiLaunBrch() {
			return localBusiLaunBrch;
		}

		/**
		 * Auto generated setter method
		 * @param param BusiLaunBrch
		 */
		public void setBusiLaunBrch(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localBusiLaunBrchTracker = true;
			} else {
				localBusiLaunBrchTracker = false;

			}

			this.localBusiLaunBrch = param;

		}

		/**
		 * field for AccoutBrch
		 */

		protected java.lang.String localAccoutBrch;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localAccoutBrchTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getAccoutBrch() {
			return localAccoutBrch;
		}

		/**
		 * Auto generated setter method
		 * @param param AccoutBrch
		 */
		public void setAccoutBrch(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localAccoutBrchTracker = true;
			} else {
				localAccoutBrchTracker = false;

			}

			this.localAccoutBrch = param;

		}

		/**
		 * field for AccountOperBrch
		 */

		protected java.lang.String localAccountOperBrch;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localAccountOperBrchTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getAccountOperBrch() {
			return localAccountOperBrch;
		}

		/**
		 * Auto generated setter method
		 * @param param AccountOperBrch
		 */
		public void setAccountOperBrch(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localAccountOperBrchTracker = true;
			} else {
				localAccountOperBrchTracker = false;

			}

			this.localAccountOperBrch = param;

		}

		/**
		 * field for ContractNo
		 */

		protected java.lang.String localContractNo;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localContractNoTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getContractNo() {
			return localContractNo;
		}

		/**
		 * Auto generated setter method
		 * @param param ContractNo
		 */
		public void setContractNo(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localContractNoTracker = true;
			} else {
				localContractNoTracker = false;

			}

			this.localContractNo = param;

		}

		/**
		 * field for AgentFlag
		 */

		protected java.lang.String localAgentFlag;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localAgentFlagTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getAgentFlag() {
			return localAgentFlag;
		}

		/**
		 * Auto generated setter method
		 * @param param AgentFlag
		 */
		public void setAgentFlag(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localAgentFlagTracker = true;
			} else {
				localAgentFlagTracker = false;

			}

			this.localAgentFlag = param;

		}

		/**
		 * field for DiscType
		 */

		protected java.lang.String localDiscType;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localDiscTypeTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getDiscType() {
			return localDiscType;
		}

		/**
		 * Auto generated setter method
		 * @param param DiscType
		 */
		public void setDiscType(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localDiscTypeTracker = true;
			} else {
				localDiscTypeTracker = false;

			}

			this.localDiscType = param;

		}

		/**
		 * field for DiscDt
		 */

		protected java.lang.String localDiscDt;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localDiscDtTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getDiscDt() {
			return localDiscDt;
		}

		/**
		 * Auto generated setter method
		 * @param param DiscDt
		 */
		public void setDiscDt(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localDiscDtTracker = true;
			} else {
				localDiscDtTracker = false;

			}

			this.localDiscDt = param;

		}

		/**
		 * field for BillClass
		 */

		protected java.lang.String localBillClass;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localBillClassTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getBillClass() {
			return localBillClass;
		}

		/**
		 * Auto generated setter method
		 * @param param BillClass
		 */
		public void setBillClass(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localBillClassTracker = true;
			} else {
				localBillClassTracker = false;

			}

			this.localBillClass = param;

		}

		/**
		 * field for BillType
		 */

		protected java.lang.String localBillType;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localBillTypeTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getBillType() {
			return localBillType;
		}

		/**
		 * Auto generated setter method
		 * @param param BillType
		 */
		public void setBillType(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localBillTypeTracker = true;
			} else {
				localBillTypeTracker = false;

			}

			this.localBillType = param;

		}

		/**
		 * field for Rate
		 */

		protected java.lang.String localRate;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localRateTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getRate() {
			return localRate;
		}

		/**
		 * Auto generated setter method
		 * @param param Rate
		 */
		public void setRate(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localRateTracker = true;
			} else {
				localRateTracker = false;

			}

			this.localRate = param;

		}

		/**
		 * field for RateType
		 */

		protected java.lang.String localRateType;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localRateTypeTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getRateType() {
			return localRateType;
		}

		/**
		 * Auto generated setter method
		 * @param param RateType
		 */
		public void setRateType(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localRateTypeTracker = true;
			} else {
				localRateTypeTracker = false;

			}

			this.localRateType = param;

		}

		/**
		 * field for PayType
		 */

		protected java.lang.String localPayType;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localPayTypeTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getPayType() {
			return localPayType;
		}

		/**
		 * Auto generated setter method
		 * @param param PayType
		 */
		public void setPayType(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localPayTypeTracker = true;
			} else {
				localPayTypeTracker = false;

			}

			this.localPayType = param;

		}

		/**
		 * field for IsPayCustInnerBank
		 */

		protected java.lang.String localIsPayCustInnerBank;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localIsPayCustInnerBankTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getIsPayCustInnerBank() {
			return localIsPayCustInnerBank;
		}

		/**
		 * Auto generated setter method
		 * @param param IsPayCustInnerBank
		 */
		public void setIsPayCustInnerBank(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localIsPayCustInnerBankTracker = true;
			} else {
				localIsPayCustInnerBankTracker = false;

			}

			this.localIsPayCustInnerBank = param;

		}

		/**
		 * field for IsThirdCustInnerBank
		 */

		protected java.lang.String localIsThirdCustInnerBank;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localIsThirdCustInnerBankTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getIsThirdCustInnerBank() {
			return localIsThirdCustInnerBank;
		}

		/**
		 * Auto generated setter method
		 * @param param IsThirdCustInnerBank
		 */
		public void setIsThirdCustInnerBank(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localIsThirdCustInnerBankTracker = true;
			} else {
				localIsThirdCustInnerBankTracker = false;

			}

			this.localIsThirdCustInnerBank = param;

		}

		/**
		 * field for PayCustName
		 */

		protected java.lang.String localPayCustName;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localPayCustNameTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getPayCustName() {
			return localPayCustName;
		}

		/**
		 * Auto generated setter method
		 * @param param PayCustName
		 */
		public void setPayCustName(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localPayCustNameTracker = true;
			} else {
				localPayCustNameTracker = false;

			}

			this.localPayCustName = param;

		}

		/**
		 * field for PayBankNo
		 */

		protected java.lang.String localPayBankNo;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localPayBankNoTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getPayBankNo() {
			return localPayBankNo;
		}

		/**
		 * Auto generated setter method
		 * @param param PayBankNo
		 */
		public void setPayBankNo(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localPayBankNoTracker = true;
			} else {
				localPayBankNoTracker = false;

			}

			this.localPayBankNo = param;

		}

		/**
		 * field for PayCustNo
		 */

		protected java.lang.String localPayCustNo;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localPayCustNoTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getPayCustNo() {
			return localPayCustNo;
		}

		/**
		 * Auto generated setter method
		 * @param param PayCustNo
		 */
		public void setPayCustNo(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localPayCustNoTracker = true;
			} else {
				localPayCustNoTracker = false;

			}

			this.localPayCustNo = param;

		}

		/**
		 * field for PayAcctNo
		 */

		protected java.lang.String localPayAcctNo;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localPayAcctNoTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getPayAcctNo() {
			return localPayAcctNo;
		}

		/**
		 * Auto generated setter method
		 * @param param PayAcctNo
		 */
		public void setPayAcctNo(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localPayAcctNoTracker = true;
			} else {
				localPayAcctNoTracker = false;

			}

			this.localPayAcctNo = param;

		}

		/**
		 * field for ThirdCustName
		 */

		protected java.lang.String localThirdCustName;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localThirdCustNameTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getThirdCustName() {
			return localThirdCustName;
		}

		/**
		 * Auto generated setter method
		 * @param param ThirdCustName
		 */
		public void setThirdCustName(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localThirdCustNameTracker = true;
			} else {
				localThirdCustNameTracker = false;

			}

			this.localThirdCustName = param;

		}

		/**
		 * field for ThirdBankNo
		 */

		protected java.lang.String localThirdBankNo;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localThirdBankNoTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getThirdBankNo() {
			return localThirdBankNo;
		}

		/**
		 * Auto generated setter method
		 * @param param ThirdBankNo
		 */
		public void setThirdBankNo(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localThirdBankNoTracker = true;
			} else {
				localThirdBankNoTracker = false;

			}

			this.localThirdBankNo = param;

		}

		/**
		 * field for ThirdCustNo
		 */

		protected java.lang.String localThirdCustNo;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localThirdCustNoTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getThirdCustNo() {
			return localThirdCustNo;
		}

		/**
		 * Auto generated setter method
		 * @param param ThirdCustNo
		 */
		public void setThirdCustNo(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localThirdCustNoTracker = true;
			} else {
				localThirdCustNoTracker = false;

			}

			this.localThirdCustNo = param;

		}

		/**
		 * field for ThirdAcctNo
		 */

		protected java.lang.String localThirdAcctNo;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localThirdAcctNoTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getThirdAcctNo() {
			return localThirdAcctNo;
		}

		/**
		 * Auto generated setter method
		 * @param param ThirdAcctNo
		 */
		public void setThirdAcctNo(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localThirdAcctNoTracker = true;
			} else {
				localThirdAcctNoTracker = false;

			}

			this.localThirdAcctNo = param;

		}

		/**
		 * field for CustNo
		 */

		protected java.lang.String localCustNo;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localCustNoTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getCustNo() {
			return localCustNo;
		}

		/**
		 * Auto generated setter method
		 * @param param CustNo
		 */
		public void setCustNo(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localCustNoTracker = true;
			} else {
				localCustNoTracker = false;

			}

			this.localCustNo = param;

		}

		/**
		 * field for CustName
		 */

		protected java.lang.String localCustName;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localCustNameTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getCustName() {
			return localCustName;
		}

		/**
		 * Auto generated setter method
		 * @param param CustName
		 */
		public void setCustName(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localCustNameTracker = true;
			} else {
				localCustNameTracker = false;

			}

			this.localCustName = param;

		}

		/**
		 * field for CustAcctNo
		 */

		protected java.lang.String localCustAcctNo;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localCustAcctNoTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getCustAcctNo() {
			return localCustAcctNo;
		}

		/**
		 * Auto generated setter method
		 * @param param CustAcctNo
		 */
		public void setCustAcctNo(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localCustAcctNoTracker = true;
			} else {
				localCustAcctNoTracker = false;

			}

			this.localCustAcctNo = param;

		}

		/**
		 * field for BuyPayRate
		 */

		protected java.lang.String localBuyPayRate;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localBuyPayRateTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getBuyPayRate() {
			return localBuyPayRate;
		}

		/**
		 * Auto generated setter method
		 * @param param BuyPayRate
		 */
		public void setBuyPayRate(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localBuyPayRateTracker = true;
			} else {
				localBuyPayRateTracker = false;

			}

			this.localBuyPayRate = param;

		}

		/**
		 * field for ThirdPayRate
		 */

		protected java.lang.String localThirdPayRate;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localThirdPayRateTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getThirdPayRate() {
			return localThirdPayRate;
		}

		/**
		 * Auto generated setter method
		 * @param param ThirdPayRate
		 */
		public void setThirdPayRate(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localThirdPayRateTracker = true;
			} else {
				localThirdPayRateTracker = false;

			}

			this.localThirdPayRate = param;

		}

		/**
		 * field for MagrNo
		 */

		protected java.lang.String localMagrNo;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localMagrNoTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getMagrNo() {
			return localMagrNo;
		}

		/**
		 * Auto generated setter method
		 * @param param MagrNo
		 */
		public void setMagrNo(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localMagrNoTracker = true;
			} else {
				localMagrNoTracker = false;

			}

			this.localMagrNo = param;

		}

		/**
		 * field for MagrName
		 */

		protected java.lang.String localMagrName;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localMagrNameTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getMagrName() {
			return localMagrName;
		}

		/**
		 * Auto generated setter method
		 * @param param MagrName
		 */
		public void setMagrName(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localMagrNameTracker = true;
			} else {
				localMagrNameTracker = false;

			}

			this.localMagrName = param;

		}

		/**
		 * field for MagrBranchNo
		 */

		protected java.lang.String localMagrBranchNo;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localMagrBranchNoTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getMagrBranchNo() {
			return localMagrBranchNo;
		}

		/**
		 * Auto generated setter method
		 * @param param MagrBranchNo
		 */
		public void setMagrBranchNo(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localMagrBranchNoTracker = true;
			} else {
				localMagrBranchNoTracker = false;

			}

			this.localMagrBranchNo = param;

		}

		/**
		 * field for RedeemRate
		 */

		protected java.lang.String localRedeemRate;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localRedeemRateTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getRedeemRate() {
			return localRedeemRate;
		}

		/**
		 * Auto generated setter method
		 * @param param RedeemRate
		 */
		public void setRedeemRate(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localRedeemRateTracker = true;
			} else {
				localRedeemRateTracker = false;

			}

			this.localRedeemRate = param;

		}

		/**
		 * field for IfTc
		 */

		protected java.lang.String localIfTc;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localIfTcTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getIfTc() {
			return localIfTc;
		}

		/**
		 * Auto generated setter method
		 * @param param IfTc
		 */
		public void setIfTc(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localIfTcTracker = true;
			} else {
				localIfTcTracker = false;

			}

			this.localIfTc = param;

		}

		/**
		 * field for AssuranceRatio
		 */

		protected java.lang.String localAssuranceRatio;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localAssuranceRatioTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getAssuranceRatio() {
			return localAssuranceRatio;
		}

		/**
		 * Auto generated setter method
		 * @param param AssuranceRatio
		 */
		public void setAssuranceRatio(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localAssuranceRatioTracker = true;
			} else {
				localAssuranceRatioTracker = false;

			}

			this.localAssuranceRatio = param;

		}

		/**
		 * field for AssuranceAccountNo
		 */

		protected java.lang.String localAssuranceAccountNo;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localAssuranceAccountNoTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getAssuranceAccountNo() {
			return localAssuranceAccountNo;
		}

		/**
		 * Auto generated setter method
		 * @param param AssuranceAccountNo
		 */
		public void setAssuranceAccountNo(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localAssuranceAccountNoTracker = true;
			} else {
				localAssuranceAccountNoTracker = false;

			}

			this.localAssuranceAccountNo = param;

		}

		/**
		 * field for GuaranteeMode
		 */

		protected java.lang.String localGuaranteeMode;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localGuaranteeModeTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getGuaranteeMode() {
			return localGuaranteeMode;
		}

		/**
		 * Auto generated setter method
		 * @param param GuaranteeMode
		 */
		public void setGuaranteeMode(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localGuaranteeModeTracker = true;
			} else {
				localGuaranteeModeTracker = false;

			}

			this.localGuaranteeMode = param;

		}

		/**
		 * field for BranchNo
		 */

		protected java.lang.String localBranchNo;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localBranchNoTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getBranchNo() {
			return localBranchNo;
		}

		/**
		 * Auto generated setter method
		 * @param param BranchNo
		 */
		public void setBranchNo(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localBranchNoTracker = true;
			} else {
				localBranchNoTracker = false;

			}

			this.localBranchNo = param;

		}

		/**
		 * field for UserNo
		 */

		protected java.lang.String localUserNo;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localUserNoTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getUserNo() {
			return localUserNo;
		}

		/**
		 * Auto generated setter method
		 * @param param UserNo
		 */
		public void setUserNo(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localUserNoTracker = true;
			} else {
				localUserNoTracker = false;

			}

			this.localUserNo = param;

		}

		/**
		 * field for ConsumerSeqNo
		 */

		protected java.lang.String localConsumerSeqNo;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localConsumerSeqNoTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getConsumerSeqNo() {
			return localConsumerSeqNo;
		}

		/**
		 * Auto generated setter method
		 * @param param ConsumerSeqNo
		 */
		public void setConsumerSeqNo(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localConsumerSeqNoTracker = true;
			} else {
				localConsumerSeqNoTracker = false;

			}

			this.localConsumerSeqNo = param;

		}

		/**
		 * field for BussSeqNo
		 */

		protected java.lang.String localBussSeqNo;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localBussSeqNoTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getBussSeqNo() {
			return localBussSeqNo;
		}

		/**
		 * Auto generated setter method
		 * @param param BussSeqNo
		 */
		public void setBussSeqNo(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localBussSeqNoTracker = true;
			} else {
				localBussSeqNoTracker = false;

			}

			this.localBussSeqNo = param;

		}

		/**
		 * field for TransDate
		 */

		protected java.lang.String localTransDate;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localTransDateTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getTransDate() {
			return localTransDate;
		}

		/**
		 * Auto generated setter method
		 * @param param TransDate
		 */
		public void setTransDate(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localTransDateTracker = true;
			} else {
				localTransDateTracker = false;

			}

			this.localTransDate = param;

		}

		/**
		 * field for WsId
		 */

		protected java.lang.String localWsId;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localWsIdTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getWsId() {
			return localWsId;
		}

		/**
		 * Auto generated setter method
		 * @param param WsId
		 */
		public void setWsId(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localWsIdTracker = true;
			} else {
				localWsIdTracker = false;

			}

			this.localWsId = param;

		}

		/**
		 * field for TransFlag
		 */

		protected java.lang.String localTransFlag;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localTransFlagTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getTransFlag() {
			return localTransFlag;
		}

		/**
		 * Auto generated setter method
		 * @param param TransFlag
		 */
		public void setTransFlag(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localTransFlagTracker = true;
			} else {
				localTransFlagTracker = false;

			}

			this.localTransFlag = param;

		}

		/**
		 * field for TotalAmt
		 */

		protected java.lang.String localTotalAmt;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localTotalAmtTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getTotalAmt() {
			return localTotalAmt;
		}

		/**
		 * Auto generated setter method
		 * @param param TotalAmt
		 */
		public void setTotalAmt(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localTotalAmtTracker = true;
			} else {
				localTotalAmtTracker = false;

			}

			this.localTotalAmt = param;

		}

		/**
		 * field for IsRedeem
		 */

		protected java.lang.String localIsRedeem;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localIsRedeemTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getIsRedeem() {
			return localIsRedeem;
		}

		/**
		 * Auto generated setter method
		 * @param param IsRedeem
		 */
		public void setIsRedeem(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localIsRedeemTracker = true;
			} else {
				localIsRedeemTracker = false;

			}

			this.localIsRedeem = param;

		}

		/**
		 * field for DiscountRanOpenDate
		 */

		protected java.lang.String localDiscountRanOpenDate;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localDiscountRanOpenDateTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getDiscountRanOpenDate() {
			return localDiscountRanOpenDate;
		}

		/**
		 * Auto generated setter method
		 * @param param DiscountRanOpenDate
		 */
		public void setDiscountRanOpenDate(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localDiscountRanOpenDateTracker = true;
			} else {
				localDiscountRanOpenDateTracker = false;

			}

			this.localDiscountRanOpenDate = param;

		}

		/**
		 * field for RedeemEndDt
		 */

		protected java.lang.String localRedeemEndDt;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localRedeemEndDtTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getRedeemEndDt() {
			return localRedeemEndDt;
		}

		/**
		 * Auto generated setter method
		 * @param param RedeemEndDt
		 */
		public void setRedeemEndDt(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localRedeemEndDtTracker = true;
			} else {
				localRedeemEndDtTracker = false;

			}

			this.localRedeemEndDt = param;

		}

		/**
		 * field for RedeemRateType
		 */

		protected java.lang.String localRedeemRateType;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localRedeemRateTypeTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getRedeemRateType() {
			return localRedeemRateType;
		}

		/**
		 * Auto generated setter method
		 * @param param RedeemRateType
		 */
		public void setRedeemRateType(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localRedeemRateTypeTracker = true;
			} else {
				localRedeemRateTypeTracker = false;

			}

			this.localRedeemRateType = param;

		}

		/**
		 * field for IsInstore
		 */

		protected java.lang.String localIsInstore;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localIsInstoreTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getIsInstore() {
			return localIsInstore;
		}

		/**
		 * Auto generated setter method
		 * @param param IsInstore
		 */
		public void setIsInstore(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localIsInstoreTracker = true;
			} else {
				localIsInstoreTracker = false;

			}

			this.localIsInstore = param;

		}

		/**
		 * field for IsDelayInstore
		 */

		protected java.lang.String localIsDelayInstore;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localIsDelayInstoreTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getIsDelayInstore() {
			return localIsDelayInstore;
		}

		/**
		 * Auto generated setter method
		 * @param param IsDelayInstore
		 */
		public void setIsDelayInstore(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localIsDelayInstoreTracker = true;
			} else {
				localIsDelayInstoreTracker = false;

			}

			this.localIsDelayInstore = param;

		}

		/**
		 * field for OnlineMark
		 */

		protected java.lang.String localOnlineMark;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localOnlineMarkTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getOnlineMark() {
			return localOnlineMark;
		}

		/**
		 * Auto generated setter method
		 * @param param OnlineMark
		 */
		public void setOnlineMark(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localOnlineMarkTracker = true;
			} else {
				localOnlineMarkTracker = false;

			}

			this.localOnlineMark = param;

		}

		/**
		 * field for Remark
		 */

		protected java.lang.String localRemark;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localRemarkTracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getRemark() {
			return localRemark;
		}

		/**
		 * Auto generated setter method
		 * @param param Remark
		 */
		public void setRemark(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localRemarkTracker = true;
			} else {
				localRemarkTracker = false;

			}

			this.localRemark = param;

		}

		/**
		 * field for Array
		 * This was an Array!
		 */

		protected FMT_CRMS_SVR_S01001010021003_IN_SUB[] localArray;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localArrayTracker = false;

		/**
		 * Auto generated getter method
		 * @return FMT_CRMS_SVR_S01001010021003_IN_SUB[]
		 */
		public FMT_CRMS_SVR_S01001010021003_IN_SUB[] getArray() {
			return localArray;
		}

		/**
		 * validate the array for Array
		 */
		protected void validateArray(FMT_CRMS_SVR_S01001010021003_IN_SUB[] param) {

		}

		/**
		 * Auto generated setter method
		 * @param param Array
		 */
		public void setArray(FMT_CRMS_SVR_S01001010021003_IN_SUB[] param) {

			validateArray(param);

			if (param != null) {
				// update the setting tracker
				localArrayTracker = true;
			} else {
				localArrayTracker = false;

			}

			this.localArray = param;
		}

		/**
		 * Auto generated add method for the array for convenience
		 * @param param FMT_CRMS_SVR_S01001010021003_IN_SUB
		 */
		public void addArray(FMT_CRMS_SVR_S01001010021003_IN_SUB param) {
			if (localArray == null) {
				localArray = new FMT_CRMS_SVR_S01001010021003_IN_SUB[] {};
			}

			// update the setting tracker
			localArrayTracker = true;

			java.util.List list = org.apache.axis2.databinding.utils.ConverterUtil.toList(localArray);
			list.add(param);
			this.localArray = (FMT_CRMS_SVR_S01001010021003_IN_SUB[]) list.toArray(new FMT_CRMS_SVR_S01001010021003_IN_SUB[list.size()]);

		}

		/**
		 * field for Reserve1
		 */

		protected java.lang.String localReserve1;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localReserve1Tracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getReserve1() {
			return localReserve1;
		}

		/**
		 * Auto generated setter method
		 * @param param Reserve1
		 */
		public void setReserve1(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localReserve1Tracker = true;
			} else {
				localReserve1Tracker = false;

			}

			this.localReserve1 = param;

		}

		/**
		 * field for Reserve2
		 */

		protected java.lang.String localReserve2;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localReserve2Tracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getReserve2() {
			return localReserve2;
		}

		/**
		 * Auto generated setter method
		 * @param param Reserve2
		 */
		public void setReserve2(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localReserve2Tracker = true;
			} else {
				localReserve2Tracker = false;

			}

			this.localReserve2 = param;

		}

		/**
		 * field for Reserve3
		 */

		protected java.lang.String localReserve3;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localReserve3Tracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getReserve3() {
			return localReserve3;
		}

		/**
		 * Auto generated setter method
		 * @param param Reserve3
		 */
		public void setReserve3(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localReserve3Tracker = true;
			} else {
				localReserve3Tracker = false;

			}

			this.localReserve3 = param;

		}

		/**
		 * field for Reserve4
		 */

		protected java.lang.String localReserve4;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localReserve4Tracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getReserve4() {
			return localReserve4;
		}

		/**
		 * Auto generated setter method
		 * @param param Reserve4
		 */
		public void setReserve4(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localReserve4Tracker = true;
			} else {
				localReserve4Tracker = false;

			}

			this.localReserve4 = param;

		}

		/**
		 * field for Reserve5
		 */

		protected java.lang.String localReserve5;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localReserve5Tracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getReserve5() {
			return localReserve5;
		}

		/**
		 * Auto generated setter method
		 * @param param Reserve5
		 */
		public void setReserve5(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localReserve5Tracker = true;
			} else {
				localReserve5Tracker = false;

			}

			this.localReserve5 = param;

		}

		/**
		 * field for Reserve6
		 */

		protected java.lang.String localReserve6;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localReserve6Tracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getReserve6() {
			return localReserve6;
		}

		/**
		 * Auto generated setter method
		 * @param param Reserve6
		 */
		public void setReserve6(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localReserve6Tracker = true;
			} else {
				localReserve6Tracker = false;

			}

			this.localReserve6 = param;

		}

		/**
		 * field for Reserve7
		 */

		protected java.lang.String localReserve7;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localReserve7Tracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getReserve7() {
			return localReserve7;
		}

		/**
		 * Auto generated setter method
		 * @param param Reserve7
		 */
		public void setReserve7(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localReserve7Tracker = true;
			} else {
				localReserve7Tracker = false;

			}

			this.localReserve7 = param;

		}

		/**
		 * field for Reserve8
		 */

		protected java.lang.String localReserve8;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localReserve8Tracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getReserve8() {
			return localReserve8;
		}

		/**
		 * Auto generated setter method
		 * @param param Reserve8
		 */
		public void setReserve8(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localReserve8Tracker = true;
			} else {
				localReserve8Tracker = false;

			}

			this.localReserve8 = param;

		}

		/**
		 * field for Reserve9
		 */

		protected java.lang.String localReserve9;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localReserve9Tracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getReserve9() {
			return localReserve9;
		}

		/**
		 * Auto generated setter method
		 * @param param Reserve9
		 */
		public void setReserve9(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localReserve9Tracker = true;
			} else {
				localReserve9Tracker = false;

			}

			this.localReserve9 = param;

		}

		/**
		 * field for Reserve10
		 */

		protected java.lang.String localReserve10;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method
		 * for this attribute. It will be used to determine whether to include this field
		 * in the serialized XML
		 */
		protected boolean localReserve10Tracker = false;

		/**
		 * Auto generated getter method
		 * @return java.lang.String
		 */
		public java.lang.String getReserve10() {
			return localReserve10;
		}

		/**
		 * Auto generated setter method
		 * @param param Reserve10
		 */
		public void setReserve10(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localReserve10Tracker = true;
			} else {
				localReserve10Tracker = false;

			}

			this.localReserve10 = param;

		}

		/**
		 * isReaderMTOMAware
		 * @return true if the reader supports MTOM
		 */
		public static boolean isReaderMTOMAware(javax.xml.stream.XMLStreamReader reader) {
			boolean isReaderMTOMAware = false;

			try {
				isReaderMTOMAware = java.lang.Boolean.TRUE.equals(reader.getProperty(org.apache.axiom.om.OMConstants.IS_DATA_HANDLERS_AWARE));
			} catch (java.lang.IllegalArgumentException e) {
				isReaderMTOMAware = false;
			}
			return isReaderMTOMAware;
		}

		/**
		 * @param parentQName
		 * @param factory
		 * @return org.apache.axiom.om.OMElement
		 */
		public org.apache.axiom.om.OMElement getOMElement(final javax.xml.namespace.QName parentQName, final org.apache.axiom.om.OMFactory factory) throws org.apache.axis2.databinding.ADBException {

			org.apache.axiom.om.OMDataSource dataSource = new org.apache.axis2.databinding.ADBDataSource(this, parentQName) {

				public void serialize(org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
					FMT_CRMS_SVR_S01001010021003_IN.this.serialize(parentQName, factory, xmlWriter);
				}
			};
			return new org.apache.axiom.om.impl.llom.OMSourcedElementImpl(parentQName, factory, dataSource);

		}

		public void serialize(final javax.xml.namespace.QName parentQName, final org.apache.axiom.om.OMFactory factory, org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {

			java.lang.String prefix = null;
			java.lang.String namespace = null;

			prefix = parentQName.getPrefix();
			namespace = parentQName.getNamespaceURI();

			if (namespace != null) {
				java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
				if (writerPrefix != null) {
					xmlWriter.writeStartElement(namespace, parentQName.getLocalPart());
				} else {
					if (prefix == null) {
						prefix = generatePrefix(namespace);
					}

					xmlWriter.writeStartElement(prefix, parentQName.getLocalPart(), namespace);
					xmlWriter.writeNamespace(prefix, namespace);
					xmlWriter.setPrefix(prefix, namespace);
				}
			} else {
				xmlWriter.writeStartElement(parentQName.getLocalPart());
			}
			if (localBusiLaunBrchTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "busiLaunBrch", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "busiLaunBrch");
					}

				} else {
					xmlWriter.writeStartElement("busiLaunBrch");
				}

				if (localBusiLaunBrch == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("busiLaunBrch cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localBusiLaunBrch);

				}

				xmlWriter.writeEndElement();
			}
			if (localAccoutBrchTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "accoutBrch", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "accoutBrch");
					}

				} else {
					xmlWriter.writeStartElement("accoutBrch");
				}

				if (localAccoutBrch == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("accoutBrch cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localAccoutBrch);

				}

				xmlWriter.writeEndElement();
			}
			if (localAccountOperBrchTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "accountOperBrch", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "accountOperBrch");
					}

				} else {
					xmlWriter.writeStartElement("accountOperBrch");
				}

				if (localAccountOperBrch == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("accountOperBrch cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localAccountOperBrch);

				}

				xmlWriter.writeEndElement();
			}
			if (localContractNoTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "contractNo", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "contractNo");
					}

				} else {
					xmlWriter.writeStartElement("contractNo");
				}

				if (localContractNo == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("contractNo cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localContractNo);

				}

				xmlWriter.writeEndElement();
			}
			if (localAgentFlagTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "agentFlag", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "agentFlag");
					}

				} else {
					xmlWriter.writeStartElement("agentFlag");
				}

				if (localAgentFlag == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("agentFlag cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localAgentFlag);

				}

				xmlWriter.writeEndElement();
			}
			if (localDiscTypeTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "discType", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "discType");
					}

				} else {
					xmlWriter.writeStartElement("discType");
				}

				if (localDiscType == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("discType cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localDiscType);

				}

				xmlWriter.writeEndElement();
			}
			if (localDiscDtTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "discDt", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "discDt");
					}

				} else {
					xmlWriter.writeStartElement("discDt");
				}

				if (localDiscDt == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("discDt cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localDiscDt);

				}

				xmlWriter.writeEndElement();
			}
			if (localBillClassTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "billClass", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "billClass");
					}

				} else {
					xmlWriter.writeStartElement("billClass");
				}

				if (localBillClass == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("billClass cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localBillClass);

				}

				xmlWriter.writeEndElement();
			}
			if (localBillTypeTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "billType", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "billType");
					}

				} else {
					xmlWriter.writeStartElement("billType");
				}

				if (localBillType == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("billType cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localBillType);

				}

				xmlWriter.writeEndElement();
			}
			if (localRateTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "rate", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "rate");
					}

				} else {
					xmlWriter.writeStartElement("rate");
				}

				if (localRate == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("rate cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localRate);

				}

				xmlWriter.writeEndElement();
			}
			if (localRateTypeTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "rateType", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "rateType");
					}

				} else {
					xmlWriter.writeStartElement("rateType");
				}

				if (localRateType == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("rateType cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localRateType);

				}

				xmlWriter.writeEndElement();
			}
			if (localPayTypeTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "payType", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "payType");
					}

				} else {
					xmlWriter.writeStartElement("payType");
				}

				if (localPayType == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("payType cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localPayType);

				}

				xmlWriter.writeEndElement();
			}
			if (localIsPayCustInnerBankTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "isPayCustInnerBank", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "isPayCustInnerBank");
					}

				} else {
					xmlWriter.writeStartElement("isPayCustInnerBank");
				}

				if (localIsPayCustInnerBank == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("isPayCustInnerBank cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localIsPayCustInnerBank);

				}

				xmlWriter.writeEndElement();
			}
			if (localIsThirdCustInnerBankTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "isThirdCustInnerBank", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "isThirdCustInnerBank");
					}

				} else {
					xmlWriter.writeStartElement("isThirdCustInnerBank");
				}

				if (localIsThirdCustInnerBank == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("isThirdCustInnerBank cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localIsThirdCustInnerBank);

				}

				xmlWriter.writeEndElement();
			}
			if (localPayCustNameTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "payCustName", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "payCustName");
					}

				} else {
					xmlWriter.writeStartElement("payCustName");
				}

				if (localPayCustName == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("payCustName cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localPayCustName);

				}

				xmlWriter.writeEndElement();
			}
			if (localPayBankNoTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "payBankNo", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "payBankNo");
					}

				} else {
					xmlWriter.writeStartElement("payBankNo");
				}

				if (localPayBankNo == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("payBankNo cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localPayBankNo);

				}

				xmlWriter.writeEndElement();
			}
			if (localPayCustNoTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "payCustNo", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "payCustNo");
					}

				} else {
					xmlWriter.writeStartElement("payCustNo");
				}

				if (localPayCustNo == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("payCustNo cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localPayCustNo);

				}

				xmlWriter.writeEndElement();
			}
			if (localPayAcctNoTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "payAcctNo", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "payAcctNo");
					}

				} else {
					xmlWriter.writeStartElement("payAcctNo");
				}

				if (localPayAcctNo == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("payAcctNo cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localPayAcctNo);

				}

				xmlWriter.writeEndElement();
			}
			if (localThirdCustNameTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "thirdCustName", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "thirdCustName");
					}

				} else {
					xmlWriter.writeStartElement("thirdCustName");
				}

				if (localThirdCustName == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("thirdCustName cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localThirdCustName);

				}

				xmlWriter.writeEndElement();
			}
			if (localThirdBankNoTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "thirdBankNo", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "thirdBankNo");
					}

				} else {
					xmlWriter.writeStartElement("thirdBankNo");
				}

				if (localThirdBankNo == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("thirdBankNo cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localThirdBankNo);

				}

				xmlWriter.writeEndElement();
			}
			if (localThirdCustNoTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "thirdCustNo", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "thirdCustNo");
					}

				} else {
					xmlWriter.writeStartElement("thirdCustNo");
				}

				if (localThirdCustNo == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("thirdCustNo cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localThirdCustNo);

				}

				xmlWriter.writeEndElement();
			}
			if (localThirdAcctNoTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "thirdAcctNo", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "thirdAcctNo");
					}

				} else {
					xmlWriter.writeStartElement("thirdAcctNo");
				}

				if (localThirdAcctNo == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("thirdAcctNo cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localThirdAcctNo);

				}

				xmlWriter.writeEndElement();
			}
			if (localCustNoTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "custNo", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "custNo");
					}

				} else {
					xmlWriter.writeStartElement("custNo");
				}

				if (localCustNo == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("custNo cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localCustNo);

				}

				xmlWriter.writeEndElement();
			}
			if (localCustNameTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "custName", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "custName");
					}

				} else {
					xmlWriter.writeStartElement("custName");
				}

				if (localCustName == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("custName cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localCustName);

				}

				xmlWriter.writeEndElement();
			}
			if (localCustAcctNoTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "custAcctNo", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "custAcctNo");
					}

				} else {
					xmlWriter.writeStartElement("custAcctNo");
				}

				if (localCustAcctNo == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("custAcctNo cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localCustAcctNo);

				}

				xmlWriter.writeEndElement();
			}
			if (localBuyPayRateTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "buyPayRate", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "buyPayRate");
					}

				} else {
					xmlWriter.writeStartElement("buyPayRate");
				}

				if (localBuyPayRate == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("buyPayRate cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localBuyPayRate);

				}

				xmlWriter.writeEndElement();
			}
			if (localThirdPayRateTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "thirdPayRate", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "thirdPayRate");
					}

				} else {
					xmlWriter.writeStartElement("thirdPayRate");
				}

				if (localThirdPayRate == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("thirdPayRate cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localThirdPayRate);

				}

				xmlWriter.writeEndElement();
			}
			if (localMagrNoTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "magrNo", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "magrNo");
					}

				} else {
					xmlWriter.writeStartElement("magrNo");
				}

				if (localMagrNo == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("magrNo cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localMagrNo);

				}

				xmlWriter.writeEndElement();
			}
			if (localMagrNameTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "magrName", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "magrName");
					}

				} else {
					xmlWriter.writeStartElement("magrName");
				}

				if (localMagrName == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("magrName cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localMagrName);

				}

				xmlWriter.writeEndElement();
			}
			if (localMagrBranchNoTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "magrBranchNo", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "magrBranchNo");
					}

				} else {
					xmlWriter.writeStartElement("magrBranchNo");
				}

				if (localMagrBranchNo == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("magrBranchNo cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localMagrBranchNo);

				}

				xmlWriter.writeEndElement();
			}
			if (localRedeemRateTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "redeemRate", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "redeemRate");
					}

				} else {
					xmlWriter.writeStartElement("redeemRate");
				}

				if (localRedeemRate == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("redeemRate cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localRedeemRate);

				}

				xmlWriter.writeEndElement();
			}
			if (localIfTcTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "ifTc", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "ifTc");
					}

				} else {
					xmlWriter.writeStartElement("ifTc");
				}

				if (localIfTc == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("ifTc cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localIfTc);

				}

				xmlWriter.writeEndElement();
			}
			if (localAssuranceRatioTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "assuranceRatio", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "assuranceRatio");
					}

				} else {
					xmlWriter.writeStartElement("assuranceRatio");
				}

				if (localAssuranceRatio == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("assuranceRatio cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localAssuranceRatio);

				}

				xmlWriter.writeEndElement();
			}
			if (localAssuranceAccountNoTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "assuranceAccountNo", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "assuranceAccountNo");
					}

				} else {
					xmlWriter.writeStartElement("assuranceAccountNo");
				}

				if (localAssuranceAccountNo == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("assuranceAccountNo cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localAssuranceAccountNo);

				}

				xmlWriter.writeEndElement();
			}
			if (localGuaranteeModeTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "guaranteeMode", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "guaranteeMode");
					}

				} else {
					xmlWriter.writeStartElement("guaranteeMode");
				}

				if (localGuaranteeMode == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("guaranteeMode cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localGuaranteeMode);

				}

				xmlWriter.writeEndElement();
			}
			if (localBranchNoTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "branchNo", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "branchNo");
					}

				} else {
					xmlWriter.writeStartElement("branchNo");
				}

				if (localBranchNo == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("branchNo cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localBranchNo);

				}

				xmlWriter.writeEndElement();
			}
			if (localUserNoTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "userNo", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "userNo");
					}

				} else {
					xmlWriter.writeStartElement("userNo");
				}

				if (localUserNo == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("userNo cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localUserNo);

				}

				xmlWriter.writeEndElement();
			}
			if (localConsumerSeqNoTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "consumerSeqNo", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "consumerSeqNo");
					}

				} else {
					xmlWriter.writeStartElement("consumerSeqNo");
				}

				if (localConsumerSeqNo == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("consumerSeqNo cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localConsumerSeqNo);

				}

				xmlWriter.writeEndElement();
			}
			if (localBussSeqNoTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "bussSeqNo", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "bussSeqNo");
					}

				} else {
					xmlWriter.writeStartElement("bussSeqNo");
				}

				if (localBussSeqNo == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("bussSeqNo cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localBussSeqNo);

				}

				xmlWriter.writeEndElement();
			}
			if (localTransDateTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "transDate", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "transDate");
					}

				} else {
					xmlWriter.writeStartElement("transDate");
				}

				if (localTransDate == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("transDate cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localTransDate);

				}

				xmlWriter.writeEndElement();
			}
			if (localWsIdTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "wsId", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "wsId");
					}

				} else {
					xmlWriter.writeStartElement("wsId");
				}

				if (localWsId == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("wsId cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localWsId);

				}

				xmlWriter.writeEndElement();
			}
			if (localTransFlagTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "transFlag", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "transFlag");
					}

				} else {
					xmlWriter.writeStartElement("transFlag");
				}

				if (localTransFlag == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("transFlag cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localTransFlag);

				}

				xmlWriter.writeEndElement();
			}
			if (localTotalAmtTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "totalAmt", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "totalAmt");
					}

				} else {
					xmlWriter.writeStartElement("totalAmt");
				}

				if (localTotalAmt == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("totalAmt cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localTotalAmt);

				}

				xmlWriter.writeEndElement();
			}
			if (localIsRedeemTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "isRedeem", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "isRedeem");
					}

				} else {
					xmlWriter.writeStartElement("isRedeem");
				}

				if (localIsRedeem == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("isRedeem cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localIsRedeem);

				}

				xmlWriter.writeEndElement();
			}
			if (localDiscountRanOpenDateTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "discountRanOpenDate", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "discountRanOpenDate");
					}

				} else {
					xmlWriter.writeStartElement("discountRanOpenDate");
				}

				if (localDiscountRanOpenDate == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("discountRanOpenDate cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localDiscountRanOpenDate);

				}

				xmlWriter.writeEndElement();
			}
			if (localRedeemEndDtTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "redeemEndDt", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "redeemEndDt");
					}

				} else {
					xmlWriter.writeStartElement("redeemEndDt");
				}

				if (localRedeemEndDt == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("redeemEndDt cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localRedeemEndDt);

				}

				xmlWriter.writeEndElement();
			}
			if (localRedeemRateTypeTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "redeemRateType", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "redeemRateType");
					}

				} else {
					xmlWriter.writeStartElement("redeemRateType");
				}

				if (localRedeemRateType == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("redeemRateType cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localRedeemRateType);

				}

				xmlWriter.writeEndElement();
			}
			if (localIsInstoreTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "isInstore", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "isInstore");
					}

				} else {
					xmlWriter.writeStartElement("isInstore");
				}

				if (localIsInstore == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("isInstore cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localIsInstore);

				}

				xmlWriter.writeEndElement();
			}
			if (localIsDelayInstoreTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "isDelayInstore", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "isDelayInstore");
					}

				} else {
					xmlWriter.writeStartElement("isDelayInstore");
				}

				if (localIsDelayInstore == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("isDelayInstore cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localIsDelayInstore);

				}

				xmlWriter.writeEndElement();
			}
			if (localOnlineMarkTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "onlineMark", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "onlineMark");
					}

				} else {
					xmlWriter.writeStartElement("onlineMark");
				}

				if (localOnlineMark == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("onlineMark cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localOnlineMark);

				}

				xmlWriter.writeEndElement();
			}
			if (localRemarkTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "remark", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "remark");
					}

				} else {
					xmlWriter.writeStartElement("remark");
				}

				if (localRemark == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("remark cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localRemark);

				}

				xmlWriter.writeEndElement();
			}
			if (localArrayTracker) {
				if (localArray != null) {
					for (int i = 0; i < localArray.length; i++) {
						if (localArray[i] != null) {
							localArray[i].serialize(new javax.xml.namespace.QName("", "array"), factory, xmlWriter);
						} else {

							// we don't have to do any thing since minOccures is zero

						}

					}
				} else {

					throw new org.apache.axis2.databinding.ADBException("array cannot be null!!");

				}
			}
			if (localReserve1Tracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "reserve1", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "reserve1");
					}

				} else {
					xmlWriter.writeStartElement("reserve1");
				}

				if (localReserve1 == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("reserve1 cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localReserve1);

				}

				xmlWriter.writeEndElement();
			}
			if (localReserve2Tracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "reserve2", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "reserve2");
					}

				} else {
					xmlWriter.writeStartElement("reserve2");
				}

				if (localReserve2 == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("reserve2 cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localReserve2);

				}

				xmlWriter.writeEndElement();
			}
			if (localReserve3Tracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "reserve3", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "reserve3");
					}

				} else {
					xmlWriter.writeStartElement("reserve3");
				}

				if (localReserve3 == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("reserve3 cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localReserve3);

				}

				xmlWriter.writeEndElement();
			}
			if (localReserve4Tracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "reserve4", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "reserve4");
					}

				} else {
					xmlWriter.writeStartElement("reserve4");
				}

				if (localReserve4 == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("reserve4 cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localReserve4);

				}

				xmlWriter.writeEndElement();
			}
			if (localReserve5Tracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "reserve5", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "reserve5");
					}

				} else {
					xmlWriter.writeStartElement("reserve5");
				}

				if (localReserve5 == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("reserve5 cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localReserve5);

				}

				xmlWriter.writeEndElement();
			}
			if (localReserve6Tracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "reserve6", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "reserve6");
					}

				} else {
					xmlWriter.writeStartElement("reserve6");
				}

				if (localReserve6 == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("reserve6 cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localReserve6);

				}

				xmlWriter.writeEndElement();
			}
			if (localReserve7Tracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "reserve7", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "reserve7");
					}

				} else {
					xmlWriter.writeStartElement("reserve7");
				}

				if (localReserve7 == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("reserve7 cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localReserve7);

				}

				xmlWriter.writeEndElement();
			}
			if (localReserve8Tracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "reserve8", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "reserve8");
					}

				} else {
					xmlWriter.writeStartElement("reserve8");
				}

				if (localReserve8 == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("reserve8 cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localReserve8);

				}

				xmlWriter.writeEndElement();
			}
			if (localReserve9Tracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "reserve9", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "reserve9");
					}

				} else {
					xmlWriter.writeStartElement("reserve9");
				}

				if (localReserve9 == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("reserve9 cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localReserve9);

				}

				xmlWriter.writeEndElement();
			}
			if (localReserve10Tracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "reserve10", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "reserve10");
					}

				} else {
					xmlWriter.writeStartElement("reserve10");
				}

				if (localReserve10 == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("reserve10 cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localReserve10);

				}

				xmlWriter.writeEndElement();
			}
			xmlWriter.writeEndElement();

		}

		/**
		 * Util method to write an attribute with the ns prefix
		 */
		private void writeAttribute(java.lang.String prefix, java.lang.String namespace, java.lang.String attName, java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			if (xmlWriter.getPrefix(namespace) == null) {
				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);

			}

			xmlWriter.writeAttribute(namespace, attName, attValue);

		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeAttribute(java.lang.String namespace, java.lang.String attName, java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			if (namespace.equals("")) {
				xmlWriter.writeAttribute(attName, attValue);
			} else {
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attValue);
			}
		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeQNameAttribute(java.lang.String namespace, java.lang.String attName, javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {

			java.lang.String attributeNamespace = qname.getNamespaceURI();
			java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
			if (attributePrefix == null) {
				attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
			}
			java.lang.String attributeValue;
			if (attributePrefix.trim().length() > 0) {
				attributeValue = attributePrefix + ":" + qname.getLocalPart();
			} else {
				attributeValue = qname.getLocalPart();
			}

			if (namespace.equals("")) {
				xmlWriter.writeAttribute(attName, attributeValue);
			} else {
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attributeValue);
			}
		}

		/**
		 * method to handle Qnames
		 */

		private void writeQName(javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
			java.lang.String namespaceURI = qname.getNamespaceURI();
			if (namespaceURI != null) {
				java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
				if (prefix == null) {
					prefix = generatePrefix(namespaceURI);
					xmlWriter.writeNamespace(prefix, namespaceURI);
					xmlWriter.setPrefix(prefix, namespaceURI);
				}

				if (prefix.trim().length() > 0) {
					xmlWriter.writeCharacters(prefix + ":" + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
				} else {
					// i.e this is the default namespace
					xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
				}

			} else {
				xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
			}
		}

		private void writeQNames(javax.xml.namespace.QName[] qnames, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

			if (qnames != null) {
				// we have to store this data until last moment since it is not possible to write any
				// namespace data after writing the charactor data
				java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
				java.lang.String namespaceURI = null;
				java.lang.String prefix = null;

				for (int i = 0; i < qnames.length; i++) {
					if (i > 0) {
						stringToWrite.append(" ");
					}
					namespaceURI = qnames[i].getNamespaceURI();
					if (namespaceURI != null) {
						prefix = xmlWriter.getPrefix(namespaceURI);
						if ((prefix == null) || (prefix.length() == 0)) {
							prefix = generatePrefix(namespaceURI);
							xmlWriter.writeNamespace(prefix, namespaceURI);
							xmlWriter.setPrefix(prefix, namespaceURI);
						}

						if (prefix.trim().length() > 0) {
							stringToWrite.append(prefix).append(":").append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
						} else {
							stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
						}
					} else {
						stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
					}
				}
				xmlWriter.writeCharacters(stringToWrite.toString());
			}

		}

		/**
		 * Register a namespace prefix
		 */
		private java.lang.String registerPrefix(javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace) throws javax.xml.stream.XMLStreamException {
			java.lang.String prefix = xmlWriter.getPrefix(namespace);

			if (prefix == null) {
				prefix = generatePrefix(namespace);

				while (xmlWriter.getNamespaceContext().getNamespaceURI(prefix) != null) {
					prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
				}

				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);
			}

			return prefix;
		}

		/**
		 * databinding method to get an XML representation of this object
		 */
		public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName) throws org.apache.axis2.databinding.ADBException {

			java.util.ArrayList elementList = new java.util.ArrayList();
			java.util.ArrayList attribList = new java.util.ArrayList();

			if (localBusiLaunBrchTracker) {
				elementList.add(new javax.xml.namespace.QName("", "busiLaunBrch"));

				if (localBusiLaunBrch != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localBusiLaunBrch));
				} else {
					throw new org.apache.axis2.databinding.ADBException("busiLaunBrch cannot be null!!");
				}
			}
			if (localAccoutBrchTracker) {
				elementList.add(new javax.xml.namespace.QName("", "accoutBrch"));

				if (localAccoutBrch != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localAccoutBrch));
				} else {
					throw new org.apache.axis2.databinding.ADBException("accoutBrch cannot be null!!");
				}
			}
			if (localAccountOperBrchTracker) {
				elementList.add(new javax.xml.namespace.QName("", "accountOperBrch"));

				if (localAccountOperBrch != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localAccountOperBrch));
				} else {
					throw new org.apache.axis2.databinding.ADBException("accountOperBrch cannot be null!!");
				}
			}
			if (localContractNoTracker) {
				elementList.add(new javax.xml.namespace.QName("", "contractNo"));

				if (localContractNo != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localContractNo));
				} else {
					throw new org.apache.axis2.databinding.ADBException("contractNo cannot be null!!");
				}
			}
			if (localAgentFlagTracker) {
				elementList.add(new javax.xml.namespace.QName("", "agentFlag"));

				if (localAgentFlag != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localAgentFlag));
				} else {
					throw new org.apache.axis2.databinding.ADBException("agentFlag cannot be null!!");
				}
			}
			if (localDiscTypeTracker) {
				elementList.add(new javax.xml.namespace.QName("", "discType"));

				if (localDiscType != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localDiscType));
				} else {
					throw new org.apache.axis2.databinding.ADBException("discType cannot be null!!");
				}
			}
			if (localDiscDtTracker) {
				elementList.add(new javax.xml.namespace.QName("", "discDt"));

				if (localDiscDt != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localDiscDt));
				} else {
					throw new org.apache.axis2.databinding.ADBException("discDt cannot be null!!");
				}
			}
			if (localBillClassTracker) {
				elementList.add(new javax.xml.namespace.QName("", "billClass"));

				if (localBillClass != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localBillClass));
				} else {
					throw new org.apache.axis2.databinding.ADBException("billClass cannot be null!!");
				}
			}
			if (localBillTypeTracker) {
				elementList.add(new javax.xml.namespace.QName("", "billType"));

				if (localBillType != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localBillType));
				} else {
					throw new org.apache.axis2.databinding.ADBException("billType cannot be null!!");
				}
			}
			if (localRateTracker) {
				elementList.add(new javax.xml.namespace.QName("", "rate"));

				if (localRate != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localRate));
				} else {
					throw new org.apache.axis2.databinding.ADBException("rate cannot be null!!");
				}
			}
			if (localRateTypeTracker) {
				elementList.add(new javax.xml.namespace.QName("", "rateType"));

				if (localRateType != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localRateType));
				} else {
					throw new org.apache.axis2.databinding.ADBException("rateType cannot be null!!");
				}
			}
			if (localPayTypeTracker) {
				elementList.add(new javax.xml.namespace.QName("", "payType"));

				if (localPayType != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localPayType));
				} else {
					throw new org.apache.axis2.databinding.ADBException("payType cannot be null!!");
				}
			}
			if (localIsPayCustInnerBankTracker) {
				elementList.add(new javax.xml.namespace.QName("", "isPayCustInnerBank"));

				if (localIsPayCustInnerBank != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localIsPayCustInnerBank));
				} else {
					throw new org.apache.axis2.databinding.ADBException("isPayCustInnerBank cannot be null!!");
				}
			}
			if (localIsThirdCustInnerBankTracker) {
				elementList.add(new javax.xml.namespace.QName("", "isThirdCustInnerBank"));

				if (localIsThirdCustInnerBank != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localIsThirdCustInnerBank));
				} else {
					throw new org.apache.axis2.databinding.ADBException("isThirdCustInnerBank cannot be null!!");
				}
			}
			if (localPayCustNameTracker) {
				elementList.add(new javax.xml.namespace.QName("", "payCustName"));

				if (localPayCustName != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localPayCustName));
				} else {
					throw new org.apache.axis2.databinding.ADBException("payCustName cannot be null!!");
				}
			}
			if (localPayBankNoTracker) {
				elementList.add(new javax.xml.namespace.QName("", "payBankNo"));

				if (localPayBankNo != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localPayBankNo));
				} else {
					throw new org.apache.axis2.databinding.ADBException("payBankNo cannot be null!!");
				}
			}
			if (localPayCustNoTracker) {
				elementList.add(new javax.xml.namespace.QName("", "payCustNo"));

				if (localPayCustNo != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localPayCustNo));
				} else {
					throw new org.apache.axis2.databinding.ADBException("payCustNo cannot be null!!");
				}
			}
			if (localPayAcctNoTracker) {
				elementList.add(new javax.xml.namespace.QName("", "payAcctNo"));

				if (localPayAcctNo != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localPayAcctNo));
				} else {
					throw new org.apache.axis2.databinding.ADBException("payAcctNo cannot be null!!");
				}
			}
			if (localThirdCustNameTracker) {
				elementList.add(new javax.xml.namespace.QName("", "thirdCustName"));

				if (localThirdCustName != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localThirdCustName));
				} else {
					throw new org.apache.axis2.databinding.ADBException("thirdCustName cannot be null!!");
				}
			}
			if (localThirdBankNoTracker) {
				elementList.add(new javax.xml.namespace.QName("", "thirdBankNo"));

				if (localThirdBankNo != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localThirdBankNo));
				} else {
					throw new org.apache.axis2.databinding.ADBException("thirdBankNo cannot be null!!");
				}
			}
			if (localThirdCustNoTracker) {
				elementList.add(new javax.xml.namespace.QName("", "thirdCustNo"));

				if (localThirdCustNo != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localThirdCustNo));
				} else {
					throw new org.apache.axis2.databinding.ADBException("thirdCustNo cannot be null!!");
				}
			}
			if (localThirdAcctNoTracker) {
				elementList.add(new javax.xml.namespace.QName("", "thirdAcctNo"));

				if (localThirdAcctNo != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localThirdAcctNo));
				} else {
					throw new org.apache.axis2.databinding.ADBException("thirdAcctNo cannot be null!!");
				}
			}
			if (localCustNoTracker) {
				elementList.add(new javax.xml.namespace.QName("", "custNo"));

				if (localCustNo != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCustNo));
				} else {
					throw new org.apache.axis2.databinding.ADBException("custNo cannot be null!!");
				}
			}
			if (localCustNameTracker) {
				elementList.add(new javax.xml.namespace.QName("", "custName"));

				if (localCustName != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCustName));
				} else {
					throw new org.apache.axis2.databinding.ADBException("custName cannot be null!!");
				}
			}
			if (localCustAcctNoTracker) {
				elementList.add(new javax.xml.namespace.QName("", "custAcctNo"));

				if (localCustAcctNo != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCustAcctNo));
				} else {
					throw new org.apache.axis2.databinding.ADBException("custAcctNo cannot be null!!");
				}
			}
			if (localBuyPayRateTracker) {
				elementList.add(new javax.xml.namespace.QName("", "buyPayRate"));

				if (localBuyPayRate != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localBuyPayRate));
				} else {
					throw new org.apache.axis2.databinding.ADBException("buyPayRate cannot be null!!");
				}
			}
			if (localThirdPayRateTracker) {
				elementList.add(new javax.xml.namespace.QName("", "thirdPayRate"));

				if (localThirdPayRate != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localThirdPayRate));
				} else {
					throw new org.apache.axis2.databinding.ADBException("thirdPayRate cannot be null!!");
				}
			}
			if (localMagrNoTracker) {
				elementList.add(new javax.xml.namespace.QName("", "magrNo"));

				if (localMagrNo != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localMagrNo));
				} else {
					throw new org.apache.axis2.databinding.ADBException("magrNo cannot be null!!");
				}
			}
			if (localMagrNameTracker) {
				elementList.add(new javax.xml.namespace.QName("", "magrName"));

				if (localMagrName != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localMagrName));
				} else {
					throw new org.apache.axis2.databinding.ADBException("magrName cannot be null!!");
				}
			}
			if (localMagrBranchNoTracker) {
				elementList.add(new javax.xml.namespace.QName("", "magrBranchNo"));

				if (localMagrBranchNo != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localMagrBranchNo));
				} else {
					throw new org.apache.axis2.databinding.ADBException("magrBranchNo cannot be null!!");
				}
			}
			if (localRedeemRateTracker) {
				elementList.add(new javax.xml.namespace.QName("", "redeemRate"));

				if (localRedeemRate != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localRedeemRate));
				} else {
					throw new org.apache.axis2.databinding.ADBException("redeemRate cannot be null!!");
				}
			}
			if (localIfTcTracker) {
				elementList.add(new javax.xml.namespace.QName("", "ifTc"));

				if (localIfTc != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localIfTc));
				} else {
					throw new org.apache.axis2.databinding.ADBException("ifTc cannot be null!!");
				}
			}
			if (localAssuranceRatioTracker) {
				elementList.add(new javax.xml.namespace.QName("", "assuranceRatio"));

				if (localAssuranceRatio != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localAssuranceRatio));
				} else {
					throw new org.apache.axis2.databinding.ADBException("assuranceRatio cannot be null!!");
				}
			}
			if (localAssuranceAccountNoTracker) {
				elementList.add(new javax.xml.namespace.QName("", "assuranceAccountNo"));

				if (localAssuranceAccountNo != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localAssuranceAccountNo));
				} else {
					throw new org.apache.axis2.databinding.ADBException("assuranceAccountNo cannot be null!!");
				}
			}
			if (localGuaranteeModeTracker) {
				elementList.add(new javax.xml.namespace.QName("", "guaranteeMode"));

				if (localGuaranteeMode != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localGuaranteeMode));
				} else {
					throw new org.apache.axis2.databinding.ADBException("guaranteeMode cannot be null!!");
				}
			}
			if (localBranchNoTracker) {
				elementList.add(new javax.xml.namespace.QName("", "branchNo"));

				if (localBranchNo != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localBranchNo));
				} else {
					throw new org.apache.axis2.databinding.ADBException("branchNo cannot be null!!");
				}
			}
			if (localUserNoTracker) {
				elementList.add(new javax.xml.namespace.QName("", "userNo"));

				if (localUserNo != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localUserNo));
				} else {
					throw new org.apache.axis2.databinding.ADBException("userNo cannot be null!!");
				}
			}
			if (localConsumerSeqNoTracker) {
				elementList.add(new javax.xml.namespace.QName("", "consumerSeqNo"));

				if (localConsumerSeqNo != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localConsumerSeqNo));
				} else {
					throw new org.apache.axis2.databinding.ADBException("consumerSeqNo cannot be null!!");
				}
			}
			if (localBussSeqNoTracker) {
				elementList.add(new javax.xml.namespace.QName("", "bussSeqNo"));

				if (localBussSeqNo != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localBussSeqNo));
				} else {
					throw new org.apache.axis2.databinding.ADBException("bussSeqNo cannot be null!!");
				}
			}
			if (localTransDateTracker) {
				elementList.add(new javax.xml.namespace.QName("", "transDate"));

				if (localTransDate != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localTransDate));
				} else {
					throw new org.apache.axis2.databinding.ADBException("transDate cannot be null!!");
				}
			}
			if (localWsIdTracker) {
				elementList.add(new javax.xml.namespace.QName("", "wsId"));

				if (localWsId != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localWsId));
				} else {
					throw new org.apache.axis2.databinding.ADBException("wsId cannot be null!!");
				}
			}
			if (localTransFlagTracker) {
				elementList.add(new javax.xml.namespace.QName("", "transFlag"));

				if (localTransFlag != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localTransFlag));
				} else {
					throw new org.apache.axis2.databinding.ADBException("transFlag cannot be null!!");
				}
			}
			if (localTotalAmtTracker) {
				elementList.add(new javax.xml.namespace.QName("", "totalAmt"));

				if (localTotalAmt != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localTotalAmt));
				} else {
					throw new org.apache.axis2.databinding.ADBException("totalAmt cannot be null!!");
				}
			}
			if (localIsRedeemTracker) {
				elementList.add(new javax.xml.namespace.QName("", "isRedeem"));

				if (localIsRedeem != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localIsRedeem));
				} else {
					throw new org.apache.axis2.databinding.ADBException("isRedeem cannot be null!!");
				}
			}
			if (localDiscountRanOpenDateTracker) {
				elementList.add(new javax.xml.namespace.QName("", "discountRanOpenDate"));

				if (localDiscountRanOpenDate != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localDiscountRanOpenDate));
				} else {
					throw new org.apache.axis2.databinding.ADBException("discountRanOpenDate cannot be null!!");
				}
			}
			if (localRedeemEndDtTracker) {
				elementList.add(new javax.xml.namespace.QName("", "redeemEndDt"));

				if (localRedeemEndDt != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localRedeemEndDt));
				} else {
					throw new org.apache.axis2.databinding.ADBException("redeemEndDt cannot be null!!");
				}
			}
			if (localRedeemRateTypeTracker) {
				elementList.add(new javax.xml.namespace.QName("", "redeemRateType"));

				if (localRedeemRateType != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localRedeemRateType));
				} else {
					throw new org.apache.axis2.databinding.ADBException("redeemRateType cannot be null!!");
				}
			}
			if (localIsInstoreTracker) {
				elementList.add(new javax.xml.namespace.QName("", "isInstore"));

				if (localIsInstore != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localIsInstore));
				} else {
					throw new org.apache.axis2.databinding.ADBException("isInstore cannot be null!!");
				}
			}
			if (localIsDelayInstoreTracker) {
				elementList.add(new javax.xml.namespace.QName("", "isDelayInstore"));

				if (localIsDelayInstore != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localIsDelayInstore));
				} else {
					throw new org.apache.axis2.databinding.ADBException("isDelayInstore cannot be null!!");
				}
			}
			if (localOnlineMarkTracker) {
				elementList.add(new javax.xml.namespace.QName("", "onlineMark"));

				if (localOnlineMark != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localOnlineMark));
				} else {
					throw new org.apache.axis2.databinding.ADBException("onlineMark cannot be null!!");
				}
			}
			if (localRemarkTracker) {
				elementList.add(new javax.xml.namespace.QName("", "remark"));

				if (localRemark != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localRemark));
				} else {
					throw new org.apache.axis2.databinding.ADBException("remark cannot be null!!");
				}
			}
			if (localArrayTracker) {
				if (localArray != null) {
					for (int i = 0; i < localArray.length; i++) {

						if (localArray[i] != null) {
							elementList.add(new javax.xml.namespace.QName("", "array"));
							elementList.add(localArray[i]);
						} else {

							// nothing to do

						}

					}
				} else {

					throw new org.apache.axis2.databinding.ADBException("array cannot be null!!");

				}

			}
			if (localReserve1Tracker) {
				elementList.add(new javax.xml.namespace.QName("", "reserve1"));

				if (localReserve1 != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localReserve1));
				} else {
					throw new org.apache.axis2.databinding.ADBException("reserve1 cannot be null!!");
				}
			}
			if (localReserve2Tracker) {
				elementList.add(new javax.xml.namespace.QName("", "reserve2"));

				if (localReserve2 != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localReserve2));
				} else {
					throw new org.apache.axis2.databinding.ADBException("reserve2 cannot be null!!");
				}
			}
			if (localReserve3Tracker) {
				elementList.add(new javax.xml.namespace.QName("", "reserve3"));

				if (localReserve3 != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localReserve3));
				} else {
					throw new org.apache.axis2.databinding.ADBException("reserve3 cannot be null!!");
				}
			}
			if (localReserve4Tracker) {
				elementList.add(new javax.xml.namespace.QName("", "reserve4"));

				if (localReserve4 != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localReserve4));
				} else {
					throw new org.apache.axis2.databinding.ADBException("reserve4 cannot be null!!");
				}
			}
			if (localReserve5Tracker) {
				elementList.add(new javax.xml.namespace.QName("", "reserve5"));

				if (localReserve5 != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localReserve5));
				} else {
					throw new org.apache.axis2.databinding.ADBException("reserve5 cannot be null!!");
				}
			}
			if (localReserve6Tracker) {
				elementList.add(new javax.xml.namespace.QName("", "reserve6"));

				if (localReserve6 != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localReserve6));
				} else {
					throw new org.apache.axis2.databinding.ADBException("reserve6 cannot be null!!");
				}
			}
			if (localReserve7Tracker) {
				elementList.add(new javax.xml.namespace.QName("", "reserve7"));

				if (localReserve7 != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localReserve7));
				} else {
					throw new org.apache.axis2.databinding.ADBException("reserve7 cannot be null!!");
				}
			}
			if (localReserve8Tracker) {
				elementList.add(new javax.xml.namespace.QName("", "reserve8"));

				if (localReserve8 != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localReserve8));
				} else {
					throw new org.apache.axis2.databinding.ADBException("reserve8 cannot be null!!");
				}
			}
			if (localReserve9Tracker) {
				elementList.add(new javax.xml.namespace.QName("", "reserve9"));

				if (localReserve9 != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localReserve9));
				} else {
					throw new org.apache.axis2.databinding.ADBException("reserve9 cannot be null!!");
				}
			}
			if (localReserve10Tracker) {
				elementList.add(new javax.xml.namespace.QName("", "reserve10"));

				if (localReserve10 != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localReserve10));
				} else {
					throw new org.apache.axis2.databinding.ADBException("reserve10 cannot be null!!");
				}
			}

			return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName, elementList.toArray(), attribList.toArray());

		}

		/**
		 * Factory class that keeps the parse method
		 */
		public static class Factory {

			/**
			 * static method to create the object
			 * Precondition: If this object is an element, the current or next start element starts this object and any intervening reader events are
			 * ignorable
			 * If this object is not an element, it is a complex type and the reader is at the event just after the outer start element
			 * Postcondition: If this object is an element, the reader is positioned at its end element
			 * If this object is a complex type, the reader is positioned at the end element of its outer element
			 */
			public static FMT_CRMS_SVR_S01001010021003_IN parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception {
				FMT_CRMS_SVR_S01001010021003_IN object = new FMT_CRMS_SVR_S01001010021003_IN();

				int event;
				java.lang.String nillableValue = null;
				java.lang.String prefix = "";
				java.lang.String namespaceuri = "";
				try {

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "type") != null) {
						java.lang.String fullTypeName = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "type");
						if (fullTypeName != null) {
							java.lang.String nsPrefix = null;
							if (fullTypeName.indexOf(":") > -1) {
								nsPrefix = fullTypeName.substring(0, fullTypeName.indexOf(":"));
							}
							nsPrefix = nsPrefix == null ? "" : nsPrefix;

							java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":") + 1);

							if (!"FMT_CRMS_SVR_S01001010021003_IN".equals(type)) {
								// find namespace for the prefix
								java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
								return (FMT_CRMS_SVR_S01001010021003_IN) ExtensionMapper.getTypeObject(nsUri, type, reader);
							}

						}

					}

					// Note all attributes that were handled. Used to differ normal attributes
					// from anyAttributes.
					java.util.Vector handledAttributes = new java.util.Vector();

					reader.next();

					java.util.ArrayList list52 = new java.util.ArrayList();

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "busiLaunBrch").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setBusiLaunBrch(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "accoutBrch").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setAccoutBrch(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "accountOperBrch").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setAccountOperBrch(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "contractNo").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setContractNo(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "agentFlag").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setAgentFlag(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "discType").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setDiscType(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "discDt").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setDiscDt(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "billClass").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setBillClass(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "billType").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setBillType(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "rate").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setRate(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "rateType").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setRateType(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "payType").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setPayType(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "isPayCustInnerBank").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setIsPayCustInnerBank(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "isThirdCustInnerBank").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setIsThirdCustInnerBank(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "payCustName").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setPayCustName(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "payBankNo").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setPayBankNo(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "payCustNo").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setPayCustNo(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "payAcctNo").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setPayAcctNo(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "thirdCustName").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setThirdCustName(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "thirdBankNo").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setThirdBankNo(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "thirdCustNo").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setThirdCustNo(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "thirdAcctNo").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setThirdAcctNo(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "custNo").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setCustNo(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "custName").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setCustName(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "custAcctNo").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setCustAcctNo(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "buyPayRate").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setBuyPayRate(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "thirdPayRate").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setThirdPayRate(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "magrNo").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setMagrNo(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "magrName").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setMagrName(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "magrBranchNo").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setMagrBranchNo(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "redeemRate").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setRedeemRate(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "ifTc").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setIfTc(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "assuranceRatio").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setAssuranceRatio(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "assuranceAccountNo").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setAssuranceAccountNo(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "guaranteeMode").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setGuaranteeMode(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "branchNo").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setBranchNo(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "userNo").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setUserNo(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "consumerSeqNo").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setConsumerSeqNo(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "bussSeqNo").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setBussSeqNo(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "transDate").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setTransDate(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "wsId").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setWsId(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "transFlag").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setTransFlag(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "totalAmt").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setTotalAmt(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "isRedeem").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setIsRedeem(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "discountRanOpenDate").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setDiscountRanOpenDate(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "redeemEndDt").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setRedeemEndDt(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "redeemRateType").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setRedeemRateType(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "isInstore").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setIsInstore(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "isDelayInstore").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setIsDelayInstore(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "onlineMark").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setOnlineMark(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "remark").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setRemark(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "array").equals(reader.getName())) {

						// Process the array and step past its final element's end.
						list52.add(FMT_CRMS_SVR_S01001010021003_IN_SUB.Factory.parse(reader));

						// loop until we find a start element that is not part of this array
						boolean loopDone52 = false;
						while (!loopDone52) {
							// We should be at the end element, but make sure
							while (!reader.isEndElement())
								reader.next();
							// Step out of this element
							reader.next();
							// Step to next element event.
							while (!reader.isStartElement() && !reader.isEndElement())
								reader.next();
							if (reader.isEndElement()) {
								// two continuous end elements means we are exiting the xml structure
								loopDone52 = true;
							} else {
								if (new javax.xml.namespace.QName("", "array").equals(reader.getName())) {
									list52.add(FMT_CRMS_SVR_S01001010021003_IN_SUB.Factory.parse(reader));

								} else {
									loopDone52 = true;
								}
							}
						}
						// call the converter utility to convert and set the array

						object.setArray((FMT_CRMS_SVR_S01001010021003_IN_SUB[]) org.apache.axis2.databinding.utils.ConverterUtil.convertToArray(FMT_CRMS_SVR_S01001010021003_IN_SUB.class, list52));

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "reserve1").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setReserve1(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "reserve2").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setReserve2(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "reserve3").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setReserve3(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "reserve4").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setReserve4(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "reserve5").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setReserve5(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "reserve6").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setReserve6(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "reserve7").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setReserve7(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "reserve8").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setReserve8(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "reserve9").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setReserve9(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "reserve10").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setReserve10(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement())
						// A start element we are not expecting indicates a trailing invalid property
						throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());

				} catch (javax.xml.stream.XMLStreamException e) {
					throw new java.lang.Exception(e);
				}

				return object;
			}

		}// end of factory class

	}

	public static class S01001010021003Response implements org.apache.axis2.databinding.ADBBean {

		public static final javax.xml.namespace.QName MY_QNAME = new javax.xml.namespace.QName("http://www.adtec.com.cn", "S01001010021003Response", "ns1");

		private static java.lang.String generatePrefix(java.lang.String namespace) {
			if (namespace.equals("http://www.adtec.com.cn")) {
				return "ns1";
			}
			return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
		}

		/**
		 * field for ResTranHeader
		 */

		protected FMT_SOAP_UTF8_ResTranHeader localResTranHeader;

		/**
		 * Auto generated getter method
		 * @return FMT_SOAP_UTF8_ResTranHeader
		 */
		public FMT_SOAP_UTF8_ResTranHeader getResTranHeader() {
			return localResTranHeader;
		}

		/**
		 * Auto generated setter method
		 * @param param ResTranHeader
		 */
		public void setResTranHeader(FMT_SOAP_UTF8_ResTranHeader param) {

			this.localResTranHeader = param;

		}

		/**
		 * field for ResponseBody
		 */

		protected FMT_CRMS_SVR_S01001010021003_OUT localResponseBody;

		/**
		 * Auto generated getter method
		 * @return FMT_CRMS_SVR_S01001010021003_OUT
		 */
		public FMT_CRMS_SVR_S01001010021003_OUT getResponseBody() {
			return localResponseBody;
		}

		/**
		 * Auto generated setter method
		 * @param param ResponseBody
		 */
		public void setResponseBody(FMT_CRMS_SVR_S01001010021003_OUT param) {

			this.localResponseBody = param;

		}

		/**
		 * field for ResponseHeader
		 */

		protected FMT_SOAP_UTF8_ResponseHeader localResponseHeader;

		/**
		 * Auto generated getter method
		 * @return FMT_SOAP_UTF8_ResponseHeader
		 */
		public FMT_SOAP_UTF8_ResponseHeader getResponseHeader() {
			return localResponseHeader;
		}

		/**
		 * Auto generated setter method
		 * @param param ResponseHeader
		 */
		public void setResponseHeader(FMT_SOAP_UTF8_ResponseHeader param) {

			this.localResponseHeader = param;

		}

		/**
		 * isReaderMTOMAware
		 * @return true if the reader supports MTOM
		 */
		public static boolean isReaderMTOMAware(javax.xml.stream.XMLStreamReader reader) {
			boolean isReaderMTOMAware = false;

			try {
				isReaderMTOMAware = java.lang.Boolean.TRUE.equals(reader.getProperty(org.apache.axiom.om.OMConstants.IS_DATA_HANDLERS_AWARE));
			} catch (java.lang.IllegalArgumentException e) {
				isReaderMTOMAware = false;
			}
			return isReaderMTOMAware;
		}

		/**
		 * @param parentQName
		 * @param factory
		 * @return org.apache.axiom.om.OMElement
		 */
		public org.apache.axiom.om.OMElement getOMElement(final javax.xml.namespace.QName parentQName, final org.apache.axiom.om.OMFactory factory) throws org.apache.axis2.databinding.ADBException {

			org.apache.axiom.om.OMDataSource dataSource = new org.apache.axis2.databinding.ADBDataSource(this, MY_QNAME) {

				public void serialize(org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
					S01001010021003Response.this.serialize(MY_QNAME, factory, xmlWriter);
				}
			};
			return new org.apache.axiom.om.impl.llom.OMSourcedElementImpl(MY_QNAME, factory, dataSource);

		}

		public void serialize(final javax.xml.namespace.QName parentQName, final org.apache.axiom.om.OMFactory factory, org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {

			java.lang.String prefix = null;
			java.lang.String namespace = null;

			prefix = parentQName.getPrefix();
			namespace = parentQName.getNamespaceURI();

			if (namespace != null) {
				java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
				if (writerPrefix != null) {
					xmlWriter.writeStartElement(namespace, parentQName.getLocalPart());
				} else {
					if (prefix == null) {
						prefix = generatePrefix(namespace);
					}

					xmlWriter.writeStartElement(prefix, parentQName.getLocalPart(), namespace);
					xmlWriter.writeNamespace(prefix, namespace);
					xmlWriter.setPrefix(prefix, namespace);
				}
			} else {
				xmlWriter.writeStartElement(parentQName.getLocalPart());
			}

			if (localResTranHeader == null) {
				throw new org.apache.axis2.databinding.ADBException("ResTranHeader cannot be null!!");
			}
			localResTranHeader.serialize(new javax.xml.namespace.QName("", "ResTranHeader"), factory, xmlWriter);

			if (localResponseBody == null) {
				throw new org.apache.axis2.databinding.ADBException("ResponseBody cannot be null!!");
			}
			localResponseBody.serialize(new javax.xml.namespace.QName("", "ResponseBody"), factory, xmlWriter);

			if (localResponseHeader == null) {
				throw new org.apache.axis2.databinding.ADBException("ResponseHeader cannot be null!!");
			}
			localResponseHeader.serialize(new javax.xml.namespace.QName("", "ResponseHeader"), factory, xmlWriter);

			xmlWriter.writeEndElement();

		}

		/**
		 * Util method to write an attribute with the ns prefix
		 */
		private void writeAttribute(java.lang.String prefix, java.lang.String namespace, java.lang.String attName, java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			if (xmlWriter.getPrefix(namespace) == null) {
				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);

			}

			xmlWriter.writeAttribute(namespace, attName, attValue);

		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeAttribute(java.lang.String namespace, java.lang.String attName, java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			if (namespace.equals("")) {
				xmlWriter.writeAttribute(attName, attValue);
			} else {
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attValue);
			}
		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeQNameAttribute(java.lang.String namespace, java.lang.String attName, javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {

			java.lang.String attributeNamespace = qname.getNamespaceURI();
			java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
			if (attributePrefix == null) {
				attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
			}
			java.lang.String attributeValue;
			if (attributePrefix.trim().length() > 0) {
				attributeValue = attributePrefix + ":" + qname.getLocalPart();
			} else {
				attributeValue = qname.getLocalPart();
			}

			if (namespace.equals("")) {
				xmlWriter.writeAttribute(attName, attributeValue);
			} else {
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attributeValue);
			}
		}

		/**
		 * method to handle Qnames
		 */

		private void writeQName(javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
			java.lang.String namespaceURI = qname.getNamespaceURI();
			if (namespaceURI != null) {
				java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
				if (prefix == null) {
					prefix = generatePrefix(namespaceURI);
					xmlWriter.writeNamespace(prefix, namespaceURI);
					xmlWriter.setPrefix(prefix, namespaceURI);
				}

				if (prefix.trim().length() > 0) {
					xmlWriter.writeCharacters(prefix + ":" + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
				} else {
					// i.e this is the default namespace
					xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
				}

			} else {
				xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
			}
		}

		private void writeQNames(javax.xml.namespace.QName[] qnames, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

			if (qnames != null) {
				// we have to store this data until last moment since it is not possible to write any
				// namespace data after writing the charactor data
				java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
				java.lang.String namespaceURI = null;
				java.lang.String prefix = null;

				for (int i = 0; i < qnames.length; i++) {
					if (i > 0) {
						stringToWrite.append(" ");
					}
					namespaceURI = qnames[i].getNamespaceURI();
					if (namespaceURI != null) {
						prefix = xmlWriter.getPrefix(namespaceURI);
						if ((prefix == null) || (prefix.length() == 0)) {
							prefix = generatePrefix(namespaceURI);
							xmlWriter.writeNamespace(prefix, namespaceURI);
							xmlWriter.setPrefix(prefix, namespaceURI);
						}

						if (prefix.trim().length() > 0) {
							stringToWrite.append(prefix).append(":").append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
						} else {
							stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
						}
					} else {
						stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
					}
				}
				xmlWriter.writeCharacters(stringToWrite.toString());
			}

		}

		/**
		 * Register a namespace prefix
		 */
		private java.lang.String registerPrefix(javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace) throws javax.xml.stream.XMLStreamException {
			java.lang.String prefix = xmlWriter.getPrefix(namespace);

			if (prefix == null) {
				prefix = generatePrefix(namespace);

				while (xmlWriter.getNamespaceContext().getNamespaceURI(prefix) != null) {
					prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
				}

				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);
			}

			return prefix;
		}

		/**
		 * databinding method to get an XML representation of this object
		 */
		public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName) throws org.apache.axis2.databinding.ADBException {

			java.util.ArrayList elementList = new java.util.ArrayList();
			java.util.ArrayList attribList = new java.util.ArrayList();

			elementList.add(new javax.xml.namespace.QName("", "ResTranHeader"));

			if (localResTranHeader == null) {
				throw new org.apache.axis2.databinding.ADBException("ResTranHeader cannot be null!!");
			}
			elementList.add(localResTranHeader);

			elementList.add(new javax.xml.namespace.QName("", "ResponseBody"));

			if (localResponseBody == null) {
				throw new org.apache.axis2.databinding.ADBException("ResponseBody cannot be null!!");
			}
			elementList.add(localResponseBody);

			elementList.add(new javax.xml.namespace.QName("", "ResponseHeader"));

			if (localResponseHeader == null) {
				throw new org.apache.axis2.databinding.ADBException("ResponseHeader cannot be null!!");
			}
			elementList.add(localResponseHeader);

			return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName, elementList.toArray(), attribList.toArray());

		}

		/**
		 * Factory class that keeps the parse method
		 */
		public static class Factory {

			/**
			 * static method to create the object
			 * Precondition: If this object is an element, the current or next start element starts this object and any intervening reader events are
			 * ignorable
			 * If this object is not an element, it is a complex type and the reader is at the event just after the outer start element
			 * Postcondition: If this object is an element, the reader is positioned at its end element
			 * If this object is a complex type, the reader is positioned at the end element of its outer element
			 */
			public static S01001010021003Response parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception {
				S01001010021003Response object = new S01001010021003Response();

				int event;
				java.lang.String nillableValue = null;
				java.lang.String prefix = "";
				java.lang.String namespaceuri = "";
				try {

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "type") != null) {
						java.lang.String fullTypeName = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "type");
						if (fullTypeName != null) {
							java.lang.String nsPrefix = null;
							if (fullTypeName.indexOf(":") > -1) {
								nsPrefix = fullTypeName.substring(0, fullTypeName.indexOf(":"));
							}
							nsPrefix = nsPrefix == null ? "" : nsPrefix;

							java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":") + 1);

							if (!"S01001010021003Response".equals(type)) {
								// find namespace for the prefix
								java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
								return (S01001010021003Response) ExtensionMapper.getTypeObject(nsUri, type, reader);
							}

						}

					}

					// Note all attributes that were handled. Used to differ normal attributes
					// from anyAttributes.
					java.util.Vector handledAttributes = new java.util.Vector();

					reader.next();

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "ResponseHeader").equals(reader.getName())) {

						object.setResponseHeader(FMT_SOAP_UTF8_ResponseHeader.Factory.parse(reader));

						reader.next();

					} // End of if for expected property start element

					else {
						// A start element we are not expecting indicates an invalid parameter was passed
						throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());
					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "ResTranHeader").equals(reader.getName())) {

						object.setResTranHeader(FMT_SOAP_UTF8_ResTranHeader.Factory.parse(reader));

						reader.next();

					} // End of if for expected property start element

					else {
						// A start element we are not expecting indicates an invalid parameter was passed
						throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());
					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "ResponseBody").equals(reader.getName())) {

						object.setResponseBody(FMT_CRMS_SVR_S01001010021003_OUT.Factory.parse(reader));

						reader.next();

					} // End of if for expected property start element

					else {
						// A start element we are not expecting indicates an invalid parameter was passed
						throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());
					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement())
						// A start element we are not expecting indicates a trailing invalid property
						throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());

				} catch (javax.xml.stream.XMLStreamException e) {
					throw new java.lang.Exception(e);
				}

				return object;
			}

		}// end of factory class

	}

	private org.apache.axiom.om.OMElement toOM(com.primeton.ecds.S01001010021003ServiceStub.S01001010021003 param, boolean optimizeContent) throws org.apache.axis2.AxisFault {

		try {
			return param.getOMElement(com.primeton.ecds.S01001010021003ServiceStub.S01001010021003.MY_QNAME, org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.om.OMElement toOM(com.primeton.ecds.S01001010021003ServiceStub.S01001010021003Response param, boolean optimizeContent) throws org.apache.axis2.AxisFault {

		try {
			return param.getOMElement(com.primeton.ecds.S01001010021003ServiceStub.S01001010021003Response.MY_QNAME, org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, com.primeton.ecds.S01001010021003ServiceStub.S01001010021003 param, boolean optimizeContent)
			throws org.apache.axis2.AxisFault {

		try {

			org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
			emptyEnvelope.getBody().addChild(param.getOMElement(com.primeton.ecds.S01001010021003ServiceStub.S01001010021003.MY_QNAME, factory));
			return emptyEnvelope;
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	/* methods to provide back word compatibility */

	/**
	 * get the default envelope
	 */
	private org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory) {
		return factory.getDefaultEnvelope();
	}

	private java.lang.Object fromOM(org.apache.axiom.om.OMElement param, java.lang.Class type, java.util.Map extraNamespaces) throws org.apache.axis2.AxisFault {

		try {

			if (com.primeton.ecds.S01001010021003ServiceStub.S01001010021003.class.equals(type)) {

				return com.primeton.ecds.S01001010021003ServiceStub.S01001010021003.Factory.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (com.primeton.ecds.S01001010021003ServiceStub.S01001010021003Response.class.equals(type)) {

				return com.primeton.ecds.S01001010021003ServiceStub.S01001010021003Response.Factory.parse(param.getXMLStreamReaderWithoutCaching());

			}

		} catch (java.lang.Exception e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}
		return null;
	}

}
