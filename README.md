# JFixture
JFixture is an open source library based on the popular .NET library, [AutoFixture](https://github.com/AutoFixture/AutoFixture).

[![Build Status](https://travis-ci.org/FlexTradeUKLtd/jfixture.svg?branch=master)](https://travis-ci.org/FlexTradeUKLtd/jfixture)

# Description
JFixture is a Java library to assist in the writing of Unit Tests, particularly when following Test Driven Development. It generates types based on the concept of 'constrained non-determinism', which is an implementation of the [Generated Value](http://xunitpatterns.com/Generated%20Value.html) xUnit test pattern.

# Overview
A traditional unit test cycle following the method of Triangulation would result in a number of similar looking unit tests that look like 'Tests by example'.

'Tests by example' will contain hard coded values, (`String orderId = "ABCD";`) in the Arrange or Act phase of a unit test and repeat that value in the Assert phase. This causes tests to read like examples ("The id passed to the logger should be "ABCD"), rather than tests by specification ("The id passed to the logger should be the same id as the Order I've created"). This effect can be reduced by factoring the values into fields or variables, but this just hides the problem rather than fixing it.

To write 'tests by specification' the input shouldn't be known at the time of writing the test, that is, the input should be generated at run time (`String id = createId();`). This forces us to avoid repeated test cases and triangulate on correct behaviour much quicker. Tests and code are forced to focus on the semantic representation of the code (the Id) rather than the syntactic representation ("ABCD"). In the previous example createId() could return a new UUID so we can't hard code any values in either the test or the production code. 

This is called **non-determinism**.

Ordinarily the thought of non-determinism inside a unit test should invoke fear. We're aware of the perils of using random numbers, threading, file system etc, so creating unit tests with non-deterministic input would seem to go against the grain. This is a valid concern for truly randomised data, but if we constrain the input to be within the same Equivalence Class we can be confident that our input is valid for each test run even though specific values may vary. As an example, if we always generate strings that will never be empty or null these will almost certainly be in the same equivalence class.

This is called **constrained non-determinism**.

JFixture has been designed to produce input for unit tests following the principle of constrained non-determinism. This allows the developer to focus on the important parts of unit testing rather than choosing arbitrary values for test data.

# Implementation

JFixture is an unofficial cousin of the .NET library AutoFixture. It's heavily inspired by the wonderful work produced by the contributors to AutoFixture but with a Java flair. It's not as fully featured as AutoFixture but any .NET developer familiar with the library should feel comfortable using it.

JFixture started off an an internal project developed by FlexTrade UK to fill the gap in Java testing that AutoFixture fills for .NET testing. It's been used internally for a while and a decision was made to open source it for any other Java developers that may find it useful. For this reason there isn't any git commit history prior to open-sourcing because it would be too much work to rewrite history removing any internal references, links, urls etc.

# Example Usage

The [Cheat Sheet](https://github.com/FlexTradeUKLtd/jfixture/wiki/Usage-Cheat-Sheet) contains most of the information to get started, but here's a quick example.
  
To create a field as an anonymous value you can leverage JUnit's Rule engine as such,
```  
@Rule public FixtureRule fr = FixtureRule.initFixtures()
@Fixture private Order order;
```

To create a variable inside a test method simply call,
```
JFixture fixture = new JFixture();
Order order = fixture.create(Order.class);
```

# Maven
Coming soon!