package com.nt.binding;

import lombok.Data;

@Data
public class DashboardResponseDTO {
private Long totalEnqCnt;
private Long openEnqCnt;
private Long enrolledEnqCnt;
private Long lostEnqCnt;
}
