<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 王世春
  - Date: 2013-08-18 16:18:58
  - Description:
-->
<head>
<title>客户内部评级信息 - 意见</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<div id="panel1" class="nui-panel" title="评级意见(总行时直接结束流程，否则要经过审批)"
	style="width:100%;height:auto;" showToolbar="false"
	showCollapseButton="true" showFooter="false" allowResize="true">
	<div id="form1" style="width:100%;height:auto;overflow:hidden;">
		<table style="width:100%;height:auto;table-layout:fixed;" class="nui-form-table">
			<tr>
				<td>
				<input class="nui-hidden" id="it" name="it"/>
				<input class="nui-hidden" id="it.nextOrgCd" name="it.nextOrgCd"/>
				<input class="nui-hidden" id="it.nextOrgName" name="it.nextOrgName"/>
				<input class="nui-hidden" id="it.nextUsersName" name="it.nextUsersName"/>
				<input id="it.opinion" name="it.opinion" class="nui-textarea nui-form-input"
					required="true" maxLength="512" style="width:90%;"/></td>
			</tr>
			<tr>
			</tr>
			<tr>
				<td><label id="selectuserLabel" class="nui-form-label">请选择： </label>
					<input id="selectuser" name="it.nextUsersNum" textName="it.nextUsersName"
						allowInput="false" class="nui-buttonEdit" onbuttonclick="selectEmpOrg"/>
				</td>
			</tr>
		</table>
	</div>

	<div class="nui-toolbar" style="text-align:center;padding-top:5px;padding-bottom:5px;" 
    	borderStyle="border:0;">
	    <a class="nui-button" onclick="submitEval" id="btn_submitEval">提交</a>
	    <a class="nui-button" iconCls="icon-save" onclick="updateEvalAdvice">临时保存</a>
	    <a class="nui-button" onclick="abortEval" id="btn_abortEval">撤销</a>
	    <input id="back" class="nui-combobox" style="width:150px;" textField="dactivityInstName" 
	    	valueField="dactivityInstId" emptyText="请选择..."
			data="NULL_ARRAY" value=""  required="false" allowInput="false" 
			showNullItem="true" nullItemText="请选择.." onitemclick="backone"/>   
	</div>
