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
   2. Max number of Articles published Year 
   3. All Years with max number of Articles published