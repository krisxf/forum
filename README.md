# 项目背景

# 所需环境

- redis
- rabbitmq
- minio
- mysql
- mail

# 实现功能

## 安全效验
- 注册时使用第三方的验证码效验
- 自定义注解实现的接口限流
- jwt进行用户的认证
- RBAC 权限管理设计

## 用户功能

- 登入登出
- 查询，评论文章
- 查看已评论，已收藏，已发布的信息
- 点赞，取消赞

## 管理员功能

- 用户的管理
- 首页轮播图的管理
- 文章管理
- 分类管理
- 评论管理
- 标签管理

## 其他

- 使用消息队列RabbitMQ进行异步通知(包括邮件发送验证码，文章被评论通知)
- 使用Minio进行图片的存储
- Redis 实现排行榜
- 敏感词汇过滤

# 待完善

## 功能

- [x] 标签管理
- [ ] 实时通讯（使用消息对列 或者 webSocket）
- [ ] Minio进行大文件的断点续传，传gif，视频之类的
- [ ] 关注功能
- [ ] RBAC 权限管理设计

## 优化
- [x] 需要添加一个表用来存储已点赞信息
- [ ] 使用Redis对点赞进行缓存
- [ ] Redis对浏览量进行定时持久化
- [x] Redis实现排行榜时的数据不一致问题