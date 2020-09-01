### SSH(Spring SpringMVC Hibernate)框架开发的简易在线留言管理系统

### 演示站
- [gbook.licyun.com](https://gbook.licyun.com)
- [用户登录](https://gbook.licyun.com/user/login) 用户帐号：licyun@qq.com  密码：123456
- [管理员登录](https://gbook.licyun.com/admin/login) 管理员帐号： licyun@outlook.com 密码：123456

### 功能介绍：
- 系统分为2个角色，用户和管理员。
- 用户 ：未注册用户可以观看所有留言，但是提交留言需要注册登录，登录后可以修改个人信息：邮箱，用户名，头像和密码。
- 管理员：管理员和用户和用户公用一张表，管理员在表中的type字段为2，用户的type字段为1，管理员可以删除用户和留言，同时也可以编辑用户信息。

### 系统结构：
- controller:控制层 AdminController和UserController分别控制管理员和用户的请求转发。
- dao: 封装操作数据库的方法,抽象类BaseDao封装了gethibernateTemplate常用的操作,UserDao和MessageDao分别封装了用户管理员对数据库的操作和留言类对数据库的操作。
- interceptor: 拦截层，配置当用户和管理员未登录时不允许访问其他页面，通过session判断并跳转到登录页
- model: 持久层，定义了User和Message两个持久化类，使用springmvc的注解和数据库对应的user和message表建立对应的映射。
- service: 服务层，MessageService和UserService封装了用户管理员和留言的一些业务逻辑处理。
- util: 工具层，UploadImg图片上传，Validate输入表单验证及错误返回。
- vo: 服务层对bean的操作。避免和持久层重复。

### 配置信息
- web.xml  注册SpringMVC默认dispacher转发器及mvc-dispacher-servlet.xml，添加hibernate拦截器，设置输入编码和application.xml hibernate配置文件。
- mvc-dispacher-servlet.xml 扫描包注册bean的自动装载，配置国际化资源，返回对应物理逻辑视图，配置上传文件大小和格式，配置静态文件访问，配置intercepter拦截器实现用户和管理员登录拦截。
- application.xml 扫描除Controller外的bean包自动装载,配置C3P0数据源连接数据库信息，定义sessionFactory工厂并为其注入datasouce数据源，定义gethibernate数据库操作类 和 transaction事务管理 并为其注入SessionFactory， 声明事务采用注解方式。

## 首页分页说明
- 首页采用ajax获取json分页数据，让更多业务逻辑在客户端处理，减少服务器压力。
- messageDao层使用抽象类BaseDao的的分页查询，并配置jackson包返回json对象的分页数据。
- 通过js获取首页所有分页class属性，并通过js闭包循环为页面a标签添加监听。

