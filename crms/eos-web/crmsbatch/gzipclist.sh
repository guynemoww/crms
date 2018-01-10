#################################shell实现功能介绍############################
#描述：
#1、执行CMD命令
##############################################################################
###############################参数配置区域###################################
#初始化数据库变量
. $1/initDB.sh $*
#参数配置选项
echo "参数1："$1
echo "参数2："$2
echo "参数3："$3
echo "参数4："$4
echo "参数5："$5
#要导出的字段
sqlcols=$2
#导出日期
inputdate=$3
filedate=${inputdate//[^0-9]/}
#导出机构，以；分隔
orgs=$4
#数据清单类型
datalisttype=$5
##############################CRMS主体shell执行区域###########################
#开始任务
echo "Starting Tasks......"
	#连接数据库
	db2 connect to $db_alias user $db_user using $db_pwd
	#执行db2命令
	OLD_IFS="$IFS"
	IFS=","
	arr=($orgs)
	IFS="$OLD_IFS"
	cd $clistdir
	if [ ! -x "$filedate" ];then
		mkdir $filedate
	fi
	cd $filedate
	for org in "${arr[@]}"
	do
	  echo "begin export orgid :"$org
	  filename=$datalisttype.$org.$filedate.unl
	  echo "文件名：$filename"	
	 # echo "db2 export to $filename of del modified by nochardel coldel| select $sqlcols from tb_batch_c_list where operateorgid in (select orgcode from om_organization where orgseq like '%$org%') and inputdate='$inputdate'"
	  db2 "export to $filename of del modified by nochardel coldel| select $sqlcols from tb_batch_c_list where operateorgid in (select orgcode from om_organization where orgseq like '%$org%') and inputdate='$inputdate'"
      iconv -f utf-8 -t GBK $filename > "$filename".csv
      mv "$filename".csv $filename
	  if [ -f $filename ];then
		gzip -f $filename
	  fi	
	done
# 断开连接
#	db2 connect reset
