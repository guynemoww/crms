<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 王世春
  - Date: 2013-08-18 12:42:24
  - Description:
-->
<head>
<title>查询客户</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<fieldset>
<legend>
    	<span>单笔EVA测算</span>
    </legend>
<div id="form" class="nui-dynpanel" columns="4" style="text-align:left;" style="width:80%;" >
		         <label>贷款金额</label>
		         <input id="loanAmt" required="true"  class="nui-textBox nui-form-input" style="width:80%;" />
		         <label>营业税金及附加率</label>
		         <input id="businessAndAddTax" required="true"  class="nui-textBox nui-form-input" style="width:80%;" />
		         <label>借款人规模</label>
		         <input id="csmScale" name="partyNum" required="true"  class="nui-combobox nui-form-input" emptyText="--请选择--" textField="textName" valueField="paramCode" style="width:80%;" onvaluechanged="setCsmScale2"/>
		         <label id="csmScale2Text">借款人选项</label>
		         <input id="csmScale2" name="partyNum" required="true"  class="nui-combobox nui-form-input" emptyText="--请选择--" textField="textName" valueField="paramCode" style="width:80%;" />
		         <label>授信品种名称</label> 
		         <input id="businessCode" required="true" class="nui-buttonEdit nui-form-input" allowInput="false" onbuttonclick="selectProduct" />
                 <label id="businessDetail" ></label> 
		         <input id="businessDetail2" name="partyNum" required="true" textField="textName" valueField="paramCode" class="nui-combobox nui-form-input" style="width:80%;" />
		         <label>合同剩余期限</label>
		         <input id="csmPeriod" name="contractResiduePeriod" required="true" emptyText="--请选择--" class="nui-combobox nui-form-input" textField="textName" valueField="paramCode" style="width:80%;"  />
		         <label>合同期限</label>
		         <input id="contractPeriod" name="contractPeriod" onvaluechanged="setContractValue()" required="true" emptyText="--请选择--" class="nui-combobox nui-form-input" textField="textName" valueField="paramCode" style="width:80%;"  />
		         <label>贷款利率</label>
		         <input id="loanRate" name="partyNum" required="true"  class="nui-text nui-form-input" style="width:80%;" />
		         <label>内部供应金价格</label>
		         <input id="ftp" name="partyNum" required="true"  class="nui-text nui-form-input" style="width:80%;" />
		         <label>担保方式</label> 
		         <input id="warrantType" required="false"  required="true" emptyText="--请选择--" class="nui-combobox nui-form-input" textField="textName" valueField="paramCode" style="width:80%;" onvaluechanged="setGuaranteeType" />
		         <label id="guaranteeTypeText">担保品类型</label>
		         <input id="guaranteeType" required="true" emptyText="--请选择--" class="nui-combobox nui-form-input" textField="textName" valueField="paramCode" style="width:80%;" />
		         <label>资本充足率</label>
		         <input id="capitalRtio" required="true"  class="nui-text nui-form-input" style="width:80%;" />
		         <label>底线回报率</label>
		         <input id="baseReturn" required="true"  class="nui-textBox nui-form-input" style="width:80%;" />
		         //     
		         <label>贷款投向分类</label> 
		         <input id="loanToType" required="true"  class="nui-combobox nui-form-input" emptyText="--请选择--" textField="textName" valueField="paramCode" style="width:80%;" onvaluechanged="setLoanTo" />
		         <label id="loanText">贷款投向</label>
		         <input id="loanTo" required="true" class="nui-combobox nui-form-input" emptyText="--请选择--" textField="textName" valueField="paramCode" style="width:80%;" onvaluechanged="setLoanTo2"/>
		         <label id="loanText2"></label>
		         <input id="loanTo2" required="true" class="nui-combobox nui-form-input" emptyText="--请选择--" textField="textName" valueField="paramCode" style="width:80%;" />
