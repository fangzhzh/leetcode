# martrix
## 遍历
* 顺时针
* 逆时针
* 对角线
* 环形遍历

下面我们用一道题引入各种遍历

![54.spiral-matrix 螺旋矩阵](./graphs/matrix.drawio.svg)


## 置零
根据一些条件的置零，
* 另建一个二维数组标记状态，然后根据状态标记 O(m*n)
* 零件两个一位数组标记状态，然后根据状态标记 O(m+n)
* 投射到矩阵的第一行，第一列
    * 这个需要注意，在处理时，需要避开第一行第一列，回头单独处理

[73.set-matrix-zeroes 矩阵置零](./73.set-matrix-zeroes)

![矩阵置零图解](./graphs/73-set-matrix-zeroes.drawio.svg)
