# MBTA-Guide
This program displays the shortest route and time for a given two MBTA stations, 
realized by converting all MBTA routes into an EdgeWeightedGraph and using the Dijkstra's Algorithm.
You can run this program by the following command:
```
$ java MBTA_Guide data/MBTA_stations.txt data/MBTA_maps.txt
```

We use two data files to run this program.
The first one is *MBTA_stations* containing the names of all MBTA stations (comma-separated values (CSV) format).
Each line contains two fileds: the first field is the station ID and the second field is the corresponding station name.
The second one is *MBTA_maps* containing information about the relationship between MBTA stations. 
The format of the file is as follows:
```
$ more ../data/MBTA_maps.txt
121
228
0 1 2.00
1 0 2.00
1 2 2.00
2 1 2.00
2 3 3.00
...
```
The first line shows the number of vertices (number of stations) in the graph, and the second line shows the number of edges. 
The third and subsequent lines show any two points and the relationship between them, separated by spaces. 
In this case, it means the time relationship between two stations. For example, 2.00 means that it takes 2 minutes to go 
from one station to the other.

Note: This project utilizes libraries and data types provided by Princeton University. All rights reserved to the author.