</div>
<div class="nui-toolbar" style="border-bottom:0;text-align:right;" >
	    <a class="nui-button" iconCls="icon-search" onclick="sum()" id="btnAdd" style="float:left;margin-left: 470px;">计算</a>
</div>

<div class="nui-dynpanel" columns="2" style="text-align:center;" style="width:80%;" >
		    <label>贷款净收益(EVA)</label>
		    <input id="resultEva" required="false"  class="nui-textBox nui-form-input" style="width:80%;" />
</div>
</fieldset>
<fieldset style="margin-top: 10px;">
    <legend>
        <span>计算公式</span>
    </legend>
<div style="border-bottom: 1px;border-bottom-color: black;">
     <Strong>贷款净收益 = 贷款金额 * (贷款利率 - 内部资金供应价格 - 权重 * (1-缓释系数) * 资本充足率 * 底线回报率  - 贷款利率 * 营业税金及附加率)<Strong/>
</div>
</fieldset>

<script type="text/javascript"> 
	nui.parse();
    //var contractResiduePeriod=[{"id": "0.9", "text": "合同剩余期限≤1年" },{ "id": "1.05", "text": "1年<合同剩余期限≤3年" },
    //              {"id": "1.2", "text": "3年<合同剩余期限≤5年" },{ "id": "1.4", "text": "5年<合同剩余期限<10年" },{"id": "1.8", "text": "10年≤合同剩余期限"}];
   // nui.get("contractResiduePeriod").setData(contractResiduePeriod);
   // var contractPeriod=[{"id": "01", "text": "合同期限≤3个月" },{ "id": "02", "text": "3个月<合同期限≤6个月" },{ "id": "03", "text": "6个月<合同期限≤1年" },
      //            {"id": "04", "text": "1年<合同期限≤2年" },{ "id": "05", "text": "2年<合同期限<3年" },{"id": "06", "text": "3年<合同期限≤5年"},{"id": "07", "text": "5年<合同期限"}];
    //nui.get("contractPeriod").setData(contractPeriod);
    //var values=[{"id": "01", "v1": "5.6","v2":"4.44"},{"id": "02", "v1": "5.6","v2":"4.54"},{"id": "03", "v1": "6","v2":"4.96"},
    //                    {"id": "04", "v1": "6.15","v2":"5.05"},{"id": "05", "v1": "6.15","v2":"5.15"},{"id": "06", "v1": "6.4","v2":"5.25"},
    //                    {"id": "07", "v1": "6.55","v2":"5.25"},];
    //var csmScale=[{"id": "01", "text": "一般企业" },{ "id": "02", "text": "小企业（500万元以下小微除外）" },
     //             {"id": "03", "text": "500万元以下（含）的小微企业（企业及企业集团在我行敞口不超过500万元）" },{ "id": "04", "text": "其他企业" }];
   
    //var businessType=[{ "id": "1", "text": "出口托收押汇" },{ "id": "1", "text": "TT出口押汇）" },
          //        { "id": "0.25", "text": "出口信用证押汇" },{ "id": "1", "text": "出口押汇" }];
    var grades=[];
    var listNml = new Array();
    var listSml = new Array();
    var form = new nui.Form("#form");
    var rlst=[];                                              //参数集合
    var csmScale =[];                                         //客户规模
    var csmScale2 = [];                                       //客户其他选项
    var businessCode = [];                                    //业务品种系数
    var creditType = [];                                      //信用证类型
    var billType = [];                                        //押汇类型
    var loanPeriod = [];                                      //贷款承诺函类型
    var loanToType = [];                                      //投向分类
    var csmPeriod = [];                                       //公司客户剩余期限
    var warrantType = [];                                     //担保方式
    var contractPeriod = [];                                  //合同期限
    var ftp = [];                                             //ftp利率
    var loanRate = [];                                        //利率
    var busCode;                                              //业务品种代码
    var capitalRtio;                                          //资本充足率 
    var baseReturn;                                           //底线回报率
    var businessTax;                                          //营业税税率
    var addTax;                                               //附加税
    document.getElementById("businessDetail").style.visibility = "hidden";          //银行类型标题 
    document.getElementById("loanText").style.visibility = "hidden";                //贷款投向大类
    document.getElementById("loanText2").style.visibility = "hidden";               //投向产能过剩类
    document.getElementById("guaranteeTypeText").style.visibility = "hidden";       //担保方式
    document.getElementById("csmScale2Text").style.visibility = "hidden";            //担保方式
    nui.get("csmScale2").hide();                                                     //担保类型
    nui.get("guaranteeType").hide();                                                //担保类型
    nui.get("loanTo").hide();                                                       //具体投向
    nui.get("loanTo2").hide();                                                      //产能过剩具体行业
    
    nui.get("businessDetail2").hide();                        //隐藏品种具体型号
    nui.get("csmScale2").setEnabled(false);                   //初始化禁用借款人选项
    //nui.get("businessDetail").hide();                       //隐藏品种具体型号
    $.ajax({
          url:"rskAnalysis.pageEva.getGrade.biz.ext",
          type:'POST',
          //data:json,
          contentType:'text/json',
          cache:false,
          success:function(mydata){
            rlst=nui.decode(mydata.rlst);
            for(var i =0;i<rlst.length;i++){
                var object = nui.decode(rlst[i]);
                if(object.analysisCode=="csmScale" && object.paramCode.length==4){
                  var index = csmScale.length;
                  csmScale[index] = {"analysisCode":object.analysisCode,"paramCode":object.paramCode,"textName":object.textName,"paramValue":object.paramValue,"markCode":object.markCode,"superCode":object.superCode};
                }else if(object.analysisCode=="csmScale"&&object.paramCode.length==6){
                  var index = csmScale2.length;
                  csmScale2[index] = {"analysisCode":object.analysisCode,"paramCode":object.paramCode,"textName":object.textName,"paramValue":object.paramValue,"markCode":object.markCode,"superCode":object.superCode};
                }else if(object.analysisCode=="busVarieties"){
                  var index = businessCode.length;
                  businessCode[index] = {"analysisCode":object.analysisCode,"paramCode":object.paramCode,"textName":object.textName,"paramValue":object.paramValue,"markCode":object.markCode,"superCode":object.superCode};
                }else if(object.analysisCode=="Loans"){
                  var index = loanToType.length;
                  loanToType[index] = {"analysisCode":object.analysisCode,"paramCode":object.paramCode,"textName":object.textName,"paramValue":object.paramValue,"markCode":object.markCode,"superCode":object.superCode};
                }else if(object.analysisCode=="csmPeriod"){
                  var index = csmPeriod.length;   
                  csmPeriod[index] = {"analysisCode":object.analysisCode,"paramCode":object.paramCode,"textName":object.textName,"paramValue":object.paramValue,"markCode":object.markCode,"superCode":object.superCode};
                }else if(object.analysisCode=="warrantType"){
                  var index = warrantType.length;
                  warrantType[index] = {"analysisCode":object.analysisCode,"paramCode":object.paramCode,"textName":object.textName,"paramValue":object.paramValue,"markCode":object.markCode,"superCode":object.superCode};
                }else if(object.analysisCode=="contractPeriod"){
                  var index = contractPeriod.length;
                  contractPeriod[index] = {"analysisCode":object.analysisCode,"paramCode":object.paramCode,"textName":object.textName,"paramValue":object.paramValue,"markCode":object.markCode,"superCode":object.superCode};
                }else if(object.analysisCode=="ftp"){
                  var index = ftp.length;
                  ftp[index] = {"analysisCode":object.analysisCode,"paramCode":object.paramCode,"textName":object.textName,"paramValue":object.paramValue,"markCode":object.markCode,"superCode":object.superCode};
                }else if(object.analysisCode=="loanRate"){
                  var index = loanRate.length;
                  loanRate[index] = {"analysisCode":object.analysisCode,"paramCode":object.paramCode,"textName":object.textName,"paramValue":object.paramValue,"markCode":object.markCode,"superCode":object.superCode};
                }else if(object.analysisCode=="capitalRtio"){
                  capitalRtio = object.paramValue;
                }else if(object.analysisCode=="baseReturn"){
                  baseReturn = object.paramValue;
                }else if(object.analysisCode=="businessTax"){
                  businessTax = object.paramValue;
                }else if(object.analysisCode=="addTax"){
                  addTax = object.paramValue;
                }
                
            }
            
            for(var i=0;i<businessCode.length;i++){
                var object = nui.decode(businessCode[i]);
                if(object.analysisCode=="busVarieties" && object.superCode=="10300101"){                                              //装载押汇类型
                  var index = billType.length;
                  billType[index] = {"analysisCode":object.analysisCode,"paramCode":object.paramCode,"textName":object.textName,"paramValue":object.paramValue,"markCode":object.markCode,"superCode":object.superCode};
                }else if(object.analysisCode=="busVarieties" && object.superCode=="10300401"){                                        //装载信用证类型
                  var index = creditType.length;
                  creditType[index] = {"analysisCode":object.analysisCode,"paramCode":object.paramCode,"textName":object.textName,"paramValue":object.paramValue,"markCode":object.markCode,"superCode":object.superCode};
                }else if(object.analysisCode=="busVarieties" && object.superCode=="10700101"){                                        //装载贷款承诺期限
                  var index = loanPeriod.length;
                  loanPeriod[index] = {"analysisCode":object.analysisCode,"paramCode":object.paramCode,"textName":object.textName,"paramValue":object.paramValue,"markCode":object.markCode,"superCode":object.superCode};
                }
            }
            
             //     businessTax addTax
             nui.get("capitalRtio").setValue(capitalRtio);
             nui.get("baseReturn").setValue(baseReturn);
             nui.get("csmScale").setData(csmScale);
             nui.get("csmPeriod").setData(csmPeriod);
             nui.get("contractPeriod").setData(contractPeriod);
             //alert(nui.encode(csmScale));
             initLoanTo();
             initWarrantType();
             businessTax=(businessTax.split("%")[0])/100;
             addTax=(addTax.split("%")[0])/100;
             var businessAndAddTax = ((businessTax + addTax)*100)+"%";
             nui.get("businessAndAddTax").setValue(businessAndAddTax);
            }
          })

