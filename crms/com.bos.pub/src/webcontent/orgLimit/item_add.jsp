<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): wangshichun@git.com.cn
  - Date: 2014-03-13
  - Description:TB_SYS_BAL_CONTROL, com.bos.pub.sys.TbSysBalControl
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<%-- #{hiddenData} --%>
	<div class="nui-dynpanel" columns="4">
		<label>机构编码：</label>
		<input id="orgcode" name="tbSysBalControl.orgnizastionCd" required="false" class="nui-buttonEdit" onbuttonclick="selectEmpOrgCode" />
		
		<label>机构名称：</label>
		<input id="orgname" name="tbSysBalControl.orgnizastionName" required="false" allowInput="false" class="nui-buttonEdit" onbuttonclick="selectEmpOrg" />
		<label>机构层级：</label>
		<input id="levle" name="tbSysBalControl.orgnizastionLevle" required="false"  class="nui-dictcombobox nui-form-input"  dictTypeId="CDZZ0002"/>
		
		<label>生效日期：</label>
		<input name="tbSysBalControl.effectiveDate" required="true" class="nui-datepicker nui-form-input" />

		<label>机构总限额：</label>
		<input name="tbSysBalControl.orgnizistionBal" required="true" class="nui-textbox nui-form-input" vtype="maxLength:26" />

		
	</div>
</div>
				
<div class="nui-toolbar" style="border-bottom:0;padding-right:20px;text-align:right;">
	<a class="nui-button" iconCls="icon-save" onclick="save()">保存</a>
	<a class="nui-button"  iconCls="icon-close" onclick="CloseWindow()">关闭</a>
</div>
	    
			
    <script type="text/javascript">
	 	nui.parse();
	    var form = new nui.Form("#form1");
	    
function save() {
	form.validate();
	if (form.isValid() == false) {
		nui.alert("请将信息填写完整");
		return;
	}
	
	//nui.alert(json);return;
	var code=nui.get("orgcode").getValue();
	var name=nui.get("orgname").getValue();
	var lev=nui.get("levle").getValue();
	//如果机构编号或者机构名称不为空
	if(code!="" || name!=""){
				//机构层级也不为空
				if(lev!=""){
					  alert("确定机构后，不能再设置机构层级");
					  nui.get("levle").setValue("");
					  return;
				}else{//机构层级为空
							var o=form.getData();
							var json=nui.encode(o);
							alert(json);
								$.ajax({
								        url: "com.bos.pub.orgLimit.addTbSysBalControl.biz.ext",
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
				}
	  
	}
	var levle=nui.get("levle").getValue();
	//如果机构编号和机构名称都为空
	if(code=="" && name==""){
		if(levle==""){//机构层级也为空
			alert("（机构编码、机构名称）和机构层级必须填写一样");
			return;
		}else{//机构层级不为空
					var o=form.getData();
					var json=nui.encode(o);
					$.ajax({
					        url: "com.bos.pub.orgLimit.addTbSysBalControl.biz.ext",
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
		
		}
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
                    if (data) {
                        btnEdit.setValue(data.orgid);
                        btnEdit.setText(data.orgname);
                        // 添加机构编码
                        nui.get("orgcode").setValue(data.orgcode);
                         nui.get("orgcode").setText(data.orgcode);
                    }
                }
            }
        });            
    }
    //机构编码
    function selectEmpOrgCode(e) {
        var btnEdit = this;
        nui.open({
            url: nui.context + "/pub/orgLimit/org_code_tree.jsp",
            showMaxButton: true,
            title: "选择机构",
            width: 350,
            height: 450,
            ondestroy: function (action) {            
                if (action == "ok") {
                    var iframe = this.getIFrameEl();
                    var data = iframe.contentWindow.GetData();
                    data = nui.clone(data);
                    if (data) {
                        btnEdit.setValue(data.orgcode);
                        btnEdit.setText(data.orgcode);
                        // 添加机构名称
                        nui.get("orgname").setValue(data.orgid);
                        nui.get("orgname").setText(data.orgname);
                    }
                }
            }
        });            
    }
    
	</script>
</body>
</html>
