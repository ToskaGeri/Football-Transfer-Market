package com.football_transfer_market.spec;

import com.football_transfer_market.Models.Player;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class PlayerSpec {

    public static Specification<Player> getSpec(String name,
                                                String surname,
                                                int age ,
                                                boolean isFreeAgent,
                                                String nation,
                                                String position,
                                                int price,
                                                String teamName){
        return ((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (name != null && !(name.isEmpty())){
                predicates.add(criteriaBuilder.equal(root.get("playerName"),name));
            }
            if (surname != null && !(surname.isEmpty())){
                predicates.add(criteriaBuilder.equal(root.get("playerSurname"),surname));
            }
            if (age != 0){
                predicates.add(criteriaBuilder.equal(root.get("age"),age));
            }
            if (isFreeAgent){
                predicates.add(criteriaBuilder.equal(root.get("isFreeAgent"), true));
            }
            if (nation != null && !(nation.isEmpty())){
                predicates.add(criteriaBuilder.equal(root.get("playerNation"),nation));
            }
            if (position != null && !(position.isEmpty())){
                predicates.add(criteriaBuilder.equal(root.get("playerPosition"),position));
            }
            if (teamName != null && !(teamName.isEmpty())){
                predicates.add(criteriaBuilder.equal(root.get("teamName"),teamName));
            }
            if (!String.valueOf(price).isEmpty()){
                predicates.add(criteriaBuilder.equal(root.get("playerPrice"),price));
            }

            return criteriaBuilder.and(predicates.toArray(predicates.toArray(new Predicate[0])));
        });
    }
}
