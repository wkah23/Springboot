package com.study.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.springboot.dao.ITransaction1DAO;
import com.study.springboot.dao.ITransaction2DAO;

// @Service 어노테이션을 지정하면 이 클래스를 빈으로 사용한다 라는 의미
@Service
public class BuyTicketService implements IBuyTicketService {
	// new는 사용하지 않고 자동으로 대입
	@Autowired	// 자동주입을 받아 변수로 만든다.
	ITransaction1DAO transaction1;
	@Autowired
	ITransaction2DAO transaction2;
	
	public int buy(String consumerId, int amount, String error) {
		/*
			상황 : 현장에서는 표가 발행되었는데 전산에 등록이 안될 때...
		 */
		try {
			// DAO 변수인 transaction1 에서 pay() 메서드를 호출
			transaction1.pay(consumerId, amount);	// 현장 판매처 상황
			// 의도적 에러 발생
			if (error.equals("1")) {int n = 10 / 0;}
			// DAO 변수인 transaction2 에서 pay() 메서드를 호출
			transaction2.pay(consumerId, amount);	// 회계장부 상황
			/*
			 	에러1이 들어오면 이 테이블에는 저장이 안된다.
			 	이런일이 생기면 안되므로 트랜잭션이 필요하다.
			 	transaction1 부터 저장이 안되고 롤백을 해야함.
			 */
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}
}
