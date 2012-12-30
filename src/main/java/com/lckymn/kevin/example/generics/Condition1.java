package com.lckymn.kevin.example.generics;

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
 * "http://projects.elixirian.org/kommonlee/browser/kommonlee-type/src/main/java/org/elixirian/kommonlee/type/functional/Condition1.java"
 * >KommonLee&apos;s Condition1.java</a>
 *
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2012-12-05)
 * @param <T>
 */
public interface Condition1<T>
{
  boolean isMet(T input);
}