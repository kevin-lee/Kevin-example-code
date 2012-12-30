package com.lckymn.kevin.example.generics;

import java.util.Collection;

/**
 * Copied from <a href=
 * "http://projects.elixirian.org/kommonlee/browser/kommonlee-function/src/main/java/org/elixirian/kommonlee/functional/collect/IterableToCollectionSelector.java"
 * >KommonLee&apos;s IterableToCollectionSelector.java</a>
 *
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2012-12-05)
 * @param <E>
 * @param <T>
 * @param <C>
 * @param <R>
 */
public class IterableToCollectionSelector<E, T extends Iterable<? extends E>, C extends Condition1<? super E>, R extends Collection<E>>
    implements Selector1<T, C, R>
{
  private final CollectionCreator<E, ? extends R> collectionCreator;

  public <CC extends CollectionCreator<E, ? extends R>> IterableToCollectionSelector(final CC collectionCreator)
  {
    this.collectionCreator = collectionCreator;
  }

  @Override
  public R select(final C condition, final T source)
  {
    final R result = collectionCreator.createCollection();
    for (final E element : source)
    {
      if (condition.isMet(element))
      {
        result.add(element);
      }
    }
    return result;
  }

  /* @formatter:off */
	public static <E,
									 T extends Iterable<? extends E>,
									 C extends Condition1<E>,
									 R extends Collection<E>,
									 CC extends CollectionCreator<E, ? extends R>>
					IterableToCollectionSelector<E, T, C, R>
		newInstance(final CC collectionCreator)
	{
		return new IterableToCollectionSelector<E, T, C, R>(collectionCreator);
	}
	/* @formatter:on */
}