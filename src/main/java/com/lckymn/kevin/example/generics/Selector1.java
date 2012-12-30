package com.lckymn.kevin.example.generics;

/**
 * Copied from <a href=
 * "http://projects.elixirian.org/kommonlee/browser/kommonlee-type/src/main/java/org/elixirian/kommonlee/type/selector/Selector1.java"
 * >KommonLee&apos;s Selector1.java</a>
 *
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2012-12-05)
 * @param <T>
 * @param <C>
 * @param <R>
 */
public interface Selector1<T, C, R>
{
  R select(C condition, T source);
}