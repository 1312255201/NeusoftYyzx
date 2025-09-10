package cn.gugufish.yyzx.utils;

import org.springframework.stereotype.Component;

/**
 * 雪花算法分布式ID生成器
 * 基于Twitter开源的Snowflake算法实现，用于生成全局唯一的64位长整型ID
 * 
 * 算法原理：
 * 雪花算法生成的ID是一个64位的长整型数字，由以下几部分组成：
 * - 1位符号位：固定为0，保证生成的ID为正数
 * - 41位时间戳：存储毫秒级时间戳与起始时间的差值，可使用约69年
 * - 5位数据中心ID：支持32个数据中心
 * - 5位工作机器ID：每个数据中心支持32台机器
 * - 12位序列号：同一毫秒内支持4096个不同序列号
 * 
 * ID结构示意图：
 * +----------+----------+----------+----------+
 * |0|       timestamp    |dataCenterId|workerId|sequence|
 * +----------+----------+----------+----------+
 *  1    41              5         5        12
 * 
 * 特点优势：
 * - 高性能：每秒可生成409.6万个ID，满足高并发需求
 * - 趋势递增：基于时间戳生成，保证ID整体趋势递增
 * - 无依赖：不依赖数据库或第三方服务，避免单点故障
 * - 灵活配置：支持多数据中心和多机器部署
 * - 信息丰富：ID中包含时间信息，便于问题排查
 * 
 * 使用场景：
 * - 分布式系统中的唯一主键生成
 * - 订单号、交易流水号等业务ID
 * - 日志追踪ID、请求链路ID
 * - 分库分表场景下的全局ID
 * 
 * @author GuguFish
 */
@Component
public class SnowflakeIdGenerator {
    
    /**
     * 起始时间戳（2023-08-04 00:00:00）
     * 用作时间戳计算的基准点，减少存储空间占用
     * 可根据项目启动时间进行调整，延长算法可用时间
     */
    private static final long START_TIMESTAMP = 1691087910202L;

    /**
     * 数据中心ID所占位数（5位）
     * 支持0-31共32个数据中心
     */
    private static final long DATA_CENTER_ID_BITS = 5L;
    
    /**
     * 工作机器ID所占位数（5位）
     * 每个数据中心支持0-31共32台机器
     */
    private static final long WORKER_ID_BITS = 5L;
    
    /**
     * 序列号所占位数（12位）
     * 同一毫秒内支持0-4095共4096个不同序列号
     */
    private static final long SEQUENCE_BITS = 12L;

    /**
     * 数据中心ID的最大值（31）
     * 通过位运算计算：~(-1L << 5) = 31
     */
    private static final long MAX_DATA_CENTER_ID = ~(-1L << DATA_CENTER_ID_BITS);
    
    /**
     * 工作机器ID的最大值（31）
     * 通过位运算计算：~(-1L << 5) = 31
     */
    private static final long MAX_WORKER_ID = ~(-1L << WORKER_ID_BITS);
    
    /**
     * 序列号的最大值（4095）
     * 通过位运算计算：~(-1L << 12) = 4095
     */
    private static final long MAX_SEQUENCE = ~(-1L << SEQUENCE_BITS);

    /**
     * 工作机器ID左移位数（12位）
     * 为序列号预留空间
     */
    private static final long WORKER_ID_SHIFT = SEQUENCE_BITS;
    
    /**
     * 数据中心ID左移位数（17位）
     * 为序列号和工作机器ID预留空间
     */
    private static final long DATA_CENTER_ID_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS;
    
    /**
     * 时间戳左移位数（22位）
     * 为序列号、工作机器ID和数据中心ID预留空间
     */
    private static final long TIMESTAMP_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS + DATA_CENTER_ID_BITS;

    /**
     * 数据中心ID（0-31）
     * 用于标识不同的数据中心，防止不同数据中心生成相同ID
     */
    private final long dataCenterId;
    
    /**
     * 工作机器ID（0-31）
     * 用于标识同一数据中心内的不同机器，防止同一数据中心内生成相同ID
     */
    private final long workerId;
    
