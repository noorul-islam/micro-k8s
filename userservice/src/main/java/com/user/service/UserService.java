package com.user.service;

import com.user.VO.Department;
import com.user.VO.ResponseTemplateVO;
import com.user.entity.User;
import com.user.repository.UserRepository;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    public User saveUser(User user) {
        log.info("Inside saveUser of UserService");
        return userRepository.save(user);
    }

    private static final String USER_SERVICE = "USER-SERVICE";
    private static final String DEPARTMENT_SERVICE = "DEPARTMENT-SERVICE";
    
    //private Long lUserId;
    User user;
    @CircuitBreaker(name = USER_SERVICE, fallbackMethod = "fallbackMethodDept")
    //@Retry(name = USER_SERVICE)
    public ResponseTemplateVO getUserWithDepartment(Long userId) {
        log.info("Inside getUserWithDepartment of UserService");
        ResponseTemplateVO vo = new ResponseTemplateVO();
        //lUserId = userId;
        user = userRepository.findByUserId(userId);

        Department department =
                restTemplate.getForObject("http://" + DEPARTMENT_SERVICE + "/departments/" + user.getDepartmentId()
                        ,Department.class);

        vo.setUser(user);
        vo.setDepartment(department);

        return  vo;
    }
    
    public ResponseTemplateVO fallbackMethodDept(Exception e) {
    	
    	ResponseTemplateVO vo = new ResponseTemplateVO();
    	vo.setUser(user);
    	
    	vo.setStr("DEPARTMENT_SERVICE is currently down. Please Try later");
    	
    	return vo;
    }
}
