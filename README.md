# Heap Data Structures and Top-K Tracking

This project implements and rigorously tests **min-heaps**, **max-heaps**, 
and a **Top-K Heap** for efficiently tracking the `k` largest elements. 
All heaps are backed by binary trees represented using arrays and support standard 
heap operations with performance guarantees.

## Project Origin

These implementations and tests were created by the **University of Washington** 
for educational purposes. They are designed to teach core concepts in priority queues, heaps, and streaming analytics.


## Implemented Data Structures

### `BinaryMinHeap`
- Maintains the **smallest** element at the root.
- Common use cases: priority queues, Dijkstra's algorithm, scheduling.

### `BinaryMaxHeap`
- Maintains the **largest** element at the root.
- Common use cases: selecting top scores, leaderboards, simulations.

### `TopKHeap`
- Keeps track of the **k largest elements** from a dynamic stream of input.
- Useful for analytics, streaming data processing, and statistics collection.

## Supported Operations

Each heap supports the following:
- `insert(value)`
- `extract()` – removes and returns root (min or max)
- `peek()` – view root without removal
- `updatePriority(value)` – adjust an existing value's priority
- `isEmpty()`, `size()`

The `TopKHeap` additionally supports:
- `topK()` – returns the top-k elements in sorted order

## Test Coverage

All the following test cases have **passed**:

### BinaryMinHeap
- Size tracking after various insertions
- Correct root element after insertions
- Stability after multiple peeks
- Valid heap structure
- Correct ordering after many insertions and removals
- Accurate behavior of `updatePriority`

### BinaryMaxHeap
- All corresponding tests as above, verifying max-heap behavior

### TopKHeap
- Correct behavior for `topK()` under all conditions
- Proper resizing and ordering after updates and removals
- Maintains top-k elements from dynamic input streams
