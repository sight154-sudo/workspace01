package com.huawei.graphql;

import com.huawei.graphql.entity.Card;
import com.huawei.graphql.entity.User;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.apache.commons.io.IOUtils;

public class GraphQLSDLDemo {
    public static void main(String[] args) throws Exception {
        //读取graphqls文件
        String fileName = "user.graphqls";
        String fileContent = IOUtils.toString(GraphQLSDLDemo.class.getClassLoader().getResource(fileName), "UTF-8");
        //解析文件
        TypeDefinitionRegistry typeDefinitionRegistry = new SchemaParser().parse(fileContent);

        RuntimeWiring wiring = RuntimeWiring.newRuntimeWiring()
                .type("UserQuery", builder ->
                        builder.dataFetcher("queryUserById", environment -> {
                            //解析请求参数，根据业务返回结果
                            Long id = Long.parseLong(environment.getArgument("input"));
                            Card card = new Card("123456", id);
                            return new User(18, id, "user0" + id, card);
                        })
                )
                .build();

        GraphQLSchema graphQLSchema = new SchemaGenerator().makeExecutableSchema(typeDefinitionRegistry, wiring);

        GraphQL graphQL = GraphQL.newGraphQL(graphQLSchema).build();

        String query = "{queryUserById(id:15){id,name,age,card{cardNumber,userId}}}";
        ExecutionResult result = graphQL.execute(query);

        System.out.println("query: " + query);
        System.out.println(result.toSpecification());
    }
}