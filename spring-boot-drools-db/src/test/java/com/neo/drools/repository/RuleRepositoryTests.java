package com.neo.drools.repository;

import com.neo.drools.model.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RuleRepositoryTests {

    @Autowired
    private RuleRepository ruleRepository;


    @Test
    public void test() throws Exception {

        Rule rule=ruleRepository.findByRuleKey("score");

        if(rule==null){
            System.out.println("rule = is null");
        }else {
            System.out.println("rule = " + rule.toString());
        }

	}



    @Test
    public void testRandom() throws Exception {

        System.out.println(generateRandom(6));

    }


    public String generateRandom(int num) {
        String chars = "0123456789";
        StringBuffer number=new StringBuffer();
        for (int i = 0; i < num; i++) {
            int rand = (int) (Math.random() * 10);
            number=number.append(chars.charAt(rand));
        }
        return number.toString();
    }

}