function sum(){
      form.validate();
      if(form.isValid()==false){
         alert("请完善填写！");
         return;
      }
      var rlstAmt = nui.get("loanAmt").value;                                //贷款金额
      var rlstLoanRate = nui.get("loanRate").value;                          //贷款利率
      var rlstFtp = nui.get("ftp").value;                                    //FTP价格
      var rlstCsmScale;                                                      //借款人系数
      var rlstBusiness;                                                      //业务品种系数
      var rlstLoanTo;                                                        //投向系数 loanToType loanTo loanTo2
      var rlstCsmPeriod;                                                     //剩余期限系数
      var rlstWarrantType;                                                   //缓释系数    
      
      
       
      
      if(nui.get("guaranteeType").value != null && nui.get("guaranteeType").value != "null" && nui.get("guaranteeType").value != ""){
        for(var i=0;i<warrantType.length;i++){
            if(warrantType[i].paramCode==nui.get("guaranteeType").value){
               rlstWarrantType=warrantType[i].paramValue;
            }
        }
      }else if(nui.get("warrantType").value != null && nui.get("warrantType").value != "null" && nui.get("warrantType").value != ""){
        for(var i=0;i<warrantType.length;i++){
            if(warrantType[i].paramCode==nui.get("warrantType").value){
               rlstWarrantType=warrantType[i].paramValue;
            }
        }
      } 
      //获取剩余期限系数
      for(var i=0;i<csmPeriod.length;i++){
         if(csmPeriod[i].paramCode==nui.get("csmPeriod").value){
            rlstCsmPeriod=csmPeriod[i].paramValue;
         }
      }
      //获取贷款投向系数
      if(nui.get("loanTo2").value != null && nui.get("loanTo2").value != "null" && nui.get("loanTo2").value != ""){
        for(var i=0;i<loanToType.length;i++){
            if(loanToType[i].paramCode==nui.get("loanTo2").value){
               rlstLoanTo=loanToType[i].paramValue;
            }
        }
      }else if(nui.get("loanTo").value != null && nui.get("loanTo").value != "null" && nui.get("loanTo").value != ""){
        for(var i=0;i<loanToType.length;i++){
            if(loanToType[i].paramCode==nui.get("loanTo").value){
               rlstLoanTo=loanToType[i].paramValue;
            }
        }
      }else if(nui.get("loanToType").value != null && nui.get("loanToType").value != "null" && nui.get("loanToType").value != ""){
        for(var i=0;i<loanToType.length;i++){
            if(loanToType[i].paramCode==nui.get("loanToType").value){
               rlstLoanTo=loanToType[i].paramValue;
            }
        }
      }
      //获取授信品种系数  businessCode businessDetail2
      for(var i =0;i<businessCode.length;i++){
          if(businessCode[i].paramValue != "null" && businessCode[i].paramValue != null){
               if(businessCode[i].paramCode == busCode || businessCode[i].paramCode == nui.get("businessDetail2").value){
                    
                    rlstBusiness=businessCode[i].paramValue;
                    break;
               }
          }
      }
      
      //获取借款人系数值
      for(var i =0;i<csmScale.length;i++){                                       
          if(csmScale[i].paramCode == nui.get("csmScale").value){
             rlstCsmScale=csmScale[i].paramValue;
             break;
          }
      }
      if(rlstCsmScale=="null"||rlstCsmScale==null){
         for(var i =0;i<csmScale2.length;i++){
          if(csmScale2[i].paramCode == nui.get("csmScale2").value){
             rlstCsmScale=csmScale2[i].paramValue;
             break;
          }
         }
      }

    var businessAndAddTax = nui.get("businessAndAddTax").value; 
     rlstLoanRate = (rlstLoanRate.split("%")[0])/100;
     rlstFtp = (rlstFtp.split("%")[0])/100;
     rlstCsmScale = (rlstCsmScale.split("%")[0])/100;
     rlstBusiness = (rlstBusiness.split("%")[0])/100;
     rlstLoanTo = (rlstLoanTo.split("%")[0])/100;
     rlstCsmPeriod = (rlstCsmPeriod.split("%")[0])/100;
     rlstWarrantType = (rlstWarrantType.split("%")[0])/100;
     
     capitalRtio=(capitalRtio.split("%")[0])/100;
     baseReturn=(baseReturn.split("%")[0])/100;
     businessAndAddTax=(businessAndAddTax.split("%")[0])/100;
      var evaResult=rlstAmt*(rlstLoanRate-rlstFtp-(rlstAmt*(rlstCsmScale*rlstBusiness*rlstLoanTo*rlstCsmPeriod)*baseReturn*capitalRtio*(1-rlstWarrantType))/rlstAmt-(rlstLoanRate*businessAndAddTax));
      //var evaResult=(1-rlstWarrantType);
      nui.get("resultEva").setValue(evaResult);                                                      //EVA测算结果
}

