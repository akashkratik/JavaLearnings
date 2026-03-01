package org.example;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        //waysToCreateStreams();
        intermediateOperations();
    }

    private static void waysToCreateStreams() {
        // Ways to create stream
        // 1: From collections
        List<Integer> list = Arrays.asList(100, 200, 300, 400);
        Stream<Integer> streamFromList = list.stream();

        // 2: From Array
        int[] arr = {1, 2, 3, 4, 5};
        IntStream streamFromArray = Arrays.stream(arr);
        Integer[] arr2 = {1, 2, 3, 5};
        Stream<Integer> stream = Arrays.stream(arr2);

        // 3: From Static Method
        Stream<Integer> stream2 = Stream.of(1, 2, 3, 4);

        // 4: From stream builder
        Stream.Builder<Integer> streamBuilder = Stream.builder();
        streamBuilder.add(100).add(200).add(300);

        // 5: From stream Iterate
        Stream<Integer> streamIterate = Stream.iterate(100, (Integer n) -> n + 50).limit(5);
    }

    // We can chain multiple intermediate operations together to perform more complex processing before
    // applying terminal operation to produce the result.
    // filter, map, flatMap, peek, limit, sorted, distinct, limit, skip, maptoInt, maptoLong, mapToDouble
    private static void intermediateOperations() {

        // Every intermediate operation returns a new stream
        // 1: Filter
        Stream<String> stream = Stream.of("hello", "how", "are", "you", "doing", "mate", "ssup");
        Stream<String> filteredStream = stream.filter((String word) -> word.length() <= 3);
        System.out.println("Output of filter: " + filteredStream.count());

        // 2: Map
        Stream<String> stream2 = Stream.of("hello", "how", "hellO", "are", "you", "doing", "mate", "ssup");
        Stream<String> mapStream = stream2.map(word -> word.toUpperCase());


        //3: distinct
        Stream<String> distinctStream = mapStream.distinct();
        //4: peek
        Stream<String> peekedStream = distinctStream.peek(word -> System.out.println(word));

        // count will not work because count() may skip traversal if size is known and operations don’t affect count.
        //So it’s unreliable when you need side-effects like peek().
        peekedStream.forEach(x -> {});

        // 5: flatMap : Used to iterate over each element of the complex collection and helps to flatten it.
        List<List<String>> sentenceList = Arrays.asList(
                Arrays.asList("I", "J", "k"),
                Arrays.asList("L", "M", "n")
        );

        Stream<String> wordsStream = sentenceList.stream().flatMap(sentence -> sentence.stream()).map(x -> x.toLowerCase()).peek(str -> System.out.println(str));
        wordsStream.forEach(x -> {});
        //output: i j k l m n

        // 6: sorted (use comparator inside sorted to sort in descending
        Integer[] arr = {5, 2, 42, 625, 2, 4, 72, 75};
        Stream<Integer> arrStream = Arrays.stream(arr).sorted();

        // 7: skip : skips the first n elements of the stream
        Stream<Integer> skippedStream = arrStream.skip(2);
        System.out.println("Output for 6, 7, 8......");
        // 8: limit: decides the maxSize of stream
        Stream<Integer> limitStream = skippedStream.limit(4).peek(x -> System.out.print(x + " "));
        limitStream.forEach(x -> {});

        // 9: mapToInt - helps to work with primitive "int" data types
        List<String> numbers = Arrays.asList("2", "4", "7", "89");
        IntStream numberStream = numbers.stream().mapToInt(val -> Integer.parseInt(val));

        int[] numberArray = numberStream.toArray();
        for(int n: numberArray) {
            System.out.print(n + " ");
        }

        // 10: similarly: mapToLong and mapToDouble

    }

}