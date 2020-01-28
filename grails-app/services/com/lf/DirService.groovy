package com.lf

import grails.gorm.services.Service

@Service(Dir)
interface DirService {

    Dir get(Serializable id)

    List<Dir> list(Map args)

    Long count()

    void delete(Serializable id)

    Dir save(Dir dir)

}