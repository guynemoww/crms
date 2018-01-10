#################################shell实现功能介绍############################
#描述：
#1、CRMS提供指定目录，外围系统主动ftp文件到指定目录下
#2、cp源文件到项目目录下和备份目录下
#3、检查文件是否存在
#4、将文件编码格式统一转化为utf-8
#5、load源文件数据到数据库中
#6、将备份目录下源文件进行打包压缩备份
##############################################################################
#!/bin/sh
###############################参数配置区域###################################
#参数配置选项
#要导入的文件名
echo "第一个参数"$1
echo "第二个参数"$2
echo "第三个参数"$3
echo "第四个参数"$4
echo "第五个参数"$5
echo "第六个参数"$6
#初始化数据库变量
. $1/initDB.sh $*
#文件来源
filefrom=$2
#文件
file=$3
#表名称
tb_name=$4
#列名称
colums=$5
#文件时间
file_date=$6
##############################CRMS主体shell执行区域###########################
#文件分隔符
filecoldel='|'
#源文件GTP存放目录位置
cd $srcdir
#创建日期文件夹
#判断文件夹是否存在并且是否具有可执行权限
if [ ! -x "$bakdir/$filefrom/$file_date" ];then
	mkdir $bakdir/$filefrom/$file_date
	chmod u+x $bakdir/$filefrom/$file_date
fi

if [ "$filefrom" = "EDW" ] || [ "$filefrom" = "ECIF" ] || [ "$filefrom" = "SHM" ] || [ "$filefrom" = "CCMS" ];then
	#判断日期文件夹是否存在，即文件是否到来
	while [ ! -x "$filefrom/$file_date" ];
        do 
                echo "$(date):文件未到,5分钟再次查询,请等待。。。";
                if [ "$filefrom" = "EDW" -a `date +%H` = 08 ];then
                echo echo "$(date)：未检测到数仓文件，程序退出！";
                exit 1;
                fi
                sleep 300; 
        done
	cd $filefrom/$file_date
else
	cd $filefrom
fi
#检查文件是否存在(轮询等待，每五分钟轮询一次)
while [ ! -f $file ];
do
echo "$(date):waiting for  "$file" for 5 minutes......"
#八点 数仓文件还不来 则退出
if [ "$filefrom" = "EDW" -a `date +%H` = 08 ];then
	echo "$(date)未检测到数仓文件，程序退出！";
	exit 1;
fi
sleep 300
done
echo "$(date):Checking "$file "...... 成功！"
#开始任务
echo "Starting Tasks......"
	#拷贝文件
		cp -p $file $appdir
		cp -a $file $bakdir/$filefrom/$file_date/
		#if [ "$filefrom" = "EDW" ] || [ "$filefrom" = "ECIF" ] || [ "$filefrom" = "SHM" ] || [ "$filefrom" = "CCMS" ];then
		#	cp -a $file $bakdir/$filefrom/$file_date/
		#else
		#	cp -a $file $bakdir/$filefrom/
		#fi

	cd $appdir

	#对数据文件编码格式进行转换。源文件为gbk格式，db2数据库编码方式为utf-8格式
	
	#首先判断文件编码方式是否为utf-8,然后在转换
	file_Encode=`file ${file}|awk '{print $2}'`;
	if [ "$file_Encode" != "UTF-8" ];
	then
	iconv -f GBK -t utf-8 $file>${file}.csv;
	mv ${file}.csv $file;
	chmod u+x $file;
	fi
	
    #把分隔符@!@转换为|
	if [ "$filefrom" = "ECIF" ] || [ "$filefrom" = "CCMS" ] ;
	then
    sed 's/@!@/|/g' $file>./a.dat
	mv ./a.dat $file
	echo "convert successed************************"$file$file
	fi

	#文件读写授权
	#chmod -R 666 $file
	echo "db_alias="$db_alias",db_user="$db_user	
	#借据信息更新
	db2 connect to $db_alias user $db_user using $db_pwd
	#插入法透数据，不删除，因为插入借据时已经删除
	if [[ $file != businessduebilllegal_* ]] && [[ $file != businessinfoscf_* ]] && [[ $file != nmoneyexchange_* ]] && [[ $file != CORERECONDOC_* ]] ; then
	echo "start truncate table "$tb_name
	db2 "TRUNCATE TABLE "$db_schema.$tb_name" immediate";
	echo "result:"$?
	fi
        
	if [ "$colums" != "" ];then
	db2 "load client from "$appdir/$file" of del modified by coldel"$filecoldel" insert into "$db_schema.$tb_name\($colums\)" nonrecoverable"
	else 
	db2 "load client from "$appdir/$file" of del modified by coldel"$filecoldel" insert into "$db_schema.$tb_name" nonrecoverable"
	fi
	db2 "runstats on table "$db_schema.$tb_name" with distribution and detailed indexes all allow write access"
	if [ $? = 0 ];then
		echo ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>数据导入成功！"
	else 	
		echo ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>数据导入失败！"
	fi
	# 断开连接
	db2 connect reset
	cd $appdir
	rm -Rf $file
