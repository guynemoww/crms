#!/bin/sh
filepath="/crms/tarlogs"
#filepath="/crm/crmssit/tarlogs"
if [ ! -x "$filepath" ];then
cd       
mkdir tarlogs
fi
cd /crms/profiles/crmsSrv01/apps_config/crms/work_temp/logs
#cd /crm/crmssit/profiles/xxdSrv01/apps_config/crms/work_temp/logs
tar -zcvf logs.tar.gz *.log*
mv logs.tar.gz $filepath/logs.tar.gz
cd /crms/profiles/crmsSrv01/logs/server1
#cd /crm/crmssit/profiles/xxdSrv01/logs/server1
cp SystemErr.log $filepath/SystemErr.log
cd $filepath
rm CRMSlogs.tar.gz
tar -zcvf CRMSlogs.tar.gz *