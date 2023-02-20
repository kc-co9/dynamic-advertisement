# 动态广告位系统

本项目是基于`EAV`模型实现的一款动态化广告位系统，通过它我们可以轻易地实现接口字段的新增、修改和删除。

## 背景

在项目快速迭代的过程中，由于产品经理不断的对广告位系统进行变更与限制，让我们不停地需要进行表字段的添加和版本的发布，并且有时候添加的字段可能只对某种类型的数据有效，造成了表空间的浪费。因此，我们为了能更快速地响应需求和更有效地利用表空间，设计出了这个基于`EAV`模型的动态广告位系统。

## EAV模型

`EAV`模型，即`Entity–attribute–value model`，是一种以节省空间的方式进行编码的数据模型，特别适用于存在大量描述实体属性但实际每个对象应用到的数量比较少的情况（类似于数学中的"稀疏矩阵"）。

对于`EAV`模型的数据存储，它采取了类似于稀疏矩阵的存储方法（节省空间），即只存储非空的值。在实现上，它使用了一种"长瘦型"的策略，即每个实体属性以`attribute-value`的方式在数据表中存储为一行，一般可以被存储为`3`列：

> 所谓"长瘦型"即：
>
> - "长"指的是数据行的数量（多）。
> - "瘦"指的是数据列的数量（少）。

| 列               | 描述                                    |
|-----------------|---------------------------------------|
| 实体(`entity`)    | 表示实体，一般存储着实体`ID`（作为实体定义表的外键）。         |
| 属性(`attribute`) | 表示实体的属性项，一般存储着属性`ID`（作为属性定义表的外键）。     |
| 值(`value`)      | 表示实体属性对应的值，一般会将存储`value`的表分开不同类型进行存储。 |

> 为什么将存储`value`的表分开不同类型进行存储呢？
> 
> 将所有类型存储为单一类型（一般为字符串类型），会存在如下劣势：
> 
> 1. 扩展性较差。
> 2. 需额外类型转换。
> 3. 建立索引基本无用。

在`EAV`模型中，一般需要引入`Metadata`表对模型提供支持和维护。通过`Metadata`表，我们可以以逻辑模式（应用层）对系统进行交互，而不是传统的物理模式（数据库）。即，在软件执行过程中的各种操作都需要向`Metadata`表询问：数据如何显示？数据是否有效？等等。总的来说，在`EAV`模型中我们就是通过`Metadata`来定义它的行为的。

> 需要注意，对于使用基于`EAV`模型（`EAV-based`）的数据库，并不意味着要所有表设计都采取`EAV`模型，而是只针对存在大量稀疏属性的实体采取`EAV`模型，其他则继续采用传统表模型。

一般我们会在以下两种情况下适用`EAV`模型：

- 高度稀疏且具有不同类型属性的实体。

  当实体具有大量的稀疏属性时，通过传统的关系型模型可能会造成空间的浪费，这种情况就需要用到一种更节省的方式进行存储了。

- 高度动态属性但实例数量相对少的实体。

  在项目快速发展阶段，数据模块可能需要不断地定义新属性与修改表示的方式，这种情况就需要我们在短时间内完成变更。

虽然`EAV`模块能带来不少的优势，但是也存在致命的劣势，即难以处理大量`EAV`数据。因为在使用过程中我们需要完成对列模型与`EAV`模型之间的转换，这如果手动完成很容易出错，并且会占用大量`CPU`。关于前者我们可以使用通用的框架来解决，而后者则需要进行额外的性能优化了，例如：

