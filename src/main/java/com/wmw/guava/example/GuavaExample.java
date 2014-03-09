package com.wmw.guava.example;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newHashMap;
import static com.google.common.collect.Sets.newHashSet;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.base.Objects;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.BiMap;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Multimap;

public class GuavaExample implements Comparable<GuavaExample> {

  int i;
  double d;

  @SuppressWarnings("unused")
  public void staticFactoryMethodsOfCollections() {
    // Don't need to retype the generic anymore
    List<Integer> list = newArrayList();
    Set<String> set = newHashSet();
    Map<Integer, String> maop = newHashMap();
  }

  public void equalMethodForFindingTheEquilityOfAny2Objects() {
    // No NullPointerException anymore
    Objects.equal(null, "ABC");
    // It's often used to override the equals() method of a class
  }

  public void friendlyHashCodeMethod() {
    // It's often used to override the hashCode() method of a class
    Objects.hashCode("a", 1, 1.0);
  }

  public void dummyToStringImplementation() {
    // It can easily print out all properties of an object
    Objects.toStringHelper(getClass()).add("int", i).add("double", d)
        .toString();
  }

  @SuppressWarnings("unused")
  public void cleanStaticMethodsToEnforceProconditions(String str) {
    String s = checkNotNull(str, "Argument str can't be null.");
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

  // Smart ComparisonChain makes you override compareTo much easier
  @Override
  public int compareTo(GuavaExample o) {
    return ComparisonChain.start().compare(i, o.i).compare(d, o.d).result();
  }

}
