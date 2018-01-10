#!/bin/sh
echo $1 $2 ">>" $3 >> /crmsshare/CRMS/logs/start.log
chmod u+x $1
$1 $2 >> $3