function initWarrantType(){
                var temp = [];
                for(var i=0;i<warrantType.length;i++){
                    var object = nui.decode(warrantType[i]);
                    if(object.paramCode.length==4){
                       var index = temp.length;
                            temp[index] = {"analysisCode":object.analysisCode,"paramCode":object.paramCode,"textName":object.textName,"paramValue":object.paramValue,"markCode":object.markCode,"superCode":object.superCode};
                    }
                }
                nui.get("warrantType").setData(temp);
}

function initLoanTo(){
                var temp = [];
                for(var i=0;i<loanToType.length;i++){
                    var object = nui.decode(loanToType[i]);
                    if(object.paramCode.length==3){
                       var index = temp.length;
                       temp[index] = {"analysisCode":object.analysisCode,"paramCode":object.paramCode,"textName":object.textName,"paramValue":object.paramValue,"markCode":object.markCode,"superCode":object.superCode};
                    }
                }
                nui.get("loanToType").setData(temp);
            }

function setGuaranteeType(){
   var guaranteeWay = nui.get("warrantType").value;
   nui.get("guaranteeType").setValue("");       
   var temp = [];
                for(var i=0;i<warrantType.length;i++){
                    var object = nui.decode(warrantType[i]);
                    if(object.paramCode.length >4){
                       if(object.superCode==guaranteeWay){
                          var index = temp.length;
                          temp[index] = {"analysisCode":object.analysisCode,"paramCode":object.paramCode,"textName":object.textName,"paramValue":object.paramValue,"markCode":object.markCode,"superCode":object.superCode};
                      }
                    }
                }
                if(temp.length !=0){
                   document.getElementById("guaranteeTypeText").style.visibility = "visible";          //担保方式
                   nui.get("guaranteeType").show();                                                    //担保类型
                   nui.get("guaranteeType").setData(temp);
                }else{
                   document.getElementById("guaranteeTypeText").style.visibility = "hidden";          //担保方式
                   
                   nui.get("guaranteeType").hide(); 
                }
}

