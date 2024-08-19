package org.demir.dormitory.listener;

import jakarta.persistence.PostPersist;
import jakarta.persistence.PostRemove;
import jakarta.persistence.PostUpdate;
import org.demir.dormitory.common.ApplicationContextProvider;
import org.demir.dormitory.common.BaseEntity;
import org.demir.dormitory.service.LogService;
import org.demir.dormitory.service.impl.LogServiceImpl;
import org.springframework.context.ApplicationContext;

public class EntityListener {

    private LogService logService;

    private LogService getLogService() {
        if (logService == null) {
            ApplicationContext context = ApplicationContextProvider.getApplicationContext();
            logService = context.getBean(LogServiceImpl.class);
        }
        return logService;
    }

    @PostPersist
    public void afterInsert(BaseEntity entity) {
        getLogService().log(entity.getClass().getSimpleName(), entity.getId(), "created");
    }

    @PostRemove
    public void afterDelete(BaseEntity entity) {
        getLogService().log(entity.getClass().getSimpleName(), entity.getId(), "deleted");
    }

    @PostUpdate
    public void afterUpdate(BaseEntity entity) {
        getLogService().log(entity.getClass().getSimpleName(), entity.getId(), "updated");
    }
}
