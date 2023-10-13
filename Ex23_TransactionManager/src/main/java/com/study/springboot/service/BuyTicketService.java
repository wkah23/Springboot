package com.study.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;

import com.study.springboot.dao.ITransaction1DAO;
import com.study.springboot.dao.ITransaction2DAO;

// @Service 어노테이션을 지정하면 이 클래스를 빈으로 사용한다 라는 의미
@Service
public class BuyTicketService implements IBuyTicketService {
	@Autowired	// 자동주입을 받아 변수로 만든다.
	ITransaction1DAO transaction1;
	@Autowired
	ITransaction2DAO transaction2;
	
	// 트랜잭션 처리를 위한 자동주입
	@Autowired
	PlatformTransactionManager transactionManager;
	@Autowired
	TransactionDefinition definition;
	// Definition : 기본 설정 값을 사용하겠다는 의미
	
	public int buy(String consumerId, int amount, String error) {
		TransactionStatus status = transactionManager.getTransaction(definition);
		try {
			transaction1.pay(consumerId, amount);	// 현장 판매처 상황
			// 의도적 에러 발생
			if (error.equals("1")) {int n = 10 / 0;}
			// DAO 변수인 transaction2 에서 pay() 메서드를 호출
			transaction2.pay(consumerId, amount);	// 회계장부 상황
			// 여기로 롤백을 함.
			transactionManager.commit(status);
			return 1;
		} catch (Exception e) {
			System.out.println("[PlatformTransactionManager] Rollback");
			transactionManager.rollback(status);
			return 0;
		}
	}
}
