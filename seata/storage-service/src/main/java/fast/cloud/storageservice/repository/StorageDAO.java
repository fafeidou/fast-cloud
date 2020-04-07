package fast.cloud.storageservice.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import fast.cloud.storageservice.entity.Storage;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @Classname StorageDAO
 * @Description TODO
 * @Date 2020/4/4 21:16
 * @Created by qinfuxiang
 */
@Mapper
@Repository
public interface StorageDAO extends BaseMapper<Storage> {

}