- 通过使用一个单独用作查询的 [`"warehouse"`](https://en.wikipedia.org/wiki/Data_warehouse) 库整合`EAV`模型的数据，并在其中添加大量的索引和各种反范式的设计（例如冗余字段等）以降低性能的消耗（减少表连接带来的性能损失）。
- 通过在内存中使用哈希表或二维数组进行缓存，简化`EAV`模型的查询操作和降低数据库的`I/O`次数。

> 行模型（`Row modeling`）
> 
> 与`EAV`模型类似，行模型也会将实体属性记录多行而不是多列。而它们之间存在如下差异：
> 
> - 行模型属性值存储的数据是相同类型的（特指业务类型），而`EAV`模块则可以存储任何类型的数据（特指业务类型）。
> - 行模型属性值的数据类型是预先定义好的（特指数据类型），而`EAV`模型下属性值的数据类型是依赖于属性（特指数据类型）。

## 表结构

```text
                                                                                                      +------------------------+
                                                        +---------------------------+                 |                        |
                                                      +-+                           +-+               |  dad_config_metadata   |
                                             +--------+-|  dad_config_value_int     |-+-------+       |                        |
                                             |        +-+                           +-+       |       +------------+-----------+
                                             |          +---------------------------+         |                    |
                                             |                                                |                    |
                                             |                                                |                    |
+--------------+       +--------------+      |          +---------------------------+         |       +------------+-----------+
|              |     +-+              |      |        +-+                           +-+       |       |                        |
|  dad_module--------+-|  dad_config  +---------------+-|  dad_config_value_long    |-+---------------+  dad_config_attribute  |
|              |     +-+              |      |        +-+                           +-+       |       |                        |
+--------------+       +--------------+      |          +---------------------------+         |       +------------------------+
                                             |                                                |
                                             |                                                |
                                             |          +---------------------------+         |
                                             |        +-+                           +-+       |
                                             +--------+-|  dad_config_value_string  |-+-------+
                                                      +-+                           +-+
                                                        +---------------------------+
```

模块项：`dad_module`

| 字段            | 类型               | 描述     |
|---------------|------------------|--------|
| `id`          | BIGINT UNSIGNED  | 主键ID   |
| `code`        | VARCHAR(20)      | 标识     |
| `name`        | VARCHAR(20)      | 名称     |
| `image`       | VARCHAR(512)     | 图片     |
| `description` | VARCHAR(255)     | 描述     |
| `is_enabled`  | TINYINT UNSIGNED | 状态     |
| `create_time` | DATETIME         | 创建时间   |
| `update_time` | DATETIME         | 最后更新时间 |

配置项：`dad_config`

| 字段            | 类型               | 描述         |
|---------------|------------------|------------|
| `id`          | BIGINT UNSIGNED  | 主键ID       |
| `module_id`   | BIGINT UNSIGNED  | 模块ID       |
| `code`        | VARCHAR(20)      | 标识         |
| `name`        | VARCHAR(20)      | 名称         |
| `description` | VARCHAR(255)     | 描述         |
| `image`       | VARCHAR(512)     | 图片         |
| `sort`        | TINYINT          | 排序 越大优先级越高 |
| `is_enabled`  | TINYINT UNSIGNED | 状态         |
| `create_time` | DATETIME         | 创建时间       |
| `update_time` | DATETIME         | 最后更新时间     |

配置项元数据：`dad_config_metadata`

| 字段               | 类型               | 描述        |
|------------------|------------------|-----------|
| `id`             | BIGINT UNSIGNED  | 主键ID      |
| `attribute_id`   | BIGINT UNSIGNED  | 属性ID      |
| `is_required`    | TINYINT UNSIGNED | 是否必须填写    |
| `value_type`     | TINYINT          | 值类型       |
| `value_limit`    | TINYINT          | 值限制       |
| `value_range`    | VARCHAR(512)     | 取值范围:JSON |
| `format_type`    | TINYINT          | 内容格式校验类型  |
| `format_rule`    | VARCHAR(512)     | 内容格式校验规则  |
| `validated_type` | TINYINT          | 业务校验类型    |
| `validated_rule` | VARCHAR(512)     | 业务校验规则    |
| `create_time`    | DATETIME         | 创建时间      |
| `update_time`    | DATETIME         | 最后更新时间    |

配置项属性：`dad_config_attribute`

| 字段            | 类型              | 描述     |
|---------------|-----------------|--------|
| `id`          | BIGINT UNSIGNED | 主键ID   |
| `key`         | VARCHAR(20)     | 键      |
| `name`        | VARCHAR(20)     | 名称     |
| `description` | VARCHAR(255)    | 描述     |
| `create_time` | DATETIME        | 创建时间   |
| `update_time` | DATETIME        | 最后更新时间 |

配置项`INT`值：`dad_config_value_int`

| 字段             | 类型              | 描述     |
|----------------|-----------------|--------|
| `id`           | BIGINT UNSIGNED | 主键ID   |
| `config_id`    | BIGINT UNSIGNED | 属性ID   |
| `attribute_id` | BIGINT UNSIGNED | 属性ID   |
| `value`        | INT             | 值      |
| `is_null`      | TINYINT         | 是否为空   |
| `create_time`  | DATETIME        | 创建时间   |
| `update_time`  | DATETIME        | 最后更新时间 |

配置项`LONG`值：`dad_config_value_long`

| 字段             | 类型              | 描述     |
|----------------|-----------------|--------|
| `id`           | BIGINT UNSIGNED | 主键ID   |
| `config_id`    | BIGINT UNSIGNED | 属性ID   |
| `attribute_id` | BIGINT UNSIGNED | 属性ID   |
| `value`        | BIGINT          | 值      |
| `is_null`      | TINYINT         | 是否为空   |
| `create_time`  | DATETIME        | 创建时间   |
| `update_time`  | DATETIME        | 最后更新时间 |

配置项`STRING`值：`dad_config_value_string`

| 字段             | 类型              | 描述     |
|----------------|-----------------|--------|
| `id`           | BIGINT UNSIGNED | 主键ID   |
| `config_id`    | BIGINT UNSIGNED | 属性ID   |
| `attribute_id` | BIGINT UNSIGNED | 属性ID   |
| `value`        | TEXT            | 值      |
| `is_null`      | TINYINT         | 是否为空   |
| `create_time`  | DATETIME        | 创建时间   |
| `update_time`  | DATETIME        | 最后更新时间 |

## 参考

- [Wiki《Entity–attribute–value model》](https://en.wikipedia.org/wiki/Entity%E2%80%93attribute%E2%80%93value_model)
 