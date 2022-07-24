## 使用

将Test类主方法com.wwt.example.jvm.jit.JitCompilationTest#main注释去掉

添加vm option参数
```
-XX:+PrintCompilation -verbose:gc
```
运行结果1：
``` shell
213  292 %     3       com.wwt.example.jvm.jit.Test::doubleValue @ 2 (18 bytes)   made not entrant
213  299       4       com.wwt.example.jvm.jit.Test::doubleValue (18 bytes)
214  296       3       com.wwt.example.jvm.jit.Test::doubleValue (18 bytes)   made not entrant
214  300       3       com.wwt.example.jvm.jit.Test::calcSum (26 bytes)
214  301 %     4       com.wwt.example.jvm.jit.Test::calcSum @ 4 (26 bytes)
217  302       4       com.wwt.example.jvm.jit.Test::calcSum (26 bytes)
218  300       3       com.wwt.example.jvm.jit.Test::calcSum (26 bytes)   made not entrant
```

结果输出含有%说明是有回边计数器触发的OSR编译

从运行结果1中可以确认，main()、calcSum()和doubleValue()方法已经被编译，我们

加上参数要求虚拟机输出方法内联信息
```
-XX:+UnlockDiagnosticVMOptions
-XX:+PrintCompilation
-XX:+PrintInlining
-verbose:gc`
```
运行结果2：
``` shell
    214  282 %     3       com.wwt.example.jvm.jit.JitCompilationTest::doubleValue @ 2 (18 bytes)   made not entrant
    214  289       4       com.wwt.example.jvm.jit.JitCompilationTest::doubleValue (18 bytes)
    215  284       3       com.wwt.example.jvm.jit.JitCompilationTest::doubleValue (18 bytes)   made not entrant
    215  290       3       com.wwt.example.jvm.jit.JitCompilationTest::calcSum (26 bytes)
                              @ 12   com.wwt.example.jvm.jit.JitCompilationTest::doubleValue (18 bytes)   inlining prohibited by policy
    215  291 %     4       com.wwt.example.jvm.jit.JitCompilationTest::calcSum @ 4 (26 bytes)
                              @ 12   com.wwt.example.jvm.jit.JitCompilationTest::doubleValue (18 bytes)   inline (hot)
    218  292       4       com.wwt.example.jvm.jit.JitCompilationTest::calcSum (26 bytes)
    219  290       3       com.wwt.example.jvm.jit.JitCompilationTest::calcSum (26 bytes)   made not entrant
                              @ 12   com.wwt.example.jvm.jit.JitCompilationTest::doubleValue (18 bytes)   inline (hot)

```
从运行结果2可以看到，doubleValue()方法已被内联编译到calcSum()方法中，所以虚拟机再次执行calcSum()时doubleValue()方法是不会再被实际调用的，没有任何方法
分派的开销，它们的代码逻辑都被直接内联到calcSum()方法里面了。


加上参数要求虚拟机打印汇编结果
```
-XX:+UnlockDiagnosticVMOptions
-XX:+PrintCompilation
-XX:+PrintInlining
-XX:+PrintAssembly
-verbose:gc`
```
运行结果3：
```
这里是编译log
ImmutableOopMap{[184]=Oop [192]=Oop rax=Oop }pc offsets: 2521 2554     231  272       3       com.wwt.example.jvm.jit.JitCompilationTest::calcSum (26 bytes)   made not entrant
                              @ 12   com.wwt.example.jvm.jit.JitCompilationTest::doubleValue (18 bytes)   inline (hot)
Compiled method (c2)     231  275       4       com.wwt.example.jvm.jit.JitCompilationTest::calcSum (26 bytes)
 total in heap  [0x0000019707aeb310,0x0000019707aeb690] = 896
 relocation     [0x0000019707aeb470,0x0000019707aeb478] = 8
 main code      [0x0000019707aeb480,0x0000019707aeb560] = 224
 stub code      [0x0000019707aeb560,0x0000019707aeb578] = 24
 oops           [0x0000019707aeb578,0x0000019707aeb580] = 8
 metadata       [0x0000019707aeb580,0x0000019707aeb590] = 16
 scopes data    [0x0000019707aeb590,0x0000019707aeb5c8] = 56
 scopes pcs     [0x0000019707aeb5c8,0x0000019707aeb688] = 192
 dependencies   [0x0000019707aeb688,0x0000019707aeb690] = 8
 
想看汇编代码可以自己用其他工具生成汇编代码，任选其一即可
https://github.com/ymm135/hsdis-jitwatch
1.HSDIS插件
2.JitWatch 需本机jdk环境版本为jdk11及以上
https://github.com/AdoptOpenJDK/jitwatch
```


参考：
>https://github.com/ymm135/hsdis-jitwatch

>深入理解Java虚拟机：JVM高级特性与最佳实践（第3版）