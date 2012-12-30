package com.lckymn.kevin.example.generics;

import java.util.ArrayList;

/**
 * <pre>
 *     ___  _____                                _____
 *    /   \/    /_________  ___ ____ __ ______  /    /   ______  ______
 *   /        / /  ___ \  \/  //___// //     / /    /   /  ___ \/  ___ \
 *  /        \ /  _____/\    //   //   __   / /    /___/  _____/  _____/
 * /____/\____\\_____/   \__//___//___/ /__/ /________/\_____/ \_____/
 * </pre>
 *
 * Copied from <a href=
 * "http://projects.elixirian.org/kommonlee/browser/kommonlee-function/src/main/java/org/elixirian/kommonlee/functional/collect/ArrayListCreator.java"
 * >KommonLee&apos;s ArrayListCreator.java</a>
 *
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2012-12-05)
 * @param <E>
 */
public class ArrayListCreator<E> implements CollectionCreator<E, ArrayList<E>>
{
  public static final ArrayListCreator<Object> ARRAY_LIST_CREATOR = new ArrayListCreator<Object>();

  @Override
  public ArrayList<E> createCollection()
  {
    return new ArrayList<E>();
  }

  public static <E> ArrayListCreator<E> getInstance()
  {
    @SuppressWarnings("unchecked")
    final ArrayListCreator<E> arrayListCreator = (ArrayListCreator<E>) ARRAY_LIST_CREATOR;
    return arrayListCreator;
  }
}