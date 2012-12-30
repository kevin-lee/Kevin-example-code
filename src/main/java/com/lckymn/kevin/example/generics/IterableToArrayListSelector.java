package com.lckymn.kevin.example.generics;

import java.util.ArrayList;

/**
 * Copied from <a href=
 * "http://projects.elixirian.org/kommonlee/browser/kommonlee-function/src/main/java/org/elixirian/kommonlee/functional/collect/IterableToArrayListSelector.java"
 * >KommonLee&apos;s IterableToArrayListSelector.java</a>
 *
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2012-12-05)
 * @param <E>
 */
public final class IterableToArrayListSelector<E> extends
    IterableToCollectionSelector<E, Iterable<? extends E>, Condition1<? super E>, ArrayList<E>>
{
  IterableToArrayListSelector(final ArrayListCreator<E> collectionCreator)
  {
    super(collectionCreator);
  }

  public static <E> IterableToArrayListSelector<E> newInstance(final ArrayListCreator<E> collectionCreator)
  {
    return new IterableToArrayListSelector<E>(collectionCreator);
  }
}
