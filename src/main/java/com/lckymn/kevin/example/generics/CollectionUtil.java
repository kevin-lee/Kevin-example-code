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
 * "http://projects.elixirian.org/kommonlee/browser/kommonlee-function/src/main/java/org/elixirian/kommonlee/functional/collect/CollectionUtil.java"
 * >KommonLee&apos;s CollectionUtil.java</a>
 *
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2012-12-05)
 */
public class CollectionUtil
{
  private static final IterableToArrayListSelector<?> ITERABLE_TO_ARRAY_LIST_SELECTOR =
    new IterableToArrayListSelector<Object>(ArrayListCreator.getInstance());

  public static <E> IterableToArrayListSelector<E> getIterableToArrayListSelector()
  {
    @SuppressWarnings("unchecked")
    final IterableToArrayListSelector<E> iterableToCollectionSelector =
      (IterableToArrayListSelector<E>) ITERABLE_TO_ARRAY_LIST_SELECTOR;
    return iterableToCollectionSelector;
  }

  public static <E, T extends Iterable<? extends E>, C extends Condition1<? super E>> ArrayList<E> select(
      final C condition, final T iterable)
  {
    final IterableToArrayListSelector<E> iterableToCollectionSelector =
      (IterableToArrayListSelector<E>) ITERABLE_TO_ARRAY_LIST_SELECTOR;
    return iterableToCollectionSelector.select(condition, iterable);
  }
}
