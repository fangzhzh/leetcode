# Algorithm Coding Standards & Patterns

This document captures the preferred coding standards and architectural patterns for this leetcode workspace. Adhere to these when implementing or reviewing algorithm solutions.

## 1. Graph Traversal: Caller/Callee Decoupling
- **Caller (Orchestrator)**: Responsible for the macro-level logic (e.g., iterating through all grid cells, finding unvisited nodes, or initializing ocean boundary searches).
- **Callee (Explorer)**: The `dfs` or `bfs` function. It should focus strictly on exploring connected components and marking state.
- **Mandate**: Never mix "finding the next starting point" with "traversing from the current point" inside the recursive function.

## 2. DFS Responsibility & Visited State
- **Self-Contained Marking**: The `dfs` function should be responsible for marking its own current cell as `visited`.
- **Pre-check vs. Post-check**: Prefer checking bounds and `visited` status *before* making the recursive call to keep the call stack leaner.

## 3. Resource Efficiency
- **Direction Constants**: Avoid re-allocating direction arrays (e.g., `int[][] dirs = {{0,1}, ...}`) inside recursive functions. Use a class-level `private static final int[][] DIRS`.
- **Memory Reuse**: For problems involving large grids, consider if an in-place modification (e.g., using the input grid to mark visited) is safe or if BFS is required to avoid stack overflow.

## 4. Java & Kotlin Idioms
- **List Construction**: Use `List.of(i, j)` or `Arrays.asList(i, j)` for simple result coordinates instead of multiple `.add()` calls.
- **Streams**: Use streams for result collection only when it enhances readability without significant performance cost.

## 5. Review Philosophy
- Focus on **Empirical Correctness** first.
- Prioritize **Idiomatic Clarity** (naming, structure) over micro-optimizations.
- Always check if the solution handles **Edge Cases** (empty input, single element, negative values).
