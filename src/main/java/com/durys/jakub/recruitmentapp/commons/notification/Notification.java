package com.durys.jakub.recruitmentapp.commons.notification;

import com.durys.jakub.recruitmentapp.sharedkernel.TenantId;

import java.util.Set;

public record Notification(TenantId tenantId, String type, String content, Set<NotificationType> types) {
}
