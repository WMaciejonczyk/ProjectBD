package persistance.archives;

import persistance.entity.ServiceArchivesEntity;

import java.util.List;

public interface IArchives {
    void addToArchives(ServiceArchivesEntity serviceArchivesEntity);
    List<ServiceArchivesEntity> getAllServices();
    ServiceArchivesEntity getOneService(int id);
}
