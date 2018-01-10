
/**
 * S01501070000007ServiceStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.3  Built on : Aug 10, 2007 (04:45:47 LKT)
 */
        package com.primeton.crmsgj.client;

        

        /*
        *  S01501070000007ServiceStub java implementation
        */

        
        public class S01501070000007ServiceStub extends org.apache.axis2.client.Stub
        {
        protected org.apache.axis2.description.AxisOperation[] _operations;

        //hashmaps to keep the fault mapping
        private java.util.HashMap faultExceptionNameMap = new java.util.HashMap();
        private java.util.HashMap faultExceptionClassNameMap = new java.util.HashMap();
        private java.util.HashMap faultMessageMap = new java.util.HashMap();

    
    private void populateAxisService() throws org.apache.axis2.AxisFault {

     //creating the Service with a unique name
     _service = new org.apache.axis2.description.AxisService("S01501070000007Service" + this.hashCode());

        //creating the operations
        org.apache.axis2.description.AxisOperation __operation;

        _operations = new org.apache.axis2.description.AxisOperation[1];
        
                   __operation = new org.apache.axis2.description.OutInAxisOperation();
                

            __operation.setName(new javax.xml.namespace.QName("http://www.adtec.com.cn", "S01501070000007"));
	    _service.addOperation(__operation);
	    

	    
	    
            _operations[0]=__operation;
            
        
        }

    //populates the faults
    private void populateFaults(){
         


    }

    /**
      *Constructor that takes in a configContext
      */

    public S01501070000007ServiceStub(org.apache.axis2.context.ConfigurationContext configurationContext,
       java.lang.String targetEndpoint)
       throws org.apache.axis2.AxisFault {
         this(configurationContext,targetEndpoint,false);
   }


   /**
     * Constructor that takes in a configContext  and useseperate listner
     */
   public S01501070000007ServiceStub(org.apache.axis2.context.ConfigurationContext configurationContext,
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
    public S01501070000007ServiceStub(org.apache.axis2.context.ConfigurationContext configurationContext) throws org.apache.axis2.AxisFault {
        
                    this(configurationContext,"http://172.16.221.83:12103/WebService/CRMS_SVR/S01501070000007" );
                
    }

    /**
     * Default Constructor
     */
    public S01501070000007ServiceStub() throws org.apache.axis2.AxisFault {
        
                    this("http://172.16.221.83:12103/WebService/CRMS_SVR/S01501070000007" );
                
    }

    /**
     * Constructor taking the target endpoint
     */
    public S01501070000007ServiceStub(java.lang.String targetEndpoint) throws org.apache.axis2.AxisFault {
        this(null,targetEndpoint);
    }



        
                    /**
                     * Auto generated method signature
                     * @see com.primeton.crmsgj.client.S01501070000007Service#S01501070000007
                     * @param s015010700000070
                    
                     */

                    
                            public  com.primeton.crmsgj.client.S01501070000007ServiceStub.S01501070000007Response S01501070000007(

                            com.primeton.crmsgj.client.S01501070000007ServiceStub.S01501070000007 s015010700000070)
                        

                    throws java.rmi.RemoteException
                    
                    {

              try{
               org.apache.axis2.client.OperationClient _operationClient = _serviceClient.createClient(_operations[0].getName());
              _operationClient.getOptions().setAction("http://172.16.221.83:12103/WebService/CRMS_SVR/S01501070000007");
              _operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

              
              
                  addPropertyToOperationClient(_operationClient,org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,"&");
              

              // create a message context
              org.apache.axis2.context.MessageContext _messageContext = new org.apache.axis2.context.MessageContext();

              

              // create SOAP envelope with that payload
              org.apache.axiom.soap.SOAPEnvelope env = null;
                    
                                                    
                                                    env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()),
                                                    s015010700000070,
                                                    optimizeContent(new javax.xml.namespace.QName("http://www.adtec.com.cn",
                                                    "S01501070000007")));
                                                
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
                                             com.primeton.crmsgj.client.S01501070000007ServiceStub.S01501070000007Response.class,
                                              getEnvelopeNamespaces(_returnEnv));
                                _messageContext.getTransportOut().getSender().cleanup(_messageContext);
                               
                                        return (com.primeton.crmsgj.client.S01501070000007ServiceStub.S01501070000007Response)object;
                                   
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
                * @see com.primeton.crmsgj.client.S01501070000007Service#startS01501070000007
                    * @param s015010700000070
                
                */
                public  void startS01501070000007(

                 com.primeton.crmsgj.client.S01501070000007ServiceStub.S01501070000007 s015010700000070,

                  final com.primeton.crmsgj.client.S01501070000007ServiceCallbackHandler callback)

                throws java.rmi.RemoteException{

              org.apache.axis2.client.OperationClient _operationClient = _serviceClient.createClient(_operations[0].getName());
             _operationClient.getOptions().setAction("http://172.16.221.83:12103/WebService/CRMS_SVR/S01501070000007");
             _operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

              
              
                  addPropertyToOperationClient(_operationClient,org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,"&");
              


              // create SOAP envelope with that payload
              org.apache.axiom.soap.SOAPEnvelope env=null;
              org.apache.axis2.context.MessageContext _messageContext = new org.apache.axis2.context.MessageContext();

                    
                                    //Style is Doc.
                                    
                                                    
                                                    env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()),
                                                    s015010700000070,
                                                    optimizeContent(new javax.xml.namespace.QName("http://www.adtec.com.cn",
                                                    "S01501070000007")));
                                                
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
                                                                         com.primeton.crmsgj.client.S01501070000007ServiceStub.S01501070000007Response.class,
                                                                         getEnvelopeNamespaces(resultEnv));
                                        callback.receiveResultS01501070000007(
                                        (com.primeton.crmsgj.client.S01501070000007ServiceStub.S01501070000007Response)object);
                                        
                            } catch (org.apache.axis2.AxisFault e) {
                                callback.receiveErrorS01501070000007(e);
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
													

										            callback.receiveErrorS01501070000007(new java.rmi.RemoteException(ex.getMessage(), ex));
                                            } catch(java.lang.ClassCastException e){
                                                // we cannot intantiate the class - throw the original Axis fault
                                                callback.receiveErrorS01501070000007(f);
                                            } catch (java.lang.ClassNotFoundException e) {
                                                // we cannot intantiate the class - throw the original Axis fault
                                                callback.receiveErrorS01501070000007(f);
                                            } catch (java.lang.NoSuchMethodException e) {
                                                // we cannot intantiate the class - throw the original Axis fault
                                                callback.receiveErrorS01501070000007(f);
                                            } catch (java.lang.reflect.InvocationTargetException e) {
                                                // we cannot intantiate the class - throw the original Axis fault
                                                callback.receiveErrorS01501070000007(f);
                                            } catch (java.lang.IllegalAccessException e) {
                                                // we cannot intantiate the class - throw the original Axis fault
                                                callback.receiveErrorS01501070000007(f);
                                            } catch (java.lang.InstantiationException e) {
                                                // we cannot intantiate the class - throw the original Axis fault
                                                callback.receiveErrorS01501070000007(f);
                                            } catch (org.apache.axis2.AxisFault e) {
                                                // we cannot intantiate the class - throw the original Axis fault
                                                callback.receiveErrorS01501070000007(f);
                                            }
									    } else {
										    callback.receiveErrorS01501070000007(f);
									    }
									} else {
									    callback.receiveErrorS01501070000007(f);
									}
								} else {
								    callback.receiveErrorS01501070000007(error);
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
     //http://172.16.221.83:12103/WebService/CRMS_SVR/S01501070000007

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
           
          

        public static class S01501070000007
        implements org.apache.axis2.databinding.ADBBean{
        
                public static final javax.xml.namespace.QName MY_QNAME = new javax.xml.namespace.QName(
                "http://www.adtec.com.cn",
                "S01501070000007",
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

                        
                            protected FMT_CRMS_SVR_S01501070000007_IN localRequestBody ;
                        

                           /**
                           * Auto generated getter method
                           * @return FMT_CRMS_SVR_S01501070000007_IN
                           */
                           public  FMT_CRMS_SVR_S01501070000007_IN getRequestBody(){
                               return localRequestBody;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param RequestBody
                               */
                               public void setRequestBody(FMT_CRMS_SVR_S01501070000007_IN param){
                            
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
                       S01501070000007.this.serialize(MY_QNAME,factory,xmlWriter);
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
        public static S01501070000007 parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            S01501070000007 object =
                new S01501070000007();

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
                    
                            if (!"S01501070000007".equals(type)){
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                                return (S01501070000007)ExtensionMapper.getTypeObject(
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
                                
                                        object.setRequestBody(FMT_CRMS_SVR_S01501070000007_IN.Factory.parse(reader));
                                      
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
                  "FMT_CRMS_SVR_S01501070000007_OUT".equals(typeName)){
                   
                            return  FMT_CRMS_SVR_S01501070000007_OUT.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://www.adtec.com.cn".equals(namespaceURI) &&
                  "FMT_SOAP_UTF8_ReqTranHeader".equals(typeName)){
                   
                            return  FMT_SOAP_UTF8_ReqTranHeader.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://www.adtec.com.cn".equals(namespaceURI) &&
                  "FMT_CRMS_SVR_S01501070000007_IN".equals(typeName)){
                   
                            return  FMT_CRMS_SVR_S01501070000007_IN.Factory.parse(reader);
                        

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
           
          

        public static class FMT_CRMS_SVR_S01501070000007_OUT
        implements org.apache.axis2.databinding.ADBBean{
        /* This type was generated from the piece of schema that had
                name = FMT_CRMS_SVR_S01501070000007_OUT
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
                        * field for DebitNo
                        */

                        
                            protected java.lang.String localDebitNo ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localDebitNoTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getDebitNo(){
                               return localDebitNo;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param DebitNo
                               */
                               public void setDebitNo(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localDebitNoTracker = true;
                                       } else {
                                          localDebitNoTracker = false;
                                              
                                       }
                                   
                                            this.localDebitNo=param;
                                    

                               }
                            

                        /**
                        * field for KnotSeqNo
                        */

                        
                            protected java.lang.String localKnotSeqNo ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localKnotSeqNoTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getKnotSeqNo(){
                               return localKnotSeqNo;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param KnotSeqNo
                               */
                               public void setKnotSeqNo(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localKnotSeqNoTracker = true;
                                       } else {
                                          localKnotSeqNoTracker = false;
                                              
                                       }
                                   
                                            this.localKnotSeqNo=param;
                                    

                               }
                            

                        /**
                        * field for TransStat
                        */

                        
                            protected java.lang.String localTransStat ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localTransStatTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getTransStat(){
                               return localTransStat;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param TransStat
                               */
                               public void setTransStat(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localTransStatTracker = true;
                                       } else {
                                          localTransStatTracker = false;
                                              
                                       }
                                   
                                            this.localTransStat=param;
                                    

                               }
                            

                        /**
                        * field for ErrMsg
                        */

                        
                            protected java.lang.String localErrMsg ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localErrMsgTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getErrMsg(){
                               return localErrMsg;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param ErrMsg
                               */
                               public void setErrMsg(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localErrMsgTracker = true;
                                       } else {
                                          localErrMsgTracker = false;
                                              
                                       }
                                   
                                            this.localErrMsg=param;
                                    

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
                       FMT_CRMS_SVR_S01501070000007_OUT.this.serialize(parentQName,factory,xmlWriter);
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
                 if (localDebitNoTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"DebitNo", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"DebitNo");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("DebitNo");
                                    }
                                

                                          if (localDebitNo==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("DebitNo cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localDebitNo);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localKnotSeqNoTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"KnotSeqNo", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"KnotSeqNo");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("KnotSeqNo");
                                    }
                                

                                          if (localKnotSeqNo==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("KnotSeqNo cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localKnotSeqNo);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localTransStatTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"TransStat", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"TransStat");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("TransStat");
                                    }
                                

                                          if (localTransStat==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("TransStat cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localTransStat);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localErrMsgTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"ErrMsg", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"ErrMsg");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("ErrMsg");
                                    }
                                

                                          if (localErrMsg==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("ErrMsg cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localErrMsg);
                                            
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

                 if (localDebitNoTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "DebitNo"));
                                 
                                        if (localDebitNo != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localDebitNo));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("DebitNo cannot be null!!");
                                        }
                                    } if (localKnotSeqNoTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "KnotSeqNo"));
                                 
                                        if (localKnotSeqNo != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localKnotSeqNo));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("KnotSeqNo cannot be null!!");
                                        }
                                    } if (localTransStatTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "TransStat"));
                                 
                                        if (localTransStat != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localTransStat));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("TransStat cannot be null!!");
                                        }
                                    } if (localErrMsgTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "ErrMsg"));
                                 
                                        if (localErrMsg != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localErrMsg));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("ErrMsg cannot be null!!");
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
        public static FMT_CRMS_SVR_S01501070000007_OUT parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            FMT_CRMS_SVR_S01501070000007_OUT object =
                new FMT_CRMS_SVR_S01501070000007_OUT();

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
                    
                            if (!"FMT_CRMS_SVR_S01501070000007_OUT".equals(type)){
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                                return (FMT_CRMS_SVR_S01501070000007_OUT)ExtensionMapper.getTypeObject(
                                     nsUri,type,reader);
                              }
                        

                  }

                }
                

                
                // Note all attributes that were handled. Used to differ normal attributes
                // from anyAttributes.
                java.util.Vector handledAttributes = new java.util.Vector();
                

                 
                    
                    reader.next();
                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","DebitNo").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setDebitNo(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","KnotSeqNo").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setKnotSeqNo(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","TransStat").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setTransStat(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","ErrMsg").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setErrMsg(
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
           
          

        public static class FMT_CRMS_SVR_S01501070000007_IN
        implements org.apache.axis2.databinding.ADBBean{
        /* This type was generated from the piece of schema that had
                name = FMT_CRMS_SVR_S01501070000007_IN
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
                        * field for DebitNo
                        */

                        
                            protected java.lang.String localDebitNo ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localDebitNoTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getDebitNo(){
                               return localDebitNo;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param DebitNo
                               */
                               public void setDebitNo(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localDebitNoTracker = true;
                                       } else {
                                          localDebitNoTracker = false;
                                              
                                       }
                                   
                                            this.localDebitNo=param;
                                    

                               }
                            

                        /**
                        * field for ExtenAgrNo
                        */

                        
                            protected java.lang.String localExtenAgrNo ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localExtenAgrNoTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getExtenAgrNo(){
                               return localExtenAgrNo;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param ExtenAgrNo
                               */
                               public void setExtenAgrNo(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localExtenAgrNoTracker = true;
                                       } else {
                                          localExtenAgrNoTracker = false;
                                              
                                       }
                                   
                                            this.localExtenAgrNo=param;
                                    

                               }
                            

                        /**
                        * field for KnotTradeNo
                        */

                        
                            protected java.lang.String localKnotTradeNo ;
                        
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localKnotTradeNoTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getKnotTradeNo(){
                               return localKnotTradeNo;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param KnotTradeNo
                               */
                               public void setKnotTradeNo(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localKnotTradeNoTracker = true;
                                       } else {
                                          localKnotTradeNoTracker = false;
                                              
                                       }
                                   
                                            this.localKnotTradeNo=param;
                                    

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
                       FMT_CRMS_SVR_S01501070000007_IN.this.serialize(parentQName,factory,xmlWriter);
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
                 if (localDebitNoTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"DebitNo", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"DebitNo");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("DebitNo");
                                    }
                                

                                          if (localDebitNo==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("DebitNo cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localDebitNo);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localExtenAgrNoTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"ExtenAgrNo", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"ExtenAgrNo");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("ExtenAgrNo");
                                    }
                                

                                          if (localExtenAgrNo==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("ExtenAgrNo cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localExtenAgrNo);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localKnotTradeNoTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"KnotTradeNo", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"KnotTradeNo");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("KnotTradeNo");
                                    }
                                

                                          if (localKnotTradeNo==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("KnotTradeNo cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localKnotTradeNo);
                                            
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

                 if (localDebitNoTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "DebitNo"));
                                 
                                        if (localDebitNo != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localDebitNo));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("DebitNo cannot be null!!");
                                        }
                                    } if (localExtenAgrNoTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "ExtenAgrNo"));
                                 
                                        if (localExtenAgrNo != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localExtenAgrNo));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("ExtenAgrNo cannot be null!!");
                                        }
                                    } if (localKnotTradeNoTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "KnotTradeNo"));
                                 
                                        if (localKnotTradeNo != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localKnotTradeNo));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("KnotTradeNo cannot be null!!");
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
        public static FMT_CRMS_SVR_S01501070000007_IN parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            FMT_CRMS_SVR_S01501070000007_IN object =
                new FMT_CRMS_SVR_S01501070000007_IN();

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
                    
                            if (!"FMT_CRMS_SVR_S01501070000007_IN".equals(type)){
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                                return (FMT_CRMS_SVR_S01501070000007_IN)ExtensionMapper.getTypeObject(
                                     nsUri,type,reader);
                              }
                        

                  }

                }
                

                
                // Note all attributes that were handled. Used to differ normal attributes
                // from anyAttributes.
                java.util.Vector handledAttributes = new java.util.Vector();
                

                 
                    
                    reader.next();
                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","DebitNo").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setDebitNo(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","ExtenAgrNo").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setExtenAgrNo(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","KnotTradeNo").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setKnotTradeNo(
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
           
          

        public static class S01501070000007Response
        implements org.apache.axis2.databinding.ADBBean{
        
                public static final javax.xml.namespace.QName MY_QNAME = new javax.xml.namespace.QName(
                "http://www.adtec.com.cn",
                "S01501070000007Response",
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

                        
                            protected FMT_CRMS_SVR_S01501070000007_OUT localResponseBody ;
                        

                           /**
                           * Auto generated getter method
                           * @return FMT_CRMS_SVR_S01501070000007_OUT
                           */
                           public  FMT_CRMS_SVR_S01501070000007_OUT getResponseBody(){
                               return localResponseBody;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param ResponseBody
                               */
                               public void setResponseBody(FMT_CRMS_SVR_S01501070000007_OUT param){
                            
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
                       S01501070000007Response.this.serialize(MY_QNAME,factory,xmlWriter);
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
        public static S01501070000007Response parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            S01501070000007Response object =
                new S01501070000007Response();

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
                    
                            if (!"S01501070000007Response".equals(type)){
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                                return (S01501070000007Response)ExtensionMapper.getTypeObject(
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
                                    
                                   
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());
                                }
                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                    
                    if (reader.isStartElement() && new javax.xml.namespace.QName("","ResTranHeader").equals(reader.getName())){
                
                        object.setResTranHeader(FMT_SOAP_UTF8_ResTranHeader.Factory.parse(reader));
                      
                        reader.next();
                                    
                                  
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());
                                }
                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                    
                    if (reader.isStartElement() && new javax.xml.namespace.QName("","ResponseBody").equals(reader.getName())){
                
                        object.setResponseBody(FMT_CRMS_SVR_S01501070000007_OUT.Factory.parse(reader));
                      
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
           
          
            private  org.apache.axiom.om.OMElement  toOM(com.primeton.crmsgj.client.S01501070000007ServiceStub.S01501070000007 param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(com.primeton.crmsgj.client.S01501070000007ServiceStub.S01501070000007.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(com.primeton.crmsgj.client.S01501070000007ServiceStub.S01501070000007Response param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(com.primeton.crmsgj.client.S01501070000007ServiceStub.S01501070000007Response.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
                            
                            private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, com.primeton.crmsgj.client.S01501070000007ServiceStub.S01501070000007 param, boolean optimizeContent)
                            throws org.apache.axis2.AxisFault{

                                 
                                        try{

                                                org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                                                emptyEnvelope.getBody().addChild(param.getOMElement(com.primeton.crmsgj.client.S01501070000007ServiceStub.S01501070000007.MY_QNAME,factory));
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
        
                if (com.primeton.crmsgj.client.S01501070000007ServiceStub.S01501070000007.class.equals(type)){
                
                           return com.primeton.crmsgj.client.S01501070000007ServiceStub.S01501070000007.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (com.primeton.crmsgj.client.S01501070000007ServiceStub.S01501070000007Response.class.equals(type)){
                
                           return com.primeton.crmsgj.client.S01501070000007ServiceStub.S01501070000007Response.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
        } catch (java.lang.Exception e) {
        throw org.apache.axis2.AxisFault.makeFault(e);
        }
           return null;
        }



    
   }
   