</div>
<script type="text/javascript">
	nui.parse(document.getElementById("panel1"));
	//提交审批逻辑流路径
	var form = new nui.Form("#form1");
    nui.get("selectuser").hide();
    nui.get("back").hide();
    nui.get("btn_abortEval").hide();
    document.getElementById("selectuserLabel").style.display="none";
    //初始化工作项信息
	function getInit() {
		var json = nui.encode({"o/bizId":"<%=request.getParameter("bizId") %>"});
        $.ajax({
            url: "com.bos.bps.flow.findSingleWork.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            cache: false,
            success: function (mydata) {
                var o = nui.decode(mydata);//alert(nui.encode(o));
                form.setData(o);
                window['formData'] = o;
                
                initBack();
                initSelect();
            }
        });
    }
    getInit();
    //初始化，是否显示人员/角色选择框
    function initSelect() {
    	var o = form.getData();
        var json = nui.encode(o);//alert(json);
        
    	$.ajax({
        url: "com.bos.bps.flow.getNextNodeExtendAttibute.biz.ext",
        type: 'POST',
        data: json,
        cache: false,
        contentType:'text/json',
        success: function (text) {
        	if(text&&text.map){
        		if (text.map.type=='finish') {
        			//无需选人
        			nui.get("selectuser").hide();
        			document.getElementById("selectuserLabel").style.display="none";
        		} else {
        			window['bps_attr_map'] = text.map;
        		
        			//得到选择方式
        			var chooseFlag = getChooseFlag();
        			//如果是选择角色，则不需要展示人员选择列表
        			if(null!=chooseFlag && '1'==chooseFlag){
        				return;
        			}else{
        				nui.get("selectuser").show();
        				document.getElementById("selectuserLabel").style.display="";
        			}
        		}
        	}
        },
        error: function (jqXHR, textStatus, errorThrown) {
            nui.alert(jqXHR.responseText);
        }
        });
    }
    
    //获取选择方式事件
    function getChooseFlag(){
    	//从待办列表中传过来，默认选人
    	var chooseFlag =2;
    	chooseFlag= <%=request.getParameter("chooseFlag") %>;
    	if(null != chooseFlag && ''!=chooseFlag){
    		
    		return chooseFlag;
    	}else{
    		var o = form.getData();
    		var json = nui.encode(o);//alert(json);return;
            $.ajax({
                url: "com.bos.bps.flow.getCurrentNodeExtendAttibute.biz.ext",
                type: 'POST',
                data: json,
                cache: false,
                async: true,
                contentType:'text/json',
                success: function (data) {
                	if (null != data.map) {
                		//获取选择方式
                		chooseFlag = data.map.chooseFlag;
                		//如果是选择角色，则不需要展示人员选择列表
	        			if(null!=chooseFlag && '1'==chooseFlag){
	        				nui.get("selectuser").hide();
	        				document.getElementById("selectuserLabel").style.display="none";
	        			}else{
	        				nui.get("selectuser").show();
	        				document.getElementById("selectuserLabel").style.display="";
	        			}
                	}
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    nui.alert(jqXHR.responseText);
                }
            });
    	}
    	
    	return chooseFlag;
    }
    
    //选择机构
    function selectEmpOrg(e) {
        var btnEdit = this;
        nui.open({
            url: nui.context + "/utp/org/employee/select_bps_user.jsp?map="+escape(nui.encode(window['bps_attr_map'])),
            showMaxButton: false,
            title: "选择经手人/审查审批人",
            width: 350,
            height: 450,
            ondestroy: function (action) {
                if (action == "ok") {
                    var iframe = this.getIFrameEl();
                    var data = iframe.contentWindow.GetData();
                    data = nui.clone(data);
                    if (data) {
                    	//nui.alert(nui.encode(data));
                        btnEdit.setValue(data.USERID);
                        btnEdit.setText(data.OPERATORNAME);
                        nui.get("it.nextOrgCd").setValue(data.ORGCODE);
                        nui.get("it.nextOrgName").setValue(data.ORGNAME);
                        nui.get("it.nextUsersName").setValue(data.OPERATORNAME);
                    }
                }
            }
        });
    }
    //初始化，是否展示退回选择框
    function initBack() {
    	var o = form.getData();
        var json = nui.encode(o);//alert(json);
        nui.get("back").hide();
        $.ajax({
            url: "com.bos.bps.flow.getPreviousWorkInfo.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            success: function (text) {
            	if(text&&text.msg){
            		nui.alert(text.msg); //失败时后台直接返回出错信息
            	} else {
            		if (null==text.info) {
            			//未获取到
            			nui.get("back").hide();
            			nui.get("btn_abortEval").show();
            		} else {
            			nui.get("back").setData([text.info, {dactivityInstId:"T0001",dactivityInstName:"退回发起人"}]);
            			//nui.alert(nui.encode(text.info));
            			if (text.info.dactivityInstName){
            				nui.get("back").show();
            				nui.get("btn_abortEval").hide();
            			} else {
            				nui.get("back").hide();
            				nui.get("btn_abortEval").show();
            			}
            		}
            	}
            },
            error: function (jqXHR, textStatus, errorThrown) {
                nui.alert(jqXHR.responseText);
            }
        });
    
    }
    
    //审批结论选择事件
    function backone(e) {
    	var o = form.getData();
    	o.info = e.item;
    	e.item.steps=null;
    	if(!e.item.dactivityInstId) {
    		return;
    	}
        var json = nui.encode(o);//alert(json);
        $.ajax({
            url: "com.bos.bps.flow.submitBack.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            success: function (text) {
            	if(text&&text.msg){
            		nui.alert(text.msg); //失败时后台直接返回出错信息
            	} else {
            		//nui.alert(nui.encode(text));
            		git_gohome();
            	}
            },
            error: function (jqXHR, textStatus, errorThrown) {
                nui.alert(jqXHR.responseText);
            }
        });
    }
    //提交事件
    function submitEval() {
		//校验
		form.validate();
        if (form.isValid()==false) return;
        nui.get("btn_submitEval").setEnabled(false);
        
       var o = form.getData();
       
       if (!!window['bps_attr_map'] && window['bps_attr_map']['WFActivityDefineParticipantType']=='relevantdata') {
       		o.reldata=o.reldata||{};
       		o.reldata.userVariable=window['bps_attr_map']['WFActivityDefineId'];
       		if (o.reldata.userVariable == 'null')
       			o.reldata.userVariable=null;
       		else {
       			o.reldata.userVariable='nextUser_'+o.reldata.userVariable;
       		}
       		//赋选择方式,角色id,机构等级，创建机构编号
	       o.reldata.chooseFlag=<%=request.getParameter("chooseFlag") %>;
	       o.reldata.roleid=window['bps_attr_map']['roleid'];;
	       o.reldata.orglevel=window['bps_attr_map']['orglevel'];
	       o.reldata.createrOrgCd=window['bps_attr_map']['createrOrgCd'];
       }
       
        var json = nui.encode(o);
        //alert(json);return;
        $.ajax({
            url: "com.bos.csm.corp.innerevel.submitCorpEval.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            success: function (text) {
            	if(text&&text.msg){
            		nui.alert(text.msg); //失败时后台直接返回出错信息
        			nui.get("btn_submitEval").setEnabled(true);
            	} else {
            		//nui.alert("操作成功！");
            		git_gohome();
            	}
            },
            error: function (jqXHR, textStatus, errorThrown) {
                nui.alert(jqXHR.responseText);
            }
        });
    }
    
    //临时保存事件
    function updateEvalAdvice() {
		//校验
		form.validate();
        if (form.isValid()==false) return;
        
       var o = form.getData();
        var json = nui.encode(o);//alert(json);
        $.ajax({
            url: "com.bos.bps.flow.updateSingelWork.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            success: function (text) {
            	if(text&&text.msg){
            		nui.alert(text.msg); //失败时后台直接返回出错信息
            	} else {
            		nui.alert("操作成功！");
            		getInit();
            	}
            },
            error: function (jqXHR, textStatus, errorThrown) {
                nui.alert(jqXHR.responseText);
            }
        });
    }
    //撤销事件
    function abortEval() {
		//校验
		//form.validate();
        //if (form.isValid()==false) return;
        
        nui.get("btn_abortEval").setEnabled(false);
        
       var o = form.getData();
       
        var json = nui.encode(o);//alert(json);
        $.ajax({
            url: "com.bos.csm.corp.innerevel.deleteCorpEval.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            success: function (text) {
            	if(text&&text.msg){
            		nui.alert(text.msg); //失败时后台直接返回出错信息
        			nui.get("btn_abortEval").setEnabled(true);
            	} else {
            		//nui.alert("操作成功！");
            		git_gohome();
            	}
            },
            error: function (jqXHR, textStatus, errorThrown) {
                nui.alert(jqXHR.responseText);
            }
        });
    }
    
    
</script>
</body>
</html>