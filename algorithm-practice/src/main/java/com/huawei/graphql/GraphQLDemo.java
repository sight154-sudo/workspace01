package com.huawei.graphql;

import com.huawei.graphql.entity.User;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.Scalars;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLList;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLSchema;
import graphql.schema.StaticDataFetcher;

public class GraphQLDemo {
    public static void main(String[] args) {
        /*
            定义GraphQL对象,等同于schema中定义的
            type User {
                id:ID
                age:Int
                name:String
            }
        */
        GraphQLObjectType userObjectType = GraphQLObjectType.newObject()
                .name("User")
                .field(GraphQLFieldDefinition.newFieldDefinition().name("id").type(Scalars.GraphQLLong))
                .field(GraphQLFieldDefinition.newFieldDefinition().name("age").type(Scalars.GraphQLInt))
                .field(GraphQLFieldDefinition.newFieldDefinition().name("name").type(Scalars.GraphQLString))
                .field(GraphQLFieldDefinition.newFieldDefinition().name("address").type(new GraphQLList(Scalars.GraphQLString)))
                .build();
        /*
            queryUser : User 指定对象及参数类型
            等同于在GraphQL中定义一个无参方法 queryUser，返回值为User
            queryUser:User
            dataFetcher指定了响应的数据集，这个demo里使用了静态写入的方式
         */
        GraphQLFieldDefinition userFileldDefinition = GraphQLFieldDefinition.newFieldDefinition()
                .name("queryUser")
                .type(userObjectType)
                //静态数据
                .dataFetcher(new StaticDataFetcher(new User(19, 2, "CLC", new String[]{"aa","bbb"})))
                .build();
        /*
            type UserQuery 定义查询类型

            对应的graphQL为：
                type UserQuery {
                    queryUser:User
                }
         */
        GraphQLObjectType userQueryObjectType = GraphQLObjectType.newObject()
                .name("UserQuery")
                .field(userFileldDefinition)
                .build();
        /*
            Schema 定义查询
            定义了query的root类型
            对应的GraphQL语法为：
               schema {
                    query:UserQuery
               }
         */
        GraphQLSchema qlSchema = GraphQLSchema.newSchema().query(userQueryObjectType).build();

        //构建一个GraphQl对象，执行逻辑都在此处进行
        GraphQL graphQL = GraphQL.newGraphQL(qlSchema).build();

        //模拟客户端传入查询脚本，方法名queryUser，获取响应值为 id name age
        String query = "{queryUser{id name age address}}";
        // 执行业务操作逻辑，获取返回值
        ExecutionResult result = graphQL.execute(query);

        System.out.println(result.toSpecification());
    }
}