    /**
     * 上一次生成ID的时间戳
     * 用于检测时钟回拨和控制序列号重置
     */
    private long lastTimestamp = -1L;
    
    /**
     * 序列号（0-4095）
     * 同一毫秒内的递增序列，防止同一毫秒内生成相同ID
     */
    private long sequence = 0L;

    /**
     * 默认构造函数
     * 使用默认的数据中心ID=1，工作机器ID=1
     * 适用于单机部署或测试环境
     */
    public SnowflakeIdGenerator(){
        this(1, 1);
    }

    /**
     * 带参数的构造函数
     * 指定数据中心ID和工作机器ID，适用于分布式部署
     * 
     * @param dataCenterId 数据中心ID（0-31）
     * @param workerId 工作机器ID（0-31）
     * @throws IllegalArgumentException 当ID超出有效范围时抛出异常
     */
    private SnowflakeIdGenerator(long dataCenterId, long workerId) {
        // 验证数据中心ID的有效性
        if (dataCenterId > MAX_DATA_CENTER_ID || dataCenterId < 0) {
            throw new IllegalArgumentException("Data center ID can't be greater than " + MAX_DATA_CENTER_ID + " or less than 0");
        }
        // 验证工作机器ID的有效性
        if (workerId > MAX_WORKER_ID || workerId < 0) {
            throw new IllegalArgumentException("Worker ID can't be greater than " + MAX_WORKER_ID + " or less than 0");
        }
        this.dataCenterId = dataCenterId;
        this.workerId = workerId;
    }

    /**
     * 生成下一个唯一ID
     * 使用synchronized确保线程安全，防止并发情况下生成重复ID
     * 
     * 生成流程：
     * 1. 获取当前时间戳
     * 2. 检查时钟是否回拨，如果回拨则抛出异常
     * 3. 如果在同一毫秒内，序列号递增
     * 4. 如果序列号溢出，等待下一毫秒
     * 5. 如果时间戳变化，重置序列号为0
     * 6. 组装最终的64位ID并返回
     * 
     * @return 64位唯一ID
     * @throws IllegalStateException 当检测到时钟回拨时抛出异常
     */
    public synchronized long nextId() {
        // 获取当前时间戳
        long timestamp = getCurrentTimestamp();
        
        // 检查时钟回拨：如果当前时间小于上次记录的时间，说明发生了时钟回拨
        if (timestamp < lastTimestamp) {
            throw new IllegalStateException("Clock moved backwards. Refusing to generate ID.");
        }
        
        // 如果在同一毫秒内生成ID
        if (timestamp == lastTimestamp) {
            // 序列号递增，使用位运算防止溢出
            sequence = (sequence + 1) & MAX_SEQUENCE;
            
            // 如果序列号溢出（达到4096），等待下一毫秒
            if (sequence == 0) {
                timestamp = getNextTimestamp(lastTimestamp);
            }
        } else {
            // 时间戳改变，重置序列号为0
            sequence = 0L;
        }
        
        // 更新上次生成ID的时间戳
        lastTimestamp = timestamp;
        
        // 组装64位ID：时间戳 + 数据中心ID + 工作机器ID + 序列号
        return ((timestamp - START_TIMESTAMP) << TIMESTAMP_SHIFT) |
                (dataCenterId << DATA_CENTER_ID_SHIFT) |
                (workerId << WORKER_ID_SHIFT) |
                sequence;
    }

    /**
     * 获取当前时间戳（毫秒）
     * 
     * @return 当前系统时间的毫秒时间戳
     */
    private long getCurrentTimestamp() {
        return System.currentTimeMillis();
    }

    /**
     * 获取下一个时间戳
     * 当序列号在同一毫秒内用尽时，通过自旋等待下一毫秒
     * 
     * @param lastTimestamp 上一次的时间戳
     * @return 下一个可用的时间戳（比lastTimestamp大）
     */
    private long getNextTimestamp(long lastTimestamp) {
        long timestamp = getCurrentTimestamp();
        // 自旋等待，直到获取到下一个毫秒的时间戳
        while (timestamp <= lastTimestamp) {
            timestamp = getCurrentTimestamp();
        }
        return timestamp;
    }
}
