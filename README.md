# POS in Shell

[![asciicast](https://asciinema.org/a/474270.svg)](https://asciinema.org/a/474270)

### 补充实现

```
ap：添加新的Product种类
c：显示账单具体购买商品和总金额，结帐并清空购物车
e：直接清空当前购物车
m：修改选定商品数量
s：展示当前账单
```

### 代码理解

cli：负责与用户交互的部分，指令作为构件通过SprintBoot注入。

biz：业务逻辑，负责实现cli中命令调用。

db：数据库存储逻辑，负责业务数据的存取、管理。

model：业务中用到的对象，作为Data注入，其中Item与Product利用SpringBoot架构中提供的构造方式。

其中体现的分层思想：

交互、业务、数据逻辑分层分离，有助于解耦，在更新实现时不至于影响其他部分。各层面对统一接口，即interface文件中定义的函数，与具体实现隔离。

### 描述

The demo shows a simple POS system with command line interface. 

To run

```shell
mvn clean spring-boot:run
```

Currently, it implements three commands which you can see using the `help` command.

```shell
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                (v2.5.7)
 
shell:>help
AVAILABLE COMMANDS

Built-In Commands
        clear: Clear the shell screen.
        exit, quit: Exit the shell.
        help: Display help about available commands.
        history: Display or save the history of previously run commands
        script: Read and execute commands from a file.
        stacktrace: Display the full stacktrace of the last error.

Pos Command
        a: Add a Product to Cart
        n: New Cart
        p: List Products
```

Everytime a customer come to make a purchase, use `n` to create a new cart and then use `a ${productid} ${amount}` to add a product to the cart.

Please make the POS system robust and fully functional by implementing more commands, for instance, print/empty/modify cart.

Implementing a PosDB with real database is very much welcome. 

Please use asciinema (https://asciinema.org) to record a demo and submit the url in QQ group. 

And please elaborate your understanding in layered systems via this homework in your README.md.