function setContractValue(){
   var value=nui.get("contractPeriod").value;
   var fv;                                                                                              //ftp利率
   var lrv;                                                                                             //利率
   nui.get("loanRate").setValue("");                                                                    //赋值前先清空老数据
   nui.get("ftp").setValue("");                                                                         //赋值前先清空老数据                
   for(var i=0;i<ftp.length;i++){
      if(ftp[i].paramCode==value){
        nui.get("ftp").setValue(ftp[i].paramValue);
      }
   }
   for(var i=0;i<loanRate.length;i++){
      if(ftp[i].paramCode==value){
        nui.get("loanRate").setValue(loanRate[i].paramValue);
      }
   }
}          

function setLoanTo2(){                                                     //
   var rslt = nui.get("loanTo").value;
   var rlstText = nui.get("loanTo").text;
   nui.get("loanTo2").setValue("");
   var str = rlstText.split(".")[1];
   var loanToTypeDetail = [];
   if(rslt.length >4){
      for(var i=0;i<loanToType.length;i++){
                var object = nui.decode(loanToType[i]);
                if(object.paramCode.length == 7 && object.superCode==rslt){
                  var index = loanToTypeDetail.length;
                  loanToTypeDetail[index] = {"analysisCode":object.analysisCode,"paramCode":object.paramCode,"textName":object.textName,"paramValue":object.paramValue,"markCode":object.markCode,"superCode":object.superCode};
             }
        }
   }
   if(loanToTypeDetail.length==0){
              document.getElementById("loanText2").style.visibility = "hidden";
              nui.get("loanTo2").hide();
   }else{
              document.getElementById("loanText2").style.visibility = "visible";
              document.getElementById("loanText2").innerHTML = str;
              nui.get("loanTo2").show();
              nui.get("loanTo2").setData(loanToTypeDetail);
   }
}

