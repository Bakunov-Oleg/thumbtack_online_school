#!/bin/bash
echo "Task1 start"; 
mkdir "/home/user/linux_practice_third/files_task1" -p
for i in {0..364};
do
     date=$(date -d "2019-01-01 +$i days" +'%Y.%m.%d');
     echo "cite;country;date;views;clicks
www.abc.com;USA;$date;$RANDOM;$RANDOM
www.cba.com;France;$date;$RANDOM;$RANDOM" > "/home/user/linux_practice_third/files_task1/${date}.csv";
done

echo "Task1 complete";  
