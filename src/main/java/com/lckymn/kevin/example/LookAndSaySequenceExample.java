package com.lckymn.kevin.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Lee, SeongHyun (Kevin) / <a href="http://blog.lckymn.com">Kevin&apos;s Blog</a>
 * @version 0.0.1 (2012-09-09)
 */
public class LookAndSaySequenceExample
{
	public static String LookAndReadUsingForLoop(final String number)
	{
		if (null == number || number.isEmpty())
		{
			return "";
		}
		int firstCharPosition = 0;
		final StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < number.length(); i++)
		{
			if (number.charAt(firstCharPosition) != number.charAt(i))
			{
				/* @formatter:off */
				/*
				 * the char at the current position is not equal to the first char
				 * in the group of the equal chars so collect the chars
				 * from the first char position to just before the current position.
				 */
				final String digitsFound = number.substring(firstCharPosition, i);
				stringBuilder.append(digitsFound
														.length())
										 .append(number
												 		.charAt(firstCharPosition));
				/* @formatter:on */
				firstCharPosition = i; // set the new first char position
			}
		}
		/* @formatter:off */
		/* add the leftover */
		stringBuilder.append(number
												.substring(firstCharPosition,
																	 number.length())
												.length())
								 .append(number
										 		.charAt(firstCharPosition));
		/* @formatter:on */
		return stringBuilder.toString();
	}

	public static String lookAndSay(final int howMany, final String number)
	{
		return 0 >= howMany ? "" : number + " " + lookAndSay(howMany - 1, lookAndSayV1(number, 0));
	}

	private static String lookAndSayV1(final String number, final int position)
	{
		if (number.length() == position)
		{
			/* @formatter:off */
			/* reached the end which means every digit in the number is
			 * equal to one another so just read off the entire number.
			 */
			return String.valueOf(number.length()) + number.charAt(0);
			/* @formatter:on */
		}
		final char firstChar = number.charAt(0);
		/* @formatter:off */
		return firstChar == number.charAt(position) ?
							/* the current char equals to the first one so keep checking */
							lookAndSayV1(number, position + 1) :
							/* otherwise, read off until just before the current position
							 * then check from the current position
							 * calling this function itself again.
							 */
							String.valueOf(number
														.substring(0, position)
														.length()) +
								firstChar +
								lookAndSayV1(number
														.substring(position), 0);
		/* @formatter:on */
	}

	public static String lookAndSay2(final int howMany, final String number)
	{
		return 0 >= howMany ? "" : number + " " + lookAndSay2(howMany - 1, lookAndSayV2(number, 0));
	}

	private static String lookAndSayV2(final String number, final int position)
	{
		/* @formatter:off */
		return number.length() == position ?
							String.valueOf(number
														.length()) +
								number.charAt(0) :
							(number.charAt(0) == number.charAt(position) ?
									lookAndSayV2(number, position + 1) :
									String.valueOf(number
																.substring(0, position)
																.length()) +
										number.charAt(0) +
										lookAndSayV2(number
															.substring(position), 0));
		/* @formatter:on */
	}

	public static String lookAndSay3(final int howMany, final String number)
	{
		return 0 >= howMany ? "" : number + " " + lookAndSay3(howMany - 1, lookAndSayV3(number, 0));
	}

	private static String readOff(final String digits)
	{
		return String.valueOf(digits.length()) + digits.charAt(0);
	}

	private static String lookAndSayV3(final String number, final int position)
	{
		/* @formatter:off */
		return number.length() == position ?
							readOff(number) :
							(number.charAt(0) == number.charAt(position) ?
									lookAndSayV3(number, position + 1) :
									readOff(number
												.substring(0, position)) +
												lookAndSayV3(number
																		.substring(position), 0));
		/* @formatter:on */
	}

	public static void lookAndSayUsingList(final String number, final int howMany, final List<String> resultList)
	{
		resultList.add(number);
		if (resultList.size() < howMany)
		{
			lookAndSayUsingList(lookAndSayV3(number, 0), howMany, resultList);
		}
	}

	private static List<String> lookAndSayUsingList2(final String number, final int howMany, final List<String> list)
	{
		list.add(number);
		if (list.size() < howMany)
		{
			return lookAndSayUsingList2(lookAndSayV3(number, 0), howMany, list);
		}
		return Collections.unmodifiableList(new ArrayList<String>(list));
	}

	public static List<String> lookAndSayUsingList2(final String number, final int howMany)
	{
		final List<String> list = new ArrayList<String>();
		return lookAndSayUsingList2(number, howMany, list);
	}

	public interface LookAndSayFunction
	{
		void apply(String number, int howMany);
	}

	public interface Function<R>
	{
		R apply();
	}

	public interface Function1<T, R>
	{
		R apply(T input);
	}

	public interface Function2<T1, T2, R>
	{
		R apply(T1 input1, T2 input2);
	}

	public static void main(final String[] args)
	{
		final String number = "1";
		final int howMany = 10;

		String result1 = number;
		System.out.println("Using LookAndReadUsingForLoop: ");
		System.out.print(result1 + " ");
		for (int i = 1; i < howMany; i++)
		{
			result1 = LookAndReadUsingForLoop(result1);
			System.out.print(result1 + " ");
		}
		System.out.println("\n");

		System.out.println("Using lookAndSayV1: ");
		System.out.println(lookAndSay(howMany, number));
		System.out.println();

		System.out.println("Using lookAndSaV2: ");
		System.out.println(lookAndSay2(howMany, number));
		System.out.println();

		System.out.println("Using lookAndSaV3: ");
		System.out.println(lookAndSay3(howMany, number));
		System.out.println();

		System.out.println("Using lookAndSayUsingList: ");
		final List<String> resultList = new ArrayList<String>();
		lookAndSayUsingList(number, howMany, resultList);
		System.out.println(resultList);
		System.out.println();

		System.out.println("Using lookAndSayUsingList2: ");
		System.out.println(lookAndSayUsingList2(number, howMany, new ArrayList<String>()));
		System.out.println();

		System.out.println("Using another lookAndSayUsingList2 (without passing List): ");
		System.out.println(lookAndSayUsingList2(number, howMany));
		System.out.println();

		System.out.println("Using Function object: ");
		final List<String> resultListFromFunctionObject = new ArrayList<String>();
		new LookAndSayFunction() {
			@Override
			public void apply(final String number, final int howMany)
			{
				resultListFromFunctionObject.add(number);
				if (resultListFromFunctionObject.size() < howMany)
				{
					apply(lookAndSayV3(number, 0), howMany);
				}
			}
		}.apply(number, howMany);
		System.out.println(resultListFromFunctionObject);
		System.out.println();

		System.out.println("Using Function object with returned List object: ");
		final Function2<String, Integer, List<String>> anotherFunction = new Function2<String, Integer, List<String>>() {
			@Override
			public List<String> apply(final String number, final Integer howMany)
			{
				return new Function<List<String>>() {
					@Override
					public List<String> apply()
					{
						final List<String> resultList = new ArrayList<String>();
						return new Function1<String, List<String>>() {
							@Override
							public List<String> apply(final String number)
							{
								resultList.add(number);
								if (resultList.size() < howMany.intValue())
								{
									return apply(lookAndSayV3(number, 0));
								}
								return Collections.unmodifiableList(resultList);
							}
						}.apply(number);
					}
				}.apply();
			}
		};
		System.out.println(anotherFunction.apply(number, howMany));
		System.out.println(anotherFunction.apply(number, 5));
		System.out.println(anotherFunction.apply(number, 7));
		System.out.println();

		System.out.println("Using currying (without passing List): ");
		final Function1<Integer, Function1<String, List<String>>> lookAndSayWithCurrying =
			new Function1<Integer, Function1<String, List<String>>>() {
				@Override
				public Function1<String, List<String>> apply(final Integer howMany)
				{
					return new Function1<String, List<String>>() {
						@Override
						public List<String> apply(final String number)
						{
							final List<String> resultList = new ArrayList<String>();
							return new Function1<String, List<String>>() {
								@Override
								public List<String> apply(final String number)
								{
									resultList.add(number);
									if (resultList.size() < howMany.intValue())
									{
										return apply(lookAndSayV3(number, 0));
									}
									return Collections.unmodifiableList(resultList);
								}
							}.apply(number);
						}
					};
				}
			};

		System.out.println(lookAndSayWithCurrying.apply(howMany)
				.apply(number));

		System.out.println();
		final Function1<String, List<String>> lookAndSayToGet10Elements = lookAndSayWithCurrying.apply(10);
		System.out.println(lookAndSayToGet10Elements.apply(number));
		System.out.println(lookAndSayToGet10Elements.apply(number));
		System.out.println(lookAndSayToGet10Elements.apply(number));
		System.out.println();

		final Function1<String, List<String>> lookAndSayToGet5Elements = lookAndSayWithCurrying.apply(5);
		System.out.println(lookAndSayToGet5Elements.apply(number));
		System.out.println(lookAndSayToGet5Elements.apply(number));
		System.out.println(lookAndSayToGet5Elements.apply(number));
		System.out.println();

		System.out.println(lookAndSayToGet5Elements.apply(number));
		System.out.println(lookAndSayToGet5Elements.apply("111221"));
		System.out.println(lookAndSayToGet5Elements.apply("21"));
		System.out.println(lookAndSayToGet5Elements.apply("1"));

		System.out.println();
	}
}
