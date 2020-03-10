##名称：我的社区  
[具体网站地址](https://github.com/hehetiancai/community)  
###参考：  
[示例网站](https://elasticsearch.cn/category-12)  
###帮助：  
[Bootstrap](https://v3.bootcss.com/)  
[GitHub App](https://developer.github.com/apps/building-oauth-apps/creating-an-oauth-app/)
###数据库
```sql
create table USER
(
    ID           INTEGER default NEXT VALUE FOR "PUBLIC"."SYSTEM_SEQUENCE_B70DB47E_FEB2_44C2_BAA8_FE7CB14651E3" auto_increment,
    ACCOUNT_ID   VARCHAR(100),
    NAME         VARCHAR(50),
    TOKEN        CHAR(36),
    GMT_CREATE   BIGINT,
    GMT_MODIFIED BIGINT,
    constraint TABLE_NAME_PK
        primary key (ID)
);

```