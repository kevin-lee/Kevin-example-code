package com.lckymn.kevin.example.generics;

import static com.lckymn.kevin.example.generics.CollectionUtil.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.lckymn.kevin.example.LookAndSaySequenceExample.Function1;

/**
 * Please visit <a href="http://blog.lckymn.com/2012/12/06/java-generics-generics-in-real-life-programming" >Java
 * Generics: Generics in Real Life Programming</a> for detailed explanation.
 *
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2012-12-05)
 */
public class GenericsTestClass
{
  private static class Product
  {
    private String name;
    private BigDecimal price;

    public Product(final String name, final BigDecimal price)
    {
      this.name = name;
      this.price = price;
    }

    public String getName()
    {
      return name;
    }

    public BigDecimal getPrice()
    {
      return price;
    }

    @Override
    public String toString()
    {
      /* @formatter:off */
      return new StringBuilder(this
                              .getClass()
                              .getSimpleName())
              .append("{")
              .append("name=")
              .append(name)
              .append(", ")
              .append("price=")
              .append(price)
              .append("}")
            .toString();
      /* @formatter:on */
    }
  }

  private static class DiscountedProduct extends Product
  {

    public DiscountedProduct(final Product product, final int discountRate)
    {
      super(product.getName(), product.getPrice()
          .multiply(new BigDecimal(100 - discountRate).divide(new BigDecimal(100))));
    }
  }

  public List<BigDecimal> takePricesOf(final List<Product> list)
  {
    final List<BigDecimal> newList = new ArrayList<BigDecimal>();
    for (final Product product : list)
    {
      newList.add(product.getPrice());
    }
    return newList;
  }

  public List<DiscountedProduct> toDiscountedProducts(final int discountRate, final List<Product> list)
  {
    final List<DiscountedProduct> newList = new ArrayList<DiscountedProduct>();
    for (final Product product : list)
    {
      newList.add(new DiscountedProduct(product, discountRate));
    }
    return newList;
  }

  public interface Mapper
  {
    Object map(Object input);
  }

  public List<Object> mapWithoutGenerics(final Mapper mapperWithoutGenerics, final List<?> list)
  {
    final List<Object> newList = new ArrayList<Object>();
    for (final Object object : list)
    {
      newList.add(mapperWithoutGenerics.map(object));
    }
    return newList;
  }

  public <T, R, F extends Function1<T, R>> List<R> map(final F mapper, final List<T> list)
  {
    final List<R> newList = new ArrayList<R>();
    for (final T t : list)
    {
      newList.add(mapper.apply(t));
    }
    return newList;
  }

  public static void main(final String[] args)
  {
    final GenericsTestClass genericsTestClass = new GenericsTestClass();
    final List<Product> productList =
      Arrays.asList(new Product("A", new BigDecimal("10.50")), new Product("B", new BigDecimal("99.99")), new Product(
          "C", new BigDecimal("20.00")), new Product("D", new BigDecimal("1.75")), new Product("E", new BigDecimal(
          "33.33")));

    System.out.println(genericsTestClass.takePricesOf(productList));
    System.out.println(genericsTestClass.toDiscountedProducts(15, productList));

    System.out.println(genericsTestClass.mapWithoutGenerics(new Mapper() {
      @Override
      public Object map(final Object input)
      {
        final Product product = (Product) input;
        return product.getPrice();
      }
    }, productList));

    // Runtime exception: java.lang.ClassCastException
    // System.out.println(testClass.mapWithoutGenerics(new Mapper() {
    // @Override
    // public Object map(final Object input)
    // {
    // final Product product = (Product) input;
    // return product.getPrice();
    // }
    // }, Arrays.asList("Some", "String", "List")));

    System.out.println(genericsTestClass.map(new Function1<Product, BigDecimal>() {
      @Override
      public BigDecimal apply(final Product product)
      {
        return product.getPrice();
      }
    }, productList));

    final int discountRate = 15;

    final List<DiscountedProduct> discountedProductList =
      genericsTestClass.map(new Function1<Product, DiscountedProduct>() {
        @Override
        public DiscountedProduct apply(final Product product)
        {
          return new DiscountedProduct(product, discountRate);
        }
      }, productList);
    System.out.println(discountedProductList);

    final Condition1<Product> greaterThan20 = new Condition1<Product>() {
      private final BigDecimal twenty = new BigDecimal("20");

      @Override
      public boolean isMet(final Product input)
      {
        return 0 > twenty.compareTo(input.getPrice());
      }
    };

    final IterableToCollectionSelector<Product, Iterable<Product>, Condition1<Product>, ArrayList<Product>> collectionSelector =
      new IterableToCollectionSelector<Product, Iterable<Product>, Condition1<Product>, ArrayList<Product>>(
          new ArrayListCreator());
    System.out.println(collectionSelector.select(greaterThan20, productList));

    final IterableToCollectionSelector<DiscountedProduct, Iterable<DiscountedProduct>, Condition1<Product>, ArrayList<DiscountedProduct>> collectionSelector2 =
      new IterableToCollectionSelector<DiscountedProduct, Iterable<DiscountedProduct>, Condition1<Product>, ArrayList<DiscountedProduct>>(
          new ArrayListCreator());

    System.out.println(collectionSelector2.select(greaterThan20, discountedProductList));

    // final IterableToCollectionSelector<Product, Iterable<Product>, Condition1<Product>, ArrayList<Product>>
    // collectionSelector1_1 =
    // CollectionUtil.getIterableToCollectionSelector();
    // System.out.println(collectionSelector1_1.select(greaterThan20, productList));
    //
    // final IterableToCollectionSelector<DiscountedProduct, Iterable<DiscountedProduct>, Condition1<Product>,
    // ArrayList<DiscountedProduct>> collectionSelector2_1 =
    // CollectionUtil.getIterableToCollectionSelector();
    // System.out.println(collectionSelector2_1.select(greaterThan20, discountedProductList));

    final IterableToArrayListSelector<Product> arrayListSelectorForProduct = getIterableToArrayListSelector();
    final List<Product> resultForProduct = arrayListSelectorForProduct.select(greaterThan20, productList);
    System.out.println(resultForProduct);

    final IterableToArrayListSelector<DiscountedProduct> arrayListSelectorForDiscountedProduct =
      getIterableToArrayListSelector();
    final List<DiscountedProduct> resultForDiscountedProduct =
      arrayListSelectorForDiscountedProduct.select(greaterThan20, discountedProductList);
    System.out.println(resultForDiscountedProduct);

    final List<Product> resultForProduct2 = CollectionUtil.<Product> getIterableToArrayListSelector()
        .select(greaterThan20, productList);
    System.out.println("resultForProduct2: " + resultForProduct2);

    final List<Product> resultForProduct3 = select(greaterThan20, productList);
    System.out.println("resultForProduct3: " + resultForProduct3);
  }
}