function setLoanTo(){                                                       //加载具体贷后投向
   var rslt = nui.get("loanToType").value;
   nui.get("loanTo").setValue(""); 
   nui.get("loanTo2").setValue("");
   //alert(rslt);
   var loanToTypeDetail = [];
   for(var i=0;i<loanToType.length;i++){
                var object = nui.decode(loanToType[i]);
                if(object.markCode != 1 || object.superCode !=null || object.superCode!= ""){
                  if(object.superCode==rslt){                                              //装载押汇类型
                          var index = loanToTypeDetail.length;
                          loanToTypeDetail[index] = {"analysisCode":object.analysisCode,"paramCode":object.paramCode,"textName":object.textName,"paramValue":object.paramValue,"markCode":object.markCode,"superCode":object.superCode};
                  }
             }
        }
        if(loanToTypeDetail.length !=0){
              document.getElementById("loanText").style.visibility = "visible";
              nui.get("loanTo").show();
              nui.get("loanTo").setData(loanToTypeDetail);
              document.getElementById("loanText2").style.visibility = "hidden";
              nui.get("loanTo2").hide();
        }else{
              document.getElementById("loanText").style.visibility = "hidden";
              nui.get("loanTo").hide();   
              document.getElementById("loanText2").style.visibility = "hidden";
              nui.get("loanTo2").hide();
        }
}

