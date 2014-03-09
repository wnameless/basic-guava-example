package com.wmw.examples.guava;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newHashMap;
import static com.google.common.collect.Sets.newHashSet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.base.Function;
import com.google.common.base.Objects;
import com.google.common.base.Predicate;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.BiMap;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Iterables;
import com.google.common.collect.Multimap;

public class GuavaExample implements Comparable<GuavaExample> {

  Integer i;
  Double d;

  @SuppressWarnings("unused")
  public void staticFactoryMethodsOfCollections() {
    // Without Guava
    List<Integer> list1 = new ArrayList<Integer>();
    Set<String> set1 = new HashSet<String>();
    Map<Integer, String> map1 = new HashMap<Integer, String>();

    // With Guava
    List<Integer> list2 = newArrayList();
    Set<String> set2 = newHashSet();
    Map<Integer, String> map2 = newHashMap();
    // Don't need to retype the generic anymore

    // Without Guava
    List<Integer> ints = new ArrayList<Integer>(Arrays.asList(1, 2, 3));

    // With Guava
    ints = newArrayList(1, 2, 3);
  }

  @SuppressWarnings("unused")
  @Override
  public boolean equals(Object o) {
    if (o instanceof GuavaExample) {
      GuavaExample ge = (GuavaExample) o;
      // Without Guava
      boolean b =
          (i == null ? ge.i == null : i.equals(ge.i))
              && (d == null ? ge.d == null : d.equals(ge.d));
      // return b;

      // With Guava
      return Objects.equal(i, ge.i) && Objects.equal(d, ge.d);
      // No need to handle NullPointerException anymore
    }
    return false;
  }

  @Override
  public int hashCode() {
    // Without Guava
    int result = 6;
    result = result ^ 31 + i.hashCode();
    result = result ^ 31 + d.hashCode();
    // return result;

    // With Guava
    return Objects.hashCode(i, d);
  }

  @SuppressWarnings("unused")
  @Override
  public String toString() {
    // Without Guava
    String s = getClass().getSimpleName() + "{i=" + "i, " + "d=" + d + "}";
    // return s;

    // With Guava
    return Objects.toStringHelper(getClass()).add("i", i).add("d", d)
        .toString();
  }

  @SuppressWarnings("unused")
  public void cleanStaticMethodsToEnforceProconditions(String str) {
    // Without Guava
    if (str == null)
      throw new NullPointerException("Argument str can't be null.");
    if (str.isEmpty())
      throw new IllegalArgumentException("Argument str can't be empty.");
    String s = str;

    // With Guava
    s = checkNotNull(str, "Argument str can't be null.");
    checkArgument(!str.isEmpty(), "Argument str can't be empty");
  }

  @SuppressWarnings("unused")
  public void usefulMultiMap() {
    // Instead of doing something like this
    Map<Integer, List<String>> map = newHashMap();

    // Try to do it with the new Multimap collection provided by Guava
    Multimap<Integer, String> mmap = ArrayListMultimap.create();
  }

  @SuppressWarnings("unused")
  public void usefulBiMap() {
    // Instead of doing something like this
    Map<Integer, String> int2Str = newHashMap();
    Map<String, Integer> str2Int = newHashMap();

    // Try to do it with the new BiMap collection provided by Guava
    BiMap<Integer, String> biMap = HashBiMap.create();
  }

  @Override
  public int compareTo(GuavaExample o) {
    // Without Guava
    int result;
    result = i.compareTo(o.i);
    if (result != 0)
      return result;

    result = d.compareTo(o.d);
    if (result != 0)
      return result;

    // With Guava
    return ComparisonChain.start().compare(i, o.i).compare(d, o.d).result();
  }

  public void functionIdiomUsedToManipulateCollections() {
    List<String> strs = newArrayList("1", "2", "3", "a", "b", "c");
    // Without Guava
    List<String> strInts = new ArrayList<String>();
    for (String str : strs) {
      if (str.matches("\\d+"))
        strInts.add(str);
    }
    List<Integer> evenNums = new ArrayList<Integer>();
    for (String strInt : strInts) {
      evenNums.add(Integer.valueOf(strInt));
    }

    // With Guava
    evenNums =
        newArrayList(Iterables.transform(
            Iterables.filter(strs, new Predicate<String>() {

              @Override
              public boolean apply(String arg0) {
                return arg0.matches("\\d+");
              }

            }), new Function<String, Integer>() {

              @Override
              public Integer apply(String arg0) {
                return Integer.valueOf(arg0);
              }

            }));
    // Using function idioms with Iterables.filter & transform is much more
    // efficiency, because all Iterables are lazy. No intermediate collections
    // are needed.
    // BTW, function idioms are the key for you to master the upcoming Java 8.
  }

}
