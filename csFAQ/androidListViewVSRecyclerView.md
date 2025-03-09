# Android Recycler View
Since Android Lollipop(5.0)

## 2. 架构对比

### 2.1 基本架构

| 特性 | ListView | RecyclerView |
|------|----------|--------------|
| 包位置 | android.widget.ListView | androidx.recyclerview.widget.RecyclerView |
| 发布时间 | Android初始版本 | Android 5.0 (API 21) |
| 基类 | AbsListView | ViewGroup |
| 适配器基类 | BaseAdapter | RecyclerView.Adapter |
| 视图持有者 | 非强制性模式 | 强制使用ViewHolder |
| 布局管理 | 仅垂直方向 | 可配置多种布局管理器 |

### 2.2 内部实现对比

```
ListView                          RecyclerView
+------------------+              +------------------+
| ListView         |              | RecyclerView     |
+------------------+              +------------------+
| - RecycleBin     |              | - Recycler       |
| - Built-in Layout|              | - LayoutManager  |
| - Built-in Click |              | - ItemAnimator   |
| - Built-in Divider|             | - ItemDecoration |
+------------------+              | - ItemTouchHelper|
                                  +------------------+
```

## Implementation
### **ViewHolder Pattern**
**ListView**:
- [ViewHolder是推荐使用的模式，但非强制](./androidListView.md)
**RecyclerView**:
- ViewHolder是强制使用的模式
- 必须继承RecyclerView.ViewHolder
- 视图与ViewHolder的关系更加明确

```java
// RecyclerView中的ViewHolder
public class MyViewHolder extends RecyclerView.ViewHolder {
    TextView title;
    ImageView icon;
    
    public MyViewHolder(View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.title);
        icon = itemView.findViewById(R.id.icon);
    }
}

@Override
public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = inflater.inflate(R.layout.list_item, parent, false);
    return new MyViewHolder(view);
}

@Override
public void onBindViewHolder(MyViewHolder holder, int position) {
    // 设置数据
}
```
### **LayoutManager**
**ListView**:
- 仅支持垂直方向的线性布局

**RecyclerView**:
- 通过LayoutManager实现灵活的布局策略
- 内置多种LayoutManager:
  1. **LinearLayoutManager**: 支持水平和垂直线性布局
  2. **GridLayoutManager**: 支持网格布局
  3. **StaggeredGridLayoutManager**: 支持错落网格布局（类似Pinterest）

### 3.3 动画支持

**ListView**:
- 动画支持有限
- 主要依赖notifyDataSetChanged()刷新整个列表
- 没有内置的项目动画

**RecyclerView**:
- 强大的动画支持
- 细粒度的数据变化通知方法:
  - notifyItemInserted()
  - notifyItemRemoved()
  - notifyItemChanged()
  - notifyItemMoved()
- 可自定义ItemAnimator实现各种动画效果

```java
// RecyclerView的动画
// 默认动画
recyclerView.setItemAnimator(new DefaultItemAnimator());

// 自定义动画
recyclerView.setItemAnimator(new MyCustomItemAnimator());

// 细粒度更新
adapter.notifyItemInserted(position);
adapter.notifyItemRemoved(position);
adapter.notifyItemChanged(position);
adapter.notifyItemMoved(fromPosition, toPosition);
```

### **Item Decoration**
**ListView**:
- 内置简单的分割线(divider)
- 自定义分割线需要设置背景或使用自定义视图
- 难以实现复杂的项目装饰

**RecyclerView**:
- 通过ItemDecoration实现灵活的项目装饰
- 可以添加多个ItemDecoration
- 支持为每个项目自定义边距、分割线、背景等

```java
// RecyclerView的项目装饰
// 添加分割线
recyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));

// 添加间距
recyclerView.addItemDecoration(new SpacingItemDecoration(16)); // 16dp间距

// 可以添加多个装饰
recyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
recyclerView.addItemDecoration(new MyCustomItemDecoration());
```
### **OnItemTouchListener**
**ListView**:
- 简单的点击事件监听
- 通过setOnItemClickListener()设置
- 长按事件通过setOnItemLongClickListener()设置

```java
// ListView的点击事件
listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // 处理点击事件
    }
});
```

**RecyclerView**:
- 没有内置的点击事件监听
- 需要自己实现点击监听
- 提供OnItemTouchListener接口处理复杂的触摸事件
- 可以使用ItemTouchHelper实现拖拽和滑动删除

```java
// RecyclerView的点击事件 - 在ViewHolder中实现
public class MyViewHolder extends RecyclerView.ViewHolder {
    public MyViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = getAdapterPosition();
                // 处理点击事件
            }
        });
    }
}

// 拖拽和滑动删除
ItemTouchHelper itemTouchHelper = new ItemTouchHelper(
    new ItemTouchHelper.SimpleCallback(
        ItemTouchHelper.UP | ItemTouchHelper.DOWN,  // 拖拽方向
        ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {  // 滑动方向
        
        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder source, RecyclerView.ViewHolder target) {
            // 处理拖拽
            return true;
        }
        
        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            // 处理滑动删除
        }
    });
itemTouchHelper.attachToRecyclerView(recyclerView);
```

## 4. 性能对比

### 4.1 视图回收机制

**ListView**:
- 使用RecycleBin回收视图
- 两级缓存: ActiveViews和ScrapViews
- 回收机制相对简单

**RecyclerView**:
- 使用Recycler回收视图
- 四级缓存机制:
  1. **mAttachedScrap**: 仍附加到RecyclerView但已被标记为移除的ViewHolder
  2. **mCachedViews**: 最近分离的ViewHolder，不需要重新绑定数据
  3. **mViewCacheExtension**: 开发者自定义的缓存层
  4. **mRecyclerPool**: 共享回收池，需要重新绑定数据
- 更精细的缓存控制，性能更优

### 4.2 数据更新效率

**ListView**:
- 主要依赖notifyDataSetChanged()
- 更新时会刷新整个列表
- 效率较低，尤其是大列表

**RecyclerView**:
- 提供细粒度的更新方法
- 只刷新变化的项目
- DiffUtil工具类支持高效的数据对比和更新

```java
// 使用DiffUtil高效更新
DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new MyDiffCallback(oldList, newList));
diffResult.dispatchUpdatesTo(adapter);
```