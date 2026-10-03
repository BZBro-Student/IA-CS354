# Interpreter Assignment

* Author: Broden
* Class: CS354
* Semester: Fall 2026

## Overview

### Part 1: Lexical Analysis

This program takes in a string representation of a program and tokenizes it based on type. Comments
are skipped by the tokenization process since they will end up having no meaning to the program later 
on.

### Part 2: Parsing

This program takes a token stream from the Lexer and creates a parse tree from it. The parse tree can
be printed to the console for testing.

## Reflection

### Part 1: Lexical Analysis

This part of the assignment was fairly simple. Creating hashsets for each token type was easy as all I 
needed to do for that was look at an ascii table to see where and when I could use a range of values 
and when I would have to individually add values. The pre-stamped code was easy to understand and add
my work to and the given jUnit tests were great templates for working on making a black box test before 
actually starting work on the project (and adding to it during development as things changed). Overall
a very digestible project.

Issues did occur however when implementing the nextKw___() functions for example with the num tokenizer
my logic for checking to make sure two decimals did not occur in the same token would not execute. I had
to spend a bunch of time using the break point function in OSS VScode before realizing the issue was that my advance()
call at the beginning of the while loop was causing the check to happen after the token would've been peek()ed
adjusting the order of the advance() call to the end resolved the issue. I had a similar issue with commentParser()
where it would automatically skip and go out of the bounds of the string because of a issue with how the advance() call
was placed.

### Part 2: Parsing

This assignment was suuuuuupppppperrrrr easy. The text stubs for everything was easy to work around and copying the append
function from the provided class implementations to work in the other class was pretty easy. I did somehow mess it up at some 
point by setting the term to itself rather than null in the end of the append function which is arguably stupid seeing as the 
example absolutely does not do that.

```
Cool Code:
        this.mulop=term.mulop;
        this.term=term;
        term.mulop=null;

My first attempt for some reason:

        this.mulop=term.mulop;
        this.term=term;
        term.mulop=term;

```

Other than that the rest of the assignment was pretty easy. I did have some slight issues with how the parser was receiving tokens 
and determining correct order especially with parenthesis. ((x) would not throw an error it was very weird and partially related 
to the issue above I think? I can't fully remember as I did this a week ago. I eventually got it to break, but in the good expected
way which is great. The assignment was pretty great and easy to work through.

## Results

### Part 1: Lexical Analysis
By the end of this part of the assignment all given tests have passed in jUnit and all of my tests have passed as well,
my tests focused on what I viewed to be edge cases or major changes such as newlines, mid token operators, Token
types being placed inside of comments, and Token types being placed right outside of comments. All of which resulted in
expected behavior.

### Part 2: Parsing
All tests pass and output shows as expected based on what my expected parse tree looked like for the programs I was passing 
into the parser. Errors are thrown when expected and it overall seems to work! 

## Sources used

(https://docs.oracle.com/javase/8/docs/api/java/util/HashMap.html)
