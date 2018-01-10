#!/bin/bash
#初始化数据库变量
. $1/initDB.sh $*
#数据库自动备份路径
dbbackup_path="/crmsshare/CRMS/data/dbbackup"

#-------------------------------------------------
#测试用（注意today变量的时间格式）
#rtpath=$dbbackup_path
#today=`date +%Y%m%d%H%M`
#cd ${rtpath}
#find ${rtpath} -cmin +5 -type f |xargs rm -f
#echo "已删除5分钟前数据"
#-------------------------------------------------
#实际用
rtpath=$dbbackup_path
today=`date +%Y%m%d`
cd ${rtpath}
find ${rtpath} -mtime +3 -type f |xargs rm -f
echo "已删除3天前数据"
#-------------------------------------------------

mkdir crms_${today} 
bakpathdb=${rtpath}/crms_${today}
echo ${bakpathdb}
cd ${bakpathdb}
#echo "db2look..."
#return 0
#db2look -d $db_alias -z $db_schema -l -e -o crms.ddl -i $db_user -a -w $db_pwd
#return 0
echo "连接数据库"
db2 connect to $db_alias user $db_user using $db_pwd
db2 -x "select 'export to ' || rtrim(tabname) || '.del of del select * from "${db_schema}".'|| rtrim(tabname) ||' with ur ;' from syscat.tables where tabschema='${db_schema}' and type='T' and tabname not like 'TB_ACC%'" > exportcrms.sql
db2 -tvf exportcrms.sql
db2 disconnect $db_alias

echo "tar..."
cd ${rtpath}
    
tar -zcvf ${bakpathdb}.tar.gz ${bakpathdb}
echo ${bakpathdb}
rm -rf ${bakpathdb}