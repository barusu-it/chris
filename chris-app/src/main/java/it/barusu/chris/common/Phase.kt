package it.barusu.chris.common

/**
 * 阶段
 */
enum class Phase(val description: String) {
    CREATED("已创建"),
    PREPARED("已准备"),
    CONFIRMED("已确认"),
    EXECUTED("已执行"),
    ;
}