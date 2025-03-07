| Data Structure | Java | Python | C++ |
|---------------|------|--------|-----|
| **Array/List** | ArrayList | List | vector |
| Add element | `arr.add(val)` | `arr.append(val)` | `arr.push_back(val)` |
| Insert at index | `arr.insert(i, val)` | `arr.insert(i, val)` | `arr.insert(i, val)` |
| Get element | `arr.get(i)` | `arr[i]` | `arr[i]` |
| Set element | `arr.set(i, val)` | `arr[i] = val` | `arr[i] = val` |
| Remove element | `arr.remove(val)` | `arr.remove(val)` | `-` |
| **Linked List** | LinkedList | None | list |
| Add element | `ll.add(val)` | - | `ll.push_back(val)` |
| Add front | `ll.addFirst(val)` | - | `ll.push_front(val)` |
| Add back | `ll.addLast(val)` | - | `ll.push_back(val)` |
| Get front | `ll.getFirst()` | - | `ll.front()` |
| Get back | `ll.getLast()` | - | `ll.back()` |
| **Stack** | Stack | Stack | stack |
| Push | `st.push(val)` | `st.append(val)` | `st.push_back()` |
| Pop | `st.pop()` | `st.pop()` | `st.pop_back()` |
| Peek | `st.peek()` | `st[-1]` | `st.top()` |
| **Queue** | Queue | deque | queue |
| Add | `q.add(val)` | `q.append(val)` | `q.push(val)` |
| Remove | `q.poll()` | `q.popleft()` | `q.pop()` |
| Peek | `q.peek()` | `q[0]` | `q.front()` |
| **Priority Queue** | PriorityQueue | heap | priority_queue |
| Add | `q.add(val)` | `heappush(q, val)` | `q.push(val)` |
| Remove | `q.poll()` | `heappop(q)` | `q.pop()` |
| Peek | `q.peek()` | `q[0]` | `q.top()` |
| **Hash Set** | HashSet | Set | unordered_set |
| Add | `s.add(val)` | `s.add(val)` | `s.insert(val)` |
| Contains | `s.contains(val)` | `val in s` | `s.count(val)` |
| Remove | `s.remove(val)` | `s.remove(val)` | `s.erase(val)` |
| **Hash Map** | HashMap | Dictionaries | unordered_map |
| Put | `ht.put(i, val)` | `ht[i] = val` | `ht.insert({i, val})` |
| Get | `ht.get(i)` | `ht[i]` | `ht[i]` |
| Remove | `ht.remove(i)` | `del ht[i]` | `ht.erase(i)` |
| **Tree Map** | TreeMap | None | map |
| Put | `bt.put(i, val)` | - | `bt.insert(i, val)` |
| Get | `bt.get(i)` | - | `bt[i]` |
| Ceiling | `bt.ceilingEntry(i)` | - | `bt.lower_bound(i)` |
| Higher | `bt.higherEntry(i)` | - | `bt.upper_bound(i)` |
| **Binary Search** | Collections | bisect | algorithm |
| Search | `binarySearch(array, i)` | `bisect_left(arr, i)` | `lower_bound(arr.begin(), arr.end(), i)` |
| - | - | `bisect_right(arr, i)` | `upper_bound(arr.begin(), arr.end(), i)` |
| **String** | String | string | string |
| Get char | `st.charAt(i)` | `st[i]` | `st[i]` |
| Compare | `st.compareTo(st2)` | `st == st2` | `st.compare(st2)` |
| Contains | `st.contains(i)` | `i in st` | `st.contains(i)` |
| Parse int | `Integer.parseInt(i)` | `int(i)` | `stoi(i)` |