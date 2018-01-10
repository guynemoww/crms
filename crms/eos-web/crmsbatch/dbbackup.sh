#!/bin/sh
#PATH=$PATH:$HOME/bin
#export PATH
#PATH=$PATH:$HOME/bin:$HOME/sbin
#export ORACLE_BASE=/home/was/oracle
#export ORACLE_HOME=$ORACLE_BASE/product/11.2.0/dbhome_1
#export ORACLE_SID=lcsdb
#PATH=$PATH:$HOME/bin:$ORACLE_HOME/bin
#export PATH=$PATH:$HOME/bin:$ORACLE_HOME/bin
export TNS_ADMIN=$ORACLE_HOME/network/admin
export NLS_LANG="SIMPLIFIED CHINESE_CHINA.AL32UTF8"


yyrq=$1
filename=crms_`date +'%Y%m%d%H%M%S'`
basepath=/home/was/dbbak
echo bak db begin `date +'%Y%m%d%H%M%S'` >> $basepath/logs/dbbak.log
exp crmsmv/crmsmv@172.20.12.196:1522/lcsdb file=$basepath/$filename.dmp log=$basepath/logs/$filename.log
gzip $basepath/$filename.dmp
echo bak db end `date +'%Y%m%d%H%M%S'` >> $basepath/logs/dbbak.log
#gzip $basepath/logs/$filename.log
