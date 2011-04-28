package org.dotkam.mongodb.partition;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DocumentPartitioner<DOCUMENT> {

    // { 1 : listOfDocuments1, 2 : listOfDocuments2, ... }
    public Map<Integer, List<DOCUMENT>> partition( List<DOCUMENT> documents, Integer gridSize ) {

        Map<Integer, List<DOCUMENT>> partitions = new HashMap<Integer, List<DOCUMENT>>();

        int numberOfDocuments = documents.size();

        int min = 0;
        int max = numberOfDocuments;

        int numberOfPartitions = ( max - min ) / gridSize + 1;

        int partitionNumber = 0;
        int start = min;
        int end = start + numberOfPartitions - 1;

        while ( start <= max ) {

            partitions.put( partitionNumber, documents.subList( start, end ) );

            if (end >= max) { end = max; }

            start += numberOfPartitions;
            end += numberOfPartitions;
            partitionNumber++;
        }

        return partitions;
    }
}
