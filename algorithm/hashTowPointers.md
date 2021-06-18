# hashmap and two pointers
O(1) find 所以HashMap会被用在很多需要查找的场景

hashmap的使用通常是tow pass，第一遍build map,第二遍使用

但是有时候one pass hash也很好，一遍build一遍使用，可避免在处理某个元素的时候，从hashmap里取出自己


## two pointers
快速排序partition函数里的两个指针

two pointers关键要清楚指针的定义，这个指针位置左边都是XX，包不包含当前坐标

相向而行的指针就是快速排序的思路。
* 快排根据大于小于移动，跑到不能再跑移动。
* 这题左边不等0，右边等0，跑到不能再跑