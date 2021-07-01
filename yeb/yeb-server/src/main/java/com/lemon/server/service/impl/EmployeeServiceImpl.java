package com.lemon.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lemon.server.constants.MailConstants;
import com.lemon.server.mapper.EmployeeMapper;
import com.lemon.server.mapper.MailLogMapper;
import com.lemon.server.model.Employee;
import com.lemon.server.model.MailLog;
import com.lemon.server.pojo.common.RespBean;
import com.lemon.server.pojo.common.RespPageBean;
import com.lemon.server.service.IEmployeeService;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 员工服务实现类
 *
 * @author lemon
 * @since 2021-04-07
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements IEmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private MailLogMapper mailLogMapper;

    @Override
    public RespPageBean getEmployeePage(Integer currentPage, Integer size, Employee employee, LocalDate[] beginDateScope) {
        // 开启分页
        Page<Employee> page = new Page<>(currentPage, size);
        IPage<Employee> employeeByPage = employeeMapper.getEmployeeByPage(page, employee, beginDateScope);
        RespPageBean respPageBean = new RespPageBean(employeeByPage.getTotal(), employeeByPage.getRecords());
        return respPageBean;
    }

    @Override
    public RespBean maxWorkID() {
        List<Map<String, Object>> maps = employeeMapper.selectMaps(new QueryWrapper<Employee>().select("max(workID)"));
        return RespBean.ok(null, String.format("%08d", Integer.parseInt(maps.get(0).get("max(workID)").toString()) + 1));

    }

    @Override
    public RespBean addEmp(Employee employee) {
        // 处理合同期限,保留两位小数
        LocalDate beginContract = employee.getBeginContract();
        LocalDate endContract = employee.getEndContract();
        long days = beginContract.until(endContract, ChronoUnit.DAYS);
        DecimalFormat decimalFormat = new DecimalFormat("##.00");
        employee.setContractTerm(Double.parseDouble(decimalFormat.format(days / 365.00)));
        if (1 == employeeMapper.insert(employee)) {
            Employee emp = employeeMapper.getEmployee(employee.getId()).get(0);

            // 数据库记录发送的消息
            String msgId = UUID.randomUUID().toString();
            MailLog mailLog = new MailLog();
            mailLog.setMsgId(msgId);
            mailLog.setEmpId(employee.getId());
            mailLog.setStatus(0);
            mailLog.setRouteKey(MailConstants.MAIL_ROUTING_KEY_NAME);
            mailLog.setExchange(MailConstants.MAIL_EXCHANGE_NAME);
            mailLog.setCount(0);
            mailLog.setTryTime(LocalDateTime.now().plusMinutes(MailConstants.MSG_TIMEOUT));
            mailLog.setCreateTime(LocalDateTime.now());
            mailLog.setUpdateTime(LocalDateTime.now());
            mailLogMapper.insert(mailLog);
            // 发送消息
            rabbitTemplate.convertAndSend(MailConstants.MAIL_EXCHANGE_NAME, MailConstants.MAIL_ROUTING_KEY_NAME, emp, new CorrelationData(msgId));
            return RespBean.ok("添加成功！");
        }
        return RespBean.error("添加失败！");
    }

    @Override
    public List<Employee> getEmployee(Integer id) {
        return employeeMapper.getEmployee(id);
    }

    @Override
    public RespPageBean getEmployeeWithSalary(Integer currentPage, Integer size) {
        Page<Employee> page = new Page<>(currentPage, size);
        IPage<Employee> employeeIPage = employeeMapper.getEmployeeWithSalary(page);
        RespPageBean respPageBean = new RespPageBean(employeeIPage.getTotal(), employeeIPage.getRecords());
        return respPageBean;
    }
}
