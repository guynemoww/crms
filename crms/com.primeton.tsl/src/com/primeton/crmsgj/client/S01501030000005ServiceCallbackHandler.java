
/**
 * S01501030000005ServiceCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.3  Built on : Aug 10, 2007 (04:45:47 LKT)
 */

    package com.primeton.crmsgj.client;

    /**
     *  S01501030000005ServiceCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class S01501030000005ServiceCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public S01501030000005ServiceCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public S01501030000005ServiceCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for S01501030000005 method
            * override this method for handling normal response from S01501030000005 operation
            */
           public void receiveResultS01501030000005(
                    com.primeton.crmsgj.client.S01501030000005ServiceStub.S01501030000005Response result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from S01501030000005 operation
           */
            public void receiveErrorS01501030000005(java.lang.Exception e) {
            }
                


    }
    