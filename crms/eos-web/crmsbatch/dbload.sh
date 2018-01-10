USAGE="usage : $0 [-u user/passwd] -t tablename [-d path] [-f txtfile] [-a] [-p sepchar]"

uflag=0
tflag=0
aflag=0
dflag=0
fflag=0
dbname="crmsuat/crmsuat@172.20.12.202:1521/loandb"
mode="TRUNCATE"
datadir="."
sepchar='|'

while getopts u:t:d:-f:ap: opt
do
	case $opt in
		a)
			aflag=1;;
		u)
			uflag=`expr $uflag + 1`
			dbname=$OPTARG
			;;
		t)
			tflag=`expr $tflag + 1`
			tabname=$OPTARG
			;;
		d)
			datadir=$OPTARG
			;;
		f)
			fflag=`expr $fflag + 1`
			txtfile=$OPTARG
			;;
		p)
			sepchar=$OPTARG
			;;
		?)
			echo $USAGE
			exit 2;;
	esac
done

#if [ $uflag -ne 1 ]
#then
#        echo $USAGE
#        exit 2
#fi

if [ $tflag -ne 1 ]
then
        echo $USAGE
        exit 2
fi
export ORACLE_BASE=/home/oracle/app/oracle
export ORACLE_HOME=$ORACLE_BASE/product/11.2.0/dbhome_1
export NLS_LANG="SIMPLIFIED CHINESE_CHINA.AL32UTF8"
#export NLS_LANG="AMERICAN_AMERICA.UTF8"
export NLS_DATE_FORMAT="YYYY/MM/DD"
ctlfile=./ctl/${tabname}.ctl

if [ $fflag -eq 0 ]
then
   txtfile=${tabname}.txt
fi

badfile=${tabname}.bad
logfile=./log/load_${tabname}.log

if [ $aflag -ne 0 ]
then
    mode="APPEND"
fi

cd ${datadir}
mkdir -p log
mkdir -p ctl
> ${ctlfile}
sqlplus -S ${dbname} <<!  > /dev/null 
set	wrap off
set	feedback off
set	pagesize 0
set	verify off
set     termout off
set     echo off
spool '${ctlfile}' rep

prompt LOAD DATA
prompt INFILE *
prompt BADFILE '${badfile}'
prompt INTO TABLE ${tabname}	
prompt ${mode}
prompt FIELDS TERMINATED BY '${sepchar}'
prompt TRAILING NULLCOLS
prompt (
select  decode(column_id, 1, '' , ',') ||
        lower(column_name) ||
        decode(data_type, 'VARCHAR2', ' "nvl(trim(:'||lower(column_name)||'), '''')"'
                        , 'NVARCHAR2', '        "nvl(:'||lower(column_name)||', '''')"'
                        , 'NUMBER', '   "nvl(:'||lower(column_name)||', 0)"'
                        , 'LONG', '     "nvl(:'||lower(column_name)||', '''')"'
                        , 'BINARY_FLOAT', '     "nvl(:'||lower(column_name)||', 0)"'
                        , 'BINARY_DOUBLE', '    "nvl(:'||lower(column_name)||', 0)"'
                        , 'RAW', '      "nvl(:'||lower(column_name)||', ''00'')"'
                        , 'LONG RAW', ' "nvl(:'||lower(column_name)||', ''00'')"'
                        , 'CHAR', '     "nvl(trim(:'||lower(column_name)||'), '''')"'
                        , 'NCHAR', '    "nvl(:'||lower(column_name)||', '''')"'
                        ,  '    ')
from	user_tab_columns
where	table_name = upper('${tabname}') 
order by column_id;
prompt )
spool off
set echo on
set termout on
!

if [ $? -ne 0 ]
then 
   echo "general control file error!"
   exit 1
fi

sqlldr ${dbname} control=${ctlfile} data=${txtfile} log=${logfile}
