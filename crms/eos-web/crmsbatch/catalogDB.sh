#Add node
db2 uncatalog node batchsit
db2 catalog tcpip node batchsit remote 10.240.81.117 server 50000
db2 catalog tcpip node crmssit remote 10.240.81.116 server 50000
db2 catalog tcpip node node179 remote 10.240.81.179 server 50000
db2 catalog tcpip node crmsnode remote 10.240.1.201 server 50000
terminate
#Add DB CATA
db2 uncatalog db batchsit
db2 catalog db batchsit as batchsit at node batchsit
db2 uncatalog db guaranty
db2 catalog db guaranty as guaranty at node batchsit
db2 uncatalog db batchuat
db2 catalog db batchuat as batchuat at node batchsit
db2 catalog db crmssit as crmssit at node crmssit
db2 catalog db crmsuat as db179 at node node179
db2 catalog db crmsdev as dev179 at node node179
db2 catalog db crmssit as sit179 at node node179
db2 catalog db crmsuat as crmsuat at node crmssit
db2 catalog db crms as crms at node crmsnode
