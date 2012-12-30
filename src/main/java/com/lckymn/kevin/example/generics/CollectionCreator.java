package com.lckymn.kevin.example.generics;

import java.util.Collection;

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
 * "http://projects.elixirian.org/kommonlee/browser/kommonlee-function/src/main/java/org/elixirian/kommonlee/functional/collect/CollectionCreator.java"
 * >KommonLee&apos;s CollectionCreator.java</a>
 *
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2012-12-05)
 * @param <E>
 * @param <T>
 */
public interface CollectionCreator<E, T extends Collection<? extends E>>
{
  T createCollection();
}