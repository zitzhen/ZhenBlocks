# ZhenBlocks

一个面向 **Minecraft Java 26.3** 的 Fabric 模组：新增「苍白橡木显示方块」，方块六面为苍白橡木木板纹理，**只有朝向玩家的正面显示可编辑文字**，点击方块弹出输入框即可修改显示的文字（支持中文）。

## 功能

- 新增方块 `zhenblocks:display_block`，外观取自原版 `minecraft:block/pale_oak_planks`
- 正面用 Block Entity Renderer 叠加动态文字，方块旋转后文字始终跟随正面
- 右键点击方块 → 弹出输入框 → 输入文字 → 回车或点「确认」写回
- 文字由服务端权威写入并同步，支持中文（Minecraft Unifont 回退）
- 文字长度上限 128 字符；破坏方块后文字随 BlockEntity 持久化
- 生存模式可合成（4 苍白橡木木板 + 1 铁锭），破坏掉落自身

## 构建

目标版本：MC 26.3 / Loader 0.20.1 / Loom 1.19-SNAPSHOT / Fabric API 0.166.2+26.3 / **Java 25**（26.1+ 已去混淆，不使用 Yarn 映射）。

```bash
./gradlew build       # 构建，产物在 build/libs/
./gradlew runClient   # 启动客户端试玩（需 Mojang 账号）
./gradlew runServer   # 启动本地服务器验证
```

若环境存在多个 JDK，需确保构建 JVM 为 Java 25（本机的可用 JDK 25 位于 `/home/oliver/.jdks/jdk-25.0.5+8`）：

```bash
GRADLE_USER_HOME=/home/oliver/.gradle ./gradlew build --no-daemon \
  -Dorg.gradle.java.home=/home/oliver/.jdks/jdk-25.0.5+8 \
  -Dorg.gradle.problems.report.enabled=false
```

## 玩法

1. 在创造模式「建筑方块」标签或生存模式合成获得「苍白橡木显示方块」
2. 放置方块（正面朝向你）
3. 右键点击方块，输入想显示的文字，回车/确认
4. 方块正面即显示该文字

## 验证状态

- ✅ `./gradlew build` 通过
- ✅ `./gradlew runServer` 正常启动（模组加载、方块/物品/配方/战利品表注册无报错）
- ⬜ 客户端目视验证（渲染、中文显示、点击弹窗）尚未执行——需在带账号的图形化客户端中试玩确认

## 提交历史

所有提交均为本地提交，未推送。每个提交只做一件事：脚手架 → 方块注册 → BlockEntity → BER 渲染 → 网络链路 → 编辑界面 → 配方战利品 → 两处运行时修复。
