package com.lemon.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lemon.server.mapper.PositionMapper;
import com.lemon.server.model.Position;
import com.lemon.server.service.IPositionService;
import org.springframework.stereotype.Service;

/**
 *  职称服务实现类
 *
 * @author lemon
 * @since 2021-04-07
 */
@Service
public class PositionServiceImpl extends ServiceImpl<PositionMapper, Position> implements IPositionService {

}
