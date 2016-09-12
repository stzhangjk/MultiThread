# MultiThread
Java多线程Demo
笔记：
    002-停止线程
        `interrupted()`，测试当前线程（运行代码的线程）是否已经中断。
        `isInterrupted()`，测试线程是否已经中断。
    同步：
        `sleep()`不释放锁。
        `join()`会释放锁。
        `join(long)`内部使用`wait(long)`实现的。


