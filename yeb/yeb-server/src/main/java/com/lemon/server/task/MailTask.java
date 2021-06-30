package com.lemon.server.task;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.lemon.server.constants.MailConstants;
import com.lemon.server.model.Employee;
import com.lemon.server.model.MailLog;
import com.lemon.server.service.IEmployeeService;
import com.lemon.server.service.IMailLogService;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 邮件发送定时任务
 *
 * @author: Lemon
 * @Date : 2021/6/29 22:40
 */
@Component
public class MailTask {

    @Autowired
    private IMailLogService mailLogService;
    @Autowired
    private IEmployeeService employeeService;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 邮件发送定时任务
     * 每10秒执行一次
     */
    @Scheduled(cron = "0/10 * * * * ?")
    public void mailTask() {
        List<MailLog> mailLogList = mailLogService.list(new QueryWrapper<MailLog>()
                .eq("status", MailConstants.DELIVEERING).lt("tryTime", LocalDateTime.now()));
        if (mailLogList == null || mailLogList.size() == 0) {
            return;
        }
        mailLogList.forEach(mailLog -> {
            // 如果重试次数超过三次，更新状态改为失败，不再重试
            if (mailLog.getCount() >= MailConstants.MAX_TRY_COUNT) {
                mailLogService.update(new UpdateWrapper<MailLog>()
                        .set("status", MailConstants.FAILURE).eq("msgId", mailLog.getMsgId()));
            }

            mailLogService.update(new UpdateWrapper<MailLog>()
                    .set("count", mailLog.getCount() + 1)
                    .set("updateTime", LocalDateTime.now())
                    .set("tryTime", LocalDateTime.now().plusMinutes(MailConstants.MSG_TIMEOUT))
                    .eq("msgId", mailLog.getMsgId()));

            Employee employee = employeeService.getEmployee(mailLog.getEmpId()).get(0);
            // 发送消息
            rabbitTemplate.convertAndSend(MailConstants.MAIL_EXCHANGE_NAME, MailConstants.MAIL_ROUTING_KEY_NAME,
                    employee, new CorrelationData(mailLog.getMsgId()));
        });
    }

}
