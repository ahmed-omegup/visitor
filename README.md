# Visitor Challenge

## Context

This codebase implements a small expression language in Java using the visitor pattern.

Version 1 already supports arithmetic, logic, conditionals, function calls, and a set of handlers built around that syntax tree, such as printers, evaluators, folders, reducers, and traversal helpers.

The language was then extended with a new construct: lambda expressions.

The goal is not to redesign the system, but to add the missing V2 implementation so the existing architecture continues to work with the new construct.

## Task

Implement the missing V2 and lambda-related production code.

The tests describe the expected behavior and also show how the new version is supposed to fit into the existing design.

In particular, the solution should preserve the existing visitor-based structure, reuse existing logic where possible, and extend handlers only where lambda support requires special behavior.

Your goal is to make the tests pass without deleting or editing any existing file. You may only add code.

If the tests pass, the score is based on the number of added tokens matching `\w+`. Lower is better.

## What The Specs Are Checking

The specs are not only checking that lambda expressions exist. They also check that the new version integrates correctly with the rest of the system, including:

- syntax printing
- traversal helpers
- metadata extraction
- state-based handlers
- constant folding
- substitution and capture avoidance

## Rules

- Do not delete files.
- Do not edit existing files.
- Do not modify or remove the provided tests.
- Solve the task by adding code only.

## Validation

Run the full check with:

```bash
./run_tests.sh
```