function setBusiness(e){                                                   //设置品种具体类型
   var businessCode = e;
   nui.get("businessDetail2").setValue("");
   busCode = e;
   if(businessCode=="10300101"){
      document.getElementById("businessDetail").innerHTML="押汇类型";
      document.getElementById("businessDetail").style.visibility="visible";
      nui.get("businessDetail2").show();
      nui.get("businessDetail2").setData(billType);
   }else if(businessCode=="10300401"){
      document.getElementById("businessDetail").innerHTML="信用证类型";
      document.getElementById("businessDetail").style.visibility="visible";
      nui.get("businessDetail2").show();
      nui.get("businessDetail2").setData(creditType);
   }else if(businessCode=="10700101"){
      document.getElementById("businessDetail").innerHTML="贷款承诺函类型";
      document.getElementById("businessDetail").style.visibility="visible";
      nui.get("businessDetail2").show();
      nui.get("businessDetail2").setData(loanPeriod);
   }else{
      document.getElementById("businessDetail").style.visibility = "hidden";  //银行类型标题
      nui.get("businessDetail2").hide();                        //隐藏品种具体型号
   }
}

function setCsmScale2(){
   var csmScaleRslt = nui.get("csmScale").value;
   //alert("csmScaleRslt:"+csmScaleRslt);
   nui.get("csmScale2").setValue("");
  var temp=[];
  for(var i=0;i<csmScale2.length;i++){
    // alert(nui.encode(csmScale2[i]));
     if(csmScale2[i].superCode==csmScaleRslt){
         var index = temp.length;
         temp[index] = csmScale2[i];
     }
  }
  if(temp.length!=0){
   document.getElementById("csmScale2Text").style.visibility = "visible";            //显示借款人
   nui.get("csmScale2").show();                                                      //显示借款人
   nui.get("csmScale2").setEnabled(true);                    //有选项则不禁用借款人选项
   nui.get("csmScale2").setData(temp);
  }else{
   document.getElementById("csmScale2Text").style.visibility = "hidden";            //隐藏借款人具体类型
   nui.get("csmScale2").hide();                                                     //隐藏借款人具体类型
  }
   //alert(nui.encode(temp));
}

function selectProduct(e) {
   var btnEdit = this;
        nui.open({
            url: nui.context + "/pub/product/rule/product.jsp",
            title: "选择",
            width: 200,
            height: 450,
            ondestroy: function (action) {
                if (action == "ok") {
                    var iframe = this.getIFrameEl();
                    var data = iframe.contentWindow.GetData();
                    data = nui.clone(data);
                    if (data) {
                        btnEdit.setValue(data.split(":")[1]);//alert(nui.encode(data));
                        btnEdit.setText(data.split(":")[1]);
                        //alert("data:"+data.split(":")[0]);
                        setBusiness(data.split(":")[0]);
                        
                    }
                }
                if (action == "clear") { //清空选择的内容
                	btnEdit.setValue("");
                	btnEdit.setText("");
                	
            	}
            	
        	},
        	onload: function () {
                        var iframe = this.getIFrameEl();
                        //iframe.contentWindow.save();
                        //this.max();//最大化窗口
                    }
        	
        });            
	}


    
</script>
</body>
</html>