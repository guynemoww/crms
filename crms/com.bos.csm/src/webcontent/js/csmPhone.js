
CsmPhoneObj = function(mainIdname,id1Name,id2Name,id3Name){
	this.mainIdName=mainIdname;
	this.id1Name=id1Name;
	this.id2Name=id2Name;
	this.id3Name=id3Name;
	
	this.init=function(){
    	var phone=nui.get(this.mainIdName).getValue(); 
    	var strs=phone.split('-');
    	if(strs && strs.length>=2){
    		var phone1=strs[0];
    		var phone2=strs[1];
    		
    		nui.get(this.id1Name).setValue(phone1); 
    		nui.get(this.id2Name).setValue(phone2); 
    	}
    	if(strs && strs.length>=3){
    		var phone3=strs[2];
    		nui.get(this.id3Name).setValue(phone3); 
    	}
    };
    
    this.validate=function(e){
    	var phone1=nui.get(this.id1Name).getValue(); 
    	var phone2=nui.get(this.id2Name).getValue(); 
    	var phone3=nui.get(this.id3Name).getValue(); 
    	
    	if(null==phone1 || ''==phone1.trim()){
    		e.isValid = false;
    		e.errorText = "区号不能为空!";
    		return false;
    	}
    	
    	if(null==phone2 || ''==phone2.trim()){
    		e.isValid = false;
    		e.errorText = "电话号码不能为空!";
    		return false;
    	}
    	
    	var phone=phone1 + '-' + phone2 + '-' + phone3;
    	var re =  /^0?\d{3,4}\-\d{7,8}-{0,1}\d{0,8}$/g;
    	if (!re.test(phone)){
    		e.isValid = false;
    		e.errorText = "电话号码格式错误!";
    		return false;
    	}
    	
    	return true;
    };
    
    this.setValue=function(){
    	var phone1=nui.get(this.id1Name).getValue();
    	var phone2=nui.get(this.id2Name).getValue();
    	var phone3=nui.get(this.id3Name).getValue();
    	
    	var phone = phone1.trim()+'-' + phone2.trim();
    	if(null != phone3 && '' != phone3.trim()){
    		phone = phone + '-' + phone3.trim();
    	}
    	nui.get(this.mainIdName).setValue(phone); 
    };
    
    this.toString=function(){
    	var reStr="{";
    	reStr=reStr+this.mainIdName+":"+this[this.mainIdName]+", ";
    	reStr=reStr+this.id1Name+":"+this[this.id1Name]+", ";
    	reStr=reStr+this.id2Name+":"+this[this.id2Name]+", ";
    	reStr=reStr+this.id3Name+":"+this[this.id3Name]+", ";
    	reStr=reStr+"}";
    	return reStr;
    }
};

