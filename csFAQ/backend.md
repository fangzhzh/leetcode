## 线程安全集合
class AppointmentServiceImp(
    private val vetService: VetService,
    private val petService: PetService,
    private val petOwnerService: PetOwnerService,
    private val appointmentRepository: AppointmentRepository
) : AppointmentService {
    // 使用线程安全集合
    private val appointmentCache = Collections.synchronizedList(mutableListOf<Appointment>())
    
    // ... 其他方法 ...
}
```

**性能分析**：
- 适用请求量级：中等，约 1,000-10,000 QPS
- 优点：实现简单，适合单机部署
- 缺点：随着并发增加，锁竞争会导致性能下降
- 适用场景：中小型诊所，并发预约请求不多

## 2. 数据库事务


override fun scheudleAppointment(
    petId: UUID,
    vetId: UUID,
    startTime: LocalDateTime,
    endTime: LocalDateTime
): Appointment? {
    return transactionManager.executeInTransaction {
        val vet = vetService.getVet(vetId)
        
        // 检查可用时间段
        var timeSlot: TimeSlot? = null
        for(avail in vet.availabilities) {
            if(avail.startTime <= startTime && avail.endTime >= endTime) {
                timeSlot = avail
                break
            }
        }
        
        timeSlot?.let {
            val appointment = Appointment(
                vetId = vetId,
                petId = petId,
                startTime = startTime,
                endTime = endTime,
            )
            appointmentRepository.saveAppointment(appointment)
            appointment
        }
    }
}
```

**性能分析**：
- 适用请求量级：高，约 10,000-50,000 QPS（取决于数据库性能）
- 优点：保证数据一致性，适合分布式系统
- 缺点：数据库可能成为瓶颈
- 适用场景：大型连锁宠物医院，多个分支机构共享数据

## 3. 锁机制
class AppointmentServiceImp(
    private val vetService: VetService,
    private val petService: PetService,
    private val petOwnerService: PetOwnerService,
    private val appointmentRepository: AppointmentRepository
) : AppointmentService {
    private val locks = ConcurrentHashMap<UUID, ReentrantLock>()
    
    override fun scheudleAppointment(
        petId: UUID,
        vetId: UUID,
        startTime: LocalDateTime,
        endTime: LocalDateTime
    ): Appointment? {
        // 获取或创建特定兽医的锁
        val lock = locks.computeIfAbsent(vetId) { ReentrantLock() }
        
        lock.lock()
        try {
            // 原有预约逻辑
            val vet = vetService.getVet(vetId)
            
            var timeSlot: TimeSlot? = null
            for(avail in vet.availabilities) {
                if(avail.startTime <= startTime && avail.endTime >= endTime) {
                    timeSlot = avail
                    break
                }
            }
            
            return timeSlot?.let {
                val appointment = Appointment(
                    vetId = vetId,
                    petId = petId,
                    startTime = startTime,
                    endTime = endTime,
                )
                appointmentRepository.saveAppointment(appointment)
                appointment
            }
        } finally {
            lock.unlock()
        }
    }
}
```

**性能分析**：
- 适用请求量级：中等，约 5,000-20,000 QPS
- 优点：细粒度锁控制，可以针对特定兽医进行锁定
- 缺点：锁管理复杂，可能导致死锁
- 适用场景：中大型宠物医院，不同兽医的预约互不影响

## 4. 框架支持

// 使用Spring框架的@Transactional注解
@Service
class AppointmentServiceImp(
    private val vetService: VetService,
    private val petService: PetService,
    private val petOwnerService: PetOwnerService,
    private val appointmentRepository: AppointmentRepository
) : AppointmentService {
    
    @Transactional
    override fun scheudleAppointment(
        petId: UUID,
        vetId: UUID,
        startTime: LocalDateTime,
        endTime: LocalDateTime
    ): Appointment? {
        // 原有预约逻辑
    }
}
```

**性能分析**：
- 适用请求量级：高，约 20,000-100,000 QPS（配合适当的缓存和数据库优化）
- 优点：开发效率高，内置多种优化
- 缺点：学习成本高，配置复杂
- 适用场景：企业级宠物医疗系统，需要处理大量并发请求

## 5. 事件驱动架构

class AppointmentServiceImp(
    private val vetService: VetService,
    private val petService: PetService,
    private val petOwnerService: PetOwnerService,
    private val appointmentRepository: AppointmentRepository,
    private val eventPublisher: EventPublisher
) : AppointmentService {
    
    override fun scheudleAppointment(
        petId: UUID,
        vetId: UUID,
        startTime: LocalDateTime,
        endTime: LocalDateTime
    ): Appointment? {
        // 原有预约逻辑
        val vet = vetService.getVet(vetId)
        
        var timeSlot: TimeSlot? = null
        for(avail in vet.availabilities) {
            if(avail.startTime <= startTime && avail.endTime >= endTime) {
                timeSlot = avail
                break
            }
        }
        
        return timeSlot?.let {
            val appointment = Appointment(
                vetId = vetId,
                petId = petId,
                startTime = startTime,
                endTime = endTime,
            )
            
            appointmentRepository.saveAppointment(appointment)
            
            // 发布预约创建事件
            eventPublisher.publish(AppointmentCreatedEvent(appointment))
            
            appointment
        }
    }
}
```

**性能分析**：
- 适用请求量级：超高，约 100,000+ QPS
- 优点：极高的可扩展性，系统组件解耦
- 缺点：系统复杂度高，调试困难
- 适用场景：全国性宠物医疗平台，需要处理海量并发请求

## 综合比较

| 方法 | 请求处理能力 | 实现复杂度 | 适用场景 |
|------|------------|-----------|---------|
| 线程安全集合 | 1k-10k QPS | 低 | 小型诊所 |
| 锁机制 | 5K-20K QPS | 中高 | 中型医院 |
| 数据库事务 | 10K-50K QPS | 中 | 连锁医院 |
| 框架支持 | 20K-100K QPS | 高 | 企业级系统 |
| 事件驱动 | 100K+ QPS | 非常高 | 大型平台 |

## 建议

根据您的系统规模和增长预期选择合适的方案：

1. **初创期**：使用线程安全集合或简单的锁机制
2. **成长期**：引入数据库事务和框架支持
3. **成熟期**：考虑事件驱动架构进行系统重构

最佳实践是从简单方案开始，随着业务增长逐步演进架构，避免过度设计。