#################################shell实现功能介绍############################
#描述：
#1、备份TB_BATCH_DATALIST_ATTACHMENT 的指定日期的数据

##############################################################################
###############################参数配置区域###################################
#初始化数据库变量
. $1/initDB.sh $*
#参数配置选项
#文件日期
v_date=$2
file_date=${v_date//[^0-9]/}
##############################CRMS主体shell执行区域###########################
#开始任务
echo "Starting Tasks......"

	#建立连接
	db2 connect to $db_alias user $db_user using $db_pwd
	db2 "export to "$clistbak/$v_date".unl of del modified by NOCHARDEL codepage=1208 COLDEL| striplzeros decplusblank select ORG_NUM,INPUTDATE,DATALISTTYPE,FILENAME,CONTENTTYPE,CONTENTLENGTH,CONTENTSTATUS,INPUTTIME,nvl(DATALISTPATH,' ') from TB_BATCH_DATALIST_ATTACHMENT where inputdate = "$v_date
#关闭连接
	db2 connect reset	
#打包
	gzip -f $clistbak/$v_date".unl"
