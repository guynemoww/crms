
/**
 * S03601010ZX0001ServiceStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.3  Built on : Aug 10, 2007 (04:45:47 LKT)
 */
        package com.primeton.p2p;

        

        /*
        *  S03601010ZX0001ServiceStub java implementation
        */

        
        public class S03601010ZX0001ServiceStub extends org.apache.axis2.client.Stub
        {
        protected org.apache.axis2.description.AxisOperation[] _operations;

        //hashmaps to keep the fault mapping
        private java.util.HashMap faultExceptionNameMap = new java.util.HashMap();
        private java.util.HashMap faultExceptionClassNameMap = new java.util.HashMap();
        private java.util.HashMap faultMessageMap = new java.util.HashMap();

    
    private void populateAxisService() throws org.apache.axis2.AxisFault {

     //creating the Service with a unique name
     _service = new org.apache.axis2.description.AxisService("S03601010ZX0001Service" + this.hashCode());

        //creating the operations
        org.apache.axis2.description.AxisOperation __operation;

        _operations = new org.apache.axis2.description.AxisOperation[1];
        
                   __operation = new org.apache.axis2.description.OutInAxisOperation();
                

            __operation.setName(new javax.xml.namespace.QName("http://www.adtec.com.cn", "S03601010ZX0001"));
	    _service.addOperation(__operation);
	    

	    
	    
            _operations[0]=__operation;
            
        
        }

    //populates the faults
    private void populateFaults(){
         


    }

    /**
      *Constructor that takes in a configContext
      */

    public S03601010ZX0001ServiceStub(org.apache.axis2.context.ConfigurationContext configurationContext,
       java.lang.String targetEndpoint)
       throws org.apache.axis2.AxisFault {
         this(configurationContext,targetEndpoint,false);
   }


   /**
     * Constructor that takes in a configContext  and useseperate listner
     */
   public S03601010ZX0001ServiceStub(org.apache.axis2.context.ConfigurationContext configurationContext,
        java.lang.String targetEndpoint, boolean useSeparateListener)
        throws org.apache.axis2.AxisFault {
         //To populate AxisService
         populateAxisService();
         populateFaults();

        _serviceClient = new org.apache.axis2.client.ServiceClient(configurationContext,_service);
        
	
        configurationContext = _serviceClient.getServiceContext().getConfigurationContext();

        _serviceClient.getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(
                targetEndpoint));
        _serviceClient.getOptions().setUseSeparateListener(useSeparateListener);
        
    
    }

    /**
     * Default Constructor
     */
    public S03601010ZX0001ServiceStub(org.apache.axis2.context.ConfigurationContext configurationContext) throws org.apache.axis2.AxisFault {
        
                    this(configurationContext,"http://172.16.221.83:12103/WebService/CRMS_SVR/S03601010ZX0001" );
                
    }

    /**
     * Default Constructor
     */
    public S03601010ZX0001ServiceStub() throws org.apache.axis2.AxisFault {
        
                    this("http://172.16.221.83:12103/WebService/CRMS_SVR/S03601010ZX0001" );
                
    }

    /**
     * Constructor taking the target endpoint
     */
    public S03601010ZX0001ServiceStub(java.lang.String targetEndpoint) throws org.apache.axis2.AxisFault {
        this(null,targetEndpoint);
    }



        
                    /**
                     * Auto generated method signature
                     * @see com.primeton.p2p.S03601010ZX0001Service#S03601010ZX0001
                     * @param s03601010ZX00010
                    
                     */

                    
                            public  com.primeton.p2p.S03601010ZX0001ServiceStub.S03601010ZX0001Response S03601010ZX0001(

                            com.primeton.p2p.S03601010ZX0001ServiceStub.S03601010ZX0001 s03601010ZX00010)
                        

                    throws java.rmi.RemoteException
                    
                    {

              try{
               org.apache.axis2.client.OperationClient _operationClient = _serviceClient.createClient(_operations[0].getName());
              _operationClient.getOptions().setAction("http://172.16.221.83:12103/WebService/CRMS_SVR/S03601010ZX0001");
              _operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

              
              
                  addPropertyToOperationClient(_operationClient,org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,"&");
              

              // create a message context
              org.apache.axis2.context.MessageContext _messageContext = new org.apache.axis2.context.MessageContext();

              

              // create SOAP envelope with that payload
              org.apache.axiom.soap.SOAPEnvelope env = null;
                    
                                                    
                                                    env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()),
                                                    s03601010ZX00010,
                                                    optimizeContent(new javax.xml.namespace.QName("http://www.adtec.com.cn",
                                                    "S03601010ZX0001")));
                                                
        //adding SOAP soap_headers
         _serviceClient.addHeadersToEnvelope(env);
        // set the message context with that soap envelope
        _messageContext.setEnvelope(env);

        // add the message contxt to the operation client
        _operationClient.addMessageContext(_messageContext);

        //execute the operation client
        _operationClient.execute(true);

         
               org.apache.axis2.context.MessageContext _returnMessageContext = _operationClient.getMessageContext(
                                           org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
                org.apache.axiom.soap.SOAPEnvelope _returnEnv = _returnMessageContext.getEnvelope();
                
                
                                java.lang.Object object = fromOM(
                                             _returnEnv.getBody().getFirstElement() ,
                                             com.primeton.p2p.S03601010ZX0001ServiceStub.S03601010ZX0001Response.class,
                                              getEnvelopeNamespaces(_returnEnv));
                                _messageContext.getTransportOut().getSender().cleanup(_messageContext);
                               
                                        return (com.primeton.p2p.S03601010ZX0001ServiceStub.S03601010ZX0001Response)object;
                                   
         }catch(org.apache.axis2.AxisFault f){

            org.apache.axiom.om.OMElement faultElt = f.getDetail();
            if (faultElt!=null){
                if (faultExceptionNameMap.containsKey(faultElt.getQName())){
                    //make the fault by reflection
                    try{
                        java.lang.String exceptionClassName = (java.lang.String)faultExceptionClassNameMap.get(faultElt.getQName());
                        java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
                        java.lang.Exception ex=
                                (java.lang.Exception) exceptionClass.newInstance();
                        //message class
                        java.lang.String messageClassName = (java.lang.String)faultMessageMap.get(faultElt.getQName());
                        java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
                        java.lang.Object messageObject = fromOM(faultElt,messageClass,null);
                        java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
                                   new java.lang.Class[]{messageClass});
                        m.invoke(ex,new java.lang.Object[]{messageObject});
                        

                        throw new java.rmi.RemoteException(ex.getMessage(), ex);
                    }catch(java.lang.ClassCastException e){
                       // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    } catch (java.lang.ClassNotFoundException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    }catch (java.lang.NoSuchMethodException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    } catch (java.lang.reflect.InvocationTargetException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    }  catch (java.lang.IllegalAccessException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    }   catch (java.lang.InstantiationException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    }
                }else{
                    throw f;
                }
            }else{
                throw f;
            }
        }
        }
            
                /**
                * Auto generated method signature for Asynchronous Invocations
                * @see com.primeton.p2p.S03601010ZX0001Service#startS03601010ZX0001
                    * @param s03601010ZX00010
                
                */
                public  void startS03601010ZX0001(

                 com.primeton.p2p.S03601010ZX0001ServiceStub.S03601010ZX0001 s03601010ZX00010,

                  final com.primeton.p2p.S03601010ZX0001ServiceCallbackHandler callback)

                throws java.rmi.RemoteException{

              org.apache.axis2.client.OperationClient _operationClient = _serviceClient.createClient(_operations[0].getName());
             _operationClient.getOptions().setAction("http://172.16.221.83:12103/WebService/CRMS_SVR/S03601010ZX0001");
             _operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

              
              
                  addPropertyToOperationClient(_operationClient,org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,"&");
              


              // create SOAP envelope with that payload
              org.apache.axiom.soap.SOAPEnvelope env=null;
              org.apache.axis2.context.MessageContext _messageContext = new org.apache.axis2.context.MessageContext();

                    
                                    //Style is Doc.
                                    
                                                    
                                                    env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()),
                                                    s03601010ZX00010,
                                                    optimizeContent(new javax.xml.namespace.QName("http://www.adtec.com.cn",
                                                    "S03601010ZX0001")));
                                                
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
                                
                                        java.lang.Object object = fromOM(resultEnv.getBody().getFirstElement(),
                                                                         com.primeton.p2p.S03601010ZX0001ServiceStub.S03601010ZX0001Response.class,
                                                                         getEnvelopeNamespaces(resultEnv));
                                        callback.receiveResultS03601010ZX0001(
                                        (com.primeton.p2p.S03601010ZX0001ServiceStub.S03601010ZX0001Response)object);
                                        
                            } catch (org.apache.axis2.AxisFault e) {
                                callback.receiveErrorS03601010ZX0001(e);
                            }
                            }

                            public void onError(java.lang.Exception error) {
								if (error instanceof org.apache.axis2.AxisFault) {
									org.apache.axis2.AxisFault f = (org.apache.axis2.AxisFault) error;
									org.apache.axiom.om.OMElement faultElt = f.getDetail();
									if (faultElt!=null){
										if (faultExceptionNameMap.containsKey(faultElt.getQName())){
											//make the fault by reflection
											try{
													java.lang.String exceptionClassName = (java.lang.String)faultExceptionClassNameMap.get(faultElt.getQName());
													java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
													java.lang.Exception ex=
														(java.lang.Exception) exceptionClass.newInstance();
													//message class
													java.lang.String messageClassName = (java.lang.String)faultMessageMap.get(faultElt.getQName());
														java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
													java.lang.Object messageObject = fromOM(faultElt,messageClass,null);
													java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
															new java.lang.Class[]{messageClass});
													m.invoke(ex,new java.lang.Object[]{messageObject});
													

										            callback.receiveErrorS03601010ZX0001(new java.rmi.RemoteException(ex.getMessage(), ex));
                                            } catch(java.lang.ClassCastException e){
                                                // we cannot intantiate the class - throw the original Axis fault
                                                callback.receiveErrorS03601010ZX0001(f);
                                            } catch (java.lang.ClassNotFoundException e) {
                                                // we cannot intantiate the class - throw the original Axis fault
                                                callback.receiveErrorS03601010ZX0001(f);
                                            } catch (java.lang.NoSuchMethodException e) {
                                                // we cannot intantiate the class - throw the original Axis fault
                                                callback.receiveErrorS03601010ZX0001(f);
                                            } catch (java.lang.reflect.InvocationTargetException e) {
                                                // we cannot intantiate the class - throw the original Axis fault
                                                callback.receiveErrorS03601010ZX0001(f);
                                            } catch (java.lang.IllegalAccessException e) {
                                                // we cannot intantiate the class - throw the original Axis fault
                                                callback.receiveErrorS03601010ZX0001(f);
                                            } catch (java.lang.InstantiationException e) {
                                                // we cannot intantiate the class - throw the original Axis fault
                                                callback.receiveErrorS03601010ZX0001(f);
                                            } catch (org.apache.axis2.AxisFault e) {
                                                // we cannot intantiate the class - throw the original Axis fault
                                                callback.receiveErrorS03601010ZX0001(f);
                                            }
									    } else {
										    callback.receiveErrorS03601010ZX0001(f);
									    }
									} else {
									    callback.receiveErrorS03601010ZX0001(f);
									}
								} else {
								    callback.receiveErrorS03601010ZX0001(error);
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
        if ( _operations[0].getMessageReceiver()==null &&  _operationClient.getOptions().isUseSeparateListener()) {
           _callbackReceiver = new org.apache.axis2.util.CallbackReceiver();
          _operations[0].setMessageReceiver(
                    _callbackReceiver);
        }

           //execute the operation client
           _operationClient.execute(false);

                    }
                


       /**
        *  A utility method that copies the namepaces from the SOAPEnvelope
        */
       private java.util.Map getEnvelopeNamespaces(org.apache.axiom.soap.SOAPEnvelope env){
        java.util.Map returnMap = new java.util.HashMap();
        java.util.Iterator namespaceIterator = env.getAllDeclaredNamespaces();
        while (namespaceIterator.hasNext()) {
            org.apache.axiom.om.OMNamespace ns = (org.apache.axiom.om.OMNamespace) namespaceIterator.next();
            returnMap.put(ns.getPrefix(),ns.getNamespaceURI());
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
     //http://172.16.221.83:12103/WebService/CRMS_SVR/S03601010ZX0001

        public static class FMT_SOAP_UTF8_ResponseHeader
        implements org.apache.axis2.databinding.ADBBean{
        /* This type was generated from the piece of schema that had
                name = FMT_SOAP_UTF8_ResponseHeader
                Namespace URI = http://www.adtec.com.cn
                Namespace Prefix = ns1
                */
            

        private static java.lang.String generatePrefix(java.lang.String namespace) {
            if(namespace.equals("http://www.adtec.com.cn")){
                return "ns1";
            }
            return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
        }

        

                        /**
                        * field for VersionNo
                        */

                        
                            protected java.lang.String localVersionNo ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localVersionNoTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getVersionNo(){
                               return localVersionNo;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param VersionNo
                               */
                               public void setVersionNo(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localVersionNoTracker = true;
                                       } else {
                                          localVersionNoTracker = false;
                                              
                                       }
                                   
                                            this.localVersionNo=param;
                                    

                               }
                            

                        /**
                        * field for ReqSysCode
                        */

                        
                            protected java.lang.String localReqSysCode ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localReqSysCodeTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getReqSysCode(){
                               return localReqSysCode;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param ReqSysCode
                               */
                               public void setReqSysCode(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localReqSysCodeTracker = true;
                                       } else {
                                          localReqSysCodeTracker = false;
                                              
                                       }
                                   
                                            this.localReqSysCode=param;
                                    

                               }
                            

                        /**
                        * field for ReqSecCode
                        */

                        
                            protected java.lang.String localReqSecCode ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localReqSecCodeTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getReqSecCode(){
                               return localReqSecCode;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param ReqSecCode
                               */
                               public void setReqSecCode(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localReqSecCodeTracker = true;
                                       } else {
                                          localReqSecCodeTracker = false;
                                              
                                       }
                                   
                                            this.localReqSecCode=param;
                                    

                               }
                            

                        /**
                        * field for TxType
                        */

                        
                            protected java.lang.String localTxType ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localTxTypeTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getTxType(){
                               return localTxType;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param TxType
                               */
                               public void setTxType(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localTxTypeTracker = true;
                                       } else {
                                          localTxTypeTracker = false;
                                              
                                       }
                                   
                                            this.localTxType=param;
                                    

                               }
                            

                        /**
                        * field for TxMode
                        */

                        
                            protected java.lang.String localTxMode ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localTxModeTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getTxMode(){
                               return localTxMode;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param TxMode
                               */
                               public void setTxMode(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localTxModeTracker = true;
                                       } else {
                                          localTxModeTracker = false;
                                              
                                       }
                                   
                                            this.localTxMode=param;
                                    

                               }
                            

                        /**
                        * field for TxCode
                        */

                        
                            protected java.lang.String localTxCode ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localTxCodeTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getTxCode(){
                               return localTxCode;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param TxCode
                               */
                               public void setTxCode(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localTxCodeTracker = true;
                                       } else {
                                          localTxCodeTracker = false;
                                              
                                       }
                                   
                                            this.localTxCode=param;
                                    

                               }
                            

                        /**
                        * field for ReqDate
                        */

                        
                            protected java.lang.String localReqDate ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localReqDateTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getReqDate(){
                               return localReqDate;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param ReqDate
                               */
                               public void setReqDate(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localReqDateTracker = true;
                                       } else {
                                          localReqDateTracker = false;
                                              
                                       }
                                   
                                            this.localReqDate=param;
                                    

                               }
                            

                        /**
                        * field for ReqTime
                        */

                        
                            protected java.lang.String localReqTime ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localReqTimeTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getReqTime(){
                               return localReqTime;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param ReqTime
                               */
                               public void setReqTime(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localReqTimeTracker = true;
                                       } else {
                                          localReqTimeTracker = false;
                                              
                                       }
                                   
                                            this.localReqTime=param;
                                    

                               }
                            

                        /**
                        * field for ReqSeqNo
                        */

                        
                            protected java.lang.String localReqSeqNo ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localReqSeqNoTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getReqSeqNo(){
                               return localReqSeqNo;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param ReqSeqNo
                               */
                               public void setReqSeqNo(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localReqSeqNoTracker = true;
                                       } else {
                                          localReqSeqNoTracker = false;
                                              
                                       }
                                   
                                            this.localReqSeqNo=param;
                                    

                               }
                            

                        /**
                        * field for SvrDate
                        */

                        
                            protected java.lang.String localSvrDate ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localSvrDateTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getSvrDate(){
                               return localSvrDate;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param SvrDate
                               */
                               public void setSvrDate(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localSvrDateTracker = true;
                                       } else {
                                          localSvrDateTracker = false;
                                              
                                       }
                                   
                                            this.localSvrDate=param;
                                    

                               }
                            

                        /**
                        * field for SvrTime
                        */

                        
                            protected java.lang.String localSvrTime ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localSvrTimeTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getSvrTime(){
                               return localSvrTime;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param SvrTime
                               */
                               public void setSvrTime(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localSvrTimeTracker = true;
                                       } else {
                                          localSvrTimeTracker = false;
                                              
                                       }
                                   
                                            this.localSvrTime=param;
                                    

                               }
                            

                        /**
                        * field for SvrSeqNo
                        */

                        
                            protected java.lang.String localSvrSeqNo ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localSvrSeqNoTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getSvrSeqNo(){
                               return localSvrSeqNo;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param SvrSeqNo
                               */
                               public void setSvrSeqNo(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localSvrSeqNoTracker = true;
                                       } else {
                                          localSvrSeqNoTracker = false;
                                              
                                       }
                                   
                                            this.localSvrSeqNo=param;
                                    

                               }
                            

                        /**
                        * field for RecvFileName
                        */

                        
                            protected java.lang.String localRecvFileName ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localRecvFileNameTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getRecvFileName(){
                               return localRecvFileName;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param RecvFileName
                               */
                               public void setRecvFileName(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localRecvFileNameTracker = true;
                                       } else {
                                          localRecvFileNameTracker = false;
                                              
                                       }
                                   
                                            this.localRecvFileName=param;
                                    

                               }
                            

                        /**
                        * field for TotNum
                        */

                        
                            protected java.lang.String localTotNum ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localTotNumTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getTotNum(){
                               return localTotNum;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param TotNum
                               */
                               public void setTotNum(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localTotNumTracker = true;
                                       } else {
                                          localTotNumTracker = false;
                                              
                                       }
                                   
                                            this.localTotNum=param;
                                    

                               }
                            

                        /**
                        * field for CurrNum
                        */

                        
                            protected java.lang.String localCurrNum ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localCurrNumTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getCurrNum(){
                               return localCurrNum;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param CurrNum
                               */
                               public void setCurrNum(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localCurrNumTracker = true;
                                       } else {
                                          localCurrNumTracker = false;
                                              
                                       }
                                   
                                            this.localCurrNum=param;
                                    

                               }
                            

                        /**
                        * field for FileHMac
                        */

                        
                            protected java.lang.String localFileHMac ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localFileHMacTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getFileHMac(){
                               return localFileHMac;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param FileHMac
                               */
                               public void setFileHMac(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localFileHMacTracker = true;
                                       } else {
                                          localFileHMacTracker = false;
                                              
                                       }
                                   
                                            this.localFileHMac=param;
                                    

                               }
                            

                        /**
                        * field for HMac
                        */

                        
                            protected java.lang.String localHMac ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localHMacTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getHMac(){
                               return localHMac;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param HMac
                               */
                               public void setHMac(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localHMacTracker = true;
                                       } else {
                                          localHMacTracker = false;
                                              
                                       }
                                   
                                            this.localHMac=param;
                                    

                               }
                            

     /**
     * isReaderMTOMAware
     * @return true if the reader supports MTOM
     */
   public static boolean isReaderMTOMAware(javax.xml.stream.XMLStreamReader reader) {
        boolean isReaderMTOMAware = false;
        
        try{
          isReaderMTOMAware = java.lang.Boolean.TRUE.equals(reader.getProperty(org.apache.axiom.om.OMConstants.IS_DATA_HANDLERS_AWARE));
        }catch(java.lang.IllegalArgumentException e){
          isReaderMTOMAware = false;
        }
        return isReaderMTOMAware;
   }
     
     
        /**
        *
        * @param parentQName
        * @param factory
        * @return org.apache.axiom.om.OMElement
        */
       public org.apache.axiom.om.OMElement getOMElement (
               final javax.xml.namespace.QName parentQName,
               final org.apache.axiom.om.OMFactory factory) throws org.apache.axis2.databinding.ADBException{


        
               org.apache.axiom.om.OMDataSource dataSource =
                       new org.apache.axis2.databinding.ADBDataSource(this,parentQName){

                 public void serialize(org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
                       FMT_SOAP_UTF8_ResponseHeader.this.serialize(parentQName,factory,xmlWriter);
                 }
               };
               return new org.apache.axiom.om.impl.llom.OMSourcedElementImpl(
               parentQName,factory,dataSource);
            
       }



         public void serialize(final javax.xml.namespace.QName parentQName,
                               final org.apache.axiom.om.OMFactory factory,
                               org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException{
            
                


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
                 if (localVersionNoTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"VersionNo", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"VersionNo");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("VersionNo");
                                    }
                                

                                          if (localVersionNo==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("VersionNo cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localVersionNo);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localReqSysCodeTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"ReqSysCode", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"ReqSysCode");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("ReqSysCode");
                                    }
                                

                                          if (localReqSysCode==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("ReqSysCode cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localReqSysCode);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localReqSecCodeTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"ReqSecCode", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"ReqSecCode");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("ReqSecCode");
                                    }
                                

                                          if (localReqSecCode==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("ReqSecCode cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localReqSecCode);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localTxTypeTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"TxType", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"TxType");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("TxType");
                                    }
                                

                                          if (localTxType==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("TxType cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localTxType);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localTxModeTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"TxMode", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"TxMode");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("TxMode");
                                    }
                                

                                          if (localTxMode==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("TxMode cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localTxMode);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localTxCodeTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"TxCode", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"TxCode");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("TxCode");
                                    }
                                

                                          if (localTxCode==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("TxCode cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localTxCode);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localReqDateTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"ReqDate", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"ReqDate");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("ReqDate");
                                    }
                                

                                          if (localReqDate==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("ReqDate cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localReqDate);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localReqTimeTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"ReqTime", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"ReqTime");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("ReqTime");
                                    }
                                

                                          if (localReqTime==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("ReqTime cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localReqTime);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localReqSeqNoTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"ReqSeqNo", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"ReqSeqNo");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("ReqSeqNo");
                                    }
                                

                                          if (localReqSeqNo==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("ReqSeqNo cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localReqSeqNo);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localSvrDateTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"SvrDate", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"SvrDate");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("SvrDate");
                                    }
                                

                                          if (localSvrDate==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("SvrDate cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localSvrDate);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localSvrTimeTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"SvrTime", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"SvrTime");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("SvrTime");
                                    }
                                

                                          if (localSvrTime==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("SvrTime cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localSvrTime);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localSvrSeqNoTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"SvrSeqNo", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"SvrSeqNo");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("SvrSeqNo");
                                    }
                                

                                          if (localSvrSeqNo==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("SvrSeqNo cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localSvrSeqNo);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localRecvFileNameTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"RecvFileName", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"RecvFileName");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("RecvFileName");
                                    }
                                

                                          if (localRecvFileName==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("RecvFileName cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localRecvFileName);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localTotNumTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"TotNum", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"TotNum");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("TotNum");
                                    }
                                

                                          if (localTotNum==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("TotNum cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localTotNum);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localCurrNumTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"CurrNum", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"CurrNum");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("CurrNum");
                                    }
                                

                                          if (localCurrNum==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("CurrNum cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localCurrNum);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localFileHMacTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"FileHMac", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"FileHMac");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("FileHMac");
                                    }
                                

                                          if (localFileHMac==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("FileHMac cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localFileHMac);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localHMacTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"HMac", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"HMac");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("HMac");
                                    }
                                

                                          if (localHMac==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("HMac cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localHMac);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             }
                    xmlWriter.writeEndElement();
               

        }

         /**
          * Util method to write an attribute with the ns prefix
          */
          private void writeAttribute(java.lang.String prefix,java.lang.String namespace,java.lang.String attName,
                                      java.lang.String attValue,javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException{
              if (xmlWriter.getPrefix(namespace) == null) {
                       xmlWriter.writeNamespace(prefix, namespace);
                       xmlWriter.setPrefix(prefix, namespace);

              }

              xmlWriter.writeAttribute(namespace,attName,attValue);

         }

        /**
          * Util method to write an attribute without the ns prefix
          */
          private void writeAttribute(java.lang.String namespace,java.lang.String attName,
                                      java.lang.String attValue,javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException{
                if (namespace.equals(""))
              {
                  xmlWriter.writeAttribute(attName,attValue);
              }
              else
              {
                  registerPrefix(xmlWriter, namespace);
                  xmlWriter.writeAttribute(namespace,attName,attValue);
              }
          }


           /**
             * Util method to write an attribute without the ns prefix
             */
            private void writeQNameAttribute(java.lang.String namespace, java.lang.String attName,
                                             javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

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
         *  method to handle Qnames
         */

        private void writeQName(javax.xml.namespace.QName qname,
                                javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
            java.lang.String namespaceURI = qname.getNamespaceURI();
            if (namespaceURI != null) {
                java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
                if (prefix == null) {
                    prefix = generatePrefix(namespaceURI);
                    xmlWriter.writeNamespace(prefix, namespaceURI);
                    xmlWriter.setPrefix(prefix,namespaceURI);
                }

                if (prefix.trim().length() > 0){
                    xmlWriter.writeCharacters(prefix + ":" + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
                } else {
                    // i.e this is the default namespace
                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
                }

            } else {
                xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
            }
        }

        private void writeQNames(javax.xml.namespace.QName[] qnames,
                                 javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

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
                            xmlWriter.setPrefix(prefix,namespaceURI);
                        }

                        if (prefix.trim().length() > 0){
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
        *
        */
        public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName)
                    throws org.apache.axis2.databinding.ADBException{


        
                 java.util.ArrayList elementList = new java.util.ArrayList();
                 java.util.ArrayList attribList = new java.util.ArrayList();

                 if (localVersionNoTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "VersionNo"));
                                 
                                        if (localVersionNo != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localVersionNo));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("VersionNo cannot be null!!");
                                        }
                                    } if (localReqSysCodeTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "ReqSysCode"));
                                 
                                        if (localReqSysCode != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localReqSysCode));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("ReqSysCode cannot be null!!");
                                        }
                                    } if (localReqSecCodeTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "ReqSecCode"));
                                 
                                        if (localReqSecCode != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localReqSecCode));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("ReqSecCode cannot be null!!");
                                        }
                                    } if (localTxTypeTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "TxType"));
                                 
                                        if (localTxType != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localTxType));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("TxType cannot be null!!");
                                        }
                                    } if (localTxModeTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "TxMode"));
                                 
                                        if (localTxMode != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localTxMode));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("TxMode cannot be null!!");
                                        }
                                    } if (localTxCodeTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "TxCode"));
                                 
                                        if (localTxCode != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localTxCode));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("TxCode cannot be null!!");
                                        }
                                    } if (localReqDateTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "ReqDate"));
                                 
                                        if (localReqDate != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localReqDate));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("ReqDate cannot be null!!");
                                        }
                                    } if (localReqTimeTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "ReqTime"));
                                 
                                        if (localReqTime != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localReqTime));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("ReqTime cannot be null!!");
                                        }
                                    } if (localReqSeqNoTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "ReqSeqNo"));
                                 
                                        if (localReqSeqNo != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localReqSeqNo));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("ReqSeqNo cannot be null!!");
                                        }
                                    } if (localSvrDateTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "SvrDate"));
                                 
                                        if (localSvrDate != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localSvrDate));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("SvrDate cannot be null!!");
                                        }
                                    } if (localSvrTimeTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "SvrTime"));
                                 
                                        if (localSvrTime != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localSvrTime));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("SvrTime cannot be null!!");
                                        }
                                    } if (localSvrSeqNoTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "SvrSeqNo"));
                                 
                                        if (localSvrSeqNo != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localSvrSeqNo));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("SvrSeqNo cannot be null!!");
                                        }
                                    } if (localRecvFileNameTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "RecvFileName"));
                                 
                                        if (localRecvFileName != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localRecvFileName));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("RecvFileName cannot be null!!");
                                        }
                                    } if (localTotNumTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "TotNum"));
                                 
                                        if (localTotNum != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localTotNum));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("TotNum cannot be null!!");
                                        }
                                    } if (localCurrNumTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "CurrNum"));
                                 
                                        if (localCurrNum != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCurrNum));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("CurrNum cannot be null!!");
                                        }
                                    } if (localFileHMacTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "FileHMac"));
                                 
                                        if (localFileHMac != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localFileHMac));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("FileHMac cannot be null!!");
                                        }
                                    } if (localHMacTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "HMac"));
                                 
                                        if (localHMac != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHMac));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("HMac cannot be null!!");
                                        }
                                    }

                return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName, elementList.toArray(), attribList.toArray());
            
            

        }

  

     /**
      *  Factory class that keeps the parse method
      */
    public static class Factory{

        
        

        /**
        * static method to create the object
        * Precondition:  If this object is an element, the current or next start element starts this object and any intervening reader events are ignorable
        *                If this object is not an element, it is a complex type and the reader is at the event just after the outer start element
        * Postcondition: If this object is an element, the reader is positioned at its end element
        *                If this object is a complex type, the reader is positioned at the end element of its outer element
        */
        public static FMT_SOAP_UTF8_ResponseHeader parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            FMT_SOAP_UTF8_ResponseHeader object =
                new FMT_SOAP_UTF8_ResponseHeader();

            int event;
            java.lang.String nillableValue = null;
            java.lang.String prefix ="";
            java.lang.String namespaceuri ="";
            try {
                
                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                
                if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","type")!=null){
                  java.lang.String fullTypeName = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                        "type");
                  if (fullTypeName!=null){
                    java.lang.String nsPrefix = null;
                    if (fullTypeName.indexOf(":") > -1){
                        nsPrefix = fullTypeName.substring(0,fullTypeName.indexOf(":"));
                    }
                    nsPrefix = nsPrefix==null?"":nsPrefix;

                    java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":")+1);
                    
                            if (!"FMT_SOAP_UTF8_ResponseHeader".equals(type)){
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                                return (FMT_SOAP_UTF8_ResponseHeader)ExtensionMapper.getTypeObject(
                                     nsUri,type,reader);
                              }
                        

                  }

                }
                

                
                // Note all attributes that were handled. Used to differ normal attributes
                // from anyAttributes.
                java.util.Vector handledAttributes = new java.util.Vector();
                

                 
                    
                    reader.next();
                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","VersionNo").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setVersionNo(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","ReqSysCode").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setReqSysCode(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","ReqSecCode").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setReqSecCode(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","TxType").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setTxType(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","TxMode").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setTxMode(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","TxCode").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setTxCode(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","ReqDate").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setReqDate(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","ReqTime").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setReqTime(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","ReqSeqNo").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setReqSeqNo(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","SvrDate").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setSvrDate(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","SvrTime").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setSvrTime(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","SvrSeqNo").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setSvrSeqNo(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","RecvFileName").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setRecvFileName(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","TotNum").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setTotNum(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","CurrNum").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setCurrNum(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","FileHMac").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setFileHMac(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","HMac").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setHMac(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
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

        }//end of factory class

        

        }
           
          

        public static class FMT_CRMS_SVR_S03601010ZX0001_IN
        implements org.apache.axis2.databinding.ADBBean{
        /* This type was generated from the piece of schema that had
                name = FMT_CRMS_SVR_S03601010ZX0001_IN
                Namespace URI = http://www.adtec.com.cn
                Namespace Prefix = ns1
                */
            

        private static java.lang.String generatePrefix(java.lang.String namespace) {
            if(namespace.equals("http://www.adtec.com.cn")){
                return "ns1";
            }
            return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
        }

        

                        /**
                        * field for CustType
                        */

                        
                            protected java.lang.String localCustType ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localCustTypeTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getCustType(){
                               return localCustType;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param CustType
                               */
                               public void setCustType(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localCustTypeTracker = true;
                                       } else {
                                          localCustTypeTracker = false;
                                              
                                       }
                                   
                                            this.localCustType=param;
                                    

                               }
                            

                        /**
                        * field for CustName
                        */

                        
                            protected java.lang.String localCustName ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localCustNameTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getCustName(){
                               return localCustName;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param CustName
                               */
                               public void setCustName(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localCustNameTracker = true;
                                       } else {
                                          localCustNameTracker = false;
                                              
                                       }
                                   
                                            this.localCustName=param;
                                    

                               }
                            

                        /**
                        * field for CertType
                        */

                        
                            protected java.lang.String localCertType ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localCertTypeTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getCertType(){
                               return localCertType;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param CertType
                               */
                               public void setCertType(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localCertTypeTracker = true;
                                       } else {
                                          localCertTypeTracker = false;
                                              
                                       }
                                   
                                            this.localCertType=param;
                                    

                               }
                            

                        /**
                        * field for CertNo
                        */

                        
                            protected java.lang.String localCertNo ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localCertNoTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getCertNo(){
                               return localCertNo;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param CertNo
                               */
                               public void setCertNo(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localCertNoTracker = true;
                                       } else {
                                          localCertNoTracker = false;
                                              
                                       }
                                   
                                            this.localCertNo=param;
                                    

                               }
                            

                        /**
                        * field for IdvCtfEffDate
                        */

                        
                            protected java.lang.String localIdvCtfEffDate ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localIdvCtfEffDateTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getIdvCtfEffDate(){
                               return localIdvCtfEffDate;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param IdvCtfEffDate
                               */
                               public void setIdvCtfEffDate(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localIdvCtfEffDateTracker = true;
                                       } else {
                                          localIdvCtfEffDateTracker = false;
                                              
                                       }
                                   
                                            this.localIdvCtfEffDate=param;
                                    

                               }
                            

                        /**
                        * field for IdvCtfLsefcDate
                        */

                        
                            protected java.lang.String localIdvCtfLsefcDate ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localIdvCtfLsefcDateTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getIdvCtfLsefcDate(){
                               return localIdvCtfLsefcDate;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param IdvCtfLsefcDate
                               */
                               public void setIdvCtfLsefcDate(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localIdvCtfLsefcDateTracker = true;
                                       } else {
                                          localIdvCtfLsefcDateTracker = false;
                                              
                                       }
                                   
                                            this.localIdvCtfLsefcDate=param;
                                    

                               }
                            

                        /**
                        * field for OnslfEnqr
                        */

                        
                            protected java.lang.String localOnslfEnqr ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localOnslfEnqrTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getOnslfEnqr(){
                               return localOnslfEnqr;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param OnslfEnqr
                               */
                               public void setOnslfEnqr(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localOnslfEnqrTracker = true;
                                       } else {
                                          localOnslfEnqrTracker = false;
                                              
                                       }
                                   
                                            this.localOnslfEnqr=param;
                                    

                               }
                            

                        /**
                        * field for TrstName
                        */

                        
                            protected java.lang.String localTrstName ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localTrstNameTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getTrstName(){
                               return localTrstName;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param TrstName
                               */
                               public void setTrstName(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localTrstNameTracker = true;
                                       } else {
                                          localTrstNameTracker = false;
                                              
                                       }
                                   
                                            this.localTrstName=param;
                                    

                               }
                            

                        /**
                        * field for TrstCtfType
                        */

                        
                            protected java.lang.String localTrstCtfType ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localTrstCtfTypeTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getTrstCtfType(){
                               return localTrstCtfType;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param TrstCtfType
                               */
                               public void setTrstCtfType(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localTrstCtfTypeTracker = true;
                                       } else {
                                          localTrstCtfTypeTracker = false;
                                              
                                       }
                                   
                                            this.localTrstCtfType=param;
                                    

                               }
                            

                        /**
                        * field for TrstCtfNo
                        */

                        
                            protected java.lang.String localTrstCtfNo ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localTrstCtfNoTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getTrstCtfNo(){
                               return localTrstCtfNo;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param TrstCtfNo
                               */
                               public void setTrstCtfNo(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localTrstCtfNoTracker = true;
                                       } else {
                                          localTrstCtfNoTracker = false;
                                              
                                       }
                                   
                                            this.localTrstCtfNo=param;
                                    

                               }
                            

                        /**
                        * field for TrstCtfEffDate
                        */

                        
                            protected java.lang.String localTrstCtfEffDate ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localTrstCtfEffDateTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getTrstCtfEffDate(){
                               return localTrstCtfEffDate;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param TrstCtfEffDate
                               */
                               public void setTrstCtfEffDate(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localTrstCtfEffDateTracker = true;
                                       } else {
                                          localTrstCtfEffDateTracker = false;
                                              
                                       }
                                   
                                            this.localTrstCtfEffDate=param;
                                    

                               }
                            

                        /**
                        * field for TrstCtfDueDate
                        */

                        
                            protected java.lang.String localTrstCtfDueDate ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localTrstCtfDueDateTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getTrstCtfDueDate(){
                               return localTrstCtfDueDate;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param TrstCtfDueDate
                               */
                               public void setTrstCtfDueDate(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localTrstCtfDueDateTracker = true;
                                       } else {
                                          localTrstCtfDueDateTracker = false;
                                              
                                       }
                                   
                                            this.localTrstCtfDueDate=param;
                                    

                               }
                            

                        /**
                        * field for CustMgr
                        */

                        
                            protected java.lang.String localCustMgr ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localCustMgrTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getCustMgr(){
                               return localCustMgr;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param CustMgr
                               */
                               public void setCustMgr(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localCustMgrTracker = true;
                                       } else {
                                          localCustMgrTracker = false;
                                              
                                       }
                                   
                                            this.localCustMgr=param;
                                    

                               }
                            

                        /**
                        * field for BusiType
                        */

                        
                            protected java.lang.String localBusiType ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localBusiTypeTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getBusiType(){
                               return localBusiType;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param BusiType
                               */
                               public void setBusiType(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localBusiTypeTracker = true;
                                       } else {
                                          localBusiTypeTracker = false;
                                              
                                       }
                                   
                                            this.localBusiType=param;
                                    

                               }
                            

                        /**
                        * field for AhrEffDate
                        */

                        
                            protected java.lang.String localAhrEffDate ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localAhrEffDateTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getAhrEffDate(){
                               return localAhrEffDate;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param AhrEffDate
                               */
                               public void setAhrEffDate(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localAhrEffDateTracker = true;
                                       } else {
                                          localAhrEffDateTracker = false;
                                              
                                       }
                                   
                                            this.localAhrEffDate=param;
                                    

                               }
                            

                        /**
                        * field for AhrLsefcDate
                        */

                        
                            protected java.lang.String localAhrLsefcDate ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localAhrLsefcDateTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getAhrLsefcDate(){
                               return localAhrLsefcDate;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param AhrLsefcDate
                               */
                               public void setAhrLsefcDate(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localAhrLsefcDateTracker = true;
                                       } else {
                                          localAhrLsefcDateTracker = false;
                                              
                                       }
                                   
                                            this.localAhrLsefcDate=param;
                                    

                               }
                            

                        /**
                        * field for BsnNo
                        */

                        
                            protected java.lang.String localBsnNo ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localBsnNoTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getBsnNo(){
                               return localBsnNo;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param BsnNo
                               */
                               public void setBsnNo(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localBsnNoTracker = true;
                                       } else {
                                          localBsnNoTracker = false;
                                              
                                       }
                                   
                                            this.localBsnNo=param;
                                    

                               }
                            

                        /**
                        * field for BrchNo
                        */

                        
                            protected java.lang.String localBrchNo ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localBrchNoTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getBrchNo(){
                               return localBrchNo;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param BrchNo
                               */
                               public void setBrchNo(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localBrchNoTracker = true;
                                       } else {
                                          localBrchNoTracker = false;
                                              
                                       }
                                   
                                            this.localBrchNo=param;
                                    

                               }
                            

                        /**
                        * field for AhrImaNo
                        */

                        
                            protected java.lang.String localAhrImaNo ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localAhrImaNoTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getAhrImaNo(){
                               return localAhrImaNo;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param AhrImaNo
                               */
                               public void setAhrImaNo(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localAhrImaNoTracker = true;
                                       } else {
                                          localAhrImaNoTracker = false;
                                              
                                       }
                                   
                                            this.localAhrImaNo=param;
                                    

                               }
                            

                        /**
                        * field for TrstCtfImaNo
                        */

                        
                            protected java.lang.String localTrstCtfImaNo ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localTrstCtfImaNoTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getTrstCtfImaNo(){
                               return localTrstCtfImaNo;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param TrstCtfImaNo
                               */
                               public void setTrstCtfImaNo(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localTrstCtfImaNoTracker = true;
                                       } else {
                                          localTrstCtfImaNoTracker = false;
                                              
                                       }
                                   
                                            this.localTrstCtfImaNo=param;
                                    

                               }
                            

                        /**
                        * field for IdvCtfImaNo
                        */

                        
                            protected java.lang.String localIdvCtfImaNo ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localIdvCtfImaNoTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getIdvCtfImaNo(){
                               return localIdvCtfImaNo;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param IdvCtfImaNo
                               */
                               public void setIdvCtfImaNo(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localIdvCtfImaNoTracker = true;
                                       } else {
                                          localIdvCtfImaNoTracker = false;
                                              
                                       }
                                   
                                            this.localIdvCtfImaNo=param;
                                    

                               }
                            

                        /**
                        * field for QueryReason
                        */

                        
                            protected java.lang.String localQueryReason ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localQueryReasonTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getQueryReason(){
                               return localQueryReason;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param QueryReason
                               */
                               public void setQueryReason(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localQueryReasonTracker = true;
                                       } else {
                                          localQueryReasonTracker = false;
                                              
                                       }
                                   
                                            this.localQueryReason=param;
                                    

                               }
                            

                        /**
                        * field for Bwflag
                        */

                        
                            protected java.lang.String localBwflag ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localBwflagTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getBwflag(){
                               return localBwflag;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Bwflag
                               */
                               public void setBwflag(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localBwflagTracker = true;
                                       } else {
                                          localBwflagTracker = false;
                                              
                                       }
                                   
                                            this.localBwflag=param;
                                    

                               }
                            

     /**
     * isReaderMTOMAware
     * @return true if the reader supports MTOM
     */
   public static boolean isReaderMTOMAware(javax.xml.stream.XMLStreamReader reader) {
        boolean isReaderMTOMAware = false;
        
        try{
          isReaderMTOMAware = java.lang.Boolean.TRUE.equals(reader.getProperty(org.apache.axiom.om.OMConstants.IS_DATA_HANDLERS_AWARE));
        }catch(java.lang.IllegalArgumentException e){
          isReaderMTOMAware = false;
        }
        return isReaderMTOMAware;
   }
     
     
        /**
        *
        * @param parentQName
        * @param factory
        * @return org.apache.axiom.om.OMElement
        */
       public org.apache.axiom.om.OMElement getOMElement (
               final javax.xml.namespace.QName parentQName,
               final org.apache.axiom.om.OMFactory factory) throws org.apache.axis2.databinding.ADBException{


        
               org.apache.axiom.om.OMDataSource dataSource =
                       new org.apache.axis2.databinding.ADBDataSource(this,parentQName){

                 public void serialize(org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
                       FMT_CRMS_SVR_S03601010ZX0001_IN.this.serialize(parentQName,factory,xmlWriter);
                 }
               };
               return new org.apache.axiom.om.impl.llom.OMSourcedElementImpl(
               parentQName,factory,dataSource);
            
       }



         public void serialize(final javax.xml.namespace.QName parentQName,
                               final org.apache.axiom.om.OMFactory factory,
                               org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException{
            
                


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
                 if (localCustTypeTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"CustType", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"CustType");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("CustType");
                                    }
                                

                                          if (localCustType==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("CustType cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localCustType);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localCustNameTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"CustName", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"CustName");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("CustName");
                                    }
                                

                                          if (localCustName==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("CustName cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localCustName);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localCertTypeTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"CertType", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"CertType");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("CertType");
                                    }
                                

                                          if (localCertType==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("CertType cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localCertType);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localCertNoTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"CertNo", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"CertNo");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("CertNo");
                                    }
                                

                                          if (localCertNo==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("CertNo cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localCertNo);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localIdvCtfEffDateTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"IdvCtfEffDate", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"IdvCtfEffDate");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("IdvCtfEffDate");
                                    }
                                

                                          if (localIdvCtfEffDate==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("IdvCtfEffDate cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localIdvCtfEffDate);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localIdvCtfLsefcDateTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"IdvCtfLsefcDate", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"IdvCtfLsefcDate");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("IdvCtfLsefcDate");
                                    }
                                

                                          if (localIdvCtfLsefcDate==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("IdvCtfLsefcDate cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localIdvCtfLsefcDate);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localOnslfEnqrTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"OnslfEnqr", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"OnslfEnqr");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("OnslfEnqr");
                                    }
                                

                                          if (localOnslfEnqr==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("OnslfEnqr cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localOnslfEnqr);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localTrstNameTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"TrstName", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"TrstName");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("TrstName");
                                    }
                                

                                          if (localTrstName==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("TrstName cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localTrstName);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localTrstCtfTypeTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"TrstCtfType", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"TrstCtfType");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("TrstCtfType");
                                    }
                                

                                          if (localTrstCtfType==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("TrstCtfType cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localTrstCtfType);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localTrstCtfNoTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"TrstCtfNo", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"TrstCtfNo");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("TrstCtfNo");
                                    }
                                

                                          if (localTrstCtfNo==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("TrstCtfNo cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localTrstCtfNo);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localTrstCtfEffDateTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"TrstCtfEffDate", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"TrstCtfEffDate");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("TrstCtfEffDate");
                                    }
                                

                                          if (localTrstCtfEffDate==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("TrstCtfEffDate cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localTrstCtfEffDate);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localTrstCtfDueDateTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"TrstCtfDueDate", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"TrstCtfDueDate");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("TrstCtfDueDate");
                                    }
                                

                                          if (localTrstCtfDueDate==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("TrstCtfDueDate cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localTrstCtfDueDate);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localCustMgrTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"CustMgr", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"CustMgr");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("CustMgr");
                                    }
                                

                                          if (localCustMgr==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("CustMgr cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localCustMgr);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localBusiTypeTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"BusiType", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"BusiType");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("BusiType");
                                    }
                                

                                          if (localBusiType==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("BusiType cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localBusiType);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localAhrEffDateTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"AhrEffDate", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"AhrEffDate");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("AhrEffDate");
                                    }
                                

                                          if (localAhrEffDate==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("AhrEffDate cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localAhrEffDate);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localAhrLsefcDateTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"AhrLsefcDate", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"AhrLsefcDate");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("AhrLsefcDate");
                                    }
                                

                                          if (localAhrLsefcDate==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("AhrLsefcDate cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localAhrLsefcDate);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localBsnNoTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"BsnNo", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"BsnNo");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("BsnNo");
                                    }
                                

                                          if (localBsnNo==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("BsnNo cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localBsnNo);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localBrchNoTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"BrchNo", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"BrchNo");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("BrchNo");
                                    }
                                

                                          if (localBrchNo==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("BrchNo cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localBrchNo);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localAhrImaNoTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"AhrImaNo", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"AhrImaNo");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("AhrImaNo");
                                    }
                                

                                          if (localAhrImaNo==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("AhrImaNo cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localAhrImaNo);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localTrstCtfImaNoTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"TrstCtfImaNo", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"TrstCtfImaNo");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("TrstCtfImaNo");
                                    }
                                

                                          if (localTrstCtfImaNo==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("TrstCtfImaNo cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localTrstCtfImaNo);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localIdvCtfImaNoTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"IdvCtfImaNo", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"IdvCtfImaNo");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("IdvCtfImaNo");
                                    }
                                

                                          if (localIdvCtfImaNo==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("IdvCtfImaNo cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localIdvCtfImaNo);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localQueryReasonTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"QueryReason", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"QueryReason");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("QueryReason");
                                    }
                                

                                          if (localQueryReason==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("QueryReason cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localQueryReason);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localBwflagTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"bwflag", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"bwflag");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("bwflag");
                                    }
                                

                                          if (localBwflag==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("bwflag cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localBwflag);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             }
                    xmlWriter.writeEndElement();
               

        }

         /**
          * Util method to write an attribute with the ns prefix
          */
          private void writeAttribute(java.lang.String prefix,java.lang.String namespace,java.lang.String attName,
                                      java.lang.String attValue,javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException{
              if (xmlWriter.getPrefix(namespace) == null) {
                       xmlWriter.writeNamespace(prefix, namespace);
                       xmlWriter.setPrefix(prefix, namespace);

              }

              xmlWriter.writeAttribute(namespace,attName,attValue);

         }

        /**
          * Util method to write an attribute without the ns prefix
          */
          private void writeAttribute(java.lang.String namespace,java.lang.String attName,
                                      java.lang.String attValue,javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException{
                if (namespace.equals(""))
              {
                  xmlWriter.writeAttribute(attName,attValue);
              }
              else
              {
                  registerPrefix(xmlWriter, namespace);
                  xmlWriter.writeAttribute(namespace,attName,attValue);
              }
          }


           /**
             * Util method to write an attribute without the ns prefix
             */
            private void writeQNameAttribute(java.lang.String namespace, java.lang.String attName,
                                             javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

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
         *  method to handle Qnames
         */

        private void writeQName(javax.xml.namespace.QName qname,
                                javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
            java.lang.String namespaceURI = qname.getNamespaceURI();
            if (namespaceURI != null) {
                java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
                if (prefix == null) {
                    prefix = generatePrefix(namespaceURI);
                    xmlWriter.writeNamespace(prefix, namespaceURI);
                    xmlWriter.setPrefix(prefix,namespaceURI);
                }

                if (prefix.trim().length() > 0){
                    xmlWriter.writeCharacters(prefix + ":" + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
                } else {
                    // i.e this is the default namespace
                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
                }

            } else {
                xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
            }
        }

        private void writeQNames(javax.xml.namespace.QName[] qnames,
                                 javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

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
                            xmlWriter.setPrefix(prefix,namespaceURI);
                        }

                        if (prefix.trim().length() > 0){
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
        *
        */
        public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName)
                    throws org.apache.axis2.databinding.ADBException{


        
                 java.util.ArrayList elementList = new java.util.ArrayList();
                 java.util.ArrayList attribList = new java.util.ArrayList();

                 if (localCustTypeTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "CustType"));
                                 
                                        if (localCustType != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCustType));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("CustType cannot be null!!");
                                        }
                                    } if (localCustNameTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "CustName"));
                                 
                                        if (localCustName != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCustName));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("CustName cannot be null!!");
                                        }
                                    } if (localCertTypeTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "CertType"));
                                 
                                        if (localCertType != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCertType));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("CertType cannot be null!!");
                                        }
                                    } if (localCertNoTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "CertNo"));
                                 
                                        if (localCertNo != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCertNo));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("CertNo cannot be null!!");
                                        }
                                    } if (localIdvCtfEffDateTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "IdvCtfEffDate"));
                                 
                                        if (localIdvCtfEffDate != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localIdvCtfEffDate));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("IdvCtfEffDate cannot be null!!");
                                        }
                                    } if (localIdvCtfLsefcDateTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "IdvCtfLsefcDate"));
                                 
                                        if (localIdvCtfLsefcDate != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localIdvCtfLsefcDate));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("IdvCtfLsefcDate cannot be null!!");
                                        }
                                    } if (localOnslfEnqrTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "OnslfEnqr"));
                                 
                                        if (localOnslfEnqr != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localOnslfEnqr));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("OnslfEnqr cannot be null!!");
                                        }
                                    } if (localTrstNameTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "TrstName"));
                                 
                                        if (localTrstName != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localTrstName));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("TrstName cannot be null!!");
                                        }
                                    } if (localTrstCtfTypeTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "TrstCtfType"));
                                 
                                        if (localTrstCtfType != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localTrstCtfType));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("TrstCtfType cannot be null!!");
                                        }
                                    } if (localTrstCtfNoTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "TrstCtfNo"));
                                 
                                        if (localTrstCtfNo != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localTrstCtfNo));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("TrstCtfNo cannot be null!!");
                                        }
                                    } if (localTrstCtfEffDateTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "TrstCtfEffDate"));
                                 
                                        if (localTrstCtfEffDate != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localTrstCtfEffDate));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("TrstCtfEffDate cannot be null!!");
                                        }
                                    } if (localTrstCtfDueDateTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "TrstCtfDueDate"));
                                 
                                        if (localTrstCtfDueDate != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localTrstCtfDueDate));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("TrstCtfDueDate cannot be null!!");
                                        }
                                    } if (localCustMgrTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "CustMgr"));
                                 
                                        if (localCustMgr != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCustMgr));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("CustMgr cannot be null!!");
                                        }
                                    } if (localBusiTypeTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "BusiType"));
                                 
                                        if (localBusiType != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localBusiType));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("BusiType cannot be null!!");
                                        }
                                    } if (localAhrEffDateTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "AhrEffDate"));
                                 
                                        if (localAhrEffDate != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localAhrEffDate));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("AhrEffDate cannot be null!!");
                                        }
                                    } if (localAhrLsefcDateTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "AhrLsefcDate"));
                                 
                                        if (localAhrLsefcDate != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localAhrLsefcDate));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("AhrLsefcDate cannot be null!!");
                                        }
                                    } if (localBsnNoTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "BsnNo"));
                                 
                                        if (localBsnNo != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localBsnNo));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("BsnNo cannot be null!!");
                                        }
                                    } if (localBrchNoTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "BrchNo"));
                                 
                                        if (localBrchNo != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localBrchNo));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("BrchNo cannot be null!!");
                                        }
                                    } if (localAhrImaNoTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "AhrImaNo"));
                                 
                                        if (localAhrImaNo != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localAhrImaNo));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("AhrImaNo cannot be null!!");
                                        }
                                    } if (localTrstCtfImaNoTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "TrstCtfImaNo"));
                                 
                                        if (localTrstCtfImaNo != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localTrstCtfImaNo));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("TrstCtfImaNo cannot be null!!");
                                        }
                                    } if (localIdvCtfImaNoTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "IdvCtfImaNo"));
                                 
                                        if (localIdvCtfImaNo != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localIdvCtfImaNo));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("IdvCtfImaNo cannot be null!!");
                                        }
                                    } if (localQueryReasonTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "QueryReason"));
                                 
                                        if (localQueryReason != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localQueryReason));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("QueryReason cannot be null!!");
                                        }
                                    } if (localBwflagTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "bwflag"));
                                 
                                        if (localBwflag != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localBwflag));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("bwflag cannot be null!!");
                                        }
                                    }

                return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName, elementList.toArray(), attribList.toArray());
            
            

        }

  

     /**
      *  Factory class that keeps the parse method
      */
    public static class Factory{

        
        

        /**
        * static method to create the object
        * Precondition:  If this object is an element, the current or next start element starts this object and any intervening reader events are ignorable
        *                If this object is not an element, it is a complex type and the reader is at the event just after the outer start element
        * Postcondition: If this object is an element, the reader is positioned at its end element
        *                If this object is a complex type, the reader is positioned at the end element of its outer element
        */
        public static FMT_CRMS_SVR_S03601010ZX0001_IN parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            FMT_CRMS_SVR_S03601010ZX0001_IN object =
                new FMT_CRMS_SVR_S03601010ZX0001_IN();

            int event;
            java.lang.String nillableValue = null;
            java.lang.String prefix ="";
            java.lang.String namespaceuri ="";
            try {
                
                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                
                if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","type")!=null){
                  java.lang.String fullTypeName = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                        "type");
                  if (fullTypeName!=null){
                    java.lang.String nsPrefix = null;
                    if (fullTypeName.indexOf(":") > -1){
                        nsPrefix = fullTypeName.substring(0,fullTypeName.indexOf(":"));
                    }
                    nsPrefix = nsPrefix==null?"":nsPrefix;

                    java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":")+1);
                    
                            if (!"FMT_CRMS_SVR_S03601010ZX0001_IN".equals(type)){
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                                return (FMT_CRMS_SVR_S03601010ZX0001_IN)ExtensionMapper.getTypeObject(
                                     nsUri,type,reader);
                              }
                        

                  }

                }
                

                
                // Note all attributes that were handled. Used to differ normal attributes
                // from anyAttributes.
                java.util.Vector handledAttributes = new java.util.Vector();
                

                 
                    
                    reader.next();
                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","CustType").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setCustType(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","CustName").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setCustName(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","CertType").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setCertType(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","CertNo").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setCertNo(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","IdvCtfEffDate").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setIdvCtfEffDate(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","IdvCtfLsefcDate").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setIdvCtfLsefcDate(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","OnslfEnqr").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setOnslfEnqr(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","TrstName").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setTrstName(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","TrstCtfType").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setTrstCtfType(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","TrstCtfNo").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setTrstCtfNo(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","TrstCtfEffDate").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setTrstCtfEffDate(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","TrstCtfDueDate").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setTrstCtfDueDate(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","CustMgr").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setCustMgr(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","BusiType").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setBusiType(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","AhrEffDate").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setAhrEffDate(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","AhrLsefcDate").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setAhrLsefcDate(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","BsnNo").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setBsnNo(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","BrchNo").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setBrchNo(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","AhrImaNo").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setAhrImaNo(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","TrstCtfImaNo").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setTrstCtfImaNo(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","IdvCtfImaNo").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setIdvCtfImaNo(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","QueryReason").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setQueryReason(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","bwflag").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setBwflag(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
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

        }//end of factory class

        

        }
           
          

        public static class FMT_SOAP_UTF8_ResTranHeader
        implements org.apache.axis2.databinding.ADBBean{
        /* This type was generated from the piece of schema that had
                name = FMT_SOAP_UTF8_ResTranHeader
                Namespace URI = http://www.adtec.com.cn
                Namespace Prefix = ns1
                */
            

        private static java.lang.String generatePrefix(java.lang.String namespace) {
            if(namespace.equals("http://www.adtec.com.cn")){
                return "ns1";
            }
            return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
        }

        

                        /**
                        * field for HSecFlag
                        */

                        
                            protected java.lang.String localHSecFlag ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localHSecFlagTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getHSecFlag(){
                               return localHSecFlag;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param HSecFlag
                               */
                               public void setHSecFlag(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localHSecFlagTracker = true;
                                       } else {
                                          localHSecFlagTracker = false;
                                              
                                       }
                                   
                                            this.localHSecFlag=param;
                                    

                               }
                            

                        /**
                        * field for HCombFlag
                        */

                        
                            protected java.lang.String localHCombFlag ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localHCombFlagTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getHCombFlag(){
                               return localHCombFlag;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param HCombFlag
                               */
                               public void setHCombFlag(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localHCombFlagTracker = true;
                                       } else {
                                          localHCombFlagTracker = false;
                                              
                                       }
                                   
                                            this.localHCombFlag=param;
                                    

                               }
                            

                        /**
                        * field for HSvcInfo
                        */

                        
                            protected java.lang.String localHSvcInfo ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localHSvcInfoTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getHSvcInfo(){
                               return localHSvcInfo;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param HSvcInfo
                               */
                               public void setHSvcInfo(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localHSvcInfoTracker = true;
                                       } else {
                                          localHSvcInfoTracker = false;
                                              
                                       }
                                   
                                            this.localHSvcInfo=param;
                                    

                               }
                            

                        /**
                        * field for HSecInfoVerNo
                        */

                        
                            protected java.lang.String localHSecInfoVerNo ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localHSecInfoVerNoTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getHSecInfoVerNo(){
                               return localHSecInfoVerNo;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param HSecInfoVerNo
                               */
                               public void setHSecInfoVerNo(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localHSecInfoVerNoTracker = true;
                                       } else {
                                          localHSecInfoVerNoTracker = false;
                                              
                                       }
                                   
                                            this.localHSecInfoVerNo=param;
                                    

                               }
                            

                        /**
                        * field for HMsgRefNo
                        */

                        
                            protected java.lang.String localHMsgRefNo ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localHMsgRefNoTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getHMsgRefNo(){
                               return localHMsgRefNo;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param HMsgRefNo
                               */
                               public void setHMsgRefNo(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localHMsgRefNoTracker = true;
                                       } else {
                                          localHMsgRefNoTracker = false;
                                              
                                       }
                                   
                                            this.localHMsgRefNo=param;
                                    

                               }
                            

                        /**
                        * field for HIdentFlag
                        */

                        
                            protected java.lang.String localHIdentFlag ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localHIdentFlagTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getHIdentFlag(){
                               return localHIdentFlag;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param HIdentFlag
                               */
                               public void setHIdentFlag(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localHIdentFlagTracker = true;
                                       } else {
                                          localHIdentFlagTracker = false;
                                              
                                       }
                                   
                                            this.localHIdentFlag=param;
                                    

                               }
                            

                        /**
                        * field for HSuperFlag
                        */

                        
                            protected java.lang.String localHSuperFlag ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localHSuperFlagTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getHSuperFlag(){
                               return localHSuperFlag;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param HSuperFlag
                               */
                               public void setHSuperFlag(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localHSuperFlagTracker = true;
                                       } else {
                                          localHSuperFlagTracker = false;
                                              
                                       }
                                   
                                            this.localHSuperFlag=param;
                                    

                               }
                            

                        /**
                        * field for HChkFlag
                        */

                        
                            protected java.lang.String localHChkFlag ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localHChkFlagTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getHChkFlag(){
                               return localHChkFlag;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param HChkFlag
                               */
                               public void setHChkFlag(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localHChkFlagTracker = true;
                                       } else {
                                          localHChkFlagTracker = false;
                                              
                                       }
                                   
                                            this.localHChkFlag=param;
                                    

                               }
                            

                        /**
                        * field for HChkTxnCd
                        */

                        
                            protected java.lang.String localHChkTxnCd ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localHChkTxnCdTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getHChkTxnCd(){
                               return localHChkTxnCd;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param HChkTxnCd
                               */
                               public void setHChkTxnCd(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localHChkTxnCdTracker = true;
                                       } else {
                                          localHChkTxnCdTracker = false;
                                              
                                       }
                                   
                                            this.localHChkTxnCd=param;
                                    

                               }
                            

                        /**
                        * field for HVerfCd
                        */

                        
                            protected java.lang.String localHVerfCd ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localHVerfCdTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getHVerfCd(){
                               return localHVerfCd;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param HVerfCd
                               */
                               public void setHVerfCd(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localHVerfCdTracker = true;
                                       } else {
                                          localHVerfCdTracker = false;
                                              
                                       }
                                   
                                            this.localHVerfCd=param;
                                    

                               }
                            

                        /**
                        * field for HTranRes
                        */

                        
                            protected java.lang.String localHTranRes ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localHTranResTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getHTranRes(){
                               return localHTranRes;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param HTranRes
                               */
                               public void setHTranRes(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localHTranResTracker = true;
                                       } else {
                                          localHTranResTracker = false;
                                              
                                       }
                                   
                                            this.localHTranRes=param;
                                    

                               }
                            

                        /**
                        * field for HRefTxnCd
                        */

                        
                            protected java.lang.String localHRefTxnCd ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localHRefTxnCdTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getHRefTxnCd(){
                               return localHRefTxnCd;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param HRefTxnCd
                               */
                               public void setHRefTxnCd(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localHRefTxnCdTracker = true;
                                       } else {
                                          localHRefTxnCdTracker = false;
                                              
                                       }
                                   
                                            this.localHRefTxnCd=param;
                                    

                               }
                            

                        /**
                        * field for HServerDt
                        */

                        
                            protected java.lang.String localHServerDt ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localHServerDtTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getHServerDt(){
                               return localHServerDt;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param HServerDt
                               */
                               public void setHServerDt(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localHServerDtTracker = true;
                                       } else {
                                          localHServerDtTracker = false;
                                              
                                       }
                                   
                                            this.localHServerDt=param;
                                    

                               }
                            

                        /**
                        * field for HServerTm
                        */

                        
                            protected java.lang.String localHServerTm ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localHServerTmTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getHServerTm(){
                               return localHServerTm;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param HServerTm
                               */
                               public void setHServerTm(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localHServerTmTracker = true;
                                       } else {
                                          localHServerTmTracker = false;
                                              
                                       }
                                   
                                            this.localHServerTm=param;
                                    

                               }
                            

                        /**
                        * field for HServerSeq
                        */

                        
                            protected java.lang.String localHServerSeq ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localHServerSeqTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getHServerSeq(){
                               return localHServerSeq;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param HServerSeq
                               */
                               public void setHServerSeq(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localHServerSeqTracker = true;
                                       } else {
                                          localHServerSeqTracker = false;
                                              
                                       }
                                   
                                            this.localHServerSeq=param;
                                    

                               }
                            

                        /**
                        * field for HAcountDt
                        */

                        
                            protected java.lang.String localHAcountDt ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localHAcountDtTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getHAcountDt(){
                               return localHAcountDt;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param HAcountDt
                               */
                               public void setHAcountDt(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localHAcountDtTracker = true;
                                       } else {
                                          localHAcountDtTracker = false;
                                              
                                       }
                                   
                                            this.localHAcountDt=param;
                                    

                               }
                            

                        /**
                        * field for HRefSeq
                        */

                        
                            protected java.lang.String localHRefSeq ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localHRefSeqTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getHRefSeq(){
                               return localHRefSeq;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param HRefSeq
                               */
                               public void setHRefSeq(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localHRefSeqTracker = true;
                                       } else {
                                          localHRefSeqTracker = false;
                                              
                                       }
                                   
                                            this.localHRefSeq=param;
                                    

                               }
                            

                        /**
                        * field for HRefDt
                        */

                        
                            protected java.lang.String localHRefDt ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localHRefDtTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getHRefDt(){
                               return localHRefDt;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param HRefDt
                               */
                               public void setHRefDt(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localHRefDtTracker = true;
                                       } else {
                                          localHRefDtTracker = false;
                                              
                                       }
                                   
                                            this.localHRefDt=param;
                                    

                               }
                            

                        /**
                        * field for HNextStep
                        */

                        
                            protected java.lang.String localHNextStep ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localHNextStepTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getHNextStep(){
                               return localHNextStep;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param HNextStep
                               */
                               public void setHNextStep(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localHNextStepTracker = true;
                                       } else {
                                          localHNextStepTracker = false;
                                              
                                       }
                                   
                                            this.localHNextStep=param;
                                    

                               }
                            

                        /**
                        * field for HVchChk
                        */

                        
                            protected java.lang.String localHVchChk ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localHVchChkTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getHVchChk(){
                               return localHVchChk;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param HVchChk
                               */
                               public void setHVchChk(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localHVchChkTracker = true;
                                       } else {
                                          localHVchChkTracker = false;
                                              
                                       }
                                   
                                            this.localHVchChk=param;
                                    

                               }
                            

                        /**
                        * field for HRetResInfo
                        */

                        
                            protected java.lang.String localHRetResInfo ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localHRetResInfoTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getHRetResInfo(){
                               return localHRetResInfo;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param HRetResInfo
                               */
                               public void setHRetResInfo(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localHRetResInfoTracker = true;
                                       } else {
                                          localHRetResInfoTracker = false;
                                              
                                       }
                                   
                                            this.localHRetResInfo=param;
                                    

                               }
                            

                        /**
                        * field for HErrTranNo
                        */

                        
                            protected java.lang.String localHErrTranNo ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localHErrTranNoTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getHErrTranNo(){
                               return localHErrTranNo;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param HErrTranNo
                               */
                               public void setHErrTranNo(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localHErrTranNoTracker = true;
                                       } else {
                                          localHErrTranNoTracker = false;
                                              
                                       }
                                   
                                            this.localHErrTranNo=param;
                                    

                               }
                            

                        /**
                        * field for HAssiInfo
                        */

                        
                            protected java.lang.String localHAssiInfo ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localHAssiInfoTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getHAssiInfo(){
                               return localHAssiInfo;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param HAssiInfo
                               */
                               public void setHAssiInfo(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localHAssiInfoTracker = true;
                                       } else {
                                          localHAssiInfoTracker = false;
                                              
                                       }
                                   
                                            this.localHAssiInfo=param;
                                    

                               }
                            

                        /**
                        * field for HRetCode
                        */

                        
                            protected java.lang.String localHRetCode ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localHRetCodeTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getHRetCode(){
                               return localHRetCode;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param HRetCode
                               */
                               public void setHRetCode(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localHRetCodeTracker = true;
                                       } else {
                                          localHRetCodeTracker = false;
                                              
                                       }
                                   
                                            this.localHRetCode=param;
                                    

                               }
                            

                        /**
                        * field for HRetNo
                        */

                        
                            protected java.lang.String localHRetNo ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localHRetNoTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getHRetNo(){
                               return localHRetNo;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param HRetNo
                               */
                               public void setHRetNo(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localHRetNoTracker = true;
                                       } else {
                                          localHRetNoTracker = false;
                                              
                                       }
                                   
                                            this.localHRetNo=param;
                                    

                               }
                            

                        /**
                        * field for HRetMsg
                        */

                        
                            protected java.lang.String localHRetMsg ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localHRetMsgTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getHRetMsg(){
                               return localHRetMsg;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param HRetMsg
                               */
                               public void setHRetMsg(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localHRetMsgTracker = true;
                                       } else {
                                          localHRetMsgTracker = false;
                                              
                                       }
                                   
                                            this.localHRetMsg=param;
                                    

                               }
                            

                        /**
                        * field for HWarnMsg
                        */

                        
                            protected java.lang.String localHWarnMsg ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localHWarnMsgTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getHWarnMsg(){
                               return localHWarnMsg;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param HWarnMsg
                               */
                               public void setHWarnMsg(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localHWarnMsgTracker = true;
                                       } else {
                                          localHWarnMsgTracker = false;
                                              
                                       }
                                   
                                            this.localHWarnMsg=param;
                                    

                               }
                            

     /**
     * isReaderMTOMAware
     * @return true if the reader supports MTOM
     */
   public static boolean isReaderMTOMAware(javax.xml.stream.XMLStreamReader reader) {
        boolean isReaderMTOMAware = false;
        
        try{
          isReaderMTOMAware = java.lang.Boolean.TRUE.equals(reader.getProperty(org.apache.axiom.om.OMConstants.IS_DATA_HANDLERS_AWARE));
        }catch(java.lang.IllegalArgumentException e){
          isReaderMTOMAware = false;
        }
        return isReaderMTOMAware;
   }
     
     
        /**
        *
        * @param parentQName
        * @param factory
        * @return org.apache.axiom.om.OMElement
        */
       public org.apache.axiom.om.OMElement getOMElement (
               final javax.xml.namespace.QName parentQName,
               final org.apache.axiom.om.OMFactory factory) throws org.apache.axis2.databinding.ADBException{


        
               org.apache.axiom.om.OMDataSource dataSource =
                       new org.apache.axis2.databinding.ADBDataSource(this,parentQName){

                 public void serialize(org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
                       FMT_SOAP_UTF8_ResTranHeader.this.serialize(parentQName,factory,xmlWriter);
                 }
               };
               return new org.apache.axiom.om.impl.llom.OMSourcedElementImpl(
               parentQName,factory,dataSource);
            
       }



         public void serialize(final javax.xml.namespace.QName parentQName,
                               final org.apache.axiom.om.OMFactory factory,
                               org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException{
            
                


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
                 if (localHSecFlagTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"HSecFlag", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"HSecFlag");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("HSecFlag");
                                    }
                                

                                          if (localHSecFlag==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("HSecFlag cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localHSecFlag);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localHCombFlagTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"HCombFlag", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"HCombFlag");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("HCombFlag");
                                    }
                                

                                          if (localHCombFlag==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("HCombFlag cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localHCombFlag);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localHSvcInfoTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"HSvcInfo", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"HSvcInfo");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("HSvcInfo");
                                    }
                                

                                          if (localHSvcInfo==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("HSvcInfo cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localHSvcInfo);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localHSecInfoVerNoTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"HSecInfoVerNo", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"HSecInfoVerNo");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("HSecInfoVerNo");
                                    }
                                

                                          if (localHSecInfoVerNo==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("HSecInfoVerNo cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localHSecInfoVerNo);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localHMsgRefNoTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"HMsgRefNo", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"HMsgRefNo");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("HMsgRefNo");
                                    }
                                

                                          if (localHMsgRefNo==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("HMsgRefNo cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localHMsgRefNo);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localHIdentFlagTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"HIdentFlag", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"HIdentFlag");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("HIdentFlag");
                                    }
                                

                                          if (localHIdentFlag==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("HIdentFlag cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localHIdentFlag);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localHSuperFlagTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"HSuperFlag", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"HSuperFlag");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("HSuperFlag");
                                    }
                                

                                          if (localHSuperFlag==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("HSuperFlag cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localHSuperFlag);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localHChkFlagTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"HChkFlag", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"HChkFlag");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("HChkFlag");
                                    }
                                

                                          if (localHChkFlag==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("HChkFlag cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localHChkFlag);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localHChkTxnCdTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"HChkTxnCd", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"HChkTxnCd");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("HChkTxnCd");
                                    }
                                

                                          if (localHChkTxnCd==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("HChkTxnCd cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localHChkTxnCd);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localHVerfCdTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"HVerfCd", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"HVerfCd");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("HVerfCd");
                                    }
                                

                                          if (localHVerfCd==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("HVerfCd cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localHVerfCd);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localHTranResTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"HTranRes", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"HTranRes");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("HTranRes");
                                    }
                                

                                          if (localHTranRes==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("HTranRes cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localHTranRes);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localHRefTxnCdTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"HRefTxnCd", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"HRefTxnCd");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("HRefTxnCd");
                                    }
                                

                                          if (localHRefTxnCd==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("HRefTxnCd cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localHRefTxnCd);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localHServerDtTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"HServerDt", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"HServerDt");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("HServerDt");
                                    }
                                

                                          if (localHServerDt==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("HServerDt cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localHServerDt);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localHServerTmTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"HServerTm", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"HServerTm");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("HServerTm");
                                    }
                                

                                          if (localHServerTm==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("HServerTm cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localHServerTm);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localHServerSeqTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"HServerSeq", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"HServerSeq");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("HServerSeq");
                                    }
                                

                                          if (localHServerSeq==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("HServerSeq cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localHServerSeq);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localHAcountDtTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"HAcountDt", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"HAcountDt");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("HAcountDt");
                                    }
                                

                                          if (localHAcountDt==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("HAcountDt cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localHAcountDt);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localHRefSeqTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"HRefSeq", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"HRefSeq");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("HRefSeq");
                                    }
                                

                                          if (localHRefSeq==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("HRefSeq cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localHRefSeq);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localHRefDtTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"HRefDt", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"HRefDt");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("HRefDt");
                                    }
                                

                                          if (localHRefDt==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("HRefDt cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localHRefDt);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localHNextStepTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"HNextStep", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"HNextStep");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("HNextStep");
                                    }
                                

                                          if (localHNextStep==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("HNextStep cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localHNextStep);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localHVchChkTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"HVchChk", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"HVchChk");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("HVchChk");
                                    }
                                

                                          if (localHVchChk==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("HVchChk cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localHVchChk);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localHRetResInfoTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"HRetResInfo", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"HRetResInfo");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("HRetResInfo");
                                    }
                                

                                          if (localHRetResInfo==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("HRetResInfo cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localHRetResInfo);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localHErrTranNoTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"HErrTranNo", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"HErrTranNo");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("HErrTranNo");
                                    }
                                

                                          if (localHErrTranNo==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("HErrTranNo cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localHErrTranNo);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localHAssiInfoTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"HAssiInfo", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"HAssiInfo");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("HAssiInfo");
                                    }
                                

                                          if (localHAssiInfo==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("HAssiInfo cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localHAssiInfo);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localHRetCodeTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"HRetCode", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"HRetCode");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("HRetCode");
                                    }
                                

                                          if (localHRetCode==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("HRetCode cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localHRetCode);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localHRetNoTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"HRetNo", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"HRetNo");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("HRetNo");
                                    }
                                

                                          if (localHRetNo==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("HRetNo cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localHRetNo);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localHRetMsgTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"HRetMsg", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"HRetMsg");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("HRetMsg");
                                    }
                                

                                          if (localHRetMsg==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("HRetMsg cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localHRetMsg);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localHWarnMsgTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"HWarnMsg", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"HWarnMsg");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("HWarnMsg");
                                    }
                                

                                          if (localHWarnMsg==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("HWarnMsg cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localHWarnMsg);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             }
                    xmlWriter.writeEndElement();
               

        }

         /**
          * Util method to write an attribute with the ns prefix
          */
          private void writeAttribute(java.lang.String prefix,java.lang.String namespace,java.lang.String attName,
                                      java.lang.String attValue,javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException{
              if (xmlWriter.getPrefix(namespace) == null) {
                       xmlWriter.writeNamespace(prefix, namespace);
                       xmlWriter.setPrefix(prefix, namespace);

              }

              xmlWriter.writeAttribute(namespace,attName,attValue);

         }

        /**
          * Util method to write an attribute without the ns prefix
          */
          private void writeAttribute(java.lang.String namespace,java.lang.String attName,
                                      java.lang.String attValue,javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException{
                if (namespace.equals(""))
              {
                  xmlWriter.writeAttribute(attName,attValue);
              }
              else
              {
                  registerPrefix(xmlWriter, namespace);
                  xmlWriter.writeAttribute(namespace,attName,attValue);
              }
          }


           /**
             * Util method to write an attribute without the ns prefix
             */
            private void writeQNameAttribute(java.lang.String namespace, java.lang.String attName,
                                             javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

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
         *  method to handle Qnames
         */

        private void writeQName(javax.xml.namespace.QName qname,
                                javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
            java.lang.String namespaceURI = qname.getNamespaceURI();
            if (namespaceURI != null) {
                java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
                if (prefix == null) {
                    prefix = generatePrefix(namespaceURI);
                    xmlWriter.writeNamespace(prefix, namespaceURI);
                    xmlWriter.setPrefix(prefix,namespaceURI);
                }

                if (prefix.trim().length() > 0){
                    xmlWriter.writeCharacters(prefix + ":" + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
                } else {
                    // i.e this is the default namespace
                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
                }

            } else {
                xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
            }
        }

        private void writeQNames(javax.xml.namespace.QName[] qnames,
                                 javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

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
                            xmlWriter.setPrefix(prefix,namespaceURI);
                        }

                        if (prefix.trim().length() > 0){
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
        *
        */
        public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName)
                    throws org.apache.axis2.databinding.ADBException{


        
                 java.util.ArrayList elementList = new java.util.ArrayList();
                 java.util.ArrayList attribList = new java.util.ArrayList();

                 if (localHSecFlagTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "HSecFlag"));
                                 
                                        if (localHSecFlag != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHSecFlag));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("HSecFlag cannot be null!!");
                                        }
                                    } if (localHCombFlagTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "HCombFlag"));
                                 
                                        if (localHCombFlag != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHCombFlag));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("HCombFlag cannot be null!!");
                                        }
                                    } if (localHSvcInfoTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "HSvcInfo"));
                                 
                                        if (localHSvcInfo != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHSvcInfo));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("HSvcInfo cannot be null!!");
                                        }
                                    } if (localHSecInfoVerNoTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "HSecInfoVerNo"));
                                 
                                        if (localHSecInfoVerNo != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHSecInfoVerNo));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("HSecInfoVerNo cannot be null!!");
                                        }
                                    } if (localHMsgRefNoTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "HMsgRefNo"));
                                 
                                        if (localHMsgRefNo != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHMsgRefNo));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("HMsgRefNo cannot be null!!");
                                        }
                                    } if (localHIdentFlagTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "HIdentFlag"));
                                 
                                        if (localHIdentFlag != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHIdentFlag));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("HIdentFlag cannot be null!!");
                                        }
                                    } if (localHSuperFlagTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "HSuperFlag"));
                                 
                                        if (localHSuperFlag != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHSuperFlag));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("HSuperFlag cannot be null!!");
                                        }
                                    } if (localHChkFlagTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "HChkFlag"));
                                 
                                        if (localHChkFlag != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHChkFlag));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("HChkFlag cannot be null!!");
                                        }
                                    } if (localHChkTxnCdTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "HChkTxnCd"));
                                 
                                        if (localHChkTxnCd != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHChkTxnCd));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("HChkTxnCd cannot be null!!");
                                        }
                                    } if (localHVerfCdTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "HVerfCd"));
                                 
                                        if (localHVerfCd != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHVerfCd));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("HVerfCd cannot be null!!");
                                        }
                                    } if (localHTranResTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "HTranRes"));
                                 
                                        if (localHTranRes != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHTranRes));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("HTranRes cannot be null!!");
                                        }
                                    } if (localHRefTxnCdTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "HRefTxnCd"));
                                 
                                        if (localHRefTxnCd != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHRefTxnCd));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("HRefTxnCd cannot be null!!");
                                        }
                                    } if (localHServerDtTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "HServerDt"));
                                 
                                        if (localHServerDt != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHServerDt));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("HServerDt cannot be null!!");
                                        }
                                    } if (localHServerTmTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "HServerTm"));
                                 
                                        if (localHServerTm != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHServerTm));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("HServerTm cannot be null!!");
                                        }
                                    } if (localHServerSeqTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "HServerSeq"));
                                 
                                        if (localHServerSeq != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHServerSeq));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("HServerSeq cannot be null!!");
                                        }
                                    } if (localHAcountDtTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "HAcountDt"));
                                 
                                        if (localHAcountDt != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHAcountDt));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("HAcountDt cannot be null!!");
                                        }
                                    } if (localHRefSeqTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "HRefSeq"));
                                 
                                        if (localHRefSeq != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHRefSeq));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("HRefSeq cannot be null!!");
                                        }
                                    } if (localHRefDtTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "HRefDt"));
                                 
                                        if (localHRefDt != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHRefDt));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("HRefDt cannot be null!!");
                                        }
                                    } if (localHNextStepTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "HNextStep"));
                                 
                                        if (localHNextStep != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHNextStep));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("HNextStep cannot be null!!");
                                        }
                                    } if (localHVchChkTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "HVchChk"));
                                 
                                        if (localHVchChk != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHVchChk));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("HVchChk cannot be null!!");
                                        }
                                    } if (localHRetResInfoTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "HRetResInfo"));
                                 
                                        if (localHRetResInfo != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHRetResInfo));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("HRetResInfo cannot be null!!");
                                        }
                                    } if (localHErrTranNoTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "HErrTranNo"));
                                 
                                        if (localHErrTranNo != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHErrTranNo));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("HErrTranNo cannot be null!!");
                                        }
                                    } if (localHAssiInfoTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "HAssiInfo"));
                                 
                                        if (localHAssiInfo != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHAssiInfo));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("HAssiInfo cannot be null!!");
                                        }
                                    } if (localHRetCodeTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "HRetCode"));
                                 
                                        if (localHRetCode != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHRetCode));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("HRetCode cannot be null!!");
                                        }
                                    } if (localHRetNoTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "HRetNo"));
                                 
                                        if (localHRetNo != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHRetNo));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("HRetNo cannot be null!!");
                                        }
                                    } if (localHRetMsgTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "HRetMsg"));
                                 
                                        if (localHRetMsg != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHRetMsg));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("HRetMsg cannot be null!!");
                                        }
                                    } if (localHWarnMsgTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "HWarnMsg"));
                                 
                                        if (localHWarnMsg != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHWarnMsg));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("HWarnMsg cannot be null!!");
                                        }
                                    }

                return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName, elementList.toArray(), attribList.toArray());
            
            

        }

  

     /**
      *  Factory class that keeps the parse method
      */
    public static class Factory{

        
        

        /**
        * static method to create the object
        * Precondition:  If this object is an element, the current or next start element starts this object and any intervening reader events are ignorable
        *                If this object is not an element, it is a complex type and the reader is at the event just after the outer start element
        * Postcondition: If this object is an element, the reader is positioned at its end element
        *                If this object is a complex type, the reader is positioned at the end element of its outer element
        */
        public static FMT_SOAP_UTF8_ResTranHeader parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            FMT_SOAP_UTF8_ResTranHeader object =
                new FMT_SOAP_UTF8_ResTranHeader();

            int event;
            java.lang.String nillableValue = null;
            java.lang.String prefix ="";
            java.lang.String namespaceuri ="";
            try {
                
                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                
                if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","type")!=null){
                  java.lang.String fullTypeName = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                        "type");
                  if (fullTypeName!=null){
                    java.lang.String nsPrefix = null;
                    if (fullTypeName.indexOf(":") > -1){
                        nsPrefix = fullTypeName.substring(0,fullTypeName.indexOf(":"));
                    }
                    nsPrefix = nsPrefix==null?"":nsPrefix;

                    java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":")+1);
                    
                            if (!"FMT_SOAP_UTF8_ResTranHeader".equals(type)){
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                                return (FMT_SOAP_UTF8_ResTranHeader)ExtensionMapper.getTypeObject(
                                     nsUri,type,reader);
                              }
                        

                  }

                }
                

                
                // Note all attributes that were handled. Used to differ normal attributes
                // from anyAttributes.
                java.util.Vector handledAttributes = new java.util.Vector();
                

                 
                    
                    reader.next();
                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","HSecFlag").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setHSecFlag(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","HCombFlag").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setHCombFlag(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","HSvcInfo").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setHSvcInfo(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","HSecInfoVerNo").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setHSecInfoVerNo(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","HMsgRefNo").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setHMsgRefNo(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","HIdentFlag").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setHIdentFlag(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","HSuperFlag").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setHSuperFlag(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","HChkFlag").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setHChkFlag(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","HChkTxnCd").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setHChkTxnCd(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","HVerfCd").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setHVerfCd(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","HTranRes").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setHTranRes(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","HRefTxnCd").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setHRefTxnCd(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","HServerDt").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setHServerDt(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","HServerTm").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setHServerTm(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","HServerSeq").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setHServerSeq(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","HAcountDt").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setHAcountDt(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","HRefSeq").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setHRefSeq(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","HRefDt").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setHRefDt(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","HNextStep").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setHNextStep(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","HVchChk").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setHVchChk(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","HRetResInfo").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setHRetResInfo(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","HErrTranNo").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setHErrTranNo(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","HAssiInfo").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setHAssiInfo(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","HRetCode").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setHRetCode(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","HRetNo").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setHRetNo(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","HRetMsg").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setHRetMsg(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","HWarnMsg").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setHWarnMsg(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
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

        }//end of factory class

        

        }
           
          

        public static class FMT_SOAP_UTF8_RequestHeader
        implements org.apache.axis2.databinding.ADBBean{
        /* This type was generated from the piece of schema that had
                name = FMT_SOAP_UTF8_RequestHeader
                Namespace URI = http://www.adtec.com.cn
                Namespace Prefix = ns1
                */
            

        private static java.lang.String generatePrefix(java.lang.String namespace) {
            if(namespace.equals("http://www.adtec.com.cn")){
                return "ns1";
            }
            return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
        }

        

                        /**
                        * field for VersionNo
                        */

                        
                            protected java.lang.String localVersionNo ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localVersionNoTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getVersionNo(){
                               return localVersionNo;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param VersionNo
                               */
                               public void setVersionNo(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localVersionNoTracker = true;
                                       } else {
                                          localVersionNoTracker = false;
                                              
                                       }
                                   
                                            this.localVersionNo=param;
                                    

                               }
                            

                        /**
                        * field for ReqSysCode
                        */

                        
                            protected java.lang.String localReqSysCode ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localReqSysCodeTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getReqSysCode(){
                               return localReqSysCode;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param ReqSysCode
                               */
                               public void setReqSysCode(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localReqSysCodeTracker = true;
                                       } else {
                                          localReqSysCodeTracker = false;
                                              
                                       }
                                   
                                            this.localReqSysCode=param;
                                    

                               }
                            

                        /**
                        * field for ReqSecCode
                        */

                        
                            protected java.lang.String localReqSecCode ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localReqSecCodeTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getReqSecCode(){
                               return localReqSecCode;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param ReqSecCode
                               */
                               public void setReqSecCode(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localReqSecCodeTracker = true;
                                       } else {
                                          localReqSecCodeTracker = false;
                                              
                                       }
                                   
                                            this.localReqSecCode=param;
                                    

                               }
                            

                        /**
                        * field for TxType
                        */

                        
                            protected java.lang.String localTxType ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localTxTypeTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getTxType(){
                               return localTxType;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param TxType
                               */
                               public void setTxType(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localTxTypeTracker = true;
                                       } else {
                                          localTxTypeTracker = false;
                                              
                                       }
                                   
                                            this.localTxType=param;
                                    

                               }
                            

                        /**
                        * field for TxMode
                        */

                        
                            protected java.lang.String localTxMode ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localTxModeTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getTxMode(){
                               return localTxMode;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param TxMode
                               */
                               public void setTxMode(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localTxModeTracker = true;
                                       } else {
                                          localTxModeTracker = false;
                                              
                                       }
                                   
                                            this.localTxMode=param;
                                    

                               }
                            

                        /**
                        * field for TxCode
                        */

                        
                            protected java.lang.String localTxCode ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localTxCodeTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getTxCode(){
                               return localTxCode;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param TxCode
                               */
                               public void setTxCode(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localTxCodeTracker = true;
                                       } else {
                                          localTxCodeTracker = false;
                                              
                                       }
                                   
                                            this.localTxCode=param;
                                    

                               }
                            

                        /**
                        * field for ReqDate
                        */

                        
                            protected java.lang.String localReqDate ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localReqDateTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getReqDate(){
                               return localReqDate;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param ReqDate
                               */
                               public void setReqDate(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localReqDateTracker = true;
                                       } else {
                                          localReqDateTracker = false;
                                              
                                       }
                                   
                                            this.localReqDate=param;
                                    

                               }
                            

                        /**
                        * field for ReqTime
                        */

                        
                            protected java.lang.String localReqTime ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localReqTimeTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getReqTime(){
                               return localReqTime;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param ReqTime
                               */
                               public void setReqTime(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localReqTimeTracker = true;
                                       } else {
                                          localReqTimeTracker = false;
                                              
                                       }
                                   
                                            this.localReqTime=param;
                                    

                               }
                            

                        /**
                        * field for ReqSeqNo
                        */

                        
                            protected java.lang.String localReqSeqNo ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localReqSeqNoTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getReqSeqNo(){
                               return localReqSeqNo;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param ReqSeqNo
                               */
                               public void setReqSeqNo(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localReqSeqNoTracker = true;
                                       } else {
                                          localReqSeqNoTracker = false;
                                              
                                       }
                                   
                                            this.localReqSeqNo=param;
                                    

                               }
                            

                        /**
                        * field for ChanlNo
                        */

                        
                            protected java.lang.String localChanlNo ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localChanlNoTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getChanlNo(){
                               return localChanlNo;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param ChanlNo
                               */
                               public void setChanlNo(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localChanlNoTracker = true;
                                       } else {
                                          localChanlNoTracker = false;
                                              
                                       }
                                   
                                            this.localChanlNo=param;
                                    

                               }
                            

                        /**
                        * field for Brch
                        */

                        
                            protected java.lang.String localBrch ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localBrchTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getBrch(){
                               return localBrch;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Brch
                               */
                               public void setBrch(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localBrchTracker = true;
                                       } else {
                                          localBrchTracker = false;
                                              
                                       }
                                   
                                            this.localBrch=param;
                                    

                               }
                            

                        /**
                        * field for TermNo
                        */

                        
                            protected java.lang.String localTermNo ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localTermNoTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getTermNo(){
                               return localTermNo;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param TermNo
                               */
                               public void setTermNo(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localTermNoTracker = true;
                                       } else {
                                          localTermNoTracker = false;
                                              
                                       }
                                   
                                            this.localTermNo=param;
                                    

                               }
                            

                        /**
                        * field for Oper
                        */

                        
                            protected java.lang.String localOper ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localOperTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getOper(){
                               return localOper;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Oper
                               */
                               public void setOper(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localOperTracker = true;
                                       } else {
                                          localOperTracker = false;
                                              
                                       }
                                   
                                            this.localOper=param;
                                    

                               }
                            

                        /**
                        * field for SendFileName
                        */

                        
                            protected java.lang.String localSendFileName ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localSendFileNameTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getSendFileName(){
                               return localSendFileName;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param SendFileName
                               */
                               public void setSendFileName(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localSendFileNameTracker = true;
                                       } else {
                                          localSendFileNameTracker = false;
                                              
                                       }
                                   
                                            this.localSendFileName=param;
                                    

                               }
                            

                        /**
                        * field for BeginRec
                        */

                        
                            protected java.lang.String localBeginRec ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localBeginRecTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getBeginRec(){
                               return localBeginRec;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param BeginRec
                               */
                               public void setBeginRec(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localBeginRecTracker = true;
                                       } else {
                                          localBeginRecTracker = false;
                                              
                                       }
                                   
                                            this.localBeginRec=param;
                                    

                               }
                            

                        /**
                        * field for MaxRec
                        */

                        
                            protected java.math.BigInteger localMaxRec ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localMaxRecTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.math.BigInteger
                           */
                           public  java.math.BigInteger getMaxRec(){
                               return localMaxRec;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param MaxRec
                               */
                               public void setMaxRec(java.math.BigInteger param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localMaxRecTracker = true;
                                       } else {
                                          localMaxRecTracker = false;
                                              
                                       }
                                   
                                            this.localMaxRec=param;
                                    

                               }
                            

                        /**
                        * field for FileHMac
                        */

                        
                            protected java.lang.String localFileHMac ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localFileHMacTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getFileHMac(){
                               return localFileHMac;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param FileHMac
                               */
                               public void setFileHMac(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localFileHMacTracker = true;
                                       } else {
                                          localFileHMacTracker = false;
                                              
                                       }
                                   
                                            this.localFileHMac=param;
                                    

                               }
                            

                        /**
                        * field for HMac
                        */

                        
                            protected java.lang.String localHMac ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localHMacTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getHMac(){
                               return localHMac;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param HMac
                               */
                               public void setHMac(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localHMacTracker = true;
                                       } else {
                                          localHMacTracker = false;
                                              
                                       }
                                   
                                            this.localHMac=param;
                                    

                               }
                            

     /**
     * isReaderMTOMAware
     * @return true if the reader supports MTOM
     */
   public static boolean isReaderMTOMAware(javax.xml.stream.XMLStreamReader reader) {
        boolean isReaderMTOMAware = false;
        
        try{
          isReaderMTOMAware = java.lang.Boolean.TRUE.equals(reader.getProperty(org.apache.axiom.om.OMConstants.IS_DATA_HANDLERS_AWARE));
        }catch(java.lang.IllegalArgumentException e){
          isReaderMTOMAware = false;
        }
        return isReaderMTOMAware;
   }
     
     
        /**
        *
        * @param parentQName
        * @param factory
        * @return org.apache.axiom.om.OMElement
        */
       public org.apache.axiom.om.OMElement getOMElement (
               final javax.xml.namespace.QName parentQName,
               final org.apache.axiom.om.OMFactory factory) throws org.apache.axis2.databinding.ADBException{


        
               org.apache.axiom.om.OMDataSource dataSource =
                       new org.apache.axis2.databinding.ADBDataSource(this,parentQName){

                 public void serialize(org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
                       FMT_SOAP_UTF8_RequestHeader.this.serialize(parentQName,factory,xmlWriter);
                 }
               };
               return new org.apache.axiom.om.impl.llom.OMSourcedElementImpl(
               parentQName,factory,dataSource);
            
       }



         public void serialize(final javax.xml.namespace.QName parentQName,
                               final org.apache.axiom.om.OMFactory factory,
                               org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException{
            
                


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
                 if (localVersionNoTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"VersionNo", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"VersionNo");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("VersionNo");
                                    }
                                

                                          if (localVersionNo==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("VersionNo cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localVersionNo);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localReqSysCodeTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"ReqSysCode", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"ReqSysCode");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("ReqSysCode");
                                    }
                                

                                          if (localReqSysCode==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("ReqSysCode cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localReqSysCode);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localReqSecCodeTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"ReqSecCode", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"ReqSecCode");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("ReqSecCode");
                                    }
                                

                                          if (localReqSecCode==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("ReqSecCode cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localReqSecCode);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localTxTypeTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"TxType", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"TxType");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("TxType");
                                    }
                                

                                          if (localTxType==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("TxType cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localTxType);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localTxModeTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"TxMode", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"TxMode");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("TxMode");
                                    }
                                

                                          if (localTxMode==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("TxMode cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localTxMode);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localTxCodeTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"TxCode", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"TxCode");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("TxCode");
                                    }
                                

                                          if (localTxCode==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("TxCode cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localTxCode);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localReqDateTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"ReqDate", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"ReqDate");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("ReqDate");
                                    }
                                

                                          if (localReqDate==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("ReqDate cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localReqDate);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localReqTimeTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"ReqTime", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"ReqTime");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("ReqTime");
                                    }
                                

                                          if (localReqTime==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("ReqTime cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localReqTime);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localReqSeqNoTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"ReqSeqNo", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"ReqSeqNo");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("ReqSeqNo");
                                    }
                                

                                          if (localReqSeqNo==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("ReqSeqNo cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localReqSeqNo);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localChanlNoTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"ChanlNo", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"ChanlNo");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("ChanlNo");
                                    }
                                

                                          if (localChanlNo==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("ChanlNo cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localChanlNo);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localBrchTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"Brch", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"Brch");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("Brch");
                                    }
                                

                                          if (localBrch==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("Brch cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localBrch);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localTermNoTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"TermNo", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"TermNo");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("TermNo");
                                    }
                                

                                          if (localTermNo==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("TermNo cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localTermNo);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localOperTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"Oper", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"Oper");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("Oper");
                                    }
                                

                                          if (localOper==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("Oper cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localOper);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localSendFileNameTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"SendFileName", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"SendFileName");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("SendFileName");
                                    }
                                

                                          if (localSendFileName==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("SendFileName cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localSendFileName);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localBeginRecTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"BeginRec", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"BeginRec");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("BeginRec");
                                    }
                                

                                          if (localBeginRec==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("BeginRec cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localBeginRec);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localMaxRecTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"MaxRec", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"MaxRec");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("MaxRec");
                                    }
                                

                                          if (localMaxRec==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("MaxRec cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localMaxRec));
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localFileHMacTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"FileHMac", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"FileHMac");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("FileHMac");
                                    }
                                

                                          if (localFileHMac==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("FileHMac cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localFileHMac);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localHMacTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"HMac", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"HMac");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("HMac");
                                    }
                                

                                          if (localHMac==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("HMac cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localHMac);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             }
                    xmlWriter.writeEndElement();
               

        }

         /**
          * Util method to write an attribute with the ns prefix
          */
          private void writeAttribute(java.lang.String prefix,java.lang.String namespace,java.lang.String attName,
                                      java.lang.String attValue,javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException{
              if (xmlWriter.getPrefix(namespace) == null) {
                       xmlWriter.writeNamespace(prefix, namespace);
                       xmlWriter.setPrefix(prefix, namespace);

              }

              xmlWriter.writeAttribute(namespace,attName,attValue);

         }

        /**
          * Util method to write an attribute without the ns prefix
          */
          private void writeAttribute(java.lang.String namespace,java.lang.String attName,
                                      java.lang.String attValue,javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException{
                if (namespace.equals(""))
              {
                  xmlWriter.writeAttribute(attName,attValue);
              }
              else
              {
                  registerPrefix(xmlWriter, namespace);
                  xmlWriter.writeAttribute(namespace,attName,attValue);
              }
          }


           /**
             * Util method to write an attribute without the ns prefix
             */
            private void writeQNameAttribute(java.lang.String namespace, java.lang.String attName,
                                             javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

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
         *  method to handle Qnames
         */

        private void writeQName(javax.xml.namespace.QName qname,
                                javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
            java.lang.String namespaceURI = qname.getNamespaceURI();
            if (namespaceURI != null) {
                java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
                if (prefix == null) {
                    prefix = generatePrefix(namespaceURI);
                    xmlWriter.writeNamespace(prefix, namespaceURI);
                    xmlWriter.setPrefix(prefix,namespaceURI);
                }

                if (prefix.trim().length() > 0){
                    xmlWriter.writeCharacters(prefix + ":" + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
                } else {
                    // i.e this is the default namespace
                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
                }

            } else {
                xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
            }
        }

        private void writeQNames(javax.xml.namespace.QName[] qnames,
                                 javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

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
                            xmlWriter.setPrefix(prefix,namespaceURI);
                        }

                        if (prefix.trim().length() > 0){
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
        *
        */
        public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName)
                    throws org.apache.axis2.databinding.ADBException{


        
                 java.util.ArrayList elementList = new java.util.ArrayList();
                 java.util.ArrayList attribList = new java.util.ArrayList();

                 if (localVersionNoTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "VersionNo"));
                                 
                                        if (localVersionNo != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localVersionNo));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("VersionNo cannot be null!!");
                                        }
                                    } if (localReqSysCodeTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "ReqSysCode"));
                                 
                                        if (localReqSysCode != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localReqSysCode));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("ReqSysCode cannot be null!!");
                                        }
                                    } if (localReqSecCodeTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "ReqSecCode"));
                                 
                                        if (localReqSecCode != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localReqSecCode));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("ReqSecCode cannot be null!!");
                                        }
                                    } if (localTxTypeTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "TxType"));
                                 
                                        if (localTxType != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localTxType));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("TxType cannot be null!!");
                                        }
                                    } if (localTxModeTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "TxMode"));
                                 
                                        if (localTxMode != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localTxMode));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("TxMode cannot be null!!");
                                        }
                                    } if (localTxCodeTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "TxCode"));
                                 
                                        if (localTxCode != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localTxCode));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("TxCode cannot be null!!");
                                        }
                                    } if (localReqDateTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "ReqDate"));
                                 
                                        if (localReqDate != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localReqDate));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("ReqDate cannot be null!!");
                                        }
                                    } if (localReqTimeTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "ReqTime"));
                                 
                                        if (localReqTime != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localReqTime));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("ReqTime cannot be null!!");
                                        }
                                    } if (localReqSeqNoTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "ReqSeqNo"));
                                 
                                        if (localReqSeqNo != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localReqSeqNo));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("ReqSeqNo cannot be null!!");
                                        }
                                    } if (localChanlNoTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "ChanlNo"));
                                 
                                        if (localChanlNo != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localChanlNo));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("ChanlNo cannot be null!!");
                                        }
                                    } if (localBrchTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "Brch"));
                                 
                                        if (localBrch != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localBrch));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("Brch cannot be null!!");
                                        }
                                    } if (localTermNoTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "TermNo"));
                                 
                                        if (localTermNo != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localTermNo));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("TermNo cannot be null!!");
                                        }
                                    } if (localOperTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "Oper"));
                                 
                                        if (localOper != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localOper));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("Oper cannot be null!!");
                                        }
                                    } if (localSendFileNameTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "SendFileName"));
                                 
                                        if (localSendFileName != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localSendFileName));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("SendFileName cannot be null!!");
                                        }
                                    } if (localBeginRecTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "BeginRec"));
                                 
                                        if (localBeginRec != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localBeginRec));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("BeginRec cannot be null!!");
                                        }
                                    } if (localMaxRecTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "MaxRec"));
                                 
                                        if (localMaxRec != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localMaxRec));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("MaxRec cannot be null!!");
                                        }
                                    } if (localFileHMacTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "FileHMac"));
                                 
                                        if (localFileHMac != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localFileHMac));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("FileHMac cannot be null!!");
                                        }
                                    } if (localHMacTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "HMac"));
                                 
                                        if (localHMac != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHMac));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("HMac cannot be null!!");
                                        }
                                    }

                return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName, elementList.toArray(), attribList.toArray());
            
            

        }

  

     /**
      *  Factory class that keeps the parse method
      */
    public static class Factory{

        
        

        /**
        * static method to create the object
        * Precondition:  If this object is an element, the current or next start element starts this object and any intervening reader events are ignorable
        *                If this object is not an element, it is a complex type and the reader is at the event just after the outer start element
        * Postcondition: If this object is an element, the reader is positioned at its end element
        *                If this object is a complex type, the reader is positioned at the end element of its outer element
        */
        public static FMT_SOAP_UTF8_RequestHeader parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            FMT_SOAP_UTF8_RequestHeader object =
                new FMT_SOAP_UTF8_RequestHeader();

            int event;
            java.lang.String nillableValue = null;
            java.lang.String prefix ="";
            java.lang.String namespaceuri ="";
            try {
                
                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                
                if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","type")!=null){
                  java.lang.String fullTypeName = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                        "type");
                  if (fullTypeName!=null){
                    java.lang.String nsPrefix = null;
                    if (fullTypeName.indexOf(":") > -1){
                        nsPrefix = fullTypeName.substring(0,fullTypeName.indexOf(":"));
                    }
                    nsPrefix = nsPrefix==null?"":nsPrefix;

                    java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":")+1);
                    
                            if (!"FMT_SOAP_UTF8_RequestHeader".equals(type)){
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                                return (FMT_SOAP_UTF8_RequestHeader)ExtensionMapper.getTypeObject(
                                     nsUri,type,reader);
                              }
                        

                  }

                }
                

                
                // Note all attributes that were handled. Used to differ normal attributes
                // from anyAttributes.
                java.util.Vector handledAttributes = new java.util.Vector();
                

                 
                    
                    reader.next();
                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","VersionNo").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setVersionNo(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","ReqSysCode").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setReqSysCode(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","ReqSecCode").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setReqSecCode(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","TxType").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setTxType(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","TxMode").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setTxMode(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","TxCode").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setTxCode(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","ReqDate").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setReqDate(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","ReqTime").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setReqTime(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","ReqSeqNo").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setReqSeqNo(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","ChanlNo").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setChanlNo(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","Brch").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setBrch(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","TermNo").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setTermNo(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","Oper").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setOper(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","SendFileName").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setSendFileName(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","BeginRec").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setBeginRec(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","MaxRec").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setMaxRec(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToInteger(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","FileHMac").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setFileHMac(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","HMac").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setHMac(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
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

        }//end of factory class

        

        }
           
          
        public static class ExtensionMapper{

          public static java.lang.Object getTypeObject(java.lang.String namespaceURI,
                                                       java.lang.String typeName,
                                                       javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{

              
                  if (
                  "http://www.adtec.com.cn".equals(namespaceURI) &&
                  "FMT_CRMS_SVR_S03601010ZX0001_IN".equals(typeName)){
                   
                            return  FMT_CRMS_SVR_S03601010ZX0001_IN.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://www.adtec.com.cn".equals(namespaceURI) &&
                  "FMT_SOAP_UTF8_ResponseHeader".equals(typeName)){
                   
                            return  FMT_SOAP_UTF8_ResponseHeader.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://www.adtec.com.cn".equals(namespaceURI) &&
                  "FMT_SOAP_UTF8_ResTranHeader".equals(typeName)){
                   
                            return  FMT_SOAP_UTF8_ResTranHeader.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://www.adtec.com.cn".equals(namespaceURI) &&
                  "FMT_SOAP_UTF8_RequestHeader".equals(typeName)){
                   
                            return  FMT_SOAP_UTF8_RequestHeader.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://www.adtec.com.cn".equals(namespaceURI) &&
                  "FMT_SOAP_UTF8_ReqTranHeader".equals(typeName)){
                   
                            return  FMT_SOAP_UTF8_ReqTranHeader.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://www.adtec.com.cn".equals(namespaceURI) &&
                  "FMT_CRMS_SVR_S03601010ZX0001_OUT".equals(typeName)){
                   
                            return  FMT_CRMS_SVR_S03601010ZX0001_OUT.Factory.parse(reader);
                        

                  }

              
             throw new org.apache.axis2.databinding.ADBException("Unsupported type " + namespaceURI + " " + typeName);
          }

        }
    

        public static class FMT_SOAP_UTF8_ReqTranHeader
        implements org.apache.axis2.databinding.ADBBean{
        /* This type was generated from the piece of schema that had
                name = FMT_SOAP_UTF8_ReqTranHeader
                Namespace URI = http://www.adtec.com.cn
                Namespace Prefix = ns1
                */
            

        private static java.lang.String generatePrefix(java.lang.String namespace) {
            if(namespace.equals("http://www.adtec.com.cn")){
                return "ns1";
            }
            return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
        }

        

                        /**
                        * field for HPinSeed
                        */

                        
                            protected java.lang.String localHPinSeed ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localHPinSeedTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getHPinSeed(){
                               return localHPinSeed;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param HPinSeed
                               */
                               public void setHPinSeed(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localHPinSeedTracker = true;
                                       } else {
                                          localHPinSeedTracker = false;
                                              
                                       }
                                   
                                            this.localHPinSeed=param;
                                    

                               }
                            

                        /**
                        * field for HOriChnl
                        */

                        
                            protected java.lang.String localHOriChnl ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localHOriChnlTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getHOriChnl(){
                               return localHOriChnl;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param HOriChnl
                               */
                               public void setHOriChnl(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localHOriChnlTracker = true;
                                       } else {
                                          localHOriChnlTracker = false;
                                              
                                       }
                                   
                                            this.localHOriChnl=param;
                                    

                               }
                            

                        /**
                        * field for HSecFlag
                        */

                        
                            protected java.lang.String localHSecFlag ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localHSecFlagTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getHSecFlag(){
                               return localHSecFlag;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param HSecFlag
                               */
                               public void setHSecFlag(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localHSecFlagTracker = true;
                                       } else {
                                          localHSecFlagTracker = false;
                                              
                                       }
                                   
                                            this.localHSecFlag=param;
                                    

                               }
                            

                        /**
                        * field for HPwdFlag
                        */

                        
                            protected java.lang.String localHPwdFlag ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localHPwdFlagTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getHPwdFlag(){
                               return localHPwdFlag;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param HPwdFlag
                               */
                               public void setHPwdFlag(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localHPwdFlagTracker = true;
                                       } else {
                                          localHPwdFlagTracker = false;
                                              
                                       }
                                   
                                            this.localHPwdFlag=param;
                                    

                               }
                            

                        /**
                        * field for HCombFlag
                        */

                        
                            protected java.lang.String localHCombFlag ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localHCombFlagTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getHCombFlag(){
                               return localHCombFlag;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param HCombFlag
                               */
                               public void setHCombFlag(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localHCombFlagTracker = true;
                                       } else {
                                          localHCombFlagTracker = false;
                                              
                                       }
                                   
                                            this.localHCombFlag=param;
                                    

                               }
                            

                        /**
                        * field for HSvcInfo
                        */

                        
                            protected java.lang.String localHSvcInfo ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localHSvcInfoTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getHSvcInfo(){
                               return localHSvcInfo;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param HSvcInfo
                               */
                               public void setHSvcInfo(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localHSvcInfoTracker = true;
                                       } else {
                                          localHSvcInfoTracker = false;
                                              
                                       }
                                   
                                            this.localHSvcInfo=param;
                                    

                               }
                            

                        /**
                        * field for HSecInfoVerNo
                        */

                        
                            protected java.lang.String localHSecInfoVerNo ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localHSecInfoVerNoTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getHSecInfoVerNo(){
                               return localHSecInfoVerNo;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param HSecInfoVerNo
                               */
                               public void setHSecInfoVerNo(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localHSecInfoVerNoTracker = true;
                                       } else {
                                          localHSecInfoVerNoTracker = false;
                                              
                                       }
                                   
                                            this.localHSecInfoVerNo=param;
                                    

                               }
                            

                        /**
                        * field for HSysChnl
                        */

                        
                            protected java.lang.String localHSysChnl ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localHSysChnlTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getHSysChnl(){
                               return localHSysChnl;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param HSysChnl
                               */
                               public void setHSysChnl(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localHSysChnlTracker = true;
                                       } else {
                                          localHSysChnlTracker = false;
                                              
                                       }
                                   
                                            this.localHSysChnl=param;
                                    

                               }
                            

                        /**
                        * field for HLegaObj
                        */

                        
                            protected java.lang.String localHLegaObj ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localHLegaObjTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getHLegaObj(){
                               return localHLegaObj;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param HLegaObj
                               */
                               public void setHLegaObj(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localHLegaObjTracker = true;
                                       } else {
                                          localHLegaObjTracker = false;
                                              
                                       }
                                   
                                            this.localHLegaObj=param;
                                    

                               }
                            

                        /**
                        * field for HMsgRefNo
                        */

                        
                            protected java.lang.String localHMsgRefNo ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localHMsgRefNoTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getHMsgRefNo(){
                               return localHMsgRefNo;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param HMsgRefNo
                               */
                               public void setHMsgRefNo(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localHMsgRefNoTracker = true;
                                       } else {
                                          localHMsgRefNoTracker = false;
                                              
                                       }
                                   
                                            this.localHMsgRefNo=param;
                                    

                               }
                            

                        /**
                        * field for HintOrigMark
                        */

                        
                            protected java.math.BigInteger localHintOrigMark ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localHintOrigMarkTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.math.BigInteger
                           */
                           public  java.math.BigInteger getHintOrigMark(){
                               return localHintOrigMark;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param HintOrigMark
                               */
                               public void setHintOrigMark(java.math.BigInteger param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localHintOrigMarkTracker = true;
                                       } else {
                                          localHintOrigMarkTracker = false;
                                              
                                       }
                                   
                                            this.localHintOrigMark=param;
                                    

                               }
                            

                        /**
                        * field for HTermNo
                        */

                        
                            protected java.lang.String localHTermNo ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localHTermNoTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getHTermNo(){
                               return localHTermNo;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param HTermNo
                               */
                               public void setHTermNo(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localHTermNoTracker = true;
                                       } else {
                                          localHTermNoTracker = false;
                                              
                                       }
                                   
                                            this.localHTermNo=param;
                                    

                               }
                            

                        /**
                        * field for HCityCd
                        */

                        
                            protected java.lang.String localHCityCd ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localHCityCdTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getHCityCd(){
                               return localHCityCd;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param HCityCd
                               */
                               public void setHCityCd(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localHCityCdTracker = true;
                                       } else {
                                          localHCityCdTracker = false;
                                              
                                       }
                                   
                                            this.localHCityCd=param;
                                    

                               }
                            

                        /**
                        * field for HCommitFlag
                        */

                        
                            protected java.lang.String localHCommitFlag ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localHCommitFlagTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getHCommitFlag(){
                               return localHCommitFlag;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param HCommitFlag
                               */
                               public void setHCommitFlag(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localHCommitFlagTracker = true;
                                       } else {
                                          localHCommitFlagTracker = false;
                                              
                                       }
                                   
                                            this.localHCommitFlag=param;
                                    

                               }
                            

                        /**
                        * field for HBrchNo
                        */

                        
                            protected java.lang.String localHBrchNo ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localHBrchNoTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getHBrchNo(){
                               return localHBrchNo;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param HBrchNo
                               */
                               public void setHBrchNo(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localHBrchNoTracker = true;
                                       } else {
                                          localHBrchNoTracker = false;
                                              
                                       }
                                   
                                            this.localHBrchNo=param;
                                    

                               }
                            

                        /**
                        * field for HUserID
                        */

                        
                            protected java.lang.String localHUserID ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localHUserIDTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getHUserID(){
                               return localHUserID;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param HUserID
                               */
                               public void setHUserID(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localHUserIDTracker = true;
                                       } else {
                                          localHUserIDTracker = false;
                                              
                                       }
                                   
                                            this.localHUserID=param;
                                    

                               }
                            

                        /**
                        * field for HTxnCd
                        */

                        
                            protected java.lang.String localHTxnCd ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localHTxnCdTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getHTxnCd(){
                               return localHTxnCd;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param HTxnCd
                               */
                               public void setHTxnCd(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localHTxnCdTracker = true;
                                       } else {
                                          localHTxnCdTracker = false;
                                              
                                       }
                                   
                                            this.localHTxnCd=param;
                                    

                               }
                            

                        /**
                        * field for HTxnMod
                        */

                        
                            protected java.lang.String localHTxnMod ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localHTxnModTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getHTxnMod(){
                               return localHTxnMod;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param HTxnMod
                               */
                               public void setHTxnMod(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localHTxnModTracker = true;
                                       } else {
                                          localHTxnModTracker = false;
                                              
                                       }
                                   
                                            this.localHTxnMod=param;
                                    

                               }
                            

                        /**
                        * field for HReserveLen
                        */

                        
                            protected java.lang.String localHReserveLen ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localHReserveLenTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getHReserveLen(){
                               return localHReserveLen;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param HReserveLen
                               */
                               public void setHReserveLen(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localHReserveLenTracker = true;
                                       } else {
                                          localHReserveLenTracker = false;
                                              
                                       }
                                   
                                            this.localHReserveLen=param;
                                    

                               }
                            

                        /**
                        * field for HSenderSvcCd
                        */

                        
                            protected java.lang.String localHSenderSvcCd ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localHSenderSvcCdTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getHSenderSvcCd(){
                               return localHSenderSvcCd;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param HSenderSvcCd
                               */
                               public void setHSenderSvcCd(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localHSenderSvcCdTracker = true;
                                       } else {
                                          localHSenderSvcCdTracker = false;
                                              
                                       }
                                   
                                            this.localHSenderSvcCd=param;
                                    

                               }
                            

                        /**
                        * field for HSenderSeq
                        */

                        
                            protected java.lang.String localHSenderSeq ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localHSenderSeqTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getHSenderSeq(){
                               return localHSenderSeq;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param HSenderSeq
                               */
                               public void setHSenderSeq(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localHSenderSeqTracker = true;
                                       } else {
                                          localHSenderSeqTracker = false;
                                              
                                       }
                                   
                                            this.localHSenderSeq=param;
                                    

                               }
                            

                        /**
                        * field for HSenderDate
                        */

                        
                            protected java.lang.String localHSenderDate ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localHSenderDateTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getHSenderDate(){
                               return localHSenderDate;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param HSenderDate
                               */
                               public void setHSenderDate(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localHSenderDateTracker = true;
                                       } else {
                                          localHSenderDateTracker = false;
                                              
                                       }
                                   
                                            this.localHSenderDate=param;
                                    

                               }
                            

                        /**
                        * field for HAuthUserID
                        */

                        
                            protected java.lang.String localHAuthUserID ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localHAuthUserIDTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getHAuthUserID(){
                               return localHAuthUserID;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param HAuthUserID
                               */
                               public void setHAuthUserID(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localHAuthUserIDTracker = true;
                                       } else {
                                          localHAuthUserIDTracker = false;
                                              
                                       }
                                   
                                            this.localHAuthUserID=param;
                                    

                               }
                            

                        /**
                        * field for HAuthVerfInfo
                        */

                        
                            protected java.lang.String localHAuthVerfInfo ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localHAuthVerfInfoTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getHAuthVerfInfo(){
                               return localHAuthVerfInfo;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param HAuthVerfInfo
                               */
                               public void setHAuthVerfInfo(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localHAuthVerfInfoTracker = true;
                                       } else {
                                          localHAuthVerfInfoTracker = false;
                                              
                                       }
                                   
                                            this.localHAuthVerfInfo=param;
                                    

                               }
                            

                        /**
                        * field for HAuthFlag
                        */

                        
                            protected java.lang.String localHAuthFlag ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localHAuthFlagTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getHAuthFlag(){
                               return localHAuthFlag;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param HAuthFlag
                               */
                               public void setHAuthFlag(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localHAuthFlagTracker = true;
                                       } else {
                                          localHAuthFlagTracker = false;
                                              
                                       }
                                   
                                            this.localHAuthFlag=param;
                                    

                               }
                            

                        /**
                        * field for HRefSeq
                        */

                        
                            protected java.lang.String localHRefSeq ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localHRefSeqTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getHRefSeq(){
                               return localHRefSeq;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param HRefSeq
                               */
                               public void setHRefSeq(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localHRefSeqTracker = true;
                                       } else {
                                          localHRefSeqTracker = false;
                                              
                                       }
                                   
                                            this.localHRefSeq=param;
                                    

                               }
                            

                        /**
                        * field for HAuthSeri
                        */

                        
                            protected java.lang.String localHAuthSeri ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localHAuthSeriTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getHAuthSeri(){
                               return localHAuthSeri;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param HAuthSeri
                               */
                               public void setHAuthSeri(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localHAuthSeriTracker = true;
                                       } else {
                                          localHAuthSeriTracker = false;
                                              
                                       }
                                   
                                            this.localHAuthSeri=param;
                                    

                               }
                            

                        /**
                        * field for HHostSeq
                        */

                        
                            protected java.lang.String localHHostSeq ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localHHostSeqTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getHHostSeq(){
                               return localHHostSeq;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param HHostSeq
                               */
                               public void setHHostSeq(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localHHostSeqTracker = true;
                                       } else {
                                          localHHostSeqTracker = false;
                                              
                                       }
                                   
                                            this.localHHostSeq=param;
                                    

                               }
                            

                        /**
                        * field for HRefDt
                        */

                        
                            protected java.lang.String localHRefDt ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localHRefDtTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getHRefDt(){
                               return localHRefDt;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param HRefDt
                               */
                               public void setHRefDt(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localHRefDtTracker = true;
                                       } else {
                                          localHRefDtTracker = false;
                                              
                                       }
                                   
                                            this.localHRefDt=param;
                                    

                               }
                            

                        /**
                        * field for HSvcVer
                        */

                        
                            protected java.lang.String localHSvcVer ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localHSvcVerTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getHSvcVer(){
                               return localHSvcVer;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param HSvcVer
                               */
                               public void setHSvcVer(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localHSvcVerTracker = true;
                                       } else {
                                          localHSvcVerTracker = false;
                                              
                                       }
                                   
                                            this.localHSvcVer=param;
                                    

                               }
                            

                        /**
                        * field for HreserveMsg
                        */

                        
                            protected java.lang.String localHreserveMsg ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localHreserveMsgTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getHreserveMsg(){
                               return localHreserveMsg;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param HreserveMsg
                               */
                               public void setHreserveMsg(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localHreserveMsgTracker = true;
                                       } else {
                                          localHreserveMsgTracker = false;
                                              
                                       }
                                   
                                            this.localHreserveMsg=param;
                                    

                               }
                            

     /**
     * isReaderMTOMAware
     * @return true if the reader supports MTOM
     */
   public static boolean isReaderMTOMAware(javax.xml.stream.XMLStreamReader reader) {
        boolean isReaderMTOMAware = false;
        
        try{
          isReaderMTOMAware = java.lang.Boolean.TRUE.equals(reader.getProperty(org.apache.axiom.om.OMConstants.IS_DATA_HANDLERS_AWARE));
        }catch(java.lang.IllegalArgumentException e){
          isReaderMTOMAware = false;
        }
        return isReaderMTOMAware;
   }
     
     
        /**
        *
        * @param parentQName
        * @param factory
        * @return org.apache.axiom.om.OMElement
        */
       public org.apache.axiom.om.OMElement getOMElement (
               final javax.xml.namespace.QName parentQName,
               final org.apache.axiom.om.OMFactory factory) throws org.apache.axis2.databinding.ADBException{


        
               org.apache.axiom.om.OMDataSource dataSource =
                       new org.apache.axis2.databinding.ADBDataSource(this,parentQName){

                 public void serialize(org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
                       FMT_SOAP_UTF8_ReqTranHeader.this.serialize(parentQName,factory,xmlWriter);
                 }
               };
               return new org.apache.axiom.om.impl.llom.OMSourcedElementImpl(
               parentQName,factory,dataSource);
            
       }



         public void serialize(final javax.xml.namespace.QName parentQName,
                               final org.apache.axiom.om.OMFactory factory,
                               org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException{
            
                


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
                 if (localHPinSeedTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"HPinSeed", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"HPinSeed");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("HPinSeed");
                                    }
                                

                                          if (localHPinSeed==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("HPinSeed cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localHPinSeed);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localHOriChnlTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"HOriChnl", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"HOriChnl");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("HOriChnl");
                                    }
                                

                                          if (localHOriChnl==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("HOriChnl cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localHOriChnl);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localHSecFlagTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"HSecFlag", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"HSecFlag");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("HSecFlag");
                                    }
                                

                                          if (localHSecFlag==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("HSecFlag cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localHSecFlag);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localHPwdFlagTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"HPwdFlag", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"HPwdFlag");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("HPwdFlag");
                                    }
                                

                                          if (localHPwdFlag==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("HPwdFlag cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localHPwdFlag);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localHCombFlagTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"HCombFlag", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"HCombFlag");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("HCombFlag");
                                    }
                                

                                          if (localHCombFlag==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("HCombFlag cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localHCombFlag);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localHSvcInfoTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"HSvcInfo", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"HSvcInfo");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("HSvcInfo");
                                    }
                                

                                          if (localHSvcInfo==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("HSvcInfo cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localHSvcInfo);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localHSecInfoVerNoTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"HSecInfoVerNo", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"HSecInfoVerNo");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("HSecInfoVerNo");
                                    }
                                

                                          if (localHSecInfoVerNo==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("HSecInfoVerNo cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localHSecInfoVerNo);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localHSysChnlTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"HSysChnl", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"HSysChnl");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("HSysChnl");
                                    }
                                

                                          if (localHSysChnl==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("HSysChnl cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localHSysChnl);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localHLegaObjTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"HLegaObj", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"HLegaObj");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("HLegaObj");
                                    }
                                

                                          if (localHLegaObj==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("HLegaObj cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localHLegaObj);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localHMsgRefNoTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"HMsgRefNo", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"HMsgRefNo");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("HMsgRefNo");
                                    }
                                

                                          if (localHMsgRefNo==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("HMsgRefNo cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localHMsgRefNo);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localHintOrigMarkTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"HintOrigMark", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"HintOrigMark");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("HintOrigMark");
                                    }
                                

                                          if (localHintOrigMark==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("HintOrigMark cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHintOrigMark));
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localHTermNoTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"HTermNo", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"HTermNo");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("HTermNo");
                                    }
                                

                                          if (localHTermNo==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("HTermNo cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localHTermNo);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localHCityCdTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"HCityCd", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"HCityCd");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("HCityCd");
                                    }
                                

                                          if (localHCityCd==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("HCityCd cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localHCityCd);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localHCommitFlagTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"HCommitFlag", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"HCommitFlag");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("HCommitFlag");
                                    }
                                

                                          if (localHCommitFlag==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("HCommitFlag cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localHCommitFlag);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localHBrchNoTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"HBrchNo", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"HBrchNo");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("HBrchNo");
                                    }
                                

                                          if (localHBrchNo==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("HBrchNo cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localHBrchNo);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localHUserIDTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"HUserID", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"HUserID");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("HUserID");
                                    }
                                

                                          if (localHUserID==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("HUserID cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localHUserID);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localHTxnCdTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"HTxnCd", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"HTxnCd");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("HTxnCd");
                                    }
                                

                                          if (localHTxnCd==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("HTxnCd cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localHTxnCd);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localHTxnModTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"HTxnMod", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"HTxnMod");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("HTxnMod");
                                    }
                                

                                          if (localHTxnMod==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("HTxnMod cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localHTxnMod);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localHReserveLenTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"HReserveLen", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"HReserveLen");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("HReserveLen");
                                    }
                                

                                          if (localHReserveLen==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("HReserveLen cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localHReserveLen);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localHSenderSvcCdTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"HSenderSvcCd", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"HSenderSvcCd");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("HSenderSvcCd");
                                    }
                                

                                          if (localHSenderSvcCd==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("HSenderSvcCd cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localHSenderSvcCd);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localHSenderSeqTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"HSenderSeq", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"HSenderSeq");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("HSenderSeq");
                                    }
                                

                                          if (localHSenderSeq==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("HSenderSeq cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localHSenderSeq);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localHSenderDateTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"HSenderDate", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"HSenderDate");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("HSenderDate");
                                    }
                                

                                          if (localHSenderDate==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("HSenderDate cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localHSenderDate);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localHAuthUserIDTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"HAuthUserID", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"HAuthUserID");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("HAuthUserID");
                                    }
                                

                                          if (localHAuthUserID==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("HAuthUserID cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localHAuthUserID);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localHAuthVerfInfoTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"HAuthVerfInfo", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"HAuthVerfInfo");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("HAuthVerfInfo");
                                    }
                                

                                          if (localHAuthVerfInfo==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("HAuthVerfInfo cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localHAuthVerfInfo);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localHAuthFlagTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"HAuthFlag", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"HAuthFlag");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("HAuthFlag");
                                    }
                                

                                          if (localHAuthFlag==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("HAuthFlag cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localHAuthFlag);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localHRefSeqTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"HRefSeq", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"HRefSeq");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("HRefSeq");
                                    }
                                

                                          if (localHRefSeq==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("HRefSeq cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localHRefSeq);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localHAuthSeriTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"HAuthSeri", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"HAuthSeri");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("HAuthSeri");
                                    }
                                

                                          if (localHAuthSeri==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("HAuthSeri cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localHAuthSeri);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localHHostSeqTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"HHostSeq", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"HHostSeq");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("HHostSeq");
                                    }
                                

                                          if (localHHostSeq==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("HHostSeq cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localHHostSeq);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localHRefDtTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"HRefDt", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"HRefDt");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("HRefDt");
                                    }
                                

                                          if (localHRefDt==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("HRefDt cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localHRefDt);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localHSvcVerTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"HSvcVer", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"HSvcVer");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("HSvcVer");
                                    }
                                

                                          if (localHSvcVer==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("HSvcVer cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localHSvcVer);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localHreserveMsgTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"HreserveMsg", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"HreserveMsg");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("HreserveMsg");
                                    }
                                

                                          if (localHreserveMsg==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("HreserveMsg cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localHreserveMsg);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             }
                    xmlWriter.writeEndElement();
               

        }

         /**
          * Util method to write an attribute with the ns prefix
          */
          private void writeAttribute(java.lang.String prefix,java.lang.String namespace,java.lang.String attName,
                                      java.lang.String attValue,javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException{
              if (xmlWriter.getPrefix(namespace) == null) {
                       xmlWriter.writeNamespace(prefix, namespace);
                       xmlWriter.setPrefix(prefix, namespace);

              }

              xmlWriter.writeAttribute(namespace,attName,attValue);

         }

        /**
          * Util method to write an attribute without the ns prefix
          */
          private void writeAttribute(java.lang.String namespace,java.lang.String attName,
                                      java.lang.String attValue,javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException{
                if (namespace.equals(""))
              {
                  xmlWriter.writeAttribute(attName,attValue);
              }
              else
              {
                  registerPrefix(xmlWriter, namespace);
                  xmlWriter.writeAttribute(namespace,attName,attValue);
              }
          }


           /**
             * Util method to write an attribute without the ns prefix
             */
            private void writeQNameAttribute(java.lang.String namespace, java.lang.String attName,
                                             javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

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
         *  method to handle Qnames
         */

        private void writeQName(javax.xml.namespace.QName qname,
                                javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
            java.lang.String namespaceURI = qname.getNamespaceURI();
            if (namespaceURI != null) {
                java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
                if (prefix == null) {
                    prefix = generatePrefix(namespaceURI);
                    xmlWriter.writeNamespace(prefix, namespaceURI);
                    xmlWriter.setPrefix(prefix,namespaceURI);
                }

                if (prefix.trim().length() > 0){
                    xmlWriter.writeCharacters(prefix + ":" + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
                } else {
                    // i.e this is the default namespace
                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
                }

            } else {
                xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
            }
        }

        private void writeQNames(javax.xml.namespace.QName[] qnames,
                                 javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

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
                            xmlWriter.setPrefix(prefix,namespaceURI);
                        }

                        if (prefix.trim().length() > 0){
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
        *
        */
        public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName)
                    throws org.apache.axis2.databinding.ADBException{


        
                 java.util.ArrayList elementList = new java.util.ArrayList();
                 java.util.ArrayList attribList = new java.util.ArrayList();

                 if (localHPinSeedTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "HPinSeed"));
                                 
                                        if (localHPinSeed != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHPinSeed));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("HPinSeed cannot be null!!");
                                        }
                                    } if (localHOriChnlTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "HOriChnl"));
                                 
                                        if (localHOriChnl != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHOriChnl));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("HOriChnl cannot be null!!");
                                        }
                                    } if (localHSecFlagTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "HSecFlag"));
                                 
                                        if (localHSecFlag != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHSecFlag));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("HSecFlag cannot be null!!");
                                        }
                                    } if (localHPwdFlagTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "HPwdFlag"));
                                 
                                        if (localHPwdFlag != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHPwdFlag));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("HPwdFlag cannot be null!!");
                                        }
                                    } if (localHCombFlagTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "HCombFlag"));
                                 
                                        if (localHCombFlag != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHCombFlag));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("HCombFlag cannot be null!!");
                                        }
                                    } if (localHSvcInfoTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "HSvcInfo"));
                                 
                                        if (localHSvcInfo != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHSvcInfo));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("HSvcInfo cannot be null!!");
                                        }
                                    } if (localHSecInfoVerNoTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "HSecInfoVerNo"));
                                 
                                        if (localHSecInfoVerNo != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHSecInfoVerNo));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("HSecInfoVerNo cannot be null!!");
                                        }
                                    } if (localHSysChnlTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "HSysChnl"));
                                 
                                        if (localHSysChnl != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHSysChnl));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("HSysChnl cannot be null!!");
                                        }
                                    } if (localHLegaObjTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "HLegaObj"));
                                 
                                        if (localHLegaObj != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHLegaObj));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("HLegaObj cannot be null!!");
                                        }
                                    } if (localHMsgRefNoTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "HMsgRefNo"));
                                 
                                        if (localHMsgRefNo != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHMsgRefNo));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("HMsgRefNo cannot be null!!");
                                        }
                                    } if (localHintOrigMarkTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "HintOrigMark"));
                                 
                                        if (localHintOrigMark != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHintOrigMark));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("HintOrigMark cannot be null!!");
                                        }
                                    } if (localHTermNoTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "HTermNo"));
                                 
                                        if (localHTermNo != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHTermNo));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("HTermNo cannot be null!!");
                                        }
                                    } if (localHCityCdTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "HCityCd"));
                                 
                                        if (localHCityCd != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHCityCd));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("HCityCd cannot be null!!");
                                        }
                                    } if (localHCommitFlagTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "HCommitFlag"));
                                 
                                        if (localHCommitFlag != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHCommitFlag));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("HCommitFlag cannot be null!!");
                                        }
                                    } if (localHBrchNoTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "HBrchNo"));
                                 
                                        if (localHBrchNo != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHBrchNo));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("HBrchNo cannot be null!!");
                                        }
                                    } if (localHUserIDTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "HUserID"));
                                 
                                        if (localHUserID != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHUserID));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("HUserID cannot be null!!");
                                        }
                                    } if (localHTxnCdTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "HTxnCd"));
                                 
                                        if (localHTxnCd != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHTxnCd));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("HTxnCd cannot be null!!");
                                        }
                                    } if (localHTxnModTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "HTxnMod"));
                                 
                                        if (localHTxnMod != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHTxnMod));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("HTxnMod cannot be null!!");
                                        }
                                    } if (localHReserveLenTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "HReserveLen"));
                                 
                                        if (localHReserveLen != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHReserveLen));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("HReserveLen cannot be null!!");
                                        }
                                    } if (localHSenderSvcCdTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "HSenderSvcCd"));
                                 
                                        if (localHSenderSvcCd != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHSenderSvcCd));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("HSenderSvcCd cannot be null!!");
                                        }
                                    } if (localHSenderSeqTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "HSenderSeq"));
                                 
                                        if (localHSenderSeq != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHSenderSeq));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("HSenderSeq cannot be null!!");
                                        }
                                    } if (localHSenderDateTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "HSenderDate"));
                                 
                                        if (localHSenderDate != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHSenderDate));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("HSenderDate cannot be null!!");
                                        }
                                    } if (localHAuthUserIDTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "HAuthUserID"));
                                 
                                        if (localHAuthUserID != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHAuthUserID));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("HAuthUserID cannot be null!!");
                                        }
                                    } if (localHAuthVerfInfoTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "HAuthVerfInfo"));
                                 
                                        if (localHAuthVerfInfo != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHAuthVerfInfo));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("HAuthVerfInfo cannot be null!!");
                                        }
                                    } if (localHAuthFlagTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "HAuthFlag"));
                                 
                                        if (localHAuthFlag != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHAuthFlag));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("HAuthFlag cannot be null!!");
                                        }
                                    } if (localHRefSeqTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "HRefSeq"));
                                 
                                        if (localHRefSeq != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHRefSeq));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("HRefSeq cannot be null!!");
                                        }
                                    } if (localHAuthSeriTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "HAuthSeri"));
                                 
                                        if (localHAuthSeri != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHAuthSeri));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("HAuthSeri cannot be null!!");
                                        }
                                    } if (localHHostSeqTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "HHostSeq"));
                                 
                                        if (localHHostSeq != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHHostSeq));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("HHostSeq cannot be null!!");
                                        }
                                    } if (localHRefDtTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "HRefDt"));
                                 
                                        if (localHRefDt != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHRefDt));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("HRefDt cannot be null!!");
                                        }
                                    } if (localHSvcVerTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "HSvcVer"));
                                 
                                        if (localHSvcVer != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHSvcVer));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("HSvcVer cannot be null!!");
                                        }
                                    } if (localHreserveMsgTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "HreserveMsg"));
                                 
                                        if (localHreserveMsg != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHreserveMsg));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("HreserveMsg cannot be null!!");
                                        }
                                    }

                return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName, elementList.toArray(), attribList.toArray());
            
            

        }

  

     /**
      *  Factory class that keeps the parse method
      */
    public static class Factory{

        
        

        /**
        * static method to create the object
        * Precondition:  If this object is an element, the current or next start element starts this object and any intervening reader events are ignorable
        *                If this object is not an element, it is a complex type and the reader is at the event just after the outer start element
        * Postcondition: If this object is an element, the reader is positioned at its end element
        *                If this object is a complex type, the reader is positioned at the end element of its outer element
        */
        public static FMT_SOAP_UTF8_ReqTranHeader parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            FMT_SOAP_UTF8_ReqTranHeader object =
                new FMT_SOAP_UTF8_ReqTranHeader();

            int event;
            java.lang.String nillableValue = null;
            java.lang.String prefix ="";
            java.lang.String namespaceuri ="";
            try {
                
                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                
                if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","type")!=null){
                  java.lang.String fullTypeName = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                        "type");
                  if (fullTypeName!=null){
                    java.lang.String nsPrefix = null;
                    if (fullTypeName.indexOf(":") > -1){
                        nsPrefix = fullTypeName.substring(0,fullTypeName.indexOf(":"));
                    }
                    nsPrefix = nsPrefix==null?"":nsPrefix;

                    java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":")+1);
                    
                            if (!"FMT_SOAP_UTF8_ReqTranHeader".equals(type)){
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                                return (FMT_SOAP_UTF8_ReqTranHeader)ExtensionMapper.getTypeObject(
                                     nsUri,type,reader);
                              }
                        

                  }

                }
                

                
                // Note all attributes that were handled. Used to differ normal attributes
                // from anyAttributes.
                java.util.Vector handledAttributes = new java.util.Vector();
                

                 
                    
                    reader.next();
                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","HPinSeed").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setHPinSeed(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","HOriChnl").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setHOriChnl(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","HSecFlag").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setHSecFlag(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","HPwdFlag").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setHPwdFlag(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","HCombFlag").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setHCombFlag(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","HSvcInfo").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setHSvcInfo(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","HSecInfoVerNo").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setHSecInfoVerNo(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","HSysChnl").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setHSysChnl(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","HLegaObj").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setHLegaObj(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","HMsgRefNo").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setHMsgRefNo(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","HintOrigMark").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setHintOrigMark(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToInteger(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","HTermNo").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setHTermNo(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","HCityCd").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setHCityCd(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","HCommitFlag").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setHCommitFlag(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","HBrchNo").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setHBrchNo(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","HUserID").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setHUserID(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","HTxnCd").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setHTxnCd(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","HTxnMod").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setHTxnMod(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","HReserveLen").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setHReserveLen(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","HSenderSvcCd").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setHSenderSvcCd(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","HSenderSeq").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setHSenderSeq(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","HSenderDate").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setHSenderDate(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","HAuthUserID").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setHAuthUserID(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","HAuthVerfInfo").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setHAuthVerfInfo(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","HAuthFlag").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setHAuthFlag(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","HRefSeq").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setHRefSeq(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","HAuthSeri").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setHAuthSeri(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","HHostSeq").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setHHostSeq(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","HRefDt").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setHRefDt(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","HSvcVer").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setHSvcVer(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","HreserveMsg").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setHreserveMsg(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
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

        }//end of factory class

        

        }
           
          

        public static class S03601010ZX0001Response
        implements org.apache.axis2.databinding.ADBBean{
        
                public static final javax.xml.namespace.QName MY_QNAME = new javax.xml.namespace.QName(
                "http://www.adtec.com.cn",
                "S03601010ZX0001Response",
                "ns1");

            

        private static java.lang.String generatePrefix(java.lang.String namespace) {
            if(namespace.equals("http://www.adtec.com.cn")){
                return "ns1";
            }
            return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
        }

        

                        /**
                        * field for ResTranHeader
                        */

                        
                            protected FMT_SOAP_UTF8_ResTranHeader localResTranHeader ;
                        

                           /**
                           * Auto generated getter method
                           * @return FMT_SOAP_UTF8_ResTranHeader
                           */
                           public  FMT_SOAP_UTF8_ResTranHeader getResTranHeader(){
                               return localResTranHeader;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param ResTranHeader
                               */
                               public void setResTranHeader(FMT_SOAP_UTF8_ResTranHeader param){
                            
                                            this.localResTranHeader=param;
                                    

                               }
                            

                        /**
                        * field for ResponseBody
                        */

                        
                            protected FMT_CRMS_SVR_S03601010ZX0001_OUT localResponseBody ;
                        

                           /**
                           * Auto generated getter method
                           * @return FMT_CRMS_SVR_S03601010ZX0001_OUT
                           */
                           public  FMT_CRMS_SVR_S03601010ZX0001_OUT getResponseBody(){
                               return localResponseBody;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param ResponseBody
                               */
                               public void setResponseBody(FMT_CRMS_SVR_S03601010ZX0001_OUT param){
                            
                                            this.localResponseBody=param;
                                    

                               }
                            

                        /**
                        * field for ResponseHeader
                        */

                        
                            protected FMT_SOAP_UTF8_ResponseHeader localResponseHeader ;
                        

                           /**
                           * Auto generated getter method
                           * @return FMT_SOAP_UTF8_ResponseHeader
                           */
                           public  FMT_SOAP_UTF8_ResponseHeader getResponseHeader(){
                               return localResponseHeader;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param ResponseHeader
                               */
                               public void setResponseHeader(FMT_SOAP_UTF8_ResponseHeader param){
                            
                                            this.localResponseHeader=param;
                                    

                               }
                            

     /**
     * isReaderMTOMAware
     * @return true if the reader supports MTOM
     */
   public static boolean isReaderMTOMAware(javax.xml.stream.XMLStreamReader reader) {
        boolean isReaderMTOMAware = false;
        
        try{
          isReaderMTOMAware = java.lang.Boolean.TRUE.equals(reader.getProperty(org.apache.axiom.om.OMConstants.IS_DATA_HANDLERS_AWARE));
        }catch(java.lang.IllegalArgumentException e){
          isReaderMTOMAware = false;
        }
        return isReaderMTOMAware;
   }
     
     
        /**
        *
        * @param parentQName
        * @param factory
        * @return org.apache.axiom.om.OMElement
        */
       public org.apache.axiom.om.OMElement getOMElement (
               final javax.xml.namespace.QName parentQName,
               final org.apache.axiom.om.OMFactory factory) throws org.apache.axis2.databinding.ADBException{


        
                org.apache.axiom.om.OMDataSource dataSource =
                       new org.apache.axis2.databinding.ADBDataSource(this,MY_QNAME){

                 public void serialize(org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
                       S03601010ZX0001Response.this.serialize(MY_QNAME,factory,xmlWriter);
                 }
               };
               return new org.apache.axiom.om.impl.llom.OMSourcedElementImpl(
               MY_QNAME,factory,dataSource);
            
       }



         public void serialize(final javax.xml.namespace.QName parentQName,
                               final org.apache.axiom.om.OMFactory factory,
                               org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException{
            
                


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
                
                                            if (localResTranHeader==null){
                                                 throw new org.apache.axis2.databinding.ADBException("ResTranHeader cannot be null!!");
                                            }
                                           localResTranHeader.serialize(new javax.xml.namespace.QName("","ResTranHeader"),
                                               factory,xmlWriter);
                                        
                                            if (localResponseBody==null){
                                                 throw new org.apache.axis2.databinding.ADBException("ResponseBody cannot be null!!");
                                            }
                                           localResponseBody.serialize(new javax.xml.namespace.QName("","ResponseBody"),
                                               factory,xmlWriter);
                                        
                                            if (localResponseHeader==null){
                                                 throw new org.apache.axis2.databinding.ADBException("ResponseHeader cannot be null!!");
                                            }
                                           localResponseHeader.serialize(new javax.xml.namespace.QName("","ResponseHeader"),
                                               factory,xmlWriter);
                                        
                    xmlWriter.writeEndElement();
               

        }

         /**
          * Util method to write an attribute with the ns prefix
          */
          private void writeAttribute(java.lang.String prefix,java.lang.String namespace,java.lang.String attName,
                                      java.lang.String attValue,javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException{
              if (xmlWriter.getPrefix(namespace) == null) {
                       xmlWriter.writeNamespace(prefix, namespace);
                       xmlWriter.setPrefix(prefix, namespace);

              }

              xmlWriter.writeAttribute(namespace,attName,attValue);

         }

        /**
          * Util method to write an attribute without the ns prefix
          */
          private void writeAttribute(java.lang.String namespace,java.lang.String attName,
                                      java.lang.String attValue,javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException{
                if (namespace.equals(""))
              {
                  xmlWriter.writeAttribute(attName,attValue);
              }
              else
              {
                  registerPrefix(xmlWriter, namespace);
                  xmlWriter.writeAttribute(namespace,attName,attValue);
              }
          }


           /**
             * Util method to write an attribute without the ns prefix
             */
            private void writeQNameAttribute(java.lang.String namespace, java.lang.String attName,
                                             javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

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
         *  method to handle Qnames
         */

        private void writeQName(javax.xml.namespace.QName qname,
                                javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
            java.lang.String namespaceURI = qname.getNamespaceURI();
            if (namespaceURI != null) {
                java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
                if (prefix == null) {
                    prefix = generatePrefix(namespaceURI);
                    xmlWriter.writeNamespace(prefix, namespaceURI);
                    xmlWriter.setPrefix(prefix,namespaceURI);
                }

                if (prefix.trim().length() > 0){
                    xmlWriter.writeCharacters(prefix + ":" + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
                } else {
                    // i.e this is the default namespace
                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
                }

            } else {
                xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
            }
        }

        private void writeQNames(javax.xml.namespace.QName[] qnames,
                                 javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

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
                            xmlWriter.setPrefix(prefix,namespaceURI);
                        }

                        if (prefix.trim().length() > 0){
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
        *
        */
        public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName)
                    throws org.apache.axis2.databinding.ADBException{


        
                 java.util.ArrayList elementList = new java.util.ArrayList();
                 java.util.ArrayList attribList = new java.util.ArrayList();

                
                            elementList.add(new javax.xml.namespace.QName("",
                                                                      "ResTranHeader"));
                            
                            
                                    if (localResTranHeader==null){
                                         throw new org.apache.axis2.databinding.ADBException("ResTranHeader cannot be null!!");
                                    }
                                    elementList.add(localResTranHeader);
                                
                            elementList.add(new javax.xml.namespace.QName("",
                                                                      "ResponseBody"));
                            
                            
                                    if (localResponseBody==null){
                                         throw new org.apache.axis2.databinding.ADBException("ResponseBody cannot be null!!");
                                    }
                                    elementList.add(localResponseBody);
                                
                            elementList.add(new javax.xml.namespace.QName("",
                                                                      "ResponseHeader"));
                            
                            
                                    if (localResponseHeader==null){
                                         throw new org.apache.axis2.databinding.ADBException("ResponseHeader cannot be null!!");
                                    }
                                    elementList.add(localResponseHeader);
                                

                return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName, elementList.toArray(), attribList.toArray());
            
            

        }

  

     /**
      *  Factory class that keeps the parse method
      */
    public static class Factory{

        
        

        /**
        * static method to create the object
        * Precondition:  If this object is an element, the current or next start element starts this object and any intervening reader events are ignorable
        *                If this object is not an element, it is a complex type and the reader is at the event just after the outer start element
        * Postcondition: If this object is an element, the reader is positioned at its end element
        *                If this object is a complex type, the reader is positioned at the end element of its outer element
        */
        public static S03601010ZX0001Response parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            S03601010ZX0001Response object =
                new S03601010ZX0001Response();

            int event;
            java.lang.String nillableValue = null;
            java.lang.String prefix ="";
            java.lang.String namespaceuri ="";
            try {
                
                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                
                if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","type")!=null){
                  java.lang.String fullTypeName = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                        "type");
                  if (fullTypeName!=null){
                    java.lang.String nsPrefix = null;
                    if (fullTypeName.indexOf(":") > -1){
                        nsPrefix = fullTypeName.substring(0,fullTypeName.indexOf(":"));
                    }
                    nsPrefix = nsPrefix==null?"":nsPrefix;

                    java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":")+1);
                    
                            if (!"S03601010ZX0001Response".equals(type)){
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                                return (S03601010ZX0001Response)ExtensionMapper.getTypeObject(
                                     nsUri,type,reader);
                              }
                        

                  }

                }
                

                
                // Note all attributes that were handled. Used to differ normal attributes
                // from anyAttributes.
                java.util.Vector handledAttributes = new java.util.Vector();
                

                 
                    
                    reader.next();
                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                if (reader.isStartElement() && new javax.xml.namespace.QName("","ResponseHeader").equals(reader.getName())){
                                  
                                  object.setResponseHeader(FMT_SOAP_UTF8_ResponseHeader.Factory.parse(reader));
                                
                                  reader.next();
                              
                        } // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                   
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","ResTranHeader").equals(reader.getName())){
                                
                                        object.setResTranHeader(FMT_SOAP_UTF8_ResTranHeader.Factory.parse(reader));
                                      
                                        reader.next();
                                    
                              }      // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                     if (reader.isStartElement() && new javax.xml.namespace.QName("","ResponseBody").equals(reader.getName())){
                                  
                                  object.setResponseBody(FMT_CRMS_SVR_S03601010ZX0001_OUT.Factory.parse(reader));
                                
                                  reader.next();
                              
                        }    // End of if for expected property start element
                                
                                else{
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

        }//end of factory class

        

        }
           
          

        public static class FMT_CRMS_SVR_S03601010ZX0001_OUT
        implements org.apache.axis2.databinding.ADBBean{
        /* This type was generated from the piece of schema that had
                name = FMT_CRMS_SVR_S03601010ZX0001_OUT
                Namespace URI = http://www.adtec.com.cn
                Namespace Prefix = ns1
                */
            

        private static java.lang.String generatePrefix(java.lang.String namespace) {
            if(namespace.equals("http://www.adtec.com.cn")){
                return "ns1";
            }
            return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
        }

        

                        /**
                        * field for BusiNo
                        */

                        
                            protected java.lang.String localBusiNo ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localBusiNoTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getBusiNo(){
                               return localBusiNo;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param BusiNo
                               */
                               public void setBusiNo(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localBusiNoTracker = true;
                                       } else {
                                          localBusiNoTracker = false;
                                              
                                       }
                                   
                                            this.localBusiNo=param;
                                    

                               }
                            

                        /**
                        * field for ImageNo
                        */

                        
                            protected java.lang.String localImageNo ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localImageNoTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getImageNo(){
                               return localImageNo;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param ImageNo
                               */
                               public void setImageNo(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localImageNoTracker = true;
                                       } else {
                                          localImageNoTracker = false;
                                              
                                       }
                                   
                                            this.localImageNo=param;
                                    

                               }
                            

                        /**
                        * field for CurOverNodk
                        */

                        
                            protected java.lang.String localCurOverNodk ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localCurOverNodkTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getCurOverNodk(){
                               return localCurOverNodk;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param CurOverNodk
                               */
                               public void setCurOverNodk(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localCurOverNodkTracker = true;
                                       } else {
                                          localCurOverNodkTracker = false;
                                              
                                       }
                                   
                                            this.localCurOverNodk=param;
                                    

                               }
                            

                        /**
                        * field for CurreOveAmountdk
                        */

                        
                            protected java.lang.String localCurreOveAmountdk ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localCurreOveAmountdkTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getCurreOveAmountdk(){
                               return localCurreOveAmountdk;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param CurreOveAmountdk
                               */
                               public void setCurreOveAmountdk(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localCurreOveAmountdkTracker = true;
                                       } else {
                                          localCurreOveAmountdkTracker = false;
                                              
                                       }
                                   
                                            this.localCurreOveAmountdk=param;
                                    

                               }
                            

                        /**
                        * field for HigtNoOfOvePerdsdk
                        */

                        
                            protected java.lang.String localHigtNoOfOvePerdsdk ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localHigtNoOfOvePerdsdkTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getHigtNoOfOvePerdsdk(){
                               return localHigtNoOfOvePerdsdk;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param HigtNoOfOvePerdsdk
                               */
                               public void setHigtNoOfOvePerdsdk(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localHigtNoOfOvePerdsdkTracker = true;
                                       } else {
                                          localHigtNoOfOvePerdsdkTracker = false;
                                              
                                       }
                                   
                                            this.localHigtNoOfOvePerdsdk=param;
                                    

                               }
                            

                        /**
                        * field for CumuOverNoOfHstrydk
                        */

                        
                            protected java.lang.String localCumuOverNoOfHstrydk ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localCumuOverNoOfHstrydkTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getCumuOverNoOfHstrydk(){
                               return localCumuOverNoOfHstrydk;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param CumuOverNoOfHstrydk
                               */
                               public void setCumuOverNoOfHstrydk(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localCumuOverNoOfHstrydkTracker = true;
                                       } else {
                                          localCumuOverNoOfHstrydkTracker = false;
                                              
                                       }
                                   
                                            this.localCumuOverNoOfHstrydk=param;
                                    

                               }
                            

                        /**
                        * field for CurOverNodjk
                        */

                        
                            protected java.lang.String localCurOverNodjk ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localCurOverNodjkTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getCurOverNodjk(){
                               return localCurOverNodjk;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param CurOverNodjk
                               */
                               public void setCurOverNodjk(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localCurOverNodjkTracker = true;
                                       } else {
                                          localCurOverNodjkTracker = false;
                                              
                                       }
                                   
                                            this.localCurOverNodjk=param;
                                    

                               }
                            

                        /**
                        * field for CurreOveAmountdjk
                        */

                        
                            protected java.lang.String localCurreOveAmountdjk ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localCurreOveAmountdjkTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getCurreOveAmountdjk(){
                               return localCurreOveAmountdjk;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param CurreOveAmountdjk
                               */
                               public void setCurreOveAmountdjk(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localCurreOveAmountdjkTracker = true;
                                       } else {
                                          localCurreOveAmountdjkTracker = false;
                                              
                                       }
                                   
                                            this.localCurreOveAmountdjk=param;
                                    

                               }
                            

                        /**
                        * field for HigtNoOfOvePerdsdjk
                        */

                        
                            protected java.lang.String localHigtNoOfOvePerdsdjk ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localHigtNoOfOvePerdsdjkTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getHigtNoOfOvePerdsdjk(){
                               return localHigtNoOfOvePerdsdjk;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param HigtNoOfOvePerdsdjk
                               */
                               public void setHigtNoOfOvePerdsdjk(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localHigtNoOfOvePerdsdjkTracker = true;
                                       } else {
                                          localHigtNoOfOvePerdsdjkTracker = false;
                                              
                                       }
                                   
                                            this.localHigtNoOfOvePerdsdjk=param;
                                    

                               }
                            

                        /**
                        * field for CumuOverNoOfHstrydjk
                        */

                        
                            protected java.lang.String localCumuOverNoOfHstrydjk ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localCumuOverNoOfHstrydjkTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getCumuOverNoOfHstrydjk(){
                               return localCumuOverNoOfHstrydjk;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param CumuOverNoOfHstrydjk
                               */
                               public void setCumuOverNoOfHstrydjk(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localCumuOverNoOfHstrydjkTracker = true;
                                       } else {
                                          localCumuOverNoOfHstrydjkTracker = false;
                                              
                                       }
                                   
                                            this.localCumuOverNoOfHstrydjk=param;
                                    

                               }
                            

                        /**
                        * field for CurOverNozdjk
                        */

                        
                            protected java.lang.String localCurOverNozdjk ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localCurOverNozdjkTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getCurOverNozdjk(){
                               return localCurOverNozdjk;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param CurOverNozdjk
                               */
                               public void setCurOverNozdjk(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localCurOverNozdjkTracker = true;
                                       } else {
                                          localCurOverNozdjkTracker = false;
                                              
                                       }
                                   
                                            this.localCurOverNozdjk=param;
                                    

                               }
                            

                        /**
                        * field for CurreOveAmountzdjk
                        */

                        
                            protected java.lang.String localCurreOveAmountzdjk ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localCurreOveAmountzdjkTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getCurreOveAmountzdjk(){
                               return localCurreOveAmountzdjk;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param CurreOveAmountzdjk
                               */
                               public void setCurreOveAmountzdjk(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localCurreOveAmountzdjkTracker = true;
                                       } else {
                                          localCurreOveAmountzdjkTracker = false;
                                              
                                       }
                                   
                                            this.localCurreOveAmountzdjk=param;
                                    

                               }
                            

                        /**
                        * field for HigtNoOfOvePerdszdjk
                        */

                        
                            protected java.lang.String localHigtNoOfOvePerdszdjk ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localHigtNoOfOvePerdszdjkTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getHigtNoOfOvePerdszdjk(){
                               return localHigtNoOfOvePerdszdjk;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param HigtNoOfOvePerdszdjk
                               */
                               public void setHigtNoOfOvePerdszdjk(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localHigtNoOfOvePerdszdjkTracker = true;
                                       } else {
                                          localHigtNoOfOvePerdszdjkTracker = false;
                                              
                                       }
                                   
                                            this.localHigtNoOfOvePerdszdjk=param;
                                    

                               }
                            

                        /**
                        * field for CumuOverNoOfHstryzdjk
                        */

                        
                            protected java.lang.String localCumuOverNoOfHstryzdjk ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localCumuOverNoOfHstryzdjkTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getCumuOverNoOfHstryzdjk(){
                               return localCumuOverNoOfHstryzdjk;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param CumuOverNoOfHstryzdjk
                               */
                               public void setCumuOverNoOfHstryzdjk(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localCumuOverNoOfHstryzdjkTracker = true;
                                       } else {
                                          localCumuOverNoOfHstryzdjkTracker = false;
                                              
                                       }
                                   
                                            this.localCumuOverNoOfHstryzdjk=param;
                                    

                               }
                            

                        /**
                        * field for OrgNumdk
                        */

                        
                            protected java.lang.String localOrgNumdk ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localOrgNumdkTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getOrgNumdk(){
                               return localOrgNumdk;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param OrgNumdk
                               */
                               public void setOrgNumdk(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localOrgNumdkTracker = true;
                                       } else {
                                          localOrgNumdkTracker = false;
                                              
                                       }
                                   
                                            this.localOrgNumdk=param;
                                    

                               }
                            

                        /**
                        * field for OrgNumxyk
                        */

                        
                            protected java.lang.String localOrgNumxyk ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localOrgNumxykTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getOrgNumxyk(){
                               return localOrgNumxyk;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param OrgNumxyk
                               */
                               public void setOrgNumxyk(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localOrgNumxykTracker = true;
                                       } else {
                                          localOrgNumxykTracker = false;
                                              
                                       }
                                   
                                            this.localOrgNumxyk=param;
                                    

                               }
                            

                        /**
                        * field for QueNumdk
                        */

                        
                            protected java.lang.String localQueNumdk ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localQueNumdkTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getQueNumdk(){
                               return localQueNumdk;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param QueNumdk
                               */
                               public void setQueNumdk(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localQueNumdkTracker = true;
                                       } else {
                                          localQueNumdkTracker = false;
                                              
                                       }
                                   
                                            this.localQueNumdk=param;
                                    

                               }
                            

                        /**
                        * field for QueNumxyk
                        */

                        
                            protected java.lang.String localQueNumxyk ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localQueNumxykTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getQueNumxyk(){
                               return localQueNumxyk;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param QueNumxyk
                               */
                               public void setQueNumxyk(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localQueNumxykTracker = true;
                                       } else {
                                          localQueNumxykTracker = false;
                                              
                                       }
                                   
                                            this.localQueNumxyk=param;
                                    

                               }
                            

                        /**
                        * field for FiveLevClass
                        */

                        
                            protected java.lang.String localFiveLevClass ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localFiveLevClassTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getFiveLevClass(){
                               return localFiveLevClass;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param FiveLevClass
                               */
                               public void setFiveLevClass(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localFiveLevClassTracker = true;
                                       } else {
                                          localFiveLevClassTracker = false;
                                              
                                       }
                                   
                                            this.localFiveLevClass=param;
                                    

                               }
                            

     /**
     * isReaderMTOMAware
     * @return true if the reader supports MTOM
     */
   public static boolean isReaderMTOMAware(javax.xml.stream.XMLStreamReader reader) {
        boolean isReaderMTOMAware = false;
        
        try{
          isReaderMTOMAware = java.lang.Boolean.TRUE.equals(reader.getProperty(org.apache.axiom.om.OMConstants.IS_DATA_HANDLERS_AWARE));
        }catch(java.lang.IllegalArgumentException e){
          isReaderMTOMAware = false;
        }
        return isReaderMTOMAware;
   }
     
     
        /**
        *
        * @param parentQName
        * @param factory
        * @return org.apache.axiom.om.OMElement
        */
       public org.apache.axiom.om.OMElement getOMElement (
               final javax.xml.namespace.QName parentQName,
               final org.apache.axiom.om.OMFactory factory) throws org.apache.axis2.databinding.ADBException{


        
               org.apache.axiom.om.OMDataSource dataSource =
                       new org.apache.axis2.databinding.ADBDataSource(this,parentQName){

                 public void serialize(org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
                       FMT_CRMS_SVR_S03601010ZX0001_OUT.this.serialize(parentQName,factory,xmlWriter);
                 }
               };
               return new org.apache.axiom.om.impl.llom.OMSourcedElementImpl(
               parentQName,factory,dataSource);
            
       }



         public void serialize(final javax.xml.namespace.QName parentQName,
                               final org.apache.axiom.om.OMFactory factory,
                               org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException{
            
                


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
                 if (localBusiNoTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"BusiNo", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"BusiNo");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("BusiNo");
                                    }
                                

                                          if (localBusiNo==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("BusiNo cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localBusiNo);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localImageNoTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"ImageNo", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"ImageNo");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("ImageNo");
                                    }
                                

                                          if (localImageNo==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("ImageNo cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localImageNo);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localCurOverNodkTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"CurOverNodk", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"CurOverNodk");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("CurOverNodk");
                                    }
                                

                                          if (localCurOverNodk==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("CurOverNodk cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localCurOverNodk);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localCurreOveAmountdkTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"CurreOveAmountdk", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"CurreOveAmountdk");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("CurreOveAmountdk");
                                    }
                                

                                          if (localCurreOveAmountdk==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("CurreOveAmountdk cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localCurreOveAmountdk);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localHigtNoOfOvePerdsdkTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"HigtNoOfOvePerdsdk", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"HigtNoOfOvePerdsdk");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("HigtNoOfOvePerdsdk");
                                    }
                                

                                          if (localHigtNoOfOvePerdsdk==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("HigtNoOfOvePerdsdk cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localHigtNoOfOvePerdsdk);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localCumuOverNoOfHstrydkTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"CumuOverNoOfHstrydk", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"CumuOverNoOfHstrydk");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("CumuOverNoOfHstrydk");
                                    }
                                

                                          if (localCumuOverNoOfHstrydk==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("CumuOverNoOfHstrydk cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localCumuOverNoOfHstrydk);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localCurOverNodjkTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"CurOverNodjk", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"CurOverNodjk");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("CurOverNodjk");
                                    }
                                

                                          if (localCurOverNodjk==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("CurOverNodjk cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localCurOverNodjk);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localCurreOveAmountdjkTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"CurreOveAmountdjk", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"CurreOveAmountdjk");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("CurreOveAmountdjk");
                                    }
                                

                                          if (localCurreOveAmountdjk==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("CurreOveAmountdjk cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localCurreOveAmountdjk);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localHigtNoOfOvePerdsdjkTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"HigtNoOfOvePerdsdjk", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"HigtNoOfOvePerdsdjk");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("HigtNoOfOvePerdsdjk");
                                    }
                                

                                          if (localHigtNoOfOvePerdsdjk==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("HigtNoOfOvePerdsdjk cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localHigtNoOfOvePerdsdjk);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localCumuOverNoOfHstrydjkTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"CumuOverNoOfHstrydjk", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"CumuOverNoOfHstrydjk");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("CumuOverNoOfHstrydjk");
                                    }
                                

                                          if (localCumuOverNoOfHstrydjk==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("CumuOverNoOfHstrydjk cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localCumuOverNoOfHstrydjk);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localCurOverNozdjkTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"CurOverNozdjk", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"CurOverNozdjk");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("CurOverNozdjk");
                                    }
                                

                                          if (localCurOverNozdjk==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("CurOverNozdjk cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localCurOverNozdjk);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localCurreOveAmountzdjkTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"CurreOveAmountzdjk", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"CurreOveAmountzdjk");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("CurreOveAmountzdjk");
                                    }
                                

                                          if (localCurreOveAmountzdjk==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("CurreOveAmountzdjk cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localCurreOveAmountzdjk);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localHigtNoOfOvePerdszdjkTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"HigtNoOfOvePerdszdjk", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"HigtNoOfOvePerdszdjk");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("HigtNoOfOvePerdszdjk");
                                    }
                                

                                          if (localHigtNoOfOvePerdszdjk==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("HigtNoOfOvePerdszdjk cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localHigtNoOfOvePerdszdjk);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localCumuOverNoOfHstryzdjkTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"CumuOverNoOfHstryzdjk", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"CumuOverNoOfHstryzdjk");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("CumuOverNoOfHstryzdjk");
                                    }
                                

                                          if (localCumuOverNoOfHstryzdjk==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("CumuOverNoOfHstryzdjk cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localCumuOverNoOfHstryzdjk);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localOrgNumdkTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"OrgNumdk", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"OrgNumdk");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("OrgNumdk");
                                    }
                                

                                          if (localOrgNumdk==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("OrgNumdk cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localOrgNumdk);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localOrgNumxykTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"OrgNumxyk", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"OrgNumxyk");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("OrgNumxyk");
                                    }
                                

                                          if (localOrgNumxyk==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("OrgNumxyk cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localOrgNumxyk);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localQueNumdkTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"QueNumdk", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"QueNumdk");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("QueNumdk");
                                    }
                                

                                          if (localQueNumdk==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("QueNumdk cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localQueNumdk);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localQueNumxykTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"QueNumxyk", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"QueNumxyk");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("QueNumxyk");
                                    }
                                

                                          if (localQueNumxyk==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("QueNumxyk cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localQueNumxyk);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localFiveLevClassTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"FiveLevClass", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"FiveLevClass");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("FiveLevClass");
                                    }
                                

                                          if (localFiveLevClass==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("FiveLevClass cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localFiveLevClass);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             }
                    xmlWriter.writeEndElement();
               

        }

         /**
          * Util method to write an attribute with the ns prefix
          */
          private void writeAttribute(java.lang.String prefix,java.lang.String namespace,java.lang.String attName,
                                      java.lang.String attValue,javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException{
              if (xmlWriter.getPrefix(namespace) == null) {
                       xmlWriter.writeNamespace(prefix, namespace);
                       xmlWriter.setPrefix(prefix, namespace);

              }

              xmlWriter.writeAttribute(namespace,attName,attValue);

         }

        /**
          * Util method to write an attribute without the ns prefix
          */
          private void writeAttribute(java.lang.String namespace,java.lang.String attName,
                                      java.lang.String attValue,javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException{
                if (namespace.equals(""))
              {
                  xmlWriter.writeAttribute(attName,attValue);
              }
              else
              {
                  registerPrefix(xmlWriter, namespace);
                  xmlWriter.writeAttribute(namespace,attName,attValue);
              }
          }


           /**
             * Util method to write an attribute without the ns prefix
             */
            private void writeQNameAttribute(java.lang.String namespace, java.lang.String attName,
                                             javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

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
         *  method to handle Qnames
         */

        private void writeQName(javax.xml.namespace.QName qname,
                                javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
            java.lang.String namespaceURI = qname.getNamespaceURI();
            if (namespaceURI != null) {
                java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
                if (prefix == null) {
                    prefix = generatePrefix(namespaceURI);
                    xmlWriter.writeNamespace(prefix, namespaceURI);
                    xmlWriter.setPrefix(prefix,namespaceURI);
                }

                if (prefix.trim().length() > 0){
                    xmlWriter.writeCharacters(prefix + ":" + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
                } else {
                    // i.e this is the default namespace
                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
                }

            } else {
                xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
            }
        }

        private void writeQNames(javax.xml.namespace.QName[] qnames,
                                 javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

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
                            xmlWriter.setPrefix(prefix,namespaceURI);
                        }

                        if (prefix.trim().length() > 0){
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
        *
        */
        public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName)
                    throws org.apache.axis2.databinding.ADBException{


        
                 java.util.ArrayList elementList = new java.util.ArrayList();
                 java.util.ArrayList attribList = new java.util.ArrayList();

                 if (localBusiNoTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "BusiNo"));
                                 
                                        if (localBusiNo != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localBusiNo));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("BusiNo cannot be null!!");
                                        }
                                    } if (localImageNoTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "ImageNo"));
                                 
                                        if (localImageNo != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localImageNo));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("ImageNo cannot be null!!");
                                        }
                                    } if (localCurOverNodkTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "CurOverNodk"));
                                 
                                        if (localCurOverNodk != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCurOverNodk));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("CurOverNodk cannot be null!!");
                                        }
                                    } if (localCurreOveAmountdkTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "CurreOveAmountdk"));
                                 
                                        if (localCurreOveAmountdk != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCurreOveAmountdk));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("CurreOveAmountdk cannot be null!!");
                                        }
                                    } if (localHigtNoOfOvePerdsdkTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "HigtNoOfOvePerdsdk"));
                                 
                                        if (localHigtNoOfOvePerdsdk != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHigtNoOfOvePerdsdk));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("HigtNoOfOvePerdsdk cannot be null!!");
                                        }
                                    } if (localCumuOverNoOfHstrydkTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "CumuOverNoOfHstrydk"));
                                 
                                        if (localCumuOverNoOfHstrydk != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCumuOverNoOfHstrydk));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("CumuOverNoOfHstrydk cannot be null!!");
                                        }
                                    } if (localCurOverNodjkTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "CurOverNodjk"));
                                 
                                        if (localCurOverNodjk != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCurOverNodjk));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("CurOverNodjk cannot be null!!");
                                        }
                                    } if (localCurreOveAmountdjkTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "CurreOveAmountdjk"));
                                 
                                        if (localCurreOveAmountdjk != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCurreOveAmountdjk));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("CurreOveAmountdjk cannot be null!!");
                                        }
                                    } if (localHigtNoOfOvePerdsdjkTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "HigtNoOfOvePerdsdjk"));
                                 
                                        if (localHigtNoOfOvePerdsdjk != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHigtNoOfOvePerdsdjk));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("HigtNoOfOvePerdsdjk cannot be null!!");
                                        }
                                    } if (localCumuOverNoOfHstrydjkTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "CumuOverNoOfHstrydjk"));
                                 
                                        if (localCumuOverNoOfHstrydjk != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCumuOverNoOfHstrydjk));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("CumuOverNoOfHstrydjk cannot be null!!");
                                        }
                                    } if (localCurOverNozdjkTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "CurOverNozdjk"));
                                 
                                        if (localCurOverNozdjk != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCurOverNozdjk));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("CurOverNozdjk cannot be null!!");
                                        }
                                    } if (localCurreOveAmountzdjkTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "CurreOveAmountzdjk"));
                                 
                                        if (localCurreOveAmountzdjk != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCurreOveAmountzdjk));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("CurreOveAmountzdjk cannot be null!!");
                                        }
                                    } if (localHigtNoOfOvePerdszdjkTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "HigtNoOfOvePerdszdjk"));
                                 
                                        if (localHigtNoOfOvePerdszdjk != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHigtNoOfOvePerdszdjk));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("HigtNoOfOvePerdszdjk cannot be null!!");
                                        }
                                    } if (localCumuOverNoOfHstryzdjkTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "CumuOverNoOfHstryzdjk"));
                                 
                                        if (localCumuOverNoOfHstryzdjk != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCumuOverNoOfHstryzdjk));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("CumuOverNoOfHstryzdjk cannot be null!!");
                                        }
                                    } if (localOrgNumdkTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "OrgNumdk"));
                                 
                                        if (localOrgNumdk != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localOrgNumdk));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("OrgNumdk cannot be null!!");
                                        }
                                    } if (localOrgNumxykTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "OrgNumxyk"));
                                 
                                        if (localOrgNumxyk != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localOrgNumxyk));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("OrgNumxyk cannot be null!!");
                                        }
                                    } if (localQueNumdkTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "QueNumdk"));
                                 
                                        if (localQueNumdk != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localQueNumdk));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("QueNumdk cannot be null!!");
                                        }
                                    } if (localQueNumxykTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "QueNumxyk"));
                                 
                                        if (localQueNumxyk != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localQueNumxyk));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("QueNumxyk cannot be null!!");
                                        }
                                    } if (localFiveLevClassTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "FiveLevClass"));
                                 
                                        if (localFiveLevClass != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localFiveLevClass));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("FiveLevClass cannot be null!!");
                                        }
                                    }

                return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName, elementList.toArray(), attribList.toArray());
            
            

        }

  

     /**
      *  Factory class that keeps the parse method
      */
    public static class Factory{

        
        

        /**
        * static method to create the object
        * Precondition:  If this object is an element, the current or next start element starts this object and any intervening reader events are ignorable
        *                If this object is not an element, it is a complex type and the reader is at the event just after the outer start element
        * Postcondition: If this object is an element, the reader is positioned at its end element
        *                If this object is a complex type, the reader is positioned at the end element of its outer element
        */
        public static FMT_CRMS_SVR_S03601010ZX0001_OUT parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            FMT_CRMS_SVR_S03601010ZX0001_OUT object =
                new FMT_CRMS_SVR_S03601010ZX0001_OUT();

            int event;
            java.lang.String nillableValue = null;
            java.lang.String prefix ="";
            java.lang.String namespaceuri ="";
            try {
                
                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                
                if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","type")!=null){
                  java.lang.String fullTypeName = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                        "type");
                  if (fullTypeName!=null){
                    java.lang.String nsPrefix = null;
                    if (fullTypeName.indexOf(":") > -1){
                        nsPrefix = fullTypeName.substring(0,fullTypeName.indexOf(":"));
                    }
                    nsPrefix = nsPrefix==null?"":nsPrefix;

                    java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":")+1);
                    
                            if (!"FMT_CRMS_SVR_S03601010ZX0001_OUT".equals(type)){
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                                return (FMT_CRMS_SVR_S03601010ZX0001_OUT)ExtensionMapper.getTypeObject(
                                     nsUri,type,reader);
                              }
                        

                  }

                }
                

                
                // Note all attributes that were handled. Used to differ normal attributes
                // from anyAttributes.
                java.util.Vector handledAttributes = new java.util.Vector();
                

                 
                    
                    reader.next();
                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","BusiNo").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setBusiNo(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","ImageNo").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setImageNo(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","CurOverNodk").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setCurOverNodk(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","CurreOveAmountdk").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setCurreOveAmountdk(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","HigtNoOfOvePerdsdk").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setHigtNoOfOvePerdsdk(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","CumuOverNoOfHstrydk").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setCumuOverNoOfHstrydk(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","CurOverNodjk").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setCurOverNodjk(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","CurreOveAmountdjk").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setCurreOveAmountdjk(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","HigtNoOfOvePerdsdjk").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setHigtNoOfOvePerdsdjk(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","CumuOverNoOfHstrydjk").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setCumuOverNoOfHstrydjk(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","CurOverNozdjk").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setCurOverNozdjk(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","CurreOveAmountzdjk").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setCurreOveAmountzdjk(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","HigtNoOfOvePerdszdjk").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setHigtNoOfOvePerdszdjk(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","CumuOverNoOfHstryzdjk").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setCumuOverNoOfHstryzdjk(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","OrgNumdk").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setOrgNumdk(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","OrgNumxyk").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setOrgNumxyk(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","QueNumdk").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setQueNumdk(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","QueNumxyk").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setQueNumxyk(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","FiveLevClass").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setFiveLevClass(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
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

        }//end of factory class

        

        }
           
          

        public static class S03601010ZX0001
        implements org.apache.axis2.databinding.ADBBean{
        
                public static final javax.xml.namespace.QName MY_QNAME = new javax.xml.namespace.QName(
                "http://www.adtec.com.cn",
                "S03601010ZX0001",
                "ns1");

            

        private static java.lang.String generatePrefix(java.lang.String namespace) {
            if(namespace.equals("http://www.adtec.com.cn")){
                return "ns1";
            }
            return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
        }

        

                        /**
                        * field for ReqTranHeader
                        */

                        
                            protected FMT_SOAP_UTF8_ReqTranHeader localReqTranHeader ;
                        

                           /**
                           * Auto generated getter method
                           * @return FMT_SOAP_UTF8_ReqTranHeader
                           */
                           public  FMT_SOAP_UTF8_ReqTranHeader getReqTranHeader(){
                               return localReqTranHeader;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param ReqTranHeader
                               */
                               public void setReqTranHeader(FMT_SOAP_UTF8_ReqTranHeader param){
                            
                                            this.localReqTranHeader=param;
                                    

                               }
                            

                        /**
                        * field for RequestBody
                        */

                        
                            protected FMT_CRMS_SVR_S03601010ZX0001_IN localRequestBody ;
                        

                           /**
                           * Auto generated getter method
                           * @return FMT_CRMS_SVR_S03601010ZX0001_IN
                           */
                           public  FMT_CRMS_SVR_S03601010ZX0001_IN getRequestBody(){
                               return localRequestBody;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param RequestBody
                               */
                               public void setRequestBody(FMT_CRMS_SVR_S03601010ZX0001_IN param){
                            
                                            this.localRequestBody=param;
                                    

                               }
                            

                        /**
                        * field for RequestHeader
                        */

                        
                            protected FMT_SOAP_UTF8_RequestHeader localRequestHeader ;
                        

                           /**
                           * Auto generated getter method
                           * @return FMT_SOAP_UTF8_RequestHeader
                           */
                           public  FMT_SOAP_UTF8_RequestHeader getRequestHeader(){
                               return localRequestHeader;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param RequestHeader
                               */
                               public void setRequestHeader(FMT_SOAP_UTF8_RequestHeader param){
                            
                                            this.localRequestHeader=param;
                                    

                               }
                            

     /**
     * isReaderMTOMAware
     * @return true if the reader supports MTOM
     */
   public static boolean isReaderMTOMAware(javax.xml.stream.XMLStreamReader reader) {
        boolean isReaderMTOMAware = false;
        
        try{
          isReaderMTOMAware = java.lang.Boolean.TRUE.equals(reader.getProperty(org.apache.axiom.om.OMConstants.IS_DATA_HANDLERS_AWARE));
        }catch(java.lang.IllegalArgumentException e){
          isReaderMTOMAware = false;
        }
        return isReaderMTOMAware;
   }
     
     
        /**
        *
        * @param parentQName
        * @param factory
        * @return org.apache.axiom.om.OMElement
        */
       public org.apache.axiom.om.OMElement getOMElement (
               final javax.xml.namespace.QName parentQName,
               final org.apache.axiom.om.OMFactory factory) throws org.apache.axis2.databinding.ADBException{


        
                org.apache.axiom.om.OMDataSource dataSource =
                       new org.apache.axis2.databinding.ADBDataSource(this,MY_QNAME){

                 public void serialize(org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
                       S03601010ZX0001.this.serialize(MY_QNAME,factory,xmlWriter);
                 }
               };
               return new org.apache.axiom.om.impl.llom.OMSourcedElementImpl(
               MY_QNAME,factory,dataSource);
            
       }



         public void serialize(final javax.xml.namespace.QName parentQName,
                               final org.apache.axiom.om.OMFactory factory,
                               org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException{
            
                


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
                
                                            if (localReqTranHeader==null){
                                                 throw new org.apache.axis2.databinding.ADBException("ReqTranHeader cannot be null!!");
                                            }
                                           localReqTranHeader.serialize(new javax.xml.namespace.QName("","ReqTranHeader"),
                                               factory,xmlWriter);
                                        
                                            if (localRequestBody==null){
                                                 throw new org.apache.axis2.databinding.ADBException("RequestBody cannot be null!!");
                                            }
                                           localRequestBody.serialize(new javax.xml.namespace.QName("","RequestBody"),
                                               factory,xmlWriter);
                                        
                                            if (localRequestHeader==null){
                                                 throw new org.apache.axis2.databinding.ADBException("RequestHeader cannot be null!!");
                                            }
                                           localRequestHeader.serialize(new javax.xml.namespace.QName("","RequestHeader"),
                                               factory,xmlWriter);
                                        
                    xmlWriter.writeEndElement();
               

        }

         /**
          * Util method to write an attribute with the ns prefix
          */
          private void writeAttribute(java.lang.String prefix,java.lang.String namespace,java.lang.String attName,
                                      java.lang.String attValue,javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException{
              if (xmlWriter.getPrefix(namespace) == null) {
                       xmlWriter.writeNamespace(prefix, namespace);
                       xmlWriter.setPrefix(prefix, namespace);

              }

              xmlWriter.writeAttribute(namespace,attName,attValue);

         }

        /**
          * Util method to write an attribute without the ns prefix
          */
          private void writeAttribute(java.lang.String namespace,java.lang.String attName,
                                      java.lang.String attValue,javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException{
                if (namespace.equals(""))
              {
                  xmlWriter.writeAttribute(attName,attValue);
              }
              else
              {
                  registerPrefix(xmlWriter, namespace);
                  xmlWriter.writeAttribute(namespace,attName,attValue);
              }
          }


           /**
             * Util method to write an attribute without the ns prefix
             */
            private void writeQNameAttribute(java.lang.String namespace, java.lang.String attName,
                                             javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

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
         *  method to handle Qnames
         */

        private void writeQName(javax.xml.namespace.QName qname,
                                javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
            java.lang.String namespaceURI = qname.getNamespaceURI();
            if (namespaceURI != null) {
                java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
                if (prefix == null) {
                    prefix = generatePrefix(namespaceURI);
                    xmlWriter.writeNamespace(prefix, namespaceURI);
                    xmlWriter.setPrefix(prefix,namespaceURI);
                }

                if (prefix.trim().length() > 0){
                    xmlWriter.writeCharacters(prefix + ":" + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
                } else {
                    // i.e this is the default namespace
                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
                }

            } else {
                xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
            }
        }

        private void writeQNames(javax.xml.namespace.QName[] qnames,
                                 javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

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
                            xmlWriter.setPrefix(prefix,namespaceURI);
                        }

                        if (prefix.trim().length() > 0){
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
        *
        */
        public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName)
                    throws org.apache.axis2.databinding.ADBException{


        
                 java.util.ArrayList elementList = new java.util.ArrayList();
                 java.util.ArrayList attribList = new java.util.ArrayList();

                
                            elementList.add(new javax.xml.namespace.QName("",
                                                                      "ReqTranHeader"));
                            
                            
                                    if (localReqTranHeader==null){
                                         throw new org.apache.axis2.databinding.ADBException("ReqTranHeader cannot be null!!");
                                    }
                                    elementList.add(localReqTranHeader);
                                
                            elementList.add(new javax.xml.namespace.QName("",
                                                                      "RequestBody"));
                            
                            
                                    if (localRequestBody==null){
                                         throw new org.apache.axis2.databinding.ADBException("RequestBody cannot be null!!");
                                    }
                                    elementList.add(localRequestBody);
                                
                            elementList.add(new javax.xml.namespace.QName("",
                                                                      "RequestHeader"));
                            
                            
                                    if (localRequestHeader==null){
                                         throw new org.apache.axis2.databinding.ADBException("RequestHeader cannot be null!!");
                                    }
                                    elementList.add(localRequestHeader);
                                

                return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName, elementList.toArray(), attribList.toArray());
            
            

        }

  

     /**
      *  Factory class that keeps the parse method
      */
    public static class Factory{

        
        

        /**
        * static method to create the object
        * Precondition:  If this object is an element, the current or next start element starts this object and any intervening reader events are ignorable
        *                If this object is not an element, it is a complex type and the reader is at the event just after the outer start element
        * Postcondition: If this object is an element, the reader is positioned at its end element
        *                If this object is a complex type, the reader is positioned at the end element of its outer element
        */
        public static S03601010ZX0001 parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            S03601010ZX0001 object =
                new S03601010ZX0001();

            int event;
            java.lang.String nillableValue = null;
            java.lang.String prefix ="";
            java.lang.String namespaceuri ="";
            try {
                
                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                
                if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","type")!=null){
                  java.lang.String fullTypeName = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                        "type");
                  if (fullTypeName!=null){
                    java.lang.String nsPrefix = null;
                    if (fullTypeName.indexOf(":") > -1){
                        nsPrefix = fullTypeName.substring(0,fullTypeName.indexOf(":"));
                    }
                    nsPrefix = nsPrefix==null?"":nsPrefix;

                    java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":")+1);
                    
                            if (!"S03601010ZX0001".equals(type)){
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                                return (S03601010ZX0001)ExtensionMapper.getTypeObject(
                                     nsUri,type,reader);
                              }
                        

                  }

                }
                

                
                // Note all attributes that were handled. Used to differ normal attributes
                // from anyAttributes.
                java.util.Vector handledAttributes = new java.util.Vector();
                

                 
                    
                    reader.next();
                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","ReqTranHeader").equals(reader.getName())){
                                
                                        object.setReqTranHeader(FMT_SOAP_UTF8_ReqTranHeader.Factory.parse(reader));
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","RequestBody").equals(reader.getName())){
                                
                                        object.setRequestBody(FMT_CRMS_SVR_S03601010ZX0001_IN.Factory.parse(reader));
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","RequestHeader").equals(reader.getName())){
                                
                                        object.setRequestHeader(FMT_SOAP_UTF8_RequestHeader.Factory.parse(reader));
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
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

        }//end of factory class

        

        }
           
          
            private  org.apache.axiom.om.OMElement  toOM(com.primeton.p2p.S03601010ZX0001ServiceStub.S03601010ZX0001 param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(com.primeton.p2p.S03601010ZX0001ServiceStub.S03601010ZX0001.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(com.primeton.p2p.S03601010ZX0001ServiceStub.S03601010ZX0001Response param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(com.primeton.p2p.S03601010ZX0001ServiceStub.S03601010ZX0001Response.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
                            
                            private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, com.primeton.p2p.S03601010ZX0001ServiceStub.S03601010ZX0001 param, boolean optimizeContent)
                            throws org.apache.axis2.AxisFault{

                                 
                                        try{

                                                org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                                                emptyEnvelope.getBody().addChild(param.getOMElement(com.primeton.p2p.S03601010ZX0001ServiceStub.S03601010ZX0001.MY_QNAME,factory));
                                                return emptyEnvelope;
                                            } catch(org.apache.axis2.databinding.ADBException e){
                                                throw org.apache.axis2.AxisFault.makeFault(e);
                                            }
                                    

                            }

                             
                             /* methods to provide back word compatibility */

                             


        /**
        *  get the default envelope
        */
        private org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory){
        return factory.getDefaultEnvelope();
        }


        private  java.lang.Object fromOM(
        org.apache.axiom.om.OMElement param,
        java.lang.Class type,
        java.util.Map extraNamespaces) throws org.apache.axis2.AxisFault{

        try {
        
                if (com.primeton.p2p.S03601010ZX0001ServiceStub.S03601010ZX0001.class.equals(type)){
                
                           return com.primeton.p2p.S03601010ZX0001ServiceStub.S03601010ZX0001.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (com.primeton.p2p.S03601010ZX0001ServiceStub.S03601010ZX0001Response.class.equals(type)){
                
                           return com.primeton.p2p.S03601010ZX0001ServiceStub.S03601010ZX0001Response.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
        } catch (java.lang.Exception e) {
        throw org.apache.axis2.AxisFault.makeFault(e);
        }
           return null;
        }



    
   }
   