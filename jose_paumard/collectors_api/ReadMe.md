## Reference
Collectors in the wild! by José Paumard
https://www.youtube.com/watch?v=yddwA3458eo&t=2772s

Presentation Slides:
https://www.slideshare.net/jpaumard/collectors-in-the-wild

Github:
https://github.com/JosePaumard/devoxx-be-2017

You need to add the following file to this repository for the examples to work. Add this file in the 'files' directory of this project. This file is 12MB, thus not added to this repository.

Download: http://introcs.cs.princeton.edu/java/data/papers.lst

## Notes
1. Types of operations in Streams
   1. Intermediate Operations
      1. Stateless Operations - do not need to remember anything (map, filter, flatmap)
      2. Stateful Operations - do need a buffer (sort, distinct) , or counter (limit, skip)
   2. Terminal Operations - Only a terminal operation triggers the consuming of the data from the source
      1. Consume all data from source - forEach, count, max, min, reduce, toArray
      2. Do not consume all data / Short-circuit operations - allMatch, anyMatch, noneMatch, findFirst, findAny
      3. Returns Optional - max, min, reduce   
  

2. groupingBy
   1. Returns a Map
   2. Syntax
      1. groupingBy(classifier)
         1. classifier is a function mapper like String::length, in this example it takes string as input and returns integer (length)
         2. The return type of this classifier is the 'key' of the map
         3. The List of elements is the value of the 'key'. In this example, list of strings whose length is same
      2. groupingBy(classifier, downstream)
         1. The downstream collectors apply to the value of map. Sample is below -
         2. groupingBy(String::length, downstream)  
            3 -> one, two, six  .stream().collect(downstream)  
            4 -> four, five, nine  .stream().collect(downstream)
         3. Example: groupingBy(String::length, Collectors.counting())
      3. groupingBy(identity, downstream)
         1. If the element in the source stream is considered as a key in the result map, then we can use 'identity()' to represent it as key
  

3. collect
   1. Syntax
      1. collect(supplier, accumulator, combiner)
         1. Performs a 'mutable' reduction operation on the elements of this stream
         2. this doesn't work with 'String' type as it is immutable
         3. combiner is useful only in parallel streams, not in synchronous streams
  

4. Collectors.collectingAndThen
   1. Syntax
      1. collectingAndThen(downstream, finisher)
  

5. Collectors.mapping
   1. This is used to convert one data type to another. Just like map()
   2. Syntax
      1. Collectors.mapping(mapper, downstream)  
      2. Ex: Collectors.mapping(getKey(), toList())
  

6. Collectors.toMap
   1. Convert the stream to map / Convert existing map to another map i.e., remap
   2. Syntax
      1. Collectors.toMap(keyMapper, valueMapper) 
   3. Difference between groupingBy() vs toMap()
      1. toMap() doesn't create duplicate keys
      2. groupingBy() can create duplicate keys to group them later 
        



## Coding Examples with Collect API
### Part1
1. Devoxx2017A class
   1. Total Number of Articles
   2. Minimum Article's Inception Year
   3. Maximum Article's Inception Year
   4. Articles published in 1960. Only titles separated by comma
   5. Summary Statistics on Inception Years
  
2. Devoxx2017B class
   1. Number of articles per Year
   2. Year with Max number of Articles published 
   3. All Years with max number of Articles published

3. Devoxx2017C class
   1. Number of Articles per Author
   2. Maximum Articles published by an Author

4. Devoxx2017D class
   1. find the highest articles published in a year (with readable code) 

5. Devoxx2017E class
   1. All Years with max number of Articles published. Display only max article count with list of years.  
        Map<Article_Count, List<Year>>
        