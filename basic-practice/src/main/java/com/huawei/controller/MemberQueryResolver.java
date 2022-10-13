package com.huawei.controller;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.google.common.collect.Lists;
import com.huawei.model.Member;
import com.huawei.model.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MemberQueryResolver implements GraphQLQueryResolver {

    private List<Member> lists = Lists.newArrayList(
            new Member("1","zhangsan",12),
            new Member("2","lisi",14),
            new Member("3","wang",17));


    public Member getMember(String memberId) {
        List<Member> collect = lists.stream().filter((list) -> list.getMemberId().equals(memberId)).limit(1).collect(Collectors.toList());
        return collect.get(0);
    }

    public Member getMemberByInput(Param param) {
        List<Member> collect = lists.stream().filter((list) -> list.getMemberId().equals(param.getMemberId())).limit(1).collect(Collectors.toList());
        return collect.get(0);
    }
}