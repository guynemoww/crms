<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): 卢金彬
  - Date: 2014-02-14
  - Description:TB_SYS_COLLATERAL_RATE, com.bos.dataset.sys.TbSysCollateralRate
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<input type="hidden" name="item._entity" value="com.bos.dataset.sys.TbSysCollateralRate" class="nui-hidden" />
	<input id="parentId" type="hidden" name="parentId"  class="nui-text" />
	<div class="nui-dynpanel" columns="4">

		<label>担保类型：</label>
		<input name="item.guaranteeType" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:6" dictTypeId="XD_SXCD1185"/>

		<label>担保物属性：</label>
		<input name="item.collateralProperty" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:6" dictTypeId="XD_DBCD3004"/>

		<label>担保物主类：</label>
		<input name="item.collateralMainType" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:6" />

		<label>担保物子类：</label>
		<input name="item.collateralSubType" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:6" />

		
		<label>适用机构：</label>
		<input id="orgs" name="item.applicationOrgId" required="false"  class="nui-buttonEdit" onbuttonclick="selectEmpOrg" dictTypeId="org" vtype="maxLength:32" />
		<label>适用机构级别：</label>
		<input id="orglevel" name="item.applicationOrgLevel" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:6" dictTypeId="XD_GGCD6004" enabled="false"/>

		<label>适用业务种类：</label>
		<input name="item.applicationBusinessType" required="false"  class="nui-buttonEdit" vtype="maxLength:6" onbuttonclick="selectPinZhong"/>

		<label>适用客户信用等级：</label>
		<input name="item.applicationCustCredit" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:6" dictTypeId="XD_KHCD0019"/>

		<label>抵质押率（%）：</label>
		<input id="yalv" name="item.collateralRate" required="true" class="nui-textbox nui-form-input" vtype="maxLength:26;float" onvaluechanged="checkUpLevel()"/>

		<label>生效日期：</label>
		<input id="rateEffectDate" name="item.rateEffectDate" required="true" class="nui-datepicker nui-form-input" />

	</div>
</div>
				
<div class="nui-toolbar" style="border-bottom:0;padding-right:20px;text-align:right;">
	<a class="nui-button" iconCls="icon-save" onclick="save()">保存</a>
	<a class="nui-button"  iconCls="icon-close" onclick="CloseWindow()">关闭</a>
</div>
	    
			
    <script type="text/javascript">
	 	nui.parse();
	    var form = new nui.Form("#form1");
	    
	 //比较时间大小
    function toDate(str){
     var sd=str.split("-");
    return new Date(sd[0],sd[1],sd[2]);
      }
	
	function save() {
		form.validate();
		if (form.isValid() == false) {
			nui.alert("请将信息填写完整");
			return;
		}
	
	   var time;
       var a=new Date();
       //当前时间
       var time1=a.getFullYear() + "-" + (a.getMonth() + 1) + "-" + a.getDate();
        //生效时间
        time=nui.get("rateEffectDate").getValue();
        var time2=time.split(" ");
        var d1=toDate(time2[0]);
		var d2=toDate(time1);
		if(d1>d2){
						var o=form.getData();
						var json=nui.encode(o);
						//nui.alert(json);return;
						$.ajax({
					        url: "com.bos.pub.earlyWarning.addparameters.addItemCollateralRate.biz.ext",
					        type: 'POST',
					        data: json,
					        cache: false,
					        contentType:'text/json',
					        success: function (text) {
					        	if(text.msg){
					        		nui.alert(text.msg);
					        	} else {
					        		CloseWindow("ok");
					        	}
					        },
					        error: function (jqXHR, textStatus, errorThrown) {
					            nui.alert(jqXHR.responseText);
					        }
						});
		}else{
		   alert("生效时间应该晚于当前时间");
		   return;
		}
	
}


function selectPinZhong(e) {
        var btnEdit = this;
        nui.open({
            url: nui.context + "/pub/collateralrate/item_tree.jsp",
            showMaxButton: true,
            title: "选择业务品种",
            width: 230,
            height: 450,
            ondestroy: function (action) {            
                if (action == "ok") {
                    var iframe = this.getIFrameEl();
                    var data = iframe.contentWindow.json;
                    data = nui.clone(data);
                   //  alert(nui.encode(data));
                    if (data) {
                        btnEdit.setValue(data.split(":")[0]);
                       btnEdit.setText(data.split(":")[1]);
                    }
                }
            }
        });            
    }
    //检查上一级的抵押率的大小
    function checkUpLevel(){
      var org; 
      var parterId;
      var yalv;
      org=nui.get("orgs").getValue();
      parterId=nui.get("parentId").getValue();
       yalv=nui.get("yalv").getValue();
      if(org==""){
        alert("请先填写机构");
        nui.get("yalv").setValue("");
      }else{
     
          var  json=nui.encode({"parterId":parterId});
		         $.ajax({
							        url: "com.bos.pub.TbSysCollateralRate.checkParentOrgYaPin.biz.ext",
							        type: 'POST',
							        data: json,
							        cache: false,
							        contentType:'text/json',
							        success: function (text) {
							        	if(text.msg){
							        		nui.alert(text.msg);
							        	} else {
							        	  if(Number(yalv)>Number(text.shuzhi)){
							        	    alert("押率不能大于上级机构的押率");
							        	     nui.get("yalv").setValue("");
							        	  }
							        	}
							        },
							        error: function (jqXHR, textStatus, errorThrown) {
							            nui.alert(jqXHR.responseText);
							        }
								});
        }
    }
    function selectEmpOrg(e) {
        var btnEdit = this;
        nui.open({
            url: nui.context + "/pub/sys/select_org_tree.jsp",
            showMaxButton: true,
            title: "选择机构",
            width: 350,
            height: 450,
            ondestroy: function (action) {            
                if (action == "ok") {
                    var iframe = this.getIFrameEl();
                    var data = iframe.contentWindow.GetData();
                    data = nui.clone(data);
                  //  alert(nui.encode(data));
                    if (data) {
                        btnEdit.setValue(data.orgid);
                        btnEdit.setText(data.orgname);
                         // 添加机构级别
                        nui.get("orglevel").setValue(data.orglevel);
                        if(data.orgid=="10001"){
                        }else{// 此机构的上级机构
                         nui.get("parentId").setValue(data.omOrganization.orgid);
                        }
                       
                    }
                }
            }
        });            
    }
	</script>
</body>
</html>
